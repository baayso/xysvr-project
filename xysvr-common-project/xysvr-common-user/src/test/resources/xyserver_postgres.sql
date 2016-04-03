
-- 权限表
DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --权限ID，主键
       name                 VARCHAR(100)                   NOT NULL                ,           --权限名称
       type                 VARCHAR(50)                                            ,           --权限类型
       url                  VARCHAR(200)                                           ,           --权限对应的url
       parent_id            VARCHAR(36)                                            ,           --父权限ID
       parent_ids           VARCHAR                                                ,           --父权限ID列表
       permission_str       VARCHAR(100)                   NOT NULL                ,           --权限字符串
       is_available         BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否可用
       descriptions         VARCHAR(255)                                                       --权限描述
);


-- 角色表
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --角色ID，主键
       name                 VARCHAR(100)                   NOT NULL                ,           --角色名称
       permission_ids       VARCHAR                                                ,           --授于的权限列表
       is_available         BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否可用
       descriptions         VARCHAR(255)                                                       --角色描述
);

-- INSERT INTO t_role(id, name) VALUES(replace(uuid(),'-',''), '管理员');
-- INSERT INTO t_role(id, name) VALUES(replace(uuid(),'-',''), '巡逻员');
-- INSERT INTO t_role(id, name) VALUES(replace(uuid(),'-',''), '正常用户');
-- INSERT INTO t_role(id, name) VALUES(replace(uuid(),'-',''), '禁言用户');


-- 用户与角色的关联表
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role(
       id                   VARCHAR(36)                    PRIMARY KEY             ,            --用户与角色的关联表ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,            --用户ID（外键）
       role_id              VARCHAR(36)                    NOT NULL                             --角色ID（外键）
);


-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --用户ID，主键
       username             VARCHAR(100)                   NOT NULL                ,           --用户名（登录名）
       nickname             VARCHAR(100)                   NOT NULL                ,           --昵称
       name                 VARCHAR(100)                                           ,           --用户的真实姓名
       password             VARCHAR(100)                   NOT NULL                ,           --密码
       salt                 VARCHAR(100)                   NOT NULL                ,           --密码盐（密码作料）
       gender               SMALLINT                       NOT NULL DEFAULT 0      ,           --用户性别，0:保密、1:男、2:女
       birthday             VARCHAR(10)                                            ,           --用户出生日期
       telephone            VARCHAR(17)                                            ,           --用户电话
       email                VARCHAR(100)                                           ,           --用户电子邮件
       address              VARCHAR(255)                                           ,           --用户地址
       is_locked            BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已锁定（默认FALSE）
       is_disabled          BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已禁用（默认FALSE）
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已通过审核（默认FALSE）
       is_activated         BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否已激活（默认FALSE）
       -- is_admin             BOOLEAN                        NOT NULL DEFAULT FALSE  ,            --用户是否为管理员（默认FALSE）
       reg_ip               VARCHAR(64)                                            ,           --注册IP
       reg_longitude        NUMERIC(13, 10)                NOT NULL                ,           --用户注册时的经度
       reg_latitude         NUMERIC(13, 10)                NOT NULL                ,           --用户注册时的纬度
       create_time          BIGINT                         NOT NULL                ,           --用户创建（注册）时间戳
       last_login_time      BIGINT                                                 ,           --用户最后登录时间戳
       last_microblog_id    VARCHAR(36)                                            ,           --用户最后发布的微博ID
       last_microblog_time  BIGINT                                                 ,           --用户最后发布微博的时间戳
       last_activity_id     VARCHAR(36)                                            ,           --用户最后发起的活动ID
       last_activity_time   BIGINT                                                 ,           --用户最后发起活动的时间戳
       intro                VARCHAR(255)                                           ,           --用户介绍
       icon_path            VARCHAR(255)                                           ,           --用户头像保存路径
       role_ids             VARCHAR                                                ,           --角色列表
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --用户是否已删除（默认FALSE）
       -- role_id              VARCHAR(36)                    NOT NULL                            --角色ID（外键）
);
ALTER TABLE "t_user" ADD CONSTRAINT "t_user_username_unique" UNIQUE ("username");
ALTER TABLE "t_user" ADD CONSTRAINT "t_user_email_unique" UNIQUE ("email");
CREATE INDEX "t_user_create_time_index" ON "t_user"("create_time");
CREATE INDEX "t_user_reg_longitude_reg_latitude_index" ON "t_user"("reg_longitude", "reg_latitude");


