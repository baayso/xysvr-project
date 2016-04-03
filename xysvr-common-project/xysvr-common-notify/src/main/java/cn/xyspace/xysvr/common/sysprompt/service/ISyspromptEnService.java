/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.service;

import java.util.List;

import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptEn;
import cn.xyspace.xysvr.common.sysprompt.form.manager.SysprompUpdatetMgrForm;

import com.github.pagehelper.PageInfo;

/**
 * 英文系统提示业务逻辑接口。
 *
 * @author Tanrongrong(2015年1月24日 下午3:31:05)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public interface ISyspromptEnService {

    /**
     * 查找所有英文系统提示列表。
     * 
     * @return 英文系统提示列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<SyspromptEn> findsAll();

    /**
     * 根据条件查找英文系统提示。
     * 
     * @param pageNumber
     *            当前页
     * @param pageSize
     *            页大小
     * @param code
     *            编码
     * @return 英文系统提示列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<SyspromptEn> mgrFinds(String pageNumber, String pageSize, Integer code);

    /***
     * 根据ID查找单个英文系统提示详细信息。
     * 
     * @param id
     *            系统提示ID
     * @return 英文系统提示实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public SyspromptEn findById(String id);

    /**
     * 根据ID更新英文系统提示。
     * 
     * @param form
     *            更新英文系统提示时客户端提交的数据
     * @return 更新成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean update(SysprompUpdatetMgrForm form);

}
