/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 客户端提交的分页数据。继承 {@linkplain cn.xyspace.xysvr.common.core.form.BaseForm}。
 * 
 * @author ChenFangjie(2014/12/17 14:36:41)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class PageForm extends BaseForm {

    private String pageNumber;
    private String pageSize;

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{0,7}$", message = "非法数据")
    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Pattern(regexp = "^[0-9]{0,3}$", message = "非法数据")
    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

}
