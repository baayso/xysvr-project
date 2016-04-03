--系统配置表
DROP TABLE IF EXISTS t_sysconfig;
CREATE TABLE t_sysconfig(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --主键             
       type                 VARCHAR(20)                    NOT NULL                ,           --配置类型
       name                 VARCHAR(100)                   NOT NULL                ,           --配置名称
       value                VARCHAR(255)                   NOT NULL                ,           --配置值
       descriptions         VARCHAR(255)                                           ,           --配置描述
       mtime                BIGINT                         NOT NULL                            --修改时间
);

INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191110', '系统', 'page_size','10', '分页时每页显示记录数，不可配置为小于1的数', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191111', '系统', 'client_authorize_switch', 'false', '客户端认证开关', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191112', '系统', 'client_authorize_sign_one', 'Xr6OjHd4cV1G7wpKRDpfLZBJylffo8cn4H4QQg1w', '客户端认证校验码一', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191113', '系统', 'client_authorize_sign_two', 'Aehblb98gk5TTJ4ns8GRAwJHC3UPi99gDUuQnUih', '客户端认证校验码二', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191114', '系统', 'distance_km', '1.0', '查找数据的距离（公里）', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191115', '系统', 'history_retention_day', '1', '历史数据保留天数，不可配置为小于1的数', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191116', '系统', 'slave_database_select_sleep_switch', 'true', '从数据库查询休眠开关', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191117', '系统', 'slave_database_select_sleep_millis', '10', '从数据库查询休眠毫秒数，不可配置为小于1的数', extract(epoch FROM now()) * 1000);

INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191214', '用户', 'user_ranking_top_number', '50', '用户排名显示名次数量，不可配置为小于1的数', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191215', '用户', 'user_initial_money', '500', '用户初始金币，不可配置为负数', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191216', '用户', 'user_initial_bonus_point', '5', '用户初始积分，不可配置为负数', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f3191217', '用户', 'user_initial_level', '1', '用户初始等级，不可配置为负数', extract(epoch FROM now()) * 1000);

INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f1', '七牛云存储', 'qiniu_access_key','RZ7_0FGAn5ZKi9DVKHSvLT5gqgpKzsAD3yvtjgHW', '七牛云公钥', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f2', '七牛云存储', 'qiniu_secret_key','ylekLvN9V5KUaSExhHSAElYZs4DMzJwFapxvHbD4', '七牛云私钥', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f3', '七牛云存储', 'qiniu_public_bucket_name','xiaoyao-public', '七牛云存储公有空间名称', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f4', '七牛云存储', 'qiniu_public_bucket_https_domain','https://dn-baayso-xiaoyao-public.qbox.me', '七牛云存储公有空间HTTPS域名', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f5', '七牛云存储', 'qiniu_public_bucket_domain','http://7xiqdo.com1.z0.glb.clouddn.com', '七存储牛云公有空间推荐域名', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f6', '七牛云存储', 'qiniu_private_bucket_name','xiaoyao-private', '七牛云存储私有空间名称', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f7', '七牛云存储', 'qiniu_private_bucket_https_domain','https://dn-baayso-xiaoyao-private.qbox.me', '七牛云存储私有空间HTTPS域名', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysconfig(id, type, name, value, descriptions, mtime) VALUES('54c35dd4f48edef1f31913f8', '七牛云存储', 'qiniu_private_bucket_domain','http://7xiqdp.com1.z0.glb.clouddn.com', '七存储牛云私有空间推荐域名', extract(epoch FROM now()) * 1000);


--中文系统提示表
DROP TABLE IF EXISTS t_sysprompt_zhcn;
CREATE TABLE t_sysprompt_zhcn(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --主键
       module               INTEGER                        NOT NULL                ,           --模块
       code                 INTEGER                        NOT NULL                ,           --编码
       content              VARCHAR(255)                   NOT NULL                ,           --提示内容
       mtime                BIGINT                         NOT NULL                            --修改时间
);
--系统
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912f7', 2000, 2011, '非法数据', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c746c8f48eac1aee75e380', 2000, 2012, '出错了', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c5ecf9f48e96c850678611', 2000, 2013, '客户端认证成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c5ecf9f48e96c850678612', 2000, 2014, '客户端认证失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713d5', 2000, 2015, '提交反馈失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713d6', 2000, 2016, '提交反馈成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713d7', 2000, 2017, '支付宝充值成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713d8', 2000, 2018, '支付宝充值失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713e0', 2000, 2019, '未设置电子邮件地址', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713e1', 2000, 2020, '邮件发送成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54cc40a7f48e005ffe1713e2', 2000, 2021, '邮件发送失败', extract(epoch FROM now()) * 1000);

