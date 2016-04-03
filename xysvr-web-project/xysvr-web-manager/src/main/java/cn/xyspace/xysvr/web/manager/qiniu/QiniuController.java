/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager.qiniu;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Validator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

import cn.xyspace.xysvr.common.tool.qiniu.IQiniuService;
import cn.xyspace.xysvr.common.tool.qiniu.form.GetDownloadUrlForm;

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
@RequestMapping(value = "/qiniu")
public class QiniuController {

    // private static final Logger logger = LoggerFactory.getLogger(QiniuController.class);

    @Inject
    private IQiniuService qiniuService;

    @Inject
    private Validator validator;

    /**
     * 获取七牛云存储<b>私有空间</b>上传授权凭证。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/private/uptoken", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public Map<String, String> getPrivateUptoken() {
        String uptoken = this.qiniuService.getPrivateUptoken();

        Map<String, String> data = new HashMap<String, String>(1);
        data.put("uptoken", uptoken);

        return data;
    }

    /**
     * 获取七牛云存储<b>私有空间</b>下载URL。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/private/download", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public Map<String, String> getDownloadUrl(GetDownloadUrlForm form) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        String downloadUrl = this.qiniuService.getPrivateDownloadToken(form.getKey());

        Map<String, String> data = new HashMap<String, String>(1);
        data.put("url", downloadUrl);

        return data;
    }

    /**
     * 获取七牛云存储<b>公有空间</b>上传授权凭证。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/public/uptoken", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public Map<String, String> getPublicUptoken() {
        String uptoken = this.qiniuService.getPublicUptoken();

        Map<String, String> data = new HashMap<String, String>(1);
        data.put("uptoken", uptoken);

        return data;
    }

}
