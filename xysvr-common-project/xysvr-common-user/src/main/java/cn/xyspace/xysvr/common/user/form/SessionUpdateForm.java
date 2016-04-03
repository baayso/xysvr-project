/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 实时更新session客户端提交的数据。
 *
 * @author CaoZhongsheng
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class SessionUpdateForm {

    private String longitude; // 经纬
    private String latitude; // 纬度
    private String city; // 城市
    private String position; // 详细地理位置

    @NotNull
    @Pattern(regexp = "^-?[1-9][0-9]{0,2}\\.[0-9]*|-?0\\.[0-9]*[1-9][0-9]*$", message = "非法数据")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @NotNull
    @Pattern(regexp = "^-?[1-9][0-9]{0,2}\\.[0-9]*|-?0\\.[0-9]*[1-9][0-9]*$", message = "非法数据")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @NotBlank
    @Length(min = 1, max = 10)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Pattern(regexp = "^[\u4E00-\u9FA5A-Za-z0-9]{0,255}$", message = "非法数据")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
