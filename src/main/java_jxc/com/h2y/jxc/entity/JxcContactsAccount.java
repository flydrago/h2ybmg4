package com.h2y.jxc.entity;

import java.io.Serializable;


/**
 * 进销存收支账户 Model create
 * @author hwttnet
 * version:1.2
 * time:2015-07-24
 */

public class JxcContactsAccount implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcContactsAccount";
    private long id;
    private long unitsId;
    private String unitsName;
    private String accountName;
    private String spellCode;
    private int accountType;
    private String accountCurrency;
    private String depositBank;
    private String depositor;
    private String bankAccount;
    private String dutyParagraph;
    private String data1;
    private String data2;
    private String data3;
    private int data4;
    private int data5;
    private int data6;
    private Double data7;
    private Double data8;
    private Double data9;
    private long data10;

	public JxcContactsAccount(){
		super();
	}

	public JxcContactsAccount(long id){
		super();
		this.id = id;
	}

	public JxcContactsAccount(long id,long unitsId,String unitsName,String accountName,String spellCode,int accountType,String accountCurrency,String depositBank,String depositor,String bankAccount,String dutyParagraph,String data1,String data2,String data3,int data4,int data5,int data6,Double data7,Double data8,Double data9,long data10){
		super();
		this.id = id;
		this.unitsId = unitsId;
		this.unitsName = unitsName;
		this.accountName = accountName;
		this.spellCode = spellCode;
		this.accountType = accountType;
		this.accountCurrency = accountCurrency;
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
		this.data7 = data7;
		this.data8 = data8;
		this.data9 = data9;
		this.data10 = data10;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getUnitsId(){
      return unitsId;
    }
    
    public void setUnitsId(long unitsId){
      this.unitsId = unitsId;
    }


    public String getUnitsName(){
      return unitsName;
    }
    
    public void setUnitsName(String unitsName){
      this.unitsName = unitsName;
    }


    public String getAccountName(){
      return accountName;
    }
    
    public void setAccountName(String accountName){
      this.accountName = accountName;
    }


    public String getSpellCode(){
      return spellCode;
    }
    
    public void setSpellCode(String spellCode){
      this.spellCode = spellCode;
    }


    public int getAccountType(){
      return accountType;
    }
    
    public void setAccountType(int accountType){
      this.accountType = accountType;
    }


    public String getAccountCurrency(){
      return accountCurrency;
    }
    
    public void setAccountCurrency(String accountCurrency){
      this.accountCurrency = accountCurrency;
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


    public String getData1(){
      return data1;
    }
    
    public void setData1(String data1){
      this.data1 = data1;
    }


    public String getData2(){
      return data2;
    }
    
    public void setData2(String data2){
      this.data2 = data2;
    }


    public String getData3(){
      return data3;
    }
    
    public void setData3(String data3){
      this.data3 = data3;
    }


    public int getData4(){
      return data4;
    }
    
    public void setData4(int data4){
      this.data4 = data4;
    }


    public int getData5(){
      return data5;
    }
    
    public void setData5(int data5){
      this.data5 = data5;
    }


    public int getData6(){
      return data6;
    }
    
    public void setData6(int data6){
      this.data6 = data6;
    }


    public Double getData7(){
      return data7;
    }
    
    public void setData7(Double data7){
      this.data7 = data7;
    }


    public Double getData8(){
      return data8;
    }
    
    public void setData8(Double data8){
      this.data8 = data8;
    }


    public Double getData9(){
      return data9;
    }
    
    public void setData9(Double data9){
      this.data9 = data9;
    }


    public long getData10(){
      return data10;
    }
    
    public void setData10(long data10){
      this.data10 = data10;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitsId:"+unitsId+"\t"+"unitsName:"+unitsName+"\t"+"accountName:"+accountName+"\t"+"spellCode:"+spellCode+"\t"+"accountType:"+accountType+"\t"+"accountCurrency:"+accountCurrency+"\t"+"depositBank:"+depositBank+"\t"+"depositor:"+depositor+"\t"+"bankAccount:"+bankAccount+"\t"+"dutyParagraph:"+dutyParagraph+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10;
    }
}