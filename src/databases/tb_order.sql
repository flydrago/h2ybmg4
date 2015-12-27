-- ----------------------------
-- 会员收货地址
-- ----------------------------
DROP TABLE IF EXISTS tb_receive_address;
CREATE TABLE tb_receive_address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  member_id bigint(20) DEFAULT NULL COMMENT '会员Id',
  zone_id bigint(20) DEFAULT NULL COMMENT '区域Id',
  zone_code varchar(50) DEFAULT NULL COMMENT '区域编码',
  zone_name varchar(255) DEFAULT NULL COMMENT '区域名称',
  zone_detail varchar(500) DEFAULT NULL COMMENT '详细地址',
  longitude decimal(20,17) DEFAULT NULL COMMENT '经度(decimal数据更加精确)',
  latitude decimal(20,17) DEFAULT NULL COMMENT '纬度(decimal数据更加精确)',
  receiver_name varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  receiver_mobile varchar(12) DEFAULT NULL COMMENT '收货人手机号码',
  receiver_telephone varchar(20) DEFAULT NULL COMMENT '收货人固话',
  receiver_mail varchar(20) DEFAULT NULL COMMENT '收货人邮件',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  memo varchar(225) DEFAULT NULL COMMENT '备注',
  is_default int(2) DEFAULT NULL COMMENT '是否为默认 0：否、1：是',
  status int(2) DEFAULT '0' COMMENT '-1：删除、0：正常',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '会员收货地址';


