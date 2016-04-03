/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.user.dao.mongo.IUserUseLogMongoDao;
import cn.xyspace.xysvr.common.user.entity.UserUseLog;
import cn.xyspace.xysvr.common.user.service.IUserUseLogService;

/**
 * 用户使用日志业务逻辑实现。
 * 
 * @author Tanrongrong(2014年12月25日 上午11:06:51)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
@Service
public class UserUseLogServiceImpl implements IUserUseLogService {

    private static final Logger logger = LoggerFactory.getLogger(UserUseLogServiceImpl.class);

    @Inject
    private IUserUseLogMongoDao userUseLogMongoDao;

    @Inject
    private TaskExecutor taskExecutor;

    @Override
    public boolean add(UserUseLog log) {
        boolean success = true;

        try {
            this.taskExecutor.execute(() -> {
                if (this.userUseLogMongoDao.isMongoConnected()) {
                    this.userUseLogMongoDao.insert(log);
                }
                else {
                    logger.error("MongoDB集群故障，不记录用户使用日志！");
                }
            });
        }
        catch (Exception e) {
            success = false;
            logger.error("记录用户使用日志失败！", e);
        }

        return success;
    }

}
