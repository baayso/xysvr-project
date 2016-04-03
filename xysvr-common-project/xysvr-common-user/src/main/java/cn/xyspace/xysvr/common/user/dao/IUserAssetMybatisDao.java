/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.common.user.entity.UserAsset;

/**
 * 用户财富表数据访问对象接口。
 * 
 * @author ChenFangjie(2014/11/30 18:24:57)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IUserAssetMybatisDao extends IBaseMybatisDao {

    /**
     * 新增用户财富数据。
     * 
     * @param entity
     *            用户财富实体
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(UserAsset entity);

    /**
     * 根据主键更新基本金币。
     * 
     * @param id
     *            主键
     * @param money
     *            基本金币
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateMoneyById(@Param(value = "id") String id, @Param(value = "money") Integer money);

    /**
     * 根据主键更新基本金币和钱财。
     * 
     * @param id
     *            主键
     * @param money
     *            基本金币
     * @param rmoney
     *            基本钱财
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateMoneyAndRmoneyById(@Param(value = "id") String id, @Param(value = "money") Integer money, @Param(value = "rmoney") BigDecimal rmoney);

    /**
     * 根据主键更新基本金币（存储过程）。
     * 
     * @param id
     *            主键
     * @param username
     *            用户名
     * @param recordId
     *            操作记录id
     * @param desc
     *            操作描述
     * @param money
     *            基本金币
     * @return 更新成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean processingUpdateMoneyById(@Param(value = "id") String id, @Param(value = "username") String username, @Param(value = "recordId") String recordId,
            @Param(value = "desc") String desc, @Param(value = "money") Integer money);

    /**
     * 根据主键更新基本钱财。
     * 
     * @param id
     *            主键
     * @param rmoney
     *            基本钱财
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateRmoneyById(@Param(value = "id") String id, @Param(value = "rmoney") BigDecimal rmoney);

    /**
     * 根据主键更新锁定钱财。
     * 
     * @param id
     *            主键
     * @param lRmoney
     *            锁定钱财
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateLrmoneyById(@Param(value = "id") String id, @Param(value = "lRmoney") BigDecimal lRmoney);

    /**
     * 根据主键更新锁定金币。
     * 
     * @param id
     *            主键
     * @param lockMoney
     *            锁定金币
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateLockMoneyById(@Param(value = "id") String id, @Param(value = "lockMoney") Integer lockMoney);

    /**
     * 根据主键更新基本金币和锁定金币。
     * 
     * @param id
     *            主键
     * @param money
     *            基本金币
     * @param lockMoney
     *            锁定金币
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateMoneyAndLockMoneyById(@Param(value = "id") String id, @Param(value = "money") Integer money, @Param(value = "lockMoney") Integer lockMoney);

    /**
     * 根据主键更新积分。
     * 
     * @param id
     *            主键
     * @param bonusPoint
     *            积分
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateBonusPointById(@Param(value = "id") String id, @Param(value = "bonusPoint") Integer bonusPoint);

    /**
     * 根据主键更新积分和基本金币。
     * 
     * @param id
     *            主键
     * @param bonusPoint
     *            积分
     * @param money
     *            金币
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateBonusPointAndMoneyById(@Param(value = "id") String id, @Param(value = "bonusPoint") Integer bonusPoint, @Param(value = "money") Integer money);

    /**
     * 根据主键更新积分和基本金币（存储过程）。
     * 
     * @param id
     *            主键
     * @param username
     *            用户名
     * @param recordId
     *            记录id
     * @param ruleType
     *            财富规则类型
     * @return 更新成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean processingUpdateBonusPointAndMoneyById(@Param(value = "id") String id, @Param(value = "username") String username, @Param(value = "recordId") String recordId,
            @Param(value = "ruleType") Integer ruleType);

    /**
     * 根据主键更新真实钱财和锁定真实钱财。
     * 
     * @param id
     *            主键（同用户表id）
     * @param rmoney
     *            给定的真实钱财
     * @param lockRmoney
     *            给定的锁定真实钱财
     * @return 受影响行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateRmoneyAndLockRmoneyById(@Param(value = "id") String id, @Param(value = "rmoney") BigDecimal rmoney, @Param(value = "lockRmoney") BigDecimal lockRmoney);

    /**
     * 根据主键查询用户财富（不使用行级排他锁查询）。
     * 
     * @param id
     *            主键
     * @return 返回 {@linkplain com.xiaoyou.xysvr.common.user.entity.UserAsset}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public UserAsset selectById(String id);

    /**
     * 根据主键查询用户财富（使用行级排他锁查询）。
     * 
     * @param id
     *            主键
     * @return 返回 {@linkplain com.xiaoyou.xysvr.common.user.entity.UserAsset}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public UserAsset queryById(String id);

    /**
     * 根据主键更新基本金币和功德。
     * 
     * @param id
     *            主键
     * @param money
     *            金币
     * @param merit
     *            功德
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateMoneyAndMeritById(@Param(value = "id") String id, @Param(value = "money") Integer money, @Param(value = "merit") Integer merit);

    /**
     * 根据主键更新用户赢取的金币。
     * 
     * @param id
     *            主键
     * @param earnMoney
     *            更新后的赢取钱财
     * @return 受影响行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateEarnMoneyById(@Param(value = "id") String id, @Param(value = "earnMoney") Integer earnMoney);
}
