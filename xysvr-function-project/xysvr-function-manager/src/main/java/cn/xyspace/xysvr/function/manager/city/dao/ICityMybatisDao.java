/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.city.dao;

import java.util.List;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.city.entity.City;

/**
 * 城市表数据访问对象接口。
 *
 * @author CaoZhongsheng(2015年4月17日 上午11:04:15)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface ICityMybatisDao extends IBaseMybatisDao {

    /**
     * 查询所有城市列表。
     * 
     * @return 返回{@linkplain cn.xyspace.xysvr.function.manager.city.entity.City}列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<City> selectsAll();

    /**
     * 查询所有城市列表（无中国）。
     * 
     * @return 返回{@linkplain cn.xyspace.xysvr.function.manager.city.entity.City}列表
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<City> selectsAllWithoutChina();
}
