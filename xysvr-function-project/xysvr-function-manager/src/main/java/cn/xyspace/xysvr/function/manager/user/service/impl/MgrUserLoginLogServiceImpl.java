/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.function.manager.user.dao.mongo.IMgrUserLoginLogMongoDao;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUserLoginLog;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserLoginLogService;

/**
 * 后台管理用户登录日志业务逻辑接口。
 * 
 * @author ChenFangjie(2015年2月5日 下午2:49:38)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class MgrUserLoginLogServiceImpl implements IMgrUserLoginLogService {

    private static final Logger logger = LoggerFactory.getLogger(MgrUserLoginLogServiceImpl.class);

    @Inject
    private IMgrUserLoginLogMongoDao mgrUserLoginLogMongoDao;

    // private IMgrUserLoginLogMybatisDao mgrUserLoginLogDao = MybatisDaoProxyFactory.createProxy(IMgrUserLoginLogMybatisDao.class);

    @Inject
    private TaskExecutor taskExecutor;

    @Override
    public boolean add(MgrUserLoginLog log) {
        boolean success = true;

        try {
            this.taskExecutor.execute(() -> {
                if (this.mgrUserLoginLogMongoDao.isMongoConnected()) {
                    this.mgrUserLoginLogMongoDao.insert(log);
                }
                else {
                    logger.error("MongoDB集群故障，不记录服务端后台管理用户登录日志！");
                }
            });
        }
        catch (Exception e) {
            success = false;
            logger.error("记录服务端后台管理用户登录日志失败！", e);
        }

        return success;
    }

}
