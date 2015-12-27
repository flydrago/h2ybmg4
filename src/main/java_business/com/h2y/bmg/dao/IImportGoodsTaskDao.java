package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.ImportGoodsTask;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IImportGoodsTaskDao  
 * 类描述：导入商品任务数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:38:23  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:38:23  
 * 修改备注：  
 * @version
 */
@Component
public interface IImportGoodsTaskDao{

	/**
	 * add
	 */
	public void add(ImportGoodsTask importGoodsTask);
	
	/**
	 * update
	 */
	public void update(ImportGoodsTask importGoodsTask);
	
	/**
	 * get
	 * @return
	 */
	public ImportGoodsTask get(long id);
	
	/**
	 * 得到审核列表
	 * @param map
	 * {bagCode:礼包编码,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * ifQuery:查询条件,
	 * sortorder:排序方式,
	 * sortname:排序字段}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到社和列表总数
	 * @param map
	 * {bagCode:礼包编码,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
}