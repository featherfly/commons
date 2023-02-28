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

-- @include executor.sql;

SET FOREIGN_KEY_CHECKS=1