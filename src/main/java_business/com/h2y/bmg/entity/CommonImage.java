package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：CommonImage  
 * 类描述：图片维护  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月8日 下午2:41:45  
 * 修改人：侯飞龙
 * 修改时间：2015年5月8日 下午2:41:45  
 * 修改备注：  
 * @version
 */
public class CommonImage extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyCommonImage";
    private long id;
    private long unitId;
    private String typeCode;
    private String logoName;
    private String rootPath;
    private String relativePath;
    private String iosFileName;
    private String androidFileName;
    private String wechatFileName;
    private Date createDate;
    private Date updateDate;
    private Date startDate;
    private Date endDate;
    private int isDefault;
    private int status;
    private String memo;
    private long ord;

	public CommonImage(){
		super();
	}

	public CommonImage(long id){
		super();
		this.id = id;
	}

	public CommonImage(long id,long unitId,String typeCode,String logoName,String rootPath,String relativePath,String iosFileName,String androidFileName,String wechatFileName,Date createDate,Date updateDate,Date startDate,Date endDate,int isDefault,int status,String memo,long ord){
		super();
		this.id = id;
		this.unitId = unitId;
		this.typeCode = typeCode;
		this.logoName = logoName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.iosFileName = iosFileName;
		this.androidFileName = androidFileName;
		this.wechatFileName = wechatFileName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isDefault = isDefault;
		this.status = status;
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


    public String getTypeCode(){
      return typeCode;
    }
    
    public void setTypeCode(String typeCode){
      this.typeCode = typeCode;
    }


    public String getLogoName(){
      return logoName;
    }
    
    public void setLogoName(String logoName){
      this.logoName = logoName;
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


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
    }


    public Date getStartDate(){
      return startDate;
    }
    
    public void setStartDate(Date startDate){
      this.startDate = startDate;
    }


    public Date getEndDate(){
      return endDate;
    }
    
    public void setEndDate(Date endDate){
      this.endDate = endDate;
    }


    public int getIsDefault(){
      return isDefault;
    }
    
    public void setIsDefault(int isDefault){
      this.isDefault = isDefault;
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


    public long getOrd(){
      return ord;
    }
    
    public void setOrd(long ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"typeCode:"+typeCode+"\t"+"logoName:"+logoName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"iosFileName:"+iosFileName+"\t"+"androidFileName:"+androidFileName+"\t"+"wechatFileName:"+wechatFileName+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"isDefault:"+isDefault+"\t"+"status:"+status+"\t"+"memo:"+memo+"\t"+"ord:"+ord;
    }
}