package com.h2y.jxc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.entity.SysUser;
import com.h2y.jxc.basic.JxcKeys.BillAuditLogKeys;
import com.h2y.jxc.dao.IJxcCheckDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcCheck;
import com.h2y.jxc.util.DataResponseUtil;

/**
  * 仓库盘点单  ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-13
 */
@Service("jxcCheckService")
public class JxcCheckServiceImpl implements IJxcCheckService{

	@Autowired
	protected IBillCheckService takeStockBillCheck;
	
	@Autowired
	protected IJxcCheckDao jxcCheckDao;

	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param jxcCheck
	 *
	 */
	public void add(JxcCheck jxcCheck) {
		jxcCheckDao.add(jxcCheck);
	}


	public void delete(long id) {
		jxcCheckDao.deleteById(id);
	}

	public void update(JxcCheck jxcCheck) {
		jxcCheckDao.update(jxcCheck);
	}

	public JxcCheck get(long id) {
		return jxcCheckDao.get(id);
	}


	public List<JxcCheck> getList(JxcCheck jxcCheck){
		List<JxcCheck> list = jxcCheckDao.getList(jxcCheck);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcCheck> getListPage(Map<String,Object> map){
		return jxcCheckDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcCheckDao.getRows(map);
	}


	/**
	 * 仓库盘点单  获取表格数据
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
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
		List<Map<String, Object>> dataList = jxcCheckDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcCheckDao.getListRows(map));
		
		return gridMap;
	}


	/**
	 * 仓库盘点单 保存新单
	 */
	public Map<String, Object> save(Map<String, Object> reqMap) {
		//单据校验
		JxcBillCheckParams billCheckParams = takeStockBillCheck.billCheck(reqMap);
		if(billCheckParams.getResultFlag() == 1){
			//校验成功 ， 保存单据信息 
			JxcCheck takeStockBill =  (JxcCheck) billCheckParams.getBill();
			jxcCheckDao.add(takeStockBill);
			
			/**记录单据办理过程**/
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("bill", takeStockBill);
			paraMap.put("operator", (SysUser)reqMap.get("operator"));
			paraMap.put("auditStage", BillAuditLogKeys.pendingAuditStage.value());
			paraMap.put("auditProcess", BillAuditLogKeys.pendingAudit.value());
			jxcAuditLogService.recordBillAuditLog(paraMap);
			
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg(),takeStockBill);
		}else{
			//校验失败
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg());
		}
	}
}