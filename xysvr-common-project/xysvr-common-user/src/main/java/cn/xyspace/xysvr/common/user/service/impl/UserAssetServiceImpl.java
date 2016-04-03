/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service.impl;

import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.user.dao.IUserAssetMybatisDao;
import cn.xyspace.xysvr.common.user.entity.UserAsset;
import cn.xyspace.xysvr.common.user.service.IUserAssetService;

/**
 * 用户财富业务逻辑实现。
 * 
 * @author ChenFangjie(2014/12/15 18:59:50)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class UserAssetServiceImpl implements IUserAssetService {

    private IUserAssetMybatisDao userAssetDao = MybatisDaoProxyFactory.createProxy(IUserAssetMybatisDao.class);

    @Override
    public UserAsset findById(String id) {
        return this.userAssetDao.selectById(id);
    }

    @Override
    public UserAsset queryById(String id) {
        return this.userAssetDao.queryById(id);
    }

}
