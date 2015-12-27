DROP TABLE IF EXISTS tb_bound_user_rt;
CREATE TABLE tb_bound_user_rt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  bound_code varchar(255) DEFAULT NULL COMMENT '绑定的编码 eg:open_id',
  member_id bigint(20) DEFAULT NULL COMMENT '会员id',
  member_account varchar(50) DEFAULT NULL COMMENT '会员账号',
  bound_type varchar(50) DEFAULT NULL COMMENT '绑定类型 wechat:微信、qq：qq号',
  bound_status int(2) DEFAULT 0 COMMENT '绑定状态 0：未绑定、1：已绑定',
  bound_time datetime DEFAULT NULL COMMENT '绑定时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '第三方平台绑定用户关联表';