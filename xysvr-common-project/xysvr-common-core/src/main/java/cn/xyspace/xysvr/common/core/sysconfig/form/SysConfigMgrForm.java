/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 存储后台配置信息页面传的数据。
 *
 * @author WuQiying(2015年1月29日 下午3:37:50)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class SysConfigMgrForm {

    private String id;

    public SysConfigMgrForm(String id) {
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
