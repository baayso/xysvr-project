/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 系统配置信息实体类。
 *
 * @author WuQiying(2015年1月27日 下午6:38:51)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class SysConfig extends IdEntity {

    private static final long serialVersionUID = 4152577036914423963L;

    private String type; // 配置类型
    private String name; // 配置名称
    private String value; // 配置值
    private String descriptions;// 配置描述
    private Long mtime; // 修改时间

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

}
