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
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
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
            $.post("sys/zone/save.htm", queryString, function (data) {

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

                            $("#menuName").val("");
                            $("#menuKey").val("");
                            $("#menuUrl").val("");
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
        <input type="hidden" name="id" value="${zone.id}"/>
        <input type="hidden" name="pid" value="${zone.pid}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_dialog_table">
        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="name" type="text" class="h2y_dialog_input_long" id="name" ltype="text" value="${zone.name}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>