-- ----------------------------
-- 会员购物车
-- ----------------------------
DROP TABLE IF EXISTS tb_shopping_cart;
CREATE TABLE tb_shopping_cart (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  member_id bigint(20) DEFAULT NULL COMMENT '会员Id',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品Id',
  goods_price_id bigint(20) DEFAULT NULL COMMENT '商品定价Id',
  goods_count int(11) DEFAULT NULL COMMENT '商品数量',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  buy_type int(2) DEFAULT '0' COMMENT '购买类型：0：购买、1：酒库',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  status int(2) DEFAULT '0' COMMENT '-1：已删除、0：正常、1：已提交订单',
  memo varchar(225) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '会员购物车';


-- ----------------------------
-- 购物车历史表
-- ----------------------------
DROP TABLE IF EXISTS tb_shopping_cart_his;
CREATE TABLE tb_shopping_cart_his (
  id bigint(20) NOT NULL,
  member_id bigint(20) DEFAULT NULL COMMENT '会员Id',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品Id',
  goods_price_id bigint(20) DEFAULT NULL COMMENT '商品定价Id',
  goods_count int(11) DEFAULT NULL COMMENT '商品数量',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  buy_type int(2) DEFAULT '0' COMMENT '购买类型：0：购买、1：酒库',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  status int(2) DEFAULT '0' COMMENT '-1：已删除、0：正常、1：已提交订单',
  memo varchar(225) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '会员购物车历史表';


-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  order_no varchar(50) DEFAULT NULL COMMENT '订单编号',
  order_unique varchar(50) DEFAULT NULL COMMENT '订单唯一标识（用于查询同时提交的订单）',
  order_longitude decimal(20,17) DEFAULT NULL COMMENT '订单提交所处经度(decimal数据更加精确)',
  order_latitude decimal(20,17) DEFAULT NULL COMMENT '订单提交所处纬度(decimal数据更加精确)',
  order_position varchar(50) DEFAULT NULL COMMENT '订单提交所处位置',
  order_os varchar(50) DEFAULT '' COMMENT '下单操作客户端类型（IOS:苹果、android:安卓、wechat:微信）',
  order_osv varchar(50) DEFAULT '' COMMENT '下单客户端版本号',
  order_appv varchar(50) DEFAULT '' COMMENT '下单app版本',
  goods_count int(11) DEFAULT NULL COMMENT '商品总数量',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  goods_transport_amount double(10,2) DEFAULT NULL COMMENT '商品总运费',
  unit_id bigint(20) DEFAULT NULL COMMENT '订单所属单位',
  unit_type bigint(20) DEFAULT NULL COMMENT '所属单位类型',
  unit_name varchar(100) DEFAULT NULL COMMENT  '单位名称 和营业执照一致',
  unit_short_name varchar(100) DEFAULT NULL COMMENT '单位简称',
  storehouse_id bigint(20) DEFAULT NULL COMMENT '订单所属仓库',
  storehouse_name varchar(255) DEFAULT NULL COMMENT '仓库名称',
  shop_id bigint(20) DEFAULT NULL COMMENT '订单所属门店',
  shop_name varchar(255) DEFAULT NULL COMMENT '订单所属门店名称',
  shop_longitude decimal(20,17) DEFAULT NULL COMMENT '门店所处经度(decimal数据更加精确)',
  shop_latitude decimal(20,17) DEFAULT NULL COMMENT '门店所处经度(decimal数据更加精确)',
  shop_type varchar(50) DEFAULT NULL COMMENT '门店类型（real：实际门店、virtual：虚拟门店）',
  shop_address varchar(300) DEFAULT NULL COMMENT '门店位置',
  member_id bigint(20) DEFAULT NULL COMMENT '会员id',
  member_account varchar(20) DEFAULT NULL COMMENT '会员账号',
  receiver_address_id bigint(20) DEFAULT NULL COMMENT '收货地址id',
  receiver_address varchar(500) DEFAULT NULL COMMENT '收货地址',
  receiver_longitude decimal(20,17) DEFAULT NULL COMMENT '经度(decimal数据更加精确)',
  receiver_latitude decimal(20,17) DEFAULT NULL COMMENT '纬度(decimal数据更加精确)',
  receiver_name varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  receiver_mobile varchar(12) DEFAULT NULL COMMENT '收货人手机号码',
  receiver_telephone varchar(20) DEFAULT NULL COMMENT '收货人固话',
  receiver_mail varchar(20) DEFAULT NULL COMMENT '收货人邮件',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  order_status int(2) DEFAULT NULL COMMENT '订单状态（20：提交、0：受理、1：待抢单、10：商品出库中、11：商品配送中、12：配送完成、21：签收订单、-21：取消订单、-22：拒绝签收、-11:客户拒收）',
  deliveryer_status int(2) DEFAULT NULL COMMENT '配送状态（10：商品出库中、11：商品配送中、12：配送完成、-11:客户拒收）',
  receiver_status int(2) DEFAULT NULL COMMENT '收货状态（20：提交订单、21：签收订单、-21：取消订单、-22:拒绝签收）',
  deliveryer_id bigint(20) DEFAULT NULL COMMENT '配送员id',
  deliveryer_name varchar(50) DEFAULT NULL COMMENT '配送员姓名',
  deliveryer_mobile varchar(20) DEFAULT NULL COMMENT '配送员手机',
  deliveryer_date datetime DEFAULT NULL COMMENT '配送时间',
  is_pay int(2) DEFAULT NULL COMMENT '是否支付，0：未支付、1：已支付',
  pay_type int(2) DEFAULT NULL COMMENT '支付方式，0：货到付款、1：支付宝支付、2：微信支付',
  pay_account varchar(50) DEFAULT NULL COMMENT '支付账号',
  order_category int(2) DEFAULT NULL COMMENT '订单类别，0:正常下单、1：个人酒库领取、2：非会员用户领取礼包',  
  receive_code varchar(50) DEFAULT NULL COMMENT '非会员领取礼包时的唯一码',
  vip_coin double(10,2) DEFAULT NULL COMMENT '使用的达人币数量',
  coin_amount double(10,2) DEFAULT NULL COMMENT '达人币对应人民币金额',
  real_amount double(10,2) DEFAULT NULL COMMENT '订单实际金额',
  is_child int(2) DEFAULT NULL COMMENT '是否为子订单，0：否、1：是',
  s3ordercode varchar(255) DEFAULT '' COMMENT '进销存订单主编码',
  s3ordercode2 varchar(255) DEFAULT '' COMMENT '进销存子订单编码',
  s3createdate datetime DEFAULT NULL COMMENT '修改时间',
  s3op varchar(255) DEFAULT '' COMMENT '进销存对应订单处理',
  s3ucode varchar(255) DEFAULT '' COMMENT '进销存对应的公司编码',
  s3stcode varchar(255) DEFAULT '' COMMENT '进销存仓库编码',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '订单表';


-- ----------------------------
-- 订单商品关联表
-- ----------------------------
DROP TABLE IF EXISTS tb_order_goods_rt;
CREATE TABLE tb_order_goods_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  order_id bigint(20) DEFAULT NULL COMMENT '订单id',
  order_no varchar(50) DEFAULT NULL COMMENT '订单编号',
  order_unique varchar(50) DEFAULT NULL COMMENT '订单唯一标识（用于查询同时提交的订单）',
  storehouse_id bigint(20) DEFAULT NULL COMMENT '仓库id',
  storehouse_name varchar(255) DEFAULT NULL COMMENT '仓库名称',
  goods_nick_name varchar(50) DEFAULT NULL COMMENT '商品昵称',
  goods_number varchar(50) DEFAULT NULL COMMENT '商品编码',
  goods_short_number varchar(50) DEFAULT NULL COMMENT '商品短编码',
  goods_count int(11) DEFAULT NULL COMMENT '商品总数量',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  single_price double(10,2) DEFAULT NULL COMMENT '商品单价',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  goods_price_id bigint(20) DEFAULT NULL COMMENT '商品定价Id',
  goods_price_version int(11) DEFAULT NULL COMMENT '商品定价版本号',  
  is_gift int(2) DEFAULT NULL COMMENT '是否赠品，0：否、1：是',
  data_id bigint(20) DEFAULT NULL COMMENT '赠品关联主商品定价id',
  buy_type int(2) DEFAULT NULL COMMENT '商品购买方式（0：购买、1：酒库）',
  s3stcode varchar(255) DEFAULT NULL COMMENT '进销存仓库编码',
  s3gdscode varchar(255) DEFAULT '' COMMENT '进销存商品编码',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '订单商品关联表';


-- ----------------------------
-- 订单流向
-- ----------------------------
DROP TABLE IF EXISTS tb_order_flow;
CREATE TABLE tb_order_flow (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  order_id bigint(20) DEFAULT NULL COMMENT '订单id',
  order_no varchar(50) DEFAULT NULL COMMENT '订单编号',
  longitude decimal(20,17) DEFAULT NULL COMMENT '订单当前所处经度(decimal数据更加精确)',
  latitude decimal(20,17) DEFAULT NULL COMMENT '订单当前所处纬度(decimal数据更加精确)',
  position varchar(50) DEFAULT NULL COMMENT '订单提交所处位置',
  order_status int(2) DEFAULT NULL COMMENT '订单状态（0：提交、1：受理、2：出库、3：签单配送中、4：客户收货、5：完成、-1：取消订单、-2：客户拒收）',
  memo varchar(255) DEFAULT NULL COMMENT '备注',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  operator_type int(2) DEFAULT NULL COMMENT '操作人员类型（0：系统人员、1：配送人员、2：会员）',
  operator_id bigint(20) DEFAULT NULL COMMENT '操作人员id',  
  operator_name varchar(50) DEFAULT NULL COMMENT '操作人员名称',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '订单流向';



-- ----------------------------
-- 订单历史表
-- ----------------------------
DROP TABLE IF EXISTS tb_order_hi;
CREATE TABLE tb_order_hi (
  id bigint(20) NOT NULL,
  order_no varchar(50) DEFAULT NULL COMMENT '订单编号',
  order_unique varchar(50) DEFAULT NULL COMMENT '订单唯一标识（用于查询同时提交的订单）',
  order_longitude decimal(20,17) DEFAULT NULL COMMENT '订单提交所处经度(decimal数据更加精确)',
  order_latitude decimal(20,17) DEFAULT NULL COMMENT '订单提交所处纬度(decimal数据更加精确)',
  order_position varchar(50) DEFAULT NULL COMMENT '订单提交所处位置',
  order_os varchar(50) DEFAULT '' COMMENT '下单操作客户端类型（IOS:苹果、android:安卓、wechat:微信）',
  order_osv varchar(50) DEFAULT '' COMMENT '下单客户端版本号',
  order_appv varchar(50) DEFAULT '' COMMENT '下单app版本',
  goods_count int(11) DEFAULT NULL COMMENT '商品总数量',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  goods_transport_amount double(10,2) DEFAULT NULL COMMENT '商品总运费',
  unit_id bigint(20) DEFAULT NULL COMMENT '订单所属单位',
  unit_type bigint(20) DEFAULT NULL COMMENT '所属单位类型',
  unit_name varchar(100) DEFAULT NULL COMMENT  '单位名称 和营业执照一致',
  unit_short_name varchar(100) DEFAULT NULL COMMENT '单位简称',
  storehouse_id bigint(20) DEFAULT NULL COMMENT '订单所属仓库',
  storehouse_name varchar(255) DEFAULT NULL COMMENT '仓库名称',
  shop_id bigint(20) DEFAULT NULL COMMENT '订单所属门店',
  shop_name varchar(255) DEFAULT NULL COMMENT '订单所属门店名称',
  shop_longitude decimal(20,17) DEFAULT NULL COMMENT '门店所处经度(decimal数据更加精确)',
  shop_latitude decimal(20,17) DEFAULT NULL COMMENT '门店所处经度(decimal数据更加精确)',
  shop_type varchar(50) DEFAULT NULL COMMENT '门店类型（real：实际门店、virtual：虚拟门店）',
  shop_address varchar(300) DEFAULT NULL COMMENT '门店位置',
  member_id bigint(20) DEFAULT NULL COMMENT '会员id',
  member_account varchar(20) DEFAULT NULL COMMENT '会员账号',
  receiver_address_id bigint(20) DEFAULT NULL COMMENT '收货地址id',
  receiver_address varchar(500) DEFAULT NULL COMMENT '收货地址',
  receiver_longitude decimal(20,17) DEFAULT NULL COMMENT '经度(decimal数据更加精确)',
  receiver_latitude decimal(20,17) DEFAULT NULL COMMENT '纬度(decimal数据更加精确)',
  receiver_name varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  receiver_mobile varchar(12) DEFAULT NULL COMMENT '收货人手机号码',
  receiver_telephone varchar(20) DEFAULT NULL COMMENT '收货人固话',
  receiver_mail varchar(20) DEFAULT NULL COMMENT '收货人邮件',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  order_status int(2) DEFAULT NULL COMMENT '订单状态（20：提交、0：受理、1：待抢单、10：商品出库中、11：商品配送中、12：配送完成、21：签收订单、-21：取消订单、-22：拒绝签收、-11:客户拒收）',
  deliveryer_status int(2) DEFAULT NULL COMMENT '配送状态（10：商品出库中、11：商品配送中、12：配送完成、-11:客户拒收）',
  receiver_status int(2) DEFAULT NULL COMMENT '收货状态（20：提交订单、21：签收订单、-21：取消订单、-22:拒绝签收）',
  deliveryer_id bigint(20) DEFAULT NULL COMMENT '配送员id',
  deliveryer_name varchar(50) DEFAULT NULL COMMENT '配送员姓名',
  deliveryer_mobile varchar(20) DEFAULT NULL COMMENT '配送员手机',
  deliveryer_date datetime DEFAULT NULL COMMENT '配送时间',
  is_pay int(2) DEFAULT NULL COMMENT '是否支付，0：未支付、1：已支付',
  pay_type int(2) DEFAULT NULL COMMENT '支付方式，0：货到付款、1：支付宝支付、2：微信支付',
  pay_account varchar(50) DEFAULT NULL COMMENT '支付账号',
  order_category int(2) DEFAULT NULL COMMENT '订单类别，0:正常下单、1：个人酒库领取、2：非会员用户领取礼包',  
  receive_code varchar(50) DEFAULT NULL COMMENT '非会员领取礼包时的唯一码',
  vip_coin double(10,2) DEFAULT NULL COMMENT '使用的达人币数量',
  coin_amount double(10,2) DEFAULT NULL COMMENT '达人币对应人民币金额',
  real_amount double(10,2) DEFAULT NULL COMMENT '订单实际金额',
  is_child int(2) DEFAULT NULL COMMENT '是否为子订单，0：否、1：是',
  s3ordercode varchar(255) DEFAULT '' COMMENT '进销存订单主编码',
  s3ordercode2 varchar(255) DEFAULT '' COMMENT '进销存子订单编码',
  s3createdate datetime DEFAULT NULL COMMENT '修改时间',
  s3op varchar(255) DEFAULT '' COMMENT '进销存对应订单处理',
  s3ucode varchar(255) DEFAULT '' COMMENT '进销存对应的公司编码',
  s3stcode varchar(255) DEFAULT '' COMMENT '进销存仓库编码',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '订单历史表';


-- ----------------------------
-- 订单商品关联历史表
-- ----------------------------
DROP TABLE IF EXISTS tb_order_goods_rt_hi;
CREATE TABLE tb_order_goods_rt_hi (
  id bigint(20) NOT NULL,
  order_id bigint(20) DEFAULT NULL COMMENT '订单id',
  order_no varchar(50) DEFAULT NULL COMMENT '订单编号',
  order_unique varchar(50) DEFAULT NULL COMMENT '订单唯一标识（用于查询同时提交的订单）',
  storehouse_id bigint(20) DEFAULT NULL COMMENT '仓库id',
  storehouse_name varchar(255) DEFAULT NULL COMMENT '仓库名称',
  goods_nick_name varchar(50) DEFAULT NULL COMMENT '商品昵称',
  goods_number varchar(50) DEFAULT NULL COMMENT '商品编码',
  goods_short_number varchar(50) DEFAULT NULL COMMENT '商品短编码',
  goods_count int(11) DEFAULT NULL COMMENT '商品总数量',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  single_price double(10,2) DEFAULT NULL COMMENT '商品单价',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  goods_price_id bigint(20) DEFAULT NULL COMMENT '商品定价Id',
  goods_price_version int(11) DEFAULT NULL COMMENT '商品定价版本号',  
  is_gift int(2) DEFAULT NULL COMMENT '是否赠品，0：否、1：是',
  data_id bigint(20) DEFAULT NULL COMMENT '赠品关联主商品定价id',
  buy_type int(2) DEFAULT NULL COMMENT '商品购买方式（0：购买、1：酒库）',
  s3stcode varchar(255) DEFAULT NULL COMMENT '进销存仓库编码',
  s3gdscode varchar(255) DEFAULT '' COMMENT '进销存商品编码',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '订单商品关联历史表';


-- ----------------------------
-- 订单流向历史表
-- ----------------------------
DROP TABLE IF EXISTS tb_order_flow_hi;
CREATE TABLE tb_order_flow_hi (
  id bigint(20) NOT NULL,
  order_id bigint(20) DEFAULT NULL COMMENT '订单id',
  order_no varchar(50) DEFAULT NULL COMMENT '订单编号',
  longitude decimal(20,17) DEFAULT NULL COMMENT '订单当前所处经度(decimal数据更加精确)',
  latitude decimal(20,17) DEFAULT NULL COMMENT '订单当前所处纬度(decimal数据更加精确)',
  position varchar(50) DEFAULT NULL COMMENT '订单提交所处位置',
  order_status int(2) DEFAULT NULL COMMENT '订单状态（0：提交、1：受理、2：出库、3：签单配送中、4：客户收货、5：完成、-1：取消订单、-2：客户拒收）',
  memo varchar(255) DEFAULT NULL COMMENT '备注',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  operator_type int(2) DEFAULT NULL COMMENT '操作人员类型（0：系统人员、1：配送人员、2：会员）',
  operator_id bigint(20) DEFAULT NULL COMMENT '操作人员id',  
  operator_name varchar(50) DEFAULT NULL COMMENT '操作人员名称',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '订单流向历史表';





-- 订单添加区域编码和是否评论标识
alter table tb_order add zone_code VARCHAR(255) DEFAULT NULL COMMENT '区域编码';
alter table tb_order add is_comment int(2) DEFAULT '0' NOT NULL COMMENT '是否已经评论 0：否、1：是';
-- 订单添加区域编码和是否评论标识
alter table tb_order_hi add zone_code VARCHAR(255) DEFAULT NULL COMMENT '区域编码';
alter table tb_order_hi add is_comment int(2) DEFAULT '0' NOT NULL COMMENT '是否已经评论 0：否、1：是';


-- 订单商品添加是否评论标识
alter table tb_order_goods_rt add is_comment int(2) DEFAULT '0' NOT NULL COMMENT '是否已经评论 0：否、1：是';
alter table tb_order_goods_rt_hi add is_comment int(2) DEFAULT '0' NOT NULL COMMENT '是否已经评论 0：否、1：是';

-- 订单汇总    2015-08-05
alter table tb_order add is_has_count int(2) DEFAULT 0 NOT NULL COMMENT '是否已经汇总 0：否、1：是';
alter table tb_order_hi add is_has_count int(2) DEFAULT 0 NOT NULL COMMENT '是否已经汇总 0：否、1：是';

-- 订单支付    2015-08-25
alter table tb_order add is_refund int(2) DEFAULT 0 NOT NULL COMMENT '是否已退款，0：未退款、1：已退款';
alter table tb_order add refund_amount double(10,2) DEFAULT NULL COMMENT '退款金额';
alter table tb_order_hi add is_refund int(2) DEFAULT 0 NOT NULL COMMENT '是否已退款，0：未退款、1：已退款';
alter table tb_order_hi add refund_amount double(10,2) DEFAULT NULL COMMENT '退款金额';

alter table tb_order add trade_no varchar(100) DEFAULT NULL COMMENT '交易号';
alter table tb_order add trade_data1 varchar(100) DEFAULT NULL COMMENT '交易信息扩展1';
alter table tb_order add trade_data2 varchar(100) DEFAULT NULL COMMENT '交易信息扩展2';
alter table tb_order add trade_data3 varchar(100) DEFAULT NULL COMMENT '交易信息扩展3';

alter table tb_order_hi add trade_no varchar(100) DEFAULT NULL COMMENT '交易号';
alter table tb_order_hi add trade_data1 varchar(100) DEFAULT NULL COMMENT '交易信息扩展1';
alter table tb_order_hi add trade_data2 varchar(100) DEFAULT NULL COMMENT '交易信息扩展2';
alter table tb_order_hi add trade_data3 varchar(100) DEFAULT NULL COMMENT '交易信息扩展3';

alter table tb_order add refund_batch_no varchar(50) DEFAULT NULL COMMENT '退款批次号';
alter table tb_order_hi add refund_batch_no varchar(50) DEFAULT NULL COMMENT '退款批次号';

alter table tb_order add user_id bigint(20) DEFAULT NULL COMMENT '制单人id';
alter table tb_order_hi add user_id bigint(20) DEFAULT NULL COMMENT '制单人id';

-- 创建订单号唯一索引
CREATE UNIQUE INDEX orderNoIndex ON tb_order(order_no(50));
