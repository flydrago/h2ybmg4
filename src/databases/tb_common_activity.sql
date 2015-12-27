


/*
-- 检测字典
select * from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('common_activity_type','common_activity_goodsLevel','common_activity_rewardType'));
select * from tb_sys_dict_main where dict_code in('common_activity_type','common_activity_goodsLevel','common_activity_rewardType');

select * from tb_sys_menu  where id like '1016%';
select * from tb_sys_button  where id like '1016%';
select * from tb_sys_grid_columns  where  id like '1016%';
select * from tb_sys_type  where  id like '1016%';
select * from tb_sys_query_item  where  id like '1016%';
select * from tb_sys_validation  where  id like '1016%';

-- 删除字典
delete from tb_sys_dict_detail where dict_main_id in(select id from tb_sys_dict_main where dict_code in('common_activity_type','common_activity_goodsLevel','common_activity_rewardType'));
delete from tb_sys_dict_main where dict_code in('common_activity_type','common_activity_goodsLevel','common_activity_rewardType');
*/

insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('common_activity_type', '促销活动', 'dict', '', '278', '1', '0');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_type'), 'index', '主页', '主页', '1');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_type'), 'other', '其他', '其他', '3');


insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('common_activity_goodsLevel', '活动商品级别', 'dict', '', '180', '1', '0');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_goodsLevel'), '4', '一般', '一般', '1');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_goodsLevel'), '3', '较高', '较高', '2');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_goodsLevel'), '2', '高', '高', '3');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_goodsLevel'), '1', '非常高', '非常高', '5');

insert into tb_sys_dict_main(dict_code,dict_name,dict_type,dict_value,dict_ord,if_sys,if_user_conf) values ('common_activity_rewardType', '活动奖励类型', 'dict', '', '184', '1', '0');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_rewardType'), '0', '不奖励', '不奖励', '1');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_rewardType'), '1', '达人豆', '达人豆', '3');
INSERT INTO `tb_sys_dict_detail` (unit_id,dict_main_id,code,value,memo,ord) VALUES ('1', (SELECT id FROM tb_sys_dict_main where dict_code = 'common_activity_rewardType'), '2', '达人币', '达人币', '6');




-- ----------------------------
-- 一般活动（白酒热销、红酒热销等）
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity;
CREATE TABLE tb_common_activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  user_id bigint(20) DEFAULT NULL COMMENT '创建用户Id',
  title varchar(255) DEFAULT NULL COMMENT '标题',
  memo varchar(500) DEFAULT NULL COMMENT '描述',
  activity_type varchar(50) DEFAULT NULL COMMENT '活动类型，index:主页等',
  ord int(10) DEFAULT '0' COMMENT '活动排序',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  if_work int(2) DEFAULT '0' COMMENT '是否启用 1：启用 0：不启用',
  if_delete int(2) DEFAULT '0' COMMENT '是否删除，0：正常、1：删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='一般活动（白酒热销、红酒热销等）';

-- ----------------------------
-- 活动与商品关联
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity_goods;
CREATE TABLE tb_common_activity_goods (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) NOT NULL COMMENT '单位Id',
  activity_id bigint(20) NOT NULL COMMENT '活动Id',
  goods_id bigint(20) NOT NULL COMMENT '商品Id',
  activity_price double(10,2) DEFAULT NULL COMMENT '活动价格',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间',
  end_date datetime DEFAULT NULL COMMENT '结束时间',
  if_work int(2) DEFAULT '0' COMMENT '是否启用 1：启用 0：不启用',
  limit_number int(10) DEFAULT '0' COMMENT '商品上限：0：不做限制',
  remaining_number int(10) DEFAULT '0' COMMENT '商品剩余数',
  reward_type int(10) DEFAULT '0' COMMENT '奖励类型、0：不奖励、1：达人豆、2：达人币',
  reward_number int(10) DEFAULT '0' COMMENT '奖励数量',
  memo varchar(255) DEFAULT NULL COMMENT '描述',
  goods_level int(10) DEFAULT '0' COMMENT '级别：与排序字段结合使用，进而控制优先显示顺序',
  ord bigint(20) DEFAULT '0' COMMENT '商品排序',
  if_start int(10) DEFAULT '0' COMMENT '是否开始，提供扫描用',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='活动与商品的关联';


