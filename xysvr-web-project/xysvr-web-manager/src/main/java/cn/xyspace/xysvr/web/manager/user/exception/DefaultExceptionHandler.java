/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager.user.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 没有权限异常处理控制器。
 * 
 * @author JiangAnran(2015年3月7日 下午4:54:41)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 没有权限异常。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @ExceptionHandler({ UnauthorizedException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("common/unauthorized");
        return mv;
    }

}
