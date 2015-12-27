
select * from tb_sys_type where id like '101609%';
select * from tb_sys_grid_columns where id like '101609%';
select * from tb_sys_query_item where id like '101609%';

INSERT INTO `tb_sys_type` VALUES ('1016090100', 'mmsghis_manager', '0', '推送消息管理', '推送消息管理', 'grid', '22');
INSERT INTO `tb_sys_type` VALUES ('1016090101', 'mmsghis_delivery_grid', '1016090100', '配送端推送消息列', '配送端推送消息列', 'grid', '1');
INSERT INTO `tb_sys_type` VALUES ('1016090102', 'mmsghis_app_grid', '1016090100', '客户端推送消息列', '客户端推送消息列', 'grid', '2');
INSERT INTO `tb_sys_type` VALUES ('1016090103', 'mmsghis_pc_grid', '1016090100', 'PC推送消息列', 'PC推送消息列', 'grid', '3');

INSERT INTO `tb_sys_grid_columns` VALUES ('1016090101', '1016090101', '推送确认', 'RECEIPT_MARK', '100', 'center', 'string', null, 'if(value==\'0\'){ 	return \"未确认\"; }else if(value==\'1\'){ 	return \"已确认\"; }', '1', 'grid', '9', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090102', '1016090101', '确认时间', 'RECEIPT_DATE', '150', 'center', 'string', null, '', '1', 'grid', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090103', '1016090101', '发送时间', 'SEND_DATE', '150', 'center', 'string', null, '', '1', 'grid', '8', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090104', '1016090101', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090105', '1016090101', '描述', 'DESCRIBTION', '300', 'left', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090106', '1016090101', '标题', 'TITLE', '150', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090107', '1016090101', '账号', 'ACCOUNT', '100', 'center', 'string', null, '', '1', 'grid', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090108', '1016090101', '姓名', 'NAME', '100', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090109', '1016090101', '终端', 'TOPIC', '100', 'left', 'string', null, 'if(value==\'baidupd\'){ 	return \"安卓\"; }else if(value==\'iosd\'){ 	return \"苹果\"; }', '1', 'grid', '6', '1', '1');


