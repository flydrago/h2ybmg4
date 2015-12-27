-- ----------------------------
-- 参数类型
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_param_type;
CREATE TABLE tb_sys_param_type (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type_code varchar(50)  DEFAULT NULL COMMENT '类型编码',
  type_name varchar(255)  DEFAULT NULL COMMENT '类型名称',
  memo varchar(255)  DEFAULT NULL COMMENT '备注',
  ord bigint(20) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 参数
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_param;
CREATE TABLE tb_sys_param (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  type_id bigint(20) DEFAULT NULL COMMENT '参数类型Id',
  params_code varchar(50) DEFAULT NULL COMMENT '参数编码',
  params_value varchar(255) DEFAULT NULL COMMENT '参数值',
  memo varchar(255) DEFAULT NULL COMMENT '备注',
  ord bigint(20) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



INSERT INTO `tb_sys_menu` VALUES ('1009070008', '1', '参数类型维护', 'sys/paramtype/init.htm', '', '22', '1', '0', '1', '1', '1', '1');
INSERT INTO `tb_sys_button` VALUES ('1009070723', '1009070008', '添加', null, 'add', 'h2y_add', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070724', '1009070008', '修改', null, 'modify', 'h2y_modify', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070725', '1009070008', '删除', null, 'delete', 'h2y_delete', '8', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070726', '1009070008', '查询', null, 'search', 'h2y_search', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070727', '1009070008', '刷新', null, 'refresh', 'h2y_refresh', '13', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1009070598', '1009070008', '编码', 'TYPE_CODE', '150', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070599', '1009070008', '名称', 'TYPE_NAME', '300', 'left', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070600', '1009070008', '排序', 'ORD', '100', 'left', 'string', null, '', '1', 'menu', '5', '1', '1');

INSERT INTO `tb_sys_query_item` VALUES ('184', '1009070008', 'menu', '编码', 'type_code', 'type_code', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('185', '1009070008', 'menu', '名称', 'type_name', 'type_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '2', '0');


INSERT INTO `tb_sys_menu` VALUES ('1009070009', '38', '参数维护', 'sys/param/init.htm', '', '220', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070728', '1009070009', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070729', '1009070009', '修改', null, 'modify', 'h2y_modify', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070730', '1009070009', '删除', null, 'delete', 'h2y_delete', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070731', '1009070009', '刷新', null, 'refresh', 'h2y_refresh', '7', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1009070601', '1009070009', '编码', 'PARAMS_CODE', '150', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070602', '1009070009', '值', 'PARAMS_VALUE', '150', 'left', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070603', '1009070009', '备注', 'MEMO', '200', 'left', 'string', null, '', '1', 'menu', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070604', '1009070009', '排序', 'ORD', '100', 'left', 'string', null, '', '1', 'menu', '11', '1', '1');



INSERT INTO `tb_sys_type` VALUES ('50', 'sysparamtype_validate', '20', '参数类型验证', '参数类型验证', 'validate', '3');
INSERT INTO `tb_sys_type` VALUES ('51', 'sysparam_validate', '13', '参数维护验证', '参数维护验证', 'validate', '25');

INSERT INTO `tb_sys_validation` VALUES ('307', 'typeCode', '50', 'validate', 'required', 'true', '编码不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('308', 'typeCode', '50', 'validate', 'maxlength', '50', '编码最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('309', 'typeName', '50', 'validate', 'required', 'true', '名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('310', 'typeName', '50', 'validate', 'maxlength', '255', '名称最大长度为{0}!', '1');
INSERT INTO `tb_sys_validation` VALUES ('311', 'memo', '50', 'validate', 'maxlength', '255', '备注最大长度为{0}!', '1');
INSERT INTO `tb_sys_validation` VALUES ('312', 'paramsCode', '51', 'validate', 'required', 'true', '参数编码不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('313', 'paramsCode', '51', 'validate', 'alnumex', 'true', '参数编码只能为字母数字下划线', '1');
INSERT INTO `tb_sys_validation` VALUES ('314', 'paramsValue', '51', 'validate', 'required', 'true', '参数值不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('315', 'paramsValue', '51', 'validate', 'maxlength', '255', '参数值最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('316', 'memo', '51', 'validate', 'maxlength', '255', '备注最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('317', 'paramsCode', '51', 'validate', 'maxlength', '50', '参数编码最大长度为{0}！', '1');

