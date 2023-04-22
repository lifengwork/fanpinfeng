/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : maibaduoduo_tenant_code

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 01/09/2021 23:25:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36)  NOT NULL COMMENT 'uuid',
  `code` varchar(6)  NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('5de8806e-5c3e-48c2-8ade-f3f485e3d12c', '2np6d', '2023-04-06 20:17:16','');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50)  NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000)  NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500)  NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3  COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息','');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50)  NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50)  NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200)  NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000)  NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64)  NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20  COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"管理员列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"admin\",\"orderNum\":1}]', 16, '127.0.0.1', '2023-04-06 20:16:47','');
INSERT INTO `sys_log` VALUES (2, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":15,\"parentId\":2,\"name\":\"查看\",\"perms\":\"sys:user:list,sys:user:info\",\"type\":2,\"orderNum\":0}]', 14, '127.0.0.1', '2023-04-06 20:18:26','');
INSERT INTO `sys_log` VALUES (3, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":16,\"parentId\":2,\"name\":\"新增\",\"perms\":\"sys:user:save,sys:role:select\",\"type\":2,\"orderNum\":0}]', 10, '127.0.0.1', '2023-04-06 20:18:47','');
INSERT INTO `sys_log` VALUES (4, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":17,\"parentId\":2,\"name\":\"修改\",\"perms\":\"sys:user:update,sys:role:select\",\"type\":2,\"orderNum\":0}]', 12, '127.0.0.1', '2023-04-06 20:18:58','');
INSERT INTO `sys_log` VALUES (5, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":18,\"parentId\":2,\"name\":\"删除\",\"perms\":\"sys:user:delete\",\"type\":2,\"orderNum\":0}]', 11, '127.0.0.1', '2023-04-06 20:19:09','');
INSERT INTO `sys_log` VALUES (6, 'admin', '保存配置', 'com.maibaduoduo.service.sys.controller.SysConfigController.save()', '[{\"id\":2,\"paramKey\":\"参数\",\"paramValue\":\"1\",\"remark\":\"\"}]', 203, '127.0.0.1', '2023-04-06 20:19:40','');
INSERT INTO `sys_log` VALUES (7, 'admin', '删除配置', 'com.maibaduoduo.service.sys.controller.SysConfigController.delete()', '[[2]]', 36, '127.0.0.1', '2023-04-06 20:19:45','');
INSERT INTO `sys_log` VALUES (8, 'admin', '删除菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.delete()', '[6]', 0, '127.0.0.1', '2023-04-06 20:20:18','');
INSERT INTO `sys_log` VALUES (9, 'admin', '删除菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.delete()', '[6]', 0, '127.0.0.1', '2023-04-06 20:20:36','');
INSERT INTO `sys_log` VALUES (10, 'admin', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"zncdz\",\"password\":\"5984385e3eff2d8c36e802fb6970b3e1e930ffa5a64fd170463fb1c2509b76b5\",\"salt\":\"Mw2Zc1E4OW5ThtAXvKFO\",\"email\":\"zncdZ@yeah.net\",\"mobile\":\"13298765432\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 6, 2023 8:21:56 PM\"}]', 54, '127.0.0.1', '2023-04-06 20:21:57','');
INSERT INTO `sys_log` VALUES (11, 'admin', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"zncdz\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[-666666],\"createTime\":\"Apr 6, 2023 8:22:19 PM\"}]', 40, '127.0.0.1', '2023-04-06 20:22:20','');
INSERT INTO `sys_log` VALUES (12, 'admin', '修改角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"zncdz\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,27,29,30,-666666,1]}]', 67, '127.0.0.1', '2023-04-06 20:22:34','');
INSERT INTO `sys_log` VALUES (13, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"zncdz\",\"salt\":\"Mw2Zc1E4OW5ThtAXvKFO\",\"email\":\"zncdZ@yeah.net\",\"mobile\":\"13298765432\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 18, '127.0.0.1', '2023-04-06 20:23:01','');
INSERT INTO `sys_log` VALUES (14, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 17, '127.0.0.1', '2023-04-06 20:23:07','');
INSERT INTO `sys_log` VALUES (15, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 12, '127.0.0.1', '2023-04-06 20:23:13','');
INSERT INTO `sys_log` VALUES (16, 'zncdz', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":3,\"username\":\"hello\",\"password\":\"4c58f4665324d579e932c7ea3a90d11cf66eadfeb8df72d5b911591fda237a67\",\"salt\":\"YbfDaJgGo7gKultwomsd\",\"email\":\"hello@yeah.net\",\"mobile\":\"13512348765\",\"status\":1,\"roleIdList\":[],\"createUserId\":2,\"createTime\":\"Apr 6, 2023 8:23:58 PM\"}]', 10, '127.0.0.1', '2023-04-06 20:23:59','');
INSERT INTO `sys_log` VALUES (17, 'zncdz', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"devuser\",\"remark\":\"开发员工\",\"createUserId\":2,\"menuIdList\":[27,29,30,-666666,1],\"createTime\":\"Apr 6, 2023 8:25:52 PM\"}]', 28, '127.0.0.1', '2023-04-06 20:25:53','');
INSERT INTO `sys_log` VALUES (18, 'zncdz', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"devuser\",\"remark\":\"开发员工\",\"createUserId\":2,\"menuIdList\":[27,29,30,-666666,1],\"createTime\":\"Apr 6, 2023 8:25:53 PM\"}]', 25, '127.0.0.1', '2023-04-06 20:25:54','');
INSERT INTO `sys_log` VALUES (19, 'zncdz', '删除角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.delete()', '[[3]]', 14, '127.0.0.1', '2023-04-06 20:26:01','');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50)  NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200)  NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500)  NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：employee:list,employee:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50)  NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31  COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0,'');
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1,'');
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2,'');
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3,'');
INSERT INTO `sys_menu` VALUES (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4,'');
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5,'');
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0,'');
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6,'');
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7,'');
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6,'');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200)  NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100)  NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100)  NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4  COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'zncdz', '', 1, '2023-04-06 20:22:20','');
INSERT INTO `sys_role` VALUES (2, 'devuser', '开发员工', 2, '2023-04-06 20:25:53','');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32  COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1, 2,'');
INSERT INTO `sys_role_menu` VALUES (3, 1, 15,'');
INSERT INTO `sys_role_menu` VALUES (4, 1, 16,'');
INSERT INTO `sys_role_menu` VALUES (5, 1, 17,'');
INSERT INTO `sys_role_menu` VALUES (6, 1, 18,'');
INSERT INTO `sys_role_menu` VALUES (7, 1, 3,'');
INSERT INTO `sys_role_menu` VALUES (8, 1, 19,'');
INSERT INTO `sys_role_menu` VALUES (9, 1, 20,'');
INSERT INTO `sys_role_menu` VALUES (10, 1, 21,'');
INSERT INTO `sys_role_menu` VALUES (11, 1, 22,'');
INSERT INTO `sys_role_menu` VALUES (12, 1, 4,'');
INSERT INTO `sys_role_menu` VALUES (13, 1, 23,'');
INSERT INTO `sys_role_menu` VALUES (14, 1, 24,'');
INSERT INTO `sys_role_menu` VALUES (15, 1, 25,'');
INSERT INTO `sys_role_menu` VALUES (16, 1, 26,'');
INSERT INTO `sys_role_menu` VALUES (17, 1, 27,'');
INSERT INTO `sys_role_menu` VALUES (18, 1, 29,'');
INSERT INTO `sys_role_menu` VALUES (19, 1, 30,'');
INSERT INTO `sys_role_menu` VALUES (20, 1, -666666,'');
INSERT INTO `sys_role_menu` VALUES (21, 1, 1,'');
INSERT INTO `sys_role_menu` VALUES (22, 2, 27,'');
INSERT INTO `sys_role_menu` VALUES (23, 2, 29,'');
INSERT INTO `sys_role_menu` VALUES (24, 2, 30,'');
INSERT INTO `sys_role_menu` VALUES (25, 2, -666666,'');
INSERT INTO `sys_role_menu` VALUES (26, 2, 1,'');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50)  NOT NULL COMMENT '用户名',
  `password` varchar(100)  NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20)  NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100)  NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100)  NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4  COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11','');
