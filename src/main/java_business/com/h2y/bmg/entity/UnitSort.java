package com.h2y.bmg.entity;



import java.util.Date;

/**
 * UnitSort Model create
 * @author hwttnet
 * version:1.2
 * time:2015-05-04
 * email:info@hwttnet.com
 */

public class UnitSort{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyUnitSort";
	private long id;
	private long parentId;
	private String sortCode;
	private String sortName;
	private Date createDate;
	private Date updateDate;
	private String memo;
	private int status;
	private String sortPrefix;
	private long ord;

	public UnitSort(){
		super();
	}

	public UnitSort(long id){
		super();
		this.id = id;
	}

	public UnitSort(long id,long parentId,String sortCode,String sortName,Date createDate,Date updateDate,String memo,int status,String sortPrefix,long ord){
		super();
		this.id = id;
		this.parentId = parentId;
		this.sortCode = sortCode;
		this.sortName = sortName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.memo = memo;
		this.status = status;
		this.sortPrefix = sortPrefix;
		this.ord = ord;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getParentId(){
		return parentId;
	}

	public void setParentId(long parentId){
		this.parentId = parentId;
	}


	public String getSortCode(){
		return sortCode;
	}

	public void setSortCode(String sortCode){
		this.sortCode = sortCode;
	}


	public String getSortName(){
		return sortName;
	}

	public void setSortName(String sortName){
		this.sortName = sortName;
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


	public String getSortPrefix(){
		return sortPrefix;
	}

	public void setSortPrefix(String sortPrefix){
		this.sortPrefix = sortPrefix;
	}


	public long getOrd(){
		return ord;
	}

	public void setOrd(long ord){
		this.ord = ord;
	}

	public String toString(){
		return "id:"+id+"\t"+"parentId:"+parentId+"\t"+"sortCode:"+sortCode+"\t"+"sortName:"+sortName+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"memo:"+memo+"\t"+"status:"+status+"\t"+"sortPrefix:"+sortPrefix+"\t"+"ord:"+ord;
	}
}