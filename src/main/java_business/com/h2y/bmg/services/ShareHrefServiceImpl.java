package com.h2y.bmg.services;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.IShareHrefDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.ShareHref;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 */
@Service("shareHrefService")
public class ShareHrefServiceImpl implements IShareHrefService,IFileDownService{


	@Autowired
	protected IShareHrefDao shareHrefDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param shareHref
	 *
	 */
	public void add(ShareHref shareHref) {
		// TODO Auto-generated method stub

		shareHrefDao.add(shareHref);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		shareHrefDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	shareHrefDao.deleteByIds(ids);
	//}

	public void update(ShareHref shareHref) {
		// TODO Auto-generated method stub
		shareHrefDao.update(shareHref);
	}

	public ShareHref get(long id) {
		// TODO Auto-generated method stub
		return shareHrefDao.get(id);
	}



	/**
	 * 获取分享列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = shareHrefDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", shareHrefDao.getListRows(map));
		return gridData;
	}



	/**
	 * 保存
	 * @param request
	 * @param shareHref
	 * @param op
	 * @param dictPath
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request,ShareHref shareHref,String op,String dictPath){

		//获取上传图片
		String [] file_array = request.getParameterValues("shareHref_file");

		if("add".equals(op)){
			//图片
			setFileData(file_array,shareHref,dictPath);

			shareHref.setHrefStatus(0);
			shareHref.setCreateDate(DateUtil.getSystemTime());
			shareHref.setUpdateDate(DateUtil.getSystemTime());
			shareHrefDao.add(shareHref);

		}else{
			ShareHref shareHref2 = shareHrefDao.get(shareHref.getId());
			//若图片未修改
			shareHref.setFileName(shareHref2.getFileName());
			shareHref.setFileSize(shareHref2.getFileSize());
			shareHref.setFileSuffix(shareHref2.getFileSuffix());
			shareHref.setDiskFileName(shareHref2.getDiskFileName());
			shareHref.setRelativePath(shareHref2.getRelativePath());
			shareHref.setRootPath(shareHref2.getRootPath());

			//图片
			setFileData(file_array,shareHref,dictPath);

			shareHref.setCreateDate(shareHref2.getCreateDate());
			shareHref.setUpdateDate(DateUtil.getSystemTime());
			shareHrefDao.update(shareHref);

		}



	}


	/**
	 * 设置活动的图片信息
	 * @param file_array 上传文件信息
	 * @param findActivity 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,ShareHref shareHref,String dictPath){

		//设置广告图片
		if (null!=file_array && file_array.length >0) {

			String fileJsonData = file_array[0];
			Map<String,Object> map = JSONUtil.getMap(fileJsonData);
			if (map.get("id")==null) {

				String saveName = map.get("saveName")+"";
				String savePath = "";
				try {
					savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				shareHref.setDiskFileName(saveName);
				shareHref.setFileName(map.get("fileName")+"");
				shareHref.setFileSize(Long.valueOf(map.get("fileSize")+""));
				shareHref.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				shareHref.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				shareHref.setRelativePath(relative_path);
				File filePath = new File(dictPath+relative_path);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				try {

					if (!new File(dictPath+relative_path+saveName).exists()) {
						FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 获取图片
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {

			ShareHref shareHref = shareHrefDao.get(Long.valueOf(id));
			String fileName = shareHref.getDiskFileName();
			fileDownMode.setFilePath(shareHref.getRootPath()+shareHref.getRelativePath()+fileName);
		}
		return fileDownMode;
	}


}