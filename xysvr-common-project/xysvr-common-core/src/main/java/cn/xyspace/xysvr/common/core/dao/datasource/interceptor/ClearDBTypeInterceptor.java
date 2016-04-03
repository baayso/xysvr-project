/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.datasource.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.xyspace.xysvr.common.core.dao.datasource.DBContextHolder;

/**
 * 清除当前线程数据库类型拦截器。
 *
 * @author ChenFangjie(2015年2月8日 上午11:04:56)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class ClearDBTypeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClearDBTypeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.debug("当前数据库类型（DBType）：{}", DBContextHolder.getCurrentDBType());

        // 清除当前线程的数据库类型
        DBContextHolder.clear();

        return true;
    }

}
