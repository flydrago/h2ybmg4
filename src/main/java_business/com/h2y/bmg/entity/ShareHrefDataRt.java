package com.h2y.bmg.entity;




/**
 * ShareHrefDataRt Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-18
 * email:info@hwttnet.com
 */

public class ShareHrefDataRt{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyShareHrefDataRt";
    private long id;
    private long hrefId;
    private long dataId;
    private int ord;
    private String str1;
    private String str2;
    private String str3;

	public ShareHrefDataRt(){
		super();
	}

	public ShareHrefDataRt(long id){
		super();
		this.id = id;
	}

	public ShareHrefDataRt(long id,long hrefId,long dataId,int ord,String str1,String str2,String str3){
		super();
		this.id = id;
		this.hrefId = hrefId;
		this.dataId = dataId;
		this.ord = ord;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getHrefId(){
      return hrefId;
    }
    
    public void setHrefId(long hrefId){
      this.hrefId = hrefId;
    }


    public long getDataId(){
      return dataId;
    }
    
    public void setDataId(long dataId){
      this.dataId = dataId;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }


    public String getStr1(){
      return str1;
    }
    
    public void setStr1(String str1){
      this.str1 = str1;
    }


    public String getStr2(){
      return str2;
    }
    
    public void setStr2(String str2){
      this.str2 = str2;
    }


    public String getStr3(){
      return str3;
    }
    
    public void setStr3(String str3){
      this.str3 = str3;
    }

    public String toString(){
	return "id:"+id+"\t"+"hrefId:"+hrefId+"\t"+"dataId:"+dataId+"\t"+"ord:"+ord+"\t"+"str1:"+str1+"\t"+"str2:"+str2+"\t"+"str3:"+str3;
    }
}