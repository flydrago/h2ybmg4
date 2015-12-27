package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IZoneDao;
import com.h2y.bmg.entity.Zone;
import com.h2y.util.PinyinUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-11-04
 * email:info@hwttnet.com
 */
@Service("zoneService")
public class ZoneServiceImpl implements IZoneService{


	@Autowired
	protected IZoneDao zoneDao;
	

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param zone
	 *
	 */
	public void add(Zone zone) {
		zoneDao.add(zone);
	}


	public void delete(long id) {
		zoneDao.deleteById(id);
	}

	public void update(Zone zone) {
		zoneDao.update(zone);
	}

	public Zone get(long id) {
		return zoneDao.get(id);
	}
	
	/**
	 * 根据父级Id，得到tree列表
	 * @param pid 父级Id（0：为顶级）
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(long pid,String zonecode,long unitId){

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("code", zonecode);
		params.put("unitId", unitId);
		
		List<Map<String,Object>> list = zoneDao.getTreeList(params);
		if (list==null) {
			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}


	/**
	 * 根据父级Id，得到grid列表
	 * @param request 访问对象
	 * @param parentId 父级Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long pid,String zonecode,long unitId){

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("code", zonecode);
		params.put("unitId", unitId);
		List<Map<String,Object>> list = zoneDao.getGridList(params);
		if (list==null) {
			list = new ArrayList<Map<String,Object>>();
		}

		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", list);
		gridData.put("Total", list.size());
		return gridData;
	}

	
    
    
    /**
     * 保存
     * @param op
     * @param zone
     */
    public void save(String op,Zone zone){
    	  
        if (op.equals("location")) {//定位
        	
        	Zone zone2 = zoneDao.get(zone.getId());
        	zone2.setLatitude(zone.getLatitude());
        	zone2.setLongitude(zone.getLongitude());
        	zone2.setLocation(zone.getLocation());
        	zoneDao.update(zone2);
		}else if(op.equals("add")){//添加
			
			String maxCode = zoneDao.getMaxCodeByPid(zone.getPid());
			
			Zone pZone = zoneDao.get(Long.valueOf(zone.getPid()));
			String currenCode = "";
			if (maxCode==null) {
				currenCode = pZone.getCode()+"001";
			}else {
				currenCode =(Long.valueOf(maxCode)+1)+"";
			}
			
			//更新父级islast字段
			pZone.setIsLast("0");
			zoneDao.update(pZone);
			zone.setSpellName(PinyinUtil.getPinYin(zone.getName()));
			zone.setFirSpellName(PinyinUtil.getPinYinHeadChar(zone.getName()));
			zone.setCode(currenCode);
			zone.setPcode(pZone.getCode());
			zone.setIsLast("1");
			zone.setLevel((Integer.parseInt(pZone.getLevel())+1)+"");
			zoneDao.add(zone);

			
			zone.setPreFix(pZone.getPreFix()+"_"+zone.getId());
			zoneDao.update(zone);
			
		}else {//修改
			
			Zone zone2 = zoneDao.get(zone.getId());
			zone2.setName(zone.getName());
			zone.setSpellName(PinyinUtil.getPinYin(zone.getName()));
			zone.setFirSpellName(PinyinUtil.getPinYinHeadChar(zone.getName()));
        	zoneDao.update(zone2);
		}
    }
    
    
    
	
	/**
	 * 得到当前位置名称
	 * @param map
	 * @return
	 */
	public String getCurrentName(String prefix){
		
		String [] prefix_array = prefix.split("_");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", Arrays.asList(prefix_array));
		return zoneDao.getCurrentName(map);
	}
	
	
	
	/**
	 * 是否有子级
	 * @param pid
	 * @return
	 */
	public boolean isHasChild(String pid){
		
		return zoneDao.getChildRowsByPid(pid)>0;
	}


	public Map<String,Object> getAll(HttpServletRequest request,long unid) {
		Map<String, Object> paraMap  = new HashMap<String, Object>();
		Map<String, Object> resultMap  = new HashMap<String, Object>();
		String keyWord= request.getParameter("keyword");
		if(StringUtils.isNotBlank(keyWord)){
			keyWord="%"+keyWord+"%";
		}
		String page = request.getParameter("page");
		String pagesize = request.getParameter("rows");
		paraMap.put("page",Integer.parseInt(page));
		paraMap.put("pagesize",Integer.parseInt(pagesize));
		paraMap.put("keyWord", keyWord);
		paraMap.put("unid", unid);
		List<Map<String, Object>> resultList = zoneDao.getAll(paraMap);
	    if (resultList==null) {
			resultList = new ArrayList<Map<String,Object>>();
	    }
	    resultMap.put("rows", resultList);
	    resultMap.put("total", zoneDao.getAllSize(paraMap));
		return resultMap;
	}


	public Zone getZoneByCode(String zoneCode) {
		return zoneDao.getZoneByCode(zoneCode);
	}
	
}