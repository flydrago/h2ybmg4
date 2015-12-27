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
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        $("#tree1").ligerTree({
            //远程加载，有时会加载不出来
            //url: "business/goodstype/getList.htm?op=tree",
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
    	
        var url_1 = "business/shoplifecondition/getList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [
                { name: "id", value: id}
            ],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            checkbox:false,
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
    	id = node.data.id;
		var treemanager= $("#tree1").ligerGetTreeManager();
		var haschildren=node.data.children ? true : false;
    	if(!haschildren){
			$.ajax({
				type: "POST",
				url: "business/shoplifecondition/getTreeList.htm",
				data: {id:id},
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
    	
    	var treemanager = $("#tree1").ligerGetTreeManager();
        var treeNote = treemanager.getSelected();
        if(treeNote==null || treeNote.length == 0){
            alert("请选择父级类型！");
            return;
        }

        var src = "<%=basePath%>business/shoplifecondition/add.htm?op=add&parentId="+id;
        top.f_addTab("shoplifecondition_add","添加条件", src);

        
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

        var src = "<%=basePath%>business/shoplifecondition/add.htm?op=modify&id="+rows[0].id;
        top.f_addTab("shoplifecondition_modify"+rows[0].id,"修改条件", src);
        
       
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
        $.post("business/shoplifecondition/delete.htm?id=" + rows[0].id, function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                h2y_refresh();
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
                 { name: "id", value: id}
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
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>