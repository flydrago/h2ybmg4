package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysParamType Model create
 * @author hwttnet
 * version:1.2
 * email:info@hwttnet.com
 */

public class SysParamType extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysParamType";
    private long id;
    private String typeCode;
    private String typeName;
    private String memo;
    private long ord;

	public SysParamType(){
		super();
	}

	public SysParamType(long id){
		super();
		this.id = id;
	}

	public SysParamType(long id,String typeCode,String typeName,String memo,long ord){
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.memo = memo;
		this.ord = ord;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getTypeCode(){
      return typeCode;
    }
    
    public void setTypeCode(String typeCode){
      this.typeCode = typeCode;
    }


    public String getTypeName(){
      return typeName;
    }
    
    public void setTypeName(String typeName){
      this.typeName = typeName;
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
	return "id:"+id+"\t"+"typeCode:"+typeCode+"\t"+"typeName:"+typeName+"\t"+"memo:"+memo+"\t"+"ord:"+ord;
    }
}