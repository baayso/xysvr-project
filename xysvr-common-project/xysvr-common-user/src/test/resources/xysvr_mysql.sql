-- CREATE DATABASE xysvr;

USE xysvr;


-- 权限表
DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '权限ID，主键',
       name                 VARCHAR(100)                   NOT NULL                            COMMENT '权限名称',
       `type`               VARCHAR(50)                                                        COMMENT '权限类型',
       url                  VARCHAR(200)                                                       COMMENT '权限对应的url',
       parent_id            CHAR(36)                                                           COMMENT '父权限ID',
       parent_ids           VARCHAR(255)                                                       COMMENT '父权限ID列表',
       permission_str       VARCHAR(100)                   NOT NULL                            COMMENT '权限字符串',
       is_available         BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否可用',
       descriptions         VARCHAR(255)                                                       COMMENT '权限描述'
)COMMENT='权限表';


-- 角色表
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '角色ID，主键',
       name                 VARCHAR(100)                   NOT NULL                            COMMENT '角色名称',
       permission_ids       VARCHAR(200)                                                       COMMENT '授于的权限列表',
       is_available         BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否可用',
       descriptions         VARCHAR(255)                                                       COMMENT '角色描述'
)COMMENT='角色表';

-- INSERT INTO t_role(name) VALUES('管理员');
-- INSERT INTO t_role(name) VALUES('巡逻员');
-- INSERT INTO t_role(name) VALUES('正常用户');
-- INSERT INTO t_role(name) VALUES('禁言用户');


-- 用户与角色的关联表
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '用户与角色的关联表ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '用户ID（外键）',
       role_id              CHAR(36)                       NOT NULL                            COMMENT '角色ID（外键）'
)COMMENT='用户与角色的关联表';


-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '用户ID，主键',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '用户名（登录名）',
       nickname             VARCHAR(100)                   NOT NULL                            COMMENT '昵称',
       name                 VARCHAR(100)                                                       COMMENT '用户的真实姓名',
       password             VARCHAR(100)                   NOT NULL                            COMMENT '密码',
       salt                 VARCHAR(100)                   NOT NULL                            COMMENT '密码盐（密码作料）',
       gender               TINYINT            UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '用户性别，0:保密、1:男、2:女',
       birthday             VARCHAR(10)                                                        COMMENT '用户出生日期',
       telephone            VARCHAR(17)                                                        COMMENT '用户电话',
       email                VARCHAR(100)                                                       COMMENT '用户电子邮件',
       address              VARCHAR(255)                                                       COMMENT '用户地址',
       is_locked            BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '用户是否已锁定（默认FALSE）',
       is_disabled          BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '用户是否已禁用（默认FALSE）',
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '用户是否已通过审核（默认FALSE）',
       is_activated         BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '用户是否已激活（默认FALSE）',
       -- is_admin             BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '用户是否为管理员（默认FALSE）',
       reg_ip               VARCHAR(64)                                                        COMMENT '注册IP',
       reg_longitude        DOUBLE                         NOT NULL                            COMMENT '用户注册时的经度',
       reg_latitude         DOUBLE                         NOT NULL                            COMMENT '用户注册时的纬度',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '用户创建（注册）时间戳',
       last_login_time      BIGINT             UNSIGNED                                        COMMENT '用户最后登录时间戳',
       last_microblog_id    CHAR(36)                                                           COMMENT '用户最后发布的微博ID',
       last_microblog_time  BIGINT             UNSIGNED                                        COMMENT '用户最后发布微博的时间戳',
       last_activity_id     CHAR(36)                                                           COMMENT '用户最后发起的活动ID',
       last_activity_time   BIGINT             UNSIGNED                                        COMMENT '用户最后发起活动的时间戳',
       intro                VARCHAR(255)                                                       COMMENT '用户介绍',
       -- bonus_point          INT                            NOT NULL DEFAULT 0                  COMMENT '用户积分',
       -- money                INT                            NOT NULL DEFAULT 0                  COMMENT '用户金币',
       -- level                TINYINT            UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '用户等级',
       icon_path            VARCHAR(255)                                                       COMMENT '用户头像保存路径',
       role_ids             VARCHAR(100)                                                       COMMENT '角色列表',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '用户是否已删除（默认FALSE）'
       -- role_id              INT                UNSIGNED    NOT NULL                            COMMENT '角色ID（外键）'
       -- constraint role_user_fk foreign key(roleId) references t_role(id)
)COMMENT='用户表';


