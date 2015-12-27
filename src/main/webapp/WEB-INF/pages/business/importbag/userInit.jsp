<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

    $(function () {
    	
    	hwtt_setSqlCondition();

    	if("${op}"=="detail"){
    		
    	   	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
    	}else{
    		
    	   	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '添加' , click: h2y_add , icon:'add' },{ line:true },{ text: '修改' , click: h2y_modify , icon:'modify' },{ line:true },{ text: '删除' , click: h2y_delete , icon:'delete' },{ line:true },{ text: '导入' , click: h2y_import , icon:'import' },{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});    		
    	}
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        <%--
        $("#tree1").ligerTree({
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });
        --%>
        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "business/importbag/getUserList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"bagCode",value:"${bagCode}"}],
            showTitle: false,
            dataAction: "server",
            height: "100%",
            width: "100%",
            pageSize: 20,
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
    
    
    function h2y_import() {
    	
    	 <%--注意该处url可能不符合你的要求，请注意修改--%>
         $.post("business/importbag/isEnd.htm?bagCode=${bagCode}", function (data) {
             var jsonReturn = eval("(" + data + ")");
             if (jsonReturn.code == "1") {
            	 var src = "<%=basePath%>business/importbag/importUserInit.htm?bagCode=${bagCode}";
                 top.f_addTab("importbag_user${bagCode}","接受用户导入", src);
             } else {
            	 alert(jsonReturn.msg);
             }
         });
    }
    
    function h2y_refresh() {
        document.location.reload();
    }
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.loadData(true);
    }
    
    function h2y_search(){
    	
   	 	var sqlWhere = hwtt_getSqlCondition();
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.changePage("first"); 
        manager.setOptions({
            parms: [{name:"ifQuery",value:sqlWhere},{name:"bagCode",value:"${bagCode}"}]
        });
        manager.loadData(true);
   }
    
	function h2y_add(){
    	
        var src = "business/importbag/userAdd.htm?op=add&bagCode=${bagCode}";
        $.ligerDialog.open({
            name:"business_importbag_userAdd",
            title:  "接受用户维护",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '保存', onclick: function (item, dialog) {
                    f_getframe("business_importbag_userAdd").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }
	
	
	function h2y_modify(){
    	
		var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        var src = "business/importbag/userAdd.htm?op=modify&id="+rows[0].id;
        $.ligerDialog.open({
            name:"business_importbag_userAdd",
            title:  "接受用户维护",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '保存', onclick: function (item, dialog) {
                    f_getframe("business_importbag_userAdd").h2y_save();
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
        $.post("business/importbag/deleteUser.htm?id=" + rows[0].id, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
        });
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
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>