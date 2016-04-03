/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 后台管理用户登录日志实体类。
 * 
 * @author ChenFangjie(2015年2月5日 下午1:55:56)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Document(collection = "mgr_user_login_log")
public class MgrUserLoginLog extends IdEntity {

    private static final long serialVersionUID = -7018616070057004686L;

    private String userId; // 登录用户的ID（外键）
    private String username; // 登录用户的用户名（登录名，外键）
    private String loginIp; // 用户登录IP
    private Boolean isSuccess; // 是否登录成功
    private Long loginTime; // 用户登录时间戳

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

}
