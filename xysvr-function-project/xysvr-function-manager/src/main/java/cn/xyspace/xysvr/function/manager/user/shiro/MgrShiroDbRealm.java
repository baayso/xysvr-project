/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.shiro;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.Encodes;

import cn.xyspace.xysvr.function.manager.user.entity.MgrUser;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserService;

/**
 * 后台管理自定义Realm。
 * 
 * @author ChenFangjie(2015年2月5日 下午3:13:01)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
@Service("mgrShiroDbRealm")
public class MgrShiroDbRealm extends AuthorizingRealm {

    // private static final Logger logger = LoggerFactory.getLogger(MgrShiroDbRealm.class);

    @Inject
    protected IMgrUserService mgrUserService;

    // @Inject
    // private CacheManager shiroCacheManager;

    /**
     * 认证回调函数，登录时调用。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        String username = token.getUsername();

        MgrUser user = this.mgrUserService.findByUsername(username);

        if (null == user) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 用户是否锁定
        if (Boolean.TRUE.equals(user.getIsLocked())) {
            throw new LockedAccountException("帐号已被锁定");
        }

        // 用户是否禁用
        if (Boolean.TRUE.equals(user.getIsDisabled())) {
            throw new DisabledAccountException("帐号已被禁用");
        }

        MgrShiroUser shiroUser = new MgrShiroUser(user.getId(), user.getUsername());
        shiroUser.setGender(user.getGender());
        shiroUser.setBirthday(user.getBirthday());
        shiroUser.setTelephone(user.getTelephone());
        shiroUser.setEmail(user.getEmail());
        shiroUser.setAddress(user.getAddress());
        shiroUser.setIntro(user.getIntro());
        shiroUser.setIconPath(user.getIconPath());

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码验证
        byte[] salt = Encodes.decodeHex(user.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(salt), super.getName());

        return authenticationInfo;
    }

    /**
     * 授权查询回调函数，进行鉴权但缓存中无用户的授权信息时调用。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }

        MgrShiroUser shiroUser = (MgrShiroUser) principals.getPrimaryPrincipal();
        // // String userId = (String) principalCollection.fromRealm(getName()).iterator().next();
        // String userId = shiroUser.id;
        // if (StringUtils.isBlank(userId)) {
        // return null;
        // }
        // // 添加角色及权限信息
        // SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        // try {
        // sazi.addRoles(userRoleMenuService.getRolesAsString(userId));
        // sazi.addStringPermissions(userRoleMenuService.getPermissionsAsString(userId));
        // }
        // catch (Exception e) {
        // logger.error(e.getMessage(), e);
        // }

        // MgrUser user = this.mgrUserService.findByUsername(shiroUser.getUsername());
        SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        // sazi.addRoles(user.getRoleList());
        if ("super".equals(shiroUser.getUsername())) {
            sazi.addRole("*");
            sazi.addStringPermission("*");
            return sazi;
        }
        sazi.setRoles(mgrUserService.findRoles(shiroUser.getUsername()));
        sazi.setStringPermissions(mgrUserService.findPermissions(shiroUser.getUsername()));
        return sazi;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
