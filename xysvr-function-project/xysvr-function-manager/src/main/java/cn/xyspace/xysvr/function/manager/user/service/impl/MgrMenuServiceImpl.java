/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.function.manager.user.dao.IMgrMenuMybatisDao;
import cn.xyspace.xysvr.function.manager.user.entity.MgrMenu;
import cn.xyspace.xysvr.function.manager.user.service.IMgrMenuService;

/**
 * 后台管理菜单业务逻辑实现。
 * 
 * @author JiangAnran(2015年3月3日 下午2:43:33)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class MgrMenuServiceImpl implements IMgrMenuService {

    private IMgrMenuMybatisDao mgrMenuDao = MybatisDaoProxyFactory.createProxy(IMgrMenuMybatisDao.class);

    @Override
    public List<MgrMenu> findsAll() {

        return this.mgrMenuDao.selectsWithChildren();

    }

}
