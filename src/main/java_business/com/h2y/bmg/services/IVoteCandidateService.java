package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/*

 * @ClassName: IVoteCandidateService

 * @Description: 投票候选人业务接口类

 * @author 李剑

 * @date 2015年8月31日 下午4:03:49
 */
public interface IVoteCandidateService{
	/**
	 * 
	 * @param request
	 * @param unitId
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,long unitId);
	public Map<String, Object> getVoteItemsGridData(HttpServletRequest request,long unitId);
	public void delete(long id);
}
