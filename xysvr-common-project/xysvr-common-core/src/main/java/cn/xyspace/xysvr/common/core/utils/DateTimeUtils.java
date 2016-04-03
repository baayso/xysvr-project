/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取时间的工具类。
 * 
 * @author ChenFangJie(2015/1/1 15:35:30)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public final class DateTimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    /** 时间格式（yyyy-MM-dd HH:mm:ss） */
    public static final String LONG_TIME_STR = "yyyy-MM-dd HH:mm:ss";

    /** SimpleDateFormat（yyyy-MM-dd HH:mm:ss） */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(LONG_TIME_STR);

    // 让工具类彻底不可以实例化
    private DateTimeUtils() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 将分钟转换成毫秒。
     * 
     * @param minute
     *            分钟
     * @return 毫秒
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static final long minuteToMillis(long minute) {
        if (minute < 0L) {
            return 0L;
        }

        return minute * 60000L;
    }

    /**
     * 将小时转换成毫秒。
     * 
     * @param hour
     *            小时
     * @return 毫秒
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static final long hourToMillis(long hour) {
        if (hour < 0L) {
            return 0L;
        }

        return hour * 3600000L;
    }

    /**
     * 返回当前时间的字符串表示形式。
     * 
     * @return 当前时间的字符串表示形式
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static final String nowDateTimeString() {
        return DateTime.now().toString(LONG_TIME_STR);
    }

    /**
     * 返回给定时间的字符串表示形式。
     * 
     * @param nowTime
     *            给定时间
     * @return 给定时间的字符串表示形式
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String dateTimeString(DateTime dateTime) {
        return dateTime.toString(LONG_TIME_STR);
    }

    /**
     * 将字符串形式的时间（yyyy-MM-dd HH:mm:ss）转换为毫秒
     * 
     * @param strDateTime
     *            字符串形式的时间（yyyy-MM-dd HH:mm:ss）
     * @return 毫秒
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static long strDateTimeToMillis(String strDateTime) {
        long millis = -1;

        if (StringUtils.isNotBlank(strDateTime)) {
            try {
                millis = SIMPLE_DATE_FORMAT.parse(strDateTime).getTime();
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return millis;
    }

    /**
     * 将Long类型的时间转换成格式化的时间字符串。
     * 
     * @param millis
     *            Long类型的时间毫秒数
     * @return 格式化的时间字符串
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String millisToStrDate(long millis) {
        Date date = new Date(millis);
        return SIMPLE_DATE_FORMAT.format(date);
    }

}