INSERT INTO `sys_user` VALUES (2, 'zncdz', '5984385e3eff2d8c36e802fb6970b3e1e930ffa5a64fd170463fb1c2509b76b5', 'Mw2Zc1E4OW5ThtAXvKFO', 'zncdZ@yeah.net', '13298765432', 1, 1, '2023-04-06 20:21:57','');
INSERT INTO `sys_user` VALUES (3, 'hello', '4c58f4665324d579e932c7ea3a90d11cf66eadfeb8df72d5b911591fda237a67', 'YbfDaJgGo7gKultwomsd', 'hello@yeah.net', '13512348765', 1, 2, '2023-04-06 20:23:59','');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3  COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 2, 1,'');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(255)  NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB  COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, '3cdece4d540626f5bbd53d8fec8d0936', '2023-04-08 12:02:27', '2023-04-08 00:02:27','');
INSERT INTO `sys_user_token` VALUES (2, '8c421a02ff56a7521d9ece7b734a92ad', '2023-04-07 08:23:27', '2023-04-06 20:23:27','');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50)  NOT NULL COMMENT '用户名',
  `mobile` varchar(20)  NOT NULL COMMENT '手机号',
  `password` varchar(64)  NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2  COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41','');

-- ----------------------------
-- Table structure for test_tree
-- ----------------------------
DROP TABLE IF EXISTS `test_tree`;
CREATE TABLE `test_tree`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `parent_id` varchar(64)  NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000)  NOT NULL COMMENT '所有父级编号',
  `name` varchar(100)  NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_tree_del_flag`(`del_flag`) USING BTREE,
  INDEX `test_data_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '树结构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_tree
-- ----------------------------
INSERT INTO `test_tree` VALUES ('952da0305213401da7e081f86188b818', '0', '0,', '求购管理', 30, '1', '2021-08-17 15:32:09', '1', '2021-08-17 15:32:09', '', '0','');
INSERT INTO `test_tree` VALUES ('bcc6b85ba6f8496b8e3f08c1478f0083', '952da0305213401da7e081f86188b818', '0,952da0305213401da7e081f86188b818,', '订单管理', 30, '1', '2021-08-17 15:32:16', '1', '2021-08-17 15:32:16', '', '0','');

SET FOREIGN_KEY_CHECKS = 1;
