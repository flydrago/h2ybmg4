package com.h2y.bmg.services;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.basic.WbsKeys.XghKeys;
import com.h2y.bmg.dao.IFileDataDao;
import com.h2y.bmg.dao.IGoodsDao;
import com.h2y.bmg.dao.IGoodsInfoDao;
import com.h2y.bmg.dao.IGoodsMarkRtDao;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.dao.IGoodsPriceHisDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.DataGoodsInfo;
import com.h2y.bmg.entity.FileData;
import com.h2y.bmg.entity.Goods;
import com.h2y.bmg.entity.GoodsInfo;
import com.h2y.bmg.entity.GoodsMarkRt;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.GoodsPriceHis;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.dict.DictUtil;
import com.h2y.security.Base64Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.PinyinUtil;

/**
 * 类描述：商品的定价业务操作接口实现类 作者：侯飞龙 时间：2015年1月7日上午9:59:28 邮件：1162040314@qq.com
 */
@Service("goodsPriceService")
public class GoodsPriceServiceImpl implements IGoodsPriceService {

	private final static Logger logger = Logger
			.getLogger(GoodsPriceServiceImpl.class);

	@Autowired
	protected IGoodsInfoDao goodsInfoDao;

	@Autowired
	protected IFileDataDao fileDataDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;

	@Autowired
	protected IGoodsPriceHisDao goodsPriceHisDao;

	@Autowired
	protected IGoodsMarkRtDao goodsMarkRtDao;

	@Autowired
	protected IGoodsDao goodsDao;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");

		// 值为2 所有数据包括本公司添加和代理商品，否则代理商品
		String goodsSource = request.getParameter("goodsSource");

