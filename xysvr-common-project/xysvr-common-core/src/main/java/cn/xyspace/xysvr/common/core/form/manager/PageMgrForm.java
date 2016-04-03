/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.form.manager;

import javax.validation.constraints.Pattern;

/**
 * 页面提交的分页数据。
 * 
 * @author ChenFangjie
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class PageMgrForm {

    private String pageNumber;
    private String pageSize;

    public PageMgrForm() {
    }

    public PageMgrForm(String pageNumber, String pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Pattern(regexp = "^[1-9]{0,1}[0-9]{0,7}$", message = "非法数据")
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
