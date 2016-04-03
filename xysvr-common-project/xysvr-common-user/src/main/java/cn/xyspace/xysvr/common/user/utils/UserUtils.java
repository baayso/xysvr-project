/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import cn.xyspace.xysvr.common.core.utils.MoneyFormatUtils;
import cn.xyspace.xysvr.common.user.entity.User;
import cn.xyspace.xysvr.common.user.entity.UserAsset;
import cn.xyspace.xysvr.common.user.shrio.ShiroUser;

/**
 * 用户相关工具类。
 *
 * @author ChenFangjie(2015年1月15日 下午1:25:12)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public final class UserUtils {

    // 让工具类彻底不可以实例化
    private UserUtils() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 当前登录用户
     */
    public static final String CURRENT_USER = "currentUser";

    /**
     * 当前生成的验证码
     */
    public static final String CAPTCHA_TOKEN = "captchaToken";

    /**
     * HTTP Session中的用户名
     */
    public static final String SESSION_USERNAME = "username";

    /**
     * WebSocket Session中的用户名ID
     */
    public static final String WEBSOCKET_USERID = "ws_userid";

    /**
     * WebSocket Session中的用户名
     */
    public static final String WEBSOCKET_USERNAME = "ws_username";

    /**
     * 获取当前登录用户。
     * 
     * @return 当前登录用户
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static ShiroUser getCurrentUser() {
        // ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

        Session session = SecurityUtils.getSubject().getSession(false);
        SimplePrincipalCollection principals = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        return shiroUser;
    }

    /**
     * 刷新用户。
     * 
     * @param user
     *            用户实体类对象
     * @return {@linkplain cn.xyspace.xysvr.common.user.shrio.ShiroUser}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static ShiroUser refreshUser(User user) {
        Session session = SecurityUtils.getSubject().getSession(false);
        SimplePrincipalCollection principals = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        // 更新最新用户数据
        shiroUser.setGender(user.getGender());
        shiroUser.setBirthday(user.getBirthday());
        shiroUser.setTelephone(user.getTelephone());
        shiroUser.setEmail(user.getEmail());
        shiroUser.setAddress(user.getAddress());
        shiroUser.setIntro(user.getIntro());
        shiroUser.setIconPath(user.getIconPath());
        if (user.getAsset() != null) {
            shiroUser.setBonusPoint(user.getAsset().getBonusPoint());
            shiroUser.setMoney(user.getAsset().getMoney());
            shiroUser.setLockMoney(user.getAsset().getLockMoney());
            shiroUser.setEarnMoney(user.getAsset().getEarnMoney());
            shiroUser.setRmoney(MoneyFormatUtils.rounding(user.getAsset().getRmoney()));
            shiroUser.setLockRmoney(MoneyFormatUtils.rounding(user.getAsset().getLockRmoney()));
            shiroUser.setLucky(user.getAsset().getLucky());
            shiroUser.setHitface(user.getAsset().getHitface());
            shiroUser.setHittedface(user.getAsset().getHittedface());
            shiroUser.setMerit(user.getAsset().getMerit());
            shiroUser.setLevel(user.getAsset().getLevel());
        }

        session.setAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY, principals);

        return shiroUser;
    }

    /**
     * 刷新用户财富。
     * 
     * @param userAsset
     *            用户财富实体类对象
     * @return {@linkplain cn.xyspace.xysvr.common.user.shrio.ShiroUser}
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static ShiroUser refreshUserAsset(UserAsset userAsset) {
        Session session = SecurityUtils.getSubject().getSession(false);
        SimplePrincipalCollection principals = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        // 更新最新用户财富数据
        shiroUser.setBonusPoint(userAsset.getBonusPoint());
        shiroUser.setMoney(userAsset.getMoney());
        shiroUser.setLockMoney(userAsset.getLockMoney());
        shiroUser.setEarnMoney(userAsset.getEarnMoney());
        shiroUser.setRmoney(MoneyFormatUtils.rounding(userAsset.getRmoney()));
        shiroUser.setLockRmoney(MoneyFormatUtils.rounding(userAsset.getLockRmoney()));
        shiroUser.setLucky(userAsset.getLucky());
        shiroUser.setHitface(userAsset.getHitface());
        shiroUser.setHittedface(userAsset.getHittedface());
        shiroUser.setMerit(userAsset.getMerit());
        shiroUser.setLevel(userAsset.getLevel());

        session.setAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY, principals);

        return shiroUser;
    }

}
