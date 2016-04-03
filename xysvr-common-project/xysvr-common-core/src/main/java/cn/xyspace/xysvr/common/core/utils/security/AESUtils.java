/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils.security;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES加密解密工具类。
 * 
 * @author ChenFangjie(2014/12/08 20:28)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public final class AESUtils {

    private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);

    // 让工具类彻底不可以实例化
    private AESUtils() {
        throw new Error("工具类不可以实例化！");
    }

    private static Key key;
    private static final String KEY_STR = "XiaoYou2014XiaoYao";

    private static final String KEY_AES = "AES";
    private static final String UTF_8 = "UTF8";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(KEY_AES);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(128, secureRandom);
            key = generator.generateKey();
            generator = null;
        }
        catch (Exception e) {
            logger.error("初始化密钥出错！", e);
        }
    }

    /**
     * 对给定字符串进行加密。
     * 
     * @param str
     *            待加密的字符串
     * @return 加密数据
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String getEncryptString(String str) {
        String result = "";
        try {
            byte[] strBytes = str.getBytes(UTF_8);
            Cipher cipher = Cipher.getInstance(KEY_AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            result = Base64.encodeBase64String(encryptStrBytes);
        }
        catch (Exception e) {
            logger.error("加密出错！", e);
        }

        return result;
    }

    /**
     * 对给定字符串进行解密。
     * 
     * @param str
     *            待解密的字符串
     * @return 解密数据
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String getDecryptString(String str) {
        String result = "";
        try {
            byte[] strBytes = Base64.decodeBase64(str);
            Cipher cipher = Cipher.getInstance(KEY_AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            result = new String(decryptStrBytes, UTF_8);
        }
        catch (Exception e) {
            logger.error("解密出错！", e);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1) {
            System.out.println("请输入要加密的字符，用空格分隔。");
        }
        else {
            for (String arg : args) {
                System.out.println(arg + ":" + getEncryptString(arg));
            }
        }
    }

}
