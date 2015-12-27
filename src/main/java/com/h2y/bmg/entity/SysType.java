package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysType Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-17
 * email:info@hwttnet.com
 */

public class SysType extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysType";
    private long id;
    private String code;
    private long pid;
    private String name;
    private String memo;
    private String type;
    private long ord;

	public SysType(){
		super();
	}

	public SysType(Long id){
		super();
		this.id = id;
	}

	public SysType(Long id,String code,Long pid,String name,String memo,String type,long ord){
		super();
		this.id = id;
		this.code = code;
		this.pid = pid;
		this.name = name;
		this.memo = memo;
		this.type = type;
        this.ord = ord;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getOrd() {
        return ord;
    }

    public void setOrd(long ord) {
        this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"code:"+code+"\t"+"pid:"+pid+"\t"+"name:"+name+"\t"+"memo:"+memo+"\t"+"type:"+type+"\t"+"ord:"+ord;
    }
}