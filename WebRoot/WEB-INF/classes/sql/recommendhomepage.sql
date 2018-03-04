/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ebookdb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-04 18:58:40
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `recommendhomepage` VALUES ('1', '958945');
INSERT INTO `recommendhomepage` VALUES ('2', '29864390');
INSERT INTO `recommendhomepage` VALUES ('3', '5968669');
