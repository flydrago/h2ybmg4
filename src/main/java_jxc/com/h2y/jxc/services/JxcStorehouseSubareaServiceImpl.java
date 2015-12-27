package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcStorehouseSubareaDao;
import com.h2y.jxc.entity.StorehouseSubarea;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcStorehouseSubareaService")
public class JxcStorehouseSubareaServiceImpl implements IJxcStorehouseSubareaService{


	@Autowired
	protected IJxcStorehouseSubareaDao storehouseSubareaDao;

	public void add(StorehouseSubarea storehouseSubarea) {

		storehouseSubareaDao.add(storehouseSubarea);
	}


	public void delete(long id) {
		storehouseSubareaDao.deleteById(id);
	}

	public void update(StorehouseSubarea storehouseSubarea) {
		storehouseSubareaDao.update(storehouseSubarea);
	}

	public StorehouseSubarea get(long id) {
		return storehouseSubareaDao.get(id);
	}


	public List<StorehouseSubarea> getList(StorehouseSubarea storehouseSubarea){
		List<StorehouseSubarea> list = storehouseSubareaDao.getList(storehouseSubarea);

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<StorehouseSubarea> getListPage(Map<String,Object> map){
		return storehouseSubareaDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return storehouseSubareaDao.getRows(map);
	}
}