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
            }else{
            	
            	$("input[type=radio][name='dictType']:first").attr("checked",true);
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#dictOrd").ligerSpinner({type: 'int' ,height: 25});
            
            if($("#parentId").val()=="0"){
            	
            	$("#dictCode").attr("disabled",true);
            	$("#dictValue").attr("disabled",true);
            	$("[name='dictType']:radio").attr("disabled",true);
            	$("[name='ifSys']:radio").attr("disabled",true);
            	$("[name='ifUserConf']:radio").attr("disabled",true);
            	$("[name='isExtends']:radio").attr("disabled",true);
            }
            
            dictChange($("[name='dictType']:radio:checked").val());
            $("[name='dictType']:radio").change(function(){
            	dictChange(this.value);
            });
            
            //form = $("form").ligerForm();
        });
        
        function dictChange(value){
        	//alert(value);
        	if(value=="dict"){
        		$("[name='isExtends']:radio").attr("disabled",false);
        	}else{
        		$("[name='isExtends']:radio").attr("disabled",true);
        	}
        }


        function h2y_save() {
        	
        	if(!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/sysdictmain/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.h2y_refresh();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        var ifnext = $("input[@type=radio][name=next][checked]").val();
                        if (ifnext == 1) {
                            $("#dictCode").val("");
                            $("#dictName").val("");
                            $("#dictValue").val("");
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

</head>

<body>
	<form name="editform" method="post" action="" id="editform">
		<input type="hidden" name="op" value="${op}" /> 
		<input type="hidden" name="id" value="${sysDictMain.id}" />
		<input type="hidden" id="parentId" name="parentId" value="${sysDictMain.parentId}" />
		
		<table class="h2y_dialog_table">

			<tr>
				<td class="h2y_table_label_td">编码:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="dictCode" type="text" id="dictCode" class="h2y_input_just"
						value="${sysDictMain.dictCode}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="dictName" type="text" id="dictName" class="h2y_input_just"
					value="${sysDictMain.dictName}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">附加信息:</td>
				<td class="h2y_dialog_table_edit_td">
					<textarea class="h2y_dialog_textarea" id="dictValue" name="dictValue">${sysDictMain.dictValue}</textarea>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">类型:</td>
				<td class="h2y_dialog_table_edit_td">
                    <h2y:input name="dictType" id="dictType" type="radio" initoption="dict,字典:json,JSON:sql,SQL:param,参数" value="${sysDictMain.dictType}"/>
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">属性:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="ifSys" id="ifSys" type="radio" initoption="1,系统参数:0,单位参数" value="${sysDictMain.ifSys}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">菜单配置:</td>
				<td class="h2y_dialog_table_edit_td">
                    <h2y:input name="ifUserConf" id="ifUserConf" type="radio" initoption="1,允许:0,不允许" value="${sysDictMain.ifUserConf}"/>
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">继承:</td>
				<td class="h2y_dialog_table_edit_td">
                    <h2y:input name="isExtends" id="isExtends" type="radio" initoption="1,可继承:0,不可继承" value="${sysDictMain.isExtends}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">排序:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="dictOrd" type="text" id="dictOrd" value="${sysDictMain.dictOrd}" />
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