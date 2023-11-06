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

 Date: 06/11/2023 16:26:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jxctablebf
-- ----------------------------
DROP TABLE IF EXISTS `jxctablebf`;
CREATE TABLE `jxctablebf` (
  `id` varchar(255) DEFAULT NULL,
  `pnumber` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `pname` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `type` varchar(255) DEFAULT NULL COMMENT '规格',
  `qcs` varchar(255) DEFAULT NULL COMMENT '期初数',
  `rccount` varchar(255) DEFAULT NULL COMMENT '入仓适量',
  `cccount` varchar(255) DEFAULT NULL COMMENT '出库数量',
  `jccount` varchar(255) DEFAULT NULL COMMENT '结存数量',
  `pdcount` varchar(255) DEFAULT NULL COMMENT '盘点数量',
  `cycount` varchar(255) DEFAULT NULL COMMENT '差异条数',
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  `pdstatus` varchar(255) DEFAULT NULL COMMENT '盘点状态',
  `date` varchar(255) DEFAULT NULL COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
