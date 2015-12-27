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

import com.h2y.bmg.basic.WbsKeys.XghKeys;
import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.dao.ISysUnitsFileDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysPrivilegeList;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUnitsFile;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.PrivilegeKey;
import com.h2y.security.Base64Util;
import com.h2y.security.MD5Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */
@Service("sysUnitsService")
public class SysUnitsServiceImpl implements ISysUnitsService,IFileDownService{

	private static final Logger logger = Logger.getLogger(SysUnitsServiceImpl.class);


	@Autowired
	protected ISysUnitsDao sysUnitsDao;


	@Autowired
	protected ISysUserService sysUserService;


	@Autowired
	protected ISysPrivilegeListDao sysPrivilegeListDao;

	@Autowired
	protected ISysUnitsFileDao sysUnitsFileDao;

	@Autowired
	protected IStorehouseService storehouseService;

	@Autowired
	protected ISysLogService sysLogService;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysUnits
	 *
	 */
	public void add(SysUnits sysUnits) {
		sysUnitsDao.add(sysUnits);
	}


	public void delete(long id) {
		sysUnitsDao.deleteById(id);
	}

	public void update(SysUnits sysUnits) {
		sysUnitsDao.update(sysUnits);
	}

	public SysUnits get(long id) {
		return sysUnitsDao.get(id);
	}


	/**
	 * 得到grid列表
	 * @param request
	 * @param parentId
	 * @return
	 */
	public Map<String,Object> getGridData(long unitId,HttpServletRequest request,long parentId){

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String op = request.getParameter("op");
		String ifQuery = request.getParameter("ifQuery");
		String unitType = request.getParameter("unitType");

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("parentId",parentId);
		map.put("ifQuery", ifQuery);
		map.put("unitType", unitType);

		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		long totalRows = 0;
		if(op.equals("init")){

			dataList = sysUnitsDao.getUnitListMap(map);
			totalRows = sysUnitsDao.getUnitListRows(map);
		}else if(op.equals("check")){
			//不传条件 平台公司可以查全部
			if(1 != unitId){
				//省级代理审核旗舰店
				if("4".equals(unitType)){
					map.put("parentUnitId",parentId);
				}else{
					map.put("parentUnitId",unitId);
				}

			}

			dataList = sysUnitsDao.getUnCheckListMap(map);
			totalRows = sysUnitsDao.getUnCheckListRows(map);
		}

		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}



	/**
	 * 得到注册单位树数据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeDataByUnitId(long unitId){
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();	
		//不传条件 平台公司可以查全部
		if(1 != unitId){
			paramsMap.put("unitId", unitId);
			dataList = sysUnitsDao.getUnitTreeDataByUnitId(paramsMap);
			paramsMap.remove("unitId");
			paramsMap.put("partentId", unitId);
			List<Map<String,Object>> list = sysUnitsDao.getUnitTreeDataByUnitId(paramsMap);
			dataList.addAll(list);
		}else{
			dataList = sysUnitsDao.getUnitTreeDataByUnitId(paramsMap);
		}



		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		return dataList;
	}

	/**
	 * 省级代理审核旗舰店
	 */
	public List<Map<String,Object>> getUnitTreeDataByProvinceId(long unitId){
		List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();		
		if(1 != unitId){
			paramsMap.put("unitId", unitId);
			datalist = sysUnitsDao.getUnitTreeDataByUnitId(paramsMap);
			paramsMap.remove("unitId");
			paramsMap.put("partentId", unitId);
			List<Map<String,Object>> list = sysUnitsDao.getUnitTreeDataByUnitId(paramsMap);
			datalist.addAll(list);
			if(null != list && !list.isEmpty()){
				for(Map<String,Object> map : list){
					paramsMap.put("partentId", map.get("id"));
					List<Map<String,Object>> shoplist = sysUnitsDao.getUnitTreeDataByProvinceId(paramsMap);
					datalist.addAll(shoplist);
				}
			}

		}

		return datalist;
	}

