/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 后台管理菜单实体类。
 *
 * @author JiangAnran(2015年3月10日 下午1:45:07)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class MgrMenu extends IdEntity {

    private static final long serialVersionUID = -8820518204681893017L;

    private String name; // 菜单条目名
    private String url; // 菜单链接地址
    private Integer sortnum; // 排序数字
    private String spanClass; // 菜单条目样式
    private String needperm; // 是否有访问权限（默认FALSE）
    private boolean authorized; // 是否有访问权限（默认FALSE）
    private boolean isdiv; // 是否有访问权限（默认FALSE）
    private String parentId; // 父菜单ID

    private List<MgrMenu> menus;// 封装菜单信息

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSortnum() {
        return sortnum;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    public String getSpanClass() {
        return spanClass;
    }

    public void setSpanClass(String spanClass) {
        this.spanClass = spanClass;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<MgrMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<MgrMenu> menus) {
        this.menus = menus;
    }

    public String getNeedperm() {
        return needperm;
    }

    public void setNeedperm(String needperm) {
        this.needperm = needperm;
    }

    public boolean isIsdiv() {
        return isdiv;
    }

    public void setIsdiv(boolean isdiv) {
        this.isdiv = isdiv;
    }

}
