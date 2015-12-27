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

        	//$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }else{
            	$("input[type=radio][name='columnType']:first").attr("checked",true);
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
            $.post("business/advertcolumn/save.htm", queryString, function (data) {

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
                        parent.f_query();
                        var ifnext = $("input[@type=radio][name=next][checked]").val();
                        if (ifnext == 1) {
                            $("#columnName").val("");
                            $("#memo").val("");
                        } else {
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
		
		<input name="id" type="hidden" id="id" value="${advertColumn.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_dialog_table">
			
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td">
					<input name="columnName" type="text" id="columnName" class="h2y_dialog_input_long"
							value="${advertColumn.columnName}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					类型：
				</td>
				<td class="h2y_table_edit_td">
					<!-- 平台栏位无需分配、代理商栏位需要分配 -->
					<h2y:input name="columnType" id="columnType" type="radio" initoption="0,公共栏位:1,代理商栏位:2,平台栏位" value="${advertColumn.columnType}"/>	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td">
					<input name="memo" id="memo" type="input" value="${advertColumn.memo}"  class="h2y_dialog_input_long"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					<input name="ord" id="ord" type="input" value="${advertColumn.ord}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${advertColumn.status}"/>
				</td>
			</tr>
			
			<tr id="tr_next">
	            <td class="h2y_table_label_td">下一步:</td>
	            <td class="h2y_table_edit_td">
	                <h2y:input name="next" id="next" type="radio" initoption="1,继续添加:0,返回列表" value="1"/>
	            </td>
	        </tr>
		</table>
	</form>
</body>
</html>