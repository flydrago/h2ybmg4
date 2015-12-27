/*
-- 检测字典
select * from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('activity_level','findactivity_path'));
select * from tb_sys_dict_main where dict_code in('activity_level','findactivity_path');

select * from tb_sys_menu  where id like '1013%';
select * from tb_sys_button  where id like '1013%';
select * from tb_sys_grid_columns  where  id like '1013%';
select * from tb_sys_type  where  id like '1013%';
select * from tb_sys_query_item  where  id like '1013%';
select * from tb_sys_validation  where  id like '1013%';

-- 删除字典
delete from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('activity_level','findactivity_path'));
delete from tb_sys_dict_main where dict_code in('activity_level','findactivity_path');
*/

-- ----------------------------
-- 发现模块活动主表维护
-- ----------------------------
DROP TABLE IF EXISTS tb_find_activity;
CREATE TABLE tb_find_activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  user_id bigint(20) NOT NULL COMMENT '创建用户Id',
  title varchar(255) DEFAULT NULL COMMENT '标题',
  description varchar(500) DEFAULT NULL COMMENT '描述',
  activity_level int(10)  DEFAULT '0' COMMENT '级别：用于进行排序，进而控制优先显示顺序',
  content longtext DEFAULT NULL COMMENT '正文',
  file_name varchar(255) DEFAULT NULL COMMENT '文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '发布时间',
  end_date datetime DEFAULT NULL COMMENT '结束时间',
  if_set_date int(10) DEFAULT '0' COMMENT '是否设置时间 1：设置 0：否',
  activity_status int(10)  DEFAULT '0' COMMENT '状态：-1：删除 0：正常  1：下架 ',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='活动主表 存储活动的主题信息';

-- ----------------------------
-- 活动商品关联
-- ----------------------------
DROP TABLE IF EXISTS tb_find_activity_goods;
CREATE TABLE tb_find_activity_goods (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_id bigint(20) NOT NULL COMMENT '发现Id',
  goods_id varchar(50) DEFAULT NULL COMMENT '商品Id',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='建立活动与商品的关联';

-- ----------------------------
-- 活动评论
-- ----------------------------
DROP TABLE IF EXISTS tb_find_activity_comment;
CREATE TABLE tb_find_activity_comment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_id bigint(20) NOT NULL COMMENT '发现活动Id',
  member_id bigint(20) NOT NULL COMMENT '评论会员Id',
  content varchar(255) NOT NULL COMMENT '评论内容',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  if_pass int(10) DEFAULT '0' COMMENT '是否通过 0：待审 1：通过  2：不通过 （暂时不用）',
  if_delete int(10) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
  reverse_1 varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='对活动的评论进行记录';



-- 活动级别（类型编码不可随机改，并且只能为数字，用于排序）
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ( 'activity_level', '活动级别', 'dict', '', '212', '1', '0');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'activity_level'), '1', '一般', '一般', '1');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'activity_level'), '2', '较高', '较高', '3');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'activity_level'), '3', '高', '高', '5');
INSERT INTO `tb_sys_dict_detail`(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'activity_level'), '4', '非常高', '非常高', '7');

-- 发现活动上传路径
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('findactivity_path', '发现活动图片上传路径', 'param', '/opt/wps/file/fup/findactivity_path/', '245', '1', '0');



INSERT INTO `tb_sys_menu` VALUES ('1013000000', '0', '活动维护', '', '', '130', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_menu` VALUES ('1013010000', '1013000000', '活动维护', 'business/findactivity/init.htm', '', '3', '1', '0', '1', '1', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1013010100', '1013010000', '查询', null, 'search', 'h2y_search', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1013010200', '1013010000', '添加', null, 'add', 'h2y_add', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1013010300', '1013010000', '修改', null, 'modify', 'h2y_modify', '11', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1013010400', '1013010000', '删除', null, 'delete', 'h2y_delete', '13', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1013010500', '1013010000', '刷新', null, 'refresh', 'h2y_refresh', '16', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1013010600', '1013010000', '查看评论', null, 'comment', 'h2y_comment', '2', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1013010100', '1013010000', '标题', 'TITLE', '300', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013010200', '1013010000', '级别', 'ACTIVITY_LEVEL_NAME', '100', 'left', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013010300', '1013010000', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'menu', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013010400', '1013010000', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'menu', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013010500', '1013010000', '结束时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'menu', '13', '1', '1');

INSERT INTO `tb_sys_query_item` VALUES ('1013010100', '1013010000', 'menu', '标题', 'fa.title', 'title', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1013010200', '1013010000', 'menu', '级别', 'fa.activity_level', 'activity_level', 'string', 'string', '', 'dict', 'activity_level', 'select', '=', '1', '1', '100', '0', '3', '0');




-- 验证
INSERT INTO `tb_sys_type` VALUES ('1013010000', 'findactivitymanager', '0', '活动维护', '活动维护', 'validate', '25');
INSERT INTO `tb_sys_type` VALUES ('1013010100', 'findactivity_validate', '1013010000', '活动维护验证', '活动维护验证', 'validate', '2');

INSERT INTO `tb_sys_validation` VALUES ('1013010101', 'title', '1013010100', 'validate', 'required', 'true', '标题不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1013010102', 'title', '1013010100', 'validate', 'maxlength', '255', '标题最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1013010103', 'description', '1013010100', 'validate', 'maxlength', '500', '描述最大长度{0}！', '1');



-- 表格列
INSERT INTO `tb_sys_type` VALUES ('1013020000', 'findactivity_manager', '0', '活动维护', '活动维护', 'grid', '7');
INSERT INTO `tb_sys_type` VALUES ('1013020100', 'findactivity_comment', '1013020000', '活动评论列表', '活动评论列表', 'grid', '3');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013020101', '1013020100', '内容', 'CONTENT', '400', 'left', 'string', null, '', '1', 'grid', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013020102', '1013020100', '昵称', 'NICK_NAME', '150', 'left', 'string', null, '', '1', 'grid', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013020103', '1013020100', '姓名', 'REAL_NAME', '150', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013020104', '1013020100', '账号', 'ACCOUNT', '150', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1013020105', '1013020100', '时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '13', '1', '1');


-- 查询
INSERT INTO `tb_sys_type` VALUES ('1013030000', 'findactivity_manager', '0', '活动类型', '活动类型', 'query', '5');
INSERT INTO `tb_sys_type` VALUES ('1013030100', 'findactivity_comment', '1013030000', '活动评论查询', '活动评论查询', 'query', '4');
INSERT INTO `tb_sys_query_item` VALUES ('1013030101', '1013030100', 'query', '评论内容', 'fac.content', 'content', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '7', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1013030102', '1013030100', 'query', '昵称', 'mu.nick_name', 'nick_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '5', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1013030103', '1013030100', 'query', '姓名', 'mu.real_name', 'real_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1013030104', '1013030100', 'query', '账号', 'mu.account', 'account', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');


