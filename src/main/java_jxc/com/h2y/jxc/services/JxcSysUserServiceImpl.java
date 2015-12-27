package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcSysUserDao;
import com.h2y.jxc.entity.SysUser;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcSysUserService")
public class JxcSysUserServiceImpl implements IJxcSysUserService{


	@Autowired
	protected IJxcSysUserDao sysUserDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param sysUser
	 */
	public void add(SysUser sysUser) {
		sysUserDao.add(sysUser);
	}


	public void delete(long id) {
		sysUserDao.deleteById(id);
	}


	public void update(SysUser sysUser) {
		sysUserDao.update(sysUser);
	}

	public SysUser get(long id) {
		return sysUserDao.get(id);
	}


	public List<SysUser> getList(SysUser sysUser){
		List<SysUser> list = sysUserDao.getList(sysUser);

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<SysUser> getListPage(Map<String,Object> map){
		return sysUserDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return sysUserDao.getRows(map);
	}
}