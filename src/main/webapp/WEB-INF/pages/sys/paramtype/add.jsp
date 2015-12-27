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
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            
            $("#ord").ligerSpinner({type: 'int' ,height: 25});
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
            $.post("sys/paramtype/save.htm", queryString, function (data) {

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
                            $("#typeCode").val("");
                            $("#typeName").val("");
                            $("#memo").val("");
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
        
        function h2y_refresh() {
            document.location.reload();
        }
    </script>
</head>

<body>

	<form name="editform" method="post" action="" id="editform">
		
		<input name="id" type="hidden" id="id" value="${sysParamType.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_dialog_table">
			
			<tr>
				<td class="h2y_table_label_td">
					编码：
				</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="typeCode" type="text" id="typeCode" class="h2y_input_just"
							value="${sysParamType.typeCode}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="typeName" type="text" id="typeName" class="h2y_input_just"
							value="${sysParamType.typeName}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="ord" type="text" id="ord" value="${sysParamType.ord}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="memo" type="text" id="memo" class="h2y_dialog_input_long"
							value="${sysParamType.memo}" />	
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