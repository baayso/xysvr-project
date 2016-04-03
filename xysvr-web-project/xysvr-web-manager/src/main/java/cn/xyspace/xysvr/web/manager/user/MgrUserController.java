/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

import cn.xyspace.xysvr.common.core.form.manager.PageMgrForm;
import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.ValidateUtils;
import cn.xyspace.xysvr.common.core.utils.WebUtils;
import cn.xyspace.xysvr.common.user.form.UploadUserIconForm;
import cn.xyspace.xysvr.common.user.shrio.ShiroUser;
import cn.xyspace.xysvr.function.manager.user.form.IdMgrForm;
import cn.xyspace.xysvr.function.manager.user.entity.MgrRole;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUser;
import cn.xyspace.xysvr.function.manager.user.form.FindUserForm;
import cn.xyspace.xysvr.function.manager.user.form.ModifyPwdForm;
import cn.xyspace.xysvr.function.manager.user.form.RegisterForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdateUserInfoForm;
import cn.xyspace.xysvr.function.manager.user.service.IMgrRoleService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserService;
import cn.xyspace.xysvr.function.manager.user.shiro.MgrShiroUser;

import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户控制器。
 * 
 * @author ChenFangjie(2015年2月5日 下午3:04:21)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping(value = "/user")
public class MgrUserController {

    // private static final Logger logger = LoggerFactory.getLogger(MgrUserController.class);

    @Inject
    private IMgrUserService userService;

    @Inject
    protected IMgrUserService mgrUserService;

    @Inject
    private Validator validator;

    @Inject
    private IMgrRoleService roleService;

    // /**
    // * 验证用户名是否可用。
    // *
    // * @since 1.0.0
    // * @version 1.0.0
    // */
    // @RequestMapping(value = "/hasUsername", produces = MediaTypes.JSON_UTF_8)
    // public ResponseEntity<OperationResult> hasUsername(@RequestParam("username") String username) {
    // OperationResult result = new OperationResult();
    // HttpStatus httpStatus = HttpStatus.OK;
    //
    // username = StringUtils.lowerCase(username);
    //
    // if (!ValidateUtils.isUsername(username)) {
    // httpStatus = HttpStatus.BAD_REQUEST;
    // result.setStatus(false);
    // result.setMessage("必须为5-16位的字符且以汉字或字母开头（只可包含汉字、字母、数字）");
    // }
    // else {
    // boolean isAvailable = this.userService.hasUsername(username);
    // result.setStatus(!isAvailable);
    // result.setStatusCode(isAvailable ? SyspromptStatus.USERNAME_ALREADY_USED.value() : SyspromptStatus.USERNAME_NOT_USED.value());
    // // result.setMessage(isAvailable ? "此用户名已被使用" : "此用户名未使用");
    // }
    //
    // return new ResponseEntity<OperationResult>(result, httpStatus);
    // }

    /**
     * 验证用户名是否可用。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/hasUsername", produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<Boolean> hasUsername(@RequestParam("username") String username) {
        boolean result = false;
        HttpStatus httpStatus = HttpStatus.OK;

        username = StringUtils.lowerCase(username);

        if (!ValidateUtils.isUsername(username)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result = false;
        }
        else {
            boolean isAvailable = this.userService.hasUsername(username);
            result = !isAvailable;
        }
        return new ResponseEntity<Boolean>(result, httpStatus);
    }

    // /**
    // * 验证Email是否可用。
    // *
    // * @since 1.0.0
    // * @version 1.0.0
    // */
    // @RequestMapping(value = "/hasEmail", produces = MediaTypes.JSON_UTF_8)
    // public ResponseEntity<OperationResult> hasEmail(@RequestParam("email") String email) {
    // OperationResult result = new OperationResult();
    // HttpStatus httpStatus = HttpStatus.OK;
    //
    // email = StringUtils.lowerCase(email);
    //
    // if (!ValidateUtils.isEmail(email)) {
    // httpStatus = HttpStatus.BAD_REQUEST;
    // result.setStatus(false);
    // result.setMessage("请输入正确的电子邮件地址");
    // }
    // else {
    // boolean isAvailable = this.userService.hasEmail(email);
    // result.setStatus(!isAvailable);
    // result.setStatusCode(isAvailable ? SyspromptStatus.EMAIL_ALREADY_USED.value() : SyspromptStatus.EMAIL_NOT_USED.value());
    // // result.setMessage(isAvailable ? "此email已被使用" : "此email未使用");
    // }
    //
    // return new ResponseEntity<OperationResult>(result, httpStatus);
    // }

