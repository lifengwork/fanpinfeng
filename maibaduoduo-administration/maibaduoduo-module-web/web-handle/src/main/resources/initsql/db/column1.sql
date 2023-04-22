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
-- Table structure for act_evt_log
-- ----------------------------
DROP TABLE IF EXISTS `act_evt_log`;
CREATE TABLE `act_evt_log`  (
  `LOG_NR` bigint(0) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(64)  NULL DEFAULT NULL,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `TIME_STAMP` timestamp(3) NULL DEFAULT NULL,
  `USER_ID` varchar(255)  NULL DEFAULT NULL,
  `DATA` longblob NULL,
  `LOCK_OWNER` varchar(255)  NULL DEFAULT NULL,
  `LOCK_TIME` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED` tinyint(0) NULL DEFAULT 0,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`LOG_NR`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ge_bytearray
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_bytearray`;
CREATE TABLE `act_ge_bytearray`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(64)  NULL DEFAULT NULL,
  `BYTES` longblob NULL,
  `GENERATED` tinyint(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_FK_BYTEARR_DEPL`(`DEPLOYMENT_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID`) REFERENCES `act_re_deployment` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ge_property
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_property`;
CREATE TABLE `act_ge_property`  (
  `NAME` varchar(64)  NOT NULL DEFAULT '',
  `VALUE` varchar(300)  NULL DEFAULT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`NAME`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_actinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_actinst`;
CREATE TABLE `act_hi_actinst`  (
  `ID` varchar(64)  NOT NULL,
  `PROC_DEF_ID` varchar(64)  NOT NULL,
  `PROC_INST_ID` varchar(64)  NOT NULL,
  `EXECUTION_ID` varchar(64)  NOT NULL,
  `ACT_ID` varchar(255)  NOT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `CALL_PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `ACT_NAME` varchar(255)  NULL DEFAULT NULL,
  `ACT_TYPE` varchar(255)  NOT NULL,
  `ASSIGNEE` varchar(255)  NULL DEFAULT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `DURATION` bigint(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_START`(`START_TIME`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_END`(`END_TIME`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_PROCINST`(`PROC_INST_ID`, `ACT_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_EXEC`(`EXECUTION_ID`, `ACT_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_attachment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_attachment`;
CREATE TABLE `act_hi_attachment`  (
  `ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `USER_ID` varchar(255)  NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `DESCRIPTION` varchar(4000)  NULL DEFAULT NULL,
  `TYPE` varchar(255)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `URL` varchar(4000)  NULL DEFAULT NULL,
  `CONTENT_ID` varchar(64)  NULL DEFAULT NULL,
  `TIME` datetime(3) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_comment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_comment`;
CREATE TABLE `act_hi_comment`  (
  `ID` varchar(64)  NOT NULL,
  `TYPE` varchar(255)  NULL DEFAULT NULL,
  `TIME` datetime(0) NULL DEFAULT NULL,
  `USER_ID` varchar(255)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `ACTION` varchar(255)  NULL DEFAULT NULL,
  `MESSAGE` varchar(4000)  NULL DEFAULT NULL,
  `FULL_MSG` longblob NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_detail
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_detail`;
CREATE TABLE `act_hi_detail`  (
  `ID` varchar(64)  NOT NULL,
  `TYPE` varchar(255)  NOT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `ACT_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `NAME` varchar(255)  NOT NULL,
  `VAR_TYPE` varchar(255)  NULL DEFAULT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `TIME` datetime(0) NULL DEFAULT NULL,
  `BYTEARRAY_ID` varchar(64)  NULL DEFAULT NULL,
  `DOUBLE` double NULL DEFAULT NULL,
  `LONG` bigint(0) NULL DEFAULT NULL,
  `TEXT` varchar(4000)  NULL DEFAULT NULL,
  `TEXT2` varchar(4000)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_PROC_INST`(`PROC_INST_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_ACT_INST`(`ACT_INST_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_TIME`(`TIME`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_NAME`(`NAME`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_TASK_ID`(`TASK_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_identitylink`;
CREATE TABLE `act_hi_identitylink`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `GROUP_ID` varchar(255)  NULL DEFAULT NULL,
  `TYPE` varchar(255)  NULL DEFAULT NULL,
  `USER_ID` varchar(255)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_USER`(`USER_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_TASK`(`TASK_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_PROCINST`(`PROC_INST_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_procinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_procinst`;
CREATE TABLE `act_hi_procinst`  (
  `ID` varchar(64)  NOT NULL,
  `PROC_INST_ID` varchar(64)  NOT NULL,
  `BUSINESS_KEY` varchar(255)  NULL DEFAULT NULL,
  `PROC_DEF_ID` varchar(64)  NOT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `DURATION` bigint(0) NULL DEFAULT NULL,
  `START_USER_ID` varchar(255)  NULL DEFAULT NULL,
  `START_ACT_ID` varchar(255)  NULL DEFAULT NULL,
  `END_ACT_ID` varchar(255)  NULL DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID` varchar(64)  NULL DEFAULT NULL,
  `DELETE_REASON` varchar(4000)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  `NAME` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `PROC_INST_ID`(`PROC_INST_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_PRO_INST_END`(`END_TIME`) USING BTREE,
  INDEX `ACT_IDX_HI_PRO_I_BUSKEY`(`BUSINESS_KEY`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_taskinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_taskinst`;
CREATE TABLE `act_hi_taskinst`  (
  `ID` varchar(64)  NOT NULL,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `TASK_DEF_KEY` varchar(255)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `PARENT_TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `DESCRIPTION` varchar(4000)  NULL DEFAULT NULL,
  `OWNER` varchar(255)  NULL DEFAULT NULL,
  `ASSIGNEE` varchar(255)  NULL DEFAULT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `CLAIM_TIME` datetime(0) NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `DURATION` bigint(0) NULL DEFAULT NULL,
  `DELETE_REASON` varchar(4000)  NULL DEFAULT NULL,
  `PRIORITY` int(0) NULL DEFAULT NULL,
  `DUE_DATE` datetime(0) NULL DEFAULT NULL,
  `FORM_KEY` varchar(255)  NULL DEFAULT NULL,
  `CATEGORY` varchar(255)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_HI_TASK_INST_PROCINST`(`PROC_INST_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_varinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_varinst`;
CREATE TABLE `act_hi_varinst`  (
  `ID` varchar(64)  NOT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `NAME` varchar(255)  NOT NULL,
  `VAR_TYPE` varchar(100)  NULL DEFAULT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `BYTEARRAY_ID` varchar(64)  NULL DEFAULT NULL,
  `DOUBLE` double NULL DEFAULT NULL,
  `LONG` bigint(0) NULL DEFAULT NULL,
  `TEXT` varchar(4000)  NULL DEFAULT NULL,
  `TEXT2` varchar(4000)  NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `LAST_UPDATED_TIME` datetime(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_PROC_INST`(`PROC_INST_ID`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_NAME_TYPE`(`NAME`, `VAR_TYPE`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_TASK_ID`(`TASK_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_id_group
-- ----------------------------
DROP TABLE IF EXISTS `act_id_group`;
CREATE TABLE `act_id_group`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `TYPE` varchar(255)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_id_info
-- ----------------------------
DROP TABLE IF EXISTS `act_id_info`;
CREATE TABLE `act_id_info`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `USER_ID` varchar(64)  NULL DEFAULT NULL,
  `TYPE` varchar(64)  NULL DEFAULT NULL,
  `KEY` varchar(255)  NULL DEFAULT NULL,
  `VALUE` varchar(255)  NULL DEFAULT NULL,
  `PASSWORD` longblob NULL,
  `PARENT_ID` varchar(255)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_id_membership
-- ----------------------------
DROP TABLE IF EXISTS `act_id_membership`;
CREATE TABLE `act_id_membership`  (
  `USER_ID` varchar(64)  NOT NULL DEFAULT '',
  `GROUP_ID` varchar(64)  NOT NULL DEFAULT '',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`USER_ID`, `GROUP_ID`) USING BTREE,
  INDEX `ACT_FK_MEMB_GROUP`(`GROUP_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID`) REFERENCES `act_id_group` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID`) REFERENCES `act_id_user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_id_user
-- ----------------------------
DROP TABLE IF EXISTS `act_id_user`;
CREATE TABLE `act_id_user`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `FIRST` varchar(255)  NULL DEFAULT NULL,
  `LAST` varchar(255)  NULL DEFAULT NULL,
  `EMAIL` varchar(255)  NULL DEFAULT NULL,
  `PWD` varchar(255)  NULL DEFAULT NULL,
  `PICTURE_ID` varchar(64)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_procdef_info
-- ----------------------------
DROP TABLE IF EXISTS `act_procdef_info`;
CREATE TABLE `act_procdef_info`  (
  `ID` varchar(64)  NOT NULL,
  `PROC_DEF_ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `INFO_JSON_ID` varchar(64)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ACT_UNIQ_INFO_PROCDEF`(`PROC_DEF_ID`) USING BTREE,
  INDEX `ACT_IDX_INFO_PROCDEF`(`PROC_DEF_ID`) USING BTREE,
  INDEX `ACT_FK_INFO_JSON_BA`(`INFO_JSON_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID`) REFERENCES `act_ge_bytearray` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID`) REFERENCES `act_re_procdef` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_re_deployment`;
CREATE TABLE `act_re_deployment`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `CATEGORY` varchar(255)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  `DEPLOY_TIME` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_re_model
-- ----------------------------
DROP TABLE IF EXISTS `act_re_model`;
CREATE TABLE `act_re_model`  (
  `ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `KEY` varchar(255)  NULL DEFAULT NULL,
  `CATEGORY` varchar(255)  NULL DEFAULT NULL,
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` timestamp(0) NULL DEFAULT NULL,
  `VERSION` int(0) NULL DEFAULT NULL,
  `META_INFO` varchar(4000)  NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(64)  NULL DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID` varchar(64)  NULL DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID` varchar(64)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_FK_MODEL_SOURCE`(`EDITOR_SOURCE_VALUE_ID`) USING BTREE,
  INDEX `ACT_FK_MODEL_SOURCE_EXTRA`(`EDITOR_SOURCE_EXTRA_VALUE_ID`) USING BTREE,
  INDEX `ACT_FK_MODEL_DEPLOYMENT`(`DEPLOYMENT_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID`) REFERENCES `act_re_deployment` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID`) REFERENCES `act_ge_bytearray` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID`) REFERENCES `act_ge_bytearray` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS `act_re_procdef`;
CREATE TABLE `act_re_procdef`  (
  `ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `CATEGORY` varchar(255)  NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `KEY` varchar(255)  NOT NULL,
  `VERSION` int(0) NOT NULL,
  `DEPLOYMENT_ID` varchar(64)  NULL DEFAULT NULL,
  `RESOURCE_NAME` varchar(4000)  NULL DEFAULT NULL,
  `DGRM_RESOURCE_NAME` varchar(4000)  NULL DEFAULT NULL,
  `DESCRIPTION` varchar(4000)  NULL DEFAULT NULL,
  `HAS_START_FORM_KEY` tinyint(0) NULL DEFAULT NULL,
  `SUSPENSION_STATE` int(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  `HAS_GRAPHICAL_NOTATION` tinyint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ACT_UNIQ_PROCDEF`(`KEY`, `VERSION`, `TENANT_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_event_subscr
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_event_subscr`;
CREATE TABLE `act_ru_event_subscr`  (
  `ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `EVENT_TYPE` varchar(255)  NOT NULL,
  `EVENT_NAME` varchar(255)  NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `ACTIVITY_ID` varchar(64)  NULL DEFAULT NULL,
  `CONFIGURATION` varchar(255)  NULL DEFAULT NULL,
  `CREATED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_EVENT_SUBSCR_CONFIG`(`CONFIGURATION`) USING BTREE,
  INDEX `ACT_FK_EVENT_EXEC`(`EXECUTION_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_execution`;
CREATE TABLE `act_ru_execution`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `BUSINESS_KEY` varchar(255)  NULL DEFAULT NULL,
  `PARENT_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `SUPER_EXEC` varchar(64)  NULL DEFAULT NULL,
  `ACT_ID` varchar(255)  NULL DEFAULT NULL,
  `IS_ACTIVE` tinyint(0) NULL DEFAULT NULL,
  `IS_CONCURRENT` tinyint(0) NULL DEFAULT NULL,
  `IS_SCOPE` tinyint(0) NULL DEFAULT NULL,
  `IS_EVENT_SCOPE` tinyint(0) NULL DEFAULT NULL,
  `SUSPENSION_STATE` int(0) NULL DEFAULT NULL,
  `CACHED_ENT_STATE` int(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `LOCK_TIME` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_EXEC_BUSKEY`(`BUSINESS_KEY`) USING BTREE,
  INDEX `ACT_FK_EXE_PROCINST`(`PROC_INST_ID`) USING BTREE,
  INDEX `ACT_FK_EXE_PARENT`(`PARENT_ID`) USING BTREE,
  INDEX `ACT_FK_EXE_SUPER`(`SUPER_EXEC`) USING BTREE,
  INDEX `ACT_FK_EXE_PROCDEF`(`PROC_DEF_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID`) REFERENCES `act_re_procdef` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_identitylink`;
CREATE TABLE `act_ru_identitylink`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `GROUP_ID` varchar(255)  NULL DEFAULT NULL,
  `TYPE` varchar(255)  NULL DEFAULT NULL,
  `USER_ID` varchar(255)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_IDENT_LNK_USER`(`USER_ID`) USING BTREE,
  INDEX `ACT_IDX_IDENT_LNK_GROUP`(`GROUP_ID`) USING BTREE,
  INDEX `ACT_IDX_ATHRZ_PROCEDEF`(`PROC_DEF_ID`) USING BTREE,
  INDEX `ACT_FK_TSKASS_TASK`(`TASK_ID`) USING BTREE,
  INDEX `ACT_FK_IDL_PROCINST`(`PROC_INST_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID`) REFERENCES `act_re_procdef` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID`) REFERENCES `act_ru_task` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_job`;
CREATE TABLE `act_ru_job`  (
  `ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `TYPE` varchar(255)  NOT NULL,
  `LOCK_EXP_TIME` timestamp(0) NULL DEFAULT NULL,
  `LOCK_OWNER` varchar(255)  NULL DEFAULT NULL,
  `EXCLUSIVE` tinyint(1) NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `PROCESS_INSTANCE_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `RETRIES` int(0) NULL DEFAULT NULL,
  `EXCEPTION_STACK_ID` varchar(64)  NULL DEFAULT NULL,
  `EXCEPTION_MSG` varchar(4000)  NULL DEFAULT NULL,
  `DUEDATE` timestamp(0) NULL DEFAULT NULL,
  `REPEAT` varchar(255)  NULL DEFAULT NULL,
  `HANDLER_TYPE` varchar(255)  NULL DEFAULT NULL,
  `HANDLER_CFG` varchar(4000)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_FK_JOB_EXCEPTION`(`EXCEPTION_STACK_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID`) REFERENCES `act_ge_bytearray` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_task
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_task`;
CREATE TABLE `act_ru_task`  (
  `ID` varchar(64)  NOT NULL DEFAULT '',
  `REV` int(0) NULL DEFAULT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_DEF_ID` varchar(64)  NULL DEFAULT NULL,
  `NAME` varchar(255)  NULL DEFAULT NULL,
  `PARENT_TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `DESCRIPTION` varchar(4000)  NULL DEFAULT NULL,
  `TASK_DEF_KEY` varchar(255)  NULL DEFAULT NULL,
  `OWNER` varchar(255)  NULL DEFAULT NULL,
  `ASSIGNEE` varchar(255)  NULL DEFAULT NULL,
  `DELEGATION` varchar(64)  NULL DEFAULT NULL,
  `PRIORITY` int(0) NULL DEFAULT NULL,
  `CREATE_TIME` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE` datetime(0) NULL DEFAULT NULL,
  `CATEGORY` varchar(255)  NULL DEFAULT NULL,
  `SUSPENSION_STATE` int(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '',
  `FORM_KEY` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_TASK_CREATE`(`CREATE_TIME`) USING BTREE,
  INDEX `ACT_FK_TASK_EXE`(`EXECUTION_ID`) USING BTREE,
  INDEX `ACT_FK_TASK_PROCINST`(`PROC_INST_ID`) USING BTREE,
  INDEX `ACT_FK_TASK_PROCDEF`(`PROC_DEF_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID`) REFERENCES `act_re_procdef` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_variable
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_variable`;
CREATE TABLE `act_ru_variable`  (
  `ID` varchar(64)  NOT NULL,
  `REV` int(0) NULL DEFAULT NULL,
  `TYPE` varchar(255)  NOT NULL,
  `NAME` varchar(255)  NOT NULL,
  `EXECUTION_ID` varchar(64)  NULL DEFAULT NULL,
  `PROC_INST_ID` varchar(64)  NULL DEFAULT NULL,
  `TASK_ID` varchar(64)  NULL DEFAULT NULL,
  `BYTEARRAY_ID` varchar(64)  NULL DEFAULT NULL,
  `DOUBLE` double NULL DEFAULT NULL,
  `LONG` bigint(0) NULL DEFAULT NULL,
  `TEXT` varchar(4000)  NULL DEFAULT NULL,
  `TEXT2` varchar(4000)  NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACT_IDX_VARIABLE_TASK_ID`(`TASK_ID`) USING BTREE,
  INDEX `ACT_FK_VAR_EXE`(`EXECUTION_ID`) USING BTREE,
  INDEX `ACT_FK_VAR_PROCINST`(`PROC_INST_ID`) USING BTREE,
  INDEX `ACT_FK_VAR_BYTEARRAY`(`BYTEARRAY_ID`) USING BTREE,
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID`) REFERENCES `act_ge_bytearray` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID`) REFERENCES `act_ru_execution` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_common_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_common_opt_log`;
CREATE TABLE `c_common_opt_log`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `request_ip` varchar(50)  NULL DEFAULT '' COMMENT '操作IP',
  `type` varchar(5)  NULL DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
  `user_name` varchar(50)  NULL DEFAULT '' COMMENT '操作人',
  `description` varchar(255)  NULL DEFAULT '' COMMENT '操作描述',
  `class_path` varchar(255)  NULL DEFAULT '' COMMENT '类路径',
  `action_method` varchar(50)  NULL DEFAULT '' COMMENT '请求方法',
  `request_uri` varchar(50)  NULL DEFAULT '' COMMENT '请求地址',
  `http_method` varchar(10)  NULL DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `params` longtext  NULL COMMENT '请求参数',
  `result` longtext  NULL COMMENT '返回值',
  `ex_desc` longtext  NULL COMMENT '异常详情信息',
  `ex_detail` longtext  NULL COMMENT '异常描述',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp(0) NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(0) NULL DEFAULT 0 COMMENT '消耗时间',
  `ua` varchar(500)  NULL DEFAULT '' COMMENT '浏览器',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_type`(`type`) USING BTREE COMMENT '日志类型'
) ENGINE = InnoDB  COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `category_id` varchar(64)  NOT NULL COMMENT '栏目编号',
  `title` varchar(255)  NOT NULL COMMENT '标题',
  `link` varchar(255)  NULL DEFAULT NULL COMMENT '文章链接',
  `color` varchar(50)  NULL DEFAULT NULL COMMENT '标题颜色',
  `image` varchar(255)  NULL DEFAULT NULL COMMENT '文章图片',
  `keywords` varchar(255)  NULL DEFAULT NULL COMMENT '关键字',
  `description` varchar(255)  NULL DEFAULT NULL COMMENT '描述、摘要',
  `weight` int(0) NULL DEFAULT 0 COMMENT '权重，越大越靠前',
  `weight_date` datetime(0) NULL DEFAULT NULL COMMENT '权重期限',
  `hits` int(0) NULL DEFAULT 0 COMMENT '点击数',
  `posid` varchar(10)  NULL DEFAULT NULL COMMENT '推荐位，多选',
  `custom_content_view` varchar(255)  NULL DEFAULT NULL COMMENT '自定义内容视图',
  `view_config` text  NULL COMMENT '视图配置',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_article_create_by`(`create_by`) USING BTREE,
  INDEX `cms_article_title`(`title`) USING BTREE,
  INDEX `cms_article_keywords`(`keywords`) USING BTREE,
  INDEX `cms_article_del_flag`(`del_flag`) USING BTREE,
  INDEX `cms_article_weight`(`weight`) USING BTREE,
  INDEX `cms_article_update_date`(`update_date`) USING BTREE,
  INDEX `cms_article_category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_article_data
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_data`;
CREATE TABLE `cms_article_data`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `content` text  NULL COMMENT '文章内容',
  `copyfrom` varchar(255)  NULL DEFAULT NULL COMMENT '文章来源',
  `relation` varchar(255)  NULL DEFAULT NULL COMMENT '相关文章',
  `allow_comment` char(1)  NULL DEFAULT NULL COMMENT '是否允许评论',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '文章详表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `parent_id` varchar(64)  NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000)  NOT NULL COMMENT '所有父级编号',
  `site_id` varchar(64)  NULL DEFAULT '1' COMMENT '站点编号',
  `office_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属机构',
  `module` varchar(20)  NULL DEFAULT NULL COMMENT '栏目模块',
  `name` varchar(100)  NOT NULL COMMENT '栏目名称',
  `image` varchar(255)  NULL DEFAULT NULL COMMENT '栏目图片',
  `href` varchar(255)  NULL DEFAULT NULL COMMENT '链接',
  `target` varchar(20)  NULL DEFAULT NULL COMMENT '目标',
  `description` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `keywords` varchar(255)  NULL DEFAULT NULL COMMENT '关键字',
  `sort` int(0) NULL DEFAULT 30 COMMENT '排序（升序）',
  `in_menu` char(1)  NULL DEFAULT '1' COMMENT '是否在导航中显示',
  `in_list` char(1)  NULL DEFAULT '1' COMMENT '是否在分类页中显示列表',
  `show_modes` char(1)  NULL DEFAULT '0' COMMENT '展现方式',
  `allow_comment` char(1)  NULL DEFAULT NULL COMMENT '是否允许评论',
  `is_audit` char(1)  NULL DEFAULT NULL COMMENT '是否需要审核',
  `custom_list_view` varchar(255)  NULL DEFAULT NULL COMMENT '自定义列表视图',
  `custom_content_view` varchar(255)  NULL DEFAULT NULL COMMENT '自定义内容视图',
  `view_config` text  NULL COMMENT '视图配置',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_category_parent_id`(`parent_id`) USING BTREE,
  INDEX `cms_category_module`(`module`) USING BTREE,
  INDEX `cms_category_name`(`name`) USING BTREE,
  INDEX `cms_category_sort`(`sort`) USING BTREE,
  INDEX `cms_category_del_flag`(`del_flag`) USING BTREE,
  INDEX `cms_category_office_id`(`office_id`) USING BTREE,
  INDEX `cms_category_site_id`(`site_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '栏目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `category_id` varchar(64)  NOT NULL COMMENT '栏目编号',
  `content_id` varchar(64)  NOT NULL COMMENT '栏目内容的编号',
  `title` varchar(255)  NULL DEFAULT NULL COMMENT '栏目内容的标题',
  `content` varchar(255)  NULL DEFAULT NULL COMMENT '评论内容',
  `name` varchar(100)  NULL DEFAULT NULL COMMENT '评论姓名',
  `ip` varchar(100)  NULL DEFAULT NULL COMMENT '评论IP',
  `create_date` datetime(0) NOT NULL COMMENT '评论时间',
  `audit_user_id` varchar(64)  NULL DEFAULT NULL COMMENT '审核人',
  `audit_date` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_comment_category_id`(`category_id`) USING BTREE,
  INDEX `cms_comment_content_id`(`content_id`) USING BTREE,
  INDEX `cms_comment_status`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_guestbook
-- ----------------------------
DROP TABLE IF EXISTS `cms_guestbook`;
CREATE TABLE `cms_guestbook`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `type` char(1)  NOT NULL COMMENT '留言分类',
  `content` varchar(255)  NOT NULL COMMENT '留言内容',
  `name` varchar(100)  NOT NULL COMMENT '姓名',
  `email` varchar(100)  NOT NULL COMMENT '邮箱',
  `phone` varchar(100)  NOT NULL COMMENT '电话',
  `workunit` varchar(100)  NOT NULL COMMENT '单位',
  `ip` varchar(100)  NOT NULL COMMENT 'IP',
  `create_date` datetime(0) NOT NULL COMMENT '留言时间',
  `re_user_id` varchar(64)  NULL DEFAULT NULL COMMENT '回复人',
  `re_date` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `re_content` varchar(100)  NULL DEFAULT NULL COMMENT '回复内容',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_guestbook_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '留言板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `category_id` varchar(64)  NOT NULL COMMENT '栏目编号',
  `title` varchar(255)  NOT NULL COMMENT '链接名称',
  `color` varchar(50)  NULL DEFAULT NULL COMMENT '标题颜色',
  `image` varchar(255)  NULL DEFAULT NULL COMMENT '链接图片',
  `href` varchar(255)  NULL DEFAULT NULL COMMENT '链接地址',
  `weight` int(0) NULL DEFAULT 0 COMMENT '权重，越大越靠前',
  `weight_date` datetime(0) NULL DEFAULT NULL COMMENT '权重期限',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_link_category_id`(`category_id`) USING BTREE,
  INDEX `cms_link_title`(`title`) USING BTREE,
  INDEX `cms_link_del_flag`(`del_flag`) USING BTREE,
  INDEX `cms_link_weight`(`weight`) USING BTREE,
  INDEX `cms_link_create_by`(`create_by`) USING BTREE,
  INDEX `cms_link_update_date`(`update_date`) USING BTREE
) ENGINE = InnoDB  COMMENT = '友情链接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `name` varchar(100)  NOT NULL COMMENT '站点名称',
  `title` varchar(100)  NOT NULL COMMENT '站点标题',
  `logo` varchar(255)  NULL DEFAULT NULL COMMENT '站点Logo',
  `domain` varchar(255)  NULL DEFAULT NULL COMMENT '站点域名',
  `description` varchar(255)  NULL DEFAULT NULL COMMENT '描述',
  `keywords` varchar(255)  NULL DEFAULT NULL COMMENT '关键字',
  `theme` varchar(255)  NULL DEFAULT 'default' COMMENT '主题',
  `copyright` text  NULL COMMENT '版权信息',
  `custom_index_view` varchar(255)  NULL DEFAULT NULL COMMENT '自定义站点首页视图',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cms_site_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '站点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for d_tenant
-- ----------------------------
DROP TABLE IF EXISTS `d_tenant`;
CREATE TABLE `d_tenant`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `code` varchar(20)  NULL DEFAULT '' COMMENT '企业编码',
  `name` varchar(255)  NULL DEFAULT '' COMMENT '企业名称',
  `type` varchar(10)  NULL DEFAULT 'CREATE' COMMENT '类型\n#{CREATE:创建;REGISTER:注册}',
  `status` varchar(10)  NULL DEFAULT 'NORMAL' COMMENT '状态\n#{NORMAL:正常;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝}',
  `readonly` bit(1) NULL DEFAULT b'0' COMMENT '是否内置',
  `duty` varchar(50)  NULL DEFAULT NULL COMMENT '责任人',
  `expiration_time` datetime(0) NULL DEFAULT NULL COMMENT '有效期\n为空表示永久',
  `logo` varchar(255)  NULL DEFAULT '' COMMENT 'logo地址',
  `describe` varchar(255)  NULL DEFAULT '' COMMENT '企业简介',
  `password_expire` int(0) NULL DEFAULT 0 COMMENT '用户密码有效期\n单位：天 0表示永久有效\n',
  `is_multiple_login` bit(1) NULL DEFAULT b'1' COMMENT '是否多地登录',
  `password_error_num` int(0) NULL DEFAULT 10 COMMENT '密码输错次数\n密码输错锁定账号的次数\n单位：次\n',
  `password_error_lock_time` varchar(50)  NULL DEFAULT '0' COMMENT '账号锁定时间\n密码输错${passwordErrorNum}次后，锁定账号的时间\n单位： h | d | w | m\n单位： 时 | 天 | 周 | 月\n如：0=当天晚上24点 2h = 2小时   2d = 2天  ',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(0) NULL DEFAULT NULL COMMENT '修改人',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UN_CODE`(`code`) USING BTREE COMMENT '租户唯一编码'
) ENGINE = InnoDB  COMMENT = '企业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_scheme
-- ----------------------------
DROP TABLE IF EXISTS `gen_scheme`;
CREATE TABLE `gen_scheme`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `name` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `category` varchar(2000)  NULL DEFAULT NULL COMMENT '分类',
  `package_name` varchar(500)  NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30)  NULL DEFAULT NULL COMMENT '生成模块名',
  `sub_module_name` varchar(30)  NULL DEFAULT NULL COMMENT '生成子模块名',
  `function_name` varchar(500)  NULL DEFAULT NULL COMMENT '生成功能名',
  `function_name_simple` varchar(100)  NULL DEFAULT NULL COMMENT '生成功能名（简写）',
  `function_author` varchar(100)  NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_table_id` varchar(200)  NULL DEFAULT NULL COMMENT '生成表编号',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gen_scheme_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '生成方案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `name` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `comments` varchar(500)  NULL DEFAULT NULL COMMENT '描述',
  `class_name` varchar(100)  NULL DEFAULT NULL COMMENT '实体类名称',
  `parent_table` varchar(200)  NULL DEFAULT NULL COMMENT '关联父表',
  `parent_table_fk` varchar(100)  NULL DEFAULT NULL COMMENT '关联父表外键',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gen_table_name`(`name`) USING BTREE,
  INDEX `gen_table_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `gen_table_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属表编号',
  `name` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `comments` varchar(500)  NULL DEFAULT NULL COMMENT '描述',
  `jdbc_type` varchar(100)  NULL DEFAULT NULL COMMENT '列的数据类型的字节长度',
  `java_type` varchar(500)  NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200)  NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1)  NULL DEFAULT NULL COMMENT '是否主键',
  `is_null` char(1)  NULL DEFAULT NULL COMMENT '是否可为空',
  `is_insert` char(1)  NULL DEFAULT NULL COMMENT '是否为插入字段',
  `is_edit` char(1)  NULL DEFAULT NULL COMMENT '是否编辑字段',
  `is_list` char(1)  NULL DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1)  NULL DEFAULT NULL COMMENT '是否查询字段',
  `query_type` varchar(200)  NULL DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）',
  `show_type` varchar(200)  NULL DEFAULT NULL COMMENT '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）',
  `dict_type` varchar(200)  NULL DEFAULT NULL COMMENT '字典类型',
  `settings` varchar(2000)  NULL DEFAULT NULL COMMENT '其它设置（扩展字段JSON）',
  `sort` decimal(10, 0) NULL DEFAULT NULL COMMENT '排序（升序）',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gen_table_column_table_id`(`gen_table_id`) USING BTREE,
  INDEX `gen_table_column_name`(`name`) USING BTREE,
  INDEX `gen_table_column_sort`(`sort`) USING BTREE,
  INDEX `gen_table_column_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_template
-- ----------------------------
DROP TABLE IF EXISTS `gen_template`;
CREATE TABLE `gen_template`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `name` varchar(200)  NULL DEFAULT NULL COMMENT '名称',
  `category` varchar(2000)  NULL DEFAULT NULL COMMENT '分类',
  `file_path` varchar(500)  NULL DEFAULT NULL COMMENT '生成文件路径',
  `file_name` varchar(200)  NULL DEFAULT NULL COMMENT '生成文件名',
  `content` text  NULL COMMENT '内容',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gen_template_del_falg`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '代码模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oa_leave
-- ----------------------------
DROP TABLE IF EXISTS `oa_leave`;
CREATE TABLE `oa_leave`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `process_instance_id` varchar(64)  NULL DEFAULT NULL COMMENT '流程实例编号',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `leave_type` varchar(20)  NULL DEFAULT NULL COMMENT '请假类型',
  `reason` varchar(255)  NULL DEFAULT NULL COMMENT '请假理由',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `reality_start_time` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `reality_end_time` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oa_leave_create_by`(`create_by`) USING BTREE,
  INDEX `oa_leave_process_instance_id`(`process_instance_id`) USING BTREE,
  INDEX `oa_leave_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '请假流程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oa_notify
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE `oa_notify`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `type` char(1)  NULL DEFAULT NULL COMMENT '类型',
  `title` varchar(200)  NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000)  NULL DEFAULT NULL COMMENT '内容',
  `files` varchar(2000)  NULL DEFAULT NULL COMMENT '附件',
  `status` char(1)  NULL DEFAULT NULL COMMENT '状态',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oa_notify_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '通知通告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oa_notify_record
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE `oa_notify_record`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `oa_notify_id` varchar(64)  NULL DEFAULT NULL COMMENT '通知通告ID',
  `user_id` varchar(64)  NULL DEFAULT NULL COMMENT '接受人',
  `read_flag` char(1)  NULL DEFAULT '0' COMMENT '阅读标记',
  `read_date` date NULL DEFAULT NULL COMMENT '阅读时间',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oa_notify_record_notify_id`(`oa_notify_id`) USING BTREE,
  INDEX `oa_notify_record_user_id`(`user_id`) USING BTREE,
  INDEX `oa_notify_record_read_flag`(`read_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '通知通告发送记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oa_test_audit
-- ----------------------------
DROP TABLE IF EXISTS `oa_test_audit`;
CREATE TABLE `oa_test_audit`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `PROC_INS_ID` varchar(64)  NULL DEFAULT NULL COMMENT '流程实例ID',
  `USER_ID` varchar(64)  NULL DEFAULT NULL COMMENT '变动用户',
  `OFFICE_ID` varchar(64)  NULL DEFAULT NULL COMMENT '归属部门',
  `POST` varchar(255)  NULL DEFAULT NULL COMMENT '岗位',
  `AGE` char(1)  NULL DEFAULT NULL COMMENT '性别',
  `EDU` varchar(255)  NULL DEFAULT NULL COMMENT '学历',
  `CONTENT` varchar(255)  NULL DEFAULT NULL COMMENT '调整原因',
  `OLDA` varchar(255)  NULL DEFAULT NULL COMMENT '现行标准 薪酬档级',
  `OLDB` varchar(255)  NULL DEFAULT NULL COMMENT '现行标准 月工资额',
  `OLDC` varchar(255)  NULL DEFAULT NULL COMMENT '现行标准 年薪总额',
  `NEWA` varchar(255)  NULL DEFAULT NULL COMMENT '调整后标准 薪酬档级',
  `NEWB` varchar(255)  NULL DEFAULT NULL COMMENT '调整后标准 月工资额',
  `NEWC` varchar(255)  NULL DEFAULT NULL COMMENT '调整后标准 年薪总额',
  `ADD_NUM` varchar(255)  NULL DEFAULT NULL COMMENT '月增资',
  `EXE_DATE` varchar(255)  NULL DEFAULT NULL COMMENT '执行时间',
  `HR_TEXT` varchar(255)  NULL DEFAULT NULL COMMENT '人力资源部门意见',
  `LEAD_TEXT` varchar(255)  NULL DEFAULT NULL COMMENT '分管领导意见',
  `MAIN_LEAD_TEXT` varchar(255)  NULL DEFAULT NULL COMMENT '集团主要领导意见',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `OA_TEST_AUDIT_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '审批流程测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(128)  NULL DEFAULT NULL COMMENT '客户端标识',
  `resource_ids` varchar(256)  NULL DEFAULT NULL COMMENT '要访问的资源编码',
  `client_secret` varchar(256)  NULL DEFAULT NULL COMMENT '客户端密码',
  `scope` varchar(256)  NULL DEFAULT NULL COMMENT '访问范围',
  `authorized_grant_types` varchar(256)  NULL DEFAULT NULL COMMENT '认证方式',
  `web_server_redirect_uri` varchar(256)  NULL DEFAULT NULL COMMENT '重定义URL',
  `authorities` varchar(256)  NULL DEFAULT NULL COMMENT '授权权限',
  `access_token_validity` int(0) NULL DEFAULT NULL COMMENT '访问token有效期',
  `refresh_token_validity` int(0) NULL DEFAULT NULL COMMENT '刷心token有效期',
  `additional_information` varchar(100)  NULL DEFAULT NULL COMMENT '额外的数据',
  `autoapprove` varchar(20)  NULL DEFAULT NULL,
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_resource
-- ----------------------------
DROP TABLE IF EXISTS `oauth_resource`;
CREATE TABLE `oauth_resource`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `oauth_resource_id` varchar(20)  NULL DEFAULT NULL COMMENT '资源id',
  `prefix` varchar(20)  NULL DEFAULT NULL COMMENT '路由前缀',
  `oauth_resource_path` varchar(20)  NULL DEFAULT NULL COMMENT '资源路径',
  `oauth_service_id` varchar(20)  NULL DEFAULT NULL COMMENT '服务id对应注册中心的Id',
  `role_id` varchar(64)  NULL DEFAULT NULL COMMENT '角色编号',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_api_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_log`;
CREATE TABLE `sys_api_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50)  NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50)  NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200)  NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000)  NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(0) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64)  NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `parent_id` varchar(64)  NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000)  NOT NULL COMMENT '所有父级编号',
  `name` varchar(100)  NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `code` varchar(100)  NULL DEFAULT NULL COMMENT '区域编码',
  `type` char(1)  NULL DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_area_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_area_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `value` varchar(100)  NOT NULL COMMENT '数据值',
  `label` varchar(100)  NOT NULL COMMENT '标签名',
  `type` varchar(100)  NOT NULL COMMENT '类型',
  `description` varchar(100)  NOT NULL COMMENT '描述',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64)  NULL DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`label`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `type` char(1)  NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255)  NULL DEFAULT '' COMMENT '日志标题',
  `create_by` varchar(64)  NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255)  NULL DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255)  NULL DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255)  NULL DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5)  NULL DEFAULT NULL COMMENT '操作方式',
  `params` text  NULL COMMENT '操作提交的数据',
  `exception` text  NULL COMMENT '异常信息',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_date`) USING BTREE
) ENGINE = InnoDB  COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_mdict
-- ----------------------------
DROP TABLE IF EXISTS `sys_mdict`;
CREATE TABLE `sys_mdict`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `parent_id` varchar(64)  NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000)  NOT NULL COMMENT '所有父级编号',
  `name` varchar(100)  NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `description` varchar(100)  NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_mdict_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_mdict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '多级字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `parent_id` varchar(64)  NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000)  NOT NULL COMMENT '所有父级编号',
  `name` varchar(100)  NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `href` varchar(2000)  NULL DEFAULT NULL COMMENT '链接',
  `target` varchar(20)  NULL DEFAULT NULL COMMENT '目标',
  `icon` varchar(100)  NULL DEFAULT NULL COMMENT '图标',
  `is_show` char(1)  NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200)  NULL DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_menu_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_menu_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `parent_id` varchar(64)  NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000)  NOT NULL COMMENT '所有父级编号',
  `name` varchar(100)  NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `area_id` varchar(64)  NOT NULL COMMENT '归属区域',
  `code` varchar(100)  NULL DEFAULT NULL COMMENT '区域编码',
  `type` char(1)  NOT NULL COMMENT '机构类型',
  `grade` char(1)  NOT NULL COMMENT '机构等级',
  `address` varchar(255)  NULL DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100)  NULL DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100)  NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200)  NULL DEFAULT NULL COMMENT '电话',
  `fax` varchar(200)  NULL DEFAULT NULL COMMENT '传真',
  `email` varchar(200)  NULL DEFAULT NULL COMMENT '邮箱',
  `USEABLE` varchar(64)  NULL DEFAULT NULL COMMENT '是否启用',
  `PRIMARY_PERSON` varchar(64)  NULL DEFAULT NULL COMMENT '主负责人',
  `DEPUTY_PERSON` varchar(64)  NULL DEFAULT NULL COMMENT '副负责人',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_office_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_office_del_flag`(`del_flag`) USING BTREE,
  INDEX `sys_office_type`(`type`) USING BTREE
) ENGINE = InnoDB  COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `office_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100)  NOT NULL COMMENT '角色名称',
  `enname` varchar(255)  NULL DEFAULT NULL COMMENT '英文名称',
  `role_type` varchar(255)  NULL DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1)  NULL DEFAULT NULL COMMENT '数据范围',
  `is_sys` varchar(64)  NULL DEFAULT NULL COMMENT '是否系统数据',
  `useable` varchar(64)  NULL DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_role_del_flag`(`del_flag`) USING BTREE,
  INDEX `sys_role_enname`(`enname`) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(64)  NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64)  NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色-菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_office`;
CREATE TABLE `sys_role_office`  (
  `role_id` varchar(64)  NOT NULL COMMENT '角色编号',
  `office_id` varchar(64)  NOT NULL COMMENT '机构编号',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`role_id`, `office_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色-机构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `company_id` varchar(64)  NOT NULL COMMENT '归属公司',
  `office_id` varchar(64)  NOT NULL COMMENT '归属部门',
  `login_name` varchar(100)  NOT NULL COMMENT '登录名',
  `password` varchar(100)  NOT NULL COMMENT '密码',
  `no` varchar(100)  NULL DEFAULT NULL COMMENT '工号',
  `name` varchar(100)  NOT NULL COMMENT '姓名',
  `email` varchar(200)  NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200)  NULL DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200)  NULL DEFAULT NULL COMMENT '手机',
  `user_type` char(1)  NULL DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(1000)  NULL DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100)  NULL DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64)  NULL DEFAULT NULL COMMENT '是否可登录',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_user_office_id`(`office_id`) USING BTREE,
  INDEX `sys_user_login_name`(`login_name`) USING BTREE,
  INDEX `sys_user_company_id`(`company_id`) USING BTREE,
  INDEX `sys_user_update_date`(`update_date`) USING BTREE,
  INDEX `sys_user_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(64)  NOT NULL COMMENT '用户编号',
  `role_id` varchar(64)  NOT NULL COMMENT '角色编号',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '用户-角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_data
