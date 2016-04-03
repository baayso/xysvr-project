-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --用户ID，主键
       username             VARCHAR(100)                   NOT NULL                ,           --用户名（登录名）
       nickname             VARCHAR(100)                   NOT NULL                ,           --昵称
       name                 VARCHAR(100)                                           ,           --用户的真实姓名
       password             VARCHAR(100)                   NOT NULL                ,           --密码
       salt                 VARCHAR(100)                   NOT NULL                ,           --密码盐（密码作料）
       gender               SMALLINT                       NOT NULL DEFAULT 0      ,           --用户性别，0:保密、1:男、2:女
       birthday             VARCHAR(10)                                            ,           --用户出生日期
       telephone            VARCHAR(20)                                            ,           --用户电话
       email                VARCHAR(100)                                           ,           --用户电子邮件
       inviter              VARCHAR(100)                                           ,           --邀请人
       identifier           VARCHAR(36)                                            ,           --注册标识符
       address              VARCHAR(255)                                           ,           --用户地址
       is_locked            BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已锁定（默认FALSE）
       is_disabled          BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已禁用（默认FALSE）
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已通过审核（默认FALSE）
       is_activated         BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已激活（默认FALSE）
       reg_ip               VARCHAR(64)                                            ,           --注册IP
       reg_longitude        NUMERIC(13, 10)                NOT NULL                ,           --用户注册时的经度
       reg_latitude         NUMERIC(13, 10)                NOT NULL                ,           --用户注册时的纬度
       reg_city             VARCHAR(100)                   NOT NULL                ,           --用户注册时所在的城市
       reg_position         VARCHAR(255)                                           ,           --用户注册时的详细地理位置
       create_time          BIGINT                         NOT NULL                ,           --用户创建（注册）时间戳
       last_login_time      BIGINT                                                 ,           --用户最后登录时间戳
       last_microblog_id    VARCHAR(32)                                            ,           --用户最后发布的微博ID
       last_microblog_time  BIGINT                                                 ,           --用户最后发布微博的时间戳
       last_activity_id     VARCHAR(32)                                            ,           --用户最后发起的活动ID
       last_activity_time   BIGINT                                                 ,           --用户最后发起活动的时间戳
       intro                VARCHAR(255)                                           ,           --用户介绍
       --
       icon_path            VARCHAR(255)                                           ,           --用户头像保存路径
       size                 BIGINT                                                 ,           --用户头像大小
       mime                 VARCHAR(64)                                            ,           --用户头像格式
       extname              VARCHAR(20)                                            ,           --用户头像扩展名
       hash                 VARCHAR(64)                                            ,           --用户头像哈希值
       width                INTEGER                                                ,           --用户头像宽度
       height               INTEGER                                                ,           --用户头像高度
       uptime               BIGINT                                                 ,           --用户头像上传时间戳
       --
       role_ids             VARCHAR                                                ,           --角色列表
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --用户是否已删除（默认FALSE）
);
ALTER TABLE "t_user" ADD CONSTRAINT "t_user_username_unique" UNIQUE ("username");
ALTER TABLE "t_user" ADD CONSTRAINT "t_user_email_unique" UNIQUE ("email");
-- CREATE INDEX "t_user_create_time_index" ON "t_user"("create_time");
-- CREATE INDEX "t_user_reg_longitude_reg_latitude_index" ON "t_user"("reg_longitude", "reg_latitude");
-- CREATE INDEX "t_user_reg_city_index" ON "t_user"("reg_city");


-- 用户财富表
DROP TABLE IF EXISTS t_user_asset;
CREATE TABLE t_user_asset(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --用户ID（主键，和用户表主键对应）
       bonus_point          INTEGER                        NOT NULL DEFAULT 0      ,           --用户积分
       money                INTEGER                        NOT NULL DEFAULT 0      ,           --用户基本金币
       lock_money           INTEGER                        NOT NULL DEFAULT 0      ,           --用户锁定金币
       earn_money           INTEGER                        NOT NULL DEFAULT 0      ,           --用户所赚得的金币
       rmoney               NUMERIC(15, 6)                 NOT NULL DEFAULT 0      ,           --用户真实钱财
       lock_rmoney          NUMERIC(15, 6)                 NOT NULL DEFAULT 0      ,           --用户锁定的真实钱财
       lucky                INTEGER                        NOT NULL DEFAULT 0      ,           --用户幸运指数
       hitface              INTEGER                        NOT NULL DEFAULT 0      ,           --用户打脸指数
       hittedface           INTEGER                        NOT NULL DEFAULT 0      ,           --用户被打脸指数
       merit                INTEGER                        NOT NULL DEFAULT 0      ,           --用户功德
       level                SMALLINT                       NOT NULL DEFAULT 1                  --用户等级
);

INSERT INTO t_user(id, username, nickname, password, salt, is_audited, is_activated, reg_ip, reg_longitude, reg_latitude, reg_city, create_time) VALUES('549cbb9deb2911998b2582f3', 'super', '超级管理员', '15bab70a56a0328dfb385f07d84aa39255e90f94', '643068642c33351b', TRUE, TRUE, '127.0.0.1', 999.0, 999.0, '未知', extract(epoch FROM now()) * 1000);
INSERT INTO t_user_asset(id, money) VALUES((SELECT u.id FROM t_user AS u WHERE u.username='super'), 100);

