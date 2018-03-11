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

 Date: 11/03/2018 19:27:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for recommendhomepage
-- ----------------------------
DROP TABLE IF EXISTS `recommendhomepage`;
CREATE TABLE `recommendhomepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recommendhomepage
-- ----------------------------
BEGIN;
INSERT INTO `recommendhomepage` VALUES (1, '958945');
INSERT INTO `recommendhomepage` VALUES (2, '29864390');
INSERT INTO `recommendhomepage` VALUES (3, '5968669');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
