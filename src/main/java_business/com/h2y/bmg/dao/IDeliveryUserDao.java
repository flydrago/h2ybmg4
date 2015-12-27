package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.DeliveryUser;


/**
 * 配送员
 * @author sunfj
 *
 */
@Component
public interface IDeliveryUserDao {

	/**
	 * update
	 */
	public void update(DeliveryUser deliveryUser);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	// public void deleteByIds(List<long> ids);

	/**
	 * get
	 * 
	 * @return
	 */
	public DeliveryUser get(long id);

	/**
	 * 得到分页列表
	 * 
	 * @param map
	 *            key1:ifQuery value:查询条件(null时，不做判断) key2:page,pagesize
	 *            value2:页码、页显示最大条数 key3:sortname,sortorder value3:排序字段，排序方式
	 * @return 分页信息列表
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 得到总行数
	 * 
	 * @param map
	 *            key1:ifQuery value1:查询条件(null时，不做判断)
	 */
	public long getListRows(Map<String, Object> map);

	/**
	 * 修改状态
	 * 
	 * @param map
	 *            key1:status value1:状态 key2:list value2:配送员Id集合
	 */
	public void updateStatus(Map<String, Object> map);

	/**
	 * 得到审核通过的单位
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getPassUnitList();

	/**
	 * 获取公司下的门店列表
	 * 
	 * @param unitId
	 * @return
	 */
	public List<Map<String, Object>> getShopList(long unitId);
	
	
	/**
	 * 得到配送员选择列表
	 * @param map
	 * {shopId:门店id,
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getSelectListMap(Map<String,Object> map);
	
	/**
	 * 得到配送员选择列表总数
	 * @param map
	 * {shopId:门店id,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getSelectListRows(Map<String,Object> map);

	/**
	 * 分页获取门店表格数据（供分配门店选择）
	 * @param unitId
	 * @return
	 */
	public List<Map<String, Object>> getShopGridData(Map<String,Object> map);

	/**
	 * 按条件获取门店总数
	 * @param map
	 * @return
	 */
	public Long getShopRows(Map<String, Object> map);

	/**
	 * 保存 配送员&门店 关联关系
	 * @param argsMap
	 */
	public void addDeliveryShopRt(Map<String, Object> argsMap);

	/**
	 * 获取配送员负责门店列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getDeliveryTakeChargeShopList(Map<String, Object> map);

	/**
	 * 获取配送员负责门店总数
	 * @param map
	 * @return
	 */
	public Long getDeliveryTakeChargeShopRows(Map<String, Object> map);

	/**
	 * 删除 配送员&门店 关联关系
	 * @param argsMap
	 */
	public void deleteDeliveryShopRt(Map<String, Object> argsMap);

	/**
	 * 获取配送员负责的所有门店ID
	 * @param deliveryUserId
	 * @return
	 */
	public List<Long> getDeliveryChargeShopList(String account);

	/**
	 * 清空指定配送员&门店 关联关系
	 * @param account
	 */
	public void emptyDeliveryShopRt(String account);

	public List<Map<String, Object>> getAllListMap(Map<String, Object> map);

	public Long getAllListRows(Map<String, Object> map);
}