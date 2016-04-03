/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.shrio.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.joda.time.DateTime;

import cn.xyspace.xysvr.common.core.utils.IdUtils;
import cn.xyspace.xysvr.common.user.entity.UserLoginLog;
import cn.xyspace.xysvr.common.user.service.IUserLoginLogService;
import cn.xyspace.xysvr.common.user.shrio.UsernamePasswordPositionToken;

/**
 * 限制密码重试次数凭证管理器。
 * 
 * @author ChenFangjie(2014/12/30 9:28:22)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    // private static final Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    @Inject
    private IUserLoginLogService userLoginLogService;

    private Cache<String, AtomicInteger> cache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-password-retry");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordPositionToken token = (UsernamePasswordPositionToken) authcToken;

        String username = token.getUsername();
        String host = token.getHost();
        Double longitude = token.getLongitude();
        Double latitude = token.getLatitude();
        String city = token.getCity();
        String position = token.getPosition();

        String cacheName = "password-retry-" + username;

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
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setId(IdUtils.getId());
        userLoginLog.setUsername(username);
        userLoginLog.setLongitude(longitude);
        userLoginLog.setLatitude(latitude);
        userLoginLog.setCity(city);
        userLoginLog.setPosition(position);
        userLoginLog.setLoginIp(host);
        userLoginLog.setIsSuccess(matches);
        userLoginLog.setLoginTime(DateTime.now().getMillis());

        // 记录登录日志
        this.userLoginLogService.add(userLoginLog);

        return matches;
    }

}
