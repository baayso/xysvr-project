/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.security;

import static org.junit.Assert.assertEquals;

import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder}.
 * 
 * @author ChenFangJie(2015年1月10日 下午6:04:07)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class AESCoderTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder#encryptStr(java.lang.String)}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testEncryptStr() {
        String actual = AESCoder.encryptStr("postgres");
        System.out.println(actual);
        assertEquals("ESCrViDw9pWg+GpfVRLNZQ==", actual);
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder#decryptStr(java.lang.String)}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testDecryptStr() {
        String actual = AESCoder.decryptStr("ESCrViDw9pWg+GpfVRLNZQ==");
        assertEquals("postgres", actual);
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder#initKey()}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testInitKey() {
        byte[] actual = AESCoder.initKey();
        System.out.println(Base64.getEncoder().encodeToString(actual));
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder#toKey(byte[])}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testToKey() {
        byte[] expected = AESCoder.initKey();
        byte[] actual = AESCoder.toKey(expected).getEncoded();
        assertEquals(Base64.getEncoder().encodeToString(expected), org.apache.commons.codec.binary.Base64.encodeBase64String(actual));
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder#encrypt(byte[], byte[])}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testEncrypt() {
    }

    /**
     * Test method for {@link cn.xyspace.xysvr.common.core.utils.security.AESCoder#decrypt(byte[], byte[])}.
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @Test
    public void testDecrypt() {
    }

}
