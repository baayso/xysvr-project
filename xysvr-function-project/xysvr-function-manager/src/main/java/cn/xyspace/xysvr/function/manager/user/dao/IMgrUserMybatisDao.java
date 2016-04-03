/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUser;

/**
 * 后台管理用户表数据访问对象接口。
 * 
 * @author ChenFangjie(2015年2月5日 下午1:59:21)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IMgrUserMybatisDao extends IBaseMybatisDao {

    /**
     * 查询用户名数目。
     * 
     * @param username
     *            用户名
     * @return 记录数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int selectCountUsername(String username);

    /**
     * 查询email数目。
     * 
     * @param email
     *            电子邮件地址
     * @return 记录数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int selectCountEmail(String email);

    /**
     * 新增用户。
     * 
     * @param entity
     *            用户实体对象
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int insert(MgrUser entity);

    /**
     * 根据主键更新用户密码和密码盐。
     * 
     * @param id
     *            主键
     * @param newPassword
     *            密码
     * @param salt
     *            密码盐
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updatePassword(@Param(value = "id") String id, @Param(value = "newPassword") String newPassword, @Param(value = "salt") String salt);

    /**
     * 根据主键更新用户最后登录时间戳。
     * 
     * @param id
     *            主键
     * @param lastLoginTime
     *            登录时间戳
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateLastLoginTime(@Param(value = "id") String id, @Param(value = "lastLoginTime") Long lastLoginTime);

    /**
     * 更新用户的头像信息。
     * 
     * @param user
     *            用户实体对象
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateUserIcon(MgrUser user);

    /**
     * 根据主键查询用户。
     * 
     * @param id
     *            主键
     * @return 返回后台管理用户实体对象
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrUser selectById(String id);

    /**
     * 根据用户名查询用户。
     * 
     * @param username
     *            用户名
     * @return 返回后台管理用户实体对象
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public MgrUser selectByUsername(String username);

    /**
     * 根据用户名查询用户关联查询出角色信息。
     * 
     * @param username
     *            用户名
     * @return 返回MgrUser类型list
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrUser> selectByUsernameWithRoles(String username);

    /**
     * 根据ID更新管理员资料信息。
     * 
     * @param user
     *            用户实体对象
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateUserInfo(MgrUser user);

    /**
     * 查询除当前用户以外的email数目。
     * 
     * @param userId
     *            用户ID
     * @param email
     *            用户邮箱
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int selectSumEmail(@Param(value = "userId") String userId, @Param(value = "email") String email);

    /**
     * 查询所有后台管理员。
     * 
     * @return 返回后台管理用户实体对象
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public List<MgrUser> selectsUser();

    /**
     * 删除用户
     * 
     * @param userId
     *            用户id
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateToDelete(String userId);

}
