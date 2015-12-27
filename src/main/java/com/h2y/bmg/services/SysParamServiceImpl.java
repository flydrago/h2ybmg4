package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysParamDao;
import com.h2y.bmg.dao.ISysParamTypeDao;
import com.h2y.bmg.entity.SysParam;
import com.h2y.bmg.entity.SysParamType;
import com.h2y.util.JSONUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-12-05
 * email:info@hwttnet.com
 */
@Service("sysParamService")
public class SysParamServiceImpl implements ISysParamService{


	@Autowired
	protected ISysParamDao sysParamDao;
	
	@Autowired
	protected ISysParamTypeDao sysParamTypeDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysParam
	 *
	 */
	public void add(SysParam sysParam) {
		// TODO Auto-generated method stub

		sysParamDao.add(sysParam);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysParamDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysParamDao.deleteByIds(ids);
	//}

	public void update(SysParam sysParam) {
		// TODO Auto-generated method stub
		sysParamDao.update(sysParam);
	}

	public SysParam get(long id) {
		// TODO Auto-generated method stub
		return sysParamDao.get(id);
	}

	
	/**
	 * 得到列表数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGirdData(HttpServletRequest request,long unitId){
		
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String typeId = request.getParameter("typeId");

		if (sortname!=null && !sortname.equals("")) {
			sortname = "a."+sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("typeId", Long.valueOf(typeId));
		
		List<Map<String,Object>> dataList = sysParamDao.getListMap(map);
		long totalRows = sysParamDao.getListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
	
	
	/**
	 * 得到参数类型树数据
	 * @return
	 */
	public String getParamTypeTreeData(){
		List<Map<String,Object>> treeList = sysParamTypeDao.getTreeList();
		if (treeList==null) {
			
			treeList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 0);
		map.put("text", "参数类型");
		treeList.add(map);
		return JSONUtil.getJson(treeList);
	}
	
	
	/**
	 * 是否有相同编码
	 * @param op
	 * @param sysParam
	 * @return
	 */
	public boolean isHasSameCode(String op,SysParam sysParam){
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("typeId", sysParam.getTypeId());
		map.put("paramsCode", sysParam.getParamsCode());
		map.put("unitId", sysParam.getUnitId());
		if (op.equals("modify")) {
			map.put("id", sysParam.getId());
		}
		return sysParamDao.getRowsByTypeIdAndCode(map)>0;
	}
	
	
	public void save(String op,long unitId,SysParam sysParam){
		
		if (op.equals("add")) {
			
			sysParamDao.add(sysParam);
		}else {
			
			if (unitId == 1) {
				sysParamDao.update(sysParam);
			}else {
				
				SysParam sysParam2 = sysParamDao.get(sysParam.getId());
				//检测当前单位是否有记录，没有记录则添加平台数据
				if (sysParam2.getUnitId()!=unitId) {
					
					sysParam2.setParamsValue(sysParam.getParamsValue());
					sysParam2.setMemo(sysParam.getMemo());
					sysParam2.setOrd(sysParam.getOrd());
					sysParam2.setUnitId(unitId);
					sysParamDao.add(sysParam2);
				}else {
					
					sysParam2.setParamsValue(sysParam.getParamsValue());
					sysParam2.setMemo(sysParam.getMemo());
					sysParam2.setOrd(sysParam.getOrd());
					sysParamDao.update(sysParam2);
				}
			}
		}
	}
	
	
	/**
	 * 根据编码的得到
	 * @param unitId 单位Id
	 * @param typeCode 类型编码
	 * @param code 编码
	 * @return
	 */
	public SysParam getSysParamByCode(long unitId,String typeCode,String code){
		
		SysParamType sysParamType = sysParamTypeDao.getParamTypeByCode(typeCode);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", unitId);
		map.put("typeId", sysParamType.getId());
		map.put("paramsCode", code);
		SysParam sysParam = sysParamDao.getByTypeIdAndCode(map);
		
		if (sysParam==null) {
			map.put("unitId", 1);
			sysParam = sysParamDao.getByTypeIdAndCode(map);
		}
		return sysParam;
	}
	
	
	/**
	 * 根据类型编码的得到对应参数的列表（对外开放）
	 * @param unitId 单位Id
	 * @param typeCode 类型编码
	 * @return
	 */
	public List<SysParam> getSysParamListByTypeCode(long unitId,String typeCode){
		
		SysParamType sysParamType = sysParamTypeDao.getParamTypeByCode(typeCode);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", unitId);
		map.put("typeId", sysParamType.getId());
		return sysParamDao.getListByTypeId(map);
	}

}