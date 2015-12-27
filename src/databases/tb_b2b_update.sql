alter table tb_sys_units add unit_type int(2) NOT NULL COMMENT '单位类型：0：区域代理（区域唯一）、1：采购商（区域不限）、2：供应商';

-- ----------------------------
-- 单位详细（目前主要是主要维护营业执照图片，考虑以后扩展）
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_units_info;
CREATE TABLE tb_sys_units_info (
  id bigint(20) NOT NULL ,
  unit_id varchar(255) NOT NULL COMMENT '单位Id',
  file_name varchar(255) DEFAULT NULL COMMENT '营业执照文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 单位文件上传
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_units_file;
CREATE TABLE tb_sys_units_file (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  file_name varchar(255) DEFAULT NULL COMMENT '营业执照文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  file_type int(2) NOT NULL COMMENT '文件类型，0：营业执照、1：身份证等',
  if_delete int(2) NOT NULL COMMENT '是否删除，0：正常、1：已删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

