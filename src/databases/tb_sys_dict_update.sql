-- ----------------------------
-- 字典详细表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_dict_detail;
CREATE TABLE tb_sys_dict_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unit_id bigint(20) DEFAULT NULL COMMENT '单位Id',
  dict_main_id bigint(20) DEFAULT NULL COMMENT '数据字典主表Id',
  code varchar(255)  DEFAULT NULL COMMENT '数据字典明细编码',
  value varchar(255)  DEFAULT NULL COMMENT '数据字典明细值',
  memo varchar(255)  DEFAULT NULL COMMENT '数据字典明细备注',
  ord bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '字典详细表';


-- ----------------------------
-- 字典主表
-- ----------------------------
DROP TABLE IF EXISTS tb_sys_dict_main;
CREATE TABLE tb_sys_dict_main (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  parent_id bigint(20) DEFAULT '0' COMMENT '父级Id 第一级为0',
  dict_prefix varchar(30)  DEFAULT NULL COMMENT '字典子父级关系前缀，方便检索（目前只有两级，为以后做扩充使用）',
  dict_code varchar(30)  DEFAULT NULL COMMENT '数据字典编码',
  dict_name varchar(30)  DEFAULT NULL COMMENT '数据字典名称',
  dict_type varchar(50)  DEFAULT 'dict' COMMENT '数据字典类型:json 以json方式解析 dict_value , sql 以sql方式解析dict_value ,param 直接使用dict_value ,dict: 从子表中获得',
  dict_value varchar(1000)  DEFAULT NULL COMMENT '字典数值 根据dict_type决定如何解析 ，当dict_type 为dict时，该值无效',
  dict_ord bigint(10) DEFAULT '100' COMMENT '排序',
  if_sys bigint(10) DEFAULT '0' COMMENT '是否系统参数 ： 1 是，0 单位参数 ,如果系统私有,程序不显示，但可以供其它功能使用',
  if_user_conf bigint(10) DEFAULT '0' COMMENT '是否用户可配置 ： 1 是，0 否 ,如果为1 则可以单独配置菜单供用户配置',
  is_mate int(1) DEFAULT '0' COMMENT '是否元数据 ： 1 是，0 否 ,如果为元数据 则当前数据制作分类使用，不做业务调用',
  is_extends int(1) DEFAULT '0' COMMENT '是否继承 ： 1 是，0 否 ,如果可以继承 则当前数据如果代理单位维护，则取代理数据，反之取平台数据',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '字典主表';