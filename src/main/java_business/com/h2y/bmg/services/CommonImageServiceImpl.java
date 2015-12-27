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

import com.h2y.bmg.dao.ICommonImageDao;
import com.h2y.bmg.entity.CommonImage;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：一般图片（加载图片）业务操作接口实现类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("commonImageService")
public class CommonImageServiceImpl implements ICommonImageService,IFileDownService{
	
	private static final Logger logger = Logger.getLogger(CommonImageServiceImpl.class);

	@Autowired
	protected ICommonImageDao commonImageDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String typeCode = request.getParameter("typeCode");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("typeCode", typeCode);
		List<Map<String,Object>> dataList = commonImageDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", commonImageDao.getListRows(map));
		return gridData;
	}
	
	public void save(HttpServletRequest request,String op, CommonImage commonImage,SysDictMain sysDictMain) {

		String iosFileData = request.getParameter("iosFileData");
		String androidFileData = request.getParameter("androidFileData");
		String wechatFileData = request.getParameter("wechatFileData");

		String dictPath = sysDictMain.getDictValue();
		if (op.equals("add")) {

			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",commonImage, dictPath);
			addFileData(androidFileData, "android",commonImage, dictPath);
			addFileData(wechatFileData, "wechat",commonImage, dictPath);
			
			commonImage.setCreateDate(DateUtil.getSystemTime());
			commonImage.setUpdateDate(DateUtil.getSystemTime());
			commonImageDao.add(commonImage);
		}else {
			
			CommonImage commonImage2 = commonImageDao.get(commonImage.getId());
			commonImage2.setLogoName(commonImage.getLogoName());
			commonImage2.setUpdateDate(DateUtil.getSystemTime());
			commonImage2.setStartDate(commonImage.getStartDate());
			commonImage2.setEndDate(commonImage.getEndDate());
			commonImage2.setStatus(commonImage.getStatus());
			commonImage2.setIsDefault(commonImage.getIsDefault());
			commonImage2.setMemo(commonImage.getMemo());
			commonImage2.setOrd(commonImage.getOrd());
			
			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",commonImage2, dictPath);
			addFileData(androidFileData, "android",commonImage2, dictPath);
			addFileData(wechatFileData, "wechat",commonImage2, dictPath);
			commonImageDao.update(commonImage2);
		}
	}


	/**
	 * 添加商品图片信息到列表中
	 * @param fileDataList 图片对象列表
	 * @param fileData_array 图片数据数组
	 * @param commonImage 一般图片
	 * @param dictPath 字典存储路径
	 */
	private void addFileData(String fileData,String fileType,CommonImage commonImage,String dictPath){

		if (null!=fileData && !"".equals(fileData)) {
			Map<String,Object> map = JSONUtil.getMap(fileData);
			if (map.get("id")==null) {
				try {
					//存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
					String saveName = map.get("saveName")+"";
					
					if ("ios".equals(fileType)) {//ios
						commonImage.setIosFileName(saveName);
					}else if ("android".equals(fileType)) {
						commonImage.setAndroidFileName(saveName);
					}else {
						commonImage.setWechatFileName(saveName);
					}

					String relative_path = commonImage.getRelativePath();
					String root_path = commonImage.getRootPath();

					if (null==relative_path || "".equals(relative_path)) {
						//日期作为相对路径
						SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");
						relative_path = formatdate.format(new Date());
						commonImage.setRelativePath(relative_path);
					}

					if (null==root_path || "".equals(root_path)) {
						root_path = dictPath;
						commonImage.setRootPath(root_path);
					}

					FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		}else {
			if ("android".equals(fileType)) {
				commonImage.setAndroidFileName(null);
			}else if ("ios".equals(fileType)) {
				commonImage.setIosFileName(null);
			}else if ("wechat".equals(fileType)) {
				commonImage.setWechatFileName(null);
			}
		}
	}

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		String type = request.getParameter("type");
		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {
			CommonImage commonImage = commonImageDao.get(Long.valueOf(id));
			String fileName = "";
			if ("ios".equals(type)) {
				fileName = commonImage.getIosFileName();
			}else if ("android".equals(type)) {
				fileName = commonImage.getAndroidFileName();
			}else {
				fileName = commonImage.getWechatFileName();
			}
			fileDownMode.setFilePath(commonImage.getRootPath()+commonImage.getRelativePath()+fileName);
		}
		return fileDownMode;
	}
}