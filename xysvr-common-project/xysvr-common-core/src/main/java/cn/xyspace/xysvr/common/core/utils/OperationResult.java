/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 返回给客户端的操作结果（转换成json格式后返回给客户端）。
 * 
 * @author ChenFangjie
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class OperationResult implements Serializable {

    private static final long serialVersionUID = 8933427412753745518L;

    private boolean status;
    private int statusCode;
    private Object message;
    private Object data;

    public OperationResult() {
    }

    public OperationResult(boolean status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public OperationResult(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public OperationResult(boolean status, int statusCode, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
