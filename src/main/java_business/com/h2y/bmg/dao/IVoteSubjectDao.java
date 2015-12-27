package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.h2y.bmg.entity.VoteSubject;
/**
 * 

* @ClassName: IVoteSubjectDao

* @Description: 投票主题维护数据库接口

* @author 李剑

* @date 2015年8月26日 下午6:32:08
 */
@Component
public interface IVoteSubjectDao{

	/**
	 * add
	 */
	public void add(VoteSubject voteSubject);
	
	/**
	 * update
	 */
	public void update(VoteSubject voteSubject);
	
	/**
	 * delete
	 */
	public void deleteById(long id);
	
	
	public VoteSubject get(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public List<Map<String, Object>> selectBySubjectName(String subjectName);
	
	
	/**
	 * 获取投票主题列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 获取投票主题列表总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
}