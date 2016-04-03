/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao;

import java.util.List;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.user.entity.MgrRole;

/**
 * 后台管理角色表数据访问对象接口。
 * 
 * @author JiangAnran(2015年3月3日 下午1:58:00)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IMgrRoleMybatisDao extends IBaseMybatisDao {

    /**
     * 新增角色
     * 
     * @param role
     *            角色实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(MgrRole role);

    /**
     * 修改角色
     * 
     * @param role
     *            角色实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int update(MgrRole role);

    /**
     * 删除角色
     * 
     * @param roleId
     *            角色id
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateToDelete(String roleId);

    /**
     * 根据id查询角色
     * 
     * @param roleId
     *            角色id
     * @return 角色实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrRole selectById(String roleId);

    /**
     * 查询角色列表
     * 
     * @return 角色类型的list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrRole> selectsAll();

}
