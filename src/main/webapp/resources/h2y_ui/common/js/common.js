/**
 * 根据所给iframe名称获取iframe对象
 *
 * @param {}
 *            nameV
 * @return {}
 */
function f_getframe(iframeName) {
    var frame;
    var argobj = arguments;
    var framename = iframeName;
    var hwtt_tempname = $(this).attr("name");

    if (argobj == null || argobj == undefined || argobj.length < 1) {
        alert("f_getframe(iframeName) parameter error");
    }
    $("body iframe").each(function(i) {
        hwtt_tempname = $(this).attr("name");
        if (hwtt_tempname.substring(0, framename.length) == framename) {
            frame = $(this)[0].contentWindow;
            return;
        }
    });
    if (frame == '' || frame == undefined) {
        frame = null;
    }
    return frame;
}


//获取批量选中的行ID
function f_getSelectIds(rows){
	var selectIds="";
	if(rows.length>0){
		for(var i=0;i<rows.length-1;i++){
			selectIds+=rows[i].id+",";
		}
		selectIds+=rows[rows.length-1].id;
	}
	return selectIds;
}


//打开混合选择窗口
function openMixSelectDialog() {

    var src = "sys/dialog/init.htm?op=mix";
    $.ligerDialog.open({
        name:"select_mix_dialog",
        title:  "混合选择窗口",
        height: 340,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	
            	var data=$("#select_mix_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_mixSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                dialog.close();
            } }
        ]
    });
}


