package com.h2y.bmg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysDeptUserDao;
import com.h2y.bmg.entity.SysDeptUser;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */
@Service("sysDeptUserService")
public class SysDeptUserServiceImpl implements ISysDeptUserService{


	@Autowired
	protected ISysDeptUserDao sysDeptUserDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysDeptUser
	 *
	 */
	public void add(SysDeptUser sysDeptUser) {
		// TODO Auto-generated method stub

		sysDeptUserDao.add(sysDeptUser);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysDeptUserDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysDeptUserDao.deleteByIds(ids);
	//}

	public void update(SysDeptUser sysDeptUser) {
		// TODO Auto-generated method stub
		sysDeptUserDao.update(sysDeptUser);
	}

	public SysDeptUser get(long id) {
		// TODO Auto-generated method stub
		return sysDeptUserDao.get(id);
	}
	
	
	public void deleteByUserId(long userId){
		sysDeptUserDao.deleteByUserId(userId);
	}
}