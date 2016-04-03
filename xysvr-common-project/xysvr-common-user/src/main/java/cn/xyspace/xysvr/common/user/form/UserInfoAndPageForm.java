/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Null;

import cn.xyspace.xysvr.common.core.form.PageForm;
import cn.xyspace.xysvr.common.user.entity.UserGender;

/**
 * 封装从Session中获取的用户信息。继承 {@linkplain cn.xyspace.xysvr.common.core.form.PageForm}。
 * 
 * @author ChenFangjie(2014/12/17 19:00:50)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UserInfoAndPageForm extends PageForm {

    private String userId; // 用户编号
    private String username; // 用户名
    private String iconPath; // 用户头像
    private UserGender gender; // 用户性别

    @Null
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Null
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Null
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Null
    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

}
