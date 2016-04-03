/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.beanvalidator.BeanValidators;

import cn.xyspace.xysvr.common.core.form.manager.PageMgrForm;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;
import cn.xyspace.xysvr.function.manager.user.form.AddPermissionMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.PermissionIdMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdatePermissionMgrForm;
import cn.xyspace.xysvr.function.manager.user.service.IMgrPermissionService;

import com.github.pagehelper.PageInfo;

/**
 * 后台管理权限控制器。
 * 
 * @author JiangAnran(2015年3月3日 下午3:04:21)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping(value = "/user/permission")
public class MgrPermissionController {

    // private static final Logger logger = LoggerFactory.getLogger(MgrUserController.class);

    @Inject
    private IMgrPermissionService permissionService;

    @Inject
    private Validator validator;

    /**
     * 查询权限列表。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("permission:view")
    @RequestMapping("/list")
    public String list(@ModelAttribute("pageNumber") String pageNumber, @ModelAttribute("pageSize") String pageSize, ModelMap model) {
        PageMgrForm form = new PageMgrForm(pageNumber, pageSize);
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        PageInfo<MgrPermission> list = this.permissionService.findsAllPaging(pageNumber, pageSize);

        model.addAttribute("pageInfo", list);

        return "user/permission/list";
    }

    /**
     * 显示权限修改页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("permission:update")
    @RequestMapping("/editUI/{id}")
    public String editUI(@PathVariable("id") String id, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new PermissionIdMgrForm(id));

        // 根据页面传过来的Id获取相对应的Permission
        MgrPermission permission = this.permissionService.findById(id);

        model.addAttribute("permission", permission);

        return "user/permission/editUI";
    }

    /**
     * 显示权限添加页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("permission:create")
    @RequestMapping("/addUI")
    public String addUI() {

        return "user/permission/addUI";
    }

    /**
     * 添加权限
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("permission:create")
    @RequestMapping("/add")
    public String add(AddPermissionMgrForm form, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "添加失败");

        boolean success = this.permissionService.addPermission(form);
        if (success) {
            resultMap.put("status", "true");
            resultMap.put("message", "添加成功");
            resultMap.put("jumpUrl", "../permission/list");
        }
        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

    /**
     * 修改权限
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("permission:update")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, UpdatePermissionMgrForm form, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        form.setId(id);
        // BeanValidators.validateWithException(this.validator, new PermissionIdMgrForm(id));
        BeanValidators.validateWithException(this.validator, form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "修改失败");

        boolean success = this.permissionService.updatePermission(form);

        if (success) {
            resultMap.put("status", "true");
            resultMap.put("message", "修改成功");
            resultMap.put("jumpUrl", "../list");
        }
        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

    /**
     * 根据主键ID删除权限
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new PermissionIdMgrForm(id));
        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "删除失败");

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isPermitted("permission:delete")) {
            boolean success = this.permissionService.deletePermission(id);
            if (success) {
                resultMap.put("status", "true");
                resultMap.put("message", "删除成功");
                resultMap.put("jumpUrl", "../list");
            }
        }
        else {
            resultMap.put("message", "您没有权限");
        }

        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

}
