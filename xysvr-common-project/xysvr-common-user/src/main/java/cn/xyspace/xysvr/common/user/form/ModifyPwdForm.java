/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用于保存用户修改密码时客户端提交的数据。
 * 
 * @author ChenFangjie(2014/12/30 14:36:51)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class ModifyPwdForm extends UserInfoForm {

    private String password;
    private String newPassword;
    private String reNewPassword;

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
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @NotBlank
    @Pattern(regexp = "^[\\dA-Za-z(!@#$%&)]{1,16}$", message = "必须为1-16位字符且只能包含数字，字母，特殊符号(!@#$%&)组合的密码")
    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

}
