<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
	var parentId = null;
	var parentName = null;
    $(function () {
    	
    	//hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        $("#tree1").ligerTree({
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
    	
        var url_1 = "business/traceprovidergoods/getList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [
                    { name: "parentId", value: parentId}
                ],
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
    
 	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	parentId = node.data.id.replace(/prov_/g,"");
    	parentName = node.data.text;
        f_query();
    }

    
    function h2y_add() {
    	
    	if(parentId == null || parentId.indexOf("unit_") >= 0){
    		alert("请选择供应商");
    		return;
    	}
    	
    	var paramString = "op=add&parentId=" + parentId + "&parentName=" +  parentName;
        var src = "<%=basePath%>business/traceprovidergoods/add.htm?" + paramString;
       
       /*  $.ligerDialog.open({
            name:"business_tracenumbersection_add",
            title:  "添加供应商商品",
            height: 300,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '保存', onclick: function (item, dialog) {
                    f_getframe("business_traceprovidergoods_add").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        }); */
        top.f_addTab("business_traceprovidergoods_add"+parentId,"添加供应商商品", src);
    }
    

    function h2y_refresh() {
        document.location.reload();
    }
    
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
	   	 manager.setOptions({
	   		 parms: [
	                    { name: "parentId", value: parentId},
	                ],
	     });
        manager.loadData(true);
   }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.loadData(true);
    }
    
    function h2y_stop(){
    	
    	 var manager = $("#listgrid").ligerGetGridManager();
    	 var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert("请选择行");
             return;
         } else if (rows.length >= 1) {
        	 if (!confirm("删除后不可恢复，确定删除选中行吗？")) return;
            
           	 var ids = "";
           	 
           	 $.each(rows,function(i,n){
           		 ids = ids + n.ID + "_";
           	 });
           	 ids = ids.slice(0,ids.length - 1);
           	 var src = "<%=basePath%>business/traceprovidergoods/stop.htm?";
           	 $.post(src, {ids:ids,parentId:parentId}, function (data) {
           		 
           		 var jsonReturn = eval("(" + data + ")");
                 if (jsonReturn.code == "1") {
                     alert(jsonReturn.msg);
                     f_query();
                 } else {
                     alert(jsonReturn.msg);
                 }
           	 });
            
         }
        
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
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
    	 --%>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>