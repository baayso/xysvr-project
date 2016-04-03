/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.service;

import java.util.List;
import java.util.Set;

import cn.xyspace.xysvr.common.user.form.UploadUserIconForm;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUser;
import cn.xyspace.xysvr.function.manager.user.form.FindUserForm;
import cn.xyspace.xysvr.function.manager.user.form.ModifyPwdForm;
import cn.xyspace.xysvr.function.manager.user.form.RegisterForm;
import cn.xyspace.xysvr.function.manager.user.form.UpdateUserInfoForm;

import com.github.pagehelper.PageInfo;

/**
 * 后台管理用户业务逻辑接口。
 * 
 * @author ChenFangjie(2015年2月5日 下午2:46:37)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public interface IMgrUserService {

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
     * 用户注册。
     * 
     * @param form
     *            用户注册时客户端提交的数据
     * @return 注册成功与否。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean register(RegisterForm form);

    /**
     * 根据用户名查找用户。
     * 
     * @param username
     *            用户名
     * @return 返回后台管理用户实体对象
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrUser findByUsername(String username);

    /**
     * 根据用户名查找用户关联查询出角色信息。
     * 
     * @param username
     *            用户名
     * @return 返回后台管理用户类型list
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrUser> findByUsernameWithRoles(String username);

    /**
     * 根据用户ID查找用户。
     * 
     * @param form
     *            用户数据
     * @return 返回后台管理用户实体对象
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrUser findById(FindUserForm form);

    /**
     * 查询所有后台管理员。
     * 
     * @param pageNumber
     *            当前页码
     * @param pageSize
     *            每页显示的总记录数
     * 
     * @return 返回后台管理用户实体对象
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<MgrUser> findAll(String pageNumber, String pageSize);

    /**
     * 修改用户密码。
     * 
     * @param form
     *            用户修改密码时客户端提交的数据
     * @return 修改成功返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean modifyPassword(ModifyPwdForm form);

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
     * 根据用户名查找其角色
     * 
     * @param username
     * @return set类型角色列表
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * 
     * @param username
     * @return set类型权限列表
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public Set<String> findPermissions(String username);

    /**
     * 查询所有后台管理员关联查询出角色信息。
     * 
     * @param pageNumber
     *            当前页码
     * @param pageSize
     *            每页显示的总记录数
     * 
     * @return 返回后台管理用户实体对象
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public PageInfo<MgrUser> findsAllWithRoles(String pageNumber, String pageSize);

    /**
     * 删除用户
     * 
     * @param id
     *            用户id
     * @return 删除成功与否
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean deleteUser(String id);

}
