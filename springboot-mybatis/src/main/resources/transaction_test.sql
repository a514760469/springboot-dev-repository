/*
Navicat MySQL Data Transfer

Source Server         : connlocalhost
Source Server Version : 50132
Source Host           : localhost:3306
Source Database       : mbg-dev

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2020-05-09 00:27:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user1`
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT 'name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user1
-- ----------------------------

-- ----------------------------
-- Table structure for `user2`
-- ----------------------------
DROP TABLE IF EXISTS `user2`;
CREATE TABLE `user2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT 'name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user2
-- ----------------------------
