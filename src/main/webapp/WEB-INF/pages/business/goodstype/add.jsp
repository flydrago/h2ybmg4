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
        var op = "${op}";
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

            $.post("business/goodstype/save.htm", queryString, function (data) {

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

                        	$("#name").val("");
                        	$("#remark").val("");
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
        <input type="hidden" name="id" value="${goodsType.id}"/>
        <input type="hidden" name="parentId" value="${goodsType.parentId}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="typeName" type="text" id="typeName"  class="h2y_input_just"  value="${goodsType.typeName}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">备注:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="memo" type="text" id="memo"  class="h2y_dialog_input_long"  value="${goodsType.memo}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td" valign="top">排序:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="ord" type="text" id="ord" value="${goodsType.ord}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">状态:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${goodsType.status}"/>
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