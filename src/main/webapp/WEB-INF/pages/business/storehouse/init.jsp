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
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        //if(unitType==0){
        	$("#tree1").ligerTree({
	            //远程加载，有时会加载不出来
	            //url: "business/storehouse/getList.htm?op=tree",
	            data:${treedata},
	            checkbox: false,
	            nodeWidth: 120,
	            delay: 2,  
	            onSelect: f_onSelect,
	            idFieldName: "id",
	            parentIDFieldName: "pid",
	            textFieldName: "text"
	        });
       // }else{
        //	parentType = "unit";
       // }

        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "business/storehouse/getList.htm";

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

    function h2y_add() {
    	
        if(parentType==null){
            alert("请选择类型！");
            return;
        }
        
        if(parentType=="dept"){
            alert("部门下面不能创建仓库！");
            return;
        }

        var src = "<%=basePath%>business/storehouse/add.htm?op=add&parentType="+parentType+"&shopId="+shopId;

        $.ligerDialog.open({
            name:"storehouse_add",
            title:  "添加仓库",
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
                    f_getframe("storehouse_add").h2y_save();
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
        
        var src = "<%=basePath%>business/storehouse/add.htm?op=modify&id="+rows[0].ID;
        
        $.ligerDialog.open({
            name:"storehouse_modify",
            title:  "修改仓库",
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
                    f_getframe("storehouse_modify").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }
    
    
    function h2y_delete() {
    	do_status("delete");
    }
    
    function h2y_stop() {
    	do_status("stop");
    }
    
    function h2y_start() {
    	do_status("start");
    }
    
    function do_status(op){
    	
    	 var manager = $("#listgrid").ligerGetGridManager();

         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         }
 		
         var opWarn = "";
         if(op == "delete"){
         	opWarn = "删除后不可恢复，确定删除选中仓库吗？";
         }else if(op == "stop"){
         	opWarn = "确定停用选中仓库吗？";
         }else{
         	opWarn = "确定启用选中仓库吗？";
         }
         if (!confirm(opWarn)) return;
         
         var dataIds = "";
         $(rows).each(function(){
         	if(dataIds==""){
         		dataIds = this.ID;
         	}else{
         		dataIds += ","+this.ID;
         	}
         })
         
         <%--注意该处url可能不符合你的要求，请注意修改--%>
         $.post("business/storehouse/status.htm",{dataIds:dataIds,op:op},function (data) {

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
            parms: [
                { name: "parentType", value: parentType},
                { name: "shopId", value: shopId}
            ]
        });
        manager.loadData(true);
    }
    
    
    function h2y_goods() {

        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        var src = "<%=basePath%>business/storehousegoodsinfo/init.htm?op=modify&storehouseId="+rows[0].ID;
        top.f_addTab("storehouse_goods"+rows[0].ID,"商品维护", src);
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