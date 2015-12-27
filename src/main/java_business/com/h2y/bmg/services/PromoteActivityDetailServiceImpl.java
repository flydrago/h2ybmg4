package com.h2y.bmg.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IPromoteActivityDetailDao;
import com.h2y.bmg.entity.PromoteActivityDetail;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-08-13
 * email:info@hwttnet.com
 */
@Service("promoteActivityDetailService")
public class PromoteActivityDetailServiceImpl implements IPromoteActivityDetailService{


	@Autowired
	protected IPromoteActivityDetailDao promoteActivityDetailDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param promoteActivityDetail
	 *
	 */
	public void add(PromoteActivityDetail promoteActivityDetail) {
		// TODO Auto-generated method stub

		promoteActivityDetailDao.add(promoteActivityDetail);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		promoteActivityDetailDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	promoteActivityDetailDao.deleteByIds(ids);
	//}

	public void update(PromoteActivityDetail promoteActivityDetail) {
		// TODO Auto-generated method stub
		promoteActivityDetailDao.update(promoteActivityDetail);
	}

	public PromoteActivityDetail get(long id) {
		// TODO Auto-generated method stub
		return promoteActivityDetailDao.get(id);
	}


	/**
	 * 根据活动id获取活动明细
	 * @param promoteId
	 * @return
	 */
	public PromoteActivityDetail getByPromoteId(long promoteId) {
		return this.promoteActivityDetailDao.getByPromoteId(promoteId);
	}

}