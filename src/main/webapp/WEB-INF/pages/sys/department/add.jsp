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


            //$.metadata.setType("attr", "validate");
            //form = $("form").ligerForm();

            if (op == "modify") {
                $("#tr_next").hide();
            } else {
            	
            	$("input[type=radio][name='reverse1']:first").attr("checked",true);
            }
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            
            $("#deptOrd").ligerSpinner({type: 'int' ,height: 25,width:194});
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
            $.post("sys/department/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.h2y_refresh();
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        var ifnext = $("input[@type=radio][name=next][checked]").val();

                        if (ifnext == 1) {

                            $("#deptName").val("");
                            $("#deptShortName").val("");
                            $("#deptDesc").val("");
                        } else {

                            parent.h2y_refresh();
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
			<input type="hidden" name="id" value="${sysDepartment.id}" /> 
			<input type="hidden" name="op" value="${op}" /> 
			<input type="hidden" name="parentId" value="${sysDepartment.parentId}" />
			<input type="hidden" name="deptType" value="${sysDepartment.deptType}" />
		</div>
		<table class="h2y_dialog_table">

			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="deptName" type="text" id="deptName"  class="h2y_input_just" 
						value="${sysDepartment.deptName}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">简称:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="deptShortName" type="text" id="deptShortName"  class="h2y_input_just" 
					value="${sysDepartment.deptShortName}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">排序:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="deptOrd" type="text" id="deptOrd" value="${sysDepartment.deptOrd}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">描述:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="deptDesc" type="text" id="deptDesc" class="h2y_dialog_input_long" 
					value="${sysDepartment.deptDesc}" /></td>
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