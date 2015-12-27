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
    var joinId = 0;
    var parentJoinId = 0;

    var dataTypeList = [{DATA_TYPE:'string',text:'字符串'},
                        {DATA_TYPE:'int',text:'整数'},
                        {DATA_TYPE:'float',text:'浮点'},
                        {DATA_TYPE:'date',text:'时间'}];

    var alignList = [{ALIGN:'left',text:'左对齐'},
                    {ALIGN:'right',text:'右对齐'},
                    {ALIGN:'center',text:'居中'}];

    var ifWidthList = [{IF_WIDTH:'1',text:'宽度'},{IF_WIDTH:'0',text:'百分比'}];

    var isSortList = [{IS_SORT:'1',text:'支持'},{IS_SORT:'0',text:'不支持'}];

    var ifVisibleList = [{IF_VISIBLE:'1',text:'显示'},{IF_VISIBLE:'0',text:'不显示'}];


    var joinType = "${joinType}";
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
            //url: "sys/gridcolumns/getList.htm?op=tree",
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

        var url_1 = "sys/gridcolumns/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [
                {display: '列标题', name: 'TITLE', width: 200, maxWidth: 150, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '列名', name: 'NAME', width: 150, maxWidth: 200, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '宽度', name: 'WIDTH', width: 100, maxWidth: 150, align: 'left', isSort:false, type: 'string',editor: { type: 'int' }},
                {display: '单位', name: 'IF_WIDTH', width: 100, maxWidth: 150, align: 'left', isSort:false, type: 'string',
                    editor: { type: 'select',data: ifWidthList , valueColumnName:'IF_WIDTH',value:1},
                    render:function(rowdata, index, value){
                        return getDataText(ifWidthList,"IF_WIDTH",value);
                }},
                {display: '对齐方式', name: 'ALIGN', width: 100, maxWidth: 150, align: 'left', isSort:false, type: 'string',
                    editor: { type: 'select' ,data:alignList,valueColumnName:'ALIGN'},
                    render:function(rowdata, index, value){
                        return getDataText(alignList,"ALIGN",value);
                    }
                },
                {display: '数据类型', name: 'DATA_TYPE', width: 100, maxWidth: 150, align: 'left', isSort:false,type: 'string',
                    editor: { type: 'select',data: dataTypeList , valueColumnName:'DATA_TYPE',valueField:'DATA_TYPE'},
                    render:function(rowdata, index, value){

                        return getDataText(dataTypeList,"DATA_TYPE",value);
                    }
                },
                {display: '支持排序', name: 'IS_SORT', width: 100, maxWidth: 150, align: 'left', isSort:false, type: 'string',
                    editor: { type: 'select',data: isSortList, valueColumnName:'IS_SORT'},
                    render:function(rowdata, index, value){
                        return getDataText(isSortList,"IS_SORT",value);
                    }
                },
                {display: '渲染器', name: 'RENDER', width: 150, maxWidth: 200, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '是否显示', name: 'IF_VISIBLE', width: 100, maxWidth: 150, align: 'left', isSort:false, type: 'string',
                    editor: { type: 'select',data: ifVisibleList, valueColumnName:'IF_VISIBLE'},
                    render:function(rowdata, index, value){
                        return getDataText(ifVisibleList,"IF_VISIBLE",value);
                    }
                },
                {display: '序号', name: 'ORD', width: 100, maxWidth: 200, align: 'left', isSort:false, type: 'string',editor: { type: 'spinner' }}
            ],
            url: url_1,
            parms: [
                { name: "joinId", value: joinId},{ name: "joinType", value: joinType}
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
            isScroll: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }

    function getDataText(dataRows,key,value){

        var text = "";
         $(dataRows).each(function(){

             if(this[key] == value){
                 text = this.text;
                 return;
             }
         })
        return text;
    }

    function f_html(row) {

        return "";
    }

    function f_onSelect(node) {
        joinId = node.data.id;
        parentJoinId = node.data.pid;
        f_query();
    }

    function h2y_add() {



        if(joinId==null || joinId == 0){
            alert("请选择左侧二级树节点！");
            return;
        }

        if(parentJoinId==0){
            alert("顶级不可添加列维护！");
            return;
        }

        var manager = $("#listgrid").ligerGetGridManager();
        manager.addRow({"RENDER":"","IS_SORT":1,"NAME":"","WIDTH":"","IF_WIDTH":1,"IF_VISIBLE":1,"ALIGN":"left","ORD":0,"DATA_TYPE":"string","TITLE":""});
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

        //alert(JSON.stringify(data));
        //return;
        $.post("sys/gridcolumns/save.htm",{gridColumnsData:JSON.stringify(data),joinId:joinId,joinType:joinType}, function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
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
                { name: "joinId", value: joinId},{ name: "joinType", value: joinType}
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