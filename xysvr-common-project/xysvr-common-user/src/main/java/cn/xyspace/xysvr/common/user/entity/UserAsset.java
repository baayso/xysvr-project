/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 用户财富实体类。
 * 
 * @author ChenFangjie(2014/11/29 20:45:06)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UserAsset extends IdEntity {

    private static final long serialVersionUID = 7282511527766344581L;

    private Integer bonusPoint; // 用户积分
    private Integer money; // 用户基本金币
    private Integer lockMoney; // 用户锁定金币
    private Integer earnMoney; // 用户所赚得的金币
    private BigDecimal rmoney; // 用户真实钱财
    private BigDecimal lockRmoney; // 用户锁定的真实钱财
    private Integer lucky; // 用户幸运指数
    private Integer hitface; // 用户打脸指数
    private Integer hittedface; // 用户被打脸指数
    private Integer merit; // 用户功德
    private Integer level; // 用户等级

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Integer getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(Integer bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(Integer lockMoney) {
        this.lockMoney = lockMoney;
    }

    public Integer getEarnMoney() {
        return earnMoney;
    }

    public void setEarnMoney(Integer earnMoney) {
        this.earnMoney = earnMoney;
    }

    public BigDecimal getRmoney() {
        return rmoney;
    }

    public void setRmoney(BigDecimal rmoney) {
        this.rmoney = rmoney;
    }

    public BigDecimal getLockRmoney() {
        return lockRmoney;
    }

    public void setLockRmoney(BigDecimal lockRmoney) {
        this.lockRmoney = lockRmoney;
    }

    public Integer getLucky() {
        return lucky;
    }

    public void setLucky(Integer lucky) {
        this.lucky = lucky;
    }

    public Integer getHitface() {
        return hitface;
    }

    public void setHitface(Integer hitface) {
        this.hitface = hitface;
    }

    public Integer getHittedface() {
        return hittedface;
    }

    public void setHittedface(Integer hittedface) {
        this.hittedface = hittedface;
    }

    public Integer getMerit() {
        return merit;
    }

    public void setMerit(Integer merit) {
        this.merit = merit;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
