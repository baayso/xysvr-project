/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.Clock;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.sysconfig.dao.ISysConfigMybatisDao;
import cn.xyspace.xysvr.common.core.sysconfig.entity.SysConfig;
import cn.xyspace.xysvr.common.core.sysconfig.form.UpdateSysConfigMgrForm;
import cn.xyspace.xysvr.common.core.sysconfig.service.ISysConfigService;
import cn.xyspace.xysvr.common.core.utils.Constants;
import cn.xyspace.xysvr.common.core.utils.ValidateUtils;

/**
 * 配置信息业务逻辑实现。
 *
 * @author WuQiying(2015年1月29日 下午3:03:39)
 * @author ChenFangjie
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    private static final Logger logger = LoggerFactory.getLogger(SysConfigServiceImpl.class);

    private ISysConfigMybatisDao sysConfigMybatisDao = MybatisDaoProxyFactory.createProxy(ISysConfigMybatisDao.class);

    private Clock clock = Clock.DEFAULT;

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean refreshConfig() {
        List<SysConfig> configList = this.findAll();

        if (configList == null || configList.isEmpty()) {
            return false;
        }

        Map<String, String> configMap = new HashMap<String, String>(configList.size());
        configList.forEach(sysConfig -> {
            configMap.put(sysConfig.getName(), sysConfig.getValue());
        });

        // 系统
        String pageSize = configMap.get("page_size");
        String clientAuthorizeSwitch = configMap.get("client_authorize_switch");
        String client_authorize_sign_one = configMap.get("client_authorize_sign_one");
        String client_authorize_sign_two = configMap.get("client_authorize_sign_two");
        String distanceKM = configMap.get("distance_km");
        String historyRetentionDay = configMap.get("history_retention_day");
        String slaveDatabaseSelectSleepSwitch = configMap.get("slave_database_select_sleep_switch");
        String slaveDatabaseSelectSleepMillis = configMap.get("slave_database_select_sleep_millis");

        Config.PAGE_SIZE = ValidateUtils.isUnsignedInt(pageSize) ? Integer.parseUnsignedInt(pageSize) : Config.PAGE_SIZE;

        Config.CLIENT_AUTHORIZE_SWITCH = Boolean.parseBoolean(clientAuthorizeSwitch);
        Config.CLIENT_AUTHORIZE_SIGN_ONE = client_authorize_sign_one;
        Config.CLIENT_AUTHORIZE_SIGN_TWO = client_authorize_sign_two;

        Config.DISTANCE_KM = ValidateUtils.isDouble(distanceKM) ? Double.parseDouble(distanceKM) : Config.DISTANCE_KM;

        Config.HISTORY_RETENTION_DAY = ValidateUtils.isUnsignedInt(historyRetentionDay) ? Integer.parseUnsignedInt(historyRetentionDay) : Config.HISTORY_RETENTION_DAY;

        Config.HISTORY_RETENTION_MILLIS = Constants.HOURS_24_MILLISECONDS * Config.HISTORY_RETENTION_DAY;

        Config.SLAVE_DATABASE_SELECT_SLEEP_SWITCH = Boolean.parseBoolean(slaveDatabaseSelectSleepSwitch);

        Config.SLAVE_DATABASE_SELECT_SLEEP_MILLIS = ValidateUtils.isUnsignedLong(slaveDatabaseSelectSleepMillis) ? Long.parseUnsignedLong(slaveDatabaseSelectSleepMillis)
                : Config.SLAVE_DATABASE_SELECT_SLEEP_MILLIS;

        // 用户
        String userRankingTopNumber = configMap.get("user_ranking_top_number");
        String userInitialMoney = configMap.get("user_initial_money");
        String userInitialBonusPoint = configMap.get("user_initial_bonus_point");
        String userInitialLevel = configMap.get("user_initial_level");

        Config.USER_RANKING_TOP_NUMBER = ValidateUtils.isUnsignedInt(userRankingTopNumber) ? Integer.parseUnsignedInt(userRankingTopNumber) : Config.USER_RANKING_TOP_NUMBER;

        Config.USER_INITIAL_MONEY = ValidateUtils.isUnsignedInt(userInitialMoney) ? Integer.valueOf(userInitialMoney) : Config.USER_INITIAL_MONEY;

        Config.USER_INITIAL_BONUS_POINT = ValidateUtils.isUnsignedInt(userInitialBonusPoint) ? Integer.valueOf(userInitialBonusPoint) : Config.USER_INITIAL_BONUS_POINT;

        Config.USER_INITIAL_LEVEL = ValidateUtils.isUnsignedInt(userInitialLevel) ? Integer.valueOf(userInitialLevel) : Config.USER_INITIAL_LEVEL;

        // 七牛云存储
        Config.QINIU_ACCESS_KEY = configMap.get("qiniu_access_key");
        Config.QINIU_SECRET_KEY = configMap.get("qiniu_secret_key");
        Config.QINIU_PUBLIC_BUCKET_NAME = configMap.get("qiniu_public_bucket_name");
        Config.QINIU_PUBLIC_BUCKET_HTTPS_DOMAIN = configMap.get("qiniu_public_bucket_https_domain");
        Config.QINIU_PUBLIC_BUCKET_DOMAIN = configMap.get("qiniu_public_bucket_domain");
        Config.QINIU_PRIVATE_BUCKET_NAME = configMap.get("qiniu_private_bucket_name");
        Config.QINIU_PRIVATE_BUCKET_HTTPS_DOMAIN = configMap.get("qiniu_private_bucket_https_domain");
        Config.QINIU_PRIVATE_BUCKET_DOMAIN = configMap.get("qiniu_private_bucket_domain");

        //

        logger.info("更新系统配置。{}", configMap);

        return true;
    }

    @Override
    public List<SysConfig> findAll() {
        List<SysConfig> configlist = this.sysConfigMybatisDao.selectAll(null, null);

        return configlist;
    }

    @Override
    public PageInfo<SysConfig> findALLSysConfig(String pageNumber, String pageSize, String type, String name) {
        int mpageNumber = StringUtils.isNumeric(pageNumber) ? Integer.parseUnsignedInt(pageNumber) : 1; // 页码
        int mpageSize = StringUtils.isNumeric(pageSize) ? Integer.parseUnsignedInt(pageSize) : Config.PAGE_SIZE;// 本页显示记录数

        PageHelper.startPage(mpageNumber, mpageSize); // 调用分页方法 紧跟其后的一个select被分页
        List<SysConfig> list = this.sysConfigMybatisDao.selectAll(type, name);

        return new PageInfo<SysConfig>(list);
    }

    @Override
    public boolean updateSysConfig(UpdateSysConfigMgrForm form) {
        boolean updateSysConfig = this.sysConfigMybatisDao.updateSysConfig(form.getId(), form.getValue(), form.getDescriptions(), clock.getCurrentTimeInMillis()) > 0;

        return updateSysConfig;
    }

    @Override
    public SysConfig findSysConfigById(String id) {
        SysConfig sysConfig = this.sysConfigMybatisDao.selectById(id);

        return sysConfig;
    }

}
