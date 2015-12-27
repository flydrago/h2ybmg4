

select * from tb_sys_type where id like '101605%';
select * from tb_sys_grid_columns where id like '101605%';
select * from tb_sys_query_item where id like '101605%';
select * from tb_sys_validation where id like '101605%';

select * from tb_sys_menu where id like '101605%';
select * from tb_sys_button where id like '101605%';

INSERT INTO `tb_sys_menu` VALUES ('1016050000', '0', '酒库管理', '', '', '1600', '1', '0', '1', '0', '0', '0');
INSERT INTO `tb_sys_menu` VALUES ('1016050100', '1016050000', '待审核礼包', 'business/importbag/startInit.htm', '', '1', '1', '0', '0', '0', '0', '0');
INSERT INTO `tb_sys_menu` VALUES ('1016050200', '1016050000', '一级审核', 'business/importbag/checkInit1.htm', '', '2', '1', '0', '0', '0', '0', '0');
INSERT INTO `tb_sys_menu` VALUES ('1016050300', '1016050000', '二级审核', 'business/importbag/checkInit2.htm', '', '3', '1', '0', '0', '0', '0', '0');
INSERT INTO `tb_sys_menu` VALUES ('1016050400', '1016050000', '通过礼包', 'business/importbag/endInit.htm', '', '5', '1', '0', '0', '0', '0', '0');


