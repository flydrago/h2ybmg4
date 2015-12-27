-- ----------------------------
-- 酒库
-- ----------------------------
DROP TABLE IF EXISTS `tb_spirit_room`;
CREATE TABLE `tb_spirit_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '酒库密码',
  `room_code` varchar(50) DEFAULT NULL COMMENT '酒库编码 酒库唯一标识',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品总数量',
  `goods_amount` double(10,2) DEFAULT NULL COMMENT '商品总金额 暂时不用',
  `create_date` datetime DEFAULT NULL,
  `last_date` datetime DEFAULT NULL COMMENT '最后一次操作时间',
  `status` int(2) DEFAULT NULL COMMENT '是否激活，0：未激活、1：已激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 酒库商品
-- ----------------------------
DROP TABLE IF EXISTS `tb_spirit_room_goods_rt`;
CREATE TABLE `tb_spirit_room_goods_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_code` varchar(50) DEFAULT NULL COMMENT '酒库编码 酒库唯一标识',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `unit_type` int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  `zone_code` varchar(50) DEFAULT NULL COMMENT '区域编码',
  `goods_nick_name` varchar(255) DEFAULT NULL COMMENT '商品昵称',
  `goods_price_id` bigint(20) DEFAULT NULL COMMENT '商品定价id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `goods_amount` double(10,2) DEFAULT NULL COMMENT '商品金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- 酒库商品历史（酒库历史，就是成功）
-- ----------------------------
DROP TABLE IF EXISTS `tb_spirit_room_goods_rt_his`;
CREATE TABLE `tb_spirit_room_goods_rt_his` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号（购买时为null）',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实名称（购买时为null）',
  `bag_code` varchar(50) DEFAULT NULL COMMENT '礼包编码（购买时为null）',
  `to_account` varchar(20) DEFAULT NULL COMMENT '目标账号',
  `to_real_name` varchar(20) DEFAULT NULL COMMENT '真实名称（购买时为null）',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `unit_type` int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  `zone_code` varchar(50) DEFAULT NULL COMMENT '区域编码',
  `goods_nick_name` varchar(255) DEFAULT NULL COMMENT '商品昵称',
  `goods_price_id` bigint(20) DEFAULT NULL COMMENT '商品定价id',
  `goods_price_version` int(11) DEFAULT NULL COMMENT '商品定价版本号（逻辑暂时不用）',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id（逻辑暂时不用）',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `goods_amount` double(10,2) DEFAULT NULL COMMENT '商品金额（逻辑暂时不用）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `data_type` int(2) DEFAULT NULL COMMENT '10：购买、12：收取礼包、13：礼包失效退还、14：下单拒收退还、20：礼包下单提取、21：赠送礼包',
  `data_status` int(2) DEFAULT NULL COMMENT '对应各个操作的状态',
  `data_1` varchar(255) DEFAULT NULL COMMENT '扩展数据1',
  `data_2` varchar(500) DEFAULT NULL COMMENT '扩展数据2',
  `data_3` datetime DEFAULT NULL COMMENT '扩展数据3',
  `data_4` int(11) DEFAULT NULL COMMENT '扩展数据4',
  `data_5` double(10,2) DEFAULT NULL COMMENT '扩展数据5',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 礼包
-- ----------------------------
DROP TABLE IF EXISTS `tb_gift_bag`;
CREATE TABLE `tb_gift_bag` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `zone_code` varchar(50) DEFAULT NULL COMMENT '区域编码',
  `bag_code` varchar(50) DEFAULT NULL COMMENT '礼包编码',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量 ',
  `goods_amount` double DEFAULT NULL COMMENT '商品金额 可以暂时不用',
  `account` varchar(20) DEFAULT NULL COMMENT '送礼人账号',
  `sign_code` varchar(255) DEFAULT NULL COMMENT '签收唯一码 暂时不用',
  `security_code` varchar(255) DEFAULT NULL COMMENT '礼包验证码 暂时不用',
  `bag_status` int(2) DEFAULT NULL COMMENT '礼包状态（-1：删除、0:待送出、1：已送出）',
  `sign_status` int(2) DEFAULT NULL COMMENT '签收状态 （-2：过期、-1：拒签、0:待签收、1：已签收）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `sign_date` datetime DEFAULT NULL COMMENT '签收时间 或 拒签时间',
  `end_date` datetime DEFAULT NULL COMMENT '截止时间 在截止日期未领取自动退还到礼包赠送人酒库中',
  `to_account` varchar(11) DEFAULT NULL COMMENT '收礼人',
  `to_mobile` varchar(11) DEFAULT NULL COMMENT '收礼人提示手机号',
  `to_user_name` varchar(11) DEFAULT NULL COMMENT '收礼姓名',
  `content` varchar(500)  DEFAULT NULL COMMENT '礼包消息（eg:捎句话） ',
  `data_type` int(2) DEFAULT '1' COMMENT '礼包类型  1:用户创建礼包   2:后台导入礼包   3：系统发放礼包',
  `data_1` varchar(255) DEFAULT NULL COMMENT '扩展数据1',
  `data_2` varchar(500) DEFAULT NULL COMMENT '扩展数据2',
  `data_3` datetime DEFAULT NULL COMMENT '扩展数据3',
  `data_4` int(11) DEFAULT NULL COMMENT '扩展数据4',
  `data_5` double(10,2) DEFAULT NULL COMMENT '扩展数据5',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- 礼包商品关联
-- ----------------------------
DROP TABLE IF EXISTS `tb_gift_bag_goods_rt`;
CREATE TABLE `tb_gift_bag_goods_rt` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `bag_code` varchar(50) DEFAULT NULL COMMENT '礼包编码',
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `unit_type` int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  `zone_code` varchar(50) DEFAULT NULL COMMENT '区域编码',
  `goods_nick_name` varchar(255) DEFAULT NULL COMMENT '商品昵称',
  `goods_price_id` bigint(20) DEFAULT NULL COMMENT '商品定价id',
  `goods_price_version` int(11) DEFAULT NULL COMMENT '商品定价版本号（逻辑暂时不用）',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id（逻辑暂时不用）',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `goods_amount` double(10,2) DEFAULT NULL COMMENT '商品金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT NULL COMMENT '状态（-1：移除、0：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 后台导入商品礼包
-- ----------------------------
DROP TABLE IF EXISTS `tb_import_bag`;
CREATE TABLE `tb_import_bag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `bag_name` varchar(50) DEFAULT NULL COMMENT '礼包名称',
  `business_user` varchar(50) DEFAULT NULL COMMENT '业务人员',
  `business_mobile` varchar(20) DEFAULT NULL COMMENT '业务手机号',
  `bag_code` varchar(50) DEFAULT NULL COMMENT '礼包编码',
  `account` varchar(20) DEFAULT NULL COMMENT '送礼人',
  `unit_id` bigint(20) NOT NULL COMMENT '单位id',
  `unit_type` int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  `zone_code` varchar(50) DEFAULT NULL COMMENT '区域编码',
  `goods_number` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `goods_nick_name` varchar(255) DEFAULT NULL COMMENT '商品昵称',
  `goods_price_id` bigint(20) DEFAULT NULL COMMENT '商品定价id',
  `goods_price_version` int(11) DEFAULT NULL COMMENT '商品定价版本号（逻辑暂时不用）',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id（逻辑暂时不用）',
  `single_price` double(10,2) DEFAULT NULL COMMENT '商品单价',
  `status` int(2) DEFAULT NULL COMMENT '礼包状态（-1：作废、0:待发起、1：处理中、2：结束、3：当前礼包已经同步）',
  `current_task` varchar(20) DEFAULT NULL COMMENT '当前任务 start:开始 chk_status1：对应审核 end:结束 ',
  `chk_status1` int(2) DEFAULT NULL COMMENT '礼包状态（-1：不通过、0:待审核、1：通过）',
  `chk_status2` int(2) DEFAULT NULL COMMENT '礼包状态（-1：不通过、0:待审核、1：通过）',
  `chk_status3` int(2) DEFAULT NULL COMMENT '礼包状态（-1：不通过、0:待审核、1：通过）',
  `chk_status4` int(2) DEFAULT NULL COMMENT '礼包状态（-1：不通过、0:待审核、1：通过）',
  `chk_status5` int(2) DEFAULT NULL COMMENT '礼包状态（-1：不通过、0:待审核、1：通过）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `start_date` datetime DEFAULT NULL COMMENT '发起时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `data_1` varchar(255) DEFAULT NULL COMMENT '扩展数据1',
  `data_2` varchar(500) DEFAULT NULL COMMENT '扩展数据2',
  `data_3` datetime DEFAULT NULL COMMENT '扩展数据3',
  `data_4` int(11) DEFAULT NULL COMMENT '扩展数据4',
  `data_5` double(10,2) DEFAULT NULL COMMENT '扩展数据5',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 后台导入商品
