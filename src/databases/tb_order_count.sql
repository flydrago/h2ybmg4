-- 订单统计表
DROP TABLE IF EXISTS tb_order_count;
CREATE TABLE tb_order_count (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '订单所属单位',
  unit_type bigint(20) DEFAULT NULL COMMENT '所属单位类型',
  unit_name varchar(100) DEFAULT NULL COMMENT  '单位名称 和营业执照一致',
  unit_short_name varchar(100) DEFAULT NULL COMMENT '单位简称',
  zone_code varchar(50) DEFAULT NULL COMMENT '区域编码',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  order_day date DEFAULT NULL COMMENT '订单日期',
  order_count int(11) DEFAULT NULL COMMENT '订单总数量',
  real_amount double(10,2) DEFAULT NULL COMMENT '订单实际金额',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  coin_amount double(10,2) DEFAULT NULL COMMENT '达人币对应人民币金额',
  goods_transport_amount double(10,2) DEFAULT NULL COMMENT '运费',
  coupons_amount double(10,2) DEFAULT NULL COMMENT '优惠劵金额',
  double1 double(10,2) DEFAULT NULL COMMENT '备用字段',
  double2 double(10,2) DEFAULT NULL COMMENT '备用字段',
  str1 varchar(100) DEFAULT NULL COMMENT '备用字段',
  str2 varchar(150) DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单统计表';


-- 订单统计信息表
DROP TABLE IF EXISTS tb_order_count_info;
CREATE TABLE tb_order_count_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  count_id bigint(20) DEFAULT NULL COMMENT '统计id',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  order_count int(11) DEFAULT NULL COMMENT '订单总数量',
  real_amount double(10,2) DEFAULT NULL COMMENT '订单实际金额',
  goods_amount double(10,2) DEFAULT NULL COMMENT '商品总金额',
  coin_amount double(10,2) DEFAULT NULL COMMENT '达人币对应人民币金额',
  goods_transport_amount double(10,2) DEFAULT NULL COMMENT '运费',
  coupons_amount double(10,2) DEFAULT NULL COMMENT '优惠劵金额',
  double1 double(10,2) DEFAULT NULL COMMENT '备用字段',
  double2 double(10,2) DEFAULT NULL COMMENT '备用字段',
  str1 varchar(100) DEFAULT NULL COMMENT '备用字段',
  str2 varchar(150) DEFAULT NULL COMMENT '备用字段',
  info_type int(2) NOT NULL DEFAULT '0' COMMENT '汇总方式 0：有效订单、1：在线支付、2：线下支付、3：电话外卖、4：拒收订单',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单统计信息表';


