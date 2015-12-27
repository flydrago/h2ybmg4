-- ----------------------------
-- Table structure for tb_delivery_user
-- ----------------------------
-- ---------------
-- 配送员表
-- ---------------
DROP TABLE IF EXISTS tb_delivery_user;
CREATE TABLE tb_delivery_user (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  account varchar(20) DEFAULT NULL COMMENT '账号',
  unit_id bigint(10) DEFAULT NULL COMMENT '单位id',
  dept_id bigint(10) DEFAULT NULL COMMENT '部门id',
  shop_id bigint(10) DEFAULT NULL COMMENT '门店id',
  name varchar(20) DEFAULT NULL COMMENT '配送员名称',
  password varchar(255) DEFAULT NULL COMMENT '登录密码',
  mobile varchar(255) DEFAULT NULL COMMENT '手机号码',
  create_date datetime DEFAULT NULL,
  latitude double DEFAULT NULL COMMENT '地址纬度',
  longitude double DEFAULT NULL COMMENT '地址经度',
  address varchar(255) DEFAULT NULL COMMENT '地址名称',
  update_date datetime DEFAULT NULL,
  check_status int(2) DEFAULT NULL COMMENT '审核状态 0：待审核、1：审核通过、2：停用、-1：审核不通过',
  work_status int(2) DEFAULT NULL COMMENT '工作状态 0：上班 、1：下班、2：请假',
  login_type int(2) DEFAULT NULL COMMENT '登录类型 1：安卓、2：苹果',
  register_type int(2) DEFAULT NULL COMMENT '注册类型  1：安卓、2：苹果',
  work_type int(2) DEFAULT NULL COMMENT '工作类型  1：内部员工、2：兼职等',
  ios_push_code varchar(255) DEFAULT NULL,
  android_push_code varchar(255) DEFAULT NULL,
  memo varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ---------------
-- 配送员定位轨迹
-- ---------------
DROP TABLE IF EXISTS tb_delivery_location;
CREATE TABLE tb_delivery_location (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  delivery_id bigint(10) DEFAULT NULL COMMENT '配送员id',
  delivery_name varchar(255) DEFAULT NULL COMMENT '配送员名称',
  delivery_phone varchar(20) DEFAULT NULL COMMENT '配送员手机号',
  latitude decimal(20,17) DEFAULT NULL COMMENT '纬度',
  longitude decimal(20,17) DEFAULT NULL COMMENT '经度',
  address varchar(255) DEFAULT NULL COMMENT '地址信息',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  memo varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '配送员定位轨迹';


-- 配送员与配送门店的关联表
DROP TABLE IF EXISTS tb_delivery_user_shop_rt;
CREATE TABLE tb_delivery_user_shop_rt (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  account varchar(20) DEFAULT NULL COMMENT '账号',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位id',
  shop_id bigint(20) DEFAULT NULL COMMENT '门店id',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  str_1 varchar(255) DEFAULT NULL COMMENT '字符扩展数据1',
  str_2 varchar(255) DEFAULT NULL COMMENT '字符扩展数据2',
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '配送员与配送门店的关联表';