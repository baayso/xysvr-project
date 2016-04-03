/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.form.manager;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
public class SysprompUpdatetMgrForm {

    private String id;// 系统提示ID
    private String content;// 系统提示内容

    @NotBlank
    @Pattern(regexp = "^\\w{24}$", message = "格式错误")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank
    @Length(min = 1, max = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
