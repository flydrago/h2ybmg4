package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.Storehouse;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IJxcStorehouseService{
	
	public void add(Storehouse storehouse);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(Storehouse storehouse);

	public Storehouse get(long id);
	
	public List<Storehouse> getList(Storehouse storehouse);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<Storehouse> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 分页查询仓库信息 （供选择用）
	 * @param request
	 * @return
	 */
	public Map<String,Object> getSelectGridData(HttpServletRequest request);

	/**
	 * 将单据中的商品信息转化成 仓库 库存的变更
	 * @param billGoodsList
	 * @param storageId 
	 * @param borkerId
	 */
	public Map<String,Object> receiptsTransStorage(Map<String,Object> paraMap);
	
	/**
	 * 仓库调拨单  库存变更
	 * @param paraMap
	 * @return
	 */
	public Map<String,Object>  allocationTransStorage(Map<String,Object> paraMap);
	
	/**
	 * 报损单&报溢单 转化库存变更
	 * @param paraMap
	 * @return
	 */
	public Map<String,Object> profitAndLossTransStorage(Map<String,Object> paraMap);
}
