package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CommonImage;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICommonImageDao  
 * 类描述：图片维护数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月8日 下午2:42:37  
 * 修改人：侯飞龙
 * 修改时间：2015年5月8日 下午2:42:37  
 * 修改备注：  
 * @version
 */
@Component
public interface ICommonImageDao{

	/**
	 * add
	 */
	public void add(CommonImage commonImage);
	
	/**
	 * update
	 */
	public void update(CommonImage commonImage);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public CommonImage get(long id);
	
	
	/**
	 * 得到分页列表
	 * @param map
	 * {unitId:单位Id,
	 * typeCode:类型编码,
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到分页列表
	 * @param map
	 * {unitId:单位Id,
	 * typeCode:类型编码,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 得到时间冲突的记录数
	 * @param map
	 * {startDate:起始时间,
	 * endDate:截止时间,
	 * typeCode:类型编码,
	 * unitId:单位id,
	 * op:操作类型(add:添加、modify:修改),
	 * id:id op为modify时，做判断}
	 * @return
	 */
	public long getDateCrossListRows(Map<String,Object> map);
	
	/**
	 * 得到默认的记录数
	 * @param map
	 * {typeCode:类型编码,
	 * unitId:单位id,
	 * op:操作类型(add:添加、modify:修改),
	 * id:id op为modify时，做判断}
	 * @return
	 */
	public long getDefaultListRows(Map<String,Object> map);
	
	/**
	 * 得到最终结果的图片
	 * @param map
	 * {unitId:单位Id,
	 * typeCode:类型编码}
	 * @return
	 */
	public CommonImage getResult(Map<String,Object> map);
}