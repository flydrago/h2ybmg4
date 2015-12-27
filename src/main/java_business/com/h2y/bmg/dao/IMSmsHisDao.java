package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.MSmsHis;

/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：IMSmsHisDao  
 * 类描述：  短信发送记录查看数据库接口类
 * 创建人：李剑 
 * 创建时间：2015年9月21日 下午1:03:25  
 * 修改人：李剑
 * 修改时间：2015年9月21日 下午1:03:25  
 * 修改备注：  如果你看到这个，那么说明你现在已经在负责我以前的项目了。我感到非常抱歉。愿上帝保佑你。
 * @version
 */
@Component
public interface IMSmsHisDao{

	/**
	 * add
	 */
	public void add(MSmsHis mSmsHis);
	
	/**
	 * update
	 */
	public void update(MSmsHis mSmsHis);
	
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
	public MSmsHis get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<MSmsHis> getList(MSmsHis mSmsHis);
	

	/**
	 * 获取短信发送列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 获取短信发送列表总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
}