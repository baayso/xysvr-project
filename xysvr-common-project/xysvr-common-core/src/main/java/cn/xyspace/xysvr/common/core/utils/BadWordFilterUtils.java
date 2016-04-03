/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 敏感词过滤工具类。
 * <br>
 * 原自 http://www.cnblogs.com/chenssy/p/3751221.html
 * 
 * @author (2015年4月18日 上午11:51:26)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public final class BadWordFilterUtils {

    private static final Logger logger = LoggerFactory.getLogger(BadWordFilterUtils.class);

    // 让工具类彻底不可以实例化
    private BadWordFilterUtils() {
        throw new Error("工具类不可以实例化！");
    }

    private static final String ENCODING = "UTF-8"; // 字符编码

    private static File wordFilterFile = null;

    private static long fileLastModified = 0L;

    private static Set<String> words = null;

    private static Map badWordMap = null;

    static {
        try {
            wordFilterFile = new ClassPathResource("wordfilter.txt").getFile();
        }
        catch (IOException e) {
            logger.error("获取敏感词库文件出错！", e);
        }

        // 读取敏感词库
        reload();
    }

    /**
     * 敏感词匹配规则。
     *
     * @author ChenFangjie(2015年4月18日 下午1:54:09)
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static enum MatchType {
        /** 最小匹配规则 */
        MIN,
        /** 最大匹配规则 */
        MAX;
    }

    /**
     * 判断文本是否包含敏感字符。
     * 
     * @param text
     *            文本
     * @param matchType
     *            匹配规则
     * @return 若包含返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static boolean contains(String text, MatchType matchType) {
        boolean flag = false;

        if (StringUtils.isEmpty(text)) {
            return flag;
        }

        if (matchType == null) {
            matchType = MatchType.MIN;
        }

        for (int i = 0; i < text.length(); i++) {
            int matchFlag = check(text, i, matchType); // 判断是否包含敏感字符
            if (matchFlag > 0) { // 大于0存在，返回true
                flag = true;
            }
        }

        return flag;
    }

    /**
     * 替换文本中的敏感词。
     * 
     * @param text
     *            文本
     * @param replaceChar
     *            替换字符
     * @param matchType
     *            匹配规则
     * @return 替换后的文本
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String replace(String text, String replaceChar, MatchType matchType) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        if (StringUtils.isEmpty(replaceChar)) {
            replaceChar = "*";
        }

        if (matchType == null) {
            matchType = MatchType.MIN;
        }

        String resultText = text;
        Set<String> wordSet = find(text, matchType); // 获取所有的敏感词

        for (String word : wordSet) {
            String replaceStr = getReplaceChars(replaceChar, word.length());
            resultText = StringUtils.replace(resultText, word, replaceStr); // resultText = resultText.replaceAll(word, replaceStr);
        }

        return resultText;
    }

    /**
     * 查找文本中的敏感词。
     * 
     * @param text
     *            文本
     * @param matchType
     *            匹配规则
     * @return 敏感词集合
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static Set<String> find(String text, MatchType matchType) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        if (matchType == null) {
            matchType = MatchType.MIN;
        }

        Set<String> baseWordSet = new HashSet<String>();

        for (int i = 0; i < text.length(); i++) {
            int badWordLength = check(text, i, matchType); // 判断是否包含敏感字符
            if (badWordLength > 0) { // 存在则加入set中
                baseWordSet.add(text.substring(i, i + badWordLength));
                i = i + badWordLength - 1; // 减1的原因，是因为for会自增
            }
        }

        return baseWordSet;
    }

    /**
     * 检查文本中是否包含敏感字符。
     * 
     * @param text
     *            文本
     * @param beginIndex
     *            开始检查位置
     * @param matchType
     *            匹配规则
     * @return 如果存在，则返回敏感词字符的长度，不存在返回0
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static int check(String text, int beginIndex, MatchType matchType) {
        if (StringUtils.isEmpty(text)) {
            return 0;
        }

        if (matchType == null) {
            matchType = MatchType.MIN;
        }

        // 读取敏感词库
        reload();

        boolean flag = false; // 敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0; // 匹配标识数默认为0
        Map nowMap = badWordMap;
        for (int i = beginIndex; i < text.length(); i++) {
            char word = text.charAt(i);
            nowMap = (Map) nowMap.get(word); // 获取指定key
            if (nowMap != null) { // 存在，则判断是否为最后一个
                matchFlag++; // 找到相应key，匹配标识+1
                if (Boolean.TRUE.equals(nowMap.get("isEnd"))) { // 如果为最后一个匹配规则结束循环，返回匹配标识数
                    flag = true; // 结束标志位为true
                    if (MatchType.MIN.equals(matchType)) { // 最小规则直接返回，最大规则还需继续查找
                        break;
                    }
                }
            }
            else { // 不存在，直接返回
                break;
            }
        }

        if (matchFlag < 2 || !flag) { // 词长度必须大于等于1
            matchFlag = 0;
        }

        return matchFlag;
    }

    /**
     * 返回敏感词列表。
     * 
     * @return 敏感词列表
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static Set<String> list() {
        // 读取敏感词库
        reload();

        return words;
    }

    /**
     * 增加敏感词。
     * 
     * @param word
     *            敏感词
     * @throws IOException
     *             如果发生 I/O 错误
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static void add(String word) throws IOException {
        word = word.toLowerCase();

        synchronized (BadWordFilterUtils.class) {
            if (!words.contains(word)) {
                words.add(word);
                badWordMap = badWordSetToMap(words);
                FileUtils.writeLines(wordFilterFile, ENCODING, words);
                fileLastModified = wordFilterFile.lastModified();
            }
        }
    }

    /**
     * 移除敏感词。
     * 
     * @param word
     *            敏感词
     * @throws IOException
     *             如果发生 I/O 错误
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static void delete(String word) throws IOException {
        word = word.toLowerCase();

        synchronized (BadWordFilterUtils.class) {
            if (words.contains(word)) {
                words.remove(word);
                badWordMap = badWordSetToMap(words);
                FileUtils.writeLines(wordFilterFile, ENCODING, words);
                fileLastModified = wordFilterFile.lastModified();
            }
        }
    }

    // ======================================================================================================================== //

    /*
     * 获取替换字符串。
     * 
     * @param replaceChar 替换字符
     * 
     * @param length 长度
     * 
     * @return 替换字符串
     * 
     * @since 1.0.0
     * 
     * @version 1.0.0
     */
    private static String getReplaceChars(String replaceChar, int length) {
        StringBuilder resultReplace = new StringBuilder(replaceChar);

        for (int i = 1; i < length; i++) {
            resultReplace.append(replaceChar);
        }

        return resultReplace.toString();
    }

    /*
     * 读取敏感词库文件中的内容，将内容添加到set集合中。
     * 
     * @return 敏感词库集合
     * 
     * @since 1.0.0
     * 
     * @version 1.0.0
     */
    private static void reload() {
        if (!wordFilterFile.exists()) {
            return;
        }

        if (wordFilterFile.lastModified() > fileLastModified) {
            synchronized (BadWordFilterUtils.class) {
                if (wordFilterFile.lastModified() > fileLastModified) {
                    LineIterator lines = null;
                    try {
                        lines = FileUtils.lineIterator(wordFilterFile, ENCODING);
                    }
                    catch (IOException e) {
                        logger.error("读取敏感词库文件出错！", e);
                    }

                    if (lines != null) {
                        words = new HashSet<String>();
                        fileLastModified = wordFilterFile.lastModified();
                        while (lines.hasNext()) {
                            String line = lines.nextLine();
                            if (StringUtils.isNotBlank(line)) {
                                words.add(line.trim().toLowerCase());
                            }
                        }

                        // 将敏感词库加入到HashMap中
                        badWordMap = badWordSetToMap(words);
                    }
                }
            }
        }
    }

    /*
     * 读取敏感词库，将敏感词放入HashMap中，构建一个DFA算法模型：
     * 
     * 中 = { isEnd = 0 国 = { isEnd = 1 人 = { isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = { isEnd = 1 } } } }
     * 
     * 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 = { isEnd = 1 } } } }
     * 
     * @param keywordSet 词库
     * 
     * @return DFA算法模型Map
     * 
     * @since 1.0.0
     * 
     * @version 1.0.0
     */
    private static Map badWordSetToMap(Set<String> keywordSet) {
        Map badWordMap = new HashMap(keywordSet.size()); // 初始化敏感词容器，减少扩容操作

        for (String keyword : keywordSet) {
            Map nowMap = badWordMap;

            for (int i = 0; i < keyword.length(); i++) {
                Character keyChar = keyword.charAt(i); // 转换成char型
                Object wordMap = nowMap.get(keyChar); // 获取

                if (wordMap != null) { // 如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else { // 不存在则，则构建一个map，同时将isEnd设置为false，因为他不是最后一个
                    Map<String, Boolean> newWordMap = new HashMap<String, Boolean>();
                    newWordMap.put("isEnd", Boolean.FALSE); // 不是最后一个，设置标志位

                    // 添加到集合
                    nowMap.put(keyChar, newWordMap);
                    nowMap = newWordMap;
                }

                // 最后一个
                if (i == keyword.length() - 1) {
                    nowMap.put("isEnd", Boolean.TRUE);
                }
            }
        }

        return badWordMap;
    }

    // ======================================================================================================================== //

}