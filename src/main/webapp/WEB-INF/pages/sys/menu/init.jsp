<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var id = 0;
    var menuId = 0;
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [
            { line: true },
            {text: '添加', click: h2y_add, icon: 'add'},
            { line: true },
            {text: '修改', click: h2y_modify, icon: 'modify'},
            { line: true },
            {text: '删除', click: h2y_delete, icon: 'delete'},
            { line: true },
            {text: '刷新', click: h2y_refresh, icon: 'refresh'}
        ]});


        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });


        $("#tree1").ligerTree({
            //远程加载，有时会加载不出来
            //url: "sys/menu/getList.htm?op=tree",
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });

        f_getList();
    });

    function f_getList() {
        <%-- 注意该处url可能不符合你的要求，请注意修改 --%>
        var url_1 = "sys/menu/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [
                {display: 'ID', name: 'ID', width: 120, maxWidth: 120, align: 'left', type: 'text'},
                {display: '菜单名称', name: 'MENU_NAME', width: 200, maxWidth: 150, align: 'left', type: 'text'},
                {display: 'URL', name: 'MENU_URL', width: 300, maxWidth: 500, align: 'left', type: 'text'},
                {display: '显示', name: 'IF_VISIBLE', width: 50, maxWidth: 100, align: 'center', type: 'text',
                    render:function(rowdata, index, value){
                    return value==1?"是":"否";
                }},
                {display: '系统菜单', name: 'IF_SYS', width: 60, maxWidth: 100, align: 'center', type: 'text',
                    render:function(rowdata, index, value){
                        return value==1?"是":"否";
                }},
                {display: '列维护', name: 'IF_GRID', width: 60, maxWidth: 100, align: 'center', type: 'text',
                    render:function(rowdata, index, value){
                    return value==1?"是":"否";
                }},
                {display: '查询', name: 'IF_QUERY', width: 60, maxWidth: 100, align: 'center', type: 'text',
                    render:function(rowdata, index, value){
                    return value==1?"是":"否";
                }},
                {display: '验证', name: 'IF_VALIDATE', width: 60, maxWidth: 100, align: 'center', type: 'text',
                    render:function(rowdata, index, value){
                    return value==1?"是":"否";
                }},
                {display: '图标', name: 'MENU_ICON', width: 100, maxWidth: 50, align: 'center', type: 'text'},
                {display: '排序', name: 'MENU_ORD', width: 100, maxWidth: 50, align: 'center', type: 'text'}
            ],
            url: url_1,
            parms: [
                { name: "pid", value: menuId}
            ],
            showTitle: false,
            dataAction: "server",
            sortName: "ID",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: false,
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

    function f_onSelect(node) {
        menuId = node.data.id;
        f_query();
    }

    function h2y_add() {


        var treemanager = $("#tree1").ligerGetTreeManager();
        var treeNote = treemanager.getSelected();
        if(treeNote==null || treeNote.length == 0){

            alert("请选择父级菜单！");
            return;
        }
        /*
        if (menuId == 0) {
            alert("请选择父级菜单！");
            return;
        }*/

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        var src = "<%=basePath%>sys/menu/add.htm?op=add&parentId=" + menuId;


        $.ligerDialog.open({
            name:"sys_menu_add",
            title:  "添加菜单",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                    f_getframe("sys_menu_add").h2y_save();
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

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        var src = "<%=basePath%>sys/menu/add.htm?op=modify&id=" + rows[0].ID;

        $.ligerDialog.open({
            name:"sys_menu_modify",
            title:  "修改菜单",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                    f_getframe("sys_menu_modify").h2y_save();
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
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }

        if (!confirm("删除后不可恢复，确定删除此行吗？")) return;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("sys/menu/delete.htm?id=" + rows[0].ID, function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                h2y_refresh()
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
            parms: [
                { name: "pid", value: menuId}
            ]
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

    <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>

    <div position="center" title="">
        <%--
     <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${exparams.conditionHtml} </div>
     --%>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>