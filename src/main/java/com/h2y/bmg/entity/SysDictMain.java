package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * 项目名称：h2ybmg2  
 * 类名称：SysDictMain  
 * 类描述：  字典主表（字典主表）
 * 创建人：侯飞龙  
 * 创建时间：2015年3月23日 上午9:20:52  
 * 修改人：侯飞龙
 * 修改时间：2015年3月23日 上午9:20:52  
 * 修改备注：  
 * @version
 */
public class SysDictMain extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysDictMain";
    private long id;
    private long parentId;
    private String dictPrefix;
    private String dictCode;
    private String dictName;
    private String dictType;
    private String dictValue;
    private long dictOrd;
    private long ifSys;
    private long ifUserConf;
    private int isMate;
    private int isExtends;

	public SysDictMain(){
		super();
	}

	public SysDictMain(long id){
		super();
		this.id = id;
	}

	public SysDictMain(long id,long parentId,String dictPrefix,String dictCode,String dictName,String dictType,String dictValue,long dictOrd,long ifSys,long ifUserConf,int isMate,int isExtends){
		super();
		this.id = id;
		this.parentId = parentId;
		this.dictPrefix = dictPrefix;
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.dictOrd = dictOrd;
		this.ifSys = ifSys;
		this.ifUserConf = ifUserConf;
		this.isMate = isMate;
		this.isExtends = isExtends;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getParentId(){
      return parentId;
    }
    
    public void setParentId(long parentId){
      this.parentId = parentId;
    }


    public String getDictPrefix(){
      return dictPrefix;
    }
    
    public void setDictPrefix(String dictPrefix){
      this.dictPrefix = dictPrefix;
    }


    public String getDictCode(){
      return dictCode;
    }
    
    public void setDictCode(String dictCode){
      this.dictCode = dictCode;
    }


    public String getDictName(){
      return dictName;
    }
    
    public void setDictName(String dictName){
      this.dictName = dictName;
    }


    public String getDictType(){
      return dictType;
    }
    
    public void setDictType(String dictType){
      this.dictType = dictType;
    }


    public String getDictValue(){
      return dictValue;
    }
    
    public void setDictValue(String dictValue){
      this.dictValue = dictValue;
    }


    public long getDictOrd(){
      return dictOrd;
    }
    
    public void setDictOrd(long dictOrd){
      this.dictOrd = dictOrd;
    }


    public long getIfSys(){
      return ifSys;
    }
    
    public void setIfSys(long ifSys){
      this.ifSys = ifSys;
    }


    public long getIfUserConf(){
      return ifUserConf;
    }
    
    public void setIfUserConf(long ifUserConf){
      this.ifUserConf = ifUserConf;
    }


    public int getIsMate(){
      return isMate;
    }
    
    public void setIsMate(int isMate){
      this.isMate = isMate;
    }


    public int getIsExtends(){
      return isExtends;
    }
    
    public void setIsExtends(int isExtends){
      this.isExtends = isExtends;
    }

    public String toString(){
	return "id:"+id+"\t"+"parentId:"+parentId+"\t"+"dictPrefix:"+dictPrefix+"\t"+"dictCode:"+dictCode+"\t"+"dictName:"+dictName+"\t"+"dictType:"+dictType+"\t"+"dictValue:"+dictValue+"\t"+"dictOrd:"+dictOrd+"\t"+"ifSys:"+ifSys+"\t"+"ifUserConf:"+ifUserConf+"\t"+"isMate:"+isMate+"\t"+"isExtends:"+isExtends;
    }
}