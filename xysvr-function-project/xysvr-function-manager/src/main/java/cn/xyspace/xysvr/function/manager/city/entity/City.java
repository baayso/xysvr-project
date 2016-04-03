/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.city.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 城市实体类。
 *
 * @author CaoZhongsheng(2015年4月17日 上午11:05:18)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class City extends IdEntity {

    private static final long serialVersionUID = 3123223263020505723L;

    private String name; // 城市名
    private String detail; // 详细城市名
    private Integer baiduCode; // 百度城市代码
    private Integer areaCode; // 电话区域代码
    private Integer zipCode; // 邮政编码（省份无编码）
    private Integer adminCode; // 行政区划代码
    private String parentId; // 上级ID（外键，自关联）

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getBaiduCode() {
        return baiduCode;
    }

    public void setBaiduCode(Integer baiduCode) {
        this.baiduCode = baiduCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(Integer adminCode) {
        this.adminCode = adminCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
