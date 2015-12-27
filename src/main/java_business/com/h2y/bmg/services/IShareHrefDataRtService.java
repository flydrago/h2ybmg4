package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.ShareHrefDataRt;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-08-18
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IShareHrefDataRtService{

	public void add(ShareHrefDataRt shareHrefDataRt);

	public int delete(long id);

	//public void deleteByIds(List<long> ids);

	public void update(ShareHrefDataRt shareHrefDataRt);

	public ShareHrefDataRt get(long id);


	/**
	 * 获取列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String, Object> getGridData(HttpServletRequest request);


	public Map<String, Object> getPromoteList(HttpServletRequest request,long unitId);

	public ShareHrefDataRt save(HttpServletRequest request,String hrefId,String str1);


	/**
	 * 获取关联投票列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String, Object> getVoteList(HttpServletRequest request,long unitId);


	public Map<String, Object> getVoteSelectList(HttpServletRequest request,long unitId);

}
