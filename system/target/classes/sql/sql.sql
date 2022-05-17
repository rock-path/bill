CREATE TABLE `bill_type` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                             `t_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型id',
                             `t_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型名称',
                             `p_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父类型id',
                             `user_description` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
                             `deleted` varchar(1) DEFAULT '0' COMMENT '删除标记',
                             `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `crname` varchar(32) DEFAULT NULL COMMENT '创建人',
                             `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `chname` varchar(32) DEFAULT NULL COMMENT '更新人',
                             `session_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户判断当前用户是否重复登录',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8 COMMENT='消费类型表'



CREATE TABLE `bill_record` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                             `t_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消费类型id',
                             `r_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录id',
                             `r_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消费时间',
                             `r_number` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消费金额',
                             `desc` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
                             `deleted` varchar(1) DEFAULT '0' COMMENT '删除标记',
                             `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `crname` varchar(32) DEFAULT NULL COMMENT '创建人',
                             `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `chname` varchar(32) DEFAULT NULL COMMENT '更新人',
                             `session_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户判断当前用户是否重复登录',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8 COMMENT='消费记录表'



CREATE TABLE `bill_income` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `i_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收入的时间,年月',
                               `i_number` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '金额',
                               `desc` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
                               `deleted` varchar(1) DEFAULT '0' COMMENT '删除标记',
                               `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `crname` varchar(32) DEFAULT NULL COMMENT '创建人',
                               `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `chname` varchar(32) DEFAULT NULL COMMENT '更新人',
                               `session_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户判断当前用户是否重复登录',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8 COMMENT='收入表'


CREATE TABLE `important_events` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `important_time` timestamp NULL DEFAULT NULL COMMENT '事件时间,年月',
                               `important_descd` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '事件描述',
                               `deleted` varchar(1) DEFAULT '0' COMMENT '删除标记',
                               `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `crname` varchar(32) DEFAULT NULL COMMENT '创建人',
                               `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `chname` varchar(32) DEFAULT NULL COMMENT '更新人',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 COMMENT='重要事件'