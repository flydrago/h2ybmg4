package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcContactsAccount;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcContactsAccountDao{

	/**
	 * add
	 */
	public void add(JxcContactsAccount jxcContactsAccount);
	
	/**
	 * update
	 */
	public void update(JxcContactsAccount jxcContactsAccount);
	
	/**
	 * delete
	 */
	public void deleteById(long id);
	
	/**
	 * get
	 * @return
	 */
	public JxcContactsAccount get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcContactsAccount> getList(JxcContactsAccount jxcContactsAccount);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcContactsAccount> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取收支账户列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 获取收支账户总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}