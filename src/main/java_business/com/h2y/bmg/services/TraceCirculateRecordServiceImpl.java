package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.dao.ITraceCirculateRecordDao;
import com.h2y.bmg.dao.ITraceQrcodeSerialDao;
import com.h2y.bmg.entity.TraceCirculateRecord;
import com.h2y.bmg.entity.TraceQrcodeSerial;
import com.h2y.util.DataResponseUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Service("traceCirculateRecordService")
public class TraceCirculateRecordServiceImpl implements ITraceCirculateRecordService{

	private final static Logger logger = Logger.getLogger(TraceCirculateRecordServiceImpl.class);
	
	@Autowired
	protected ITraceCirculateRecordDao traceCirculateRecordDao;
	
	@Autowired
	protected ITraceQrcodeSerialDao traceQrcodeSerialDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceCirculateRecord
	 *
	 */
	public void add(TraceCirculateRecord traceCirculateRecord) {
		// TODO Auto-generated method stub

		traceCirculateRecordDao.add(traceCirculateRecord);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceCirculateRecordDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceCirculateRecordDao.deleteByIds(ids);
	//}

	public void update(TraceCirculateRecord traceCirculateRecord) {
		// TODO Auto-generated method stub
		traceCirculateRecordDao.update(traceCirculateRecord);
	}

	public TraceCirculateRecord get(long id) {
		// TODO Auto-generated method stub
		return traceCirculateRecordDao.get(id);
	}


	public List<TraceCirculateRecord> getList(TraceCirculateRecord traceCirculateRecord){
		List<TraceCirculateRecord> list = traceCirculateRecordDao.getList(traceCirculateRecord);
		//
		//traceCirculateRecord = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceCirculateRecord> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceCirculateRecord());
		return traceCirculateRecordDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceCirculateRecordDao.getRows(map);
	}


	public void addPatch(List<TraceQrcodeSerial> traceQrcodeSerialList, Map<String,Object> map) {
		
		List<TraceCirculateRecord> traceCirculateRecordList = new ArrayList<TraceCirculateRecord>();
		
		for(TraceQrcodeSerial traceQrcodeSerial : traceQrcodeSerialList){
			TraceCirculateRecord traceCirculateRecord = new TraceCirculateRecord();
			traceCirculateRecord.setBoxQrcodeNo(traceQrcodeSerial.getBoxQrcodeNo());
			traceCirculateRecord.setBottleQrcodeNo(traceQrcodeSerial.getBottleQrcodeNo());
			traceCirculateRecord.setSpec(Integer.valueOf(map.get("spec").toString()));
			traceCirculateRecord.setShowFlag(1);
			traceCirculateRecord.setPackFlag(Integer.valueOf( map.get("packFlag").toString() ));
			traceCirculateRecord.setCirculateFlag(Integer.valueOf( map.get("circulateFlag").toString() ));
			traceCirculateRecord.setCreateDate(DateUtil.getSystemTime());
			traceCirculateRecord.setNewRecordFlag(100);
			traceCirculateRecord.setOptUserId(1L);
			traceCirculateRecord.setOptUserAccount("1");
			traceCirculateRecord.setOptUserName("1");
			traceCirculateRecordList.add(traceCirculateRecord);
		}
		traceCirculateRecordDao.addPatch(traceCirculateRecordList);
		
	}


	public List<TraceCirculateRecord> getListData(Map<String, Object> map) {
		
		return traceCirculateRecordDao.getListData(map);
	}


	public void updateNewRecord(Map<String, Object> map) {
		traceCirculateRecordDao.updateNewRecord(map);
	}


	public Map<String, Object> addRecord(Map<String, Object> reqMap) {
		
		Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
	
		
		try {
			String qrcodeNo = paraMap.get("qrcodeNo") + "";
			//String circulateFlag = paraMap.get("circulateFlag") + "";
			//Long optUserId = Long.valueOf(paraMap.get("optUserId") + "");
			
			if(qrcodeNo != null && !qrcodeNo.equals("")){
				String tempString = qrcodeNo.substring(qrcodeNo.length() - 2, qrcodeNo.length());
				
				if(tempString.equals("00")){//箱
					return addRecordFromBox(reqMap,paraMap);
				}else{//瓶
					addRecordFromBottle(reqMap,paraMap);
				}
			}else{
				return	DataResponseUtil.getResultData(reqMap, 0, "扫码出错,未获取相应二维码");
			}
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMap = DataResponseUtil.getResultData(reqMap, 0, "扫码出错");
			return resultMap;
		}	
		
		resultMap = DataResponseUtil.getResultData(reqMap, 1, "扫码成功");
		return resultMap;
		
	}
	
	
	public Map<String, Object> addRecordFromBox(Map<String, Object> reqMap,Map<String,Object> postDataMap){
		
		List<TraceQrcodeSerial> traceQrcodeSerialList = new ArrayList<TraceQrcodeSerial>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("boxQrcodeNo", postDataMap.get("qrcodeNo") + "");
		traceQrcodeSerialList = traceQrcodeSerialDao.getListData(dataMap);
		int spec = traceQrcodeSerialList.size();
		if(spec == 0){
			
			return DataResponseUtil.getResultData(reqMap, 0, "二维码不属于平台分配的二维码");
		}else{
			
			Map<String,Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("boxQrcode", postDataMap.get("qrcodeNo") + "");
			dataMap1.put("packFlag", 0);
			dataMap1.put("spec", spec);
			dataMap1.put("optUserId", postDataMap.get("optUserId") + "");
			dataMap1.put("circulateFlag", postDataMap.get("circulateFlag") + "");
			dataMap1.put("newRecordFlag", 100);
			List<TraceCirculateRecord> traceCirculateRecordList = new ArrayList<TraceCirculateRecord>();
			traceCirculateRecordList = getListData(dataMap1);
			int recordSize = traceCirculateRecordList.size();
			if(recordSize != 0){
				
				traceCirculateRecordDao.updateNewRecord(dataMap1);
			}
			Map<String,Object> dataMap2 = new HashMap<String, Object>();
			dataMap2.put("spec", spec);
			dataMap2.put("packFlag", 0);
			dataMap2.put("circulateFlag", postDataMap.get("circulateFlag") + "");
			addPatch(traceQrcodeSerialList,dataMap2);
		}
		
		return DataResponseUtil.getResultData(reqMap, 1, "扫码成功");
	}
	
	public Map<String, Object> addRecordFromBottle(Map<String, Object> reqMap,Map<String,Object> postDataMap){
		
		List<TraceQrcodeSerial> traceQrcodeSerialList = new ArrayList<TraceQrcodeSerial>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("bottleQrcodeNo", postDataMap.get("qrcodeNo") + "");
		traceQrcodeSerialList = traceQrcodeSerialDao.getListData(dataMap);
		int spec = traceQrcodeSerialList.size();
		if(spec == 0){
			
			return DataResponseUtil.getResultData(reqMap, 0, "二维码不属于平台分配的二维码");
		}else{
			
			Map<String,Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("bottleQrcode", postDataMap.get("qrcodeNo") + "");
			dataMap1.put("packFlag", 1);
			dataMap1.put("spec", spec);
			dataMap1.put("optUserId", postDataMap.get("optUserId") + "");
			dataMap1.put("circulateFlag", postDataMap.get("circulateFlag") + "");
			dataMap1.put("newRecordFlag", 100);
			List<TraceCirculateRecord> traceCirculateRecordList = new ArrayList<TraceCirculateRecord>();
			traceCirculateRecordList = traceCirculateRecordDao.getListData(dataMap1);
			int recordSize = traceCirculateRecordList.size();
			if(recordSize != 0){
				
				updateNewRecord(dataMap1);
			}
			Map<String,Object> dataMap2 = new HashMap<String, Object>();
			dataMap2.put("spec", spec);
			dataMap2.put("packFlag", 1);
			dataMap2.put("circulateFlag", postDataMap.get("circulateFlag") + "");
			addPatch(traceQrcodeSerialList,dataMap2);
		}
		
		return DataResponseUtil.getResultData(reqMap, 1, "扫码成功");
	}


	public Map<String, Object> getRecordList(Map<String, Object> reqMap) {
		
		Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			String qrcodeNo = paraMap.get("qrcodeNo") + "";
			//String circulateFlag = paraMap.get("circulateFlag") + "";
			Long optUserId = paraMap.get("optUserId") == null ? null : Long.valueOf(paraMap.get("optUserId") + "");
			
			List<TraceQrcodeSerial> traceQrcodeSerialList = new ArrayList<TraceQrcodeSerial>();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("bottleQrcodeNo", qrcodeNo);
			traceQrcodeSerialList = traceQrcodeSerialDao.getListData(dataMap);
			int spec = traceQrcodeSerialList.size();
			if(spec == 0){
				resultMap = DataResponseUtil.getResultData(reqMap, 0, "二维码不属于平台分配的二维码");
			}else{
				if(optUserId != null){
					
					addRecord(reqMap); //添加记录
				}
				
				Map<String,Object> dataMap1 = new HashMap<String, Object>();
				dataMap1.put("bottleQrcodeNo", qrcodeNo);
				dataMap1.put("newRecordFlag", 100);
				dataMap1.put("showFlag", 1);
				List<TraceCirculateRecord> traceCirculateRecordList = new ArrayList<TraceCirculateRecord>();
				traceCirculateRecordList = traceCirculateRecordDao.getListData(dataMap1);
				resultMap = DataResponseUtil.getResultData(reqMap, 1, "获取数据成功",traceCirculateRecordList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMap = DataResponseUtil.getResultData(reqMap, 0, "获取数据异常");
			return resultMap;
		}
		return resultMap;
	}


	public Map<String, Object> getGridData(HttpServletRequest request) {
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("bottleQrcodeNo", request.getParameter("bottleQrcodeNo"));
		map.put("newRecordFlag", 100);
		map.put("showFlag", 1);
		
		List<Map<String,Object>> dataList = traceCirculateRecordDao.getListDataByPage(map);
		long totalRows = traceCirculateRecordDao.getRows(map);
		if (dataList == null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
}