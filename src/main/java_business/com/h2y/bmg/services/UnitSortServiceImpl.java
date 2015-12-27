package com.h2y.bmg.services;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IUnitSortDao;
import com.h2y.bmg.dao.IUnitSortRtDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.UnitSort;
import com.h2y.bmg.entity.UnitSortRt;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 
 * @author sunfj
 *
 */
@Service("unitSortService")
public class UnitSortServiceImpl implements IUnitSortService{


	@Autowired
	protected IUnitSortDao unitSortDao;

	@Autowired
	protected IUnitSortRtDao unitSortRtDao;


	/**
	 * 获取分类树
	 */
	public List<Map<String,Object>> getAllTreeList(){

		List<Map<String,Object>> treeList = this.unitSortDao.getAllTreeList();

		if (null == treeList) {
			treeList = new ArrayList<Map<String, Object>>();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("text", "分类");
		treeList.add(root);

		return treeList;
	}

	/**
	 * 获取树
	 */
	public List<Map<String, Object>> getChildTreeListById(long id) {
		return unitSortDao.getChildTreeListById(id);
	}

	/**
	 * 获取列表
	 */
	public List<Map<String, Object>> getChildSelectListById(long id) {
		return unitSortDao.getChildSelectListById(id);
	}

	public void add(UnitSort unitSort) {

		unitSortDao.add(unitSort);
	}


	public void delete(long id) {
		unitSortDao.deleteById(id);
	}

	public void deleteByIds(List<Long> ids){
		unitSortDao.deleteByIds(ids);
	}

	public void update(UnitSort unitSort) {
		unitSortDao.update(unitSort);
	}

	public UnitSort get(long id) {
		return unitSortDao.get(id);
	}


	public List<UnitSort> getList(UnitSort unitSort){
		List<UnitSort> list = unitSortDao.getList(unitSort);


		return list;
	}




	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return unitSortDao.getRows(map);
	}


	/**
	 * 获取列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String id = request.getParameter("id");
		if (sortname!=null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		if(null != id ){
			map.put("parentId", Long.valueOf(id));
		}else{
			map.put("parentId", 0);
		}

		List<Map<String,Object>> dataList = unitSortDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", unitSortDao.getRows(map));
		return gridData;
	}

	/**
	 * 根据code判断是否重复
	 */
	public List<Map<String, Object>> getUnitSortByCode(UnitSort unitSort) {
		return unitSortDao.getUnitSortByCode(unitSort);
	}




	public void save(String op, UnitSort unitSort) {

		if (op.equals("add")) {

			unitSort.setCreateDate(DateUtil.getSystemTime());			
			unitSortDao.add(unitSort);

			//更新编码
			if (unitSort.getParentId()==0) {

				unitSort.setSortPrefix(unitSort.getId()+"");
			}else {

				UnitSort unitSort2 = unitSortDao.get(unitSort.getParentId());
				unitSort.setSortPrefix(unitSort2.getSortPrefix()+"_"+unitSort.getId());
			}
			unitSortDao.update(unitSort);

		}else {

			UnitSort unitSort2 = unitSortDao.get(unitSort.getId());
			unitSort.setCreateDate(unitSort2.getCreateDate());
			unitSort.setUpdateDate(DateUtil.getSystemTime());
			unitSortDao.update(unitSort);
		}
	}


	/**
	 * 根据parentId获取子列表
	 */
	public List<Map<String,Object>> getRowsByParentId(long parentId){
		return unitSortDao.getRowsByParentId(parentId);
	}



	public List<Map<String,Object>> getListMapById(Map<String,Object> map){
		return unitSortDao.getListMapById(map);
	}


	/**
	 * 保存经营范围
	 * @param request
	 * @param sysUnits
	 * @return
	 */
	public String saveUnitSort(HttpServletRequest request,String op,SysUnits sysUnits){
		String sortData  = request.getParameter("sortData");
		List<Map<String, Object>> sortList = new ArrayList<Map<String, Object>>();

		//删除原数据
		if("modify".equals(op)){
			UnitSortRt unitSortRt = new UnitSortRt();
			unitSortRt.setUnitId(sysUnits.getId());
			unitSortRt.setUpdateDate(DateUtil.getSystemTime());
			unitSortRtDao.deleteSortRt(unitSortRt);
		}

		if(null != sortData && !"".equals(sortData)){			

			sortList = JSONUtil.jsonToListMap(sortData);
			if(null != sortList && sortList.size() > 0){

				for(int i=0;i<sortList.size();i++){
					Map<String,Object> sortMap = new HashMap<String,Object>();
					UnitSortRt unitSortRt = new UnitSortRt();
					sortMap = sortList.get(i);
					unitSortRt.setSortId(Long.valueOf(sortMap.get("id")+""));
					unitSortRt.setMemo(sortMap.get("text")+"");
					unitSortRt.setCreateDate(DateUtil.getSystemTime());
					unitSortRt.setStatus(0);
					unitSortRt.setUnitId(sysUnits.getId());
					unitSortRtDao.add(unitSortRt);
				}
			}
		}
		return null;
	}


	/**
	 * 获取经营范围
	 */
	public List<Map<String,Object>> getSortList(SysUnits sysUnits){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("unitId", sysUnits.getId());		
		return unitSortRtDao.getSortList(paramsMap);
	}


}