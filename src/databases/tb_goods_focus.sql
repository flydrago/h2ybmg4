-- ----------------------------
-- 用户商品关注(关注、取消关注)2015-01-15
-- ----------------------------
DROP TABLE IF EXISTS tb_goods_focus;
CREATE TABLE tb_goods_focus (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  zone_code varchar(10) NOT NULL COMMENT '区域编码',
  member_id bigint(20) NOT NULL COMMENT '会员Id',
  goods_id bigint(20) NOT NULL COMMENT '关注商品Id',
  focus_date datetime NOT NULL COMMENT '关注时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


