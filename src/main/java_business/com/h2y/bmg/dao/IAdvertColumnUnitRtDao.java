package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.AdvertColumnUnitRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertColumnUnitRtDao  
 * 类描述：广告栏位单位关联  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午10:38:24  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午10:38:24  
 * 修改备注：  
 * @version
 */
@Component
public interface IAdvertColumnUnitRtDao{

	/**
	 * add
	 */
	public void add(AdvertColumnUnitRt advertColumnUnitRt);
	
	/**
	 * update
	 */
	public void update(AdvertColumnUnitRt advertColumnUnitRt);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public AdvertColumnUnitRt get(long id);
	
	/**
	 * 得到广告栏位的列表
	 * @param map
	 * {unitId：单位id，
	 * sortname：排序字段，
	 * sortorder：排序方式}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 根据单位id，得到单位对应的栏位数量（用于判断单位栏位是否重复添加）
	 * @param map
	 * {unitId:单位Id,
	 * columnId:栏位Id,
	 * id:为null时，不做判断}
	 * @return
	 */
	public long getColumnRowsByUnitId(Map<String,Object> map);
}