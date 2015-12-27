package com.h2y.bmg.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ICouponsSelectFilter {

	/**
	 * 得到优惠劵编码过滤列表
	 * @param request
	 * @param sysUnits
	 * @return
	 */
	public List<String> getCouponsCodeFilterList(HttpServletRequest request,long unitId);
}
