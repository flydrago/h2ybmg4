package com.h2y.bmg.entity;



import java.util.Date;

/**
 * FindActivityComment Model create
 * @author hwttnet
 * version:1.2
 * time:2015-04-17
 * email:info@hwttnet.com
 */

public class FindActivityComment{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyFindActivityComment";
	private long id;
	private long activityId;
	private long memberId;
	private String content;
	private Date createDate;
	private int ifPass;
	private int status;
	private String reverse1;
	private String reverse2;

	public FindActivityComment(){
		super();
	}

	public FindActivityComment(long id){
		super();
		this.id = id;
	}

	public FindActivityComment(long id,long activityId,long memberId,String content,Date createDate,int ifPass,int status,String reverse1,String reverse2){
		super();
		this.id = id;
		this.activityId = activityId;
		this.memberId = memberId;
		this.content = content;
		this.createDate = createDate;
		this.ifPass = ifPass;
		this.status = status;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getActivityId(){
		return activityId;
	}

	public void setActivityId(long activityId){
		this.activityId = activityId;
	}


	public long getMemberId(){
		return memberId;
	}

	public void setMemberId(long memberId){
		this.memberId = memberId;
	}


	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public int getIfPass(){
		return ifPass;
	}

	public void setIfPass(int ifPass){
		this.ifPass = ifPass;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}


	public String getReverse1(){
		return reverse1;
	}

	public void setReverse1(String reverse1){
		this.reverse1 = reverse1;
	}


	public String getReverse2(){
		return reverse2;
	}

	public void setReverse2(String reverse2){
		this.reverse2 = reverse2;
	}

	public String toString(){
		return "id:"+id+"\t"+"activityId:"+activityId+"\t"+"memberId:"+memberId+"\t"+"content:"+content+"\t"+"createDate:"+createDate+"\t"+"ifPass:"+ifPass+"\t"+"status:"+status+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2;
	}
}