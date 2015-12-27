<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var deptId = 0;
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "sys/role/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
            showTitle: false,
            dataAction: "server",
            sortName: "ROLE_ORD",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }

    function f_html(row) {

        return "";
    }

    function h2y_add() {
    	
        var src = "<%=basePath%>sys/role/add.htm?op=add&id=0";

        $.ligerDialog.open({
            name:"sys_role_add",
            title:  "添加角色",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: false,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                    f_getframe("sys_role_add").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }


    function h2y_modify() {

        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].ID < 0){
        	alert("初始化角色不可修改！");
        	return;
        }

        var src = "sys/role/add.htm?op=modify&id="+rows[0].ID;

        $.ligerDialog.open({
            name:"sys_role_modify",
            title:  "修改角色",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: false,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                    f_getframe("sys_role_modify").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }

    function h2y_delete() {

        var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert("请选择行");
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].ID < 0){
        	alert("系统平台角色不能删除！");
        	return;
        }
        
        if (!confirm("删除后不可恢复，确定删除此行吗？")) return;
        
        var src = "sys/role/delete.htm?op=delete&id="+rows[0].ID;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post(src, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
        });
    }

    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: []
        });
        manager.loadData(true);
    }
    
</script>


</head>
<body>
<div id="layout1" style="width: 100%">

    <div position="top">
        <table width="100%" class="my_toptoolbar_td">
            <tr>
                <td id="my_toptoolbar_td">
                    <div class="l-toolbar">&nbsp;${mname}</div>
                </td>
                <td align="right" width="50%">
                    <div id="toptoolbar"></div>
                </td>
            </tr>
        </table>
    </div>

	<%--
	 <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
	 --%>

    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>