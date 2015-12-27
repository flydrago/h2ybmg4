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
        var op = "${op}"
        var form = null;

        $(function () {

            //form = $("form").ligerForm();
            
            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#roleOrd").ligerSpinner({type: 'int' ,height: 25});
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
            $.post("sys/role/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        var ifnext = $("input[@type=radio][name=next][checked]").val();
                        if (ifnext == 1) {
                            $("#roleName").val("");
                            $("#roleDesc").val("");
                        } else {
                            parent.f_query();
                            frameElement.dialog.close();
                        }
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }

    </script>

<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>


</head>

<body>
	<form name="editform" method="post" action="" id="editform">
		<div>
			<input type="hidden" name="op" value="${op}" /> 
			<input type="hidden" name="id" value="${sysRole.id}" />
		</div>
		
		<table class="h2y_dialog_table">

			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_table_edit_td">
					<input name="roleName" type="text" id="roleName"  class="h2y_input_just"
					value="${sysRole.roleName}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">描述:</td>
				<td class="h2y_table_edit_td">
					<input name="roleDesc" type="text" id="roleDesc" class="h2y_input_just"
					value="${sysRole.roleDesc}" /></td>
			</tr>
			
			<%--
			<tr>
				<td class="h2y_table_label_td">系统角色:</td>
				<td class="h2y_table_edit_td">
					<div id="if_sys"></div>
			</tr>
			 --%>
			 
			<c:choose>
			 	<c:when test="${userId==-1}">
			 		 <input name="ifSys" id="if_sys" type="hidden" value="1"/>
					 <tr>
						<td class="h2y_table_label_td">特权角色:</td>
						<td class="h2y_table_edit_td">
							<h2y:input name="ifPrivilege" id="if_privilege" type="radio" initoption="1,是:0,否" value="${sysRole.ifPrivilege}"/>
						</td>
					 </tr>	
			 	</c:when>
			 	<c:otherwise>
			 		<input name="ifSys" id="if_sys" type="hidden" value="0"/>
			 	</c:otherwise>
			</c:choose>
			
			<tr>
				<td class="h2y_table_label_td">排序:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="roleOrd" type="text" id="roleOrd" value="${sysRole.roleOrd}"/>
				</td>
			</tr>
			
			<tr id="tr_next">
	            <td class="h2y_table_label_td">下一步:</td>
	            <td class="h2y_dialog_table_edit_td">
	                <h2y:input name="next" id="next" type="radio" initoption="1,继续添加:0,返回列表" value="1"/>
	            </td>
	        </tr>
		</table>
	</form>

</body>
</html>