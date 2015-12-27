package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IAdvertColumnDao;
import com.h2y.bmg.dao.IAdvertColumnSubjectRtDao;
import com.h2y.bmg.dao.IAdvertSubjectDao;
import com.h2y.bmg.entity.AdvertColumnSubjectRt;
import com.h2y.bmg.entity.AdvertSubject;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.util.DateUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnSubjectRtServiceImpl  
 * 类描述：广告栏位主题关联业务操作接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月9日 上午9:59:30  
 * 修改人：侯飞龙
 * 修改时间：2015年4月9日 上午9:59:30  
 * 修改备注：  
 * @version
 */
@Service("advertColumnSubjectRtService")
public class AdvertColumnSubjectRtServiceImpl implements IAdvertColumnSubjectRtService,IFileDownService{

	@Autowired
	protected IAdvertColumnSubjectRtDao advertColumnSubjectRtDao;
	
	@Autowired
	protected IAdvertColumnDao advertColumnDao;

	@Autowired
	protected IAdvertSubjectDao advertSubjectDao;
	
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){


		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");

		if (sortname!=null && !sortname.equals("")) {

			if(sortname.equals("SUBJECT_NAME")){
				sortname = "ads.subject_name";
			}else {
				sortname = "ad."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("ifQuery", request.getParameter("ifQuery"));
		map.put("unitId", unitId);
		map.put("columnId", request.getParameter("columnId"));

		List<Map<String,Object>> dataList = advertColumnSubjectRtDao.getListMap(map);
		long totalRows = advertColumnSubjectRtDao.getListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}

	/**
	 * 得到时间冲突表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getDateCrossGridData(HttpServletRequest request,long unitId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String op = request.getParameter("op");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String repeatStart = request.getParameter("repeatStart");
		String repeatEnd = request.getParameter("repeatEnd");
		String columnId = request.getParameter("columnId");
		String id = request.getParameter("id");
		if (sortname!=null && !sortname.equals("")) {

			if(sortname.equals("SUBJECT_NAME")){
				sortname = "ads.subject_name";
			}else {
				sortname = "ad."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("unitId", unitId);
		map.put("columnId", columnId);
		map.put("op", op);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("repeatStart", repeatStart);
		map.put("repeatEnd", repeatEnd);
		map.put("id", id);

		List<Map<String,Object>> dataList = advertColumnSubjectRtDao.getDateCrossListMap(map);
		long totalRows = advertColumnSubjectRtDao.getDateCrossListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	/**
	 * 广告栏位主题关联保存
	 * @param request 访问对象
	 * @param op 操作类型
	 * @param advertColumnSubjectRt 广告栏位主题关联对象
	 */
	public void save(HttpServletRequest request,String op,AdvertColumnSubjectRt advertColumnSubjectRt){

		advertColumnSubjectRt.setStatus(0);
		if (op.equals("add")) {

			advertColumnSubjectRt.setCreateDate(DateUtil.getSystemTime());
			advertColumnSubjectRtDao.add(advertColumnSubjectRt);
		}else {

			AdvertColumnSubjectRt advertColumnSubjectRt2 = advertColumnSubjectRtDao.get(advertColumnSubjectRt.getId());
			advertColumnSubjectRt.setCreateDate(advertColumnSubjectRt2.getCreateDate());
			advertColumnSubjectRt.setUserId(advertColumnSubjectRt2.getUserId());
			advertColumnSubjectRt.setUnitId(advertColumnSubjectRt2.getUnitId());
			advertColumnSubjectRtDao.update(advertColumnSubjectRt);
		}
	}

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		String type = request.getParameter("type");
		String saveName = "";
		
		AdvertSubject advertSubject = advertSubjectDao.get(Long.valueOf(id));
		
		String androidFileName = advertSubject.getAndroidFileName();
		String iosFileName = advertSubject.getIosFileName();
		String wechatFileName = advertSubject.getWechatFileName();
		
		if (null==type || "".equals(type) || "android".equals(type)) {
			
			if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else if (null!=iosFileName && !"".equals(iosFileName)) {
				saveName = iosFileName+"";
			}else{
				saveName = wechatFileName+"";
			}
		}else if ("ios".equals(type)) {
			
			if (null!=iosFileName && !"".equals(iosFileName)) {
				saveName = iosFileName+"";
			}else if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else{
				saveName = wechatFileName+"";
			}
		}else if ("wechat".equals(type)) {
			
			if (null!=wechatFileName && !"".equals(wechatFileName)) {
				saveName = wechatFileName+"";
			}else if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else{
				saveName = iosFileName+"";
			}
		}
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(advertSubject.getRootPath()+""+advertSubject.getRelativePath()+saveName);
		fileDownMode.setSaveName(saveName);
		return fileDownMode;
	}

}