/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.entity;

/**
 * 附件类。
 *
 * @author CaoZhongsheng(2015/1/31/ 12:05:10)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
public class Attachment {

    private String path; // 附件保存路径
    private String mime; // 附件格式，不返回给客户端
    private Long size; // 附件大小
    private String hash; // 附件哈希值
    private String oname; // 附件原名称，不返回给客户端
    private Integer width; // 图片宽度，若附件不是图片则为NULL
    private Integer height; // 图片高度，若附件不是图片则为NULL
    private String type; // 附件类型

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