-- ----------------------------
DROP TABLE IF EXISTS `tb_import_bag_user_rt`;
CREATE TABLE `tb_import_bag_user_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bag_code` varchar(50) DEFAULT NULL COMMENT '礼包编码',
  `to_account` varchar(20) DEFAULT NULL COMMENT '收礼人',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '发起时间',
  `sign_date` datetime DEFAULT NULL COMMENT '签收时间 或 拒签时间',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注 ',
  `status` int(2) DEFAULT '0' COMMENT '接受用户状态（-2：已失效、-1：删除、0:正常、1：已签收）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 后台导入商品任务
-- ----------------------------
DROP TABLE IF EXISTS `tb_import_goods_task`;
CREATE TABLE `tb_import_goods_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bag_code` varchar(50) DEFAULT NULL COMMENT '礼包编码',
  `data_id` bigint(20) NOT NULL  COMMENT '主表id',
  `data_type` int(20) NOT NULL  COMMENT '业务类型 0：导入商品审核',
  `user_id` bigint(20) DEFAULT NULL COMMENT '处理人',
  `task_type` varchar(50) DEFAULT NULL COMMENT '审核类型 对应审核列字段名',
  `task_value` varchar(50) DEFAULT NULL COMMENT '审核类型值',
  `result_code` varchar(50) DEFAULT NULL COMMENT '审核结果编码',
  `result_value` varchar(50) DEFAULT NULL COMMENT '审核结果值',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;