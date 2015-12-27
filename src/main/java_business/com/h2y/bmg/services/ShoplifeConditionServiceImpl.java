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

import com.h2y.bmg.dao.IShoplifeConditionDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.ShoplifeCondition;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-29
 * email:info@hwttnet.com
 */
@Service("shoplifeConditionService")
public class ShoplifeConditionServiceImpl implements IShoplifeConditionService,IFileDownService{


	@Autowired
	protected IShoplifeConditionDao shoplifeConditionDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param shoplifeCondition
	 *
	 */
	public void add(ShoplifeCondition shoplifeCondition) {
		// TODO Auto-generated method stub

		shoplifeConditionDao.add(shoplifeCondition);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		shoplifeConditionDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	shoplifeConditionDao.deleteByIds(ids);
	//}

	public void update(ShoplifeCondition shoplifeCondition) {
		// TODO Auto-generated method stub
		shoplifeConditionDao.update(shoplifeCondition);
	}

	public ShoplifeCondition get(long id) {
		// TODO Auto-generated method stub
		return shoplifeConditionDao.get(id);
	}


	public List<ShoplifeCondition> getList(ShoplifeCondition shoplifeCondition){
		List<ShoplifeCondition> list = shoplifeConditionDao.getList(shoplifeCondition);
		//
		//shoplifeCondition = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<ShoplifeCondition> getListPage(Map<String,Object> map){
		//map.put("aaa", new ShoplifeCondition());
		return shoplifeConditionDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return shoplifeConditionDao.getRows(map);
	}



	public Map<String, Object> getGridData(HttpServletRequest request) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String id = request.getParameter("id");
		if (sortname!=null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("id", Long.valueOf(id));
		List<Map<String,Object>> dataList = shoplifeConditionDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", shoplifeConditionDao.getListRows(map));
		return gridData;
	}


	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op, ShoplifeCondition shoplifeCondition,String dictPath) {
		String [] file_array = request.getParameterValues("lifeLogo_file");

		if (op.equals("add")) {

			shoplifeCondition.setCreateDate(DateUtil.getSystemTime());
			shoplifeCondition.setConditionCode("");
			//图片
			setFileData(file_array,shoplifeCondition,dictPath);
			shoplifeConditionDao.add(shoplifeCondition);

			//更新编码
			if (shoplifeCondition.getParentId()==0) {

				shoplifeCondition.setConditionCode(shoplifeCondition.getId()+"");
			}else {

				ShoplifeCondition shoplifeCondition2 = shoplifeConditionDao.get(shoplifeCondition.getParentId());
				shoplifeCondition.setConditionCode(shoplifeCondition2.getConditionCode()+"_"+shoplifeCondition.getId());
			}
			shoplifeConditionDao.update(shoplifeCondition);
		}else {

			ShoplifeCondition shoplifeCondition2 = shoplifeConditionDao.get(shoplifeCondition.getId());
			shoplifeCondition.setUpdateDate(DateUtil.getSystemTime());
			shoplifeCondition.setCreateDate(shoplifeCondition2.getCreateDate());
			shoplifeCondition.setConditionCode(shoplifeCondition2.getConditionCode());
			shoplifeCondition.setFileName(shoplifeCondition2.getFileName());
			shoplifeCondition.setDiskFileName(shoplifeCondition2.getDiskFileName());
			shoplifeCondition.setRootPath(shoplifeCondition2.getRootPath());
			shoplifeCondition.setRelativePath(shoplifeCondition2.getRelativePath());			
			shoplifeCondition.setFileSize(shoplifeCondition2.getFileSize());
			shoplifeCondition.setFileSuffix(shoplifeCondition2.getFileSuffix());
			//图片
			setFileData(file_array, shoplifeCondition, dictPath);
			shoplifeConditionDao.update(shoplifeCondition);
		}
	}

	/**
	 * 得到活动图片下载对象
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		ShoplifeCondition shoplifeCondition = shoplifeConditionDao.get(Long.valueOf(id));

		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setSaveName(shoplifeCondition.getFileName());
		fileDownMode.setFilePath(shoplifeCondition.getRootPath()+shoplifeCondition.getRelativePath()+shoplifeCondition.getDiskFileName());
		return fileDownMode;
	}

	/**
	 * 设置活动的图片信息
	 * @param file_array 上传文件信息
	 * @param findActivity 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,ShoplifeCondition shoplifeCondition,String dictPath){

		//设置广告图片
		if (null!=file_array && file_array.length >0) {

			String fileJsonData = file_array[0];
			System.out.println("**"+file_array[0]);
			Map<String,Object> map = JSONUtil.getMap(fileJsonData);
			if (map.get("id")==null) {

				String saveName = map.get("saveName")+"";
				String savePath = "";
				try {
					savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				shoplifeCondition.setDiskFileName(saveName);
				shoplifeCondition.setFileName(map.get("fileName")+"");
				shoplifeCondition.setFileSize(Long.valueOf(map.get("fileSize")+""));
				shoplifeCondition.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				shoplifeCondition.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				shoplifeCondition.setRelativePath(relative_path);
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

}