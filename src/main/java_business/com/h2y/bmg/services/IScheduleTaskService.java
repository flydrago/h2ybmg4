package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IScheduleTaskService {
	public Map<String, Object> getGridData(HttpServletRequest request);

}
