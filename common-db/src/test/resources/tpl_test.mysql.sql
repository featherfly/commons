drop database IF EXISTS db_test;
create database db_test;
use db_test;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID，由于此表数据不用上传，所以直接使用自动递增',
  `NAME` varchar(20) DEFAULT NULL,
  `DESCP` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4', 'updater_82', 'updater_d_1');
INSERT INTO `role` VALUES ('5', 'n_7', 'descp_4');
INSERT INTO `role` VALUES ('6', 'n_11', 'descp_64');
INSERT INTO `role` VALUES ('7', 'n_98', 'descp_79');
INSERT INTO `role` VALUES ('8', 'n_21', 'descp_5');
INSERT INTO `role` VALUES ('9', 'n_96', 'descp_98');
INSERT INTO `role` VALUES ('10', 'n_96', 'descp_29');
INSERT INTO `role` VALUES ('11', 'n_42', 'descp_54');
INSERT INTO `role` VALUES ('12', 'n_12', 'descp_65');
INSERT INTO `role` VALUES ('18', 'n_13', 'descp_52');
INSERT INTO `role` VALUES ('19', 'n_80', 'descp_99');
INSERT INTO `role` VALUES ('20', 'n_34', 'descp_18');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID，由于此表数据不用上传，所以直接使用自动递增',
  `USERNAME` varchar(255) DEFAULT NULL COMMENT '用户名，登陆系统用，单一数据库保证唯一',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码，保存加密后的密码',
  `MOBILE_NO` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `AGE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_NAME_UQ` (`USERNAME`),
  UNIQUE KEY `MOBILE_NO_UQ` (`MOBILE_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yufei', '123456', '12345678901', '5');
INSERT INTO `user` VALUES ('2', 'featherfly', '654321', '98765432101', '5');
INSERT INTO `user` VALUES ('3', 'yufei15', '123456', '15345678915', '15');
INSERT INTO `user` VALUES ('4', 'yufei25', '123456', '25345678925', '25');
INSERT INTO `user` VALUES ('5', 'yufei35', '123456', '35345678935', '35');
INSERT INTO `user` VALUES ('6', 'yufei45', '123456', '45345678945', '45');
INSERT INTO `user` VALUES ('7', 'yufei55', '123456', '55345678955', '55');
INSERT INTO `user` VALUES ('8', 'featherfly10', '654321', '10765432110', '10');
INSERT INTO `user` VALUES ('9', 'featherfly20', '654321', '20765432120', '20');
INSERT INTO `user` VALUES ('10', 'featherfly30', '654321', '30765432130', '30');
INSERT INTO `user` VALUES ('11', 'featherfly40', '654321', '40765432140', '40');
INSERT INTO `user` VALUES ('12', 'featherfly50', '654321', '50765432150', '50');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2', null);
INSERT INTO `user_role` VALUES ('2', '2', 'desc_cc66');
INSERT INTO `user_role` VALUES ('51', '218', 'descp401');
INSERT INTO `user_role` VALUES ('215', '890', 'descp446');
INSERT INTO `user_role` VALUES ('522', '367', 'descp866');
INSERT INTO `user_role` VALUES ('760', '263', 'descp848');
INSERT INTO `user_role` VALUES ('769', '248', 'descp489');
INSERT INTO `user_role` VALUES ('920', '122', 'descp581');


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID，由于此表数据不用上传，所以直接使用自动递增',
  `user_id` int(10) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------

INSERT INTO `user_info` (`ID`, `user_id`, `name`, `descp`, `province`, `city`, `district`) VALUES ('1', '1', '羽飞', '羽飞描述', '四川', '成都', '金牛');
INSERT INTO `user_info` (`ID`, `user_id`, `name`, `descp`, `province`, `city`, `district`) VALUES ('2', '2', '翼', '翼描述', '广东', '深圳', '罗湖');

DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID，由于此表数据不用上传，所以直接使用自动递增',
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
