/*
 Navicat Premium Data Transfer

 Source Server         : local data
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 06/11/2023 16:25:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for container
-- ----------------------------
DROP TABLE IF EXISTS `container`;
CREATE TABLE `container` (
  `id` varchar(255) DEFAULT NULL,
  `gysfs` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '运输方式',
  `gnumber` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '柜号',
  `gdbfs` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '打包方式',
  `gsize` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '柜子尺寸',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电话',
  `bankname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '付款银行',
  `cgdate` varchar(255) DEFAULT NULL COMMENT '出港时间',
  `status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '状态 已到港 未到港口',
  `dgdate` varchar(255) DEFAULT NULL COMMENT '到港时间',
  `banknumber` varchar(255) DEFAULT NULL COMMENT '银行账号',
  `content` varchar(1500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '货物内容\n',
  `type` varchar(255) DEFAULT NULL COMMENT '货物类别',
  `count` varchar(255) DEFAULT NULL COMMENT '数量',
  `price` varchar(255) DEFAULT NULL COMMENT '货值',
  `dls` varchar(255) DEFAULT NULL COMMENT '代理商是否支付 已支付 未支付'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
