/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.api.msg.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import cn.xyspace.xysvr.common.user.form.UserInfoForm;

/**
 * 用于保存用户发送私信时客户端提交的数据。
 *
 * @author WuQiying(2015年2月26日 下午7:08:11)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class AddMsgForm extends UserInfoForm {

    private String toUser;
    private String type;
    private String content;

    @NotBlank
    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @NotBlank
    @Pattern(regexp = "^(TEXT|IMAGE|AUDIO|VIDEO)$", message = "非法数据")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotBlank
    @Length(min = 1, max = 140)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