INSERT INTO `tb_sys_button` VALUES ('1016050101', '1016050100', '添加', null, 'add', 'h2y_add', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050102', '1016050100', '修改', null, 'modify', 'h2y_modify', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050103', '1016050100', '删除', null, 'delete', 'h2y_delete', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050104', '1016050100', '刷新', null, 'refresh', 'h2y_refresh', '10', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050105', '1016050100', '查询', null, 'search', 'h2y_search', '8', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050106', '1016050100', '接受用户', null, 'user', 'h2y_user', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050107', '1016050100', '审核过程', null, 'track', 'h2y_track', '2', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050108', '1016050100', '发起', null, 'start', 'h2y_start', '0', '1', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1016050201', '1016050200', '接受用户', null, 'user', 'h2y_user', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050202', '1016050200', '审核', null, 'check', 'h2y_check', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050203', '1016050200', '刷新', null, 'refresh', 'h2y_refresh', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050204', '1016050200', '查询', null, 'search', 'h2y_search', '0', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050205', '1016050200', '审核过程', null, 'track', 'h2y_track', '2', '1', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1016050301', '1016050300', '查询', null, 'search', 'h2y_search', '0', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050302', '1016050300', '接受用户', null, 'user', 'h2y_user', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050303', '1016050300', '审核', null, 'check', 'h2y_check', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050304', '1016050300', '刷新', null, 'refresh', 'h2y_refresh', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050305', '1016050300', '审核过程', null, 'track', 'h2y_track', '2', '1', '1', '0');

INSERT INTO `tb_sys_button` VALUES ('1016050401', '1016050400', '查询', null, 'search', 'h2y_search', '0', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050402', '1016050400', '接受用户', null, 'user', 'h2y_user', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050403', '1016050400', '审核过程', null, 'track', 'h2y_track', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050404', '1016050400', '刷新', null, 'refresh', 'h2y_refresh', '6', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016050405', '1016050400', '同步', null, 'syn', 'h2y_syn', '2', '1', '1', '0');


INSERT INTO `tb_sys_type` VALUES ('1016050000', 'spiritroom_manaer', '0', '酒库管理', '酒库管理', 'grid', '18');

INSERT INTO `tb_sys_type` VALUES ('1016050100', 'importbag_grid', '1016050000', '礼包列表', '礼包列表', 'grid', '1');
INSERT INTO `tb_sys_type` VALUES ('1016050200', 'bag_user_gird', '1016050000', '礼包接受用户列表', '礼包接受用户列表', 'grid', '3');
INSERT INTO `tb_sys_type` VALUES ('1016050300', 'importbagTrack_grid', '1016050000', '礼包审核过程', '礼包审核过程', 'grid', '5');

INSERT INTO `tb_sys_type` VALUES ('1016050400', 'spiritroom_manager', '0', '酒库管理', '酒库管理', 'validate', '39');
INSERT INTO `tb_sys_type` VALUES ('1016050500', 'importbag_validate', '1016050400', '礼包维护验证', '礼包维护验证', 'validate', '1');
INSERT INTO `tb_sys_type` VALUES ('1016050600', 'importbaguser_validate', '1016050400', '礼包用户验证', '礼包用户验证', 'validate', '3');

INSERT INTO `tb_sys_type` VALUES ('1016050700', 'spiritroom', '0', '酒库管理', '酒库管理', 'query', '17');
INSERT INTO `tb_sys_type` VALUES ('1016050800', 'bag_user_query', '1016050700', '礼包用户查询', '礼包用户查询', 'query', '0');
INSERT INTO `tb_sys_type` VALUES ('1016050900', 'importbag_query', '1016050700', '礼包查询', '礼包查询', 'query', '2');


INSERT INTO `tb_sys_grid_columns` VALUES ('1016050101', '1016050100', '当前任务', 'currentTask', '150', 'left', 'string', null, 'if(value==\'start\'){ 	return \"待发送\"; }else if(value==\'check1\'){ 	return \"一级审核\"; }else if(value==\'check2\'){ 	return \"二级审核\" }', '1', 'grid', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050102', '1016050100', '备注', 'memo', '150', 'left', 'string', null, '', '1', 'grid', '8', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050103', '1016050100', '送礼账号', 'account', '150', 'left', 'string', null, '', '1', 'grid', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050104', '1016050100', '礼包名称', 'bagName', '150', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050105', '1016050100', '单价', 'singlePrice', '100', 'left', 'string', null, '', '1', 'grid', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050106', '1016050100', '商品名称', 'goodsNickName', '300', 'left', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050107', '1016050100', '礼包编码', 'bagCode', '200', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050108', '1016050100', '业务人员', 'businessUser', '100', 'left', 'string', null, '', '1', 'grid', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050109', '1016050100', '状态', 'status', '150', 'left', 'string', null, 'if(value==\'0\'){ 	return \"待发送\"; }else if(value==\'2\'){ 	return \"审核通过\"; }else if(value==\'3\'){ 	return \"礼包已同步\" }else{return \"待审核\"}', '1', 'grid', '10', '1', '1');


INSERT INTO `tb_sys_grid_columns` VALUES ('1016050201', '1016050200', '更新时间', 'updateDate', '150', 'center', 'string', null, '', '1', 'grid', '11', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050202', '1016050200', '创建时间', 'createDate', '150', 'center', 'string', null, '', '1', 'grid', '9', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050203', '1016050200', '商品数量', 'goodsCount', '100', 'left', 'string', null, '', '1', 'grid', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050204', '1016050200', '账号', 'toAccount', '150', 'center', 'string', null, '', '1', 'grid', '1', '1', '1');

INSERT INTO `tb_sys_grid_columns` VALUES ('1016050301', '1016050300', '任务名称', 'taskValue', '200', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050302', '1016050300', '审核结果', 'resultValue', '150', 'left', 'string', null, '', '1', 'grid', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050303', '1016050300', '审核时间', 'createDate', '150', 'center', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016050304', '1016050300', '备注', 'memo', '300', 'left', 'string', null, '', '1', 'grid', '4', '1', '1');


INSERT INTO `tb_sys_grid_columns` VALUES ('1016050205', '1016050200', '状态', 'status', '100', 'left', 'string', null, 'if(value==\'0\'){ 	return \"待签收\"; }else if(value==\'1\'){ 	return \"已签收\"; }', '1', 'grid', '12', '1', '1');







INSERT INTO `tb_sys_query_item` VALUES ('1016050901', '1016050900', 'query', '人员手机号', 'business_mobile', 'business_mobile', 'string', 'string', '', 'input', '', 'input', 'like', '2', '1', '100', '0', '6', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050902', '1016050900', 'query', '业务人员', 'business_user', 'business_user', 'string', 'string', '', 'input', '', 'input', 'like', '2', '1', '100', '0', '5', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050903', '1016050900', 'query', '商品名称', 'goods_nick_name', 'goods_nick_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050904', '1016050900', 'query', '送礼人账号', 'account', 'account', 'string', 'string', '', 'input', '', 'input', 'like', '2', '1', '100', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050905', '1016050900', 'query', '礼包编码', 'bag_code', 'bag_code', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '2', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050906', '1016050900', 'query', '礼包名称', 'bag_name', 'bag_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');

INSERT INTO `tb_sys_query_item` VALUES ('1016050801', '1016050800', 'query', '状态', 'bu.status', 'status', 'string', 'int', '', 'json', '[{\"text\":\"--请选择--\",\"value\":\"\"},{\"text\":\"待签收\",\"value\":0},{\"text\":\"已签收\",\"value\":1}]', 'select', '=', '1', '1', '100', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050802', '1016050800', 'query', '账号', 'bu.to_account', 'to_account', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016050803', '1016050800', 'query', '数量', 'bu.goods_count', 'goods_count', 'int', 'int', '', 'input', '', 'input', '=', '1', '1', '150', '0', '2', '0');





INSERT INTO `tb_sys_validation` VALUES ('1016050501', 'singlePrice', '1016050500', 'validate', 'required', 'true', '单价不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050502', 'businessUser', '1016050500', 'validate', 'maxlength', '50', '最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050503', 'businessMobile', '1016050500', 'validate', 'cellphone', 'true', '请输入合法的手机号！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050504', 'businessUser', '1016050500', 'validate', 'required', 'true', '业务员不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050505', 'bagName', '1016050500', 'validate', 'maxlength', '200', '最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050506', 'bagName', '1016050500', 'validate', 'required', 'true', '礼包名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050507', 'businessMobile', '1016050500', 'validate', 'required', 'true', '手机号不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050508', 'account', '1016050500', 'validate', 'required', 'true', '送礼人账号不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050509', 'account', '1016050500', 'validate', 'cellphone', 'true', '送礼人账号请输入手机号！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050511', 'memo', '1016050500', 'validate', 'maxlength', '200', '备注最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050601', 'toAccount', '1016050600', 'validate', 'required', 'true', '账号不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016050602', 'toAccount', '1016050600', 'validate', 'cellphone', 'true', '账号只能为手机号！', '1');
