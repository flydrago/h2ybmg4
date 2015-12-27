package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.ImportBagUserRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IImportBagUserRtDao  
 * 类描述：导入礼包用户关联数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:35:14  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:35:14  
 * 修改备注：  
 * @version
 */
@Component
public interface IImportBagUserRtDao{

	/**
	 * add
	 */
	public void add(ImportBagUserRt importBagUserRt);
	
	/**
	 * update
	 */
	public void update(ImportBagUserRt importBagUserRt);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public ImportBagUserRt get(long id);
	
	
	/**
	 * 得到礼包列表
	 * @param map
	 * {unitId:单位id,
	 * bagCode:礼包编码,
	 * ifQuery:查询条件（非必须）,
	 * sortname:排序字段（非必须）,
	 * sortorder:排序方式（非必须）,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getUserListMap(Map<String,Object> map);
	
	
	/**
	 * 得到礼包总数
	 * @param map
	 * {unitId:单位id,
	 * bagCode:礼包编码,
	 * ifQuery:查询条件（非必须）}
	 * @return
	 */
	public long getUserListRows(Map<String,Object> map);
	
	/**
	 * 更具账号和礼包编码得到对应的行数（用于判断是否是否用户重复导入）
	 * @param map  
	 * {toAccount:接受者账号,
	 * bagCode:礼包编码}
	 * @return 
	 */
	public long getRowsByAccountAndBagCode(Map<String,Object> map);
	
	
	/**
	 * 根据礼包编码，得到对应的接受用户行数
	 * @param bagCode 礼包编码
	 * @return
	 */
	public long getRowsByBagCode(String bagCode);
	
	
	/**
	 * 根据礼包编码和账号，更新商品数量
	 * @param map  
	 * {toAccount:接受者账号,
	 * bagCode:礼包编码,
	 * goodsCount:商品数量}
	 */
	public void updateGoodsCount(ImportBagUserRt importBagUserRt);
	
	/**
	 * 批量添加礼包接受用户
	 * @param list
	 */
	public void addBatch(List<ImportBagUserRt> list);
	
}