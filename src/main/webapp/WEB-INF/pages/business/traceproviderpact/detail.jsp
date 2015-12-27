<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
	<style type="text/css">
		.goodsInput {
			padding-left: 5px;
			border: 0;
			border-bottom: 1px solid #D1E8FF;
		}
		.pactPicImg { 
		 	width:240px;
		 	height:240px;
		 }
	</style>
	
    <script type="text/javascript">
       var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var parentId = "${parentId}";
        var form = null;
        var fileId = 0;
      
        $(function () {
        	$("#toptoolbar").ligerToolBar({
        		items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]
        	});
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }
          
        	h2y_showPic(${traceScanningPathList});
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
        });

 		function h2y_showPic(fileData){
        	
        	$("#pactpic_div").html("");
        	var imageHtml = "";
        	$(fileData).each(function(){
            	
           		var json_str = "{\"id\":\""+this.ID+"\"}";
           	
           		$("#pactpic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"pactPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=traceProviderPactService&id="+this.ID+"\" title=\""+this.FILE_NAME+"\"><img  id=\"file_img_"+fileId+"\" class=\"pactPicImg\" src=\"common/image/view.htm?fileBean=traceProviderPactService&id="+this.ID+"\"></a>");
           		
           		fileId++;
            });
        	
            $(".pactPicImg_lightbox").lightBox();
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
      <%--   <input type="hidden" name="parentId" value="${parentId}"/> --%>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_table"><!-- h2y_dialog_table" -->

        <tr>
            <td class="h2y_table_label_td">供应商名称:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3">
                <input name="providerName" type="text" id="providerName"  class="h2y_dialog_input_long" readonly="readonly" value="${traceProviderPact.providerName}" style="padding-left:5px;"/>
                <input name="providerId" type="hidden" id="providerId" readonly="readonly" value="${traceProviderPact.provideId}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">合同开始时间:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="startDate" id="startDate" class="h2y_input_datetime" readonly="readonly" type="input" style="padding-left:5px;" value="<fmt:formatDate value="${traceProviderPact.startDate}"  pattern="yyyy-MM-dd"/>"/>
            </td>
            <td class="h2y_table_label_td">合同结束时间:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="endDate" id="endDate" class="h2y_input_datetime" readonly="readonly" type="input" style="padding-left:5px;" value="<fmt:formatDate value="${traceProviderPact.endDate}"  pattern="yyyy-MM-dd"/>" />
            </td>
        </tr> 
       
  	 <tr>
           <td class="h2y_table_label_td">合同扫描件:</td>
           	<td class="h2y_table_edit_td2" colspan="3">
				<div id="pactpic_div"></div>
			</td>
       </tr>
    </table>
</form>

</body>
</html>