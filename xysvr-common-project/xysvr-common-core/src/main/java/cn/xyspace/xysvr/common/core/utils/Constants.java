/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import org.springside.modules.utils.PropertiesLoader;

/**
 * 常量。
 * 
 * @author ChenFangjie(2014/12/17 12:11:55)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public final class Constants {

    // 让工具类彻底不可以实例化
    private Constants() {
        throw new Error("工具类不可以实例化！");
    }

    // -------------------------------------------------------------------------------------------

    private static final PropertiesLoader PL = new PropertiesLoader("classpath:xysvr.properties");

    // -------------------------------------------------------------------------------------------

    /** 程序中用于发送邮件的E-Mail地址 */
    public static final String FROM_EMAIL = PL.getProperty("FROM_EMAIL");

    /** 服务器名称，用来唯一标识服务器 */
    public static final String SERVER_NAME = PL.getProperty("SERVER_NAME");

    // -------------------------------------------------------------------------------------------

    public static final String UTF_8 = "UTF-8";

    // -------------------------------------------------------------------------------------------

    /** 新私信发送消息的Redis频道 */
    public static final String MSG_REDIS_CHANNEL = "xysvr2:msg:topic";

    /** WebSocket客户端发送给服务端的心跳消息 */
    public static final String RECEIVE_KEEP_ALIVE_MSG = "keepalive:iamhere.";
    /** WebSocket服务端返回给客户端的心跳消息 */
    public static final String RETURN_KEEP_ALIVE_MSG = "keepalive:iamheretoo.";

    // -------------------------------------------------------------------------------------------

    /** 1公里范围 */
    public static final double DISTANCE_1KM = 1.0D;

    /** 一天（24个小时）的毫秒数 */
    public static final long HOURS_24_MILLISECONDS = 86400000L; // 24个小时 等于 86400000毫秒(ms)

    /** 三天（72个小时）的毫秒数 */
    public static final long HOURS_72_MILLISECONDS = HOURS_24_MILLISECONDS * 3; // 24个小时 等于 86400000毫秒(ms)

    /** 5分钟毫秒数 */
    public static final long MINUTES_5_MILLISECONDS = 300000L; // 5分钟 等于 300000毫秒(ms)

    public static final int PICTURE_SIZE_512K = 1 * 512 * 1024;

    public static final int PICTURE_SIZE_1024K = 1 * 1024 * 1024;

    public static final int PICTURE_SIZE_4096K = 4 * 1024 * 1024;

    // -------------------------------------------------------------------------------------------

    /** 升序 */
    public static final String ORDER_BY_ASC = "ASC";

    /** 降序 */
    public static final String ORDER_BY_DESC = "DESC";

    // -------------------------------------------------------------------------------------------

    /** 分表时的表后缀 */
    public static final String TABLE_SUFFIX = "_yyyyMM";

    // -------------------------------------------------------------------------------------------

    /** 星号 */
    public static final String ASTERISK = "*";

    // -------------------------------------------------------------------------------------------

}
