/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.shrio;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springside.modules.utils.Encodes;

import cn.xyspace.xysvr.common.core.utils.MoneyFormatUtils;
import cn.xyspace.xysvr.common.user.entity.User;
import cn.xyspace.xysvr.common.user.service.IUserService;

/**
 * 自定义Realm。
 * 
 * @author ChenFangjie(2014/12/12 14:08:41)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public class ShiroDbRealm extends AuthorizingRealm {

    // private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Inject
    protected IUserService userService;

    // @Inject
    // private CacheManager shiroCacheManager;

    /**
     * 认证回调函数，登录时调用。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UsernamePasswordPositionToken token = (UsernamePasswordPositionToken) authcToken;

        String username = token.getUsername();
        // String password = "";
        // String host = token.getHost();
        Double longitude = token.getLongitude();
        Double latitude = token.getLatitude();
        String city = token.getCity();
        String position = token.getPosition();

        // if (null != token.getPassword()) {
        // password = new String(token.getPassword());
        // }

        User user = this.userService.findWithAssetByUsername(username);

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

        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUsername());
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
        shiroUser.setLongitude(longitude);
        shiroUser.setLatitude(latitude);
        shiroUser.setCity(city);
        shiroUser.setPosition(position);

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码验证
        byte[] salt = Encodes.decodeHex(user.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(salt), super.getName());

        return authenticationInfo;
    }

    /**
     * 授权查询回调函数，进行鉴权但缓存中无用户的授权信息时调用。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }

        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
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

        User user = userService.findByUsername(shiroUser.getUsername());
        SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        sazi.addRoles(user.getRoleList());

        return sazi;
    }

    // /**
    // * 设定Password校验的Hash算法与迭代次数。
    // */
    // @PostConstruct
    // public void initCredentialsMatcher() {
    // HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(PasswordUtils.HASH_ALGORITHM);
    // matcher.setHashIterations(PasswordUtils.HASH_INTERATIONS);
    //
    // super.setCredentialsMatcher(matcher);
    // }

}
