package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnSubjectRt  
 * 类描述：广告栏位主题关联模型  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月9日 上午9:46:10  
 * 修改人：侯飞龙
 * 修改时间：2015年4月9日 上午9:46:10  
 * 修改备注：  
 * @version
 */
public class AdvertColumnSubjectRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyAdvertColumnSubjectRt";
    private long id;
    private long unitId;
    private long columnId;
    private long userId;
    private long subjectId;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private String repeatStart;
    private String repeatEnd;
    private String repeatType;
    private String memo;
    private int isDefault;
    private int status;

	public AdvertColumnSubjectRt(){
		super();
	}

	public AdvertColumnSubjectRt(long id){
		super();
		this.id = id;
	}

	public AdvertColumnSubjectRt(long id,long unitId,long columnId,long userId,long subjectId,Date createDate,Date startDate,Date endDate,String repeatStart,String repeatEnd,String repeatType,String memo,int isDefault,int status){
		super();
		this.id = id;
		this.unitId = unitId;
		this.columnId = columnId;
		this.userId = userId;
		this.subjectId = subjectId;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.repeatStart = repeatStart;
		this.repeatEnd = repeatEnd;
		this.repeatType = repeatType;
		this.memo = memo;
		this.isDefault = isDefault;
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


    public long getColumnId(){
      return columnId;
    }
    
    public void setColumnId(long columnId){
      this.columnId = columnId;
    }


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public long getSubjectId(){
      return subjectId;
    }
    
    public void setSubjectId(long subjectId){
      this.subjectId = subjectId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
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


    public String getRepeatStart(){
      return repeatStart;
    }
    
    public void setRepeatStart(String repeatStart){
      this.repeatStart = repeatStart;
    }


    public String getRepeatEnd(){
      return repeatEnd;
    }
    
    public void setRepeatEnd(String repeatEnd){
      this.repeatEnd = repeatEnd;
    }


    public String getRepeatType(){
      return repeatType;
    }
    
    public void setRepeatType(String repeatType){
      this.repeatType = repeatType;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
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

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"columnId:"+columnId+"\t"+"userId:"+userId+"\t"+"subjectId:"+subjectId+"\t"+"createDate:"+createDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"repeatStart:"+repeatStart+"\t"+"repeatEnd:"+repeatEnd+"\t"+"repeatType:"+repeatType+"\t"+"memo:"+memo+"\t"+"isDefault:"+isDefault+"\t"+"status:"+status;
    }
}