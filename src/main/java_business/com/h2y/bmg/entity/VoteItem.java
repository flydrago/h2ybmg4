package com.h2y.bmg.entity;

import java.util.Date;

/**
 * 

* @ClassName: VoteItem

* @Description: 候选人投票记录模型

* @author 李剑

* @date 2015年9月1日 上午9:05:48
 */

public class VoteItem{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteItem";
    private long id;
    private long unitId;
    private long subjectId;
    private long memberId;
    private String memberAccount;
    private long candidateId;
    private Date createDate;
    private String reserve1;
    private String reserve2;
    private String reserve3;

	public VoteItem(){
		super();
	}

	public VoteItem(long id){
		super();
		this.id = id;
	}

	public VoteItem(long id,long unitId,long subjectId,long memberId,String memberAccount,long candidateId,Date createDate,String reserve1,String reserve2,String reserve3){
		super();
		this.id = id;
		this.unitId = unitId;
		this.subjectId = subjectId;
		this.memberId = memberId;
		this.memberAccount = memberAccount;
		this.candidateId = candidateId;
		this.createDate = createDate;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.reserve3 = reserve3;
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


    public long getCandidateId(){
      return candidateId;
    }
    
    public void setCandidateId(long candidateId){
      this.candidateId = candidateId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
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

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"subjectId:"+subjectId+"\t"+"memberId:"+memberId+"\t"+"memberAccount:"+memberAccount+"\t"+"candidateId:"+candidateId+"\t"+"createDate:"+createDate+"\t"+"reserve1:"+reserve1+"\t"+"reserve2:"+reserve2+"\t"+"reserve3:"+reserve3;
    }
}