-- 用户财富表
DROP TABLE IF EXISTS t_user_asset;
CREATE TABLE t_user_asset(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --用户ID（主键，和用户表主键对应）
       bonus_point          INTEGER                        NOT NULL DEFAULT 0      ,           --用户积分
       money                INTEGER                        NOT NULL DEFAULT 0      ,           --用户金币
       level                SMALLINT                       NOT NULL DEFAULT 1                  --用户等级
);

-- INSERT INTO t_user VALUES(0, 'super', '超级管理员', MD5('supersuperlIFQaEFi68'), 'lIFQaEFi68', 0, '0000-00-00', '00000000000', 'super@super.com', '', FALSE, FALSE, TRUE, TRUE, '127.0.0.1', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', NULL, FALSE);
INSERT INTO t_user(id, username, nickname, password, salt, is_audited, is_activated, reg_ip, reg_longitude, reg_latitude, create_time) VALUES('6cba07e9-7d11-11e4-9cd1-00ffc365a8bf', 'super', '超级管理员', '0d14c08459da63c947aa1d92aa4bce13', 'lIFQaEFi68', TRUE, TRUE, '127.0.0.1', 0.0, 0.0, 0);
INSERT INTO t_user_asset(id, money) VALUES((SELECT u.id FROM t_user AS u WHERE u.username='super'), 100);


-- 用户登录日志表
DROP TABLE IF EXISTS t_user_login_log;
CREATE TABLE t_user_login_log(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --用户登录日志ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --登录用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --登录用户的用户名（登录名，外键）
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --用户登录时的经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --用户登录时的纬度
       login_ip             VARCHAR(64)                                            ,           --用户登录IP
       login_time           BIGINT                         NOT NULL                            --用户登录时间戳
);
CREATE INDEX "t_user_login_log_user_id_index" ON "t_user_login_log"("user_id");
CREATE INDEX "t_user_login_log_longitude_latitude_index" ON "t_user_login_log"("longitude", "latitude");


-- 活动类型表
DROP TABLE IF EXISTS t_activity_type;
CREATE TABLE t_activity_type(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --活动类型ID，主键
       name                 VARCHAR(100)                   NOT NULL                ,           --活动类型名称
       descriptions         VARCHAR(255)                                           ,           --活动类型描述
       parent_id            VARCHAR(36)                                                        --上级活动类型ID（外键，自关联）
);

INSERT INTO t_activity_type(id, name, parent_id) VALUES('e09bd7f7-7c4c-11e4-952f-7429af3f5960', '猜拳', NULL);
INSERT INTO t_activity_type(id, name, parent_id) VALUES('e09bf188-7c4c-11e4-952f-7429af3f5960', '猜我', NULL);
INSERT INTO t_activity_type(id, name, parent_id) VALUES('e09dd68f-7c4c-11e4-952f-7429af3f5960', '猜体重', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));
INSERT INTO t_activity_type(id, name, parent_id) VALUES('e09df3e5-7c4c-11e4-952f-7429af3f5960', '猜身高', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));
INSERT INTO t_activity_type(id, name, parent_id) VALUES('e09e0a59-7c4c-11e4-952f-7429af3f5960', '猜性别', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));
INSERT INTO t_activity_type(id, name, parent_id) VALUES('e09e1aa0-7c4c-11e4-952f-7429af3f5960', '猜胸围', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));


