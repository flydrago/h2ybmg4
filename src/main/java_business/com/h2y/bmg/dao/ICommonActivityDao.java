package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CommonActivity;

/**
 * 类描述：活动数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年2月6日下午3:27:57
 * 邮件：1162040314@qq.com
 */
@Component
public interface ICommonActivityDao{

	/**
	 * add
	 */
	public void add(CommonActivity commonActivity);

	/**
	 * update
	 */
	public void update(CommonActivity commonActivity);

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
	public CommonActivity get(long id);


	/**
	 * 得到活动的分页列表
	 * @param map
	 * {unitId: 单位Id,
	 * ifQuery: 查询条件（null时，不做判断）,
	 * sortname: 排序字段,
	 * sortorder: 排序方式,
	 * page: 页码,
	 * pagesize: 页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	/**
	 * 得到活动的总数
	 * @param map
	 * {unitId: 单位Id,
	 * ifQuery: 查询条件（null时，不做判断）}
	 * @return
	 */
	public Long getListRows(Map<String,Object> map);



	/**
	 * 判断秒杀活动是否有时间冲突 
	 * @param map
	 * @return
	 */
	public Long getDateCrossListRows(Map<String,Object> map);


	/**
	 * 得到活动树列表数据
	 * @param map
	 * {unitId:单位Id,
	 * dataType:显示类型 0：商品列表 1：主题图片}
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(Map<String,Object> map);
	
	
	
	/**
	 * 得到活动的分页列表
	 * @param map
	 * {unitId: 单位Id,
	 * ifQuery: 查询条件（null时，不做判断）,
	 * dataType:活动类型（null时，不做判断）,
	 * sortname: 排序字段,
	 * sortorder: 排序方式,
	 * page: 页码,
	 * pagesize: 页显示最大记录数,
	 * activityIdList:活动id列表（排除的活动id）（null时，不做判断）}
	 * @return
	 */
	public List<Map<String,Object>> getSelectListMap(Map<String,Object> map);

	/**
	 * 得到活动的总数
	 * @param map
	 * {unitId: 单位Id,
	 * activityIdList:活动id列表（排除的活动id）（null时，不做判断）,
	 * dataType:活动类型（null时，不做判断）}
	 * @return
	 */
	public Long getSelectListRows(Map<String,Object> map);
}