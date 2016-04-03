/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 用户使用日志实体。
 * 
 * @author Tanrongrong(2014年12月24日 下午4:53:59)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 */
@Document(collection = "user_use_log")
public class UserUseLog extends IdEntity {

    private static final long serialVersionUID = -4302527909623749255L;

    private String userId; // 用户ID（外键）
    private String username; // 用户名（登录名，外键）
    private String httpUrl; // 用户访问的HTTP路径
    private String httpMethod; // 用户访问时使用的HTTP方法
    private String operation; // 用户的操作
    private Double longitude; // 用户操作时所在地经度
    private Double latitude; // 用户操作时所在地纬度
    private String city; // 用户操作时所在的城市
    private String position; // 用户操作时的详细地理位置
    private String clientIp; // 客户端IP
    private String clientInfo; // 客户端信息
    private Long createTime; // 记录日志时的时间戳

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

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
