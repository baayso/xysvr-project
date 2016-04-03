/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service;

import cn.xyspace.xysvr.common.user.entity.UserLoginLog;

/**
 * 用户登录日志业务逻辑接口。
 * 
 * @author ChenFangjie(2014/12/30 10:54:32)
 * @author WuQiying(2015年2月6日 上午10:35:25)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IUserLoginLogService {

    /**
     * 新增用户登录日志。
     * 
     * @param log
     *            用户登录日志实体对象。
     * @return 新增成功与否。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean add(UserLoginLog log);

}
