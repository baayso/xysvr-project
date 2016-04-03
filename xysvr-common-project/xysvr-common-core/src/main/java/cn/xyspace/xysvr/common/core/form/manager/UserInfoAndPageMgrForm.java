/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.form.manager;

import javax.validation.constraints.Null;

/**
 * 封装从Session中获取的用户信息。继承 {@linkplain cn.xyspace.xysvr.common.core.form.manager.PageMgrForm}。
 * 
 * @author ChenFangjie
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UserInfoAndPageMgrForm extends PageMgrForm {

    private String userId;
    private String username;
    private String iconPath;

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

}