//打开人员选择窗口
function openUserSelectDialog() {

    var src = "sys/dialog/init.htm?op=user";
    $.ligerDialog.open({
        name:"select_user_dialog",
        title:  "选择用户",
        height: 340,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	
            	var data=$("#select_user_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_userSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

//打开人员选择窗口
function openMapLocationDialog(locationData) {
	
	var src = "sys/dialog/pageinit.htm?op=maplocation";
	
	/*
	var h2y_longitude = "";
	var h2y_latitude = "";
	var h2y_location = "";
	if (locationData.h2y_longitude!=null) {
		src += "&"+locationData.h2y_longitude;
	}
	
	if (locationData.h2y_latitude!=null) {
		src += "&"+locationData.h2y_latitude;
	}
	
	if (locationData.h2y_location!=null) {
		src += "&"+locationData.h2y_location;
	}*/
    
    $.ligerDialog.open({
        name:"map_location",
        title:  "地图定位",
        height: 340,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: false,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	
            	var data=$("#map_location")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_mapLocationCallBack(data);
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
 * 选中的数据显示--侯飞龙
 * @param obj
 * @return
 */
function data_to_div(obj){
	var _data=obj.data,_inputName=obj.inputName,_divId=obj.divId;
	
	var selectData = "";
	
	$(_data).each(function(i) {
		
		if(i==(_data.length-1))
			selectData += "<span display=\"block\"><input  id=\""+_inputName+"_"+i+"\" type=\"hidden\" value=\""+ this.ID+ "\" name=\""+_inputName+"\"/>"+this.NAME+"</span>";
		else
			selectData += "<span display=\"block\"><input  id=\""+_inputName+"_"+i+"\" type=\"hidden\" value=\""+ this.ID+ "\" name=\""+_inputName+"\"/>"+this.NAME+"</span>,";
	});
	$("#"+_divId).html(selectData);
}



/**
 * 选中的数据显示--侯飞龙
 * @param obj
 * @return
 */
function data_to_div2(obj){
	var _data=obj.data,_inputName=obj.inputName,_divId=obj.divId,_rowSize=obj.rowSize,_tdWidth=obj.tdWidth;
	
	if (_rowSize==null || _rowSize == undefined) {
		_rowSize = 4;
	}
	
	$("#"+_divId).html("");
	
	if(_data==null || _data == undefined) return;
	
	var selectData = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"h2y_select_data_table\">";
	$(_data).each(function(i) {
		if (i%_rowSize == 0){
            if (i!=0){
            	selectData += "</tr>";
            };
            selectData += "<tr>";
        }
		selectData += "<td class=\"h2y_select_data_td\"><input  id=\""+_inputName+"_"+i+"\" type=\"hidden\" value=\""+ this.ID+ "\" name=\""+_inputName+"\"/><label for=\""+_inputName+"_"+i+"\">"+this.NAME+"</label></td>";
	});
	if(_data.length%_rowSize == 0){
		selectData += "</tr>";
	}
	selectData += "</table>";
	
	$("#"+_divId).html(selectData);
	
	if(_tdWidth!=null && _tdWidth!=undefined){
		_tdWidth = 200;
	}else{
		_tdWidth = $(".h2y_select_data_table").width()/_rowSize;
	}
	
	$(".h2y_select_data_td").css("width",_tdWidth+"px");
}


var deafultValidate = function (validateElements) 
{
	
    return  validateElements.validate({
    	//错误样式
        errorPlacement: function (lable, element)
        {
        	 clearLable(lable);
        	 //气泡依附在错误标签中
        	 element.ligerTip({ content: lable.html(), appendIdTo: lable });
        },
        //成功清除样式
        success: function (lable)
        {
        	clearLable(lable);
        }
    });
};

//清除气泡和标签
function clearLable(lable){
 	lable.ligerHideTip();    
 	lable.remove();	
}

//自定义验证
$(function ()
{
    if (jQuery.validator)
    {
        //字母数字
        jQuery.validator.addMethod("alnum", function (value, element)
        {
            return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
        }, "只能包括英文字母和数字");
        
        //字母数字下划线
        jQuery.validator.addMethod("alnumex", function (value, element)
        {
            return this.optional(element) || /^[a-zA-Z0-9_\.-]+$/.test(value);
        }, "只能包括英文字母数字和下划线");

        //手机号码验证   
        jQuery.validator.addMethod("cellphone", function (value, element)
        {
            var length = value.length;
            return this.optional(element) || (length == 11 && /^(1\d{10})$/.test(value));
        }, "请正确填写手机号码");

        //电话号码验证   
        jQuery.validator.addMethod("telephone", function (value, element)
        {
            var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写电话号码");

        //邮政编码验证
        jQuery.validator.addMethod("zipcode", function (value, element)
        {
            var tel = /^[0-9]{6}$/;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写邮政编码");

        //汉字
        jQuery.validator.addMethod("chcharacter", function (value, element)
        {
            var tel = /^[\u4e00-\u9fa5]+$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入汉字");

        //qq
        jQuery.validator.addMethod("qq", function (value, element)
        {
            var tel = /^[1-9][0-9]{4,}$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入正确的QQ");

        //账户
        jQuery.validator.addMethod("account", function (value, element)
        { 
            return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
        }, "账户格式不正确");
        
        //身份证验证
        jQuery.validator.addMethod("idcard", function (value, element)
        {
            return checkCard(value);
        }, "身份证格式不正确");
    }
});


//上传文件
function openFileUploadDialog(obj) {

	var src = "common/upload/uploadInit.htm?paraJson="+JSON.stringify(obj);
	
    $.ligerDialog.open({
        name:"file_upload_dialog",
        title:  "文件上传窗口",
        height: 340,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: false,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#file_upload_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_fileUploadCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                dialog.close();
            } }
        ]
    });
}

//选择经营范围选择窗口
function openUnitSortSelectDialog(){
	
		var src = "business/unitsort/unitSortInit.htm?selectType='radio'";
	    $.ligerDialog.open({
	        name:"select_unitsort_dialog",
	        title:  "选择经营范围",
	        height: 350,
	        url: src,
	        width: 700,
	        showMax: true,
	        showToggle: true,
	        showMin: true,
	        isResize: true,
	        modal: true,
	        buttons: [
	            { text: '确定', onclick: function (item, dialog) {
	            	var data=$("#select_unitsort_dialog")[0].contentWindow.h2y_returnData();								  	
	 			  	if (data){
	 			  		h2y_unitSortSelectCallBack(data);
	 			  		dialog.close();
	 			  	}
	            } },
	            { text: '取消', onclick: function (item, dialog) {
	                	dialog.close();
	            } }
	        ]
	    });
	
}

//打开人员选择窗口
/*
function openGoodsSelectDialog(obj) {
	var selectType = "multi"
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
    var src = "sys/dialog/goodsInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_goods_dialog",
        title:  "选择商品",
        height: 350,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_goods_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_goodsSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}*/


//打开素材选择窗口
function openMaterialSelectDialog(obj) {
	
    var src = "wechat/material/selectinit.htm?paraJson="+JSON.stringify(obj);
    $.ligerDialog.open({
        name:"select_material_dialog",
        title:  "选择素材",
        height: 350,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_material_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_materialSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}




//打开消息选择窗口
function openMsgSelectDialog(obj) {
	
  var src = "wechat/msg/selectinit.htm?paraJson="+JSON.stringify(obj);
  $.ligerDialog.open({
      name:"select_msg_dialog",
      title:  "选择消息",
      height: 400,
      url: src,
      width: 800,
      showMax: true,
      showToggle: true,
      showMin: true,
      isResize: true,
      modal: true,
      buttons: [
          { text: '确定', onclick: function (item, dialog) {
          	var data=$("#select_msg_dialog")[0].contentWindow.h2y_returnData();								  	
			  	if (data){
			  		h2y_msgSelectCallBack(data);
			  		dialog.close();
			  	}
          } },
          { text: '取消', onclick: function (item, dialog) {
              	dialog.close();
          } }
      ]
  });
}

//打开标签选择窗口
function openMarkSelectDialog(obj) {
	
    var src = "sys/dialog/markInit.htm?typeCode="+obj.typeCode;
    $.ligerDialog.open({
        name:"select_mark_dialog",
        title:  "选择标签",
        height: 350,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_mark_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_markSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}


//上传Image文件
function openImageUploadDialog(obj) {
	
	var src = "common/upload/imageUploadInit.htm?paraJson="+JSON.stringify(obj);
    $.ligerDialog.open({
        name:"image_upload_dialog",
        title:  "图片上传窗口",
        height: 540,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: false,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#image_upload_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_imageUploadCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                dialog.close();
            } }
        ]
    });
}


//打开商品选择窗口
function openGoodsSelectDialog(obj) {
	
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "business/goods/selectInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_goods_dialog",
        title:  "选择商品",
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
            	var data=$("#select_goods_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_goodsSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}



//打开商品定价选择窗口
function openPriceGoodsSelectDialog(obj) {
	
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "business/goodsprice/selectInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_goodsprice_dialog",
        title:  "选择商品",
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
            	var data=$("#select_goodsprice_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_priceGoodsSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}




//打开商品定价选择窗口
function openSubjectSelectDialog(activityId) {
	
    var src = "business/commonsubject/selectInit.htm?activityId="+activityId;
    $.ligerDialog.open({
        name:"select_subject_dialog",
        title:  "选择主题",
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
            	var data=$("#select_subject_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_subjectSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}




//打开广告主题选择窗口openAdvertSubjectSelectDialog 
function openAdvertSubjectSelectDialog() {
	
    var src = "business/advertsubject/selectInit.htm";
    $.ligerDialog.open({
        name:"select_advert_subject",
        title:  "选择广告主题",
        height: 500,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_advert_subject")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_advertSubjectSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}


//打开配送员选择列表
function openDeliverySelectDialog(obj) {
	
	var shopId = "";
	if(obj!=null && obj.shopId!=null){
		shopId = obj.shopId;
	}
	
    var src = "business/deliveryuser/selectInit.htm?shopId="+shopId;
    $.ligerDialog.open({
        name:"select_delivery",
        title:  "选择配送员",
        height: 500,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_delivery")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_deliverySelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

//Enter键 搜索
document.onkeydown = function enterSearch(e){
    if (window.event)
        var keyPressed = window.event.keyCode; // IE
    else
        keyPressed = e.which; // Firefox
    if (keyPressed == 13) {
    	if( typeof h2y_search === 'function' ){
    		h2y_search();
    	}
    	
    }
}


//打开优惠劵选择列表
function openCouponsSelectDialog(obj) {
	
	var selectType = "radio";
	var sourceId = 0;
	var sourceCode = 0;
	var isStart = "";//是否过滤未开始优惠劵
	var filterBean = "default";//优惠劵选择模块
	var data1 = "";//扩展数据1
	var data2 = "";//扩展数据2
	var data3 = "";//扩展数据3
	var data4 = "";//扩展数据4
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
	if(obj!=null && obj.sourceId!=null){
		sourceId = obj.sourceId;
	}
	
	if(obj!=null && obj.sourceCode!=null){
		sourceCode = obj.sourceCode;
	}
	
	if(obj!=null && obj.isStart!=null){
		isStart = obj.isStart;
	}
	
	if(obj!=null && obj.filterBean!=null){
		filterBean = obj.filterBean;
	}
	if(obj!=null && obj.data1!=null){
		data1 = obj.data1;
	}
	if(obj!=null && obj.data2!=null){
		data2 = obj.data2;
	}
	if(obj!=null && obj.data3!=null){
		data3 = obj.data3;
	}
	
	if(obj!=null && obj.data4!=null){
		data4 = obj.data4;
	}
	
	
    var src = "business/coupons/selectInit.htm?selectType="+selectType+"&sourceId="+sourceId+"&sourceCode="+sourceCode+"&isStart="+isStart+"&filterBean="+filterBean+"&data1="+data1+"&data2="+data2+"&data3="+data3+"&data4="+data4;
    $.ligerDialog.open({
        name:"select_coupons",
        title:  "选择优惠劵",
        height: 500,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_coupons")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_couponsSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}


//打开省级代理选择窗口 --Mr.Lu
function openProvinceSelectDialog(obj) {
	
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "business/tracenumbersection/selectProvinceinit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_tracenumbersection_province_dialog",
        title:  "选择省级代理",
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
            	var data=$("#select_tracenumbersection_province_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_provinceSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

//打开供应商选择窗口  --Mr.Lu
function openProviderSelectDialog(obj) {
	
	var parentId = "";
	if(obj!=null && obj.parentId!=null){
		parentId = obj.parentId;
	}
	
    var src = "business/tracenumbersection/selectProviderinit.htm?parentId="+parentId;
    $.ligerDialog.open({
        name:"select_tracenumbersection_provider_dialog",
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
            	var data=$("#select_tracenumbersection_provider_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_providerSelectCallBack(data);
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
 * 选中省级代理的数据显示--Mr.Lu
 * @param obj
 * @return
 */
/*function data_to_input(obj){
	var _data=obj.data,_inputToNameId=obj.inputToNameId,_inputToId=obj.inputToId;
	//alert(JSON.stringify(_data));
	$("#" + _inputToNameId).val(_data.UNIT_NAME);
	$("#" + _inputToId).val(_data.ID);
}*/

/**
 * 选中商品后数据显示--Mr.Lu
 * @param obj
 * @return
 */
function data_to_div_input(obj){
	var _data=obj.data,inputToId=obj.inputToId;
	var continueFlag = true;
	
	var goodsIdArry = $("#"+ inputToId).find("input[name=\"providerGoodsId\"]");
	$.each(goodsIdArry,function(i,n){
		if($(n).val() == _data.ID){
			alert(_data.GOODS_NAME + "已经选过了");
			continueFlag = false;
			return false;
		}
	});
	$.each(goodsIds,function(i,n){
		if(n == _data.ID){
			alert(_data.GOODS_NAME + "已经添加过了");
			continueFlag = false;
			return false;
		}
	});
	if(!continueFlag){
		return;
	}
	
	var selectData = "";
	selectData = "<div> <input name=\"providerGoodsName\" type=\"text\" class=\"h2y_dialog_input_long goodsInput\" readonly=\"readonly\" value=\""+ _data.GOODS_NAME +"\"/>"
				+ "<input name=\"providerGoodsId\" type=\"hidden\" readonly=\"readonly\" value=\""+ _data.ID +"\" style=\"display:none;\"/>"
				+ "<span onclick=\"clearLine(this)\" style=\"font-weight:bold;margin-left:-20px;\">X</span></div>";
	$("#"+ inputToId).html($("#"+ inputToId).html() + selectData);
}





//打开推广活动选择列表
function openPromoteCommonSelectDialog(obj) {
	
	var selectType = "radio";
	var sourceId = 0;
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "business/promoteactivity/selectInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_promote_common",
        title:  "选择推广活动",
        height: 500,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_promote_common")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_promoteCommonSelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}

//打开广告栏位促销活动选择列表
function openAdvertActivitySelectDialog(obj) {
	
	var subjectId = 0;
	var activityType = 2;
	if(obj!=null && obj.subjectId!=null){
		subjectId = obj.subjectId;
	}
	
	if(obj!=null && obj.activityType!=null){
		activityType = obj.activityType;
	}
	
    var src = "business/advertsubjectinfort/selectActivityInit.htm?subjectId="+subjectId+"&activityType="+activityType;
    $.ligerDialog.open({
        name:"select_advert_activity",
        title:  "选择广告促销活动",
        height: 500,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#select_advert_activity")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_advertActivitySelectCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                	dialog.close();
            } }
        ]
    });
}



//打开图片库选择列表
function openImgStorageSelectDialog(obj) {
	
	var selectType = "radio";
	var filterBean = "";//优惠劵选择模块
	var moduleCode = obj.moduleCode;//图片模块编码
	var usageCode = obj.usageCode;//图片用途编码
	var data1 = "";//扩展数据1
	var data2 = "";//扩展数据2
	var data3 = "";//扩展数据3
	var data4 = "";//扩展数据4
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
	if(obj!=null && obj.filterBean!=null){
		filterBean = obj.filterBean;
	}
	
	if(obj!=null && obj.moduleCode!=null){
		moduleCode = obj.moduleCode;
	}
	
	if(obj!=null && obj.usageCode!=null){
		usageCode = obj.usageCode;
	}
	if(obj!=null && obj.data1!=null){
		data1 = obj.data1;
	}
	if(obj!=null && obj.data2!=null){
		data2 = obj.data2;
	}
	if(obj!=null && obj.data3!=null){
		data3 = obj.data3;
	}
	
	if(obj!=null && obj.data4!=null){
		data4 = obj.data4;
	}
	
	
  var src = "business/imgstorage/selectInit.htm?selectType="+selectType+"&filterBean="+filterBean+"&moduleCode="+moduleCode+"&usageCode="+usageCode+"&data1="+data1+"&data2="+data2+"&data3="+data3+"&data4="+data4;
  $.ligerDialog.open({
      name:"select_imgstorage",
      title:  "选择图片",
      height: 500,
      url: src,
      width: 700,
      showMax: true,
      showToggle: true,
      showMin: true,
      isResize: true,
      modal: true,
      buttons: [
          { text: '确定', onclick: function (item, dialog) {
          	var data=$("#select_imgstorage")[0].contentWindow.h2y_returnData();								  	
			  	if (data){
			  		h2y_imgStorageSelectCallBack(data);
			  		dialog.close();
			  	}
          } },
          { text: '取消', onclick: function (item, dialog) {
              	dialog.close();
          } }
      ]
  });
}




//打开图片库选择列表
function openImgStorageViewDialog(obj) {
	
  var src = "business/imgstorage/view.htm?id="+obj.id;
  $.ligerDialog.open({
      name:"view_imgstorage",
      title:  "图片预览",
      height: 500,
      url: src,
      width: 700,
      isResize: true,
      modal: false,
  });
}


/**
 * 选择 图片所属模块 对话窗
 * @param obj
 */
function openModuleDialog(obj){
	var selectType = "radio";
	if(obj!=null && obj.selectType!=null){
		selectType = obj.selectType;
	}
	
    var src = "img/mu/selectModuleListInit.htm?selectType="+selectType;
    $.ligerDialog.open({
        name:"select_module_dialog",
        title:  "选择模块",
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
            	var data=$("#select_module_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_moduleSelectCallBack(data);
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
 *	模块选择 回调函数
 */
function h2y_moduleSelectCallBack(data){
    	
    	if(data==null || data.length==0) return;
//    	alert(JSON.stringify(data));
    	$("#moduleId").val(data.id);
    	$("#moduleInfoDiv").text(data.moduleName);
}



/**
 * 选择 图片用途 对话窗
 * @param obj
 */
function openUsageDialog(obj){
	var selectType = "radio";
	
    var src = "img/mu/selectUsageListInit.htm?selectType="+selectType+"&moduleId="+obj;
    $.ligerDialog.open({
        name:"select_usage_dialog",
        title:  "选择用途",
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
            	var data=$("#select_usage_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_usageSelectCallBack(data);
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
 *	用途选择 回调函数
 */
function h2y_usageSelectCallBack(data){
    	
    	if(data==null || data.length==0) return;
//    	alert(JSON.stringify(data));
    	$("#usageId").val(data.id);
    	$("#usageInfoDiv").text(data.usageName);
}



//图片上传对话窗
function openImgUploadDialog(obj) {

	var src = "common/upload/uploadInit.htm?paraJson="+JSON.stringify(obj);
	
    $.ligerDialog.open({
        name:"img_upload_dialog",
        title:  "图片上传窗口",
        height: 340,
        url: src,
        width: 700,
        showMax: true,
        showToggle: true,
        showMin: false,
        isResize: true,
        modal: true,
        buttons: [
            { text: '确定', onclick: function (item, dialog) {
            	var data=$("#img_upload_dialog")[0].contentWindow.h2y_returnData();								  	
 			  	if (data){
 			  		h2y_imgUploadCallBack(data);
 			  		dialog.close();
 			  	}
            } },
            { text: '取消', onclick: function (item, dialog) {
                dialog.close();
            } }
        ]
    });
}
