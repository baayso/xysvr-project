/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.task;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyspace.xysvr.common.core.sysconfig.service.ISysConfigService;

/**
 * 定时刷新系统配置。
 *
 * @author WuQiying(2015年1月29日 下午2:04:12)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class SysConfigTimedTask {

    private static final Logger logger = LoggerFactory.getLogger(SysConfigTimedTask.class);

    @Inject
    private ISysConfigService sysConfigService;

    /**
     * 定时刷新系统配置。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public void refreshConfigFromDatabase() {

        boolean success = this.sysConfigService.refreshConfig();

        if (success) {
            logger.info("刷新系统配置成功。");
        }
        else {
            logger.error("刷新系统配置失败。");
        }
    }

}
