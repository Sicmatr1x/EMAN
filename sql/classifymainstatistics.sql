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

 Date: 11/03/2018 19:24:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classifymainstatistics
-- ----------------------------
DROP TABLE IF EXISTS `classifymainstatistics`;
CREATE TABLE `classifymainstatistics` (
  `classifyMain` varchar(64) NOT NULL,
  `reviewCount5` int(8) DEFAULT NULL,
  `reviewCount4` int(8) DEFAULT NULL,
  `reviewCount3` int(8) DEFAULT NULL,
  `reviewCount2` int(8) DEFAULT NULL,
  `reviewCount1` int(8) DEFAULT NULL,
  `varianceRatingValue` double(32,16) DEFAULT NULL,
  `avgRatingValue` double(32,16) DEFAULT NULL,
  PRIMARY KEY (`classifyMain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifymainstatistics
-- ----------------------------
BEGIN;
INSERT INTO `classifymainstatistics` VALUES ('avg', 19637, 8381, 3724, 780, 660, 1.0011500000000000, 4.2840500000000000);
INSERT INTO `classifymainstatistics` VALUES ('人文社科', 24511, 10595, 4919, 1150, 1246, 0.9644800000000000, 4.3195100000000000);
INSERT INTO `classifymainstatistics` VALUES ('小说', 128744, 46243, 16833, 3173, 2662, 0.6782700000000000, 4.4936800000000000);
INSERT INTO `classifymainstatistics` VALUES ('少儿', 2939, 772, 252, 52, 62, 0.6250600000000000, 4.5879300000000000);
INSERT INTO `classifymainstatistics` VALUES ('成功励志', 3684, 1690, 1057, 343, 344, 1.2776300000000000, 4.1277000000000000);
INSERT INTO `classifymainstatistics` VALUES ('教育考试', 233, 98, 57, 5, 17, 1.0531600000000000, 4.2804900000000000);
INSERT INTO `classifymainstatistics` VALUES ('文学', 31583, 15011, 6535, 1364, 1204, 0.8640900000000000, 4.3358900000000000);
INSERT INTO `classifymainstatistics` VALUES ('杂志', 42985, 24845, 13908, 2911, 1671, 0.9235600000000000, 4.2113300000000000);
INSERT INTO `classifymainstatistics` VALUES ('漫画绘本', 1159, 456, 308, 83, 114, 1.3069900000000000, 4.1617900000000000);
INSERT INTO `classifymainstatistics` VALUES ('生活', 5333, 2320, 1281, 331, 351, 1.0950600000000000, 4.2430300000000000);
INSERT INTO `classifymainstatistics` VALUES ('科技科普', 3417, 1376, 543, 102, 129, 0.8251400000000000, 4.4101000000000000);
INSERT INTO `classifymainstatistics` VALUES ('经济管理', 8404, 4288, 2095, 479, 563, 1.0509700000000000, 4.2313500000000000);
INSERT INTO `classifymainstatistics` VALUES ('艺术设计', 722, 467, 233, 44, 78, 1.1762400000000000, 4.1081600000000000);
INSERT INTO `classifymainstatistics` VALUES ('计算机与互联网', 1568, 804, 394, 105, 139, 1.1743400000000000, 4.1817300000000000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
