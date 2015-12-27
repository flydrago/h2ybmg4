package com.h2y.bmg.entity;

import java.util.Date;

/**
 * 

* @ClassName: VoteCandidate

* @Description: 投票候选人

* @author 李剑

* @date 2015年8月31日 下午4:12:59
 */

public class VoteCandidate{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteCandidate";
    private long id;
    private long unitId;
    private long subjectId;
    private long memberId;
    private String memberAccount;
    private long userNo;
    private String userName;
    private String userPhone;
    private long userVotes;
    private long visitTimes;
    private Date createDate;
    private Date updateDate;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private int status;

	public VoteCandidate(){
		super();
	}

	public VoteCandidate(long id){
		super();
		this.id = id;
	}

	public VoteCandidate(long id,long unitId,long subjectId,long memberId,String memberAccount,long userNo,String userName,String userPhone,long userVotes,long visitTimes,Date createDate,Date updateDate,String reserve1,String reserve2,String reserve3,int ifDelete, int status){
		super();
		this.id = id;
		this.unitId = unitId;
		this.subjectId = subjectId;
		this.memberId = memberId;
		this.memberAccount = memberAccount;
		this.userNo = userNo;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userVotes = userVotes;
		this.visitTimes = visitTimes;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.reserve3 = reserve3;
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


    public long getSubjectId(){
      return subjectId;
    }
    
    public void setSubjectId(long subjectId){
      this.subjectId = subjectId;
    }


    public long getMemberId(){
      return memberId;
    }
    
    public void setMemberId(long memberId){
      this.memberId = memberId;
    }


    public String getMemberAccount(){
      return memberAccount;
    }
    
    public void setMemberAccount(String memberAccount){
      this.memberAccount = memberAccount;
    }


    public long getUserNo(){
      return userNo;
    }
    
    public void setUserNo(long userNo){
      this.userNo = userNo;
    }


    public String getUserName(){
      return userName;
    }
    
    public void setUserName(String userName){
      this.userName = userName;
    }


    public String getUserPhone(){
      return userPhone;
    }
    
    public void setUserPhone(String userPhone){
      this.userPhone = userPhone;
    }


    public long getUserVotes(){
      return userVotes;
    }
    
    public void setUserVotes(long userVotes){
      this.userVotes = userVotes;
    }


    public long getVisitTimes(){
      return visitTimes;
    }
    
    public void setVisitTimes(long visitTimes){
      this.visitTimes = visitTimes;
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


    public String getReserve1(){
      return reserve1;
    }
    
    public void setReserve1(String reserve1){
      this.reserve1 = reserve1;
    }


    public String getReserve2(){
      return reserve2;
    }
    
    public void setReserve2(String reserve2){
      this.reserve2 = reserve2;
    }


    public String getReserve3(){
      return reserve3;
    }
    
    public void setReserve3(String reserve3){
      this.reserve3 = reserve3;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"subjectId:"+subjectId+"\t"+"memberId:"+memberId+"\t"+"memberAccount:"+memberAccount+"\t"+"userNo:"+userNo+"\t"+"userName:"+userName+"\t"+"userPhone:"+userPhone+"\t"+"userVotes:"+userVotes+"\t"+"visitTimes:"+visitTimes+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"reserve1:"+reserve1+"\t"+"reserve2:"+reserve2+"\t"+"reserve3:"+reserve3+"\t"+"status:"+status;
    }
}