/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.shrio.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springside.modules.mapper.JsonMapper;

import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.core.utils.WebUtils;

/**
 * 用户过滤器。只有用户已经通过身份验证或上次登录时有记住我的操作才可以访问功能。 <br>
 * 继承 {@linkplain org.apache.shiro.web.filter.authc.UserFilter}，重写验证失败时执行的方法，使其不在转向登录页面。
 * 
 * @author ChenFangjie(2014/12/14 23:29:21)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class RestUserFilter extends UserFilter {

    private JsonMapper jsonMapper = new JsonMapper();

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        OperationResult result = new OperationResult(false, SyspromptStatus.NOT_LOGGED_ON.value());

        // 401 Unauthorized
        WebUtils.writeJson(httpResponse, jsonMapper.toJson(result), HttpStatus.UNAUTHORIZED.value());

        return false;
    }

}
