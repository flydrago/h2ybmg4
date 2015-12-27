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
import com.h2y.jxc.dao.IJxcPurchaseReturnsDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcPurchaseReturns;
import com.h2y.jxc.util.DataResponseUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcPurchaseReturnsService")
public class JxcPurchaseReturnsServiceImpl implements IJxcPurchaseReturnsService{


	@Autowired
	protected IJxcPurchaseReturnsDao jxcPurchaseReturnsDao;

	@Autowired
	protected IBillCheckService purchaseReturnsBillCheck;
	
	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param jxcPurchaseReturns
	 *
	 */
	public void add(JxcPurchaseReturns jxcPurchaseReturns) {
		jxcPurchaseReturnsDao.add(jxcPurchaseReturns);
	}


	public void delete(long id) {
		jxcPurchaseReturnsDao.deleteById(id);
	}

	public void update(JxcPurchaseReturns jxcPurchaseReturns) {
		jxcPurchaseReturnsDao.update(jxcPurchaseReturns);
	}

	public JxcPurchaseReturns get(long id) {
		return jxcPurchaseReturnsDao.get(id);
	}


	public List<JxcPurchaseReturns> getList(JxcPurchaseReturns jxcPurchaseReturns){
		List<JxcPurchaseReturns> list = jxcPurchaseReturnsDao.getList(jxcPurchaseReturns);

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcPurchaseReturns> getListPage(Map<String,Object> map){
		return jxcPurchaseReturnsDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return jxcPurchaseReturnsDao.getRows(map);
	}


	/**
	 * 采购退货单 分页获取表格数据
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
		List<Map<String, Object>> dataList = jxcPurchaseReturnsDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcPurchaseReturnsDao.getListRows(map));
		
		return gridMap;
	}


	/**
	 * 采购退货单  保存单据接口
	 */
	public Map<String, Object> outStorageSave(Map<String, Object> reqMap) {
		//单据校验
		JxcBillCheckParams billCheckParams = purchaseReturnsBillCheck.billCheck(reqMap);
		if(billCheckParams.getResultFlag() == 1){
			//校验成功 ， 保存单据信息 
			JxcPurchaseReturns prBill= (JxcPurchaseReturns) billCheckParams.getBill();
			jxcPurchaseReturnsDao.add(prBill);
			
			/**记录单据办理过程**/
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("bill", prBill);
			paraMap.put("operator", (SysUser)reqMap.get("operator"));
			paraMap.put("auditStage", BillAuditLogKeys.pendingAuditStage.value());
			paraMap.put("auditProcess", BillAuditLogKeys.pendingAudit.value());
			jxcAuditLogService.recordBillAuditLog(paraMap);
			
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg(),prBill);
		}else{
			//校验失败
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg());
		}
	}
}