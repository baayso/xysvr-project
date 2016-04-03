/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.shiro;

import java.io.Serializable;

import cn.xyspace.xysvr.common.user.entity.UserGender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * 后台管理自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息。
 * 
 * @author ChenFangjie(2015年2月5日 下午3:12:49)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class MgrShiroUser implements Serializable {

    private static final long serialVersionUID = 207366298830087475L;

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

    public MgrShiroUser(String id, String username) {
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
        MgrShiroUser other = (MgrShiroUser) obj;
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

}