	public List<Map<String,Object>> getUnitTreeData(){

		List<Map<String,Object>> list = sysUnitsDao.getUnitTreeData();

		if (list==null) {
			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}

	/**
	 * 审核单位
	 * @param request
	 * @param sysUnits
	 * UnitStatus：register初次审核 rechange再次审核并修改权限 recheck再次审核 pass审核通过  unpass审核不通过
	 */
	@Transactional(rollbackFor=Exception.class)
	public void checkUnit(HttpServletRequest request,SysUnits sysUnits,SysUser loginSysUser){

		String unitRoleId = request.getParameter("unitRoleId");
		
		String unitStatus = sysUnits.getUnitStatus();
		SysUnits sysUnits2 = sysUnitsDao.get(sysUnits.getId());
		//UnitStatus：register初次审核 rechange再次审核并修改权限 recheck再次审核 pass审核通过  unpass审核不通过
		if(sysUnits2.getUnitStatus().equals("register")){
			sysUnits2.setUnitStatus(unitStatus);
			sysUnitsDao.update(sysUnits2);
			//审核通过
			if (unitStatus.equals("pass")) {

				//初始化用户
				SysUser sysUser = new SysUser();
				sysUser.setAccount("admin");
				sysUser.setUserName("admin");
				sysUser.setUnitId(sysUnits.getId());
				sysUser.setMobile(sysUnits2.getLegalPersonMobile());
				sysUser.setIfDelete(0l);
				sysUser.setIfLock(0l);
				sysUser.setIfSys(1l);
				sysUser.setUpdateDate(DateUtil.getSystemTime());				
				//sysUser.setPassword(MD5Util.getMD5(String.valueOf((int) ((Math.random() * 9 + 1) * 100000))));
				sysUser.setPassword(MD5Util.getMD5("123456"));
				sysUserService.add(sysUser);

				//初始化权限
				SysPrivilegeList unitPrivilege = new SysPrivilegeList();
				unitPrivilege.setUnitId(sysUnits.getId());
				unitPrivilege.setPrivilegeMaster(PrivilegeKey.UNIT);
				unitPrivilege.setPrivilegeMasterValue(sysUnits.getId());
				unitPrivilege.setPrivilegeAccess(PrivilegeKey.ROLE);
				unitPrivilege.setPrivilegeAccessValue(Long.valueOf(unitRoleId));
				sysPrivilegeListDao.add(unitPrivilege);

				SysPrivilegeList userPrivilege = new SysPrivilegeList();
				userPrivilege.setUnitId(sysUnits.getId());
				userPrivilege.setPrivilegeMaster(PrivilegeKey.USER);
				userPrivilege.setPrivilegeMasterValue(sysUser.getId());
				userPrivilege.setPrivilegeAccess(PrivilegeKey.ROLE);
				userPrivilege.setPrivilegeAccessValue(Long.valueOf(unitRoleId));
				sysPrivilegeListDao.add(userPrivilege);
			}
		}else{
			//审核不通过 或者是再次审核不必初始化仓库及管理员
			sysUnits2.setUnitStatus(unitStatus);
			sysUnitsDao.update(sysUnits2);
		}
	}


	/**
	 * 得到区域列表
	 * @param request
	 * @param prefix
	 */
	public List<Map<String,Object>> getChildZoneList(HttpServletRequest request,String code){


		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", "other");
		map.put("pcode", code);

		List<Map<String,Object>> list = sysUnitsDao.getChildZoneList(map);

		if (list==null) {

			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}


	/**
	 * 得到一级区域列表
	 * @param request
	 * @param code
	 */
	public List<Map<String,Object>> getFirstZoneList(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", "first");
		return sysUnitsDao.getChildZoneList(map);
	}

	/**
	 * 根据父code获取区域列表
	 * @param pcode
	 * @return
	 */
	public List<Map<String,Object>> getZoneListByPCode(String pcode){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pcode", pcode);
		return sysUnitsDao.getChildZoneList(map);
	}

	/**
	 * 根据pid，得到下拉框列表
	 * @param Pid
	 */
	public List<Map<String,Object>> getChildZoneListByPid(long pid){

		return sysUnitsDao.getChildZoneListByPid(pid);
	}



	/**
	 * 是否可以保存
	 * @param op
	 * @param sysUnits
	 * @return
	 */
	public boolean isHasSameZoneCode(String op,SysUnits sysUnits){

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("zoneCode", sysUnits.getZoneCode());
		if (op.equals("modify")) {
			map.put("id", sysUnits.getId());
		}
		return sysUnitsDao.getUnitRowsByZoneCode(map)>0;
	}



	/**
	 * 判断域名是否重复
	 * @param op
	 * @param sysUnits
	 * @return
	 */
	public boolean isHasSame(String op,SysUnits sysUnits,Map<String,Object> map){

		if (op.equals("modify")) {
			map.put("id", sysUnits.getId());
		}
		List<Map<String,Object>> list = sysUnitsDao.getUnitRowsBySame(map);
		if(null != list && list.size() > 0){
			return true;
		}
		return false;		
	}

	/**
	 * 保存单位详细
	 * @param request 访问对象
	 * @param op 操作类型（add:添加、modify:修改）
	 * @param sysUnits 单位对象
	 */
	public void saveUnitFile(HttpServletRequest request,String op,SysUnits sysUnits,String dictPath){

		String [] fileData_array = request.getParameterValues("fileData");

		List<Long> filterIds = new ArrayList<Long>();

		List<SysUnitsFile> fileList = new ArrayList<SysUnitsFile>();
		if (fileData_array!=null && fileData_array.length > 0) {

			for (String fileData : fileData_array) {

				Map<String,Object> map = JSONUtil.getMap(fileData);
				if (map.get("id")!=null) {
					filterIds.add(Long.valueOf(map.get("id")+""));
					continue;
				}

				try {

					//存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
					String saveName = map.get("saveName")+"";
					String fileName = map.get("fileName")+"";

					SysUnitsFile sysUnitsFile = new SysUnitsFile();

					sysUnitsFile.setDiskFileName(saveName);
					sysUnitsFile.setFileName(fileName);
					sysUnitsFile.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
					//日期作为相对路径
					SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
					String relative_path = formatdate.format(new Date());
					sysUnitsFile.setRelativePath(relative_path);
					sysUnitsFile.setRootPath(dictPath);
					sysUnitsFile.setFileSize(new File(savePath).length());
					sysUnitsFile.setUnitId(sysUnits.getId());
					sysUnitsFile.setIfDelete(0);
					sysUnitsFile.setFileType(Integer.parseInt(map.get("fileType")+""));
					FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
					fileList.add(sysUnitsFile);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		}

		Map<String,Object> params = new HashMap<String, Object>();
		if (!filterIds.isEmpty()) {
			params.put("filterIdList", filterIds);
		}
		params.put("unitId", sysUnits.getId());
		sysUnitsFileDao.updateIfDeleteByUnitId(params);

		if (!fileList.isEmpty()) {
			sysUnitsFileDao.addBatch(fileList);
		}
	}




	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {

			SysUnitsFile sysUnitsFile = sysUnitsFileDao.get(Long.valueOf(id));
			fileDownMode.setSaveName(sysUnitsFile.getFileName());
			fileDownMode.setFilePath(sysUnitsFile.getRootPath()+sysUnitsFile.getRelativePath()+sysUnitsFile.getDiskFileName());
		}
		return fileDownMode;
	}


	/**
	 * 得到单位文件类型列表
	 * @return
	 */
	public List<Map<String,Object>> getUnitFileTypeList(){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("type", "0");
		map1.put("name", "营业执照");
		map1.put("flag", "image");

		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("type", "1");
		map2.put("name", "身份证");
		map2.put("flag", "image");

		Map<String,Object> map3 = new HashMap<String, Object>();
		map3.put("type", "2");
		map3.put("name", "单位logo");
		map3.put("flag", "image");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		return list;
	}


	/**
	 * 获取为配送员分配的地域列表
	 */
	public List<Map<String, Object>> getZoneListForDelivery(Map<String,Object> argMap) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		List<Map<String,Object>> zoneList = new ArrayList<Map<String,Object>>();
		
		SysUnits sysUnits = (SysUnits) argMap.get("unit");
		int zoneLevel = (Integer) argMap.get("zoneLevel");
		
		
		if(zoneLevel == 3){	//如果登录用户可分配权限范围 在 省级
			paraMap.put("unitId", sysUnits.getId());
			zoneList = sysUnitsDao.getProvincialAgentZoneList(paraMap);
			return zoneList;
		}else if(zoneLevel == 4){	//如果登录用户可分配权限范围 在 市级
			paraMap.put("zoneCode", sysUnits.getZoneCode());
			zoneList = sysUnitsDao.getMunicipalAgentZoneList(paraMap);
			return zoneList;
		}else{	//如果登录用户可分配权限范围 在 县级
			paraMap.put("zoneCode", sysUnits.getZoneCode()+"%");
			zoneList = sysUnitsDao.getCountyAgentZoneList(paraMap);
			return zoneList;
		}
		
	}

	

	public SysUnits getUnitByZoneCode(String zoneCode) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("zoneCode", zoneCode);
		return sysUnitsDao.getUnitByZoneCode(paraMap);
	}


	/**
	 * 选择区域时，获取选中区域的从属区域列表
	 */
	public List<Map<String, Object>> getZoneListOnChange(
			HttpServletRequest request) {
		List<Map<String, Object>> zoneList = new ArrayList<Map<String,Object>>();
		String zoneLevel = request.getParameter("zoneLevel");
		String zoneCode = request.getParameter("zoneCode");

		Map<String,Object> paraMap = new HashMap<String, Object>();
		if("4".equals(zoneLevel)){
			paraMap.put("zoneCode", zoneCode);
			zoneList = sysUnitsDao.getMunicipalAgentZoneList(paraMap);
		}else if("5".equals(zoneLevel)){
			paraMap.put("zoneCode", zoneCode+"%");
			zoneList = sysUnitsDao.getCountyAgentZoneList(paraMap);
		}
		return zoneList;
	}


	public List<Map<String, Object>> getAreaList(Map<String,Object> map) {
		SysUnits sysUnits = (SysUnits) map.get("sysUnits");
		String queryFlag = (String) map.get("queryFlag");
		
		//结果List
		List<Map<String,Object>> zoneList = new ArrayList<Map<String,Object>>();
		//参数Map
		Map<String,Object> paraMap = new HashMap<String, Object>();
		
		if("parent".equals(queryFlag)){
			paraMap.put("id", sysUnits.getParentId());
			zoneList = sysUnitsDao.getAreaList(paraMap); 
		}else if("current".equals(queryFlag)){
			paraMap.put("id", sysUnits.getId());
			zoneList = sysUnitsDao.getAreaList(paraMap);
		}
		return zoneList;
	}
}