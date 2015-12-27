-- ----------------------------
-- Table structure for `tb_trace_number_section` 号段生成表
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_number_section`;
CREATE TABLE `tb_trace_number_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_id` bigint(20) DEFAULT NULL COMMENT '来源id(平台/省级代理id)',
  `to_id` bigint(20) DEFAULT NULL COMMENT '目标id(null/省级代理id/供应商)', 
  `parent_id` bigint(20) DEFAULT NULL COMMENT '号段对应的上级id', 
  `start_no` bigint(20) DEFAULT NULL COMMENT '号段起始值',
  `end_no` bigint(20) DEFAULT NULL COMMENT '号段结束值',
  `spec` int(11) DEFAULT NULL COMMENT '规格',
  `if_receive` int(11) DEFAULT NULL COMMENT '录入标志',
  `if_enable` int(11) DEFAULT NULL COMMENT '启用标志',
  `if_create` int(11) DEFAULT NULL COMMENT '生成标志',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(50) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `opt_flag` int(11) DEFAULT NULL COMMENT '操作标志（0分配1录入2启用3平台-省4省-供应商）',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注(此处记录操作描述)',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1--省级代理名称',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2---供应商名称',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tb_trace_qrcode_serial` 瓶箱二维码关联表
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_qrcode_serial`;
CREATE TABLE `tb_trace_qrcode_serial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL COMMENT '对应的号段id',
  `box_qrcode_no` bigint(20) DEFAULT NULL COMMENT '箱二维码编号',
  `bottle_qrcode_no` bigint(20) DEFAULT NULL COMMENT '瓶二维码编号',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省级代理id',
  `privide_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `privide_name` varchar(50) DEFAULT NULL COMMENT '供应商名称',
  `if_receive` int(11) DEFAULT 1 COMMENT '录入标志',
  `if_enable` int(11) DEFAULT 1 COMMENT '启用标志',
  `relate_flag` int(11) DEFAULT 0 COMMENT '关联操作标志（初始化0、纠正关联1）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(50) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `tb_trace_circulate_record` 溯源表 流通记录
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_circulate_record`;
CREATE TABLE `tb_trace_circulate_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `box_qrcode_no` bigint(20) DEFAULT NULL COMMENT '箱二维码编号',
  `bottle_qrcode_no` bigint(20) DEFAULT NULL COMMENT '瓶二维码编号',
  `pack_flag` int(11) DEFAULT NULL COMMENT '箱瓶记录来源区别',
  `spec` int(11) DEFAULT NULL COMMENT '包装规格',
  `create_date` datetime DEFAULT NULL COMMENT '扫码时间',
  `circulate_flag` int(11) DEFAULT NULL COMMENT '流通标志出库/入库/配送/接收',
  `show_flag` int(11) DEFAULT 1 COMMENT '溯源标志是否显示到溯源中',
  `new_record_flag` int(11) DEFAULT NULL COMMENT '最新记录标志',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(20) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 由【二维码编号 记录标志 出库/入库标志 供应商/加盟商id】4个条件区分是否是重复录入的问题

-- ----------------------------
-- Table structure for `tb_trace_provider_item` 供应商信息管理
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_provider_item`;
CREATE TABLE `tb_trace_provider_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '供应商id',
  `provider_name` varchar(100) DEFAULT NULL COMMENT '供应商名称',
  `legal_user` varchar(50) DEFAULT NULL COMMENT '法人',
  `contact_telephone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `company_address` varchar(100) DEFAULT NULL COMMENT '公司住址',
  `beneficiary_account` varchar(50) DEFAULT NULL COMMENT '收款账号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(20) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `if_enable` int(11) DEFAULT NULL COMMENT '供应商禁用标志（禁止后，供应商商品将不能入库）',
  `parent_id` bigint(20) DEFAULT NULL  COMMENT '供应商隶属关系id',
  `zone_code` varchar(50) DEFAULT NULL COMMENT '区域码',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tb_trace_provider_pact` 供应商合同管理
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_provider_pact`;
CREATE TABLE `tb_trace_provider_pact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provide_id` bigint(20) NOT NULL COMMENT '供应商id',
  `provider_name` varchar(100) DEFAULT NULL COMMENT '供应商名称',
  `sign_date` datetime DEFAULT NULL COMMENT '签订日期',
  `start_date`datetime DEFAULT NULL COMMENT '合同开始时间',
  `end_date`datetime DEFAULT NULL COMMENT '合同结束时间',
  `if_enable` bigint(20) DEFAULT NULL COMMENT '是否有效',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(20) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `tb_trace_scanning_path` 合同扫描件路径
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_scanning_path`;     ----------------parent_id
CREATE TABLE `tb_trace_scanning_path` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provide_id` bigint(20) NOT NULL COMMENT '供应商Id',
  `parent_id` bigint(20) NOT NULL COMMENT '对应合同id',
  `pact_file_name` varchar(255) DEFAULT NULL COMMENT '合同文件名称',
  `disk_file_name` varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  `root_path` varchar(255) DEFAULT NULL COMMENT '根路径',
  `relative_path` varchar(255) DEFAULT NULL COMMENT '相对路径',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_suffix` varchar(20) DEFAULT NULL COMMENT '文件后缀（扩展名）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(20) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `if_enable` int(11) DEFAULT NULL  COMMENT '是否启用',
  `ord` int(11) DEFAULT NULL  COMMENT '图片顺序',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `tb_trace_provider_goods` 供应商商品管理
-- ----------------------------
DROP TABLE IF EXISTS `tb_trace_provider_goods`;
CREATE TABLE `tb_trace_provider_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provide_id` bigint(20) NOT NULL COMMENT '供应商Id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `batch_no` int(11) DEFAULT NULL COMMENT '批号',
  `privide_date` datetime DEFAULT NULL COMMENT '供货时间',
  `start_date` datetime DEFAULT NULL COMMENT '起始时间',
  `end_date` datetime DEFAULT '9999-12-31' COMMENT '结束时间',
  `if_enable` int(11) DEFAULT NULL COMMENT '启用标志',
  `opt_user_id` bigint(20) DEFAULT NULL  COMMENT '操作人员id',
  `opt_user_account` varchar(20) DEFAULT NULL  COMMENT '操作账号',
  `opt_user_name` varchar(50) DEFAULT NULL  COMMENT '操作人员名称',
  `data_1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `data_2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `data_3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


