/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : mall_applet

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 27/06/2021 20:03:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_carousel
-- ----------------------------
DROP TABLE IF EXISTS `mall_carousel`;
CREATE TABLE `mall_carousel`  (
  `tid` int(0) NOT NULL COMMENT '主键',
  `carouselUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图本地图片url',
  `redirectUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图跳转地址',
  `usePlaceId` int(0) NOT NULL COMMENT '轮播图使用地方id',
  `carouselRank` int(0) NOT NULL COMMENT '轮播图排序(越小越靠前)',
  `isDeleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除（0-否，1-是）',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` int(0) NOT NULL COMMENT '创建者',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` int(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
