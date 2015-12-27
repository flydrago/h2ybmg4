package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysQueryItem Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-27
 * email:info@hwttnet.com
 */

public class SysQueryItem extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysQueryItem";
    private long id;
    private long joinId;
    private String joinType;
    private String name;
    private String fieldName;
    private String formName;
    private String inputName;
    private String dataType;
    private String value;
    private String datasourceType;
    private String datasourceValue;
    private String queryType;
    private String operator;
    private long row;
    private long rowspan;
    private long width;
    private long x;
    private long ord;
    private long unitId;

	public SysQueryItem(){
		super();
	}

	public SysQueryItem(long id){
		super();
		this.id = id;
	}

	public SysQueryItem(long id,long joinId,String joinType,String name,String fieldName,String formName,String inputName,String dataType,String value,String datasourceType,String datasourceValue,String queryType,String operator,long row,long rowspan,long width,long x,long ord,long unitId){
		super();
		this.id = id;
		this.joinId = joinId;
		this.joinType = joinType;
		this.name = name;
		this.fieldName = fieldName;
		this.formName = formName;
		this.inputName = inputName;
		this.dataType = dataType;
		this.value = value;
		this.datasourceType = datasourceType;
		this.datasourceValue = datasourceValue;
		this.queryType = queryType;
		this.operator = operator;
		this.row = row;
		this.rowspan = rowspan;
		this.width = width;
		this.x = x;
		this.ord = ord;
		this.unitId = unitId;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
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


    public String getName(){
      return name;
    }
    
    public void setName(String name){
      this.name = name;
    }


    public String getFieldName(){
      return fieldName;
    }
    
    public void setFieldName(String fieldName){
      this.fieldName = fieldName;
    }


    public String getFormName(){
      return formName;
    }
    
    public void setFormName(String formName){
      this.formName = formName;
    }


    public String getInputName(){
      return inputName;
    }
    
    public void setInputName(String inputName){
      this.inputName = inputName;
    }


    public String getDataType(){
      return dataType;
    }
    
    public void setDataType(String dataType){
      this.dataType = dataType;
    }


    public String getValue(){
      return value;
    }
    
    public void setValue(String value){
      this.value = value;
    }


    public String getDatasourceType(){
      return datasourceType;
    }
    
    public void setDatasourceType(String datasourceType){
      this.datasourceType = datasourceType;
    }


    public String getDatasourceValue(){
      return datasourceValue;
    }
    
    public void setDatasourceValue(String datasourceValue){
      this.datasourceValue = datasourceValue;
    }


    public String getQueryType(){
      return queryType;
    }
    
    public void setQueryType(String queryType){
      this.queryType = queryType;
    }


    public String getOperator(){
      return operator;
    }
    
    public void setOperator(String operator){
      this.operator = operator;
    }


    public long getRow(){
      return row;
    }
    
    public void setRow(long row){
      this.row = row;
    }


    public long getRowspan(){
      return rowspan;
    }
    
    public void setRowspan(long rowspan){
      this.rowspan = rowspan;
    }


    public long getWidth(){
      return width;
    }
    
    public void setWidth(long width){
      this.width = width;
    }


    public long getX(){
      return x;
    }
    
    public void setX(long x){
      this.x = x;
    }


    public long getOrd(){
      return ord;
    }
    
    public void setOrd(long ord){
      this.ord = ord;
    }


    public long getunitId(){
      return unitId;
    }
    
    public void setunitId(long unitId){
      this.unitId = unitId;
    }

    public String toString(){
	return "id:"+id+"\t"+"joinId:"+joinId+"\t"+"joinType:"+joinType+"\t"+"name:"+name+"\t"+"fieldName:"+fieldName+"\t"+"formName:"+formName+"\t"+"inputName:"+inputName+"\t"+"dataType:"+dataType+"\t"+"value:"+value+"\t"+"datasourceType:"+datasourceType+"\t"+"datasourceValue:"+datasourceValue+"\t"+"queryType:"+queryType+"\t"+"operator:"+operator+"\t"+"row:"+row+"\t"+"rowspan:"+rowspan+"\t"+"width:"+width+"\t"+"x:"+x+"\t"+"ord:"+ord+"\t"+"unitId:"+unitId;
    }
}