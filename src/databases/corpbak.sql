SELECT * FROM tb_goods_mark1;
select * from tb_goods_mark;

/*
select * from tb_goods_type;
select * from tb_goods_type1;
-- 复制商品类型
INSERT tb_goods_type1 (id,type_name,status,ord,create_date,user_id) select a.id,a.`name`,0,a.ord,a.create_date,-2 from tb_goods_type a where a.states = 0

-- 更改商品编码为id
update tb_goods_type1 set type_code = id ;
*/

/*

-- 复制标签
insert tb_goods_mark1 (id,type_id,type_code,mark_name,spe_name,fir_spe_name,status,ord,create_date,memo,user_id) select gm.id,gt.id,gt.type_code,gm.`name`,gm.spe_name,gm.fir_spe_name,0,gm.ord,gm.create_date,gm.`name`,-2 from tb_goods_mark gm join tb_goods_type1 gt ON gm.goods_type_id = gt.id where gm.`status` = 0
select gt.type_name,gm.mark_name from tb_goods_type1 gt join tb_goods_mark1 gm ON gt.id = gm.type_id
*/


/*
-- 商品标签详细
select * from tb_goods_mark_info;
select * from tb_goods_mark_info1;

insert tb_goods_mark_info1 (id,mark_id,name,spe_name,fir_spell_name,status,ord,create_date,memo,user_id) select gmi.id,gm.id,gmi.`name`,gmi.spell_name,gmi.fir_spell_name,0,0,gmi.create_date,'',-2 from tb_goods_mark_info gmi join tb_goods_mark1 gm on gmi.goods_mark_id = gm.id where gmi.`status` = 0 and gm.`status` = 0;
*/





/*
-- 商品标签
select GROUP_CONCAT(CONCAT("[",gmd.goods_mark_id,"]") SEPARATOR '') from tb_goods_mark_detail gmd where gmd.goods_id = 27 GROUP BY goods_id;
-- 商品标签详细
select GROUP_CONCAT(CONCAT("[",gmd.goods_mark_info_id,"]") SEPARATOR '') from tb_goods_mark_detail gmd where gmd.goods_id = 27 GROUP BY goods_id;

select * from tb_goods_mark_detail gmd where gmd.goods_id = 27;

-- 设置商品标签主表id
update tb_goods1 g set g.mark_ids = (select GROUP_CONCAT(CONCAT("[",gmd.goods_mark_id,"]") SEPARATOR '') from tb_goods_mark_detail gmd where gmd.goods_id =  g.id GROUP BY gmd.goods_id)

-- 设置商品标签详细id
update tb_goods1 g set g.mark_info_ids = (select GROUP_CONCAT(CONCAT("[",gmd.goods_mark_info_id,"]") SEPARATOR '') from tb_goods_mark_detail gmd where gmd.goods_id =  g.id GROUP BY gmd.goods_id)

*/


/*

select * from tb_goods_mark_detail1;

select * from tb_goods1

update tb_goods_mark_detail1 gmd set type_code = (select gds_code from tb_goods1 where id = gmd.goods_id)

insert tb_goods_mark_detail1 (id,goods_id,mark_id,mark_info_id,version) select gmd.id,gmd.goods_id,gmd.goods_mark_id,gmd.goods_mark_info_id,1 from tb_goods_mark_detail gmd join tb_goods g ON gmd.goods_id = g.id and g.`status` = 0

*/








