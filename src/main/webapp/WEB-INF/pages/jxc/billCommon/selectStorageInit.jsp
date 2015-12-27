<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var shopId = 0;
    var parentType = null;
    var unitType = ${sysUnits.unitType};
    var selectType = "${selectType}";
    
    $(function () {
        $("#layout1").ligerLayout({
            leftWidth: 300,
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
    	
        var url_1 = "jxc/common/getStorageList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [
                { name: "parentType", value: parentType},
                { name: "shopId", value: shopId}
            ],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                //id = row.ID;
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
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	var type = node.data.type;
    	shopId = node.data.id.replace(/dept_|unit_|shop_/g,"");
    	if(type == -1){
    		parentType = "unit";
    		f_query();
    	}else if(type == 0){
    		parentType = "dept";
    	}else if(type == 1){
    		parentType = "shop";
    		f_query();
    	}
    }

    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [
                { name: "parentType", value: parentType},
                { name: "shopId", value: shopId}
            ]
        });
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
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>