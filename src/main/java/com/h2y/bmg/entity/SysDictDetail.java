package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysDictDetail Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-26
 * email:info@hwttnet.com
 */

public class SysDictDetail extends BaseObject{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysDictDetail";
    private long id;
    private long unitId;
    private long dictMainId;
    private String code;
    private String value;
    private String memo;
    private long ord;

	public SysDictDetail(){
		super();
	}

	public SysDictDetail(long id){
		super();
		this.id = id;
	}

	public SysDictDetail(long id,long unitId,long dictMainId,String code,String value,String memo,long ord){
		super();
		this.id = id;
		this.unitId = unitId;
		this.dictMainId = dictMainId;
		this.code = code;
		this.value = value;
		this.memo = memo;
		this.ord = ord;
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


    public long getDictMainId(){
      return dictMainId;
    }
    
    public void setDictMainId(long dictMainId){
      this.dictMainId = dictMainId;
    }


    public String getCode(){
      return code;
    }
    
    public void setCode(String code){
      this.code = code;
    }


    public String getValue(){
      return value;
    }
    
    public void setValue(String value){
      this.value = value;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public long getOrd(){
      return ord;
    }
    
    public void setOrd(long ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"dictMainId:"+dictMainId+"\t"+"code:"+code+"\t"+"value:"+value+"\t"+"memo:"+memo+"\t"+"ord:"+ord;
    }
}