/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : askme

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-04-19 16:33:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `answer` varchar(2000) DEFAULT NULL COMMENT '回答内容',
  `like` int(11) DEFAULT NULL COMMENT '点赞数',
  `is_accept` bit(1) DEFAULT NULL COMMENT '是否采纳',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `title` varchar(255) DEFAULT NULL COMMENT '问题标题',
  `content` varchar(2000) DEFAULT NULL COMMENT '问题内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `sequence` int(11) DEFAULT NULL COMMENT '排序号',
  `is_answered` bit(1) DEFAULT NULL COMMENT '是否已回答',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tel` varchar(20) DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` bit(1) DEFAULT NULL COMMENT '性别',
  `birth_day` datetime DEFAULT NULL COMMENT '生日',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq号',
  `head_img_attach_id` int(11) DEFAULT NULL COMMENT '头像附件id',
  `age_group` varchar(20) DEFAULT NULL COMMENT '年龄层',
  `profession` varchar(20) DEFAULT NULL COMMENT '职业id',
  `company` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `personalized_signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `marital_status` bit(1) DEFAULT NULL COMMENT '婚姻状况',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `area` varchar(20) DEFAULT NULL COMMENT '区',
  `street` varchar(20) DEFAULT NULL COMMENT '街道',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `openid` varchar(100) DEFAULT NULL COMMENT '微信open_id',
  `union_id` varchar(100) DEFAULT NULL COMMENT '微信union_id',
  `session_key` varchar(100) DEFAULT NULL COMMENT 'session_key',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
