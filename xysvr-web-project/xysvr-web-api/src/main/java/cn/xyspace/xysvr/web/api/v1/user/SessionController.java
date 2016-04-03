/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.api.v1.user;

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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
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
import cn.xyspace.xysvr.common.core.utils.WebUtils;
import cn.xyspace.xysvr.common.user.form.LoginForm;
import cn.xyspace.xysvr.common.user.form.SessionUpdateForm;
import cn.xyspace.xysvr.common.user.shrio.ShiroUser;
import cn.xyspace.xysvr.common.user.shrio.UsernamePasswordPositionToken;
import cn.xyspace.xysvr.common.user.utils.UserUtils;

/**
 * 会话 RESTful API。
 * 
 * @author ChenFangjie(2014/12/12 13:21:41)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "/api/v1/session")
public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    // @Inject
    // private IUserService userService;

    // @Inject
    // private IUserAssetService userAssetService;

    @Inject
    private Validator validator;

    /**
     * 创建新的会话（登录）。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    // @RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> create(LoginForm loginForm, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, loginForm);

        OperationResult result = new OperationResult();
        HttpStatus httpStatus = HttpStatus.CREATED;

        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (obj != null) {
            result.setStatus(true);
            result.setStatusCode(SyspromptStatus.ALREADY_LOGGED_ON.value());
            // result.setMessage("您已经登录");
            result.setData(UserUtils.getCurrentUser()); // 登录成功后，返回用户信息

            return new ResponseEntity<OperationResult>(result, httpStatus);
        }

        String username = StringUtils.lowerCase(loginForm.getUsername());
        String password = loginForm.getPassword();
        String rememberMe = loginForm.getRememberMe();
        Double longitude = Double.parseDouble(loginForm.getLongitude());
        Double latitude = Double.parseDouble(loginForm.getLatitude());
        String city = loginForm.getCity();
        String position = loginForm.getPosition();

        String loginIp = WebUtils.getRealIp(request);

        // UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        UsernamePasswordPositionToken token = new UsernamePasswordPositionToken(username, password, loginIp, longitude, latitude, city, position);

        if (StringUtils.isNotBlank(rememberMe)) {
            token.setRememberMe(true);
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            // 会调用ShiroDbRealm的认证方法
            subject.login(token);

            result.setStatus(true);
            result.setStatusCode(SyspromptStatus.LOGIN_SUCCESS.value());
            // result.setMessage("登录成功");
            result.setData(UserUtils.getCurrentUser()); // 登录成功后，返回用户信息
        }
        catch (ExcessiveAttemptsException eae) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.WAIT_A_WHILE_TO_TRY.value());
            // result.setMessage("登录失败次数过多，请稍候在试");
        }
        catch (UnknownAccountException uae) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.USERNAME_OR_PASSWORD_ERROR.value());
            // result.setMessage("用户名或密码错误");
        }
        catch (IncorrectCredentialsException ice) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.USERNAME_OR_PASSWORD_ERROR.value());
            // result.setMessage("用户名或密码错误");
        }
        catch (LockedAccountException lae) {
            httpStatus = HttpStatus.LOCKED;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ACCOUNT_LOCKED.value());
            // result.setMessage("帐号已被锁定");
        }
        catch (DisabledAccountException dae) {
            httpStatus = HttpStatus.LOCKED;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.ACCOUNT_DISABLED.value());
            // result.setMessage("帐号已被禁用");
        }
        catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.LOGIN_ERROR.value());
            // result.setMessage("登录出错");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 销毁当前会话（登出）。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<OperationResult> delete() {

        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try {
                subject.logout();
            }
            catch (Exception e) { // ignore
                logger.warn(e.getMessage());
            }
        }

        OperationResult result = new OperationResult();
        result.setStatus(true);
        result.setStatusCode(SyspromptStatus.LOGOUT_SUCCESS.value());
        // result.setMessage("您已成功注销登录");

        return new ResponseEntity<OperationResult>(result, HttpStatus.OK);
    }

    /**
     * 获取当前会话信息。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> read() {
        OperationResult result = new OperationResult();
        HttpStatus httpStatus = HttpStatus.OK;

        ShiroUser shiroUser = UserUtils.getCurrentUser();

        if (shiroUser != null) {
            // 刷新用户财富数据
            // shiroUser = this.userAssetService.refreshUserAsset(shiroUser.getId());

            result.setStatus(true);
            result.setStatusCode(SyspromptStatus.GET_USER_INFORMATION_SUCCESS.value());
            // result.setMessage("获取用户信息成功");
            result.setData(shiroUser);
        }
        else {
            httpStatus = HttpStatus.UNAUTHORIZED;
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.NOT_LOGGED_ON.value());
            // result.setMessage("您尚未登录");
        }

        return new ResponseEntity<OperationResult>(result, httpStatus);
    }

    /**
     * 判断是否登录。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<OperationResult> isLogin() {
        OperationResult result = new OperationResult();

        Object shiroUser = SecurityUtils.getSubject().getPrincipal();

        if (null == shiroUser) {
            result.setStatus(false);
            result.setStatusCode(SyspromptStatus.NOT_LOGGED_ON.value());
            // result.setMessage("未登录");
        }
        else {
            result.setStatus(true);
            result.setStatusCode(SyspromptStatus.ALREADY_LOGGED_ON.value());
            // result.setMessage("已登录");
        }

        return new ResponseEntity<OperationResult>(result, HttpStatus.OK);
    }

    /**
     * 更新session中当前用户的经纬度信息。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public void update(SessionUpdateForm form, HttpServletRequest request) {
        // 调用JSR303 Bean Validator进行校验，异常将由RestExceptionHandler统一处理
        BeanValidators.validateWithException(this.validator, form);

        // 获取当前用户
        Session session = SecurityUtils.getSubject().getSession(false);
        SimplePrincipalCollection principals = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        // 更新数据
        shiroUser.setLatitude(Double.valueOf(form.getLatitude()));
        shiroUser.setLongitude(Double.valueOf(form.getLongitude()));
        shiroUser.setCity(form.getCity());
        shiroUser.setPosition(form.getPosition());

        session.setAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY, principals);
    }

}
