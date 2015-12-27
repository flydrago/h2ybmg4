-- ----------------------------
-- 商品类别（酒类、生鲜食品、休闲食品、粮油等）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_type;
CREATE TABLE tb_goods_type (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type_code varchar(255) NOT NULL COMMENT '通过下划线连接子父级id的字符（eg:1_2_3），方便检索',
  parent_id bigint(20) DEFAULT 0 COMMENT '父级id',
  name varchar(50) NOT NULL COMMENT '商品类别名称',
  status int(3) DEFAULT 0 COMMENT '商品类别状态，0：正常、1：不启用、2：删除',
  create_date datetime DEFAULT NULL COMMENT '类别创建时间',
  remark varchar(255) DEFAULT NULL COMMENT '类别描述',
  ord int(20) DEFAULT 0 COMMENT '类别排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*
删除时：下面有子类型时，不可删除
1、商品分类对商品大致的分类，方便用户查找（type_code方便检索下级商品）
2、通过改变states（总部控制下架、进而控制下面的商品下架状态）
*/

-- ----------------------------
-- 商品主表，存储商品的关键信息（保证列表查询的速度）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods;
CREATE TABLE tb_goods (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type_code varchar(255) DEFAULT NULL COMMENT '商品类别code',
  number varchar(10) DEFAULT NULL COMMENT '商品编号',
  name varchar(255) DEFAULT NULL COMMENT '商品名称',
  inventory int(10) DEFAULT NULL COMMENT '库存',
  goods_unit int(10) DEFAULT NULL COMMENT '商品单位，0：瓶、1：箱等',
  spec int(10) DEFAULT NULL COMMENT '商品规格，数量',
  spell_name varchar(255) DEFAULT NULL COMMENT '商品名称全拼 用于检索',
  fir_spell_name varchar(255) DEFAULT NULL COMMENT '商品名称首字母 用于检索',
  max_price double(10,2) DEFAULT NULL COMMENT '商品价格上限',
  min_price double(10,2) DEFAULT NULL COMMENT '商品价格下限',
  member_price double(10,2) DEFAULT NULL COMMENT '会员价',
  market_price double(10,2) DEFAULT NULL COMMENT '市场价',
  sell_price double(10,2) DEFAULT NULL COMMENT '销售价',
  version int(10) DEFAULT 1 COMMENT '商品版本号 用于商品变更',
  status int(1) DEFAULT 0 COMMENT '商品状态 0：上架、1：下架、2：删除',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  shelves_date datetime DEFAULT NULL COMMENT '上架时间',
  click_rate int(11) DEFAULT NULL COMMENT '点击量',
  sell_rate int(11) DEFAULT NULL COMMENT '销售量',
  mark_ids varchar(255) DEFAULT NULL COMMENT '商品标签Id字符串组合（eg:[1][2]） 用于检索',
  mark_info_ids varchar(255) DEFAULT NULL COMMENT '商品标签详细Id字符串组合（eg:[1][2]） 用于检索',
  ord bigint(20) DEFAULT 0 COMMENT '商品排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*

删除时：下面有子类型时，不可删除

1、商品分类对商品大致的分类，方便用户查找（type_code方便检索下级商品）

2、通过改变states（总部控制下架、进而控制下面的商品下架状态）

*/

-- ----------------------------
-- 商品主表历史，存储商品的关键信息（保证列表查询的速度）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_his;
CREATE TABLE tb_goods_his (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  goods_id bigint(20) NOT NULL COMMENT '商品Id',
  type_code varchar(255) DEFAULT NULL COMMENT '商品类别code',
  number varchar(10) DEFAULT NULL COMMENT '商品编号',
  name varchar(255) DEFAULT NULL COMMENT '商品名称',
  inventory int(10) DEFAULT NULL COMMENT '库存',
  goods_unit int(50) DEFAULT NULL COMMENT '商品单位，瓶、箱',
  spec int(10) DEFAULT NULL COMMENT '商品规格，数量',
  spell_name varchar(255) DEFAULT NULL COMMENT '商品名称全拼 用于检索',
  fir_spell_name varchar(255) DEFAULT NULL COMMENT '商品名称首字母 用于检索',
  max_price double(10,2) DEFAULT NULL COMMENT '商品价格上限',
  min_price double(10,2) DEFAULT NULL COMMENT '商品价格下限',
  member_price double(10,2) DEFAULT NULL COMMENT '会员价',
  market_price double(10,2) DEFAULT NULL COMMENT '市场价',
  sell_price double(10,2) DEFAULT NULL COMMENT '销售价',
  version int(10) DEFAULT 1 COMMENT '商品版本号 用于商品变更',
  status int(1) DEFAULT 0 COMMENT '商品状态 0：上架、1：下架、2：删除',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  shelves_date datetime DEFAULT NULL COMMENT '上架时间',
  click_rate int(11) DEFAULT NULL COMMENT '点击量',
  sell_rate int(11) DEFAULT NULL COMMENT '销售量',
  mark_ids varchar(255) DEFAULT NULL COMMENT '商品标签Id字符串组合（eg:[1][2]） 用于检索',
  mark_info_ids varchar(255) DEFAULT NULL COMMENT '商品标签详细Id字符串组合（eg:[1][2]） 用于检索',
  ord bigint(20) DEFAULT 0 COMMENT '商品排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 商品详细（有大字段，故与主表分开）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_info;
CREATE TABLE tb_goods_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  goods_id bigint(20) NOT NULL COMMENT '商品Id',
  introduce longtext COMMENT '商品介绍',
  spec_param longtext COMMENT '规格参数',
  version int(10) DEFAULT 1 COMMENT '商品版本号 用于商品变更',
  data_type int(2) DEFAULT 0 COMMENT '数据类型，0：商品详细、1：定价商品详细',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 商品标签（品牌、产地、品类、口感等）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_mark;
CREATE TABLE tb_goods_mark (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type_code varchar(255) NOT NULL COMMENT '商品类别code',
  name varchar(255) DEFAULT NULL COMMENT '标签名',
  spe_name varchar(255) DEFAULT NULL COMMENT '标签名拼音',
  fir_spe_name varchar(255) DEFAULT NULL COMMENT '标签名拼音首字母',
  status int(2) DEFAULT NULL COMMENT '标签状态、0：正常、1：不启用、2：删除',
  create_date datetime DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  ord bigint(20) DEFAULT 0 COMMENT '标签排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 商品标签详细（品牌、产地、品类、口感等）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_mark_info;
CREATE TABLE tb_goods_mark_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  mark_id bigint(10) NOT NULL COMMENT '标签Id',
  name varchar(255) DEFAULT NULL COMMENT '标签详细名称',
  spell_name varchar(255) DEFAULT NULL COMMENT '标签详细名称全拼',
  fir_spell_name varchar(255) DEFAULT NULL COMMENT '标签详细名称全拼首字母',
  status int(2) DEFAULT NULL COMMENT '标签状态、0：正常、1：不启用、2：删除',
  create_date datetime DEFAULT NULL,
  remark varchar(255) DEFAULT NULL COMMENT '标签详细描述',
  ord bigint(20) DEFAULT 0 COMMENT '标签详细排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 商品标签详细关联（逻辑暂时不用）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_mark_detail;
CREATE TABLE tb_goods_mark_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  goods_id bigint(20) DEFAULT NULL,
  type_code varchar(255) NOT NULL COMMENT '商品类别code',
  mark_id bigint(20) DEFAULT NULL,
  mark_info_id bigint(20) DEFAULT NULL,
  version int(10) DEFAULT 1 COMMENT '商品版本号 用于商品变更',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 商品标签详细关联（逻辑暂时不用）
-- ----------------------------
DROP TABLE IF EXISTS tb_file_data;
CREATE TABLE tb_file_data (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  file_name varchar(255) DEFAULT NULL COMMENT '文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  if_delete int(2) NOT NULL COMMENT '是否删除，0：正常、1：已删除',
  file_type int(2) NOT NULL COMMENT '文件类型：0：logo,1:快照',
  data_id bigint(20) DEFAULT NULL COMMENT '对应主表Id',
  data_version int(10) DEFAULT NULL COMMENT '主表信息版本号',
  create_date datetime DEFAULT NULL COMMENT '上传时间',
  data_type int(2) DEFAULT NULL COMMENT '数据类型，0:商品、1：定价商品',
  ord int(10) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 商品定价表（各个代理商进行操作的）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_price;
CREATE TABLE tb_goods_price (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
  goods_nick_name varchar(255) DEFAULT NULL COMMENT '商品昵称',
  goods_version int(10) DEFAULT 1 COMMENT '商品版本 用于查询历史',
  goods_status int(1) DEFAULT 0 COMMENT '状态：0：正常、1：下架（用于平台控制商品上下架）',
  zone_code varchar(9) DEFAULT NULL COMMENT '地区编码',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位ID',
  unit_type int(2) DEFAULT '0' COMMENT '单位类型：0：区域代理（区域唯一）、1：采购商（区域不限）、2：供应商',
  member_price double(10,2) DEFAULT NULL COMMENT '会员价',
  market_price double(10,2) DEFAULT NULL COMMENT '市场价',
  sell_price double(10,2) DEFAULT NULL COMMENT '销售价',
  status int(1) DEFAULT 0 COMMENT '状态：0：正常、1：下架、2：删除',
  version int(10) DEFAULT 1 COMMENT '版本 用于查询历史',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  shelves_date datetime DEFAULT NULL COMMENT '上架时间',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  inventory int(11) DEFAULT NULL COMMENT '库存',
  if_activity int(1) DEFAULT NULL COMMENT '是否参与活动 0:否、1：是',
  activity_price double DEFAULT NULL COMMENT '活动价格',
  activity_goods_id bigint(20) DEFAULT NULL COMMENT '活动ID',
  activity_type int(4) DEFAULT 0 COMMENT '活动类别 ',
  is_gift int(2) DEFAULT 0 COMMENT '是否为赠品',
  is_relation int(2) DEFAULT 0 COMMENT '是否为赠品',
  ord bigint(20) DEFAULT 0 COMMENT '商品排序',
  weight bigint(20) DEFAULT 0 COMMENT '商品权重',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '商品定价表';


-- ----------------------------
-- 商品定价表历史（各个代理商进行操作的）
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_price_his;
CREATE TABLE tb_goods_price_his (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  goods_price_id bigint(20) DEFAULT NULL,
  goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
  goods_nick_name varchar(255) DEFAULT NULL COMMENT '商品昵称',
  goods_version int(10) DEFAULT 1 COMMENT '商品版本 用于查询历史',
  goods_status int(1) DEFAULT 0 COMMENT '状态：0：正常、1：下架（用于平台控制商品上下架）',
  zone_code varchar(9) DEFAULT NULL COMMENT '地区编码',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位ID',
  unit_type int(2) DEFAULT '0' COMMENT '单位类型：0：区域代理（区域唯一）、1：采购商（区域不限）、2：供应商',
  member_price double(10,2) DEFAULT NULL COMMENT '会员价',
  market_price double(10,2) DEFAULT NULL COMMENT '市场价',
  sell_price double(10,2) DEFAULT NULL COMMENT '销售价',
  status int(1) DEFAULT 0 COMMENT '状态：0：正常、1：下架、2：删除',
  version int(10) DEFAULT 1 COMMENT '版本 用于查询历史',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  shelves_date datetime DEFAULT NULL COMMENT '上架时间',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  inventory int(11) DEFAULT NULL COMMENT '库存',
  if_activity int(1) DEFAULT NULL COMMENT '是否参与活动 0:否、1：是',
  activity_price double DEFAULT NULL COMMENT '活动价格',
  activity_goods_id bigint(20) DEFAULT NULL COMMENT '活动ID',
  activity_type int(4) DEFAULT 0 COMMENT '活动类别 ',
  is_gift int(2) DEFAULT 0 COMMENT '是否为赠品',
  is_relation int(2) DEFAULT 0 COMMENT '是否为赠品',
  ord bigint(20) DEFAULT 0 COMMENT '商品排序',
  weight bigint(20) DEFAULT 0 COMMENT '商品权重',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '商品定价表历史';


-- ----------------------------
-- 商品类型Logo
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_type_logo;
CREATE TABLE tb_goods_type_logo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type_id bigint(20) DEFAULT NULL COMMENT '类型Id',
  type_code varchar(255) DEFAULT NULL COMMENT '类型编码',
  logo_name varchar(255) DEFAULT NULL COMMENT 'Logo名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  ios_file_name varchar(255) DEFAULT NULL COMMENT 'ios图片存储名称',
  android_file_name varchar(255) DEFAULT NULL COMMENT 'android图片存储名称',
  wechat_file_name varchar(255) DEFAULT NULL COMMENT '微信图片存储名称',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间',
  end_date datetime DEFAULT NULL COMMENT '截止时间',
  if_default int(2) DEFAULT '0' COMMENT '是否为默认Logo，0：否、1：是',
  status int(2) DEFAULT '0' COMMENT '0：正常 1：不启用 2：删除',
  memo varchar(255) DEFAULT NULL COMMENT '主题备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品类型Logo';
