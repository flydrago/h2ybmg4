<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
	<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
	<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
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
        		items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },
        	            { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]
        	});
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }
           
           	$("#startDate").datetimepicker({});
            $("#endDate").datetimepicker({});
            
            $("#uploadScanBtn").click(function(){
            	uploadScan();
            });
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
        });

		//保存
        function h2y_save() {
        	
        	if(!Validator.form()) return;
			
        	if(!compareTime("startDate", "endDate")) return;
        	if(!judgeHadImage()) return;
        
        	
            var queryString = $('#editform').serialize();
			
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/traceproviderpact/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                       /*  parent.f_query();
                        frameElement.dialog.close(); */
                       if (top.f_getframe("business_traceproviderpact_init_htm") != null) {
							top.f_getframe("business_traceproviderpact_init_htm").f_query();
						 }
						top.f_delTab("business_traceproviderpact_add" + parentId); 
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                      //  parent.f_query();
                      //  frameElement.dialog.close();
                        if (top.f_getframe("business_traceproviderpact_init_htm") != null) {
							top.f_getframe("business_traceproviderpact_init_htm").f_query();
						 }
						top.f_delTab("business_traceproviderpact_add" + parentId); 
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
		//刷新
        function h2y_refresh() {
            document.location.reload();
        }
        
		//上传图片
        function uploadScan(){
           	openFileUploadDialog({
           		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
           		multi:true
           	});
        }
        
		//上传回调
		function h2y_fileUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	$(data).each(function(){
        		
        		var json_str = "{\"fileType\":\"1\",\"fileName\":\""+this.fileName+"\",\"saveName\":\""+this.saveName+"\",\"savePath\":\""+this.savePath+"\",\"fileSize\":\""+this.fileSize+"\"}";
        		$("#pactpic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"packPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+this.savePath+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"pactPicImg\" src=\"common/image/view.htm?URLPATH="+this.savePath+"\"></a><a id=\"file_delete_href_"+fileId+"\" href=\"javascript:deletePic('"+fileId+"');\">删除</a>");
        		fileId++;
        	});
        	
        	$(".packPicImg_lightbox").lightBox();
        }
		
		//删除图片
		function deletePic(fileId){
        	
        	if(!confirm("您确定要删除文件吗?")){
				return true;
			}
        	$("#file_input_"+fileId).remove();
        	$("#file_lightbox_"+fileId).remove();
        	$("#file_img_"+fileId).remove();
        	$("#file_delete_href_"+fileId).remove();
		}
		
		//比较时间
		function compareTime(startId, endId) { 
			
			var startDate = $("#"+startId).val();
			var endDate = $("#"+endId).val();
			if(startDate==null || endDate==null || startDate=="" || endDate==""){
				alert("时间不能为空！");
				return false;
			} 
			startDate = startDate.replace(/[:-\s]/g,"");
			endDate = endDate.replace(/[:-\s]/g,"");
			if (parseInt(startDate) >= parseInt(endDate)) {   
		        alert("开始时间不能大于截止时间！");   
		        return false;   
			} 
		    return true;   
		}
		
		//判断上传图片
		function judgeHadImage(){
			if($("#pactpic_div").html().replace(/\s/g, "") == ""){
				alert("请上传合同扫描件");
				return false;
			}else{
				return true;
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
                <div id="toptoolbar"></div>
            </td>
        </tr>
    </table>
</div>
<form name="editform" method="post" action="" id="editform">
        <input type="hidden" name="parentId" value="${parentId}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_table"><!-- h2y_dialog_table" -->

        <tr>
            <td class="h2y_table_label_td">供应商名称:</td>
            <td class="h2y_dialog_table_edit_td"  colspan="3">
                <input name="providerName" type="text" id="providerName"  class="h2y_dialog_input_long" readonly="readonly" value="${parentName}" style="padding-left:5px;"/>
                <input name="providerId" type="hidden" id="providerId" readonly="readonly" value="${parentId}"/>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td">合同开始时间:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="startDate" id="startDate" class="h2y_input_datetime" readonly="readonly" type="input" value="<fmt:formatDate value="${providerpact.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
            </td>
            <td class="h2y_table_label_td">合同结束时间:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="endDate" id="endDate" class="h2y_input_datetime" readonly="readonly" type="input" value="<fmt:formatDate value="${providerpact.endDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
            </td>
        </tr>
       
  	 <tr>
           <td class="h2y_table_label_td">
          	 <input type="button" id="uploadScanBtn"  value="上传扫描件" style="padding-left:5px;padding-right:5px;"/>
           </td>
           	<td class="h2y_table_edit_td2" colspan="3">
				<div id="pactpic_div"></div>
			</td>
       </tr>
    </table>
</form>

</body>
</html>