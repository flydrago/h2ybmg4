<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        $(function () {

        });
        

        function h2y_save() {
        	
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/importbag/doCheck.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
           	 	if (jsonReturn.code == "0") {
           	 		
           	 	 	alert(jsonReturn.msg);
                } else {
                	 alert(jsonReturn.msg);
                     parent.f_query();
                     frameElement.dialog.close();
                }
                isSubmiting = false;
            });
        }
        
		function h2y_refresh() {
	        document.location.reload();
	    }
        
    </script>
</head>

<body>

	<form name="editform" method="post" action="" id="editform">
		
		<input name="taskCode" type="hidden" id="taskCode" value="${taskCode}" />
		<input name="bagCode" type="hidden" id="bagCode" value="${bagCode}" />
			
		<table class="h2y_dialog_table">
			
			<tr>
				<td class="h2y_table_label_td">
					审核选项：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="resultCode" id="resultCode" type="radio" value="1" initoption="1,通过:0,不通过"/>	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td">
					<input name="memo" id="memo" type="input" value="" maxlength="200" class="h2y_dialog_input_long"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>