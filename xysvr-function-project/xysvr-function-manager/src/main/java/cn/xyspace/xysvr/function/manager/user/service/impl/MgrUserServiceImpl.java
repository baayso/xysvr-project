/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.core.entity.Attachment;
import cn.xyspace.xysvr.common.core.exception.ManagerServiceException;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.core.utils.FileUtils;
import cn.xyspace.xysvr.common.core.utils.IdUtils;
import cn.xyspace.xysvr.common.core.utils.PasswordUtils;
import cn.xyspace.xysvr.common.user.entity.UserGender;
import cn.xyspace.xysvr.common.user.form.UploadUserIconForm;
import cn.xyspace.xysvr.function.manager.user.dao.IMgrUserMybatisDao;
import cn.xyspace.xysvr.function.manager.user.dao.IMgrUserRoleMybatisDao;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUser;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUserRole;
import cn.xyspace.xysvr.function.manager.user.form.FindUserForm;
import cn.xyspace.xysvr.function.manager.user.form.ModifyPwdForm;
import cn.xyspace.xysvr.function.manager.user.form.RegisterForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdateUserInfoForm;
import cn.xyspace.xysvr.function.manager.user.service.IMgrRoleService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserService;
import cn.xyspace.xysvr.function.manager.user.shiro.MgrShiroUser;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户业务逻辑实现。
 * 
 * @author ChenFangjie(2015年2月5日 下午2:50:02)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class MgrUserServiceImpl implements IMgrUserService {

    // private static final Logger logger = LoggerFactory.getLogger(MgrUserServiceImpl.class);

    private static final int SALT_SIZE = 8;

    private IMgrUserMybatisDao mgrUserDao = MybatisDaoProxyFactory.createProxy(IMgrUserMybatisDao.class);

    private IMgrUserRoleMybatisDao mgrUserRoleDao = MybatisDaoProxyFactory.createProxy(IMgrUserRoleMybatisDao.class);

    private JsonMapper jsonMapper = new JsonMapper();

    private Clock clock = Clock.DEFAULT;

    @Inject
    private IMgrRoleService mgrRoleService;

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean hasUsername(String username) {
        boolean has = false;

        // has = BadWordUtils.check(username) != null;

        // if (!has) {
        // has = this.mgrUserDao.selectCountUsername(username) > 0;
        // }

        has = this.mgrUserDao.selectCountUsername(username) > 0;

        return has;
    }

    @Override
    public boolean hasEmail(String email) {
        return this.mgrUserDao.selectCountEmail(email) > 0;
    }

    @Override
    public boolean register(RegisterForm form) {

        String username = StringUtils.lowerCase(form.getUsername());
        String email = StringUtils.lowerCase(form.getEmail());
        UserGender gender = UserGender.SECRET;
        if (UserGender.MALE.getDesc().equals(form.getGender())) {
            gender = UserGender.MALE;
        }
        else if (UserGender.FEMALE.getDesc().equals(form.getGender())) {
            gender = UserGender.FEMALE;
        }

        if (!StringUtils.equals(form.getPassword(), form.getRepassword())) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.TWICE_PASSWORD_NOT_CONSISTENT);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "两次输入的新密码不一致");
        }

        if (this.hasUsername(form.getUsername())) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.USERNAME_ALREADY_USED);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "此用户名已被使用");
        }

        if (this.hasEmail(form.getEmail())) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.EMAIL_ALREADY_USED);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "此email已被使用");
        }

        // 用户数据
        MgrUser user = new MgrUser();
        user.setId(IdUtils.getId());
        user.setUsername(username);
        user.setNickname(username);

        byte[] salt = Digests.generateSalt(SALT_SIZE);
        String hashPassword = PasswordUtils.encryptPassword(form.getPassword(), salt);
        user.setSalt(Encodes.encodeHex(salt));
        user.setPassword(hashPassword);

        user.setEmail(email);
        user.setGender(gender);

        user.setIsAudited(Boolean.TRUE); // 用户是否已通过审核（默认FALSE）
        user.setIsActivated(Boolean.TRUE); // 用户是否已激活（默认FALSE）
        user.setIsAdmin(Boolean.FALSE); // 用户是否为管理员（默认FALSE）

        // user.setRoles("user");
        user.setCreateTime(this.clock.getCurrentTimeInMillis());

        boolean insert = this.mgrUserDao.insert(user) > 0;
        List<String> roleIds = form.getRoleIds();
        List<MgrUserRole> list = new LinkedList<MgrUserRole>();

        for (String roleId : roleIds) {
            MgrUserRole userRole = new MgrUserRole();
            userRole.setId(IdUtils.getId());
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());

            list.add(userRole);
        }

        boolean insertMore = this.mgrUserRoleDao.insertMore(list) > 0;

        return insert && insertMore;

    }

    @Override
    public MgrUser findByUsername(String username) {
        return this.mgrUserDao.selectByUsername(username);
    }

    @Override
    public MgrUser findById(FindUserForm form) {

        return this.mgrUserDao.selectById(form.getId());
    }

    @Override
    public boolean modifyPassword(ModifyPwdForm form) {

        MgrShiroUser mgrShiroUser = (MgrShiroUser) SecurityUtils.getSubject().getPrincipal();

        String userId = mgrShiroUser.getId();
        String oldPassword = form.getPassword();
        String newPassword = form.getNewPassword();
        String reNewPassword = form.getReNewPassword();

        if (!StringUtils.equals(newPassword, reNewPassword)) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.TWICE_PASSWORD_NOT_CONSISTENT);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "两次输入的新密码不一致");
        }

        if (StringUtils.equals(oldPassword, newPassword)) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.NEW_OLD_PASSWORD_CONSISTENT);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "新密码和原密码一致");
        }

        MgrUser user = this.mgrUserDao.selectById(userId);

        if (!PasswordUtils.matches(user.getPassword(), user.getSalt(), oldPassword)) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ORIGINAL_PASSWORD_ERROR);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "原密码错误");
        }

        byte[] salt = Digests.generateSalt(SALT_SIZE); // 生成新的密码盐

        return this.mgrUserDao.updatePassword(userId, PasswordUtils.encryptPassword(newPassword, salt), Encodes.encodeHex(salt)) > 0;
    }

    @Override
    public boolean uploadUserIcon(UploadUserIconForm form) {
        String userId = form.getUserId();
        // String username = form.getUsername();
        String userIcon = form.getUserIcon();

        Long nowTimeMillis = this.clock.getCurrentTimeInMillis();

        Attachment attachment = null;
        try {
            attachment = this.jsonMapper.fromJson(userIcon, Attachment.class);
        }
        catch (Exception e) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ATTACHMENT_DATA_FORMAT_ERROR);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "附件数据格式不正确");
        }

        if (attachment == null || StringUtils.isEmpty(StringUtils.trim(attachment.getPath()))) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.ATTACHMENT_DATA_FORMAT_ERROR);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "附件数据格式不正确");
        }

        MgrUser user = new MgrUser();
        user.setId(userId); // 需要根据用户Id来更新
        user.setIconPath(attachment.getPath());
        user.setMime(attachment.getMime());
        user.setSize(attachment.getSize());
        user.setHash(attachment.getHash());
        user.setWidth(attachment.getWidth());
        user.setHeight(attachment.getHeight());
        user.setExtname(FileUtils.getExtensionName(attachment.getPath())); // 设置附件的扩展名
        user.setUptime(nowTimeMillis);

        boolean updated = this.mgrUserDao.updateUserIcon(user) > 0;

        // // 封装用户使用日志
        // UserUseLog useLog = new UserUseLog();
        // useLog.setId(IdUtils.getId());
        // useLog.setUserId(userId);
        // useLog.setUsername(username);
        // useLog.setHttpUrl(form.getHttpUrl());
        // useLog.setHttpMethod(form.getHttpMethod());
        // useLog.setOperation("用户上传头像");
        // useLog.setLongitude(longitude);
        // useLog.setLatitude(latitude);
        // useLog.setCity(form.getCity());
        // useLog.setClientIp(form.getClientIp());
        // useLog.setClientInfo(form.getClientInfo());
        // useLog.setCreateTime(nowTimeMillis);
        // // 记录用户使用日志
        // this.userUseLogDao.insert(useLog);

        return updated;
    }

    public boolean updateUserInfo(UpdateUserInfoForm form) {

        String newUserId = StringUtils.trim(form.getUserId());
        String newNickname = StringUtils.trim(form.getNickname());
        String newName = StringUtils.trim(form.getName());
        String newEmail = StringUtils.trim(form.getEmail());
        String newBirthday = StringUtils.trim(form.getBirthday());
        String newTelephone = StringUtils.trim(form.getTelephone());
        String newAddress = StringUtils.trim(form.getAddress());
        Boolean newIsLocked = Boolean.parseBoolean(StringUtils.trim(form.getIsLocked()));
        Boolean newIsDisabled = Boolean.parseBoolean(StringUtils.trim(form.getIsDisabled()));
        Boolean newIsAudited = Boolean.parseBoolean(StringUtils.trim(form.getIsAudited()));
        Boolean newIsActivated = Boolean.parseBoolean(StringUtils.trim(form.getIsActivated()));
        Boolean newIsAdmin = Boolean.parseBoolean(StringUtils.trim(form.getIsAdmin()));
        String newIntro = StringUtils.trim(form.getIntro());

        Subject currentUser = SecurityUtils.getSubject();
        MgrUser user = this.mgrUserDao.selectById(newUserId);

        Set<String> roles = this.findRoles(user.getUsername());

        if (!currentUser.hasRole("*")) {
            if (roles.contains("admin") && currentUser.hasRole("admin")) {
                return false;
            }
        }

        if ("super".equals(form.getName())) {
            return false;
        }

        UserGender newGender = UserGender.SECRET;
        if (UserGender.MALE.getDesc().equals(form.getGender())) {
            newGender = UserGender.MALE;
        }
        else if (UserGender.FEMALE.getDesc().equals(form.getGender())) {
            newGender = UserGender.FEMALE;
        }

        // if (StringUtils.equals(newEmail, email) && StringUtils.equals(newTelephone, telephone) && StringUtils.equals(newIntro, intro)) {
        // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.NOT_CHANGE);
        // }

        boolean existEmail = this.mgrUserDao.selectSumEmail(newUserId, newEmail) > 0;

        if (existEmail) {
            // throw new ApiServiceException(HttpStatus.BAD_REQUEST, SyspromptStatus.EMAIL_ALREADY_USED);
            throw new ManagerServiceException(HttpStatus.BAD_REQUEST, "此email已被使用");
        }

        MgrUser userInfo = new MgrUser();
        userInfo.setName(newName);
        userInfo.setNickname(newNickname);
        userInfo.setId(newUserId);
        userInfo.setEmail(newEmail);
        userInfo.setBirthday(newBirthday);
        userInfo.setTelephone(newTelephone);
        userInfo.setAddress(newAddress);
        userInfo.setIsLocked(newIsLocked);
        userInfo.setIsDisabled(newIsDisabled);
        userInfo.setIsAudited(newIsAudited);
        userInfo.setIsActivated(newIsActivated);
        userInfo.setIsAdmin(newIsAdmin);
        userInfo.setIntro(newIntro);
        userInfo.setGender(newGender);

        boolean update = this.mgrUserDao.updateUserInfo(userInfo) > 0;

        this.mgrUserRoleDao.deleteMore(userInfo.getId());

        List<String> roleIds = form.getRoleIds();
        List<MgrUserRole> list = new LinkedList<MgrUserRole>();

        for (String roleId : roleIds) {
            MgrUserRole userRole = new MgrUserRole();
            userRole.setId(IdUtils.getId());
            userRole.setRoleId(roleId);
            userRole.setUserId(userInfo.getId());

            list.add(userRole);
        }

        boolean insertMore = this.mgrUserRoleDao.insertMore(list) > 0;

        return update && insertMore;

    }

    // /*
    // * 判断是否超级管理员。
    // */
    // private boolean isSupervisor(String id) {
    // return "6cba07e9-7d11-11e4-9cd1-00ffc365a8bf".equals(id);
    // }

    @Override
    public PageInfo<MgrUser> findAll(String pageNumber, String pageSize) {

        int thisPageNumber = StringUtils.isNumeric(pageNumber) ? Integer.parseInt(pageNumber) : 1; // 页码
        int thisPageSize = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : Config.PAGE_SIZE; // 本页显示记录数

        PageHelper.startPage(thisPageNumber, thisPageSize); // 调用分页方法 紧跟其后的一个select被分页
        List<MgrUser> list = this.mgrUserDao.selectsUser();

        return new PageInfo<MgrUser>(list);
    }

    @Override
    public Set<String> findRoles(String username) {
        MgrUser user = findByUsername(username);
        if (user == null) {
            return Collections.emptySet();
        }
        List<MgrUser> userList = findByUsernameWithRoles(username);
        List<String> list = new LinkedList<String>();
        for (MgrUser muser : userList) {
            list.add(muser.getRoleId());
        }
        user.setRoleIds(list);
        return this.mgrRoleService.findRoles(user.getRoleIds());
    }

    @Override
    public Set<String> findPermissions(String username) {
        MgrUser user = findByUsername(username);
        if (user == null) {
            return Collections.emptySet();
        }
        List<MgrUser> userList = findByUsernameWithRoles(username);
        List<String> list = new LinkedList<String>();
        for (MgrUser muser : userList) {
            list.add(muser.getRoleId());
        }
        user.setRoleIds(list);
        return this.mgrRoleService.findPermissions(user.getRoleIds().toArray(new String[0]));
    }

    @Override
    public List<MgrUser> findByUsernameWithRoles(String username) {
        return this.mgrUserDao.selectByUsernameWithRoles(username);
    }

    @Override
    public PageInfo<MgrUser> findsAllWithRoles(String pageNumber, String pageSize) {
        int thisPageNumber = StringUtils.isNumeric(pageNumber) ? Integer.parseInt(pageNumber) : 1; // 页码
        int thisPageSize = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : Config.PAGE_SIZE; // 本页显示记录数

        PageHelper.startPage(thisPageNumber, thisPageSize); // 调用分页方法 紧跟其后的一个select被分页
        List<MgrUser> list = this.mgrUserDao.selectsUser();

        for (MgrUser user : list) {
            Set<String> roles = this.findRoles(user.getUsername());
            user.setRoles(roles);
        }

        return new PageInfo<MgrUser>(list);
    }

    @Override
    public boolean deleteUser(String id) {
        Subject currentUser = SecurityUtils.getSubject();
        MgrUser user = this.mgrUserDao.selectById(id);

        Set<String> roles = this.findRoles(user.getUsername());

        if (!currentUser.hasRole("*")) {
            if (roles.contains("admin") && currentUser.hasRole("admin")) {
                return false;
            }
        }
        if ("super".equals(user.getName())) {
            return false;
        }
        return this.mgrUserDao.updateToDelete(id) > 0;
    }

}
