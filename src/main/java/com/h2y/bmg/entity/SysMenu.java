package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysMenu Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-13
 * email:info@hwttnet.com
 */
public class SysMenu extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysMenu";
    private long id;
    private long parentId;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private long menuOrd;
    private long ifVisible;
    private long ifMain;
    private long ifGrid;
    private long ifQuery;
    private long ifValidate;
    private long ifSys;

	public SysMenu(){
		super();
	}

	public SysMenu(long id){
		super();
		this.id = id;
	}

	public SysMenu(long id,long parentId,String menuName,String menuUrl,String menuIcon,long menuOrd,long ifVisible,long ifMain,long ifGrid,long ifQuery,long ifValidate,long ifSys){
		super();
		this.id = id;
		this.parentId = parentId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuIcon = menuIcon;
		this.menuOrd = menuOrd;
		this.ifVisible = ifVisible;
		this.ifMain = ifMain;
		this.ifGrid = ifGrid;
		this.ifQuery = ifQuery;
		this.ifValidate = ifValidate;
		this.ifSys = ifSys;
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


    public String getMenuName(){
      return menuName;
    }
    
    public void setMenuName(String menuName){
      this.menuName = menuName;
    }


    public String getMenuUrl(){
      return menuUrl;
    }
    
    public void setMenuUrl(String menuUrl){
      this.menuUrl = menuUrl;
    }


    public String getMenuIcon(){
      return menuIcon;
    }
    
    public void setMenuIcon(String menuIcon){
      this.menuIcon = menuIcon;
    }


    public long getMenuOrd(){
      return menuOrd;
    }
    
    public void setMenuOrd(long menuOrd){
      this.menuOrd = menuOrd;
    }


    public long getIfVisible(){
      return ifVisible;
    }
    
    public void setIfVisible(long ifVisible){
      this.ifVisible = ifVisible;
    }


    public long getIfMain(){
      return ifMain;
    }
    
    public void setIfMain(long ifMain){
      this.ifMain = ifMain;
    }


    public long getIfGrid(){
      return ifGrid;
    }
    
    public void setIfGrid(long ifGrid){
      this.ifGrid = ifGrid;
    }


    public long getIfQuery(){
      return ifQuery;
    }
    
    public void setIfQuery(long ifQuery){
      this.ifQuery = ifQuery;
    }


    public long getIfValidate(){
      return ifValidate;
    }
    
    public void setIfValidate(long ifValidate){
      this.ifValidate = ifValidate;
    }


    public long getIfSys(){
      return ifSys;
    }
    
    public void setIfSys(long ifSys){
      this.ifSys = ifSys;
    }

    public String toString(){
	return "id:"+id+"\t"+"parentId:"+parentId+"\t"+"menuName:"+menuName+"\t"+"menuUrl:"+menuUrl+"\t"+"menuIcon:"+menuIcon+"\t"+"menuOrd:"+menuOrd+"\t"+"ifVisible:"+ifVisible+"\t"+"ifMain:"+ifMain+"\t"+"ifGrid:"+ifGrid+"\t"+"ifQuery:"+ifQuery+"\t"+"ifValidate:"+ifValidate+"\t"+"ifSys:"+ifSys;
    }
}