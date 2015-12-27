package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumn  
 * 类描述：广告栏位  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午9:17:56  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午9:17:56  
 * 修改备注：  
 * @version
 */
public class AdvertColumn extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyAdvertColumn";
    private long id;
    private String columnName;
    private int columnType;
    private int ord;
    private Date createDate;
    private String memo;
    private int status;

	public AdvertColumn(){
		super();
	}

	public AdvertColumn(long id){
		super();
		this.id = id;
	}

	public AdvertColumn(long id,String columnName,int columnType,int ord,Date createDate,String memo,int status){
		super();
		this.id = id;
		this.columnName = columnName;
		this.columnType = columnType;
		this.ord = ord;
		this.createDate = createDate;
		this.memo = memo;
		this.status = status;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getColumnName(){
      return columnName;
    }
    
    public void setColumnName(String columnName){
      this.columnName = columnName;
    }


    public int getColumnType(){
      return columnType;
    }
    
    public void setColumnType(int columnType){
      this.columnType = columnType;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
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


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }

    public String toString(){
	return "id:"+id+"\t"+"columnName:"+columnName+"\t"+"columnType:"+columnType+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"memo:"+memo+"\t"+"status:"+status;
    }
}