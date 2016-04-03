/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.service;

import java.util.List;

import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptZhcn;
import cn.xyspace.xysvr.common.sysprompt.form.manager.SysprompUpdatetMgrForm;

import com.github.pagehelper.PageInfo;

/**
 * 中文系统提示业务逻辑接口。
 * 
 * @author Tanrongrong(2015年1月24日 下午2:45:21)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface ISyspromptZhcnService {

    /**
     * 查找所有中文系统提示列表。
     * 
     * @return 中文系统提示列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<SyspromptZhcn> findsAll();

    /**
     * 根据条件查找中文系统提示。
     * 
     * @param pageNumber
     *            当前页
     * @param pageSize
     *            页大小
     * @param code
     *            编码
     * @return 中文系统提示列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<SyspromptZhcn> mgrFinds(String pageNumber, String pageSize, Integer code);

    /***
     * 根据ID查找单个中文系统提示详细信息。
     * 
     * @param id
     *            系统提示ID
     * @return 中文系统提示实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public SyspromptZhcn findById(String id);

    /**
     * 根据ID更新中文系统提示。
     * 
     * @param form
     *            更新中文系统提示时客户端提交的数据
     * @return 更新成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean update(SysprompUpdatetMgrForm form);

}
