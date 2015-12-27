select * from tb_sys_type where id like '101607%';
select * from tb_sys_grid_columns where id like '101607%';
select * from tb_sys_query_item where id like '101607%';
select * from tb_sys_validation where id like '101607%';

select * from tb_sys_menu where id like '101607%';
select * from tb_sys_button where id like '101607%';



-- 菜单
INSERT INTO `tb_sys_menu` VALUES ('1016070000', '0', '优惠券管理', '', '', '1601', '1', '0', '1', '1', '0', '0');
INSERT INTO `tb_sys_menu` VALUES ('1016070100', '1016070000', '优惠券管理', 'business/coupons/init.htm', '', '1', '1', '0', '1', '1', '0', '0');
INSERT INTO `tb_sys_menu` VALUES ('1016070200', '1016070000', '优惠券来源管理', 'business/couponssource/init.htm', '', '2', '1', '0', '1', '1', '0', '0');


INSERT INTO `tb_sys_button` VALUES ('1016070101', '1016070100', '查询', null, 'search', 'h2y_search', '2', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070102', '1016070100', '添加', null, 'add', 'h2y_add', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070103', '1016070100', '修改', null, 'modify', 'h2y_modify', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070104', '1016070100', '删除', null, 'delete', 'h2y_delete', '5', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070105', '1016070100', '刷新', null, 'refresh', 'h2y_refresh', '6', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070106', '1016070100', '认领情况', null, 'claim', 'h2y_claim', '1', '1', '1', '0');


INSERT INTO `tb_sys_button` VALUES ('1016070201', '1016070200', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070202', '1016070200', '修改', null, 'modify', 'h2y_modify', '2', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070203', '1016070200', '删除', null, 'delete', 'h2y_delete', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070204', '1016070200', '查询', null, 'search', 'h2y_search', '4', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070205', '1016070200', '刷新', null, 'refresh', 'h2y_refresh', '7', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016070206', '1016070200', '优惠劵维护', null, 'coupons', 'h2y_coupons', '5', '1', '1', '0');




INSERT INTO `tb_sys_type` VALUES ('1016070100', 'couponsManager', '0', '优惠券管理', '优惠券管理', 'validate', '41');
INSERT INTO `tb_sys_type` VALUES ('1016070101', 'coupons_validate', '1016070100', '优惠券验证', '优惠券验证', 'validate', '1');

INSERT INTO `tb_sys_type` VALUES ('1016070200', 'couponsManager', '0', '优惠券管理', '优惠券管理', 'grid', '20');
INSERT INTO `tb_sys_type` VALUES ('1016070201', 'couponsClaim_grid', '1016070200', '优惠券认领表格列', '优惠券认领表格列', 'grid', '3');
INSERT INTO `tb_sys_type` VALUES ('1016070202', 'sourceCounpons_grid', '1016070200', '来源优惠劵表格列', '来源优惠劵表格列', 'grid', '4');
INSERT INTO `tb_sys_type` VALUES ('1016070203', 'couponsSelect_grid', '1016070200', '优惠劵选择表格列', '优惠劵选择表格列', 'grid', '6');


INSERT INTO `tb_sys_type` VALUES ('1016070300', 'couponsManager', '0', '优惠券管理', '优惠券管理', 'query', '20');
INSERT INTO `tb_sys_type` VALUES ('1016070301', 'couponsClaim_query', '1016070300', '优惠券认领查询', '优惠券认领查询', 'query', '1');
INSERT INTO `tb_sys_type` VALUES ('1016070302', 'couponsSelect_query', '1016070300', '优惠劵选择查询', '优惠劵选择查询', 'query', '2');
INSERT INTO `tb_sys_type` VALUES ('1016070303', 'sourceCounpons_query', '1016070300', '来源优惠劵查询', '来源优惠劵查询', 'query', '3');




INSERT INTO `tb_sys_grid_columns` VALUES ('1016070101', '1016070100', '备注', 'MEMO', '200', 'left', 'string', null, '', '1', 'menu', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070102', '1016070100', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'menu', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070103', '1016070100', '截止时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'menu', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070104', '1016070100', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'menu', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070105', '1016070100', '金额', 'COUPONS_BALANCE', '100', 'left', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070106', '1016070100', '卷名', 'COUPONS_NAME', '200', 'left', 'string', null, '', '1', 'menu', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070107', '1016070100', '卷号', 'COUPONS_CODE', '150', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');

INSERT INTO `tb_sys_grid_columns` VALUES ('1016070111', '1016070200', '来员名称', 'SOURCE_NAME', '200', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070112', '1016070200', '来源类型', 'SOURCE_CODE_NAME', '150', 'left', 'string', null, '', '1', 'menu', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070113', '1016070200', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'menu', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070114', '1016070200', '截止时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'menu', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070115', '1016070200', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'menu', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070116', '1016070200', '备注', 'MEMO', '150', 'left', 'string', null, '', '1', 'menu', '6', '1', '1');


INSERT INTO `tb_sys_grid_columns` VALUES ('1016070121', '1016070201', '订单编号', 'ORDER_NO', '200', 'center', 'string', null, '', '1', 'grid', '9', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070122', '1016070201', '昵称', 'NICK_NAME', '150', 'left', 'string', null, '', '1', 'grid', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070123', '1016070201', '来源编码', 'SOURCE_CODE_NAME', '150', 'left', 'string', null, '', '1', 'grid', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070124', '1016070201', '使用时间', 'USE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '8', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070125', '1016070201', '认领时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070126', '1016070201', '账号', 'ACCOUNT', '100', 'center', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070127', '1016070201', '真实名', 'REAL_NAME', '150', 'left', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070128', '1016070201', '认领卷号', 'CLAIM_CODE', '150', 'center', 'string', null, '', '1', 'grid', '2', '1', '1');



INSERT INTO `tb_sys_grid_columns` VALUES ('1016070131', '1016070202', '卷号', 'COUPONS_CODE', '150', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070132', '1016070202', '卷名', 'COUPONS_NAME', '200', 'left', 'string', null, '', '1', 'grid', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070133', '1016070202', '金额', 'COUPONS_BALANCE', '100', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070134', '1016070202', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070135', '1016070202', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'grid', '5', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070136', '1016070202', '截止时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'grid', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070137', '1016070202', '备注', 'MEMO', '200', 'left', 'string', null, '', '1', 'grid', '7', '1', '1');

INSERT INTO `tb_sys_grid_columns` VALUES ('1016070141', '1016070203', '截止时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'grid', '7', '1', '0');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070142', '1016070203', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'grid', '5', '1', '0');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070143', '1016070203', '创建时间', 'CREATE_DATE', '150', 'center', 'string', null, '', '1', 'grid', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070144', '1016070203', '金额', 'COUPONS_BALANCE', '100', 'left', 'string', null, '', '1', 'grid', '3', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070145', '1016070203', '卷名', 'COUPONS_NAME', '200', 'left', 'string', null, '', '1', 'grid', '2', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070146', '1016070203', '卷号', 'COUPONS_CODE', '150', 'left', 'string', null, '', '1', 'grid', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016070147', '1016070203', '备注', 'MEMO', '200', 'left', 'string', null, '', '1', 'grid', '8', '1', '0');




INSERT INTO `tb_sys_validation` VALUES ('1016070101', 'coupons_couponsName', '1016070101', 'validate', 'required', 'true', '优惠券名称不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016070102', 'coupons_couponsName', '1016070101', 'validate', 'maxlength', '255', '优惠券最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016070103', 'coupons_startDate', '1016070101', 'validate', 'required', 'true', '开始时间不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016070104', 'coupons_endDate', '1016070101', 'validate', 'required', 'true', '截止时间不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016070105', 'coupons_memo', '1016070101', 'validate', 'maxlength', '255', '备注最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016070106', 'coupons_couponsBalance', '1016070101', 'validate', 'required', 'true', '金额不能为空！', '1');


INSERT INTO `tb_sys_query_item` VALUES ('1016070101', '1016070100', 'menu', '金额小于', 'coupons_balance', 'coupons_balance_emd', 'int', 'string', '', 'input', '', 'input', '<=', '1', '1', '50', '0', '6', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070102', '1016070100', 'menu', '卷号', 'coupons_code', 'coupons_code', 'int', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070103', '1016070100', 'menu', '金额大于', 'coupons_balance', 'coupons_balance_start', 'int', 'string', '', 'input', '', 'input', '>=', '1', '1', '50', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070104', '1016070100', 'menu', '卷名', 'coupons_name', 'coupons_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '3', '0');

INSERT INTO `tb_sys_query_item` VALUES ('1016070111', '1016070200', 'menu', '类型', 'cs.source_code', 'source_code', 'string', 'string', '', 'dict', 'sourceCode', 'select', '=', '1', '1', '150', '0', '2', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070112', '1016070200', 'menu', '名称', 'cs.source_name', 'source_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '1', '0');


INSERT INTO `tb_sys_query_item` VALUES ('1016070121', '1016070301', 'query', '昵称', 'mu.nick_name', 'nick_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '6', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070122', '1016070301', 'query', '真实名', 'mu.real_name', 'real_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070123', '1016070301', 'query', '账号', 'mu.account', 'account', 'int', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070124', '1016070301', 'query', '卷号', 'cu.claim_code', 'claim_code', 'int', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');


INSERT INTO `tb_sys_query_item` VALUES ('1016070131', '1016070302', 'query', '金额小于', 'coupons_balance', 'coupons_balance_emd', 'int', 'string', '', 'input', '', 'input', '<=', '1', '1', '50', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070132', '1016070302', 'query', '金额大于', 'coupons_balance', 'coupons_balance_start', 'int', 'string', '', 'input', '', 'input', '>=', '1', '1', '50', '0', '3', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070133', '1016070302', 'query', '卷号', 'coupons_code', 'coupons_code', 'int', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070134', '1016070302', 'query', '卷名', 'coupons_name', 'coupons_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '100', '0', '2', '0');

INSERT INTO `tb_sys_query_item` VALUES ('1016070141', '1016070303', 'query', '金额小于', 'c.coupons_balance', 'coupons_balance_emd', 'int', 'string', '', 'input', '', 'input', '<=', '1', '1', '50', '0', '4', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070142', '1016070303', 'query', '卷号', 'c.coupons_code', 'coupons_code', 'int', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070143', '1016070303', 'query', '卷名', 'c.coupons_name', 'coupons_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '150', '0', '2', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016070144', '1016070303', 'query', '金额大于', 'c.coupons_balance', 'coupons_balance_start', 'int', 'string', '', 'input', '', 'input', '>=', '1', '1', '50', '0', '3', '0');

