/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig;

import cn.xyspace.xysvr.common.core.utils.Constants;

/**
 * 系统配置。
 * 
 * @author WuQiying(2015年1月27日 下午5:06:49)
 * @author ChenFangjie
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public final class Config {

    // 让工具类彻底不可以实例化
    private Config() {
        throw new Error("工具类不可以实例化！");
    }

    // private static AtomicInteger nextInc = new AtomicInteger(0);

    /*--------------------------------------------
    |                系        统                                      |
    ============================================*/

    /** 本机主机名，在程序启动时设置 */
    public static String HOST_NAME = "xysvr-1";

    /** 分页时每页显示记录数，不可配置为小于1的数 */
    public static int PAGE_SIZE = 10;

    /** 客户端认证开关 */
    public static boolean CLIENT_AUTHORIZE_SWITCH = false;

    /** 客户端认证校验码一 */
    public static String CLIENT_AUTHORIZE_SIGN_ONE = "";

    /** 客户端认证校验码二 */
    public static String CLIENT_AUTHORIZE_SIGN_TWO = "";

    /** 查找数据的距离（公里） */
    public static double DISTANCE_KM = 1.0D;

    /** 历史数据保留天数，不可配置为小于1的数 */
    public static int HISTORY_RETENTION_DAY = 1;

    /** 历史数据保留毫秒数 */
    public static Long HISTORY_RETENTION_MILLIS = Constants.HOURS_24_MILLISECONDS * Config.HISTORY_RETENTION_DAY;

    /** 从数据库查询休眠开关 */
    public static boolean SLAVE_DATABASE_SELECT_SLEEP_SWITCH = true;

    /** 从数据库查询休眠毫秒数，不可配置为小于1的数 */
    public static long SLAVE_DATABASE_SELECT_SLEEP_MILLIS = 10L;

    /*--------------------------------------------
    |                用        户                                      |
    ============================================*/

    /** 用户排名显示名次数量，不可配置为小于1的数 */
    public static int USER_RANKING_TOP_NUMBER = 50;

    /** 用户初始金币，不可配置为负数 */
    public static Integer USER_INITIAL_MONEY = 500;

    /** 用户初始积分，不可配置为负数 */
    public static Integer USER_INITIAL_BONUS_POINT = 5;

    /** 用户初始等级，不可配置为负数 */
    public static Integer USER_INITIAL_LEVEL = 1;

    /*--------------------------------------------
    |           七      牛      云      存      储                     |
    ============================================*/

    /** 七牛云存储公钥 */
    public static String QINIU_ACCESS_KEY = "";

    /** 七牛云存储私钥 */
    public static String QINIU_SECRET_KEY = "";

    /** 七牛云存储<b>公有空间</b>名称 */
    public static String QINIU_PUBLIC_BUCKET_NAME = "xiaoyao-public";

    /** 七牛云存储<b>公有空间</b>HTTPS域名 */
    public static String QINIU_PUBLIC_BUCKET_HTTPS_DOMAIN = "https://dn-baayso-xiaoyao-public.qbox.me";

    /** 七牛云存储<b>公有空间</b>推荐域名 */
    public static String QINIU_PUBLIC_BUCKET_DOMAIN = "http://7xiqdo.com1.z0.glb.clouddn.com";

    /** 七牛云存储<b>私有空间</b>名称 */
    public static String QINIU_PRIVATE_BUCKET_NAME = "xiaoyao-private";

    /** 七牛云存储<b>私有空间</b>HTTPS域名 */
    public static String QINIU_PRIVATE_BUCKET_HTTPS_DOMAIN = "https://dn-baayso-xiaoyao-private.qbox.me";

    /** 七牛云存储<b>私有空间</b>推荐域名 */
    public static String QINIU_PRIVATE_BUCKET_DOMAIN = "http://7xiqdp.com1.z0.glb.clouddn.com";

    /*--------------------------------------------
    |                                           |
    ============================================*/

}
