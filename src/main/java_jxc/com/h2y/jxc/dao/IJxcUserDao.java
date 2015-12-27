package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.h2y.jxc.entity.JxcUser;

/**
 * JxcUserDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-06-24
 * email:info@hwttnet.com
 */
@Component
public interface IJxcUserDao{

	/**
	 * add
	 */
	public void add(JxcUser jxcUser);
	
	/**
	 * update
	 */
	public void update(JxcUser jxcUser);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public JxcUser get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcUser> getList(JxcUser jxcUser);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcUser> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}