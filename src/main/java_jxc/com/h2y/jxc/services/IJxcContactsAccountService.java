package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcContactsAccount;

/**
 * 进销存 收支账户 Service Interface
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
public interface IJxcContactsAccountService{
	
	public void add(JxcContactsAccount jxcContactsAccount);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(JxcContactsAccount jxcContactsAccount);

	public JxcContactsAccount get(long id);
	
	public List<JxcContactsAccount> getList(JxcContactsAccount jxcContactsAccount);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcContactsAccount> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取账户信息列表
	 * @param request
	 * @return
	 */
	public Map<String,Object> getUnitAccountList(HttpServletRequest request);
}
