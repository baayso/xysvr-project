/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.token.service;

/**
 * 客户端认识信息业务逻辑接口。
 * 
 * @author ChenFangjie(2014/12/23 17:28:39)
 * @author WuQiying(2015/1/12 10:04:46)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public interface ITokenService {

    /**
     * 客户端身份认证。
     * 
     * @param sign
     *            签名。
     * @return 认证通过返回true，否则返回false。
     * 
     * @author ChenFangjie(2015/1/11 18:18:18)
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean clientAuthentication(String sign);

}