-- ----------------------------
-- 活动与商品关联历史表
-- ----------------------------
DROP TABLE IF EXISTS tb_common_activity_goods_his;
CREATE TABLE tb_common_activity_goods_his (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_goods_id bigint(20) DEFAULT NULL COMMENT '关联表Id',
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  activity_id bigint(20) DEFAULT NULL COMMENT '活动Id',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品Id',
  activity_price double(10,2) DEFAULT NULL COMMENT '活动价格',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  start_date datetime DEFAULT NULL COMMENT '开始时间',
  end_date datetime DEFAULT NULL COMMENT '结束时间',
  op_date datetime DEFAULT NULL COMMENT '操作时间',
  if_work int(3) DEFAULT '0' COMMENT '是否启用 1：启用 0：不启用',
  limit_number int(10) DEFAULT '0' COMMENT '商品上限：0：不做限制',
  remaining_number int(10) DEFAULT '0' COMMENT '商品剩余数',
  reward_type int(10) DEFAULT '0' COMMENT '奖励类型、0：不奖励、1：达人豆、2：达人币',
  reward_number int(10) DEFAULT '0' COMMENT '奖励数量',
  memo varchar(255) DEFAULT NULL COMMENT '描述',
  goods_level int(10) DEFAULT '0' COMMENT '级别：与排序字段结合使用，进而控制优先显示顺序',
  ord bigint(20) DEFAULT '0' COMMENT '商品排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='活动与商品关联历史表';



INSERT INTO `tb_sys_menu` VALUES ('1016000000', '0', '促销活动', '', '', '136', '1', '0', '1', '1', '1', '0');

INSERT INTO `tb_sys_menu` VALUES ('1016010000', '1016000000', '活动维护', 'business/commonactivity/init.htm', '', '1', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016010100', '1016010000', '添加', null, 'add', 'h2y_add', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016010200', '1016010000', '修改', null, 'modify', 'h2y_modify', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016010300', '1016010000', '删除', null, 'delete', 'h2y_delete', '6', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016010400', '1016010000', '刷新', null, 'refresh', 'h2y_refresh', '7', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1016010100', '1016010000', '排序', 'ORD', '150', 'left', 'string', null, '', '1', 'menu', '18', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016010200', '1016010000', '是否启用', 'IF_WORK', '150', 'left', 'string', null, 'return value==1?\"已启用\":\"未启用\";', '1', 'menu', '14', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016010300', '1016010000', '类型', 'ACTIVITY_TYPE_NAME', '150', 'left', 'string', null, '', '1', 'menu', '6', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016010400', '1016010000', '创建时间', 'CREATE_DATE', '200', 'center', 'string', null, '', '1', 'menu', '11', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016010500', '1016010000', '标题', 'TITLE', '200', 'left', 'string', null, '', '1', 'menu', '2', '1', '1');

INSERT INTO `tb_sys_menu` VALUES ('1016020000', '1016000000', '活动商品', 'business/commonactivitygoods/init.htm', '', '4', '1', '0', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016020100', '1016020000', '查询', null, 'search', 'h2y_search', '1', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016020200', '1016020000', '添加', null, 'add', 'h2y_add', '3', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016020300', '1016020000', '修改', null, 'modify', 'h2y_modify', '6', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016020400', '1016020000', '删除', null, 'delete', 'h2y_delete', '10', '1', '1', '0');
INSERT INTO `tb_sys_button` VALUES ('1016020500', '1016020000', '刷新', null, 'refresh', 'h2y_refresh', '15', '1', '1', '0');

INSERT INTO `tb_sys_grid_columns` VALUES ('1016020100', '1016020000', '排序', 'ORD', '150', 'left', 'string', null, '', '1', 'menu', '13', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020200', '1016020000', '级别', 'GOODS_LEVEL_NAME', '150', 'left', 'string', null, '', '1', 'menu', '15', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020300', '1016020000', '是否启用', 'IF_WORK', '150', 'left', 'string', null, 'return value==1?\"已启用\":\"未启用\";', '1', 'menu', '17', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020400', '1016020000', '商品名称', 'GOODS_NAME', '200', 'left', 'string', null, '', '1', 'menu', '1', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020500', '1016020000', '商品类型', 'GOODS_TYPE_NAME', '150', 'left', 'string', null, '', '1', 'menu', '4', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020600', '1016020000', '活动价格', 'ACTIVITY_PRICE', '150', 'left', 'string', null, '', '1', 'menu', '7', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020700', '1016020000', '开始时间', 'START_DATE', '150', 'center', 'string', null, '', '1', 'menu', '10', '1', '1');
INSERT INTO `tb_sys_grid_columns` VALUES ('1016020800', '1016020000', '结束时间', 'END_DATE', '150', 'center', 'string', null, '', '1', 'menu', '12', '1', '1');


INSERT INTO `tb_sys_query_item` VALUES ('1016020100', '1016020000', 'menu', '商品级别', 'cag.goods_level', 'goods_level_name', 'string', 'string', '', 'dict', 'common_activity_goodsLevel', 'select', '=', '1', '1', '150', '0', '7', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016020200', '1016020000', 'menu', '商品名称', 'g.`name`', 'goods_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '1', '0');
INSERT INTO `tb_sys_query_item` VALUES ('1016020300', '1016020000', 'menu', '商品类型', 'gt.`name`', 'goods_type_name', 'string', 'string', '', 'input', '', 'input', 'like', '1', '1', '200', '0', '4', '0');


INSERT INTO `tb_sys_type` VALUES ('1016000000', 'commonactivity_manager', '0', '促销活动管理', '促销活动管理', 'validate', '33');

INSERT INTO `tb_sys_type` VALUES ('1016010000', 'commonactivity_validate', '1016000000', '促销活动维护', '促销活动维护', 'validate', '2');

INSERT INTO `tb_sys_validation` VALUES ('1016010100', 'memo', '1016010000', 'validate', 'maxlength', '255', '备注最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016010200', 'title', '1016010000', 'validate', 'maxlength', '255', '标题最大长度为{0}！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016010300', 'title', '1016010000', 'validate', 'required', 'true', '标题不能为空！', '1');

INSERT INTO `tb_sys_type` VALUES ('1016020000', 'commonactivitygoods_validate', '1016000000', '活动商品维护', '活动商品维护', 'validate', '4');

INSERT INTO `tb_sys_validation` VALUES ('1016020100', 'activityPrize', '1016020000', 'validate', 'required', 'true', '活动价格不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016020200', 'startDate', '1016020000', 'validate', 'required', 'true', '开始时间不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016020300', 'endDate', '1016020000', 'validate', 'required', 'true', '结束时间不能为空！', '1');
INSERT INTO `tb_sys_validation` VALUES ('1016020400', 'memo', '1016020000', 'validate', 'maxlength', '255', '备注最大长度为{0}！', '1');

