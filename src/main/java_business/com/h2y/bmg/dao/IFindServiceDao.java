package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.FindService;

/**
 * 类描述：客户端发现服务数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年3月5日下午3:42:50
 * 邮件：1162040314@qq.com
 */
@Component
public interface IFindServiceDao{

	/**
	 * add
	 */
	public void add(FindService findService);

	/**
	 * update
	 */
	public void update(FindService findService);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);

	/**
	 * get
	 * @return
	 */
	public FindService get(long id);

	/**
	 * 得到发现服务分页列表
	 * @param map
	 * {parentId:父级Id,
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);


	/**
	 * 得到发现服务分页列表
	 * @param map
	 * {parentId:父级Id,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);

	/**
	 * 得到服务数列表
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(Map<String,Object> map);

	/**
	 * 得到相同服务编码的数量
	 * @param map
	 * {serviceCode:服务编码,
	 * op:标识（add:添加、modify:修改）,
	 * id:服务Id（op为modify进行判断）}
	 * @return
	 */
	public long getSameServiceCodeRows(Map<String,Object> map);
	
	/**
	 * 根据服务编码得到，发现服务
	 * @param serviceCode 发现编码
	 * @return
	 */
	public FindService getByServiceCode(String serviceCode);
}