--用户
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912f8', 3000, 3011, '您的帐户已在其它地方登录，您已被迫下线', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912f9', 3000, 3012, '此用户名已被使用', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912fa', 3000, 3013, '此用户名未使用', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912fc', 3000, 3014, '此email已被使用', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912fd', 3000, 3015, '此email未使用', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f31912ff', 3000, 3016, '注册成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35dd4f48edef1f3191300', 3000, 3017, '注册失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4dc', 3000, 3018, '您已经登录', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4dd', 3000, 3019, '您尚未登录', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4de', 3000, 3020, '登录成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4df', 3000, 3021, '登录出错', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4e0', 3000, 3022, '登录失败次数过多，请稍候在试', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4e1', 3000, 3023, '帐号已被锁定', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4e2', 3000, 3024, '帐号已被禁用', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4e3', 3000, 3025, '用户名或密码错误', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4e4', 3000, 3026, '您已成功注销登录', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c35f2cf48e0751d470e4e5', 3000, 3027, '获取用户信息成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd4a', 3000, 3028, '原密码错误', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd4b', 3000, 3029, '新密码和原密码一致', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd4c', 3000, 3030, '两次输入的新密码不一致', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd4d', 3000, 3031, '修改密码成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd4e', 3000, 3032, '修改密码失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd4f', 3000, 3033, '上传成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd50', 3000, 3034, '上传失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd51', 3000, 3035, '您没有进行任何修改', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd52', 3000, 3036, '修改成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36007f48ed89a37d5fd53', 3000, 3037, '修改失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c36126f48ef22679e0a7de', 3000, 3038, '加载出错', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e381', 3000, 3039, '附件数据格式不正确', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e382', 3000, 3040, '更新用户财富失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e383', 3000, 3041, '兑换成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e384', 3000, 3042, '兑换失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e385', 3000, 3043, '给定的金币不正确', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e386', 3000, 3044, '您的金币不足', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e387', 3000, 3045, '您的钱财不足', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e388', 3000, 3046, '您的赢取金币不足', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e410', 3000, 3047, '此设备已注册过', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e411', 3000, 3048, '此设备未注册过', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e412', 3000, 3049, '重置密码成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e413', 3000, 3050, '重置密码失败', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e414', 3000, 3051, '校验码错误', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e415', 3000, 3052, '邀请人不存在', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c796c8f48eac1aee75e416', 3000, 3053, '用户不存在', extract(epoch FROM now()) * 1000);

--私信
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c3643df48e9f9ed0ca4f8c', 6000, 6012, '发送私信成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c3643df48e9f9ed0ca4f8d', 6000, 6013, '发送私信失败', extract(epoch FROM now()) * 1000);

--七牛云存储
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c8b8ccf48ec07e2fdc03c0', 10000, 10011, '获取成功', extract(epoch FROM now()) * 1000);
INSERT INTO t_sysprompt_zhcn(id, module, code, content, mtime) VALUES('54c8b8ccf48ec07e2fdc03c1', 10000, 10012, '获取失败', extract(epoch FROM now()) * 1000);


--英文系统提示表
DROP TABLE IF EXISTS t_sysprompt_en;
CREATE TABLE t_sysprompt_en(
       id                   VARCHAR(32)                    PRIMARY KEY             ,           --主键
       module               INTEGER                        NOT NULL                ,           --模块
       code                 INTEGER                        NOT NULL                ,           --编码
       content              VARCHAR(255)                   NOT NULL                ,           --提示内容
       mtime                BIGINT                         NOT NULL                            --修改时间
);


