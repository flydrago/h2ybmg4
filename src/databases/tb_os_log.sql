-- os服务日志
DROP TABLE IF EXISTS tb_os_log;
CREATE TABLE tb_os_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  member_id bigint(20) DEFAULT NULL COMMENT '会员ID',
  account varchar(20) DEFAULT NULL COMMENT '会员账号',
  zone_code varchar(50) DEFAULT NULL COMMENT '区域编码',
  post_url varchar(500)  DEFAULT NULL COMMENT '访问url',
  post_ip varchar(50)  DEFAULT NULL COMMENT '开始可以不用',
  post_hd1 varchar(255)  DEFAULT NULL COMMENT '访问app硬件信息1 开始可以不用',
  post_hd2 varchar(255)  DEFAULT NULL COMMENT '访问app硬件信息2 开始可以不用',
  post_mark varchar(50)  DEFAULT NULL COMMENT '访问标识（用于区分是同一次的访问）',
  slock varchar(50)  DEFAULT NULL COMMENT '配合skey使用进行安全验证',
  skey varchar(50)  DEFAULT NULL COMMENT '配合slock使用',
  sid varchar(50)  DEFAULT NULL COMMENT '请求发出后的唯一标示',
  os varchar(50)  DEFAULT NULL COMMENT '操作系统',
  osv varchar(50) DEFAULT NULL COMMENT '系统操作类型',
  appv varchar(20)  DEFAULT NULL COMMENT 'app版本号',
  post_data varchar(5000)  DEFAULT NULL COMMENT '访问业务参数',
  result_flag varchar(2)  DEFAULT NULL COMMENT '返回结果标示',
  result_msg varchar(1000)  DEFAULT NULL COMMENT '返回结果信息',
  result_data varchar(1000)  DEFAULT NULL COMMENT '返回结果数据（暂时可以不用）',
  start_date datetime DEFAULT NULL COMMENT '调用接口开始时间',
  end_date datetime DEFAULT NULL COMMENT '调用接口结束时间',
  memo varchar(5000)  DEFAULT NULL COMMENT '备注',
  reserve_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reserve_2 varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '服务日志';


-- os服务日志详细
DROP TABLE IF EXISTS tb_os_log_info;
CREATE TABLE tb_os_log_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  post_mark varchar(50)  DEFAULT NULL COMMENT '访问标识（用于区分是同一次的访问）',
  op_flag varchar(50)  DEFAULT NULL COMMENT '操作标识',
  op_desc varchar(255)  DEFAULT NULL COMMENT '操作描述',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  result_flag varchar(2)  DEFAULT NULL COMMENT '返回结果标示',
  result_msg varchar(1000)  DEFAULT NULL COMMENT '返回结果信息',
  data_1 varchar(255) DEFAULT NULL COMMENT '数据1',
  data_2 varchar(255) DEFAULT NULL COMMENT '数据2',
  data_3 varchar(255) DEFAULT NULL COMMENT '数据3',
  data_4 varchar(255) DEFAULT NULL COMMENT '数据4',
  data_5 varchar(500) DEFAULT NULL COMMENT '数据5',
  data_6 varchar(500) DEFAULT NULL COMMENT '数据6',
  data_7 varchar(500) DEFAULT NULL COMMENT '数据7',
  data_8 varchar(500) DEFAULT NULL COMMENT '数据8',
  data_9 varchar(1000) DEFAULT NULL COMMENT '数据9',
  data_10 varchar(1000) DEFAULT NULL COMMENT '数据10',
  data_11 varchar(1000) DEFAULT NULL COMMENT '数据11',
  data_12 varchar(1000) DEFAULT NULL COMMENT '数据12',
  memo varchar(1000)  DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '服务日志详细';


