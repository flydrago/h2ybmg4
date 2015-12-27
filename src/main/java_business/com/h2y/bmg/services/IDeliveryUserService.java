package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.DeliveryUser;
import com.h2y.bmg.entity.SysUnits;

public interface IDeliveryUserService {


	public void delete(long id);

	//public void deleteByIds(List<long> ids);

	public void update(DeliveryUser deliveryUser);

	public DeliveryUser get(long id);

	/**
	 * 得到列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,SysUnits sysUnits);

	/**
	 * 修改状态
	 * @param list 列表
	 * @param status 状态
	 * @return
	 */
	public void updateStatus(List<String> list,long status);


	/**
	 * 获取公司下的门店列表
	 * @param unitId
	 * @return
	 */
	public List<Map<String,Object>> getShopList(long unitId);
	
	
	/**
	 * 得到选择列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getSelectGridData(HttpServletRequest request,long unitId);

	/**
	 * 分页获取门店表格数据（供选择）
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getShopGridData(HttpServletRequest request);

	/**
	 * 保存 配送员&门店 关联关系
	 * @param request
	 * @return
	 */
	public Map<String,Object> saveDeliveryShopRt(HttpServletRequest request);

	/**
	 * 获取配送员负责的门店列表
	 * @param request
	 * @return
	 */
	public Map<String,Object> getDeliveryTakeChargeShopList(HttpServletRequest request);

	/**
	 * 删除 配送员&门店 关联关系
	 * @param request
	 * @return
	 */
	public Map<String,Object> deleteDeliveryShopRt(HttpServletRequest request);

	/**
	 * 清空 指定配送员&门店 关联关系
	 * @param account
	 */
	public void emptyDeliveryShopRt(String account);
}
