
-- ----------------------------
-- 仓库
-- ----------------------------
DROP TABLE IF EXISTS tb_storehouse;
CREATE TABLE tb_storehouse (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type varchar(1) DEFAULT NULL COMMENT '仓库类型：1：正品库，2：在途库，3：残次品库，4：普通库，5：初始化库（不可删除）',
  store_name varchar(255) DEFAULT NULL COMMENT '仓库名称',
  store_spe_name varchar(255) DEFAULT NULL COMMENT '仓库名称全拼',
  store_fir_spe_name varchar(255) DEFAULT NULL COMMENT '仓库名称拼音首字母',
  status int(2) DEFAULT NULL COMMENT '门店状态 0：正常、1：停用、2：删除',
  unit_id bigint(20) DEFAULT '0' COMMENT '代理单位ID',
  unit_type int(10) DEFAULT '0' COMMENT '代理单位ID',
  zone_code varchar(10) DEFAULT NULL COMMENT '地区单位编码',
  shop_id bigint(20) DEFAULT '0' COMMENT '门店ID',
  parent_type varchar(10) DEFAULT NULL COMMENT '隶属上级：unit:单位、shop:门店',
  longitude double(10,2) DEFAULT '0.00' COMMENT '经度',
  latitude double(10,2) DEFAULT '0.00' COMMENT '纬度',
  zone_detail varchar(255) DEFAULT NULL COMMENT '仓库具体位置',
  tele_phone varchar(100) DEFAULT NULL COMMENT '仓库固定电话',
  principal varchar(50) DEFAULT NULL COMMENT '仓库负责人',
  principal_mobile varchar(12) DEFAULT NULL COMMENT '仓库负责人电话',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 仓库历史
-- ----------------------------
DROP TABLE IF EXISTS tb_storehouse_goods_detail;
CREATE TABLE tb_storehouse_goods_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  storehouse_id bigint(20) DEFAULT NULL COMMENT '仓库ID',
  goods_price_id bigint(20) DEFAULT NULL COMMENT '商品定价Id',
  goods_price_version int(10) DEFAULT NULL COMMENT '商品定价Id',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
  goods_location varchar(50) DEFAULT NULL,
  goods_count int(11) DEFAULT NULL COMMENT '商品数量',
  type varchar(1) DEFAULT NULL COMMENT '0:入库；1：出库',
  status int(2) DEFAULT NULL COMMENT '仓库商品 0：正常、-1：作废',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  create_user_id bigint(20) DEFAULT NULL COMMENT '创建用户Id',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 仓库商品详细（仓库具体商品的数量）
-- ----------------------------
DROP TABLE IF EXISTS tb_storehouse_goods_info;
CREATE TABLE tb_storehouse_goods_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  storehouse_id bigint(20) DEFAULT NULL,
  goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
  goods_count int(11) DEFAULT NULL COMMENT '商品实际库存数量',
  virtual_count int(11) DEFAULT NULL COMMENT '商品虚拟数量 暂时不用',
  shop_id bigint(20) DEFAULT '0' COMMENT '门店ID',
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
