package com.h2y.bmg.services;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.ShoplifeCondition;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-29
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IShoplifeConditionService{

	public void add(ShoplifeCondition shoplifeCondition);

	public void delete(long id);

	//public void deleteByIds(List<long> ids);

	public void update(ShoplifeCondition shoplifeCondition);

	public ShoplifeCondition get(long id);

	public List<ShoplifeCondition> getList(ShoplifeCondition shoplifeCondition);

	/**
	 * getListPage
	 * @return
	 */
	public List<ShoplifeCondition> getListPage(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);


	public Map<String, Object> getGridData(HttpServletRequest request);

	/**
	 * 保存
	 * @param op
	 * @param shoplifeCondition
	 */
	public void save(HttpServletRequest request,String op, ShoplifeCondition shoplifeCondition,String dictPath);


}
