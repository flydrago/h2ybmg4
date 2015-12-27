-- ----------------------------
-- 支付方式
-- ----------------------------
DROP TABLE IF EXISTS tb_pay_mode;
CREATE TABLE tb_pay_mode (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL COMMENT '名称',
  pay_type int(2) DEFAULT '1' COMMENT '1：支付宝',
  file_name varchar(255) DEFAULT NULL COMMENT 'Logo文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT 'Logo文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT 'Logo文件存储根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT 'Logo文件存储相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT 'Logo文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT 'Logo文件后缀（扩展名）',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  memo varchar(255)  DEFAULT NULL COMMENT '备注',
  mode_status int(2) DEFAULT '0' COMMENT '状态（-1：删除、0:正常、1：未启用）',
  ord int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '支付方式';

-- ----------------------------
-- 支付方式单位关联
-- ----------------------------
DROP TABLE IF EXISTS tb_pay_mode_unit_rt;
CREATE TABLE tb_pay_mode_unit_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位id',
  unit_type int(2) DEFAULT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）',
  zone_code varchar(50) DEFAULT NULL COMMENT '区域编码',
  mode_id bigint(20) DEFAULT NULL COMMENT '支付方式id',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '支付方式单位关联';