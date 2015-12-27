package com.h2y.jxc.entity;

import com.h2y.jxc.basic.BaseBill;

/**
 * 进销存  单据校验返回参数类
 * @author jyd-yfb-02
 *
 */
public class JxcBillCheckParams {
	private int resultFlag;
	private String resultMsg;
	private BaseBill bill;

	public JxcBillCheckParams(){
	}
	
	public JxcBillCheckParams(int resultFlag, String resultMsg, BaseBill bill) {
		super();
		this.resultFlag = resultFlag;
		this.resultMsg = resultMsg;
		this.bill = bill;
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

	public BaseBill getBill() {
		return bill;
	}

	public void setBill(BaseBill bill) {
		this.bill = bill;
	}
	
	
	
}
