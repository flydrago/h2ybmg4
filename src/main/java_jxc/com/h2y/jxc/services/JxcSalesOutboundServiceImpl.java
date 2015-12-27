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
import com.h2y.jxc.dao.IJxcSalesOutboundDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcSalesOutbound;
import com.h2y.jxc.util.DataResponseUtil;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-07-08
 * email:info@hwttnet.com
 */
@Service("jxcSalesOutboundService")
public class JxcSalesOutboundServiceImpl implements IJxcSalesOutboundService{


	@Autowired
	protected IJxcSalesOutboundDao jxcSalesOutboundDao;

	@Autowired
	protected IBillCheckService saleoutBillCheck;
	
	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	/**
	 * @param jxcSalesOutbound
	 */
	public void add(JxcSalesOutbound jxcSalesOutbound) {
		jxcSalesOutboundDao.add(jxcSalesOutbound);
	}


	public void delete(long id) {
		jxcSalesOutboundDao.deleteById(id);
	}

	public void update(JxcSalesOutbound jxcSalesOutbound) {
		jxcSalesOutboundDao.update(jxcSalesOutbound);
	}

	public JxcSalesOutbound get(long id) {
		return jxcSalesOutboundDao.get(id);
	}


	public List<JxcSalesOutbound> getList(JxcSalesOutbound jxcSalesOutbound){
		List<JxcSalesOutbound> list = jxcSalesOutboundDao.getList(jxcSalesOutbound);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcSalesOutbound> getListPage(Map<String,Object> map){
		return jxcSalesOutboundDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcSalesOutboundDao.getRows(map);
	}

	/**
	 * 销售出库单  获取表格数据
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
		List<Map<String, Object>> dataList = jxcSalesOutboundDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcSalesOutboundDao.getListRows(map));
		
		return gridMap;
	}


	public Map<String, Object> outStorageSave(Map<String, Object> reqMap) {
		//单据校验
		JxcBillCheckParams billCheckParams = saleoutBillCheck.billCheck(reqMap);
		if(billCheckParams.getResultFlag() == 1){
			//校验成功 ， 保存单据信息 
			JxcSalesOutbound soBill=  (JxcSalesOutbound) billCheckParams.getBill();
			jxcSalesOutboundDao.add(soBill);
			
			/**记录单据办理过程**/
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("bill", soBill);
			paraMap.put("operator", (SysUser)reqMap.get("operator"));
			paraMap.put("auditStage", BillAuditLogKeys.pendingAuditStage.value());
			paraMap.put("auditProcess", BillAuditLogKeys.pendingAudit.value());
			jxcAuditLogService.recordBillAuditLog(paraMap);
			
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg(),soBill);
		}else{
			//校验失败
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg());
		}
	}
}