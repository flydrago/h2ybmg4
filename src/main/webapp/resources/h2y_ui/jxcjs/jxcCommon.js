/**
 * 选择供应商 对话窗
 */
function openSupplierDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "jxc/common/selectContactUnitInit.htm?selectType="+selectType+"&unitType=0";
    $.ligerDialog.open({
        name:"select_supplier_dialog",
        title:  "选择供应商",
        height: 450,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_supplier_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_supplierSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}
    
    
    
//供应商选择回调函数
function h2y_supplierSelectCallBack(data){
    	
    	if(data==null || data.length==0) return;
//    	alert(JSON.stringify(data));
    	$("#supplierId").val(data.ID);
    	$("#chosen_supplier").text(data.UNITS_NAME);
}


/**
 * 选择客户 对话窗
 */
function openCustomerDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "jxc/common/selectContactUnitInit.htm?selectType="+selectType+"&unitType=1";
    $.ligerDialog.open({
        name:"select_customer_dialog",
        title:  "选择客户",
        height: 450,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_customer_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_customerSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

//供应商选择回调函数
function h2y_customerSelectCallBack(data){
    	
    	if(data==null || data.length==0) return;
//    	alert(JSON.stringify(data));
    	$("#customerId").val(data.ID);
    	$("#chosen_customer").text(data.UNITS_NAME);
}

/**
 * 选择仓库对话窗
 * @param obj
 */
function openStorageDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "jxc/common/selectStorageInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_storage_dialog",
        title:  "选择入库仓库",
        height: 450,
        url: src,
        width: 900,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_storage_dialog")[0].contentWindow.h2y_returnData();								  	
// 			  	alert(JSON.stringify(data));
            	if (data){
 			  		h2y_storageSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

//仓库选择回调函数
function h2y_storageSelectCallBack(data){
    	
    	if(data==null || data.length==0) return;
//    	alert(JSON.stringify(data));
    	$("#storageId").val(data.ID);
    	$("#chosen_storage").text(data.STORE_NAME);
}

/**
 * 选择经手人 对话窗
 * @param obj
 */
function openBrokerDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "jxc/common/selectBrokerInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_broker_dialog",
        title:  "选择经手人",
        height: 450,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_broker_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_brokerSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

/**
 *	经手人选择 回调函数
 */
function h2y_brokerSelectCallBack(data){
    	
    	if(data==null || data.length==0) return;
//    	alert(JSON.stringify(data));
    	$("#brokerId").val(data.ID);
    	$("#chosen_broker").text(data.USER_NAME);
}


/**
 * 调拨单  选择调出仓库
 */
function openExStorageDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "jxc/common/selectStorageInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_exStorage_dialog",
        title:  "选择调出仓库",
        height: 450,
        url: src,
        width: 900,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_exStorage_dialog")[0].contentWindow.h2y_returnData();								  	
// 			  	alert(JSON.stringify(data));
            	if (data){
            		h2y_exStorageSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}



/**
 * 调拨单  选择调出仓库  回调函数
 */
function h2y_exStorageSelectCallBack(data){
	
	if(data==null || data.length==0) return;
//	alert(JSON.stringify(data));
	$("#exStorageId").val(data.ID);
	$("#chosen_exStorage").text(data.STORE_NAME);
}


/**
 * 调拨单  选择调入仓库
 */
function openInStorageDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "jxc/common/selectStorageInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_inStorage_dialog",
        title:  "选择调出仓库",
        height: 450,
        url: src,
        width: 900,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_inStorage_dialog")[0].contentWindow.h2y_returnData();								  	
// 			  	alert(JSON.stringify(data));
            	if (data){
            		h2y_inStorageSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}



/**
 * 调拨单  选择调入仓库  回调函数
 */
function h2y_inStorageSelectCallBack(data){
	
	if(data==null || data.length==0) return;
//	alert(JSON.stringify(data));
	$("#inStorageId").val(data.ID);
	$("#chosen_inStorage").text(data.STORE_NAME);
}


/**
 * 选择往来单位账户对话窗
 * @param accountType
 */
function openAccountSelectDialog(accountType){
	var selectType = "radio";
	
    var src = "jxc/common/selectUnitAccountInit.htm?selectType="+selectType+"&accountType="+accountType;
    $.ligerDialog.open({
        name:"select_unitAccount_dialog",
        title:  "选择账户",
        height: 450,
        url: src,
        width: 900,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_unitAccount_dialog")[0].contentWindow.h2y_returnData();								  	
// 			  	alert(JSON.stringify(data));
            	if (data){
            		h2y_accountSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

/**
 * 选择往来单位账户回调函数
 * @param data
 */
function h2y_accountSelectCallBack(data){
	if(data==null || data.length==0) return;
	$("#unitAccountId").val(data.id);
	$("#chosen_account").text(data.accountName);
}
