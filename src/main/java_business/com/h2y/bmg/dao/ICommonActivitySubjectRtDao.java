package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CommonActivitySubjectRt;

/**
 * 类描述：活动主题关联数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年2月7日上午11:42:24
 * 邮件：1162040314@qq.com
 */
@Component
public interface ICommonActivitySubjectRtDao{

	/**
	 * add
	 */
	public void add(CommonActivitySubjectRt commonActivitySubject);
	
	/**
	 * update
	 */
	public void update(CommonActivitySubjectRt commonActivitySubject);
	
	/**
	 * delete
	 */
	public void deleteById(long id);
	
	/**
	 * get
	 * @return
	 */
	public CommonActivitySubjectRt get(long id);
	
	
	/**
	 * 得到列表
	 * @param map
	 * {activityId:活动Id,
	 * unitId:单位Id,
	 * ifQuery:查询条件 null时不做判断,
	 * sortname:排序字段,
	 * sortorder:排序字段}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	/**
	 * 根据活动Id，删除关联关系
	 * @param map
	 * {activityId:活动Id,
	 * list:主题Id集合}
	 */
	public void deleteByActivityIdAndSubjectIds(Map<String,Object> map);
	
	
	/**
	 * 根据活动Id，删除关联关系
	 * @param map
	 * {activityId:活动Id,
	 * subjectId:主题Id}
	 */
	public void deleteByActivityIdAndSubjectId(Map<String,Object> map);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<CommonActivitySubjectRt> list);
	
	
	/**
	 * 根据活动Id，得到活动下面主题的数量
	 * @param activityId 活动Id
	 * @return
	 */
	public long getRowsByActivityId(long activityId);
	
}