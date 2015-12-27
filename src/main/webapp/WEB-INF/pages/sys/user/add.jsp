<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;

        $(function () {

  
            
            if (op == "modify") {
                $("#tr_next").hide();              
            } else {
            	$("input[type=radio][name='next']:first").attr("checked",true);
               
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            //form = $("form").ligerForm();
            
            $("#duOrd").ligerSpinner({type: 'int' ,height: 25,width:194});
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
            $.post("sys/user/save.htm", queryString, function (data) {

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
                            $("#sysUser_userName").val("");
                            $("#sysUser_account").val("");
                            $("#sysUser_mobile").val("");
                            $("#sysUser_email").val("");
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


</style>


</head>

<body>
	<form name="editform" method="post" action="" id="editform">
		<div>
			<input type="hidden" name="sysUser.id" value="${sysUser.id}" /> 
			<input type="hidden" name="op" value="${op}" /> 
			<input type="hidden" name="sysDeptUser.id" value="${sysDeptUser.id}" />
			<input type="hidden" name="sysDeptUser.deptId" value="${sysDeptUser.deptId}" />
		</div>
		
		<table class="h2y_dialog_table">			

			<tr>
				<td class="h2y_table_label_td">用户名:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysUser.userName" type="text" id="sysUser_userName" class="h2y_input_just" 
						value="${sysUser.userName}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">账号:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysUser.account" type="text" id="sysUser_account" class="h2y_input_just" 
					value="${sysUser.account}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">电话:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysUser.mobile" type="text" id="sysUser_mobile" class="h2y_input_just" 
					value="${sysUser.mobile}" /></td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">邮箱:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysUser.email" type="text" id="sysUser_email" class="h2y_input_just" 
					value="${sysUser.email}" /></td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">级别:</td>
				<td class="h2y_dialog_table_edit_td">					
					<h2y:input name="userLevel" id="userLevel" type="radio" initoption="1,负责人:0,普通用户" value="${sysDeptUser.userLevel}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">排序:</td>
				<td class="h2y_dialog_table_edit_td">
					
					<input name="duOrd" type="text" id="duOrd" value="${sysDeptUser.duOrd}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">是否锁定:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="ifLock" id="ifLock" type="radio" initoption="1,是:0,否" value="${sysUser.ifLock}"/>
				</td>
			</tr>
			
			
			<tr id="tr_next">
				<td class="h2y_table_label_td">下一步:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="next" id="next" type="radio" initoption="1,继续添加:0,返回列表" value="1"/>
					<div id="ifNext_radios"></div>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>