		if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.name";
			} else if (sortname.equals("GOODS_UNIT_NAME")) {
				sortname = "gp.goods_unit";
			} else if (sortname.equals("SPEC") || sortname.equals("NAME")
					|| sortname.equals("NUMBER")) {
				sortname = "gp." + sortname.toLowerCase();
			} else {
				sortname = "gp." + sortname.toLowerCase();
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		//商品来源 1自营 2旗舰店 3其他
		if (null != goodsSource && !"".equals(goodsSource) && "2".equals(goodsSource)) {
			map.put("goodsSource", Integer.parseInt(goodsSource));
			dataList = goodsPriceDao.getListPriceMap(map);
			gridData.put("Total", goodsPriceDao.getListPriceRows(map));
		} else {
			dataList = goodsPriceDao.getListMap(map);
			gridData.put("Total", goodsPriceDao.getListRows(map));
		}

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		return gridData;
	}

	// 采购商浏览商品列表
	public Map<String, Object> getBuyGridData(HttpServletRequest request) {
		Map<String, Object> gridMap = new HashMap<String, Object>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String ifQuery = request.getParameter("ifQuery");
		paraMap.put("page", Integer.parseInt(page));
		paraMap.put("pagesize", Integer.parseInt(pagesize));
		paraMap.put("ifQuery", ifQuery);
		List<Map<String, Object>> gridList = goodsPriceDao
				.getBuyGridData(paraMap);
		if (gridList == null) {
			gridList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", gridList);
		gridMap.put("Total", goodsPriceDao.getBuyGridDataSize(paraMap));
		return gridMap;
	}

	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");

		String typeCode = request.getParameter("typeCode");
		String number = request.getParameter("number");
		String name = request.getParameter("name");

		if (sortname != null && !sortname.equals("")) {
			if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.name";
			} else if (sortname.equals("GOODS_UNIT_NAME")) {
				sortname = "gp.goods_unit";
			} else if (sortname.equals("SPEC") || sortname.equals("NAME")
					|| sortname.equals("NUMBER")) {
				sortname = "gp." + sortname.toLowerCase();
			} else {
				sortname = "gp." + sortname.toLowerCase();
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if (null != typeCode && !"".equals(typeCode)) {
			map.put("typeCode", typeCode + "%");
		}

		if (null != number && !"".equals(number)) {
			map.put("number", "%" + number + "%");
		}

		if (null != name && !"".equals(name)) {
			map.put("name", "%" + name + "%");
		}

		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		List<Map<String, Object>> dataList = goodsPriceDao
				.getPriceSelectListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsPriceDao.getPriceSelectListRows(map));
		return gridData;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, String op,
			GoodsPrice goodsPrice, SysUser sysUser, SysUnits sysUnits) {
		
	}




	/**
	 * 保存 赠品、关联
	 * 
	 * @param goodsPrice
	 * @param goods
	 * @param request
	 */
	private void saveDataGoodInfo(GoodsPrice goodsPrice,
			HttpServletRequest request,String zoneCode) {
		// 赠品
		String[] giftGoodCount = request.getParameterValues("gift_goods_count");
		String[] giftGoodId = request.getParameterValues("gift_goods_id");

		// 包含赠品
		if (null != giftGoodId
				&& giftGoodId.length > 0) {
			for (int i = 0; i < giftGoodId.length; i++) {
				int count = Integer.parseInt(giftGoodCount[i]);
				long gid = Long.valueOf(giftGoodId[i]);
				if (gid==0) continue;

				DataGoodsInfo dataGoodsInfo = new DataGoodsInfo();
				// 定价表商品id
				dataGoodsInfo.setGoodsId(goodsPrice.getId());
				dataGoodsInfo.setDataGoodsId(gid);// 选中的商品id
				dataGoodsInfo.setDataGoodsCount(count);// 数量
				dataGoodsInfo.setDataType(1); // 赠品
				dataGoodsInfo.setCreateDate(DateUtil.getSystemTime());
				dataGoodsInfo.setDataStatus(0);
				goodsPriceDao.addDataGoodsInfo(dataGoodsInfo);
				goodsPrice.setIsGift(1);
			}
		} else {
			goodsPrice.setIsGift(0);
		}

		// 关联商品
		String [] relationGoodsIds = request.getParameterValues("relation_goods_id");
		// 包含关联商品
		if (null != relationGoodsIds
				&& !"".equals(relationGoodsIds)) {
			
			for (String tempGoodsId : relationGoodsIds) {
				long gid = Long.valueOf(tempGoodsId);
				if (gid==0) continue;
				
				DataGoodsInfo dataGoodsInfo = new DataGoodsInfo();
				// 定价表商品id
				dataGoodsInfo.setGoodsId(goodsPrice.getId());
				dataGoodsInfo.setDataGoodsId(gid);// 选中的商品id
				dataGoodsInfo.setDataGoodsCount(1);
				dataGoodsInfo.setDataType(2); // 关联商品
				dataGoodsInfo.setCreateDate(DateUtil.getSystemTime());
				dataGoodsInfo.setDataStatus(0);
				goodsPriceDao.addDataGoodsInfo(dataGoodsInfo);
				goodsPrice.setIsRelation(1);
			}
		} else {
			goodsPrice.setIsRelation(0);
		}
		
		Map<String,Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("id", goodsPrice.getId());
		goodsMap.put("isGift", goodsPrice.getIsGift());
		goodsPriceDao.updateIsGiftByGoodsId(goodsMap);		//更新赠品标识
		
	}

	/**
	 * 添加详细
	 * 
	 * @param request
	 * @param goodsPrice
	 */
	private GoodsInfo addGoodsPriceInfo(HttpServletRequest request,
			GoodsPrice goodsPrice, SysUser sysUser) {

		String introduce = request.getParameter("introduce");
		String specParam = request.getParameter("specParam");
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsId(goodsPrice.getId());
		goodsInfo.setVersion(goodsPrice.getVersion());
		goodsInfo.setIntroduce(introduce);
		goodsInfo.setSpecParam(specParam);
		goodsInfo.setDataType(1);// 数据类型，0：商品详细、1：定价商品详细
		goodsInfo.setUserId(sysUser.getId());
		goodsInfo.setCreateDate(DateUtil.getSystemTime());
		goodsInfoDao.add(goodsInfo);

		return goodsInfo;
	}



	/**
	 * 批量添加详细
	 * 
	 * @param request
	 * @param goodsPrice
	 */
	private GoodsInfo addGoodsPriceInfoBath(GoodsPrice goodsPrice, SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataType", 0);
		map.put("version", goodsPrice.getGoodsVersion());
		map.put("goodsId", goodsPrice.getGoodsId());
		GoodsInfo goodsInfoPrice = goodsInfoDao.getByVersionAndGoodsId(map);

		GoodsInfo goodsInfo = new GoodsInfo();
		if(null != goodsInfoPrice){

			goodsInfo.setGoodsId(goodsPrice.getId());
			goodsInfo.setVersion(goodsPrice.getVersion());
			goodsInfo.setIntroduce(goodsInfoPrice.getIntroduce());
			goodsInfo.setSpecParam(goodsInfoPrice.getSpecParam());
			goodsInfo.setDataType(1);// 数据类型，0：商品详细、1：定价商品详细
			goodsInfo.setUserId(sysUser.getId());
			goodsInfo.setCreateDate(DateUtil.getSystemTime());
			goodsInfoDao.add(goodsInfo);
		}

		return goodsInfo;

	}

	/**
	 * 添加商品图片
	 * 
	 * @param request
	 * @param goods
	 */
	private void addFileData(HttpServletRequest request, GoodsPrice goodsPrice) {

		String[] logoData_array = request.getParameterValues("logoData");
		String[] picData_array = request.getParameterValues("picData");

		String logoData_path = DictUtil.getMainByCode("goodsLogoData_path")
				.getDictValue();
		String picData_path = DictUtil.getMainByCode("goodsPicData_path")
				.getDictValue();

		List<FileData> fileDataList = new ArrayList<FileData>();
		// 商品Logo图片信息
		addDataToFileDataList(fileDataList, logoData_array, goodsPrice,
				logoData_path,0);

		// 商品图片信息
		addDataToFileDataList(fileDataList, picData_array, goodsPrice,
				picData_path,1);
		if (!fileDataList.isEmpty()) {
			fileDataDao.addBatch(fileDataList);
		}
	}


	/**
	 * 添加商品图片
	 * 
	 * @param request
	 * @param goods
	 */
	private void addFileDataBath(Goods goods,GoodsPrice goodsPrice) {

		// 取商品的图片信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataId", goods.getId());
		params.put("dataVersion", goods.getVersion());
		params.put("dataType", 0);

		List<FileData> fileDataList = fileDataDao.getFileListByGoodsVersion(params);

		List<FileData> fileList = new ArrayList<FileData>();

		if(null != fileDataList && !fileDataList.isEmpty()){
			for(FileData fileData:fileDataList){
				fileData.setDataId(goodsPrice.getId());				
				fileData.setDataVersion(goodsPrice.getVersion());
				fileData.setCreateDate(DateUtil.getSystemTime());
				fileData.setDataType(1);
				fileList.add(fileData);
			}
		}

		if (!fileList.isEmpty()) {
			fileDataDao.addBatch(fileList);
		}
	}

	/**
	 * 添加商品图片信息到列表中
	 * 
	 * @param fileDataList
	 *            图片对象列表
	 * @param fileData_array
	 *            图片数据数组
	 * @param goodsPrice
	 *            商品信息
	 * @param dictPath
	 *            字典存储路径
	 */
	private void addDataToFileDataList(List<FileData> fileDataList,
			String[] fileData_array, GoodsPrice goodsPrice, String dictPath,int fileType) {

		List<Long> ids = new ArrayList<Long>();
		
		if (fileData_array != null && fileData_array.length > 0) {
			
			int i = 0;
			for (String logoData : fileData_array) {

				Map<String, Object> map = JSONUtil.getMap(logoData);
				if (map.get("id") != null) {

					ids.add(Long.valueOf(map.get("id")+""));
//					FileData fileData = fileDataDao.get(Long.valueOf(map
//							.get("id") + ""));
//					fileData.setDataVersion(goodsPrice.getVersion());
//					fileData.setDataId(goodsPrice.getId());
//					fileData.setDataType(1);
//					fileDataList.add(fileData);
				} else {
					try {

						// 存储路径
						String savePath = Base64Util.decodeBytesInAndroid(map
								.get("savePath") + "");
						String saveName = map.get("saveName") + "";
						String fileName = map.get("fileName") + "";

						FileData fileData = new FileData();
						fileData.setDiskFileName(saveName);
						fileData.setFileName(fileName);
						fileData.setFileSuffix(saveName.substring(
								saveName.lastIndexOf(".") + 1,
								saveName.length()));
						// 日期作为相对路径
						SimpleDateFormat formatdate = new SimpleDateFormat(
								"yyyy/MM/dd/");
						String relative_path = formatdate.format(new Date());
						fileData.setRelativePath(relative_path);
						fileData.setRootPath(dictPath);
						fileData.setFileSize(new File(savePath).length());
						fileData.setDataId(goodsPrice.getId());
						fileData.setDataVersion(goodsPrice.getVersion());
						fileData.setIfDelete(0);
						fileData.setFileType(fileType);
						fileData.setOrd(i);
						fileData.setCreateDate(DateUtil.getSystemTime());
						fileData.setDataType(1);
						FileUtils.copyFile(new File(savePath), new File(
								dictPath + relative_path + saveName));
						fileDataList.add(fileData);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage(), e);
					}
				}
				i++;
			}
		}
		
		Map<String,Object> params = new HashMap<String, Object>();
		if (!ids.isEmpty()) params.put("ids", ids);
		params.put("fileType", fileType);
		params.put("goodsId", goodsPrice.getId());
		fileDataDao.deleteByGoodsId(params);//删除已经删除的图片
	}

	/**
	 * 整箱、单品关联
	 */
	public Map<String, Object> getGoodsPriceRt(long goodsId, String zoneCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("goodsId", goodsId);
		resultMap.put("dataType", "3");
		resultMap.put("zoneCode", zoneCode);
		List<Map<String, Object>> goodsPriceTrList = goodsPriceDao
				.getRelationGoods(resultMap);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : goodsPriceTrList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			long gid = Long.valueOf(map.get("data_goods_id") + "");

			GoodsPrice goodsPrice = goodsPriceDao.get(gid);
			returnMap.put("NAME", goodsPrice.getGoodsNickName());
			returnMap.put("ID", gid);
			returnList.add(returnMap);
		}
		resultMap.put("returnList", returnList);
		if (null == returnList || returnList.size() == 0) {
			resultMap.put("goodsDataJson4", "[]");
		} else {
			resultMap.put("goodsDataJson4", JSONUtil.getJson(returnList));
		}

		return resultMap;
	}

	// 赠品
	public Map<String, Object> getGoodsGift(long goodsId, String zoneCode) {
		String goodsIds = "";
		String goodsInfos = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("goodsId", goodsId);
		resultMap.put("dataType", "1");
		resultMap.put("zoneCode", zoneCode);
		List<Map<String, Object>> goodsGiftList = goodsPriceDao
				.getRelationGoods(resultMap);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		resultMap.clear();

		for (Map<String, Object> map : goodsGiftList) {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			long gid = Long.valueOf(map.get("data_goods_id") + "");
			String tempGoods = "";

			GoodsPrice goodsPrice = goodsPriceDao.get(gid);
			tempGoods = goodsPrice.getGoodsNickName() + ":"
					+ map.get("data_goods_count");
			jsonMap.put("NAME", goodsPrice.getGoodsNickName());

			String tempGoodsIds = map.get("data_goods_id") + ";"
					+ map.get("data_goods_count");
			goodsInfos += tempGoods + "\n";
			if ("".equals(goodsIds)) {
				goodsIds = tempGoodsIds;
			} else {
				goodsIds += "," + tempGoodsIds;
			}
			jsonMap.put("ID", map.get("data_goods_id"));
			jsonMap.put("COUNT", map.get("data_goods_count"));

			list.add(jsonMap);
		}

		resultMap.put("goodsIds", goodsIds);
		resultMap.put("goodsInfos", goodsInfos);
		resultMap.put("goodsGiftList", goodsGiftList);
		resultMap.put("giftList", list);
		if (null == list || list.size() == 0) {
			resultMap.put("goodsDataJson2", "[]");
		} else {
			resultMap.put("goodsDataJson2", JSONUtil.getJson(list));
		}

		return resultMap;
	}

	// 关联商品
	public Map<String, Object> getGoodsRelation(long goodsId, String zoneCode) {
		String relationGoodsIds = "";
		String relationGoodsInfos = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("goodsId", goodsId);
		resultMap.put("dataType", "2");
		resultMap.put("zoneCode", zoneCode);
		List<Map<String, Object>> goodsRelationList = goodsPriceDao
				.getRelationGoods(resultMap);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		resultMap.clear();
		for (Map<String, Object> map : goodsRelationList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			long gid = Long.valueOf(map.get("data_goods_id") + "");

			GoodsPrice goodsPrice = goodsPriceDao.get(gid);
			returnMap.put("NAME", goodsPrice.getGoodsNickName());
			relationGoodsInfos += goodsPrice.getGoodsNickName() + "\n";

			returnMap.put("ID", gid);
			returnList.add(returnMap);
			relationGoodsIds += gid + ";";
		}
		resultMap.put("returnList", returnList);
		resultMap.put("relationGoodsIds",
				relationGoodsIds.substring(0, relationGoodsIds.length() - 1));
		resultMap.put("relationGoodsInfos", relationGoodsInfos);

		if (null == returnList || returnList.size() == 0) {
			resultMap.put("goodsDataJson3", "[]");
		} else {
			resultMap.put("goodsDataJson3", JSONUtil.getJson(returnList));
		}

		return resultMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public void savePrice(HttpServletRequest request, String op,
			GoodsPrice goodsPrice, SysUser sysUser, SysUnits sysUnits) {

		String markData = request.getParameter("markData");


		if (op.equals("add")) {

			goodsPrice.setFirSpellName(PinyinUtil.getPinYinHeadChar(goodsPrice
					.getGoodsNickName()));
			goodsPrice.setSpellName(PinyinUtil.getPinYin(goodsPrice
					.getGoodsNickName()));
			goodsPrice.setVersion(1);
			goodsPrice.setStatus(1);// 默认为下架状态
			goodsPrice.setGoodsStatus(0);// 默认为上架状态
			goodsPrice.setUserId(sysUser.getId());
			goodsPrice.setCreateDate(DateUtil.getSystemTime());
			if (goodsPrice.getStatus() == 0) {
				goodsPrice.setShelvesDate(DateUtil.getSystemTime());
			}
			// 设置标签商品信息
			setGoodMark(goodsPrice, markData);
			// 设置商品类型编码
			setGoodType(goodsPrice);
			// 公司id
			goodsPrice.setSellUnit(sysUser.getUnitId());
			goodsPrice.setUnitId(sysUser.getUnitId());
			goodsPrice.setZoneCode(sysUnits.getZoneCode());
			goodsPrice.setUnitType(sysUnits.getUnitType());
			goodsPrice.setUpdateDate(DateUtil.getSystemTime());
			// 商品来源 1自营 2旗舰店 3其他
			goodsPrice.setGoodsSource(2);
			goodsPriceDao.add(goodsPrice);

			// 保存赠品、关联
			saveDataGoodInfo(goodsPrice, request,sysUnits.getZoneCode());
		} else {
			op = "update";
			GoodsPrice goodsPrice2 = goodsPriceDao.get(goodsPrice.getId());
			goodsPrice.setFirSpellName(PinyinUtil.getPinYinHeadChar(goodsPrice
					.getGoodsNickName()));
			goodsPrice.setSpellName(PinyinUtil.getPinYin(goodsPrice
					.getGoodsNickName()));
			goodsPrice.setVersion(goodsPrice2.getVersion() + 1);
			goodsPrice.setUserId(sysUser.getId());
			goodsPrice.setStatus(goodsPrice2.getStatus());
			goodsPrice.setGoodsStatus(goodsPrice2.getGoodsStatus());// 默认为上架状态
			goodsPrice.setCreateDate(goodsPrice2.getCreateDate());
			goodsPrice.setUpdateDate(DateUtil.getSystemTime());
			goodsPrice.setS3createdate(goodsPrice2.getS3createdate());
			goodsPrice.setS3gdscode(goodsPrice2.getS3gdscode());
			goodsPrice.setS3ucode(goodsPrice2.getS3ucode());
			goodsPrice.setGoodsScode(goodsPrice2.getGoodsScode());
			goodsPrice.setGoodsScode2(goodsPrice2.getGoodsScode2());

			goodsPrice.setAddedDate(goodsPrice2.getAddedDate());
			goodsPrice.setShelvesDate(goodsPrice2.getShelvesDate());
			goodsPrice.setIsActivity(goodsPrice2.getIsActivity());
			goodsPrice.setActivityType(goodsPrice2.getActivityType());
			goodsPrice.setActivityPrice(goodsPrice2.getActivityPrice());
			goodsPrice.setActivityGoodsId(goodsPrice2.getActivityGoodsId());			
			goodsPrice.setClickRate(goodsPrice2.getClickRate());
			goodsPrice.setSellRate(goodsPrice2.getSellRate());
			if (goodsPrice.getStatus() == 0) {
				goodsPrice.setShelvesDate(DateUtil.getSystemTime());//上架时间
			}
			// 设置标签商品信息
			setGoodMark(goodsPrice, markData);
			// 设置商品类型编码
			setGoodType(goodsPrice);
			// 公司id
			goodsPrice.setSellUnit(sysUser.getUnitId());
			goodsPrice.setUnitId(sysUser.getUnitId());
			goodsPrice.setZoneCode(sysUnits.getZoneCode());			
			goodsPrice.setUnitType(sysUnits.getUnitType());

			// 商品来源 1自营 2旗舰店 3其他
			goodsPrice.setGoodsSource(2);
			goodsPrice.setUpdateDate(DateUtil.getSystemTime());
			goodsPriceDao.update(goodsPrice);
			
			goodsPriceDao.delateGoodaGift(goodsPrice.getId());
			goodsPriceDao.deleteRelation(goodsPrice.getId());

			// 保存赠品、关联
			saveDataGoodInfo(goodsPrice, request,sysUnits.getZoneCode());
			
			
			goodsMarkRtDao.deleteByGoodsId(goodsPrice.getId());//删除商品标签关联
			goodsInfoDao.deleteByGoodsId(goodsPrice.getId());//删除以前的商品详细
		}

		// 添加商品标签关联
		addGoodsMarkRt(goodsPrice, markData, sysUser);

		// 添加商品详细信息（大字段）
		addGoodsInfo(request, goodsPrice, sysUser);

		// 添加商品logo和图片
		addFileData(request, goodsPrice);
	}

	/**
	 * 添加详细
	 * 
	 * @param request
	 * @param goods
	 */
	private GoodsInfo addGoodsInfo(HttpServletRequest request,
			GoodsPrice goodsPrice, SysUser sysUser) {
		
		String introduce = request.getParameter("introduce");
		String specParam = request.getParameter("specParam");
		
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsId(goodsPrice.getId());
		goodsInfo.setVersion(goodsPrice.getVersion());
		goodsInfo.setIntroduce(introduce);
		goodsInfo.setSpecParam(specParam);
		goodsInfo.setDataType(1);// 数据类型，0：商品详细、1：定价商品详细
		goodsInfo.setUserId(sysUser.getId());
		goodsInfo.setCreateDate(DateUtil.getSystemTime());
		goodsInfoDao.add(goodsInfo);
		return goodsInfo;
	}

	/**
	 * 设置标签，并添加详细
	 * 
	 * @param goods
	 * @param markData
	 */
	private void addGoodsMarkRt(GoodsPrice goodsPrice, String markData,
			SysUser sysUser) {

		if (null != markData && "" != markData) {
			
			List<GoodsMarkRt> markRts = new ArrayList<GoodsMarkRt>();
			List<Map<String, Object>> markInfoList = JSONUtil
					.jsonToListMap(markData);
			for (Map<String, Object> markInfo : markInfoList) {
				GoodsMarkRt goodsMarkRt = new GoodsMarkRt();
				goodsMarkRt.setGoodsId(goodsPrice.getId());
				goodsMarkRt.setTypeCode(goodsPrice.getGoodsTypeId()+"");
				goodsMarkRt.setMarkId(Long.valueOf(markInfo.get("MARK_ID") + ""));
				goodsMarkRt.setMarkInfoId(Long.valueOf(markInfo.get("ID") + ""));
				goodsMarkRt.setVersion(goodsPrice.getVersion());
				goodsMarkRt.setCreateDate(DateUtil.getSystemTime());
				goodsMarkRt.setUserId(sysUser.getId());
				markRts.add(goodsMarkRt);
			}
			if (!markRts.isEmpty()) {
				goodsMarkRtDao.addBatch(markRts);
			}
		}
	}

	/**
	 * 设置商品类别typecode 有父id和当前id组成，格式“100200”
	 * 
	 * @param goods
	 */
	private void setGoodType(GoodsPrice goodsPrice) {

		if (null != goodsPrice && !"".equals(goodsPrice.getGdsCode())
				&& null != goodsPrice.getGdsCode()) {
			String[] code = goodsPrice.getGdsCode().split("_");
			String code0 = code[0];
			String code1 = code[1];
			if (code0.length() < 3) {
				code0 = code0 + "00";
			}
			if (code1.length() < 3) {
				code1 = code1 + "00";
			}
			goodsPrice.setGoodsTypeId(Long.valueOf(code[1]));
			goodsPrice.setGdsCode2(code0 + code1);
		}
	}

	/**
	 * 设置标签，并添加详细
	 * 
	 * @param goods
	 * @param markData
	 */
	private void setGoodMark(GoodsPrice goodsPrice, String markData) {

		if (null == markData || "" == markData) {
			goodsPrice.setMarkIds("");
			goodsPrice.setMarkInfoIds("");
		} else {

			List<Map<String, Object>> markInfoList = JSONUtil
					.jsonToListMap(markData);

			Set<String> markIds = new HashSet<String>();
			Set<String> markInfoIds = new HashSet<String>();
			for (Map<String, Object> markInfo : markInfoList) {
				markIds.add(markInfo.get("MARK_ID") + "");
				markInfoIds.add(markInfo.get("ID") + "");
			}
			goodsPrice.setMarkIds(listToString(markIds));
			goodsPrice.setMarkInfoIds(listToString(markInfoIds));
		}
	}

	/**
	 * 把列表元素用[]包括并拼接成字符串
	 * 
	 * @param set
	 * @return
	 */
	private String listToString(Set<String> set) {

		String result = "";
		if (!set.isEmpty()) {
			for (String data : set) {
				result += "[" + data + "]";
			}
		}
		return result;
	}

	/**
	 * 批量添加
	 */
	public void saveBatch(SysUser sysUser,SysUnits sysUnits,String dataIds) {
		String dataId_array[] = dataIds.split(",");
		if (dataId_array.length > 0) {
			for (String id : dataId_array) {
				Goods goods = goodsDao.get(Long.valueOf(id));
				GoodsPrice goodsPrice = new GoodsPrice();
				int flag = 0;

				goodsPrice.setGoodsId(goods.getId());
				goodsPrice.setGoodsTypeId(goods.getGoodsTypeId());
				goodsPrice.setGdsCode(goods.getGdsCode());
				goodsPrice.setGdsCode2(goods.getGdsCode2());
				goodsPrice.setGoodsNumber(goods.getGoodsNumber());
				goodsPrice.setShortGoodsNumber(goods.getShortGoodsNumber());
				goodsPrice.setGoodsName(goods.getGoodsName());
				goodsPrice.setGoodsNickName(goods.getGoodsName());
				goodsPrice.setInventory(goods.getInventory());
				goodsPrice.setGoodsUnit(goods.getGoodsUnit());
				goodsPrice.setSpec(goods.getSpec());
				goodsPrice.setSpellName(PinyinUtil.getPinYin(goodsPrice.getGoodsNickName()));
				goodsPrice.setFirSpellName(PinyinUtil.getPinYinHeadChar(goodsPrice.getGoodsNickName()));								
				goodsPrice.setMaxPrice(goods.getMaxPrice());
				goodsPrice.setMinPrice(goods.getMinPrice());
				goodsPrice.setMarketPrice(goods.getMarketPrice());
				goodsPrice.setMemberPrice(goods.getMemberPrice());
				goodsPrice.setSellPrice(goods.getSellPrice());
				goodsPrice.setVersion(1);
				goodsPrice.setStatus(1);// 默认为下架状态
				goodsPrice.setCreateDate(DateUtil.getSystemTime());
				goodsPrice.setMarkIds(goods.getMarkIds());
				goodsPrice.setMarkInfoIds(goods.getMarkInfoIds());
				goodsPrice.setOrd(goods.getOrd());
				goodsPrice.setMemo(goods.getMemo());
				goodsPrice.setUserId(sysUser.getId());
				goodsPrice.setSellUnit(sysUser.getUnitId());
				goodsPrice.setIsLimitSell(goods.getIsLimitSell());
				goodsPrice.setIsGiftGrade(goods.getIsGiftGrade());
				goodsPrice.setIsMallVisible(goods.getIsMallVisible());
				goodsPrice.setIsAllowGrade(goods.getIsAllowGrade());
				goodsPrice.setLimitGiftNumber(goods.getLimitGiftNumber());
				goodsPrice.setLimitGradeNumber(goods.getLimitGradeNumber());
				goodsPrice.setLimitSellNumber(goods.getLimitSellNumber());
				goodsPrice.setGoodsVersion(goods.getVersion());
				// 商品来源
				goodsPrice.setGoodsSource(1);				
				goodsPrice.setUnitId(sysUser.getUnitId());
				goodsPrice.setZoneCode(sysUnits.getZoneCode());
				goodsPrice.setUnitType(sysUnits.getUnitType());
				goodsPrice.setGoodsEditStatus(0);				
				goodsPrice.setGoodsStatus(0);// 总部默认为上架状态


				flag = goodsPriceDao.add(goodsPrice);

				// 添加历史
				GoodsPriceHis goodsPriceHis = new GoodsPriceHis();
				try {
					PropertyUtils.copyProperties(goodsPriceHis, goodsPrice);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				goodsPriceHis.setGoodsPriceId(goodsPrice.getId());
				goodsPriceHis.setGoodsId(goodsPrice.getGoodsId());
				goodsPriceHis.setCreateDate(DateUtil.getSystemTime());
				goodsPriceHisDao.add(goodsPriceHis);

				// 添加详细信息（大字段）
				GoodsInfo goodsInfo = addGoodsPriceInfoBath(goodsPrice, sysUser);

				// 添加商品logo和图片
				addFileDataBath(goods, goodsPrice);


				//创建、修改商品
				if(flag > 0){
					//象过河商品对接
					saveXghGoods("add",goodsPrice,sysUnits,goodsInfo);
				}


			}
		}




	}




	/**
	 * 象过河对接 商品
	 * @param op
	 * @param goodsPrice
	 * @param sysUnits
	 * @param goodsInfo
	 */
	private void saveXghGoods(String op,
			GoodsPrice goodsPrice,  SysUnits sysUnits,GoodsInfo goodsInfo){
		Map<String,Object> postMap = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		postMap.put("op", op);
		//公司信息
		postMap.put(XghKeys.unitid.value(), sysUnits.getId());
		postMap.put(XghKeys.unitcode.value(), sysUnits.getUnitCode());
		postMap.put(XghKeys.xghucode.value(), sysUnits.getS3ucode());
		postMap.put(XghKeys.xghuname.value(), sysUnits.getS3uname());
		if("update".equals(op)){
			postMap.put(XghKeys.xghgdscode.value(), goodsPrice.getS3gdscode());
		}
		List<SysDictDetail> list =  DictUtil.getDetailListByMainCode(sysUnits.getId(), "goods_unit");
		if(null != list && !list.isEmpty()){
			for(SysDictDetail sysDictDetail:list){
				if(sysDictDetail.getCode().equals(goodsPrice.getGoodsUnit()+"")){
					//商品包装 桶、听 全部按瓶处理 0瓶 1箱
					if(goodsPrice.getGoodsUnit() != 0 && goodsPrice.getGoodsUnit() != 1){
						postMap.put(XghKeys.goodsunit.value(), "瓶");
						postMap.put(XghKeys.goodstype.value(), 0);
					}else if(goodsPrice.getGoodsUnit() == 1){
						params.put("goodsId", goodsPrice.getId());
						params.put("dataType", 3);
						params.put("zoneCode", sysUnits.getZoneCode());
						//获取与该箱关联的单品 ：象过河商品编码
						List<Map<String,Object>> listRt = goodsPriceDao.getGoodsPriceRtByGoodsId(params);
						if(null != listRt && !listRt.isEmpty()){
							Map<String,Object> rtMap = listRt.get(0);
							GoodsPrice goodsPriceRt = this.goodsPriceDao.get(Long.valueOf(rtMap.get("data_goods_id")+""));
							postMap.put(XghKeys.xghgdscode.value(), goodsPriceRt.getS3gdscode());
							postMap.put(XghKeys.goodsunit.value(), sysDictDetail.getValue());
							postMap.put(XghKeys.goodstype.value(), goodsPrice.getGoodsUnit());
						}

					}else{
						postMap.put(XghKeys.goodsunit.value(), sysDictDetail.getValue());
						postMap.put(XghKeys.goodstype.value(), goodsPrice.getGoodsUnit());
					}

				}
			}				
		}

		//商品信息
		postMap.put(XghKeys.goodsid.value(), goodsPrice.getId());
		postMap.put(XghKeys.goodscode.value(), goodsPrice.getGoodsNumber());
		postMap.put(XghKeys.goodsname.value(), goodsPrice.getGoodsNickName());
		postMap.put(XghKeys.goodsspec.value(), goodsPrice.getSpec());
		//商品价钱
		postMap.put(XghKeys.memberprice.value(), goodsPrice.getMemberPrice());			
		postMap.put(XghKeys.marketprice.value(), goodsPrice.getMarketPrice());
		postMap.put(XghKeys.sellprice.value(), goodsPrice.getSellPrice());			
		postMap.put(XghKeys.activityprice.value(), goodsPrice.getActivityPrice());
		//商品描述
		postMap.put(XghKeys.goodsintrodoce.value(), goodsInfo.getIntroduce());
		postMap.put(XghKeys.goodsintrodoce2.value(), goodsInfo.getSpecParam());


		//象过河 调用服务
		Map<String,Object> resultMap = DataRequestUtil.getRequestData("xgh/goods.htm", postMap);
		if (null != resultMap && "1".equals(resultMap.get("resultflg") + "")) {
			//象过河创建成功							
			Map<String,Object> xghMap = JSONUtil.getMap(resultMap.get("resultdata"));
			goodsPrice.setS3gdscode(xghMap.get(XghKeys.xghgdscode.value())+"");
			goodsPrice.setS3createdate(DateUtil.toDate(xghMap.get(XghKeys.xghcreatedate.value())+""));				
			goodsPrice.setS3ucode(sysUnits.getS3ucode());	
			goodsPrice.setGoodsScode(goodsPrice.getGoodsNumber());

			goodsPriceDao.update(goodsPrice);

			logger.info(op+"象过河对接商品："+goodsPrice.getGoodsNickName()+"成功");
		}else if (null != resultMap && "-1".equals(resultMap.get("resultflg") + "")){
			logger.info(op+"象过河对接商品："+goodsPrice.getGoodsNickName()+"失败"+"，象过河服务未启动！");
		}else{
			if(null != resultMap){
				logger.info(op+"象过河对接商品："+goodsPrice.getGoodsNickName()+"失败,"+resultMap.get("resultmsg"));
			}else{
				logger.info(op+"象过河对接商品："+goodsPrice.getGoodsNickName()+"失败");
			}

		}
	}



}