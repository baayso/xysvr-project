/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.exception;

import org.springframework.http.HttpStatus;

import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;

/**
 * 专用于 API Service层的异常。
 * 
 * @author ChenFangjie(2014/12/20 16:39:58)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class ApiServiceException extends RuntimeException {

    private static final long serialVersionUID = 1714203480413401732L;

    public HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public SyspromptStatus syspromptStatus = SyspromptStatus.UNKNOW_ERROR;

    public ApiServiceException() {
    }

    public ApiServiceException(SyspromptStatus syspromptStatus) {
        this.syspromptStatus = syspromptStatus;
    }

    public ApiServiceException(HttpStatus httpStatus, SyspromptStatus syspromptStatus) {
        this.httpStatus = httpStatus;
        this.syspromptStatus = syspromptStatus;
    }

    public ApiServiceException(SyspromptStatus syspromptStatus, String message) {
        super(message);
        this.syspromptStatus = syspromptStatus;
    }

    public ApiServiceException(HttpStatus httpStatus, SyspromptStatus syspromptStatus, String message) {
        this(syspromptStatus, message);
        this.httpStatus = httpStatus;
    }

}
