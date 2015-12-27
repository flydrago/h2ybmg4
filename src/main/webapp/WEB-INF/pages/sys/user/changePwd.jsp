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
        var form = null;
        $(function () {

            //form = $("form").ligerForm();
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
        });


        function h2y_save() {
        	
			if(!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/user/savePwd.htm", queryString, function (data) {
            	
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    frameElement.dialog.close();
                } else {
                    alert(jsonReturn.msg);
                }
                isSubmiting = false;
            });
        }

    </script>

</head>

<body>
	<form name="editform" method="post" action="" id="editform">
		<div>
			<input type="hidden" name="op" value="${op}" /> 
			<input type="hidden" name="id" value="${sysRole.id}" />
		</div>
		
		<table class="h2y_dialog_table">

			<tr>
				<td class="h2y_table_label_td">密码:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="oldPassword" type="password" class="h2y_input_just" id="oldPassword" 
						value="" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">新密码:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="newPassword" type="password" class="h2y_input_just" id="newPassword" 
						value="" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">确认密码:</td>
				<td class="h2y_dialog_table_edit_td">
					<input type="password" id="confimPassword" class="h2y_input_just"
					value="" /></td>
			</tr>
		</table>
	</form>
</body>
</html>