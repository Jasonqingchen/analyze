/*
 Navicat Premium Data Transfer

 Source Server         : 文静的滑降绳
 Source Server Type    : MySQL
 Source Server Version : 50562 (5.5.62-log)
 Source Host           : 1.12.238.58:3306
 Source Schema         : myadmin

 Target Server Type    : MySQL
 Target Server Version : 50562 (5.5.62-log)
 File Encoding         : 65001

 Date: 10/05/2023 15:34:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sk
-- ----------------------------
DROP TABLE IF EXISTS `sk`;
CREATE TABLE `sk` (
  `id` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cpname` varchar(255) DEFAULT NULL,
  `dktime` time DEFAULT NULL,
  `usertime` time DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `jcj` time DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `zb` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sk
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
