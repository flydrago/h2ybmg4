-- ----------------------------
-- 积分缓存表
-- ----------------------------
DROP TABLE IF EXISTS tb_grade_info_cache;
CREATE TABLE tb_grade_info_cache (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  member_user_id bigint(10) DEFAULT NULL,
  type int(2) DEFAULT NULL COMMENT '积分类别',
  grade double DEFAULT NULL COMMENT '积分',
  create_day varchar(8) DEFAULT NULL,
  create_date datetime DEFAULT NULL COMMENT '产生积分时间',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  memo varchar(500) DEFAULT NULL COMMENT '备注',
  category varchar(1) DEFAULT NULL,
  wealth double DEFAULT NULL,
  order_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '积分缓存表';