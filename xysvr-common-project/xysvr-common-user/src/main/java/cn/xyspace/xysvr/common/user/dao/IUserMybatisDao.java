/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.dao;

import org.apache.ibatis.annotations.Param;

import cn.xyspace.xysvr.common.core.dao.IBaseMybatisDao;
import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.common.user.entity.User;

/**
 * 用户表数据访问对象接口。
 * 
 * @author ChenFangjie(2014/11/30 15:36:26)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IUserMybatisDao extends IBaseMybatisDao {

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
     * 查询注册标识符数目。
     * 
     * @param identifier
     *            注册标识符
     * @return 记录数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int selectCountIdentifier(String identifier);

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
    public int insert(User entity);

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
     * 根据主键更新最后发起的活动ID和时间戳。
     * 
     * @param id
     *            主键
     * @param lastActivityId
     *            活动ID
     * @param lastActivityTime
     *            时间戳
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateLastActivity(@Param(value = "id") String id, @Param(value = "lastActivityId") String lastActivityId, @Param(value = "lastActivityTime") Long lastActivityTime);

    /**
     * 根据主键更新最后发布的微博ID和时间戳。
     * 
     * @param id
     *            主键
     * @param lastMicroblogId
     *            微博ID
     * @param lastMicroblogTime
     *            时间戳
     * @return 受影响的行数
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateLastMicroblog(@Param(value = "id") String id, @Param(value = "lastMicroblogId") String lastMicroblogId, @Param(value = "lastMicroblogTime") Long lastMicroblogTime);

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
    public int updateUserIcon(User user);

    /**
     * 根据主键查询用户。
     * 
     * @param id
     *            主键
     * @return 返回不带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User selectById(String id);

    /**
     * 根据主键查询用户（包括用户财富信息）。
     * 
     * @param id
     *            主键
     * @return 返回带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User selectWithAssetById(String id);

    /**
     * 根据用户名查询用户。
     * 
     * @param username
     *            用户名
     * @return 返回不带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User selectByUsername(String username);

    /**
     * 根据用户名查询用户（包括用户财富信息）。
     * 
     * @param username
     *            用户名
     * @return 返回带 {@linkplain cn.xyspace.xysvr.common.user.entity.UserAsset} 的 {@linkplain cn.xyspace.xysvr.common.user.entity.User}
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public User selectWithAssetByUsername(String username);

    /**
     * 根据用户ID更新用户个人资料信息。
     * 
     * @param userId
     *            用户ID
     * @param intro
     *            用户签名
     * @param email
     *            用户邮箱
     * @param telephone
     *            用户电话
     * @return 受影响的行数
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public int updateUserInfo(@Param(value = "userId") String userId, @Param(value = "intro") String intro, @Param(value = "email") String email, @Param(value = "telephone") String telephone);

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

}
