/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager.sysconfig;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.beanvalidator.BeanValidators;

import cn.xyspace.xysvr.common.core.sysconfig.entity.SysConfig;
import cn.xyspace.xysvr.common.core.sysconfig.form.SysConfigMgrForm;
import cn.xyspace.xysvr.common.core.sysconfig.form.SysConfigSearchMgrForm;
import cn.xyspace.xysvr.common.core.sysconfig.form.UpdateSysConfigMgrForm;
import cn.xyspace.xysvr.common.core.sysconfig.service.ISysConfigService;

import com.github.pagehelper.PageInfo;

/**
 * 配置信息控制器。
 *
 * @author WuQiying(2015年2月3日 下午3:20:53)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("/sysConfig")
public class SysConfigController {

    @Inject
    private ISysConfigService sysConfigService;

    @Inject
    private Validator validator;

    /**
     * 获取配置信息列表。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/list")
    public String list(@ModelAttribute("pageNumber") String pageNumber, @ModelAttribute("pageSize") String pageSize, @ModelAttribute("type") String type, @ModelAttribute("name") String name,
            ModelMap model) {

        SysConfigSearchMgrForm form = new SysConfigSearchMgrForm();
        form.setName(name);
        form.setType(type);
        form.setPageNumber(pageNumber);
        form.setPageSize(pageSize);
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        String mtype = StringUtils.trim(type);
        String mname = StringUtils.trim(name);
        PageInfo<SysConfig> list = this.sysConfigService.findALLSysConfig(pageNumber, pageSize, mtype, mname);

        model.addAttribute("pageInfo", list);

        return "sysConfig/list";
    }

    /**
     * 显示系统配置修改页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/editUI/{id}")
    public String editUI(@PathVariable String id, ModelMap model) {

        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new SysConfigMgrForm(id));

        // 根据页面传过来的Id获取相对应的SysConfig
        SysConfig sysconfig = this.sysConfigService.findSysConfigById(id);

        model.addAttribute("sysconfig", sysconfig);

        if ("true".equals(sysconfig.getValue()) || "false".equals(sysconfig.getValue())) {
            return "sysConfig/editBoolean";
        }

        return "sysConfig/edit";
    }

    /**
     * 修改系统配置。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, UpdateSysConfigMgrForm form, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        form.setId(id);
        BeanValidators.validateWithException(this.validator, form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "修改失败");

        // 根据页面传过来的Id获取相对应的SysConfig
        SysConfig sysconfig = this.sysConfigService.findSysConfigById(id);

        if (("page_size".equals(sysconfig.getName()) && Integer.parseInt(form.getValue()) > 0) || (!"page_size".equals(sysconfig.getName()))) {

            boolean success = this.sysConfigService.updateSysConfig(form);
            if (success) {
                resultMap.put("status", "true");
                resultMap.put("message", "修改成功");
                resultMap.put("jumpUrl", "../list");
            }
        }

        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

}
