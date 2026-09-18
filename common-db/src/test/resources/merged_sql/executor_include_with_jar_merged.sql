SET FOREIGN_KEY_CHECKS=0;

-- include  executor1.sql start;
CREATE TABLE IF NOT EXISTS `RC_CONFIGURATION_DIFINITION`
(
   `NAME`                 VARCHAR(100) NOT NULL,
   `DESCP`                VARCHAR(50),
   PRIMARY KEY (`NAME`)
);
CREATE TABLE IF NOT EXISTS `RC_CONFIGURATION_VALUE`
(
   `CONFIG_NAME`          VARCHAR(100),
   `NAME`                 VARCHAR(50),
   `DESCP`                VARCHAR(50),
   VALUE                VARCHAR(300),
   PRIMARY KEY (`CONFIG_NAME`, `NAME`)
);
ALTER TABLE `RC_CONFIGURATION_VALUE` ADD CONSTRAINT FK_REFERENCE_CONFIG_NAME FOREIGN KEY (`CONFIG_NAME`)
      REFERENCES `RC_CONFIGURATION_DIFINITION` (`NAME`) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- include  executor1.sql end;

-- include  executor2.sql start;
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

-- include  executor2.sql end;

-- include  META-INF/test/user.sql start;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT ,
  `username` varchar(255) DEFAULT NULL ,
  `password` varchar(255) DEFAULT NULL ,
  `mobile_no` varchar(11) DEFAULT NULL ,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_NAME_UQ` (`username`),
  UNIQUE KEY `MOBILE_NO_UQ` (`mobile_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- include  META-INF/test/user.sql end;

-- include  META-INF/test/user_data.sql start;
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
INSERT INTO `user` VALUES ('13', 'featherfly10-2', '654321', '10765432112', '10');

-- include  META-INF/test/user_data.sql end;
SET FOREIGN_KEY_CHECKS=1;
