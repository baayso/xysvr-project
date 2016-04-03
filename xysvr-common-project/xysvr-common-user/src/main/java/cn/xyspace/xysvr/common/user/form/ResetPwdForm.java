/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import cn.xyspace.xysvr.common.core.form.BaseForm;

/**
 * 用于保存用户重置密码时客户端提交的数据。
 *
 * @author WuQiying(2015年5月5日 上午9:44:41)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class ResetPwdForm extends BaseForm {

    private String username;
    private String checkCode;
    private String newPassword;
    private String reNewPassword;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{4,15}$", message = "必须为5-16位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "必须为6-20位只包含字母、数字的字符")
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
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
