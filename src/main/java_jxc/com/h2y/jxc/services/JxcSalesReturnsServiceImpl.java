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
import com.h2y.jxc.dao.IJxcSalesReturnsDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcSalesReturns;
import com.h2y.jxc.util.DataResponseUtil;

/**
  * 进销存  销售退货单 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-21
 */
@Service("jxcSalesReturnsService")
public class JxcSalesReturnsServiceImpl implements IJxcSalesReturnsService{


	@Autowired
	protected IJxcSalesReturnsDao jxcSalesReturnsDao;

	@Autowired
	protected IBillCheckService salesoutReturnsBillCheck;
	
	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param jxcSalesReturns
	 */
	public void add(JxcSalesReturns jxcSalesReturns) {
		jxcSalesReturnsDao.add(jxcSalesReturns);
	}

	public void delete(long id) {
		jxcSalesReturnsDao.deleteById(id);
	}

	public void update(JxcSalesReturns jxcSalesReturns) {
		jxcSalesReturnsDao.update(jxcSalesReturns);
	}

	public JxcSalesReturns get(long id) {
		return jxcSalesReturnsDao.get(id);
	}


	public List<JxcSalesReturns> getList(JxcSalesReturns jxcSalesReturns){
		List<JxcSalesReturns> list = jxcSalesReturnsDao.getList(jxcSalesReturns);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcSalesReturns> getListPage(Map<String,Object> map){
		return jxcSalesReturnsDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcSalesReturnsDao.getRows(map);
	}

	/**
	 * 销售退货单 分页获取表格数据
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
		List<Map<String, Object>> dataList = jxcSalesReturnsDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcSalesReturnsDao.getListRows(map));
		
		return gridMap;
	}

	/**
	 * 销售退货单 新单保存接口
	 */
	public Map<String, Object> save(Map<String, Object> reqMap) {
		//单据校验
		JxcBillCheckParams billCheckParams = salesoutReturnsBillCheck.billCheck(reqMap);
		if(billCheckParams.getResultFlag() == 1){
			//校验成功 ， 保存单据信息 
			JxcSalesReturns returnsBill =  (JxcSalesReturns) billCheckParams.getBill();
			jxcSalesReturnsDao.add(returnsBill);
			
			/**记录单据办理过程**/
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("bill", returnsBill);
			paraMap.put("operator", (SysUser)reqMap.get("operator"));
			paraMap.put("auditStage", BillAuditLogKeys.pendingAuditStage.value());
			paraMap.put("auditProcess", BillAuditLogKeys.pendingAudit.value());
			jxcAuditLogService.recordBillAuditLog(paraMap);
			
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg(),returnsBill);
		}else{
			//校验失败
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg());
		}
	}
}