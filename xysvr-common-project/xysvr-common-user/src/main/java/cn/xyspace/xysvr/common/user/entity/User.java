/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;

/**
 * 用户实体类。
 * 
 * @author ChenFangjie(2014/11/29 20:21:36)
 * 
 * @since 1.0.0
 * 
 * @version 1.1.0
 *
 */
public class User extends IdEntity {

    private static final long serialVersionUID = -262981068912930960L;

    private String username; // 用户名（登录名）
    private String nickname; // 昵称
    private String name; // 用户的真实姓名
    @JsonIgnore
    private String password; // 密码
    @JsonIgnore
    private String salt; // 密码盐（密码作料）
    private UserGender gender; // 用户性别，0:保密、1:男、2:女
    private String birthday; // 用户出生日期
    private String telephone; // 用户电话
    private String email; // 用户电子邮件
    private String inviter; // 邀请人
    @JsonIgnore
    private String identifier; // 注册标识符
    private String address; // 用户地址
    private Boolean isLocked; // 用户是否已锁定（默认FALSE）
    private Boolean isDisabled; // 用户是否已禁用（默认FALSE）
    private Boolean isAudited; // 用户是否已通过审核（默认FALSE）
    private Boolean isActivated; // 用户是否已激活（默认FALSE）
    private String regIp; // 注册IP
    private Double regLongitude; // 用户注册时的经度
    private Double regLatitude; // 用户注册时的纬度
    private String regCity;// 用户注册时所在的城市
    private String regPosition;// 用户注册时的详细地理位置
    private Long createTime; // 用户创建（注册）时间戳
    private Long lastLoginTime; // 用户最后登录时间戳
    private String lastMicroblogId; // 用户最后发布的微博ID
    private Long lastMicroblogTime; // 用户最后发布微博的时间戳
    private String lastActivityId; // 用户最后发起的活动ID
    private Long lastActivityTime; // 用户最后发起活动的时间戳
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
    @JsonIgnore
    private String roleIds; // 角色列表
    private Boolean isDeleted; // 用户是否已删除（默认FALSE）

    private UserAsset asset; // 用户财富

    @JsonIgnore
    public List<String> getRoleList() {
        // 角色列表在数据库中实际以逗号分隔字符串存储，因此返回不能修改的List.
        return ImmutableList.copyOf(StringUtils.split(this.roleIds, ","));
    }

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

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public Double getRegLongitude() {
        return regLongitude;
    }

    public void setRegLongitude(Double regLongitude) {
        this.regLongitude = regLongitude;
    }

    public Double getRegLatitude() {
        return regLatitude;
    }

    public void setRegLatitude(Double regLatitude) {
        this.regLatitude = regLatitude;
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    public String getRegPosition() {
        return regPosition;
    }

    public void setRegPosition(String regPosition) {
        this.regPosition = regPosition;
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

    public String getLastMicroblogId() {
        return lastMicroblogId;
    }

    public void setLastMicroblogId(String lastMicroblogId) {
        this.lastMicroblogId = lastMicroblogId;
    }

    public Long getLastMicroblogTime() {
        return lastMicroblogTime;
    }

    public void setLastMicroblogTime(Long lastMicroblogTime) {
        this.lastMicroblogTime = lastMicroblogTime;
    }

    public String getLastActivityId() {
        return lastActivityId;
    }

    public void setLastActivityId(String lastActivityId) {
        this.lastActivityId = lastActivityId;
    }

    public Long getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(Long lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public UserAsset getAsset() {
        return asset;
    }

    public void setAsset(UserAsset asset) {
        this.asset = asset;
    }

}
