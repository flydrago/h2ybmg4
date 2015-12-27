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

import com.h2y.bmg.dao.IFindServiceDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.FindService;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：商品类型Logo的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("findServiceService")
public class FindServiceServiceImpl implements IFindServiceService,IFileDownService{


	private static final Logger logger = Logger.getLogger(FindServiceServiceImpl.class); 

	@Autowired
	protected IFindServiceDao findServiceDao;

	/**
	 * 获取列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,long parentId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String serviceType = request.getParameter("serviceType");
		if (sortname!=null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("parentId", parentId);
		map.put("serviceType", serviceType);
		List<Map<String,Object>> dataList = findServiceDao.getListMap(map);

		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", findServiceDao.getListRows(map));
		return gridData;
	}

	/**
	 * 元数据保存
	 */
	public void save(HttpServletRequest request,String op,FindService findService) {

		if (op.equals("add")) {			

			findService.setCreateDate(DateUtil.getSystemTime());
			findService.setUpdateDate(DateUtil.getSystemTime());
			findService.setServicePrefix("");			

			findServiceDao.add(findService);

			//更新编码
			if (findService.getParentId()==0) {
				findService.setServicePrefix(findService.getId()+"");
			}else {
				FindService findService2 = findServiceDao.get(findService.getParentId());
				findService.setServicePrefix(findService2.getServicePrefix()+"_"+findService2.getId());
			}
			findServiceDao.update(findService);
		}else {

			FindService findService2 = findServiceDao.get(findService.getId());
			findService2.setName(findService.getName());
			findService2.setOrd(findService.getOrd());
			findService2.setMemo(findService.getMemo());
			findService2.setStatus(findService.getStatus());
			findService2.setUpdateDate(DateUtil.getSystemTime());
			findService2.setIsLogin(findService.getIsLogin());
			findService2.setClickEvent(findService.getClickEvent());

			findServiceDao.update(findService2);
		}
	}


	/**
	 * 服务保存
	 */
	public void saveService(HttpServletRequest request,String op,
			FindService findService,SysDictMain sysDictMain) {

		String iosFileData = request.getParameter("iosFileData");
		String androidFileData = request.getParameter("androidFileData");
		String wechatFileData = request.getParameter("wechatFileData");

		String dictPath = sysDictMain.getDictValue();

		String urlParams_array [] = request.getParameterValues("urlParamsStr");

		if (null!=urlParams_array && urlParams_array.length>0) {

			String urlParams = "";
			for (String urlParam : urlParams_array) {
				if ("".equals(urlParams)) {
					urlParams=urlParam;
				}else {
					urlParams+=","+urlParam;
				}
			}
			findService.setUrlParams(urlParams);
		}

		if (op.equals("add")) {

			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",findService, dictPath);
			addFileData(androidFileData, "android",findService, dictPath);
			addFileData(wechatFileData, "wechat",findService, dictPath);

			findService.setCreateDate(DateUtil.getSystemTime());
			findService.setUpdateDate(DateUtil.getSystemTime());
			findService.setServicePrefix("");

			findServiceDao.add(findService);

			//更新编码
			if (findService.getParentId()==0) {
				findService.setServicePrefix(findService.getId()+"");
			}else {
				FindService findService2 = findServiceDao.get(findService.getParentId());
				findService.setServicePrefix(findService2.getServicePrefix()+"_"+findService2.getId());
			}
			findServiceDao.update(findService);
		}else {

			FindService findService2 = findServiceDao.get(findService.getId());
			findService2.setName(findService.getName());
			findService2.setOrd(findService.getOrd());
			findService2.setMemo(findService.getMemo());
			findService2.setStatus(findService.getStatus());
			findService2.setUpdateDate(DateUtil.getSystemTime());
			findService2.setIsLogin(findService.getIsLogin());
			findService2.setClickEvent(findService.getClickEvent());
			findService2.setUrlParams(findService.getUrlParams());
			findService2.setServiceUrl(findService.getServiceUrl());


			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",findService2, dictPath);
			addFileData(androidFileData, "android",findService2, dictPath);
			addFileData(wechatFileData, "wechat",findService2, dictPath);
			findServiceDao.update(findService2);
		}
	}



	/**
	 * 添加商品图片信息到列表中
	 * @param fileData 图片信息
	 * @param fileType 图片类型
	 * @param findService 
	 * @param dictPath 字典存储路径
	 */
	private void addFileData(String fileData,String fileType,FindService findService,String dictPath){

		if (null!=fileData && !"".equals(fileData)) {
			Map<String,Object> map = JSONUtil.getMap(fileData);
			if (map.get("id")==null) {
				try {
					//存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
					String saveName = map.get("saveName")+"";

					if ("ios".equals(fileType)) {//ios
						findService.setIosFileName(saveName);
					}else if ("android".equals(fileType)) {
						findService.setAndroidFileName(saveName);
					}else {
						findService.setWechatFileName(saveName);
					}

					String relative_path = findService.getRelativePath();
					String root_path = findService.getRootPath();

					if (null==relative_path || "".equals(relative_path)) {
						//日期作为相对路径
						SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");
						relative_path = formatdate.format(new Date());
						findService.setRelativePath(relative_path);
					}

					if (null==root_path || "".equals(root_path)) {
						root_path = dictPath;
						findService.setRootPath(root_path);
					}

					FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		}else {
			if ("android".equals(fileType)) {
				findService.setAndroidFileName(null);
			}else if ("ios".equals(fileType)) {
				findService.setIosFileName(null);
			}else if ("wechat".equals(fileType)) {
				findService.setWechatFileName(null);
			}
		}
	}

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		String type = request.getParameter("type");
		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {

			FindService findService = findServiceDao.get(Long.valueOf(id));
			String fileName = "";
			if ("ios".equals(type)) {
				fileName = findService.getIosFileName();
			}else if ("android".equals(type)) {
				fileName = findService.getAndroidFileName();
			}else {
				fileName = findService.getWechatFileName();
			}
			fileDownMode.setFilePath(findService.getRootPath()+findService.getRelativePath()+fileName);
		}
		return fileDownMode;
	}


	public List<Map<String, Object>> getTreeList(String serviceType) {

		Map<String,Object> paramsMap = new HashMap<String,Object>();
		if(null != serviceType){
			paramsMap.put("serviceType", serviceType);
		}else{
			paramsMap.put("serviceType", 0);
		}

		List<Map<String,Object>> treeList = findServiceDao.getTreeList(paramsMap);

		if (null==treeList) {
			treeList = new ArrayList<Map<String,Object>>();
		}

		Map<String,Object> root =  new HashMap<String, Object>();
		root.put("id", 0);
		if(null != serviceType && "0".equals(serviceType)){
			root.put("text", "公共服务");
		}else{
			root.put("text", "发现服务");
		}

		root.put("dataType", 0);
		treeList.add(root);
		return treeList;
	}
}