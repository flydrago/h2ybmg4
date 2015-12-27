package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysDictDetail;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-26
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysDictDetailService{
	
	public void add(SysDictDetail sysDictDetail);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysDictDetail sysDictDetail);

	public SysDictDetail get(long id);
	
	
	/**
	 * 得到表格列表
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 是否有相同编码
	 * @param sysDictDetail
	 * @param op
	 * @return
	 */
	public boolean isHasSameCode(SysDictDetail sysDictDetail,String op);
}
