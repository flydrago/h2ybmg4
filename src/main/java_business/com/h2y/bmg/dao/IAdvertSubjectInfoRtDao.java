package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.AdvertSubjectInfoRt;

/**
 * AdvertSubjectInfoRtDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-10-10
 * email:info@hwttnet.com
 */
@Component
public interface IAdvertSubjectInfoRtDao{

	/**
	 * add
	 */
	public void add(AdvertSubjectInfoRt advertSubjectInfoRt);
	
	/**
	 * update
	 */
	public void update(AdvertSubjectInfoRt advertSubjectInfoRt);
	
	/**
	 * get
	 * @return
	 */
	public AdvertSubjectInfoRt get(long id);
	
	/**
	 * getList
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> params);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public long getListRows(Map<String,Object> map);
	
	
	/**
	 * 获取指定活动主题下面的指定数据类型的data1列表
	 * @param map
	 * {subjectId:主题id,
	 * dataType:数据类型}
	 * @return
	 */
	public List<String> getData1ListBySubjectId(Map<String,Object> map);
	
	
	/**
	 * 获取指定活动主题下面的指定数据类型的data1列表
	 * @param map
	 * {subjectId:主题id,
	 * dataType:数据类型}
	 * @return
	 */
	public List<String> getData2ListBySubjectId(Map<String,Object> map);
	
	
	/**
	 * 根据主题id，得到对应的广告信息数量
	 * @param map
	 * {subjectId:主题id,
	 * dataType:数据类型,
	 * data1:数据1}
	 * @return  id desc,name ,date asc
	 */  
	public long getListRowsBySubjectId(Map<String,Object> map);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<AdvertSubjectInfoRt> list);
	
	
	
	/**
	 * 得到优惠劵列表
	 * {dataType:数据类型（coupons）,
	 * subjectId:主题id,
	 * ifQuery:查询条件 可为null,
	 * sortname:可为null,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getCouponsListMap(Map<String,Object> params);

	/**
	 * 得到优惠劵列表
	 * {dataType:数据类型（coupons）,
	 * subjectId:主题id,
	 * ifQuery:查询条件 可为null}
	 * @return
	 */
	public long getCouponsListRows(Map<String,Object> map);
	
	
	/**
	 * 得到优惠劵列表
	 * {dataType:数据类型（img）,
	 * subjectId:主题id,
	 * ifQuery:查询条件 可为null,
	 * sortname:可为null,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getImgListMap(Map<String,Object> params);

	/**
	 * 得到优惠劵列表
	 * {dataType:数据类型（img）,
	 * subjectId:主题id,
	 * ifQuery:查询条件 可为null}
	 * @return
	 */
	public long getImgListRows(Map<String,Object> map);
}