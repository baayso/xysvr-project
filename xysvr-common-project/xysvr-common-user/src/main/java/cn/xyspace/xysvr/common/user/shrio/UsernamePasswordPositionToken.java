/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.user.shrio;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 继承了 {@linkplain UsernamePasswordToken UsernamePasswordToken} 类并扩充登录时的经度和纬度信息的身份验证机制。
 * 
 * @author ChenFangjie
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class UsernamePasswordPositionToken extends UsernamePasswordToken {

    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;

    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    /** 登录时的经度 */
    private Double longitude;

    /** 登录时的纬度 */
    private Double latitude;

    /** 登录时所在的城市 */
    private String city;

    /** 登录时的详细地理位置 */
    private String position;

    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

    public UsernamePasswordPositionToken() {
    }

    public UsernamePasswordPositionToken(final String username, final char[] password) {
        this(username, password, false, null, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final String password) {
        this(username, password != null ? password.toCharArray() : null, false, null, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final char[] password, final String host) {
        this(username, password, false, host, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final String password, final String host) {
        this(username, password != null ? password.toCharArray() : null, false, host, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final char[] password, final boolean rememberMe) {
        this(username, password, rememberMe, null, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final String password, final boolean rememberMe) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, null, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final char[] password, final boolean rememberMe, final String host) {
        this(username, password, rememberMe, host, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final String password, final boolean rememberMe, final String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host, null, null, null, null);
    }

    public UsernamePasswordPositionToken(final String username, final char[] password, final boolean rememberMe, final String host, final Double longitude, final Double latitude, final String city,
            final String position) {
        super(username, password, rememberMe, host);
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.position = position;
    }

    public UsernamePasswordPositionToken(final String username, final String password, final boolean rememberMe, final String host, final Double longitude, final Double latitude, final String city,
            final String position) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host, longitude, latitude, city, position);
    }

    public UsernamePasswordPositionToken(final String username, final char[] password, final String host, final Double longitude, final Double latitude, final String city, final String position) {
        super(username, password, host);
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.position = position;
    }

    public UsernamePasswordPositionToken(final String username, final String password, final String host, final Double longitude, final Double latitude, final String city, final String position) {
        super(username, password, host);
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.position = position;
    }

    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/

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

    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    public void clear() {
        super.clear();
        this.longitude = null;
        this.latitude = null;
        this.city = null;
        this.position = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" - ");
        sb.append(super.getUsername());
        sb.append(", rememberMe=").append(super.isRememberMe());
        if (super.getHost() != null) {
            sb.append(" (").append(super.getHost()).append(")");
        }
        sb.append(", longitude=");
        sb.append(this.longitude);
        sb.append(", latitude=");
        sb.append(this.latitude);
        sb.append(", city=");
        sb.append(this.city);
        sb.append(", position=");
        sb.append(this.position);

        return sb.toString();
    }

}
