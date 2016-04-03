/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.security;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 继承 {@linkplain org.springframework.beans.factory.config.PropertyPlaceholderConfigurer} 的定义支持密文属性的属性配置器。
 * 
 * @author ChenFangjie(2014/12/11 18:33:39)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String[] encryptPropNames = { "jdbc.username", "jdbc.password", "jdbc.username.1", "jdbc.password.1", "jdbc.username.2", "jdbc.password.2", "redis.password",
            "mongodb.password", "email.password" };

    // 对特定属性的属性值进行转换
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (this.isEncryptProp(propertyName)) {
            String decryptValue = AESCoder.decryptStr(propertyValue);
            return decryptValue;
        }
        else {
            return propertyValue;
        }
    }

    /*
     * 判断是否是加密的属性。
     * 
     * @param propertyName
     * 
     * @return
     * 
     * @since 1.0.0
     * 
     * @version 1.0.0
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptPropName : encryptPropNames) {
            if (encryptPropName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

}
