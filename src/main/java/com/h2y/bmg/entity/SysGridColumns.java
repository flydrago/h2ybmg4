package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * 表格列
 */
public class SysGridColumns extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysGridColumns";
    private long id;
    private long joinId;
    private String title;
    private String name;
    private String width;
    private String align;
    private String dataType;
    private String editorType;
    private String render;
    private long isSort;
    private String joinType;
    private long ord;
    private long ifWidth;
    private long ifVisible;

	public SysGridColumns(){
		super();
	}

	public SysGridColumns(long id){
		super();
		this.id = id;
	}

	public SysGridColumns(long id,long joinId,String title,String name,String width,String align,String dataType,String editorType,String render,long isSort,String joinType,long ord,long ifWidth,long ifVisible){
		super();
		this.id = id;
		this.joinId = joinId;
		this.title = title;
		this.name = name;
		this.width = width;
		this.align = align;
		this.dataType = dataType;
		this.editorType = editorType;
		this.render = render;
		this.isSort = isSort;
		this.joinType = joinType;
		this.ord = ord;
		this.ifWidth = ifWidth;
		this.ifVisible = ifVisible;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getJoinId(){
      return joinId;
    }
    
    public void setJoinId(long joinId){
      this.joinId = joinId;
    }


    public String getTitle(){
      return title;
    }
    
    public void setTitle(String title){
      this.title = title;
    }


    public String getName(){
      return name;
    }
    
    public void setName(String name){
      this.name = name;
    }


    public String getWidth(){
      return width;
    }
    
    public void setWidth(String width){
      this.width = width;
    }


    public String getAlign(){
      return align;
    }
    
    public void setAlign(String align){
      this.align = align;
    }


    public String getDataType(){
      return dataType;
    }
    
    public void setDataType(String dataType){
      this.dataType = dataType;
    }


    public String getEditorType(){
      return editorType;
    }
    
    public void setEditorType(String editorType){
      this.editorType = editorType;
    }


    public String getRender(){
      return render;
    }
    
    public void setRender(String render){
      this.render = render;
    }


    public long getIsSort(){
      return isSort;
    }
    
    public void setIsSort(long isSort){
      this.isSort = isSort;
    }


    public String getJoinType(){
      return joinType;
    }
    
    public void setJoinType(String joinType){
      this.joinType = joinType;
    }


    public long getOrd(){
      return ord;
    }
    
    public void setOrd(long ord){
      this.ord = ord;
    }


    public long getIfWidth(){
      return ifWidth;
    }
    
    public void setIfWidth(long ifWidth){
      this.ifWidth = ifWidth;
    }


    public long getIfVisible(){
      return ifVisible;
    }
    
    public void setIfVisible(long ifVisible){
      this.ifVisible = ifVisible;
    }

    public String toString(){
	return "id:"+id+"\t"+"joinId:"+joinId+"\t"+"title:"+title+"\t"+"name:"+name+"\t"+"width:"+width+"\t"+"align:"+align+"\t"+"dataType:"+dataType+"\t"+"editorType:"+editorType+"\t"+"render:"+render+"\t"+"isSort:"+isSort+"\t"+"joinType:"+joinType+"\t"+"ord:"+ord+"\t"+"ifWidth:"+ifWidth+"\t"+"ifVisible:"+ifVisible;
    }
}