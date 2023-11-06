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

 Date: 06/11/2023 16:26:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rctable
-- ----------------------------
DROP TABLE IF EXISTS `rctable`;
CREATE TABLE `rctable` (
  `id` varchar(255) DEFAULT NULL,
  `rcdate` varchar(255) DEFAULT NULL COMMENT '入仓如期',
  `pnumber` varchar(255) DEFAULT NULL COMMENT '产品编号 自定义 同一列别不得重复',
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `rccount` varchar(255) DEFAULT NULL COMMENT '入仓数量',
  `costprice` varchar(255) DEFAULT NULL COMMENT '成本单价',
  `costcount` varchar(255) DEFAULT NULL COMMENT '成本总价',
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  `sshg` varchar(255) DEFAULT NULL COMMENT '所属货柜',
  `gys` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '供应商信息',
  `pname` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `qcs` varchar(255) DEFAULT NULL COMMENT '期初数',
  `dbfs` varchar(255) DEFAULT NULL COMMENT '打包方式',
  `rcfs` varchar(255) DEFAULT NULL COMMENT '入库方式'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
