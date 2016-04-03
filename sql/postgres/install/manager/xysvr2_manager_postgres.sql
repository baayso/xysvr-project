-- 后台管理权限表
DROP TABLE IF EXISTS t_mgr_permission;
CREATE TABLE t_mgr_permission(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --权限ID，主键
       name                 VARCHAR(100)                   NOT NULL                ,           --权限名称
       type                 VARCHAR(50)                                            ,           --权限类型
       url                  VARCHAR(200)                                           ,           --权限对应的url
       parent_id            VARCHAR(32)                                            ,           --父权限ID
       parent_ids           VARCHAR                                                ,           --父权限ID列表
       permission_str       VARCHAR(100)                   NOT NULL                ,           --权限字符串
       is_available         BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否可用
       descriptions         VARCHAR(255)                                                       --权限描述
);

INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(1, '资源', '0', '', '0', '0/', '', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(11, '后台用户管理', '0', '/user', '1', '0/1/', 'user:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(12, '后台用户新增', '1', '', '11', '0/1/11/', 'user:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(13, '后台用户修改', '1', '', '11', '0/1/11/', 'user:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(14, '后台用户删除', '1', '', '11', '0/1/11/', 'user:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(15, '后台用户查看', '1', '', '11', '0/1/11/', 'user:view', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(21, '角色管理', '0', '/role', '1', '0/1/', 'role:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(22, '角色新增', '1', '', '21', '0/1/21/', 'role:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(23, '角色修改', '1', '', '21', '0/1/21/', 'role:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(24, '角色删除', '1', '', '21', '0/1/21/', 'role:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(25, '角色查看', '1', '', '21', '0/1/21/', 'role:view', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(26, '角色授权', '1', '', '21', '0/1/21/', 'role:authorize', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(31, '权限管理', '0', '/permission', '1', '0/1/', 'permission:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(32, '权限新增', '1', '', '31', '0/1/31/', 'permission:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(33, '权限修改', '1', '', '31', '0/1/31/', 'permission:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(34, '权限删除', '1', '', '31', '0/1/31/', 'permission:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(35, '权限查看', '1', '', '31', '0/1/31/', 'permission:view', 'true', null);

INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(61, '用户管理', '0', '/userLog', '1', '0/1/', 'userLog:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(62, '用户新增', '1', '', '61', '0/1/61/', 'userLog:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(63, '用户修改', '1', '', '61', '0/1/61/', 'userLog:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(64, '用户删除', '1', '', '61', '0/1/61/', 'userLog:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(65, '用户查看', '1', '', '61', '0/1/61/', 'userLog:view', 'true', null);

INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(131, '系统提示管理', '0', '/sysprompt', '1', '0/1/', 'sysprompt:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(132, '系统提示新增', '1', '', '131', '0/1/131/', 'sysprompt:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(133, '系统提示修改', '1', '', '131', '0/1/131/', 'sysprompt:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(134, '系统提示删除', '1', '', '131', '0/1/131/', 'sysprompt:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(135, '系统提示查看', '1', '', '131', '0/1/131/', 'sysprompt:view', 'true', null);

INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(161, '系统配置信息管理', '0', '/sysConfig', '1', '0/1/', 'sysConfig:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(162, '系统配置信息新增', '1', '', '161', '0/1/161/', 'sysConfig:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(163, '系统配置信息修改', '1', '', '161', '0/1/161/', 'sysConfig:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(164, '系统配置信息删除', '1', '', '161', '0/1/161/', 'sysConfig:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(165, '系统配置信息查看', '1', '', '161', '0/1/161/', 'sysConfig:view', 'true', null);


-- 后台管理角色表
DROP TABLE IF EXISTS t_mgr_role;
CREATE TABLE t_mgr_role(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --角色ID，主键
       name                 VARCHAR(100)                   NOT NULL                ,           --角色名称
       permission_ids       VARCHAR                                                ,           --授予的权限列表
       is_available         BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否可用
       descriptions         VARCHAR(255)                                                       --角色描述
);


-- 后台管理用户与角色的关联表
DROP TABLE IF EXISTS t_mgr_user_role;
CREATE TABLE t_mgr_user_role(
       id                   VARCHAR(32)                    PRIMARY KEY             ,            --用户与角色的关联表ID，主键
       user_id              VARCHAR(32)                    NOT NULL                ,            --用户ID（外键）
       role_id              VARCHAR(32)                    NOT NULL                             --角色ID（外键）
);


-- 后台管理用户表
DROP TABLE IF EXISTS t_mgr_user;
CREATE TABLE t_mgr_user(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --用户ID，主键
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
       is_admin             BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --用户是否为管理员（默认FALSE）
       create_ip            VARCHAR(64)                                            ,           --注册IP
       create_time          BIGINT                         NOT NULL                ,           --用户创建（注册）时间戳
       last_login_time      BIGINT                                                 ,           --用户最后登录时间戳
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
       is_deleted           BOOLEAN                        NOT NULL DEFAULT FALSE              --用户是否已删除（默认FALSE）
);
ALTER TABLE "t_mgr_user" ADD CONSTRAINT "t_mgr_user_username_unique" UNIQUE ("username");
ALTER TABLE "t_mgr_user" ADD CONSTRAINT "t_mgr_user_email_unique" UNIQUE ("email");
CREATE INDEX "t_mgr_user_create_time_index" ON "t_mgr_user"("create_time");

INSERT INTO t_mgr_user(id, username, nickname, password, salt, is_audited, is_activated, create_ip, create_time) VALUES('549cbb9deb2911998b2582f3', 'super', '超级管理员', '15bab70a56a0328dfb385f07d84aa39255e90f94', '643068642c33351b', TRUE, TRUE, '127.0.0.1', extract(epoch FROM now()) * 1000);


-- 后台管理用户登录日志表
DROP TABLE IF EXISTS t_mgr_user_login_log;
CREATE TABLE t_mgr_user_login_log(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --用户登录日志ID，主键
       user_id              VARCHAR(32)                                            ,           --登录用户ID（外键）
       username             VARCHAR(100)                   NOT NULL                ,           --登录用户的用户名（登录名，外键）
       login_ip             VARCHAR(64)                                            ,           --用户登录IP
       is_success           BOOLEAN                        NOT NULL                ,           --是否登录成功
       login_time           BIGINT                         NOT NULL                            --用户登录时间戳
);
CREATE INDEX "t_mgr_user_login_log_user_id_index" ON "t_mgr_user_login_log"("user_id");


-- 后台管理菜单表
DROP TABLE IF EXISTS t_mgr_menu;
CREATE TABLE t_mgr_menu(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --菜单ID，主键
       name                 VARCHAR(32)                    NOT NULL                ,           --菜单条目名
       url                  VARCHAR(200)                                           ,           --菜单链接地址
       sortnum              SMALLINT                                               ,           --排序数字
       span_class           VARCHAR(100)                                           ,           --菜单条目样式
       needperm             VARCHAR(36)                    NOT NULL                ,           --需求的权限
       authorized           BOOLEAN                        NOT NULL DEFAULT FALSE  ,           --是否有访问权限（默认FALSE）
       isdiv                BOOLEAN                        NOT NULL                ,           --是否是div（默认FALSE）
       parent_id            VARCHAR(32)                                                        --父菜单ID
);
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdf9', '系统提示管理', null, 91, 'am-icon-leaf', 'sysprompt:view', false, false, null);
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfa', '中文系统提示列表', 'sysprompt/zhcn/list', 92, 'am-icon-table', 'sysprompt:view', false, false, '54fea21f805741497c06bdf9');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfb', '英文系统提示列表', 'sysprompt/en/list', 93, 'am-icon-table', 'sysprompt:view', false, false, '54fea21f805741497c06bdf9');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfi', '系统配置信息管理', 'sysConfig/list', 141, 'am-icon-cogs', 'sysConfig:view', false, true, null);
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfq', '后台用户管理', null, 171, 'am-icon-user-md', 'user:view', false, false, null);
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfr', '新增管理员', 'user/addUI', 172, 'am-icon-pencil', 'user:create', false, false, '54fea21f805741497c06bdfq');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfs', '管理员列表', 'user/list', 173, 'am-icon-table', 'user:view', false, false, '54fea21f805741497c06bdfq');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdft', '新增角色', 'user/role/addUI', 174, 'am-icon-pencil', 'role:create', false, false, '54fea21f805741497c06bdfq');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfu', '角色列表', 'user/role/list', 175, 'am-icon-table', 'role:view', false, false, '54fea21f805741497c06bdfq');

