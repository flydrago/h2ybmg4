package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：促销主题   
 * 作者：侯飞龙
 * 时间：2015年2月7日上午11:11:48
 * 邮件：1162040314@qq.com
 */
public class CommonSubject extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyCommonSubject";
    private long id;
    private String subjectName;
    private int subjectType;
    private String subjectContent;
    private long unitId;
    private String rootPath;
    private String relativePath;
    private String iosFileName;
    private String androidFileName;
    private String wechatFileName;
    private Date createDate;
    private int status;
    private String memo;

	public CommonSubject(){
		super();
	}

	public CommonSubject(long id){
		super();
		this.id = id;
	}

	public CommonSubject(long id,String subjectName,int subjectType,String subjectContent,long unitId,String rootPath,String relativePath,String iosFileName,String androidFileName,String wechatFileName,Date createDate,int status,String memo){
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.subjectType = subjectType;
		this.subjectContent = subjectContent;
		this.unitId = unitId;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.iosFileName = iosFileName;
		this.androidFileName = androidFileName;
		this.wechatFileName = wechatFileName;
		this.createDate = createDate;
		this.status = status;
		this.memo = memo;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getSubjectName(){
      return subjectName;
    }
    
    public void setSubjectName(String subjectName){
      this.subjectName = subjectName;
    }


    public int getSubjectType(){
      return subjectType;
    }
    
    public void setSubjectType(int subjectType){
      this.subjectType = subjectType;
    }


    public String getSubjectContent(){
      return subjectContent;
    }
    
    public void setSubjectContent(String subjectContent){
      this.subjectContent = subjectContent;
    }


    public long getUnitId(){
      return unitId;
    }
    
    public void setUnitId(long unitId){
      this.unitId = unitId;
    }


    public String getRootPath(){
      return rootPath;
    }
    
    public void setRootPath(String rootPath){
      this.rootPath = rootPath;
    }


    public String getRelativePath(){
      return relativePath;
    }
    
    public void setRelativePath(String relativePath){
      this.relativePath = relativePath;
    }


    public String getIosFileName(){
      return iosFileName;
    }
    
    public void setIosFileName(String iosFileName){
      this.iosFileName = iosFileName;
    }


    public String getAndroidFileName(){
      return androidFileName;
    }
    
    public void setAndroidFileName(String androidFileName){
      this.androidFileName = androidFileName;
    }


    public String getWechatFileName(){
      return wechatFileName;
    }
    
    public void setWechatFileName(String wechatFileName){
      this.wechatFileName = wechatFileName;
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


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }

    public String toString(){
	return "id:"+id+"\t"+"subjectName:"+subjectName+"\t"+"subjectType:"+subjectType+"\t"+"subjectContent:"+subjectContent+"\t"+"unitId:"+unitId+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"iosFileName:"+iosFileName+"\t"+"androidFileName:"+androidFileName+"\t"+"wechatFileName:"+wechatFileName+"\t"+"createDate:"+createDate+"\t"+"status:"+status+"\t"+"memo:"+memo;
    }
}