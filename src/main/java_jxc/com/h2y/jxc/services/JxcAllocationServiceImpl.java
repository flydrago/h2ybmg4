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
import com.h2y.jxc.dao.IJxcAllocationDao;
import com.h2y.jxc.entity.JxcAllocation;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.util.DataResponseUtil;

/**
  * 进销存 调拨单 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-09
 * email:info@hwttnet.com
 */
@Service("jxcAllocationService")
public class JxcAllocationServiceImpl implements IJxcAllocationService{


	@Autowired
	protected IJxcAllocationDao jxcAllocationDao;

	@Autowired
	protected IBillCheckService allocationBillCheck;
	
	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	public void add(JxcAllocation jxcAllocation) {
		jxcAllocationDao.add(jxcAllocation);
	}


	public void delete(long id) {
		jxcAllocationDao.deleteById(id);
	}


	public void update(JxcAllocation jxcAllocation) {
		jxcAllocationDao.update(jxcAllocation);
	}

	public JxcAllocation get(long id) {
		return jxcAllocationDao.get(id);
	}


	public List<JxcAllocation> getList(JxcAllocation jxcAllocation){
		List<JxcAllocation> list = jxcAllocationDao.getList(jxcAllocation);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcAllocation> getListPage(Map<String,Object> map){
		return jxcAllocationDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcAllocationDao.getRows(map);
	}


	/**
	 * 仓库调拨单  分页获取表格数据
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
		List<Map<String, Object>> dataList = jxcAllocationDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcAllocationDao.getListRows(map));
		
		return gridMap;
	}


	/**
	 * 仓储调拨单 新单保存
	 */
	public Map<String, Object> save(Map<String, Object> reqMap) {
		//单据校验
		JxcBillCheckParams billCheckParams = allocationBillCheck.billCheck(reqMap);
		if(billCheckParams.getResultFlag() == 1){
			//校验成功 ， 保存单据信息 
			JxcAllocation allocationBill=  (JxcAllocation) billCheckParams.getBill();
			jxcAllocationDao.add(allocationBill);
			
			/**记录单据办理过程**/
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("bill", allocationBill);
			paraMap.put("operator", (SysUser)reqMap.get("operator"));
			paraMap.put("auditStage", BillAuditLogKeys.pendingAuditStage.value());
			paraMap.put("auditProcess", BillAuditLogKeys.pendingAudit.value());
			jxcAuditLogService.recordBillAuditLog(paraMap);
			
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg(),allocationBill);
		}else{
			//校验失败
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg());
		}
	}
}