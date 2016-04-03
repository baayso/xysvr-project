/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;

import cn.xyspace.xysvr.common.user.shrio.ShiroUser;
import cn.xyspace.xysvr.common.user.utils.UserUtils;

/**
 * WebSocket握手拦截器。
 * 
 * @author ChenFangjie(2015年3月13日 下午3:58:17)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UserHandshakeInterceptor extends ShiroSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // request.getHeaders().get("sign");
        ShiroUser user = UserUtils.getCurrentUser();
        if (user != null) {
            String userId = user.getId();
            String username = user.getUsername();
            attributes.put(UserUtils.WEBSOCKET_USERID, userId);
            attributes.put(UserUtils.WEBSOCKET_USERNAME, username);
        }

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
