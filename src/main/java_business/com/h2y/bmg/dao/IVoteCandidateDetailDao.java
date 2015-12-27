package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.VoteCandidateDetail;

/**
 * 

* @ClassName: IVoteCandidateDetailDao

* @Description: 投票候选人详细数据库接口

* @author 李剑

* @date 2015年8月31日 下午4:09:17
 */
@Component
public interface IVoteCandidateDetailDao{

	/**
	 * add
	 */
	public void add(VoteCandidateDetail voteCandidateDetail);
	
	/**
	 * update
	 */
	public void update(VoteCandidateDetail voteCandidateDetail);
	
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
	public VoteCandidateDetail get(long id);
	
	
	
	/**
	 * getByUserId
	 * @return
	 */
	public VoteCandidateDetail getByUserId(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<VoteCandidateDetail> getList(VoteCandidateDetail voteCandidateDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<VoteCandidateDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}