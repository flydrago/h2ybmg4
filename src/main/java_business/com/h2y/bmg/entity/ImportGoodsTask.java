package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ImportGoodsTask  
 * 类描述：导入商品任务
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:36:38  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:36:38  
 * 修改备注：  
 * @version
 */
public class ImportGoodsTask extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyImportGoodsTask";
    private long id;
    private String bagCode;
    private long dataId;
    private int dataType;
    private long userId;
    private String taskType;
    private String taskValue;
    private String resultCode;
    private String resultValue;
    private Date createDate;
    private String memo;

	public ImportGoodsTask(){
		super();
	}

	public ImportGoodsTask(long id){
		super();
		this.id = id;
	}

	public ImportGoodsTask(long id,String bagCode,long dataId,int dataType,long userId,String taskType,String taskValue,String resultCode,String resultValue,Date createDate,String memo){
		super();
		this.id = id;
		this.bagCode = bagCode;
		this.dataId = dataId;
		this.dataType = dataType;
		this.userId = userId;
		this.taskType = taskType;
		this.taskValue = taskValue;
		this.resultCode = resultCode;
		this.resultValue = resultValue;
		this.createDate = createDate;
		this.memo = memo;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getBagCode(){
      return bagCode;
    }
    
    public void setBagCode(String bagCode){
      this.bagCode = bagCode;
    }


    public long getDataId(){
      return dataId;
    }
    
    public void setDataId(long dataId){
      this.dataId = dataId;
    }


    public int getDataType(){
      return dataType;
    }
    
    public void setDataType(int dataType){
      this.dataType = dataType;
    }


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public String getTaskType(){
      return taskType;
    }
    
    public void setTaskType(String taskType){
      this.taskType = taskType;
    }


    public String getTaskValue(){
      return taskValue;
    }
    
    public void setTaskValue(String taskValue){
      this.taskValue = taskValue;
    }


    public String getResultCode(){
      return resultCode;
    }
    
    public void setResultCode(String resultCode){
      this.resultCode = resultCode;
    }


    public String getResultValue(){
      return resultValue;
    }
    
    public void setResultValue(String resultValue){
      this.resultValue = resultValue;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }

    public String toString(){
	return "id:"+id+"\t"+"bagCode:"+bagCode+"\t"+"dataId:"+dataId+"\t"+"dataType:"+dataType+"\t"+"userId:"+userId+"\t"+"taskType:"+taskType+"\t"+"taskValue:"+taskValue+"\t"+"resultCode:"+resultCode+"\t"+"resultValue:"+resultValue+"\t"+"createDate:"+createDate+"\t"+"memo:"+memo;
    }
}