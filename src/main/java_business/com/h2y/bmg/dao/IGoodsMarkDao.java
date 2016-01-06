package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsMark;

/**
 * 类描述：   商品标签数据库接口
 * 作者：侯飞龙
 * 时间：2015年1月24日上午9:16:43
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsMarkDao{

	/**
	 * add
	 */
	public void add(GoodsMark goodsMark);
	
	/**
	 * update
	 */
	public void update(GoodsMark goodsMark);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public GoodsMark get(long id);
	
	/**
	 * 得到商品类型下面标签分页列表
	 * @param map
	 * {typeId:商品类型id,
	 * ifQuery:查询条件 null时不做判断,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页码}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	/**
	 * 得到商品类型下面标签总数
	 * @param map
	 * {typeId:商品类型id,
	 * ifQuery:查询条件 null时不做判断}
	 * @return 
	 */  
	public long getListRows(Map<String,Object> map);
	
	
	/**
	 * 根据类型编码，得到下面的标签数量
	 * @param typeCode 类型编码
	 * @return
	 */
	public long getRowsByTypeCode(Long typeCode);
	
	/**
	 * 根据类型编码得到对应标签的树数据
	 * @param typeCode 类型编码
	 * @return
	 */
	public List<Map<String,Object>> getMarkTreeListByTypeCode(String typeCode);
}