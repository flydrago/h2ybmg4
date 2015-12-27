-- ----------------------------
-- 促销活动
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity;
CREATE TABLE tb_common_activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  user_id bigint(20) DEFAULT NULL COMMENT '创建用户Id',
  title varchar(255) DEFAULT NULL COMMENT '标题',
  memo varchar(500) DEFAULT NULL COMMENT '描述',
  data_type int(2) DEFAULT '0' COMMENT '数据类型 0：商品列表，1：专题',
  activity_type varchar(50) DEFAULT NULL COMMENT '活动类型，index:主页等',
  ord int(10) DEFAULT '0' COMMENT '活动排序',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  status int(2) DEFAULT '0' COMMENT '-1：已删除、0：正常、1：未启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='活动主表（定义活动类型）';

-- ----------------------------
-- 活动或主题与商品的关联
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity_goods_rt;
CREATE TABLE tb_common_activity_goods_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  data_id bigint(20) NOT NULL COMMENT 'data_type为0 活动Id，1 主题Id',
  data_type int(2) NOT NULL COMMENT '数据类型 0：活动、1：主题',
  goods_id bigint(20) NOT NULL COMMENT '商品Id',
  goods_price_id bigint(20) NOT NULL COMMENT '商品定价Id',
  activity_price double(10,2) DEFAULT NULL COMMENT '活动价格',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间',
  end_date datetime DEFAULT NULL COMMENT '结束时间',
  limit_number int(10) DEFAULT '0' COMMENT '商品上限：0：不做限制',
  sell_number int(10) DEFAULT '0' COMMENT '已售商品数量',
  reward_type int(10) DEFAULT '0' COMMENT '奖励类型、0：不奖励、1：达人豆、2：达人币',
  reward_number int(10) DEFAULT '0' COMMENT '奖励数量',
  memo varchar(255) DEFAULT NULL COMMENT '描述',
  goods_level int(10) DEFAULT '0' COMMENT '级别：与排序字段结合使用，进而控制优先显示顺序',
  ord bigint(20) DEFAULT '0' COMMENT '商品排序',
  status int(2) DEFAULT '0' COMMENT '-1：删除、0：正常、1：不启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='活动或主题与商品的关联';

-- ----------------------------
-- 活动商品关联历史
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity_goods_rt_his;
CREATE TABLE tb_common_activity_goods_rt_his (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_goods_id bigint(20) DEFAULT NULL COMMENT '关联表Id',
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  data_id bigint(20) NOT NULL COMMENT 'data_type为0 活动Id，1 主题Id',
  data_type int(2) NOT NULL COMMENT '数据类型 0：活动、1：主题',
  goods_id bigint(20) NOT NULL COMMENT '商品Id',
  goods_price_id bigint(20) NOT NULL COMMENT '商品定价Id',
  activity_price double(10,2) DEFAULT NULL COMMENT '活动价格',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间',
  end_date datetime DEFAULT NULL COMMENT '结束时间',
  op_date datetime DEFAULT NULL COMMENT '操作时间',
  limit_number int(10) DEFAULT '0' COMMENT '商品上限：0：不做限制',
  sell_number int(10) DEFAULT '0' COMMENT '已售商品数量',
  reward_type int(10) DEFAULT '0' COMMENT '奖励类型、0：不奖励、1：达人豆、2：达人币',
  reward_number int(10) DEFAULT '0' COMMENT '奖励数量',
  memo varchar(255) DEFAULT NULL COMMENT '描述',
  goods_level int(10) DEFAULT '0' COMMENT '级别：与排序字段结合使用，进而控制优先显示顺序',
  ord bigint(20) DEFAULT '0' COMMENT '商品排序',
  status int(2) DEFAULT '0' COMMENT '-1：删除、0：正常、1：不启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='活动与商品关联历史表';

-- ----------------------------
-- 活动图片主题关联
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity_subject_rt;
CREATE TABLE tb_common_activity_subject_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_id bigint(20) NOT NULL COMMENT '活动Id',
  subject_id bigint(20) NOT NULL COMMENT '主题Id',
  ord int(10) DEFAULT '0' COMMENT '活动中主题的排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='活动主题关联表，关联活动下面的主题';

-- ----------------------------
-- 活动图片主题
-- ----------------------------
DROP TABLE IF EXISTS tb_common_subject;
CREATE TABLE tb_common_subject (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  subject_name varchar(255) DEFAULT NULL COMMENT '主题名称',
  subject_type int(2) DEFAULT NULL COMMENT '主题类型：0：商品列表、1：商品详细、2：商品宣传页面',
  subject_content longtext COMMENT '主题类型为1时，存储活动宣传页面内容',
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  ios_file_name varchar(255) DEFAULT NULL COMMENT 'ios图片存储名称',
  android_file_name varchar(255) DEFAULT NULL COMMENT 'android图片存储名称',
  wechat_file_name varchar(255) DEFAULT NULL COMMENT '微信图片存储名称',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  status int(2) DEFAULT '0' COMMENT '-1：删除0：正常 1：不启用',
  memo varchar(255) DEFAULT NULL COMMENT '主题备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='活动图片主题（红酒专区、白酒专区等）';
