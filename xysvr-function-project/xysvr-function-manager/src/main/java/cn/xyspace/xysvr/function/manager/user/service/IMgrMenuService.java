/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service;

import java.util.List;

import cn.xyspace.xysvr.function.manager.user.entity.MgrMenu;

/**
 * 后台管理菜单业务逻辑接口。
 * 
 * @author JiangAnran(2015年3月10日 下午2:46:14)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IMgrMenuService {

    /**
     * 获取菜单列表。
     * 
     * @return MgrMenu类型list
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrMenu> findsAll();

}
