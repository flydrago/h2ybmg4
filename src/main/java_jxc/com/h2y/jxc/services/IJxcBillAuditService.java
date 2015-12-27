package com.h2y.jxc.services;

import java.util.Map;

public interface IJxcBillAuditService {

	/**
	 * 单据审核接口
	 * @param paraMap
	 * @return
	 */
	Map<String,Object> billAudit(Map<String,Object> reqMap);
}
