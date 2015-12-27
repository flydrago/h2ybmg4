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
        	
        	if(op=="add"){
        		
        		$("#goodsCount").val(1);
        	}else{
        		
        		$("#toAccount").attr("disabled",true);
        	}
        	
        	$("#goodsCount").ligerSpinner({type: 'int' ,height: 25,minValue:1});
        	
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
            $.post("business/importbag/saveUser.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
           	 	if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    parent.f_query();
                    frameElement.dialog.close();
                } else {
                    alert(jsonReturn.msg);
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
		
		<input name="id" type="hidden" id="id" value="${importBagUserRt.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<input name="bagCode" type="hidden" id="bagCode" value="${importBagUserRt.bagCode}" />
			
		<table class="h2y_dialog_table">
			
			<tr>
				<td class="h2y_table_label_td">
					账号：
				</td>
				<td class="h2y_table_edit_td">
					<input name="toAccount" class="h2y_input_just" id="toAccount" type="input" value="${importBagUserRt.toAccount}"/>	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					数量：
				</td>
				<td class="h2y_table_edit_td">
					<input name="goodsCount" id="goodsCount" type="input" value="${importBagUserRt.goodsCount}" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>