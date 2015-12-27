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
import com.h2y.img.model.ImgModules;
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
@Service("imgModulesService")
public class ImgModulesServiceImpl implements IImgModulesService{


	@Autowired
	protected IImgModulesDao imgModulesDao;
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param imgModules
	 *
	 */
	public void add(ImgModules imgModules) {
		imgModulesDao.add(imgModules);
	}


	public void delete(long id) {
		imgModulesDao.deleteById(id);
	}

	public void update(ImgModules imgModules) {
		imgModulesDao.update(imgModules);
	}

	public ImgModules get(long id) {
		return imgModulesDao.get(id);
	}


	public List<ImgModules> getList(ImgModules imgModules){
		List<ImgModules> list = imgModulesDao.getList(imgModules);
		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<ImgModules> getListPage(Map<String,Object> map){
		return imgModulesDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return imgModulesDao.getRows(map);
	}

	/**
	 * 获取 图片-模块 数据
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridMap = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		
		List<Map<String, Object>> dataList = imgModulesDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", imgModulesDao.getListRows(map));
		
		return gridMap;
	}

	/**
	 * 保存模块信息
	 */
	public Map<String, Object> saveModule(Map<String, Object> reqMap) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultFlag", 0);
		resultMap.put("resultMsg", "抱歉，模块保存失败，缺少请求参数");
		
		Map<String,Object> postData = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value()));
		
		String moduleName = StringUtil.objectToString(postData.get("moduleName"));
		String moduleCode = StringUtil.objectToString(postData.get("moduleCode"));
		String memo = StringUtil.objectToString(postData.get("memo"));
		
		if( moduleName == null || moduleCode == null
			|| "".equals(moduleName) || "".equals(moduleCode)){
			return resultMap;
		}
		
		ImgModules tmpModule = new ImgModules();
		tmpModule.setModuleName(moduleName);
		tmpModule.setModuleCode(moduleCode);
		tmpModule.setMemo(memo);
		tmpModule.setCreateDate(DateUtil.getSystemTime());
		tmpModule.setStatus(0);
		
		imgModulesDao.add(tmpModule);
		
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "模块添加成功");
		return resultMap;
	}

	/**
	 * 删除模块信息
	 */
	public Map<String, Object> deleteModule(long moduleId) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		delete(moduleId);
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "成功删除模块及其下的用途信息");
		return resultMap;
	}


}