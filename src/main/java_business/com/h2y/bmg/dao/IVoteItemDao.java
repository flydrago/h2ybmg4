package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.VoteItem;

/**
 * 

* @ClassName: IVoteItemDao

* @Description: 候选人投票记录数据库接口

* @author 李剑

* @date 2015年9月1日 上午9:05:17
 */
@Component
public interface IVoteItemDao{

	/**
	 * add
	 */
	public void add(VoteItem voteItem);
	
	/**
	 * update
	 */
	public void update(VoteItem voteItem);
	
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
	public VoteItem get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<VoteItem> getList(VoteItem voteItem);
	

	
	
	/**
	 * 获取候选人投票记录列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 获取候选人投票记录列表总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
}