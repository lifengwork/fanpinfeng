/*
 Navicat Premium Data Transfer

 Source Server         : zncdz
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : default

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 27/04/2023 18:41:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'dev_01', 'DEFAULT_GROUP', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/saas/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  }\n  \n]\n\n ', 'ed608323dd1a5f2dc95655ece9c2daa9', '2023-04-13 06:37:20', '2023-04-20 04:58:24', 'nacos', '0:0:0:0:0:0:0:1', 'system-service', '', '', '', '', 'json', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'dev_01', 'DEFAULT_GROUP', '', '- id: system-service\r\n  uri: lb://system-service\r\n  predicates:\r\n    - Path=/api/**\r\n  filters:\r\n    - StripPrefix=1\r\n- id: oauth2-auth-route\r\n  uri: lb://maibaduoduo-auth-handle\r\n  predicates:\r\n    - Path=/auth/**\r\n  filters:\r\n    - StripPrefix=1\r\n    - name: RequestRateLimiter\r\n      args:\r\n        key-resolver: \'#{@pathKeyResolver}\'\r\n        redis-rate-limiter.replenishRate: 1\r\n        redis-rate-limiter.burstCapacity: 3', '2e38ed3ec4d2dfe255ad6f9a2f9e6639', '2023-04-13 14:37:19', '2023-04-13 06:37:20', 'nacos', '0:0:0:0:0:0:0:1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'dev_01', 'DEFAULT_GROUP', '', '- id: system-service\r\n  uri: lb://system-service\r\n  predicates:\r\n    - Path=/api/**\r\n  filters:\r\n    - StripPrefix=1\r\n- id: oauth2-auth-route\r\n  uri: lb://maibaduoduo-auth-handle\r\n  predicates:\r\n    - Path=/auth/**\r\n  filters:\r\n    - StripPrefix=1\r\n    - name: RequestRateLimiter\r\n      args:\r\n        key-resolver: \'#{@pathKeyResolver}\'\r\n        redis-rate-limiter.replenishRate: 1\r\n        redis-rate-limiter.burstCapacity: 3', '2e38ed3ec4d2dfe255ad6f9a2f9e6639', '2023-04-13 14:38:19', '2023-04-13 06:38:19', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 3, 'dev_01', 'DEFAULT_GROUP', 'system-service', '- id: system-service\n  uri: lb://system-service\n  predicates:\n    - Path=/api/**\n  filters:\n    - StripPrefix=1\n- id: oauth2-auth-route\n  uri: lb://maibaduoduo-auth-handle\n  predicates:\n    - Path=/auth/**\n  filters:\n    - StripPrefix=1\n    - name: RequestRateLimiter\n      args:\n        key-resolver: \'#{@pathKeyResolver}\'\n        redis-rate-limiter.replenishRate: 1\n        redis-rate-limiter.burstCapacity: 3', 'e4f8ef1d6a9d97f838546dfbf38ec879', '2023-04-13 15:04:34', '2023-04-13 07:04:35', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 4, 'dev_01', 'DEFAULT_GROUP', 'system-service', '- id: system-service\n  uri: lb://system-service\n  predicates:\n    - Path=/api/u/**\n  filters:\n    - StripPrefix=2\n- id: oauth2-auth-route\n  uri: lb://maibaduoduo-auth-handle\n  predicates:\n    - Path=/auth/**\n  filters:\n    - StripPrefix=1\n    - name: RequestRateLimiter\n      args:\n        key-resolver: \'#{@pathKeyResolver}\'\n        redis-rate-limiter.replenishRate: 1\n        redis-rate-limiter.burstCapacity: 3', 'd0cc57f3d3557ff83de9b8b6aeff3cc7', '2023-04-14 08:50:58', '2023-04-14 00:50:59', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'dev_01', 'DEFAULT_GROUP', 'system-service', '- id: system-service\n  uri: lb://system-service-app\n  predicates:\n    - Path=/api/u/**\n  filters:\n    - StripPrefix=2\n- id: system-service\n    uri: lb://system-service-app\n    predicates:\n      - Path=/api/app/**\n    filters:\n      - StripPrefix=1\n- id: system-service-file\n    uri: lb://system-service-app\n    predicates:\n      - Path=/api/f/**\n    filters:\n      - StripPrefix=1\n- id: oauth2-auth-route\n  uri: lb://maibaduoduo-auth-handle\n  predicates:\n    - Path=/auth/**\n  filters:\n    - StripPrefix=1\n    - name: RequestRateLimiter\n      args:\n        key-resolver: \'#{@pathKeyResolver}\'\n        redis-rate-limiter.replenishRate: 1\n        redis-rate-limiter.burstCapacity: 3', '5e11eb13d96ac108a24154da98f5b23f', '2023-04-20 07:13:56', '2023-04-19 23:13:56', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 6, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n{\n  id: system-service\n  uri: lb://system-service\n  predicates:\n	  Path=/api/u/**\n  filters:\n	  StripPrefix=2\n},\n{ id: system-service-app\n  uri: lb://system-service-app\n  predicates:\n	  Path=/api/app/**\n  filters:\n	  StripPrefix=1\n},\n{ id: system-service-file\n  uri: lb://system-service-file\n  predicates:\n	  Path=/api/f/**\n  filters:\n	  StripPrefix=1\n},\n{ id: store-service\n  uri: lb://store-service\n  predicates:\n	  Path=/api/store/**\n  filters:\n	  StripPrefix=1\n},\n{ id: order-service\n  uri: lb://order-service\n  predicates:\n	  Path=/api/order/**\n  filters:\n	  StripPrefix=1\n	  name: RequestRateLimiter\n	  args:\n	 	 key-resolver: \'#{@pathKeyResolver}\'\n		 redis-rate-limiter.replenishRate: 1\n		 redis-rate-limiter.burstCapacity: 3\n}\n]		', 'e06a735969f9b8f596342ea928c4051e', '2023-04-20 08:00:29', '2023-04-20 00:00:29', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 7, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n  {\n    \"id\": \"system-service\",\n    \"url\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"url\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"url\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"url\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"url\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   {\n		     \"key-resolver\":\"#{@pathKeyResolver}\"\n		   },\n		   {\n		     \"redis-rate-limiter.replenishRate\": \"1\"			\n		   },\n		   {\n		     \"redis-rate-limiter.burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '2153670ed337f4876b334b51c69b52ed', '2023-04-20 08:00:34', '2023-04-20 00:00:34', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 8, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n  {\n    \"id\": \"system-service\",\n    \"url\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"url\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"url\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"url\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"url\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   {\n		     \"key-resolver\":\"#{@pathKeyResolver}\"\n		   },\n		   {\n		     \"redis-rate-limiter.replenishRate\": \"1\"			\n		   },\n		   {\n		     \"redis-rate-limiter.burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '2153670ed337f4876b334b51c69b52ed', '2023-04-20 08:05:25', '2023-04-20 00:05:26', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 9, 'dev_01', 'DEFAULT_GROUP', 'system-service', '\"routes\": [\n   {\n    \"id\": \"system-service\",\n    \"url\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"url\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"url\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"url\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"url\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   {\n		     \"key-resolver\":\"#{@pathKeyResolver}\"\n		   },\n		   {\n		     \"redis-rate-limiter.replenishRate\": \"1\"			\n		   },\n		   {\n		     \"redis-rate-limiter.burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', 'e1c2231e5ff66b9f8d49aca7c06b74f3', '2023-04-20 08:06:41', '2023-04-20 00:06:42', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 10, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"url\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"url\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"url\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"url\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"url\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   {\n		     \"key-resolver\":\"#{@pathKeyResolver}\"\n		   },\n		   {\n		     \"redis-rate-limiter.replenishRate\": \"1\"			\n		   },\n		   {\n		     \"redis-rate-limiter.burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '3466520faaaf31f9e78a81eec4b70cc0', '2023-04-20 08:07:59', '2023-04-20 00:08:00', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 11, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   {\n		     \"key-resolver\":\"#{@pathKeyResolver}\"\n		   },\n		   {\n		     \"redis-rate-limiter.replenishRate\": \"1\"			\n		   },\n		   {\n		     \"redis-rate-limiter.burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '65959727a9497da708c76b3408b8e530', '2023-04-20 08:23:08', '2023-04-20 00:23:09', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 12, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   { \n				 \"name\": \"key-resolver\",\n				 \"args\": {\n					 \"parts\": \"#{@pathKeyResolver}\"\n			}\n		   },\n		   {\n				 \"name\": \"redis-rate-limiter.replenishRate\",\n				 \"args\": {\n            \"parts\": \"1\"\n				 }		\n		   },\n		   {\n				 \"name\": \"redis-rate-limiter.burstCapacity\",\n				 \"args\": {\n					 \"parts\": \"3\"\n				 }\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '4220acb80c13bd01676f2204f7f50313', '2023-04-20 08:39:40', '2023-04-20 00:39:40', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 13, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   { \n				 \"name\": \"key-resolver\",\n				 \"keyResolver\": \"#{@pathKeyResolver}\" \n			},\n		   {\n				 \"name\": \"redis-rate-limiter.replenishRate\",\n				 \"redis-rate-limiter.replenishRate\": \"1\"\n		   },\n		   {\n				 \"name\": \"redis-rate-limiter.burstCapacity\",\n				 \"redis-rate-limiter.burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '955c2c01bedd8449517851df72bd2903', '2023-04-20 08:43:10', '2023-04-20 00:43:11', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 14, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   { \n				 \"name\": \"key-resolver\",\n				 \"keyResolver\": \"#{@pathKeyResolver}\" \n			},\n		   {\n				 \"name\": \"redis-rate-limiter\",\n				 \"replenishRate\": \"1\"\n		   },\n		   {\n				 \"name\": \"redis-rate-limiter\",\n				 \"burstCapacity\": \"3\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '380da71de893b981144717eceb234b06', '2023-04-20 08:55:23', '2023-04-20 00:55:23', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 15, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  },\n	  {\n	   \"name\": \"RequestRateLimiter\",\n		 \"args\": [\n		   { \n				 \"keyResolver\": \"#{@pathKeyResolver}\" \n			},\n		   {\n				 \"X-RateLimit-Replenish-Rate\": \"5\"\n		   },\n		   {\n				 \"X-RateLimit-Burst-Capacity\": \"10\"\n		   }\n		 ]\n	  }\n	]\n  }\n  \n]\n\n ', '04cf502a95e75cbf6eeb12691b0514a2', '2023-04-20 09:25:37', '2023-04-20 01:25:37', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 16, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  }\n  \n]\n\n ', 'bab2d0d0077adfb45b59e2691df9e360', '2023-04-20 09:29:00', '2023-04-20 01:29:00', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 17, 'dev_01', 'DEFAULT_GROUP', 'system-service', 'routes: \n		- id: system-service\n			uri: lb://system-service\n			predicates:\n				- Path=/api/u/**\n			filters:\n				- StripPrefix=2\n		- id: system-service-app\n			uri: lb://system-service-app\n			predicates:\n					- Path=/api/app/**\n			filters:\n					- StripPrefix=1\n		- id: system-service-file\n			uri: lb://system-service-file\n			predicates:\n					- Path=/api/f/**\n			filters:\n					- StripPrefix=1\n		- id: store-service\n			uri: lb://store-service\n			predicates:\n					- Path=/api/store/**\n			filters:\n					- StripPrefix=1\n		- id: order-service\n			uri: lb://order-service\n			predicates:\n				- Path=/api/order/**\n			filters:\n				- StripPrefix=1\n				- name: RequestRateLimiter\n					args:\n						key-resolver: \'#{@pathKeyResolver}\'\n						redis-rate-limiter.replenishRate: 1\n						redis-rate-limiter.burstCapacity: 3', '31228e09bb6a83b363b5e7e83e75e787', '2023-04-20 09:29:58', '2023-04-20 01:29:58', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 18, 'dev_01', 'DEFAULT_GROUP', 'system-service', '[\n   {\n    \"id\": \"system-service\",\n    \"uri\": \"lb://system-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/u/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-app\",\n    \"uri\": \"lb://system-service-app\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/app/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	  \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\": \"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"system-service-file\",\n    \"uri\": \"lb://system-service-file\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/f/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"store-service\",\n    \"uri\": \"lb://store-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/store/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  },\n  {\n    \"id\": \"order-service\",\n    \"uri\": \"lb://order-service\",\n    \"predicates\": [\n     {\n	  \"name\": \"Path\",\n	  \"args\": {\n	    \"pattern\": \"/api/order/**\"\n	   }\n	 } \n    ],\n	\"filters\": [\n	  {\n	    \"name\": \"StripPrefix\",\n		\"args\": {\n		  \"parts\":\"1\"\n		}\n	  }\n	]\n  }\n  \n]\n\n ', 'bab2d0d0077adfb45b59e2691df9e360', '2023-04-20 12:58:24', '2023-04-20 04:58:24', 'nacos', '0:0:0:0:0:0:0:1', 'U', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

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
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('00499aa8-4622-47fa-8a8a-c4560872b482', '52dap', '2023-04-11 16:45:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('01df0d81-3e02-40c9-898f-1b066138d4e1', 'd2gd3', '2023-04-16 11:42:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('04aa677a-81db-427f-8900-df82a5848d2c', 'd333m', '2023-04-10 12:44:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('04c698fa-e80b-4de9-84cb-340dcaeaaa9a', 'abypc', '2023-04-15 21:30:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('04f24373-39dd-48eb-8fed-c058f9658347', '5aayd', '2023-04-10 14:04:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('05031a34-7d69-482f-860d-c4e6ae360fbc', 'y7w8x', '2023-04-13 14:21:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('05bcf06c-6e0d-4964-83c7-3df71cc6df1a', 'd525a', '2023-04-17 14:21:32', '租户编码');
INSERT INTO `sys_captcha` VALUES ('05d7f67f-12d9-4103-876d-1a884e9e9c75', 'nnm7m', '2023-04-13 10:41:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('061eeac4-5676-4ef6-8629-1b92ca67ca33', 'cpf8b', '2023-04-10 13:16:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('06828dae-8c23-41a6-88d8-d66f9f049f6d', 'cm824', '2023-04-13 00:06:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0695a406-6083-4a7d-8a8a-fdda8ae18142', '4gg62', '2023-04-17 14:20:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('06e4470b-dc36-46eb-8cd3-7404b47901ed', 'nwy4w', '2023-04-17 14:25:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('06f0c855-3397-4002-8111-569318a0221a', 'd3ayf', '2023-04-10 14:59:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('07082b48-e39a-41e8-87af-7000a4d2cf5d', 'cy256', '2023-04-16 00:29:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0717860e-698d-4061-8f75-0fc8ff08f490', 'cf44a', '2023-04-16 00:14:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('07746287-332b-4e69-8970-cd6c60da393b', '8p4w5', '2023-04-10 09:23:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('07947528-215a-46e4-81e6-c864f20c1390', 'pg33d', '2023-04-10 21:39:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('07f77559-c691-490e-851b-ce70c80a89a5', 'ac33b', '2023-04-15 21:58:08', '租户编码');
INSERT INTO `sys_captcha` VALUES ('08f7d969-2305-4c1c-84f3-c4435bee3ebc', 'gmgd7', '2023-04-12 00:17:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0a8d3a45-3f7f-4dd9-80af-b4f75441cda4', 'wange', '2023-04-15 18:26:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0bef32f6-9ad0-4fb3-8a2a-c75702fa9e75', '7y2fy', '2023-04-10 11:26:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0c08619f-002a-40c9-8127-249270f95587', 'g8w3a', '2023-04-15 21:32:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0d32df58-bdc2-4274-81f7-8a80b03d6984', 'aw242', '2023-04-10 14:03:14', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0d5cf73c-21be-4b46-83f1-fd9decd774f2', 'begeg', '2023-04-10 17:09:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0d6dcfda-bba6-433e-876d-b178e75755c2', '6n3xg', '2023-04-11 16:22:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0dd1db23-b2f2-4f3d-824e-25904571ed76', '6nbbx', '2023-04-14 21:34:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0ed4a876-9e35-45da-84b6-f944e813cf5f', 'ne8ca', '2023-04-10 16:34:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0ee945b6-ab11-41e7-8a4e-50347772eb8f', '33xb5', '2023-04-10 11:34:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('0fdc40c4-ae54-468a-8100-b6bf7c79d4da', '6xcng', '2023-04-13 15:05:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('10293137-c06b-491b-84a2-6e69f9552f2e', 'yggga', '2023-04-11 15:46:39', '租户编码');
INSERT INTO `sys_captcha` VALUES ('10f8f437-6b95-4ed8-8df4-061a91ea071b', 'nd8n2', '2023-04-10 09:23:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1103d707-97f1-4601-864a-76615e836f91', 'f2ec4', '2023-04-15 21:13:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('11144dfe-19c0-4887-8019-35582da0cf41', 'b8xw5', '2023-04-09 23:50:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1145c511-f0d6-449f-875a-4e2e33b2ebdd', 'fyabg', '2023-04-09 23:30:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('11d92b06-4dc1-466c-8e95-16b62722f361', 'fpgd4', '2023-04-15 19:00:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('12924dd3-a5ae-43c5-8cfc-d4364521ad2a', 'a46y3', '2023-04-12 22:04:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1305dbcb-a910-4455-87ae-222bdfd1a19b', 'w3xef', '2023-04-10 09:58:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('13378d37-beac-4ada-83cc-903c50f2b0cd', '7fgn3', '2023-04-16 11:43:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1361ac09-d097-4380-8951-daa3de2c83df', 'f5en8', '2023-04-16 22:14:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('13c37638-678d-4610-8500-0e4c24b07331', 'ppwyp', '2023-04-12 23:15:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('14482322-cc3d-4991-8b60-bc0369b6be09', 'exan3', '2023-04-16 00:44:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('16a1406e-fed5-443f-8964-eb105911a860', 'd4684', '2023-04-16 00:46:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('17009f5f-360f-4658-88ac-b94c7f99df48', '7yemf', '2023-04-09 23:09:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('173e0d72-d126-4d06-8d78-f23b2e084764', 'm55pf', '2023-04-10 00:18:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('17d56a0f-065f-4561-888a-c6cc41d4f2de', '72npn', '2023-04-13 13:40:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('181fa951-6ca6-4703-82c5-1f724e3601a8', 'xf675', '2023-04-10 21:38:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('19798404-c633-4823-841b-0a936abf40de', '847wb', '2023-04-10 09:33:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('19cd96ee-993b-4e70-8389-b912bd161451', '3yn5e', '2023-04-10 21:45:32', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1a003892-f719-464f-84d0-65664ec06b0a', '7nec7', '2023-04-10 22:39:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1b9f94fa-5bae-4517-8486-38c88b753c63', 'n4ywn', '2023-04-13 15:12:15', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1be05bf5-993c-4d9c-8b9f-6f2b4075e7fb', '6pn2n', '2023-04-09 23:56:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1c084ddb-9e56-45a9-8765-2d41c15eac8e', 'mp75p', '2023-04-17 13:26:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1c7ceba6-276e-4e64-8835-566b05710888', 'pbn2m', '2023-04-17 14:27:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1d0ffa88-dcb0-46e2-8b1f-d504a7ae3320', 'wnaxx', '2023-04-10 16:59:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1d667340-e24b-4bad-8eab-62b7ad60adcb', 'fn8mw', '2023-04-11 23:17:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1d911b5e-b388-47bf-8f6a-a08830906bb5', 'anpwy', '2023-04-16 00:29:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1dd567af-8399-44e0-82fd-28599f24d92e', '4fx7y', '2023-04-16 00:53:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1e77cb90-5848-4cd9-821a-9876f3938d78', '26ngw', '2023-04-16 11:49:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1f047a9c-248b-4ebe-831a-cf0c747bb858', '82737', '2023-04-15 19:30:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1f201f06-2230-48f3-8df6-010382bdbd46', '75225', '2023-04-11 16:46:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('1fda8445-392b-48a0-8a84-1f75656298b5', 'w68ye', '2023-04-16 00:44:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('20bdb657-16e2-485b-8369-b65c606c367a', 'd8xwy', '2023-04-12 00:17:16', '租户编码');
INSERT INTO `sys_captcha` VALUES ('223c492a-b3d1-4243-82fd-5f661ccf0d92', '462yn', '2023-04-11 23:16:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2289b9d9-dbf0-41df-81b2-dce869e6a29d', 'a3n2y', '2023-04-12 00:11:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('230106a0-cfe7-45d9-875e-73460ce54495', '2n8cn', '2023-04-12 15:48:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2461e07c-efb3-4081-8301-43d51f3f837e', 'ng38x', '2023-04-10 13:45:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2463de24-98a0-48e2-8db9-eb1aa2cc9ff9', 'wxpw6', '2023-04-09 22:00:39', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2515fae8-1bd2-45e0-8803-26988a2eb8e7', 'nyg3b', '2023-04-10 11:03:04', '租户编码');
INSERT INTO `sys_captcha` VALUES ('25b52301-2103-4ca5-8808-80841c7b2bf7', 'mmc67', '2023-04-13 13:20:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('25d5f568-4569-4127-8f3c-38cb4e3e6903', 'dew4c', '2023-04-10 13:05:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('25d646e3-1417-42da-8e64-a3c145b6551f', '2ay32', '2023-04-11 16:15:15', '租户编码');
INSERT INTO `sys_captcha` VALUES ('25df3265-9f64-46ef-8760-146a745645ab', 'nb5mx', '2023-04-15 22:18:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('262b7268-edfc-4e37-8588-7248d0d96469', 'y3272', '2023-04-13 09:43:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2788a20f-af26-4f67-8806-6a95e74729d6', 'gfe2x', '2023-04-15 19:25:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('27d02a95-1f34-4fa1-88eb-d2d3e962d019', 'b86we', '2023-04-10 17:22:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2817972a-7659-403e-8194-a7198970f0bd', '8gyge', '2023-04-13 00:57:07', '租户编码');
INSERT INTO `sys_captcha` VALUES ('28bc3a6f-6132-4ef7-856f-b9cbdcd8e90a', '4np42', '2023-04-10 21:42:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('28ebfb04-f137-4b1e-8393-db80cddbbe83', 'wm378', '2023-04-11 23:12:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('28f002e0-664e-4e2a-8a47-f9b1f7bd9a8f', 'apngw', '2023-04-11 16:03:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('296823d0-edef-4826-8cc2-b7e6854969bf', 'pw2fx', '2023-04-12 00:11:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('299de927-1d70-4b18-8c5e-1fa9486706fd', '5m57b', '2023-04-10 13:21:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('29cc615e-f723-4a41-866e-bb7577a5fa39', 'cb5w2', '2023-04-10 15:17:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('29d25d5f-a5c3-4199-8244-90b1fb9e5898', '5fxw6', '2023-04-15 21:30:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2a7bdce5-e4ad-44a5-8f36-2ee6a7bc29b8', '5xa8n', '2023-04-10 12:54:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2abbb5ee-2256-412e-8f4a-34d83eded155', '6exab', '2023-04-13 17:36:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2b626cbf-e9df-4c63-81cc-c1323fa70fc7', '7g3m6', '2023-04-16 23:11:25', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2b668ba1-51cc-463c-833b-26776dd2fae4', 'm4mxb', '2023-04-12 00:29:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2b7660b8-7cab-4d2e-8f1c-d6a70cd94034', 'ncecc', '2023-04-10 17:22:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2bf18f90-57ad-4225-872d-74b168aadbc6', 'a436n', '2023-04-10 12:24:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2cc89d41-3b28-4b9d-869b-59435f5699dd', '4xx84', '2023-04-27 13:17:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2cca7721-20e4-4dab-843d-b7db1b689ca2', 'f8wcy', '2023-04-16 00:17:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2d0de5d2-f27e-4d69-8a10-605307def168', 'nf3cm', '2023-04-16 00:43:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2d45d65c-3c7f-4b34-88ee-100d23491df8', '7p546', '2023-04-16 11:49:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2df72138-76af-4702-85fa-ba938cbf53f2', '4mnd6', '2023-04-10 15:09:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2dfc6c39-75de-42b8-8c17-28dceecba7e3', '3nwwc', '2023-04-11 22:23:16', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2f4050a4-045d-4b14-8d98-15f944a87aad', '7n2xn', '2023-04-16 00:30:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('2fc606ce-70f9-4261-8c37-a124ff999507', 'cda74', '2023-04-13 01:21:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('300e3508-74d3-4aaa-80c5-612174d2e168', '54x2d', '2023-04-12 00:13:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3085b622-dca0-4160-8dec-fc090bbad406', '6fwd3', '2023-04-10 16:59:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('30f490c1-89eb-40fd-89b6-0d517b372969', 'pbdcd', '2023-04-11 15:39:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('31c36d79-d81a-4dc4-8f76-ccef46f6717c', 'd86n7', '2023-04-09 23:11:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3313f642-2caf-43c3-8309-7a956b51ed00', '4ccgf', '2023-04-09 23:30:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('33a8ac72-63cb-457c-8c42-5186d51ada52', 'd5ey4', '2023-04-10 14:07:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('33e052a9-9389-47a2-8cb2-235947a33dc6', 'b8g38', '2023-04-17 14:18:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('35571c7e-35e9-4e8a-8abd-14a21c5d8d00', '26a7n', '2023-04-16 11:48:39', '租户编码');
INSERT INTO `sys_captcha` VALUES ('35cbfea0-2d9c-4144-8666-d3f2d994baf4', 'c2wpn', '2023-04-10 13:02:09', '租户编码');
INSERT INTO `sys_captcha` VALUES ('35e8502e-a920-488a-8aab-61e41f633b89', '2md4w', '2023-04-10 11:26:04', '租户编码');
INSERT INTO `sys_captcha` VALUES ('36c2fa5f-04ca-40db-8de9-21da9c21265b', '4m26p', '2023-04-09 23:00:25', '租户编码');
INSERT INTO `sys_captcha` VALUES ('376cdb48-f0d3-48b2-8d27-2c9df0013f3c', 'fyae2', '2023-04-16 00:15:09', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3785caf4-7c4b-4ed5-8cfd-dc28275f2e15', 'e85xb', '2023-04-16 00:45:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('385beefd-939c-4298-85ab-b46e1f34f4f3', 'xnw8n', '2023-04-10 16:08:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('388edbff-1ebb-426a-8135-866b8de70342', '82868', '2023-04-12 00:28:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('389041ee-c724-4a0a-8c34-b01dabe926ad', '2d3n4', '2023-04-13 09:39:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('398df84a-8a0a-4acc-8ac4-96c30fdd7693', 'y4apb', '2023-04-10 00:20:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3a7ea72a-7bac-4cef-87f2-7cff9505dd31', 'eg744', '2023-04-16 23:12:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3ace17ee-fcd7-41dc-8218-b83c7844fad7', 'ggbm8', '2023-04-16 23:11:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3b565810-c470-461b-8068-7b22b077800a', 'c4bpx', '2023-04-14 16:32:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3b5dcdd7-6561-47ca-8b49-62964886a974', '2b73a', '2023-04-10 12:04:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3b8a80c9-3867-42fd-81aa-d993422436fd', 'y6bm3', '2023-04-17 14:06:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3cb1e6c0-c353-4b23-8138-58dedf2079c2', '762xb', '2023-04-13 15:05:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3da8fbf5-6f9a-4a3c-8551-eb4b017cf7d4', 'mc6n2', '2023-04-12 00:36:25', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3e0e5879-c5bd-4e23-82b8-9911587d7d73', 'wnf24', '2023-04-13 14:17:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('3fa1f665-923e-4146-8082-7350282cf58f', '7c8de', '2023-04-17 14:01:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('400055b7-5782-4412-8bd4-4a9bd4e90d54', '57a6f', '2023-04-10 12:15:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('40874a4f-355c-4d9e-8800-3404b3f0af4c', 'xbea6', '2023-04-10 09:23:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('415e9336-b416-4070-8717-7830356ed5b2', '8x6ya', '2023-04-16 00:15:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('42f276c8-b724-470a-8791-81a393f71a7d', 'yf7bw', '2023-04-10 09:57:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('45164684-8ad5-4868-8126-e9fa2f79a369', '4dncn', '2023-04-09 23:35:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4562db32-dc3d-43da-8145-3bb5b821dae0', '2a2n3', '2023-04-10 14:02:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('46275033-9161-4045-8f91-58a0bb227a40', '2a2wc', '2023-04-12 23:14:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('46491f63-6ab3-47dd-8b21-950afd844547', '3y7c4', '2023-04-15 23:58:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('46782654-c637-4e4a-8895-14bd1ca608de', '6n6xn', '2023-04-10 13:16:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('46b17edd-5198-4148-86b7-f448510bbf8f', '8ffp8', '2023-04-10 13:14:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('475a88fd-c248-46a5-8199-564cf7f65e19', '6dxde', '2023-04-15 21:13:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('477ac736-4f2a-47fa-8b60-7fd3b28fb44c', 'nbag8', '2023-04-15 21:30:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('481d620d-0f42-4057-8e9a-5c60d3e9e3a4', 'xm8gx', '2023-04-15 21:42:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('483a096a-2e94-4b77-8150-e35fce65beca', '5bc45', '2023-04-10 09:23:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('48460c1e-b6d5-445b-85f7-105f6f17174b', '6xdnf', '2023-04-10 09:23:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('487c2f98-e98d-4973-82c4-cc6fa65a1cb9', '6mxcn', '2023-04-11 23:41:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('48916af4-9f5b-41d9-8529-e04808743899', '4mgxm', '2023-04-09 22:30:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('489abe7c-fa47-4620-887f-1b7f83a8d765', 'm7cbf', '2023-04-16 11:41:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('48a3a80c-eb92-4323-89ff-12cf2a3c8c86', 'nna45', '2023-04-13 12:54:04', '租户编码');
INSERT INTO `sys_captcha` VALUES ('48dc1952-ff6b-4c1d-833f-4df9b465c5d8', 'gnncn', '2023-04-15 23:13:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4918826d-6d0e-4c7c-8d68-6e882c4c5d7a', 'mcg2n', '2023-04-10 14:02:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4948acd2-19d1-4c70-841d-56684bb6b8ba', 'nfbb6', '2023-04-13 14:16:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4c035b54-b202-43fc-8e14-b1438b231aeb', '8e8c6', '2023-04-10 12:14:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4d2753c6-f9fe-4550-81e7-ab9907815a29', 'apnbw', '2023-04-10 09:36:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4ddc3e6b-abda-4187-8105-ee2e0405fa87', '825b5', '2023-04-15 19:30:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4f25502e-462e-4b59-85b7-1b14b3d64e62', '2c4pm', '2023-04-17 13:55:14', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4f5128ab-1085-45b0-8b08-68f9a38de5db', 'c33wy', '2023-04-11 17:34:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('4f8130a2-47d7-4dbf-89e2-c3f24c76ccd1', 'e3nbg', '2023-04-12 00:26:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('50003bf0-cbae-45f2-86a8-0747eac98c1a', 'fbnnp', '2023-04-16 00:13:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('501f07f0-e857-4e3a-8f30-34a1c57bd1ae', 'm3dgp', '2023-04-12 23:37:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('50cfe9bb-27c1-43af-8dcc-c6e08455872d', 'w56pf', '2023-04-16 00:19:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('510c7ec4-42b4-48ba-84f1-65a1ad733eef', 'n76ee', '2023-04-12 23:53:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('510ffd60-7cfd-4898-83f9-73fc4a7713fb', 'wgawy', '2023-04-10 12:44:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5125ab92-a452-4561-80c9-b380dd527ddd', 'g3wa6', '2023-04-16 11:48:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('522dfff2-9a8e-475c-830f-ce416ed81920', '4ge83', '2023-04-13 00:48:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('525f24bb-dcd5-435d-8060-652ceb419177', '4gnww', '2023-04-16 11:34:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('52827fee-c6a5-4ef0-8373-d2fb06f1cda1', '7pexp', '2023-04-09 23:18:39', '租户编码');
INSERT INTO `sys_captcha` VALUES ('53b5b852-4c38-4540-8533-4993af16cece', 'f8nyp', '2023-04-13 14:47:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('543107dc-6f60-4cf4-8ba1-8baf7b4796fa', 'w6abf', '2023-04-12 00:15:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('54d2ca26-b89d-4a33-8cb4-d845700d68c9', '7gbna', '2023-04-11 23:10:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('552a5159-9276-4f44-89d5-5d31b71bea9a', 'pfw8n', '2023-04-12 12:10:07', '租户编码');
INSERT INTO `sys_captcha` VALUES ('560b8083-09dd-4b6e-8783-f7d116c81916', 'fyfyp', '2023-04-10 10:51:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('567c1f2c-f465-41ce-82f2-1e8a626a42a2', 'fx4dw', '2023-04-09 22:54:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('57008bdc-7f36-4f64-816b-8ffd784e450c', '5b6dy', '2023-04-16 00:22:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5727d5cc-1a8a-4f1a-81c0-a091ed2c961e', '83gc7', '2023-04-11 23:42:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('573c2dd3-5c19-4346-811c-ff088444b2a4', '8ynbn', '2023-04-14 16:03:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('575ab37e-28ad-4e24-8bde-91d0dfbc9291', 'g6y8m', '2023-04-17 14:02:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('57827505-c595-428a-8417-ec54984f4091', 'ap7p6', '2023-04-16 00:15:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('57e081ed-cf56-4e50-8a22-906c52203ede', 'y3p5b', '2023-04-10 17:15:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('594365da-29a6-463d-856d-41b6a4a7bc65', '84xbf', '2023-04-09 23:03:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5969e2e2-e2e0-41c8-86e2-27e414f52cff', '66dyf', '2023-04-17 13:42:08', '租户编码');
INSERT INTO `sys_captcha` VALUES ('59bc0eef-98a0-4f38-849b-0a46db1f5fc0', 'cwbpg', '2023-04-15 21:16:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5b16c349-6b00-460b-8e54-cd8a5150a2c9', 'mxnnn', '2023-04-10 15:01:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5b3f72c2-ccaf-4d7e-8a8b-3f817fd39092', 'mydfw', '2023-04-10 17:37:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5c059905-7ab5-40af-8a57-b00785ef104a', 'gdf24', '2023-04-17 14:32:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5c9196a9-8b99-48a0-87a9-4f4ce8682b46', 'gggeb', '2023-04-13 14:54:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5cac82bf-b861-4973-815a-565139e78d4f', '7ddy7', '2023-04-10 12:11:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5d3ac80e-a0fe-40f5-8962-7d831878d950', 'mdy3f', '2023-04-13 11:43:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5d45d4e3-02e5-41ca-8c5c-1b38e0947931', '63f52', '2023-04-10 09:26:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5d5506a8-0b62-4ba5-8eb2-38ce7b343e79', 'b3mx8', '2023-04-10 09:41:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5de8806e-5c3e-48c2-8ade-f3f485e3d12c', '2np6d', '2023-04-06 20:17:16', '');
INSERT INTO `sys_captcha` VALUES ('5ee4958b-bb40-4e7f-8561-709fcb889c16', 'bd7ab', '2023-04-15 18:33:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('5f218a12-befd-45d1-8b45-44d6a1d042a8', 'n6b38', '2023-04-17 13:54:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('601b3400-f20f-46ff-85f0-8c0b80d95bf6', 'w6n3p', '2023-04-10 12:53:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('603067e7-7804-4d7c-8b21-15e7c0ab96d7', '3nbnm', '2023-04-13 14:12:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('609d6cc5-597c-490c-8b2c-6db2e123f823', 'dwfww', '2023-04-13 01:31:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('610f4702-2e11-4a7f-8a97-95af480cab0a', 'amx7n', '2023-04-12 23:11:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6206fa75-edb0-4e10-8536-d51b1bb1f88c', 'acy8e', '2023-04-10 09:41:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('62ed3bf2-9503-406b-8c72-04ecd3812d4c', 'ncba5', '2023-04-10 13:51:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('64039eba-3cbc-4c96-87d2-20bc20dd44d1', 'c3n6m', '2023-04-15 21:16:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('64405da0-e923-45c8-8939-5eb7dcc9975d', 'gxpxn', '2023-04-11 23:18:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6452e686-3116-40f8-874f-db34e58d9298', 'ma8wc', '2023-04-15 19:30:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('64645924-9404-42a8-881b-9167bb03be24', '8wnx4', '2023-04-16 11:32:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('64a2090c-bcb1-4311-8538-bf154b8e6b40', '6n25a', '2023-04-10 11:25:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('65d4bf39-895a-4d24-8f67-dc414015082d', 'w8ddd', '2023-04-10 15:08:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6621381c-f15d-49a5-813a-b46764a06344', '63ync', '2023-04-12 00:10:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('667de614-f108-4e27-89b0-53695284c818', 'yn7wg', '2023-04-10 13:50:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('668182b8-4ed1-42f8-8060-90fc461f5e39', '3a3g4', '2023-04-10 12:12:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('668e7325-2e19-4953-80aa-fba8c7b110a0', '32gg5', '2023-04-10 11:25:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('66f56d9c-c435-4ad6-844e-dada69fd1d8e', '5a23a', '2023-04-11 23:18:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('67899657-9c63-4274-8272-de692324a84d', '4xgfe', '2023-04-17 13:32:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('67e15274-e258-4d29-8f49-c42196e6a742', 'n35ec', '2023-04-11 16:19:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('68b6f79b-6629-436f-82bf-6b8d0901d6cb', '8x23y', '2023-04-15 21:29:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('694735c3-4395-4831-8b46-d4a1b3561c94', 'dggx8', '2023-04-10 22:43:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6ad5b12b-38a5-4a73-812e-c18730e26303', 'd7y5c', '2023-04-15 22:16:15', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6b64012f-ef0e-45b7-8452-03b1249669c7', '8yd4d', '2023-04-10 09:23:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6b92d59d-24b8-45e9-87ec-7d19dea92896', 'fb7e6', '2023-04-12 23:47:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6ba2115d-8e7f-474a-8748-a3e7a47f4106', 'pdg3e', '2023-04-10 15:05:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6bdcad77-0579-4473-8003-797bbe4c2782', 'ebnfn', '2023-04-15 19:30:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6bfba336-65ef-4898-8dcb-842bdae8cf3b', '6nn2x', '2023-04-13 14:17:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6d5fcdcf-ac02-44ed-8272-d0e04d0031bb', 'cngxx', '2023-04-15 21:35:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6e296691-017e-4c06-8bf2-81d5cb7fde46', 'c66ef', '2023-04-10 13:29:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6e7d8971-0383-4f0d-8d3c-59577713566b', 'g7f52', '2023-04-15 21:10:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6ef474e3-8b82-437a-8cc0-2dbfd2ad769c', '8ygwd', '2023-04-17 14:28:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('6f70080e-74d8-468c-847b-7bf17a817958', '8377g', '2023-04-10 14:04:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('700c1909-b347-47d5-8709-ebd0b607034a', 'ycy8x', '2023-04-13 13:11:32', '租户编码');
INSERT INTO `sys_captcha` VALUES ('70739337-acb5-47b6-8d4c-d633b7db7ebf', 'nxmea', '2023-04-10 15:11:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('70a1e2b1-a166-4ea4-8180-732e33e3d18a', 'n5dpm', '2023-04-10 12:25:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('711c3cbf-e16f-4cbf-87c2-80dd2ebb6215', '3p3xm', '2023-04-12 12:09:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('71282029-5143-4307-87c1-209b6625a9ab', 'x3fn4', '2023-04-12 15:51:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('71c505cc-fef3-41db-801e-ce4ae4b715d1', 'ee8nc', '2023-04-16 00:55:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7200fbab-bb74-46d4-8a03-59a20d4b259a', 'yccap', '2023-04-17 14:03:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('724e0395-796d-4578-89d7-1b9a4da27605', 'ywx6n', '2023-04-10 12:16:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('72a5d44f-a85e-4538-894d-74fb892f989b', 'enbc8', '2023-04-15 23:33:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('72b248b9-dd7d-43c1-8ea0-2372547b279a', 'pdffc', '2023-04-10 12:49:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('72b6d717-4440-4f2e-8f0f-5efdbaad3c63', 'n5y57', '2023-04-11 23:08:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('732cf9b8-824e-4494-8e9c-11c935b88129', 'ngf5y', '2023-04-17 14:21:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('73db7581-71ce-4426-85a3-84ecaa4c5f03', '3pxa5', '2023-04-09 23:44:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('74352521-7cf5-4e0d-80f7-7a446a8c0ad1', 'pacnn', '2023-04-10 00:19:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('75311e74-5710-4fe5-8362-80b25ef9e6de', 'gd8f3', '2023-04-09 23:01:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('769ff972-89cc-41f7-8b81-8df2e001a02c', '333cp', '2023-04-09 23:38:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('76d91040-0634-43f3-875d-c9fdf1549764', 'peey4', '2023-04-12 23:57:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('77ba3baf-529d-4e3c-8bb9-3d377d86f2b8', 'dbc8x', '2023-04-15 21:13:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7840e38b-06a0-4cb0-82d5-ade58709cff0', '54anc', '2023-04-10 13:53:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('79719892-5b81-49c3-876f-f8d4194da596', '2pnma', '2023-04-10 12:56:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('79cf6747-ee09-4408-8a2a-1460d636eba8', 'ddmcf', '2023-04-13 01:23:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7a961d57-350e-427b-8574-e9a281f67a0e', 'ggn55', '2023-04-17 13:55:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7b283dc7-1679-4592-8060-a5321000c269', 'pc4x8', '2023-04-17 13:55:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7b732cd8-cc55-4d67-8eec-c03730617b0d', '682db', '2023-04-17 13:54:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7cad8ccc-2a1d-4034-8cee-7b77fa9623a2', 'p54dw', '2023-04-11 17:37:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7cd72dd7-5559-40a8-81d2-06bd86d8f044', 'y8bnx', '2023-04-12 00:18:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7d261ce4-cb73-4079-85c5-1d4ffa5e057b', 'apex8', '2023-04-15 19:30:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7d479494-5329-4423-899b-1d571c55519f', '8w6cf', '2023-04-13 00:39:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7d51ceff-cfc7-4ca8-8900-c8c01381f14a', 'yp6yw', '2023-04-17 18:21:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7d8d55f4-aa5a-4968-8100-99c67c342c7d', '5eef3', '2023-04-10 10:41:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('7e01e42c-d362-4fb9-81ca-f231301ae20e', '43wpn', '2023-04-12 00:25:28', '租户编码');
INSERT INTO `sys_captcha` VALUES ('80cf1cff-d838-4725-8168-1faf721a822e', 'ebycw', '2023-04-09 23:11:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('80ec0891-c43d-4871-8aa2-b79e72e309b4', 'd7cmd', '2023-04-16 11:46:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8148d5c9-6ee4-4605-85fb-bc1176f559a4', 'fyd6c', '2023-04-16 22:28:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('81ab3923-36b7-430a-82b1-e878a48e8bf8', 'ywap8', '2023-04-10 12:19:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8218ae56-f84e-4282-83cf-ee2399bb5a3a', '368c7', '2023-04-13 00:36:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('826424d9-2108-4341-8545-e98ad4698270', 'wmnag', '2023-04-11 16:14:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('82bfd7fb-0817-422c-8aad-4997de2a9bdf', 'an62n', '2023-04-10 13:42:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8316dbb9-d092-4454-8a72-33e4b1449cc9', '626nm', '2023-04-10 21:57:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('833ca986-0887-434a-8856-a2506fa57d8c', 'm4ea3', '2023-04-10 11:57:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8399fc40-ad22-4c00-8c92-56db246718ad', '2d43n', '2023-04-10 17:12:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8430e97b-a45e-42b9-834c-a4c795736577', '8dp7b', '2023-04-10 17:35:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('846a7bce-eb1f-4360-8fc6-4001d841099b', '2xcc2', '2023-04-12 21:56:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('84cec070-97d7-4bbe-8d4c-bfc839d52117', 'px76b', '2023-04-10 15:18:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('84f10062-5123-49ea-8716-6ff5bfb9e84c', 'npfcw', '2023-04-11 23:17:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('850b574c-a940-4b35-8302-d3623afbb8d5', 'cnn3n', '2023-04-10 13:53:08', '租户编码');
INSERT INTO `sys_captcha` VALUES ('854cd748-12ae-4201-80a7-cf8f2ad38cf1', '78p6x', '2023-04-11 22:23:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8561ef4f-f8c7-41cc-8e21-59914e947f29', 'c7g6a', '2023-04-10 15:12:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('858738be-9c98-4176-8d5d-b2fc15bb9256', 'by8dy', '2023-04-10 09:58:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('867c6e22-3bed-47fa-8780-e57e0d875f1f', 'g2x23', '2023-04-12 00:09:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('871f7710-0549-46e6-833d-cb43329fd35a', 'bygfb', '2023-04-16 00:19:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('872d2aeb-f219-4e82-8b40-805061450371', 'fg778', '2023-04-10 13:31:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('875e094a-7fa9-4afd-8c44-509769b2c525', '4aebm', '2023-04-10 21:59:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('887897c8-12de-4ecd-8719-d516548fdae7', 'ge464', '2023-04-10 13:21:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('88be22aa-c1bb-43e8-8c0c-ba55d396af6f', '65y55', '2023-04-12 23:11:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('88c09c0d-6277-4b4b-817f-e16cb06f3532', 'pe8xx', '2023-04-09 23:09:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('88f36e1a-eadc-495c-80dc-c2d83f44a1b8', 'dbxfx', '2023-04-09 23:32:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('89b240ed-e170-45c7-8b99-0f1b2bdc3149', '83px7', '2023-04-12 15:27:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('89df2cba-9a19-45cf-81a4-453acefc5be5', 'b2any', '2023-04-11 16:48:09', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8c45eead-f331-463a-82c4-a97788ef52c0', 'ng5nw', '2023-04-16 00:18:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8c64f780-d40a-4c1d-80dc-22453f0e8f72', '7w6w2', '2023-04-15 19:30:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8d8e4f4c-07c3-41aa-823c-570c3f64a179', '6pb2c', '2023-04-10 16:40:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8dbba506-c3e0-4203-8171-ba6373535c29', 'y67e5', '2023-04-15 20:11:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8df2d834-f080-4bbb-8ce3-1b4ee3361bc0', 'aanb2', '2023-04-10 12:45:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8e2405d3-5220-421e-8b5b-58dab58d4a1d', 'afgfx', '2023-04-13 00:26:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8e71b307-3c5e-411a-845e-40340cede0cd', '8pynb', '2023-04-12 00:13:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8e90ed7f-1cdb-40e7-88ff-69f5f1ee617e', 'y6e3w', '2023-04-10 12:24:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8ecb0d4d-228a-4b11-8343-c87ce8cce080', '85dmd', '2023-04-17 14:20:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('8f2ea103-9ca4-44ab-8b0e-590e8ded4931', '22gmf', '2023-04-16 22:14:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('90aaf91f-5020-4f10-883f-c7d1b0570c28', '846f2', '2023-04-12 00:35:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('90d4e86e-04e9-4718-87ed-67b9986fa566', 'wycxf', '2023-04-16 11:32:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('90fc96bb-a96a-4aee-8581-b24a61ff01ff', 'gwf4p', '2023-04-17 14:18:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('91015bdc-4285-4101-8edc-5baeaf836856', 'n2nx8', '2023-04-13 15:12:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9150f1ef-85a1-4703-838d-9ae078f8d6d5', '8g68w', '2023-04-16 11:47:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('91c0248b-ddea-4fec-8c4c-e010db6a650a', 'mmnfd', '2023-04-15 19:30:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('92c874ef-98d8-41ea-83ce-afc5ee373b4d', 'g4png', '2023-04-10 11:01:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('92e97839-1852-4c15-84ce-619e4e3cd988', 'enn3f', '2023-04-12 15:27:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('935290df-6805-4bb7-81e2-bd481afbe29e', 'cyxef', '2023-04-13 00:20:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('94a2659d-e304-4485-84b7-5678a422f795', '258nf', '2023-04-10 00:18:09', '租户编码');
INSERT INTO `sys_captcha` VALUES ('94ea781d-d180-49f0-8283-e23ea94a5e4c', '53fnx', '2023-04-10 10:14:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('95ca86c2-d31f-4168-8a5c-5aa7320225ab', '8e5dw', '2023-04-10 15:16:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('95e6dd9a-b4c9-4f4d-8070-6feaf3814101', 'cyb4e', '2023-04-16 00:16:08', '租户编码');
INSERT INTO `sys_captcha` VALUES ('95f98e28-b6df-4346-8e7c-509bf2bca5a4', 'xeg37', '2023-04-09 23:58:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('96029dd5-5446-44c0-8275-199b44f0a41e', '6wnbm', '2023-04-10 11:03:04', '租户编码');
INSERT INTO `sys_captcha` VALUES ('964dc5cb-fe39-4974-8cae-bf58be86051a', 'den8y', '2023-04-09 22:27:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('977f72cd-8653-48fe-8ac9-82d8c8a9ad2a', '3e4yc', '2023-04-17 14:36:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('978fefce-745c-4e55-8d4e-2e5e6e8706ab', 'gmwga', '2023-04-10 13:49:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('98668d43-4d64-494c-81ce-eda7de61cb8d', '2ged3', '2023-04-12 00:15:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('989694f6-a6ee-4f01-8549-d724cab305ae', 'nxf8p', '2023-04-11 16:04:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('989740f9-9d86-4495-8cae-78fcfa692063', 'p8dbn', '2023-04-10 17:35:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('98f71aa2-d0d9-4956-8e81-30e2785f8ea2', '2apmy', '2023-04-17 14:06:39', '租户编码');
INSERT INTO `sys_captcha` VALUES ('99181c90-15bc-4ba6-8355-7aa4c0d9b55d', 'ww7g3', '2023-04-12 23:15:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('99705ed3-f63b-4a0b-8c0f-440867ca3953', 'bwnyn', '2023-04-15 21:34:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('99eb408a-fa79-49d1-827f-89617eb9efac', '5cxn4', '2023-04-10 13:12:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9a03a74b-23dc-4f7b-846a-d51714ec1e50', 'd642w', '2023-04-15 21:43:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9a19efb9-d4a8-4488-8a7c-485bf83d58e3', 'n7n84', '2023-04-15 23:58:28', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9a8dc7d6-09d4-4b69-85a6-4d65ca39ab77', '34y8w', '2023-04-15 19:52:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9aeb46f0-8f92-4036-8078-abe9da62532e', '25x8e', '2023-04-11 16:45:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9bd37b22-23ce-4d42-8acd-eed84112c836', '68mn3', '2023-04-16 22:25:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9c8587a8-27aa-41fe-8f7c-6e08214c671b', 'xgdn7', '2023-04-16 11:32:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9c9f0052-43d2-4b42-8554-211604f95748', 'pmxde', '2023-04-11 16:24:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9dde057d-6590-409d-8485-c567bf285b7b', 'ewdmn', '2023-04-12 21:38:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9de13fdc-c997-40b7-8472-47a3f4209931', 'y5c63', '2023-04-12 00:00:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9df3adfa-2038-4cd9-835d-76cb08c54017', 'g64px', '2023-04-11 23:08:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9e362464-d2a0-4aa0-88f7-4d0ec9759ed7', 'd42g5', '2023-04-10 11:35:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9e5c5b97-e1f3-4799-8b59-fb454a94efc7', 'mdwdw', '2023-04-12 23:49:16', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9e798929-1522-4de6-8bc1-d9fd21c9978f', 'f3e75', '2023-04-15 21:13:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9e9fb98c-448b-4b81-85ea-643cc0570142', 'xb8da', '2023-04-10 09:26:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('9fcfca76-369a-4664-86b4-aaf80065f031', '7b263', '2023-04-10 00:02:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a1118040-47e1-4c16-8ba2-6bea08938d94', 'nnx7x', '2023-04-17 14:27:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a13afa4b-8c9d-4afa-8f07-ecdb86f10c2c', 'dd833', '2023-04-10 16:33:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a1665bee-9599-46b6-8b4b-cbdad2bde49e', 'xaefc', '2023-04-15 20:12:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a1c7dffc-dbd7-4002-8c76-c09234795a3f', '7ybfa', '2023-04-10 16:39:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a1cfa08c-b4b0-4f75-8a1c-dd814feedf63', '4624n', '2023-04-13 00:35:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a1e6c198-d071-4dff-8288-7a3237953ba5', 'wbmpm', '2023-04-15 18:34:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a1f33ac4-7243-4b7f-85f4-d54404de9007', '3ww7w', '2023-04-15 20:11:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a2395eec-abaa-4234-8f92-7c0f47a9fbb7', '8xfwy', '2023-04-10 16:08:15', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a3121d2e-44b3-40bc-8d77-fb4a0a2e755f', 'cep3y', '2023-04-11 16:46:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a3a0ac27-1de2-4412-865a-61e49b926fc7', '4ew25', '2023-04-16 00:16:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a3ec2cc5-6ed9-43d8-8792-2e8ecb8836b3', '6nxy4', '2023-04-12 00:17:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a40d419f-aa29-4a66-83fa-59eb8e53effd', 'adxyb', '2023-04-14 16:05:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a45ae0a9-2bc5-45cd-8f4d-fc8fc0b24583', 'b3wyd', '2023-04-09 22:30:32', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a49aaf14-5b8a-42ac-8a77-9e97fb028c8d', 'bay4p', '2023-04-13 00:35:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a544f70c-6cf6-4208-828d-7cf78b9c17b1', 'ya728', '2023-04-15 18:36:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a569a115-643d-4f42-8584-23f0007bad52', 'x4emg', '2023-04-11 16:20:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a57b2ea2-fdb2-453f-8798-ae4e6e617098', 'xnc6m', '2023-04-17 14:24:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a60eb3a2-affa-4203-8f9c-c9d01efb0941', 'nn555', '2023-04-15 19:16:39', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a86ed7ab-6ccf-4b8b-8359-14506103aa77', 'n6b56', '2023-04-15 19:30:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a89805e4-ecc4-4cee-8664-115075290911', 'a7ncn', '2023-04-11 17:26:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a8ed403b-146a-4e6c-8078-34421bcc2576', 'p8xnx', '2023-04-11 00:04:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a8f35f89-6323-4cbe-855d-ed53fedbe0b4', 'y3bcw', '2023-04-15 20:18:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a93f1d91-b153-4df2-8e48-67b21e73fed4', 'eebw4', '2023-04-15 20:41:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('a9cc2630-e5cc-4141-87cf-233cacdc70c3', 'wdawn', '2023-04-10 15:26:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('aada27e1-c4f2-41f6-8364-01fb678ec132', 'd6cec', '2023-04-10 09:10:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('abb3af94-dd97-4449-874b-4cf5a96c8951', '5bxan', '2023-04-15 23:42:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ac0def48-b677-4af2-8eec-4d87ccba7405', '77f58', '2023-04-16 00:28:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ac8fb81b-055f-41dd-8224-16295f0880e6', '8bn3n', '2023-04-12 00:16:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('acc47f91-ec20-4d2b-87f0-ed163af1d06e', 'mxpyw', '2023-04-10 09:10:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('acd8f775-b875-4acd-8d57-5f3313a3b635', 'cn4xe', '2023-04-15 18:35:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('acdaa2ef-9a0c-4361-8c7f-123fe867671c', '2nyw3', '2023-04-16 22:26:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('acde4df4-4c12-457a-8241-6909e87c83bb', 'n5ybf', '2023-04-13 14:11:14', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ace6209d-07b1-4f8c-815b-99b9b8cd1bba', '4fw6g', '2023-04-13 00:41:28', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ad0cb058-a355-48b5-81e6-b83c6aa2784e', 'n7w55', '2023-04-17 13:55:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ad0f3afb-80e0-42e5-8aff-1cd60f541210', 'w3yfp', '2023-04-09 23:49:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ada3d6bf-ba9a-4ac1-80a2-72616618dfe7', 'dgwbb', '2023-04-16 00:48:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('adcdb9e8-6575-4113-827f-85483dd2d35e', 'n33x8', '2023-04-13 01:22:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ade07d7d-4f5c-40f3-80bc-0eae444f0bc1', 'yan4n', '2023-04-17 14:07:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ae2c7e55-aa70-4207-8491-74470868c407', 'eg72e', '2023-04-17 18:20:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ae9a870b-663e-4b9c-8f17-8ee8fec50148', 'wwdfe', '2023-04-17 14:27:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('aef88f53-4afb-4891-8c25-bd3c2a270d12', 'nbwc2', '2023-04-11 16:16:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('afda0679-79ff-4846-83f9-4dfe0d203f5d', 'nn5gp', '2023-04-11 23:18:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('affc5ede-188f-4aeb-85e0-c85f8b0a2244', '58c5b', '2023-04-16 00:28:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b0620f54-fa42-419b-8bfc-11e424082580', 'paa4n', '2023-04-10 10:27:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b08d67cd-65f5-41eb-8e05-903946cc87d2', 'dw36b', '2023-04-15 21:17:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b15152d6-691e-41b4-8d2b-011452e0655b', 'c8fay', '2023-04-16 00:06:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b1b31060-1077-4cd3-8e35-68ccd08a978a', 'nnxmx', '2023-04-11 16:43:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b2368431-2629-43f0-8aea-da2633157cc6', 'ng746', '2023-04-11 23:12:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b26dd07f-70a8-4c9f-8f54-7a0a9d66ccd1', 'mwgan', '2023-04-10 13:52:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b28ab21e-f663-4556-84a8-408f35f124e1', 'xpnm2', '2023-04-16 23:06:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b30a4694-7ad0-4039-8a4e-4b1272b9bfeb', 'yyxpc', '2023-04-10 11:35:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b48f58cf-ee98-4a2e-800e-e82258e25c19', 'yndpf', '2023-04-12 00:30:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b5186635-5715-4c41-852b-4641c0c0f22d', 'n6d2g', '2023-04-16 22:25:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b5217057-080d-428c-849a-6884a20c8535', 'na827', '2023-04-15 20:21:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b532e963-7c78-4686-8e29-977275befb96', 'x43ee', '2023-04-10 12:53:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b5d83800-91a7-4b07-8587-5e26e4543c8b', 'n566b', '2023-04-16 00:44:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b75a90ed-000c-41af-8633-081787cbb5d2', '2y73w', '2023-04-12 00:17:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b7f3cce4-3f93-4366-817a-cdfd774bc587', 'xn78a', '2023-04-17 13:54:16', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b8496148-5740-4d96-8105-de503aeb8b4a', 'nn8g3', '2023-04-10 12:45:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b8eed2a5-f6bc-44d3-8324-2309762caba8', 'p6xmf', '2023-04-10 13:18:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b920a16f-556d-494b-8a9a-f3427af52ad1', 'bng2f', '2023-04-10 14:58:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('b9e3182c-da6b-4bb7-82e2-8052a370ce90', 'n3ae8', '2023-04-10 12:14:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ba37db25-c2c7-46d1-8e79-f570c83567d5', 'nem38', '2023-04-12 00:09:00', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ba6600dd-6c1a-4a2b-8d50-5f165a06d0cd', 'mpmgn', '2023-04-10 09:58:32', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bad6c26b-1311-45ca-8c02-f59e1dfe4403', 'wgx64', '2023-04-15 18:27:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bb44031f-d929-4d11-87fb-9f33d34c02ea', 'yb6yn', '2023-04-15 21:46:25', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bbe6c358-9d5b-4b8c-8dbc-88e3c836f511', 'mxdcm', '2023-04-15 20:35:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bc37cb3a-adc9-4108-8be6-2130fe96b2cb', 'm72yg', '2023-04-13 13:49:20', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bd15a4d1-6f53-474a-8951-7692681edc8f', 'a2n43', '2023-04-10 13:14:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bdc1a01c-3e21-457f-864c-02b226864e0a', 'gayx5', '2023-04-16 00:07:16', '租户编码');
INSERT INTO `sys_captcha` VALUES ('be16a7b0-6e43-4df7-8465-e8c284a6f630', 'pn75m', '2023-04-11 16:15:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bf0f27cc-4e5b-4e06-886c-bcfa942556b8', '8c8fd', '2023-04-10 12:45:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('bfa53346-fa15-4ac8-8405-cdd53881d25c', 'gn8y7', '2023-04-09 21:33:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c1a0d3a5-c70a-4f66-8f36-0e238ab2f837', 'fcn37', '2023-04-10 17:02:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c20f59ac-e501-4e62-8920-95a5dc2e13ba', 'y4n6x', '2023-04-16 00:45:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c2b7e755-bbbd-4ea0-876f-0f230577fdc9', 'nwcnx', '2023-04-11 23:11:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c2f06ac3-89f1-4e4d-8e0a-c4dce12a8cf1', 'ep44f', '2023-04-09 23:31:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c456af3f-6ef8-4fb1-89a6-e826092b5b44', 'bcb48', '2023-04-15 18:26:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c48c55c0-73ee-4394-8ec3-28b654e66a41', '5nm88', '2023-04-10 00:16:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c6bdcd7b-f0b6-4053-8a5a-be31bdf0a1a9', 'ewc42', '2023-04-15 21:42:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c73541df-b68d-4843-8e5b-3e31359cf671', '6nbfy', '2023-04-10 15:30:09', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c8a53a5b-1aa9-4532-8087-b82b51a883b2', 'd6n2g', '2023-04-12 00:32:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c8d3510a-41af-44c9-8024-51e57f875df8', 'nn5pc', '2023-04-15 21:32:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c8e8dd68-388e-4c75-8fb9-a7c8bf41dbc7', 'mygmy', '2023-04-11 23:45:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c8f1107e-8be8-4c21-8609-a0985c94e074', 'nw7ga', '2023-04-09 22:00:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c984a26d-0a2b-4d59-8fdd-0ca78a3f4604', 'nb2b6', '2023-04-10 12:13:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('c9857b6a-b2ed-4801-896a-8ed9be994a3e', 'p8wf7', '2023-04-11 23:17:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ca416093-71c5-4b92-86a3-3d5f61f72186', '5bwpe', '2023-04-14 16:03:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cac4a2a5-bfcc-41e4-880b-ba2219a9096c', 'bxa6p', '2023-04-10 12:48:00', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cacb885a-91ba-423d-8364-4444a6ed0456', 'x2gmd', '2023-04-11 16:16:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cb6a434b-e76f-40b0-8120-bb5b442a458d', 'aba5e', '2023-04-15 19:16:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cb890c95-62cc-4bf5-8aca-36ca52826b5d', '2b2mn', '2023-04-16 00:28:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cc031821-75b3-42eb-85ec-11094eb5291c', 'y3b5y', '2023-04-15 18:34:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cc2e0858-b1c7-4c29-8ea1-0f6ab548499e', '8ped4', '2023-04-15 19:00:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cc7e81ef-5814-4108-8820-f95e276c1e2e', 'n76xd', '2023-04-15 18:31:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ccb0f235-39df-4605-8e9f-fb1532f1d181', '2m4a4', '2023-04-15 21:12:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cccbaa18-b398-4ef8-820d-031e83abc9d1', '5nwxp', '2023-04-10 12:15:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cce1bdf0-fc2c-4f2e-81fd-4e0ee63378b6', 'xawnc', '2023-04-15 19:37:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ccef8a5c-b005-4d15-8e36-98767fbfaa6d', '33y6n', '2023-04-10 17:07:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cd3c04d8-c5f8-475e-8bac-ad7975b13293', 'x2786', '2023-04-16 00:23:06', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cd8bd07b-ee3d-4b87-84fd-d080f441bdd4', 'mm4xp', '2023-04-17 14:41:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cda240e3-9c9a-404d-8403-9b7f6a9615dc', 'pnxyf', '2023-04-10 12:18:45', '租户编码');
INSERT INTO `sys_captcha` VALUES ('cda700a1-e482-40a8-844a-86d9f6dd4224', '4cewn', '2023-04-12 15:26:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ce5f993b-7d89-4297-8683-4652afe26e57', 'm2nb6', '2023-04-12 00:31:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d0264d25-7731-4b14-8456-a99b59b67d08', '8nf4b', '2023-04-17 14:17:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d03ba392-9068-4ef1-83aa-43fadb53b6d8', '6amgc', '2023-04-17 13:32:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d0e37c27-2460-4fa6-85f9-66ba339a8b98', 'ndap4', '2023-04-12 00:25:41', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d1556cd0-a267-4c4c-8b35-b63f97b0b0d5', 'ac7pn', '2023-04-16 00:12:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d2e965e7-264a-441c-84d5-8e50d55c3161', 'pga72', '2023-04-10 15:08:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d3128706-7b23-4e3f-8bc2-a086a7c91058', 'nb6nb', '2023-04-10 13:45:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d31b55c6-788d-42cf-8d95-298c1f45185e', '6neyf', '2023-04-10 12:16:15', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d31ee814-b0a5-4049-8aac-ad5cf0067641', 'w8f22', '2023-04-16 00:16:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d3b7f144-a69c-4b30-8a83-38c7293fbbb4', 'g8d7c', '2023-04-12 21:38:38', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d3de8f66-d420-47d2-8280-e45459eb2045', '64n8n', '2023-04-09 23:04:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d45e5540-17ef-4b53-8b87-3ecaf5909e02', 'gny3d', '2023-04-10 13:52:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d58c8e2b-5529-4c0f-8c80-84a6e4b367ce', 'c722n', '2023-04-10 15:14:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d597d808-8979-489a-8269-20806844bd00', 'ea565', '2023-04-15 21:33:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d646948e-aad7-4f75-8715-57831a6bdff3', '58y5n', '2023-04-12 00:09:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d6ad81d1-4e75-48f2-8b49-9e6a71bfe1b7', 'bnayy', '2023-04-16 00:12:44', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d71d2e8d-f134-4a69-8039-a37c6549ce8a', 'nw24x', '2023-04-15 20:11:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d75ed0c7-feb8-45e2-8bfa-d8e8c3260ae2', 'pb6wf', '2023-04-15 19:52:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d7d525e5-8a07-4763-8409-b9372ed5d045', '25dcb', '2023-04-16 22:12:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d815475a-7eba-4a35-8121-a019f672fed1', 'wcf4n', '2023-04-13 12:54:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d84e6258-533c-415c-85de-d5b91337f609', 'wn3gw', '2023-04-17 14:02:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d865f57f-cd77-4252-86fc-045dbde92ab0', 'cdnem', '2023-04-10 12:19:51', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d869c05a-1166-426e-82a3-6d794532f9ee', 'f2bf2', '2023-04-10 13:30:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d940e0b6-ea7a-4fa5-81b4-332539e2f9c9', 'ex33a', '2023-04-09 23:05:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('d9fcfde4-6fd4-44fb-8d6e-97038dc3ac5a', 'fp8ca', '2023-04-15 21:09:50', '租户编码');
INSERT INTO `sys_captcha` VALUES ('da310b38-21fa-4dc4-8bc8-7f2c0b83d27a', 'pe2pc', '2023-04-10 12:20:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('daccf4ae-9abf-4104-89ef-8cf9f9643f5d', '6n4f2', '2023-04-10 14:59:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('dbd10ccb-c516-45a1-8805-67a248320b2f', 'm65mx', '2023-04-15 22:39:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('dc9c0c26-4720-463a-8ba1-383358b357bd', 'n28wb', '2023-04-12 22:36:13', '租户编码');
INSERT INTO `sys_captcha` VALUES ('dcaac6cd-63d9-491a-8581-989bf23ad7f4', '33fnm', '2023-04-09 23:06:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('dddf9f13-18a8-47e1-8cba-5763da908f98', 'gcpnw', '2023-04-11 22:38:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('df1cacae-19e8-4eda-8d76-63ca25580e15', 'ema5p', '2023-04-11 23:35:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('df85896d-5211-4409-8d8c-cc6fa0d9733d', 'fgdy2', '2023-04-09 23:10:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e109e13a-5250-4b1c-8a25-b4037a306d76', 'bdcxn', '2023-04-09 23:41:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e156dcf5-39f9-433c-807e-23e79899cb8f', '786m2', '2023-04-16 00:22:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e1a9deb9-375f-4c08-8aff-a9cd6116ff94', 'pyx42', '2023-04-13 10:44:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e1c7124f-470b-4581-8450-76b714d3dfbf', 'cn6e5', '2023-04-09 23:33:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e1d5c4d9-2558-49c8-849d-01dc664d62b0', 'nn55g', '2023-04-10 11:32:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e2a77eb6-6433-424a-8b43-a98ee6b98f98', '6c2x7', '2023-04-15 23:12:25', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e2d1d7ab-dba7-4200-84bf-c5913d78b378', 'pxxg6', '2023-04-10 12:51:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e37c6e6e-8298-420f-86bc-5d7cac3ce5a2', 'exygc', '2023-04-13 01:06:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e4a1ab1a-2fbd-4155-88ee-cbbd44fa3da9', 'w46gx', '2023-04-10 10:01:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e512eb4d-e119-477c-8393-83307cade716', '85npe', '2023-04-10 13:50:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e5de9e76-6075-4418-80f5-a4b9dd010f78', 'pepm6', '2023-04-15 20:10:52', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e6216fc5-efe7-4f36-8e4c-8e3ee3ee5569', '7na7x', '2023-04-09 23:11:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e66401ca-1624-4233-8a5d-06bac7a4d092', '3yf47', '2023-04-10 13:27:48', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e7b7315c-e165-4838-8500-9baef0222010', '5dxnw', '2023-04-15 19:30:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e7e62fd7-9555-4950-8db7-931532db25eb', 'w52dd', '2023-04-13 13:08:53', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e80867a0-6d89-44d4-8f48-fdc99600046c', 'p6e8m', '2023-04-16 00:28:08', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e873c4dd-1d23-43d7-80a1-c8f916d51dc8', 'bn73n', '2023-04-10 15:52:12', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e9300901-231f-4286-824f-e12ea01211c9', 'nw2fn', '2023-04-13 13:11:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('e93f7604-a24d-4235-845e-df6f5c252446', 'nfcan', '2023-04-16 11:44:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ea067035-6445-4dd2-8ece-31f9ef868727', '8e28p', '2023-04-16 00:30:47', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ea501245-a4dc-46f7-8b3d-cb8ac0c0b562', '7pw2m', '2023-04-10 12:18:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('eac4515e-f772-437a-84c5-af6d92029668', '3a3e3', '2023-04-15 21:50:56', '租户编码');
INSERT INTO `sys_captcha` VALUES ('eb057a4b-78bd-4d29-897c-491cc7934e12', 'adynb', '2023-04-13 01:19:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ebe70b46-61ea-426e-8777-d2519dbbdcef', '2pddn', '2023-04-17 14:17:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ec3867b8-18a0-4691-8393-f34806c4ed67', 'ca8gb', '2023-04-14 16:01:05', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ec5088c3-75f2-4295-8c7f-c0cf5056637c', 'n48nn', '2023-04-15 20:08:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ec5d729b-c857-423a-8859-338b7f219a1d', '846fc', '2023-04-10 13:25:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ecbd89c1-c3a8-42f5-8d27-c37d91538edd', 'dbyyb', '2023-04-11 16:14:22', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ed9515fe-b2c8-42de-8edc-907bb6ee06d8', '43pxn', '2023-04-09 23:03:25', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ee5b3d51-d055-4061-8480-54f8011c675e', '6paxb', '2023-04-10 13:01:26', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ee6964d3-e6c9-4c14-810a-eac956a6d52f', '84x58', '2023-04-15 22:18:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('efdeee89-686c-454c-89b1-bf3a6ac33032', 'f64e6', '2023-04-13 13:08:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f0057d2f-24f5-416c-8f47-d855b737f13f', '78w68', '2023-04-11 14:38:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f05438ca-8437-4a3a-88d1-0b171bfc3917', 'bpnd2', '2023-04-15 21:17:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f0a02979-e55f-4a15-8cef-e259a588c7e6', 'exx26', '2023-04-15 20:11:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f2813dd2-7228-49d8-8722-8f9565501f29', '72x3n', '2023-04-17 14:25:54', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f2a27463-0c50-4f7f-82dc-37be80e4d123', 'xn642', '2023-04-17 13:49:55', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f2e4a6a5-dc2b-44dd-82b6-11b4dcd3f596', 'cpx37', '2023-04-10 16:08:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f3876cc3-3aa1-4b48-8737-b7882ef6068a', 'nge7d', '2023-04-10 11:35:18', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f3abbf31-1494-437b-83e9-6131142c1c3d', '67en7', '2023-04-10 21:43:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f4630328-77e6-40b4-8fed-f359a2595cc2', 'wxycb', '2023-04-11 16:19:57', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f46fd6b9-ff51-406a-8f9f-c0ecf2844e88', 'c5f83', '2023-04-16 11:48:49', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f4a91144-e9a0-4970-8e3a-a9bb74b275d0', 'm8xax', '2023-04-13 01:24:10', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f562d577-4b5f-4b64-8fcc-7ec3921f7838', 'b8nnb', '2023-04-10 14:06:27', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f59bbd62-346f-4a29-8d06-90c5a82c714d', 'xmxm3', '2023-04-15 21:12:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f61dc15c-3798-4909-8518-4dd9ed4d6738', 'a67m8', '2023-04-17 14:02:14', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f62517f0-b54d-4fb7-8799-bfaa0f994761', 'dddge', '2023-04-10 21:59:03', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f63d20c0-bbb4-47f6-88e8-be62cff3d45e', 'agfcn', '2023-04-13 14:17:24', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f75b2605-f55e-4f0e-8dfd-4e3c528e5cf2', 'ca6c6', '2023-04-10 13:05:15', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f75bc8ee-d934-4189-8234-5f814e74c187', 'cn7be', '2023-04-17 13:42:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f76d4a26-0468-4f77-8d9c-c1dbb680d959', 'n2g38', '2023-04-12 00:29:42', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f77ada27-9c4e-45e1-83ca-d156f35b5580', 'c84ax', '2023-04-12 22:18:35', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f77c5386-9b2c-47b3-8e18-38a6abe9a2d9', '66xbe', '2023-04-11 17:26:30', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f7d14b5d-c58e-4818-8dd6-29d3b1377eb1', '5e7f2', '2023-04-15 21:40:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f8659ea6-ea63-4389-8352-c38ae1aa450c', '7ddpn', '2023-04-10 17:38:31', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f8cd0cdd-d9a3-4dc9-8008-d4ad86e214da', 'bxxn7', '2023-04-15 19:30:19', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f91ff3b7-ca29-43ad-890f-e2d6b2a913fe', 'acx88', '2023-04-12 00:35:14', '租户编码');
INSERT INTO `sys_captcha` VALUES ('f9320446-af51-4b58-8bd5-9cdf03c2f0e3', '5cfwg', '2023-04-10 15:36:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fb4068a1-62ce-4dc6-8278-30e8c0c9f38d', 'c86m3', '2023-04-17 14:07:40', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fb788b16-367b-4f6c-8677-791a927cf6c6', 'xg85b', '2023-04-15 18:28:04', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fb8bad05-086a-4f7d-88c1-64917de94044', 'e46m3', '2023-04-12 00:30:46', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fbab8018-15cb-4d08-8187-df75ac9e0ac0', '36acg', '2023-04-16 23:03:11', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fbc25a8d-128f-40de-8e5c-f39fb9f688bd', '6ndnf', '2023-04-10 00:20:02', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fbc2b852-f935-46e1-8406-db9957dc6dbf', '28d47', '2023-04-10 11:34:59', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fcc91896-8e70-4a4c-83ed-c9396579a6d7', '2ymcn', '2023-04-13 14:15:23', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fcd6537d-671f-4604-8911-d40d00f58ed8', '3yng6', '2023-04-10 10:26:17', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fcdbbb13-ba68-4f03-8ed4-4dae5444c401', '6c2nd', '2023-04-10 15:04:58', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fd1687c7-f930-4720-8cb6-3e3552d7881d', 'd8248', '2023-04-10 16:42:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fd4df4f0-d20b-4806-8a66-1464330b96b6', 'nbf76', '2023-04-10 11:23:33', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fddb18b5-035a-4e95-8754-5ead82d439a1', 'x2yg8', '2023-04-16 00:46:34', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fdf13b4e-5b3c-4c5b-88ff-573ad90742da', '4b7ng', '2023-04-10 15:33:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fe089287-9ff2-4223-8cd9-9eed83c70ad5', 'dd6xw', '2023-04-16 00:19:21', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fe3e5ed8-707a-4d60-898c-41c8f12ae1b3', 'yndef', '2023-04-11 16:24:29', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fe514412-3cf0-43af-8563-f35384d1a4f7', '34xwf', '2023-04-10 12:51:36', '租户编码');
INSERT INTO `sys_captcha` VALUES ('fec26762-be98-4fb2-8aff-b7d678a394a7', '6dg6n', '2023-04-14 15:46:43', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ff25e0e0-bde6-4641-8937-f37a87a18a88', 'nycd7', '2023-04-15 22:27:01', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ffa13410-096e-4b53-88bd-710a9e99d78b', 'pyp22', '2023-04-10 10:58:37', '租户编码');
INSERT INTO `sys_captcha` VALUES ('ffa2f5a2-c2c5-4f9f-89f8-aaeb228b9788', 'dx6ax', '2023-04-10 13:44:20', '租户编码');

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
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
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
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_log` VALUES (20, 'admin', '修改用户', 'com.maibaduoduo.service.sys.controller.SysUserController.update()', '[{\"userId\":4,\"username\":\"214579869365829632\",\"salt\":\"TOTCQ9yRPhW3XXKdPMQw\",\"email\":\"hello@yeah.net\",\"mobile\":\"13456789012\",\"status\":1,\"roleIdList\":[1,2],\"createUserId\":1}]', 189, '127.0.0.1', '2023-04-09 17:00:29', '租户编码');
INSERT INTO `sys_log` VALUES (21, '215420858095632384', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":6,\"username\":\"SAAS4\",\"password\":\"fe71134e96a7d9ae31809f50c164ab345a06773219938773e2cccda7a5b7ba3d\",\"salt\":\"VgWVuVydlCLGHuGIqj0p\",\"email\":\"SAAS4@yeah.net\",\"mobile\":\"13512345678\",\"status\":0,\"roleIdList\":[],\"createUserId\":5,\"createTime\":\"Apr 12, 2023 12:28:40 AM\",\"tenantId\":\"215420858095632384\"}]', 19, '127.0.0.1', '2023-04-12 00:28:40', '租户编码');
INSERT INTO `sys_log` VALUES (22, '215421580396724224', '保存用户', 'com.maibaduoduo.service.sys.controller.SysUserController.save()', '[{\"userId\":8,\"username\":\"SAAS5\",\"password\":\"7694ecc42e82c901b402ba909b1d5892e582e7823057ccf752d9523e20ca84d7\",\"salt\":\"Ahnc4HchaQY7VkOnt12i\",\"email\":\"SAAS5@yeah.net\",\"mobile\":\"13512345678\",\"status\":0,\"roleIdList\":[],\"createUserId\":7,\"createTime\":\"Apr 12, 2023 12:30:56 AM\",\"tenantId\":\"215421580396724224\"}]', 27, '127.0.0.1', '2023-04-12 00:30:57', '租户编码');

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
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
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
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'zncdz', '', 1, '2023-04-06 20:22:20', '');
INSERT INTO `sys_role` VALUES (2, 'devuser', '开发员工', 2, '2023-04-06 20:25:53', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

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
  UNIQUE INDEX `key_username`(`username`) USING BTREE,
  UNIQUE INDEX `key_mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11', '');
INSERT INTO `sys_user` VALUES (2, 'zncdz', '5984385e3eff2d8c36e802fb6970b3e1e930ffa5a64fd170463fb1c2509b76b5', 'Mw2Zc1E4OW5ThtAXvKFO', 'zncdZ@yeah.net', '13298765432', 1, 1, '2023-04-06 20:21:57', '');
INSERT INTO `sys_user` VALUES (3, 'hello', '4c58f4665324d579e932c7ea3a90d11cf66eadfeb8df72d5b911591fda237a67', 'YbfDaJgGo7gKultwomsd', 'hello@yeah.net', '13512348765', 1, 2, '2023-04-06 20:23:59', '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 2, 1, '');
INSERT INTO `sys_user_role` VALUES (2, 4, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (3, 4, 2, '租户编码');
INSERT INTO `sys_user_role` VALUES (8, 5, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (10, 6, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (14, 7, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (16, 8, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (18, 9, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (20, 10, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (22, 11, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (26, 12, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (27, 13, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (30, 36, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (31, 37, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (32, 37, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (36, 38, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (38, 39, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (40, 77, 1, '租户编码');
INSERT INTO `sys_user_role` VALUES (41, 78, 1, '租户编码');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `TENANT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, '3d0e643535a380407069748876d6f4b0', '2023-04-16 23:44:29', '2023-04-16 11:44:29', '');
INSERT INTO `sys_user_token` VALUES (2, '8c421a02ff56a7521d9ece7b734a92ad', '2023-04-07 08:23:27', '2023-04-06 20:23:27', '');
INSERT INTO `sys_user_token` VALUES (4, 'a26bfebce08aba54df8e08d567f1fc35', '2023-04-13 00:05:17', '2023-04-12 12:05:17', '租户编码');
INSERT INTO `sys_user_token` VALUES (5, '9ea4eeb918f3026502bcec88dbdbb339', '2023-04-12 12:28:01', '2023-04-12 00:28:01', '215420858095632384');
INSERT INTO `sys_user_token` VALUES (6, '5c7723c17a9ffea22cee28206fc105ab', '2023-04-12 12:29:10', '2023-04-12 00:29:10', 'SAAS4');
INSERT INTO `sys_user_token` VALUES (7, '4842abd83e47669541b034de36969645', '2023-04-12 12:30:28', '2023-04-12 00:30:28', '215421580396724224');
INSERT INTO `sys_user_token` VALUES (8, 'bd77db3122b99412779891fe8e9cdada', '2023-04-12 12:31:32', '2023-04-12 00:31:32', 'SAAS5');
INSERT INTO `sys_user_token` VALUES (77, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyMTcyMDA2NDY1NjA5NDAwMzIsMjE3MjAwNjQ2NTYwOTQwMDMyIiwiaWF0IjoxNjgxNjU3NjQyLCJleHAiOjE2ODIyNjI0NDJ9.pRI0DspM5Shkew6hPe9MH_kAGv8wvkyUoxFfrTUHCSqKr_UZuJmPnv1CzOxxJtenHshzPTVrs45xY-i3Gk1kdQ', '2023-04-17 11:07:22', '2023-04-16 23:07:22', '217200646560940032');

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
  UNIQUE INDEX `key_username`(`username`) USING BTREE,
  UNIQUE INDEX `key_mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41', '');
INSERT INTO `tb_user` VALUES (2, '13512345678', '13512345678', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '2023-04-13 16:33:49', '租户编码');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', '985adbe9-4ae3-4140-8c7d-146731e64ff6', 'dev', 'dev', 'nacos', 1681300335846, 1681300335846);

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

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

-- ----------------------------
-- Table structure for xxl_job_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_blob_triggers`;
CREATE TABLE `xxl_job_qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_calendars`;
CREATE TABLE `xxl_job_qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_cron_triggers`;
CREATE TABLE `xxl_job_qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_fired_triggers`;
CREATE TABLE `xxl_job_qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_job_details`;
CREATE TABLE `xxl_job_qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_locks`;
CREATE TABLE `xxl_job_qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_locks
-- ----------------------------
INSERT INTO `xxl_job_qrtz_locks` VALUES ('DefaultQuartzScheduler', 'STATE_ACCESS');
INSERT INTO `xxl_job_qrtz_locks` VALUES ('DefaultQuartzScheduler', 'TRIGGER_ACCESS');
INSERT INTO `xxl_job_qrtz_locks` VALUES ('getSchedulerFactoryBean', 'STATE_ACCESS');
INSERT INTO `xxl_job_qrtz_locks` VALUES ('getSchedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for xxl_job_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_paused_trigger_grps`;
CREATE TABLE `xxl_job_qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_scheduler_state`;
CREATE TABLE `xxl_job_qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `xxl_job_qrtz_scheduler_state` VALUES ('DefaultQuartzScheduler', 'DESKTOP-9VIMQ9J1682133991473', 1682134170332, 5000);
INSERT INTO `xxl_job_qrtz_scheduler_state` VALUES ('getSchedulerFactoryBean', 'tangyhMacBookPro.local1553850279059', 1553850304933, 5000);

-- ----------------------------
-- Table structure for xxl_job_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simple_triggers`;
CREATE TABLE `xxl_job_qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simprop_triggers`;
CREATE TABLE `xxl_job_qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_group`;
CREATE TABLE `xxl_job_qrtz_trigger_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '执行器名称',
  `order` tinyint(4) NOT NULL DEFAULT 0 COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UN_APP_NAME`(`app_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = ' 任务组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_group
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_group` VALUES (1, 'saas-jobs-server', 'saas执行器', 1, 0, NULL);

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_info`;
CREATE TABLE `xxl_job_qrtz_trigger_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行CRON',
  `start_execute_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间 和 job_cron人选其一',
  `end_execute_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间 和 job_cron人选其一',
  `type_` int(11) NOT NULL DEFAULT 1 COMMENT '执行类型 1：cron  2：定时',
  `job_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `interval_seconds` int(11) NULL DEFAULT NULL COMMENT '间隔秒数',
  `repeat_count` int(11) NULL DEFAULT NULL COMMENT '重复次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_info
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (41, 1, NULL, '2019-07-08 21:45:00', NULL, 2, '123', '2019-07-05 10:12:50', '2019-07-08 21:44:36', '最后', '', 'FIRST', 'demo2JobHandler', 'hello', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2019-07-05 10:12:50', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (42, 1, '*/10 * * * * ? ', NULL, NULL, 1, '本地执行', '2019-07-07 18:33:16', '2019-07-08 14:49:19', '最后', '', 'FIRST', 'demo2JobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2019-07-07 18:33:16', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (43, 1, '0 0 2 * * ?', NULL, NULL, 1, '重置租户', '2020-01-16 18:08:12', '2020-01-16 18:08:12', '最后', '', 'FIRST', 'restTenantJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-01-16 18:08:12', '', 0, 0);
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (44, 1, '0 0 2 * * ?', NULL, NULL, 1, '重置默认租户数据', '2020-01-16 18:09:53', '2020-01-16 18:09:53', '最后', '', 'FIRST', 'restBase0000JobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-01-16 18:09:53', '', 0, 0);

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log`;
CREATE TABLE `xxl_job_qrtz_trigger_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行-日志',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_log
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (1, 1, 43, '127.0.0.1:8771', 'restTenantJobHandler', '', NULL, 0, '2020-01-16 18:08:28', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.34<br>执行器-注册方式：自动注册<br>执行器-地址列表：[127.0.0.1:8771]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:8771<br>code：200<br>msg：null', '2020-01-16 18:08:52', 200, '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (2, 1, 44, '127.0.0.1:8771', 'restBase0000JobHandler', '', NULL, 0, '2020-01-16 18:09:59', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.34<br>执行器-注册方式：自动注册<br>执行器-地址列表：[127.0.0.1:8771]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:8771<br>code：200<br>msg：null', NULL, 0, NULL);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (3, 1, 44, '127.0.0.1:8771', 'restBase0000JobHandler', '', NULL, 0, '2020-01-16 18:22:36', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.34<br>执行器-注册方式：自动注册<br>执行器-地址列表：[127.0.0.1:8771]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:8771<br>code：200<br>msg：null', '2020-01-16 18:22:42', 200, '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (12, 1, 43, '127.0.0.1:8771', 'restTenantJobHandler', '', NULL, 0, '2020-02-24 17:21:35', 200, '任务触发类型：手动触发<br>调度机器：192.168.2.178<br>执行器-注册方式：自动注册<br>执行器-地址列表：[127.0.0.1:8771]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:8771<br>code：200<br>msg：null', '2020-02-24 17:21:54', 200, '');

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_logglue`;
CREATE TABLE `xxl_job_qrtz_trigger_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_registry`;
CREATE TABLE `xxl_job_qrtz_trigger_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_registry
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_registry` VALUES (20, 'EXECUTOR', 'jobs-server', '127.0.0.1:8771', '2023-04-22 11:29:11');

-- ----------------------------
-- Table structure for xxl_job_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_triggers`;
CREATE TABLE `xxl_job_qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `xxl_job_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
