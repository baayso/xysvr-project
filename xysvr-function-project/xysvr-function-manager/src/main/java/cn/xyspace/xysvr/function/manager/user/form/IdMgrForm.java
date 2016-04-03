/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 后台对用户权限和角色的ID进行校验时客户端提交的数据。
 *
 * @author JiangAnran(2015年1月15日 上午10:03:13)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class IdMgrForm {

    private String id;

    public IdMgrForm(String id) {
        this.id = id;
    }

    @NotBlank
    @Pattern(regexp = "^\\w{24}$", message = "格式错误")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
