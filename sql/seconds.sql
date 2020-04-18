

-- ----------------------------
-- Table structure for ss_user
-- ----------------------------
DROP TABLE IF EXISTS `ss_user`;
CREATE TABLE `ss_user` (
  `id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt)+salt',
  `salt` varchar(10) DEFAULT NULL COMMENT '混淆盐',
  `head` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sk_user
-- ----------------------------
INSERT INTO `ss_user` VALUES ('18181818181', 'jesper', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2018-05-21 21:10:21', '2018-05-21 21:10:25', '1');
INSERT INTO `ss_user` VALUES ('18217272828', 'jesper', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2018-05-21 21:10:21', '2018-05-21 21:10:25', '1');