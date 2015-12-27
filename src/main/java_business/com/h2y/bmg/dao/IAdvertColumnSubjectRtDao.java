package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.AdvertColumnSubjectRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertColumnSubjectRtDao  
 * 类描述：广告栏位主题关联数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月9日 上午9:46:57  
 * 修改人：侯飞龙
 * 修改时间：2015年4月9日 上午9:46:57  
 * 修改备注：  
 * @version
 */
@Component
public interface IAdvertColumnSubjectRtDao{

	/**
	 * add
	 */
	public void add(AdvertColumnSubjectRt advertColumnSubjectRt);
	
	/**
	 * update
	 */
	public void update(AdvertColumnSubjectRt advertColumnSubjectRt);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public AdvertColumnSubjectRt get(long id);
	
	
	/**
	 * 得到栏位对应主题的分页列表信息
	 * @param map
	 * {columnId:栏位Id,
	 * unitId:单位Id,
	 * ifQuery:查询条件 null时不做判断,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到栏位对应主题的分页列表信息
	 * @param map
	 * {columnId:栏位Id,
	 * unitId:单位Id,
	 * ifQuery:查询条件 null时不做判断}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 得到栏位默认主题行数（判断默认主题是否重复添加）
	 * @param map
	 * {columnId:栏位Id,
	 * unitId:单位Id,
	 * op: add:添加、modify:修改,
	 * id:关联id（op为modify时做判断）}
	 * @return
	 */
	public long getDefaultListRows(Map<String,Object> map);
	
	/**
	 * 得到时间冲突的广告栏位关联分页列表信息
	 * @param map
	 * {startDate:开始时间,
	 * endDate:结束时间,
	 * repeatStart:日重复开始时间,
	 * repeatEnd:日重复截止时间,
	 * columnId:栏位Id,
	 * unitId:单位Id,
	 * op:操作类型（add:添加、modify:修改）,
	 * id:栏位主题关联id，op为modify时做判断,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getDateCrossListMap(Map<String,Object> map);
	
	
	/**
	 * 得到时间冲突的广告栏位关联列表总数
	 * @param map
	 * {startDate:开始时间,
	 * endDate:结束时间,
	 * repeatStart:日重复开始时间,
	 * repeatEnd:日重复截止时间,
	 * columnId:栏位Id,
	 * unitId:单位Id,
	 * op:操作类型（add:添加、modify:修改）,
	 * id:栏位主题关联id，op为modify时做判断}
	 * @return
	 */
	public long getDateCrossListRows(Map<String,Object> map);
	
	/**
	 * 根据栏位Id，得到栏位对应的主题信息
	 * @param map
	 * {columnId:栏位Id,
	 * unitId:单位Id}
	 * @return
	 */
	public Map<String,Object> getSubjectInfoByColumnId(Map<String,Object> map);
	
}