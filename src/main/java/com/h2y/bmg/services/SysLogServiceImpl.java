package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysLogDao;
import com.h2y.bmg.entity.SysLog;
import com.h2y.bmg.entity.SysUser;
import com.h2y.util.CilentTool;
import com.h2y.util.DateUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-28
 * email:info@hwttnet.com
 */
@Service("sysLogService")
public class SysLogServiceImpl implements ISysLogService{


	@Autowired
	protected ISysLogDao sysLogDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysLog
	 *
	 */
	public void add(SysLog sysLog) {
		// TODO Auto-generated method stub

		sysLogDao.add(sysLog);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysLogDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysLogDao.deleteByIds(ids);
	//}

	public void update(SysLog sysLog) {
		// TODO Auto-generated method stub
		sysLogDao.update(sysLog);
	}

	public SysLog get(long id) {
		// TODO Auto-generated method stub
		return sysLogDao.get(id);
	}
	
	
	/**
	 * 添加日志
	 */
	public void addLog(HttpServletRequest request,SysUser sysUser,
			String moduleName,String opType,String opResult,String memo){
		addLog(request, sysUser, moduleName, opType, opResult, memo, null, null);
	}
	
	
	/**
	 * 添加日志
	 * @param request 访问对象
	 * @param sysUser 操作用户
	 * @param moduleName 模块名称
	 * @param opType 操作类型
	 * @param opResult 操作结果
	 * @param memo 操作备注
	 * @param businessId 业务Id，多个用逗点分割
	 * @param tableName 业务表
	 */
	public void addLog(HttpServletRequest request,SysUser sysUser,
			String moduleName,String opType,String opResult,String memo,String businessId,String tableName){

		CilentTool cilentTool = CilentTool.getInstance(request);
		
		SysLog sysLog = new SysLog();
		
		if (sysUser!=null) {
			sysLog.setAccount(sysUser.getAccount());
			sysLog.setUnitId(sysUser.getUnitId());
			sysLog.setUserId(sysUser.getId());
			sysLog.setUserName(sysUser.getUserName());
		}
		
		sysLog.setIp(cilentTool.getIpAddr(request));
		sysLog.setMemo(memo);
		sysLog.setModuleName(moduleName);
		sysLog.setOperateBrowser(cilentTool.getBrowser());
		sysLog.setOperateDate(DateUtil.getSystemTime());
		sysLog.setOperateOs(cilentTool.getOs());
		sysLog.setOperateResult(opResult);
		sysLog.setOperateType(opType);
		sysLog.setBusinessId(businessId);
		sysLog.setTableName(tableName);
		sysLogDao.add(sysLog);
	}
	
	
	/**
	 * 得到列表
	 * @param requst
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);

		
		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = sysLogDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", sysLogDao.getListRows(map));
		return gridData;
	}
}