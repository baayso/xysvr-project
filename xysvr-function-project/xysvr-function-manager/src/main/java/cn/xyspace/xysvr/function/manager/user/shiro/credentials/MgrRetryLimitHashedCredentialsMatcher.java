/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.joda.time.DateTime;

import cn.xyspace.xysvr.common.core.utils.IdUtils;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUserLoginLog;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserLoginLogService;

/**
 * 后台管理限制密码重试次数凭证管理器。
 * 
 * @author ChenFangjie(2015年2月5日 下午3:29:28)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class MgrRetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Inject
    private IMgrUserLoginLogService mgrUserLoginLogService;

    private Cache<String, AtomicInteger> cache;

    public MgrRetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-mgr-password-retry");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        String username = token.getUsername();
        String host = token.getHost();

        String cacheName = "mgr-password-retry-" + username;

        AtomicInteger retryCount = this.cache.get(cacheName);

        synchronized (cacheName) {
            if (retryCount == null) {
                retryCount = new AtomicInteger(0);
            }
        }

        // retryCount++
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException("登录失败次数过多，请稍候在试");
        }

        boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            // clear retry count
            this.cache.remove(cacheName);
        }
        else {
            this.cache.put(cacheName, retryCount); // set cache
        }

        // 封用户录登录日志
        MgrUserLoginLog userLoginLog = new MgrUserLoginLog();
        userLoginLog.setId(IdUtils.getId());
        // userLoginLog.setUserId(shiroUser.getId());
        userLoginLog.setUsername(username);
        userLoginLog.setLoginIp(host);
        userLoginLog.setIsSuccess(matches);
        userLoginLog.setLoginTime(DateTime.now().getMillis());

        // 记录登录日志
        this.mgrUserLoginLogService.add(userLoginLog);

        return matches;
    }

}
