<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
 	var parentId = "${parentId}";
    $(function () {
    	
    	//hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
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
    	
        var url_1 = "business/tracenumbersection/getProviderList.htm?parentId=" + parentId;

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
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
            	parent.h2y_providerSelectCallBack(row);
                frameElement.dialog.close();
            }
        });
    }
    
    
	 function h2y_returnData(){
    	
        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
        
        if(selectType!="multi" && rows.length>1){
            alert("请选择单行记录");
            return;
        }
        
        if(selectType=="multi"){
        	return rows;        	
        }
        return rows[0];
    }
    
 	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	unitId = node.data.id;
        f_query();
    }

    
    function h2y_add() {
    	var paramString = "op=add&parentId=";
        var src = "<%=basePath%>business/tracenumbersection/add.htm?" + paramString;
       
        $.ligerDialog.open({
            name:"business_tracenumbersection_add",
            title:  "添加号段",
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
                    f_getframe("business_tracenumbersection_add").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
        //top.f_addTab("business_advert_add_htm_op_add"+unitId,"广告维护", src);
    }
    

    function h2y_refresh() {
        document.location.reload();
    }
    
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.loadData(true);
   }
    
	function h2y_returnData(){
    	
        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
        
        if(selectType!="multi" && rows.length>1){
            alert("请选择单行记录");
            return;
        }
        
        if(selectType=="multi"){
        	return rows;        	
        }
        return rows[0];
    }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.loadData(true);
    }
    
    
    //录入二维码号段
    function h2y_register(){
    	 var manager = $("#listgrid").ligerGetGridManager();
    	 var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert("请选择行");
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }
        
         var src = "<%=basePath%>business/tracenumbersection/register.htm?parentId="+rows[0].ID;
         top.f_addTab("register_qr"+rows[0].ID,"已录二维码", src);
    }
    
    
    function h2y_start(){
    	
    }
    
    function h2y_assign(){
    	
    }
</script>
</head>
<body>
<div id="layout1" style="width: 100%">

   <%--  <div position="top" style="display:none;">
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
    </div> --%>
	<%--
	<div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
 	--%>
 	
    <div position="center" title="">
    	<%--
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
    	 --%>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>