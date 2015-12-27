package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：MMsgHis  
 * 类描述：推送消息模型  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月28日 上午9:26:56  
 * 修改人：侯飞龙
 * 修改时间：2015年7月28日 上午9:26:56  
 * 修改备注：  
 * @version
 */
public class MMsgHis extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyMMsgHis";
    private int status;
    private String topic;
    private String title;
    private String describtion;
    private String url;
    private String body;
    private String mfrom;
    private String mto;
    private String channelId;
    private String tag;
    private Date createDate;
    private Date sendDate;
    private int type;
    private int isall;
    private int receiptMark;
    private Date receiptDate;
    private String data1;
    private String data2;
    private String data3;
    private int data4;
    private int data5;
    private String datasourceType;
    private String datasourceId;

	public MMsgHis(){
		super();
	}

	public MMsgHis(int status){
		super();
		this.status = status;
	}

	public MMsgHis(int status,String topic,String title,String describtion,String url,String body,String mfrom,String mto,String channelId,String tag,Date createDate,Date sendDate,int type,int isall,int receiptMark,Date receiptDate,String data1,String data2,String data3,int data4,int data5,String datasourceType,String datasourceId){
		super();
		this.status = status;
		this.topic = topic;
		this.title = title;
		this.describtion = describtion;
		this.url = url;
		this.body = body;
		this.mfrom = mfrom;
		this.mto = mto;
		this.channelId = channelId;
		this.tag = tag;
		this.createDate = createDate;
		this.sendDate = sendDate;
		this.type = type;
		this.isall = isall;
		this.receiptMark = receiptMark;
		this.receiptDate = receiptDate;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.datasourceType = datasourceType;
		this.datasourceId = datasourceId;
	}
  
    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }

    public String getTopic(){
      return topic;
    }
    
    public void setTopic(String topic){
      this.topic = topic;
    }


    public String getTitle(){
      return title;
    }
    
    public void setTitle(String title){
      this.title = title;
    }


    public String getDescribtion(){
      return describtion;
    }
    
    public void setDescribtion(String describtion){
      this.describtion = describtion;
    }


    public String getUrl(){
      return url;
    }
    
    public void setUrl(String url){
      this.url = url;
    }


    public String getBody(){
      return body;
    }
    
    public void setBody(String body){
      this.body = body;
    }


    public String getMfrom(){
      return mfrom;
    }
    
    public void setMfrom(String mfrom){
      this.mfrom = mfrom;
    }


    public String getMto(){
      return mto;
    }
    
    public void setMto(String mto){
      this.mto = mto;
    }


    public String getChannelId(){
      return channelId;
    }
    
    public void setChannelId(String channelId){
      this.channelId = channelId;
    }


    public String getTag(){
      return tag;
    }
    
    public void setTag(String tag){
      this.tag = tag;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Date getSendDate(){
      return sendDate;
    }
    
    public void setSendDate(Date sendDate){
      this.sendDate = sendDate;
    }


    public int getType(){
      return type;
    }
    
    public void setType(int type){
      this.type = type;
    }


    public int getIsall(){
      return isall;
    }
    
    public void setIsall(int isall){
      this.isall = isall;
    }


    public int getReceiptMark(){
      return receiptMark;
    }
    
    public void setReceiptMark(int receiptMark){
      this.receiptMark = receiptMark;
    }


    public Date getReceiptDate(){
      return receiptDate;
    }
    
    public void setReceiptDate(Date receiptDate){
      this.receiptDate = receiptDate;
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


    public int getData4(){
      return data4;
    }
    
    public void setData4(int data4){
      this.data4 = data4;
    }


    public int getData5(){
      return data5;
    }
    
    public void setData5(int data5){
      this.data5 = data5;
    }


    public String getDatasourceType(){
      return datasourceType;
    }
    
    public void setDatasourceType(String datasourceType){
      this.datasourceType = datasourceType;
    }


    public String getDatasourceId(){
      return datasourceId;
    }
    
    public void setDatasourceId(String datasourceId){
      this.datasourceId = datasourceId;
    }

    public String toString(){
	return "status:"+status+"\t"+"topic:"+topic+"\t"+"title:"+title+"\t"+"describtion:"+describtion+"\t"+"url:"+url+"\t"+"body:"+body+"\t"+"mfrom:"+mfrom+"\t"+"mto:"+mto+"\t"+"channelId:"+channelId+"\t"+"tag:"+tag+"\t"+"createDate:"+createDate+"\t"+"sendDate:"+sendDate+"\t"+"type:"+type+"\t"+"isall:"+isall+"\t"+"receiptMark:"+receiptMark+"\t"+"receiptDate:"+receiptDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"datasourceType:"+datasourceType+"\t"+"datasourceId:"+datasourceId;
    }
}