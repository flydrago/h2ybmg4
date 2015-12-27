package com.h2y.jxc.basic;

/**
 * 进销存常用参数处理
 * @author jyd-yfb-02
 *
 */
public class JxcKeys {

	/**
	 * 单据审核参数
	 * @author jyd-yfb-02
	 *
	 */
	public enum BillAuditKeys{
		//审核通过
		approved(1),
		//未审核
		unverified(0),
		//审核未通过
		notapproved(-1),
		//已冲账
		strikeBalance(9);
		
		private int _value;
		
		BillAuditKeys(int value){
			_value = value;
		}
		
		public int value(){
			return _value;
		}
	}
	
	/**
	 * 单据审核日志参数
	 * @author jyd-yfb-02
	 */
	public enum BillAuditLogKeys{
		//待审核
		pendingAuditStage("1"),
		pendingAudit("待审核"),
		//一级审核
		firstAuditStage("2"),
		firstAudit("一级审核"),
		//冲账
		strikeBalanceStage("3"),
		strikeBalance("已红冲");
		
		private String _value;
		
		BillAuditLogKeys(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
			
	}
	
	/**
	 * 往来单位参数
	 * @author jyd-yfb-02
	 *
	 */
	public enum ContactsKeys{
		//供应商类型
		supplierType("0"),
		//客户类型
		customerType("1");
		
		private String _value;
		
		ContactsKeys(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
	}
	
	
	/**
	 * 仓库 库存操作参数
	 * @author jyd-yfb-02
	 *
	 */
	public enum StorageKeys{
		//入库操作
		inStorage("0"),
		//出库操作
		exStorage("1"),
		//报溢
		overflow("2"),
		//报损
		breakage("3");
		
		
		private String _value;
		
		StorageKeys(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
	}
}
