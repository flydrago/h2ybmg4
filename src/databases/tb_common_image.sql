DROP TABLE IF EXISTS tb_common_image;
CREATE TABLE tb_common_image (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  type_code varchar(50) DEFAULT NULL COMMENT '类型编码',
  logo_name varchar(255) DEFAULT NULL COMMENT '图片名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  ios_file_name varchar(255) DEFAULT NULL COMMENT 'ios图片存储名称',
  android_file_name varchar(255) DEFAULT NULL COMMENT 'android图片存储名称',
  wechat_file_name varchar(255) DEFAULT NULL COMMENT '微信图片存储名称',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_date datetime DEFAULT NULL COMMENT '更新时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间',
  end_date datetime DEFAULT NULL COMMENT '截止时间',
  is_default int(2) DEFAULT '0' COMMENT '是否为默认Logo，0：否、1：是',
  status int(2) DEFAULT '0' COMMENT '0：正常 1：不启用 -1：删除',
  memo varchar(255) DEFAULT NULL COMMENT '主题备注',
  ord bigint(20) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='图片维护（启动加载图片维护等）';