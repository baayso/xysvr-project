/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.beanvalidator.BeanValidators;

import cn.xyspace.xysvr.common.core.form.manager.PageMgrForm;
import cn.xyspace.xysvr.function.manager.user.entity.MgrPermission;
import cn.xyspace.xysvr.function.manager.user.entity.MgrRole;
import cn.xyspace.xysvr.function.manager.user.form.AddRoleMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.IdMgrForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdateRoleMgrForm;
import cn.xyspace.xysvr.function.manager.user.service.IMgrPermissionService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrRoleService;

import com.github.pagehelper.PageInfo;

/**
 * 后台管理角色控制器。
 * 
 * @author JiangAnran(2015年3月3日 下午3:04:21)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping(value = "/user/role")
public class MgrRoleController {

    // private static final Logger logger = LoggerFactory.getLogger(MgrUserController.class);

    @Inject
    private IMgrRoleService roleService;

    @Inject
    private IMgrPermissionService permissionService;

    @Inject
    private Validator validator;

    /**
     * 查询角色列表。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("role:view")
    @RequestMapping("/list")
    public String list(@ModelAttribute("pageNumber") String pageNumber, @ModelAttribute("pageSize") String pageSize, ModelMap model) {
        PageMgrForm form = new PageMgrForm(pageNumber, pageSize);
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        PageInfo<MgrRole> list = this.roleService.findsAllPaging(pageNumber, pageSize);

        model.addAttribute("pageInfo", list);

        return "user/role/list";
    }

    /**
     * 显示角色修改页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("role:update")
    @RequestMapping("/editUI/{id}")
    public String editUI(@PathVariable("id") String id, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new IdMgrForm(id));

        // 根据页面传过来的Id获取相对应的role
        MgrRole role = this.roleService.findById(id);

        model.addAttribute("role", role);

        return "user/role/editUI";
    }

    /**
     * 显示角色设置权限页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("role:authorize")
    @RequestMapping("/toSetPermissions/{id}")
    public String toSetPermissions(@PathVariable("id") String id, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new IdMgrForm(id));

        // 根据页面传过来的Id获取相对应的role
        List<MgrPermission> allPermissions = this.roleService.findCheckedPermissionsList(id);

        model.addAttribute("id", id);
        model.addAttribute("allPermissions", allPermissions);

        return "user/permission/permissionsTree";
    }

    /**
     * 显示角色添加页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("role:create")
    @RequestMapping("/addUI")
    public String addUI(ModelMap model) {
        List<MgrPermission> allPermissions = this.permissionService.findsAll();

        model.addAttribute("allPermissions", allPermissions);

        return "user/role/addUI";
    }

    /**
     * 添加角色
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("role:create")
    @RequestMapping("/add")
    public String add(AddRoleMgrForm form, ModelMap model) {
        String permissionIds = form.getPermissionIds();

        if (!StringUtils.isEmpty(permissionIds)) {
            permissionIds = permissionIds.substring(0, permissionIds.length() - 1);
            form.setPermissionIds(permissionIds);
        }

        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "添加失败");

        boolean success = this.roleService.addRole(form);
        if (success) {
            resultMap.put("status", "true");
            resultMap.put("message", "添加成功");
            resultMap.put("jumpUrl", "../role/list");
        }
        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

    /**
     * 修改角色
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("role:update")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, UpdateRoleMgrForm form, ModelMap model) {
        form.setId(id);
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new IdMgrForm(id));
        BeanValidators.validateWithException(this.validator, form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "修改失败");

        boolean success = this.roleService.updateRole(form);

        if (success) {
            resultMap.put("status", "true");
            resultMap.put("message", "修改成功");
            resultMap.put("jumpUrl", "../list");
        }
        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

    /**
     * 根据主键ID删除角色
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new IdMgrForm(id));
        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "删除失败");

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isPermitted("role:delete")) {
            boolean success = this.roleService.deleteRole(id);
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

    /**
     * 根据主键ID设置角色权限
     *
     * @since 1.0.0
     * @version 1.0.0
     * @return
     */
    @RequiresPermissions("role:authorize")
    @RequestMapping("/setPermissions/{id}")
    public String setPermissions(@PathVariable("id") String id, @ModelAttribute("permissionIds") String permissionIds, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new IdMgrForm(id));
        // BeanValidators.validateWithException(this.validator, new SetPermissionMgrForm(permissionIds));

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "修改失败");

        boolean success = this.roleService.setPermissions(id, permissionIds);

        if (success) {
            resultMap.put("status", "true");
            resultMap.put("message", "修改成功");
            resultMap.put("jumpUrl", "../list");
        }

        model.addAttribute("resultMap", resultMap);

        return "common/info";

    }

}
