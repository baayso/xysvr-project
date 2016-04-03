/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用于保存用户上传头像时客户端提交的数据。
 *
 * @author ChenFangjie(2015年1月19日 下午6:58:45)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class UploadUserIconForm extends UserInfoForm {

    private String userIcon;

    @NotBlank
    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

}
