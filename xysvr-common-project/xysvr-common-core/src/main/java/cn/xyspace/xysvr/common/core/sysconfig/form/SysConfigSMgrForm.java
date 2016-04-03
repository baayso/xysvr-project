/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.sysconfig.form;

/**
 * 存储后台系统配置条件查询页面传的数据。
 *
 * @author WuQiying(2015年1月29日 下午4:53:44)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class SysConfigSMgrForm {

    private String name;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
