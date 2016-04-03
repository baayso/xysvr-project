/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao;

import java.util.List;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.user.entity.MgrMenu;

/**
 * 后台管理菜单表数据访问对象接口。
 * 
 * @author JiangAnran(2015年3月10日 下午1:58:00)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IMgrMenuMybatisDao extends IBaseMybatisDao {

    /**
     * 新增菜单
     * 
     * @param menu
     *            菜单实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(MgrMenu menu);

    /**
     * 修改菜单
     * 
     * @param menu
     *            菜单实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int update(MgrMenu menu);

    /**
     * 删除菜单
     * 
     * @param menuId
     *            菜单id
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int delete(String menuId);

    /**
     * 根据id查询菜单
     * 
     * @param menuId
     *            菜单id
     * @return 菜单实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrMenu selectById(String menuId);

    /**
     * 查询菜单列表
     * 
     * @return 菜单类型的list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrMenu> selectsAll();

    /**
     * 查询菜单列表关联查询出子菜单信息
     * 
     * @return MgrMenu类型的list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrMenu> selectsWithChildren();

}
