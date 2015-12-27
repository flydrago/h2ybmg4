package com.h2y.bmg.services;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.UnitSort;




/**
 * 
 * @author sunfj
 *
 */
public interface IUnitSortService{
	
	public void add(UnitSort unitSort);
	
	public void delete(long id);
	
	public void deleteByIds(List<Long> ids);

	public void update(UnitSort unitSort);

	public UnitSort get(long id);
	
	public List<UnitSort> getList(UnitSort unitSort);
	

	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);
	
	//获取树
	public List<Map<String,Object>> getAllTreeList();
	
	
	//获取树列表
	public List<Map<String,Object>> getChildTreeListById(long id);
	
	//获取列表
	public List<Map<String,Object>> getChildSelectListById(long id);
	
	
	//获取列表
		public Map<String,Object> getGridData(HttpServletRequest request);
		
		//根据code获取列表 判断是否重复
		public List<Map<String,Object>> getUnitSortByCode(UnitSort unitSort);
	
		//保存
		public void save(String op, UnitSort unitSort);
		
		//根据parentId获取子列表
		public List<Map<String,Object>> getRowsByParentId(long parentId);
		
		// 选择分类窗口
		public List<Map<String,Object>> getListMapById(Map<String,Object> map);
		
		//保存经营范围
		public String saveUnitSort(HttpServletRequest request,String op,SysUnits sysUnits);
		
		
		// 获取经营范围
		public List<Map<String,Object>> getSortList(SysUnits sysUnits);
}
