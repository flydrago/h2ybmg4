package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.VoteSubject;
import com.h2y.bmg.entity.VoteSubjectDetail;
import com.h2y.bmg.entity.VoteSubjectFile;
/**
 * 

 * @ClassName: IVoteSubjectService

 * @Description: 投票主题维护业务接口

 * @author 李剑

 * @date 2015年8月26日 下午6:29:14

 *
 */
public interface IVoteSubjectService{

	public Map<String, Object> getGridData(HttpServletRequest request,long unitId);
	public Map<String, Object> getNewUserGridData(HttpServletRequest request,long unitId);

	public void save(HttpServletRequest request,String op,VoteSubject voteSubject,VoteSubjectDetail voteSubjectDetail,VoteSubjectFile voteSubjectFile,String dictPath,String dictPath1);
}
