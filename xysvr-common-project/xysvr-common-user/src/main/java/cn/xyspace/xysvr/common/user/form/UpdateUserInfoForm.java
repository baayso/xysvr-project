/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 用于保存用户修改个人资料时客户端提交的数据。
 * 
 * @author Tanrongrong(2015年1月20日 上午10:48:50)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class UpdateUserInfoForm extends UserInfoForm {

    private String email; // 用户邮箱
    private String telephone; // 用户手机号
    private String intro; // 用户签名

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^[0-9]{0,20}$", message = "非法数据")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Length(min = 0, max = 255)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}
