package com.h2y.bmg.basic;

import java.util.Random;

public class BaseService {

	//获取六位随机数    
	public String getRandomCode(){
		Random random = new Random();
		String randomCode = "";
		for ( int i = 0; i < 6; i++ ){
			randomCode += Integer.toString(random.nextInt(9));
		}
		return randomCode;
	}

}
