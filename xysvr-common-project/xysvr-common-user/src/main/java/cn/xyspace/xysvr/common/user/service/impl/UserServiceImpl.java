/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.core.entity.Attachment;
import cn.xyspace.xysvr.common.core.exception.ApiServiceException;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.BadWordFilterUtils;
import cn.xyspace.xysvr.common.core.utils.BadWordFilterUtils.MatchType;
import cn.xyspace.xysvr.common.core.utils.FileUtils;
import cn.xyspace.xysvr.common.core.utils.IdUtils;
import cn.xyspace.xysvr.common.core.utils.PasswordUtils;
import cn.xyspace.xysvr.common.core.utils.SyspromptStatus;
import cn.xyspace.xysvr.common.user.dao.IUserAssetMybatisDao;
import cn.xyspace.xysvr.common.user.dao.IUserMybatisDao;
import cn.xyspace.xysvr.common.user.entity.User;
import cn.xyspace.xysvr.common.user.entity.UserAsset;
import cn.xyspace.xysvr.common.user.entity.UserGender;
import cn.xyspace.xysvr.common.user.entity.UserUseLog;
import cn.xyspace.xysvr.common.user.form.ModifyPwdForm;
import cn.xyspace.xysvr.common.user.form.RegisterForm;
import cn.xyspace.xysvr.common.user.form.ResetPwdForm;
import cn.xyspace.xysvr.common.user.form.UpdateUserInfoForm;
import cn.xyspace.xysvr.common.user.form.UploadUserIconForm;
import cn.xyspace.xysvr.common.user.service.IUserService;
import cn.xyspace.xysvr.common.user.service.IUserUseLogService;
import cn.xyspace.xysvr.common.user.shrio.ShiroUser;
import cn.xyspace.xysvr.common.user.utils.UserUtils;

/**
 * 用户业务逻辑实现。
 * 
 * @author ChenFangjie(2014/11/29 21:06:54)
 * 
 * @since 1.0.0
 * 
 * @version 1.1.0
 *
 */
@Service
public class UserServiceImpl implements IUserService {

    // private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int SALT_SIZE = 8;

    private IUserMybatisDao userDao = MybatisDaoProxyFactory.createProxy(IUserMybatisDao.class);

    private IUserAssetMybatisDao userAssetDao = MybatisDaoProxyFactory.createProxy(IUserAssetMybatisDao.class);

    @Inject
    private IUserUseLogService userUseLogService;

    private JsonMapper jsonMapper = new JsonMapper();

    private Clock clock = Clock.DEFAULT;

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean hasUsername(String username) {
        boolean has = false;

        has = BadWordFilterUtils.contains(username, MatchType.MIN);

        if (!has) {
            has = this.userDao.selectCountUsername(username) > 0;
        }

        return has;
    }

    @Override
    public boolean hasEmail(String email) {
        return this.userDao.selectCountEmail(email) > 0;
    }

    @Override
    public boolean hasIdentifier(String identifier) {
        return this.userDao.selectCountIdentifier(identifier) > 0;
    }

    @Override
    @Transactional
    public boolean register(RegisterForm form) {
        String username = StringUtils.lowerCase(form.getUsername());
        String email = StringUtils.isBlank(form.getEmail()) ? null : form.getEmail();
        email = StringUtils.lowerCase(email);

        UserGender gender = UserGender.SECRET;
        if (UserGender.MALE.getDesc().equals(form.getGender())) {
            gender = UserGender.MALE;
        }
        else if (UserGender.FEMALE.getDesc().equals(form.getGender())) {
            gender = UserGender.FEMALE;
        }

        String inviter = StringUtils.isBlank(form.getInviter()) ? null : form.getInviter(); // 邀请人
        inviter = StringUtils.lowerCase(inviter);

        String identifier = form.getIdentifier();
        String password = form.getPassword();
        String repassword = form.getRepassword();

        String clientIp = form.getClientIp();
        Double longitude = Double.parseDouble(form.getLongitude());
        Double latitude = Double.parseDouble(form.getLatitude());
        String city = form.getCity();
        String postion = form.getPosition();

        if (!StringUtils.equals(password, repassword)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.TWICE_PASSWORD_NOT_CONSISTENT);
        }

