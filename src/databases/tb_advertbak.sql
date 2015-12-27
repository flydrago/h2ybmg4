/*
-- 检测字典
select * from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('advertupload_path','subject_type','advert_obj','advertupload_path'));
select * from tb_sys_dict_main where dict_code in('advertupload_path','subject_type','advert_obj','advertupload_path');

-- 检测菜单，按钮、表格列
select * from tb_sys_menu where parent_id in (1009000000) or id = 1009000000;
select * from tb_sys_button where menu_id in (select id from tb_sys_menu where parent_id in (1009000000) or id = 1009000000);
select * from tb_sys_grid_columns where join_id in (select id from tb_sys_menu where parent_id in (1009000000) or id = 1009000000) and join_type = 'menu';


-- 检测类型验证
select * from tb_sys_type where pid in (1009000000) or id = 1009000000;
select * from tb_sys_validation where join_id in (select id from tb_sys_type where pid in (1009000000) or id = 1009000000) and join_type = 'validate';


-- 删除字典
delete from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('fileuploadtemp_path','subject_type','advert_obj','advertupload_path'));
delete from tb_sys_dict_main where dict_code in('fileuploadtemp_path','subject_type','advert_obj','advertupload_path');

-- 删除菜单，按钮、表格列
delete from tb_sys_button where menu_id in (select id from tb_sys_menu where parent_id in (1009000000) or id = 1009000000);
delete from tb_sys_grid_columns where join_id in (select id from tb_sys_menu where parent_id in (1009000000) or id = 1009000000) and join_type = 'menu';
delete from tb_sys_menu where parent_id in (1009000000) or id = 1009000000;


-- 删除类型验证
delete from tb_sys_validation where join_id in (select id from tb_sys_type where pid in (1009000000) or id = 1009000000) and join_type = 'validate';
delete from tb_sys_type where pid in (1009000000) or id = 1009000000;
*/

-- ----------------------------
-- 广告维护维护(2014-11-12)
-- ----------------------------
DROP TABLE IF EXISTS tb_advert;
CREATE TABLE tb_advert (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  column_id bigint(20) NOT NULL COMMENT '栏位Id',
  user_id bigint(20) NOT NULL COMMENT '创建用户Id',
  advert_name varchar(255) DEFAULT NULL COMMENT '广告名称',
  subject_id bigint(20) DEFAULT '0' COMMENT '广告主题Id',
  advert_obj int(10) DEFAULT NULL COMMENT '0:android客户端 1：ios客户端 2：微信 然后各个标示的2的次方相加',
  file_name varchar(255) DEFAULT NULL COMMENT '文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间 用于根据时间段变换广告',
  end_date datetime DEFAULT NULL COMMENT '结束时间 用于根据时间段变换广告',
  repeat_start varchar(20) DEFAULT NULL COMMENT '暂时用于天重复显示的情况',
  repeat_end varchar(20) DEFAULT NULL COMMENT '暂时用于天重复显示的情况',
  repeat_type varchar(20) DEFAULT NULL COMMENT '重复类型 暂时不用',
  memo varchar(100) DEFAULT NULL COMMENT '备注',
  if_default int(11) DEFAULT NULL COMMENT '是否默认 0：非默认、1：默认',
  reverse_1 bigint(20) DEFAULT '0' COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  if_delete int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 广告栏位(2014-11-12)
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_column;
CREATE TABLE tb_advert_column (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  column_name varchar(255) DEFAULT NULL COMMENT '广告栏名称',
  column_type varchar(50) DEFAULT NULL COMMENT '广告栏类型，public:公用栏位，private:私有栏位（需要分配）',
  column_ord int(11) DEFAULT '0' COMMENT '广告栏排序',
  if_work int(11) DEFAULT '1' COMMENT '0 不启用，1 启用',
  if_delete int(11) DEFAULT '0' COMMENT '0 正常，1 删除',
  memo varchar(50) DEFAULT NULL COMMENT '备注',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 广告栏位单位关联表(2014-11-12)
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_column_unit;
CREATE TABLE tb_advert_column_unit (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  column_id bigint(20) NOT NULL COMMENT '栏位Id',
  start_date datetime DEFAULT NULL COMMENT '开始时间 用于根据时间段变换广告',
  end_date datetime DEFAULT NULL COMMENT '结束时间 用于根据时间段变换广告',
  if_work int(11) DEFAULT '1' COMMENT '0 不启用，1 启用',
  if_delete int(11) DEFAULT '0' COMMENT '0 正常，1 删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 广告主题(2014-11-12)
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_subject;
CREATE TABLE tb_advert_subject (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  user_id bigint(20) NOT NULL COMMENT '创建人',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  subject_name varchar(255) DEFAULT NULL COMMENT '主题名称',
  subject_type varchar(50) DEFAULT NULL COMMENT 'list：商品列表  detail：详细  html:宣传页面',
  subject_content longtext COMMENT '主题内容，用于客户端直接跳转活动页面',
  memo varchar(255) DEFAULT NULL COMMENT '主题备注',
  if_delete int(11) DEFAULT '0' COMMENT '0 正常，1 删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 广告主题信息关联表(2014-11-12)
-- ----------------------------
DROP TABLE IF EXISTS tb_advert_subject_info;
CREATE TABLE tb_advert_subject_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  subject_id bigint(20) NOT NULL COMMENT '主题Id',
  info_value varchar(50) DEFAULT NULL COMMENT '主题信息值：商品Id等',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'fileuploadtemp_path', '文件上传暂存路径', 'param', '/opt/wps/file/fup/fileuploadtemp_path/', '245', '1', '0');
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'subject_type', '主题类型', 'dict', '', '212', '1', '0');
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'advert_obj', '广告对象', 'dict', '', '213', '1', '0');
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'advertupload_path', '广告上传路径', 'param', '/opt/wps/file/fup/advertupload_path/', '123', '1', '0');

INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'subject_type'), 'list', '列表', '列表', '1');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'subject_type'), 'detail', '详细', '详细', '2');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'subject_type'), 'html', '宣传页面', '宣传页面', '3');

INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'advert_obj'), 'android', '安卓', '安卓客户端', '1');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'advert_obj'), 'ios', '苹果', '苹果客户端', '2');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'advert_obj'), 'wechat', '微信', '微信客户端', '3');

-- 广告栏位类型
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'column_type', '广告栏位类型', 'dict', '', '212', '1', '0');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'column_type'), 'public', '代理商栏位', '代理商栏位', '1');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'column_type'), 'private', '平台栏位', '平台栏位', '3');



-- 菜单
INSERT INTO `tb_sys_menu` VALUES ('1009000000', '0', '广告栏位管理', '', '', '112', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_menu` VALUES ('1009010000', '1009000000', '广告维护', 'business/advert/init.htm', '', '211', '1', '0', '1', '0', '1', '0');
-- 按钮
INSERT INTO `tb_sys_button` VALUES ('1009010100', '1009010000', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009010200', '1009010000', '修改', null, 'modify', 'h2y_modify', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009010300', '1009010000', '删除', null, 'delete', 'h2y_delete', '9', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009010400', '1009010000', '刷新', null, 'refresh', 'h2y_refresh', '13', '1', '1', '0');

-- 表格列
INSERT INTO `tb_sys_grid_columns` VALUES ('1009010100', '1009010000', '是否默认', 'IF_DEFAULT', '80', 'center', 'string', null, 'return value==1?\"是\":\"否\";', '1', 'menu', '29', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009010300', '1009010000', '结束时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'menu', '23', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009010500', '1009010000', '主题', 'SUBJECT_NAME', '200', 'left', 'string', null, '', '1', 'menu', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009010700', '1009010000', '对象', 'ADVERT_OBJ', '200', 'left', 'string', null, 'var obj = \"\"; if(value == undefined){return \"\"} if((value&1)== 1){ 	obj+=\"安卓 \" } if((value&2)== 2){ 	obj+=\"苹果 \" } if((value&4)== 4){ 	obj+=\"微信 \" } return obj;', '1', 'menu', '16', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009010900', '1009010000', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'menu', '20', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009011100', '1009010000', '广告名称', 'ADVERT_NAME', '300', 'left', 'string', null, '', '1', 'menu', '2', '1', '1');


INSERT INTO `tb_sys_menu` VALUES ('1009030000', '1009000000', '栏位维护', 'business/advert_column/init.htm', '', '1', '1', '0', '1', '0', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009030100', '1009030000', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009030300', '1009030000', '修改', null, 'modify', 'h2y_modify', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009030500', '1009030000', '删除', null, 'delete', 'h2y_delete', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009030700', '1009030000', '刷新', null, 'refresh', 'h2y_refresh', '11', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1009030100', '1009030000', '排序', 'COLUMN_ORD', '100', 'left', 'string', null, '', '1', 'menu', '13', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009030300', '1009030000', '是否启用', 'IF_WORK', '100', 'center', 'string', null, 'return value==1?\"已启用\":\"未启用\";', '1', 'menu', '8', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009030500', '1009030000', '名称', 'COLUMN_NAME', '200', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009030700', '1009030000', '类型', 'COLUMN_TYPE', '150', 'center', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009030900', '1009030000', '备注', 'MEMO', '200', 'left', 'string', null, '', '1', 'menu', '5', '1', '1');



INSERT INTO `tb_sys_menu` VALUES ('1009050000', '1009000000', '栏位分配', 'business/advert_column_unit/init.htm', '', '21', '1', '0', '1', '0', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009050100', '1009050000', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009050300', '1009050000', '修改', null, 'modify', 'h2y_modify', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009050500', '1009050000', '删除', null, 'delete', 'h2y_delete', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009050700', '1009050000', '刷新', null, 'refresh', 'h2y_refresh', '10', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1009050100', '1009050000', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'menu', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009050300', '1009050000', '是否启用', 'IF_WORK', '100', 'center', 'string', null, 'return value==1?\"已启用\":\"未启用\";', '1', 'menu', '14', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009050500', '1009050000', '结束时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'menu', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009050700', '1009050000', '广告栏名称', 'COLUMN_NAME', '300', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');


INSERT INTO `tb_sys_menu` VALUES ('1009070000', '1009000000', '主题维护', 'business/advert_subject/init.htm', '', '233', '1', '0', '1', '0', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1009070100', '1009070000', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070300', '1009070000', '修改', null, 'modify', 'h2y_modify', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070500', '1009070000', '删除', null, 'delete', 'h2y_delete', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1009070700', '1009070000', '刷新', null, 'refresh', 'h2y_refresh', '10', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1009070100', '1009070000', '主题名称', 'SUBJECT_NAME', '300', 'left', 'string', null, '', '1', 'menu', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070300', '1009070000', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'menu', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1009070500', '1009070000', '主题类型', 'SUBJECT_TYPE', '100', 'left', 'string', null, '', '1', 'menu', '8', '1', '1');




-- 类型维护
INSERT INTO `tb_sys_type` VALUES ('1009000000', 'advertmanager', '0', '广告管理', '广告管理', 'validate', '12');
INSERT INTO `tb_sys_type` VALUES ('1009010000', 'advert_validate', '1009000000', '广告维护', '广告维护验证', 'validate', '25');
INSERT INTO `tb_sys_validation` VALUES ('1009010100', 'advertName', '1009010000', 'validate', 'maxlength', '255', '广告名称最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1009010300', 'advertName', '1009010000', 'validate', 'required', 'true', '广告名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1009010500', 'memo', '1009010000', 'validate', 'maxlength', '100', '备注最大长度为{0}！', '1');

INSERT INTO `tb_sys_type` VALUES ('1009030000', 'advertcolumn_validate', '1009000000', '广告栏维护验证', '广告栏维护验证', 'validate', '1');
INSERT INTO `tb_sys_validation` VALUES ('1009030100', 'columnName', '1009030000', 'validate', 'required', 'true', '名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1009030300', 'columnName', '1009030000', 'validate', 'maxlength', '255', '名称最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1009030500', 'memo', '1009030000', 'validate', 'maxlength', '50', '备注最大长度为{0}！', '1');

INSERT INTO `tb_sys_type` VALUES ('1009050000', 'advertsubject_validate', '1009000000', '广告主题验证', '广告主题验证', 'validate', '30');
INSERT INTO `tb_sys_validation` VALUES ('1009050100', 'subjectName', '1009050000', 'validate', 'maxlength', '255', '主题名称最大长度为{0}！·', '1');
INSERT INTO `tb_sys_validation` VALUES ('1009050300', 'subjectName', '1009050000', 'validate', 'required', 'true', '主题名称不能为空！·', '1');