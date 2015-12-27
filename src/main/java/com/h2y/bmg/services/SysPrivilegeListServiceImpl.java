package com.h2y.bmg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.entity.SysPrivilegeList;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 */
@Service("sysPrivilegeListService")
public class SysPrivilegeListServiceImpl implements ISysPrivilegeListService{


	@Autowired
	protected ISysPrivilegeListDao sysPrivilegeListDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysPrivilegeList
	 *
	 */
	public void add(SysPrivilegeList sysPrivilegeList) {
		// TODO Auto-generated method stub

		sysPrivilegeListDao.add(sysPrivilegeList);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysPrivilegeListDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysPrivilegeListDao.deleteByIds(ids);
	//}

	public void update(SysPrivilegeList sysPrivilegeList) {
		// TODO Auto-generated method stub
		sysPrivilegeListDao.update(sysPrivilegeList);
	}

	public SysPrivilegeList get(long id) {
		// TODO Auto-generated method stub
		return sysPrivilegeListDao.get(id);
	}
}