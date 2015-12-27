<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

	<style type="text/css">
		.goodstype_select{ 
			 border: 1px solid #aecaf0;
			 height:25px;
			 line-height: 25px;
		 }
	</style>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}"
        
        var contentEditor = null;
        var typeIndex = 0;
        var markDataJson = null;
        
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
           	if("${coupons.isLimitGoods}"=="1"){
           		
           		 $("#goodslistdiv").html("<a href=\"javaScript:h2y_detail(${goodsPrice.id});\">${goodsPrice.goodsNickName}<a/><br/>市场价：￥${goodsPrice.marketPrice}<br/>会员价：￥${goodsPrice.memberPrice}");
                    $("#coupons_limitGoods").val(${goodsPrice.id});
           	}
           	
           	if("${coupons.isLimitType}"=="1"){
           		
           		 var couponsLimitType = "${coupons.limitType}";
                 var couponsLimitType_array = couponsLimitType.split("_");
                 modifyselect(couponsLimitType_array);
       		}
           	
           	if("${coupons.isLimitMark}"=="1"){
           		
	           	$("#markHtml_show").html("${goodsMark.markName}：${goodsMarkInfo.name}");
	           	$("#coupons_limitMark").val(${goodsMarkInfo.id});
       		}
        });
        
		function getTypeCode(typeCode_array,index){
			
			var typeCodeTemp = "";
			for (var i = 0; i < index; i++) {
            	if(typeCodeTemp==""){
            		typeCodeTemp = typeCode_array[i];
            	}else{
            		typeCodeTemp += "_"+typeCode_array[i];
            	}
			}
			return typeCodeTemp;
		}
        
		function modifyselect(typeCode_array){
			
			var pid = typeIndex==0 ? 0 : typeCode_array[typeIndex-1];
			
			$.post("business/goodstype/getSelectList.htm",{id:pid},function(data){
				
				typeIndex++;
				var jsonReturn=eval("("+data+")");
				var selectHtml = "";
				$(jsonReturn).each(function(){
					if(getTypeCode(typeCode_array,typeIndex)==this.code){
						selectHtml += "<option value=\""+this.code+"\" selected=\"selected\">"+this.name+"</option>";
					}else{
						selectHtml += "<option value=\""+this.code+"\">"+this.name+"</option>";
					}
				});
				
				if(selectHtml!=""){
					selectHtml = "<select id=\"goodstype_select_"+typeIndex+"\" class=\"goodstype_select\"><option value=\"\">--请选择--</option>"+selectHtml+"</select>";
					$("#goods_type_td").append(selectHtml+"");
					if(typeIndex<=typeCode_array.length){
						modifyselect(typeCode_array,typeIndex);
					}
				}
			}); 
		}
		
		
        function h2y_refresh() {
            document.location.reload();
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
                    <div id="toptoolbar"></div>
                </td>
            </tr>
        </table>
    </div>
    
	<form name="editform" method="post" action="" id="editform">
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					${coupons.couponsName}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<fmt:formatDate value="${coupons.startDate}"  pattern="yyyy-MM-dd HH:mm"/>~
					<fmt:formatDate value="${coupons.endDate}"  pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					活动商品：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isActivity" id="coupons_isActivity" type="radio" disabled="disabled" initoption="0,不支持:1,支持" value="${coupons.isActivity}"/>
				</td>
				<td class="h2y_table_label_td">
					金额：
				</td>
				<td class="h2y_table_edit_td">
					${coupons.couponsBalance}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					限额：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitAmount" id="coupons_isLimitAmount" type="radio" disabled="disabled" initoption="0,不限制:1,限制" value="${coupons.isLimitAmount}"/>
				</td>
				<td class="h2y_table_label_td">
					订单限额：
				</td>
				<td class="h2y_table_edit_td">
					${coupons.limitAmount}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					限制类型：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitType" id="coupons_isLimitType" type="radio" disabled="disabled" initoption="0,不限制:1,限制" value="${coupons.isLimitType}"/>
				</td>
				<td class="h2y_table_label_td">
					类型：
				</td>
				<td class="h2y_table_edit_td" id="goods_type_td">
					
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					限制标签：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitMark" id="coupons_isLimitMark" type="radio" disabled="disabled" initoption="0,不限制:1,限制" value="${coupons.isLimitMark}"/>
				</td>
				<td class="h2y_table_label_td">
					选择标签：
				</td>
				<td class="h2y_table_edit_td">
					<div id="markHtml_show">
						
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					限制商品：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitGoods" id="coupons_isLimitGoods" type="radio" disabled="disabled" initoption="0,不限制:1,限制" value="${coupons.isLimitGoods}"/>
				</td>
				<td class="h2y_table_label_td">
					选择商品：
				</td>
				<td class="h2y_table_edit_td">
					<div id="goodslistdiv">
					</div>
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					限制平台：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitPlatform" id="coupons_isLimitPlatform" type="radio" disabled="disabled" initoption="0,不限制:1,限制" value="${coupons.isLimitPlatform}"/>
				</td>
				<td class="h2y_table_label_td">
					平台：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.limitPlatform" id="coupons_limitPlatform" type="radio" disabled="disabled" dictcode="couponsLimitPlatform" value="${coupons.limitPlatform}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${coupons.memo}
				</td>
			</tr>
			
			<tr id="subjectContent_tr">
				<td class="h2y_table_label_td">
					规则详细：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${couponsDetail.couponsRule}
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>