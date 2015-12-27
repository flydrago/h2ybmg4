package com.h2y.bmg.entity;

import java.io.Serializable;

public class ListItem implements Serializable {
     
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;
	private String value;
     public String getValue() {
		return value;
	}
     
     public ListItem(){};
     public ListItem(String value,String text){
    	 this.value=value;
    	 this.text=text;
     }
     
	public String getText() {
		return text;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String text;
}