-- 用户资产表
DROP TABLE IF EXISTS t_user_asset;
CREATE TABLE t_user_asset(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '用户ID（主键，和用户名主键对应）',
       bonus_point          INT                            NOT NULL DEFAULT 0                  COMMENT '用户积分',
       money                INT                            NOT NULL DEFAULT 0                  COMMENT '用户金币',
       level                TINYINT            UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '用户等级'
)COMMENT='用户资产表';

-- INSERT INTO t_user VALUES(0, 'admin', '超级管理员', MD5('admin'), '12345', 0, '0000-00-00', '00000000000', 'admin@admin.com', '', 0, 0, 1, 1, '127.0.0.1', 0, 0, 0, 0, '', 0, 0, 0, '', (SELECT r.id FROM role AS r WHERE r.name='管理员'));
-- INSERT INTO t_user VALUES(0, 'super', '超级管理员', MD5('supersuperlIFQaEFi68'), 'lIFQaEFi68', 0, '0000-00-00', '00000000000', 'super@super.com', '', FALSE, FALSE, TRUE, TRUE, '127.0.0.1', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', NULL, FALSE);
INSERT INTO t_user(id, username, nickname, password, salt, is_audited, is_activated, reg_ip, reg_longitude, reg_latitude, create_time) VALUES(uuid(), 'super', '超级管理员', MD5('supersuperlIFQaEFi68'), 'lIFQaEFi68', TRUE, TRUE, '127.0.0.1', 0.0, 0.0, 0);
INSERT INTO t_user_asset(id, money) VALUES((SELECT u.id FROM t_user AS u WHERE u.username='super'), 100);

-- 用户登录日志表
DROP TABLE IF EXISTS t_user_login_log;
CREATE TABLE t_user_login_log(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '用户登录日志ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '登录用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '登录用户的用户名（登录名，外键）',
       longitude            DOUBLE                         NOT NULL                            COMMENT '用户登录时的经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '用户登录时的纬度',
       login_ip             VARCHAR(64)                                                        COMMENT '用户登录IP',
       login_time           BIGINT             UNSIGNED    NOT NULL                            COMMENT '用户登录时间戳'
)COMMENT='用户登录日志表';


-- 活动类型表
DROP TABLE IF EXISTS t_activity_type;
CREATE TABLE t_activity_type(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '活动类型ID，主键',
       name                 VARCHAR(100)                   NOT NULL                            COMMENT '活动类型名称',
       descriptions         VARCHAR(255)                                                       COMMENT '活动类型描述',
       parent_id            CHAR(36)                                                           COMMENT '上级活动类型ID（外键，自关联）'
)COMMENT='活动类型表';

INSERT INTO t_activity_type(id, name, parent_id) VALUES(uuid(), '猜拳', NULL);
INSERT INTO t_activity_type(id, name, parent_id) VALUES(uuid(), '猜我', NULL);
INSERT INTO t_activity_type(id, name, parent_id) VALUES(uuid(), '猜体重', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));
INSERT INTO t_activity_type(id, name, parent_id) VALUES(uuid(), '猜身高', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));
INSERT INTO t_activity_type(id, name, parent_id) VALUES(uuid(), '猜性别', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));
INSERT INTO t_activity_type(id, name, parent_id) VALUES(uuid(), '猜胸围', (SELECT a.id FROM t_activity_type AS a WHERE a.name='猜我'));


