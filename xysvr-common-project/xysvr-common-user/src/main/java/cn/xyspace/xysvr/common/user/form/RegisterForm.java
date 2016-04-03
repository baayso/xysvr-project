/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import cn.xyspace.xysvr.common.core.form.BaseForm;

/**
 * 用于保存用户注册时客户端提交的数据。
 * 
 * @author ChenFangJie(2014/12/14 21:16:20)
 *
 * @since 1.0.0
 * 
 * @version 1.1.0
 * 
 */
public class RegisterForm extends BaseForm {

    private String username;
    private String password;
    private String repassword;
    private String email;
    private String inviter;
    private String identifier;
    private String gender;

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

    @NotBlank
    @Pattern(regexp = "^[\\dA-Za-z(!@#$%&)]{1,16}$", message = "必须为1-16位字符且只能包含数字，字母，特殊符号(!@#$%&)组合的密码")
    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^\\s{0,}|[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{4,15}$", message = "必须为5-16位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{6,36}$", message = "格式错误")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @NotBlank
    @Pattern(regexp = "^(M|F)$", message = "性别不符合要求")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
