package com.h2y.bmg.controllers;

import com.h2y.bmg.entity.DwrDemo;

public class DWRService {

	public DWRService() {
	}
	
    public String getMsg(){
        return "你好";
    }

    
    public void addMsgDemo(DwrDemo dwrDemo){
        System.out.println(dwrDemo);
    }
}
