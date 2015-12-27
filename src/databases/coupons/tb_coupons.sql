-- 优惠券
DROP TABLE IF EXISTS `tb_coupons`;
CREATE TABLE `tb_coupons` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `unit_type` int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  `zone_code` varchar(255) DEFAULT NULL COMMENT '区域编码',
  `coupons_code` varchar(255) DEFAULT NULL COMMENT '优惠券号',
  `coupons_name` varchar(255) DEFAULT NULL COMMENT '优惠券名称',
  `coupons_balance` double(10,2) DEFAULT NULL COMMENT '金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '截止时间',
  `status` int(2) DEFAULT NULL COMMENT '-1：删除、0：正常、1：未启用',
  `is_activity` int(2) DEFAULT NULL COMMENT '特价商品是否限制  0：否、1：是',
  `is_multi` int(2) DEFAULT NULL COMMENT '是否可以累加使用 0：否、1：是',
  `is_limit_amount` int(2) DEFAULT NULL COMMENT '是否限制金额 0：否、1：是',
  `limit_amount` double(10,2) DEFAULT NULL COMMENT '限制金额',
  `is_limit_type` int(2) DEFAULT NULL COMMENT '是否限制类型 0：否、1：是',
  `limit_type` varchar(255) DEFAULT NULL COMMENT '限制类型   类型编码',
  `is_limit_mark` int(2) DEFAULT NULL COMMENT '是否限制标签 0：否、1：是',
  `limit_mark` varchar(255) DEFAULT NULL COMMENT '限制的标签    ',
  `is_limit_goods` int(2) DEFAULT NULL COMMENT '是否限制商品 0：否、1：是',
  `limit_goods` bigint(20) DEFAULT NULL COMMENT '限制的商品定价id ',
  `is_limit_platform` int(2) DEFAULT NULL COMMENT '是否限制平台 0：否、1：是',
  `limit_platform` varchar(255) DEFAULT NULL COMMENT '限制的平台',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注',
  `str_1` varchar(255) DEFAULT NULL COMMENT '字符扩展数据1',
  `str_2` varchar(255) DEFAULT NULL COMMENT '字符扩展数据2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- 优惠券详细表
DROP TABLE IF EXISTS `tb_coupons_detail`;
CREATE TABLE `tb_coupons_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupons_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `coupons_code` varchar(255) DEFAULT NULL COMMENT '优惠券号',
  `coupons_rule` longtext  DEFAULT NULL COMMENT '使用规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 优惠券来源
DROP TABLE IF EXISTS `tb_coupons_source`;
CREATE TABLE `tb_coupons_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) DEFAULT NULL COMMENT '单位id',
  `unit_type` int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  `zone_code` varchar(255) DEFAULT NULL COMMENT '区域编码',
  `source_name` varchar(255) DEFAULT NULL COMMENT '来源名称',
  `source_code` varchar(255) DEFAULT NULL COMMENT '来源编码 （注册、推荐好友、满多少元赠送、首单优惠、平台发放、自己领取 等操作）',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '截止时间',
  `str_1` varchar(255) DEFAULT NULL COMMENT '字符扩展数据1',
  `str_2` varchar(255) DEFAULT NULL COMMENT '字符扩展数据2',
  `str_3` varchar(500) DEFAULT NULL COMMENT '字符扩展数据3',
  `date_1` datetime DEFAULT NULL COMMENT '时间扩展数据1',
  `date_2` datetime DEFAULT NULL COMMENT '时间扩展数据2',
  `int_1` int(11) DEFAULT NULL COMMENT '整型扩展数据1',
  `int_2` int(11) DEFAULT NULL COMMENT '整型扩展数据2',
  `double_1` double(10,2) DEFAULT NULL COMMENT '浮点扩展数据1',
  `double_2` double(10,2) DEFAULT NULL COMMENT '浮点扩展数据2',
  `memo` varchar(255)  DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(2) DEFAULT NULL COMMENT '状态 -1：删除、0：正常、1：未启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- 优惠券来源关联
DROP TABLE IF EXISTS `tb_coupons_source_rt`;
CREATE TABLE `tb_coupons_source_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(20) DEFAULT NULL COMMENT '来源id',
  `coupons_id` bigint(20) DEFAULT NULL COMMENT '优惠券id ',
  `coupons_code` varchar(255) DEFAULT NULL COMMENT '优惠券号 ',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间 ',
  `status` int(2) DEFAULT NULL COMMENT '状态 -1：删除、0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- 优惠券用户关联表 
DROP TABLE IF EXISTS `tb_coupons_user_rt`;
CREATE TABLE `tb_coupons_user_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `claim_code` varchar(255) DEFAULT NULL COMMENT '优惠卷认领编码',
  `coupons_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `coupons_code` varchar(255) DEFAULT NULL COMMENT '优惠券号',
  `account` varchar(255) NOT NULL COMMENT '会员账号',
  `status` int(2) DEFAULT NULL COMMENT '是否使用 -1：过期、0：未使用、已使用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `use_date` datetime DEFAULT NULL COMMENT '使用时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注 ',
  `source_code` varchar(255) DEFAULT NULL COMMENT '来源编码 （注册、推荐好友、满多少元赠送等操作）  ',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `str_1` varchar(255) DEFAULT NULL COMMENT '字符扩展数据1',
  `str_2` varchar(255) DEFAULT NULL COMMENT '字符扩展数据2',
  `str_3` varchar(500) DEFAULT NULL COMMENT '字符扩展数据3',
  `date_1` datetime DEFAULT NULL COMMENT '时间扩展数据1',
  `date_2` datetime DEFAULT NULL COMMENT '时间扩展数据2',
  `int_1` int(11) DEFAULT NULL COMMENT '整型扩展数据1',
  `int_2` int(11) DEFAULT NULL COMMENT '整型扩展数据2',
  `double_1` double(10,2) DEFAULT NULL COMMENT '浮点扩展数据1',
  `double_2` double(10,2) DEFAULT NULL COMMENT '浮点扩展数据2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;