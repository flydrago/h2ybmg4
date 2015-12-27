package com.h2y.bmg.services;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.h2y.bmg.entity.SysParamType;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-12-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysParamTypeService{
	
	public void add(SysParamType sysParamType);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysParamType sysParamType);

	public SysParamType get(long id);
	
	/**
	 * 得到列表数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getGirdData(HttpServletRequest request);
	
	/**
	 * 是否有相同编码
	 * @param op
	 * @param sysParamType
	 * @return
	 */
	public boolean isHasSameCode(String op,SysParamType sysParamType);
}
