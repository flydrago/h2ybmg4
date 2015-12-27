<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>

<style>
    #loading {   
        text-align:center; 
        color:#1e90ff;
        font:bold 20px verdana,tahoma,helvetica;   
		Z-index: 1;
    }   
</style> 


<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '导入' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});

            $("#importUserBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.xls;*.xlsx",
            		multi:false
            	});
            });
        });
        

        function h2y_save() {
        	
            var queryString = $('#editform').serialize();
            
            var fileData = $("#fileData").val();
            if(fileData==undefined || fileData==""){
            	alert("请上传导入文件！");
            	return;
            }

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
            
            $("#fileData_div").html("");
            $("#importMsg_div").html("");
            $("#loading").show();
            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/importbag/importUser.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                $("#importMsg_div").html(jsonReturn.resultMsg);
                $("#loading").hide();
                isSubmiting = false;
            });
        }
        
  		function h2y_fileUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
        	$("#fileData_div").html("<input type=\"hidden\" id=\"fileData\" name=\"fileData\"  value='"+json_str+"'/><span>"+data[0].fileName+"</span>");
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
		<input name="bagCode" type="hidden" id="bagCode" value="${importBag.bagCode}" />
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="导入人员" class="button" id="importUserBut"/>:
				</td>
				<td class="h2y_table_edit_td2">
					<div id="fileData_div"></div>
					<div id="importMsg_div"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="loading" style="display: none;">
						导入进行中,请耐心等待……<img src="<%=uiPath%>lib/ligerUI/skins/Aqua/images/common/loading.gif" alt="" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>