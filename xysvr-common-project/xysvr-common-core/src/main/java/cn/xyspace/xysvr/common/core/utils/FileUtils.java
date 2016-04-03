/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件相关工具类。
 * 
 * @author ChenFangjie(2015/1/14 14:49:46)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 * 
 */
public final class FileUtils {

    // public static final String FILE_TYPE = "504b0304 d0cf11e0 25504446 ffd8ffe0 ffd8ffe1 89504e47";

    /** 图片格式 */
    public static final String JPEG_PNG_GIF = "ffd8ffe0 ffd8ffe1 89504e47 47494638";

    // public static final String JPEG = "ffd8ff";

    // public static final String PNG = "89504e47 ";

    // public static final String GIF = "47494638";

    // 让工具类彻底不可以实例化
    private FileUtils() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 获取文件扩展名，返回如 .png 这种模式的文件扩展名。
     * 
     * @param fileName
     *            文件名
     * @return 文件的扩展名，如果没有扩展名将返回零长度字符串
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String getExtensionName(final String fileName) {
        if (null != fileName && !fileName.isEmpty()) {
            int index = fileName.lastIndexOf('.');

            if (-1 < index && (fileName.length() - 1) > index) {
                return fileName.substring(index);
            }
        }

        return "";
    }

    /**
     * 去掉文件扩展名。
     * 
     * @param fileName
     *            文件名
     * @return 去掉扩展名后的文件名
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static String trimExtensionName(final String fileName) {
        if (null != fileName && !fileName.isEmpty()) {
            int index = fileName.lastIndexOf('.');

            if (-1 < index && (fileName.length() - 1) > index) {
                return fileName.substring(0, index);
            }
        }

        return "";
    }

    /**
     * 使用文件的头部信息，也就是魔数(magic number)进行文件类型的验证。
     * 
     * @param file
     *            待验证的文件，需转换成字节数组
     * @param customTypes
     *            期望的文件类型，文件的头部信息，也就是魔数(magic number)
     * @return 如果文件是期望的类型返回true，否则返回false
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public static boolean validateFileType(final byte[] file, final String customTypes) {
        if (null != file) {
            int size = file.length;
            String hex = null;
            StringBuilder contentType = new StringBuilder();

            for (int i = 0; i < size; i++) {
                hex = Integer.toHexString(file[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                contentType.append(hex);
                if (i > 2) {
                    break;
                }
            }

            if (StringUtils.indexOf(customTypes, contentType.toString()) > -1) {
                return true;
            }
        }

        return false;
    }

}
