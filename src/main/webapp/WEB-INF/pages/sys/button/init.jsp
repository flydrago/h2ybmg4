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
    var parentMenuId = 0;

    var visibleData = [{ IF_VISIBLE: 1, text: '是' }, { IF_VISIBLE: 0, text: '否'}];
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [
            { line: true },
            {text: '添加', click: h2y_add, icon: 'add'},
            { line: true },
            {text: '删除', click: h2y_delete, icon: 'delete'},
            { line: true },
            {text: '保存', click: h2y_save, icon: 'save'},
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
            //url: "sys/button/getList.htm?op=tree",
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            isExpand: false, 
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });

        f_getList();
    });

    function f_getList() {

        var url_1 = "sys/button/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [
                {display: '名称', name: 'BUTTON_NAME', width: 200, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'text' }},
                {display: '点击方法', name: 'BUTTON_CLICK', width: 150, maxWidth: 200, align: 'left', type: 'text',editor: { type: 'text' }},
                {display: '图标', name: 'BUTTON_ICON', width: 100, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'text' }},
                {display: '排序', name: 'BUTTON_ORD', width: 100, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'spinner' }},
                {display: '显示', name: 'IF_VISIBLE', width: 100, maxWidth: 150, align: 'center', type: 'text',
                    editor: { type: 'select',data: visibleData , valueColumnName:'IF_VISIBLE'},
                    render:function(rowdata, index, value){
                        return value==1?"是":"否";
                    }
                }
            ],
            url: url_1,
            parms: [
                { name: "menuId", value: menuId}
            ],
            showTitle: false,
            dataAction: "server",
            sortName: "ID",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: false,
            enabledEdit: true,
            clickToEdit: true,
            isScroll: false,
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
        parentMenuId = node.data.pid;
        f_query();
    }

    function h2y_add() {

        if(menuId==null || menuId == 0){
            alert("请选择菜单！");
            return;
        }

        if(parentMenuId==0){
            alert("一级菜单不可添加按钮！");
            return;
        }

        var manager = $("#listgrid").ligerGetGridManager();
        manager.addRow({"BUTTON_ORD":0,"BUTTON_ICON":"","IF_SYS":0,"IF_VISIBLE":1,"IF_PUBLIC":1,"BUTTON_NAME":"","MENU_ID":menuId});
    }


    function h2y_modify() {

        var manager = $("#listgrid").ligerGetGridManager();
        var row = manager.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
        manager.beginEdit(row);
    }

    function h2y_save(){

        var manager = $("#listgrid").ligerGetGridManager();
        manager.endEdit();
        var data = manager.getData();
        $.post("sys/button/save.htm",{buttonData:JSON.stringify(data),menuId:menuId}, function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
                //h2y_refresh()
            } else {
                alert(jsonReturn.msg);
            }
        });
    }

    function h2y_delete() {

        var manager = $("#listgrid").ligerGetGridManager();
        manager.deleteSelectedRow();
    }

    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [
                { name: "menuId", value: menuId}
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