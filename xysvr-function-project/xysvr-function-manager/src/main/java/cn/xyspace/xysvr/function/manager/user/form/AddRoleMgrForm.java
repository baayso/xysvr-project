/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 后台管理角色表单数据。
 *
 * @author JiangAnran(2015年3月3日 下午1:45:07)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class AddRoleMgrForm {

    private String name; // 角色名称
    private String permissionIds; // 授于的权限列表
    private String isAvailable; // 是否可用
    private String descriptions; // 角色描述

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{0,60}$", message = "必须为1-60位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 250)
    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @NotBlank
    @Pattern(regexp = "^\\d+(,\\d+)*$", message = "非法数据")
    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

}
