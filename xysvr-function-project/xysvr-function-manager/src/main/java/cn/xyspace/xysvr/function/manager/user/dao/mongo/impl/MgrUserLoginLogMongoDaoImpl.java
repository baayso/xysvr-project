/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao.mongo.impl;

import org.springframework.stereotype.Repository;

import cn.xyspace.xysvr.common.core.dao.mongo.BaseMongoDaoImpl;
import cn.xyspace.xysvr.function.manager.user.dao.mongo.IMgrUserLoginLogMongoDao;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUserLoginLog;

/**
 * 后台用户登录日志数据访问实现（MongoDB）。
 *
 * @author Tanrongrong(2015年3月14日 上午10:50:55)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
@Repository
public class MgrUserLoginLogMongoDaoImpl extends BaseMongoDaoImpl<MgrUserLoginLog> implements IMgrUserLoginLogMongoDao {

}
