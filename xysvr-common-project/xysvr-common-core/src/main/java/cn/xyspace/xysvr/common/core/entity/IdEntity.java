/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * 统一定义主键（id）的Entity基类。
 * 
 * @author ChenFangjie(2014/11/29 19:27)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = -5996922415163323589L;

    /** 主键 */
    @Id
    protected String id;

    /**
     * 获取主键。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置主键。
     * 
     * @param id
     *            字符串主键
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void setId(String id) {
        this.id = id;
    }

}
