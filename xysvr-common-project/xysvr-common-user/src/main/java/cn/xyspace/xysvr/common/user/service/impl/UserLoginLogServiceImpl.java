/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.user.dao.mongo.IUserLoginLogMongoDao;
import cn.xyspace.xysvr.common.user.entity.UserLoginLog;
import cn.xyspace.xysvr.common.user.service.IUserLoginLogService;

/**
 * 用户登录日志业务逻辑接口。
 * 
 * @author ChenFangjie(2014/12/30 10:57:39)
 * @author WuQiying(2015年2月6日 上午10:37:28)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class UserLoginLogServiceImpl implements IUserLoginLogService {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginLogServiceImpl.class);

    @Inject
    private IUserLoginLogMongoDao userLoginLogMongoDao;

    @Inject
    private TaskExecutor taskExecutor;

    @Override
    public boolean add(UserLoginLog log) {
        boolean success = true;

        try {
            this.taskExecutor.execute(() -> {
                if (this.userLoginLogMongoDao.isMongoConnected()) {
                    this.userLoginLogMongoDao.insert(log);
                }
                else {
                    logger.error("MongoDB集群故障，不记录用户登录日志！");
                }
            });
        }
        catch (Exception e) {
            success = false;
            logger.error("记录用户登录日志失败！", e);
        }

        return success;
    }

}
