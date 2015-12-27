package com.h2y.bmg.services;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.controllers.TraceProviderPactController;
import com.h2y.bmg.dao.ITraceProviderPactDao;
import com.h2y.bmg.dao.ITraceScanningPathDao;
import com.h2y.bmg.entity.FileData;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderPact;
import com.h2y.bmg.entity.TraceScanningPath;
import com.h2y.dict.DictUtil;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Service("traceProviderPactService")
public class TraceProviderPactServiceImpl implements ITraceProviderPactService,IFileDownService{

	private static Logger logger = Logger.getLogger(TraceProviderPactController.class);
	
	@Autowired
	protected ITraceProviderPactDao traceProviderPactDao;
	
	@Autowired
	protected ITraceScanningPathDao traceScanningPathDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceProviderPact
	 *
	 */
	public void add(TraceProviderPact traceProviderPact) {
		// TODO Auto-generated method stub

		traceProviderPactDao.add(traceProviderPact);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceProviderPactDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceProviderPactDao.deleteByIds(ids);
	//}

	public void update(TraceProviderPact traceProviderPact) {
		// TODO Auto-generated method stub
		traceProviderPactDao.update(traceProviderPact);
	}

	public TraceProviderPact get(long id) {
		// TODO Auto-generated method stub
		return traceProviderPactDao.get(id);
	}


	public List<TraceProviderPact> getList(TraceProviderPact traceProviderPact){
		List<TraceProviderPact> list = traceProviderPactDao.getList(traceProviderPact);
		//
		//traceProviderPact = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceProviderPact> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceProviderPact());
		return traceProviderPactDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceProviderPactDao.getRows(map);
	}


	public Map<String, Object> getGridData(HttpServletRequest request) {
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String parentId = request.getParameter("parentId");
		String ifQuery = request.getParameter("ifQuery");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("parentId", parentId);
		map.put("ifQuery", ifQuery);
				
		List<Map<String,Object>> dataList = traceProviderPactDao.getListByPage(map);
		long totalRows = traceProviderPactDao.getRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	public void save(HttpServletRequest request,SysUser sysUser) {
		
		TraceProviderPact traceProviderPact = new TraceProviderPact();
		traceProviderPact.setOptUserId(sysUser.getId());
		traceProviderPact.setOptUserAccount(sysUser.getAccount());
		traceProviderPact.setOptUserName(sysUser.getUserName());
		traceProviderPact.setIfEnable(1);
		traceProviderPact.setSignDate(DateUtil.getSystemTime());
		traceProviderPact.setStartDate(DateUtil.toDate(request.getParameter("startDate"),DateUtil.YYYY_MM_DD));
		traceProviderPact.setEndDate(DateUtil.toDate(request.getParameter("endDate"),DateUtil.YYYY_MM_DD));
		traceProviderPact.setProvideId(Long.valueOf(request.getParameter("providerId")));
		traceProviderPact.setProviderName(request.getParameter("providerName"));
		
		traceProviderPactDao.add(traceProviderPact);
		addFileData(request,traceProviderPact);
		
		
	}


	private void addFileData(HttpServletRequest request,TraceProviderPact traceProviderPact) {
		String[] picData_array = request.getParameterValues("picData");

		String picData_path = DictUtil.getMainByCode("pactPicData_path")
				.getDictValue();
		List<TraceScanningPath> fileDataList = new ArrayList<TraceScanningPath>();

		// 商品图片信息
		addDataToFileDataList(request,traceProviderPact,fileDataList, picData_array,picData_path);
		if (!fileDataList.isEmpty()) {			
			traceScanningPathDao.addBatch(fileDataList);
		}		
	}


	private void addDataToFileDataList(HttpServletRequest request,TraceProviderPact traceProviderPact,List<TraceScanningPath> fileDataList,
			String[] fileData_array, String dictPath) {
		if (fileData_array != null && fileData_array.length > 0) {

			int i = 0;
			for (String picData : fileData_array) {

				Map<String, Object> map = JSONUtil.getMap(picData);
				
				try {

					// 存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map
							.get("savePath") + "");
					String saveName = map.get("saveName") + "";
					String fileName = map.get("fileName") + "";
					//String fileType = map.get("fileType") + "";

					TraceScanningPath traceScanningPath = new TraceScanningPath();
					traceScanningPath.setDiskFileName(saveName);
					traceScanningPath.setPactFileName(fileName);
					traceScanningPath.setFileSuffix(saveName.substring(
							saveName.lastIndexOf(".") + 1,
							saveName.length()));
					// 日期作为相对路径
					SimpleDateFormat formatdate = new SimpleDateFormat(
							"yyyy/MM/dd/");
					String relative_path = formatdate.format(new Date());
					traceScanningPath.setRelativePath(relative_path);
					traceScanningPath.setRootPath(dictPath);
					traceScanningPath.setFileSize(new File(savePath).length());
					traceScanningPath.setParentId(traceProviderPact.getId());
					traceScanningPath.setIfEnable(traceProviderPact.getIfEnable());
					traceScanningPath.setOrd(i);
					traceScanningPath.setProvideId(Long.valueOf(request.getParameter("providerId")));
					traceScanningPath.setCreateDate(DateUtil.getSystemTime());
					FileUtils.copyFile(new File(savePath), new File(
							dictPath + relative_path + saveName));
					fileDataList.add(traceScanningPath);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			
				i++;
			}
		}
	}

	/**
	 * 合同扫描件信息接口实现类
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		FileDownMode fileDownMode = new FileDownMode();
		if (id != null && !id.equals("")) {

			TraceScanningPath traceScanningPath = traceScanningPathDao.get(Long.valueOf(id));
			fileDownMode.setSaveName(traceScanningPath.getPactFileName());
			fileDownMode.setFilePath(traceScanningPath.getRootPath()
					+ traceScanningPath.getRelativePath() + traceScanningPath.getDiskFileName());
		}
		return fileDownMode;
	}
}