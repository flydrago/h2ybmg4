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

import com.h2y.bmg.dao.IPromoteActivityDao;
import com.h2y.bmg.dao.IPromoteActivityDetailDao;
import com.h2y.bmg.dao.IPromoteActivityRewardRtDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.PromoteActivity;
import com.h2y.bmg.entity.PromoteActivityDetail;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 推广活动维护
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-08-13
 * email:info@hwttnet.com
 */
@Service("promoteActivityService")
public class PromoteActivityServiceImpl implements IPromoteActivityService,IFileDownService{


	@Autowired
	protected IPromoteActivityDao promoteActivityDao;

	@Autowired
	protected IPromoteActivityDetailDao promoteActivityDetailDao;

	@Autowired
	protected IPromoteActivityRewardRtDao promoteActivityRewardRtDao;


	/**
	 * 获取推广活动列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		//分页
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		//排序
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		//查询
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
		List<Map<String,Object>> dataList = promoteActivityDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", promoteActivityDao.getListRows(map));
		return gridData;
	}


	/**
	 * 保存
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request,String op,PromoteActivity promoteActivity,PromoteActivityDetail promoteActivityDetail,String dictPath){

		//获取上传文件
		String [] file_array = request.getParameterValues("promoteActivity_file");

		if("add".equals(op)){
			//处理图片
			setFileData(file_array,promoteActivity,dictPath);
			//设置活动状态 0正常 1不启用 -1删除
			promoteActivity.setPromoteStatus(0);
			promoteActivity.setCreateDate(DateUtil.getSystemTime());
			promoteActivity.setUpdateDate(DateUtil.getSystemTime());
			promoteActivity.setClaimNum(0);
			promoteActivityDao.add(promoteActivity);

			//明细
			promoteActivityDetail.setPromoteId(promoteActivity.getId());
			promoteActivityDetailDao.add(promoteActivityDetail);

		}else{
			PromoteActivity promoteActivity2 = promoteActivityDao.get(promoteActivity.getId());
			//若图片未修改
			promoteActivity.setFileName(promoteActivity2.getFileName());
			promoteActivity.setFileSize(promoteActivity2.getFileSize());
			promoteActivity.setFileSuffix(promoteActivity2.getFileSuffix());
			promoteActivity.setDiskFileName(promoteActivity2.getDiskFileName());
			promoteActivity.setRelativePath(promoteActivity2.getRelativePath());
			promoteActivity.setRootPath(promoteActivity2.getRootPath());
			promoteActivity.setClaimNum(promoteActivity2.getClaimNum());

			//处理图片
			setFileData(file_array,promoteActivity,dictPath);

			promoteActivity.setCreateDate(promoteActivity2.getCreateDate());
			promoteActivity.setUpdateDate(DateUtil.getSystemTime());
			promoteActivityDao.update(promoteActivity);

			//明细 如果明细为空则新增明细
			promoteActivityDetail.setPromoteId(promoteActivity.getId());
			if(promoteActivityDetail.getId() == 0){
				promoteActivityDetailDao.add(promoteActivityDetail);
			}else{
				promoteActivityDetailDao.update(promoteActivityDetail);
			}



		}



	}


	/**
	 * 设置活动的图片信息
	 * @param file_array 上传文件信息
	 * @param findActivity 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,PromoteActivity promoteActivity,String dictPath){

		//设置活动图片
		if (null!=file_array && file_array.length >0) {

			String fileJsonData = file_array[0];
			Map<String,Object> map = JSONUtil.getMap(fileJsonData);
			if (map.get("id")==null) {//新增图片

				String saveName = map.get("saveName")+"";
				String savePath = "";
				try {
					savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				promoteActivity.setDiskFileName(saveName);
				promoteActivity.setFileName(map.get("fileName")+"");
				promoteActivity.setFileSize(Long.valueOf(map.get("fileSize")+""));
				promoteActivity.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				promoteActivity.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				promoteActivity.setRelativePath(relative_path);
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




	public void add(PromoteActivity promoteActivity) {
		// TODO Auto-generated method stub

		promoteActivityDao.add(promoteActivity);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		promoteActivityDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	promoteActivityDao.deleteByIds(ids);
	//}

	public void update(PromoteActivity promoteActivity) {
		// TODO Auto-generated method stub
		promoteActivityDao.update(promoteActivity);
	}

	public PromoteActivity get(long id) {
		// TODO Auto-generated method stub
		return promoteActivityDao.get(id);
	}

	/**
	 * 获取图片
	 * @param request
	 * @param id
	 * @return
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {

			PromoteActivity promoteActivity = promoteActivityDao.get(Long.valueOf(id));
			String fileName = promoteActivity.getDiskFileName();
			fileDownMode.setFilePath(promoteActivity.getRootPath()+promoteActivity.getRelativePath()+fileName);
		}
		return fileDownMode;
	}


	/**
	 * 查看活动人员列表
	 */
	public Map<String, Object> getUserRtList(HttpServletRequest request) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String promoteId = request.getParameter("promoteId");
		String ifQuery = request.getParameter("ifQuery");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("promoteId", promoteId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = promoteActivityDao.getUserRtList(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", promoteActivityDao.getUserRtListRows(map));
		return gridData;
	}


	public Map<String, Object> getSelectGridData(HttpServletRequest request,
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
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		
		List<Map<String,Object>> dataList = promoteActivityDao.getSelectListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", promoteActivityDao.getSelectListRows(map));
		return gridData;
	}
}