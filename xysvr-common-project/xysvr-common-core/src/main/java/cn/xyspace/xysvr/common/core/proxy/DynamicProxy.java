/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理类。
 * 
 * @author ChenFangjie(2015年2月4日 上午9:32:22)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class DynamicProxy {

    /**
     * 返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。
     * 
     * @param loader
     *            定义代理类的类加载器
     * @param interfaces
     *            代理类要实现的接口列表
     * @param handler
     *            指派方法调用的调用处理程序
     * @return 一个带有代理类的指定调用处理程序的代理实例，它由指定的类加载器定义，并实现指定的接口
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) {
        // 寻找JoinPoint连接点，AOP框架使用元数据定义
        // if (true) {
        // // 执行一个前置通知
        // }

        // 执行目标并返回结果
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }

}
