/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.function.api.msg.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

/**
 * 新私信实体类。
 *
 * @author WuQiying(2015年2月26日 下午5:26:29)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class Msg extends IdEntity {

    private static final long serialVersionUID = 2692935553850517986L;

    private String fromUid;// 私信发起者ID（外键）
    private String fromUname;// 私信发起者用户名（登录名，外键）
    private String toUid;// 私信接收者ID（外键）
    private String toUname;// 私信接收者用户名（登录名，外键）
    private String content;// 最新信息内容（非文本是IMAGE|AUDIO|VIDEO，文本就是文字）
    private Integer fromMsgNum;// 私信发起者消息总数（默认1）
    private Integer toMsgNum;// 私信接收者消息总数（默认1）
    private Double longitude; // 私信发起者所在地的经度
    private Double latitude; // 私信发起者所在地的纬度
    private String city;// 私信发起者所在的城市
    private String position; // 私信发起者的详细地理位置
    private Long ctime; // 私信发起时间戳
    @JsonIgnore
    private String clientIp; // 私信发起者的客户端IP
    private Long lastTime; // 私信最后会话时间戳
    private Boolean fromHasNew;// 私信发起者是否有新消息（默认FALSE）
    private Boolean toHasNew;// 私信接收者是否有新消息（默认FALSE）
    @JsonIgnore
    private Boolean fromIsDel; // 私信发起者是否删除该消息（默认FALSE）
    @JsonIgnore
    private Boolean toIsDel; // 私信接收者是否删除该消息（默认FALSE）

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFromMsgNum() {
        return fromMsgNum;
    }

    public void setFromMsgNum(Integer fromMsgNum) {
        this.fromMsgNum = fromMsgNum;
    }

    public Integer getToMsgNum() {
        return toMsgNum;
    }

    public void setToMsgNum(Integer toMsgNum) {
        this.toMsgNum = toMsgNum;
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

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public Boolean getFromHasNew() {
        return fromHasNew;
    }

    public void setFromHasNew(Boolean fromHasNew) {
        this.fromHasNew = fromHasNew;
    }

    public Boolean getToHasNew() {
        return toHasNew;
    }

    public void setToHasNew(Boolean toHasNew) {
        this.toHasNew = toHasNew;
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
