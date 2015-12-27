package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.VoteSubjectFile;

/**

* @ClassName: IVoteSubjectFileDao

* @Description: 投票主题主页图片数据库接口

* @author 李剑

* @date 2015年8月31日 下午4:11:19
 */
@Component
public interface IVoteSubjectFileDao{

	/**
	 * add
	 */
	public void add(VoteSubjectFile voteSubjectFile);
	
	/**
	 * update
	 */
	public void update(VoteSubjectFile voteSubjectFile);
	
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
	public VoteSubjectFile get(long id);
	/**
	 * getBySubjectId
	 */
	public VoteSubjectFile getBySubjectId(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<VoteSubjectFile> getList(VoteSubjectFile voteSubjectFile);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<VoteSubjectFile> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}