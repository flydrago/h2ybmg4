package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CommonSubject;

/**
 * 类描述：促销主题数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年2月7日上午11:12:34
 * 邮件：1162040314@qq.com
 */
@Component
public interface ICommonSubjectDao{

	/**
	 * add
	 */
	public void add(CommonSubject commonSubject);
	
	/**
	 * update
	 */
	public void update(CommonSubject commonSubject);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public CommonSubject get(long id);
	
	
	/**
	 * 主题分页列表
	 * @param map
	 * {unitId:单位Id,
	 * ifQuery:查询条件（null时不做判断）,
	 * sortname:排序字段 （可选）,
	 * sortorder:排序方式（可选）,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	/**
	 * 主题总数
	 * @param map
	 * {unitId:单位Id,
	 * ifQuery:查询条件（null时不做判断）}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	
	
	/**
	 * 主题分页列表
	 * @param map
	 * {unitId:单位Id,
	 * activityId:活动Id,
	 * ifQuery:查询条件（null时不做判断）,
	 * sortname:排序字段 （可选）,
	 * sortorder:排序方式（可选）,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getSelectListMap(Map<String,Object> map);
	
	
	/**
	 * 主题总数
	 * @param map
	 * {unitId:单位Id,
	 * activityId:活动Id,
	 * ifQuery:查询条件（null时不做判断）}
	 * @return
	 */
	public long getSelectListRows(Map<String,Object> map);
	
}