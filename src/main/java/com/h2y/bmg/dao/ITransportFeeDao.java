package com.h2y.bmg.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TransportFee;

/**
 * TransportFeeDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-02-02
 * email:info@hwttnet.com
 */
@Component
public interface ITransportFeeDao{

	public void add(TransportFee transportFee);
	
	public void update(TransportFee transportFee);
	
	public TransportFee get(long id);
	
	public List<Map<String, Object>> getGridData(Map<String, Object> map);
	
	public long getGridDataSize(Map<String, Object> map);
	
	public TransportFee getTransfee(Map<String, Object> paraMap);
	
	
	
	
}