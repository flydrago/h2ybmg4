package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.JxcUser;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-24
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IJxcUserService{
	
	public void add(JxcUser jxcUser);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(JxcUser jxcUser);

	public JxcUser get(long id);
	
	public List<JxcUser> getList(JxcUser jxcUser);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcUser> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public Long getUnitByDomain(String account);

	public JxcUser getLoginCheckUser(Long unitId, String account);
}
