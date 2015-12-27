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

import com.h2y.bmg.dao.IWechatActivityDao;
import com.h2y.bmg.dao.IWechatActivityHisDao;
import com.h2y.bmg.dao.IWechatActivityPrizeDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.WechatActivity;
import com.h2y.bmg.entity.WechatActivityPrize;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：微活动业务Service接口实现类
 * 作者：侯飞龙
 * 时间：2014年12月17日上午11:34:30
 * 邮件：1162040314@qq.com
 */
@Service("wechatActivityService")
public class WechatActivityServiceImpl implements IWechatActivityService,IFileDownService{


	@Autowired
	protected IWechatActivityDao wechatActivityDao;
	
	@Autowired
	protected IWechatActivityPrizeDao wechatActivityPrizeDao;
	
	@Autowired
	protected IWechatActivityHisDao wechatActivityHisDao;
	

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param wechatActivity
	 *
	 */
	public void add(WechatActivity wechatActivity) {
		// TODO Auto-generated method stub

		wechatActivityDao.add(wechatActivity);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		wechatActivityDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	wechatActivityDao.deleteByIds(ids);
	//}

	public void update(WechatActivity wechatActivity) {
		// TODO Auto-generated method stub
		wechatActivityDao.update(wechatActivity);
	}

	public WechatActivity get(long id) {
		// TODO Auto-generated method stub
		return wechatActivityDao.get(id);
	}


	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String activityType = request.getParameter("activityType");

		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("activityType", activityType);

		List<Map<String,Object>> dataList = wechatActivityDao.getListMap(map);
		long totalRows = wechatActivityDao.getListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
	
	
	/**
	 * 获取中奖用户Grid列表数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getHitUserGridData(HttpServletRequest request){
		
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String activityId = request.getParameter("activityId");

		if (sortname!=null && !sortname.equals("")) {
			
			String prefix = "ah";
			if (sortname.equals("ACCOUNT") || sortname.equals("REAL_NAME")) {
				prefix = "mu";
			}else if (sortname.equals("PRIZE_NAME") 
					|| sortname.equals("PRIZE_LEVEL")
					|| sortname.equals("PRIZE_NUMBER")) {
				prefix = "ap";
			}
			sortname = prefix+"."+sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("ifQuery", ifQuery);
		map.put("activityId", Long.valueOf(activityId));

		List<Map<String,Object>> dataList = wechatActivityHisDao.getHitUserListMap(map);
		long totalRows = wechatActivityHisDao.getHitUserListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
		
	}

	/**
	 * 保存操作
	 * @param request 访问对象
	 * @param op 操作类型 add:添加，modify:修改
	 * @param wechatActivity 微活动对象
	 * @param dictPath 存储路径
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,WechatActivity wechatActivity,String dictPath){

		String [] file_array = request.getParameterValues("h2y_file");

		if (op.equals("add")) {
			
			//设置图片数据
			setFileData(file_array, wechatActivity, dictPath);
			wechatActivity.setCreateDate(DateUtil.getSystemTime());
			wechatActivity.setActivityStatus(1);//默认暂停
			wechatActivityDao.add(wechatActivity);
		}else {
			WechatActivity wechatActivity2 = wechatActivityDao.get(wechatActivity.getId());
			
			wechatActivity2.setActivityType(wechatActivity.getActivityType());
			wechatActivity2.setDescription(wechatActivity.getDescription());
			wechatActivity2.setEndDate(wechatActivity.getEndDate());
			wechatActivity2.setIfSetDate(wechatActivity.getIfSetDate());
			wechatActivity2.setIfShareReward(wechatActivity.getIfShareReward());
			wechatActivity2.setKeyword(wechatActivity.getKeyword());
			wechatActivity2.setLimitFlag(wechatActivity.getLimitFlag());
			wechatActivity2.setLimitNumber(wechatActivity.getLimitNumber());
			wechatActivity2.setName(wechatActivity.getName());
			wechatActivity2.setPrizeInfo(wechatActivity.getPrizeInfo());
			wechatActivity2.setReverse1(wechatActivity.getReverse1());
			wechatActivity2.setReverse2(wechatActivity.getReverse2());
			wechatActivity2.setRewardNumber(wechatActivity.getRewardNumber());
			wechatActivity2.setStartDate(wechatActivity.getStartDate());
			wechatActivity2.setUseVal(wechatActivity.getUseVal());
			
			//设置图片数据
			setFileData(file_array, wechatActivity2, dictPath);
			
			wechatActivityDao.update(wechatActivity2);
		}

		//保存奖项
		saveAcitivityPrize(request, op, wechatActivity.getId(), wechatActivity.getUnitId());
	}
	
	
	
	
	/**
	 * 根据活动Id，得到奖项列表
	 * @param activityId 活动Id
	 * @return
	 */
	public String getPrizeListByActivityId(long activityId){
		
		List<Map<String,Object>> prizeList = wechatActivityPrizeDao.getPrizeListByActivityId(activityId);
		return JSONUtil.getJson(prizeList);
	}
	
	
	
	
	/**
	 * 设置活动的图片信息
	 * @param file_array 上传文件信息
	 * @param wechatActivity 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,WechatActivity wechatActivity,String dictPath){

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

				wechatActivity.setDiskFileName(saveName);
				wechatActivity.setFileName(map.get("fileName")+"");
				wechatActivity.setFileSize(Long.valueOf(map.get("fileSize")+""));
				wechatActivity.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				wechatActivity.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				wechatActivity.setRelativePath(relative_path);
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
	 * 保存活动奖项
	 * @param request 访问对象
	 * @param op 操作类型
	 * @param activityId 活动Id
	 * @param unitId 单位Id
	 */
	private void saveAcitivityPrize(HttpServletRequest request,String op,long activityId,long unitId){
		
		String [] prizeType_array = request.getParameterValues("prizeType");//奖品类型
		String [] goodsId_array = request.getParameterValues("goodsId");//奖品类型
		String [] prizeName_array = request.getParameterValues("prizeName");//奖品名称
		String [] prizeCount_array = request.getParameterValues("prizeCount");//奖品数量
		String [] prizeNumber_array = request.getParameterValues("prizeNumber");//奖品数量
		String [] prizeRate_array = request.getParameterValues("prizeRate");//奖品概率
		String [] prizeLevel_array = request.getParameterValues("prizeLevel");//奖项名称
		String [] prizeId_array = request.getParameterValues("prizeId");//奖品Id
		String [] ord_array = request.getParameterValues("ord");//奖品Id
		
		
		if (prizeLevel_array!=null && prizeLevel_array.length>0) {

			List<WechatActivityPrize> prizeList = new ArrayList<WechatActivityPrize>();
			
			List<Long> filterIdList = new ArrayList<Long>();

			for (int i = 1; i < prizeLevel_array.length; i++) {

				WechatActivityPrize wechatActivityPrize = new WechatActivityPrize();
				wechatActivityPrize.setActivityId(activityId);
				
				if (prizeType_array[i]!=null && prizeType_array[i].equals("goods")) {
					wechatActivityPrize.setGoodsPriceId(Long.valueOf(goodsId_array[i]));
				}
				wechatActivityPrize.setPrizeType(prizeType_array[i]);
				wechatActivityPrize.setPrizeCount(Long.valueOf(prizeCount_array[i]));
				wechatActivityPrize.setPrizeNumber(Long.valueOf(prizeNumber_array[i]));
				wechatActivityPrize.setPrizeLevel(prizeLevel_array[i]);
				wechatActivityPrize.setPrizeName(prizeName_array[i]);
				wechatActivityPrize.setPrizeRate(Double.valueOf(prizeRate_array[i])/100);
				wechatActivityPrize.setOrd(Integer.parseInt(ord_array[i]));
				wechatActivityPrize.setUnitId(unitId);
				if (!prizeId_array[i].equals("0")) {//修改
					
					wechatActivityPrize.setId(Long.valueOf(prizeId_array[i]));
					filterIdList.add(Long.valueOf(prizeId_array[i]));
					wechatActivityPrizeDao.update(wechatActivityPrize);
				}else {//添加
					
					prizeList.add(wechatActivityPrize);
				}
			}
			
			if (op.equals("modify")) {
				
				Map<String,Object> prizeMap = new HashMap<String, Object>();
				if (!filterIdList.isEmpty()) {
					prizeMap.put("filterIdList", filterIdList);
				}
				prizeMap.put("activityId", activityId);
				//删除前台已经删除的奖项
				wechatActivityPrizeDao.deleteByActivityId(prizeMap);
			}
			
			//添加奖项
			if (!prizeList.isEmpty()) {
				wechatActivityPrizeDao.addBatch(prizeList);
			}
		}
	}


	/**
	 * 活动图片下载
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		WechatActivity wechatActivity = wechatActivityDao.get(Long.valueOf(id));
		
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setSaveName(wechatActivity.getFileName());
		fileDownMode.setFilePath(wechatActivity.getRootPath()+wechatActivity.getRelativePath()+wechatActivity.getDiskFileName());
		return fileDownMode;
	}

}