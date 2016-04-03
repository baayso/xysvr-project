/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cn.xyspace.xysvr.common.core.entity.IdEntity;
import cn.xyspace.xysvr.common.user.entity.UserGender;

/**
 * 后台管理用户实体类。
 *
 * @author ChenFangjie(2015年2月5日 下午1:45:07)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class MgrUser extends IdEntity {

    private static final long serialVersionUID = 6969386479811610843L;

    private String username; // 用户名（登录名）
    private String nickname; // 昵称
    private String name; // 用户的真实姓名
    private String password; // 密码
    private String salt; // 密码盐（密码作料）
    private UserGender gender; // 用户性别，0:保密、1:男、2:女
    private String birthday; // 用户出生日期
    private String telephone; // 用户电话
    private String email; // 用户电子邮件
    private String address; // 用户地址
    private Boolean isLocked; // 用户是否已锁定（默认FALSE）
    private Boolean isDisabled; // 用户是否已禁用（默认FALSE）
    private Boolean isAudited; // 用户是否已通过审核（默认FALSE）
    private Boolean isActivated; // 用户是否已激活（默认FALSE）
    private Boolean isAdmin; // 用户是否为管理员（默认FALSE）
    private String createIp; // 注册IP
    private Long createTime; // 用户创建（注册）时间戳
    private Long lastLoginTime; // 用户最后登录时间戳
    private String intro; // 用户介绍
    //
    private String iconPath; // 用户头像保存路径
    private Long size; // 用户头像大小
    private String mime; // 用户头像格式
    private String extname; // 用户头像扩展名
    private String hash; // 用户头像哈希值
    private Integer width; // 用户头像宽度
    private Integer height; // 用户头像高度
    private Long uptime; // 用户头像上传时间戳
    //
    private Boolean isDeleted; // 用户是否已删除（默认FALSE）

    private List<String> roleIds; // 拥有的角色列表
    private String roleId; // 角色ID，主键
    private String roleName; // 角色名称
    private Boolean isAvailable; // 是否可用
    private String descriptions; // 角色描述
    private Set<String> roles;// 拥有的角色

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Boolean getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Boolean isAudited) {
        this.isAudited = isAudited;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<String> getRoleIds() {
        if (roleIds == null) {
            roleIds = new ArrayList<String>();
        }
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleIdsStr() {
        if (CollectionUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (String roleId : roleIds) {
            s.append(roleId);
            s.append(",");
        }
        return s.toString();
    }

    public void setRoleIdsStr(String roleIdsStr) {
        if (StringUtils.isEmpty(roleIdsStr)) {
            return;
        }
        String[] roleIdStrs = roleIdsStr.split(",");
        for (String roleIdStr : roleIdStrs) {
            if (StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            getRoleIds().add(String.valueOf(roleIdStr));
        }
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
