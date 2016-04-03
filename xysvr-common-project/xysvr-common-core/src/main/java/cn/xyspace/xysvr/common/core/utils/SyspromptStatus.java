/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

/**
 * API Status Codes。
 *
 * @author Tanrongrong(2015年1月26日 下午4:56:01)
 *
 * @since 1.0.0
 *
 * @version 1.1.0
 *
 */
public enum SyspromptStatus {

    // 系统

    /**
     * 2011, 非法数据
     */
    ILLEGAL_DATA(2011, "非法数据"),

    /**
     * 2012, 出错了
     */
    UNKNOW_ERROR(2012, "出错了"),

    /**
     * 2013, 客户端认证成功
     */
    CLIENT_AUTHENTICATION_SUCCESS(2013, "客户端认证成功"),

    /**
     * 2014, 客户端认证失败
     */
    CLIENT_AUTHENTICATION_FAILURE(2014, "客户端认证失败"),

    /**
     * 2015, 提交反馈失败
     */
    SUBMIT_FEEDBACK_FAILURE(2015, "提交反馈失败"),

    /**
     * 2016, 提交反馈成功
     */
    SUBMIT_FEEDBACK_SUCCESS(2016, "提交反馈成功"),

    /**
     * 2017, 支付宝充值成功
     */
    ALIPAY_RECHARGE_SUCCESS(2017, "支付宝充值成功"),

    /**
     * 2018, 支付宝充值失败
     */
    ALIPAY_RECHARGE_FAILURE(2018, "支付宝充值失败"),

    /**
     * 2019, 未设置电子邮件地址
     */
    NOT_SETUP_EMAIL(2019, "未设置电子邮件地址"),

    /**
     * 2020, 邮件发送成功
     */
    EMAIL_SEND_SUCCESS(2020, "邮件发送成功"),

    /**
     * 2021, 邮件发送失败
     */
    EMAIL_SEND_FAILURE(2021, "邮件发送失败"),

    // 用户

    /**
     * 3011, 您的帐户已在其它地方登录，您已被迫下线
     */
    FORCED_TO_LOGOFF(3011, "您的帐户已在其它地方登录，您已被迫下线"),

    /**
     * 3012, 此用户名已被使用
     */
    USERNAME_ALREADY_USED(3012, "此用户名已被使用"),

    /**
     * 3013, 此用户名未使用
     */
    USERNAME_NOT_USED(3013, "此用户名未使用"),

    /**
     * 3014, 此email已被使用
     */
    EMAIL_ALREADY_USED(3014, "此email已被使用"),

    /**
     * 3015, 此email未使用
     */
    EMAIL_NOT_USED(3015, "此email未使用"),

    /**
     * 3016, 注册成功
     */
    REGISTER_SUCCESS(3016, "注册成功"),

    /**
     * 3017, 注册失败
     */
    REGISTER_FAILURE(3017, "注册失败"),

    /**
     * 3018, 您已经登录
     */
    ALREADY_LOGGED_ON(3018, "您已经登录"),

    /**
     * 3019, 您尚未登录
     */
    NOT_LOGGED_ON(3019, "您尚未登录"),

    /**
     * 3020, 登录成功
     */
    LOGIN_SUCCESS(3020, "登录成功"),

    /**
     * 3021, 登录出错
     */
    LOGIN_ERROR(3021, "登录出错"),

    /**
     * 3022, 登录失败次数过多，请稍候在试
     */
    WAIT_A_WHILE_TO_TRY(3022, "登录失败次数过多，请稍候在试"),

    /**
     * 3023, 帐号已被锁定
     */
    ACCOUNT_LOCKED(3023, "帐号已被锁定"),

    /**
     * 3024, 帐号已被禁用
     */
    ACCOUNT_DISABLED(3024, "帐号已被禁用"),

    /**
     * 3025, 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(3025, "用户名或密码错误"),

    /**
     * 3026, 您已成功注销登录
     */
    LOGOUT_SUCCESS(3026, "您已成功注销登录"),

    /**
     * 3027, 获取用户信息成功
     */
    GET_USER_INFORMATION_SUCCESS(3027, "获取用户信息成功"),

    /**
     * 3028, 原密码错误
     */
    ORIGINAL_PASSWORD_ERROR(3028, "原密码错误"),

