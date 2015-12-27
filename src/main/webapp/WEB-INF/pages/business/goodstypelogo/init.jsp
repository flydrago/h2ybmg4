<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var typeCode = null;
	var typeId = 0;
	var isHasChild = true;
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
            //远程加载，有时会加载不出来
            //url: "business/goodsmark/getList.htm?op=tree",
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            delay: 2,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });
        
        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "business/goodstypelogo/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [ {name:"typeId",value:typeId}],
            showTitle: false,
            dataAction: "server",
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
            	do_detail(row.ID);
            }
        });
    }
    
    
	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	typeCode = node.data.code;
    	typeId = node.data.id;
		var treemanager= $("#tree1").ligerGetTreeManager();
		var haschildren=node.data.children ? true : false;
		isHasChild = haschildren;
    	if(!haschildren){
			$.ajax({
				type: "POST",
				url: "business/goodstype/getTreeList.htm",
				data: {id:typeId},
	     		success: function(jsondata){
					var nodes=eval("("+jsondata+")");
					treemanager.append(node.target, nodes);
					f_query();
	        	}
	    	});
		}else{
			f_query();
		}
    }
    
    
    function h2y_add() {
    	
    	if(typeCode==null){
             alert("请选择类型！");
             return;
        }
    	
        var src = "<%=basePath%>business/goodstypelogo/add.htm?op=add&typeId="+typeId+"&typeCode="+typeCode;
        top.f_addTab("business_goodstypelogo_add"+typeId,"类型Logo维护", src);
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
         
         var src = "<%=basePath%>business/goodstypelogo/add.htm?op=modify&id="+rows[0].ID;
         top.f_addTab("business_goodstypelogo_modify"+rows[0].ID,"类型Logo修改", src);
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
        $.post("business/goodstypelogo/delete.htm?id=" + rows[0].ID, function (data) {
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
    
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
	   	manager.setOptions({
	        parms: [{name:"typeId",value:typeId}]
	    });
        manager.loadData(true);
    }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"ifQuery",value:sqlWhere},
                     {name:"typeId",value:typeId}]
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
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>