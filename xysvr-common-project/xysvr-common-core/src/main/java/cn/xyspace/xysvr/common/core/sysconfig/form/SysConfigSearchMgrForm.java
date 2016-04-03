/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.form;

import javax.validation.constraints.Pattern;

import cn.xyspace.xysvr.common.core.form.manager.PageMgrForm;

/**
 * 存储后台配置信息查询页面传的数据。
 *
 * @author WuQiying(2015年4月21日 下午7:03:02)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class SysConfigSearchMgrForm extends PageMgrForm {

    private String type;
    private String name;

    @Pattern(regexp = "^\\s{0,15}|[\u4e00-\u9fa5\uFE30-\uFFA0]{1,15}$", message = "配置类型应为2-15位中文字符")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Pattern(regexp = "^\\s{0,95}|[a-zA-Z_]{1,95}$", message = "配置名称应为2-95位（含下划线）英文字母")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
