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
            //$.metadata.setType("attr", "validate");
            //form = $("form").ligerForm();

            $("#menuOrd").ligerSpinner({type: 'int' ,height: 25});

            if (op == "modify") {
                $("#tr_next").hide();
            }else{
            	$("input[type=radio][name='ifVisible'][value=1]").attr("checked",true);
            	$("input[type=radio][name='ifGrid'][value=1]").attr("checked",true);
            	$("input[type=radio][name='ifQuery'][value=1]").attr("checked",true);
            	$("input[type=radio][name='ifValidate'][value=1]").attr("checked",true);
            	$("input[type=radio][name='ifSys'][value=0]").attr("checked",true);
            }
        });


        function h2y_save() {

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/menu/save.htm", queryString, function (data) {

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
                            $("#menuUrl").val("");
                            $("#menuIcon").val("");
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
        <input type="hidden" name="id" value="${sysMenu.id}"/>
        <input type="hidden" name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table">

        <c:choose>
            <c:when test="${op=='add'}">
                <input type="hidden" name="parentId" value="${sysMenu.parentId}"/>
            </c:when>
            <c:otherwise>
                <tr>
                    <td class="h2y_table_label_td">父级菜单:</td>
                    <td class="h2y_dialog_table_edit_td">
                        <select class="h2y_select_just" name="parentId" id="parentId" ltype="select">
                            <option value="0" <c:if test="${0==sysMenu.parentId}">selected</c:if> title="顶级菜单">顶级菜单</option>
                            <c:forEach var="c" items="${prentMenuList}">
                                <option value="${c.id}" <c:if test="${c.id==sysMenu.parentId}">selected</c:if> title="${c.menuName }"> ${c.menuName }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="menuName" type="text" class="h2y_input_just" id="menuName"  value="${sysMenu.menuName}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">url:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="menuUrl" type="text" class="h2y_dialog_input_long" id="menuUrl"  value="${sysMenu.menuUrl}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">图标:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="menuIcon" type="text" class="h2y_input_just" id="menuIcon" 
                        value="${sysMenu.menuIcon}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">显示:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="ifVisible" id="ifVisible" type="radio" initoption="0,否:1,是" value="${sysMenu.ifVisible}"/>
            </td>
        </tr>


        <tr>
            <td class="h2y_table_label_td">列维护:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="ifGrid" id="ifGrid" type="radio" initoption="0,否:1,是" value="${sysMenu.ifGrid}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">查询:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="ifQuery" id="ifQuery" type="radio" initoption="0,否:1,是" value="${sysMenu.ifQuery}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">验证:</td>
            <td class="h2y_dialog_table_edit_td">
            	<h2y:input name="ifValidate" id="ifValidate" type="radio" initoption="0,否:1,是" value="${sysMenu.ifValidate}"/>
            </td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">系统菜单:</td>
            <td class="h2y_dialog_table_edit_td">
            	<h2y:input name="ifSys" id="ifSys" type="radio" initoption="0,否:1,是" value="${sysMenu.ifSys}"/>
            </td>
        </tr>
        <tr>
            <td class="h2y_table_label_td">排序:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="menuOrd" type="text" id="menuOrd"
                       value="${sysMenu.menuOrd}"/>
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