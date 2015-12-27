<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>

	<style type="text/css">
		.goodstype_select{ 
			 border: 1px solid #aecaf0;
			 height:25px;
			 line-height: 25px;
		 }
		 .goodsLogoImg { max-width:600px;}
		 .goodsPicImg {
		 	width:240px;
		 	height:240px;
		 }
	</style>

    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;

        var typeIndex = 0;
        var gdsCode = "";
        var markDataJson = null;
        
        
        var introduceEditor = null;
        var specParamEditor = null;
        
        var fileId = 0;
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
        	h2y_markSelectCallBack(${markData});
        	gdsCode = "${goods.gdsCode}";
            var typeCode_array = gdsCode.split("_");
            modifyselect(typeCode_array);
            $(${fileDataListJson}).each(function(){
            	
           		var json_str = "{\"id\":\""+this.id+"\"}";
           		if(this.fileType==1){
           			$("#goodspic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"goodsPicImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>");
           		}else{
           			if(this.ord==2){
           				$("#goodslogo_div").append("<input type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>");
           			}else{
           				$("#goodslogo_div").append("<input type=\"hidden\" name=\"picData\"  value='"+json_str+"'/>");
           			}
           		}
           		fileId++;
            });
            $(".goodsPicImg_lightbox").lightBox();
            $(".goodsLogoImg_lightbox").lightBox();
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
					selectHtml = "<select id=\"goodstype_select_"+typeIndex+"\" class=\"goodstype_select\"><option value=\"\"> </option>"+selectHtml+"</select>";
					$("#goodstype_td").append(selectHtml+"");
					if(typeIndex<=typeCode_array.length){
						modifyselect(typeCode_array,typeIndex);
					}
				}
			}); 
		}

        function h2y_refresh() {
            document.location.reload();
        }
        
        //标签回调
        function h2y_markSelectCallBack(data){
        	markDataJson = data;
        	var markHtml = "";
        	$(data).each(function(){
        		
        		if(markHtml==""){
        			markHtml = this.MARK_NAME+":"+this.NAME;
        		}else{
        			markHtml += "</br>"+this.MARK_NAME+":"+this.NAME;
        		}
        	});
        	$("#markHtml_show").html(markHtml);
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
					${goods.goodsName}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品编码：
				</td>
				<td class="h2y_table_edit_td">
					${goods.goodsNumber}
				</td>
				<td class="h2y_table_label_td">
					库存：
				</td>
				<td class="h2y_table_edit_td">
					${goods.inventory}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品包装：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="goodsUnit" id="goodsUnit" type="radio" disabled="disabled" dictcode="goods_unit" value="${goods.goodsUnit}"/>
				</td>
				<td class="h2y_table_label_td">
					商品规格：
				</td>
				<td class="h2y_table_edit_td">
					1x${goods.spec}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					指导价：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goods.minPrice}~${goods.maxPrice}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					会员价：
				</td>
				<td class="h2y_table_edit_td">
					${goods.memberPrice}
				</td>
				<td class="h2y_table_label_td">
					市场价：
				</td>
				<td class="h2y_table_edit_td">
					${goods.marketPrice}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					销售价：
				</td>
				<td class="h2y_table_edit_td">
					${goods.sellPrice}
				</td>
			
				<td class="h2y_table_label_td">排序：</td>
	            <td class="h2y_table_edit_td">
	                ${goods.ord}
	            </td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goods.memo}
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					允许使用虚拟货币：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isAllowGrade" id="isAllowGrade" type="radio" initoption="0,否:1,是" value="${goods.isAllowGrade}" />		
					
				</td>
				
				<td class="h2y_table_label_td">
					允许使用虚拟货币数量：
				</td>
				<td class="h2y_table_edit_td">
					${goods.limitGradeNumber}
				</td>
			
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否赠送达人币：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isGiftGrade" id="isGiftGrade" type="radio" initoption="0,否:1,是" value="${goods.isGiftGrade}" />								
				</td>
				
				<td class="h2y_table_label_td">
					赠送达人币数量：
				</td>
				<td class="h2y_table_edit_td">
					${goods.limitGiftNumber}
				</td>
				
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否限购：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isLimitSell" id="isLimitSell" type="radio" initoption="0,否:1,是" value="${goods.isLimitSell}" />								
				</td>
			
				<td class="h2y_table_label_td">限购数量：</td>
	            <td class="h2y_table_edit_td">
	                ${goods.limitSellNumber}		
	            </td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					商城是否可见：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<h2y:input name="isMallVisible" id="isMallVisible" type="radio" initoption="0,否:1,是" value="${goods.isMallVisible}"/>					
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品一维码：
				</td>
				<td class="h2y_table_edit_td">
					${goods.gdsQr1}
				</td>
			
				<td class="h2y_table_label_td">商品内码：</td>
	            <td class="h2y_table_edit_td">
	                ${goods.gdsQrInside}
	            </td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品类型：
				</td>
				<td id="goodstype_td" class="h2y_table_edit_td2" colspan="3">
					
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品标签：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="markHtml_show">
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品介绍：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsInfo.introduce}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					规格参数：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsInfo.specParam}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品Logo：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodslogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品图片：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodspic_div"></div>
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>