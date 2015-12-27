package com.h2y.bmg.services;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.ShareHref;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IShareHrefService{
	
	public void add(ShareHref shareHref);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(ShareHref shareHref);

	public ShareHref get(long id);
	
	
	public Map<String, Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 保存
	 * @param request
	 * @param shareHref
	 * @param op
	 * @param dictPath
	 */
	public void save(HttpServletRequest request,ShareHref shareHref,String op,String dictPath);
	
	
}
