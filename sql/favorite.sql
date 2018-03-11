/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : ebookdb

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 11/03/2018 19:26:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `fid` int(16) NOT NULL AUTO_INCREMENT,
  `uid` varchar(32) NOT NULL,
  `classifyMain` varchar(64) DEFAULT NULL,
  `classifySub` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------
BEGIN;
INSERT INTO `favorite` VALUES (30, '131952373', '计算机与互联网', NULL);
INSERT INTO `favorite` VALUES (31, '131952373', '小说', NULL);
INSERT INTO `favorite` VALUES (32, '131952373', '文学', NULL);
INSERT INTO `favorite` VALUES (33, '131952373', '人文社科', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
