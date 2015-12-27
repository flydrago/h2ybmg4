package com.h2y.bmg.entity;



import java.util.Date;

/**
 * UnitSortRt Model create
 * @author hwttnet
 * version:1.2
 * time:2015-05-04
 * email:info@hwttnet.com
 */

public class UnitSortRt{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyUnitSortRt";
	private long id;
	private long unitId;
	private long sortId;
	private String sortCode;
	private Date createDate;
	private Date updateDate;
	private String memo;
	private int status;

	public UnitSortRt(){
		super();
	}

	public UnitSortRt(long id){
		super();
		this.id = id;
	}

	public UnitSortRt(long id,long unitId,long sortId,String sortCode,Date createDate,Date updateDate,String memo,int status){
		super();
		this.id = id;
		this.unitId = unitId;
		this.sortId = sortId;
		this.sortCode = sortCode;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.memo = memo;
		this.status = status;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getUnitId(){
		return unitId;
	}

	public void setUnitId(long unitId){
		this.unitId = unitId;
	}


	public long getSortId(){
		return sortId;
	}

	public void setSortId(long sortId){
		this.sortId = sortId;
	}


	public String getSortCode(){
		return sortCode;
	}

	public void setSortCode(String sortCode){
		this.sortCode = sortCode;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public String toString(){
		return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"sortId:"+sortId+"\t"+"sortCode:"+sortCode+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"memo:"+memo+"\t"+"status:"+status;
	}
}