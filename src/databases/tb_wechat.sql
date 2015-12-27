/*
-- 检测字典
select * from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('wechatmenu_type','material_type','wechat_material_path','msg_type'));
select * from tb_sys_dict_main where dict_code in('wechatmenu_type','material_type','wechat_material_path','msg_type');

select * from tb_sys_menu  where id like '1011%' or id like '1012%';
select * from tb_sys_button  where id like '1011%' or id like '1012%';
select * from tb_sys_grid_columns  where  id like '1011%' or id like '1012%';
select * from tb_sys_type  where  id like '1011%' or id like '1012%';
select * from tb_sys_query_item  where  id like '1011%' or id like '1012%';
select * from tb_sys_validation  where  id like '1011%' or id like '1012%';

-- 删除字典
delete from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('wechatmenu_type','material_type','wechat_material_path','msg_type'));
delete from tb_sys_dict_main where dict_code in('wechatmenu_type','material_type','wechat_material_path','msg_type');
*/


-- 字典菜单事件类型
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('wechatmenu_type', '微信菜单类型', 'dict', '', '123', '1', '0');

INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'menu', '菜单', '菜单', '1');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'click', '点击推事件', '点击推事件', '2');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'view', '跳转URL', '跳转URL', '3');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'scancode_push', '扫码推事件', '扫码推事件', '4');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'scancode_waitmsg', '扫码推事件且弹出“消息接收中”提示框', '扫码推事件且弹出“消息接收中”提示框', '5');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'pic_sysphoto', '弹出系统拍照发图', '弹出系统拍照发图', '6');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'pic_photo_or_album', '弹出拍照或者相册发图', '弹出拍照或者相册发图', '7');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'pic_weixin', '弹出微信相册发图器', '弹出微信相册发图器', '8');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'wechatmenu_type'), 'location_select', '弹出地理位置选择器', '弹出地理位置选择器', '9');

-- 微信素材类型
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('material_type', '微信素材类型', 'dict', '', '123', '1', '0');

INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'material_type'), 'bignews', '大图文', '大图文', '1');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'material_type'), 'smallnews', '小图文', '小图文', '3');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'material_type'), 'image', '图片', '图片', '5');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'material_type'), 'voice', '语音', '语音', '7');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'material_type'), 'video', '视频', '视频', '9');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'material_type'), 'thumb', '缩略图', '缩略图', '13');


-- 微信素材上传路径
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('wechat_material_path', '微信素材上传路径', 'param', '/opt/wps/file/fup/wechat_material_path/', '245', '1', '0');

-- 微信消息类型
insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('msg_type', '微信响应消息类型', 'dict', '', '125', '1', '0');

INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'msg_type'),'text', '文本消息', '文本消息', '1');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'msg_type'),'news', '图文消息', '图文消息', '2');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'msg_type'),'image', '图片消息', '图片消息', '4');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'msg_type'),'voice', '语音消息', '语音消息', '8');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'msg_type'),'video', '视频消息', '视频消息', '10');
INSERT INTO tb_sys_dict_detail(unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'msg_type'),'music', '音乐消息', '音乐消息', '13');