        if (this.hasIdentifier(identifier)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.IDENTIFIER_ALREADY_USED);
        }

        if (this.hasUsername(username)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.USERNAME_ALREADY_USED);
        }

        if (this.hasEmail(email)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.EMAIL_ALREADY_USED);
        }

        int userInitialBonusPoint = Config.USER_INITIAL_BONUS_POINT;
        int userInitialMoney = Config.USER_INITIAL_MONEY;

        User inviterUser = null;
        boolean updateInviterUserAsset = true;

        if (StringUtils.isNotBlank(inviter)) {
            inviterUser = this.findByUsername(inviter);

            if (inviterUser == null) {
                throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.INVITER_NOT_EXIT);
            }
            else {
                // 查询财富规则基数
                //

                //
                //
                //
            }
        }

        // 用户数据
        User user = new User();
        user.setId(IdUtils.getId());
        user.setUsername(username);
        user.setNickname(username);

        byte[] salt = Digests.generateSalt(SALT_SIZE);
        String hashPassword = PasswordUtils.encryptPassword(password, salt);
        user.setSalt(Encodes.encodeHex(salt));
        user.setPassword(hashPassword);
        user.setEmail(email);
        user.setInviter(inviter);
        user.setIdentifier(identifier);
        user.setGender(gender);
        user.setRegIp(clientIp);
        user.setRegLongitude(longitude);
        user.setRegLatitude(latitude);
        user.setRegCity(city);
        user.setRegPosition(postion);

        user.setIsAudited(Boolean.TRUE); // 用户是否已通过审核（默认FALSE）
        user.setIsActivated(Boolean.TRUE); // 用户是否已激活（默认FALSE）

        // user.setRoles("user");
        user.setCreateTime(this.clock.getCurrentTimeInMillis());

        // 用户财富数据
        UserAsset userAsset = new UserAsset();
        userAsset.setId(user.getId());
        userAsset.setBonusPoint(userInitialBonusPoint);
        userAsset.setMoney(userInitialMoney);
        userAsset.setLevel(Config.USER_INITIAL_LEVEL);

        boolean insertUser = this.userDao.insert(user) > 0; // 新增用户
        boolean insertUserAsset = this.userAssetDao.insert(userAsset) > 0;

        boolean success = insertUser && insertUserAsset && updateInviterUserAsset;

        if (!success) {
            throw new ApiServiceException(SyspromptStatus.REGISTER_FAILURE);
        }

        return success;
    }

    @Override
    public User findById(String id) {
        return this.userDao.selectById(id);
    }

    @Override
    public User findWithAssetById(String id) {
        return this.userDao.selectWithAssetById(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userDao.selectByUsername(username);
    }

    @Override
    public User findWithAssetByUsername(String username) {
        return this.userDao.selectWithAssetByUsername(username);
    }

    @Override
    public boolean modifyPassword(ModifyPwdForm form) {
        String userId = form.getUserId();
        String username = form.getUsername();
        String oldPassword = form.getPassword();
        String newPassword = form.getNewPassword();
        String reNewPassword = form.getReNewPassword();
        Double longitude = Double.parseDouble(form.getLongitude()); // 获取当前用户纬度
        Double latitude = Double.parseDouble(form.getLatitude()); // 获取当前用户经度

        if (!StringUtils.equals(newPassword, reNewPassword)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.TWICE_PASSWORD_NOT_CONSISTENT);
        }

        if (StringUtils.equals(oldPassword, newPassword)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.NEW_OLD_PASSWORD_CONSISTENT);
        }

        User user = this.findByUsername(username);

        if (!PasswordUtils.matches(user.getPassword(), user.getSalt(), oldPassword)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ORIGINAL_PASSWORD_ERROR);
        }

        // 封装用户使用日志
        UserUseLog useLog = new UserUseLog();
        useLog.setId(IdUtils.getId());
        useLog.setUserId(userId);
        useLog.setUsername(username);
        useLog.setHttpUrl(form.getHttpUrl());
        useLog.setHttpMethod(form.getHttpMethod());
        useLog.setOperation("修改用户密码");
        useLog.setLongitude(longitude);
        useLog.setLatitude(latitude);
        useLog.setCity(form.getCity());
        useLog.setPosition(form.getPosition());
        useLog.setClientIp(form.getClientIp());
        useLog.setClientInfo(form.getClientInfo());
        useLog.setCreateTime(this.clock.getCurrentTimeInMillis());
        // 记录用户使用日志
        this.userUseLogService.add(useLog);

        byte[] salt = Digests.generateSalt(SALT_SIZE); // 生成新的密码盐

        return this.userDao.updatePassword(userId, PasswordUtils.encryptPassword(newPassword, salt), Encodes.encodeHex(salt)) > 0;
    }

    @Override
    public boolean resetPassword(ResetPwdForm form) {
        String username = form.getUsername();
        String newPassword = form.getNewPassword();
        String reNewPassword = form.getReNewPassword();

        if (!StringUtils.equals(newPassword, reNewPassword)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.TWICE_PASSWORD_NOT_CONSISTENT);
        }

        User user = this.findByUsername(username);

        if (user == null) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.USER_NOT_EXIST);
        }

        String userId = user.getId();

        Double longitude = Double.parseDouble(form.getLongitude()); // 获取当前用户纬度
        Double latitude = Double.parseDouble(form.getLatitude()); // 获取当前用户经度

        // 封装用户使用日志
        UserUseLog useLog = new UserUseLog();
        useLog.setId(IdUtils.getId());
        useLog.setUserId(userId);
        useLog.setUsername(username);
        useLog.setHttpUrl(form.getHttpUrl());
        useLog.setHttpMethod(form.getHttpMethod());
        useLog.setOperation("重置用户密码");
        useLog.setLongitude(longitude);
        useLog.setLatitude(latitude);
        useLog.setCity(form.getCity());
        useLog.setPosition(form.getPosition());
        useLog.setClientIp(form.getClientIp());
        useLog.setClientInfo(form.getClientInfo());
        useLog.setCreateTime(this.clock.getCurrentTimeInMillis());
        // 记录用户使用日志
        this.userUseLogService.add(useLog);

        byte[] salt = Digests.generateSalt(SALT_SIZE); // 生成新的密码盐

        return this.userDao.updatePassword(userId, PasswordUtils.encryptPassword(newPassword, salt), Encodes.encodeHex(salt)) > 0;
    }

    @Override
    public boolean uploadUserIcon(UploadUserIconForm form) {
        String userId = form.getUserId();
        String username = form.getUsername();
        Double longitude = Double.parseDouble(form.getLongitude()); // 获取当前用户纬度
        Double latitude = Double.parseDouble(form.getLatitude()); // 获取当前用户经度
        String userIcon = form.getUserIcon();

        Long nowTimeMillis = this.clock.getCurrentTimeInMillis();

        Attachment attachment = null;
        try {
            attachment = this.jsonMapper.fromJson(userIcon, Attachment.class);
        }
        catch (Exception e) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ATTACHMENT_DATA_FORMAT_ERROR);
        }

        if (attachment == null || StringUtils.isEmpty(StringUtils.trim(attachment.getPath()))) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ATTACHMENT_DATA_FORMAT_ERROR);
        }

        User user = new User();
        user.setId(userId); // 需要根据用户Id来更新
        user.setIconPath(attachment.getPath());
        user.setMime(attachment.getMime());
        user.setSize(attachment.getSize());
        user.setHash(attachment.getHash());
        user.setWidth(attachment.getWidth());
        user.setHeight(attachment.getHeight());
        user.setExtname(FileUtils.getExtensionName(attachment.getPath())); // 设置附件的扩展名
        user.setUptime(nowTimeMillis);

        boolean updated = this.userDao.updateUserIcon(user) > 0;

        // 封装用户使用日志
        UserUseLog useLog = new UserUseLog();
        useLog.setId(IdUtils.getId());
        useLog.setUserId(userId);
        useLog.setUsername(username);
        useLog.setHttpUrl(form.getHttpUrl());
        useLog.setHttpMethod(form.getHttpMethod());
        useLog.setOperation("用户上传头像");
        useLog.setLongitude(longitude);
        useLog.setLatitude(latitude);
        useLog.setCity(form.getCity());
        useLog.setPosition(form.getPosition());
        useLog.setClientIp(form.getClientIp());
        useLog.setClientInfo(form.getClientInfo());
        useLog.setCreateTime(nowTimeMillis);
        // 记录用户使用日志
        this.userUseLogService.add(useLog);

        return updated;
    }

    @Override
    public boolean updateUserInfo(UpdateUserInfoForm form) {
        String username = form.getUsername();

        String newEmail = StringUtils.isBlank(form.getEmail()) ? null : form.getEmail();
        String newTelephone = StringUtils.isBlank(form.getTelephone()) ? null : form.getTelephone();
        String newIntro = StringUtils.isBlank(form.getIntro()) ? null : form.getIntro();

        newEmail = StringUtils.lowerCase(StringUtils.trim(newEmail));
        newTelephone = StringUtils.trim(newTelephone);
        newIntro = StringUtils.trim(newIntro);

        Double longitude = Double.parseDouble(form.getLongitude()); // 获取当前用户纬度
        Double latitude = Double.parseDouble(form.getLatitude()); // 获取当前用户经度
        String city = form.getCity();
        String position = form.getPosition();

        User user = this.findByUsername(username);
        String userId = user.getId();
        String email = user.getEmail();
        String telephone = user.getTelephone();
        String intro = user.getIntro();

        if (StringUtils.equals(newEmail, email) && StringUtils.equals(newTelephone, telephone) && StringUtils.equals(newIntro, intro)) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.NOT_CHANGE);
        }

        boolean existEmail = this.userDao.selectSumEmail(userId, newEmail) > 0;

        if (existEmail) {
            throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.EMAIL_ALREADY_USED);
        }

        // 封装用户使用日志
        UserUseLog useLog = new UserUseLog();
        useLog.setId(IdUtils.getId());
        useLog.setUserId(userId);
        useLog.setUsername(username);
        useLog.setHttpUrl(form.getHttpUrl());
        useLog.setHttpMethod(form.getHttpMethod());
        useLog.setOperation("用户修改个人信息");
        useLog.setLongitude(longitude);
        useLog.setLatitude(latitude);
        useLog.setCity(city);
        useLog.setPosition(position);
        useLog.setClientIp(form.getClientIp());
        useLog.setClientInfo(form.getClientInfo());
        useLog.setCreateTime(this.clock.getCurrentTimeInMillis());
        // 记录用户使用日志
        this.userUseLogService.add(useLog);

        return this.userDao.updateUserInfo(userId, newIntro, newEmail, newTelephone) > 0;
    }

    @Override
    public ShiroUser refreshUser(String id) {
        if (Config.SLAVE_DATABASE_SELECT_SLEEP_SWITCH) {
            try {
                Thread.sleep(Config.SLAVE_DATABASE_SELECT_SLEEP_MILLIS);
            }
            catch (InterruptedException e) {
                // ignore
            }
        }

        User user = this.findById(id);

        return UserUtils.refreshUser(user);
    }

    // /**
    // * 验证用户输入的密码是否正确。
    // *
    // * @param user
    // * 用户
    // * @param enterPassword
    // * 用户输入的密码
    // * @return 如果用户输入的密码正确则返回true，否则返回false
    // *
    // * @since 1.0.0
    // * @version 1.0.0
    // */
    // public static boolean matches(User user, String enterPassword) {
    //
    // if (null == user) {
    // return false;
    // }
    //
    // // return user.getPassword().equals(Md5Utils.encryptPassword(user.getUsername(), enterPassword, user.getSalt()));
    // return user.getPassword().equals(encryptPassword(enterPassword, Encodes.decodeHex(user.getSalt())));
    // }

    // /*
    // * 判断是否超级管理员。
    // */
    // private boolean isSupervisor(String id) {
    // return "6cba07e9-7d11-11e4-9cd1-00ffc365a8bf".equals(id);
    // }
    //
    // /*
    // * 取出Shiro中的当前用户的username。
    // */
    // private String getCurrentUsername() {
    // ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    // return user.username;
    // }

    // /*
    // * 生成安全密码，经过1024次 sha-1 hash。
    // */
    // private static String encryptPassword(String plainPassword, byte[] salt) {
    // byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
    // return Encodes.encodeHex(hashPassword);
    // }

}
