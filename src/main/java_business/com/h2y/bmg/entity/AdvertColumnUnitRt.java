package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnUnitRt  
 * 类描述：广告栏位单位关联  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午10:37:21  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午10:37:21  
 * 修改备注：  
 * @version
 */
public class AdvertColumnUnitRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyAdvertColumnUnitRt";
    private long id;
    private long unitId;
    private long columnId;
    private Date startDate;
    private Date endDate;
    private int status;

	public AdvertColumnUnitRt(){
		super();
	}

	public AdvertColumnUnitRt(long id){
		super();
		this.id = id;
	}

	public AdvertColumnUnitRt(long id,long unitId,long columnId,Date startDate,Date endDate,int status){
		super();
		this.id = id;
		this.unitId = unitId;
		this.columnId = columnId;
		this.startDate = startDate;
		this.endDate = endDate;
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


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"columnId:"+columnId+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"status:"+status;
    }
}