package com.h2y.bmg.entity;




/**
 * PromoteActivityDetail Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-13
 * email:info@hwttnet.com
 */

public class PromoteActivityDetail{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyPromoteActivityDetail";
    private long id;
    private long promoteId;
    private String promoteRule;
    private String text1;
    private String text2;

	public PromoteActivityDetail(){
		super();
	}

	public PromoteActivityDetail(long id){
		super();
		this.id = id;
	}

	public PromoteActivityDetail(long id,long promoteId,String promoteRule,String text1,String text2){
		super();
		this.id = id;
		this.promoteId = promoteId;
		this.promoteRule = promoteRule;
		this.text1 = text1;
		this.text2 = text2;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getPromoteId(){
      return promoteId;
    }
    
    public void setPromoteId(long promoteId){
      this.promoteId = promoteId;
    }


    public String getPromoteRule(){
      return promoteRule;
    }
    
    public void setPromoteRule(String promoteRule){
      this.promoteRule = promoteRule;
    }


    public String getText1(){
      return text1;
    }
    
    public void setText1(String text1){
      this.text1 = text1;
    }


    public String getText2(){
      return text2;
    }
    
    public void setText2(String text2){
      this.text2 = text2;
    }

    public String toString(){
	return "id:"+id+"\t"+"promoteId:"+promoteId+"\t"+"promoteRule:"+promoteRule+"\t"+"text1:"+text1+"\t"+"text2:"+text2;
    }
}