-- ----------------------------
DROP TABLE IF EXISTS `test_data`;
CREATE TABLE `test_data`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `user_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属用户',
  `office_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属部门',
  `area_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属区域',
  `name` varchar(100)  NULL DEFAULT NULL COMMENT '名称',
  `sex` char(1)  NULL DEFAULT NULL COMMENT '性别',
  `in_date` date NULL DEFAULT NULL COMMENT '加入日期',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_data_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '业务数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_data_child
-- ----------------------------
DROP TABLE IF EXISTS `test_data_child`;
CREATE TABLE `test_data_child`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `test_data_main_id` varchar(64)  NULL DEFAULT NULL COMMENT '业务主表ID',
  `name` varchar(100)  NULL DEFAULT NULL COMMENT '名称',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_data_child_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '业务数据子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_data_main
-- ----------------------------
DROP TABLE IF EXISTS `test_data_main`;
CREATE TABLE `test_data_main`  (
  `id` varchar(64)  NOT NULL COMMENT '编号',
  `user_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属用户',
  `office_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属部门',
  `area_id` varchar(64)  NULL DEFAULT NULL COMMENT '归属区域',
  `name` varchar(100)  NULL DEFAULT NULL COMMENT '名称',
  `sex` char(1)  NULL DEFAULT NULL COMMENT '性别',
  `in_date` date NULL DEFAULT NULL COMMENT '加入日期',
  `create_by` varchar(64)  NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64)  NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1)  NOT NULL DEFAULT '0' COMMENT '删除标记',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_data_main_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB  COMMENT = '业务数据表' ROW_FORMAT = Dynamic;

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
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_tree_del_flag`(`del_flag`) USING BTREE,
  INDEX `test_data_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '树结构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint(0) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100)  NOT NULL COMMENT 'global transaction id',
  `context` varchar(128)  NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(0) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(0) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(0) NOT NULL COMMENT 'modify datetime',
  `TENANT_ID` varchar(255)  NULL DEFAULT '租户编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_blob_triggers`;
