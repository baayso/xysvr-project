/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.exception;

import org.springframework.http.HttpStatus;

/**
 * 专用于 Manager Service层的异常。
 * 
 * @author ChenFangjie(2015/1/1 12:24:49)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class ManagerServiceException extends RuntimeException {

    private static final long serialVersionUID = -8933281511657244749L;

    public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ManagerServiceException() {
    }

    public ManagerServiceException(HttpStatus status) {
        this.status = status;
    }

    public ManagerServiceException(String message) {
        super(message);
    }

    public ManagerServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
