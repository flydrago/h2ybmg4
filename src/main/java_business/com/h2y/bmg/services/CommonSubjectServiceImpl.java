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

import com.h2y.bmg.dao.ICommonSubjectDao;
import com.h2y.bmg.entity.CommonSubject;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：活动主题业务类接口实现类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("commonSubjectService")
public class CommonSubjectServiceImpl implements ICommonSubjectService,IFileDownService{
	
	private static final Logger logger = Logger.getLogger(CommonSubjectServiceImpl.class);

	@Autowired
	protected ICommonSubjectDao commonSubjectDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		List<Map<String,Object>> dataList = commonSubjectDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", commonSubjectDao.getListRows(map));
		return gridData;
	}
	
	
	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String activityId = request.getParameter("activityId");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("activityId", Long.valueOf(activityId));
		List<Map<String,Object>> dataList = commonSubjectDao.getSelectListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", commonSubjectDao.getSelectListRows(map));
		return gridData;
	}


	public void save(HttpServletRequest request,String op, CommonSubject commonSubject,SysDictMain sysDictMain) {

		String iosFileData = request.getParameter("iosFileData");
		String androidFileData = request.getParameter("androidFileData");
		String wechatFileData = request.getParameter("wechatFileData");

		String dictPath = sysDictMain.getDictValue();
		if (op.equals("add")) {

			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",commonSubject, dictPath);
			addFileData(androidFileData, "android",commonSubject, dictPath);
			addFileData(wechatFileData, "wechat",commonSubject, dictPath);
			
			commonSubject.setCreateDate(DateUtil.getSystemTime());
			commonSubjectDao.add(commonSubject);
		}else {

			CommonSubject commonSubject2 = commonSubjectDao.get(commonSubject.getId());
			commonSubject2.setCreateDate(DateUtil.getSystemTime());
			commonSubject2.setSubjectName(commonSubject.getSubjectName());
			commonSubject2.setSubjectContent(commonSubject.getSubjectContent());
			commonSubject2.setSubjectType(commonSubject.getSubjectType());
			commonSubject2.setStatus(commonSubject.getStatus());
			commonSubject2.setMemo(commonSubject.getMemo());
			
			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",commonSubject2, dictPath);
			addFileData(androidFileData, "android",commonSubject2, dictPath);
			addFileData(wechatFileData, "wechat",commonSubject2, dictPath);
			commonSubjectDao.update(commonSubject2);
		}
	}


	/**
	 * 添加商品图片信息到列表中
	 * @param fileDataList 图片对象列表
	 * @param fileData_array 图片数据数组
	 * @param goods 商品信息
	 * @param dictPath 字典存储路径
	 */
	private void addFileData(String fileData,String fileType,CommonSubject commonSubject,String dictPath){

		if (null!=fileData && !"".equals(fileData)) {
			Map<String,Object> map = JSONUtil.getMap(fileData);
			if (map.get("id")==null) {
				try {
					//存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
					String saveName = map.get("saveName")+"";
					
					if ("ios".equals(fileType)) {//ios
						commonSubject.setIosFileName(saveName);
					}else if ("android".equals(fileType)) {
						commonSubject.setAndroidFileName(saveName);
					}else {
						commonSubject.setWechatFileName(saveName);
					}

					String relative_path = commonSubject.getRelativePath();
					String root_path = commonSubject.getRootPath();

					if (null==relative_path || "".equals(relative_path)) {
						//日期作为相对路径
						SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");
						relative_path = formatdate.format(new Date());
						commonSubject.setRelativePath(relative_path);
					}

					if (null==root_path || "".equals(root_path)) {
						root_path = dictPath;
						commonSubject.setRootPath(root_path);
					}

					FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		}else {
			if ("android".equals(fileType)) {
				commonSubject.setAndroidFileName(null);
			}else if ("ios".equals(fileType)) {
				commonSubject.setIosFileName(null);
			}else if ("wechat".equals(fileType)) {
				commonSubject.setWechatFileName(null);
			}
		}
	}

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		String type = request.getParameter("type");
		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {
			
			CommonSubject commonSubject = commonSubjectDao.get(Long.valueOf(id));
			String fileName = "";
			if ("ios".equals(type)) {
				fileName = commonSubject.getIosFileName();
			}else if ("android".equals(type)) {
				fileName = commonSubject.getAndroidFileName();
			}else {
				fileName = commonSubject.getWechatFileName();
			}
			fileDownMode.setFilePath(commonSubject.getRootPath()+commonSubject.getRelativePath()+fileName);
		}
		return fileDownMode;
	}
}