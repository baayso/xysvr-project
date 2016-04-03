
-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --用户ID，主键
       username             VARCHAR(100)                   NOT NULL                            --用户名（登录名）
);
