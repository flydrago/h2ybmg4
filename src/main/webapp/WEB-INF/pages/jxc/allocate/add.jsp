<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
	<script type="text/javascript" src="<%=uiPath%>jxcjs/jxcCommon.js"></script>
	<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
	<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
	<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

    <script type="text/javascript">
        var op = "${op}";

        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
        	$("#allocateDate").datetimepicker({});
        	
        	$("#choose_exStorage_btn").click(function(){
        		openExStorageDialog();
        	});
			
        	$("#choose_inStorage_btn").click(function(){
        		openInStorageDialog();
        	});
        	
        	$("#choose_broker_btn").click(function(){
        		openBrokerDialog();
        	});
        	
        	$("#choose_billGoods_btn").click(function(){
        		openGoodsSelectDialog();
        	});
        });
        
        /**
    	 * 单据明细 商品选择 对话窗
    	 */
    	function openGoodsSelectDialog(obj){
    	     	var selectType = "multi";
    	     	if(obj!=null && obj.selectType!=null){
    	     		selectType = obj.selectType;
    	     	}
    	         var src = "jxc/billDetail/goodsSelectInit.htm?selectType="+selectType;
    	         $.ligerDialog.open({
    	             name:"select_billGoods_dialog",
    	             title:  "选择单据商品明细",
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
    	                 	var data=$("#select_billGoods_dialog")[0].contentWindow.h2y_returnData();								  	
    	      			  	if (data){
    	      			  		h2y_billGoodsSelectCallBack(data);
    	      			  		dialog.close();
    	      			  	}
    	                 } },
    	                 { text: '取消', onclick: function (item, dialog) {
    	                     	dialog.close();
    	                 } }
    	             ]
    	         });
    	}
    	
    	/**商品明细选择 回调函数**/
    	function h2y_billGoodsSelectCallBack(data){
    		if(data==null || data.length==0) return;
    		//	alert(JSON.stringify(data));
    		$("#chosen_billGoods").text("");
    	    if(data.length != 0){
    	  	  $("#billGoodsTable").remove();
    	  	  var checkedGoodsHtml = '<table class="h2y_table" id="billGoodsTable" style="width:100%;">' +
    								  	 '<tr>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;width:18%;">名称</td>'+
    								     //'<td class="h2y_table_label_td" style="text-align: center;">单位</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">成本原价</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">调拨单价</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">数量</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">调拨差额</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">金额</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">备注</td>'+
    								     '<td class="h2y_table_label_td" style="text-align: center;">操作</td>'+
    								     '</tr>';
    	  	  
    	  	  
    	  	  for(var i=0;i<data.length;i++){
    	  		  
    	  		  if(null != data[i].COUNT){
    	  			  dataCount = data[i].COUNT;
    	  		  }
    	      	
    				  checkedGoodsHtml +=   '<tr id="billGoods_tr_'+data[i].ID+'" class="billGoodsDetail">'+
    		  								'<td class="h2y_table_edit_td">'+data[i].NAME+'<input name="billGoodsId" class="billGoodsId" type="hidden" value="'+data[i].ID+'"/></td>'+
    		  								//'<td class="h2y_table_edit_td"><input name="goodsUnit" type="text" class="h2y_input_just" style="width:50px;" value="" disabled="disabled"/></td>'+
    		  								'<td class="h2y_table_edit_td"><input name="costSinglePrice" id="billGoodsCostSinglePrice'+data[i].ID+'" type="text" class="h2y_input_just" style="width:60px;"  value="0.00" onblur="calcBillGoodsTotalAmount('+data[i].ID+')"/></td>'+
    		  								'<td class="h2y_table_edit_td"><input name="allocateSinglePrice" id="billGoodsAllocateSinglePrice'+data[i].ID+'" type="text" class="h2y_input_just" style="width:60px;"  value="0.00" onblur="calcBillGoodsTotalAmount('+data[i].ID+')"/></td>'+
    		  								'<td class="h2y_table_edit_td"><input name="billGoodsCount" id="billGoodsCount'+data[i].ID+'" type="text" class="h2y_input_just billGoodsCount" style="width:60px;"  value="1" onblur="calcBillGoodsTotalAmount('+data[i].ID+')"/></td>'+
    		  								'<td class="h2y_table_edit_td"><input name="billGoodsTotalAmountGap" id="billGoodsTotalAmountGap'+data[i].ID+'" class="billGoodsTotalAmountGap" type="text" class="h2y_input_just" style="width:100px;" value="0.00" disabled="disabled"/></td>'+
    		  								'<td class="h2y_table_edit_td"><input name="billGoodsTotalAmount" id="billGoodsTotalAmount'+data[i].ID+'" class="billGoodsTotalAmount" type="text" class="h2y_input_just" style="width:100px;" value="0.00" disabled="disabled"/></td>'+
    		  								'<td class="h2y_table_edit_td"><input name="billGoodsNotes" id="billGoodsNotes'+data[i].ID+'" type="text" class="h2y_input_just" value="" style="width:100px;"/></td>'+
    		  								'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowBillGoods('+data[i].ID+')"/></td>'+
    		  								'</tr>';	
    			}
    	  	  
    	  	checkedGoodsHtml += "</table>";
    	  	$("#chosen_billGoods").append(checkedGoodsHtml);
    	  	calcBillGoodsTotalAmount();
    	  	calcBillTotalAmount();
    	    }       
    	}

    	//删除 单据商品明细 行
    	function deleteRowBillGoods(index){
    		$("#billGoods_tr_"+index).remove();
    	}
    	
    	//计算调拨总数
    	function calcAllocateCount(){
    		var allocateCount = 0;
    		$(".billGoodsCount").each(function(){
    			allocateCount = accAdd(allocateCount,this.value);
    		});
    		$("#totalCount").val(allocateCount);
    	}
    	
    	//计算单件商品总额
    	function calcBillGoodsTotalAmount(goodsID){
    		var billGoodsTotalAmount =  accMul($("#billGoodsCount"+goodsID).val(),$("#billGoodsAllocateSinglePrice"+goodsID).val());
    		$("#billGoodsTotalAmount"+goodsID).val(billGoodsTotalAmount);
    		calcBillGoodsTotalGap(goodsID);
    	}
    	
    	//计算单件商品调拨差额
    	function calcBillGoodsTotalGap(goodsID){
    		var billGoodsTotalGap = accMul(accMin($("#billGoodsAllocateSinglePrice"+goodsID).val(),$("#billGoodsCostSinglePrice"+goodsID).val()),$("#billGoodsCount"+goodsID).val());
    		$("#billGoodsTotalAmountGap"+goodsID).val(billGoodsTotalGap);
    		calcBillTotalAmount();
    	}
    	
    	//计算单据总额
    	function calcBillTotalAmount(){
    		var billTotalAmount = 0;
    		$(".billGoodsTotalAmount").each(function(){
    			billTotalAmount = accAdd(billTotalAmount,this.value);
    		});
    		$("#totalAmount").val(billTotalAmount);
    		calcBillTotalAmountGap();
    		calcAllocateCount();
    	}
    	
    	//计算单据总 差额
    	function calcBillTotalAmountGap(){
    		var billTotalAmountGap = 0;
    		$(".billGoodsTotalAmountGap").each(function(){
    			billTotalAmountGap = accAdd(billTotalAmountGap,this.value);
    		});
    		$("#totalAmountGap").val(billTotalAmountGap);
    	}
    	
    	//计算优惠
    	function calcDiscount(){
    		calcDiscountAmount();
    		calcPayableAmount();
    	}
    	
    	//计算优惠总额
    	function calcDiscountAmount(){
    		var discount = $("#discount").val();
    		var discountF = (100-discount)/100;
    		var discountAmount = accMul($("#totalAmount").val(),discountF); 
    		$("#discountAmount").val(discountAmount);
    	}	
    	
    	//计算实际总额
    	function calcPayableAmount(){
    		var discount = $("#discount").val();
    		var discountF = discount/100;
    		var receivableAmount = accMul($("#totalAmount").val(),discountF);
    		$("#receivableAmount").val(receivableAmount);
    	}
    	
    	//保存单据
    	function h2y_save(){
    		var postData = {};			//保存参数
    		var billGoodsArray = [];	//单据商品明细数组
    		postData.billNo = $("#billNo").val();
    		postData.billCustomNo = $("#billCustomNo").val();
    		
    		postData.exStorageId = $("#exStorageId").val();
    		postData.inStorageId = $("#inStorageId").val();
    		postData.brokerId = $("#brokerId").val();
    		
    		postData.allocateDate = $("#allocateDate").val();
    		postData.notes = $("#notes").val();
    		postData.reviseMark = $("input[name='reviseMark']:checked").val();
    		
    		//单据 商品明细数组 组装
    			$(".billGoodsDetail").each(function(){
    				var billGoodsSingleDetail = {};
    				billGoodsSingleDetail.goodsId = $(this).find("input[name='billGoodsId']").val();
    				billGoodsSingleDetail.costSinglePrice = $(this).find("input[name='costSinglePrice']").val();
    				billGoodsSingleDetail.allocateSinglePrice = $(this).find("input[name='allocateSinglePrice']").val();
    				billGoodsSingleDetail.goodsCount = $(this).find("input[name='billGoodsCount']").val();
    				billGoodsSingleDetail.goodsNotes= $(this).find("input[name='billGoodsNotes']").val();
    				billGoodsArray.push(billGoodsSingleDetail);
    			});
    		postData.billGoodsList = JSON.stringify(billGoodsArray);
    		postData.billMakerId = $("#billMakerId").val(); 
    			
    		$.ajaxSetup({
    			 contentType: "application/x-www-form-urlencoded; charset=utf-8"
    			});
    		
    		$.post("jxc/allocate/save.htm",{postData:JSON.stringify(postData)},function(res){
    			var resJson = eval("("+res+")");
    			if(resJson.resultFlag == 1 || resJson.resultFlag == "1"){
    				alert("单据添加成功。");
    				if (top.f_getframe("jxc_allocate_init_htm") != null) {
						top.f_getframe("jxc_allocate_init_htm").f_query();
					}
					top.f_delTab("allocate_add");
    			}else{
    				/* alert("单据添加失败，请联系管理员"); */
    				alert(resJson.resultMsg);
    			}
    		});
    	}
    	
    	//刷新页面
    	function h2y_refresh(){
    		document.location.reload();
    	}
    	
    	//由于 js 浮点数 普通运算 bug，所以 自定义 普通运算方法
    	
    	//加法调用：accAdd(arg1,arg2)
		//返回值：arg1加上arg2的精确结果
		function accAdd(arg1,arg2){
		    var r1,r2,m;
		    try{r1=String(arg1).split(".")[1].length;}catch(e){r1=0;}
		    try{r2=String(arg2).split(".")[1].length;}catch(e){r2=0;}
		    m=Math.pow(10,Math.max(r1,r2));
		    return ((arg1*m+arg2*m)/m).toFixed(2);
		}
    	
    	//减法调用：accMin(arg1,arg2)
    	//返回值：arg1 减去 arg2 的结果（保留两位）
    	function accMin(arg1,arg2){
		    var r1,r2,m;
		    try{r1=String(arg1).split(".")[1].length;}catch(e){r1=0;}
		    try{r2=String(arg2).split(".")[1].length;}catch(e){r2=0;}
		    m=Math.pow(10,Math.max(r1,r2));
		    return ((arg1*m-arg2*m)/m).toFixed(2);
		}
    	
		//乘法调用：accMul(arg1,arg2)
		//返回值：arg1乘以arg2的精确结果
		function accMul(arg1,arg2)
		{
		    var m=0,s1=String(arg1),s2=String(arg2);
		    try{m+=s1.split(".")[1].length;}catch(e){}
		    try{m+=s2.split(".")[1].length;}catch(e){}
		    return (Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)).toFixed(2);
		}
    	
		//除法调用：accDiv(arg1,arg2)
		//返回值：arg1除以arg2的精确结果
		function accDiv(arg1,arg2){
		    var t1=0,t2=0,r1,r2;
		    try{t1=String(arg1).split(".")[1].length;}catch(e){}
		    try{t2=String(arg2).split(".")[1].length;}catch(e){}
		    with(Math){
		        r1=Number(String(arg1).replace(".",""));
		        r2=Number(String(arg2).replace(".",""));
		        return ((r1/r2)*pow(10,t2-t1)).toFixed(2);
		    }
		}
    	
    </script>
