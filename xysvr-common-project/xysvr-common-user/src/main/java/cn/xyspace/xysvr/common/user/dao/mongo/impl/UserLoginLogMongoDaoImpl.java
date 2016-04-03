/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.dao.mongo.impl;

import org.springframework.stereotype.Repository;

import cn.xyspace.xysvr.common.core.dao.mongo.BaseMongoDaoImpl;
import cn.xyspace.xysvr.common.user.dao.mongo.IUserLoginLogMongoDao;
import cn.xyspace.xysvr.common.user.entity.UserLoginLog;

/**
 * 用户登录日志数据访问实现（MongoDB）。
 * 
 * @author ChenFangjie(2015年3月4日 下午2:58:10)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Repository
public class UserLoginLogMongoDaoImpl extends BaseMongoDaoImpl<UserLoginLog> implements IUserLoginLogMongoDao {

}
