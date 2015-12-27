package com.h2y.jxc.entity;

import java.io.Serializable;


/**
 * 进销存  往来单位  Model
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */

public class JxcContactsUnits implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcContactsUnits";
    private long id;
    private String unitsName;
    private String unitsAbbr;
    private int unitsType;
    private String unitsCode;
    private String spellCode;
    private String unitsAddress;
    private String contacts;
    private String contactsPhone;
    private String contactsCellphone;
    private String contactsFax;
    private String contactsEmail;
    private String depositBank;
    private String depositor;
    private String bankAccount;
    private String dutyParagraph;
    private long data1;
    private long data2;
    private long data3;
    private String data4;
    private String data5;
    private String data6;

	public JxcContactsUnits(){
		super();
	}

	public JxcContactsUnits(long id){
		super();
		this.id = id;
	}

	public JxcContactsUnits(long id,String unitsName,String unitsAbbr,int unitsType,String unitsCode,String spellCode,String unitsAddress,String contacts,String contactsPhone,String contactsCellphone,String contactsFax,String contactsEmail,String depositBank,String depositor,String bankAccount,String dutyParagraph,long data1,long data2,long data3,String data4,String data5,String data6){
		super();
		this.id = id;
		this.unitsName = unitsName;
		this.unitsAbbr = unitsAbbr;
		this.unitsType = unitsType;
		this.unitsCode = unitsCode;
		this.spellCode = spellCode;
		this.unitsAddress = unitsAddress;
		this.contacts = contacts;
		this.contactsPhone = contactsPhone;
		this.contactsCellphone = contactsCellphone;
		this.contactsFax = contactsFax;
		this.contactsEmail = contactsEmail;
		this.depositBank = depositBank;
		this.depositor = depositor;
		this.bankAccount = bankAccount;
		this.dutyParagraph = dutyParagraph;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getUnitsName(){
      return unitsName;
    }
    
    public void setUnitsName(String unitsName){
      this.unitsName = unitsName;
    }


    public String getUnitsAbbr(){
      return unitsAbbr;
    }
    
    public void setUnitsAbbr(String unitsAbbr){
      this.unitsAbbr = unitsAbbr;
    }


    public int getUnitsType(){
      return unitsType;
    }
    
    public void setUnitsType(int unitsType){
      this.unitsType = unitsType;
    }


    public String getUnitsCode(){
      return unitsCode;
    }
    
    public void setUnitsCode(String unitsCode){
      this.unitsCode = unitsCode;
    }


    public String getSpellCode(){
      return spellCode;
    }
    
    public void setSpellCode(String spellCode){
      this.spellCode = spellCode;
    }


    public String getUnitsAddress(){
      return unitsAddress;
    }
    
    public void setUnitsAddress(String unitsAddress){
      this.unitsAddress = unitsAddress;
    }


    public String getContacts(){
      return contacts;
    }
    
    public void setContacts(String contacts){
      this.contacts = contacts;
    }


    public String getContactsPhone(){
      return contactsPhone;
    }
    
    public void setContactsPhone(String contactsPhone){
      this.contactsPhone = contactsPhone;
    }


    public String getContactsCellphone(){
      return contactsCellphone;
    }
    
    public void setContactsCellphone(String contactsCellphone){
      this.contactsCellphone = contactsCellphone;
    }


    public String getContactsFax(){
      return contactsFax;
    }
    
    public void setContactsFax(String contactsFax){
      this.contactsFax = contactsFax;
    }


    public String getContactsEmail(){
      return contactsEmail;
    }
    
    public void setContactsEmail(String contactsEmail){
      this.contactsEmail = contactsEmail;
    }


    public String getDepositBank(){
      return depositBank;
    }
    
    public void setDepositBank(String depositBank){
      this.depositBank = depositBank;
    }


    public String getDepositor(){
      return depositor;
    }
    
    public void setDepositor(String depositor){
      this.depositor = depositor;
    }


    public String getBankAccount(){
      return bankAccount;
    }
    
    public void setBankAccount(String bankAccount){
      this.bankAccount = bankAccount;
    }


    public String getDutyParagraph(){
      return dutyParagraph;
    }
    
    public void setDutyParagraph(String dutyParagraph){
      this.dutyParagraph = dutyParagraph;
    }


    public long getData1(){
      return data1;
    }
    
    public void setData1(long data1){
      this.data1 = data1;
    }


    public long getData2(){
      return data2;
    }
    
    public void setData2(long data2){
      this.data2 = data2;
    }


    public long getData3(){
      return data3;
    }
    
    public void setData3(long data3){
      this.data3 = data3;
    }


    public String getData4(){
      return data4;
    }
    
    public void setData4(String data4){
      this.data4 = data4;
    }


    public String getData5(){
      return data5;
    }
    
    public void setData5(String data5){
      this.data5 = data5;
    }


    public String getData6(){
      return data6;
    }
    
    public void setData6(String data6){
      this.data6 = data6;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitsName:"+unitsName+"\t"+"unitsAbbr:"+unitsAbbr+"\t"+"unitsType:"+unitsType+"\t"+"unitsCode:"+unitsCode+"\t"+"spellCode:"+spellCode+"\t"+"unitsAddress:"+unitsAddress+"\t"+"contacts:"+contacts+"\t"+"contactsPhone:"+contactsPhone+"\t"+"contactsCellphone:"+contactsCellphone+"\t"+"contactsFax:"+contactsFax+"\t"+"contactsEmail:"+contactsEmail+"\t"+"depositBank:"+depositBank+"\t"+"depositor:"+depositor+"\t"+"bankAccount:"+bankAccount+"\t"+"dutyParagraph:"+dutyParagraph+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6;
    }
}