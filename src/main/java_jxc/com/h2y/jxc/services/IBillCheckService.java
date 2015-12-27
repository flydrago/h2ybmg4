package com.h2y.jxc.services;

import java.util.Map;

import com.h2y.jxc.entity.JxcBillCheckParams;

public interface IBillCheckService {

	/**
	 * 业务参数检验
	 * @param reqMap
	 * @return
	 */
	public JxcBillCheckParams billCheck(Map<String,Object> reqMap);
}