</head>

<body>
	<div position="top">
        <table width="100%" class="my_toptoolbar_td">
            <tr>
                <td id="my_toptoolbar_td">
                    <div class="l-toolbar">&nbsp;${mname}</div>
                </td>
                <td align="right" width="50%">
                    <div id="toptoolbar">
                    </div>
                </td>
            </tr>
        </table>
    </div>
    
	<form name="editform" method="post" action="" id="editform">
		
		<input name="id" type="hidden" id="id" value="${bill.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		
		<table class="h2y_table" style="width:95%;">
		
			<tr>
				<td class="h2y_table_label_td">
					单据编号:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="billNo" id="billNo" type="hidden" value="${billNo }"/>
					<div id="billNoD">
						${billNo }
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="选择调出仓库" class="button" id="choose_exStorage_btn"/>：
				</td>
				<td class="h2y_table_edit_td">
					<input name="exStorageId"  id="exStorageId" type="hidden"/>
					<div id="chosen_exStorage">
					</div>
				</td>
				
				<td class="h2y_table_label_td">
					<input type="button" value="选择调入仓库" class="button" id="choose_inStorage_btn"/>：
				</td>
				<td class="h2y_table_edit_td">
					<input name="inStorageId" id="inStorageId" type="hidden"/>
					<div id="chosen_inStorage">
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="选择经手人" class="button" id="choose_broker_btn"/>：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="brokerId" id="brokerId" type="hidden"/>
					<div id="chosen_broker">
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					调拨日期：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="allocateDate" id="allocateDate" class="h2y_input_datetime" type="input" 
						value="<fmt:formatDate value="${bill.allocateDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="notes" id="notes" type="text" class="h2y_input_long"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					补单标记：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input type="radio" name="reviseMark" value="0" checked="checked"/>否&nbsp;&nbsp;
					<input type="radio" name="reviseMark" value="1"/>是
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="单据明细" class="button" id="choose_billGoods_btn"/>：
				</td>			
				<td class="h2y_table_edit_td2" colspan="3" id="chosen_billGoods">
				</td>				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					调拨总数：
				</td>			
				<td class="h2y_table_edit_td">
					<input name="totalCount" id="totalCount" type="text" class="h2y_input_just" disabled="disabled" />
				</td>
				
				<td class="h2y_table_label_td">
					合计差额：
				</td>			
				<td class="h2y_table_edit_td">
					<input name="totalAmountGap" id="totalAmountGap" type="text" class="h2y_input_just" disabled="disabled" />
				</td>
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					合计金额：
				</td>			
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="totalAmount" id="totalAmount" type="text" class="h2y_input_just" disabled="disabled" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					制单人：
				</td>
				<td class="h2y_table_edit_td">
					<input name="billMaker" id="billMaker" type="text" class="h2y_input_just" disabled="disabled" value="${billMaker.userName }"/>
					<input name="billMakerId" id="billMakerId" type="hidden" class="h2y_input_just" value="${billMaker.id }"/>
				</td>
							
				<td class="h2y_table_label_td">
					制单日期：
				</td>	
				<td class="h2y_table_edit_td">
					<input name="createDate" id="createDate" type="text" class="h2y_input_just" disabled="disabled" value="${billCreateDate }"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>