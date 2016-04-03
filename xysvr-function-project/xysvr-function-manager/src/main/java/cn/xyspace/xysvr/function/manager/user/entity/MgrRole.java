/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 后台管理角色实体类。
 * 
 * @author JiangAnran(2015年3月3日 下午1:03:20)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class MgrRole extends IdEntity {

    private static final long serialVersionUID = -1400349285949723722L;

    private String name; // 角色名称
    private String permissionIds; // 授于的权限列表
    private Boolean isAvailable; // 是否可用
    private String descriptions; // 角色描述

    private Boolean check;// 封装是否拥有

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

}
