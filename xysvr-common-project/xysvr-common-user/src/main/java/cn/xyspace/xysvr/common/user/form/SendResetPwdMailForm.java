/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用于保存发送重置密码邮件时客户端提交的数据。
 * 
 * @author ChenFangjie(2015年5月3日 下午2:19:23)
 * 
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class SendResetPwdMailForm {

    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{4,15}$", message = "必须为5-16位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
