package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.VoteCandidatePic;

/**
 * 

* @ClassName: IVoteCandidatePicDao

* @Description:投票候选人图片数据库接口

* @author 李剑

* @date 2015年8月31日 下午4:12:05
 */
@Component
public interface IVoteCandidatePicDao{

	/**
	 * add
	 */
	public void add(VoteCandidatePic voteCandidatePic);
	
	/**
	 * update
	 */
	public void update(VoteCandidatePic voteCandidatePic);
	
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
	public VoteCandidatePic get(long id);
	
	
	/**
	 * getByUserId
	 * @return
	 */
	public List<Map<String, Object>> getByUserId(long id);
	/**
	 * getPicId
	 * @return
	 */
	public List<Map<String, Object>> getPicId(long id);
	
	
	
	/**
	 * getList
	 * @return
	 */
	public List<VoteCandidatePic> getList(VoteCandidatePic voteCandidatePic);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<VoteCandidatePic> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}