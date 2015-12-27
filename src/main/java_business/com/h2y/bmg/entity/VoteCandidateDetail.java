package com.h2y.bmg.entity;


/**
 * 

* @ClassName: VoteCandidateDetail

* @Description: 投票候选人详细

* @author 李剑

* @date 2015年8月31日 下午4:13:33
 */

public class VoteCandidateDetail{

	/**
	 * @Fields serialVersionUID : 候选人详细
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteCandidateDetail";
    private long id;
    private long candidateId;
    private String candidaterDesc;
    private String reserve1;
    private String reserve2;
    private String reserve3;

	public VoteCandidateDetail(){
		super();
	}

	public VoteCandidateDetail(long id){
		super();
		this.id = id;
	}

	public VoteCandidateDetail(long id,long candidateId,String candidaterDesc,String reserve1,String reserve2,String reserve3){
		super();
		this.id = id;
		this.candidateId = candidateId;
		this.candidaterDesc = candidaterDesc;
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

    public long getCandidateId(){
      return candidateId;
    }
    
    public void setCandidateId(long candidateId){
      this.candidateId = candidateId;
    }


    public String getCandidaterDesc(){
      return candidaterDesc;
    }
    
    public void setCandidaterDesc(String candidaterDesc){
      this.candidaterDesc = candidaterDesc;
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
	return "id:"+id+"\t"+"candidateId:"+candidateId+"\t"+"candidaterDesc:"+candidaterDesc+"\t"+"reserve1:"+reserve1+"\t"+"reserve2:"+reserve2+"\t"+"reserve3:"+reserve3;
    }
}