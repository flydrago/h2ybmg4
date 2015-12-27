package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysDictMain;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-26
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysDictMainService{
	
	public void add(SysDictMain sysDictMain);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysDictMain sysDictMain);

	public SysDictMain get(long id);
	
	/**
	 * 得到表格列表
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);
	
	
	/**
	 * 是否有相同编码
	 * @param sysDictMain
	 * @param op
	 * @return
	 */
	public boolean isHasSameCode(SysDictMain sysDictMain,String op);
	
	/**
	 * 加载字典数据到缓存中
	 */
	public void loadDictDataToCache();
	
	
	/**
	 * 根据编码得到字典主表
	 * @param code
	 * @return
	 */
	public SysDictMain getSysDictMainByCode(String code);
	
	
	/**
	 * 得到主表对应的详细列表
	 * @param unitId 单位Id
	 * @param mainCode 主表编码
	 * @return
	 */
	public List<SysDictDetail> getDetailListByMainCode(long unitId,String mainCode);
	
	
	/**
	 * 根据字典详细编码，得到字典详细
	 * @param unitId 单位Id
	 * @param mainCode 主表编码
	 * @param code 字典详细编码
	 * @return
	 */
	public SysDictDetail getDetailByCode(long unitId,String mainCode,String code);
	
}
