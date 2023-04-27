/*
 Navicat Premium Data Transfer

 Source Server         : zncdz
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : column_master

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 27/04/2023 18:41:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for saas_order
-- ----------------------------
DROP TABLE IF EXISTS `saas_order`;
CREATE TABLE `saas_order`  (
  `id` bigint(20) NOT NULL COMMENT '订单编码',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for saas_order_store
-- ----------------------------
DROP TABLE IF EXISTS `saas_order_store`;
CREATE TABLE `saas_order_store`  (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单编码',
  `store_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库编码',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for saas_store
-- ----------------------------
DROP TABLE IF EXISTS `saas_store`;
CREATE TABLE `saas_store`  (
  `id` bigint(20) NOT NULL COMMENT '仓库编码',
  `store_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `store_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库简述',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of saas_store
-- ----------------------------
INSERT INTO `saas_store` VALUES (1, '测试仓库', '测试仓库', 1, '2023-04-16 16:52:48', '1');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('05c19181-26a9-4987-8ca8-8743167d4f27', 'eg57b', '2023-04-11 09:31:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('07f2fd31-5692-43b7-8e18-705476b75c26', 'dyy7f', '2023-04-11 08:53:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('15ce7d2f-f159-47a2-8786-052d435e9e57', 'n4ny3', '2023-04-11 08:53:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1e0a2fb2-dbc8-4dc8-821c-60bdbfdf1c20', 'gbnc6', '2023-04-11 08:53:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2352d64a-660f-47a7-8e70-f0e90490a29b', 'fnb3f', '2023-04-11 08:53:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('256e9594-6b53-4d7a-8a80-2868841314c1', 'g6am7', '2023-04-10 06:04:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2b476d4c-bdd5-41ea-8fa2-ed06cee1e85b', 'np2pa', '2023-04-11 08:53:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2eec0d0e-cb18-47c8-8df8-78f2c0374c7d', '5a3n5', '2023-04-11 11:55:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3231a656-25bd-499f-8d4d-7909db2040d9', 'ppwdg', '2023-04-11 08:53:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3e3fdf89-457d-4cbf-89eb-e77ec6c70efc', 'wnwd3', '2023-04-11 08:53:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('41ef278a-bf0c-4aa4-8f53-4a43b599438b', 'axyxb', '2023-04-11 09:33:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('47ec73f9-ef41-4b04-87a5-ac133997a0db', 'a4ga2', '2023-04-11 09:31:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('55f45184-b2fb-4497-89c7-874b069e6dfd', 'e2a4p', '2023-04-11 09:34:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5695de00-2bfe-4454-86d4-f3d90f6217b5', 'ccncg', '2023-04-11 08:53:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('59755ed9-a3db-460d-82a9-47d853f2b36c', 'xy3g4', '2023-04-11 08:53:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('59d32a93-213b-4b33-8d1d-2ef6a3dd4a1d', 'ymx78', '2023-04-11 08:20:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5de8806e-5c3e-48c2-8ade-f3f485e3d12c', '2np6d', '2023-04-06 20:17:16', '');
INSERT INTO `sys_captcha` VALUES ('5eedf690-cb7c-4996-83de-1056cab6b61a', 'nnwe4', '2023-04-11 08:22:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6358615f-c520-45c2-8300-97b3c128d506', 'gyb7c', '2023-04-11 09:34:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('664e80ff-6334-4c74-8f14-6d06fa23d288', 'mbwgn', '2023-04-11 08:20:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6e51143f-134b-4356-8418-9925e7852981', 'ye7xn', '2023-04-11 08:24:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('75d4ab74-655f-4349-81e8-ffaf62a659f4', '4gaee', '2023-04-11 09:32:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('793f1918-3a7b-48e1-89e7-ead230656c01', 'wa34g', '2023-04-11 08:53:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8288d6b3-ccf5-4190-8a5e-d36a30c96306', 'fnna3', '2023-04-11 08:23:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8a11e1b0-f8c6-4f8f-8603-942f7724ff80', 'w3fyg', '2023-04-11 08:53:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8aa545b9-1c2e-4a03-810c-1b87701d26aa', 'gdyga', '2023-04-10 04:42:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('998d412b-99bb-4748-81c5-be8848eac66e', '7pmyb', '2023-04-10 04:45:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a31812dd-07a8-42c2-820a-501c4b5a15b1', '7fwwe', '2023-04-11 08:53:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a31c1831-a622-45a5-8dba-d0c9f9b8da5a', '6en6a', '2023-04-11 09:33:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ab1e4b90-8d8d-42d9-8b28-175501fb17ba', 'm5pyc', '2023-04-11 08:53:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b6bcf56a-1cc2-423d-8730-7974723704a1', 'wmp3m', '2023-04-10 06:05:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cba2dba1-08e7-4a4c-809b-eb3d714f356a', 'dypf4', '2023-04-11 09:33:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d08eafa2-0544-4c8d-876b-0ec1b08050c8', 'ge3x8', '2023-04-10 06:17:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('dc73f26d-1882-456a-8ac5-e02b9b874ffb', 'cb2bg', '2023-04-11 08:53:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('dde1dbba-42b5-4056-8073-231ef77c57b8', 'e4422', '2023-04-10 06:18:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ea991ea6-0e08-4e03-8287-3c4baf593467', '8n4m6', '2023-04-11 09:38:00', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ec2df77a-71fd-421b-81e4-9621de976303', 'pyd4d', '2023-04-11 08:53:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f33cecd9-9620-4f89-8322-438d40b1d706', 'x3m58', '2023-04-11 08:53:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f3e4cc31-3c82-4ce4-8be5-802888d17245', 'w5b27', '2023-04-11 08:53:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f46ac8ce-ce12-47d8-838b-b63255c6d2f0', 'efa4w', '2023-04-11 08:25:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ff7194f3-85dc-4f75-84a9-fd208a40196b', '6n67y', '2023-04-11 09:36:51', '租户编码');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息', '');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"管理员列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"admin\",\"orderNum\":1}]', 16, '127.0.0.1', '2023-04-06 20:16:47', '');
INSERT INTO `sys_log` VALUES (2, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":15,\"parentId\":2,\"name\":\"查看\",\"perms\":\"sys:user:list,sys:user:info\",\"type\":2,\"orderNum\":0}]', 14, '127.0.0.1', '2023-04-06 20:18:26', '');
INSERT INTO `sys_log` VALUES (3, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":16,\"parentId\":2,\"name\":\"新增\",\"perms\":\"sys:user:save,sys:role:select\",\"type\":2,\"orderNum\":0}]', 10, '127.0.0.1', '2023-04-06 20:18:47', '');
INSERT INTO `sys_log` VALUES (4, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":17,\"parentId\":2,\"name\":\"修改\",\"perms\":\"sys:user:update,sys:role:select\",\"type\":2,\"orderNum\":0}]', 12, '127.0.0.1', '2023-04-06 20:18:58', '');
INSERT INTO `sys_log` VALUES (5, 'admin', '修改菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.update()', '[{\"menuId\":18,\"parentId\":2,\"name\":\"删除\",\"perms\":\"sys:user:delete\",\"type\":2,\"orderNum\":0}]', 11, '127.0.0.1', '2023-04-06 20:19:09', '');
INSERT INTO `sys_log` VALUES (6, 'admin', '保存配置', 'com.maibaduoduo.service.sys.controller.SysConfigController.save()', '[{\"id\":2,\"paramKey\":\"参数\",\"paramValue\":\"1\",\"remark\":\"\"}]', 203, '127.0.0.1', '2023-04-06 20:19:40', '');
INSERT INTO `sys_log` VALUES (7, 'admin', '删除配置', 'com.maibaduoduo.service.sys.controller.SysConfigController.delete()', '[[2]]', 36, '127.0.0.1', '2023-04-06 20:19:45', '');
INSERT INTO `sys_log` VALUES (8, 'admin', '删除菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.delete()', '[6]', 0, '127.0.0.1', '2023-04-06 20:20:18', '');
INSERT INTO `sys_log` VALUES (9, 'admin', '删除菜单', 'com.maibaduoduo.service.sys.controller.SysMenuController.delete()', '[6]', 0, '127.0.0.1', '2023-04-06 20:20:36', '');
INSERT INTO `sys_log` VALUES (10, 'admin', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"zncdz\",\"password\":\"5984385e3eff2d8c36e802fb6970b3e1e930ffa5a64fd170463fb1c2509b76b5\",\"salt\":\"Mw2Zc1E4OW5ThtAXvKFO\",\"email\":\"zncdZ@yeah.net\",\"mobile\":\"13298765432\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 6, 2023 8:21:56 PM\"}]', 54, '127.0.0.1', '2023-04-06 20:21:57', '');
INSERT INTO `sys_log` VALUES (11, 'admin', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"zncdz\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[-666666],\"createTime\":\"Apr 6, 2023 8:22:19 PM\"}]', 40, '127.0.0.1', '2023-04-06 20:22:20', '');
INSERT INTO `sys_log` VALUES (12, 'admin', '修改角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"zncdz\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,27,29,30,-666666,1]}]', 67, '127.0.0.1', '2023-04-06 20:22:34', '');
INSERT INTO `sys_log` VALUES (13, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"zncdz\",\"salt\":\"Mw2Zc1E4OW5ThtAXvKFO\",\"email\":\"zncdZ@yeah.net\",\"mobile\":\"13298765432\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 18, '127.0.0.1', '2023-04-06 20:23:01', '');
INSERT INTO `sys_log` VALUES (14, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 17, '127.0.0.1', '2023-04-06 20:23:07', '');
INSERT INTO `sys_log` VALUES (15, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 12, '127.0.0.1', '2023-04-06 20:23:13', '');
INSERT INTO `sys_log` VALUES (16, 'zncdz', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":3,\"username\":\"hello\",\"password\":\"4c58f4665324d579e932c7ea3a90d11cf66eadfeb8df72d5b911591fda237a67\",\"salt\":\"YbfDaJgGo7gKultwomsd\",\"email\":\"hello@yeah.net\",\"mobile\":\"13512348765\",\"status\":1,\"roleIdList\":[],\"createUserId\":2,\"createTime\":\"Apr 6, 2023 8:23:58 PM\"}]', 10, '127.0.0.1', '2023-04-06 20:23:59', '');
INSERT INTO `sys_log` VALUES (17, 'zncdz', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"devuser\",\"remark\":\"开发员工\",\"createUserId\":2,\"menuIdList\":[27,29,30,-666666,1],\"createTime\":\"Apr 6, 2023 8:25:52 PM\"}]', 28, '127.0.0.1', '2023-04-06 20:25:53', '');
INSERT INTO `sys_log` VALUES (18, 'zncdz', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"devuser\",\"remark\":\"开发员工\",\"createUserId\":2,\"menuIdList\":[27,29,30,-666666,1],\"createTime\":\"Apr 6, 2023 8:25:53 PM\"}]', 25, '127.0.0.1', '2023-04-06 20:25:54', '');
INSERT INTO `sys_log` VALUES (19, 'zncdz', '删除角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.delete()', '[[3]]', 14, '127.0.0.1', '2023-04-06 20:26:01', '');
INSERT INTO `sys_log` VALUES (20, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":6,\"username\":\"hello1\",\"password\":\"1cb9cd494c7e4ec67675afd75509ff7759641e62f685dd137a1542cd8e39ca4d\",\"salt\":\"8NY8LQyYkpo9MAjLqDVi\",\"email\":\"hello@yeah.net\",\"mobile\":\"13523457653\",\"status\":1,\"roleIdList\":[],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 5:11:42 PM\"}]', 31, '127.0.0.1', '2023-04-10 09:11:42', '租户编码');
INSERT INTO `sys_log` VALUES (21, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":7,\"username\":\"hello132213\",\"password\":\"674852436485905a4a85c5da3537c7cf873717121439a60fbdb46baa6fbeabf4\",\"salt\":\"ukPnABmlLkPxdbs6bdgs\",\"email\":\"fdsf@yeah.net\",\"mobile\":\"13512348765\",\"status\":1,\"roleIdList\":[],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 5:18:38 PM\"}]', 211, '127.0.0.1', '2023-04-10 09:18:38', '租户编码');
INSERT INTO `sys_log` VALUES (22, '214579869365829632', '保存角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"hellorole\",\"remark\":\"\",\"createUserId\":4,\"menuIdList\":[2,15,16,17,18,-666666,1],\"createTime\":\"Apr 10, 2023 5:19:13 PM\"}]', 65, '127.0.0.1', '2023-04-10 09:19:13', '租户编码');
INSERT INTO `sys_log` VALUES (23, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":8,\"username\":\"test123\",\"password\":\"5d38940b6f230ebe22813afb36c89c5d49513b45754a968994a68856dcf08f73\",\"salt\":\"UMjsohlUriqd8cNyn4x4\",\"email\":\"test@yeah.net\",\"mobile\":\"16543216789\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 8:37:51 PM\"}]', 267, '127.0.0.1', '2023-04-10 12:37:52', '租户编码');
INSERT INTO `sys_log` VALUES (24, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":9,\"username\":\"test12\",\"password\":\"e4ca41a9513d4f4363b630fef3aa9aca6f5a2bb1c2756e2e144ce5c4dbc09783\",\"salt\":\"wv8JhCuthxgRDCWV2Kku\",\"email\":\"test@yeah.net\",\"mobile\":\"13512345678\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 8:41:47 PM\"}]', 21, '127.0.0.1', '2023-04-10 12:41:47', '租户编码');
INSERT INTO `sys_log` VALUES (25, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"test123456\",\"password\":\"5b6a62427ffa35e7c884d084f87764e024aee03bfccccc4158921e490e3649f7\",\"salt\":\"RKXGRcZDHgQvafP5eXF3\",\"email\":\"123456@yeah.net\",\"mobile\":\"12345678765\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 8:46:19 PM\"}]', 26424, '127.0.0.1', '2023-04-10 12:46:46', '租户编码');
INSERT INTO `sys_log` VALUES (26, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":11,\"username\":\"ewqeqewq\",\"password\":\"f030746ee032ddbae2115b0d27e251e97c74add90a4ed1bf4b6956a0697d2d93\",\"salt\":\"vzAkoclqRE7icc5iChxy\",\"email\":\"213@yeah.net\",\"mobile\":\"13512345678\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 9:21:29 PM\"}]', 18269, '127.0.0.1', '2023-04-10 13:21:47', '租户编码');
INSERT INTO `sys_log` VALUES (27, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":12,\"username\":\"fdsafdsafdsa\",\"password\":\"a099176f7372d027bf9e0b29f5bc2e7ceeed95b028f592a8743e5c3a35f571f2\",\"salt\":\"fK7dle8OE5ecVEljyCih\",\"email\":\"123@yeah.net\",\"mobile\":\"13512345678\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 10, 2023 9:23:04 PM\"}]', 7691, '127.0.0.1', '2023-04-10 13:23:12', '租户编码');
INSERT INTO `sys_log` VALUES (28, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":13,\"username\":\"lifeng\",\"password\":\"d0355ddb65450f82f0f965b5c3c359e09d8359a62e928ddf19f1fcda97e1c8dc\",\"salt\":\"rrYynsgVkowwyscnnJBI\",\"email\":\"lifeng@yeah.net\",\"mobile\":\"13512349876\",\"status\":0,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 11, 2023 4:19:02 PM\",\"tenantId\":\"214579869365829632\"}]', 211, '127.0.0.1', '2023-04-11 08:19:03', '租户编码');
INSERT INTO `sys_log` VALUES (29, '214579869365829632', '删除用户', 'com.maibaduoduo.service.sys.controller.SysUserController.delete()', '[[13]]', 19, '127.0.0.1', '2023-04-11 08:38:51', '租户编码');
INSERT INTO `sys_log` VALUES (30, '214579869365829632', '删除用户', 'com.maibaduoduo.service.sys.controller.SysUserController.delete()', '[[6,7,8,9,10,11]]', 12, '127.0.0.1', '2023-04-11 08:39:06', '租户编码');
INSERT INTO `sys_log` VALUES (31, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":14,\"username\":\"lifeng\",\"password\":\"e5103d5f9d2239756967533bfe3ced694c3da16189dcd1ab7ef67c840dcdd5e4\",\"salt\":\"JwelPBRKZpX88QvWnGGm\",\"email\":\"lifeng@yeah.net\",\"mobile\":\"13512345678\",\"status\":0,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 11, 2023 4:39:41 PM\",\"tenantId\":\"214579869365829632\"}]', 941, '127.0.0.1', '2023-04-11 08:39:42', '租户编码');
INSERT INTO `sys_log` VALUES (32, '214579869365829632', '修改角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.update()', '[{\"roleId\":3,\"roleName\":\"hellorole\",\"remark\":\"\",\"createUserId\":4,\"menuIdList\":[2,15,16,17,18,29,-666666,1]}]', 55, '127.0.0.1', '2023-04-11 09:25:19', '租户编码');
INSERT INTO `sys_log` VALUES (33, '214579869365829632', '修改角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.update()', '[{\"roleId\":3,\"roleName\":\"hellorole\",\"remark\":\"\",\"createUserId\":4,\"menuIdList\":[2,15,16,17,18,29,30,-666666,1]}]', 48, '127.0.0.1', '2023-04-11 09:25:27', '租户编码');
INSERT INTO `sys_log` VALUES (34, '214579869365829632', '修改角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.update()', '[{\"roleId\":3,\"roleName\":\"hellorole\",\"remark\":\"\",\"createUserId\":4,\"menuIdList\":[2,15,16,17,18,27,29,30,-666666,1]}]', 47, '127.0.0.1', '2023-04-11 09:25:39', '租户编码');
INSERT INTO `sys_log` VALUES (35, '214579869365829632', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":14,\"username\":\"lifeng\",\"password\":\"e5103d5f9d2239756967533bfe3ced694c3da16189dcd1ab7ef67c840dcdd5e4\",\"salt\":\"JwelPBRKZpX88QvWnGGm\",\"email\":\"lifeng@yeah.net\",\"mobile\":\"13512345678\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4}]', 56, '127.0.0.1', '2023-04-11 09:26:05', '租户编码');
INSERT INTO `sys_log` VALUES (36, '214579869365829632', '修改角色', 'com.maibaduoduo.service.sys.controller.SysRoleController.update()', '[{\"roleId\":3,\"roleName\":\"hellorole\",\"remark\":\"\",\"createUserId\":4,\"menuIdList\":[27,29,30,-666666,1]}]', 32, '127.0.0.1', '2023-04-11 09:27:44', '租户编码');
INSERT INTO `sys_log` VALUES (37, '214579869365829632', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":15,\"username\":\"employee\",\"password\":\"60d42e46ddae09c1811025ca7a85049ef5b826807ba1943071f5b52c12bca0f3\",\"salt\":\"62b5jPxw329Dun1TL1V5\",\"email\":\"employee@yeah.net\",\"mobile\":\"13512345678\",\"status\":0,\"roleIdList\":[3],\"createUserId\":4,\"createTime\":\"Apr 11, 2023 5:28:28 PM\",\"tenantId\":\"214579869365829632\"}]', 79, '127.0.0.1', '2023-04-11 09:28:29', '租户编码');
INSERT INTO `sys_log` VALUES (38, '214579869365829632', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":15,\"username\":\"employee\",\"password\":\"60d42e46ddae09c1811025ca7a85049ef5b826807ba1943071f5b52c12bca0f3\",\"salt\":\"62b5jPxw329Dun1TL1V5\",\"email\":\"employee@yeah.net\",\"mobile\":\"13512345678\",\"status\":1,\"roleIdList\":[3],\"createUserId\":4}]', 19, '127.0.0.1', '2023-04-11 09:32:08', '租户编码');
INSERT INTO `sys_log` VALUES (39, '217427874431369216', '保存用户', 'com.maibaduoduo.web.controller.SysUserController.save()', '[{\"userId\":7,\"username\":\"2172006465609400320001\",\"password\":\"363d0b621dca77bc88621080e398221f59f98f547982fbc707ccde0dc987e076\",\"salt\":\"kp1FEcW8fwa5Y8PEx5mY\",\"email\":\"001@yeah.net\",\"mobile\":\"13512345432\",\"status\":0,\"roleIdList\":[],\"createUserId\":6,\"createTime\":\"Apr 17, 2023 2:01:08 PM\",\"tenantId\":\"217427874431369216\"}]', 6486, '127.0.0.1', '2023-04-17 06:01:15', '租户编码');
INSERT INTO `sys_log` VALUES (40, '217441134060765184', '保存用户', 'com.maibaduoduo.web.controller.SysUserController.save()', '[{\"userId\":10,\"username\":\"217441134060765184002\",\"password\":\"d0a5a1626132ef48b1abd60796ecbe5c69138ab7ae2111f7603c20bb5e8bd1c0\",\"salt\":\"LXAnXU2m14tRmFzDW7Xi\",\"email\":\"002@yeah.net\",\"mobile\":\"13513458765\",\"status\":0,\"roleIdList\":[],\"createUserId\":9,\"createTime\":\"Apr 17, 2023 2:16:08 PM\",\"tenantId\":\"217441134060765184\"}]', 25, '127.0.0.1', '2023-04-17 06:16:08', '租户编码');
INSERT INTO `sys_log` VALUES (41, '217441134060765184002', '保存角色', 'com.maibaduoduo.web.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"view\",\"remark\":\"view\",\"createUserId\":10,\"menuIdList\":[15,19,-666666,1,2,3],\"createTime\":\"Apr 17, 2023 2:17:55 PM\"}]', 59, '127.0.0.1', '2023-04-17 06:17:56', '租户编码');
INSERT INTO `sys_log` VALUES (42, '217441134060765184', '保存角色', 'com.maibaduoduo.web.controller.SysRoleController.save()', '[{\"roleId\":5,\"roleName\":\"adminview\",\"remark\":\"adminview\",\"createUserId\":9,\"menuIdList\":[15,19,23,-666666,1,2,3,4],\"createTime\":\"Apr 17, 2023 2:20:09 PM\"}]', 51, '127.0.0.1', '2023-04-17 06:20:09', '租户编码');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：employee:list,employee:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0, '');
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1, '');
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2, '');
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3, '');
INSERT INTO `sys_menu` VALUES (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4, '');
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5, '');
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0, '');
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6, '');
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7, '');
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6, '');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'zncdz', '', 1, '2023-04-06 20:22:20', '');
INSERT INTO `sys_role` VALUES (2, 'devuser', '开发员工', 2, '2023-04-06 20:25:53', '');
INSERT INTO `sys_role` VALUES (3, 'hellorole', '', 4, '2023-04-10 09:19:13', '租户编码');
INSERT INTO `sys_role` VALUES (4, 'view', 'view', 10, '2023-04-17 06:17:56', '租户编码');
INSERT INTO `sys_role` VALUES (5, 'adminview', 'adminview', 9, '2023-04-17 06:20:09', '租户编码');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, '');
INSERT INTO `sys_role_menu` VALUES (3, 1, 15, '');
INSERT INTO `sys_role_menu` VALUES (4, 1, 16, '');
INSERT INTO `sys_role_menu` VALUES (5, 1, 17, '');
INSERT INTO `sys_role_menu` VALUES (6, 1, 18, '');
INSERT INTO `sys_role_menu` VALUES (7, 1, 3, '');
INSERT INTO `sys_role_menu` VALUES (8, 1, 19, '');
INSERT INTO `sys_role_menu` VALUES (9, 1, 20, '');
INSERT INTO `sys_role_menu` VALUES (10, 1, 21, '');
INSERT INTO `sys_role_menu` VALUES (11, 1, 22, '');
INSERT INTO `sys_role_menu` VALUES (12, 1, 4, '');
INSERT INTO `sys_role_menu` VALUES (13, 1, 23, '');
INSERT INTO `sys_role_menu` VALUES (14, 1, 24, '');
INSERT INTO `sys_role_menu` VALUES (15, 1, 25, '');
INSERT INTO `sys_role_menu` VALUES (16, 1, 26, '');
INSERT INTO `sys_role_menu` VALUES (17, 1, 27, '');
INSERT INTO `sys_role_menu` VALUES (18, 1, 29, '');
INSERT INTO `sys_role_menu` VALUES (19, 1, 30, '');
INSERT INTO `sys_role_menu` VALUES (20, 1, -666666, '');
INSERT INTO `sys_role_menu` VALUES (21, 1, 1, '');
INSERT INTO `sys_role_menu` VALUES (22, 2, 27, '');
INSERT INTO `sys_role_menu` VALUES (23, 2, 29, '');
INSERT INTO `sys_role_menu` VALUES (24, 2, 30, '');
INSERT INTO `sys_role_menu` VALUES (25, 2, -666666, '');
INSERT INTO `sys_role_menu` VALUES (26, 2, 1, '');
INSERT INTO `sys_role_menu` VALUES (61, 3, 27, '租户编码');
INSERT INTO `sys_role_menu` VALUES (62, 3, 29, '租户编码');
INSERT INTO `sys_role_menu` VALUES (63, 3, 30, '租户编码');
INSERT INTO `sys_role_menu` VALUES (64, 3, -666666, '租户编码');
INSERT INTO `sys_role_menu` VALUES (65, 3, 1, '租户编码');
INSERT INTO `sys_role_menu` VALUES (66, 4, 15, '租户编码');
INSERT INTO `sys_role_menu` VALUES (67, 4, 19, '租户编码');
INSERT INTO `sys_role_menu` VALUES (68, 4, -666666, '租户编码');
INSERT INTO `sys_role_menu` VALUES (69, 4, 1, '租户编码');
INSERT INTO `sys_role_menu` VALUES (70, 4, 2, '租户编码');
INSERT INTO `sys_role_menu` VALUES (71, 4, 3, '租户编码');
INSERT INTO `sys_role_menu` VALUES (72, 5, 15, '租户编码');
INSERT INTO `sys_role_menu` VALUES (73, 5, 19, '租户编码');
INSERT INTO `sys_role_menu` VALUES (74, 5, 23, '租户编码');
INSERT INTO `sys_role_menu` VALUES (75, 5, -666666, '租户编码');
INSERT INTO `sys_role_menu` VALUES (76, 5, 1, '租户编码');
INSERT INTO `sys_role_menu` VALUES (77, 5, 2, '租户编码');
INSERT INTO `sys_role_menu` VALUES (78, 5, 3, '租户编码');
INSERT INTO `sys_role_menu` VALUES (79, 5, 4, '租户编码');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11', '');
INSERT INTO `sys_user` VALUES (2, 'zncdz', '5984385e3eff2d8c36e802fb6970b3e1e930ffa5a64fd170463fb1c2509b76b5', 'Mw2Zc1E4OW5ThtAXvKFO', 'zncdZ@yeah.net', '13298765432', 1, 1, '2023-04-06 20:21:57', '');
INSERT INTO `sys_user` VALUES (3, '216680240133439488', '5efc05f6403836a41f9325e19a52c3424e9497cffba80775ef795c73b9aa300b', '7J1h9S0VMrtuNS2fZYMZ', NULL, NULL, 0, NULL, '2023-04-15 03:51:39', '216680240133439488');
INSERT INTO `sys_user` VALUES (4, '217199270300745728', '429d96138fddabd2e038e1e7df7912bead814ae84276e7da99ede2bd84a921ed', 'x8tK1PqlAdfaw2XPvNOI', NULL, NULL, 0, NULL, '2023-04-16 14:13:55', '217199270300745728');
INSERT INTO `sys_user` VALUES (5, '217199537595351040', '91cc84ccc5e4f07462da46c528bcf69e0a988efc359c8bb402b71ac7b13df273', 'HRgZwHAhSBTWww6ye1Fh', NULL, NULL, 0, NULL, '2023-04-16 14:14:58', '217199537595351040');
INSERT INTO `sys_user` VALUES (6, '217427874431369216', '125e71db0840d8966bf2bb938bcc75c5a9b6d7e8df048b4465da2623e0471d43', '2WV7PNwTWXlPPaFC8enr', NULL, NULL, 1, NULL, '2023-04-17 05:34:32', '217427874431369216');
INSERT INTO `sys_user` VALUES (7, '2172006465609400320001', '363d0b621dca77bc88621080e398221f59f98f547982fbc707ccde0dc987e076', 'kp1FEcW8fwa5Y8PEx5mY', '001@yeah.net', '13512345432', 1, 6, '2023-04-17 06:01:09', '217427874431369216');
INSERT INTO `sys_user` VALUES (8, '217439550253826048', 'cd281d950cb3aa72c7a5805b162dc16f0cf8bef91d21453a0a9b637e35075927', '4mSvnBxsZaCOZdd5iUhb', NULL, NULL, 0, NULL, '2023-04-17 06:09:01', '217439550253826048');
INSERT INTO `sys_user` VALUES (9, '217441134060765184', '9d763d8199d0413f6f2416e04acb29ae995eaa513b7149ad954a7313a86fe702', 'B6syNdmJLQaE2CfVALoL', NULL, NULL, 1, NULL, '2023-04-17 06:14:59', '217441134060765184');
INSERT INTO `sys_user` VALUES (10, '217441134060765184002', 'd0a5a1626132ef48b1abd60796ecbe5c69138ab7ae2111f7603c20bb5e8bd1c0', 'LXAnXU2m14tRmFzDW7Xi', '002@yeah.net', '13513458765', 1, 9, '2023-04-17 06:16:08', '217441134060765184');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 2, 1, '');
INSERT INTO `sys_user_role` VALUES (3, 3, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (4, 4, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (5, 5, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (7, 6, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (8, 7, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (9, 8, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (11, 9, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (12, 10, 1, '租户编码');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 'b632eaac9282da58ef348b398bec465f', '2023-04-10 14:53:08', '2023-04-10 02:53:08', '');
INSERT INTO `sys_user_token` VALUES (2, '8c421a02ff56a7521d9ece7b734a92ad', '2023-04-07 08:23:27', '2023-04-06 20:23:27', '');
INSERT INTO `sys_user_token` VALUES (4, 'f37dbb778e1279049ee6b7b53870ac7d', '2023-04-11 21:31:58', '2023-04-11 09:31:58', '租户编码');
INSERT INTO `sys_user_token` VALUES (6, '63ca10b9ce3cf2e7a04ac9ec0ea2a5e7', '2023-04-17 17:56:25', '2023-04-17 05:56:25', '217427874431369216');
INSERT INTO `sys_user_token` VALUES (7, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyMTcyMDA2NDY1NjA5NDAwMzIwMDAxLDIxNzQyNzg3NDQzMTM2OTIxNiIsImlhdCI6MTY4MTcxMTMxNiwiZXhwIjoxNjgyMzE2MTE2fQ.dEIHKVpcRzWiAguDUNQWXgh3-3JBhBKW9ccFjZgx-4YMh4OSESiycSkzHwFwMvXaun9G89GZHMuXOhouSpnvcA', '2023-04-17 18:01:57', '2023-04-17 06:01:57', '217427874431369216');
INSERT INTO `sys_user_token` VALUES (9, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyMTc0NDExMzQwNjA3NjUxODQsMjE3NDQxMTM0MDYwNzY1MTg0IiwiaWF0IjoxNjgxNzI2NTc5LCJleHAiOjE2ODIzMzEzNzl9.kTKeTplk_Qu9PC3XuCPLV4x-ExhDEAEqJWBGd0jPculhTnBCwFimKUiTQpgn8ipXAU1SIvuyqLm5Hluj00d6tQ', '2023-04-17 22:16:20', '2023-04-17 10:16:20', '217441134060765184');
INSERT INTO `sys_user_token` VALUES (10, '91c49f673689ba626d45d056b9ffdb47', '2023-04-17 18:20:37', '2023-04-17 06:20:37', '217441134060765184');
INSERT INTO `sys_user_token` VALUES (12, '4fb77aeb8c73754440a57d2a4a733125', '2023-04-11 21:27:06', '2023-04-11 09:27:06', 'fdsafdsafdsa');
INSERT INTO `sys_user_token` VALUES (14, '77bfa5287a8d84c08d80b7d2366fb83e', '2023-04-11 21:26:17', '2023-04-11 09:26:17', 'lifeng');
INSERT INTO `sys_user_token` VALUES (15, '0ed41c7b7c152dd31eb3109141ca1fd8', '2023-04-11 21:32:25', '2023-04-11 09:32:25', 'employee');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41', '');

-- ----------------------------
-- Table structure for test_tree
-- ----------------------------
DROP TABLE IF EXISTS `test_tree`;
CREATE TABLE `test_tree`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_tree_del_flag`(`del_flag`) USING BTREE,
  INDEX `test_data_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '树结构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_tree
-- ----------------------------
INSERT INTO `test_tree` VALUES ('952da0305213401da7e081f86188b818', '0', '0,', '求购管理', 30, '1', '2021-08-17 15:32:09', '1', '2021-08-17 15:32:09', '', '0', '');
INSERT INTO `test_tree` VALUES ('bcc6b85ba6f8496b8e3f08c1478f0083', '952da0305213401da7e081f86188b818', '0,952da0305213401da7e081f86188b818,', '订单管理', 30, '1', '2021-08-17 15:32:16', '1', '2021-08-17 15:32:16', '', '0', '');

SET FOREIGN_KEY_CHECKS = 1;
