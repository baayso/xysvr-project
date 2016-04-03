--增加菜单使用帮助（以暗号拉人为例）
--第一步：后台管理权限表加入增删查改权限
--说明：1、"secretcode:*"中"*"表示拥有所有增删查改权限，"secretcode:create"、"secretcode:update"、"secretcode:delete"、"secretcode:view"分别对应增改删查，也可以加入其它权限如微博类的"microblog:audit"代表审核微博
--2、新插入的权限id为在前的一组权限id中最小的基础上加10，secretcode的前一组权限id为181、182、183、184、185
--3、type为枚举型0代表菜单类，1代表按钮类
--4、parent_id代表父权限id，parent_ids代表父权限id列表
--5、url字段在每一组父权限加，子权限不加
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(191, '暗号使用管理', '0', '/secretcode', '1', '0/1/', 'secretcode:*', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(192, '暗号使用新增', '1', '', '191', '0/1/191/', 'secretcode:create', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(193, '暗号使用修改', '1', '', '191', '0/1/191/', 'secretcode:update', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(194, '暗号使用删除', '1', '', '191', '0/1/191/', 'secretcode:delete', 'true', null);
INSERT INTO t_mgr_permission(id, name, type, url, parent_id, parent_ids, permission_str, is_available, descriptions) VALUES(195, '暗号使用查看', '1', '', '191', '0/1/191/', 'secretcode:view', 'true', null);

--第二步：后台管理角色表修改管理员和巡逻员的授予的权限列表
--说明：1、admin为超级管理员，默认拥有所有权限，可以不作修改依然拥有所有权限
--2、patrol为巡逻员，仅拥有查看权限，也可按需求设置其他权限
--3、设置权限可以在后台用户管理/角色管理/设置权限进行设置
UPDATE t_mgr_role SET permission_ids = '1,11,12,13,14,15,21,22,23,24,25,31,32,33,34,35,41,42,43,44,45,51,52,53,54,55,56,61,62,63,64,65,71,72,73,74,75,81,82,83,84,85,91,92,93,94,95,101,102,103,104,105,111,112,113,114,115,121,122,123,124,125,131,132,133,134,135,141,142,143,144,145,151,152,153,154,155,161,162,163,164,165,171,172,173,174,175,181,182,183,184,185,191,192,193,194,195' WHERE name = 'admin';
UPDATE t_mgr_role SET permission_ids = '15,25,35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185,195' WHERE name = 'patrol';
UPDATE t_mgr_role SET permission_ids = '15,25,35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185,195' WHERE name = 'patrol1';

--第三步:后台管理菜单表加入对应菜单名,url,样式，所需权限
--说明：1、name为菜单名，url为菜单链接地址，span_class为菜单图标样式
--2、needperm为所需权限，authorized为是否授权（默认false）
--3、sortnum为在前的一组排序数字中最小的基础上加10
--4、无下拉列表的isdiv字段为true，parent_id为null；有下拉列表isdiv字段为false，带箭头主菜单parent_id为null，下拉列表parent_id为主菜单id

--例子1（暗号拉人，无下拉列表）
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfl', '暗号使用记录', 'secretcode/list', 151, 'am-icon-link', 'secretcode:view', false, true, null);

--例子2（后台用户管理，有下拉列表）
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfm', '后台用户管理', null, 161, 'am-icon-user-md', 'user:view', false, false, null);
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfn', '管理员列表', 'user/list', 162, 'am-icon-table', 'user:view', false, false, '54fea21f805741497c06bdfm');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfo', '新增管理员', 'user/register', 163, 'am-icon-pencil', 'user:create', false, false, '54fea21f805741497c06bdfm');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfp', '角色列表', 'role/list', 164, 'am-icon-table', 'role:view', false, false, '54fea21f805741497c06bdfm');
INSERT INTO t_mgr_menu(id, name, url, sortnum, span_class, needperm, authorized, isdiv, parent_id) VALUES('54fea21f805741497c06bdfq', '新增角色', 'role/showAdd', 165, 'am-icon-pencil', 'role:create', false, false, '54fea21f805741497c06bdfm');
