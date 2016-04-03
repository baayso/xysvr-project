/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.datasource.DBContextHolder;

/**
 * 切换主从数据库处理器。
 *
 * @author ChenFangjie(2015年2月3日 下午7:02:06)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class DBContextHolderInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DBContextHolderInvocationHandler.class);

    private static final String SELECT = "select";

    // 被代理的对象
    private IBaseMybatisDao target = null;

    public DBContextHolderInvocationHandler(IBaseMybatisDao obj) {
        this.target = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        logger.debug("当前数据库类型（DBType）：{}", DBContextHolder.getCurrentDBType());

        if (method.getName().startsWith(SELECT)) {
            // 切换为从数据库
            DBContextHolder.switchToSlave();
            // DBContextHolder.swithToRead();
            logger.debug("切换数据库类型为从数据库（DBType）：{}", DBContextHolder.getCurrentDBType());
        }
        else {
            // 切换为主数据库
            DBContextHolder.switchToMaster();
            // DBContextHolder.swithToWrite();
            logger.debug("切换数据库类型为主数据库（DBType）：{}", DBContextHolder.getCurrentDBType());
        }

        // 执行被代理的方法
        return method.invoke(this.target, args);
    }

}
