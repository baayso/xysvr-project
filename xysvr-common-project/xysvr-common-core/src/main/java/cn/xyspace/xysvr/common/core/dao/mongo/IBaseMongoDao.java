/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.mongo;

import java.util.List;

/**
 * 公共 MongoDB 处理接口，接口中定义了基本的操作。
 * 
 * @author ChenFangjie(2015年3月4日 下午2:10:26)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IBaseMongoDao<T> {

    /**
     * 判断是否已连接到MongoDB服务器。
     * 
     * @return 已连接返回true，否则返回false。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean isMongoConnected();

    /**
     * 新增。
     * 
     * @param entity
     *            实体对象
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void insert(T entity);

    /**
     * 根据id删除。
     * 
     * @param id
     *            编号
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void delete(String id);

    // public WriteResult updateObject(String id, String name);

    /**
     * 根据id查询。
     * 
     * @param id
     *            编号
     * @return 实体对象
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public T get(String id);

    /**
     * 查询所有数据。
     * 
     * @return 所有数据
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<T> getAll();

    /**
     * 当T类型的collection不存在时则创建它。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void createCollection();

    /**
     * 当T类型的collection存在时则删除它。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void dropCollection();

}
