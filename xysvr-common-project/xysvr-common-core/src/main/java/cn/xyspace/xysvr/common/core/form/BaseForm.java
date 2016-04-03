/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.form;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 客户端提交的数据基类。
 * 
 * @author ChenFangjie(2014/12/17 10:48:06)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class BaseForm {

    private String longitude; // 经纬
    private String latitude; // 纬度
    private String city; // 城市
    private String position; // 详细地理位置
    private String clientInfo; // 客户端信息

    private String clientIp; // 客户端IP
    private String httpUrl; // 客户端访问的url
    private String httpMethod; // 客户端访问url使用的http method

    @NotBlank
    @Pattern(regexp = "^-?[0-9]{1,3}\\.[0-9]{1,10}$", message = "非法数据")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @NotBlank
    @Pattern(regexp = "^-?[0-9]{1,3}\\.[0-9]{1,10}$", message = "非法数据")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @NotBlank
    @Pattern(regexp = "^[\u4E00-\u9FA5\\sA-Za-z0-9`~!@#$%^&*()-_=+|,./<>?:;\'\"]{1,50}$", message = "非法数据")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Length(min = 0, max = 255)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Length(min = 0, max = 255)
    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Null
    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Null
    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Null
    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

}
