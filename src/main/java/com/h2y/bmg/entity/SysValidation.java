package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysValidation Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-25
 * email:info@hwttnet.com
 */

public class SysValidation extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysValidation";
    private long id;
    private String fieldId;
    private long joinId;
    private String joinType;
    private String ruleType;
    private String ruleTypeValue;
    private String msg;
    private long ifWork;

	public SysValidation(){
		super();
	}

	public SysValidation(long id){
		super();
		this.id = id;
	}

	public SysValidation(long id,String fieldId,long joinId,String joinType,String ruleType,String ruleTypeValue,String msg,long ifWork){
		super();
		this.id = id;
		this.fieldId = fieldId;
		this.joinId = joinId;
		this.joinType = joinType;
		this.ruleType = ruleType;
		this.ruleTypeValue = ruleTypeValue;
		this.msg = msg;
		this.ifWork = ifWork;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getFieldId(){
      return fieldId;
    }
    
    public void setFieldId(String fieldId){
      this.fieldId = fieldId;
    }


    public long getJoinId(){
      return joinId;
    }
    
    public void setJoinId(long joinId){
      this.joinId = joinId;
    }


    public String getJoinType(){
      return joinType;
    }
    
    public void setJoinType(String joinType){
      this.joinType = joinType;
    }


    public String getRuleType(){
      return ruleType;
    }
    
    public void setRuleType(String ruleType){
      this.ruleType = ruleType;
    }


    public String getRuleTypeValue(){
      return ruleTypeValue;
    }
    
    public void setRuleTypeValue(String ruleTypeValue){
      this.ruleTypeValue = ruleTypeValue;
    }


    public String getMsg(){
      return msg;
    }
    
    public void setMsg(String msg){
      this.msg = msg;
    }


    public long getIfWork(){
      return ifWork;
    }
    
    public void setIfWork(long ifWork){
      this.ifWork = ifWork;
    }

    public String toString(){
	return "id:"+id+"\t"+"fieldId:"+fieldId+"\t"+"joinId:"+joinId+"\t"+"joinType:"+joinType+"\t"+"ruleType:"+ruleType+"\t"+"ruleTypeValue:"+ruleTypeValue+"\t"+"msg:"+msg+"\t"+"ifWork:"+ifWork;
    }
}