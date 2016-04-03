/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.websocket.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.mapper.JsonMapper;

import cn.xyspace.xysvr.common.core.exception.ApiServiceException;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.Constants;
import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.user.utils.UserUtils;
import cn.xyspace.xysvr.function.api.msg.entity.MsgCnt;
import cn.xyspace.xysvr.function.api.msg.entity.MsgCnt.MsgType;
import cn.xyspace.xysvr.function.api.msg.form.AddMsgForm;

/**
 * 新私信 WebSocket。
 * 
 * @author ChenFangjie(2015年3月12日 下午7:16:15)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Component
public class MsgWebsocketEndPoint extends TextWebSocketHandler implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MsgWebsocketEndPoint.class);

    private static final ConcurrentMap<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

    private JsonMapper jsonMapper = new JsonMapper();

    @Inject
    private Validator validator;

    @Inject
    private StringRedisTemplate stringRedisTemplate;

    // @Inject
    // private IMsgService msgService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 链接成功后会触发此方法，可在此处对离线消息什么的进行处理
        String username = this.getUsername(session);
        users.put(username, session);

        logger.debug("{}连接/websocket/api/v1/msg成功！", username);

        OperationResult result = new OperationResult();
        result.setStatus(true);
        result.setMessage(new StringBuilder(username).append("链接成功。连接主机为：").append(Config.HOST_NAME).toString());

        this.sendMessage(session, new TextMessage(this.jsonMapper.toJson(result)));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 前端 websocket.send() 会触发此方法
        String jsonStr = message.getPayload();

        logger.debug("{}发送数据(/websocket/api/v1/msg)：{}", this.getUsername(session), jsonStr);

        if (Constants.RECEIVE_KEEP_ALIVE_MSG.equals(jsonStr)) {
            this.sendMessage(session, new TextMessage(Constants.RETURN_KEEP_ALIVE_MSG));
            return;
        }

        AddMsgForm form = this.jsonMapper.fromJson(jsonStr, AddMsgForm.class);

        if (null == form) {
            OperationResult result = new OperationResult(false, SyspromptStatus.ILLEGAL_DATA.value());
            String body = this.jsonMapper.toJson(result);
            this.sendMessage(session, new TextMessage(body));
            return;
        }
        else {
            // 调用JSR303 Bean Validator进行校验
            Set<ConstraintViolation<AddMsgForm>> constraintViolations = this.validator.validate(form);
            if (!constraintViolations.isEmpty()) {
                Map<String, String> errors = BeanValidators.extractPropertyAndMessage(constraintViolations);
                OperationResult result = new OperationResult(false, SyspromptStatus.ILLEGAL_DATA.value());
                result.setMessage(errors);
                String body = this.jsonMapper.toJson(result);
                this.sendMessage(session, new TextMessage(body));
                return;
            }
        }

        form.setUserId((String) session.getAttributes().get(UserUtils.WEBSOCKET_USERID));
        form.setUsername((String) session.getAttributes().get(UserUtils.WEBSOCKET_USERNAME));
        form.setClientIp(session.getRemoteAddress().getAddress().getHostAddress());

        MsgCnt msgCnt = null;
        try {
            // msgCnt = this.msgService.addMsg(form);

            // 封装数据，模拟业务逻辑
            msgCnt = new MsgCnt();
            msgCnt.setFromUname(form.getUsername());
            msgCnt.setToUname(form.getToUser());
            msgCnt.setType(MsgType.valueOf(form.getType())); // 私信类型
            msgCnt.setContent(form.getContent());
            msgCnt.setLongitude(Double.parseDouble(form.getLongitude()));
            msgCnt.setLatitude(Double.parseDouble(form.getLatitude()));
            msgCnt.setCity(form.getCity());
            msgCnt.setPosition(form.getPosition());
            msgCnt.setCtime(System.currentTimeMillis());
        }
        catch (ApiServiceException e) {
            OperationResult result = new OperationResult(false, e.syspromptStatus.value());
            this.sendMessage(session, new TextMessage(this.jsonMapper.toJson(result)));
            return;
        }
        catch (Exception e) {
            OperationResult result = new OperationResult(false, SyspromptStatus.SEND_MESSAGES_FAILURE.value());
            this.sendMessage(session, new TextMessage(this.jsonMapper.toJson(result)));
            return;
        }

        // 反馈发送方
        OperationResult fromUserResult = new OperationResult(true, SyspromptStatus.SEND_MESSAGES_SUCCESS.value());
        String fromUserResultJson = this.jsonMapper.toJson(fromUserResult);
        this.sendMessage(session, new TextMessage(fromUserResultJson));

        // 将消息发送给接收方
        OperationResult toUserResult = new OperationResult(true, msgCnt);
        String toUserResultJson = this.jsonMapper.toJson(toUserResult);
        this.sendMessageToUser(form.getToUser(), toUserResultJson);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            try {
                session.close();
            }
            catch (Exception e) {
                // ignore
            }
        }

        String username = this.getUsername(session);

        logger.warn("非正常关闭/websocket/api/v1/msg连接！异常信息：{}", exception.getMessage());
        logger.debug("{}关闭/websocket/api/v1/msg连接！", username);

        users.remove(username);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username = this.getUsername(session);

        logger.debug("{}关闭/websocket/api/v1/msg连接！", username);

        users.remove(username);
    }

    /**
     * 发送私信给指定的用户。
     * 
     * @param username
     *            用户名
     * @param message
     *            私信
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void sendMessageToUser(String username, String message) {
        WebSocketSession session = users.get(username);

        if (null == session) {
            this.stringRedisTemplate.convertAndSend(Constants.MSG_REDIS_CHANNEL, message);
            return;
        }
        else if (session.isOpen()) {
            this.sendMessage(session, new TextMessage(message));
        }
    }

    // 获取WebSocket Session中的用户名
    private String getUsername(WebSocketSession session) {
        String username = (String) session.getAttributes().get(UserUtils.WEBSOCKET_USERNAME);
        return username;
    }

    // 发送WebSocket消息
    private void sendMessage(WebSocketSession session, WebSocketMessage<?> message) {
        try {
            session.sendMessage(message);
        }
        catch (Exception e) {
            logger.error("发送websocket消息失败！", e);
        }
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = org.apache.commons.codec.binary.StringUtils.newStringUtf8(pattern);

        if (Constants.MSG_REDIS_CHANNEL.equals(channel)) {
            String msg = message.toString();

            OperationResult result = this.jsonMapper.fromJson(msg, OperationResult.class);

            @SuppressWarnings("unchecked")
            Map<String, Object> msgCnt = (LinkedHashMap<String, Object>) result.getData();

            WebSocketSession session = users.get(msgCnt.get("toUname"));

            if (null != session && session.isOpen()) {
                this.sendMessage(session, new TextMessage(msg));
            }
        }
    }

}
