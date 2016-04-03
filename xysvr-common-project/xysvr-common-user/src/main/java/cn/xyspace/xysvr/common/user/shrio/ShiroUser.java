/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.shrio;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.xyspace.xysvr.common.user.entity.UserGender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息。
 * 
 * @author ChenFangjie(2014/12/12 14:08:41)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -2237575793772609479L;

    private String id;
    private String username;
    @JsonIgnore
    private String password;
    private UserGender gender;
    private String birthday;
    private String telephone;
    private String email;
    private String address;
    private String intro;
    private String iconPath;

    private Integer bonusPoint; // 用户积分
    private Integer money; // 用户基本金币
    private Integer lockMoney; // 用户锁定金币
    private Integer earnMoney; // 用户所赚得的金币
    private BigDecimal rmoney; // 用户真实钱财（单位：元）
    private BigDecimal lockRmoney; // 用户锁定的真实钱财（单位：元）
    private Integer lucky; // 用户幸运指数
    private Integer hitface; // 用户打脸指数
    private Integer hittedface; // 用户被打脸指数
    private Integer merit; // 用户功德
    private Integer level; // 用户等级

    @JsonIgnore
    private Double longitude; // 登录时的经度，不返回给客户端
    @JsonIgnore
    private Double latitude; // 登录时的纬度，不返回给客户端
    @JsonIgnore
    private String city; // 登录时的城市，不返回给客户端
    @JsonIgnore
    private String position; // 登录时的详细地理位置，不返回给客户端

    public ShiroUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出。
     */
    @Override
    public String toString() {
        return this.username;
    }

    /**
     * 重写hashCode，只计算username。
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.username);
    }

    /**
     * 重写equals，只计算username。
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (this.username == null) {
            if (other.username != null) {
                return false;
            }
        }
        else if (!this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Integer getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(Integer bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(Integer lockMoney) {
        this.lockMoney = lockMoney;
    }

    public Integer getEarnMoney() {
        return earnMoney;
    }

    public void setEarnMoney(Integer earnMoney) {
        this.earnMoney = earnMoney;
    }

    public BigDecimal getRmoney() {
        return rmoney;
    }

    public void setRmoney(BigDecimal rmoney) {
        this.rmoney = rmoney;
    }

    public BigDecimal getLockRmoney() {
        return lockRmoney;
    }

    public void setLockRmoney(BigDecimal lockRmoney) {
        this.lockRmoney = lockRmoney;
    }

    public Integer getLucky() {
        return lucky;
    }

    public void setLucky(Integer lucky) {
        this.lucky = lucky;
    }

    public Integer getHitface() {
        return hitface;
    }

    public void setHitface(Integer hitface) {
        this.hitface = hitface;
    }

    public Integer getHittedface() {
        return hittedface;
    }

    public void setHittedface(Integer hittedface) {
        this.hittedface = hittedface;
    }

    public Integer getMerit() {
        return merit;
    }

    public void setMerit(Integer merit) {
        this.merit = merit;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

}