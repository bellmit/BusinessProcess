/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : process

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 23/01/2019 09:29:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log_interactive
-- ----------------------------
DROP TABLE IF EXISTS `log_interactive`;
CREATE TABLE `log_interactive`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `requester` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交互请求发起人',
  `requester_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_time` datetime(0) NOT NULL,
  `request_from` varchar(21) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_parameter` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `response_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求对应的服务接口类',
  `response_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务的方法',
  `response_message` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正常响应返回的信息',
  `exception_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求服务发生异常的信息',
  `interactive_spend` int(11) NOT NULL COMMENT '请求响应耗时',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of log_interactive
-- ----------------------------
INSERT INTO `log_interactive` VALUES (67, '管理员', 'admin', '2019-01-22 15:09:50', '127.0.0.1:9545', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@1afe73bb', NULL, 364);
INSERT INTO `log_interactive` VALUES (68, '管理员', 'admin', '2019-01-22 15:09:55', '127.0.0.1:9545', 'arg0:\"1177dcb0781b49a9a6b794d36df575a3\" | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUser', 'com.mrbeard.process.result.Result@264a0b35', NULL, 72);
INSERT INTO `log_interactive` VALUES (69, '管理员', 'admin', '2019-01-22 15:09:55', '127.0.0.1:9545', '', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@3949685b', NULL, 79);
INSERT INTO `log_interactive` VALUES (70, '管理员', 'admin', '2019-01-22 15:23:09', '127.0.0.1:9545', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@38262527', NULL, 365);
INSERT INTO `log_interactive` VALUES (71, NULL, NULL, '2019-01-22 15:27:56', 'null:null', '', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getBrowerToken', 'com.mrbeard.process.result.Result@75224718', NULL, 102);
INSERT INTO `log_interactive` VALUES (72, NULL, NULL, '2019-01-22 15:27:57', 'null:null', 'arg0:\"e8990c51d7a5427c949c71740875774c\" | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getRandomCode', '', NULL, 189);
INSERT INTO `log_interactive` VALUES (73, NULL, NULL, '2019-01-22 15:28:02', 'null:null', '', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getBrowerToken', 'com.mrbeard.process.result.Result@3abe4cfe', NULL, 61);
INSERT INTO `log_interactive` VALUES (74, NULL, NULL, '2019-01-22 15:28:02', 'null:null', 'arg0:\"2a92639949a5470b93c2a87c595974e3\" | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getRandomCode', '', NULL, 95);
INSERT INTO `log_interactive` VALUES (75, NULL, NULL, '2019-01-22 15:28:11', 'null:null', 'arg0:{\"browerToken\":\"2a92639949a5470b93c2a87c595974e3\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"randomCode\":\"j737\",\"username\":\"admin\"} | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'login', 'com.mrbeard.process.result.Result@49426935', NULL, 432);
INSERT INTO `log_interactive` VALUES (76, '管理员', 'admin', '2019-01-22 15:29:01', '127.0.0.1:3896', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@66a891ce', NULL, 74);
INSERT INTO `log_interactive` VALUES (77, '管理员', 'admin', '2019-01-22 15:44:38', '127.0.0.1:3896', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@d5d703f', NULL, 102);
INSERT INTO `log_interactive` VALUES (78, '管理员', 'admin', '2019-01-22 15:45:08', '127.0.0.1:3896', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@5c638934', NULL, 84);
INSERT INTO `log_interactive` VALUES (79, NULL, NULL, '2019-01-22 15:45:12', 'null:null', '', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getBrowerToken', 'com.mrbeard.process.result.Result@5fc738eb', NULL, 81);
INSERT INTO `log_interactive` VALUES (80, NULL, NULL, '2019-01-22 15:45:12', 'null:null', 'arg0:\"40656269fa254f70b31bacc0af61bd95\" | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getRandomCode', '', NULL, 128);
INSERT INTO `log_interactive` VALUES (81, '管理员', 'admin', '2019-01-22 15:49:14', '127.0.0.1:3896', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@45c4df08', NULL, 70);
INSERT INTO `log_interactive` VALUES (82, '管理员', 'admin', '2019-01-22 15:49:17', '127.0.0.1:3896', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@33d55d23', NULL, 66);
INSERT INTO `log_interactive` VALUES (83, '管理员', 'admin', '2019-01-22 15:49:18', '127.0.0.1:3896', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@5e06db79', NULL, 66);
INSERT INTO `log_interactive` VALUES (84, '管理员', 'admin', '2019-01-22 15:59:48', '127.0.0.1:3896', '', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@6005bb52', NULL, 378);
INSERT INTO `log_interactive` VALUES (85, '管理员', 'admin', '2019-01-22 16:18:36', '127.0.0.1:3896', 'arg0:1 | arg1:10 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@703c91c4', NULL, 369);
INSERT INTO `log_interactive` VALUES (86, '管理员', 'admin', '2019-01-22 16:31:40', '127.0.0.1:3896', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@708ac647', NULL, 64);
INSERT INTO `log_interactive` VALUES (87, '管理员', 'admin', '2019-01-22 16:32:17', '127.0.0.1:3896', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@cf9d18e', NULL, 63);
INSERT INTO `log_interactive` VALUES (88, '管理员', 'admin', '2019-01-22 16:32:26', '127.0.0.1:3896', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@29edf7c1', NULL, 79);
INSERT INTO `log_interactive` VALUES (89, NULL, NULL, '2019-01-22 16:32:33', 'null:null', '', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getBrowerToken', 'com.mrbeard.process.result.Result@549d6bfb', NULL, 62);
INSERT INTO `log_interactive` VALUES (90, NULL, NULL, '2019-01-22 16:32:34', 'null:null', 'arg0:\"5601b382fe5749cd8a4b9788e82c0eff\" | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getRandomCode', '', NULL, 265);
INSERT INTO `log_interactive` VALUES (91, NULL, NULL, '2019-01-22 16:32:45', 'null:null', 'arg0:{\"browerToken\":\"5601b382fe5749cd8a4b9788e82c0eff\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"randomCode\":\"ukrk\",\"username\":\"admin\"} | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'login', 'com.mrbeard.process.result.Result@56ebed0d', NULL, 148);
INSERT INTO `log_interactive` VALUES (92, '管理员', 'admin', '2019-01-22 16:32:48', '127.0.0.1:12974', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@14b96cee', NULL, 62);
INSERT INTO `log_interactive` VALUES (93, '管理员', 'admin', '2019-01-22 16:32:50', '127.0.0.1:12974', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@48dbe8c1', NULL, 91);
INSERT INTO `log_interactive` VALUES (94, '管理员', 'admin', '2019-01-22 16:32:52', '127.0.0.1:12974', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@5f8d3813', NULL, 68);
INSERT INTO `log_interactive` VALUES (95, '管理员', 'admin', '2019-01-22 16:45:45', '127.0.0.1:12974', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@1252c3db', NULL, 67);
INSERT INTO `log_interactive` VALUES (96, '管理员', 'admin', '2019-01-22 16:49:58', '127.0.0.1:12974', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@2424de3a', NULL, 68);
INSERT INTO `log_interactive` VALUES (97, '管理员', 'admin', '2019-01-22 16:50:01', '127.0.0.1:12974', 'arg0:1 | arg1:100 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@2a4b4a24', NULL, 69);
INSERT INTO `log_interactive` VALUES (98, '管理员', 'admin', '2019-01-22 16:50:07', '127.0.0.1:12974', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@6aea7a7f', NULL, 65);
INSERT INTO `log_interactive` VALUES (99, '管理员', 'admin', '2019-01-22 16:52:15', '127.0.0.1:12974', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@62ff06ed', NULL, 75);
INSERT INTO `log_interactive` VALUES (100, '管理员', 'admin', '2019-01-22 16:52:16', '127.0.0.1:12974', 'arg0:1 | arg1:100 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@3231a3e1', NULL, 71);
INSERT INTO `log_interactive` VALUES (101, '管理员', 'admin', '2019-01-22 17:17:24', '127.0.0.1:12974', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@6c021622', NULL, 369);
INSERT INTO `log_interactive` VALUES (102, '管理员', 'admin', '2019-01-22 17:18:20', '127.0.0.1:12974', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@1549f3ea', NULL, 72);
INSERT INTO `log_interactive` VALUES (103, '管理员', 'admin', '2019-01-22 17:20:50', '127.0.0.1:12974', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@2ef6a573', NULL, 67);
INSERT INTO `log_interactive` VALUES (104, '管理员', 'admin', '2019-01-22 17:21:04', '127.0.0.1:12974', 'arg0:1 | arg1:5 | arg2:{\"permissions\":[],\"uname\":\"\"} | ', 'com.mrbeard.process.blocks.config.controller.UserConfigController', 'getUserList', 'com.mrbeard.process.result.Result@2222153', NULL, 82);
INSERT INTO `log_interactive` VALUES (105, NULL, NULL, '2019-01-22 17:21:06', 'null:null', '', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getBrowerToken', 'com.mrbeard.process.result.Result@63bd4633', NULL, 100);
INSERT INTO `log_interactive` VALUES (106, NULL, NULL, '2019-01-22 17:21:06', 'null:null', 'arg0:\"f7f2c79c0c4a48c792e3048c1afe9ac0\" | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'getRandomCode', '', NULL, 192);
INSERT INTO `log_interactive` VALUES (107, NULL, NULL, '2019-01-22 17:21:11', 'null:null', 'arg0:{\"browerToken\":\"f7f2c79c0c4a48c792e3048c1afe9ac0\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"randomCode\":\"r5wt\",\"username\":\"admin\"} | ', 'com.mrbeard.process.blocks.authority.controller.AuthorityController', 'login', 'com.mrbeard.process.result.Result@21dfb3d5', NULL, 156);
INSERT INTO `log_interactive` VALUES (108, '管理员', 'admin', '2019-01-22 17:21:26', '127.0.0.1:6246', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', NULL, 'Message:\r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'pid\' in field list is ambiguous\r\n### The error may exist in com/mrbeard/process/blocks/authority/mapper/RoleMapper.xml\r\n### The error may involve com.mrbeard.process.blocks.authority.mapper.RoleMapper.getPermissionList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select                   pid,pname,ptype,pvalue               from qx_permission p         left join qx_rolepermission rp on rp.pid = p.pid         where 1 = 1\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'pid\' in field list is ambiguous\n; Column \'pid\' in field list is ambiguous; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'pid\' in field list is ambiguous | Cause:null', 117);
INSERT INTO `log_interactive` VALUES (109, '管理员', 'admin', '2019-01-22 17:21:31', '127.0.0.1:6246', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@7ce33c1f', NULL, 84);
INSERT INTO `log_interactive` VALUES (110, '管理员', 'admin', '2019-01-22 17:25:32', '127.0.0.1:6246', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@676c8df0', NULL, 431);
INSERT INTO `log_interactive` VALUES (111, '管理员', 'admin', '2019-01-22 17:34:19', '127.0.0.1:6246', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@b229976', NULL, 408);
INSERT INTO `log_interactive` VALUES (112, '管理员', 'admin', '2019-01-22 17:36:13', '127.0.0.1:6246', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@5a7e63c7', NULL, 400);
INSERT INTO `log_interactive` VALUES (113, '管理员', 'admin', '2019-01-22 17:36:59', '127.0.0.1:6246', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@3ce26dad', NULL, 412);
INSERT INTO `log_interactive` VALUES (114, '管理员', 'admin', '2019-01-22 17:37:48', '127.0.0.1:6246', 'arg0:\"045001730c5c4c60a16b35fdb7aaae4b\" | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', NULL, 'Message:nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'rid\' in \'class java.lang.String\' | Cause:null', 73);
INSERT INTO `log_interactive` VALUES (115, '管理员', 'admin', '2019-01-22 17:41:06', '127.0.0.1:6246', 'arg0:\"045001730c5c4c60a16b35fdb7aaae4b\" | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', NULL, 'Message:nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'roleId\' in \'class java.lang.String\' | Cause:null', 147);
INSERT INTO `log_interactive` VALUES (116, '管理员', 'admin', '2019-01-22 17:44:49', '127.0.0.1:6246', 'arg0:\"045001730c5c4c60a16b35fdb7aaae4b\" | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', NULL, 'Message:nested exception is org.apache.ibatis.binding.BindingException: Parameter \'rid\' not found. Available parameters are [roleId, param1] | Cause:null', 158);
INSERT INTO `log_interactive` VALUES (117, '管理员', 'admin', '2019-01-22 17:48:04', '127.0.0.1:6246', 'arg0:\"045001730c5c4c60a16b35fdb7aaae4b\" | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', NULL, 'Message:nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'roleId\' in \'class java.lang.String\' | Cause:null', 147);
INSERT INTO `log_interactive` VALUES (118, '管理员', 'admin', '2019-01-22 17:49:17', '127.0.0.1:6246', 'arg0:\"045001730c5c4c60a16b35fdb7aaae4b\" | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@31ec3752', NULL, 401);
INSERT INTO `log_interactive` VALUES (119, '管理员', 'admin', '2019-01-22 17:49:31', '127.0.0.1:6246', 'arg0:null | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@5f30b617', NULL, 83);
INSERT INTO `log_interactive` VALUES (120, '管理员', 'admin', '2019-01-22 17:49:45', '127.0.0.1:6246', 'arg0:\"686a54140cfa4cf0b0c2ad51755a3d68\" | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getPermissionList', 'com.mrbeard.process.result.Result@1e016091', NULL, 98);
INSERT INTO `log_interactive` VALUES (121, '管理员', 'admin', '2019-01-22 18:00:25', '127.0.0.1:6246', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@61bf6035', NULL, 107);
INSERT INTO `log_interactive` VALUES (122, '管理员', 'admin', '2019-01-22 18:01:03', '127.0.0.1:6246', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@ed85855', NULL, 70);
INSERT INTO `log_interactive` VALUES (123, '管理员', 'admin', '2019-01-22 18:01:21', '127.0.0.1:6246', 'arg0:1 | arg1:5 | ', 'com.mrbeard.process.blocks.config.controller.RolePermissionConfigController', 'getRoleList', 'com.mrbeard.process.result.Result@b6f7791', NULL, 71);

-- ----------------------------
-- Table structure for qx_permission
-- ----------------------------
DROP TABLE IF EXISTS `qx_permission`;
CREATE TABLE `qx_permission`  (
  `pid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `ptype` int(11) NULL DEFAULT NULL COMMENT '权限类型 1.删除2.修改3.增加4.查看',
  `pvalue` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qx_permission
-- ----------------------------
INSERT INTO `qx_permission` VALUES ('5258c345227d45c99e252b79c32de7b7', '配置用户', 1, 'postUser', '2018-10-31 00:00:00', '2018-10-31 00:00:00');
INSERT INTO `qx_permission` VALUES ('5c05db6be97b4c8281f71ef6ced18ed0', '获取用户列表', 2, 'getUserList', '2018-11-07 00:00:00', '2018-11-07 00:00:00');
INSERT INTO `qx_permission` VALUES ('8021173c8a4e462195b67a8dcdbb074e', '获取权限列表', 5, 'getPermissionList', '2019-01-22 17:19:57', '2019-01-22 17:20:00');
INSERT INTO `qx_permission` VALUES ('be37e19cb4994367be4c8ed47deb1d49', '获取角色列表', 3, 'getRoleList', '2018-10-31 00:00:00', '2018-10-31 00:00:00');
INSERT INTO `qx_permission` VALUES ('eb70d5a6cd554316a34237a9b9521984', '获取用户', 4, 'getUser', '2018-11-07 00:00:00', '2018-11-07 00:00:00');

-- ----------------------------
-- Table structure for qx_role
-- ----------------------------
DROP TABLE IF EXISTS `qx_role`;
CREATE TABLE `qx_role`  (
  `rid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `rname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `rdescription` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `rvalue` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色值',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  INDEX `rid`(`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qx_role
-- ----------------------------
INSERT INTO `qx_role` VALUES ('686a54140cfa4cf0b0c2ad51755a3d68', 'admin', '管理员权限', 'admin', '2018-10-31 00:00:00', '2018-10-31 00:00:00');
INSERT INTO `qx_role` VALUES ('045001730c5c4c60a16b35fdb7aaae4b', 'user', '普通用户', 'user', '2018-10-31 00:00:00', '2018-10-31 00:00:00');

-- ----------------------------
-- Table structure for qx_rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `qx_rolepermission`;
CREATE TABLE `qx_rolepermission`  (
  `rid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `pid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  INDEX `relation_role`(`rid`) USING BTREE,
  INDEX `relation_permisson`(`pid`) USING BTREE,
  CONSTRAINT `relation_permisson` FOREIGN KEY (`pid`) REFERENCES `qx_permission` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `relation_role` FOREIGN KEY (`rid`) REFERENCES `qx_role` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qx_rolepermission
-- ----------------------------
INSERT INTO `qx_rolepermission` VALUES ('686a54140cfa4cf0b0c2ad51755a3d68', '5258c345227d45c99e252b79c32de7b7');
INSERT INTO `qx_rolepermission` VALUES ('686a54140cfa4cf0b0c2ad51755a3d68', '5c05db6be97b4c8281f71ef6ced18ed0');
INSERT INTO `qx_rolepermission` VALUES ('686a54140cfa4cf0b0c2ad51755a3d68', 'be37e19cb4994367be4c8ed47deb1d49');
INSERT INTO `qx_rolepermission` VALUES ('686a54140cfa4cf0b0c2ad51755a3d68', 'eb70d5a6cd554316a34237a9b9521984');
INSERT INTO `qx_rolepermission` VALUES ('045001730c5c4c60a16b35fdb7aaae4b', 'eb70d5a6cd554316a34237a9b9521984');
INSERT INTO `qx_rolepermission` VALUES ('045001730c5c4c60a16b35fdb7aaae4b', 'be37e19cb4994367be4c8ed47deb1d49');
INSERT INTO `qx_rolepermission` VALUES ('686a54140cfa4cf0b0c2ad51755a3d68', '8021173c8a4e462195b67a8dcdbb074e');

-- ----------------------------
-- Table structure for qx_user
-- ----------------------------
DROP TABLE IF EXISTS `qx_user`;
CREATE TABLE `qx_user`  (
  `uid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `uname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `state` int(10) NOT NULL COMMENT '状态 ：1正常 0禁用',
  `loginIp` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `loginPort` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录端口',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qx_user
-- ----------------------------
INSERT INTO `qx_user` VALUES ('1177dcb0781b49a9a6b794d36df575a3', 'root', '晓东3', 'e10adc3949ba59abbe56e057f20f883e', '2019-01-18 16:31:56', '2019-01-22 14:29:05', 1, NULL, NULL);
INSERT INTO `qx_user` VALUES ('ffa20ac2933c4cc68ea51f08b9fe6277', 'admin', '管理员', 'e10adc3949ba59abbe56e057f20f883e', '2018-11-07 08:21:22', '2018-11-07 00:00:00', 1, NULL, NULL);

-- ----------------------------
-- Table structure for qx_userrole
-- ----------------------------
DROP TABLE IF EXISTS `qx_userrole`;
CREATE TABLE `qx_userrole`  (
  `uid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `rid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  INDEX `user-key`(`uid`) USING BTREE,
  INDEX `role-key`(`rid`) USING BTREE,
  CONSTRAINT `role-key` FOREIGN KEY (`rid`) REFERENCES `qx_role` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user-key` FOREIGN KEY (`uid`) REFERENCES `qx_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qx_userrole
-- ----------------------------
INSERT INTO `qx_userrole` VALUES ('ffa20ac2933c4cc68ea51f08b9fe6277', '686a54140cfa4cf0b0c2ad51755a3d68');
INSERT INTO `qx_userrole` VALUES ('1177dcb0781b49a9a6b794d36df575a3', '045001730c5c4c60a16b35fdb7aaae4b');

SET FOREIGN_KEY_CHECKS = 1;
