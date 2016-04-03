/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 钱财转换运算工具类。
 *
 * @author CaoZhongsheng(2015年3月6日 上午11:10:37)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class MoneyFormatUtils {

    private final static int DECIMAL_PLACE = 2;

    // 让工具类彻底不可以实例化
    private MoneyFormatUtils() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 将给定BigDecimal类型的钱财转换成保留两位小数的字符串。
     * 
     * @param money
     *            BigDecimal类型的钱财 （单位：元）
     * @return 保留两位小数的BigDecimal
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static BigDecimal rounding(BigDecimal money) {
        if (money == null) {
            return new BigDecimal("0.00");
        }

        return money.setScale(DECIMAL_PLACE, RoundingMode.HALF_EVEN); // 返回保留两位小数的BigDecimal
    }

    /**
     * 将给定String类型的钱财转换成保留两位小数的字符串。
     * 
     * @param money
     *            String类型的钱财（单位：元）
     * @return 保留两位小数的字符串
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String rounding(String money) {
        if (money == null) {
            return "0.00";
        }

        BigDecimal beforeMoney = new BigDecimal(money).setScale(DECIMAL_PLACE, RoundingMode.HALF_EVEN);

        return String.format("%.2f", beforeMoney); // 返回保留两位小数的字符串
    }

}
