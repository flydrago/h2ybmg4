package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.VoteSubjectDetail;

/**
 * 

* @ClassName: IVoteSubjectDetailDao

* @Description: 投票主题详细数据库接口

* @author 李剑

* @date 2015年8月31日 下午4:11:35
 */
@Component
public interface IVoteSubjectDetailDao{

	/**
	 * add
	 */
	public void add(VoteSubjectDetail voteSubjectDetail);
	
	/**
	 * update
	 */
	public void update(VoteSubjectDetail voteSubjectDetail);
	
	/**
	 * delete
	 */
	public void deleteById(long id);
	
	/**
	 * deleteBySubjectId
	 */
	public void deleteBySubjectId(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public VoteSubjectDetail get(long id);
	
	
	/**
	 * 根据主题id获取主题详细数据
	 * @return
	 */
	public VoteSubjectDetail getBySubjectId(long subjectId);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<VoteSubjectDetail> getList(VoteSubjectDetail voteSubjectDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<VoteSubjectDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}