/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.web.bind.annotation;

import java.lang.annotation.*;

import cn.xyspace.xysvr.function.manager.user.Constants;

/**
 * 绑定当前登录的用户,不同于@ModelAttribute。
 * 
 * @author JiangAnran(2015年3月7日 下午5:02:31)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;

}
