-- 2014-12-08 (会员二维码生成以及头像)
alter table tb_member_user add qr_path varchar(255) COMMENT  '二维码绝对路径';
alter table tb_member_user add qr_rel_path varchar(255) COMMENT   '二维码相对路径';
alter table tb_member_user add uuid varchar(50) COMMENT   '会员标识uuid';
alter table tb_member_user add head_path varchar(255) COMMENT   '头像路径';

-- 会员二维码存储路径
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'member_qr_path', '会员二维码图片存储路径', 'param', '/opt/wps/file/fup/member_qr_path/', '245', '1', '0');


-- 2014-12-09 （门店虚拟店铺和实体店铺区分）

insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('shop_type', '门店类型', 'dict', '', '123', '1', '0');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'shop_type'), 'real', '实体店铺', '实体店铺', '1');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'shop_type'), 'virtual', '虚拟店铺', '虚拟店铺', '2');



-- 2015-01-10 （微活动历史，奖品信息添加，解决用户中奖后，奖品信息修改）
alter table tb_wechat_activity_his add prize_name varchar(255) DEFAULT NULL COMMENT '奖品名称';
alter table tb_wechat_activity_his add prize_number bigint(10) DEFAULT NULL COMMENT '单个奖品数量';
alter table tb_wechat_activity_his add prize_rate double(10,10) DEFAULT NULL COMMENT '中奖几率 百分比最大值为100';
alter table tb_wechat_activity_his add prize_level varchar(255) DEFAULT NULL COMMENT '奖品级别  一等奖等';
alter table tb_wechat_activity_his add prize_type varchar(50) DEFAULT NULL COMMENT '奖品类型（darenbi:达人币、darendou:达人豆、goods:商品、qita:其他）';
alter table tb_wechat_activity_his add goods_id  bigint(20) NOT NULL COMMENT '商品Id，当奖品为商品时，用到';



-- 2015-07-03 用户登录返回userkey，可再次通过userkey登录
ALTER TABLE `tb_member_user` 
ADD COLUMN `user_key` VARCHAR(255) NULL DEFAULT NULL AFTER `ios_push_userid`,
ADD COLUMN `data1` VARCHAR(255) NULL DEFAULT NULL AFTER `user_key`,
ADD COLUMN `data2` VARCHAR(255) NULL DEFAULT NULL AFTER `data1`,
ADD COLUMN `data3` VARCHAR(255) NULL DEFAULT NULL AFTER `data2`;

-- 2015-07-14 修改日志表的字段类型 
alter table tb_os_log_info modify data_1 LONGTEXT;

-- 2015-07-14 订单增加优惠券 
alter table tb_order add is_coupons int(2) DEFAULT 0 COMMENT '是否使用优惠券';
alter table tb_order add coupons_amount double(10,2) DEFAULT NULL COMMENT '优惠券金额';

alter table tb_order_hi add is_coupons int(2) DEFAULT 0 COMMENT '是否使用优惠券';
alter table tb_order_hi add coupons_amount double(10,2) DEFAULT NULL COMMENT '优惠券金额';

-- 2015-0715 解决订单提交异常
alter table tb_order_goods_rt MODIFY COLUMN goods_nick_name varchar(300),
MODIFY COLUMN goods_number varchar(300),
MODIFY COLUMN goods_short_number varchar(300);

-- 2015-07-22 部门人员象过河对接
ALTER TABLE tb_sys_department 
ADD COLUMN xghdcode VARCHAR(255) NULL DEFAULT NULL COMMENT '象过河部门编码',
ADD COLUMN xghcreatedate datetime DEFAULT NULL COMMENT '更新时间';

ALTER TABLE tb_sys_user 
ADD COLUMN xghusercode VARCHAR(255) NULL DEFAULT NULL COMMENT '象过河用户编码',
ADD COLUMN xghcreatedate datetime DEFAULT NULL COMMENT '更新时间';


-- 2015-07-31  推送升级

ALTER TABLE tb_m_msg 
ADD COLUMN `receipt_mark` int(11) DEFAULT '0' COMMENT 'client是否接收到推送消息（0：否  1：是）',
ADD COLUMN `receipt_date` datetime DEFAULT NULL COMMENT 'client接收推送消息时间',
ADD COLUMN `data_1` varchar(500) DEFAULT NULL COMMENT '冗余字段',
ADD COLUMN `data_2` varchar(500) DEFAULT NULL,
ADD COLUMN `data_3` varchar(500) DEFAULT NULL,
ADD COLUMN `data_4` int(11) DEFAULT NULL,
ADD COLUMN `data_5` int(11) DEFAULT NULL,
ADD COLUMN `datasource_type` varchar(255) DEFAULT NULL COMMENT '数据源类型（订单信息 order 、小达快报 news）',
ADD COLUMN `datasource_id` varchar(255) DEFAULT NULL COMMENT '数据源ID（订单编码、小达快报ID）';

