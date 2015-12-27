package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.VoteCandidate;

/**
 * 

* @ClassName: IVoteCandidateDao

* @Description: 投票候选人数据库接口

* @author 李剑

* @date 2015年8月31日 下午4:12:40
 */
@Component
public interface IVoteCandidateDao{

	/**
	 * add
	 */
	public void add(VoteCandidate voteCandidate);
	
	/**
	 * update
	 */
	public void update(VoteCandidate voteCandidate);
	
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
	public VoteCandidate get(long id);
	
	
	/**
	 * 
	 * @return
	 */
	public VoteCandidate getBySubjectId(long id);
	
	
	/**
	 * 获取候选人列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 获取候选人列表总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	
	public List<Map<String, Object>> getList();
	/**
	 * getBySubjectId
	 * @return
	 */
	public List<Map<String, Object>> getBySubjectId(String subjectId);
}