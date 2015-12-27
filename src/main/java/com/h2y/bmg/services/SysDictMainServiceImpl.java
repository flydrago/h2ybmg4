package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysCommUtilDao;
import com.h2y.bmg.dao.ISysDictDetailDao;
import com.h2y.bmg.dao.ISysDictMainDao;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.dict.DictUtil;
import com.h2y.memcached.MemcachedUtil;
import com.h2y.util.JSONUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：SysDictMainServiceImpl  
 * 类描述：  字典主表业务操作接口实现类
 * 创建人：侯飞龙  
 * 创建时间：2015年3月24日 上午10:07:38  
 * 修改人：侯飞龙
 * 修改时间：2015年3月24日 上午10:07:38  
 * 修改备注：  
 * @version
 */
@Service("sysDictMainService")
public class SysDictMainServiceImpl implements ISysDictMainService{

	private final static Logger logger = Logger.getLogger(SysDictMainServiceImpl.class);

	@Autowired
	protected ISysDictMainDao sysDictMainDao;

	@Autowired
	protected ISysDictDetailDao sysDictDetailDao;

	@Autowired
	protected ISysCommUtilDao sysCommUtilDao;

	protected static MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysDictMain
	 *
	 */
	public void add(SysDictMain sysDictMain) {
		// TODO Auto-generated method stub

		sysDictMainDao.add(sysDictMain);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysDictMainDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysDictMainDao.deleteByIds(ids);
	//}

	public void update(SysDictMain sysDictMain) {
		// TODO Auto-generated method stub
		sysDictMainDao.update(sysDictMain);
	}

	public SysDictMain get(long id) {
		// TODO Auto-generated method stub
		return sysDictMainDao.get(id);
	}

	/**
	 * 得到表格列表
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request){
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String parentId = request.getParameter("parentId");

		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("parentId",Integer.parseInt(parentId));

		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = sysDictMainDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", sysDictMainDao.getListRows(map));
		return gridData;
	}


	/**
	 * 是否有相同编码
	 * @param sysDictMain
	 * @param op
	 * @return
	 */
	public boolean isHasSameCode(SysDictMain sysDictMain,String op){

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictCode", sysDictMain.getDictCode());
		if (op.equals("modify")) {
			params.put("id", sysDictMain.getId());
		}
		return sysDictMainDao.getRowsByDictCode(params)>0;
	}





	/**
	 * 加载字典数据到缓存中
	 */
	public void loadDictDataToCache(){
		
		synchronized (SysDictMainServiceImpl.class) {
			
			logger.info("使用缓存！");
			logger.info("加载字典缓存开始！");
			List<SysDictMain> sysDictMainList = sysDictMainDao.getAllList();
			logger.debug("sysDictMainList"+sysDictMainList);
			List<SysDictDetail> sysDictDetailList = sysDictDetailDao.getAllList();
			logger.debug("sysDictDetailList"+sysDictDetailList);
			//字典主表
			if (sysDictMainList!=null && !sysDictMainList.isEmpty()) {
				reloadMainAndDetail(sysDictMainList, sysDictDetailList);
			}
			logger.info("加载字典缓存结束！");
		}
	}
	
	
	/**
	 * 根据编码得到字典主表
	 * @param code
	 * @return
	 */
	public SysDictMain getSysDictMainByCode(String code){
		return sysDictMainDao.getSysDictMainByCode(code);
	}
	
	
	/**
	 * 得到主表对应的详细列表
	 * @param unitId
	 * @param mainCode
	 * @return
	 */
	public List<SysDictDetail> getDetailListByMainCode(long unitId,String mainCode){
		
		List<SysDictDetail> detailList = new ArrayList<SysDictDetail>();
		
		SysDictMain sysDictMain = sysDictMainDao.getSysDictMainByCode(mainCode);
		
		if (sysDictMain!=null) {
			
			String dataType = sysDictMain.getDictType();
			
			if (dataType.equals("json")) {//json

				List<Map<String, Object>> jsonList = (List<Map<String, Object>>)JSONUtil.jsonToListMap(sysDictMain.getDictValue());
				addDataToDetailList(jsonList, detailList);
			}else if (dataType.equals("sql")) {//sql

				Map<String,Object> params = new HashMap<String, Object>();
				params.put("commSql", sysDictMain.getDictValue());
				List<Map<String,Object>> list = sysCommUtilDao.getListMap(params);
				addDataToDetailList(list, detailList);
			}else if (dataType.equals("dict")) {//字典
				
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("unitId", unitId);
				params.put("mainCode", mainCode);
				detailList = sysDictDetailDao.getDetailListByMainCode(params);
			}
		}
		return detailList;
	}
	
	
	
	/**
	 * 根据字典详细编码，得到字典详细
	 * @param unitId 单位Id
	 * @param mainCode 主表编码
	 * @param code 字典详细编码
	 * @return
	 */
	public SysDictDetail getDetailByCode(long unitId,String mainCode,String code){
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unitId", unitId);
		params.put("mainCode", mainCode);
		params.put("code", code);
		return sysDictDetailDao.getDetailByMainCode(params);
	}


	/**
	 * 重载字典主表及其详细表
	 * @param sysDictMainList
	 * @param sysDictDetailList
	 */
	private void reloadMainAndDetail(List<SysDictMain> sysDictMainList,List<SysDictDetail> sysDictDetailList){

		//字典详细映射
		Map<String,Object> detailMap = new HashMap<String, Object>();

		for (SysDictMain sysDictMain : sysDictMainList) {

			String dictMainkey = DictUtil.getDictMainKey(sysDictMain.getDictCode());
			if (memcachedUtil.existsKey(dictMainkey)) {
				memcachedUtil.update(dictMainkey, sysDictMain,0);
			}else {
				memcachedUtil.add(dictMainkey, sysDictMain,0);
			}

			//添加字典主表当条记录对应的字典详细列表
			List<SysDictDetail> detailList = new ArrayList<SysDictDetail>();
			try {
				detailList = getDictDetailList(sysDictMain, sysDictDetailList);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				continue;
			}


			//将字典详细表按公司Id进行重载
			if (!detailList.isEmpty()) {

				for (SysDictDetail sysDictDetail : detailList) {

					List<SysDictDetail> dictCorpDetails = new ArrayList<SysDictDetail>();
					//字典详细key
					String dictDetailKey = DictUtil.getDictDetailKey(sysDictDetail.getUnitId(), sysDictMain.getDictCode());

					if (detailMap.get(dictDetailKey) instanceof List) {
						dictCorpDetails = (List<SysDictDetail>) detailMap.get(dictDetailKey);
						dictCorpDetails.add(sysDictDetail);
					}else {
						dictCorpDetails.add(sysDictDetail);
						detailMap.put(dictDetailKey, dictCorpDetails);
					}
				}
			}
		}

		//重载字典详细
		for (Entry<String, Object> entry : detailMap.entrySet()) {
			if (memcachedUtil.existsKey(entry.getKey())) {
				memcachedUtil.update(entry.getKey(), entry.getValue(),0);
			}else {
				memcachedUtil.add(entry.getKey(), entry.getValue(),0);
			}
		}
	}

	/**
	 * 得到字典主表对应的字典详细列表
	 * @param sysDictMain
	 * @param sysDictDetailList
	 * @return
	 */
	private List<SysDictDetail> getDictDetailList(SysDictMain sysDictMain,List<SysDictDetail> sysDictDetailList){

		//添加字典主表当条记录对应的字典详细列表
		List<SysDictDetail> detailList = new ArrayList<SysDictDetail>();
		String dataType = sysDictMain.getDictType();
		try {

			if (dataType.equals("json")) {//json

				List<Map<String, Object>> list = (List<Map<String, Object>>)JSONUtil.jsonToListMap(sysDictMain.getDictValue());
				addDataToDetailList(list,detailList);
			}else if (dataType.equals("sql")) {//sql

				Map<String,Object> params = new HashMap<String, Object>();
				params.put("commSql", sysDictMain.getDictValue());
				List<Map<String,Object>> list = sysCommUtilDao.getListMap(params);
				addDataToDetailList(list, detailList);
			}else if (dataType.equals("dict")) {//字典

				for (int i = 0; i < sysDictDetailList.size();) {
					SysDictDetail sysDictDetail = sysDictDetailList.get(i);
					if (sysDictMain.getId() == sysDictDetail.getDictMainId()) {
						detailList.add(sysDictDetail);
						sysDictDetailList.remove(i);
					}else {
						i++;
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return detailList;
	}


	/**
	 * 将list数据封装成字典详细对象到集合中
	 * @param list
	 * @param sysDictMain
	 * @param detailList
	 */
	private void addDataToDetailList(List<Map<String,Object>> list,List<SysDictDetail> detailList){

		if (list!=null && !list.isEmpty()) {
			int i = 0;
			for (Map<String, Object> map : list) {

				SysDictDetail sysDictDetail = new SysDictDetail();
				sysDictDetail.setUnitId(1l);
				sysDictDetail.setCode(map.get("code")==null?null:map.get("code")+"");
				sysDictDetail.setValue(map.get("value")==null?null:map.get("value")+"");
				sysDictDetail.setOrd(i);
				detailList.add(sysDictDetail);
				i++;
			}
		}
	}
}