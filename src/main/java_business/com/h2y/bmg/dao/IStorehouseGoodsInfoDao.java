package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.StorehouseGoodsInfo;

/**
 * 类描述：仓库商品信息数据库操作接口 作者：侯飞龙 时间：2015年1月31日下午2:34:53 邮件：1162040314@qq.com
 */
@Component
public interface IStorehouseGoodsInfoDao {

	/**
	 * add
	 */
	public void add(StorehouseGoodsInfo storehouseGoodsInfo);

	/**
	 * update
	 */
	public void update(StorehouseGoodsInfo storehouseGoodsInfo);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * 
	 * @return
	 */
	public StorehouseGoodsInfo get(long id);

	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	public long getListRows(Map<String, Object> map);

	/**
	 * 根据商品Id，得到对应的仓库商品信息
	 * 
	 * @param map
	 *            {goodsId:商品Id, storehouseId:仓库Id}
	 * @return
	 */
	public StorehouseGoodsInfo getByGoodsPriceId(Map<String, Object> map);

	/**
	 * 得到仓库历史列表
	 * 
	 * @param map
	 *            {unitId:单位Id, storehouseId:仓库Id, goodsId:商品Id,
	 *            ifQuery:查询条件（null时不做判断）, sortname:排序字段, sortorder:排序方式,
	 *            page:页码, pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String, Object>> getDetailListMap(Map<String, Object> map);

	/**
	 * 得到仓库历史总数
	 * 
	 * @param map
	 *            {unitId:单位Id, storehouseId:仓库Id, goodsId:商品Id,
	 *            ifQuery:查询条件（null时不做判断）}
	 * @return
	 */
	public long getDetailListRows(Map<String, Object> map);
}