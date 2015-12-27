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
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.ISysDepartmentDao;
import com.h2y.bmg.dao.ISysShopFileDao;
import com.h2y.bmg.dao.ISysShopInfoDao;
import com.h2y.bmg.entity.BaseResult;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysDepartment;
import com.h2y.bmg.entity.SysShopFile;
import com.h2y.bmg.entity.SysShopInfo;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.dict.DictUtil;
import com.h2y.security.Base64Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-18
 * email:info@hwttnet.com
 */
@Service("sysDepartmentService")
public class SysDepartmentServiceImpl implements ISysDepartmentService, IFileDownService{
	
	private static Logger logger = Logger.getLogger(SysDepartmentServiceImpl.class);

	@Autowired
	protected ISysDepartmentDao sysDepartmentDao;


	@Autowired
	protected ISysShopFileDao sysShopFileDao;


	@Autowired
	protected ISysShopInfoDao sysShopInfoDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysDepartment
	 *
	 */
	public void add(SysDepartment sysDepartment) {


		sysDepartmentDao.add(sysDepartment);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysDepartmentDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysDepartmentDao.deleteByIds(ids);
	//}

	public void update(SysDepartment sysDepartment) {
		// TODO Auto-generated method stub
		sysDepartmentDao.update(sysDepartment);
	}

	public SysDepartment get(long id) {
		// TODO Auto-generated method stub
		return sysDepartmentDao.get(id);
	}


	public List<SysDepartment> getList(SysDepartment sysDepartment){
		List<SysDepartment> list = sysDepartmentDao.getList(sysDepartment);
		//
		//sysDepartment = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<SysDepartment> getListPage(Map<String,Object> map){
		//map.put("aaa", new SysDepartment());
		return sysDepartmentDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return sysDepartmentDao.getRows(map);
	}

	/**
	 * 是否子级
	 * @param sysDepartment
	 * @return
	 */
	public boolean isHasChild(SysDepartment sysDepartment){

		return sysDepartmentDao.getChildDepartmentRows(sysDepartment.getId())>0;
	}


	/**
	 * 得到表格列数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param parentId 父级编码
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId,long parentId){

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");


		Map<String,Object> map = new HashMap<String, Object>();
		map.put("parentId",parentId);
		map.put("unitId", unitId);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));

		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = sysDepartmentDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", sysDepartmentDao.getListRows(map));
		return gridData;
	}


	/**
	 * 根据父级编码，得到子级树节点
	 * @param unitId 单位Id
	 * @param parentId 父级Id
	 * @return
	 */
	public List<Map<String,Object>> getChildTreeData(long unitId,long parentId){

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", unitId);
		map.put("parentId",parentId);

		List<Map<String,Object>> dataList = sysDepartmentDao.getChildTreeData(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		return dataList;
	}


	/**
	 * 保存部门（添加、修改）
	 * @param sysDepartment 部门对象
	 * @param op 操作类型，add:添加、modify:修改
	 */
	public void save(HttpServletRequest requst,SysDepartment sysDepartment,SysShopInfo sysShopInfo,String op){

		if (op.equals("add")) {

			String prefix = "";
			if (sysDepartment.getParentId() != 0) {
				SysDepartment parentSysDepartment = sysDepartmentDao.get(sysDepartment.getParentId());
				prefix = parentSysDepartment.getDeptCode();
			}else {
				prefix = sysDepartment.getUnitId()+"";
			}
			sysDepartmentDao.add(sysDepartment);
			sysDepartment.setDeptCode(prefix+"_"+sysDepartment.getId());
			sysDepartmentDao.update(sysDepartment);



			//门店时，修改门店的顶级部门为有门店的部门
			if (sysDepartment.getDeptType()==1) {
				if (prefix.contains("_")) {

					prefix = prefix.substring(prefix.indexOf("_")+1, prefix.length()).replaceAll("_", ",");
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("ids", prefix);
					map.put("ifHasShop", 1);
					sysDepartmentDao.updateIfShopByIds(map);
				}
				//门店信息
				sysShopInfo.setShopId(sysDepartment.getId());
				sysShopInfo.setCreateDate(DateUtil.getSystemTime());
				sysShopInfo.setStatus(0);
				sysShopInfoDao.add(sysShopInfo);
				//添加门店图片
				addFileData(requst,sysDepartment);
			}
		}else {

			SysDepartment sysDepartment2 = sysDepartmentDao.get(sysDepartment.getId());
			sysDepartment2.setDeptDesc(sysDepartment.getDeptDesc());
			sysDepartment2.setDeptLeader(sysDepartment.getDeptLeader());
			sysDepartment2.setDeptName(sysDepartment.getDeptName());
			sysDepartment2.setDeptOrd(sysDepartment.getDeptOrd());
			sysDepartment2.setDeptShortName(sysDepartment.getDeptShortName());
			sysDepartment2.setDeptType(sysDepartment.getDeptType());
			sysDepartment2.setGpsLatitude(sysDepartment.getGpsLatitude());
			sysDepartment2.setGpsLongitude(sysDepartment.getGpsLongitude());
			sysDepartment2.setPosition(sysDepartment.getPosition());
			sysDepartment2.setReverse1(sysDepartment.getReverse1());
			sysDepartment2.setReverse2(sysDepartment.getReverse2());
			sysDepartmentDao.update(sysDepartment2);


			if (sysDepartment2.getDeptType()==1) {
				//门店信息
				SysShopInfo sysShopInfo2 = this.sysShopInfoDao.get(sysShopInfo.getId());
				if(null == sysShopInfo2){
					sysShopInfo.setCreateDate(DateUtil.getSystemTime());
					sysShopInfo.setStatus(0);
					sysShopInfo.setShopId(sysDepartment2.getId());
					sysShopInfoDao.add(sysShopInfo);
				}else{
					sysShopInfo.setUpdateDate(DateUtil.getSystemTime());
					sysShopInfo.setCreateDate(sysShopInfo2.getCreateDate());
					sysShopInfo.setStatus(0);
					sysShopInfoDao.update(sysShopInfo);
					//删除原门店图片
					sysShopFileDao.updateByShopId(sysShopInfo.getShopId());
				}

				//添加门店图片
				addFileData(requst,sysDepartment2);
			}
		}
	}


	/**
	 * 添加门店图片
	 * 
	 * @param request
	 * @param goods
	 */
	private void addFileData(HttpServletRequest request, SysDepartment sysDepartment) {

		String[] logoData_array = request.getParameterValues("logoData");
		String[] picData_array = request.getParameterValues("picData");

		String logoData_path = DictUtil.getMainByCode("shopLogoData_path").getDictValue();
		String picData_path = DictUtil.getMainByCode("shopPicData_path").getDictValue();

		List<SysShopFile> fileDataList = new ArrayList<SysShopFile>();
		// 店铺Logo图片信息
		addDataToFileDataList(fileDataList, logoData_array, sysDepartment,logoData_path);

		// 店铺图片信息
		addDataToFileDataList(fileDataList, picData_array, sysDepartment, picData_path);
		if (!fileDataList.isEmpty()) {
			sysShopFileDao.addBatch(fileDataList);
		}
	}


	private void addDataToFileDataList(List<SysShopFile> fileDataList,
			String[] fileData_array, SysDepartment sysDepartment, String dictPath) {

		if (fileData_array != null && fileData_array.length > 0) {

			int i = 0;
			for (String logoData : fileData_array) {

				Map<String, Object> map = JSONUtil.getMap(logoData);
				if (map.get("id") != null) {

					SysShopFile sysShopFile = sysShopFileDao.get(Long.valueOf(map
							.get("id") + ""));
					sysShopFile.setStatus(0);
					fileDataList.add(sysShopFile);
				} else {
					try {

						// 存储路径
						String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath") + "");
						String saveName = map.get("saveName") + "";
						String fileName = map.get("fileName") + "";
						String fileType = map.get("fileType") + "";

						SysShopFile sysShopFile = new SysShopFile();
						sysShopFile.setDiskFileName(saveName);
						sysShopFile.setFileName(fileName);
						sysShopFile.setFileSuffix(saveName.substring(saveName.lastIndexOf(".") + 1,saveName.length()));
						// 日期作为相对路径
						SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
						String relative_path = formatdate.format(new Date());
						sysShopFile.setRelativePath(relative_path);
						sysShopFile.setRootPath(dictPath);
						sysShopFile.setFileSize(new File(savePath).length());
						sysShopFile.setShopId(sysDepartment.getId());
						sysShopFile.setStatus(0);
						sysShopFile.setOrd(i);
						sysShopFile.setFileType(Integer.parseInt(fileType));						
						sysShopFile.setCreateDate(DateUtil.getSystemTime());					
						FileUtils.copyFile(new File(savePath), new File(dictPath + relative_path + saveName));
						fileDataList.add(sysShopFile);
					} catch (Exception e) {
						e.printStackTrace();

					}
				}
				i++;
			}
		}
	}


	/**
	 * 逻辑删除部门
	 * @param sysDepartment
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteDepartment(SysDepartment sysDepartment){

		SysDepartment sysDepartment2 = sysDepartmentDao.get(sysDepartment.getId());
		sysDepartment2.setIfDelete(1);
		sysDepartmentDao.update(sysDepartment2);


		String code = sysDepartment2.getDeptCode();
		String unitIdStr = sysDepartment2.getUnitId()+"";
		String idStr = sysDepartment2.getId()+"";

		if (code.contains("_")) {

			String code_array [] = code.split("_");

			for (String string : code_array) {
				if (!string.equals(unitIdStr) && !string.equals(idStr)) {
					//如果父级下面没有门店，就修改ifshop字段
					if (sysDepartmentDao.getShopRowsByPid(Long.valueOf(string))<=0) {

						Map<String,Object> map = new HashMap<String, Object>();
						map.put("ids", string);
						map.put("ifHasShop", 0);
						sysDepartmentDao.updateIfShopByIds(map);
					}
				}
			}
		}
	}


	/**
	 * 获取图片
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		FileDownMode fileDownMode = new FileDownMode();
		if (id != null && !id.equals("")) {

			SysShopFile sysShopFile = sysShopFileDao.get(Long.valueOf(id));
			fileDownMode.setSaveName(sysShopFile.getFileName());
			fileDownMode.setFilePath(sysShopFile.getRootPath()
					+ sysShopFile.getRelativePath() + sysShopFile.getDiskFileName());
		}
		return fileDownMode;
	}


	public void updateDeptXgh(HttpServletRequest requst,
			SysDepartment sysDepartment,SysUnits sysUnits,String op) {
		
		if (null==sysUnits.getS3ucode()||"".equals(sysUnits.getS3ucode())) {
			
			logger.error("部门象过河更新失败！信息为：单位象过河编码为空！");
		}
		
		SysDepartment sysDepartment2 = sysDepartmentDao.get(sysDepartment.getId());
		
		Map<String,Object> postData = new HashMap<String, Object>();
		postData.put("unitid", sysUnits.getId());
		postData.put("unitcode", sysUnits.getUnitCode());
		postData.put("xghucode", sysUnits.getS3ucode());
		postData.put("deptid", sysDepartment2.getId());
		postData.put("deptpid", sysDepartment2.getParentId());
		postData.put("xghdcode", null==sysDepartment2.getXghdcode()?"":sysDepartment2.getXghdcode());
		
		if (sysDepartment2.getParentId()!=0) {
			
			SysDepartment sysDepartment3 = sysDepartmentDao.get(sysDepartment2.getParentId());
			postData.put("xghdpcode", sysDepartment3.getXghdcode());
		}else {
			postData.put("xghdpcode", "");
		}
		postData.put("dpetcode", sysDepartment2.getDeptCode());
		postData.put("deptname", sysDepartment2.getDeptName());
		postData.put("deptname2", sysDepartment2.getDeptShortName());
		postData.put("ord", sysDepartment2.getDeptOrd());
		
		//象过河wbs方法名
		postData.put("method", null==sysDepartment2.getXghdcode()||"".equals(sysDepartment2.getXghdcode())?"addDept":"updateDept");
		
		BaseResult baseResult = DataRequestUtil.getCommonRequestData("xgh/common.htm", postData);
		
		logger.debug("返回结果："+JSONUtil.getJson(baseResult));
		if (baseResult.getResultFlag()==1) {
			
			logger.debug("部门象过河更新返回信息为："+baseResult.getResultData());
			@SuppressWarnings("unchecked")
			Map<String,Object> resultData = (Map<String, Object>) baseResult.getResultData();
			if (null!=resultData.get("xghdcode")) {
				sysDepartment2.setXghdcode(resultData.get("xghdcode")+"");
			}
			
			if (null!=resultData.get("xghcreatedate")) {
				sysDepartment2.setXghcreatedate(DateUtil.toDate(resultData.get("xghcreatedate")+"", DateUtil.YYYY_MM_DD_HH_MM_SS));
			}
			sysDepartmentDao.update(sysDepartment2);
		}else {
			
			logger.error("部门象过河更新失败！信息为："+baseResult.getResultMsg());
		}
	}
}