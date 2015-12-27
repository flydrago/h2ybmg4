package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.AdvertColumn;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertColumnDao  
 * 类描述：广告栏位数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午9:18:47  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午9:18:47  
 * 修改备注：  
 * @version
 */
@Component
public interface IAdvertColumnDao{

	/**
	 * add
	 */
	public void add(AdvertColumn advertColumn);
	
	/**
	 * update
	 */
	public void update(AdvertColumn advertColumn);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public AdvertColumn get(long id);
	
	/**
	 * 得到广告栏位的列表
	 * @param map
	 * {sortname:排序字段（null时默认排序）,
	 * sortorder:排序方式（null时默认排序）}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 根据单位Id，得到当前单位可以分配的栏位列表
	 * @param map
	 * {unitId:单位id，
	 * op:操作类型，（add：添加页面获取列表、modify:修改页面）}
	 * @return
	 */
	public List<Map<String,Object>> getSelectList(Map<String,Object> map);
	
	/**
	 * 得到单位对应的广告栏位数列表
	 * @param map
	 * {unitId:单位Id}
	 * @return
	 */
	public List<Map<String,Object>> getUnitColumnTreeList(Map<String,Object> map);
}