CREATE TABLE `xxl_job_qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `TRIGGER_NAME` varchar(200)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_calendars`;
CREATE TABLE `xxl_job_qrtz_calendars`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `CALENDAR_NAME` varchar(200)  NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_cron_triggers`;
CREATE TABLE `xxl_job_qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `TRIGGER_NAME` varchar(200)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  `CRON_EXPRESSION` varchar(200)  NOT NULL,
  `TIME_ZONE_ID` varchar(80)  NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_fired_triggers`;
CREATE TABLE `xxl_job_qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `ENTRY_ID` varchar(95)  NOT NULL,
  `TRIGGER_NAME` varchar(200)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  `INSTANCE_NAME` varchar(200)  NOT NULL,
  `FIRED_TIME` bigint(0) NOT NULL,
  `SCHED_TIME` bigint(0) NOT NULL,
  `PRIORITY` int(0) NOT NULL,
  `STATE` varchar(16)  NOT NULL,
  `JOB_NAME` varchar(200)  NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200)  NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1)  NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1)  NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_job_details`;
CREATE TABLE `xxl_job_qrtz_job_details`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `JOB_NAME` varchar(200)  NOT NULL,
  `JOB_GROUP` varchar(200)  NOT NULL,
  `DESCRIPTION` varchar(250)  NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250)  NOT NULL,
  `IS_DURABLE` varchar(1)  NOT NULL,
  `IS_NONCONCURRENT` varchar(1)  NOT NULL,
  `IS_UPDATE_DATA` varchar(1)  NOT NULL,
  `REQUESTS_RECOVERY` varchar(1)  NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_locks`;
