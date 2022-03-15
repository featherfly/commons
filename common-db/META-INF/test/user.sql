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