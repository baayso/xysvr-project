/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户使用金币兑换钱财时客户端提交的数据。
 *
 * @author ChenFangjie(2015年4月28日 下午2:05:42)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class MoneyToRmoneyForm extends UserInfoForm {

    private String money; // 需要兑换的金币

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{0,8}$", message = "非法数据")
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
