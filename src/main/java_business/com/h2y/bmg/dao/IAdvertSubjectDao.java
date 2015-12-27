package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.AdvertSubject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertSubjectDao  
 * 类描述：广告主题数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:32:38  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:32:38  
 * 修改备注：  
 * @version
 */
@Component
public interface IAdvertSubjectDao{

	/**
	 * add
	 */
	public void add(AdvertSubject advertSubject);
	
	/**
	 * update
	 */
	public void update(AdvertSubject advertSubject);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public AdvertSubject get(long id);
	
	/**
	 * 得到单位的主题列表
	 * @param map
	 * {unitId:单位Id,
	 * ifQuery:查询条件 null时不做判断,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到单位的主题列表总数
	 * @param map
	 * {unitId:单位Id,
	 * ifQuery:查询条件 null时不做判断}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
}