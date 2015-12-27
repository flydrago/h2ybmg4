package com.h2y.img.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.img.dao.IImgModulesDao;
import com.h2y.img.dao.IImgUsageDao;
import com.h2y.img.model.ImgModules;
import com.h2y.img.model.ImgUsage;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 */
@Service("imgUsageService")
public class ImgUsageServiceImpl implements IImgUsageService{


	@Autowired
	protected IImgUsageDao imgUsageDao;

	@Autowired
	protected IImgModulesDao imgModulesDao;
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param imgUsage
	 *
	 */
	public void add(ImgUsage imgUsage) {
		imgUsageDao.add(imgUsage);
	}


	public void delete(long id) {
		imgUsageDao.deleteById(id);
	}

	public void update(ImgUsage imgUsage) {
		imgUsageDao.update(imgUsage);
	}

	public ImgUsage get(long id) {
		return imgUsageDao.get(id);
	}


	public List<ImgUsage> getList(ImgUsage imgUsage){
		List<ImgUsage> list = imgUsageDao.getList(imgUsage);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<ImgUsage> getListPage(Map<String,Object> map){
		return imgUsageDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return imgUsageDao.getRows(map);
	}


	/**
	 * 获取模块下的用途列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String moduleId = request.getParameter("moduleId");
			
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("moduleId", moduleId);
		
		List<Map<String,Object>> dataList = imgUsageDao.getListMap(map);

		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", imgUsageDao.getListRows(map));
		return gridData;
	}

	/**
	 * 保存 图片 用途 信息
	 */
	public Map<String, Object> saveUsage(Map<String, Object> reqMap) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultFlag", 0);
		
		Map<String,Object> postData = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value()));
		String usageName = StringUtil.objectToString(postData.get("usageName"));
		String usageCode = StringUtil.objectToString(postData.get("usageCode"));
		String memo = StringUtil.objectToString(postData.get("memo"));
		String moduleId = StringUtil.objectToString(postData.get("moduleId"));
		String width = StringUtil.objectToString(postData.get("width"));
		String height = StringUtil.objectToString(postData.get("height"));
		
		
		if( usageName == null || usageCode == null || width == null || height == null || moduleId == null
				|| "".equals(usageName) || "".equals(usageCode) || "".equals(width) || "".equals(height) || "".equals(moduleId)){
			resultMap.put("resultMsg", "图片用途添加失败，原因：缺少必要的请求参数");
			return resultMap;
		}

		if( !StringUtil.isNumeric(width) || !StringUtil.isNumeric(height) ){
			resultMap.put("resultMsg", "添加失败，原因：图片的宽高只能为整数");
			return resultMap;
		}
		
		ImgUsage tmpUsage = new ImgUsage();
		
		tmpUsage.setUsageName(usageName);
		tmpUsage.setUsageCode(usageCode);
		tmpUsage.setWidth(Integer.valueOf(width+""));
		tmpUsage.setHeight(Integer.valueOf(height+""));
		tmpUsage.setMemo(memo);
		tmpUsage.setCreateDate(DateUtil.getSystemTime());
		tmpUsage.setStatus(0);
		
		ImgModules imgModule = imgModulesDao.get(Long.valueOf(moduleId));
		
		if(null == imgModule){
			resultMap.put("resultMsg", "用途添加失败，原因：用途所属的模块为空");
			return resultMap;
		}
		
		tmpUsage.setModuleId(imgModule.getId());
		tmpUsage.setModuleCode(imgModule.getModuleCode());
		
		imgUsageDao.add(tmpUsage);
		
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "图片用途添加成功");
		
		return resultMap;
	}
}