/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.form.manager;

import javax.validation.constraints.Pattern;

import cn.xyspace.xysvr.common.core.form.manager.PageMgrForm;

/**
 * 修改系统提示时客户端提交的数据。
 *
 * @author Tanrongrong(2015年2月7日 下午1:35:36)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class SyspromptListMgrForm extends PageMgrForm {

    private String code;// 系统提示编码

    @Pattern(regexp = "^[0-9]{4,5}$", message = "编码必须为4-5位的数字")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
