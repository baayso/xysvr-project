/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加后台管理员数据提交。
 * 
 * @author CaoZhongsheng(2015年2月6日 下午5:02:25)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class RegisterForm {

    private String username;// 用户名
    private String password;// 密码
    private String repassword;// 重复密码
    private String email;// 邮箱
    private String gender;// 性别
    private List<String> roleIds;// 封装页面id数据

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

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @Pattern(regexp = "^(M|F|S)$", message = "性别不符合要求")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
