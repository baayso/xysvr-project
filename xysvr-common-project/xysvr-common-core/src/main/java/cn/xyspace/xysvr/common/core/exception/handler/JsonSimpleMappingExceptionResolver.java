/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import cn.xyspace.xysvr.common.core.exception.ApiServiceException;
import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.core.utils.WebUtils;

import com.alibaba.fastjson.JSON;

/**
 * 异常处理器，返回 json数据。
 * 
 * @author ChenFangjie(2014/12/20 17:37:10)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class JsonSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(JsonSimpleMappingExceptionResolver.class);

    // private JsonMapper jsonMapper = new JsonMapper();

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        SyspromptStatus syspromptStatus = SyspromptStatus.UNKNOW_ERROR;

        if (ex instanceof ApiServiceException) {
            ApiServiceException apiEx = (ApiServiceException) ex;
            httpStatus = apiEx.httpStatus;
            syspromptStatus = apiEx.syspromptStatus;
        }

        // 输出错误信息到日志文件中
        if (SyspromptStatus.UNKNOW_ERROR.equals(syspromptStatus)) {
            logger.error("出错了！", ex);
        }

        // String exMsg = ex.getMessage() != null && ex.getMessage().length() > 50 ? "出错了" : ex.getMessage();

        OperationResult result = new OperationResult(false, syspromptStatus.value());
        result.setMessage(ex.getMessage());

        String body = JSON.toJSONString(result);
        // String body = this.jsonMapper.toJson(result);

        WebUtils.writeJson(response, body, httpStatus.value());

        return new ModelAndView();
    }

}
