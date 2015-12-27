package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderGoods;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITraceProviderGoodsService{
	
	public void add(TraceProviderGoods traceProviderGoods);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceProviderGoods traceProviderGoods);

	public TraceProviderGoods get(long id);
	
	public List<TraceProviderGoods> getList(TraceProviderGoods traceProviderGoods);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceProviderGoods> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public Map<String,Object> getGridData(HttpServletRequest request);

	public Map<String,Object> getSelectProviderGoodsGridData(HttpServletRequest request);

	public void add(String[] goodsNames, String[] goodsIds, Long parentId,SysUser sysUser );
}
