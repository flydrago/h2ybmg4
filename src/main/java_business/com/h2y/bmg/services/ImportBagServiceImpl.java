package com.h2y.bmg.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.dao.IImportBagDao;
import com.h2y.bmg.dao.IImportBagUserRtDao;
import com.h2y.bmg.dao.IImportGoodsTaskDao;
import com.h2y.bmg.dao.IMemberUserDao;
import com.h2y.bmg.entity.BaseResult;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.ImportBag;
import com.h2y.bmg.entity.ImportBagUserRt;
import com.h2y.bmg.entity.ImportGoodsTask;
import com.h2y.bmg.entity.MemberUser;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.util.ModuleCodeUitl;
import com.h2y.bmg.util.SysBaseUtil.ImportBagTask;
import com.h2y.dict.DictUtil;
import com.h2y.security.Base64Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.MatcherUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ImportBagServiceImpl  
 * 类描述：导入礼包业务接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午3:47:06  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午3:47:06  
 * 修改备注：  
 * @version
 */
@Service("importBagService")
public class ImportBagServiceImpl implements IImportBagService,IFileDownService{
	
	private final static Logger logger = Logger.getLogger(ImportBagServiceImpl.class);

	@Autowired
	protected IImportBagDao importBagDao;
	
	@Autowired
	protected IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	protected IImportGoodsTaskDao importGoodsTaskDao;
	
	@Autowired
	protected IImportBagUserRtDao importBagUserRtDao;
	
	@Autowired
	protected IMemberUserDao memberUserDao;
	
