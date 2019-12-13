/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : tech_notebook

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 13/12/2019 17:56:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tech_article
-- ----------------------------
DROP TABLE IF EXISTS `tech_article`;
CREATE TABLE `tech_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `subhead` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容文件链接',
  `state` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '状态：1.正常 2.停用 9.异常 99.删除',
  `type` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '类型：1.本人创建 2.转载 3.后台创建',
  `rank` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '等级：与权重对应，从低到高',
  `allow_comment` bit(1) NULL DEFAULT b'1' COMMENT '是否允许评论',
  `allow_reprint` bit(1) NULL DEFAULT b'1' COMMENT '是否允许转载',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tech_category
-- ----------------------------
DROP TABLE IF EXISTS `tech_category`;
CREATE TABLE `tech_category`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '系统编号',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父节点系统编号',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `priority` smallint(5) UNSIGNED NOT NULL COMMENT '排序优先级',
  `state` tinyint(4) UNSIGNED NOT NULL COMMENT '状态',
  `create_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '创建用户id',
  `create_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建用户名',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '编辑用户id',
  `update_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编辑用户名',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '编辑时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_priority`(`priority`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tech_category
-- ----------------------------
INSERT INTO `tech_category` VALUES (1, 1, 'test', 1, 1, 1, '1', '2019-06-15 17:50:55', 1, '1', '2019-06-15 17:50:55');

-- ----------------------------
-- Table structure for tech_group
-- ----------------------------
DROP TABLE IF EXISTS `tech_group`;
CREATE TABLE `tech_group`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `unique_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sender_id concat target_id for query(e.g.: 23_54)',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sender username',
  `group_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sender avatar',
  `type` bigint(20) NOT NULL COMMENT 'receiver uid',
  `level` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'content_type: 1.text, 10.sticker, 20.image, 21.audio, 22.video, 23.file, 30.audio_call, 31.video_call, 40.location,40.contact_card, 50.url_link, 100.transfer, 101.red_envelope',
  `owner_uid` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'message content',
  `create_uid` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'json str for attached data e.g.: file_url, file_path, sticker_id, uid, link_url',
  `state` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'state: 1.normal 2.recall 3.hide 9.abnormal 99.deleted',
  `send_state` tinyint(4) NOT NULL COMMENT 'send_state: 1.not_send, 2.sending, 3.send done, 4.send fail',
  `create_time` datetime(0) NOT NULL COMMENT 'create time',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'update time（update automatically）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `session_id`(`unique_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'group table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tech_im
-- ----------------------------
DROP TABLE IF EXISTS `tech_im`;
CREATE TABLE `tech_im`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `session_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sender_id concat target_id for query(e.g.: 23_54)',
  `sender_id` bigint(20) NOT NULL COMMENT 'sender uid',
  `sender_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sender username',
  `sender_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sender avatar',
  `target_id` bigint(20) NOT NULL COMMENT 'receiver uid',
  `content_type` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'content_type: 1.text, 10.sticker, 20.image, 21.audio, 22.video, 23.file, 30.audio_call, 31.video_call, 40.location,40.contact_card, 50.url_link, 100.transfer, 101.red_envelope',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'message content',
  `attach_json` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'json str for attached data e.g.: file_url, file_path, sticker_id, uid, link_url',
  `state` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'state: 1.normal 2.recall 3.hide 9.abnormal 99.deleted',
  `send_state` tinyint(4) NOT NULL COMMENT 'send_state: 1.not_send, 2.sending, 3.send done, 4.send fail',
  `create_time` datetime(0) NOT NULL COMMENT 'create time',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'update time（update automatically）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `session_id`(`session_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'user table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tech_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tech_login_log`;
CREATE TABLE `tech_login_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `login_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tech_user
-- ----------------------------
DROP TABLE IF EXISTS `tech_user`;
CREATE TABLE `tech_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `phone` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `state` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '状态：1.正常 2.停用 3.异常 99.删除',
  `type` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '类型：1.普通用户 2.测试用户',
  `rank` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '等级：1.普通 2.VIP1 3.VIP2',
  `sex` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '性别：1.未知 2.男 3.女 4.保密',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `province` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `qr_code` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人二维码',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人主页',
  `introduction` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone`(`phone`) USING BTREE,
  INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
