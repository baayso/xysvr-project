/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用于保存修改配置信息时配置信息修改页面提交的数据。
 *
 * @author WuQiying(2015年1月29日 下午2:53:31)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UpdateSysConfigMgrForm {

    private String id;
    private String value;
    private String descriptions;

    @NotBlank
    @Pattern(regexp = "^\\w{24}$", message = "格式错误")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_\u4e00-\u9fa5(,.!@#$%&/:;)\uFE30-\uFFA0-]{1,250}$", message = "配置值格式错误")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Pattern(regexp = "^[a-zA-Z0-9_\u4e00-\u9fa5(,.!@#$%&/:;)\uFE30-\uFFA0-]{1,250}$", message = "配置描述格式错误")
    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

}
