package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysButton Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-16
 * email:info@hwttnet.com
 */

public class SysButton extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysButton";
    private long id;
    private long menuId;
    private String buttonName;
    private String buttonUrl;
    private String buttonIcon;
    private String buttonClick;
    private long buttonOrd;
    private long ifVisible;
    private long ifPublic;
    private long ifSys;

	public SysButton(){
		super();
	}

	public SysButton(long id){
		super();
		this.id = id;
	}

	public SysButton(long id,long menuId,String buttonName,String buttonUrl,String buttonIcon,String buttonClick,long buttonOrd,long ifVisible,long ifPublic,long ifSys){
		super();
		this.id = id;
		this.menuId = menuId;
		this.buttonName = buttonName;
		this.buttonUrl = buttonUrl;
		this.buttonIcon = buttonIcon;
        this.buttonClick = buttonClick;
		this.buttonOrd = buttonOrd;
		this.ifVisible = ifVisible;
		this.ifPublic = ifPublic;
		this.ifSys = ifSys;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getMenuId(){
      return menuId;
    }
    
    public void setMenuId(long menuId){
      this.menuId = menuId;
    }


    public String getButtonName(){
      return buttonName;
    }
    
    public void setButtonName(String buttonName){
      this.buttonName = buttonName;
    }


    public String getButtonUrl(){
      return buttonUrl;
    }
    
    public void setButtonUrl(String buttonUrl){
      this.buttonUrl = buttonUrl;
    }


    public String getButtonIcon(){
      return buttonIcon;
    }
    
    public void setButtonIcon(String buttonIcon){
      this.buttonIcon = buttonIcon;
    }

    public String getButtonClick(){
        return buttonClick;
    }

    public void setButtonClick(String buttonClick){
        this.buttonClick = buttonClick;
    }


    public long getButtonOrd(){
      return buttonOrd;
    }
    
    public void setButtonOrd(long buttonOrd){
      this.buttonOrd = buttonOrd;
    }


    public long getIfVisible(){
      return ifVisible;
    }
    
    public void setIfVisible(long ifVisible){
      this.ifVisible = ifVisible;
    }


    public long getIfPublic(){
      return ifPublic;
    }
    
    public void setIfPublic(long ifPublic){
      this.ifPublic = ifPublic;
    }


    public long getIfSys(){
      return ifSys;
    }
    
    public void setIfSys(long ifSys){
      this.ifSys = ifSys;
    }

    public String toString(){
	return "id:"+id+"\t"+"menuId:"+menuId+"\t"+"buttonName:"+buttonName+"\t"+"buttonUrl:"+buttonUrl+"\t"+"buttonIcon:"+buttonIcon+"\t"+"buttonClick:"+buttonClick+"\t"+"buttonOrd:"+buttonOrd+"\t"+"ifVisible:"+ifVisible+"\t"+"ifPublic:"+ifPublic+"\t"+"ifSys:"+ifSys;
    }
}