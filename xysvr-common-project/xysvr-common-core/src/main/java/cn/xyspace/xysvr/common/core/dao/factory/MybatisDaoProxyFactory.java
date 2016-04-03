/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.factory;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.proxy.BaseMybatisDaoDynamicProxy;
import cn.xyspace.xysvr.common.core.utils.SpringUtils;

/**
 * 创建 数据访问对象接口代理类实例 的工厂。
 *
 * @author ChenFangjie(2015年2月4日 上午10:20:55)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class MybatisDaoProxyFactory {

    /**
     * 创建指定数据访问对象接口的代理类实例。
     * 
     * @param clz
     *            指定数据访问对象接口
     * @return 代理类实例
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static <T extends IBaseMybatisDao> T createProxy(Class<T> clz) {
        T instance = SpringUtils.getBean(clz);
        return BaseMybatisDaoDynamicProxy.newProxyInstance(instance);
    }

    /**
     * 创建指定数据访问对象的代理类实例
     * 
     * @param dao
     *            数据访问对象
     * @return 代理类实例
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static <T extends IBaseMybatisDao> T createProxy(IBaseMybatisDao dao) {
        return BaseMybatisDaoDynamicProxy.newProxyInstance(dao);
    }

}
