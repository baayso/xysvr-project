/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.api.msg.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 新私信内容实体类。
 *
 * @author WuQiying(2015年2月26日 下午5:48:38)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class MsgCnt extends IdEntity {

    private static final long serialVersionUID = -9179133009735531915L;

    private String msgId; // 私信ID（外键）
    private String fromUid; // 私信发送者ID（外键）
    private String fromUname; // 私信发送者用户名（登录名，外键）
    private String toUid; // 私信接收者ID（外键）
    private String toUname; // 私信接收者用户名（登录名，外键）
    private MsgType type;// 私信的类型，0:文字、1:图片、2:语音、3视频
    private String content; // 私信内容（非文本是路径，文本就是文字）
    private Double longitude; // 私信发送者所在地的经度
    private Double latitude; // 私信发送者所在地的纬度
    private String city;// 私信发送者所在的城市
    private String position;// 私信发送者的详细地理位置
    private Long ctime; // 发送私信的时间戳
    @JsonIgnore
    private String clientIp; // 发送私信的客户端IP
    @JsonIgnore
    private Boolean fromIsDel; // 私信发送者是否已删除（默认FALSE）
    @JsonIgnore
    private Boolean toIsDel;// 私信接收者是否已删除（默认FALSE）

    /**
     * 私信类型。0:文字、1:图片、2:语音、3视频
     *
     * @author WuQiying(2015年2月26日 下午8:03:53)
     *
     * @since 1.0.0
     * 
     * @version 1.0.0
     *
     */
    public static enum MsgType {
        /** 文字私信 **/
        TEXT("文字"), /** 图片私信 **/
        IMAGE("图片"), /** 语音私信 **/
        AUDIO("语音"), /** 视频私信 **/
        VIDEO("视频");

        private String desc;

        private MsgType(String desc) {
            this.desc = desc;
        }

        /** 获取描述 **/
        public String getDesc() {
            return this.desc;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getFromUname() {
        return fromUname;
    }

    public void setFromUname(String fromUname) {
        this.fromUname = fromUname;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public String getToUname() {
        return toUname;
    }

    public void setToUname(String toUname) {
        this.toUname = toUname;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Boolean getFromIsDel() {
        return fromIsDel;
    }

    public void setFromIsDel(Boolean fromIsDel) {
        this.fromIsDel = fromIsDel;
    }

    public Boolean getToIsDel() {
        return toIsDel;
    }

    public void setToIsDel(Boolean toIsDel) {
        this.toIsDel = toIsDel;
    }

}
