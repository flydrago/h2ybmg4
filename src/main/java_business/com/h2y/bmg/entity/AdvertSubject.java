package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubject  
 * 类描述：广告主题模型  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:31:36  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:31:36  
 * 修改备注：  
 * @version
 */
public class AdvertSubject extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyAdvertSubject";
    private long id;
    private long unitId;
    private long userId;
    private Date createDate;
    private Date updateDate;
    private String rootPath;
    private String relativePath;
    private String iosFileName;
    private String androidFileName;
    private String wechatFileName;
    private String subjectName;
    private int subjectType;
    private String subjectContent;
    private String memo;
    private int status;

	public AdvertSubject(){
		super();
	}

	public AdvertSubject(long id){
		super();
		this.id = id;
	}

	public AdvertSubject(long id,long unitId,long userId,Date createDate,Date updateDate,String rootPath,String relativePath,String iosFileName,String androidFileName,String wechatFileName,String subjectName,int subjectType,String subjectContent,String memo,int status){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.iosFileName = iosFileName;
		this.androidFileName = androidFileName;
		this.wechatFileName = wechatFileName;
		this.subjectName = subjectName;
		this.subjectType = subjectType;
		this.subjectContent = subjectContent;
		this.memo = memo;
		this.status = status;
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


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
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


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userId:"+userId+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"iosFileName:"+iosFileName+"\t"+"androidFileName:"+androidFileName+"\t"+"wechatFileName:"+wechatFileName+"\t"+"subjectName:"+subjectName+"\t"+"subjectType:"+subjectType+"\t"+"subjectContent:"+subjectContent+"\t"+"memo:"+memo+"\t"+"status:"+status;
    }
}