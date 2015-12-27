package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * Zone Model create
 * @author hwttnet
 * version:1.2
 * time:2014-11-04
 * email:info@hwttnet.com
 */

public class Zone extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyZone";
    private long id;
    private String code;
    private String name;
    private String pid;
    private String pcode;
    private String preFix;
    private String level;
    private String isLast;
    private Double longitude;
    private Double latitude;
    private String location;
    private String spellName;
    private String firSpellName;

	public Zone(){
		super();
	}

	public Zone(long id){
		super();
		this.id = id;
	}

	public Zone(long id,String code,String name,String pid,String pcode,String preFix,String level,String isLast,Double longitude,Double latitude,String location,String spellName,String firSpellName){
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.pid = pid;
		this.pcode = pcode;
		this.preFix = preFix;
		this.level = level;
		this.isLast = isLast;
		this.longitude = longitude;
		this.latitude = latitude;
		this.location = location;
		this.spellName=spellName;
		this.firSpellName=firSpellName;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getCode(){
      return code;
    }
    
    public void setCode(String code){
      this.code = code;
    }


    public String getName(){
      return name;
    }
    
    public void setName(String name){
      this.name = name;
    }


    public String getPid(){
      return pid;
    }
    
    public void setPid(String pid){
      this.pid = pid;
    }


    public String getPcode(){
      return pcode;
    }
    
    public void setPcode(String pcode){
      this.pcode = pcode;
    }


    public String getPreFix(){
      return preFix;
    }
    
    public void setPreFix(String preFix){
      this.preFix = preFix;
    }


    public String getLevel(){
      return level;
    }
    
    public void setLevel(String level){
      this.level = level;
    }


    public String getIsLast(){
      return isLast;
    }
    
    public void setIsLast(String isLast){
      this.isLast = isLast;
    }


    public Double getLongitude(){
      return longitude;
    }
    
    public void setLongitude(Double longitude){
      this.longitude = longitude;
    }


    public Double getLatitude(){
      return latitude;
    }
    
    public void setLatitude(Double latitude){
      this.latitude = latitude;
    }


    public String getLocation(){
      return location;
    }
    
    public void setLocation(String location){
      this.location = location;
    }
    

    public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public String getFirSpellName() {
		return firSpellName;
	}

	public void setFirSpellName(String firSpellName) {
		this.firSpellName = firSpellName;
	}

	public String toString(){
	return "id:"+id+"\t"+"code:"+code+"\t"+"name:"+name+"\t"+"pid:"+pid+"\t"+"pcode:"+pcode+"\t"+"preFix:"+preFix+"\t"+"level:"+level+"\t"+"isLast:"+isLast+"\t"+"longitude:"+longitude+"\t"+"latitude:"+latitude+"\t"+"location:"+location;
    }
}