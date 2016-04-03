/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptEn;

/**
 * 英文系统提示表数据访问对象接口。
 * 
 * @author Tanrongrong(2015年1月24日 下午2:18:42)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface ISyspromptEnMybatisDao extends IBaseMybatisDao {

    /**
     * 查询英文系统提示列表。
     * 
     * @return 英文系统提示列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<SyspromptEn> selects();

    /**
     * 查询英文系统提示列表。
     * 
     * @param code
     *            编码
     * @return 英文系统提示列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<SyspromptEn> query(@Param(value = "code") Integer code);

    /**
     * 查询单个英文系统提示的详细信息。
     * 
     * @param id
     *            系统提示主键
     * @return 英文系统提示实体对象
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public SyspromptEn selectById(String id);

    /**
     * 根据系统提示ID更新英文系统提示。
     * 
     * @param id
     *            系统提示ID
     * @param content
     *            系统提示内容
     * @param mtime
     *            修改时间
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int update(@Param(value = "id") String id, @Param(value = "content") String content, @Param(value = "mtime") Long mtime);

}
