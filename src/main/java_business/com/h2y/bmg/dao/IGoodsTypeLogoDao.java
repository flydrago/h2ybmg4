package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsTypeLogo;

/**
 * 类描述：商品类型Logo维护   
 * 作者：侯飞龙
 * 时间：2015年2月13日下午2:43:13
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsTypeLogoDao{

	/**
	 * add
	 */
	public void add(GoodsTypeLogo goodsTypeLogo);
	
	/**
	 * update
	 */
	public void update(GoodsTypeLogo goodsTypeLogo);
	
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
	public GoodsTypeLogo get(long id);
	
	/**
	 * 得到商品类型Logo列表
	 * @param map
	 * {typeId:类型Id,
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到商品类型Logo列表
	 * @param map
	 * {typeId:类型Id,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据商品类型，得到其时间冲突的Logo数量
	 * @param map
	 * {typeId:类型Id,
	 * startDate:开始时间,
	 * endDate:结束时间,
	 * op:操作类型（add:添加验证id不做判断，modify:判断id）,
	 * id:商品类型Id
	 * }
	 * @return
	 */
	public long getDateConflictRows(Map<String,Object> map);
	
	
	
	/**
	 * 根据商品类型，得到其默认的Logo数量
	 * @param map
	 * {typeId:类型Id,
	 * op:操作类型（add:添加验证id不做判断，modify:判断id）,
	 * id:商品类型Id
	 * }
	 * @return
	 */
	public long getDefaultRows(Map<String,Object> map);
}