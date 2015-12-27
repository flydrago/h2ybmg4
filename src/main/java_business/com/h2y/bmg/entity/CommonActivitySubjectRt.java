package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * 类描述：活动主题关联
 * 作者：侯飞龙
 * 时间：2015年2月7日上午11:41:49
 * 邮件：1162040314@qq.com
 */
public class CommonActivitySubjectRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyCommonActivitySubject";
    private long id;
    private long activityId;
    private long subjectId;
    private int ord;

	public CommonActivitySubjectRt(){
		super();
	}

	public CommonActivitySubjectRt(long id){
		super();
		this.id = id;
	}

	public CommonActivitySubjectRt(long id,long activityId,long subjectId,int ord){
		super();
		this.id = id;
		this.activityId = activityId;
		this.subjectId = subjectId;
		this.ord = ord;
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


    public long getSubjectId(){
      return subjectId;
    }
    
    public void setSubjectId(long subjectId){
      this.subjectId = subjectId;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"activityId:"+activityId+"\t"+"subjectId:"+subjectId+"\t"+"ord:"+ord;
    }
}