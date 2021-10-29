/*
 Navicat Premium Data Transfer

 Source Server         : hosmos
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : mall_applet

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 26/08/2021 23:34:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `contentType` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件类型/格式',
  `size` bigint(20) NOT NULL COMMENT '文件大小',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物理路径',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件缓存地址url',
  `type` int(4) NOT NULL COMMENT '类型(1: 图片, 2: 非图片)',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'appId',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_carousel
-- ----------------------------
DROP TABLE IF EXISTS `mall_carousel`;
CREATE TABLE `mall_carousel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `carouselId` bigint(20) NOT NULL COMMENT '轮播图Id',
  `carouselUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图本地图片url',
  `redirectUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图跳转地址',
  `goodId` bigint(20) NULL DEFAULT NULL COMMENT '商品Id',
  `usePlaceId` tinyint(4) NOT NULL COMMENT '轮播图使用地方id（0-首页，1-商品详情页）',
  `carouselRank` int(11) NOT NULL COMMENT '轮播图排序(越小越靠前)',
  `isDeleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0-否，1-是）',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id_parentId`(`isDeleted`, `usePlaceId`, `goodId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_dict
-- ----------------------------
DROP TABLE IF EXISTS `mall_dict`;
CREATE TABLE `mall_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典意义',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值意义',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_base
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_base`;
CREATE TABLE `mall_good_base`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `categoryId` bigint(20) NOT NULL COMMENT '父类Id/分类',
  `goodName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名',
  `goodPrice` decimal(10, 2) NOT NULL COMMENT '商品原价',
  `goodUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品详情跳转Url',
  `goodCoverImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品封面图片',
  `isSpecialPrice` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否特价（0-否，1-是）',
  `SpecialPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '特价优惠金额',
  `isDiscount` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否满折（0-否，1-是）',
  `paymentNo` int(11) NOT NULL DEFAULT 0 COMMENT '购买人数',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `markNo` int(11) NOT NULL DEFAULT 0 COMMENT '收藏人数',
  `commentNo` int(11) NOT NULL DEFAULT 0 COMMENT '评论数',
  `sellStatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态（0-审核，1-上架，2-下架）',
  `sellTime` datetime(0) NOT NULL COMMENT '售卖时间',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_discount`(`sellStatus`, `isSpecialPrice`) USING BTREE,
  INDEX `idx_no_payment`(`sellStatus`, `paymentNo`) USING BTREE,
  INDEX `idx_time_create`(`sellStatus`, `sellTime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_deliver_way
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_deliver_way`;
CREATE TABLE `mall_good_deliver_way`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `deliverWay` bigint(20) NOT NULL DEFAULT 0 COMMENT '配送方式',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_detail
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_detail`;
CREATE TABLE `mall_good_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `goodDescribe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品介绍',
  `goodSize` double NOT NULL COMMENT '规格',
  `companyId` bigint(20) NOT NULL COMMENT '公司Id',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_discount
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_discount`;
CREATE TABLE `mall_good_discount`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `discountOff` double(255, 0) NOT NULL COMMENT '打几折',
  `fullPrice` decimal(10, 2) NOT NULL COMMENT '满多少钱',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_info
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_info`;
CREATE TABLE `mall_good_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `infoType` tinyint(4) NOT NULL COMMENT '信息类型(0-文本，1-图片)',
  `info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息内容',
  `infoRank` int(11) NOT NULL COMMENT '信息排序',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_tag
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_tag`;
CREATE TABLE `mall_good_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `tagId` bigint(20) NOT NULL COMMENT '标签Id',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_tag_list
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_tag_list`;
CREATE TABLE `mall_good_tag_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `tagId` bigint(20) NOT NULL COMMENT '标签Id',
  `tagName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for management_company
-- ----------------------------
DROP TABLE IF EXISTS `management_company`;
CREATE TABLE `management_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) NOT NULL,
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `createUserId` bigint(20) NOT NULL,
  `gmtTime` datetime(0) NULL DEFAULT NULL,
  `gmtUserId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for management_deliver_way
-- ----------------------------
DROP TABLE IF EXISTS `management_deliver_way`;
CREATE TABLE `management_deliver_way`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deliverWay` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` int(11) NOT NULL COMMENT '创建用户Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` int(11) NULL DEFAULT NULL COMMENT '修改用户Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_deliver_way
-- ----------------------------
INSERT INTO `management_deliver_way` VALUES (1, '自提', '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_deliver_way` VALUES (2, '送货上门', '2021-01-01 00:00:00', 1, NULL, NULL);

-- ----------------------------
-- Table structure for management_dict
-- ----------------------------
DROP TABLE IF EXISTS `management_dict`;
CREATE TABLE `management_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值意义',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_dict
-- ----------------------------
INSERT INTO `management_dict` VALUES (1, 'userStatus', '0', '審覈', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (2, 'userStatus', '1', '正常', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (3, 'userStatus', '2', '鎖定', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (4, 'appletType', '0', '商品展示', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (5, 'appletType', '1', '商城', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (6, 'appletStatus', '0', '審覈', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (7, 'appletStatus', '1', '正常', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (8, 'appletStatus', '2', '鎖定', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (9, 'sellStatus', '0', '審覈', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (10, 'sellStatus', '1', '上架', '2021-01-01 00:00:00', NULL);
INSERT INTO `management_dict` VALUES (11, 'sellStatus', '2', '下架', '2021-01-01 00:00:00', NULL);

-- ----------------------------
-- Table structure for management_logs
-- ----------------------------
DROP TABLE IF EXISTS `management_logs`;
CREATE TABLE `management_logs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名',
  `flag` tinyint(4) NOT NULL DEFAULT 1 COMMENT '成功/失败',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for management_permission
-- ----------------------------
DROP TABLE IF EXISTS `management_permission`;
CREATE TABLE `management_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentId` int(11) NOT NULL COMMENT '父类Id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名',
  `css` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标编号',
  `href` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `type` int(4) NOT NULL COMMENT '类型',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限',
  `sort` int(11) NOT NULL COMMENT '队列顺序',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` int(11) NOT NULL COMMENT '创建用户Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` int(11) NULL DEFAULT NULL COMMENT '修改用户Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_permission
-- ----------------------------
INSERT INTO `management_permission` VALUES (1, 0, '用戶管理', 'fa-users', 'pages/user/userList.html', 1, '', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (2, 1, '用戶查詢', 'fa-user', 'pages/user/userList.html', 1, '', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (3, 2, '查詢', '', '', 2, 'management:user:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (4, 2, '新增', '', '', 2, 'management:user:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (5, 2, '删除', '', '', 2, 'management:user:del', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (6, 1, '修改密碼', 'fa-pencil-square-o', 'pages/user/changePassword.html', 1, 'management:user:password', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (7, 0, '系統設置', 'fa-gears', '', 1, '', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (8, 7, '菜單', 'fa-cog', 'pages/menu/menuList.html', 1, '', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (9, 8, '查詢', '', '', 2, 'management:menu:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (10, 8, '新增', '', '', 2, 'management:menu:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (11, 8, '刪除', '', '', 2, 'management:menu:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (12, 7, '角色', 'fa-user-secret', 'pages/role/roleList.html', 1, '', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (13, 12, '查詢', '', '', 2, 'management:role:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (14, 12, '新增', '', '', 2, 'management:role:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (15, 12, '刪除', '', '', 2, 'management:role:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (16, 0, '文件管理', 'fa-folder-open', 'pages/file/fileList.html', 1, '', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (17, 16, '查詢', '', '', 2, 'management:file:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (18, 16, '刪除', '', '', 2, 'management:file:del', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (19, 0, '數據源監控', 'fa-eye', 'druid/index.html', 1, '', 4, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (20, 0, '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', 1, '', 5, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (21, 0, '代碼生成', 'fa-wrench', 'pages/generate/editGenerate.html', 1, 'management:generate:edit', 6, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (22, 0, '代碼生成', 'fa-wrench', 'pages/generate/editGenerate.html', 1, 'management:generate:edit', 6, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (23, 0, '日誌查询', 'fa-reorder', 'pages/log/logList.html', 1, 'management:log:query', 8, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (24, 0, '定時任務管理', 'fa-tasks', 'pages/job/jobList.html', 1, '', 10, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (25, 24, '查詢', '', '', 2, 'management:job:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (26, 24, '新增', '', '', 2, 'management:job:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (27, 24, '刪除', '', '', 2, 'management:job:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (28, 0, 'excel導出', 'fa-arrow-circle-down', 'pages/excel/sql.html', 1, '', 11, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (29, 28, '導出', '', '', 2, 'management:excel:down', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (30, 28, '頁面顯示數據', '', '', 2, 'management:excel:show:datas', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (31, 0, '字典管理', 'fa-reddit', 'pages/dict/dictList.html', 1, '', 12, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (32, 31, '查詢', '', '', 2, 'management:dict:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (33, 31, '新增', '', '', 2, 'management:dict:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (34, 31, '刪除', '', '', 2, 'management:dict:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (35, 0, '微信小程序權限', 'fa-weixin', '', 1, '', 13, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (36, 35, '小程序配置表', 'fa-weixin', 'pages/appletSetting/applet/appletList.html', 1, '', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (37, 36, '查询', '', '', 2, 'management:applet:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (38, 36, '新增', '', '', 2, 'management:applet:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (39, 36, '删除', '', '', 2, 'management:applet:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (40, 0, '商品展示', 'fa-list', '', 1, '', 14, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (41, 40, '商品展示-分類管理', 'fa-th', 'pages/productsShown/category/categoryList.html', 1, '', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (42, 41, '查詢', '', '', 2, 'productsShown:category:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (43, 41, '新增', '', '', 2, 'productsShown:category:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (44, 41, '刪除', '', '', 2, 'productsShown:category:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (45, 40, '商品展示-商品管理', 'fa-cube', 'pages/productsShown/goodBase/goodList.html', 1, '', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (46, 45, '查詢', '', '', 2, 'productsShown:good:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (47, 45, '新增', '', '', 2, 'productsShown:good:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (48, 45, '刪除', '', '', 2, 'productsShown:good:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (49, 45, '封面上傳', 'fa-cloud-upload', '', 2, 'productsShown:good:add', 4, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (50, 35, '公司配置表', 'fa-building', 'pages/appletSetting/company/companyList.html', 1, '', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (51, 50, '查询', '', '', 2, 'management:company:query', 1, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (52, 50, '新增', '', '', 2, 'management:company:add', 2, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (53, 50, '删除', '', '', 2, 'management:company:del', 3, '2021-01-01 00:00:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (54, 40, '商品展示-商品詳細信息圖片上傳', 'fa-picture-o', 'pages/productsShown/goodInfo/goodInfoList.html', 1, '', 3, '2021-08-30 15:01:17', 1, '2021-09-28 01:19:06', 1);
INSERT INTO `management_permission` VALUES (55, 54, '查詢', '', '', 2, 'productsShown:goodInfo:query', 1, '2021-08-30 15:06:55', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (56, 54, '上傳', '', '', 2, 'productsShown:goodInfo:add', 2, '2021-08-30 15:07:52', 1, '2021-08-30 15:08:54', 1);
INSERT INTO `management_permission` VALUES (57, 54, '刪除', '', '', 2, 'productsShown:goodInfo:del', 3, '2021-08-30 15:08:31', 1, NULL, NULL);

-- ----------------------------
-- Table structure for management_role
-- ----------------------------
DROP TABLE IF EXISTS `management_role`;
CREATE TABLE `management_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` int(11) NOT NULL COMMENT '创建用户Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` int(11) NULL DEFAULT NULL COMMENT '修改用户Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_role
-- ----------------------------
INSERT INTO `management_role` VALUES (1, 'Programmer', '開發員', '2021-01-01 00:00:00', 1, '2021-09-24 15:40:21', 1);

-- ----------------------------
-- Table structure for management_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `management_role_permission`;
CREATE TABLE `management_role_permission`  (
  `roleId` int(11) NOT NULL COMMENT '角色Id',
  `permissionId` int(11) NOT NULL COMMENT '权限Id',
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_role_permission
-- ----------------------------
INSERT INTO `management_role_permission` VALUES (1, 1);
INSERT INTO `management_role_permission` VALUES (1, 2);
INSERT INTO `management_role_permission` VALUES (1, 3);
INSERT INTO `management_role_permission` VALUES (1, 4);
INSERT INTO `management_role_permission` VALUES (1, 5);
INSERT INTO `management_role_permission` VALUES (1, 6);
INSERT INTO `management_role_permission` VALUES (1, 7);
INSERT INTO `management_role_permission` VALUES (1, 8);
INSERT INTO `management_role_permission` VALUES (1, 9);
INSERT INTO `management_role_permission` VALUES (1, 10);
INSERT INTO `management_role_permission` VALUES (1, 11);
INSERT INTO `management_role_permission` VALUES (1, 12);
INSERT INTO `management_role_permission` VALUES (1, 13);
INSERT INTO `management_role_permission` VALUES (1, 14);
INSERT INTO `management_role_permission` VALUES (1, 15);
INSERT INTO `management_role_permission` VALUES (1, 35);
INSERT INTO `management_role_permission` VALUES (1, 36);
INSERT INTO `management_role_permission` VALUES (1, 37);
INSERT INTO `management_role_permission` VALUES (1, 38);
INSERT INTO `management_role_permission` VALUES (1, 39);
INSERT INTO `management_role_permission` VALUES (1, 40);
INSERT INTO `management_role_permission` VALUES (1, 41);
INSERT INTO `management_role_permission` VALUES (1, 42);
INSERT INTO `management_role_permission` VALUES (1, 43);
INSERT INTO `management_role_permission` VALUES (1, 44);
INSERT INTO `management_role_permission` VALUES (1, 45);
INSERT INTO `management_role_permission` VALUES (1, 46);
INSERT INTO `management_role_permission` VALUES (1, 47);
INSERT INTO `management_role_permission` VALUES (1, 48);
INSERT INTO `management_role_permission` VALUES (1, 49);
INSERT INTO `management_role_permission` VALUES (1, 50);
INSERT INTO `management_role_permission` VALUES (1, 51);
INSERT INTO `management_role_permission` VALUES (1, 52);
INSERT INTO `management_role_permission` VALUES (1, 53);
INSERT INTO `management_role_permission` VALUES (1, 54);
INSERT INTO `management_role_permission` VALUES (1, 55);
INSERT INTO `management_role_permission` VALUES (1, 56);
INSERT INTO `management_role_permission` VALUES (1, 57);

-- ----------------------------
-- Table structure for management_user
-- ----------------------------
DROP TABLE IF EXISTS `management_user`;
CREATE TABLE `management_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户登录名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码签名密钥',
  `nickName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `headImgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像Url',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户状态',
  `lastLoginTime` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` int(11) NOT NULL COMMENT '创建用户Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` int(11) NULL DEFAULT NULL COMMENT '修改用户Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_user
-- ----------------------------
INSERT INTO `management_user` VALUES (1, 'Hosmos', 'd21b17bf5e282557c88e972bc933ca1c', 'd28bdac96883bb09c41034617c9bf0b6', 'Hosmos', '', '63147116', 1, '2021-01-01 00:00:00', '2021-01-01 00:00:00', 1, NULL, NULL);

-- ----------------------------
-- Table structure for management_user_appid
-- ----------------------------
DROP TABLE IF EXISTS `management_user_appid`;
CREATE TABLE `management_user_appid`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `appletDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小程序描述',
  `companyId` bigint(20) NULL DEFAULT NULL COMMENT '小程序归属公司Id',
  `appletType` tinyint(4) NOT NULL COMMENT '小程序类型（0-商品展示，1-商城）',
  `status` tinyint(4) NOT NULL COMMENT '小程序状态（0-审核，1-正常，2-锁定）',
  `createUserId` bigint(20) NOT NULL COMMENT '创建用户id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改用户id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for management_user_role
-- ----------------------------
DROP TABLE IF EXISTS `management_user_role`;
CREATE TABLE `management_user_role`  (
  `userId` int(11) NOT NULL COMMENT '用户Id',
  `roleId` int(11) NOT NULL COMMENT '角色Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` int(11) NOT NULL COMMENT '创建用户Id',
  PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_user_role
-- ----------------------------
INSERT INTO `management_user_role` VALUES (1, 1, '2021-01-01 00:00:00', 1);

-- ----------------------------
-- Table structure for products_shown_carousel
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_carousel`;
CREATE TABLE `products_shown_carousel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `carouselId` bigint(20) NOT NULL COMMENT '轮播图Id',
  `carouselUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图本地图片url',
  `redirectUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图跳转地址',
  `goodId` bigint(20) NULL DEFAULT NULL COMMENT '商品Id',
  `usePlaceId` tinyint(4) NOT NULL COMMENT '轮播图使用地方id（0-首页，1-商品详情页）',
  `carouselRank` int(11) NOT NULL COMMENT '轮播图排序(越小越靠前)',
  `isDeleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0-否，1-是）',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id_parentId`(`isDeleted`, `usePlaceId`, `goodId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_category
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_category`;
CREATE TABLE `products_shown_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app id',
  `categoryId` bigint(20) NOT NULL COMMENT '分类id',
  `categoryImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类图片',
  `categoryName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_dict
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_dict`;
CREATE TABLE `products_shown_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典意义',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值意义',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_base
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_base`;
CREATE TABLE `products_shown_good_base`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `categoryId` bigint(20) NOT NULL COMMENT '父类Id/分类',
  `goodName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名',
  `goodMaxPrice` decimal(10, 2) NOT NULL COMMENT '商品最高原价',
  `goodMinPrice` decimal(10, 2) NOT NULL COMMENT '商品最低原价',
  `goodUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品详情跳转Url',
  `goodCoverImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品封面图片',
  `isSpecialPrice` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否特价（0-否，1-是）',
  `specialPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '特价优惠金额',
  `isDiscount` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否满折（0-否，1-是）',
  `paymentNo` int(11) NOT NULL DEFAULT 0 COMMENT '购买人数',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `markNo` int(11) NOT NULL DEFAULT 0 COMMENT '收藏人数',
  `commentNo` int(11) NOT NULL DEFAULT 0 COMMENT '评论数',
  `sellStatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态（0-审核，1-上架，2-下架）',
  `sellTime` datetime(0) NOT NULL COMMENT '售卖时间',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_discount`(`sellStatus`, `isSpecialPrice`) USING BTREE,
  INDEX `idx_no_payment`(`sellStatus`, `paymentNo`) USING BTREE,
  INDEX `idx_time_create`(`sellStatus`, `sellTime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_deliver_way
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_deliver_way`;
CREATE TABLE `products_shown_good_deliver_way`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `deliverWay` bigint(20) NOT NULL DEFAULT 0 COMMENT '配送方式',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_detail
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_detail`;
CREATE TABLE `products_shown_good_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `goodDescribe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品介绍',
  `goodSize` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规格',
  `companyId` bigint(20) NULL DEFAULT NULL COMMENT '公司Id',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_discount
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_discount`;
CREATE TABLE `products_shown_good_discount`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `discountOff` double(255, 0) NOT NULL COMMENT '打几折',
  `fullPrice` decimal(10, 2) NOT NULL COMMENT '满多少钱',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_info
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_info`;
CREATE TABLE `products_shown_good_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pictureId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件id',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `infoType` tinyint(4) NOT NULL COMMENT '文件类型(0: 轮播图, 1: 商品封面, 2: 商品详情页)',
  `contentType` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件类型/格式',
  `size` bigint(20) NOT NULL COMMENT '文件大小',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物理路径',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件缓存地址url',
  `type` int NOT NULL COMMENT '类型(1: 图片, 2: 非图片)',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
