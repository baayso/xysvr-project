/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service;

import cn.xyspace.xysvr.function.manager.user.entity.MgrUserLoginLog;

/**
 * 后台管理用户登录日志业务逻辑接口。
 * 
 * @author ChenFangjie(2015年2月5日 下午2:46:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IMgrUserLoginLogService {

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
    public boolean add(MgrUserLoginLog log);

}
