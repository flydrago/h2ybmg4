<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
	var parentId = 0;
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
    	var serviceType  = $("#serviceType").val();
        var url_1 = "business/findservice/getList.htm?op=grid&serviceType="+serviceType;

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [ {name:"parentId",value:parentId}],
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
    	parentId = node.data.id;
		f_query();
    }
    
    //添加服务
    function h2y_add_service() {
    	var serviceType = $("#serviceType").val();
    	
    	//得到选中行的数据JSON对象（是集合形式）      
    	var treemanager= $("#tree1").ligerGetTreeManager();
        var row = treemanager.getSelected();
    	if(null==row || row.length==0){
    		alert("请选择父级！");
    		return;
    	}else{
    		if(row.data.id == 0){
    			alert("请在分类下添加！");
        		return;
    		}else{
    			if(row.data.dataType != 0){
    				alert("请在分类下添加！");
            		return;
    			}
    		}
    	}
    	
    	
    	//dataType 服务类型：0：元数据 1：正常服务
        var src = "<%=basePath%>business/findservice/add.htm?op=add&parentId="+parentId+"&dataType=1&serviceType="+serviceType;
        top.f_addTab("business_findservice_add_service"+parentId,"发现服务维护", src);
    }
    
    
    //添加分类
    function h2y_add() {
    	var serviceType = $("#serviceType").val();
    	
    	//得到选中行的数据JSON对象（是集合形式）      
    	var treemanager= $("#tree1").ligerGetTreeManager();
        var row = treemanager.getSelected();
        
    	if(null==row || row.length==0){
    		alert("请选择父级！");
    		return;
    	}else{
    		if(row.data.id != 0 && row.data.dataType != 0){
    			alert("请在分类下添加！");
        		return;
    		}
    	}
    	//dataType 服务类型：0：元数据 1：正常服务
        var src = "<%=basePath%>business/findservice/add.htm?op=add&parentId="+parentId+"&dataType=0&serviceType="+serviceType;
        top.f_addTab("business_findservice_add"+parentId,"服务分类维护", src);
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
         
         var src = "<%=basePath%>business/findservice/add.htm?op=modify&id="+rows[0].ID;
         if(rows[0].DATA_TYPE == 0){     	
             top.f_addTab("business_findservice_modify"+rows[0].ID,"服务分类修改", src);
 		 }else{
 	         top.f_addTab("business_findservice_modify_service"+rows[0].ID,"发现服务修改", src);
 		 }
        
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
        $.post("business/findservice/delete.htm?id=" + rows[0].ID, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
        });
    }
    
 	function h2y_detail() {
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        var src = "<%=basePath%>business/findservice/add.htm?op=detail&id="+rows[0].ID;
        if(rows[0].DATA_TYPE == 0){     	
        	top.f_addTab("business_findservice_detail"+rows[0].ID,"服务分类详细", src);
		}else{
			 top.f_addTab("business_findservice_detail"+rows[0].ID,"发现服务详细", src);
		}
       
    }
    
    
	function do_detail(subjectId) {
        
        var src = "<%=basePath%>business/findservice/add.htm?op=detail&id="+subjectId;
        top.f_addTab("business_findservice_detail"+subjectId,"发现服务详细", src);
    }

    function h2y_refresh() {
        document.location.reload();
    }
    
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
	   	manager.setOptions({
	        parms: [{name:"parentId",value:parentId}]
	    });
        manager.loadData(true);
    }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"ifQuery",value:sqlWhere},
                     {name:"parentId",value:parentId}]
         });
         manager.loadData(true);
    }
    
</script>


</head>
<body>
<form>
<input name="serviceType" type="hidden" id="serviceType" value="${serviceType}" />
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
</form>
</body>
</html>