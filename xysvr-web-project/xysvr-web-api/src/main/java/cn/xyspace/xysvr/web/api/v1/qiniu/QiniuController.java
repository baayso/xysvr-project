/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.api.v1.qiniu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.web.MediaTypes;

import cn.xyspace.xysvr.common.core.exception.ApiServiceException;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.Constants;
import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.tool.qiniu.IQiniuService;
import cn.xyspace.xysvr.common.tool.qiniu.entity.PersistentNotify;
import cn.xyspace.xysvr.common.tool.qiniu.entity.PersistentNotifyItem;
import cn.xyspace.xysvr.common.tool.qiniu.form.GetDownloadUrlForm;
import cn.xyspace.xysvr.common.tool.qiniu.form.GetUptokenForm;

/**
 * 七牛云存储 RESTful API。
 * 
 * @author ChenFangjie(2014/12/16 16:37:12)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "/api/v1/qiniu")
public class QiniuController {

    private static final Logger logger = LoggerFactory.getLogger(QiniuController.class);

    @Inject
    private IQiniuService qiniuService;

    @Inject
    private Validator validator;

    private JsonMapper jsonMapper = new JsonMapper();

    private static final int QINIU_PERSISTENT_CODE_SUCCESS = 0;
    private static final int QINIU_PERSISTENT_CODE_FAILED = 3;

    /**
     * 获取七牛云存储<b>私有空间</b>上传授权凭证。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/private/uptoken", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> getPrivateUptoken(GetUptokenForm form) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        HttpStatus httpStatus = HttpStatus.OK;
        OperationResult result = new OperationResult();

        String uptoken = this.qiniuService.getPrivateUptoken(form);

        if (StringUtils.isNotBlank(uptoken)) {
            Map<String, String> data = new HashMap<String, String>(1);
            data.put("uptoken", uptoken);

            result.setStatus(true);
            result.setStatusCode(SyspromptStatus.ACHIEVE_SUCCESS.value());
            // result.setMessage("获取成功");
            result.setData(data);
        }
        else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ACHIEVE_FALIURE.value());
            // result.setMessage("获取失败");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 获取七牛云存储<b>私有空间</b>下载URL。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/private/download", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> getPrivateDownloadUrl(GetDownloadUrlForm form) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        HttpStatus httpStatus = HttpStatus.OK;
        OperationResult result = new OperationResult();

        String downloadUrl = this.qiniuService.getPrivateDownloadToken(form.getKey(), form.getExpires());

        if (StringUtils.isNotBlank(downloadUrl)) {
            Map<String, String> data = new HashMap<String, String>(1);
            data.put("realDownloadUrl", downloadUrl);

            result.setStatus(true);
            // result.setMessage("获取成功");
            result.setStatusCode(SyspromptStatus.ACHIEVE_SUCCESS.value());
            result.setData(data);
        }
        else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ACHIEVE_FALIURE.value());
            // result.setMessage("获取失败");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 获取七牛云存储<b>公有空间</b>上传授权凭证。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/public/uptoken", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> getPublicUptoken(GetUptokenForm form) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        HttpStatus httpStatus = HttpStatus.OK;
        OperationResult result = new OperationResult();

        String uptoken = this.qiniuService.getPublicUptoken(form);

        if (StringUtils.isNotBlank(uptoken)) {
            Map<String, String> data = new HashMap<String, String>(1);
            data.put("uptoken", uptoken);

            result.setStatus(true);
            result.setStatusCode(SyspromptStatus.ACHIEVE_SUCCESS.value());
            // result.setMessage("获取成功");
            result.setData(data);
        }
        else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ACHIEVE_FALIURE.value());
            // result.setMessage("获取失败");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 接收预转持久化结果通知。必须在公网上可以正常进行POST请求并能响应 HTTP/1.1 200 OK
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/persistent/notify", method = RequestMethod.POST)
    public void notify(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), Constants.UTF_8))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        catch (IOException e) {
            logger.error("接收七牛云存储预转持久化结果通知失败！", e);
        }
        catch (Exception e) {
            logger.error("接收七牛云存储预转持久化结果通知失败！", e);
        }

        String notifyJson = sb.toString();

        logger.info("七牛云存储预转持久化结果通知：" + notifyJson);

        PersistentNotify notify = this.jsonMapper.fromJson(notifyJson, PersistentNotify.class);

        if (notify == null) {
            logger.error("七牛云存储预转持久化结果通知数据格式不正确！" + notifyJson);
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ILLEGAL_DATA);
        }

        Integer code = notify.getCode();

        if (code != null) {
            if (QINIU_PERSISTENT_CODE_SUCCESS == code.intValue()) {
                String inputKey = notify.getInputKey();
                String inputBucket = notify.getInputBucket();
                List<PersistentNotifyItem> items = notify.getItems();
                PersistentNotifyItem item = items.get(0);
                Integer itemCode = item.getCode();

                if (QINIU_PERSISTENT_CODE_SUCCESS == itemCode.intValue()) {
                    String itemKey = item.getKey();

                    if (Config.QINIU_PRIVATE_BUCKET_NAME.equals(inputBucket)) {
                        this.qiniuService.deletePrivateFile(inputKey);
                        this.qiniuService.renamePrivateFile(itemKey, inputKey);
                    }
                    else if (Config.QINIU_PUBLIC_BUCKET_NAME.equals(inputBucket)) {
                        this.qiniuService.deletePublicFile(inputKey);
                        this.qiniuService.renamePublicFile(itemKey, inputKey);
                    }
                    else {
                        logger.error("七牛云存储预转持久化失败，未知的空间！" + notifyJson);
                    }
                }
                else {
                    logger.error("七牛云存储预转持久化失败！" + notifyJson);
                }
            }
            else if (QINIU_PERSISTENT_CODE_FAILED == code.intValue()) {
                logger.error("七牛云存储预转持久化失败！" + notifyJson);
            }
            else {
                logger.error("七牛云存储预转持久化失败！" + notifyJson);
            }
        }
    }

}
