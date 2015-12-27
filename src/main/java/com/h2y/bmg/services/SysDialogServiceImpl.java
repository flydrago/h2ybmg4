package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IGoodsMarkDao;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.dao.ISysDepartmentDao;
import com.h2y.bmg.dao.ISysUserDao;
import com.h2y.bmg.entity.GoodsType;
import com.h2y.bmg.util.SysBaseUtil.MixSelectListKey;

/**
 * 选择窗口Service
 */
@Service("sysDialogService")
public class SysDialogServiceImpl implements ISysDialogService {

	@Autowired
	protected ISysUserDao sysUserDao;

	@Autowired
	protected ISysDepartmentDao sysDepartmentDao;

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	@Autowired
	protected IGoodsMarkDao goodsMarkDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;

	public List<Map<String, Object>> getList(HttpServletRequest request,
			long unitId, String op) {

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		String deptCode = request.getParameter("deptCode");
		String deptId = request.getParameter("deptId");
		String isCascade = request.getParameter("isCascade");
		String searchName = request.getParameter("searchName");

		if (searchName != null) {
			searchName = "%" + searchName + "%";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (deptCode.contains("_")) {
			map.put("deptCode", deptCode + "%");
		} else {
			map.put("deptCode", deptCode + "_%");
		}
		map.put("unitId", unitId);
		map.put("isCascade", isCascade);
		map.put("deptId", deptId);
		map.put("searchName", searchName);

		if (op.equals("user")) {

			dataList = sysUserDao.getSelectDialogUserList(map);
		} else if (op.equals("dept")) {

			dataList = sysDepartmentDao.getSelectDialogDepartmentList(map);
		}

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		return dataList;
	}

	/**
	 * 得到混合窗口选中的列表
	 * 
	 * @param dataArray
	 *            后台获得的列表数组
	 * @return
	 */
	public Map<String, List<String>> getMixSelectedList(String[] dataArray) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		if (dataArray == null || dataArray.length == 0)
			return map;

		List<String> pList = new ArrayList<String>();
		List<String> dList = new ArrayList<String>();

		for (String infoIdStr : dataArray) {
			if (infoIdStr.startsWith(MixSelectListKey.pepleKey.key)) {
				pList.add(infoIdStr.replaceFirst(MixSelectListKey.pepleKey.key,
						""));
			} else if (infoIdStr.startsWith(MixSelectListKey.deptKey.key)) {
				dList.add(infoIdStr.replaceFirst(MixSelectListKey.deptKey.key,
						""));
			}
		}

		if (!pList.isEmpty())
			map.put(MixSelectListKey.pepleKey.key, pList);

		if (!dList.isEmpty())
			map.put(MixSelectListKey.deptKey.key, dList);
		return map;
	}

	/**
	 * 得到混合窗口选中的列表中，过滤的人员Id集合
	 * 
	 * @param dataArray
	 *            后台获得的列表数组
	 * @param unitId
	 *            公司Id
	 * @return
	 */
	public List<Long> getMixDistinctPepleIds(String[] dataArray, Long unitId) {

		List<Long> pIdList = new ArrayList<Long>();

		Set<Long> pIdSets = new HashSet<Long>();

		if (dataArray == null || dataArray.length == 0)
			return pIdList;

		List<String> pList = new ArrayList<String>();
		List<String> dList = new ArrayList<String>();

		for (String infoIdStr : dataArray) {

			if (infoIdStr.startsWith(MixSelectListKey.pepleKey.key)) {
				pList.add(infoIdStr.replaceFirst(MixSelectListKey.pepleKey.key,
						""));
			} else if (infoIdStr.startsWith(MixSelectListKey.deptKey.key)) {
				dList.add(infoIdStr.replaceFirst(MixSelectListKey.deptKey.key,
						""));
			}
		}

		if (!pList.isEmpty()) {

			for (String userIdStr : pList) {
				if (userIdStr != null) {
					pIdSets.add(Long.valueOf(userIdStr));
				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("corpId", unitId);

		List<Long> tempList = new ArrayList<Long>();
		if (!dList.isEmpty()) {
			map.put("list", dList);
			tempList = sysDepartmentDao.getUserIdsByDeptCodes(map);
			if (tempList != null) {
				pIdSets.addAll(tempList);
			}
		}
		pIdList.addAll(pIdSets);
		return pIdList;
	}

	public List<Map<String, Object>> getGoodsList(HttpServletRequest request,
			long unitId) {
		String searchName = request.getParameter("searchName");
		String typeId = request.getParameter("typeId");
		String isCascade = request.getParameter("isCascade");
		String goodsUnit = request.getParameter("goodsUnit");

		if (StringUtils.isNotBlank(searchName)) {
			searchName = "%" + searchName + "%";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchName", searchName);
		map.put("typeId", Long.valueOf(typeId));
		map.put("unitId", unitId);
		map.put("isCascade", isCascade);
		map.put("goodsUnit", goodsUnit);
		System.out.println(goodsPriceDao.getSelectDialogGoodsList(map));
		return goodsPriceDao.getSelectDialogGoodsList(map);
	}

	/**
	 * 获取类型
	 */
	public List<Map<String, Object>> getGroodsTypeTreeData() {
		return goodsTypeDao.getAllTreeList();
	}

	/**
	 * 得到标签树数据
	 * 
	 * @param typeCode
	 *            类型编码
	 * @return
	 */
	public List<Map<String, Object>> getMarkTreeDataByTypeCode(String typeCode) {

		List<Map<String, Object>> markList = goodsMarkDao
				.getMarkTreeListByTypeCode(typeCode);
		if (markList == null) {
			markList = new ArrayList<Map<String, Object>>();
		}

		GoodsType goodsType = goodsTypeDao.get(Long.valueOf(typeCode));

		Map<String, Object> typeData = new HashMap<String, Object>();
		typeData.put("id", "0");
		typeData.put("text", goodsType.getTypeName());
		markList.add(typeData);
		return markList;
	}

	public List<Map<String, Object>> getGoodsListForJxc(HttpServletRequest request, long unitId) {
		String searchName = request.getParameter("searchName");
		String typeId = request.getParameter("typeId");
		String isCascade = request.getParameter("isCascade");
		String goodsUnit = request.getParameter("goodsUnit");

		if (StringUtils.isNotBlank(searchName)) {
			searchName = "%" + searchName + "%";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchName", searchName);
		map.put("typeId", Long.valueOf(typeId));
		map.put("unitId", unitId);
		map.put("isCascade", isCascade);
		map.put("goodsUnit", goodsUnit);
		System.out.println(goodsPriceDao.getSelectDialogGoodsList(map));
		return goodsPriceDao.getSelectDialogGoodsListForJxc(map);
	}

}
