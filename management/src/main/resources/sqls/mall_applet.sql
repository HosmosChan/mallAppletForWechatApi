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

 Date: 24/08/2021 22:43:43
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  `isSpecialPrice` tinyint(4) NOT NULL COMMENT '是否特价（0-否，1-是）',
  `SpecialPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '特价优惠金额',
  `isDiscount` tinyint(4) NOT NULL COMMENT '是否满折（0-否，1-是）',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_deliver_way
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_deliver_way`;
CREATE TABLE `mall_good_deliver_way`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `deliverWay` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配送方式',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_good_info
-- ----------------------------
DROP TABLE IF EXISTS `mall_good_info`;
CREATE TABLE `mall_good_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `infoType` tinyint(4) NOT NULL COMMENT '信息类型(0-文本，1-图片)',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息内容',
  `infoRank` int(11) NOT NULL COMMENT '信息排序',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_dict
-- ----------------------------
INSERT INTO `management_dict` VALUES (1, 'userStatus', '0', '无效', '2017-11-17 16:26:06', NULL);
INSERT INTO `management_dict` VALUES (2, 'userStatus', '1', '正常', '2017-11-17 16:26:06', NULL);
INSERT INTO `management_dict` VALUES (3, 'userStatus', '2', '锁定', '2018-12-27 11:36:42', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_permission
-- ----------------------------
INSERT INTO `management_permission` VALUES (1, 0, '用户管理', 'fa-users', 'pages/user/userList.html', 1, '', 1, '2021-07-14 17:47:41', 1, '2021-07-16 17:56:58', 1);
INSERT INTO `management_permission` VALUES (2, 1, '用户查询', 'fa-user', 'pages/user/userList.html', 1, '', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (3, 2, '查询', '', '', 2, 'management:user:query', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (4, 2, '新增', '', '', 2, 'management:user:add', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (5, 1, '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', 1, 'management:user:password', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (6, 0, '系统设置', 'fa-gears', '', 1, '', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (7, 6, '菜单', 'fa-cog', 'pages/menu/menuList.html', 1, '', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (8, 7, '查询', '', '', 2, 'management:menu:query', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (9, 7, '新增', '', '', 2, 'management:menu:add', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (10, 7, '删除', '', '', 2, 'management:menu:del', 3, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (11, 6, '角色', 'fa-user-secret', 'pages/role/roleList.html', 1, '', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (12, 11, '查询', '', '', 2, 'management:role:query', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (13, 11, '新增', '', '', 2, 'management:role:add', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (14, 11, '删除', '', '', 2, 'management:role:del', 3, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (15, 0, '文件管理', 'fa-folder-open', 'pages/file/fileList.html', 1, '', 3, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (16, 15, '查询', '', '', 2, 'management:file:query', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (17, 15, '删除', '', '', 2, 'management:file:del', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (18, 0, '数据源监控', 'fa-eye', 'druid/index.html', 1, '', 4, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (19, 0, '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', 1, '', 5, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (20, 0, '代码生成', 'fa-wrench', 'pages/generate/editGenerate.html', 1, 'generate:edit', 6, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (25, 0, '日志查询', 'fa-reorder', 'pages/log/logList.html', 1, 'management:log:query', 8, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (29, 0, '定时任务管理', 'fa-tasks', 'pages/job/jobList.html', 1, '', 10, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (30, 29, '查询', '', '', 2, 'job:query', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (31, 29, '新增', '', '', 2, 'job:add', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (32, 29, '删除', '', '', 2, 'job:del', 3, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (33, 0, 'excel导出', 'fa-arrow-circle-down', 'pages/excel/sql.html', 1, '', 11, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (34, 33, '导出', '', '', 2, 'excel:down', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (35, 33, '页面显示数据', '', '', 2, 'excel:show:datas', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (36, 0, '字典管理', 'fa-reddit', 'pages/dict/dictList.html', 1, '', 12, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (37, 36, '查询', '', '', 2, 'dict:query', 1, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (38, 36, '新增', '', '', 2, 'dict:add', 2, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (39, 36, '删除', '', '', 2, 'dict:del', 3, '2021-07-14 17:47:41', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (40, 0, '商品展示', 'fa-list', '', 1, '', 13, '2021-08-24 20:46:03', 1, '2021-08-24 20:49:54', 1);
INSERT INTO `management_permission` VALUES (41, 40, '商品展示-分类管理', 'fa-th', 'pages/productsShown/categoryList.html', 1, '', 1, '2021-08-24 20:49:33', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (42, 41, '查询', '', '', 2, 'productsShown:category:query', 1, '2021-08-24 20:53:01', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (43, 41, '新增', '', '', 2, 'productsShown:category:add', 2, '2021-08-24 20:57:00', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (44, 41, '删除', '', '', 2, 'productsShown:category:del', 3, '2021-08-24 20:57:42', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (45, 40, '商品展示-商品管理', 'fa-cube', 'pages/productsShown/goodList.html', 1, '', 2, '2021-08-24 21:10:25', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (46, 45, '查询', '', '', 2, 'productsShown:good:query', 1, '2021-08-24 21:11:10', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (47, 45, '新增', '', '', 2, 'productsShown:good:add', 2, '2021-08-24 21:12:13', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (48, 45, '删除', '', '', 2, 'productsShown:good:del', 3, '2021-08-24 21:13:21', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (49, 0, '微信小程序权限', 'fa-weixin', '', 1, '', 14, '2021-08-24 22:24:39', 1, NULL, NULL);
INSERT INTO `management_permission` VALUES (50, 45, '圖片、視頻上傳', 'fa-cloud-upload', '', 2, 'productsShown:good:add', 4, '2021-08-24 22:36:41', 1, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_role
-- ----------------------------
INSERT INTO `management_role` VALUES (1, 'Programmer', '開發員', '2021-07-08 19:02:34', 1, '2021-08-24 22:32:11', 1);
INSERT INTO `management_role` VALUES (2, 'Manager', '管理員', '2021-08-23 21:34:05', 1, '2021-08-24 22:31:46', 1);
INSERT INTO `management_role` VALUES (3, 'WechatProductsShownManager', '小程序商品展示管理員', '2021-08-24 22:30:39', 1, NULL, NULL);

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
INSERT INTO `management_role_permission` VALUES (1, 16);
INSERT INTO `management_role_permission` VALUES (1, 17);
INSERT INTO `management_role_permission` VALUES (1, 18);
INSERT INTO `management_role_permission` VALUES (1, 19);
INSERT INTO `management_role_permission` VALUES (1, 20);
INSERT INTO `management_role_permission` VALUES (1, 25);
INSERT INTO `management_role_permission` VALUES (1, 29);
INSERT INTO `management_role_permission` VALUES (1, 30);
INSERT INTO `management_role_permission` VALUES (1, 31);
INSERT INTO `management_role_permission` VALUES (1, 32);
INSERT INTO `management_role_permission` VALUES (1, 33);
INSERT INTO `management_role_permission` VALUES (1, 34);
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
INSERT INTO `management_role_permission` VALUES (2, 1);
INSERT INTO `management_role_permission` VALUES (2, 2);
INSERT INTO `management_role_permission` VALUES (2, 3);
INSERT INTO `management_role_permission` VALUES (2, 4);
INSERT INTO `management_role_permission` VALUES (2, 5);
INSERT INTO `management_role_permission` VALUES (2, 6);
INSERT INTO `management_role_permission` VALUES (2, 7);
INSERT INTO `management_role_permission` VALUES (2, 8);
INSERT INTO `management_role_permission` VALUES (2, 9);
INSERT INTO `management_role_permission` VALUES (2, 10);
INSERT INTO `management_role_permission` VALUES (2, 11);
INSERT INTO `management_role_permission` VALUES (2, 12);
INSERT INTO `management_role_permission` VALUES (2, 13);
INSERT INTO `management_role_permission` VALUES (2, 14);
INSERT INTO `management_role_permission` VALUES (2, 36);
INSERT INTO `management_role_permission` VALUES (2, 37);
INSERT INTO `management_role_permission` VALUES (2, 38);
INSERT INTO `management_role_permission` VALUES (2, 39);
INSERT INTO `management_role_permission` VALUES (2, 40);
INSERT INTO `management_role_permission` VALUES (2, 41);
INSERT INTO `management_role_permission` VALUES (2, 42);
INSERT INTO `management_role_permission` VALUES (2, 43);
INSERT INTO `management_role_permission` VALUES (2, 44);
INSERT INTO `management_role_permission` VALUES (2, 45);
INSERT INTO `management_role_permission` VALUES (2, 46);
INSERT INTO `management_role_permission` VALUES (2, 47);
INSERT INTO `management_role_permission` VALUES (2, 48);
INSERT INTO `management_role_permission` VALUES (2, 49);
INSERT INTO `management_role_permission` VALUES (3, 40);
INSERT INTO `management_role_permission` VALUES (3, 41);
INSERT INTO `management_role_permission` VALUES (3, 42);
INSERT INTO `management_role_permission` VALUES (3, 43);
INSERT INTO `management_role_permission` VALUES (3, 44);
INSERT INTO `management_role_permission` VALUES (3, 45);
INSERT INTO `management_role_permission` VALUES (3, 46);
INSERT INTO `management_role_permission` VALUES (3, 47);
INSERT INTO `management_role_permission` VALUES (3, 48);
INSERT INTO `management_role_permission` VALUES (5, 1);
INSERT INTO `management_role_permission` VALUES (5, 5);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_user
-- ----------------------------
INSERT INTO `management_user` VALUES (1, 'Hosmos', 'd21b17bf5e282557c88e972bc933ca1c', 'd28bdac96883bb09c41034617c9bf0b6', 'Hosmos', '', '63147116', 1, '2021-08-24 22:06:23', '2018-12-03 15:19:13', 1, '2021-07-16 17:15:09', 1);
INSERT INTO `management_user` VALUES (2, 'test', 'f082acc667b886d792556449dc791d38', '0a1b4d281eb52ea4abc0e0890ddbffd6', 'test', NULL, '', 1, NULL, '2021-07-16 17:01:18', 1, '2021-07-16 21:21:59', 1);
INSERT INTO `management_user` VALUES (3, 'UserTest', 'f58b7403f7cd7776baa68c0661579ceb', '4d679bfead1dcf78becefd7d7014d98f', 'test', NULL, '', 0, NULL, '2021-08-23 17:34:35', 1, '2021-08-23 20:09:19', 1);

-- ----------------------------
-- Table structure for management_user_appid
-- ----------------------------
DROP TABLE IF EXISTS `management_user_appid`;
CREATE TABLE `management_user_appid`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户id',
  `appId` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for management_user_role
-- ----------------------------
DROP TABLE IF EXISTS `management_user_role`;
CREATE TABLE `management_user_role`  (
  `userId` int(11) NOT NULL COMMENT '用户Id',
  `roleId` int(11) NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of management_user_role
-- ----------------------------
INSERT INTO `management_user_role` VALUES (1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_category
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_category`;
CREATE TABLE `products_shown_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app id',
  `categoryId` bigint(20) NOT NULL COMMENT '分类id',
  `categoryName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  `isSpecialPrice` tinyint(4) NOT NULL COMMENT '是否特价（0-否，1-是）',
  `specialPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '特价优惠金额',
  `isDiscount` tinyint(4) NOT NULL COMMENT '是否满折（0-否，1-是）',
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products_shown_good_base
-- ----------------------------
INSERT INTO `products_shown_good_base` VALUES (1, '1', 1, 11, '测试', 100.00, 10.00, '123', NULL, 0, NULL, 0, 0, 0, 0, 0, 0, '2021-08-24 21:46:53', 1, '2021-08-24 21:47:00', NULL, NULL);

-- ----------------------------
-- Table structure for products_shown_good_deliver_way
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_deliver_way`;
CREATE TABLE `products_shown_good_deliver_way`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `deliverWay` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配送方式',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_detail
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_detail`;
CREATE TABLE `products_shown_good_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `goodDescribe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品介绍',
  `goodSize` double NOT NULL COMMENT '规格',
  `companyId` bigint(20) NULL DEFAULT NULL COMMENT '公司Id',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for products_shown_good_info
-- ----------------------------
DROP TABLE IF EXISTS `products_shown_good_info`;
CREATE TABLE `products_shown_good_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appId',
  `goodId` bigint(20) NOT NULL COMMENT '商品Id',
  `infoType` tinyint(4) NOT NULL COMMENT '信息类型(0-文本，1-图片)',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息内容',
  `infoRank` int(11) NOT NULL COMMENT '信息排序',
  `createUserId` bigint(20) NOT NULL COMMENT '创建者Id',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `gmtUserId` bigint(20) NULL DEFAULT NULL COMMENT '修改者Id',
  `gmtTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
