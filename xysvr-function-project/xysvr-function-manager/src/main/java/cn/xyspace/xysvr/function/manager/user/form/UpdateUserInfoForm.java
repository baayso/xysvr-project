/* Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改用户信息表单数据。
 *
 * @author CaoZhongsheng(2015年2月7日 上午10:06:15)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class UpdateUserInfoForm {

    private String userId; // 用户编号
    private String nickname; // 昵称
    private String name; // 用户的真实姓名
    private String gender; // 用户性别
    private String email; // 邮箱
    private String birthday; // 出生日期
    private String telephone; // 电话
    private String address; // 地址
    private String isLocked; // 是否已锁定
    private String isDisabled; // 是否已禁用
    private String isAudited; // 是否已审核
    private String isActivated; // 是否已激活
    private String isAdmin; // 是否管理员
    private String intro; // 用户介绍
    private List<String> roleIds;// 封装页面id数据

    @NotBlank
    @Pattern(regexp = "^\\w{24}$", message = "格式错误")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Length(min = 0, max = 60)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Pattern(regexp = "^[a-zA-Z0-9\u4e00-\u9fa5]{0,16}$", message = "必须为0-16位的字符（只可包含汉字、字母、数字）")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Pattern(regexp = "^(F|M|S)$", message = "非法数据")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 0, max = 15)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Pattern(regexp = "^[0-9]{0,20}$", message = "非法数据")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Length(min = 0, max = 60)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(String isAudited) {
        this.isAudited = isAudited;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    @NotBlank
    @Pattern(regexp = "^(false|true)$", message = "非法数据")
    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Length(min = 0, max = 250)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @NotNull
    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

}