-- 活动表
DROP TABLE IF EXISTS t_activity;
CREATE TABLE t_activity(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '活动ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '活动发起者ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '活动发起者用户名（登录名，外键）',
       icon_path            VARCHAR(255)                                                       COMMENT '活动发起者用户头像保存路径（外键）',
       activity_type_id     CHAR(36)                       NOT NULL                            COMMENT '活动类型ID（外键）',
       activity_type_name   VARCHAR(100)                   NOT NULL                            COMMENT '活动类型名称（外键）',
       -- title                VARCHAR(255)                                                       COMMENT '活动题目',
       correct_answer       VARCHAR(255)                   NOT NULL                            COMMENT '活动正确答案',
       answer_one           VARCHAR(255)                   NOT NULL                            COMMENT '活动显示答案1',
       answer_two           VARCHAR(255)                   NOT NULL                            COMMENT '活动显示答案2',
       answer_three         VARCHAR(255)                   NOT NULL                            COMMENT '活动显示答案3',
       member_num_limit     SMALLINT           UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '活动允许参与的人数',
       already_member_num   SMALLINT           UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '活动实际参与的人数',
       time_limit           BIGINT             UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '活动的时间限制，单位为分钟',
       pre_money            INT                            NOT NULL DEFAULT 0                  COMMENT '发起活动的预扣金币',
       return_money         INT                            NOT NULL DEFAULT 0                  COMMENT '活动结束后退还的金币',
       earn_rewards         INT                            NOT NULL DEFAULT 0                  COMMENT '活动结束后获得的奖励',
       reward               INT                            NOT NULL DEFAULT 0                  COMMENT '活动的奖励，为0表示没有奖励',
       longitude            DOUBLE                         NOT NULL                            COMMENT '活动发起地的经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '活动发起地的纬度',
       client_ip            VARCHAR(64)                                                        COMMENT '发起活动的客户端IP',
       -- client_type          INT                UNSIGNED                                        COMMENT '发起活动的客户端类型，0:网站, 1:手机网页版, 2:android, 3:iphone',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '活动发起时间戳',
       is_ended             BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '活动是否已结束（默认FALSE）',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '活动是否已删除（默认FALSE）'
)COMMENT='活动表';


