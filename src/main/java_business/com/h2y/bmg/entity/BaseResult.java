package com.h2y.bmg.entity;


/**
 * 项目名称：h2ygdsos  
 * 类名称：BaseResult  
 * 类描述：  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月11日 下午1:55:24  
 * 修改人：侯飞龙
 * 修改时间：2015年6月11日 下午1:55:24  
 * 修改备注：  
 * @version
 */
public class BaseResult{

    private int resultFlag;
    private String resultMsg;
    private Object resultData;

	public BaseResult(){
		super();
	}
	
	public BaseResult(int resultFlag){
		
		super();
		this.resultFlag = resultFlag;
	}
	
	public BaseResult(int resultFlag,String resultMsg){
		
		super();
		this.resultFlag = resultFlag;
		this.resultMsg = resultMsg;
	}
	
	public BaseResult(int resultFlag,String resultMsg,Object resultData){
		
		super();
		this.resultFlag = resultFlag;
		this.resultMsg = resultMsg;
		this.resultData = resultData;
	}

	public int getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(int resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "BaseResult [resultFlag=" + resultFlag + ", resultMsg="
				+ resultMsg + ", resultData=" + resultData + "]";
	}
}