INSERT INTO `tb_sys_grid_columns` VALUES ('1016090131', '1016090102', '确认时间', 'RECEIPT_DATE', '150', 'center', 'string', null, '', '1', 'grid', '11', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090132', '1016090102', '推送确认', 'RECEIPT_MARK', '100', 'center', 'string', null, 'if(value==\'0\'){ 	return \"未确认\"; }else if(value==\'1\'){ 	return \"已确认\"; }', '1', 'grid', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090133', '1016090102', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '8', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090134', '1016090102', '发送时间', 'SEND_DATE', '150', 'center', 'string', null, '', '1', 'grid', '9', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090135', '1016090102', '描述', 'DESCRIBTION', '300', 'left', 'string', null, '', '1', 'grid', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090136', '1016090102', '标题', 'TITLE', '150', 'left', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090137', '1016090102', '昵称', 'NICK_NAME', '100', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090138', '1016090102', '账号', 'ACCOUNT', '100', 'center', 'string', null, '', '1', 'grid', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090139', '1016090102', '真实名', 'REAL_NAME', '100', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090140', '1016090102', '终端', 'TOPIC', '100', 'center', 'string', null, 'if(value==\'baidupc\'){ 	return \"安卓\"; }else if(value==\'iosc\'){ 	return \"苹果\"; }', '1', 'grid', '7', '1', '1');



INSERT INTO `tb_sys_grid_columns` VALUES ('1016090151', '1016090103', '描述', 'DESCRIBTION', '300', 'left', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090152', '1016090103', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090153', '1016090103', '发送时间', 'SEND_DATE', '150', 'center', 'string', null, '', '1', 'grid', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090154', '1016090103', '发送确认', 'RECEIPT_MARK', '100', 'center', 'string', null, 'if(value==\'0\'){ 	return \"未确认\"; }else if(value==\'1\'){ 	return \"已确认\"; }', '1', 'grid', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090155', '1016090103', '姓名', 'NAME', '100', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090156', '1016090103', '确认时间', 'RECEIPT_DATE', '150', 'center', 'string', null, '', '1', 'grid', '8', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090157', '1016090103', '标题', 'TITLE', '150', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016090158', '1016090103', '账号', 'ACCOUNT', '100', 'left', 'string', null, '', '1', 'grid', '2', '1', '1');


INSERT INTO `tb_sys_type` VALUES ('1016090200', 'mmsghis_manager', '0', '推送消息', '推送消息', 'query', '22');
INSERT INTO `tb_sys_type` VALUES ('1016090201', 'mmsghis_delivery_query', '1016090200', '配送端查询', '配送端查询', 'query', '1');
INSERT INTO `tb_sys_type` VALUES ('1016090202', 'mmsghis_app_query', '1016090200', '客户端查询', '客户端查询', 'query', '2');
INSERT INTO `tb_sys_type` VALUES ('1016090203', 'mmsghis_pc_query', '1016090200', 'PC端查询', 'PC端查询', 'query', '3');



INSERT INTO `tb_sys_query_item` VALUES ('1016090101', '1016090201', 'query', '标题', 'mh.title', 'title', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090102', '1016090201', 'query', '推送确认', 'mh.receipt_mark', 'receipt_mark', 'string', 'string', '', 'json', '[{\"text\":\"--请选择--\",\"value\":\"\"},{\"text\":\"未确认\",\"value\":0},{\"text\":\"已确认\",\"value\":1}]', 'select', '=', '1', '1', '100', '0', '5', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090103', '1016090201', 'query', '终端', 'mh.topic', 'topic', 'string', 'string', '', 'json', '[{\"text\":\"--请选择--\",\"value\":\"\"},{\"text\":\"安卓\",\"value\":\"baidupd\"},{\"text\":\"苹果\",\"value\":\"iosd\"}]', 'select', '=', '1', '1', '100', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090104', '1016090201', 'query', '姓名', 'dus.name', 'name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090105', '1016090201', 'query', '账号', 'dus.account', 'account', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '2', '0');

INSERT INTO `tb_sys_query_item` VALUES ('1016090121', '1016090202', 'query', '终端', 'mh.topic', 'topic', 'string', 'string', '', 'json', '[{\"text\":\"--请选择--\",\"value\":\"\"},{\"text\":\"安卓\",\"value\":\"baidupc\"},{\"text\":\"苹果\",\"value\":\"iosc\"}]', 'select', '=', '1', '1', '100', '0', '5', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090122', '1016090202', 'query', '推送确认', 'mh.receipt_mark', 'receipt_mark', 'string', 'string', '', 'json', '[{\"text\":\"--请选择--\",\"value\":\"\"},{\"text\":\"未确认\",\"value\":0},{\"text\":\"已确认\",\"value\":1}]', 'select', '=', '1', '1', '100', '0', '6', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090123', '1016090202', 'query', '标题', 'mh.title', 'title', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090124', '1016090202', 'query', '姓名', 'mu.real_name', 'real_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090125', '1016090202', 'query', '账号', 'mu.account', 'account', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090126', '1016090202', 'query', '昵称', 'mu.nick_name', 'nick_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '2', '0');

INSERT INTO `tb_sys_query_item` VALUES ('1016090131', '1016090203', 'query', '标题', 'mh.title', 'title', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016090132', '1016090203', 'query', '推送确认', 'mh.receipt_mark', 'receipt_mark', 'string', 'string', '', 'json', '[{\"text\":\"--请选择--\",\"value\":\"\"},{\"text\":\"未确认\",\"value\":0},{\"text\":\"已确认\",\"value\":1}]', 'select', '=', '1', '1', '100', '0', '2', '0');



