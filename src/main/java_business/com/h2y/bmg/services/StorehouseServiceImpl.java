package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.basic.WbsKeys.XghKeys;
import com.h2y.bmg.dao.IStorehouseDao;
import com.h2y.bmg.dao.ISysDepartmentDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.Storehouse;
import com.h2y.bmg.entity.SysDepartment;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.PinyinUtil;

/**
 * 类描述：仓库的业务操作接口实现类 作者：侯飞龙 时间：2015年1月7日上午9:59:28 邮件：1162040314@qq.com
 */
@Service("storehouseService")
public class StorehouseServiceImpl implements IStorehouseService {

	private static final Logger logger = Logger.getLogger(StorehouseServiceImpl.class);

	@Autowired
	protected IStorehouseDao storehouseDao;


	@Autowired
	protected ISysUnitsDao sysUnitsDao;


	@Autowired
	protected ISysDepartmentDao sysDepartmentDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String parentType = request.getParameter("parentType");
		String shopId = request.getParameter("shopId");

		if (sortname != null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("parentType", parentType);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		if (null != shopId && !"".equals(shopId)) {
			map.put("shopId", shopId);
		}

		List<Map<String, Object>> dataList = storehouseDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", storehouseDao.getListRows(map));
		return gridData;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, String op,
			Storehouse storehouse,SysUser sysUser) {
		String xghUrl = "xgh/storage.htm";
		int flag = 0;

		if (op.equals("add")) {

			storehouse.setType(4);
			storehouse.setCreateDate(DateUtil.getSystemTime());
			storehouse.setStoreFirSpeName(PinyinUtil
					.getPinYinHeadChar(storehouse.getStoreName()));
			storehouse.setStoreSpeName(PinyinUtil.getPinYin(storehouse
					.getStoreName()));
			storehouse.setStatus(0);
			flag = storehouseDao.add(storehouse);


		} else {

			Storehouse storehouse2 = storehouseDao.get(storehouse.getId());
			storehouse.setCreateDate(storehouse2.getCreateDate());
			storehouse.setStoreFirSpeName(PinyinUtil
					.getPinYinHeadChar(storehouse.getStoreName()));
			storehouse.setStoreSpeName(PinyinUtil.getPinYin(storehouse
					.getStoreName()));
			storehouse.setStatus(storehouse2.getStatus());
			storehouse.setType(storehouse2.getType());
			storehouse.setShopId(storehouse2.getShopId());
			storehouse.setS3stcode(storehouse2.getS3stcode());
			storehouse.setS3createdate(storehouse2.getS3createdate());
			flag = storehouseDao.update(storehouse);
			op = "update";


		}

		//创建、修改仓库成功
		if(flag > 0){

			//象过河初始化仓库
			Map<String,Object> postMap = new HashMap<String,Object>();
			postMap.put("op", op);
			SysUnits  sysUnits = sysUnitsDao.get(storehouse.getUnitId());
			//区域代理对接象过河
			if(sysUnits.getUnitType()==0){

				postMap.put(XghKeys.unitid.value(), sysUnits.getId());
				postMap.put(XghKeys.unitcode.value(), sysUnits.getUnitCode());
				postMap.put(XghKeys.xghucode.value(), sysUnits.getS3ucode());
				postMap.put(XghKeys.xghuname.value(), sysUnits.getS3uname());
				postMap.put(XghKeys.storageid.value(), storehouse.getId());
				//仓库信息
				postMap.put(XghKeys.storagename.value(), storehouse.getStoreName());
				postMap.put(XghKeys.storageaddress.value(), storehouse.getZoneDetail());
				if("update".equals(op)){
					postMap.put(XghKeys.xghstcode.value(), storehouse.getS3stcode());
				}

				//门店信息			
				SysDepartment sysDepartment = sysDepartmentDao.get(storehouse.getShopId());
				postMap.put(XghKeys.shopid.value(), sysDepartment.getId());
				postMap.put(XghKeys.shopname.value(), sysDepartment.getDeptName());
				//用户信息
				postMap.put(XghKeys.username.value(), sysUser.getUserName());
				postMap.put(XghKeys.account.value(), sysUser.getAccount());

				//调用服务
				Map<String,Object> resultMap = DataRequestUtil.getRequestData(xghUrl, postMap);
				if (null != resultMap && "1".equals(resultMap.get("resultflg") + "")) {
					//象过河创建仓库成功							
					Map<String,Object> xghMap = JSONUtil.getMap(resultMap.get("resultdata"));
					storehouse.setS3stcode(xghMap.get(XghKeys.xghstcode.value())+"");
					storehouse.setS3createdate(DateUtil.toDate(xghMap.get(XghKeys.xghcreatedate.value())+""));
					storehouseDao.update(storehouse);

					logger.info(op+"仓库："+storehouse.getStoreName()+"成功");
				}else if (null != resultMap && "-1".equals(resultMap.get("resultflg") + "")){
					logger.info(op+"仓库："+storehouse.getStoreName()+"失败，象过河服务未启动！");
				}else{

					if(null != resultMap){
						logger.info(op+"仓库："+storehouse.getStoreName()+"失败,"+resultMap.get("resultmsg"));
					}else{
						logger.info(op+"仓库："+storehouse.getStoreName()+"失败");
					}
				}


			}

		}


	}

	/**
	 * 初始化单位仓库
	 * 
	 * @param sysUnits
	 */
	public void initStorehouse(SysUnits sysUnits) {

		int unitType = sysUnits.getUnitType();

		if (unitType == 0) {// H2Y代理

			storehouseDao.add(get(sysUnits, "正品库", 1));
			storehouseDao.add(get(sysUnits, "在途库", 2));
			storehouseDao.add(get(sysUnits, "残次品库", 3));
		} else if (unitType == 1) {// 采购商

			storehouseDao.add(get(sysUnits, "初始化仓库", 5));
		} else if (unitType == 2) {// 供应商

			storehouseDao.add(get(sysUnits, "初始化仓库", 5));
		}
	}

	/**
	 * 得到仓库
	 * 
	 * @param name
	 *            仓库名称
	 * @param type
	 *            仓库类型
	 * @return
	 */
	private Storehouse get(SysUnits sysUnits, String name, int type) {

		Storehouse storehouse = new Storehouse();
		storehouse.setType(type);
		storehouse.setStoreName(name);
		storehouse.setCreateDate(DateUtil.getSystemTime());
		storehouse.setStoreFirSpeName(PinyinUtil.getPinYinHeadChar(name));
		storehouse.setStoreSpeName(PinyinUtil.getPinYin(name));
		storehouse.setStatus(0);
		storehouse.setParentType("unit");
		storehouse.setMemo("初始化仓库");
		storehouse.setUnitId(sysUnits.getId());
		storehouse.setUnitType(sysUnits.getUnitType());
		storehouse.setZoneCode(sysUnits.getZoneCode());
		return storehouse;
	}
}