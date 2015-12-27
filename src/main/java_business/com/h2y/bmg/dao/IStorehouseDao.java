package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.Storehouse;

/**
 * 类描述：   仓库数据库操作接口
 * 作者：侯飞龙
 * 时间：2015年1月31日下午2:30:55
 * 邮件：1162040314@qq.com
 */
@Component
public interface IStorehouseDao{

	/**
	 * add
	 */
	public int add(Storehouse storehouse);

	/**
	 * update
	 */
	public int update(Storehouse storehouse);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public Storehouse get(long id);

	/**
	 * 得到仓库的列表 
	 * @param map 
	 * {unitId:单位Id,
	 * parentType:上级类型（unit:单位、shop:门店）,
	 * shopId:门店Id（parentType为shop时判断使用）,
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	/**
	 * 得到仓库的列表总数
	 * @param map 
	 * {unitId:单位Id,
	 * parentType:上级类型（unit:单位、shop:门店）,
	 * shopId:门店Id（parentType为shop时判断使用）,
	 * ifQuery:查询条件}
	 * @return
	 */ 
	public long getListRows(Map<String,Object> map);


	/**
	 * 批量更新仓库的状态
	 * @param map
	 * {status:状态值 （0：正常、1：停用、2:删除）,
	 * ids:要改变状态的仓库id集合,
	 * op: （delete:删除、stop:停用、start:启用） 只有type为4的仓库可删除，即单位自己添加的仓库}
	 */
	public void updateStatusByIds(Map<String,Object> map);

	/**
	 * 得到门店树列表
	 * @param map
	 * {unitId:单位Id}
	 */
	public List<Map<String,Object>> getShopTreeList(Map<String,Object> map);


	/**
	 * 根据仓库隶属上级类型得到下面仓库的数量
	 * @param map
	 * {unitId:单位Id,
	 * parentType:上级类型（ unit:单位、shop:门店）,
	 * shopId:门店Id（parentType为shop时做判断）}
	 * @return
	 */
	public long getRowsByParentType(Map<String,Object> map);
}