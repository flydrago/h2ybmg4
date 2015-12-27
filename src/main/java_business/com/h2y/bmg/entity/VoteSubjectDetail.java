package com.h2y.bmg.entity;


/**
 * 

* @ClassName: VoteSubjectDetail

* @Description: 投票主题详细

* @author 李剑

* @date 2015年8月31日 下午4:14:09
 */

public class VoteSubjectDetail{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteSubjectDetail";
    private long id;
    private long subjectId;
    private String subjectRule;
    private String text1;
    private String text2;

	public VoteSubjectDetail(){
		super();
	}

	public VoteSubjectDetail(long id){
		super();
		this.id = id;
	}

	public VoteSubjectDetail(long id,long subjectId,String subjectRule,String text1,String text2){
		super();
		this.id = id;
		this.subjectId = subjectId;
		this.subjectRule = subjectRule;
		this.text1 = text1;
		this.text2 = text2;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getSubjectId(){
      return subjectId;
    }
    
    public void setSubjectId(long subjectId){
      this.subjectId = subjectId;
    }


    public String getSubjectRule(){
      return subjectRule;
    }
    
    public void setSubjectRule(String subjectRule){
      this.subjectRule = subjectRule;
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
	return "id:"+id+"\t"+"subjectId:"+subjectId+"\t"+"subjectRule:"+subjectRule+"\t"+"text1:"+text1+"\t"+"text2:"+text2;
    }
}