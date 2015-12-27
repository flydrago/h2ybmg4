package com.h2y.bmg.entity;

import java.util.Date;

public class ScheduleTask {
	/**  

	 * @Title: ScheduleTask.java

	 * @Package com.h2y.bmg.entity

	 * @Description:

	 * @author lijian 

	 * @date 2015年9月24日 下午4:52:25

	 */
	
	
    private long id;
    private long uid;
    private String stname;
    private String sturl;
    private String stparam;
    private Date createdate;
    private Date begindate;
    private Date enddate;
    private long sttime;
    private int stnum;
    private int ststatus;
    private String memo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	public String getSturl() {
		return sturl;
	}
	public void setSturl(String sturl) {
		this.sturl = sturl;
	}
	public String getStparam() {
		return stparam;
	}
	public void setStparam(String stparam) {
		this.stparam = stparam;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public long getSttime() {
		return sttime;
	}
	public void setSttime(long sttime) {
		this.sttime = sttime;
	}
	public int getStnum() {
		return stnum;
	}
	public void setStnum(int stnum) {
		this.stnum = stnum;
	}
	public int getStstatus() {
		return ststatus;
	}
	public void setStstatus(int ststatus) {
		this.ststatus = ststatus;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "ScheduleTask [id=" + id + ", uid=" + uid + ", stname=" + stname
				+ ", sturl=" + sturl + ", stparam=" + stparam + ", createdate="
				+ createdate + ", begindate=" + begindate + ", enddate="
				+ enddate + ", sttime=" + sttime + ", stnum=" + stnum
				+ ", ststatus=" + ststatus + ", memo=" + memo + "]";
	}
}
