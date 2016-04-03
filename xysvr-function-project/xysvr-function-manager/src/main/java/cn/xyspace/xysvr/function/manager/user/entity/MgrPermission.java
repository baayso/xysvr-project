/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 后台管理权限实体类。
 *
 * @author JiangAnran(2015年3月3日 下午1:45:07)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class MgrPermission extends IdEntity {

    private static final long serialVersionUID = -4424936596448017049L;

    private String name; // 权限名称
    private ResourceType type; // 权限类型
    private String url; // 权限对应的url
    private String parentId; // 父权限ID
    private String parentIds; // 父权限ID列表
    private String permissionStr; // 权限字符串
    private Boolean isAvailable;// 是否可用
    private String descriptions;// 权限描述

    private boolean check;// 封装权限是否已有

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static enum ResourceType {
        MENU("菜单"), BUTTON("按钮");

        private final String info;

        private ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getPermissionStr() {
        return permissionStr;
    }

    public void setPermissionStr(String permissionStr) {
        this.permissionStr = permissionStr;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isRootNode() {
        return parentId == "0";
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
