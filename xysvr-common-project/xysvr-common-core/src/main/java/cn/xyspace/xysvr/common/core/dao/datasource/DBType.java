/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.datasource;

/**
 * 数据库主从类型枚举。
 *
 * @author ChenFangjie(2015年2月6日 下午6:43:36)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public enum DBType {

    /** 主数据库 */
    MASTER,

    /** 从数据库 */
    SLAVE;

}