package com.h2y.img.service;

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

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.entity.CommonImage;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.services.IFileDownService;
import com.h2y.dict.DictUtil;
import com.h2y.img.dao.IImgModulesDao;
import com.h2y.img.dao.IImgStorageDao;
import com.h2y.img.dao.IImgUsageDao;
import com.h2y.img.model.ImgModules;
import com.h2y.img.model.ImgStorage;
import com.h2y.img.model.ImgUsage;
import com.h2y.security.Base64Util;
import com.h2y.spring.IocUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.MatcherUtil;
import com.h2y.util.StringUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 */
@Service("imgStorageService")
public class ImgStorageServiceImpl implements IImgStorageService,IFileDownService{

	private Logger logger = Logger.getLogger(ImgStorageServiceImpl.class);
	
	@Autowired
	protected IImgStorageDao imgStorageDao;

	@Autowired
	protected IImgModulesDao imgModulesDao;
	
	@Autowired
	protected IImgUsageDao imgUsageDao;

	/**
	 * 删除图片信息
	 */
	public void delete(long id) {
		imgStorageDao.deleteById(id);
	}
	
	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String filterBean = request.getParameter("filterBean");
		String moduleCode = request.getParameter("moduleCode");
		String usageCode = request.getParameter("usageCode");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}
		String ifQuery = request.getParameter("ifQuery");

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("ifQuery", ifQuery);
		map.put("moduleCode", moduleCode);
		map.put("usageCode", usageCode);
		
		IImgStorageSelectFilter imgStorageSelectFilter = IocUtil.getBean(filterBean);
		List<Long> imgIdList = imgStorageSelectFilter.getSelectedImgStorageList(request, unitId);
		
		if (null!=imgIdList && !imgIdList.isEmpty()) {
			map.put("imgIdList", imgIdList);	
		}
		
		List<Map<String,Object>> dataList = imgStorageDao.getSelectListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", imgStorageDao.getSelectListRows(map));
		return gridData;
	}

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		if (!MatcherUtil.checkNumber(id)) {
			return null;
		}
		ImgStorage imgStorage = imgStorageDao.get(Long.valueOf(id));
		
		if (null==imgStorage) {
			return null;
		}
		
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(imgStorage.getRootPath()+imgStorage.getRelativePath()+imgStorage.getSaveName());
		fileDownMode.setSaveName(imgStorage.getSaveName());
		return fileDownMode;
	}

	/**
	 * 获取图片库 表格数据
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridMap = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String unitId = request.getParameter("unitId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		
		List<Map<String, Object>> dataList = imgStorageDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", imgStorageDao.getListRows(map));
		
		return gridMap;
	}

	/**
	 * 保存图片信息
	 */
	public Map<String, Object> saveImg(Map<String, Object> reqMap) {
		//结果集Map
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultFlag", 0);
		resultMap.put("resultMsg", "添加图片失败，原因：缺少必要的请求参数");
		
		//请求参数Map
		Map<String,Object> postData = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value()));
		
		String imgName = StringUtil.objectToString(postData.get("imgName"));
		String unitId = StringUtil.objectToString(postData.get("unitId"));
		String moduleId = StringUtil.objectToString(postData.get("moduleId"));
		String usageId = StringUtil.objectToString(postData.get("usageId"));
		String imgFileData = StringUtil.objectToString(postData.get("imgFileData"));
		String memo = StringUtil.objectToString(postData.get("memo"));
		
		//缺少请求参数，结束方法
		if(imgName == null || unitId == null || moduleId == null || usageId == null || imgFileData == null ||
			"".equals(imgName)||"".equals(unitId)||"".equals(moduleId)||"".equals(usageId)||"".equals(imgFileData)){
			return resultMap;
		}
		
		//图片的模块信息
		ImgModules imgModules = imgModulesDao.get(Long.valueOf(moduleId));
		
		//图片的用途信息
		ImgUsage imgUsage = imgUsageDao.get(Long.valueOf(usageId));
		
		try {
			
			//保存图片到正式目录
			SysDictMain dictInfo = DictUtil.getMainByCode("img_storage_path");
			
			if (null==dictInfo || null==dictInfo.getDictValue() || "".equals(dictInfo.getDictValue())) {
				resultMap.put("resultMsg", "请在字典中维护图片维护（启动加载图片等）存储路径，编码为：img_storage_path");
				return resultMap;
			}
			
			String imgPath = dictInfo.getDictValue();
			
			
			//添加图片信息
			ImgStorage tmpImg = new ImgStorage();
			
			tmpImg.setUnitId(Long.valueOf(unitId));
			tmpImg.setModuleId(imgModules.getId());
			tmpImg.setModuleCode(imgModules.getModuleCode());
			tmpImg.setUsageId(imgUsage.getId());
			tmpImg.setUsageCode(imgUsage.getUsageCode());
			tmpImg.setImgDes(imgName);
			tmpImg.setCreateDate(DateUtil.getSystemTime());
			tmpImg.setStatus(0);
			tmpImg.setMemo(memo);
			tmpImg.setData1(imgModules.getModuleName());
			tmpImg.setData2(imgUsage.getUsageName());

			//将临时目录中的图片 复制到 正式目录中
			addFileData(imgFileData, tmpImg, imgPath);
			
			imgStorageDao.add(tmpImg);
		} catch (Exception e) {
			logger.error("添加图片信息时异常");
			logger.error(e.getMessage());
			return resultMap;
		}
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "图片信息添加成功");
		
		return resultMap;
	}
	
	/**
	 * 添加商品图片信息到列表中
	 * @param fileDataList 图片对象列表
	 * @param fileData_array 图片数据数组
	 * @param commonImage 一般图片
	 * @param dictPath 字典存储路径
	 */
	private void addFileData(String fileData,ImgStorage tmpImg,String dictPath){

		if (null!=fileData && !"".equals(fileData)) {
			Map<String,Object> map = JSONUtil.getMap(fileData);
			if (map.get("id")==null) {
				try {
					//存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
					String saveName = map.get("saveName")+"";
					String fileSize = map.get("fileSize")+"";
					String fileName = map.get("fileName")+"";
					
					tmpImg.setSaveName(saveName);
					tmpImg.setData3(fileSize);
					tmpImg.setFileName(fileName);
					
					String relative_path = tmpImg.getRelativePath();
					String root_path = tmpImg.getRootPath();

					if (null==relative_path || "".equals(relative_path)) {
						//日期作为相对路径
						SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");
						relative_path = formatdate.format(new Date());
						tmpImg.setRelativePath(relative_path);
					}

					if (null==root_path || "".equals(root_path)) {
						root_path = dictPath;
						tmpImg.setRootPath(root_path);
					}

					FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}