	public Map<String, Object> getGridData(HttpServletRequest request,long unitId,
			ImportBagTask ... importBagTask) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		List<String> taskList = new ArrayList<String>();
		for (ImportBagTask importBagTask2 : importBagTask) {
			taskList.add(importBagTask2.code);
		}
		map.put("taskList", taskList);
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		
		List<Map<String,Object>> dataList = importBagDao.getListMap(map);
		long rows = importBagDao.getListRows(map);
		gridData.put("Rows", dataList);
		gridData.put("Total", rows);
		return gridData;
	}

	public Map<String, Object> getUserGridData(HttpServletRequest request) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String bagCode = request.getParameter("bagCode");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("bagCode", bagCode);
		
		List<Map<String,Object>> dataList = importBagUserRtDao.getUserListMap(map);
		long rows = importBagUserRtDao.getUserListRows(map);
		gridData.put("Rows", dataList);
		gridData.put("Total", rows);
		return gridData;
	}
	
	
	public Map<String, Object> getTrackGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String bagCode = request.getParameter("bagCode");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("bagCode", bagCode);
		
		List<Map<String,Object>> dataList = importGoodsTaskDao.getListMap(map);
		long rows = importGoodsTaskDao.getListRows(map);
		gridData.put("Rows", dataList);
		gridData.put("Total", rows);
		return gridData;
	}

	
	public BaseResult save(String op,ImportBag importBag) {
		
		BaseResult baseResult = new BaseResult(0);
		
		if (op.equals("start")) {//发起审核
			
			
			//判断任务是否一致
			importBag = importBagDao.get(importBag.getId());
			if (!importBag.getCurrentTask().equals(ImportBagTask.start.code)) {
				baseResult.setResultMsg("当前任务已经处理，请刷新页面！");
				return baseResult;
			}
			
			
			//发起时判断是否有接收人
			if (importBagUserRtDao.getRowsByBagCode(importBag.getBagCode())==0) {
				baseResult.setResultMsg("当前礼包没有接受用户，请为当前礼包维护接受用户！");
				return baseResult;
			}
			
			importBag.setStatus(1);
			importBag.setCurrentTask(ImportBagTask.check1.code);
			importBag.setStartDate(DateUtil.getSystemTime());
			importBagDao.update(importBag);
			
			ImportGoodsTask importGoodsTask = getTask(importBag, ImportBagTask.start, importBag.getUserId(), "发起审核");
			importGoodsTaskDao.add(importGoodsTask);
			
			baseResult.setResultMsg("礼包审核发起成功！");
		}else {
			
			MemberUser memberUser = memberUserDao.getByAccount(importBag.getAccount());
			if (null==memberUser) {
				baseResult.setResultMsg("送礼人未注册会员，请进行会员注册！");
				return baseResult;
			}
			
			GoodsPrice goodsPrice = goodsPriceDao.get(importBag.getGoodsPriceId());
			if (op.equals("add")) {//创建
				
				//设置商品信息
				setImportBagGoodsInfo(importBag, goodsPrice);
				
				importBag.setBagCode(ModuleCodeUitl.getGiftBagCode());
				importBag.setCreateDate(DateUtil.getSystemTime());
				importBag.setStatus(0);
				importBag.setCurrentTask(ImportBagTask.start.code);
				importBagDao.add(importBag);
			}else {//修改
				
				ImportBag importBag2 = importBagDao.get(importBag.getId());
				if (importBag2.getCurrentTask()!=null && importBag2.getCurrentTask().equals(ImportBagTask.end.code)) {
					baseResult.setResultMsg("当前礼包已经审核通过，不可修改！");
					return baseResult;
				}
				
				//设置商品信息
				setImportBagGoodsInfo(importBag2, goodsPrice);
				importBag2.setStatus(0);
				importBag2.setCurrentTask(ImportBagTask.start.code);
				importBag2.setAccount(importBag.getAccount());
				importBag2.setBagName(importBag.getBagName());
				importBag2.setBusinessMobile(importBag.getBusinessMobile());
				importBag2.setBusinessUser(importBag.getBusinessUser());
				importBag2.setMemo(importBag.getMemo());
				importBag2.setSinglePrice(importBag.getSinglePrice());
				importBag2.setData1(importBag.getData1());
				importBag2.setData2(importBag.getData2());
				importBag2.setData3(importBag.getData3());
				importBag2.setData4(importBag.getData4());
				importBag2.setData5(importBag.getData5());
				importBagDao.update(importBag2);
			}
			baseResult.setResultMsg("保存成功！");
		}
		
		baseResult.setResultFlag(1);
		return baseResult;
	}
	
	/**
	 * 礼包用户保存操作
	 * @param op add：添加 modify：修改
	 * @param importBagUserRt 
	 */
	public BaseResult saveUser(String op,ImportBagUserRt importBagUserRt){
		
		BaseResult baseResult = new BaseResult(0);
		
		ImportBag importBag = importBagDao.getByBagCode(importBagUserRt.getBagCode());
		
		if (null==importBag) {
			
			baseResult.setResultMsg("当前礼包不存在！");
			return baseResult;
		}
		
		if (importBag.getCurrentTask().equals(ImportBagTask.end.code)) {
			
			baseResult.setResultMsg("当前礼包已经审核通过，接受用户不可修改！");
			return baseResult;
		}
		
		if (op.equals("add")) {
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("toAccount", importBagUserRt.getToAccount());
			map.put("bagCode", importBagUserRt.getBagCode());
			if (importBagUserRtDao.getRowsByAccountAndBagCode(map)>0) {
				
				baseResult.setResultMsg("当前账号已经存在，请进行修改！");
				return baseResult;
			}
			importBagUserRt.setStatus(0);
			importBagUserRt.setCreateDate(DateUtil.getSystemTime());
			importBagUserRt.setUpdateDate(DateUtil.getSystemTime());
			importBagUserRtDao.add(importBagUserRt);
		}else {
			
			
			ImportBagUserRt importBagUserRt2 = importBagUserRtDao.get(importBagUserRt.getId());
			importBagUserRt2.setGoodsCount(importBagUserRt.getGoodsCount());
			importBagUserRt2.setMemo(importBagUserRt.getMemo());
			importBagUserRt2.setUpdateDate(DateUtil.getSystemTime());
			importBagUserRtDao.update(importBagUserRt2);
		}
		
		baseResult.setResultFlag(1);
		baseResult.setResultMsg("保存成功！");
		return baseResult;
	}
	
	private ImportGoodsTask getTask(ImportBag importBag,ImportBagTask importBagTask,long userId,String memo){
		
		ImportGoodsTask importGoodsTask = new ImportGoodsTask();
		importGoodsTask.setCreateDate(DateUtil.getSystemTime());
		importGoodsTask.setDataId(importBag.getId());
		importGoodsTask.setDataType(0);
		importGoodsTask.setMemo(memo);
		importGoodsTask.setTaskType(importBagTask.code);
		importGoodsTask.setTaskValue(importBagTask.name);
		importGoodsTask.setUserId(userId);
		importGoodsTask.setBagCode(importBag.getBagCode());
		return importGoodsTask;
	}
	
	private void setImportBagGoodsInfo(ImportBag importBag,GoodsPrice goodsPrice){
		
		//商品信息
		importBag.setGoodsId(goodsPrice.getGoodsId());
		importBag.setGoodsNickName(goodsPrice.getGoodsNickName());
		importBag.setGoodsNumber(goodsPrice.getGoodsNumber());
		importBag.setGoodsPriceId(goodsPrice.getId());
		importBag.setGoodsPriceVersion(goodsPrice.getGoodsVersion());
		importBag.setUnitId(goodsPrice.getUnitId());
		importBag.setUnitType(goodsPrice.getUnitType());
		importBag.setZoneCode(goodsPrice.getZoneCode());
	}

	public BaseResult importUser(HttpServletRequest request, ImportBag importBag) {
		
		BaseResult baseResult = new BaseResult(1);
		
		String fileData = request.getParameter("fileData");
		String bagCode = request.getParameter("bagCode");
		Map<String,Object> map = JSONUtil.getMap(fileData);
		
		String savePath = null;
		
		List<ImportBagUserRt> addBagUserList = new ArrayList<ImportBagUserRt>();
		List<ImportBagUserRt> updateBagUserList = new ArrayList<ImportBagUserRt>();
		
		SysDictMain sysDictMain=DictUtil.getMainByCode("importbagfile_path");
		String path = sysDictMain.getDictValue();
		
		String msg = null;
		
		//存储路径
		try {
			
			List<String> toAccountList = new ArrayList<String>();
			
			savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
			
			InputStream is = new FileInputStream(savePath);
			Workbook wb = WorkbookFactory.create(is);
			
			int successRows = 0;//成功条数
			int errorRows = 0;//失败条数
			
			//错误样式
			Font font = wb.createFont();
			font.setColor(Font.COLOR_RED);
			CellStyle errorStyle = wb.createCellStyle();
			errorStyle.setFont(font);
			
			int sheetNum = wb.getNumberOfSheets();
			for (int j = 0; j < sheetNum; j++) {
				Sheet sheet = wb.getSheetAt(j);
				
				//表头
				int lashIndex = sheet.getLastRowNum();
				for(int i = 1;i<=lashIndex;i++) {
					Row row = sheet.getRow(i);
					String errorMsg = "";
					
					Cell cell1 = row.getCell(0);
					Cell cell2 = row.getCell(1);
					if (cell1==null || cell2==null) {
						continue;
					}
				
					//设置单元格类型
					cell1.setCellType(Cell.CELL_TYPE_STRING);
					cell2.setCellType(Cell.CELL_TYPE_STRING);
					
					String toAccount = cell1.getStringCellValue();//接受人员
					String goodsCount = cell2.getStringCellValue();//商品数量
					
					if (!MatcherUtil.isMobileNO(toAccount)) {
						errorMsg += "账号格式不合法";
					}
					
					if (!MatcherUtil.checkNumber(goodsCount, "+")) {
						errorMsg += "  商品数量不合法，必须为正整数！";
					}
					
					//账号是否重复
					if (!toAccountList.contains(toAccount)) {
						
						toAccountList.add(toAccount);
					}else {
						errorMsg += "  账号重复导入！";
					}
					
					//有错误数据
					if (!errorMsg.equals("")) {
						
						Cell errorCell = row.createCell(2, Cell.CELL_TYPE_STRING);
						errorCell.setCellValue(errorMsg);
						errorCell.setCellStyle(errorStyle);
						errorRows++;
						continue;
					}
					
					successRows++;
					
					ImportBagUserRt importBagUserRt = new ImportBagUserRt();
					importBagUserRt.setCreateDate(DateUtil.getSystemTime());
					importBagUserRt.setBagCode(bagCode);
					importBagUserRt.setToAccount(toAccount);
					importBagUserRt.setUpdateDate(DateUtil.getSystemTime());
					importBagUserRt.setGoodsCount(Integer.parseInt(goodsCount));
					importBagUserRt.setStatus(0);
					
					Map<String,Object> params = new HashMap<String, Object>();
					params.put("toAccount", toAccount);
					params.put("bagCode", bagCode);
					if (importBagUserRtDao.getRowsByAccountAndBagCode(params)>0) {
						updateBagUserList.add(importBagUserRt);
					}else {
						addBagUserList.add(importBagUserRt);
					}
					
					//一百条数据为单位插入数据库
					if (successRows%10==0 && successRows!=0) {
						saveImportUser(updateBagUserList, addBagUserList);
					}
				}
				
				saveImportUser(updateBagUserList, addBagUserList);
			}
			
			msg = "成功导入："+successRows+"条！\n";
			msg += "失败："+errorRows+"条！\n";
			if (errorRows>0) {
				
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");
				String datePath = formatdate.format(new Date());
				if (!new File(path+datePath).exists()) {
					new File(path+datePath).mkdirs();
				}
				//后缀路劲
				String prefixPath = datePath+DateUtil.getSystemTime().getTime()+".xls";
				FileOutputStream fileOut = new FileOutputStream(path+prefixPath);
				wb.write(fileOut);
				fileOut.close();
				msg+="<a href=\"common/file/down.htm?fileBean=importBagService&id="+prefixPath+"\">点击下载错误文件</a>";
			}
			baseResult.setResultMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			baseResult.setResultMsg("导入失败，出现异常！");
		}
		return baseResult;
	}

	
	/**
	 * 保存导入用户信息
	 * @param updateBagUserList
	 * @param addBagUserList
	 */
	private void saveImportUser(List<ImportBagUserRt> updateBagUserList,List<ImportBagUserRt> addBagUserList){
		
		
		for (ImportBagUserRt importBagUserRt2 : updateBagUserList) {
			importBagUserRtDao.updateGoodsCount(importBagUserRt2);
		}
		
		if (!addBagUserList.isEmpty()) {
			
			importBagUserRtDao.addBatch(addBagUserList);
		}
		
		updateBagUserList.clear();
		addBagUserList.clear();
	}
	
	
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		SysDictMain sysDictMain=DictUtil.getMainByCode("importbagfile_path");
		String path = sysDictMain.getDictValue();
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(path+id);
		fileDownMode.setSaveName(id.substring(id.lastIndexOf("/"), id.length()));
		return fileDownMode;
	}

	/**
	 * 执行审核操作
	 * @param request
	 * @param bagCode
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public BaseResult doCheck(HttpServletRequest request,long userId,String bagCode){
		
		BaseResult baseResult = new BaseResult(0);
		String resultCode = request.getParameter("resultCode");
		String resultValue = resultCode.equals("1")?"通过":"不通过";
		String taskCode = request.getParameter("taskCode");
		String memo = request.getParameter("memo");
		if (!resultCode.equals("1") && !resultCode.equals("0")) {
			baseResult.setResultMsg("审核标识不合法！");
			return baseResult;
		}
		
		ImportBagTask importBagTask = null;//当前任务
		ImportBagTask nextBagTask = null;//下个任务
		if (taskCode.equals(ImportBagTask.check1.code)) {
			
			importBagTask = ImportBagTask.check1;
			nextBagTask = ImportBagTask.check2;
		}else if (taskCode.equals(ImportBagTask.check2.code)) {
			
			importBagTask = ImportBagTask.check2;
			nextBagTask = ImportBagTask.end;
		}else {
			baseResult.setResultMsg("审核标识不合法！");
			return baseResult;
		}
		
		if (resultCode.equals("0")) {
			nextBagTask = ImportBagTask.start;
		}
		
		ImportBag importBag = importBagDao.getByBagCode(bagCode);
		if (!importBag.getCurrentTask().equals(taskCode)) {
			baseResult.setResultMsg("当前用户非当前任务处理人，当前处理操作为："+importBagTask.name+"！");
			return baseResult;
		}
		importBag.setCurrentTask(nextBagTask.code);
		if (taskCode.equals(ImportBagTask.check1.code)) {
			importBag.setChkStatus1(Integer.parseInt(resultCode));
		}else {
			importBag.setChkStatus2(Integer.parseInt(resultCode));
		}
		
		if (nextBagTask.code == ImportBagTask.end.code) {
			importBag.setStatus(2);
		}else if (nextBagTask.code == ImportBagTask.start.code) {
			importBag.setStatus(0);
		}else {
			importBag.setStatus(1);
		}
		
		importBagDao.update(importBag);
		
		//添加任务记录
		ImportGoodsTask importGoodsTask = getTask(importBag, importBagTask, userId, memo);
		importGoodsTask.setResultCode(resultCode);
		importGoodsTask.setResultValue(resultValue);
		importGoodsTaskDao.add(importGoodsTask);
		
		//审核通过
		if (nextBagTask.code == ImportBagTask.end.code) {
			baseResult.setResultFlag(2);
		}else {
			baseResult.setResultFlag(1);
		}
		baseResult.setResultMsg("审核处理成功！");
		return baseResult;
	}

}
