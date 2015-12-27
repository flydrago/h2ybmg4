package com.h2y.jxc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcContactsAccountDao;
import com.h2y.jxc.entity.JxcContactsAccount;

/**
  * 进销存  往来单位收付款账户 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcContactsAccountService")
public class JxcContactsAccountServiceImpl implements IJxcContactsAccountService{


	@Autowired
	protected IJxcContactsAccountDao jxcContactsAccountDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param jxcContactsAccount
	 */
	public void add(JxcContactsAccount jxcContactsAccount) {
		jxcContactsAccountDao.add(jxcContactsAccount);
	}


	public void delete(long id) {
		jxcContactsAccountDao.deleteById(id);
	}

	public void update(JxcContactsAccount jxcContactsAccount) {
		jxcContactsAccountDao.update(jxcContactsAccount);
	}

	public JxcContactsAccount get(long id) {
		return jxcContactsAccountDao.get(id);
	}


	public List<JxcContactsAccount> getList(JxcContactsAccount jxcContactsAccount){
		List<JxcContactsAccount> list = jxcContactsAccountDao.getList(jxcContactsAccount);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcContactsAccount> getListPage(Map<String,Object> map){
		return jxcContactsAccountDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcContactsAccountDao.getRows(map);
	}


	/**
	 * 进销存  账户选择对话窗 获取账户列表
	 */
	public Map<String, Object> getUnitAccountList(HttpServletRequest request) {
		Map<String,Object> gridMap = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		
		/*if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.type_name";
			} else if (sortname.equals("GOODS_UNIT_NAME")) {
				sortname = "g.goods_unit";
			} else {
				sortname = "g." + sortname.toLowerCase();
			}
		}*/

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		List<Map<String, Object>> dataList = jxcContactsAccountDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcContactsAccountDao.getListRows(map));
		
		return gridMap;
	}
}