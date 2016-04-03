/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.exception.handler;

import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

import cn.xyspace.xysvr.common.core.exception.RestException;
import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;

import com.alibaba.fastjson.JSON;

/**
 * 自定义ExceptionHandler，专门处理RESTful异常。
 * 
 * @author ChenFangjie(2014/12/14 16:09:15)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
@ControllerAdvice // 会被Spring-MVC自动扫描，但又不属于Controller的annotation
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // private JsonMapper jsonMapper = new JsonMapper();

    /**
     * 处理RestException。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @ExceptionHandler(value = { RestException.class })
    public final ResponseEntity<?> handleException(RestException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        OperationResult result = new OperationResult(false, ex.syspromptStatus.value());
        result.setMessage(ex.getMessage());
        String body = JSON.toJSONString(result);
        // String body = this.jsonMapper.toJson(result);
        return super.handleExceptionInternal(ex, body, headers, ex.httpStatus, request);
    }

    /**
     * 处理JSR311 Validation异常。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    public final ResponseEntity<?> handleException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
        // String errorMessage = jsonMapper.toJson(errors);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        OperationResult result = new OperationResult(false, SyspromptStatus.ILLEGAL_DATA.value());
        result.setMessage(errors);
        String body = JSON.toJSONString(result);
        // String body = this.jsonMapper.toJson(result);
        return super.handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

}
