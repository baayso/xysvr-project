/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户性别枚举。
 *
 * @author ChenFangjie(2015年2月3日 上午10:29:28)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public enum UserGender {

    /** 保密 */
    SECRET("S"),
    /** 男 */
    MALE("M"),
    /** 女 */
    FEMALE("F");

    private String desc;

    /**
     * 返回当前性别的描述。
     */
    public String getDesc() {
        return desc;
    }

    private UserGender(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    /**
     * 返回指定序数的枚举常量。
     * 
     * @param ordinal
     *            枚举常量的序数
     * @return 枚举序数对应的枚举
     * @throws IllegalArgumentException
     *             如果该序数没有指定的枚举
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static UserGender valueOf(int ordinal) {
        for (UserGender gender : values()) {
            if (gender.ordinal() == ordinal) {
                return gender;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + ordinal + "]");
    }

    /**
     * 是否包含指定名称的枚举项。
     * 
     * @param name
     *            指定名称
     * @return 当传入的名称包含在枚举项中时返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static boolean contains(String name) {
        if (StringUtils.isNotBlank(name)) {
            // 所有的枚举值
            UserGender[] values = UserGender.values();
            // 遍历查找
            for (UserGender gender : values) {
                if (gender.name().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

}
