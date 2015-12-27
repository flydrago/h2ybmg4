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

import com.h2y.bmg.dao.IFileDataDao;
import com.h2y.bmg.dao.IGoodsDao;
import com.h2y.bmg.dao.IGoodsHisDao;
import com.h2y.bmg.dao.IGoodsInfoDao;
import com.h2y.bmg.dao.IGoodsMarkInfoDao;
import com.h2y.bmg.dao.IGoodsMarkRtDao;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.entity.FileData;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.Goods;
import com.h2y.bmg.entity.GoodsHis;
import com.h2y.bmg.entity.GoodsInfo;
import com.h2y.bmg.entity.GoodsMarkRt;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.dict.DictUtil;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.PinyinUtil;

/**
 * 类描述：商品的业务操作接口实现类 作者：侯飞龙 时间：2015年1月7日上午9:59:28 邮件：1162040314@qq.com
 */
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService, IFileDownService {

	private final static Logger logger = Logger
			.getLogger(GoodsServiceImpl.class);

	@Autowired
	protected IGoodsDao goodsDao;

	@Autowired
	protected IGoodsHisDao goodsHisDao;

	@Autowired
	protected IGoodsMarkRtDao goodsMarkRtDao;

	@Autowired
	protected IGoodsMarkInfoDao goodsMarkInfoDao;

	@Autowired
	protected IGoodsInfoDao goodsInfoDao;

	@Autowired
	protected IFileDataDao fileDataDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;

	/**
	 * 获取商品列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.type_name";
			} else if (sortname.equals("GOODS_UNIT_NAME")) {
				sortname = "g.goods_unit";
			} else {
				sortname = "g." + sortname.toLowerCase();
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		List<Map<String, Object>> dataList = goodsDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsDao.getListRows(map));
		return gridData;
	}

	/**
	 * 得到选择窗口表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @return
	 */
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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		//获取已经代理商品id list
		List<Map<String,Object>> goodsIdlist = goodsDao.getIdListMap(map);
		if(null != goodsIdlist && !goodsIdlist.isEmpty()){
			map.put("goodsIdlist", goodsIdlist);
		}

		//排序字段
		if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.name";
			} else if (sortname.equals("GOODS_UNIT_NAME")) {
				sortname = "g.goods_unit";
			} else {
				sortname = "g." + sortname.toLowerCase();
			}
		}


		//商品分类
		if (null != typeCode && !"".equals(typeCode)) {
			map.put("typeCode", typeCode + "%");
		}

		//商品编码
		if (null != number && !"".equals(number)) {
			map.put("number", "%" + number + "%");
		}

		//商品名称
		if (null != name && !"".equals(name)) {
			map.put("name", "%" + name + "%");
		}


		//获取商品列表
		List<Map<String, Object>> dataList = goodsDao.getSelectListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsDao.getSelectListRows(map));
		return gridData;
	}

	/**
	 * 获取商品代理列表
	 * 
	 * @param request
	 *            访问对象
	 * @return
	 */
	public Map<String, Object> getUnitGridData(HttpServletRequest request) {

		Map<String, Object> gridData = new HashMap<String, Object>();
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		//排序字段
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		//查询条件
		String ifQuery = request.getParameter("ifQuery");
		String goodsId = request.getParameter("goodsId");
		if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("UNIT_NAME") || sortname.equals("LEGAL_PERSON")) {
				sortname = "u" + sortname.toLowerCase();
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
		map.put("goodsId", Long.valueOf(goodsId));

		List<Map<String, Object>> dataList = goodsDao.getUnitListMap(map);
		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsDao.getUnitListRows(map));
		return gridData;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, String op, Goods goods,
			SysUser sysUser, SysUnits sysUnits) {

		String markData = request.getParameter("markData");

		// 商品编号
		String unitCode = sysUnits.getUnitCode();
		//公司编码为空
		if (null == unitCode || "".equals(unitCode)) {
			unitCode = "XX" + sysUnits.getId();
		}
		int unitType = sysUnits.getUnitType();
		//商品编码前缀 公司类型+公司编码（公司编码暂用区域码）
		String goosNumberPrefix = unitType + unitCode;
		//商品分类前缀
		String goosTypePrefix = request.getParameter("goosTypePrefix");
		//商品编码 = 商品编码前缀+商品分类前缀+输入商品短编码（6位）
		goods.setGoodsNumber(goosNumberPrefix + goosTypePrefix
				+ goods.getShortGoodsNumber());

		if (op.equals("add")) {

			goods.setCreateDate(DateUtil.getSystemTime());
			if (goods.getStatus() == 0) {
				goods.setShelvesDate(DateUtil.getSystemTime());//上架时间
			}
			goods.setFirSpellName(PinyinUtil.getPinYinHeadChar(goods
					.getGoodsName()));
			goods.setSpellName(PinyinUtil.getPinYin(goods.getGoodsName()));
			goods.setVersion(1);
			goods.setStatus(1);// 默认为下架状态
			goods.setUserId(sysUser.getId());
			// 设置标签商品信息
			setGoodMark(goods, markData);
			// 设置商品类型编码
			setGoodType(goods);
			// 公司id
			goods.setSellUnit(sysUser.getUnitId());
			// 商品来源 1自营 2旗舰店 3其他
			goods.setGoodsSource(1);
			goodsDao.add(goods);

		} else {

			Goods goods2 = goodsDao.get(goods.getId());
			goods.setCreateDate(goods2.getCreateDate());
			goods.setUpdateDate(DateUtil.getSystemTime());
			if (goods.getStatus() == 0) {
				goods.setShelvesDate(DateUtil.getSystemTime());
			}
			goods.setFirSpellName(PinyinUtil.getPinYinHeadChar(goods
					.getGoodsName()));
			goods.setSpellName(PinyinUtil.getPinYin(goods.getGoodsName()));
			goods.setVersion(goods2.getVersion() + 1);//版本号加1
			goods.setUserId(sysUser.getId());
			goods.setStatus(goods2.getStatus());

			// 设置标签商品信息
			setGoodMark(goods, markData);
			// 设置商品类型编码
			setGoodType(goods);
			// 公司id
			goods.setSellUnit(sysUser.getUnitId());
			// 商品来源 1自营 2旗舰店 3其他
			goods.setGoodsSource(1);
			goods.setUpdateDate(DateUtil.getSystemTime());
			int flag = goodsDao.update(goods);
			// 修改成功
			if (flag > 0) {
				//修改代理商品代理状态为已过期（代理中、已过期）
				goodsPriceDao.updateEditStatus(goods.getId());
			}

		}

		// 添加商品历史
		GoodsHis goodsHis = new GoodsHis();
		try {
			PropertyUtils.copyProperties(goodsHis, goods);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		goodsHis.setGoodsId(goods.getId());
		goodsHis.setUserId(sysUser.getId());
		goodsHis.setUpdateDate(DateUtil.getSystemTime());
		goodsHisDao.add(goodsHis);

		// 添加商品标签详细
		// addGoodsMarkDetail(goods, markData);
		addGoodsMarkRt(goods, markData, sysUser);

		// 添加商品详细信息（大字段）
		addGoodsInfo(request, goods, sysUser);

		// 添加商品logo和图片
		addFileData(request, goods);
	}

	/**
	 * 得到商品标签数据
	 * 
	 * @param goods
	 * @return
	 */
	public List<Map<String, Object>> getGoodsMarkData(Goods goods) {

		List<Map<String, Object>> markList = new ArrayList<Map<String, Object>>();
		String markInfoIds = goods.getMarkInfoIds();
		if (null != markInfoIds && "" != markInfoIds) {
			Map<String, Object> params = new HashMap<String, Object>();

			markInfoIds = tranMarkIds(markInfoIds);
			if (!"".equals(markInfoIds) && !"".equals(markInfoIds.trim())) {
				params.put("markInfoIds", markInfoIds);
				markList = goodsMarkInfoDao.getListMapByIds(params);
			}
		}
		return markList;
	}

	/**
	 * 添加详细
	 * 
	 * @param request
	 * @param goods
	 */
	private void addGoodsInfo(HttpServletRequest request, Goods goods,
			SysUser sysUser) {

		String introduce = request.getParameter("introduce");//商品介绍
		String specParam = request.getParameter("specParam");//商品规格
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsId(goods.getId());
		goodsInfo.setVersion(goods.getVersion());
		goodsInfo.setIntroduce(introduce);
		goodsInfo.setSpecParam(specParam);
		goodsInfo.setDataType(0);// 数据类型，0：商品详细、1：定价商品详细
		goodsInfo.setUserId(sysUser.getId());
		goodsInfo.setCreateDate(DateUtil.getSystemTime());
		goodsInfoDao.add(goodsInfo);
	}

	/**
	 * 设置标签，并添加详细
	 * 
	 * @param goods
	 * @param markData
	 */
	private void addGoodsMarkRt(Goods goods, String markData, SysUser sysUser) {

		if (null != markData && "" != markData) {

			List<GoodsMarkRt> markRts = new ArrayList<GoodsMarkRt>();
			List<Map<String, Object>> markInfoList = JSONUtil
					.jsonToListMap(markData);

			for (Map<String, Object> markInfo : markInfoList) {
				GoodsMarkRt goodsMarkRt = new GoodsMarkRt();
				goodsMarkRt.setGoodsId(goods.getId());
				goodsMarkRt.setTypeCode(goods.getGdsCode());
				goodsMarkRt
				.setMarkId(Long.valueOf(markInfo.get("MARK_ID") + ""));
				goodsMarkRt
				.setMarkInfoId(Long.valueOf(markInfo.get("ID") + ""));
				goodsMarkRt.setVersion(goods.getVersion());
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
	 * 添加商品图片
	 * 
	 * @param request
	 * @param goods
	 */
	private void addFileData(HttpServletRequest request, Goods goods) {

		String[] logoData_array = request.getParameterValues("logoData");
		String[] picData_array = request.getParameterValues("picData");

		String logoData_path = DictUtil.getMainByCode("goodsLogoData_path")
				.getDictValue();
		String picData_path = DictUtil.getMainByCode("goodsPicData_path")
				.getDictValue();

		List<FileData> fileDataList = new ArrayList<FileData>();
		// 商品Logo图片信息
		addDataToFileDataList(fileDataList, logoData_array, goods,
				logoData_path);

		// 商品图片信息
		addDataToFileDataList(fileDataList, picData_array, goods, picData_path);
		if (!fileDataList.isEmpty()) {
			fileDataDao.addBatch(fileDataList);
		}
	}

	/**
	 * 添加商品图片信息到列表中
	 * 
	 * @param fileDataList
	 *            图片对象列表
	 * @param fileData_array
	 *            图片数据数组
	 * @param goods
	 *            商品信息
	 * @param dictPath
	 *            字典存储路径
	 */
	private void addDataToFileDataList(List<FileData> fileDataList,
			String[] fileData_array, Goods goods, String dictPath) {

		if (fileData_array != null && fileData_array.length > 0) {

			int i = 0;
			for (String logoData : fileData_array) {

				Map<String, Object> map = JSONUtil.getMap(logoData);
				if (map.get("id") != null) {

					FileData fileData = fileDataDao.get(Long.valueOf(map
							.get("id") + ""));
					fileData.setDataVersion(goods.getVersion());
					fileDataList.add(fileData);
				} else {
					try {

						// 存储路径
						String savePath = Base64Util.decodeBytesInAndroid(map
								.get("savePath") + "");
						String saveName = map.get("saveName") + "";
						String fileName = map.get("fileName") + "";
						String fileType = map.get("fileType") + "";

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
						fileData.setDataId(goods.getId());
						fileData.setDataVersion(goods.getVersion());
						fileData.setIfDelete(0);
						fileData.setFileType(Integer.parseInt(fileType));
						fileData.setOrd(i);
						fileData.setCreateDate(DateUtil.getSystemTime());
						fileData.setDataType(0);
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
	}

	/**
	 * 设置标签，并添加详细
	 * 
	 * @param goods
	 * @param markData
	 */
	private void setGoodMark(Goods goods, String markData) {

		if (null == markData || "" == markData) {
			goods.setMarkIds("");
			goods.setMarkInfoIds("");
		} else {

			List<Map<String, Object>> markInfoList = JSONUtil
					.jsonToListMap(markData);

			Set<String> markIds = new HashSet<String>();
			Set<String> markInfoIds = new HashSet<String>();
			for (Map<String, Object> markInfo : markInfoList) {
				markIds.add(markInfo.get("MARK_ID") + "");
				markInfoIds.add(markInfo.get("ID") + "");
			}
			goods.setMarkIds(listToString(markIds));
			goods.setMarkInfoIds(listToString(markInfoIds));
		}
	}

	/**
	 * 设置商品类别typecode 有父id和当前id组成，格式“100200”
	 * 
	 * @param goods
	 */
	private void setGoodType(Goods goods) {

		if (null != goods && !"".equals(goods.getGdsCode())
				&& null != goods.getGdsCode()) {
			String[] code = goods.getGdsCode().split("_");
			goods.setGoodsTypeId(Long.valueOf(code[1]));
			goods.setGdsCode2(code[0] + code[1]);
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
	 * 装换标签Id，去掉[]转换成eg:1,2格式
	 * 
	 * @param ids
	 * @return
	 */
	private String tranMarkIds(String ids) {

		if (null != ids && "" != ids) {
			ids = ids.replaceAll("\\]\\[", ",");
			ids = ids.replaceAll("\\[", "");
			ids = ids.replaceAll("\\]", "");
			return ids;
		}
		return "";
	}

	/**
	 * 商品图片信息接口实现类
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		FileDownMode fileDownMode = new FileDownMode();
		if (id != null && !id.equals("")) {

			FileData fileData = fileDataDao.get(Long.valueOf(id));
			fileDownMode.setSaveName(fileData.getFileName());
			fileDownMode.setFilePath(fileData.getRootPath()
					+ fileData.getRelativePath() + fileData.getDiskFileName());
		}
		return fileDownMode;
	}

	/**
	 * 得到商品标签数据
	 * 
	 * @param goods
	 * @return
	 */
	public List<Map<String, Object>> getGoodsMarkData(GoodsPrice goodsPrice) {

		List<Map<String, Object>> markList = new ArrayList<Map<String, Object>>();
		String markInfoIds = goodsPrice.getMarkInfoIds();
		if (null != markInfoIds && "" != markInfoIds) {
			Map<String, Object> params = new HashMap<String, Object>();

			markInfoIds = tranMarkIds(markInfoIds);
			if (!"".equals(markInfoIds) && !"".equals(markInfoIds.trim())) {
				params.put("markInfoIds", markInfoIds);
				markList = goodsMarkInfoDao.getListMapByIds(params);
			}
		}
		return markList;
	}

}