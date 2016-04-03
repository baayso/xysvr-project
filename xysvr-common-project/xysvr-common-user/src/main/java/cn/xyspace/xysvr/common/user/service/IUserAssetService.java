/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service;

import cn.xyspace.xysvr.common.user.entity.UserAsset;

/**
 * 用户财富业务逻辑接口。
 * 
 * @author ChenFangjie(2014/12/15 18:56:01)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IUserAssetService {

    /**
     * 根据主键名查询用户财富数据（不使用行级排他锁查询）。
     * 
     * @param id
     *            主键
     * @return 返回 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public UserAsset findById(String id);

    /**
     * 根据主键名查询用户财富数据（使用行级排他锁查询）。
     * 
     * @param id
     *            主键
     * @return 返回 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public UserAsset queryById(String id);

}
