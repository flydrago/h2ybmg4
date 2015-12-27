package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcCheckbillDetailDao;
import com.h2y.jxc.entity.JxcCheckbillDetail;

/**
  * 进销存  仓库盘点单  单据商品明细 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-14
 */
@Service("jxcCheckbillDetailService")
public class JxcCheckbillDetailServiceImpl implements IJxcCheckbillDetailService{


	@Autowired
	protected IJxcCheckbillDetailDao jxcCheckbillDetailDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param jxcCheckbillDetail
	 */
	public void add(JxcCheckbillDetail jxcCheckbillDetail) {
		jxcCheckbillDetailDao.add(jxcCheckbillDetail);
	}


	public void delete(long id) {
		jxcCheckbillDetailDao.deleteById(id);
	}

	public void update(JxcCheckbillDetail jxcCheckbillDetail) {
		jxcCheckbillDetailDao.update(jxcCheckbillDetail);
	}

	public JxcCheckbillDetail get(long id) {
		return jxcCheckbillDetailDao.get(id);
	}


	public List<JxcCheckbillDetail> getList(JxcCheckbillDetail jxcCheckbillDetail){
		List<JxcCheckbillDetail> list = jxcCheckbillDetailDao.getList(jxcCheckbillDetail);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcCheckbillDetail> getListPage(Map<String,Object> map){
		return jxcCheckbillDetailDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcCheckbillDetailDao.getRows(map);
	}


	/**
	 * 仓库盘点单  查询单据商品明细接口
	 */
	public List<Map<String, Object>> getBillGoods(String billNo) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("billNo", billNo);
		List<Map<String,Object>> billGoodsList = jxcCheckbillDetailDao.getBillGoodsList(paraMap);
		return billGoodsList;
	}
}