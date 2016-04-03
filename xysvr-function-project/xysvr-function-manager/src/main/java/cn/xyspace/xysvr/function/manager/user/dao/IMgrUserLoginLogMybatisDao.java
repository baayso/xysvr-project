/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUserLoginLog;

/**
 * 后台管理用户登录日志数据访问对象接口。
 * 
 * @author ChenFangjie(2015年2月5日 下午1:58:00)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IMgrUserLoginLogMybatisDao extends IBaseMybatisDao {

    /**
     * 新增用户登录日志。
     * 
     * @param entity
     *            用户登录日志实体
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(MgrUserLoginLog entity);

}
