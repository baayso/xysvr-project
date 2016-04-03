/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.exception;

import org.springframework.http.HttpStatus;

import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;

/**
 * 专用于 RESTful 的异常。
 * 
 * @author ChenFangjie(2014/12/14 16:08:21)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 4335908118767418424L;

    public HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public SyspromptStatus syspromptStatus = SyspromptStatus.UNKNOW_ERROR;

    public RestException() {
    }

    public RestException(SyspromptStatus syspromptStatus) {
        this.syspromptStatus = syspromptStatus;
    }

    public RestException(HttpStatus httpStatus, SyspromptStatus syspromptStatus) {
        this.httpStatus = httpStatus;
        this.syspromptStatus = syspromptStatus;
    }

    public RestException(SyspromptStatus syspromptStatus, String message) {
        super(message);
        this.syspromptStatus = syspromptStatus;
    }

    public RestException(HttpStatus httpStatus, SyspromptStatus syspromptStatus, String message) {
        this(syspromptStatus, message);
        this.httpStatus = httpStatus;
    }

}
