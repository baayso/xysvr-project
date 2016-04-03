/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 用户登录日志实体类。
 * 
 * @author ChenFangjie(2014/11/29 20:51:02)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Document(collection = "user_login_log")
public class UserLoginLog extends IdEntity {

    private static final long serialVersionUID = -5091818530003069580L;

    private String userId; // 登录用户的ID（外键）
    private String username; // 登录用户的用户名（登录名，外键）
    private Double longitude; // 用户登录时的经度
    private Double latitude; // 用户登录时的纬度
    private String city;// 用户登录时所在的城市
    private String position; // 用户登录时的详细地理位置
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
