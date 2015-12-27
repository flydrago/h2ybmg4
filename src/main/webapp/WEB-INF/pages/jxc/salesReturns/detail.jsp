<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>

    <script type="text/javascript">
        $(function () {
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '审核' , click: h2y_audit , icon:'communication' },{ text: '办理过程' , click: h2y_auditLog , icon:'attibutes' },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        });
        
		//刷新页面
        function h2y_refresh() {
            document.location.reload();
        }
		
		function h2y_audit(){
			var billId = $("#id").val();
			var billNo = $("#billNo").val();
			var checkStatus = $("#billCheckStatus").val();	
			var auditBean = "salesReturnsBillAudit";
			
			if(checkStatus == "0"  || checkStatus == "-1"){
				var src = "<%=basePath%>jxc/common/billAudit.htm?op=audit&auditBean="+auditBean+"&id="+billId+"&billNo="+billNo;
		         $.ligerDialog.open({
		             name:"bill_check",
		             title:  "单据审核",
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
		                     f_getframe("bill_check").h2y_save();
		                 } },
		                 { text: '取消', onclick: function (item, dialog) {
		                     dialog.close();
		                 } }
		             ]
		         });
			}else{
				alert("订单已审核通过，无需重复审核！");
			}
		}
		
		
		function h2y_auditLog(){
			var billNo = $("#billNo").val();
			var src = "<%=basePath%>jxc/common/billAuditLog.htm?billNo="+billNo;
	        
	        $.ligerDialog.open({
	            name:"auditLog_dialog",
	            title:  "单据办理流程",
	            height: 450,
	            url: src,
	            width: 700,
	            showMax: true,
	            showToggle: true,
	            showMin: true,
	            isResize: true,
	            modal: true,
	            buttons: [
	                { text: '关闭', onclick: function (item, dialog) {
	                    	dialog.close();
	                } }
	            ]
	        });
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
		
		<input name="id" type="hidden" id="id" value="${billDetail.id}" />
		
		<table class="h2y_table" style="width:98%;">
		
			<tr>
				<td class="h2y_table_label_td">
					单据编号:
				</td>
				<td class="h2y_table_edit_td">
					<input name="billNo" id="billNo" type="hidden" value="${billDetail.billNo }"/>
					<div id="billNoD">
						${billDetail.billNo }
					</div>
				</td>
				
				<td class="h2y_table_label_td">
					审核状态：
				</td>
				
				<td class="h2y_table_edit_td">
					<div class="billCheckStatus">
							<input id="billCheckStatus" type="hidden" value="${billDetail.checkStatus }"/>
							<c:if test="${billDetail.checkStatus eq 0 }">
							<span style="  font-weight: bolder;color: black;">
								未审核
							</span>
							</c:if>
							<c:if test="${billDetail.checkStatus eq -1 }">
							<span style="  font-weight: bolder;color: red;">
								审核未通过
							</span>
							</c:if>
							<c:if test="${billDetail.checkStatus eq 1 }">
							<span style="  font-weight: bolder;color: green;">
								已审核
							</span>
							</c:if>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					客户：
				</td>
				<td class="h2y_table_edit_td">
					<input name="customerId"  id="customerId" type="hidden" value="${billDetail.customerId }"/>
					<div id="customerInfo">
						${billDetail.customer }
					</div>
				</td>
				
				<td class="h2y_table_label_td">
					退货仓库：
				</td>
				<td class="h2y_table_edit_td">
					<input name="storageId" id="storageId" type="hidden" value="${billDetail.storageId }"/>
					<div id="storageInfo">
						${billDetail.storage }
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					经手人：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="brokerId" id="brokerId" type="hidden" value="${billDetail.brokerId }"/>
					<div id="brokerInfo">
						${billDetail.broker }
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					退款账户：
				</td>
				<td class="h2y_table_edit_td">
					<input name="refundAccountId" id="refundAccountId" type="hidden" value="${billDetail.refundAccountId }"/>
					<div id="refundAccountInfo">
						${billDetail.refundAccount }
					</div>
				</td>

				<td class="h2y_table_label_td">
					本次退款：
				</td>
				<td class="h2y_table_edit_td">
					<input name="currentRefund" id="currentRefund" type="text"  class="h2y_input_just" value="${billDetail.currentRefund }" disabled="disabled"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					退货日期：
				</td>
				<td class="h2y_table_edit_td">
					<input name="returnsDate" id="returnsDate" class="h2y_input_datetime" type="input" 
						value="<fmt:formatDate value="${billDetail.returnsDate}"  pattern="yyyy-MM-dd HH:mm"/>" disabled="disabled"/>
				</td>
				
				<td class="h2y_table_label_td">
					退款日期：
				</td>
				<td class="h2y_table_edit_td">
					<input name="refundDate" id="refundDate" class="h2y_input_datetime" type="input"
						value="<fmt:formatDate value="${billDetail.refundDate}"  pattern="yyyy-MM-dd HH:mm"/>" disabled="disabled"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="notes" id="notes" type="text" class="h2y_input_long" value="${billDetail.notes }" disabled="disabled"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					补单标记：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
				<c:if test="${billDetail.reviseMark eq 0 }">
					<input type="radio" name="reviseMark" value="0" checked="checked" disabled="disabled"/>否&nbsp;&nbsp;
					<input type="radio" name="reviseMark" value="1" disabled="disabled"/>是
				</c:if>
				<c:if test="${billDetail.reviseMark eq 1 }">
					<input type="radio" name="reviseMark" value="0"  disabled="disabled"/>否&nbsp;&nbsp;
					<input type="radio" name="reviseMark" value="1" checked="checked" disabled="disabled"/>是
				</c:if>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					单据明细：
				</td>			
				<td class="h2y_table_edit_td2" colspan="3" id="billGoods">
						<table class="h2y_table" id="billGoodsTable" style="width:100%;">
    						<tr>
    							<td class="h2y_table_label_td" style="text-align: center;width:30%;">名称</td>
    							<td class="h2y_table_label_td" style="text-align: center;">数量</td>
    							<td class="h2y_table_label_td" style="text-align: center;">退货单价</td>
    							<td class="h2y_table_label_td" style="text-align: center;">金额</td>
    							<td class="h2y_table_label_td" style="text-align: center;">备注</td>
    						</tr>
							<c:forEach items="${billGoods }"  var="billGood">
								<tr id="billGoods_tr_${billGood.GOODS_ID }" class="billGoodsDetail">
    		  						<td class="h2y_table_edit_td">${billGood.GOODS_NICK_NAME }<input name="billGoodsId" class="billGoodsId" type="hidden" value="${billGood.GOODS_ID }"/></td>
    		  						<td class="h2y_table_edit_td"><input name="billGoodsCount" id="billGoodsCount${billGood.GOODS_ID }" type="text" class="h2y_input_just" style="width:100px;"  value="${billGood.GOODS_COUNT }"  disabled="disabled"/></td>
    		  						<td class="h2y_table_edit_td"><input name="singlePrice" id="billGoodsSinglePrice${billGood.GOODS_ID }" type="text" class="h2y_input_just" style="width:100px;"  value="${billGood.SINGLE_PRICE }"  disabled="disabled"/></td>
    		  						<td class="h2y_table_edit_td"><input name="billGoodsTotalAmount" id="billGoodsTotalAmount${billGood.GOODS_ID }" class="h2y_input_just" type="text" class="h2y_input_just" style="width:100px;" value="${billGood.TOTAL_AMOUNT }" disabled="disabled"/></td>
    		  						<td class="h2y_table_edit_td"><input name="billGoodsNotes" id="billGoodsNotes${billGood.GOODS_ID }" type="text" class="h2y_input_just" value="${billGood.NOTES }" disabled="disabled"/></td>
    		  					</tr>	
							</c:forEach>
						</table>    								     
				</td>				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					合计金额：
				</td>			
				<td class="h2y_table_edit_td">
					<input name="totalAmount" id="totalAmount" type="text" class="h2y_input_just" disabled="disabled" value="${billDetail.totalAmount }"/>
				</td>
				
				<td class="h2y_table_label_td">
					整单折扣：
				</td>
				<td class="h2y_table_edit_td">
					<input name="discount" id="discount" type="text" class="h2y_input_just" value="${billDetail.discount }"  disabled="disabled"/>
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					应退金额：
				</td>			
				<td class="h2y_table_edit_td">
					<input name="refundableAmount" id="refundableAmount" type="text" class="h2y_input_just" value="${billDetail.refundableAmount }" disabled="disabled" />
				</td>

				<td class="h2y_table_label_td">
					整单优惠：
				</td>		
				<td class="h2y_table_edit_td">
					<input name="discountAmount" id="discountAmount" type="text"  class="h2y_input_just" value="${billDetail.discountAmount }" disabled="disabled" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					制单人：
				</td>
				<td class="h2y_table_edit_td">
					<input name="billMaker" id="billMaker" type="text" disabled="disabled" class="h2y_input_just" value="${billDetail.billMaker }"/>
				</td>
							
				<td class="h2y_table_label_td">
					制单日期：
				</td>	
				<td class="h2y_table_edit_td">
					<input name="createDate" id="createDate" type="text" class="h2y_input_just" disabled="disabled" 
					value="<fmt:formatDate value="${billDetail.createDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</td>
			</tr>
		</table>
	</form>


</body>
</html>