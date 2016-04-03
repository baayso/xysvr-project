/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.datasource;

/**
 * 数据库类型持有者。
 *
 * @author ChenFangjie(2015年2月6日 下午6:41:45)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class DBContextHolder {

    private static final ThreadLocal<DBType> CONTEXT_HOLDER = new ThreadLocal<DBType>();

    /**
     * 切换当前数据库类型为主数据库。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static void switchToMaster() {
        CONTEXT_HOLDER.set(DBType.MASTER);
    }

    /**
     * 切换当前数据库类型为从数据库。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static void switchToSlave() {
        CONTEXT_HOLDER.set(DBType.SLAVE);
    }

    /**
     * 切换当前数据库类型为指定的数据库类型。
     * 
     * @param dbType
     *            指定数据库类型
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static void switchTo(DBType dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    /**
     * 获取当前数据库类型。
     * 
     * @return 当前数据库类型
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static DBType getCurrentDBType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除当前数据库类型。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }

}
