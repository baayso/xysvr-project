/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 后台管理权限表单数据。
 *
 * @author JiangAnran(2015年3月3日 下午1:45:07)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UpdatePermissionMgrForm {

    private String id; // 权限ID，主键
    private String name; // 权限名称
    private String type; // 权限类型
    private String url; // 权限对应的url
    private String parentId; // 父权限ID
    private String parentIds; // 父权限ID列表
    private String permissionStr; // 权限字符串
    private String isAvailable;// 是否可用
    private String descriptions;// 权限描述

    private boolean check;// 封装权限是否已有

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @NotBlank
    @Pattern(regexp = "^\\w{24}$", message = "格式错误")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{0,60}$", message = "必须为1-60位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = "^[(/)a-zA-Z]{0,20}$", message = "非法数据")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NotBlank
    @Pattern(regexp = "^\\d+$", message = "非法数据")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @NotBlank
    @Pattern(regexp = "^(\\d+/)*$", message = "非法数据")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Pattern(regexp = "^[a-zA-Z(:*)]{0,20}$", message = "非法数据")
    public String getPermissionStr() {
        return permissionStr;
    }

    public void setPermissionStr(String permissionStr) {
        this.permissionStr = permissionStr;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Pattern(regexp = "^[a-zA-Z0-9_\u4e00-\u9fa5(,.!@#$%&/:;)\uFE30-\uFFA0-]{0,250}$", message = "格式错误")
    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isRootNode() {
        return parentId == "0";
    }

    @NotBlank
    @Pattern(regexp = "^(MENU|BUTTON)$", message = "非法数据")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
