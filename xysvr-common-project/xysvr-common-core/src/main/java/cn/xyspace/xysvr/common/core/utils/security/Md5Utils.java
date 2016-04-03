/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils.security;

import java.security.MessageDigest;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author ChenFangjie
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class Md5Utils {

    private static final Logger logger = LoggerFactory.getLogger(Md5Utils.class);

    /**
     * 
     * @param str
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String hash(String str) {
        try {
            return new String(toHex(md5(str)).getBytes("UTF-8"), "UTF-8");
        }
        catch (Exception e) {
            logger.error("not supported charset...{}", e);
            return str;
        }
    }

    /**
     * 加密。
     * 
     * @param username
     * @param password
     * @param salt
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }

    /**
     * 随机生成密码盐。
     * 
     * @return 密码盐
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String randomSalt() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    //
    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e) {
            logger.error("MD5 Error...", e);
        }
        return null;
    }

    //
    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

}
