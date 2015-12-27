-- ----------------------------
-- 点击率
-- ----------------------------
DROP TABLE IF EXISTS `tb_click_rate`;
CREATE TABLE `tb_click_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `create_date` datetime DEFAULT NULL,
  `zone_code` varchar(20) DEFAULT NULL,
  `goods_price_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=42296 DEFAULT CHARSET=utf8;