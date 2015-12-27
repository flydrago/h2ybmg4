package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcBillDetailDao;
import com.h2y.jxc.entity.JxcBillDetail;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcBillDetailService")
public class JxcBillDetailServiceImpl implements IJxcBillDetailService{


	@Autowired
	protected IJxcBillDetailDao jxcBillDetailDao;

	public void add(JxcBillDetail jxcBillDetail) {

		jxcBillDetailDao.add(jxcBillDetail);
	}


	public void delete(long id) {
		jxcBillDetailDao.deleteById(id);
	}

	public void update(JxcBillDetail jxcBillDetail) {
		jxcBillDetailDao.update(jxcBillDetail);
	}

	public JxcBillDetail get(long id) {
		return jxcBillDetailDao.get(id);
	}


	public List<JxcBillDetail> getList(JxcBillDetail jxcBillDetail){
		List<JxcBillDetail> list = jxcBillDetailDao.getList(jxcBillDetail);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcBillDetail> getListPage(Map<String,Object> map){
		return jxcBillDetailDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcBillDetailDao.getRows(map);
	}

	/**
	 * 获取单据 商品明细
	 */
	public List<Map<String, Object>> getBillGoods(String billNo) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("billNo", billNo);
		List<Map<String,Object>> billGoodsList = jxcBillDetailDao.getBillGoodsList(paraMap);
		return billGoodsList;
	}
	
}