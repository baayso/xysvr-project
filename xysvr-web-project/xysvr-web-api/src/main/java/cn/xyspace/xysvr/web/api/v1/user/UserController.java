/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.api.v1.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

import cn.xyspace.xysvr.common.core.utils.OperationResult;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.core.utils.ValidateUtils;
import cn.xyspace.xysvr.common.core.utils.WebUtils;
import cn.xyspace.xysvr.common.tool.mail.service.MailService;
import cn.xyspace.xysvr.common.user.entity.User;
import cn.xyspace.xysvr.common.user.form.ModifyPwdForm;
import cn.xyspace.xysvr.common.user.form.RegisterForm;
import cn.xyspace.xysvr.common.user.form.ResetPwdForm;
import cn.xyspace.xysvr.common.user.form.SendResetPwdMailForm;
import cn.xyspace.xysvr.common.user.form.UpdateUserInfoForm;
import cn.xyspace.xysvr.common.user.form.UploadUserIconForm;
import cn.xyspace.xysvr.common.user.service.IUserService;
import cn.xyspace.xysvr.common.user.shrio.ShiroUser;
import cn.xyspace.xysvr.common.user.utils.UserUtils;
import cn.xyspace.xysvr.function.api.secretcode.utils.SecretCodeGenerator;

/**
 * 用户 RESTful API。
 * 
 * @author ChenFangjie(2014/12/11 21:42:55)
 * 
 * @since 1.0.0
 * 
 * @version 1.1.0
 *
 */
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private IUserService userService;

    @Inject
    private MailService mailService;

    @Inject
    private Validator validator;

    /**
     * 验证用户名是否可用。
     * 
     * @param username
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/hasUsername", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> hasUsername(HttpServletRequest request) {
        OperationResult result = new OperationResult();
        HttpStatus httpStatus = HttpStatus.OK;

        String username = request.getParameter("username");
        username = StringUtils.lowerCase(username);

        if (!ValidateUtils.isUsername(username)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ILLEGAL_DATA.value());
            result.setMessage("必须为5-16位的字符且以汉字或字母开头（只可包含汉字、字母、数字）");
        }
        else {
            boolean isAvailable = this.userService.hasUsername(username);
            result.setStatus(!isAvailable);
            result.setStatusCode(isAvailable ? SyspromptStatus.USERNAME_ALREADY_USED.value() : SyspromptStatus.USERNAME_NOT_USED.value());
            // result.setMessage(isAvailable ? "此用户名已被使用" : "此用户名未使用");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 验证Email是否可用。
     * 
     * @param username
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/hasEmail", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> hasEmail(HttpServletRequest request) {
        OperationResult result = new OperationResult();
        HttpStatus httpStatus = HttpStatus.OK;

        String email = request.getParameter("email");
        email = StringUtils.lowerCase(email);

        if (!ValidateUtils.isEmail(email)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ILLEGAL_DATA.value());
            result.setMessage("请输入正确的电子邮件地址");
        }
        else {
            boolean isAvailable = this.userService.hasEmail(email);
            result.setStatus(!isAvailable);
            result.setStatusCode(isAvailable ? SyspromptStatus.EMAIL_ALREADY_USED.value() : SyspromptStatus.EMAIL_NOT_USED.value());
            // result.setMessage(isAvailable ? "此email已被使用" : "此email未使用");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 创建新的用户（注册）。
     * 
     * @since 1.0.0
     * @version 1.1.0
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> create(RegisterForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        form.setClientIp(WebUtils.getRealIp(request));

        // 注册用户
        boolean isSuccess = this.userService.register(form);

        HttpStatus httpStatus = isSuccess ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
        OperationResult result = new OperationResult();
        result.setStatus(isSuccess);
        result.setStatusCode(isSuccess ? SyspromptStatus.REGISTER_SUCCESS.value() : SyspromptStatus.REGISTER_FAILURE.value());
        // result.setMessage(isSuccess ? "注册成功" : "注册失败");

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 修改用户密码。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/password/modify", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> modifyPassword(ModifyPwdForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        form.setClientIp(WebUtils.getRealIp(request));
        form.setHttpUrl(request.getRequestURI());
        form.setHttpMethod(request.getMethod());

        ShiroUser shiroUser = UserUtils.getCurrentUser();
        form.setUserId(shiroUser.getId());
        form.setUsername(shiroUser.getUsername());

        boolean successed = this.userService.modifyPassword(form);

        HttpStatus httpStatus = successed ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        OperationResult result = new OperationResult();
        result.setStatus(successed);
        result.setStatusCode(successed ? SyspromptStatus.CHANGE_PASSWORD_SUCCESS.value() : SyspromptStatus.CHANGE_PASSWORD_FAILURE.value());
        // result.setMessage(successed ? "修改密码成功" : "修改密码失败");

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 发送重置密码邮件。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/password/sendResetMail", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> sendResetPwdMailCaptcha(SendResetPwdMailForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        User user = this.userService.findByUsername(StringUtils.lowerCase(form.getUsername()));

        HttpStatus httpStatus = HttpStatus.OK;

        OperationResult result = new OperationResult();

        if (user != null && user.getEmail() != null) {
            String username = user.getUsername();
            String email = user.getEmail();

            String code = SecretCodeGenerator.generateCode();

            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(username, code);

            try {
                ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
                Configuration cfg = Configuration.defaultConfiguration();
                GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
                Template t = gt.getTemplate("/template/resetPassword.html");
                t.binding("username", username);
                t.binding("code", code);
                String str = t.render();

                this.mailService.sendAsyncHtmlMail(email, "[小妖]重置密码（请不要回复此邮件）！", str);

                result.setStatus(true);
                result.setStatusCode(SyspromptStatus.EMAIL_SEND_SUCCESS.value());
            }
            catch (Exception e) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                result.setStatus(false);
                result.setStatusCode(SyspromptStatus.EMAIL_SEND_FAILURE.value());

                logger.error("发送邮件失败！", e);
            }
        }
        else {
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.NOT_SETUP_EMAIL.value());
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 重置密码。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/password/reset", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> resetPassword(ResetPwdForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        form.setClientIp(WebUtils.getRealIp(request));
        form.setHttpUrl(request.getRequestURI());
        form.setHttpMethod(request.getMethod());

        OperationResult result = new OperationResult();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        // 将用户名转换为小写
        form.setUsername(StringUtils.lowerCase(form.getUsername()));

        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute(form.getUsername()); // 根据username获取对应的校验码

        // 判断用户输入的校验码
        if (StringUtils.equals(code, form.getCheckCode())) {

            boolean successed = this.userService.resetPassword(form); // 重置密码

            httpStatus = successed ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
            result.setStatus(successed);
            result.setStatusCode(successed ? SyspromptStatus.RESET_PASSWORD_SUCCESS.value() : SyspromptStatus.RESET_PASSWORD_FAILURE.value());
            // result.setMessage(successed ? "重置密码成功" : "重置密码失败");
        }
        else {
            httpStatus = HttpStatus.BAD_REQUEST;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.CHECKCODE_ERROR.value()); // 校验码错误
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 用户上传头像。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/icon/upload", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> uploadIcon(UploadUserIconForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        form.setClientIp(WebUtils.getRealIp(request));
        form.setHttpUrl(request.getRequestURI());
        form.setHttpMethod(request.getMethod());

        ShiroUser shiroUser = UserUtils.getCurrentUser();
        form.setUserId(shiroUser.getId());
        form.setUsername(shiroUser.getUsername());
        // form.setIconPath(shiroUser.getIconPath());

        boolean successed = this.userService.uploadUserIcon(form);

        ShiroUser returnShiroUser = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        SyspromptStatus syspromptStatus = SyspromptStatus.UPLOAD_FAILURE;

        if (successed) {
            // 头像上传成功后，刷新用户的信息
            returnShiroUser = this.userService.refreshUser(shiroUser.getId());

            httpStatus = HttpStatus.OK;
            syspromptStatus = SyspromptStatus.UPLOAD_SUCCESS;
        }

        OperationResult result = new OperationResult();
        result.setStatus(successed);
        result.setStatusCode(syspromptStatus.value());
        // result.setMessage(successed ? "上传成功" : "上传失败");
        result.setData(returnShiroUser);

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 用户修改个人资料。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> update(UpdateUserInfoForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理。
        BeanValidators.validateWithException(this.validator, form);

        form.setClientIp(WebUtils.getRealIp(request));
        form.setHttpUrl(request.getRequestURI());
        form.setHttpMethod(request.getMethod());

        ShiroUser shiroUser = UserUtils.getCurrentUser();
        form.setUserId(shiroUser.getId());
        form.setUsername(shiroUser.getUsername());

        boolean successed = this.userService.updateUserInfo(form);

        ShiroUser returnShiroUser = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        SyspromptStatus syspromptStatus = SyspromptStatus.MODIFY_FAILURE;

        if (successed) {
            // 修改成功后，刷新用户的信息
            returnShiroUser = this.userService.refreshUser(shiroUser.getId());

            httpStatus = HttpStatus.OK;
            syspromptStatus = SyspromptStatus.MODIFY_SUCCESS;
        }

        OperationResult result = new OperationResult();
        result.setStatus(successed);
        result.setStatusCode(syspromptStatus.value());
        // result.setMessage(successed ? "修改成功" : "修改失败");
        result.setData(returnShiroUser);

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

}
