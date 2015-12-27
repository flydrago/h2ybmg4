package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcContactsUnits;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 * Service Interface
 */
public interface IJxcContactsUnitsService{
	
	public void add(JxcContactsUnits jxcContactsUnits);
	
	public void delete(long id);
	
	public void update(JxcContactsUnits jxcContactsUnits);

	public JxcContactsUnits get(long id);
	
	public List<JxcContactsUnits> getList(JxcContactsUnits jxcContactsUnits);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcContactsUnits> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取供应商列表（供选择使用）
	 * @param request
	 * @param string 
	 * @return
	 */
	public Map<String,Object> getSelectGridData(HttpServletRequest request, String contactUnitsType);
}
