package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsType;

/**
 * 类描述：商品类型数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年1月23日下午2:42:25
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsTypeDao{

	/**
	 * add
	 */
	public void add(GoodsType goodsType);
	
	/**
	 * update
	 */
	public void update(GoodsType goodsType);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public GoodsType get(long id);
	
	/**
	 * 得到子级类型的分页列表
	 * @param map
	 * {ifQuery:查询条件 null时不做判断,
	 * id:当前单位id,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到子级类型的总数
	 * @param map
	 * {ifQuery:查询条件 null时不做判断,
	 * id:当前节点id}
	 * @return
	 */  
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据当前id，得到子级树列表
	 * @param id 当前节点Id
	 * @return
	 */
	public List<Map<String,Object>> getChildTreeListById(long id);
	
	/**
	 * 得到自己的行数
	 * @param id 当前节点Id
	 * @return
	 */
	public long getChildRowsById(long id);
	
	
	/**
	 * 得到所有类型的树数据列表
	 * @return
	 */
	public List<Map<String,Object>> getAllTreeList();
	
	/**
	 * 得到子级下拉框列表数据
	 * @param id 当前节点Id
	 * @return
	 */
	public List<Map<String,Object>> getChildSelectListById(long id);
	
	/**
	 * 根据类型编码，得到商品类型
	 * @param typeCode 类型编码
	 * @return
	 */
	public GoodsType getByTypeCode(String typeCode);
	
	/**
	 * 得到类型列表(只可内部使用，未进行防治sql注入)
	 * @param map 
	 * {ids: 1,2,3 类似的字符串}
	 * @return
	 */
	public List<GoodsType> getListByIds(Map<String,Object> map);
	
	
	/**
	 * 根据单位id，得到对应的数列表
	 * @return
	 */
	public List<Map<String,Object>> getTreeListByUnitId(long unitId);
}