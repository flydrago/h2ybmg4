-- ----------------------------
-- 广告栏位
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_column;
CREATE TABLE tb_advert_column (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  column_name varchar(255) DEFAULT NULL COMMENT '广告栏名称',
  column_type int(2) DEFAULT '0' COMMENT '0：平台栏位、1：代理商栏位',
  ord int(11) DEFAULT '0' COMMENT '广告栏排序',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  memo varchar(50) DEFAULT NULL COMMENT '备注',
  status int(2) DEFAULT '0' COMMENT '-1：已删除、0：正常、1：未启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '广告栏位';


-- ----------------------------
-- 广告栏位单位关联表
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_column_unit_rt;
CREATE TABLE tb_advert_column_unit_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  column_id bigint(20) NOT NULL COMMENT '栏位Id',
  start_date datetime DEFAULT NULL COMMENT '开始时间 用于根据时间段变换广告',
  end_date datetime DEFAULT NULL COMMENT '结束时间 用于根据时间段变换广告',
  status int(2) DEFAULT '1' COMMENT '-1：已删除、0：正常、1：未启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '广告栏位单位关联表';


-- ----------------------------
-- 广告主题
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_subject;
CREATE TABLE tb_advert_subject (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  user_id bigint(20) NOT NULL COMMENT '创建人',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '创建时间',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  ios_file_name varchar(255) DEFAULT NULL COMMENT 'ios图片存储名称',
  android_file_name varchar(255) DEFAULT NULL COMMENT 'android图片存储路径',
  wechat_file_name varchar(255) DEFAULT NULL COMMENT '微信图片存储路径',
  subject_name varchar(255) DEFAULT NULL COMMENT '主题名称',
  subject_type int(2) DEFAULT NULL COMMENT '0：商品列表，1：详细，2:宣传页面',
  subject_content longtext COMMENT '主题html内容，subject_type为2时，存储html富文本内容',
  memo varchar(255) DEFAULT NULL COMMENT '主题备注',
  status int(2) DEFAULT '0' COMMENT '-1：删除、0：正常、1：不启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '广告主题';


-- ----------------------------
-- 广告主题商品关联
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_subject_goods_rt;
CREATE TABLE tb_advert_subject_goods_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  subject_id bigint(20) DEFAULT NULL COMMENT '主题Id',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品id，逻辑上面不使用',
  goods_price_id bigint(20) DEFAULT NULL COMMENT '商品定价id',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  ord int(10) DEFAULT '0' COMMENT '商品列表排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '广告主题商品关联';


-- ----------------------------
-- 广告栏位主题关联
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_column_subject_rt;
CREATE TABLE tb_advert_column_subject_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  column_id bigint(20) DEFAULT NULL COMMENT '栏位Id',
  user_id bigint(20) DEFAULT NULL COMMENT '创建用户Id',
  subject_id bigint(20) DEFAULT NULL COMMENT '栏位Id',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '创建时间',
  end_date datetime DEFAULT NULL COMMENT '创建时间',
  repeat_start varchar(20) DEFAULT NULL COMMENT '暂时用于天重复显示的情况',
  repeat_end varchar(20) DEFAULT NULL COMMENT '暂时用于天重复显示的情况',
  repeat_type varchar(20) DEFAULT NULL COMMENT '重复类型（暂时不用）',
  memo varchar(225) DEFAULT NULL COMMENT '备注',
  is_default int(2) DEFAULT NULL COMMENT '是否为默认 0：否、1：是',
  status int(2) DEFAULT '0' COMMENT '-1：删除、0：正常',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '广告栏位主题关联';




-- ----------------------------
-- 广告主题信息关联
-- ----------------------------
DROP TABLE IF EXISTS `tb_advert_subject_info_rt`;
CREATE TABLE `tb_advert_subject_info_rt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL COMMENT '主题Id',
  `data_type` varchar(50) DEFAULT NULL COMMENT '类型 coupons:优惠劵、activity：促销活动',
  `data_1` varchar(20) DEFAULT NULL COMMENT '数据1',
  `data_2` varchar(50) DEFAULT NULL COMMENT '数据2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '数据3',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(2) DEFAULT '0' COMMENT '-1：已删除、0：正常',
  `ord` int(10) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='广告主题信息关联';




