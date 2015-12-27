-- ----------------------------
-- 推广活动
-- ----------------------------
DROP TABLE IF EXISTS tb_promote_activity;
CREATE TABLE tb_promote_activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位id',
  unit_type int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  zone_code varchar(50) DEFAULT NULL COMMENT '区域编码',
  title varchar(255) DEFAULT NULL COMMENT '推广标题',
  claim_limit int(11) DEFAULT NULL COMMENT '会员认领数量上限 （0为不限制）',
  claim_num int(11) DEFAULT '0' COMMENT '会员认领数量',
  file_name varchar(255) DEFAULT NULL COMMENT '头标文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '头标文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '头标文件存储根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '头标文件存储相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '头标文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '头标文件后缀（扩展名）',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  start_date datetime DEFAULT NULL COMMENT '起始时间',
  end_date datetime DEFAULT NULL COMMENT '截止时间',
  description varchar(500)  DEFAULT NULL COMMENT '描述',
  memo varchar(255)  DEFAULT NULL COMMENT '备注',
  promote_status int(2) DEFAULT '0' COMMENT '状态（-1：删除、0:正常、1：未启用）',
  ord int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '推广活动';

/*
0:优惠劵
bigint_1:优惠劵id
str_1:优惠劵编码
str_2:优惠劵名称

1:商品
bigint_1:商品定价id
int_1:商品数量
int_2:商品酒库礼包送出后的有效天数（超过后失效）
str_2:商品名称

2:达人币
double_1:达人币数量

3:达人豆
double_1:达人豆数量

4:储值
double_1:金额

*/

-- ----------------------------
-- 推广活动奖励
-- ----------------------------
DROP TABLE IF EXISTS tb_promote_activity_reward_rt;
CREATE TABLE tb_promote_activity_reward_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  promote_id bigint(20) NOT NULL COMMENT '推广活动id',
  reward_target int(2) DEFAULT NULL COMMENT '0、认领人、1：分享人',
  data_type int(2) DEFAULT NULL COMMENT '0：优惠劵、1：商品、2：达人币、3：达人豆、4：储值',
  bigint_1 bigint(20) DEFAULT NULL COMMENT '扩展bigint数据1',
  bigint_2 bigint(20) DEFAULT NULL COMMENT '扩展bigint数据2',
  date_1 datetime DEFAULT NULL COMMENT '扩展时间数据1',
  date_2 datetime DEFAULT NULL COMMENT '扩展时间数据2',
  int_1 int(11) DEFAULT NULL COMMENT '扩展int数据1',
  int_2 int(11) DEFAULT NULL COMMENT '扩展int数据2',
  double_1 double(10,2) DEFAULT NULL COMMENT '扩展浮点数据1',
  double_2 double(10,2) DEFAULT NULL COMMENT '扩展浮点数据2',
  str_1 varchar(255) DEFAULT NULL COMMENT '扩展字符数据1',
  str_2 varchar(255) DEFAULT NULL COMMENT '扩展字符数据2',
  str_3 varchar(255) DEFAULT NULL COMMENT '扩展字符数据3',
  str_4 varchar(500) DEFAULT NULL COMMENT '扩展字符数据4',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '推广活动奖励';



-- 推广活动详细表
DROP TABLE IF EXISTS tb_promote_activity_detail;
CREATE TABLE tb_promote_activity_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  promote_id bigint(20) DEFAULT NULL COMMENT '推广活动id',
  promote_rule longtext  DEFAULT NULL COMMENT '活动细则',
  text_1 longtext  DEFAULT NULL COMMENT '其他',
  text_2 longtext  DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '推广活动详细表（存放大字段）';



-- 推广活动认领会员关联表
DROP TABLE IF EXISTS tb_promote_activity_user_rt;
CREATE TABLE tb_promote_activity_user_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  promote_id bigint(20) DEFAULT NULL COMMENT '推广活动id',
  share_account varchar(255) DEFAULT NULL COMMENT '分享会员账号',
  claim_account varchar(255) DEFAULT NULL COMMENT '认领会员账号',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  str_1 varchar(255) DEFAULT NULL COMMENT '扩展字符数据1',
  str_2 varchar(255) DEFAULT NULL COMMENT '扩展字符数据2',
  str_3 varchar(255) DEFAULT NULL COMMENT '扩展字符数据3',
  str_4 varchar(500) DEFAULT NULL COMMENT '扩展字符数据4',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '推广活动认领会员关联表';


-- 分享链接维护
DROP TABLE IF EXISTS tb_share_href;
CREATE TABLE tb_share_href (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位id',
  unit_type int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  zone_code varchar(50) DEFAULT NULL COMMENT '区域编码',
  name varchar(255) DEFAULT NULL COMMENT '链接名称',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  title varchar(255) DEFAULT NULL COMMENT '链接标题',
  description varchar(500) DEFAULT NULL COMMENT '链接描述',
  file_name varchar(255) DEFAULT NULL COMMENT '头标文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '头标文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '头标根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '头标相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '头标文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '头标文件后缀（扩展名）',
  data_type varchar(50) DEFAULT NULL COMMENT '链接类型  register ：注册、 promote ：推广活动',
  str_1 varchar(255) DEFAULT NULL COMMENT '扩展字符数据1',
  str_2 varchar(255) DEFAULT NULL COMMENT '扩展字符数据2',
  str_3 varchar(255) DEFAULT NULL COMMENT '扩展字符数据3',
  ord int(11) DEFAULT NULL COMMENT '排序',
  href_status int(2) DEFAULT '0' COMMENT '状态（-1：删除、0:正常、1：未启用）',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '分享链接维护';

-- 分享链接数据（推广活动）关联
DROP TABLE IF EXISTS tb_share_href_data_rt;
CREATE TABLE tb_share_href_data_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  href_id bigint(20) NOT NULL COMMENT '链接id',
  data_id bigint(20) NOT NULL COMMENT '数据id',
  ord int(11) DEFAULT NULL COMMENT '排序',
  str_1 varchar(50) DEFAULT NULL COMMENT '（promote:推广活动、vote:投票）',
  str_2 varchar(255) DEFAULT NULL COMMENT '扩展字符数据2',
  str_3 varchar(255) DEFAULT NULL COMMENT '扩展字符数据3',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '分享链接数据（推广活动）关联';







/*
推广活动需求：
1、会员通过分享好友功能，分享给自己的好友一些链接，通过点击这些链接，输入手机号码，可以领取（达人币、达人豆、优惠劵、商品），通过这种方式推广H2Yapp，促进会员注册量，增加会员粘度



设计要求：
1、推广活动按单位区分
2、领取优惠信息处理
	达人币、达人豆：直接进入用户收益
	优惠劵：直接进入我的优惠劵
	商品：以礼包（有失效时间，过期不可领取）的形式，发送给用户，用户需要进行领取，方可进入会员酒库
3、单个推广活动、优惠劵、商品是否为一对一
*/















