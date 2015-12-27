package com.h2y.bmg.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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

import com.h2y.bmg.dao.IFindActivityCommentDao;
import com.h2y.bmg.dao.IFindActivityDao;
import com.h2y.bmg.dao.IFindActivityGoodsDao;
import com.h2y.bmg.dao.IFindServiceDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.FindActivity;
import com.h2y.bmg.entity.FindActivityGoods;
import com.h2y.bmg.entity.FindService;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.dict.DictUtil;
import com.h2y.entity.PushMsg;
import com.h2y.entity.PushToData;
import com.h2y.security.Base64Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.FreeMarkerUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.PushUtil;
import com.h2y.util.PushUtil.PushLoginType;
import com.h2y.util.PushUtil.PushMsgType;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-12-10
 * email:info@hwttnet.com
 */
@Service("findActivityService")
public class FindActivityServiceImpl implements IFindActivityService,IFileDownService{



	@Autowired
	protected IFindActivityDao findActivityDao;

	@Autowired
	protected IFindActivityGoodsDao findActivityGoodsDao;

	@Autowired
	protected IFindActivityCommentDao findActivityCommentDao;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	@Autowired
	protected IFindServiceDao findServiceDao;


	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param findActivity
	 *
	 */
	public void add(FindActivity findActivity) {
		// TODO Auto-generated method stub

		findActivityDao.add(findActivity);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		findActivityDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	findActivityDao.deleteByIds(ids);
	//}

	public void update(FindActivity findActivity) {
		// TODO Auto-generated method stub
		findActivityDao.update(findActivity);
	}

	public FindActivity get(long id) {
		// TODO Auto-generated method stub
		return findActivityDao.get(id);
	}

	/**
	 * 得到列表
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return 活动列表数据
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");

		if (sortname!=null && !sortname.equals("")) {

			if(sortname.equals("ACTIVITY_LEVEL_NAME")){
				sortname = "fa.activity_level";
			}else {
				sortname = "fa."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("unitId", unitId);
		map.put("ifQuery",ifQuery);

		List<Map<String,Object>> dataList = findActivityDao.getListMap(map);
		long totalRows = findActivityDao.getListRows(map);
		if (null == dataList) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	/**
	 * 保存操作
	 * @param request 访问对象
	 * @param op 操作类型 add:添加 modify:修改
	 * @param findActivity 活动对象
	 * @param dictPath 字典维护的保存路径
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,FindActivity findActivity,String dictPath,SysUser sysUser){

		String [] goodsId_array = request.getParameterValues("goodsId");
		String [] file_array = request.getParameterValues("activity_file");



		if (op.equals("add")) {

			findActivity.setUnitId(sysUser.getUnitId());
			findActivity.setUserId(sysUser.getId());			
			findActivity.setActivityStatus(0);
			findActivity.setCreateDate(DateUtil.getSystemTime());
			//图片
			setFileData(file_array,findActivity,dictPath);
			findActivityDao.add(findActivity);
		}else {

			FindActivity findActivity2 = findActivityDao.get(findActivity.getId());
			findActivity.setCreateDate(findActivity2.getCreateDate());
			findActivity.setUpdateDate(DateUtil.getSystemTime());
			findActivity.setUserId(sysUser.getId());
			findActivity.setUnitId(sysUser.getUnitId());
			findActivity.setActivityStatus(0);

			findActivity.setFileName(findActivity2.getFileName());
			findActivity.setDiskFileName(findActivity2.getDiskFileName());
			findActivity.setRootPath(findActivity2.getRootPath());
			findActivity.setRelativePath(findActivity2.getRelativePath());			
			findActivity.setFileSize(findActivity2.getFileSize());
			findActivity.setFileSuffix(findActivity2.getFileSuffix());

			//图片
			setFileData(file_array, findActivity, dictPath);
			int flag = findActivityDao.update(findActivity);

			//删除原消息推送
			if(flag > 0){
				Map<String,Object> postMap = new HashMap<String,Object>();
				postMap.put("sourceType",PushUtil.PushLoginType.datasourceType.value);
				postMap.put("sourceId", findActivity.getId());
				DataRequestUtil.getRequestData("msg/deleteUnpushedMsg.htm", postMap);

			}


			//删除商品关联
			//findActivityGoodsDao.deleteByActivityId(findActivity.getId());
		}

		//添加活动商品关联,暂时不用关联商品
		if (null!=goodsId_array && goodsId_array.length>0) {

			List<FindActivityGoods> activityGoodsList = new ArrayList<FindActivityGoods>();
			for (String goodsId : goodsId_array) {
				FindActivityGoods findActivityGoods = new FindActivityGoods();
				findActivityGoods.setActivityId(findActivity.getId());
				findActivityGoods.setGoodsId(Long.valueOf(goodsId));
				activityGoodsList.add(findActivityGoods);
			}
			findActivityGoodsDao.addBatch(activityGoodsList);
		}

		//消息推送
		if(1 == findActivity.getIfSetPush() && 0 == findActivity.getActivityStatus()){
			pushMsg(findActivity);
		}

	}


	/**
	 * 根据活动Id，得到对应商品关联列表JSON
	 * @param activityId
	 * @return
	 */
	public String getGoodsListJsonActivityId(long activityId){

		String listJson = "[]";
		List<Map<String,Object>> goodsList = findActivityGoodsDao.getGoodsListActivityId(activityId);
		if (goodsList!=null) {
			listJson = JSONUtil.getJson(goodsList);
		}
		return listJson;
	}


	/**
	 * 得到活动图片下载对象
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		FindActivity findActivity = findActivityDao.get(Long.valueOf(id));

		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setSaveName(findActivity.getFileName());
		fileDownMode.setFilePath(findActivity.getRootPath()+findActivity.getRelativePath()+findActivity.getDiskFileName());
		return fileDownMode;
	}



	/**
	 * 得到评论列表
	 * @param request 访问对象
	 * @param activityId 活动Id
	 * @return 活动对应评论列表数据
	 */
	public Map<String,Object> getCommentGridData(HttpServletRequest request,long activityId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");

		if (sortname!=null && !sortname.equals("")) {

			if(sortname.equals("ACCOUNT") 
					|| sortname.equals("REAL_NAME")  
					|| sortname.equals("NICK_NAME") ){
				sortname = "mu."+sortname.toLowerCase();
			}else {
				sortname = "fac."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("activityId", activityId);
		map.put("ifQuery",ifQuery);

		List<Map<String,Object>> dataList = findActivityCommentDao.getListMap(map);
		long totalRows = findActivityCommentDao.getListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	/**
	 * 设置活动的图片信息
	 * @param file_array 上传文件信息
	 * @param findActivity 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,FindActivity findActivity,String dictPath){

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

				findActivity.setDiskFileName(saveName);
				findActivity.setFileName(map.get("fileName")+"");
				findActivity.setFileSize(Long.valueOf(map.get("fileSize")+""));
				findActivity.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				findActivity.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				findActivity.setRelativePath(relative_path);
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
	 * 消息推送
	 * @param findActivity
	 */
	public void pushMsg(FindActivity findActivity){


		String describtion = PushMsgType.xiaoDaNews.text;
		SysDictDetail sysDictDetail = DictUtil.getDetailByCode(1, "pushMsgDescModel", "xiaoDaNewsModel");
		if (null!=sysDictDetail && null!=sysDictDetail.getValue() && !"".equals(sysDictDetail.getValue())) {
			describtion = sysDictDetail.getValue();
		}

		Map<String,Object> descMap = new HashMap<String, Object>();
		descMap.put("findActivity", findActivity);
		describtion = FreeMarkerUtil.getContentFromStringTemplate(descMap, describtion);

		FindService findService = findServiceDao.getByServiceCode("xiaodakuaibao");

		String pushUrl = SysBaseUtil.XDKB_URL + "?id="+findActivity.getId();

		String body = PushUtil.getBody(PushMsgType.xiaoDaNews.value,0,pushUrl,findService.getUrlParams());



		String sendDate="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");  
		PushMsg pushMsg = new PushMsg();
		PushToData toData = new PushToData();

		SysUnits sysUnits = sysUnitsDao.get(findActivity.getUnitId());
		String tag = "";//推送区域
		if(!"1".equals(sysUnits.getZoneCode())){
			tag = sysUnits.getZoneCode();
			toData.setIsAll(0);
		}else{
			toData.setIsAll(1);
		}




		pushMsg.setTitle(PushMsgType.xiaoDaNews.title);
		pushMsg.setDescribtion(describtion);
		pushMsg.setBody(body);
		pushMsg.setCreateDate(DateUtil.getSystemTime());		
		pushMsg.setTag(tag);
		pushMsg.setMto(tag);
		pushMsg.setDatasourceId(String.valueOf(findActivity.getId()));
		pushMsg.setDatasourceType(PushUtil.PushLoginType.datasourceType.value);


		toData.setTag(tag);//推送区域
		toData.setCreateDate(DateUtil.getSystemTime());															
		toData.setDatasourceId(String.valueOf(findActivity.getId()));
		toData.setDatasourceType(PushUtil.PushLoginType.datasourceType.value);
		toData.setMto(tag);


		try {
			if(0 == findActivity.getPushType()){//本月每天推送 
				java.util.Calendar cal = java.util.Calendar.getInstance();
				//获取当月共多少天
				int maxDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				//获取当天日期
				int currentDay = cal.get(java.util.Calendar.DATE);

				//获取当前年月
				Date d = new Date();  
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");  
				String dateNowStr = sdf2.format(d);  

				//计算推送天数
				int pushDay = maxDay - currentDay;
				if(pushDay > 0){//剩余推送天数
					for(int i=1;i<=pushDay;i++){
						for(int j=1;j<=findActivity.getPushTimes();j++){						
							if(j==1){
								sendDate = findActivity.getPushDate1();
							}else if(j==2){
								sendDate = findActivity.getPushDate2();
							}else if(j==3){
								sendDate = findActivity.getPushDate3();
							}else if(j==4){
								sendDate = findActivity.getPushDate4();
							}else if(j==5){
								sendDate = findActivity.getPushDate5();
							}else if(j==6){
								sendDate = findActivity.getPushDate6();
							}

							String toSendDate;
							if((currentDay + pushDay) < 10){
								toSendDate = dateNowStr +"-"+"0"+ String.valueOf(currentDay + pushDay)+" "+sendDate;
							}else{
								toSendDate = dateNowStr +"-"+ String.valueOf(currentDay + pushDay)+" "+sendDate;
							}

							//设置推送时间
							pushMsg.setSendDate(sdf.parse(toSendDate));
							toData.setSendDate(sdf.parse(toSendDate));

							//安卓推送
							pushMsg.setType(Integer.valueOf(PushLoginType.androidApp.value));
							toData.setLoginType(PushLoginType.androidApp.value);
							PushUtil.doPush(pushMsg, toData);

							//ios推送
							pushMsg.setType(Integer.valueOf(PushLoginType.iosApp.value));
							toData.setLoginType(PushLoginType.iosApp.value);
							PushUtil.doPush(pushMsg, toData);

						}


					}
				}

			}else if(1 == findActivity.getPushType()){//仅推一天
				//获取当前年月
				Date d = new Date();  
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");  
				String dateNowStr = sdf2.format(d);  

				for(int j=1;j<=findActivity.getPushTimes();j++){						
					if(j==1){
						sendDate = findActivity.getPushDate1();
					}else if(j==2){
						sendDate = findActivity.getPushDate2();
					}else if(j==3){
						sendDate = findActivity.getPushDate3();
					}else if(j==4){
						sendDate = findActivity.getPushDate4();
					}else if(j==5){
						sendDate = findActivity.getPushDate5();
					}else if(j==6){
						sendDate = findActivity.getPushDate6();
					}


					String toSendDate = dateNowStr +" "+sendDate;


					//设置推送时间
					pushMsg.setSendDate(sdf.parse(toSendDate));
					toData.setSendDate(sdf.parse(toSendDate));

					//安卓推送
					pushMsg.setType(Integer.valueOf(PushLoginType.androidApp.value));
					toData.setLoginType(PushLoginType.androidApp.value);
					PushUtil.doPush(pushMsg, toData);

					//ios推送
					pushMsg.setType(Integer.valueOf(PushLoginType.iosApp.value));
					toData.setLoginType(PushLoginType.iosApp.value);
					PushUtil.doPush(pushMsg, toData);

				}



			}else{//仅推一次，设置推送时间
				pushMsg.setSendDate(findActivity.getPushDate());
				toData.setSendDate(findActivity.getPushDate());

				//安卓推送
				pushMsg.setType(Integer.valueOf(PushLoginType.androidApp.value));
				toData.setLoginType(PushLoginType.androidApp.value);
				PushUtil.doPush(pushMsg, toData);

				//ios推送
				pushMsg.setType(Integer.valueOf(PushLoginType.iosApp.value));
				toData.setLoginType(PushLoginType.iosApp.value);
				PushUtil.doPush(pushMsg, toData);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}