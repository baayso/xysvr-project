/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service;

import java.util.List;
import java.util.Set;

import cn.xyspace.xysvr.function.manager.user.entity.MgrMenu;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;
import cn.xyspace.xysvr.function.manager.user.form.AddPermissionMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdatePermissionMgrForm;

import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户权限业务逻辑接口。
 * 
 * @author JiangAnran(2015年3月3日 下午2:46:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IMgrPermissionService {

    /**
     * 新增权限。
     * 
     * @param form
     *            新增权限时客户端提交的数据
     * @return 新增成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean addPermission(AddPermissionMgrForm form);

    /**
     * 更新权限。
     * 
     * @param form
     *            更新权限时客户端提交的数据
     * @return 更新成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean updatePermission(UpdatePermissionMgrForm form);

    /**
     * 删除权限。
     * 
     * @param id
     *            权限ID
     * @return 删除成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean deletePermission(String id);

    /**
     * 根据ID获取权限。
     * 
     * @param id
     *            权限ID
     * @return 权限实体
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrPermission findById(String id);

    /**
     * 获取权限列表。
     * 
     * @return 权限列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrPermission> findsAll();

    /**
     * 根据权限id获取权限。
     * 
     * @param permessionIds
     *            权限id的string类型set集合
     * @return 权限的string类型set集合
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public Set<String> findPermissions(Set<String> permessionIds);

    /**
     * 获取权限列表并分页。
     * 
     * @param pageNumber
     *            页号
     * @param pageSize
     *            页大小
     * @return 权限列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<MgrPermission> findsAllPaging(String pageNumber, String pageSize);

    /**
     * 根据用户权限得到菜单列表。
     * 
     * @param permissions
     *            权限string类型set集合
     * @return MgrMenu类型的list
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrMenu> findMenus(Set<String> permissions);

}
