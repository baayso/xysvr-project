/* Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.manager.user.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询管理员信息数据表单。
 *
 * @author CaoZhongsheng(2015年2月6日 下午7:56:24)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class FindUserForm {

    private String id; // 管理员id
    private String username;// 拥有的角色

    @NotBlank
    @Pattern(regexp = "^\\w{24}$", message = "格式错误")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]{0,60}$", message = "必须为1-60位的字符且以汉字或字母开头（只可包含汉字、字母、数字）")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
