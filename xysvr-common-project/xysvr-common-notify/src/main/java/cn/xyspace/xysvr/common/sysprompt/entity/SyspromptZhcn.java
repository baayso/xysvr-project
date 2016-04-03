/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 中文系统提示实体类。
 * 
 * @author Tanrongrong(2015年1月24日 下午2:15:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class SyspromptZhcn implements Serializable {

    private static final long serialVersionUID = 5516923184197364460L;

    @JsonIgnore
    private String id; // 主键
    @JsonIgnore
    private Integer module;// 模块
    private Integer code; // 编码
    private String content; // 提示内容
    @JsonIgnore
    private Long mtime; // 修改时间

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getId() {
        return id;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

}
