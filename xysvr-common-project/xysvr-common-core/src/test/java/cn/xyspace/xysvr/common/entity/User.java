package cn.xyspace.xysvr.common.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

public class User extends IdEntity {

    private static final long serialVersionUID = -4050487213551616608L;

    private String username; // 用户名

    public User() {
    }

    public User(String id, String username) {
        super.id = id;
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
