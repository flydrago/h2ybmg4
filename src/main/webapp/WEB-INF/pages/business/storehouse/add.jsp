<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>


    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}"
        var form = null;

        $(function () {

            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
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

            $.post("business/storehouse/save.htm", queryString, function (data) {

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
                        frameElement.dialog.close();
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
        <input type="hidden" name="id" value="${storehouse.id}"/>
        <input type="hidden" name="parentType" value="${storehouse.parentType}"/>
        <input type="hidden" name="shopId" value="${storehouse.shopId}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="storeName" type="text" id="storeName"  class="h2y_input_just"  value="${storehouse.storeName}"/>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td">固话:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="telePhone" type="text" id="telePhone"  class="h2y_input_just"  value="${storehouse.telePhone}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">负责人:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="principal" type="text" id="principal"  class="h2y_input_just"  value="${storehouse.principal}"/>
            </td>
        </tr>
        
        
        <tr>
            <td class="h2y_table_label_td">负责人手机:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="principalMobile" type="text" id="principalMobile"  class="h2y_input_just"  value="${storehouse.principalMobile}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">详细地址:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="zoneDetail" type="text" id="zoneDetail"  class="h2y_dialog_input_long"  value="${storehouse.zoneDetail}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">备注:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="memo" type="text" id="memo"  class="h2y_dialog_input_long"  value="${storehouse.memo}"/>
            </td>
        </tr>

    </table>
</form>

</body>
</html>