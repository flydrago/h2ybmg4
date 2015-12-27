package com.h2y.bmg.entity;



import java.util.Date;

/**
 * FindServiceUnit Model create
 * @author hwttnet
 * version:1.2
 * time:2015-04-17
 * email:info@hwttnet.com
 */

public class FindServiceUnit{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyFindServiceUnit";
	private long id;
	private String servicePrefix;
	private long serviceId;
	private long unitId;
	private String zoneCode;
	private Date createDate;
	private long ord;

	public FindServiceUnit(){
		super();
	}

	public FindServiceUnit(long id){
		super();
		this.id = id;
	}

	public FindServiceUnit(long id,String servicePrefix,long serviceId,long unitId,String zoneCode,Date createDate,long ord){
		super();
		this.id = id;
		this.servicePrefix = servicePrefix;
		this.serviceId = serviceId;
		this.unitId = unitId;
		this.zoneCode = zoneCode;
		this.createDate = createDate;
		this.ord = ord;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getServicePrefix(){
		return servicePrefix;
	}

	public void setServicePrefix(String servicePrefix){
		this.servicePrefix = servicePrefix;
	}


	public long getServiceId(){
		return serviceId;
	}

	public void setServiceId(long serviceId){
		this.serviceId = serviceId;
	}


	public long getUnitId(){
		return unitId;
	}

	public void setUnitId(long unitId){
		this.unitId = unitId;
	}


	public String getZoneCode(){
		return zoneCode;
	}

	public void setZoneCode(String zoneCode){
		this.zoneCode = zoneCode;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}



	public long getOrd() {
		return ord;
	}

	public void setOrd(long ord) {
		this.ord = ord;
	}

	public String toString(){
		return "id:"+id+"\t"+"servicePrefix:"+servicePrefix+"\t"+"serviceId:"+serviceId+"\t"+"unitId:"+unitId+"\t"+"zoneCode:"+zoneCode+"\t"+"createDate:"+createDate+"\t"+"ord:"+ord;
	}
}