-- ----------------------------
-- 微信自定义菜单
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_menu;
CREATE TABLE tb_wechat_menu (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  parent_id bigint(20) DEFAULT NULL COMMENT '父菜单ID 0 表示为根菜单 ',
  msg_id bigint(20) NOT NULL COMMENT '消息Id',
  menu_type varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单响应动作类型（click view scancode_push scancode_waitmsg pic_sysphoto等8种事件）',
  menu_name varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  menu_key varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单标识，click：必须',
  menu_url varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单url view：必须',
  menu_ord bigint(10) DEFAULT NULL COMMENT '菜单排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 微信素材维护（包括图文、图片、图像、等）目前只支持图文
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_material;
CREATE TABLE tb_wechat_material (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  title varchar(255) DEFAULT NULL COMMENT '标题',
  author varchar(255) DEFAULT NULL COMMENT '作者',
  user_id bigint(20) DEFAULT NULL COMMENT '创建人',
  url varchar(255) DEFAULT NULL COMMENT '跳转url',
  media_id varchar(255) DEFAULT NULL COMMENT '微信多媒体Id，类型为：voice、video、music时用到需要上传微信服务器',
  material_type varchar(255) DEFAULT NULL COMMENT '素材类型，news:图文、image：图片（暂不使用）、voice：语音、video:视频（暂不使用）、music：音乐',
  description longtext DEFAULT NULL COMMENT '描述',
  file_name varchar(255) DEFAULT NULL COMMENT '文件名称',
  disk_file_name varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  root_path varchar(255) DEFAULT NULL COMMENT '根路径',
  relative_path varchar(255) DEFAULT NULL COMMENT '相对路径',
  file_size bigint(20) DEFAULT NULL COMMENT '文件大小',
  file_suffix varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  memo varchar(100) DEFAULT NULL COMMENT '备注',
  if_work int(11) DEFAULT NULL COMMENT '是否启用，1：启用、0：不启用',
  if_delete int(11) DEFAULT NULL COMMENT '是否删除，1：已删除、0：正常',
  reverse_1 bigint(20) DEFAULT '0' COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 微信消息
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_msg;
CREATE TABLE tb_wechat_msg (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  title varchar(255) DEFAULT NULL COMMENT '标题',
  author varchar(255) DEFAULT NULL COMMENT '作者',
  user_id bigint(20) DEFAULT NULL COMMENT '创建人',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  content varchar(1000) DEFAULT NULL COMMENT '消息内容，当类型为text时，起作用',
  music_url varchar(500) DEFAULT NULL COMMENT '音乐Url，当类型为music时，时起作用',
  hq_music_url varchar(500) DEFAULT NULL COMMENT '高质量音乐Url，当类型为music时，时起作用',
  msg_type varchar(255) DEFAULT NULL COMMENT '消息类型，text:文本、news:图文、image：图片（暂不使用）、voice：语音、video:视频（暂不使用）、music：音乐（目前只支持文本和图文）',
  if_delete int(11) DEFAULT NULL COMMENT '是否删除，1：已删除、0：正常',
  reverse_1 bigint(20) DEFAULT '0' COMMENT '扩展字段1',
  reverse_2 varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 微信消息素材关联
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_msg_material;
CREATE TABLE tb_wechat_msg_material (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  msg_id bigint(20) NOT NULL COMMENT '消息Id',
  material_id bigint(20) NOT NULL COMMENT '素材Id',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 微信素材正文内容
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_material_content;
CREATE TABLE tb_wechat_material_content (
  id bigint(20) NOT NULL COMMENT '对应素材Id',
  content longtext DEFAULT NULL COMMENT '正文',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 微信自动回复（预定几个事件，eg:关注事件、扫描二维码事件等）
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_auto_reply;
CREATE TABLE tb_wechat_auto_reply (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  reply_name varchar(255) DEFAULT NULL COMMENT '名称',
  reply_type varchar(255) DEFAULT NULL COMMENT '回复类型，keyword:关键词自动回复，subscribe:被添加自动回复，automsg:消息自动回复,unsubscribe:被取消关注自动回复,location:上报地理位置事件等',
  is_reply_all int(10) DEFAULT '0' COMMENT '是否回复全部，0：否、1：是',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  if_delete int(10) DEFAULT '0' COMMENT '是否删除，0：否、1：是',
  memo varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 微信响应与消息关联
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_reply_msg;
CREATE TABLE tb_wechat_reply_msg (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  reply_id bigint(20) NOT NULL COMMENT '响应Id',
  msg_id bigint(20) NOT NULL COMMENT '消息Id',
  reply_type varchar(255) DEFAULT NULL COMMENT '响应类型 auto:自动回复、memu:菜单等，暂时不用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 微信响应与消息关联
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_reply_keyword;
CREATE TABLE tb_wechat_reply_keyword (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  reply_id bigint(20) NOT NULL COMMENT '响应Id',
  key_word varchar(255) DEFAULT NULL COMMENT '关键字',
  is_whole_match int(10) DEFAULT '0' COMMENT '是否全匹配，0：否，1：是',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure 公众账号信息
-- ----------------------------
DROP TABLE IF EXISTS tb_wechat_service;
CREATE TABLE tb_wechat_service (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  service_name varchar(200) DEFAULT NULL COMMENT '账号昵称',
  e_mail varchar(200) DEFAULT NULL COMMENT '联系邮箱',
  service_id varchar(64) DEFAULT NULL COMMENT '原始Id',
  service_account varchar(200) DEFAULT NULL COMMENT '微信号',
  is_certified int(10) DEFAULT '0' COMMENT '是否认证',
  account_type int(10) DEFAULT NULL COMMENT '账号类型 账号类型 0-订阅号,1-服务号',
  token varchar(500) DEFAULT NULL COMMENT '是否合法令牌',
  app_id varchar(64) DEFAULT NULL COMMENT '开发应用凭据ID',
  app_scret varchar(64) DEFAULT NULL COMMENT '开发应用凭据密码',
  access_token  varchar(300) DEFAULT NULL COMMENT '全局ACCESS_TOKEN',
  memo varchar(300) DEFAULT NULL COMMENT '描述',
  if_delete int(10) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='公众账号信息';





INSERT INTO `tb_sys_menu` VALUES ('1011000000', '0', '微信管理', '', '', '111', '1', '0', '1', '1', '1', '0');

INSERT INTO `tb_sys_menu` VALUES ('1011010000', '1011000000', '自定义菜单', 'wechat/menu/init.htm', '', '1', '1', '0', '1', '1', '1', '0');

-- 按钮
INSERT INTO `tb_sys_button` VALUES ('1011010100', '1011010000', '添加', null, 'add', 'h2y_add', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011010200', '1011010000', '修改', null, 'modify', 'h2y_modify', '9', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011010300', '1011010000', '删除', null, 'delete', 'h2y_delete', '11', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011010400', '1011010000', '刷新', null, 'refresh', 'h2y_refresh', '13', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011010500', '1011010000', '发布菜单', null, 'communication', 'h2y_updateMenu', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011010600', '1011010000', '添加响应', null, 'set', 'h2y_setmsg', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011010700', '1011010000', '移除响应', null, 'delete', 'h2y_removemsg', '5', '1', '1', '0');

-- 表格列
INSERT INTO `tb_sys_grid_columns` VALUES ('1011010100', '1011010000', '消息标题', 'MSG_TITLE', '300', 'left', 'string', null, '', '1', 'menu', '13', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011010200', '1011010000', '排序', 'MENU_ORD', '100', 'left', 'string', null, '', '1', 'menu', '23', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011010300', '1011010000', 'URL', 'MENU_URL', '250', 'left', 'string', null, '', '1', 'menu', '17', '1', '0');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011010400', '1011010000', '健', 'MENU_KEY', '150', 'left', 'string', null, '', '1', 'menu', '11', '1', '0');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011010500', '1011010000', '名称', 'MENU_NAME', '200', 'left', 'string', null, '', '1', 'menu', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011010600', '1011010000', '类型', 'MENU_TYPE_NAME', '150', 'left', 'string', null, '', '1', 'menu', '6', '1', '1');

INSERT INTO `tb_sys_menu` VALUES ('1011030000', '1011000000', '素材管理', 'wechat/material/init.htm', '', '3', '1', '0', '1', '1', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1011030100', '1011030000', '添加', null, 'add', 'h2y_add', '2', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011030200', '1011030000', '修改', null, 'modify', 'h2y_modify', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011030300', '1011030000', '删除', null, 'delete', 'h2y_delete', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011030400', '1011030000', '刷新', null, 'refresh', 'h2y_refresh', '15', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011030500', '1011030000', '查询', null, 'search', 'h2y_search', '0', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1011030100', '1011030000', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'menu', '15', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011030200', '1011030000', '是否启用', 'IF_WORK', '100', 'left', 'string', null, 'return value==1?\"已启用\":\"未启用\";', '1', 'menu', '22', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011030300', '1011030000', 'URL', 'URL', '300', 'left', 'string', null, '', '1', 'menu', '11', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011030400', '1011030000', '类型', 'MATERIAL_TYPE_NAME', '150', 'left', 'string', null, '', '1', 'menu', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011030500', '1011030000', '作者', 'AUTHOR', '150', 'left', 'string', null, '', '1', 'menu', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011030600', '1011030000', '标题', 'TITLE', '300', 'left', 'string', null, 'return f_html(rowdata);', '1', 'menu', '1', '1', '1');

INSERT INTO `tb_sys_query_item` VALUES ('1011030100', '1011030000', 'menu', '是否启用', 'wm.if_work', 'if_work', 'int', 'string', '', 'json', '[{\"text\":\"\",\"value\":\"\"},{\"text\":\"已启用\",\"value\":1},{\"text\":\"未启用\",\"value\":0}]', 'select', '=', '1', '1', '100', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1011030200', '1011030000', 'menu', '标题', 'wm.title', 'title', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1011030300', '1011030000', 'menu', '作者', 'wm.author', 'author', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '2', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1011030400', '1011030000', 'menu', '类型', 'wm.material_type', 'material_type', 'string', 'string', '', 'dict', 'material_type', 'select', '=', '1', '1', '100', '0', '3', '0');

INSERT INTO `tb_sys_menu` VALUES ('1011050000', '1011000000', '响应消息维护', 'wechat/msg/init.htm', '', '6', '1', '0', '1', '1', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1011050100', '1011050000', '查询', null, 'search', 'h2y_search', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011050200', '1011050000', '添加', null, 'add', 'h2y_add', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011050300', '1011050000', '修改', null, 'modify', 'h2y_modify', '8', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011050400', '1011050000', '删除', null, 'delete', 'h2y_delete', '10', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011050500', '1011050000', '刷新', null, 'refresh', 'h2y_refresh', '13', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1011050100', '1011050000', '创建时间', 'CREATE_DATE', '150', 'left', 'string', null, '', '1', 'menu', '11', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011050200', '1011050000', '作者', 'AUTHOR', '150', 'left', 'string', null, '', '1', 'menu', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011050300', '1011050000', '类型', 'MSY_TYPE_NAME', '100', 'center', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011050400', '1011050000', '标题', 'TITLE', '300', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');

INSERT INTO `tb_sys_query_item` VALUES ('1011050100', '1011050000', 'menu', '消息类型', 'wm.msg_type', 'msg_type', 'string', 'string', '', 'dict', 'msg_type', 'select', '=', '1', '1', '100', '0', '8', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1011050200', '1011050000', 'menu', '标题', 'wm.title', 'title', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '300', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1011050300', '1011050000', 'menu', '作者', 'wm.author', 'author', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '4', '0');


INSERT INTO `tb_sys_menu` VALUES ('1011070000', '1011000000', '公众号维护', 'wechat/service/init.htm', '', '9', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011070100', '1011070000', '添加', null, 'add', 'h2y_add', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011070200', '1011070000', '修改', null, 'modify', 'h2y_modify', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011070300', '1011070000', '删除', null, 'delete', 'h2y_delete', '11', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011070400', '1011070000', '刷新', null, 'refresh', 'h2y_refresh', '15', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1011070500', '1011070000', '详细', null, 'view', 'h2y_detail', '2', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1011070100', '1011070000', '名称', 'SERVICE_NAME', '200', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011070200', '1011070000', 'E_MAIL', 'E_MAIL', '150', 'left', 'string', null, '', '1', 'menu', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011070300', '1011070000', '原始ID', 'SERVICE_ID', '150', 'left', 'string', null, '', '1', 'menu', '9', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011070400', '1011070000', '微信号', 'SERVICE_ACCOUNT', '150', 'left', 'string', null, '', '1', 'menu', '12', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1011070500', '1011070000', '单位名称', 'UNIT_NAME', '150', 'left', 'string', null, '', '1', 'menu', '3', '1', '1');


INSERT INTO `tb_sys_menu` VALUES ('1012000000', '0', '微信自动回复', '', '', '120', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_menu` VALUES ('1012010000', '1012000000', '关键字回复', 'wechat/autoreply/init.htm', '', '1', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1012010100', '1012010000', '查询', null, 'search', 'h2y_search', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1012010200', '1012010000', '添加', null, 'add', 'h2y_add', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1012010300', '1012010000', '修改', null, 'modify', 'h2y_modify', '6', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1012010400', '1012010000', '删除', null, 'delete', 'h2y_delete', '9', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1012010500', '1012010000', '刷新', null, 'refresh', 'h2y_refresh', '12', '1', '1', '0');


INSERT INTO `tb_sys_grid_columns` VALUES ('1012010100', '1012010000', '名称', 'REPLY_NAME', '200', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1012010200', '1012010000', '是否回复全部', 'IS_REPLY_ALL', '150', 'center', 'string', null, 'return value==1?\"是\":\"否\";', '1', 'menu', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1012010300', '1012010000', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'menu', '7', '1', '1');

INSERT INTO `tb_sys_query_item` VALUES ('1012010100', '1012010000', 'menu', '名称', 'reply_name', 'reply_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1012010200', '1012010000', 'menu', '回复', 'is_reply_all', 'is_reply_all', 'string', 'string', '', 'json', '[{\"value\":\"\",\"text\":\"\"},{\"value\":\"0\",\"text\":\"随机回复\"},{\"value\":\"1\",\"text\":\"全部回复\"}]', 'select', '=', '1', '1', '100', '0', '4', '0');


INSERT INTO `tb_sys_menu` VALUES ('1012030000', '1012000000', '被添加回复', 'wechat/autoreply/add.htm?replyType=subscribe', '', '5', '1', '0', '0', '0', '1', '0');


INSERT INTO `tb_sys_menu` VALUES ('1012050000', '1012000000', '消息回复', 'wechat/autoreply/add.htm?replyType=automsg', '', '2', '1', '0', '0', '0', '1', '0');


-- 验证
INSERT INTO `tb_sys_type` VALUES ('1011000000', 'wechatmanager', '0', '微信管理', '微信管理', 'validate', '23');

INSERT INTO `tb_sys_type` VALUES ('1011010000', 'wechatmenu_validate', '1011000000', '自定义菜单验证', '自定义菜单验证', 'validate', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011010100', 'menuName', '1011010000', 'validate', 'required', 'true', '菜单名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011010200', 'menuUrl', '1011010000', 'validate', 'url', 'true', '', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011010300', 'menuKey', '1011010000', 'validate', 'alnumex', 'true', '', '1');

INSERT INTO `tb_sys_type` VALUES ('1011030000', 'wechatmaterial_validate', '1011000000', '素材管理', '素材管理', 'validate', '4');
INSERT INTO `tb_sys_validation` VALUES ('1011030100', 'url', '1011030000', 'validate', 'url', 'true', '请输入合法网址！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011030200', 'title', '1011030000', 'validate', 'maxlength', '255', '标题最大长度为{0}!', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011030300', 'title', '1011030000', 'validate', 'required', 'true', '标题不能为空!', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011030400', 'author', '1011030000', 'validate', 'maxlength', '255', '作者最大长度为{0}!', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011030500', 'url', '1011030000', 'validate', 'maxlength', '500', 'URL最大长度为{0}!', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011030600', 'memo', '1011030000', 'validate', 'maxlength', '255', '备注最大长度为{0}!', '1');


INSERT INTO `tb_sys_type` VALUES ('1011050000', 'wechatmsg_validate', '1011000000', '微信响应消息', '微信响应消息验证', 'validate', '5');
INSERT INTO `tb_sys_validation` VALUES ('1011050100', 'title', '1011050000', 'validate', 'required', 'true', '标题不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011050200', 'title', '1011050000', 'validate', 'maxlength', '255', '标题最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011050300', 'author', '1011050000', 'validate', 'maxlength', '150', '作者最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011050400', 'content', '1011050000', 'validate', 'required', 'true', '内容不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011050500', 'musicUrl', '1011050000', 'validate', 'url', 'true', '请输入合法网址！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011050600', 'hqMusicUrl', '1011050000', 'validate', 'url', 'true', '请输入合法网址！', '1');

INSERT INTO `tb_sys_type` VALUES ('1011070000', 'wechatservice_validate', '1011000000', '公众号维护验证', '公众号维护验证', 'validate', '9');
INSERT INTO `tb_sys_validation` VALUES ('1011070100', 'token', '1011070000', 'validate', 'required', 'true', 'token不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070200', 'serviceName', '1011070000', 'validate', 'required', 'true', '名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070300', 'serviceId', '1011070000', 'validate', 'required', 'true', '原始ID不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070400', 'email', '1011070000', 'validate', 'email', 'true', '邮件格式不正确！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070500', 'appScret', '1011070000', 'validate', 'required', 'true', 'appScret不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070600', 'appId', '1011070000', 'validate', 'required', 'true', 'appId不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070700', 'unitId', '1011070000', 'validate', 'required', 'true', '请选择单位！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070800', 'serviceName', '1011070000', 'validate', 'maxlength', '200', '名称最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011070900', 'serviceId', '1011070000', 'validate', 'maxlength', '64', '原始最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011071100', 'token', '1011070000', 'validate', 'maxlength', '200', 'token最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011071200', 'appId', '1011070000', 'validate', 'maxlength', '64', 'appId最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011071300', 'appScret', '1011070000', 'validate', 'maxlength', '300', 'app最大长度为{0}！', '1');


INSERT INTO `tb_sys_type` VALUES ('1011090000', 'wechatautoreply_validate', '1011000000', '自动回复验证', '自动回复验证', 'validate', '9');
INSERT INTO `tb_sys_validation` VALUES ('1011090100', 'replyName', '1011090000', 'validate', 'required', 'true', '名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1011090200', 'replyName', '1011090000', 'validate', 'maxlength', '255', '名称最大长度为{0}！', '1');

