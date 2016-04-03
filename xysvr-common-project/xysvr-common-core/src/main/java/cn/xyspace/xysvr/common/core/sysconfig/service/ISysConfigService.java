/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.service;

import java.util.List;

import cn.xyspace.xysvr.common.core.sysconfig.entity.SysConfig;
import cn.xyspace.xysvr.common.core.sysconfig.form.UpdateSysConfigMgrForm;

import com.github.pagehelper.PageInfo;

/**
 * 配置信息业务逻辑接口。
 *
 * @author WuQiying(2015年1月29日 下午2:33:55)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface ISysConfigService {

    /**
     * 刷新配置信息。
     * 
     * @return 刷新配置成功与否。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean refreshConfig();

    /**
     * 获取所有 配置信息。
     * 
     * @return 配置信息。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<SysConfig> findAll();

    /**
     * 获取配置信息。
     * 
     * @param pageNumber
     *            当前页码。
     * @param pageSize
     *            每页显示的总记录数。
     * @return 配置信息表。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<SysConfig> findALLSysConfig(String pageNumber, String pageSize, String type, String name);

    /**
     * 更新一条配置记录。
     * 
     * @param form
     *            修改页面传过来的数据。
     * @return 是否更新成功（true 成功，false 失败）。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean updateSysConfig(UpdateSysConfigMgrForm form);

    /**
     * 根据主键(id)获取系统配置信息。
     * 
     * @param id
     *            主键Id。
     * @return 一条目标系统配置信息。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public SysConfig findSysConfigById(String id);

}
