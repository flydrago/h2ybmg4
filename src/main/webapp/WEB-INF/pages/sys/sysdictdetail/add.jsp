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
        var unitId = "${unitId}";
        var isExtends = "${sysDictMain.isExtends}";
        $(function () {

            if (op == "modify") {
                $("#tr_next").hide();
            } else {
            }
            
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#ord").ligerSpinner({type: 'int' ,height: 25});
            
            if(unitId!="1" && isExtends=="1" && "${sysDictMain.dictCode}" != "topSearch"){
            	$("#code").attr("disabled",true);
            }
            //form = $("form").ligerForm();
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
            $.post("sys/sysdictdetail/save.htm", queryString, function (data) {

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
                            $("#code").val("");
                            $("#value").val("");
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

    </script>

</head>

<body>
	<form name="editform" method="post" action="" id="editform">
		<div>
			<input type="hidden" name="op" value="${op}" /> 
			<input type="hidden" name="id" value="${sysDictDetail.id}" />
			<input type="hidden" name="dictMainId" value="${sysDictDetail.dictMainId}" />
		</div>
		
		<table class="h2y_dialog_table">

			<tr>
				<td class="h2y_table_label_td">编码:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="code" type="text" id="code" class="h2y_input_just"
						value="${sysDictDetail.code}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="value" type="text" id="value" class="h2y_input_just"
					value="${sysDictDetail.value}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">备注:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="memo" type="text" id="memo" class="h2y_dialog_input_long"
					value="${sysDictDetail.memo}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">排序:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="ord" type="text" id="ord" value="${sysDictDetail.ord}" />
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