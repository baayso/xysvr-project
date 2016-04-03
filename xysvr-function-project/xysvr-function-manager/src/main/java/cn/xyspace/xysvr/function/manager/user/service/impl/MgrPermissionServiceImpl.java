/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.IdUtils;
import cn.xyspace.xysvr.function.manager.user.dao.IMgrPermissionMybatisDao;
import cn.xyspace.xysvr.function.manager.user.entity.MgrMenu;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission.ResourceType;
import cn.xyspace.xysvr.function.manager.user.form.AddPermissionMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdatePermissionMgrForm;
import cn.xyspace.xysvr.function.manager.user.service.IMgrMenuService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrPermissionService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户权限业务逻辑实现。
 * 
 * @author JiangAnran(2015年3月3日 下午2:43:33)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class MgrPermissionServiceImpl implements IMgrPermissionService {

    private IMgrPermissionMybatisDao mgrPermissionDao = MybatisDaoProxyFactory.createProxy(IMgrPermissionMybatisDao.class);

    @Inject
    private IMgrMenuService mgrMenuService;

    @Override
    public boolean addPermission(AddPermissionMgrForm form) {
        MgrPermission permission = new MgrPermission();

        permission.setId(IdUtils.getId());
        permission.setName(form.getName());
        permission.setType(ResourceType.valueOf(form.getType()));
        permission.setUrl(form.getUrl());
        permission.setParentId(form.getParentId());
        permission.setParentIds(form.getParentIds());
        permission.setPermissionStr(form.getPermissionStr());
        permission.setIsAvailable(Boolean.valueOf(form.getIsAvailable()));
        permission.setDescriptions(form.getDescriptions());

        return this.mgrPermissionDao.insert(permission) > 0;
    }

    @Override
    public boolean updatePermission(UpdatePermissionMgrForm form) {
        MgrPermission permission = new MgrPermission();

        permission.setId(form.getId());
        permission.setName(form.getName());
        permission.setType(ResourceType.valueOf(form.getType()));
        permission.setUrl(form.getUrl());
        permission.setParentId(form.getParentId());
        permission.setParentIds(form.getParentIds());
        permission.setPermissionStr(form.getPermissionStr());
        permission.setIsAvailable(Boolean.valueOf(form.getIsAvailable()));
        permission.setDescriptions(form.getDescriptions());

        return this.mgrPermissionDao.update(permission) > 0;
    }

    @Override
    public boolean deletePermission(String id) {
        return this.mgrPermissionDao.updateToDelete(id) > 0;
    }

    @Override
    public MgrPermission findById(String id) {
        return this.mgrPermissionDao.selectById(id);
    }

    @Override
    public List<MgrPermission> findsAll() {
        return this.mgrPermissionDao.selectsAll();
    }

    @Override
    public Set<String> findPermissions(Set<String> permessionIds) {
        Set<String> permissions = new HashSet<String>();
        for (String permessionId : permessionIds) {
            MgrPermission permission = findById(permessionId);
            if (permission != null && !StringUtils.isEmpty(permission.getPermissionStr())) {
                permissions.add(permission.getPermissionStr());
            }
        }
        return permissions;
    }

    private boolean hasPermission(Set<String> permissions, String permission) {
        for (String perm : permissions) {
            WildcardPermission p1 = new WildcardPermission(perm);
            WildcardPermission p2 = new WildcardPermission(permission);
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PageInfo<MgrPermission> findsAllPaging(String pageNumber, String pageSize) {
        int thisPageNumber = org.apache.commons.lang3.StringUtils.isNumeric(pageNumber) ? Integer.parseInt(pageNumber) : 1; // 页码
        int thisPageSize = org.apache.commons.lang3.StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : Config.PAGE_SIZE; // 本页显示记录数

        PageHelper.startPage(thisPageNumber, thisPageSize); // 调用分页方法 紧跟其后的一个select被分页
        List<MgrPermission> list = this.mgrPermissionDao.selectsAll();
        return new PageInfo<MgrPermission>(list);
    }

    @Override
    public List<MgrMenu> findMenus(Set<String> permissions) {
        List<MgrMenu> menus = this.mgrMenuService.findsAll();
        for (MgrMenu menu : menus) {
            menu.setAuthorized(hasPermission(permissions, menu.getNeedperm()));
            List<MgrMenu> children = menu.getMenus();
            if (!children.isEmpty()) {
                for (MgrMenu child : children) {
                    child.setAuthorized(hasPermission(permissions, child.getNeedperm()));
                }
            }
        }
        return menus;
    }

}
