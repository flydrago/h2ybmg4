package com.h2y.bmg.dao;




import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.ShareHrefDataRt;

/**
 * ShareHrefDataRtDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-08-18
 * email:info@hwttnet.com
 */
@Component
public interface IShareHrefDataRtDao{

	/**
	 * add
	 */
	public void add(ShareHrefDataRt shareHrefDataRt);

	/**
	 * update
	 */
	public void update(ShareHrefDataRt shareHrefDataRt);

	/**
	 * delete
	 */
	public int deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);

	/**
	 * get
	 * @return
	 */
	public ShareHrefDataRt get(long id);


	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);


	public long getListRows(Map<String,Object> map);



	/**
	 * 获取关联投票列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getVoteList(Map<String,Object> map);

	/**
	 * 获取关联投票列表总数
	 * @param map
	 * @return
	 */
	public long getVoteListRows(Map<String,Object> map);


	/**
	 * 获取活动列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getPromoteList(Map<String,Object> map);


	public long getPromoteListRows(Map<String,Object> map);


	/**
	 * 获取投票选择列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getVoteSelectList(Map<String,Object> map);


	public long getVoteSelectListRows(Map<String,Object> map);

}