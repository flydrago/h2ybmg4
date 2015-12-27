package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcProfitandlossDetailDao;
import com.h2y.jxc.entity.JxcProfitandlossDetail;

/**
  * 进销存 报损单&报溢单  单据商品详情 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 * email:info@hwttnet.com
 */
@Service("jxcProfitandlossDetailService")
public class JxcProfitandlossDetailServiceImpl implements IJxcProfitandlossDetailService{


	@Autowired
	protected IJxcProfitandlossDetailDao jxcProfitandlossDetailDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param jxcProfitandlossDetail
	 */
	public void add(JxcProfitandlossDetail jxcProfitandlossDetail) {
		jxcProfitandlossDetailDao.add(jxcProfitandlossDetail);
	}


	public void delete(long id) {
		jxcProfitandlossDetailDao.deleteById(id);
	}

	public void update(JxcProfitandlossDetail jxcProfitandlossDetail) {
		jxcProfitandlossDetailDao.update(jxcProfitandlossDetail);
	}

	public JxcProfitandlossDetail get(long id) {
		return jxcProfitandlossDetailDao.get(id);
	}


	public List<JxcProfitandlossDetail> getList(JxcProfitandlossDetail jxcProfitandlossDetail){
		List<JxcProfitandlossDetail> list = jxcProfitandlossDetailDao.getList(jxcProfitandlossDetail);
		return list;
	}

	/**
	 * getListPage
	 * @return
	 */
	public List<JxcProfitandlossDetail> getListPage(Map<String,Object> map){
		return jxcProfitandlossDetailDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcProfitandlossDetailDao.getRows(map);
	}


	/**
	 * 报溢单&报损单 获取单据商品信息
	 */
	public List<Map<String, Object>> getBillGoods(String billNo) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("billNo", billNo);
		List<Map<String,Object>> billGoodsList = jxcProfitandlossDetailDao.getBillGoodsList(paraMap);
		return billGoodsList;
	}
}