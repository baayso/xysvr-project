/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.city.service;

import java.util.List;

import cn.xyspace.xysvr.function.manager.city.entity.City;

/**
 * 城市业务逻辑处理接口。
 *
 * @author CaoZhongsheng(2015年4月17日 上午11:19:53)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public interface ICityService {

    /**
     * 查询所有城市列表。
     * 
     * @return 返回{@linkplain cn.xyspace.xysvr.function.manager.city.entity.City}列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<City> findsAll();

    /**
     * 查询所有城市列表（无中国）。
     * 
     * @return 返回{@linkplain cn.xyspace.xysvr.function.manager.city.entity.City}列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<City> findsAllWithoutChina();
}
