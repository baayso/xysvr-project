/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xyspace.xysvr.function.manager.user.Constants;
import cn.xyspace.xysvr.function.manager.user.service.impl.MgrUserServiceImpl;

/**
 * 用于根据当前登录用户身份获取 User信息放入 request； 然后就可以通过 request获取 User。
 * 
 * @author JiangAnran(2015年3月7日 下午3:27:02)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private MgrUserServiceImpl userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
