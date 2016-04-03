/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.token.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.tool.token.service.ITokenService;

/**
 * 客户端认识信息业务逻辑实现。
 * 
 * @author ChenFangjie(2014/12/23 17:29:54)
 * @author WuQiying(2015/1/11 16:08:26)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Override
    public boolean clientAuthentication(String sign) {
        boolean isPass = false;

        if (StringUtils.isEmpty(sign)) {
            isPass = false;
        }
        else if (StringUtils.equals(Config.CLIENT_AUTHORIZE_SIGN_ONE, sign) || StringUtils.equals(Config.CLIENT_AUTHORIZE_SIGN_TWO, sign)) {
            isPass = true;
        }

        return isPass;
    }

}
