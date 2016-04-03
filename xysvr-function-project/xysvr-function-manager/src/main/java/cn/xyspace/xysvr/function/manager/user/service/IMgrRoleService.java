/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service;

import java.util.List;
import java.util.Set;

import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;
import cn.xyspace.xysvr.function.manager.user.entity.MgrRole;
import cn.xyspace.xysvr.function.manager.user.form.AddRoleMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdateRoleMgrForm;

import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户角色业务逻辑接口。
 * 
 * @author JiangAnran(2015年3月3日 下午2:46:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IMgrRoleService {

    /**
     * 新增角色。
     * 
     * @param form
     *            新增角色时客户端提交的数据
     * @return 新增成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean addRole(AddRoleMgrForm form);

    /**
     * 更新角色。
     * 
     * @param form
     *            更新角色时客户端提交的数据
     * @return 更新成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean updateRole(UpdateRoleMgrForm form);

    /**
     * 删除角色。
     * 
     * @param id
     *            角色ID
     * @return 删除成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean deleteRole(String id);

    /**
     * 根据ID获取角色。
     * 
     * @param id
     *            角色ID
     * @return 角色实体
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrRole findById(String id);

    /**
     * 获取角色列表。
     * 
     * @return 角色实体列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrRole> findsAll();

    /**
     * 获取角色列表并分页。
     * 
     * @param pageNumber
     *            页号
     * @param pageSize
     *            页大小
     * @return 角色实体列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<MgrRole> findsAllPaging(String pageNumber, String pageSize);

    /**
     * 根据角色ids得到角色列表。
     * 
     * @param ids
     *            角色id构成的list
     * @return 角色的string类型set集合
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public Set<String> findRoles(List<String> ids);

    /**
     * 根据角色id得到权限列表。
     * 
     * @param roleIds
     *            角色id的String数组
     * @return 权限的string类型set集合
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public Set<String> findPermissions(String[] roleIds);

    /**
     * 根据角色id得到权限列表。
     * 
     * @param id
     *            角色ID
     * @return MgrPermission类型list
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrPermission> findCheckedPermissionsList(String id);

    /**
     * 设置权限。
     * 
     * @param id
     *            角色ID
     * @param grants
     *            授予的权限的id构成的字符串
     * @return 设置成功与否
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean setPermissions(String id, String grants);

    /**
     * 根据角色id得到权限列表。
     * 
     * @param id
     *            角色ID
     * @return 权限的string类型set集合
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public Set<String> findPermissionsList(String id);

    /**
     * 获取角色列表。
     * 
     * @param username
     *            用户名
     * @return MgrRole类型list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrRole> findsCheckedAll(String username);

}
