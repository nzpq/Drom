/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : dorm

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 27/10/2020 09:39:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_dormbuild
-- ----------------------------
DROP TABLE IF EXISTS `tb_dormbuild`;
CREATE TABLE `tb_dormbuild`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disabled` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_dormbuild
-- ----------------------------
INSERT INTO `tb_dormbuild` VALUES (1, '1号楼', '我是一号楼', 0);
INSERT INTO `tb_dormbuild` VALUES (2, '2号楼', '我是二号楼', 0);
INSERT INTO `tb_dormbuild` VALUES (3, '3号楼', '我是三号楼', 0);
INSERT INTO `tb_dormbuild` VALUES (4, '4号楼', '我是。。。', 0);
INSERT INTO `tb_dormbuild` VALUES (5, '5号楼', '我是五号楼', 0);
INSERT INTO `tb_dormbuild` VALUES (6, '6号楼', '六六六', 1);
INSERT INTO `tb_dormbuild` VALUES (7, 'test', '测试', 1);

-- ----------------------------
-- Table structure for tb_manage_dormbuild
-- ----------------------------
DROP TABLE IF EXISTS `tb_manage_dormbuild`;
CREATE TABLE `tb_manage_dormbuild`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `dormBuild_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `dormBuild_id`(`dormBuild_id`) USING BTREE,
  CONSTRAINT `tb_manage_dormbuild_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_manage_dormbuild_ibfk_2` FOREIGN KEY (`dormBuild_id`) REFERENCES `tb_dormbuild` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_manage_dormbuild
-- ----------------------------
INSERT INTO `tb_manage_dormbuild` VALUES (4, 12, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (5, 12, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (6, 20, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (7, 21, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (8, 21, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (18, 22, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (19, 22, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (22, 11, 1);
INSERT INTO `tb_manage_dormbuild` VALUES (23, 11, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (31, 45, 2);
INSERT INTO `tb_manage_dormbuild` VALUES (32, 45, 3);
INSERT INTO `tb_manage_dormbuild` VALUES (38, 54, 6);

-- ----------------------------
-- Table structure for tb_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `date` date NULL DEFAULT NULL COMMENT '缺勤时间',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `disabled` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `tb_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_record
-- ----------------------------
INSERT INTO `tb_record` VALUES (1, 32, '2019-11-01', '晚归', 1);
INSERT INTO `tb_record` VALUES (2, 35, '2019-11-06', '晚归', 0);
INSERT INTO `tb_record` VALUES (3, 33, '2019-11-01', '夜不归宿', 0);
INSERT INTO `tb_record` VALUES (4, 36, '2019-11-05', '夜不归宿', 0);
INSERT INTO `tb_record` VALUES (5, 36, '2020-06-13', '哈哈', 0);
INSERT INTO `tb_record` VALUES (6, 39, '2020-06-13', '哈哈', 0);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passWord` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stu_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `dorm_Code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍编号',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormBuildId` int(11) NULL DEFAULT NULL COMMENT '宿舍楼id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '0 表示超级管理员 1宿舍管理员 2学生',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `disabled` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stu_code`(`stu_code`) USING BTREE,
  INDEX `dormBuildId`(`dormBuildId`) USING BTREE,
  INDEX `create_user_id`(`create_user_id`) USING BTREE,
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`dormBuildId`) REFERENCES `tb_dormbuild` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_user_ibfk_2` FOREIGN KEY (`create_user_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '111', '001', NULL, '男', '13485796641', NULL, 0, 1, 0);
INSERT INTO `tb_user` VALUES (11, '宿管2', '123', '20191027', NULL, '男', '13485796641', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (12, '宿管3', '123', '20191028', NULL, '男', '13485796641', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (20, '宿管4', '123', '20191031', NULL, '男', '13485796641', NULL, 1, 1, 1);
INSERT INTO `tb_user` VALUES (21, '宿管5', '123', '20191032', NULL, '男', '13485796641', NULL, 1, 1, 1);
INSERT INTO `tb_user` VALUES (22, '宿管6', '123', '20191033', NULL, '男', '13485796641', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (32, '学生1', '123', '15170231', '1-201', '男', '13485796641', 1, 2, 1, 0);
INSERT INTO `tb_user` VALUES (33, '学生2', '123', '15170232', '1-201', '男', '13485796641', 1, 2, 1, 0);
INSERT INTO `tb_user` VALUES (34, '学生3', '123', '15170233', '2-101', '男', '13485796641', 2, 2, 1, 0);
INSERT INTO `tb_user` VALUES (35, '学生4', '123', '15170234', '3-201', '男', '13485796641', 3, 2, 1, 0);
INSERT INTO `tb_user` VALUES (36, '学生5', '123', '15170235', '1-101', '男', '13485796641', 1, 2, 1, 0);
INSERT INTO `tb_user` VALUES (37, '学生6', '123', '15170236', '3-201', '男', '13485796641', 3, 2, 1, 0);
INSERT INTO `tb_user` VALUES (38, '学生7', '123', '15170237', '4-201', '男', '13485796641', 4, 2, 1, 0);
INSERT INTO `tb_user` VALUES (39, '学生8', '123', '15170238', '1-101', '男', '13485796641', 1, 2, 1, 0);
INSERT INTO `tb_user` VALUES (45, '宿管7', '123', '66666666', NULL, '男', '13485796641', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (54, '宿管8', '123', '88888888', NULL, '女', '13485796641', NULL, 1, 1, 0);
INSERT INTO `tb_user` VALUES (55, '学生9', '1234', '15170239', '5-501', '女', '13461298874', 5, 2, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
