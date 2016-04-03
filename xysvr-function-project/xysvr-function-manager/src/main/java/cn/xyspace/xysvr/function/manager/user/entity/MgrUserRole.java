/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 后台管理用户角色关联实体类。
 * 
 * @author JiangAnran(2015年3月3日 下午1:03:20)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class MgrUserRole extends IdEntity {

    private static final long serialVersionUID = 1211383635690426953L;

    private String userId; // 用户ID（外键）
    private String roleId; // 角色ID（外键）

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
