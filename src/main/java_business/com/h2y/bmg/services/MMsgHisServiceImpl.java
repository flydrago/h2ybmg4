package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IMMsgHisDao;
import com.h2y.bmg.dao.ISysUserDao;
import com.h2y.bmg.entity.SysUser;

/**
 * 项目名称：h2ybmg2  
 * 类名称：MMsgHisServiceImpl  
 * 类描述：  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月28日 上午10:00:39  
 * 修改人：侯飞龙
 * 修改时间：2015年7月28日 上午10:00:39  
 * 修改备注：  
 * @version
 */
@Service("mMsgHisService")
public class MMsgHisServiceImpl implements IMMsgHisService{
	
	@Autowired
	protected IMMsgHisDao msgHisDao;
	
	@Autowired
	protected ISysUserDao sysUserDao;

	public Map<String, Object> getGridData(HttpServletRequest request) {
		
		String target = request.getParameter("target");
		String datasourceType = request.getParameter("datasourceType");
		String datasourceId = request.getParameter("datasourceId");
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String ifQuery = request.getParameter("ifQuery");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("datasourceType", datasourceType);
		params.put("datasourceId", datasourceId);
		params.put("page", Integer.parseInt(page));
		params.put("pagesize", Integer.parseInt(pagesize));
		params.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		long totalRows = 0;
		
		//delivery:配送端、app:客户端、pc:电脑客户端
		if (target.equals("delivery")) {
			
			dataList = msgHisDao.getDeliveryListMap(params);
			totalRows = msgHisDao.getDeliveryListRows(params);
		}else if (target.equals("app")) {
			
			dataList = msgHisDao.getAppListMap(params);
			totalRows = msgHisDao.getAppListRows(params);
		}else if (target.equals("pc")) {
			
			dataList = msgHisDao.getPcListMap(params);
			totalRows = msgHisDao.getPcListRows(params);
			
			if (null!=dataList && !dataList.isEmpty()) {
				
				for (Map<String, Object> map : dataList) {
					
					String mto = map.get("MTO")+"";
					String account = mto.substring(2, mto.indexOf("@"));
					String unitId = mto.substring(mto.indexOf("@")+1, mto.length());
					Map<String,Object> userParams = new HashMap<String, Object>();
					userParams.put("account", account);
					userParams.put("unitId", unitId);
					SysUser sysUser = sysUserDao.getUserByAccountAndUnitId(userParams);
					if (null!=sysUser) {
						map.put("NAME", sysUser.getUserName());
						map.put("ACCOUNT", sysUser.getAccount());
					}
				}
			}
		}
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
}