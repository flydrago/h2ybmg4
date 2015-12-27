package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.Goods;

/**
 * 类描述：商品数据库操作接口 作者：侯飞龙 时间：2015年1月24日下午3:49:44 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsDao {

	/**
	 * add
	 */
	public void add(Goods goods);

	/**
	 * update
	 */
	public int update(Goods goods);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * 
	 * @return
	 */
	public Goods get(long id);

	/**
	 * 得到商品的分页列表
	 * 
	 * @param map
	 *            {ifQuery:查询条件 null时不做判断, sortname:排序字段, sortorder:排序方式,
	 *            page:页码, pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 得到商品的总数
	 * 
	 * @param map
	 *            {ifQuery:查询条件 null时不做判断}
	 * @return
	 */
	public long getListRows(Map<String, Object> map);

	/**
	 * 批量更新商品的状态
	 * 
	 * @param map
	 *            {status:状态值（0:上架、1:下架、2:删除）, ids:更新状态的商品Id集合}
	 */
	public void updateStatusByIds(Map<String, Object> map);

	/**
	 * 根据商品编号得到对应商品的数量（用于验证商品编号唯一）
	 * 
	 * @param map
	 *            {number:商品编号, id:商品Id null时不做过滤判断}
	 * @return
	 */
	public long getRowsByNumber(Map<String, Object> map);

	/**
	 * 得到商品选择列表
	 * 
	 * @param map
	 *            {typeCode:商品类型编码 模糊查询null时不做判断, number:商品编码 模糊查询null时不做判断,
	 *            name:商品编码 模糊查询null时不做判断, unitId:单位Id, page:页码,
	 *            pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String, Object>> getSelectListMap(Map<String, Object> map);

	/**
	 * 得到商品选择列表
	 * 
	 * @param map
	 *            {typeCode:商品类型编码 模糊查询null时不做判断, number:商品编码 模糊查询null时不做判断,
	 *            name:商品编码 模糊查询null时不做判断, unitId:单位Id}
	 * @return
	 */
	public long getSelectListRows(Map<String, Object> map);

	/**
	 * 得到商品的代理列表
	 * 
	 * @param map
	 *            {goodsId:商品Id, ifQuery:查询条件, sortname:排序字段, sortorder:排序方式,
	 *            page:页码, pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String, Object>> getUnitListMap(Map<String, Object> map);

	/**
	 * 得到商品的代理列表
	 * 
	 * @param map
	 *            {goodsId:商品Id, ifQuery:查询条件}
	 * @return
	 */
	public long getUnitListRows(Map<String, Object> map);

	/**
	 * 获取已经代理商品id list
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getIdListMap(Map<String, Object> map);
}