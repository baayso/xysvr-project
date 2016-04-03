/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.proxy;

import java.lang.reflect.InvocationHandler;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.handler.DBContextHolderInvocationHandler;
import cn.xyspace.xysvr.common.core.proxy.DynamicProxy;

/**
 * 数据访问对象动态代理类。
 *
 * @author ChenFangjie(2015年2月4日 上午9:44:31)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class BaseMybatisDaoDynamicProxy extends DynamicProxy {

    /**
     * 返回一个数据访问对象接口的代理类实例。
     * 
     * @param dao
     *            数据访问对象接口
     * @return 代理实例
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static <T> T newProxyInstance(IBaseMybatisDao dao) {
        ClassLoader loader = dao.getClass().getClassLoader();
        Class<?>[] interfaces = dao.getClass().getInterfaces();

        InvocationHandler handler = new DBContextHolderInvocationHandler(dao);

        return newProxyInstance(loader, interfaces, handler);
    }

}
