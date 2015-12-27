package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.ITraceQrcodeSerialDao;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceNumberSection;
import com.h2y.bmg.entity.TraceQrcodeSerial;
import com.h2y.bmg.util.SysBaseUtil.DictClumn;
import com.h2y.bmg.util.SysBaseUtil.DictOrderBy;
import com.h2y.dict.ComparatorSysDictDetail;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Service("traceQrcodeSerialService")
public class TraceQrcodeSerialServiceImpl implements ITraceQrcodeSerialService{


	@Autowired
	protected ITraceQrcodeSerialDao traceQrcodeSerialDao;
	
	private static int PATCH_VALUE = 500;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceQrcodeSerial
	 *
	 */
	public void add(TraceQrcodeSerial traceQrcodeSerial) {
		// TODO Auto-generated method stub

		traceQrcodeSerialDao.add(traceQrcodeSerial);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceQrcodeSerialDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceQrcodeSerialDao.deleteByIds(ids);
	//}

	public void update(TraceQrcodeSerial traceQrcodeSerial) {
		// TODO Auto-generated method stub
		traceQrcodeSerialDao.update(traceQrcodeSerial);
	}

	public TraceQrcodeSerial get(long id) {
		// TODO Auto-generated method stub
		return traceQrcodeSerialDao.get(id);
	}


	public List<TraceQrcodeSerial> getList(TraceQrcodeSerial traceQrcodeSerial){
		List<TraceQrcodeSerial> list = traceQrcodeSerialDao.getList(traceQrcodeSerial);
		//
		//traceQrcodeSerial = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceQrcodeSerial> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceQrcodeSerial());
		return traceQrcodeSerialDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceQrcodeSerialDao.getRows(map);
	}

	/**
	 * 保存
	 */
	//@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, SysUnits sysUnits,SysUser sysUser,TraceNumberSection traceNumberSection) {
		
		Long startNo = Long.valueOf(request.getParameter("startNo"));
		int spec = Integer.parseInt(request.getParameter("spec"));
		Long endNo = Long.valueOf(request.getParameter("endNo"));
		
		Long boxStartValue = 0L;
		List<TraceQrcodeSerial> traceQrcodeSerialList = new ArrayList<TraceQrcodeSerial>();
	
		Long unitId = sysUnits.getId();
		
		int unitType = sysUnits.getUnitType();
		String userAccount = sysUser.getAccount();
		String userName = sysUser.getUserName();
		Long userId = sysUser.getId();
		
		
		//吉祥数
		List<SysDictDetail> luckyNoSysDictDetailList =   DictUtil.getDetailListByMainCode(1, "luckyNumber");
		String[] luckyArry = new String[luckyNoSysDictDetailList.size()];
		if (luckyNoSysDictDetailList!=null) {
			ComparatorSysDictDetail com = new ComparatorSysDictDetail(DictClumn.ord, DictOrderBy.asc);
			Collections.sort(luckyNoSysDictDetailList,com);
		}
		for(int i = 0 ; i < luckyNoSysDictDetailList.size(); i++){
			//System.out.println(luckyNoSysDictDetailList.get(i).getValue());
			luckyArry[i] = luckyNoSysDictDetailList.get(i).getValue();
		}
		//过滤规则
		List<SysDictDetail> filterNoSysDictDetailList = DictUtil.getDetailListByMainCode(1, "filterNumber");
		String[] filterArry = new String[filterNoSysDictDetailList.size()];
		for(int i = 0 ; i < filterNoSysDictDetailList.size(); i++){
			//System.out.println(filterNoSysDictDetailList.get(i).getValue());
			filterArry[i] = filterNoSysDictDetailList.get(i).getValue();
		}
		
		for(Long i = startNo; i <= endNo; i++){
			filterNo:
				for(int  m = 0; m < filterArry.length; m++){
					int tempIndex = i.toString().indexOf(filterArry[m]);
					while(tempIndex >= 0){
						
						if(tempIndex == i.toString().length()- 1){
							i = i + 1L;
						}else{
							int anotherInt = 10 - Integer.parseInt(i.toString().substring(tempIndex + 1, tempIndex + 2));
							i = i + Math.round(anotherInt * Math.pow(10, i.toString().length() - 2 - tempIndex));
						}
						
						if(i > endNo){
							System.out.println("======越界了，不再往下执行了=====");
							if(traceQrcodeSerialList.size() > 0){
								traceQrcodeSerialDao.patchAdd(traceQrcodeSerialList);
								traceQrcodeSerialList.clear();
							}
							return;
						}
						
						tempIndex = i.toString().indexOf(filterArry[m]);
						
						if(m == filterArry.length - 1){
							m = 0;
							continue filterNo;
						}
					}
				}
			
			boxStartValue = Long.parseLong(i + "00");
			for(int k = 0; k < spec; k++){
				TraceQrcodeSerial traceQrcodeSerial = new TraceQrcodeSerial();
				traceQrcodeSerial.setBoxQrcodeNo(boxStartValue);
				traceQrcodeSerial.setBottleQrcodeNo(Long.valueOf(i + luckyArry[k]));
				traceQrcodeSerial.setCreateDate(DateUtil.getSystemTime());
				traceQrcodeSerial.setIfReceive(1);
				traceQrcodeSerial.setIfEnable(1);
				traceQrcodeSerial.setRelateFlag(0);
				traceQrcodeSerial.setOptUserId(userId);
				traceQrcodeSerial.setOptUserAccount(userAccount);
				traceQrcodeSerial.setOptUserName(userName);
				traceQrcodeSerial.setParentId(traceNumberSection.getId());//Long.valueOf(request.getParameter("parentId"))
				traceQrcodeSerial.setProvinceId(Long.valueOf(request.getParameter("provinceId")));
				traceQrcodeSerial.setPrivideId(Long.valueOf(request.getParameter("providerId")));
				traceQrcodeSerial.setPrivideName(request.getParameter("providerName"));
				traceQrcodeSerialList.add(traceQrcodeSerial);
				
				if((traceQrcodeSerialList.size() == PATCH_VALUE )|| (traceQrcodeSerialList.size() < PATCH_VALUE && i.equals(endNo))){
					traceQrcodeSerialDao.patchAdd(traceQrcodeSerialList);
					traceQrcodeSerialList.clear();
				}
			}
			
		}
			
	}


	public List<TraceQrcodeSerial> getListData(Map<String, Object> dataMap) {
		
		return traceQrcodeSerialDao.getListData(dataMap);
	}


	public Map<String, Object> getGridData(HttpServletRequest request) {
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		Integer ifReceive = request.getParameter("ifReceive") == null ? null : Integer.parseInt(request.getParameter("ifReceive"));
		Integer ifEnable = request.getParameter("ifEnable") == null ? null : Integer.parseInt(request.getParameter("ifEnable"));
		Long parentId = request.getParameter("parentId") == null || request.getParameter("parentId") == ""? null : Long.valueOf(request.getParameter("parentId"));
		String ifQuery = request.getParameter("ifQuery");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("ifReceive", ifReceive);
		map.put("ifEnable", ifEnable);
		map.put("parentId", parentId);
		map.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = traceQrcodeSerialDao.getListByPage(map);
		long totalRows = traceQrcodeSerialDao.getRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
		
}