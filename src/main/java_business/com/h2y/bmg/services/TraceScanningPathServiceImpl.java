package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ITraceScanningPathDao;
import com.h2y.bmg.entity.TraceScanningPath;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Service("traceScanningPathService")
public class TraceScanningPathServiceImpl implements ITraceScanningPathService{


	@Autowired
	protected ITraceScanningPathDao traceScanningPathDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceScanningPath
	 *
	 */
	public void add(TraceScanningPath traceScanningPath) {
		// TODO Auto-generated method stub

		traceScanningPathDao.add(traceScanningPath);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceScanningPathDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceScanningPathDao.deleteByIds(ids);
	//}

	public void update(TraceScanningPath traceScanningPath) {
		// TODO Auto-generated method stub
		traceScanningPathDao.update(traceScanningPath);
	}

	public TraceScanningPath get(long id) {
		// TODO Auto-generated method stub
		return traceScanningPathDao.get(id);
	}


	public List<TraceScanningPath> getList(TraceScanningPath traceScanningPath){
		List<TraceScanningPath> list = traceScanningPathDao.getList(traceScanningPath);
		//
		//traceScanningPath = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceScanningPath> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceScanningPath());
		return traceScanningPathDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceScanningPathDao.getRows(map);
	}


	public List<Map<String,Object>> getList(long parentId) {
		return traceScanningPathDao.getListData(parentId);
	}
}