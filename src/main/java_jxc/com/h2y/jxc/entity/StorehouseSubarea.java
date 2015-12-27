package com.h2y.jxc.entity;

import com.h2y.object.BaseObject;


/**
 * 进销存  仓库分区信息 Model
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */

public class StorehouseSubarea extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyStorehouseSubarea";
    private long id;
    private String subareaName;
    private String subareaSpellCode;
    private long storageId;
    private String notes;

	public StorehouseSubarea(){
		super();
	}

	public StorehouseSubarea(long id){
		super();
		this.id = id;
	}

	public StorehouseSubarea(long id,String subareaName,String subareaSpellCode,long storageId,String notes){
		super();
		this.id = id;
		this.subareaName = subareaName;
		this.subareaSpellCode = subareaSpellCode;
		this.storageId = storageId;
		this.notes = notes;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getSubareaName(){
      return subareaName;
    }
    
    public void setSubareaName(String subareaName){
      this.subareaName = subareaName;
    }


    public String getSubareaSpellCode(){
      return subareaSpellCode;
    }
    
    public void setSubareaSpellCode(String subareaSpellCode){
      this.subareaSpellCode = subareaSpellCode;
    }


    public long getStorageId(){
      return storageId;
    }
    
    public void setStorageId(long storageId){
      this.storageId = storageId;
    }


    public String getNotes(){
      return notes;
    }
    
    public void setNotes(String notes){
      this.notes = notes;
    }

    public String toString(){
	return "id:"+id+"\t"+"subareaName:"+subareaName+"\t"+"subareaSpellCode:"+subareaSpellCode+"\t"+"storageId:"+storageId+"\t"+"notes:"+notes;
    }
}