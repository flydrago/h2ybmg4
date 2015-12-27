package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcSysUnitsDao;
import com.h2y.jxc.dao.IJxcUserDao;
import com.h2y.jxc.entity.JxcUser;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-24
 * email:info@hwttnet.com
 */
@Service("jxcUserService")
public class JxcUserServiceImpl implements IJxcUserService{


	@Autowired
	protected IJxcUserDao jxcUserDao;

	@Autowired
	protected IJxcSysUnitsDao sysUnitsDao;
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param jxcUser
	 *
	 */
	public void add(JxcUser jxcUser) {

		jxcUserDao.add(jxcUser);
	}


	public void delete(long id) {
		jxcUserDao.deleteById(id);
	}

	public void update(JxcUser jxcUser) {
		jxcUserDao.update(jxcUser);
	}

	public JxcUser get(long id) {
		return jxcUserDao.get(id);
	}


	public List<JxcUser> getList(JxcUser jxcUser){
		List<JxcUser> list = jxcUserDao.getList(jxcUser);

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcUser> getListPage(Map<String,Object> map){
		return jxcUserDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return jxcUserDao.getRows(map);
	}


	/**
	 * 根据登录信息获取单位ID
	 */
	public Long getUnitByDomain(String account) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("unitDomain", account.split("@")[1]);
		List<Map<String,Object>> list = this.sysUnitsDao.getUnitRowsBySame(params);
		if(null != list && list.size() > 0){
			return (Long) list.get(0).get("id");
		}
		return 0L;
	}


	/**
	 * 根据unitId,account查询用户
	 */
	public JxcUser getLoginCheckUser(Long unitId, String account) {

		
		return null;
	}
}