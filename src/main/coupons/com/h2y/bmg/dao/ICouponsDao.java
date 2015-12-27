package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.Coupons;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICouponsDao  
 * 类描述：优惠券数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午3:01:26  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午3:01:26  
 * 修改备注：  
 * @version
 */
@Component
public interface ICouponsDao{

	/**
	 * add
	 */
	public void add(Coupons coupons);
	
	/**
	 * update
	 */
	public void update(Coupons coupons);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public Coupons get(long id);
	
	/**
	 * 获取优惠券列表
	 * @param map
	 * 
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 获取优惠券列表总数
	 * @param map
	 * 
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据日期，得到当前日期最大的编码
	 * @param map
	 * key:date value:日期eg:20150706
	 * @return
	 */
	public String getMaxCodeByDate(Map<String,Object> map);
	
	
	
	/**
	 * 获取优惠券认领列表
	 * @param map
	 * {couponsCode:优惠券编码,
	 * isStart:是否有效（yes、no）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortorder:排序方式,
	 * sortname:排序字段,
	 * ifQuery:查询条件}
	 * @return
	 */
	public List<Map<String,Object>> getClaimListMap(Map<String,Object> map);
	
	
	/**
	 * 获取优惠券认领列表
	 * @param map
	 * {couponsCode:优惠券编码,
	 * isStart:是否有效（yes、no）,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getClaimListRows(Map<String,Object> map);
	
	
	
	
	/**
	 * 根据来源id，获取优惠券列表
	 * @param map
	 * {unitId:单位id,
	 * sourceId:优惠券id,
	 * isStart:是否有效（yes、no）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortorder:排序方式,
	 * sortname:排序字段,
	 * ifQuery:查询条件}
	 * @return
	 */
	public List<Map<String,Object>> getListMapBySourceId(Map<String,Object> map);
	
	/**
	 * 根据来源id，获取优惠券列表总数
	 * @param map
	 * {unitId:单位id,
	 * sourceId:优惠券id,
	 * isStart:是否有效（yes、no）,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRowsBySourceId(Map<String,Object> map);
}