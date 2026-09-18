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

-- include  executor_include.sql start;
SET FOREIGN_KEY_CHECKS=0;

-- include  executor1.sql start;

-- include  executor1.sql end;

-- include  executor2.sql start;

-- include  executor2.sql end;
SET FOREIGN_KEY_CHECKS=1;

-- include  executor_include.sql end;
SET FOREIGN_KEY_CHECKS=1;