-- 活动表
DROP TABLE IF EXISTS t_activity;
CREATE TABLE t_activity(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --活动ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --活动发起者ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --活动发起者用户名（登录名，外键）
       icon_path            VARCHAR(255)                                           ,           --活动发起者用户头像保存路径（外键）
       activity_type_id     VARCHAR(36)                    NOT NULL                ,           --活动类型ID（外键）
       activity_type_name   VARCHAR(100)                   NOT NULL                ,           --活动类型名称（外键）
       correct_answer       VARCHAR(20)                    NOT NULL                ,           --活动正确答案
       answer_one           VARCHAR(20)                    NOT NULL                ,           --活动显示答案1
       answer_two           VARCHAR(20)                    NOT NULL                ,           --活动显示答案2
       answer_three         VARCHAR(20)                    NOT NULL                ,           --活动显示答案3
       member_num_limit     SMALLINT                       NOT NULL DEFAULT 1      ,           --活动允许参与的人数
       already_member_num   SMALLINT                       NOT NULL DEFAULT 0      ,           --活动实际参与的人数
       time_limit           SMALLINT                       NOT NULL DEFAULT 0      ,           --活动的时间限制，单位为分钟
       pre_money            INTEGER                        NOT NULL DEFAULT 0      ,           --发起活动的预扣金币
       return_money         INTEGER                        NOT NULL DEFAULT 0      ,           --活动结束后退还的金币
       earn_rewards         INTEGER                        NOT NULL DEFAULT 0      ,           --活动结束后获得的奖励
       reward               INTEGER                        NOT NULL DEFAULT 0      ,           --活动的奖励，为0表示没有奖励
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --活动发起地的经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --活动发起地的纬度
       client_ip            VARCHAR(64)                                            ,           --发起活动的客户端IP
       create_time          BIGINT                         NOT NULL                ,           --活动发起时间戳
       is_ended             BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --活动是否已结束（默认FALSE）
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --活动是否已删除（默认FALSE）
);
CREATE INDEX "t_activity_user_id_index" ON "t_activity"("user_id");
CREATE INDEX "t_activity_longitude_latitude_index" ON "t_activity"("longitude", "latitude");
CREATE INDEX "t_activity_create_time_index" ON "t_activity"("create_time");
CREATE INDEX "t_activity_is_ended_index" ON "t_activity"("is_ended");
CREATE INDEX "t_activity_is_deleted_index" ON "t_activity"("is_deleted");


