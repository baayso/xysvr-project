/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao;

import java.util.List;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUserRole;

/**
 * 后台管理用户角色关联表数据访问对象接口。
 * 
 * @author JiangAnran(2015年3月3日 下午1:58:00)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IMgrUserRoleMybatisDao extends IBaseMybatisDao {

    /**
     * 新增用户角色关联数据
     * 
     * @param entity
     *            用户角色关联实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(MgrUserRole entity);

    /**
     * 新增用户角色关联数据
     * 
     * @param list
     *            用户角色关联类型list
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insertMore(List<MgrUserRole> list);

    /**
     * 修改用户角色关联
     * 
     * @param entity
     *            用户角色关联实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int update(MgrUserRole entity);

    /**
     * 删除用户角色关联
     * 
     * @param Id
     *            用户角色关联id
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int delete(String Id);

    /**
     * 根据id查询用户角色关联
     * 
     * @param id
     *            用户角色关联id
     * @return 用户角色关联实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrUserRole selectById(String Id);

    /**
     * 查询用户角色关联列表
     * 
     * @return 用户角色关联类型的list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrUserRole> selectsAll();

    /**
     * 删除用户角色关联数据
     * 
     * @param id
     *            用户id
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int deleteMore(String id);

}
