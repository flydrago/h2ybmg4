package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.ImportBag;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IImportBagDao  
 * 类描述：导入礼包数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:33:41  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:33:41  
 * 修改备注：  
 * @version
 */
@Component
public interface IImportBagDao{

	/**
	 * add
	 */
	public void add(ImportBag importBag);
	
	/**
	 * update
	 */
	public void update(ImportBag importBag);
	
	/**
	 * get
	 * @return
	 */
	public ImportBag get(long id);
	
	/**
	 * 根据礼包编码，得到对应的礼包信息
	 * @param bagCode 礼包编码 
	 * @return
	 */
	public ImportBag getByBagCode(String bagCode);
	
	/**
	 * 得到礼包列表
	 * @param map
	 * {unitId:单位id,
	 * taskList:当前任务编码列表,
	 * ifQuery:查询条件（非必须）,
	 * sortname:排序字段（非必须）,
	 * sortorder:排序方式（非必须）,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到礼包总数
	 * @param map
	 * {unitId:单位id,
	 * taskList:当前任务编码列表,
	 * ifQuery:查询条件（非必须）}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
}