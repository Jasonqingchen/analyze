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

 Date: 06/11/2023 19:00:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cctable
-- ----------------------------
DROP TABLE IF EXISTS `cctable`;
CREATE TABLE `cctable` (
  `id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '规格',
  `customername` varchar(255) DEFAULT NULL COMMENT 'customer name',
  `customerphone` varchar(255) DEFAULT NULL COMMENT 'customer phone',
  `color` varchar(255) DEFAULT NULL COMMENT 'color',
  `ccdate` varchar(255) DEFAULT NULL COMMENT '出库时间',
  `cccount` varchar(255) DEFAULT NULL COMMENT '出库数量',
  `ccprice` varchar(255) DEFAULT NULL COMMENT '出库单价',
  `ccsumprice` varchar(255) DEFAULT NULL COMMENT '出库总价',
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  `pname` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `pnumber` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `customeraddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户地址',
  `orderid` varchar(255) DEFAULT NULL COMMENT '订单编号 自动生成',
  `ccfs` varchar(255) DEFAULT NULL COMMENT '出库方式'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
