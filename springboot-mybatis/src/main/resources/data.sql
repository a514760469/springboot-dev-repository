/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : mbg-dev

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-08-27 13:49:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cls
-- ----------------------------
DROP TABLE IF EXISTS `cls`;
CREATE TABLE `cls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `total` int(100) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(2) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_PASSWORD` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_EMAIL` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