-- 活动成员（参与者）表
DROP TABLE IF EXISTS t_activity_member;
CREATE TABLE t_activity_member(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '活动成员表ID，主键',
       activity_id          CHAR(36)                       NOT NULL                            COMMENT '活动ID（外键）',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '活动参与者ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '活动参与者用户名（登录名，外键）',
       icon_path            VARCHAR(255)                                                       COMMENT '活动参与者用户头像保存路径（外键）',
       answer               VARCHAR(255)                   NOT NULL                            COMMENT '参与者的回答',
       is_win               TINYINT                        NOT NULL DEFAULT 0                  COMMENT '是否获胜，平局返回 0，参与者获胜返回大于 0 的值，参与者失败返回小于 0 的值（默认0）',
       pre_money            INT                            NOT NULL DEFAULT 0                  COMMENT '参与活动的预扣金币',
       return_money         INT                            NOT NULL DEFAULT 0                  COMMENT '活动结束后退还的金币',
       earn_rewards         INT                            NOT NULL DEFAULT 0                  COMMENT '参与者获得的奖励',
       longitude            DOUBLE                                                             COMMENT '参与活动时的经度',
       latitude             DOUBLE                                                             COMMENT '参与活动时的纬度',
       client_ip            VARCHAR(64)                                                        COMMENT '参与活动的客户端IP',
       -- client_type          INT                UNSIGNED                                        COMMENT '参与活动的客户端类型，0:网站, 1:手机网页版, 2:android, 3:iphone',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '参与活动时间戳',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='活动成员（参与者）表';


-- 活动日志表
-- DROP TABLE IF EXISTS t_activity_log;
-- CREATE TABLE t_activity_log(
--        id                   INT                UNSIGNED    NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '登录日志ID，主键',
--        user_id              INT                UNSIGNED    NOT NULL                            COMMENT '登录用户ID（外键）',
--        get_reward
--        client_ip            VARCHAR(64)                                                        COMMENT '发起活动的客户端IP',
--        create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '活动发起时间戳'
-- );


-- 私信表
DROP TABLE IF EXISTS t_message;
CREATE TABLE t_message(
       id                         CHAR(36)                 NOT NULL                PRIMARY KEY COMMENT '私信ID，主键',
       from_uid                   CHAR(36)                 NOT NULL                            COMMENT '私信发起者ID（外键）',
       from_uname                 VARCHAR(100)             NOT NULL                            COMMENT '私信发起者用户名（登录名，外键）',
       from_uicon                 VARCHAR(255)                                                 COMMENT '私信发起者用户头像保存路径（外键）',
       message_type               TINYINT      UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '私信类别，1:一对一; 2:多人',
       title                      VARCHAR(255)                                                 COMMENT '标题',
       member_num                 SMALLINT     UNSIGNED    NOT NULL DEFAULT 2                  COMMENT '参与者数量',
       member_ids                 VARCHAR(200)                                                 COMMENT '所有参与者的ID，以逗号分隔且正序排序',
       longitude                  DOUBLE                   NOT NULL                            COMMENT '私信发起者所在地的经度',
       latitude                   DOUBLE                   NOT NULL                            COMMENT '私信发起者所在地的纬度',
       create_time                BIGINT       UNSIGNED    NOT NULL                            COMMENT '私信发起时间戳',
       client_ip                  VARCHAR(64)                                                  COMMENT '私信发起者的客户端IP',
       msg_last_conversation_time BIGINT       UNSIGNED    NOT NULL                            COMMENT '私信最后会话时间戳',
       is_deleted                 BOOLEAN                  NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='私信表';


-- 私信会话内容表
DROP TABLE IF EXISTS t_message_content;
CREATE TABLE t_message_content(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '私信内对话ID，主键',
       message_id           CHAR(36)                       NOT NULL                            COMMENT '私信ID（外键）',
       from_uid             CHAR(36)                       NOT NULL                            COMMENT '私信发送者ID（外键）',
       from_uname           VARCHAR(100)                   NOT NULL                            COMMENT '私信发送者用户名（登录名，外键）',
       from_uicon           VARCHAR(255)                                                       COMMENT '私信发送者用户头像保存路径（外键）',
       content              TEXT                                                               COMMENT '私信内容',
       attachment_ids       VARCHAR(255)                                                       COMMENT '附件ID列表，用逗号分隔',
       longitude            DOUBLE                         NOT NULL                            COMMENT '私信发送者所在地的经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '私信发送者所在地的纬度',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '发送私信的时间戳',
       client_ip            VARCHAR(64)                                                        COMMENT '发送私信的客户端IP',
       is_read              BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已读（默认FALSE）',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='私信会话内容表';


-- 私信会话成员表
DROP TABLE IF EXISTS t_message_member;
CREATE TABLE t_message_member(
       id                        CHAR(36)                  NOT NULL                PRIMARY KEY COMMENT '私信会话成员表ID，主键',
       message_id                CHAR(36)                  NOT NULL                            COMMENT '私信ID（外键）',
       member_uid                CHAR(36)                  NOT NULL                            COMMENT '私信参与者ID（外键）',
       member_uname              VARCHAR(100)              NOT NULL                            COMMENT '私信参与者用户名（登录名，外键）',
       member_uicon              VARCHAR(255)                                                  COMMENT '私信参与者用户头像保存路径（外键）',
       has_new                   BOOLEAN                   NOT NULL DEFAULT FALSE              COMMENT '是否有未读消息（默认FALSE）',
       message_num               TINYINT       UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '消息总数',
       last_conversation_time    BIGINT        UNSIGNED    NOT NULL                            COMMENT '该参与者最后会话时间戳',
       is_deleted                BOOLEAN                   NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='私信会话成员表';


-- 系统通知表
DROP TABLE IF EXISTS t_notify_message;
CREATE TABLE t_notify_message(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '系统通知表ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '用户名（登录名，外键）',
       `type`               VARCHAR(50)                                                        COMMENT '系统通知类型',        
       content              VARCHAR(255)                   NOT NULL                            COMMENT '系统通知内容',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '添加时间戳',
       is_read              BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已读（默认FALSE）'
)COMMENT='系统通知表';


-- 签到记录表
DROP TABLE IF EXISTS t_signin_info;
CREATE TABLE t_signin_info(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '签到记录表ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '签到用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '签到用户的用户名（登录名，外键）',
       con_num              INT                UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '连续签到次数',
       total_num            INT                UNSIGNED    NOT NULL DEFAULT 1                  COMMENT '总签到次数',
       signin_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '签到时间戳'
)COMMENT='签到记录表';


-- 微博表
DROP TABLE IF EXISTS t_microblog;
CREATE TABLE t_microblog(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '微博ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '发布微博的用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '发布微博的用户的用户名（登录名，外键）',
       icon_path            VARCHAR(255)                                                       COMMENT '发布微博的用户的头像保存路径',
       content              TEXT                           NOT NULL                            COMMENT '纯微博内容',
       attachment_ids       VARCHAR(255)                                                       COMMENT '微博附件ID列表，用逗号分隔',
       comment_count        INT                UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '评论数',
       repost_count         INT                UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '分享数',
       endorse_count        INT                UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '点赞数',
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已通过审核（默认FALSE）',
       is_repost            BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否转发（默认FALSE）',
       client_ip            VARCHAR(64)                                                        COMMENT '发布微博的客户端IP',
       -- client_type          INT                UNSIGNED                                        COMMENT '发布微博的客户端类型，0:网站, 1:手机网页版, 2:android, 3:iphone',
       longitude            DOUBLE                         NOT NULL                            COMMENT '发布微博时的经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '发布微博时的纬度',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '发布时间戳',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='微博表';


-- 微博点赞记录表
DROP TABLE IF EXISTS t_microblog_endorse;
CREATE TABLE t_microblog_endorse(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '微博点赞记录ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '点赞用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '点赞用户的用户名（登录名）',
       icon_path            VARCHAR(255)                                                       COMMENT '点赞的用户头像保存路径',
       microblog_id         CHAR(36)                       NOT NULL                            COMMENT '被点赞的微博ID（外键）',
       client_ip            VARCHAR(64)                                                        COMMENT '客户端IP',
       longitude            DOUBLE                         NOT NULL                            COMMENT '占赞微博时的经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '占赞微博时的纬度',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '点赞时间戳'
)COMMENT='微博点赞记录表';


-- 微博评论表
DROP TABLE IF EXISTS t_microblog_comment;
CREATE TABLE t_microblog_comment(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '微博评论ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '评论者用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '评论者的用户名（登录名，外键）',
       icon_path            VARCHAR(255)                                                       COMMENT '评论者的用户头像保存路径',
       microblog_id         CHAR(36)                                                           COMMENT '评论所属的微博ID（外键）',
       attachment_ids       VARCHAR(255)                                                       COMMENT '评论附件ID列表，用逗号分隔',
       content              TEXT                           NOT NULL                            COMMENT '评论内容',
       to_comment_id        CHAR(36)                                                           COMMENT '被回复的评论ID（外键）',
       to_user_id           CHAR(36)                                                           COMMENT '被回复评论的作者的ID（外键）',
       client_ip            VARCHAR(64)                                                        COMMENT '发表评论的客户端IP',
       -- client_type          INT                UNSIGNED                                        COMMENT '客户端类型，0:网站, 1:手机网页版, 2:android, 3:iphone',
       is_audited           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已通过审核（默认FALSE）',
       longitude            DOUBLE                         NOT NULL                            COMMENT '评论微博时的经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '评论微博时的纬度',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '评论时间戳',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='微博评论表';


-- 附件表
DROP TABLE IF EXISTS t_attachment;
CREATE TABLE t_attachment(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '附件ID，主键',
       name                 VARCHAR(255)                                                       COMMENT '附件名称',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '用户名（登录名，外键）',
       attachment_type      VARCHAR(20)                                                        COMMENT '附件所属类型',
       mime                 VARCHAR(64)                                                        COMMENT '附件格式',
       size                 BIGINT             UNSIGNED    NOT NULL                            COMMENT '附件大小',
       extension_name       VARCHAR(20)                                                        COMMENT '附件扩展名',
       hashcode             VARCHAR(32)                                                        COMMENT '附件哈希值',
       save_path            VARCHAR(255)                   NOT NULL                            COMMENT '附件保存路径',
       original_name        VARCHAR(255)                   NOT NULL                            COMMENT '附件原名称',
       save_name            VARCHAR(255)                   NOT NULL                            COMMENT '附件保存名称',
       is_private           TINYINT            UNSIGNED    NOT NULL DEFAULT 0                  COMMENT '附件是否为私有（即对其他人不可见），0:否, 1：是',
       -- client_type          INT                UNSIGNED                                        COMMENT '上传附件的客户端类型，0:网站, 1:手机网页版, 2:android, 3:iphone',
       -- width                INT                UNSIGNED             DEFAULT 0                  COMMENT '图片宽度',
       -- height               INT                UNSIGNED             DEFAULT 0                  COMMENT '图片高度',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '附件上传时间戳',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='附件表';


-- 用户使用日志表
DROP TABLE IF EXISTS t_user_use_log;
CREATE TABLE t_user_use_log(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '日志ID，主键',
       user_id              CHAR(36)                       NOT NULL                            COMMENT '用户ID（外键）',
       username             VARCHAR(100)                   NOT NULL                            COMMENT '用户名（登录名，外键）',
       operation            VARCHAR(64)                    NOT NULL                            COMMENT '用户的操作',
       -- click_object
       longitude            DOUBLE                         NOT NULL                            COMMENT '用户的操作时的所在地经度',
       latitude             DOUBLE                         NOT NULL                            COMMENT '用户的操作时的所在地纬度',
       client_ip            VARCHAR(64)                                                        COMMENT '客户端IP',
       client_info          VARCHAR(255)                                                       COMMENT '客户端信息',
       create_time          BIGINT             UNSIGNED    NOT NULL                            COMMENT '记录日志时的时间戳'
)COMMENT='用户使用日志表';


-- 胡言乱语类型表
DROP TABLE IF EXISTS t_nonsense_type;
CREATE TABLE t_nonsense_type(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '胡言乱语类型ID，主键',
       name                 VARCHAR(100)                   NOT NULL                            COMMENT '胡言乱语类型名称',
       descriptions         VARCHAR(255)                                                       COMMENT '胡言乱语类型描述',
       parent_id            CHAR(36)                                                           COMMENT '上级胡言乱语类型ID（外键，自关联）'
)COMMENT='胡言乱语类型表';

INSERT INTO t_nonsense_type(id, name, parent_id) VALUES(uuid(), '登录', NULL);
INSERT INTO t_nonsense_type(id, name, parent_id) VALUES(uuid(), '注册', NULL);


-- 胡言乱语内容表
DROP TABLE IF EXISTS t_nonsense;
CREATE TABLE t_nonsense(
       id                   CHAR(36)                       NOT NULL                PRIMARY KEY COMMENT '胡言乱语类型ID，主键',
       nonsense_type_id     CHAR(36)                       NOT NULL                            COMMENT '胡言乱语类型ID（外键）',
       nonsense_type_name   VARCHAR(100)                   NOT NULL                            COMMENT '胡言乱语类型名称（外键）',
       content              TEXT                           NOT NULL                            COMMENT '胡言乱语内容',
       descriptions         VARCHAR(255)                                                       COMMENT '胡言乱语类型描述',
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              COMMENT '是否已删除（默认FALSE）'
)COMMENT='胡言乱语内容表';


-- 提交事务
COMMIT;