package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IGoodsMarkDao;
import com.h2y.bmg.entity.GoodsMark;
import com.h2y.util.DateUtil;
import com.h2y.util.PinyinUtil;

/**
 * 类描述：商品标签的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("goodsMarkService")
public class GoodsMarkServiceImpl implements IGoodsMarkService{
	
	@Autowired
	protected IGoodsMarkDao goodsMarkDao;

	
	/**
	 * 获取列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");//排序名称
		String sortorder = request.getParameter("sortorder");//排序方式 asc、desc
		String typeId = request.getParameter("typeId");//类型
		String ifQuery = request.getParameter("ifQuery");//查询条件
		if (sortname!=null && !sortname.equals("")) {
			
			sortname = sortname.toLowerCase();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("typeId", typeId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = goodsMarkDao.getListMap(map);
		
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsMarkDao.getListRows(map));
		return gridData;
	}


	/**
	 * 保存
	 */
	public void save(String op, GoodsMark goodsMark) {
		
		if (op.equals("add")) {
			
			goodsMark.setCreateDate(DateUtil.getSystemTime());
			goodsMark.setFirSpeName(PinyinUtil.getPinYinHeadChar(goodsMark.getMarkName()));
			goodsMark.setSpeName(PinyinUtil.getPinYin(goodsMark.getMarkName()));
			goodsMarkDao.add(goodsMark);
		}else {
			
			GoodsMark goodsMark2 = goodsMarkDao.get(goodsMark.getId());
			goodsMark.setCreateDate(goodsMark2.getCreateDate());
			goodsMark.setFirSpeName(PinyinUtil.getPinYinHeadChar(goodsMark.getMarkName()));
			goodsMark.setSpeName(PinyinUtil.getPinYin(goodsMark.getMarkName()));
			goodsMarkDao.update(goodsMark);
		}
	}

}