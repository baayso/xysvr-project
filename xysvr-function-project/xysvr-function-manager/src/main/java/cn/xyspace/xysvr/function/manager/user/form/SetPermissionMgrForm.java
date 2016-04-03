/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import javax.validation.constraints.Pattern;

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
public class SetPermissionMgrForm {

    private String permissionIds; // 授于的权限列表

    public SetPermissionMgrForm(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    @NotBlank
    @Pattern(regexp = "^(\\d+,)*$", message = "非法数据")
    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

}
