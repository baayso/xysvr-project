/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.common.core.sysconfig.entity.SysConfig;

/**
 * 系统配置信息数据访问对象接口。
 *
 * @author WuQiying(2015年1月27日 下午6:39:58)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface ISysConfigMybatisDao extends IBaseMybatisDao {

    /**
     * 根据配置类型、名称获取系统配置信息。
     * 
     * @param type
     *            配置类型。
     * @param name
     *            配置名称。
     * @return 系统配置信息表。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<SysConfig> selectAll(@Param(value = "type") String type, @Param(value = "name") String name);

    /**
     * 根据主键更新系统配置值。
     * 
     * @param id
     *            主键。
     * @param value
     *            配置值。
     * @param descriptions
     *            配置描述。
     * @param mtime
     *            修改时间。
     * @return 受影响行数。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateSysConfig(@Param(value = "id") String id, @Param(value = "value") String value, @Param(value = "descriptions") String descriptions, @Param(value = "mtime") Long mtime);

    /**
     * 根据主键(id)查询系统配置信息。
     * 
     * @param id
     *            主键(id)。
     * @return 系统配置信息实体。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public SysConfig selectById(String id);

}