-- 活动成员（参与者）表
DROP TABLE IF EXISTS t_activity_member;
CREATE TABLE t_activity_member(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --活动成员表ID，主键
       activity_id          VARCHAR(36)                    NOT NULL                ,           --活动ID（外键）
       user_id              VARCHAR(36)                    NOT NULL                ,           --活动参与者ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --活动参与者用户名（登录名，外键）
       icon_path            VARCHAR(255)                                           ,           --活动参与者用户头像保存路径（外键）
       answer               VARCHAR(20)                    NOT NULL                ,           --参与者的回答
       is_win               SMALLINT                       NOT NULL DEFAULT 0      ,           --是否获胜，平局返回 0，参与者获胜返回大于 0 的值，参与者失败返回小于 0 的值（默认0）
       pre_money            INTEGER                        NOT NULL DEFAULT 0      ,           --参与活动的预扣金币
       return_money         INTEGER                        NOT NULL DEFAULT 0      ,           --活动结束后退还的金币
       earn_rewards         INTEGER                        NOT NULL DEFAULT 0      ,           --参与者获得的奖励
       longitude            NUMERIC(13, 10)                                        ,           --参与活动时的经度
       latitude             NUMERIC(13, 10)                                        ,           --参与活动时的纬度
       client_ip            VARCHAR(64)                                            ,           --参与活动的客户端IP
       create_time          BIGINT                         NOT NULL                ,           --参与活动时间戳
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_activity_member_activity_id_index" ON "t_activity_member"("activity_id");
CREATE INDEX "t_activity_member_user_id_index" ON "t_activity_member"("user_id");
CREATE INDEX "t_activity_member_longitude_latitude_index" ON "t_activity_member"("longitude", "latitude");
CREATE INDEX "t_activity_member_create_time_index" ON "t_activity_member"("create_time");
CREATE INDEX "t_activity_member_is_deleted_index" ON "t_activity_member"("is_deleted");


-- 活动金币变动记录表
DROP TABLE IF EXISTS t_activity_money_change_record;
CREATE TABLE t_activity_money_change_record(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --活动金币变动记录ID，主键
       activity_id          VARCHAR(36)                    NOT NULL                ,           --所属活动ID（外键）
       user_id              VARCHAR(36)                    NOT NULL                ,           --金币变动者ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --金币变动者用户名（登录名，外键）
       type                 SMALLINT                       NOT NULL                ,           --活动金币变动类型
       before_money         INTEGER                        NOT NULL                ,           --变动之前金币
       money                INTEGER                        NOT NULL                ,           --变动金币
       after_money          INTEGER                        NOT NULL                ,           --变动之后金币
       descriptions         VARCHAR(100)                                           ,           --变动描述
       create_time          BIGINT                         NOT NULL                ,           --金币变动时间戳
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_activity_money_change_record_activity_id_index" ON "t_activity_money_change_record"("activity_id");
CREATE INDEX "t_activity_money_change_record_user_id_index" ON "t_activity_money_change_record"("user_id");


-- 私信表
DROP TABLE IF EXISTS t_message;
CREATE TABLE t_message(
       id                         VARCHAR(36)              PRIMARY KEY             ,           --私信ID，主键
       from_uid                   VARCHAR(36)              NOT NULL                ,           --私信发起者ID（外键）
       from_uname                 VARCHAR(100)             NOT NULL                ,           --私信发起者用户名（登录名，外键）
       from_uicon                 VARCHAR(255)                                     ,           --私信发起者用户头像保存路径（外键）
       message_type               SMALLINT                 NOT NULL DEFAULT 1      ,           --私信类别，1:一对一; 2:多人
       title                      VARCHAR(255)                                     ,           --标题
       member_num                 SMALLINT                 NOT NULL DEFAULT 2      ,           --参与者数量
       member_ids                 VARCHAR                                          ,           --所有参与者的ID，以逗号分隔且正序排序
       longitude                  NUMERIC(13, 10)          NOT NULL                ,           --私信发起者所在地的经度
       latitude                   NUMERIC(13, 10)          NOT NULL                ,           --私信发起者所在地的纬度
       create_time                BIGINT                   NOT NULL                ,           --私信发起时间戳
       client_ip                  VARCHAR(64)                                      ,           --私信发起者的客户端IP
       msg_last_conversation_time BIGINT                   NOT NULL                ,           --私信最后会话时间戳
       is_deleted                 BOOLEAN                  NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_message_from_uid_index" ON "t_message"("from_uid");
-- CREATE INDEX "t_message_longitude_latitude_index" ON "t_message"("longitude", "latitude");
CREATE INDEX "t_message_create_time_index" ON "t_message"("create_time");
CREATE INDEX "t_message_is_deleted_index" ON "t_message"("is_deleted");


-- 私信会话内容表
DROP TABLE IF EXISTS t_message_content;
CREATE TABLE t_message_content(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --私信内对话ID，主键
       message_id           VARCHAR(36)                    NOT NULL                ,           --私信ID（外键）
       from_uid             VARCHAR(36)                    NOT NULL                ,           --私信发送者ID（外键）
       from_uname           VARCHAR(100)                   NOT NULL                ,           --私信发送者用户名（登录名，外键）
       from_uicon           VARCHAR(255)                                           ,           --私信发送者用户头像保存路径（外键）
       content              VARCHAR(300)                                           ,           --私信内容
       attachment_ids       VARCHAR                                                ,           --附件ID列表，用逗号分隔
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --私信发送者所在地的经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --私信发送者所在地的纬度
       create_time          BIGINT                         NOT NULL                ,           --发送私信的时间戳
       client_ip            VARCHAR(64)                                            ,           --发送私信的客户端IP
       is_read              BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否已读（默认FALSE）
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_message_content_message_id_index" ON "t_message_content"("message_id");
CREATE INDEX "t_message_content_from_uid_index" ON "t_message_content"("from_uid");
CREATE INDEX "t_message_content_is_read_index" ON "t_message_content"("is_read");
CREATE INDEX "t_message_content_is_deleted_index" ON "t_message_content"("is_deleted");


-- 私信会话成员表
DROP TABLE IF EXISTS t_message_member;
CREATE TABLE t_message_member(
       id                        VARCHAR(36)               PRIMARY KEY             ,           --私信会话成员表ID，主键
       message_id                VARCHAR(36)               NOT NULL                ,           --私信ID（外键）
       member_uid                VARCHAR(36)               NOT NULL                ,           --私信参与者ID（外键）
       member_uname              VARCHAR(100)              NOT NULL                ,           --私信参与者用户名（登录名，外键）
       member_uicon              VARCHAR(255)                                      ,           --私信参与者用户头像保存路径（外键）
       has_new                   BOOLEAN                   NOT NULL DEFAULT FALSE  ,           --是否有未读消息（默认FALSE）
       message_num               SMALLINT                  NOT NULL DEFAULT 1      ,           --消息总数
       last_conversation_time    BIGINT                    NOT NULL                ,           --该参与者最后会话时间戳
       is_deleted                BOOLEAN                   NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_message_member_message_id_index" ON "t_message_member"("message_id");
CREATE INDEX "t_message_member_member_uid_index" ON "t_message_member"("member_uid");
CREATE INDEX "t_message_member_is_deleted_index" ON "t_message_member"("is_deleted");


-- 系统通知表
DROP TABLE IF EXISTS t_notify_message;
CREATE TABLE t_notify_message(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --系统通知表ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --用户名（登录名，外键）
       type                 VARCHAR(50)                                            ,           --系统通知类型
       content              VARCHAR(255)                   NOT NULL                ,           --系统通知内容
       create_time          BIGINT                         NOT NULL                ,           --添加时间戳
       is_read              BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已读（默认FALSE）
);
CREATE INDEX "t_notify_message_user_id_index" ON "t_notify_message"("user_id");
CREATE INDEX "t_notify_message_create_time_index" ON "t_notify_message"("create_time");
CREATE INDEX "t_notify_message_is_read_index" ON "t_notify_message"("is_read");


-- 签到记录表
DROP TABLE IF EXISTS t_signin_info;
CREATE TABLE t_signin_info(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --签到记录表ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --签到用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --签到用户的用户名（登录名，外键）
       con_num              INTEGER                        NOT NULL DEFAULT 1      ,           --连续签到次数
       total_num            INTEGER                        NOT NULL DEFAULT 1      ,           --总签到次数
       signin_time          BIGINT                         NOT NULL                            --签到时间戳
);
CREATE INDEX "t_signin_info_user_id_index" ON "t_signin_info"("user_id");


-- 微博表
DROP TABLE IF EXISTS t_microblog;
CREATE TABLE t_microblog(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --微博ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --发布微博的用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --发布微博的用户的用户名（登录名，外键）
       icon_path            VARCHAR(255)                                           ,           --发布微博的用户的头像保存路径
       content              VARCHAR(300)                   NOT NULL                ,           --纯微博内容
       attachment_ids       VARCHAR                                                ,           --微博附件ID列表，用逗号分隔
       comment_count        INTEGER                        NOT NULL DEFAULT 0      ,           --评论数
       repost_count         INTEGER                        NOT NULL DEFAULT 0      ,           --分享数
       endorse_count        INTEGER                        NOT NULL DEFAULT 0      ,           --点赞数
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否已通过审核（默认FALSE）
       is_repost            BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否转发（默认FALSE）
       client_ip            VARCHAR(64)                                            ,           --发布微博的客户端IP
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --发布微博时的经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --发布微博时的纬度
       create_time          BIGINT                         NOT NULL                ,           --发布时间戳
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_microblog_user_id_index" ON "t_microblog"("user_id");
CREATE INDEX "t_microblog_is_audited_index" ON "t_microblog"("is_audited");
CREATE INDEX "t_microblog_longitude_latitude_index" ON "t_microblog"("longitude", "latitude");
CREATE INDEX "t_microblog_create_time_index" ON "t_microblog"("create_time");
CREATE INDEX "t_microblog_is_deleted_index" ON "t_microblog"("is_deleted");


-- 微博点赞记录表
DROP TABLE IF EXISTS t_microblog_endorse;
CREATE TABLE t_microblog_endorse(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --微博点赞记录ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --点赞用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --点赞用户的用户名（登录名）
       icon_path            VARCHAR(255)                                           ,           --点赞的用户头像保存路径
       microblog_id         VARCHAR(36)                    NOT NULL                ,           --被点赞的微博ID（外键）
       client_ip            VARCHAR(64)                                            ,           --客户端IP
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --占赞微博时的经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --占赞微博时的纬度
       create_time          BIGINT                         NOT NULL                            --点赞时间戳
);
CREATE INDEX "t_microblog_endorse_user_id_index" ON "t_microblog_endorse"("user_id");


-- 微博评论表
DROP TABLE IF EXISTS t_microblog_comment;
CREATE TABLE t_microblog_comment(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --微博评论ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --评论者用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --评论者的用户名（登录名，外键）
       icon_path            VARCHAR(255)                                           ,           --评论者的用户头像保存路径
       microblog_id         VARCHAR(36)                                            ,           --评论所属的微博ID（外键）
       attachment_ids       VARCHAR                                                ,           --评论附件ID列表，用逗号分隔
       content              VARCHAR(300)                   NOT NULL                ,           --评论内容
       to_comment_id        VARCHAR(36)                                            ,           --被回复的评论ID（外键）
       to_user_id           VARCHAR(36)                                            ,           --被回复评论的作者的ID（外键）
       client_ip            VARCHAR(64)                                            ,           --发表评论的客户端IP
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否已通过审核（默认FALSE）
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --评论微博时的经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --评论微博时的纬度
       create_time          BIGINT                         NOT NULL                ,           --评论时间戳
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_microblog_comment_user_id_index" ON "t_microblog_comment"("user_id");
CREATE INDEX "t_microblog_comment_microblog_id_index" ON "t_microblog_comment"("microblog_id");
CREATE INDEX "t_microblog_comment_create_time_index" ON "t_microblog_comment"("create_time");
CREATE INDEX "t_microblog_comment_is_deleted_index" ON "t_microblog_comment"("is_deleted");


-- 附件表
DROP TABLE IF EXISTS t_attachment;
CREATE TABLE t_attachment(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --附件ID，主键
       name                 VARCHAR(255)                                           ,           --附件名称
       user_id              VARCHAR(36)                    NOT NULL                ,           --用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --用户名（登录名，外键）
       attachment_type      VARCHAR(20)                                            ,           --附件所属类型
       mime                 VARCHAR(64)                                            ,           --附件格式
       size                 BIGINT                         NOT NULL                ,           --附件大小
       extension_name       VARCHAR(20)                                            ,           --附件扩展名
       hashcode             VARCHAR(32)                                            ,           --附件哈希值
       save_path            VARCHAR(255)                   NOT NULL                ,           --附件保存路径
       original_name        VARCHAR(255)                   NOT NULL                ,           --附件原名称
       save_name            VARCHAR(255)                   NOT NULL                ,           --附件保存名称
       is_private           SMALLINT                       NOT NULL DEFAULT 0      ,           --附件是否为私有（即对其他人不可见），0:否, 1：是
       -- width                INT                                     DEFAULT 0      ,           --图片宽度
       -- height               INT                                     DEFAULT 0      ,           --图片高度
       create_time          BIGINT                         NOT NULL                ,           --附件上传时间戳
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);
CREATE INDEX "t_attachment_user_id_index" ON "t_attachment"("user_id");
CREATE INDEX "t_attachment_create_time_index" ON "t_attachment"("create_time");
CREATE INDEX "t_attachment_is_deleted_index" ON "t_attachment"("is_deleted");


-- 用户使用日志表
DROP TABLE IF EXISTS t_user_use_log;
CREATE TABLE t_user_use_log(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --日志ID，主键
       user_id              VARCHAR(36)                    NOT NULL                ,           --用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --用户名（登录名，外键）
       operation            VARCHAR(64)                    NOT NULL                ,           --用户的操作
       -- click_object
       longitude            NUMERIC(13, 10)                NOT NULL                ,           --用户的操作时的所在地经度
       latitude             NUMERIC(13, 10)                NOT NULL                ,           --用户的操作时的所在地纬度
       client_ip            VARCHAR(64)                                            ,           --客户端IP
       client_info          VARCHAR(255)                                           ,           --客户端信息
       create_time          BIGINT                         NOT NULL                            --记录日志时的时间戳
);
CREATE INDEX "t_user_use_log_user_id_index" ON "t_user_use_log"("user_id");


-- 胡言乱语类型表
DROP TABLE IF EXISTS t_nonsense_type;
CREATE TABLE t_nonsense_type(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --胡言乱语类型ID，主键
       name                 VARCHAR(100)                   NOT NULL                ,           --胡言乱语类型名称
       descriptions         VARCHAR(255)                                           ,           --胡言乱语类型描述
       parent_id            VARCHAR(36)                                                        --上级胡言乱语类型ID（外键，自关联）
);

INSERT INTO t_nonsense_type(id, name, parent_id) VALUES('4459204b-41bd-4a8e-a0e8-0585750cc557', '登录', NULL);
INSERT INTO t_nonsense_type(id, name, parent_id) VALUES('047bf640-37ac-4d79-b647-93508a2542fd', '注册', NULL);


-- 胡言乱语内容表
DROP TABLE IF EXISTS t_nonsense;
CREATE TABLE t_nonsense(
       id                   VARCHAR(36)                    PRIMARY KEY             ,           --胡言乱语类型ID，主键
       nonsense_type_id     VARCHAR(36)                    NOT NULL                ,           --胡言乱语类型ID（外键）
       nonsense_type_name   VARCHAR(100)                   NOT NULL                ,           --胡言乱语类型名称（外键）
       content              TEXT                           NOT NULL                ,           --胡言乱语内容
       descriptions         VARCHAR(255)                                           ,           --胡言乱语类型描述
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --是否已删除（默认FALSE）
);


-- 提交事务
COMMIT;