package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysParam Model create
 * @author hwttnet
 * version:1.2
 * time:2014-12-05
 * email:info@hwttnet.com
 */

public class SysParam extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysParam";
    private long id;
    private long unitId;
    private long typeId;
    private String paramsCode;
    private String paramsValue;
    private String memo;
    private long ord;

	public SysParam(){
		super();
	}

	public SysParam(long id){
		super();
		this.id = id;
	}

	public SysParam(long id,long unitId,long typeId,String paramsCode,String paramsValue,String memo,long ord){
		super();
		this.id = id;
		this.unitId = unitId;
		this.typeId = typeId;
		this.paramsCode = paramsCode;
		this.paramsValue = paramsValue;
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


    public long getTypeId(){
      return typeId;
    }
    
    public void setTypeId(long typeId){
      this.typeId = typeId;
    }


    public String getParamsCode(){
      return paramsCode;
    }
    
    public void setParamsCode(String paramsCode){
      this.paramsCode = paramsCode;
    }


    public String getParamsValue(){
      return paramsValue;
    }
    
    public void setParamsValue(String paramsValue){
      this.paramsValue = paramsValue;
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
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"typeId:"+typeId+"\t"+"paramsCode:"+paramsCode+"\t"+"paramsValue:"+paramsValue+"\t"+"memo:"+memo+"\t"+"ord:"+ord;
    }
}