    /**
     * 验证Email是否可用。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/hasEmail", produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<Boolean> hasEmail(@RequestParam("email") String email) {
        boolean result = false;
        HttpStatus httpStatus = HttpStatus.OK;

        email = StringUtils.lowerCase(email);

        if (!ValidateUtils.isEmail(email)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result = false;
        }
        else {
            boolean isAvailable = this.userService.hasEmail(email);
            result = !isAvailable;
        }
        return new ResponseEntity<Boolean>(result, httpStatus);
    }

    /**
     * 添加管理员。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(RegisterForm form, Model model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        // 注册用户
        boolean isSuccess = this.userService.register(form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", "false");
        resultMap.put("message", "添加失败");

        if (isSuccess) {
            resultMap.put("status", "true");
            resultMap.put("message", "添加成功");
            resultMap.put("jumpUrl", "list");
        }
        model.addAttribute("resultMap", resultMap);

        return "common/info";

    }

    /**
     * 添加管理员页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:create")
    @RequestMapping("/addUI")
    public String addUI(ModelMap model) {
        List<MgrRole> allRoles = this.roleService.findsAll();
        model.addAttribute("allRoles", allRoles);

        return "user/addUI";
    }

    /**
     * 打开登录页面。真正的登录POST请求由Filter完成。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, null);

        return "user/login";
    }

    /**
     * 登录出错页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, HttpServletRequest request, Model model) {
        // 提取登录失败时的异常
        String exceptionClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        String errorMsg = null;

        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            errorMsg = "用户名或密码错误";
        }
        else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            errorMsg = "用户名或密码错误";
        }
        else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            errorMsg = "登录失败次数过多，请稍候在试";
        }
        else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            errorMsg = "帐号已被锁定";
        }
        else if (DisabledAccountException.class.getName().equals(exceptionClassName)) {
            errorMsg = "帐号已被禁用";
        }
        else if (exceptionClassName != null) {
            errorMsg = "登录出错";
        }

        model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, errorMsg);

        return "user/login";
    }

    /**
     * 管理员列表。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:view")
    @RequestMapping("/list")
    public String list(@ModelAttribute("pageNumber") String pageNumber, @ModelAttribute("pageSize") String pageSize, ModelMap model) {
        PageMgrForm form = new PageMgrForm(pageNumber, pageSize);
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        PageInfo<MgrUser> list = this.userService.findsAllWithRoles(pageNumber, pageSize);

        model.addAttribute("pageInfo", list);

        return "user/list";
    }

    /**
     * 更新管理员信息页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/editUI", method = RequestMethod.POST)
    public String editUI(FindUserForm form, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        List<MgrRole> allRoles = this.roleService.findsCheckedAll(form.getUsername());
        model.addAttribute("allRoles", allRoles);

        MgrUser user = this.userService.findById(form);

        model.addAttribute("user", user);

        return "user/editUI";
    }

    /**
     * 获取当前用户信息。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:view")
    @RequestMapping(value = "/info  ", method = RequestMethod.GET)
    public String read(ModelMap model) {
        FindUserForm form = new FindUserForm();

        MgrShiroUser mgrShiroUser = (MgrShiroUser) SecurityUtils.getSubject().getPrincipal();

        form.setId(mgrShiroUser.getId());
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        MgrUser user = this.userService.findById(form);

        model.addAttribute("user", user);

        return "user/info";
    }

    /**
     * 修改用户密码表单页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:view")
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String showPassword() {
        return "user/password";
    }

    /**
     * 修改用户密码。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:view")
    @RequestMapping(value = "/edit/password", method = RequestMethod.POST)
    public String editPassword(ModifyPwdForm form, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        boolean isSuccess = this.userService.modifyPassword(form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", "false");
        resultMap.put("message", "修改失败");

        if (isSuccess) {
            resultMap.put("status", "true");
            resultMap.put("message", "修改成功，请重新登陆");
            resultMap.put("jumpUrl", "../logout");
        }

        model.addAttribute("resultMap", resultMap);

        return "common/info";
    }

    /**
     * 用户上传头像。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:view")
    @RequestMapping(value = "/icon/upload", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> uploadIcon(UploadUserIconForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        form.setClientIp(WebUtils.getRealIp(request));
        form.setHttpUrl(request.getRequestURI());
        form.setHttpMethod(request.getMethod());

        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        form.setUserId(shiroUser.getId());
        form.setUsername(shiroUser.getUsername());
        // form.setIconPath(shiroUser.getIconPath());

        boolean successed = this.userService.uploadUserIcon(form);

        HttpStatus httpStatus = successed ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        OperationResult result = new OperationResult();
        result.setStatus(successed);
        // result.setStatusCode(successed ? SyspromptStatus.UPLOAD_SUCCESS.value() : SyspromptStatus.UPLOAD_FAILURE.value());
        result.setMessage(successed ? "上传成功" : "上传失败");

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 更新管理员信息。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(UpdateUserInfoForm form, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        boolean isSuccess = this.userService.updateUserInfo(form);

        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", "false");
        resultMap.put("message", "修改失败");

        if (isSuccess) {
            resultMap.put("status", "true");
            resultMap.put("message", "修改成功");
            resultMap.put("jumpUrl", "list");
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
    public String del(@PathVariable String id, ModelMap model) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, new IdMgrForm(id));
        // 返回操作结果信息map
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("status", "false");
        resultMap.put("message", "删除失败");

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isPermitted("user:delete")) {
            boolean success = this.userService.deleteUser(id);
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
