package com.h2y.bmg.services;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IMemberUserDao;
import com.h2y.bmg.entity.MemberUser;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-04-08
 * email:info@hwttnet.com
 */
@Service("memberUserService")
public class MemberUserServiceImpl implements IMemberUserService{


	@Autowired
	protected IMemberUserDao memberUserDao;

	
	/**
	 * 获取会员列表
	 */
	public Map<String, Object> getList(HttpServletRequest request) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String ifQuery = request.getParameter("ifQuery");


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		List<Map<String, Object>> dataList = memberUserDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", memberUserDao.getListRows(map));
		return gridData;
	}


	public void delete(long id) {
		memberUserDao.deleteById(id);
	}

	public void update(MemberUser memberUser) {
		memberUserDao.update(memberUser);
	}

	public MemberUser get(long id) {
		return memberUserDao.get(id);
	}


	/**
	 * 根据id批量修改状态
	 */
	public void updateStatusByIds(Map<String, Object> map) {
		memberUserDao.updateStatusByIds(map);
	}


	/**
	 * 获取本地会员列表
	 */
	public Map<String, Object> getLocalMemberList(HttpServletRequest request,String zoneCode) {
		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String ifQuery = request.getParameter("ifQuery");


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("zoneCode", zoneCode);
		List<MemberUser> dataList = memberUserDao.getLocalMemberListMap(map);
		if (dataList == null) {
			dataList = new ArrayList<MemberUser>();
		}
		
		String tmpAccount;
		for(MemberUser tmpMember : dataList){
			tmpAccount = tmpMember.getAccount();
			tmpMember.setData1(tmpAccount.replace(tmpAccount.substring(3, 7), "****"));
		}
		
		gridData.put("Rows", dataList);
		gridData.put("Total", memberUserDao.getLocalMemberListRows(map));
		return gridData;
	}


	/**
	 * 获取会员 推荐用户列表
	 */
	public Map<String, Object> getMemberRecommendList(HttpServletRequest request,String account) {
		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String ifQuery = request.getParameter("ifQuery");


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("ifQuery", ifQuery);
		map.put("account", account);
		List<String> refAccountList = memberUserDao.getRefAccountList(map);
		map.put("refAccountList", refAccountList.toString().replace("[", "(").replace("]", ")"));
		List<MemberUser> dataList = memberUserDao.getRefMemberList(map);

		if (dataList == null) {
			dataList = new ArrayList<MemberUser>();
		}
		
		String tmpAccount;
		for(MemberUser tmpMember : dataList){
			tmpAccount = tmpMember.getAccount();
			tmpMember.setData1(tmpAccount.replace(tmpAccount.substring(3, 7), "****"));
		}
		
		gridData.put("Rows", dataList);
		gridData.put("Total", memberUserDao.getRefMemberRows(map));
		return gridData;
	}

}