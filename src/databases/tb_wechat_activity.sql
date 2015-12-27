-- ----------------------------
-- 微活动
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_activity;
CREATE TABLE tb_wechat_activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  user_id bigint(20) NOT NULL COMMENT '创建用户Id',
  name varchar(255) DEFAULT NULL COMMENT '活动名称',
  description varchar(500) DEFAULT NULL COMMENT '描述',
  keyword varchar(255) DEFAULT NULL COMMENT '关键字，参与活动方式之一（关键字检索）',
  file_name varchar(255) DEFAULT NULL COMMENT '消息图片文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '活动开始时间',
  end_date datetime DEFAULT NULL COMMENT '活动结束时间',
  prize_info int(10) DEFAULT '0' COMMENT '显示奖品信息  0：名称  1：名称+总数  2：名称+总数+剩余数',
  limit_number int(10) DEFAULT '0' COMMENT '抽奖次数 0：不限制 ',
  limit_flag int(10) DEFAULT '0' COMMENT '抽奖次数限制标识：0：每天 1：总共',
  if_share_reward int(10) DEFAULT '0' COMMENT '分享是否分享奖励抽奖次数，0：否 1：奖励',
  reward_number int(10) DEFAULT '0' COMMENT '分享奖励次数 一般为1次',
  activity_status int(10) DEFAULT '0' COMMENT '状态：-1：删除  0：正常  1：暂停 ',
  if_set_date int(10) DEFAULT '0' COMMENT '是否设置时间 0：立即开始  1：定时开始 ',
  activity_type varchar(50) DEFAULT NULL COMMENT '活动类型：wheel:大转盘 card:刮刮卡 等 ',
  use_val int(10) DEFAULT '0' COMMENT '使用的达人豆的数量 ',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='微信活动（大转盘、刮刮卡等）';

-- ----------------------------
-- 微活动兑奖历史
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_activity_his;
CREATE TABLE tb_wechat_activity_his (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  activity_id bigint(20) NOT NULL COMMENT '活动Id',
  prize_id bigint(20) DEFAULT NULL COMMENT '奖品',
  member_id bigint(20) DEFAULT NULL COMMENT '会员Id',
  open_id varchar(255) DEFAULT NULL COMMENT '粉丝号',
  create_date datetime DEFAULT NULL COMMENT '抽奖时间',
  exchange_date datetime DEFAULT NULL COMMENT '兑奖时间',
  prizer_mobile varchar(255) DEFAULT NULL COMMENT '抽奖人电话',
  prizer_name varchar(255) DEFAULT NULL COMMENT '抽奖人姓名',
  prizer_address varchar(255) DEFAULT NULL COMMENT '地址',
  if_exchange int(10) DEFAULT '0' COMMENT '是否已经兑换奖品 0：未兑换  1：已兑换 ',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  prize_name varchar(255) DEFAULT NULL COMMENT '奖品名称',
  prize_number bigint(10) DEFAULT NULL COMMENT '单个奖品数量',
  prize_rate double(10,10) DEFAULT NULL COMMENT '中奖几率 百分比最大值为100',
  prize_level varchar(255) DEFAULT NULL COMMENT '奖品级别  一等奖等',
  prize_type varchar(50) DEFAULT NULL COMMENT '奖品类型（darenbi:达人币、darendou:达人豆、goods:商品、qita:其他）',
  goods_price_id bigint(20) NOT NULL COMMENT '商品定价Id，当奖品为商品时，用到',
  goods_price_version int(10) NOT NULL COMMENT '商品定价版本号',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='微信活动用户参与历史';


-- ----------------------------
-- 微活动奖品
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_activity_prize;
CREATE TABLE tb_wechat_activity_prize (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  activity_id bigint(20) NOT NULL COMMENT '大转盘Id',
  prize_name varchar(255) DEFAULT NULL COMMENT '奖品名称',
  prize_count bigint(10) DEFAULT NULL COMMENT '奖品总数数量',
  prize_number bigint(10) DEFAULT NULL COMMENT '单个奖品数量',
  prize_rate double(10,10) DEFAULT NULL COMMENT '中奖几率 百分比最大值为100',
  prize_level varchar(255) DEFAULT NULL COMMENT '奖品级别  一等奖等',
  prize_type varchar(50) DEFAULT NULL COMMENT '奖品类型（darenbi:达人币、darendou:达人豆、goods:商品、qita:其他）',
  goods_price_id bigint(20) NOT NULL COMMENT '商品定价Id',
  ord int(10) DEFAULT NULL COMMENT '排序',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='微信活动奖项';
