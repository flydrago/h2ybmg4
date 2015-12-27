package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IAdvertColumnDao;
import com.h2y.bmg.entity.AdvertColumn;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnServiceImpl  
 * 类描述：广告栏位业务操作接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午9:26:28  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午9:26:28  
 * 修改备注：  
 * @version
 */
@Service("advertColumnService")
public class AdvertColumnServiceImpl implements IAdvertColumnService{


	@Autowired
	protected IAdvertColumnDao advertColumnDao;

	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request){

		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		List<Map<String,Object>> dataList = advertColumnDao.getListMap(map);
		
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
	 * @param advertColumn 
	 */
	public void save(String op,AdvertColumn advertColumn){
		
		if (op.equals("add")) {
			advertColumnDao.add(advertColumn);
		}else {
			advertColumnDao.update(advertColumn);
		}
	}
}