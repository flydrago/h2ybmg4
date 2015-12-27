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

import com.h2y.bmg.dao.IAdvertSubjectDao;
import com.h2y.bmg.dao.IAdvertSubjectGoodsRtDao;
import com.h2y.bmg.entity.AdvertSubject;
import com.h2y.bmg.entity.AdvertSubjectGoodsRt;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubjectServiceImpl  
 * 类描述：广告主题业务操作接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:38:56  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:38:56  
 * 修改备注：  
 * @version
 */
@Service("advertSubjectService")
public class AdvertSubjectServiceImpl implements IAdvertSubjectService,IFileDownService{

	@Autowired
	protected IAdvertSubjectDao advertSubjectDao;
	
	@Autowired
	protected IAdvertSubjectGoodsRtDao advertSubjectGoodsRtDao;
	
	private final static Logger logger = Logger.getLogger(AdvertColumnServiceImpl.class);
	
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,SysUnits sysUnits){
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = "ads."+sortname.toLowerCase();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", sysUnits.getId());
		map.put("unitType", sysUnits.getUnitType());
		map.put("zoneCode", sysUnits.getZoneCode()+"%");
		map.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = advertSubjectDao.getListMap(map);
		long totalRows = advertSubjectDao.getListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
	
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param subjectId 广告主题Id
	 * @return
	 */
	public Map<String,Object> getGoodsGridData(HttpServletRequest request,long subjectId){
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = "ads."+sortname.toLowerCase();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("subjectId", subjectId);
		map.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = advertSubjectGoodsRtDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}
	
	
	
	/**
	 * 广告主题保存
	 * @param request 访问对象
	 * @param op 操作类型
	 * @param advertSubject 广告主题对象
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,AdvertSubject advertSubject,SysDictMain sysDictMain){
		
		String iosFileData = request.getParameter("iosFileData");
		String androidFileData = request.getParameter("androidFileData");
		String wechatFileData = request.getParameter("wechatFileData");
		
		String dictPath = sysDictMain.getDictValue();
		
		if (op.equals("add")) {
			
			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",advertSubject, dictPath);
			addFileData(androidFileData, "android",advertSubject, dictPath);
			addFileData(wechatFileData, "wechat",advertSubject, dictPath);
			
			advertSubject.setCreateDate(DateUtil.getSystemTime());
			advertSubject.setUpdateDate(DateUtil.getSystemTime());
			advertSubjectDao.add(advertSubject);
		}else {
			
			AdvertSubject advertSubject2 = advertSubjectDao.get(advertSubject.getId());
			advertSubject2.setMemo(advertSubject.getMemo());
			advertSubject2.setStatus(advertSubject.getStatus());
			advertSubject2.setSubjectContent(advertSubject.getSubjectContent());
			advertSubject2.setSubjectName(advertSubject.getSubjectName());
			advertSubject2.setSubjectType(advertSubject.getSubjectType());
			advertSubject2.setUpdateDate(DateUtil.getSystemTime());
			
			
			//添加ios、android、wechat logo图片
			addFileData(iosFileData, "ios",advertSubject2, dictPath);
			addFileData(androidFileData, "android",advertSubject2, dictPath);
			addFileData(wechatFileData, "wechat",advertSubject2, dictPath);
			advertSubjectDao.update(advertSubject2);
		}
	}
	
	
	/**
	 * 保存主题对应的商品信息
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveGoods(HttpServletRequest request, String op) {
		
		String [] goodsPriceId_array = request.getParameter("goodsPriceIds").split(",");
		long subjectId = Long.valueOf(request.getParameter("subjectId"));
		
		if (goodsPriceId_array==null || goodsPriceId_array.length==0) {
			return;
		}
		
		if (op.equals("add")) {
			
			//商品定价Id
			List<Long> goodsPriceIds = new ArrayList<Long>();
			
			List<AdvertSubjectGoodsRt> goodsRtList = new ArrayList<AdvertSubjectGoodsRt>();
			int i = 0;
			for (String goodsPriceId : goodsPriceId_array) {
				AdvertSubjectGoodsRt advertSubjectGoodsRt = new AdvertSubjectGoodsRt();
				advertSubjectGoodsRt.setCreateDate(DateUtil.getSystemTime());
				advertSubjectGoodsRt.setGoodsPriceId(Long.valueOf(goodsPriceId));
				advertSubjectGoodsRt.setOrd(i);
				advertSubjectGoodsRt.setSubjectId(subjectId);
				goodsRtList.add(advertSubjectGoodsRt);
				goodsPriceIds.add(Long.valueOf(goodsPriceId));
				i++;
			}
			
			//删除关联
			if (!goodsPriceIds.isEmpty()) {
				
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("subjectId", subjectId);
				params.put("goodsPriceIds", goodsPriceIds);
				advertSubjectGoodsRtDao.deleteBySubjectIdAndPriceIds(params);
			}
			
			//添加关联
			if (!goodsRtList.isEmpty()) {
				advertSubjectGoodsRtDao.addBatch(goodsRtList);
			}
		}else {
			
			//商品定价Id
			List<Long> goodsPriceIds = new ArrayList<Long>();
			for (String goodsPriceId : goodsPriceId_array) {
				goodsPriceIds.add(Long.valueOf(goodsPriceId));
			}
			
			//删除关联
			if (!goodsPriceIds.isEmpty()) {
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("subjectId", subjectId);
				params.put("goodsPriceIds", goodsPriceIds);
				advertSubjectGoodsRtDao.deleteBySubjectIdAndPriceIds(params);
			}
		}
	}
	
	
	/**
	 * 添加图片信息到广告主题对象
	 * @param fileDataList 图片对象列表
	 * @param fileData_array 图片数据数组
	 * @param advertSubject 广告主题
	 * @param dictPath 字典存储路径
	 */
	private void addFileData(String fileData,String fileType,AdvertSubject advertSubject,String dictPath){

		if (null!=fileData && !"".equals(fileData)) {
			Map<String,Object> map = JSONUtil.getMap(fileData);
			if (map.get("id")==null) {
				try {
					//存储路径
					String savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
					String saveName = map.get("saveName")+"";
					
					if ("ios".equals(fileType)) {//ios
						advertSubject.setIosFileName(saveName);
					}else if ("android".equals(fileType)) {
						advertSubject.setAndroidFileName(saveName);
					}else {
						advertSubject.setWechatFileName(saveName);
					}

					String relative_path = advertSubject.getRelativePath();
					String root_path = advertSubject.getRootPath();

					if (null==relative_path || "".equals(relative_path)) {
						//日期作为相对路径
						SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");
						relative_path = formatdate.format(new Date());
						advertSubject.setRelativePath(relative_path);
					}

					if (null==root_path || "".equals(root_path)) {
						root_path = dictPath;
						advertSubject.setRootPath(root_path);
					}

					FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			}
		}else {
			if ("android".equals(fileType)) {
				advertSubject.setAndroidFileName(null);
			}else if ("ios".equals(fileType)) {
				advertSubject.setIosFileName(null);
			}else if ("wechat".equals(fileType)) {
				advertSubject.setWechatFileName(null);
			}
		}
	}


	/**
	 * 广告主题图片下载接口实现类
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		String type = request.getParameter("type");
		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {
			
			AdvertSubject advertSubject = advertSubjectDao.get(Long.valueOf(id));
			String fileName = "";
			if ("ios".equals(type)) {
				fileName = advertSubject.getIosFileName();
			}else if ("android".equals(type)) {
				fileName = advertSubject.getAndroidFileName();
			}else {
				fileName = advertSubject.getWechatFileName();
			}
			fileDownMode.setFilePath(advertSubject.getRootPath()+advertSubject.getRelativePath()+fileName);
		}
		return fileDownMode;
	}
	
}