    /**
     * 3029, 新密码和原密码一致
     */
    NEW_OLD_PASSWORD_CONSISTENT(3029, "新密码和原密码一致"),

    /**
     * 3030, 两次输入的新密码不一致
     */
    TWICE_PASSWORD_NOT_CONSISTENT(3030, "两次输入的新密码不一致"),

    /**
     * 3031, 修改密码成功
     */
    CHANGE_PASSWORD_SUCCESS(3031, "修改密码成功"),

    /**
     * 3032, 修改密码失败
     */
    CHANGE_PASSWORD_FAILURE(3032, "修改密码失败"),

    /**
     * 3033, 上传成功
     */
    UPLOAD_SUCCESS(3033, "上传成功"),

    /**
     * 3034, 上传失败
     */
    UPLOAD_FAILURE(3034, "上传失败"),

    /**
     * 3035, 您没有进行任何修改
     */
    NOT_CHANGE(3035, "您没有进行任何修改"),

    /**
     * 3036, 修改成功
     */
    MODIFY_SUCCESS(3036, "修改成功"),

    /**
     * 3037, 修改失败
     */
    MODIFY_FAILURE(3037, "修改失败"),

    /**
     * 3038, 加载出错
     */
    ERROR_LOADING(3038, "加载出错"),

    /**
     * 3039, 附件数据格式不正确
     */
    ATTACHMENT_DATA_FORMAT_ERROR(3039, "附件数据格式不正确"),

    /**
     * 3040, 更新用户财富失败
     */
    UPDATE_USER_WEALTH_FAILURE(3040, "更新用户财富失败"),

    /**
     * 3041, 金币兑换钱财成功
     */
    MONEY_EXCHANGE_REAL_MONEY_SUCCESS(3041, "兑换成功"),

    /**
     * 3042, 金币兑换钱财失败
     */
    MONEY_EXCHANGE_REAL_MONEY_FAILURE(3042, "兑换失败"),

    /**
     * 3043, 给定的金币不正确
     */
    GIVEN_MONEY_INCORRECT(3043, "给定的金币不正确"),

    /**
     * 3044, 您的金币不足
     */
    NOT_ENOUGH_MONEY(3044, "您的金币不足"),

    /**
     * 3045, 您的钱财不足
     */
    NOT_ENOUGH_REALMONEY(3045, "您的钱财不足"),

    /**
     * 3046, 您的赢取金币不足
     */
    NOT_ENOUGH_EARN_MONEY(3046, "您的赢取金币不足"),

    /**
     * 3047, 此设备已注册过
     */
    IDENTIFIER_ALREADY_USED(3047, "此设备已注册过"),

    /**
     * 3048, 此设备未注册过
     */
    IDENTIFIER_NOT_USED(3048, "此设备未注册过"),

    /**
     * 3049, 重置密码成功
     */
    RESET_PASSWORD_SUCCESS(3049, "重置密码成功"),

    /**
     * 3050, 重置密码失败
     */
    RESET_PASSWORD_FAILURE(3050, "重置密码失败"),

    /**
     * 3051, 校验码错误
     */
    CHECKCODE_ERROR(3051, "校验码错误"),

    /**
     * 3052, 邀请人不存在
     */
    INVITER_NOT_EXIT(3052, "邀请人不存在"),

    /**
     * 3053, 用户不存在
     */
    USER_NOT_EXIST(3053, "用户不存在"),

    // 私信

    /**
     * 6012, 发送私信成功
     */
    SEND_MESSAGES_SUCCESS(6012, "发送私信成功"),

    /**
     * 6013, 发送私信失败
     */
    SEND_MESSAGES_FAILURE(6013, "发送私信失败"),

    // 七牛云存储

    /**
     * 10011, 获取成功
     */
    ACHIEVE_SUCCESS(10011, "获取成功"),

    /**
     * 10012, 获取失败
     */
    ACHIEVE_FALIURE(10012, "获取失败");

    private final int value;
    private final String reasonPhrase;

    private SyspromptStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Return the enum constant of this type with the specified numeric value.
     * 
     * @param statusCode
     *            the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException
     *             if this enum has no constant for the specified numeric value
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static SyspromptStatus valueOf(int statusCode) {
        for (SyspromptStatus status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

}
