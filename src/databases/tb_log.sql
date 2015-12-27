-- ----------------------------
-- 代驾日志 tb_agent_driver_log
-- ----------------------------
DROP TABLE IF EXISTS tb_agent_driver_log;
CREATE TABLE tb_agent_driver_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  member_id bigint(20) DEFAULT NULL COMMENT '会员Id',
  account varchar(20) DEFAULT NULL COMMENT '会员账户',
  real_name varchar(50) DEFAULT NULL COMMENT '会员真实名',
  nick_name varchar(50) DEFAULT NULL COMMENT '会员真实名',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  client_type varchar(10) DEFAULT NULL COMMENT '客户端类型：android:安卓、ios:苹果客户端',
  version varchar(20) DEFAULT NULL COMMENT '版本号',
  client_tel varchar(15) DEFAULT NULL COMMENT '来电电话',
  agent_id varchar(50) DEFAULT NULL COMMENT '固定值',
  start_address varchar(500) DEFAULT NULL COMMENT '开始地址',
  end_address varchar(500) DEFAULT NULL COMMENT '结束地址', 
  yu_yue_time varchar(10) DEFAULT NULL COMMENT '固定值',
  driver_count int(10) DEFAULT NULL COMMENT '所需司机数量',
  contact_name varchar(20) DEFAULT NULL COMMENT '联系人',
  contact_tel varchar(15) DEFAULT NULL COMMENT '联系电话',
  gps_longitude double DEFAULT NULL COMMENT '经度',
  gps_latitude double DEFAULT NULL COMMENT '纬度',
  accuracy int(10)  DEFAULT NULL COMMENT '必填值',
  result varchar(50) DEFAULT NULL COMMENT '返回结果',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='代驾日志';



