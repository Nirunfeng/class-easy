DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
                             `userid` varchar(64) NOT NULL COMMENT '用户id',
                             `username` varchar(255) NOT NULL COMMENT '用户昵称',
                             `password` varchar(255) NOT NULL COMMENT '用户密码',
                             `mobilephone` varchar(255) DEFAULT NULL COMMENT '手机号',
                             `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
                             `uimage` varchar(255) DEFAULT NULL COMMENT '用户头像',
                             `sex` varchar(255) DEFAULT NULL COMMENT '用户性别',
                             `school` varchar(255) DEFAULT NULL COMMENT '学校',
                             `faculty` varchar(255) DEFAULT NULL COMMENT '院系',
                             `startime` varchar(4) DEFAULT NULL COMMENT '入学时间',
                             `userstatus` int(10) DEFAULT '1' COMMENT '1正常 0封号',
                             `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                             `status` varchar(255) DEFAULT 'offline' COMMENT '用户状态',
                             `sign` varchar(255) DEFAULT NULL COMMENT '签名',
                             PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_perm
-- ----------------------------
DROP TABLE IF EXISTS `user_perm`;
CREATE TABLE `user_perm` (
                             `roleid` int(10) DEFAULT NULL COMMENT '1普通用户 2管理员 3超级管理员',
                             `perms` varchar(255) DEFAULT NULL COMMENT '对应权限',
                             `mean` varchar(255) DEFAULT NULL COMMENT '权限解释'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_perm
-- ----------------------------
INSERT INTO `user_perm` VALUES ('1', 'user:userinfo', '用户个人信息');
INSERT INTO `user_perm` VALUES ('3', 'admin:set', '设置管理员');
INSERT INTO `user_perm` VALUES ('2', 'user:userinfo', '用户个人信息');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `user_id` varchar(64) NOT NULL COMMENT '用户id',
                             `role_id` int(10) NOT NULL DEFAULT '1' COMMENT '1普通用户 2管理员 3超级管理员',
                             `identity` varchar(255) DEFAULT NULL COMMENT '身份',
                             PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user_role` VALUES ('1589628308220845788', '3', '超级管理员');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
                         `id` varchar(64) NOT NULL COMMENT '登录id',
                         `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
                         `role_id` int(10) DEFAULT '1' COMMENT '角色id 1普通用户 2管理员 3超级管理员',
                         `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
                         `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
                         `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
                         `user_status` int(10) DEFAULT '1' COMMENT '1正常 0封号',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 密码：123456
INSERT INTO `login` VALUES ('1589628285110931399', '1589628308220845788', '3', 'admin', '1cd75ca00db28072479992f87d4a647e', '13342411140', '1');
