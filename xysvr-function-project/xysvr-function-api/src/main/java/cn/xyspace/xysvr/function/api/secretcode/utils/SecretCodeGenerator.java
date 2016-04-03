/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.api.secretcode.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;

import cn.xyspace.xysvr.common.core.utils.Base58;

/**
 * 生成暗号工具类。
 * 
 * @author LuoWanzhu(Jan 12, 2015 7:46:58 PM)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 */
public final class SecretCodeGenerator {

    // 让工具类彻底不可以实例化
    private SecretCodeGenerator() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 生成使用58进制编码的17位的ObjectId。
     * 
     * @return 58进制编码的17位的ObjectId
     * 
     * @author ChenFangjie(2015/1/19 9:26:08)
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String generateCode() {
        String objectId = Base58.encode(ObjectId.get().toByteArray());

        List<Character> charList = new ArrayList<>(objectId.length());

        char[] chars = objectId.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            charList.add(chars[j]);
        }

        Collections.shuffle(charList);

        StringBuilder sb = new StringBuilder(objectId.length());
        for (int j = 0; j < charList.size(); j++) {
            sb.append(charList.get(j));
        }

        return sb.toString();
    }

}
