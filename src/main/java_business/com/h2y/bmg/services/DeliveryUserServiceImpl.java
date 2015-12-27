package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IDeliveryUserDao;
import com.h2y.bmg.dao.ISysDepartmentDao;
import com.h2y.bmg.entity.DeliveryUser;
import com.h2y.bmg.entity.SysDepartment;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 配送员管理
 * @author sunfj
 *
 */
@Service("deliveryUserService")
public class DeliveryUserServiceImpl implements IDeliveryUserService{
	@Autowired
	protected IDeliveryUserDao deliveryUserDao;

	@Autowired
	protected ISysDepartmentDao sysDepartmentDao;


	public void delete(long id) {
		this.deliveryUserDao.deleteById(id);
	}

	public void update(DeliveryUser deliveryUser) {
		this.deliveryUserDao.update(deliveryUser);
	}

	public DeliveryUser get(long id) {
		return deliveryUserDao.get(id);
	}

	/**
	 * 获取列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,
			SysUnits sysUnits) {
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");

		if (sortname!=null && !sortname.equals("")) {

			if(sortname.equals("UNIT_NAME")){
				sortname = "su."+sortname.toLowerCase();
			}else {
				sortname = "du."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("zoneCode", sysUnits.getZoneCode()+"%");


		Map<String,Object> gridData = new HashMap<String, Object>();

		if("1".equals(sysUnits.getZoneCode())){
			List<Map<String,Object>> dataList = deliveryUserDao.getAllListMap(map);
			if (null == dataList || dataList.isEmpty()) {
				dataList = new ArrayList<Map<String,Object>>();
			}
			gridData.put("Rows", dataList);
			gridData.put("Total", deliveryUserDao.getAllListRows(map));
		}else{
			List<Map<String,Object>> dataList = deliveryUserDao.getListMap(map);
			if (null == dataList || dataList.isEmpty()) {
				dataList = new ArrayList<Map<String,Object>>();
			}
			gridData.put("Rows", dataList);
			gridData.put("Total", deliveryUserDao.getListRows(map));
		}

		return gridData;
	}

	/**
	 * 修改状态
	 */
	public void updateStatus(List<String> list, long status) {
		if (null == list || list.isEmpty()) {
			return;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("status", status);
		deliveryUserDao.updateStatus(params);

	}



	/**
	 * 获取公司下的门店列表
	 * @param unitId
	 * @return
	 */
	public List<Map<String,Object>> getShopList(long unitId){
		return this.deliveryUserDao.getShopList(unitId);
	}

	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId) {

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String shopId = request.getParameter("shopId");
		if (sortname!=null && !sortname.equals("")) {
			sortname = "du."+sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("shopId", shopId);

		Map<String,Object> gridData = new HashMap<String, Object>();


		List<Map<String,Object>> dataList = deliveryUserDao.getSelectListMap(map);
		if (null == dataList) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", deliveryUserDao.getSelectListRows(map));

		return gridData;
	}

	/**
	 * 分页获取门店表格数据（供分配门店选择）
	 */
	public Map<String, Object> getShopGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		Long unitId = Long.valueOf(request.getParameter("unitId"));
		Long deliveryUserId = Long.valueOf(request.getParameter("deliveryUserId"));
		String zoneCode = request.getParameter("zoneCode");
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		DeliveryUser tmpUser = deliveryUserDao.get(deliveryUserId);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("account", tmpUser.getAccount());
		map.put("zoneCode", zoneCode+"%");
		
		List<Map<String,Object>> shopDataList = deliveryUserDao.getShopGridData(map);
		if(null == shopDataList){
			shopDataList = new ArrayList<Map<String,Object>>();
		}
		
		gridData.put("Rows", shopDataList);
		gridData.put("Total", deliveryUserDao.getShopRows(map));
		return gridData;
	}

	/**
	 * 保存 配送员&门店 关联关系
	 */
	public Map<String,Object> saveDeliveryShopRt(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultFlag", 0);
		resultMap.put("resultMsg", "配送员&门店关联信息保存失败，原因：请求参数有误");
		
		
		String deliveryUserId = request.getParameter("deliveryUserId");
		String shopArray = request.getParameter("shopArray");
		
		//获取当前配送员信息
		DeliveryUser tmpUser = deliveryUserDao.get(Long.valueOf(deliveryUserId));
		if(null == tmpUser){
			resultMap.put("resultMsg", "配送员&门店关联信息保存失败，原因：配送员不存在");
			return resultMap;
		}
		//转化门店列表
		List<Long> shopList = JSONUtil.jsonToList(shopArray, Long.class);
		//获取配送员已经负责的门店列表
		List<Long> chargedShopList = deliveryUserDao.getDeliveryChargeShopList(tmpUser.getAccount());
		//将新增的门店列表中剔除已经负责的门店列表，避免重复添加
		for (Long shopId : chargedShopList) {
			shopList.remove(shopId);
		}
		
		Map<String,Object> argsMap = new HashMap<String, Object>();	// 添加 配送员&门店 关联关系 参数Map
		for (Long shopId : shopList) {
			SysDepartment tmpShop = sysDepartmentDao.get(shopId);
			argsMap.put("account", tmpUser.getAccount());
			argsMap.put("unitId", tmpShop.getUnitId());
			argsMap.put("shopId", shopId);
			argsMap.put("createDate", DateUtil.getSystemTime());
			//保存 配送员&门店 关联关系
			deliveryUserDao.addDeliveryShopRt(argsMap);
			argsMap.clear();
		}
		
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "向配送员分配门店成功");
		return resultMap;
	}

	/**
	 * 获取配送员负责的门店列表
	 */
	public Map<String, Object> getDeliveryTakeChargeShopList(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		Long account = Long.valueOf(request.getParameter("account"));
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("account", account);
		
		List<Map<String,Object>> shopDataList = deliveryUserDao.getDeliveryTakeChargeShopList(map);
		if(null == shopDataList){
			shopDataList = new ArrayList<Map<String,Object>>();
		}
		
		gridData.put("Rows", shopDataList);
		gridData.put("Total", deliveryUserDao.getDeliveryTakeChargeShopRows(map));
		return gridData;
	}

	/**
	 * 删除 配送员&门店 关联关系
	 */
	public Map<String, Object> deleteDeliveryShopRt(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultFlag", 0);
		resultMap.put("resultMsg", "删除配送员&门店关联关系失败，原因：请求参数有误");
		
		String deliveryUserId = request.getParameter("deliveryUserId");
		String shopArray = request.getParameter("shopArray");
		
		//获取当前配送员信息
		DeliveryUser tmpUser = deliveryUserDao.get(Long.valueOf(deliveryUserId));
		if(null == tmpUser){
			resultMap.put("resultMsg", "删除配送员&门店关联关系失败，原因：配送员不存在");
			return resultMap;
		}
		//转化门店列表
		List<Integer> shopList = JSONUtil.jsonToList(shopArray, Integer.class);
				
		Map<String,Object> argsMap = new HashMap<String, Object>();	// 删除 配送员&门店 关联关系 参数Map
		for (Integer shopId : shopList) {
			argsMap.put("account", tmpUser.getAccount());
			argsMap.put("shopId", shopId);
			//删除 配送员&门店 关联关系
			deliveryUserDao.deleteDeliveryShopRt(argsMap);
			argsMap.clear();
		}
				
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "成功删除配送员&门店关联关系");
		return resultMap;
	}
	
	/**清空指定配送员与门店关联关系**/
	public void emptyDeliveryShopRt(String account){
		deliveryUserDao.emptyDeliveryShopRt(account);
	}
}
