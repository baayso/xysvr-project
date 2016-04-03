/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SpringUtils;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.core.utils.WebUtils;
import cn.xyspace.xysvr.common.tool.token.service.ITokenService;

import com.alibaba.fastjson.JSON;

/**
 * 客户端认证拦截器。
 * 
 * @author ChenFangjie(2015/1/6 9:18:29)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isPass = true;

        if (Config.CLIENT_AUTHORIZE_SWITCH) {
            String sign = request.getHeader("sign");

            ITokenService tokenService = SpringUtils.getBean(ITokenService.class);

            isPass = tokenService.clientAuthentication(sign);

            if (isPass) {
                logger.info("客户端认证通过。");
            }
            else {
                OperationResult result = new OperationResult(false, SyspromptStatus.CLIENT_AUTHENTICATION_FAILURE.value());
                String body = JSON.toJSONString(result);
                WebUtils.writeJson(response, body, HttpStatus.UNAUTHORIZED.value());

                logger.error("请求 {} 时客户端认证失败。", request.getRequestURI());

                return false;
            }
        }
        else {
            logger.warn("未开启客户端认证。");
        }

        return isPass;
    }

}
