/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.exception.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 异常处理器。
 * 
 * @author ChenFangjie(2014/12/20 17:37:10)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // Expose ModelAndView for chosen error view.
        String viewName = super.determineViewName(ex, request);
        if (viewName != null) { // JSP格式返回
            // 如果不是异步请求
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = super.determineStatusCode(request, viewName);
                if (statusCode != null) {
                    super.applyStatusCodeIfPossible(request, response, statusCode);
                    return getModelAndView(viewName, ex, request);
                }
            }
            else {
                // JSON格式返回
                Map<String, Object> model = new HashMap<String, Object>();
                if (this.logger.isDebugEnabled()) {
                    model.put("debug", true);
                } // exception
                model.put("message", ex.getMessage());
                model.put("failure", true);
                try {
                    response.getWriter().write("有异常啦!");
                }
                catch (IOException e) {
                    super.logger.error(e.getMessage(), e);
                }
                return new ModelAndView();
            }
            return null;
        }
        else {
            return null;
        }

    }

}
