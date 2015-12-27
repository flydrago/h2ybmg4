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
	var spec = "${spec}";
	var provinceId = "${provinceId}";
	var provinceName = "${provinceName}";
	
    $(function () {
    	//hwtt_setSqlCondition();
		$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '查询' , click: h2y_search,icon:'search' },
		                   			           { line:true },{ text: '添加' , click: h2y_add,icon:'add' },
		                                       { line:true },{ text: '详情' , click: h2y_detail , icon:'view' },
		                                       { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        //$("#toptoolbar").ligerToolBar({items: [${toolbar}]});
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
	
    //获取列表
    function f_getList() {
    	var paramString = "op=assign_provider_add&ifReceive=1&ifEnable=1&ifCreate=2&parentId=" + parentId + "&spec=" + spec;
        var url_1 = "business/tracenumbersection/getList.htm?" + paramString;
     
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
            }
        });
    }
    
    //选择树
 	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	unitId = node.data.id;
        f_query();
    }

    //添加
    function h2y_add() {
    	var paramString ="op=assign_provider_add&provinceId=" + provinceId
    					+ "&provinceName=" + provinceName + "&parentId=" + parentId + "&spec=" + spec;
        var src = "<%=basePath%>business/tracenumbersection/add.htm?" + paramString;
       
        top.f_addTab("business_tracenumbersection_assign_provider_add","添加供应商号段", src);
    }
    
	//刷新
    function h2y_refresh() {
        document.location.reload();
    }
    
    //重新加载数据
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.loadData(true);
    }
    
    //查询
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
        	 parms:[{name:"ifQuery",value:sqlWhere}]
         });
         manager.loadData(true);
    }
    
 
   //详情
   function h2y_detail(){
	   var manager = $("#listgrid").ligerGetGridManager();
  	   var rows = manager.getCheckedRows();
       if (rows == null || rows.length == 0) {
           alert('请选择行');
           return;
       } else if (rows.length > 1) {
           alert("请选择单行记录");
           return;
       }
       var src = "<%=basePath%>business/tracenumbersection/detail.htm?op=detail&parentId="+rows[0].ID;
       top.f_addTab("traceprovidernumbersection_detail"+rows[0].ID,"二维码详细", src);
       
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