CREATE TABLE `xxl_job_qrtz_locks`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `LOCK_NAME` varchar(40)  NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_paused_trigger_grps`;
CREATE TABLE `xxl_job_qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_scheduler_state`;
CREATE TABLE `xxl_job_qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `INSTANCE_NAME` varchar(200)  NOT NULL,
  `LAST_CHECKIN_TIME` bigint(0) NOT NULL,
  `CHECKIN_INTERVAL` bigint(0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simple_triggers`;
CREATE TABLE `xxl_job_qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `TRIGGER_NAME` varchar(200)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  `REPEAT_COUNT` bigint(0) NOT NULL,
  `REPEAT_INTERVAL` bigint(0) NOT NULL,
  `TIMES_TRIGGERED` bigint(0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simprop_triggers`;
CREATE TABLE `xxl_job_qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `TRIGGER_NAME` varchar(200)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  `STR_PROP_1` varchar(512)  NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512)  NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512)  NULL DEFAULT NULL,
  `INT_PROP_1` int(0) NULL DEFAULT NULL,
  `INT_PROP_2` int(0) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(0) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(0) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1)  NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1)  NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_group`;
CREATE TABLE `xxl_job_qrtz_trigger_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64)  NOT NULL COMMENT '执行器AppName',
  `title` varchar(12)  NOT NULL COMMENT '执行器名称',
  `order` tinyint(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `address_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512)  NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UN_APP_NAME`(`app_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2  COMMENT = ' 任务组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_info`;
CREATE TABLE `xxl_job_qrtz_trigger_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128)  NULL DEFAULT NULL COMMENT '任务执行CRON',
  `start_execute_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间 和 job_cron人选其一',
  `end_execute_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间 和 job_cron人选其一',
  `type` int(0) NOT NULL DEFAULT 1 COMMENT '执行类型 1：cron  2：定时',
  `job_desc` varchar(255)  NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64)  NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255)  NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50)  NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255)  NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512)  NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50)  NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(0) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50)  NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext  NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128)  NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255)  NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `interval_seconds` int(0) NULL DEFAULT NULL COMMENT '间隔秒数',
  `repeat_count` int(0) NULL DEFAULT NULL COMMENT '重复次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log`;
CREATE TABLE `xxl_job_qrtz_trigger_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255)  NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255)  NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512)  NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20)  NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(0) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text  NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(0) NOT NULL COMMENT '执行-状态',
  `handle_msg` text  NULL COMMENT '执行-日志',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_logglue`;
CREATE TABLE `xxl_job_qrtz_trigger_logglue`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50)  NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext  NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128)  NOT NULL COMMENT 'GLUE备注',
  `add_time` timestamp(0) NULL DEFAULT NULL,
  `update_date` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_registry`;
CREATE TABLE `xxl_job_qrtz_trigger_registry`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255)  NOT NULL,
  `registry_key` varchar(255)  NOT NULL,
  `registry_value` varchar(255)  NOT NULL,
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_triggers`;
CREATE TABLE `xxl_job_qrtz_triggers`  (
  `SCHED_NAME` varchar(120)  NOT NULL,
  `TRIGGER_NAME` varchar(200)  NOT NULL,
  `TRIGGER_GROUP` varchar(200)  NOT NULL,
  `JOB_NAME` varchar(200)  NOT NULL,
  `JOB_GROUP` varchar(200)  NOT NULL,
  `DESCRIPTION` varchar(250)  NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(0) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(0) NULL DEFAULT NULL,
  `PRIORITY` int(0) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16)  NOT NULL,
  `TRIGGER_TYPE` varchar(8)  NOT NULL,
  `START_TIME` bigint(0) NOT NULL,
  `END_TIME` bigint(0) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200)  NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(0) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `xxl_job_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
