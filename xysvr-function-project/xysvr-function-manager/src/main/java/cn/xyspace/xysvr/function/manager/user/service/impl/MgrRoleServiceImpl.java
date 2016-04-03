/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.IdUtils;
import cn.xyspace.xysvr.function.manager.user.dao.IMgrRoleMybatisDao;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;
import cn.xyspace.xysvr.function.manager.user.entity.MgrRole;
import cn.xyspace.xysvr.function.manager.user.form.AddRoleMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdateRoleMgrForm;
import cn.xyspace.xysvr.function.manager.user.service.IMgrPermissionService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrRoleService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户角色业务逻辑实现。
 * 
 * @author JiangAnran(2015年3月3日 下午2:46:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class MgrRoleServiceImpl implements IMgrRoleService {

    private IMgrRoleMybatisDao mgrRoleDao = MybatisDaoProxyFactory.createProxy(IMgrRoleMybatisDao.class);

    @Inject
    private IMgrPermissionService mgrPermissionService;

    @Inject
    private IMgrUserService mMgrUserService;

    @Override
    public boolean addRole(AddRoleMgrForm form) {

        MgrRole role = new MgrRole();
        role.setId(IdUtils.getId());
        role.setName(form.getName());
        role.setPermissionIds(form.getPermissionIds());
        role.setIsAvailable(Boolean.parseBoolean((form.getIsAvailable())));
        role.setDescriptions(form.getDescriptions());

        return this.mgrRoleDao.insert(role) > 0;
    }

    @Override
    public boolean updateRole(UpdateRoleMgrForm form) {

        MgrRole role = new MgrRole();
        role.setId(form.getId());
        role.setName(form.getName());
        role.setPermissionIds(form.getPermissionIds());
        role.setIsAvailable(Boolean.parseBoolean((form.getIsAvailable())));
        role.setDescriptions(form.getDescriptions());

        return this.mgrRoleDao.update(role) > 0;
    }

    @Override
    public boolean deleteRole(String id) {
        return this.mgrRoleDao.updateToDelete(id) > 0;
    }

    @Override
    public MgrRole findById(String id) {
        return this.mgrRoleDao.selectById(id);
    }

    @Override
    public List<MgrRole> findsAll() {
        return this.mgrRoleDao.selectsAll();
    }

    @Override
    public Set<String> findRoles(List<String> list) {
        Set<String> roles = new HashSet<String>();
        for (String roleId : list) {
            MgrRole role = findById(roleId);
            if (role != null) {
                roles.add(role.getName());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(String[] roleIds) {
        Set<String> permissionIds = new HashSet<String>();
        for (String roleId : roleIds) {
            MgrRole role = findById(roleId);
            if (role != null) {
                if (StringUtils.isEmpty(role.getPermissionIds())) {
                    return permissionIds;
                }
                String[] roleIdStrs = role.getPermissionIds().split(",");
                for (String roleIdStr : roleIdStrs) {
                    if (StringUtils.isEmpty(roleIdStr)) {
                        continue;
                    }
                    permissionIds.add(String.valueOf(roleIdStr));
                }
            }
        }
        return this.mgrPermissionService.findPermissions(permissionIds);
    }

    @Override
    public PageInfo<MgrRole> findsAllPaging(String pageNumber, String pageSize) {
        int thisPageNumber = org.apache.commons.lang3.StringUtils.isNumeric(pageNumber) ? Integer.parseInt(pageNumber) : 1; // 页码
        int thisPageSize = org.apache.commons.lang3.StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : Config.PAGE_SIZE; // 本页显示记录数

        PageHelper.startPage(thisPageNumber, thisPageSize); // 调用分页方法 紧跟其后的一个select被分页
        List<MgrRole> list = this.mgrRoleDao.selectsAll();
        return new PageInfo<MgrRole>(list);
    }

    @Override
    public boolean setPermissions(String id, String grants) {
        MgrRole role = findById(id);
        if (role != null) {
            if (!StringUtils.isEmpty(grants)) {
                grants = grants.substring(0, grants.length() - 1);
                role.setPermissionIds(grants);
            }
        }
        return this.mgrRoleDao.update(role) > 0;
    }

    @Override
    public List<MgrPermission> findCheckedPermissionsList(String id) {
        List<MgrPermission> list = this.mgrPermissionService.findsAll();
        Set<String> permissionIds = this.findPermissionsList(id);
        for (MgrPermission permission : list) {
            permission.setCheck(permissionIds.contains(permission.getId()));
        }
        return list;
    }

    @Override
    public Set<String> findPermissionsList(String id) {
        MgrRole role = findById(id);
        Set<String> permissionIds = new HashSet<String>();
        if (role != null) {
            if (StringUtils.isEmpty(role.getPermissionIds())) {
                return permissionIds;
            }
            String[] roleIdStrs = role.getPermissionIds().split(",");
            for (String roleIdStr : roleIdStrs) {
                if (StringUtils.isEmpty(roleIdStr)) {
                    continue;
                }
                permissionIds.add(String.valueOf(roleIdStr));
            }
        }
        return permissionIds;
    }

    @Override
    public List<MgrRole> findsCheckedAll(String username) {
        Set<String> roles = this.mMgrUserService.findRoles(username);
        List<MgrRole> list = this.mgrRoleDao.selectsAll();
        for (MgrRole role : list) {
            role.setCheck(roles.contains(role.getName()));
        }
        return list;
    }

}