ALTER TABLE tb_m_msg_his 
ADD COLUMN `receipt_mark` int(11) DEFAULT '0' COMMENT 'client是否接收到推送消息（0：否  1：是）',
ADD COLUMN `receipt_date` datetime DEFAULT NULL COMMENT 'client接收推送消息时间',
ADD COLUMN `data_1` varchar(500) DEFAULT NULL COMMENT '冗余字段',
ADD COLUMN `data_2` varchar(500) DEFAULT NULL,
ADD COLUMN `data_3` varchar(500) DEFAULT NULL,
ADD COLUMN `data_4` int(11) DEFAULT NULL,
ADD COLUMN `data_5` int(11) DEFAULT NULL,
ADD COLUMN `datasource_type` varchar(255) DEFAULT NULL COMMENT '数据源类型（订单信息 order 、小达快报 news）',
ADD COLUMN `datasource_id` varchar(255) DEFAULT NULL COMMENT '数据源ID（订单编码、小达快报ID）';


-- 促销活动后台 
ALTER TABLE tb_common_activity_goods_rt 
ADD COLUMN limit_sell_number int(10) DEFAULT '0' COMMENT '商品总数';

ALTER TABLE tb_common_activity_goods_rt_his
ADD COLUMN limit_sell_number int(10) DEFAULT '0' COMMENT '商品总数';



-- 2015-08-06 订单统计汇总
alter table tb_order add is_has_count int(2) not null DEFAULT 0 COMMENT '0:未统计、1：已统计';

alter table tb_order_hi add is_has_count int(2) not null DEFAULT 0 COMMENT '0:未统计、1：已统计';


--2015-8-11 添加用户账户字段 及 累计储值字段
ALTER TABLE `tb_grade`
	ADD COLUMN `account` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户账户名' AFTER `update_date`,
	ADD COLUMN `vip_accumulative_recharge` DOUBLE NULL DEFAULT NULL COMMENT '用户累计充值总额' AFTER `vip_balance`;

--2015-8-11 记录会员财富变更的 初始、变量、终值，增添account字段辅助查询
ALTER TABLE `tb_grade_info`
	CHANGE COLUMN `wealth` `wealth` DOUBLE NULL DEFAULT NULL COMMENT '根据财富获取来源Code 得到 每次获取财富的值' AFTER `category`,
	ADD COLUMN `account` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户账户名' AFTER `order_id`,
	ADD COLUMN `previous_wealth` DOUBLE NULL DEFAULT NULL COMMENT '财富增加前，初始值' AFTER `account`,
	ADD COLUMN `latest_wealth` DOUBLE NULL DEFAULT NULL COMMENT '财富增加后，最新值' AFTER `previous_wealth`,
	ADD COLUMN `data_1` VARCHAR(255) NULL DEFAULT NULL COMMENT '备用字段' AFTER `latest_wealth`,
	ADD COLUMN `data_2` VARCHAR(255) NULL DEFAULT NULL AFTER `data_1`,
	ADD COLUMN `data_3` VARCHAR(255) NULL DEFAULT NULL AFTER `data_2`,
	ADD COLUMN `data_4` VARCHAR(255) NULL DEFAULT NULL AFTER `data_3`,
	ADD COLUMN `data_5` DOUBLE NULL DEFAULT NULL AFTER `data_4`;
	
ALTER TABLE `tb_grade_info_cache`
	CHANGE COLUMN `wealth` `wealth` DOUBLE NULL DEFAULT NULL COMMENT '根据财富获取来源Code 得到 每次获取财富的值' AFTER `category`,
	ADD COLUMN `account` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户账户名' AFTER `order_id`,
	ADD COLUMN `previous_wealth` DOUBLE NULL DEFAULT NULL COMMENT '财富增加前，初始值' AFTER `account`,
	ADD COLUMN `latest_wealth` DOUBLE NULL DEFAULT NULL COMMENT '财富增加后，最新值' AFTER `previous_wealth`,
	ADD COLUMN `data_1` VARCHAR(255) NULL DEFAULT NULL COMMENT '备用字段' AFTER `latest_wealth`,
	ADD COLUMN `data_2` VARCHAR(255) NULL DEFAULT NULL AFTER `data_1`,
	ADD COLUMN `data_3` VARCHAR(255) NULL DEFAULT NULL AFTER `data_2`,
	ADD COLUMN `data_4` VARCHAR(255) NULL DEFAULT NULL AFTER `data_3`,
	ADD COLUMN `data_5` DOUBLE NULL DEFAULT NULL AFTER `data_4`;



