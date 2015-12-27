package com.h2y.jxc.basic;

import java.io.Serializable;

public class BaseBill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061481114893902435L;

	private long id;
    private String billNo;
    private String billCustomNo;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillCustomNo() {
		return billCustomNo;
	}
	public void setBillCustomNo(String billCustomNo) {
		this.billCustomNo = billCustomNo;
	}
	
	@Override
	public String toString() {
		return "BaseBill [id=" + id + ", billNo=" + billNo + ", billCustomNo="
				+ billCustomNo + "]";
	}
    
}
