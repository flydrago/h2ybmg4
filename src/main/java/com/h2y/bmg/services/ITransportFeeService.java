package com.h2y.bmg.services;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.TransportFee;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-02-02
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITransportFeeService{
	
	public void add(TransportFee transportFee);
	
	public void update(TransportFee transportFee);

	public TransportFee get(long id);
	
	public Map<String, Object> getGridData(HttpServletRequest request,long unid);
	
}
