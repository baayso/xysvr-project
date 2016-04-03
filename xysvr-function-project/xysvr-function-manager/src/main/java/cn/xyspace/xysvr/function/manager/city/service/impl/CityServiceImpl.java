/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.city.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.function.manager.city.dao.ICityMybatisDao;
import cn.xyspace.xysvr.function.manager.city.entity.City;
import cn.xyspace.xysvr.function.manager.city.service.ICityService;

/**
 * 城市业务逻辑实现。
 *
 * @author CaoZhongsheng(2015年4月17日 上午11:21:56)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
@Service
public class CityServiceImpl implements ICityService {

    private ICityMybatisDao cityDao = MybatisDaoProxyFactory.createProxy(ICityMybatisDao.class);

    @Override
    public List<City> findsAll() {
        return this.cityDao.selectsAll();
    }

    @Override
    public List<City> findsAllWithoutChina() {
        return this.cityDao.selectsAllWithoutChina();
    }

}
