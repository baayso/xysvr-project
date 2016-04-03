/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.sysconfig.service.ISysConfigService;
import cn.xyspace.xysvr.common.core.utils.SpringUtils;

/**
 * 服务器启动时刷新系统配置。
 *
 * @author WuQiying(2015年1月31日 下午2:51:35)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class RefreshSysConfigListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(RefreshSysConfigListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("服务器启动：刷新所有系统配置……");

        try {
            Config.HOST_NAME = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            logger.error("获取主机名出错。", e);
        }

        ISysConfigService sysConfigService = SpringUtils.getBean(ISysConfigService.class);

        boolean success = sysConfigService.refreshConfig();

        if (success) {
            logger.info("刷新系统配置成功。");
        }
        else {
            logger.error("刷新系统配置失败。");
        }

        logger.info("刷新所有系统配置结束。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
