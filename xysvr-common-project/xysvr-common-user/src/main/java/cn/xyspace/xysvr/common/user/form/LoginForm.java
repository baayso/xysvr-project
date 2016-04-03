/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import cn.xyspace.xysvr.common.core.form.BaseForm;

/**
 * 用于保存用户登录时客户端提交的数据。
 * 
 * @author ChenFangjie(2014/12/14 15:30:12)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class LoginForm extends BaseForm {

    private String username;
    private String password;
    private String rememberMe;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{4,15}$", message = "必须为5-16位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Pattern(regexp = "^[\\dA-Za-z(!@#$%&)]{1,16}$", message = "必须为1-16位字符且只能包含数字，字母，特殊符号(!@#$%&)组合的密码")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

}
