package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcAllocationbillDetailDao;
import com.h2y.jxc.entity.JxcAllocationbillDetail;

/**
 * 仓库调拨单 单据商品明细  ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-10
 * email:info@hwttnet.com
 */
@Service("jxcAllocationbillDetailService")
public class JxcAllocationbillDetailServiceImpl implements IJxcAllocationbillDetailService{

	@Autowired
	protected IJxcAllocationbillDetailDao jxcAllocationbillDetailDao;

	public void add(JxcAllocationbillDetail jxcAllocationbillDetail) {
		jxcAllocationbillDetailDao.add(jxcAllocationbillDetail);
	}

	public void delete(long id) {
		jxcAllocationbillDetailDao.deleteById(id);
	}

	public void update(JxcAllocationbillDetail jxcAllocationbillDetail) {
		jxcAllocationbillDetailDao.update(jxcAllocationbillDetail);
	}

	public JxcAllocationbillDetail get(long id) {
		return jxcAllocationbillDetailDao.get(id);
	}

	public List<JxcAllocationbillDetail> getList(JxcAllocationbillDetail jxcAllocationbillDetail){
		List<JxcAllocationbillDetail> list = jxcAllocationbillDetailDao.getList(jxcAllocationbillDetail);
		return list;
	}

	/**
	 * getListPage
	 * @return
	 */
	public List<JxcAllocationbillDetail> getListPage(Map<String,Object> map){
		return jxcAllocationbillDetailDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcAllocationbillDetailDao.getRows(map);
	}

	/**
	 * 根据单据编号  查询单据中包含的商品信息
	 */
	public List<Map<String, Object>> getBillGoods(String billNo) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("billNo", billNo);
		List<Map<String,Object>> billGoodsList = jxcAllocationbillDetailDao.getBillGoodsList(paraMap);
		return billGoodsList;
	}
}