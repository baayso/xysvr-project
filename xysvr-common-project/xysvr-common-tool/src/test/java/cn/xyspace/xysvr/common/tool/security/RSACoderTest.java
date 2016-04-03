/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.security;

import static org.junit.Assert.assertTrue;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link cn.xyspace.xysvr.common.utils.security.RSACoder}.
 *
 * @author ChenFangjie(2015/1/11 15:33:57)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class RSACoderTest {

    /* 公钥 */
    private byte[] publicKey;

    /* 私钥 */
    private byte[] privateKey;

    /**
     * 初始化密钥。
     * 
     * @throws Exception
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Before
    public void setUp() throws Exception {
        Map<String, Key> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);

        System.err.println("公钥: \n" + Base64.getEncoder().encodeToString(publicKey));
        System.err.println("私钥： \n" + Base64.getEncoder().encodeToString(privateKey));
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.utils.security.RSACoder#sign(byte[], byte[])}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testSign() {
        String inputStr = "Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd.";
        byte[] data = StringUtils.getBytesUtf8(inputStr);

        // 产生签名
        byte[] sign = RSACoder.sign(data, privateKey);
        System.err.println("签名:\n" + Base64.getEncoder().encodeToString(sign));

        // 验证签名
        boolean status = RSACoder.verify(data, publicKey, sign);
        System.err.println("状态:\n" + status);
        assertTrue(status);
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.utils.security.RSACoder#sign(byte[], byte[])}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testSign_2() {
        String inputStr = "Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd.";
        byte[] data = StringUtils.getBytesUtf8(inputStr);

        final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCf40BYWqB/upR9f38KAxhp24D2ASlgItCVpaV3jp23/pIpx4N2/2f7WmXNvlLHPRxt8uyNq7q2mYx0EimhKxc0TKb1g8/9OCzO1/nLVT/uV+74ZfeLjdOpGv7o14KTfFFMsBVQSRU6vz5QJYj75XYgsmUn9wGaLoUglcxObls9pQIDAQAB";
        final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ/jQFhaoH+6lH1/fwoDGGnbgPYBKWAi0JWlpXeOnbf+kinHg3b/Z/taZc2+Usc9HG3y7I2ruraZjHQSKaErFzRMpvWDz/04LM7X+ctVP+5X7vhl94uN06ka/ujXgpN8UUywFVBJFTq/PlAliPvldiCyZSf3AZouhSCVzE5uWz2lAgMBAAECgYAV091t8nlk1qD8/RHn2QJVOU1CyGfQoxAcze9oAgVQICXuJDmzXizg6LdrPido4dPmwro+oQotcYr2MaCTSyGwn94U97WpAW/a2izYn69qW28tExa5G9NGE6rH18zeEOvL+nYZONoCq9w/krrpCSqCFcgvHZXTxep6LaAHtoqYJQJBANCo+RnwXCOo7lMLre6BNoIBq65GJ8+kzvCWMs3Ip2Tm+3t0V5sI6xkVgiXmbx7+O4/F2vvJzAfDcTqQPxgghT8CQQDEKZF2393D8fFXwkkbUfWHinS9To4LtpDyY5MkZtzYyRYOJGZTe0HVLDnQEQCnzu2upXSWloD9WLOPPzns1tAbAkEAujumIRWbVySGmc5Zzf67pKtTDLeG/tgs7yRPccW+Sduy5wv+yNvb4UaGI8eYtEPa60Z/Xa1NkjxJCikGd0VHRwJAD7zuCMWJO1FL9aiGyQRYWJmJKC8HQnVRR8EpawA9s77eYCAXfZcj/wBfWwwiK1R0yVBI/FPy4WCed12xPgk2BQJAIVMclMYmOJ6/8ZiWi2K5RQoQO1Zlig95Iczszm5CFuVB4M1Ln1G+zV2VfxZSADd8OVZhq0SHaZ5HVZYR0E0AFA==";

        // 产生签名
        byte[] sign = RSACoder.sign(data, Base64.getDecoder().decode(privateKey));
        // System.err.println("签名:\n" + Base64.encodeBase64String(sign));

        // 验证签名
        boolean status = RSACoder.verify(data, Base64.getDecoder().decode(publicKey), sign);
        // System.err.println("状态:\n" + status);

        assertTrue(status);
    }

}
