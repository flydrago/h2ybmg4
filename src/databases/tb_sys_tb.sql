
-- ----------------------------
-- 单位表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_units;
CREATE TABLE tb_sys_units (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '单位ID编码',
  unit_code varchar(255) DEFAULT NULL COMMENT '单位编码',
  parent_id bigint(20) DEFAULT NULL COMMENT '单位父级Id',
  zone_code varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '区域编码',
  unit_domain varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位域名',
  unit_name varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位名称 和营业执照一致',
  short_name varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位简称',
  reg_date datetime DEFAULT NULL COMMENT '单位注册日期',
  stop_date datetime DEFAULT NULL COMMENT '单位停用日期',
  user_count bigint(10) DEFAULT NULL COMMENT '单位用户总数',
  unit_status varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位状态： 包括注册，初审，复审，核准，停用初审，停用复审，停用核准',
  admin_url varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '管理URL',
  portal_url varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'portal URL',
  unit_address varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位地址 和营业执照一致',
  tel_area_code varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话区号',
  tel varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  tel_service varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务电话',
  fax varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  legal_person varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位法人',
  legal_person_mobile varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单位法人手机',
  memo varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- （部门，门店）表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_department;
CREATE TABLE tb_sys_department (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dept_code varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '部门编码 开始值100',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位ID，区分不同单位的部门',
  parent_id bigint(20) DEFAULT NULL COMMENT '部门父级Id',
  dept_leader bigint(20) DEFAULT NULL COMMENT '部门负责人，暂时不用',
  dept_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  dept_short_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称简称',
  dept_desc varchar(240) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '部门职责',
  dept_ord bigint(10) DEFAULT '0' COMMENT '部门排序',
  dept_type bigint(10) DEFAULT NULL COMMENT '部门类型，0：部门，1：门店',
  if_delete bigint(10) DEFAULT '0' COMMENT '是否删除： 1 是，0 否 ',
  position varchar(300) DEFAULT NULL COMMENT '位置信息（门店时，用到）',
  gps_longitude double DEFAULT NULL COMMENT '经度',
  gps_latitude double DEFAULT NULL COMMENT '纬度',
  reverse_1 varchar(200) DEFAULT NULL COMMENT '冗余字段1',
  reverse_2 varchar(255) DEFAULT NULL COMMENT '冗余字段2',
  if_has_shop bigint(10) DEFAULT '0' COMMENT '是否有门店： 1 是，0 否 ',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 部门门店关联用户中间表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_dept_user;
CREATE TABLE tb_sys_dept_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dept_id bigint(20) DEFAULT NULL COMMENT '部门Id',
  dept_code varchar(255) DEFAULT NULL COMMENT '部门编码',
  user_id bigint(20) DEFAULT NULL COMMENT '用户Id',
  du_ord bigint(20) DEFAULT '0' COMMENT '用户在部门的排序',
  user_level bigint(20) DEFAULT '0' COMMENT '用户在部门的级别 0:普通用户、1：部门负责人',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_user;
CREATE TABLE tb_sys_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位ID，区分不同单位的部门',
  user_name varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  account varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户账号',
  password varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  mobile varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  user_cord varchar(30) DEFAULT NULL COMMENT '身份证号',
  update_date datetime DEFAULT NULL,
  if_lock bigint(10) DEFAULT '0' COMMENT '是否锁定： 1 是，0 否 ',
  if_sys bigint(10) DEFAULT '0' COMMENT '是否系统用户： 1 是，0 否 ',
  if_delete bigint(10) DEFAULT '0' COMMENT '是否删除： 1 是，0 否 ',
  user_ord bigint(10) DEFAULT NULL COMMENT '用户排序',
  reverse_1 bigint(20) DEFAULT NULL COMMENT '冗余字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '冗余字段2',
  reverse_3 varchar(100) DEFAULT NULL COMMENT '冗余字段3',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- 菜单表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_menu;
CREATE TABLE tb_sys_menu (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  parent_id bigint(20) DEFAULT '0' COMMENT '父菜单ID 0 表示为根菜单 ',
  menu_name varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  menu_url varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单url',
  menu_icon varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  menu_ord bigint(10) DEFAULT NULL COMMENT '菜单排序',
  if_visible bigint(10) DEFAULT '1' COMMENT '是否显示 ： 1 是，0 否',
  if_main bigint(10) DEFAULT '0' COMMENT '是否主菜单 ： 1 是，0 否',
  if_grid bigint(10) DEFAULT '1' COMMENT '是否维护列信息 ： 1 是，0 否',
  if_query bigint(10) DEFAULT '1' COMMENT '是否查询 ： 1 是，0 否',
  if_validate bigint(10) DEFAULT '1' COMMENT '是否输入验证 ： 1 是，0 否',
  if_sys bigint(10) DEFAULT '0' COMMENT '是否系统 ： 1 是，0 否',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 菜单按钮表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_button;
CREATE TABLE tb_sys_button (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '按钮ID',
  menu_id bigint(20) NOT NULL COMMENT '菜单ID',
  button_name varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '按钮名称',
  button_url varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '按钮url',
  button_icon varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '按钮图标',
  button_click varchar(100) DEFAULT NULL,
  button_ord bigint(10) DEFAULT NULL COMMENT '按钮排序',
  if_visible bigint(10) DEFAULT '1' COMMENT '是否显示 ： 1 是，0 否',
  if_public bigint(10) DEFAULT '1' COMMENT '是否公用 ： 1 是，0 否 ',
  if_sys bigint(10) DEFAULT '0' COMMENT '是否系统 ： 1 是，0 否',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 系统类型表（表格列类型、验证类型、查询类型）
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_type;
CREATE TABLE tb_sys_type (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  pid bigint(20) DEFAULT NULL COMMENT '父级Id',
  name varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  memo varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '系统类型：grid:表格列、query:查询、validate:验证',
  ord bigint(10) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- ----------------------------
-- 验证表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_validation;
CREATE TABLE tb_sys_validation (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  field_id varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '字段Id',
  join_id bigint(20) DEFAULT NULL COMMENT '关联Id',
  join_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '关联类型： menu: 菜单，2:validate 验证类型主表 考虑以后扩展',
  rule_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '规则类型',
  rule_type_value varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '规则参数',
  msg varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '提示消息',
  if_work bigint(10) DEFAULT NULL COMMENT '是否启用，1：启用，0：不启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




-- ----------------------------
-- 查询表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_query_item;
CREATE TABLE tb_sys_query_item (
  id bigint(11) NOT NULL AUTO_INCREMENT,
  join_id bigint(20) DEFAULT NULL,
  join_type varchar(50) DEFAULT NULL COMMENT '关联类型： menu: 菜单，2 query 查询类型主表 考虑以后扩展',
  name varchar(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  field_name varchar(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  form_name varchar(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  input_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  data_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  value varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  datasource_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  datasource_value varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  query_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  operator varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  row bigint(11) DEFAULT NULL,
  rowspan bigint(11) DEFAULT NULL,
  width bigint(11) DEFAULT NULL,
  x bigint(11) DEFAULT NULL,
  ord bigint(11) DEFAULT NULL,
  unit_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;




-- ----------------------------
-- 表格列
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_grid_columns;
CREATE TABLE tb_sys_grid_columns (
  id bigint(10) NOT NULL AUTO_INCREMENT COMMENT '列编码',
  join_id bigint(10) DEFAULT NULL COMMENT '对应join_type 相应信息表的ID',
  title varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '列标题',
  name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '列名称',
  width varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '列宽度',
  align varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '显示位置',
  data_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据类型',
  editor_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '编辑类型',
  render varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '单元格渲染器',
  is_sort bigint(10) DEFAULT '1' COMMENT '是否支持排序',
  join_type varchar(50) DEFAULT '1' COMMENT '关联类型： menu: 菜单，2 grid 列类型主表 考虑以后扩展',
  ord bigint(10) DEFAULT '0' COMMENT '列顺序',
  if_width bigint(10) DEFAULT '1' COMMENT '是否百分比',
  if_visible bigint(10) DEFAULT '1' COMMENT '是否显示 ： 1 是，0 否',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=53000509 DEFAULT CHARSET=utf8;




-- ----------------------------
-- app 版本控制表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_app;
CREATE TABLE tb_sys_app (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  code varchar(50) DEFAULT NULL,
  version_code bigint(10) DEFAULT NULL,
  version_name varchar(50) DEFAULT NULL,
  version_update_msg varchar(255) DEFAULT NULL,
  apk_name varchar(255) DEFAULT NULL,
  apk_save_name varchar(255) DEFAULT NULL,
  disk_path varchar(255) DEFAULT NULL,
  disk_save_name varchar(255) DEFAULT NULL,
  down_url varchar(255) DEFAULT NULL,
  md5_code varchar(255) DEFAULT NULL,
  apk_icon varchar(255) DEFAULT NULL,
  apk_packagename varchar(255) DEFAULT NULL,
  apk_length bigint(10) DEFAULT NULL,
  apk_size varchar(255) DEFAULT NULL,
  apk_com varchar(255) DEFAULT NULL,
  update_date varchar(255) DEFAULT NULL,
  if_work bigint(10) DEFAULT NULL,
  if_delete bigint(10) DEFAULT NULL,
  encrypt_code varchar(255) DEFAULT NULL,
  signature varchar(255) DEFAULT NULL,
  memo varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=142509 DEFAULT CHARSET=utf8;





-- ----------------------------
-- 字典详细表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_dict_detail;
CREATE TABLE tb_sys_dict_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL,
  dict_main_id bigint(20) DEFAULT NULL,
  code varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据字典明细编码',
  value varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据字典明细值',
  memo varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据字典明细备注',
  ord bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 字典主表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_dict_main;
CREATE TABLE tb_sys_dict_main (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  dict_code varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据字典编码',
  dict_name varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据字典名称',
  dict_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'dict' COMMENT '数据字典类型:json 以json方式解析 dict_value , sql 以sql方式解析dict_value ,param 直接使用dict_value ,dict: 从子表中获得',
  dict_value varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '字典数值 根据dict_type决定如何解析 ，当dict_type 为dict时，该值无效',
  dict_ord bigint(10) DEFAULT '100' COMMENT '排序',
  if_sys bigint(10) DEFAULT '0' COMMENT '是否系统参数 ： 1 是，0 单位参数 ,如果系统私有,程序不显示，但可以供其它功能使用',
  if_user_conf bigint(10) DEFAULT '0' COMMENT '是否用户可配置 ： 1 是，0 否 ,如果为1 则可以单独配置菜单供用户配置',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 系统日志表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_log;
CREATE TABLE tb_sys_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(12) DEFAULT NULL COMMENT '用户ID',
  user_name varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  account varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户账号',
  ip varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端IP地址',
  module_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作模块名',
  operate_date datetime DEFAULT NULL COMMENT '操作时间',
  operate_type varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作类型： 登录 ，退出，添加，修改，删除，查询等',
  operate_result varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作结果：成功，失败',
  operate_os varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端使用的操作系统',
  operate_browser varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端使用的浏览器',
  memo varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位ID，区分不同单位的登录',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=686 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 系统权限表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_privilege_list;
CREATE TABLE tb_sys_privilege_list (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL,
  privilege_master varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  privilege_master_value bigint(20) DEFAULT NULL,
  privilege_access varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  privilege_access_value bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=869 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_role;
CREATE TABLE tb_sys_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位ID，区分不同单位的角色',
  role_code varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色编码',
  role_name varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  role_desc varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  if_sys bigint(10) DEFAULT '0' COMMENT '是否系统角色 ： 1 是，0 否',
  if_privilege bigint(10) DEFAULT '0' COMMENT '是否特权角色 ： 1 是，0 否 特权不能继承',
  if_delete bigint(10) DEFAULT '0' COMMENT '是否删除 ： 1 是，0 否',
  role_ord bigint(10) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 短信发送失败记录表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_sms_fail;
CREATE TABLE tb_sys_sms_fail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  user_id bigint(20) DEFAULT NULL COMMENT '接受用户Id',
  mobile varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '接受者的电话',
  module_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作模块编码',
  create_date datetime DEFAULT NULL COMMENT '短信插入缓存的创建时间',
  send_date datetime DEFAULT NULL COMMENT '操作时间',
  send_state varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短信服务返回发送状态：1	发送短信成功，-1	发送失败',
  msg varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  batch_id varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短信服务发送批次Id',
  memo varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 短信发送成功历史表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_sms_his;
CREATE TABLE tb_sys_sms_his (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  user_id bigint(20) DEFAULT NULL COMMENT '接受用户Id',
  mobile varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '接受者的电话',
  module_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作模块编码',
  create_date datetime DEFAULT NULL COMMENT '短信插入缓存的创建时间',
  send_date datetime DEFAULT NULL COMMENT '操作时间',
  send_state varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短信服务返回发送状态：1	发送短信成功，-1	发送失败',
  msg varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  batch_id varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短信服务发送批次Id',
  memo varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 短信发送临时表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_sms_temp;
CREATE TABLE tb_sys_sms_temp (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  user_id bigint(20) DEFAULT NULL COMMENT '接受用户Id',
  mobile varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '接受者的电话',
  module_name varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作模块编码',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  msg varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  memo varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 日志表添加字段
alter table tb_sys_log add business_Id varchar(500) COMMENT '业务数据Id';
alter table tb_sys_log add table_name varchar(50) COMMENT  '业务数据表名称';
alter table tb_sys_log add reserve_1 varchar(50) COMMENT  '扩展字段1';
alter table tb_sys_log add reserve_2 varchar(100) COMMENT   '扩展字段2';

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
