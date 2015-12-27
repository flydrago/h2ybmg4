package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubjectInfoRt  
 * 类描述：  广告主题信息关联
 * 创建人：侯飞龙  
 * 创建时间：2015年10月10日 下午2:07:15  
 * 修改人：侯飞龙
 * 修改时间：2015年10月10日 下午2:07:15  
 * 修改备注：  
 * @version
 */
public class AdvertSubjectInfoRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyAdvertSubjectInfoRt";
    private long id;
    private long subjectId;
    private String dataType;
    private String data1;
    private String data2;
    private String data3;
    private Date createDate;
    private int status;
    private int ord;

	public AdvertSubjectInfoRt(){
		super();
	}

	public AdvertSubjectInfoRt(long id){
		super();
		this.id = id;
	}

	public AdvertSubjectInfoRt(long id,long subjectId,String dataType,String data1,String data2,String data3,Date createDate,int status,int ord){
		super();
		this.id = id;
		this.subjectId = subjectId;
		this.dataType = dataType;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.createDate = createDate;
		this.status = status;
		this.ord = ord;
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


    public String getDataType(){
      return dataType;
    }
    
    public void setDataType(String dataType){
      this.dataType = dataType;
    }


    public String getData1(){
      return data1;
    }
    
    public void setData1(String data1){
      this.data1 = data1;
    }


    public String getData2(){
      return data2;
    }
    
    public void setData2(String data2){
      this.data2 = data2;
    }


    public String getData3(){
      return data3;
    }
    
    public void setData3(String data3){
      this.data3 = data3;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"subjectId:"+subjectId+"\t"+"dataType:"+dataType+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"createDate:"+createDate+"\t"+"status:"+status+"\t"+"ord:"+ord;
    }
}