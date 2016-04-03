/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service;

import cn.xyspace.xysvr.common.user.entity.UserUseLog;

/**
 * 用户使用日志业务逻辑接口。
 * 
 * @author Tanrongrong(2014年12月25日 上午11:07:59)
 * @author WuQiying(2015年2月6日 上午11:26:29)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IUserUseLogService {

    /**
     * 新增用户使用日志。
     * 
     * @param log
     *            用户使用日志实体对象。
     * @return 新增成功与否。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean add(UserUseLog log);

}
