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

import com.h2y.bmg.dao.IMemberUserDao;
import com.h2y.bmg.dao.IVoteSubjectDao;
import com.h2y.bmg.dao.IVoteSubjectDetailDao;
import com.h2y.bmg.dao.IVoteSubjectFileDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.VoteSubject;
import com.h2y.bmg.entity.VoteSubjectDetail;
import com.h2y.bmg.entity.VoteSubjectFile;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.MatcherUtil;


/**
 * 

 * @ClassName: VoteSubjectServiceImpl

 * @Description: 投票主题维护业务实现类

 * @author 李剑

 * @date 2015年8月26日 下午6:28:22

 *
 */
@Service("voteSubjectService")
public class VoteSubjectServiceImpl implements IVoteSubjectService,IFileDownService{


	@Autowired
	protected IVoteSubjectDao voteSubjectDao;

	@Autowired
	protected IMemberUserDao memberUserDao;

	@Autowired
	protected IVoteSubjectDetailDao voteSubjectDetailDao;

	@Autowired
	protected IVoteSubjectFileDao voteSubjectFileDao;


	public Map<String, Object> getGridData(HttpServletRequest request,long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		//分页
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		//排序
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();}
		//查询
		String ifQuery = request.getParameter("ifQuery");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);

		List<Map<String,Object>> dataList = voteSubjectDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", voteSubjectDao.getListRows(map));
		return gridData;
	}
	//	投票新增用户
	public Map<String, Object> getNewUserGridData(HttpServletRequest request,long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		//分页
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		//排序
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();}
		//查询
		String ifQuery = request.getParameter("ifQuery");
		String subId = request.getParameter("subId");
		String regSource = request.getParameter("regSource");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("subId", subId);
		map.put("regSource", regSource);
		List<Map<String,Object>> dataList = memberUserDao.getNewListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", memberUserDao.getNewListRows(map));
		return gridData;
	}

	/**
	 * 设置活动的logo图片信息
	 * @param file_array 上传文件信息
	 * @param voteSubject 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,VoteSubject voteSubject,String dictPath){
		//设置活动图片
		if (null!=file_array && file_array.length >0) {
			String fileJsonData = file_array[0];
			Map<String,Object> map = JSONUtil.getMap(fileJsonData);
			if (map.get("id")==null) {
				//新增图片
				String saveName = map.get("saveName")+"";
				String savePath = "";
				try {
					savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				voteSubject.setDiskFileName(saveName);
				voteSubject.setFileName(map.get("fileName")+"");
				voteSubject.setFileSize(Long.valueOf(map.get("fileSize")+""));
				voteSubject.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				voteSubject.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				voteSubject.setRelativePath(relative_path);
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
	 * 
	 * 设置活动的主页图片信息
	 * @param file_array1 上传文件信息
	 * @param voteSubjectFile 活动对象
	 * @param dictPath1 存储路径
	 */
	private void setFileData1(String [] file_array1,VoteSubjectFile voteSubjectFile,String dictPath1){
		//设置活动图片
		if (null!=file_array1 && file_array1.length >0) {
			String fileJsonData = file_array1[0];
			Map<String,Object> map = JSONUtil.getMap(fileJsonData);
			if (map.get("id")==null) {
				//新增图片
				String saveName = map.get("saveName")+"";
				String savePath = "";
				try {
					savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				voteSubjectFile.setDiskFileName(saveName);
				voteSubjectFile.setFileName(map.get("fileName")+"");
				voteSubjectFile.setFileSize(Long.valueOf(map.get("fileSize")+""));
				voteSubjectFile.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				voteSubjectFile.setRootPath(dictPath1);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				voteSubjectFile.setRelativePath(relative_path);
				File filePath = new File(dictPath1+relative_path);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				try {

					if (!new File(dictPath1+relative_path+saveName).exists()) {
						FileUtils.copyFile(new File(savePath), new File(dictPath1+relative_path+saveName));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 保存
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request,String op,VoteSubject voteSubject,VoteSubjectDetail voteSubjectDetail,
			VoteSubjectFile voteSubjectFile,String dictPath,String dictPath1){

		//获取上传文件
		String [] file_array = request.getParameterValues("voteSubject_file");
		String [] file_array1 = request.getParameterValues("voteSubjectFile_file");
		if("add".equals(op)){
			//处理图片
			setFileData(file_array,voteSubject,dictPath);
			voteSubject.setCreateDate(DateUtil.getSystemTime());
			voteSubject.setUpdateDate(DateUtil.getSystemTime());
			voteSubjectDao.add(voteSubject);
			voteSubjectDetail.setSubjectId(voteSubject.getId());
			voteSubjectDetailDao.add(voteSubjectDetail);
			voteSubjectFile.setCreateDate(DateUtil.getSystemTime());
			voteSubjectFile.setSubjectId(voteSubject.getId());
			voteSubjectFile.setOrd(0);
			voteSubjectFile.setFileType(0);
			voteSubjectFile.setIfDelete(0);
			voteSubjectFile.setCreateDate(voteSubjectFile.getCreateDate());
			setFileData1(file_array1,voteSubjectFile,dictPath1);
			voteSubjectFileDao.add(voteSubjectFile);


		}else{
			VoteSubject voteSubject2 = voteSubjectDao.get(voteSubject.getId());
			//若图片未修改
			voteSubject.setFileName(voteSubject2.getFileName());
			voteSubject.setFileSize(voteSubject2.getFileSize());
			voteSubject.setFileSuffix(voteSubject2.getFileSuffix());
			voteSubject.setDiskFileName(voteSubject2.getDiskFileName());
			voteSubject.setRelativePath(voteSubject2.getRelativePath());
			voteSubject.setRootPath(voteSubject2.getRootPath());
			//处理图片
			setFileData(file_array,voteSubject,dictPath);
			voteSubject.setUpdateDate(DateUtil.getSystemTime());
			voteSubject.setCreateDate(voteSubject2.getCreateDate());
			voteSubjectDao.update(voteSubject);

			VoteSubjectFile voteSubjectFile2=voteSubjectFileDao.getBySubjectId(voteSubject.getId());
			//若图片未修改
			voteSubjectFile.setFileName(voteSubjectFile2.getFileName());
			voteSubjectFile.setFileSize(voteSubjectFile2.getFileSize());
			voteSubjectFile.setFileSuffix(voteSubjectFile2.getFileSuffix());
			voteSubjectFile.setDiskFileName(voteSubjectFile2.getDiskFileName());
			voteSubjectFile.setRelativePath(voteSubjectFile2.getRelativePath());
			voteSubjectFile.setRootPath(voteSubjectFile2.getRootPath());
			voteSubjectFile.setSubjectId(voteSubject.getId());
			voteSubjectFile.setCreateDate(voteSubject2.getCreateDate());
			//处理图片
			setFileData1(file_array1,voteSubjectFile,dictPath1);
			VoteSubjectFile voteSubjectFile3 = voteSubjectFileDao.getBySubjectId(voteSubject.getId());
			voteSubjectFile.setId(voteSubjectFile3.getId());
			voteSubjectFile3.setIfDelete(-1);
			voteSubjectFileDao.update(voteSubjectFile3);
			voteSubjectFileDao.add(voteSubjectFile);

			//详细数据修改
			VoteSubjectDetail voteSubjectDetail3 = voteSubjectDetailDao.getBySubjectId(voteSubject.getId());
			voteSubjectDetail3.setSubjectRule(voteSubjectDetail.getSubjectRule());
			voteSubjectDetail3.setText1(voteSubjectDetail.getText1());
			voteSubjectDetail3.setText2(voteSubjectDetail.getText2());
			voteSubjectDetailDao.update(voteSubjectDetail3);
		}
	}

	/**
	 * 获取投票主题图片
	 * @param request
	 * @param id
	 * @return
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		FileDownMode fileDownMode = new FileDownMode();
		String type = request.getParameter("type");

		if (!MatcherUtil.checkNumber(id)) {
			return null;
		}

		if (type.equals("logo")) {

			VoteSubject voteSubject = voteSubjectDao.get(Long.valueOf(id));
			String fileName = voteSubject.getDiskFileName();
			fileDownMode.setFilePath(voteSubject.getRootPath()+voteSubject.getRelativePath()+fileName);
		}else {

			VoteSubjectFile voteSubjectFile = voteSubjectFileDao.get(Long.valueOf(id));
			String fileName = voteSubjectFile.getDiskFileName();
			fileDownMode.setFilePath(voteSubjectFile.getRootPath()+voteSubjectFile.getRelativePath()+fileName);
		}
		return fileDownMode;
	}


}