package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IAdvertColumnUnitRtDao;
import com.h2y.bmg.entity.AdvertColumnUnitRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnUnitRtServiceImpl  
 * 类描述：广告栏位单位关联业务操作接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午11:43:14  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午11:43:14  
 * 修改备注：  
 * @version
 */
@Service("advertColumnUnitRtService")
public class AdvertColumnUnitRtServiceImpl implements IAdvertColumnUnitRtService{


	@Autowired
	protected IAdvertColumnUnitRtDao advertColumnUnitRtDao;

	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){

		Map<String,Object> gridData = new HashMap<String, Object>();
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			
			String sortprefix = sortname.equals("COLUMN_NAME")?"cu.":"c.";
			sortname = sortprefix+sortname.toLowerCase();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		List<Map<String,Object>> dataList = advertColumnUnitRtDao.getListMap(map);
		
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}
	
	
	/**
	 * 广告栏保存操作
	 * @param op add：添加 modify：修改
	 * @param advertColumnUnitRt 
	 */
	public void save(String op,AdvertColumnUnitRt advertColumnUnitRt){
		
		if (op.equals("add")) {
			
			advertColumnUnitRtDao.add(advertColumnUnitRt);
		}else {
			
			advertColumnUnitRtDao.update(advertColumnUnitRt);
		}
	}
	
}