/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.service;

import cn.xyspace.xysvr.common.user.entity.User;
import cn.xyspace.xysvr.common.user.form.ModifyPwdForm;
import cn.xyspace.xysvr.common.user.form.RegisterForm;
import cn.xyspace.xysvr.common.user.form.ResetPwdForm;
import cn.xyspace.xysvr.common.user.form.UpdateUserInfoForm;
import cn.xyspace.xysvr.common.user.form.UploadUserIconForm;
import cn.xyspace.xysvr.common.user.shrio.ShiroUser;

/**
 * 用户业务逻辑接口。
 * 
 * @author ChenFangjie(2014/11/29 21:06:16)
 * 
 * @since 1.0.0
 * 
 * @version 1.1.0
 *
 */
public interface IUserService {

    /**
     * 验证用户名是否存在。
     * 
     * @param username
     *            用户名
     * @return 如果存在返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean hasUsername(String username);

    /**
     * 验证电子邮件地址是否存在。
     * 
     * @param email
     *            电子邮件地址
     * @return 如果存在返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean hasEmail(String email);

    /**
     * 验证注册标识符是否存在。
     * 
     * @param identifier
     *            注册标识符
     * @return 如果存在返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean hasIdentifier(String identifier);

    /**
     * 用户注册。
     * 
     * @param form
     *            用户注册时客户端提交的数据
     * @return 注册成功与否。
     * 
     * @since 1.0.0
     * @version 1.1.0
     */
    public boolean register(RegisterForm form);

    /**
     * 根据用户ID查询用户。
     * 
     * @param id
     *            用户ID
     * @return 返回不带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User findById(String id);

    /**
     * 根据用户ID查询用户（包括用户财富信息）。
     * 
     * @param id
     *            用户ID
     * @return 返回带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User findWithAssetById(String id);

    /**
     * 根据用户名查找用户。
     * 
     * @param username
     *            用户名
     * @return 返回不带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找用户（包括用户财富信息）。
     * 
     * @param username
     *            用户名
     * @return 返回带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User findWithAssetByUsername(String username);

    /**
     * 修改用户密码。
     * 
     * @param form
     *            修改密码时客户端提交的数据
     * @return 修改成功返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean modifyPassword(ModifyPwdForm form);

    /**
     * 重置密码。
     * 
     * @param form
     *            重置密码时客户端提交的数据
     * @return 重置成功返回true，否则返回false
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean resetPassword(ResetPwdForm form);

    /**
     * 上传用户头像。
     * 
     * @param form
     *            用户上传头像时客户端提交的数据
     * @return 上传成功返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean uploadUserIcon(UploadUserIconForm form);

    /**
     * 更新用户个人资料信息。
     * 
     * @param form
     *            用户上传个人资料时客户端提交的数据
     * @return 修改成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean updateUserInfo(UpdateUserInfoForm form);

    /**
     * 刷新session中的用户信息。
     * 
     * @param id
     *            用户id（主键）
     * @return {@linkplain cn.xyspace.xysvr.common.user.shrio.ShiroUser}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public ShiroUser refreshUser(String id);

}
