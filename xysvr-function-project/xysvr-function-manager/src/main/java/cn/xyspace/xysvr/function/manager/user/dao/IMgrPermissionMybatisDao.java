/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao;

import java.util.List;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;

/**
 * 后台管理权限表数据访问对象接口。
 * 
 * @author JiangAnran(2015年3月3日 下午1:58:00)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IMgrPermissionMybatisDao extends IBaseMybatisDao {

    /**
     * 新增权限
     * 
     * @param permission
     *            权限实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(MgrPermission permission);

    /**
     * 修改权限
     * 
     * @param permission
     *            权限实体
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int update(MgrPermission permission);

    /**
     * 删除权限
     * 
     * @param permissionId
     *            权限id
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateToDelete(String permissionId);

    /**
     * 根据id查询权限
     * 
     * @param permissionId
     *            权限id
     * @return 权限实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrPermission selectById(String permissionId);

    /**
     * 查询权限列表
     * 
     * @return 权限类型的list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrPermission> selectsAll();

}
