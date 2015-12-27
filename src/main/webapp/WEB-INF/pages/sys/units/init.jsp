<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var parentId = 1;
    $(function () {
		
        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "sys/units/getList.htm?op=init";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [
                { name: "parentId", value: parentId}
            ],
            showTitle: false,
            dataAction: "server",
            sortName: "DU_ORD",
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
            }
        });
    }

    function f_html(row) {

        return "";
    }

    function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	parentId = node.data.id;
        f_query();
    }

    function h2y_add() {
    	
        var initUnitType = $("#initUnitType").val();
        var src = "<%=basePath%>sys/units/add.htm?op=add&parentId="+parentId+"&initUnitType="+initUnitType;
        top.f_addTab("sys_units_add_htm_op_add_parentId_"+parentId,"单位注册", src);
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
        var initUnitType = $("#initUnitType").val();
        var src = "sys/units/add.htm?op=modify&id="+rows[0].ID+"&initUnitType="+initUnitType;
        top.f_addTab("sys_units_add_htm_op_modify_id_"+rows[0].ID,"单位信息修改", src);
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
        
        var src = "sys/units/delete.htm?op=delete&sysUser.id="+rows[0].ID+"&sysDeptUser.id="+rows[0].DEPT_USER_ID;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post(src, function (data) {
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
                { name: "parentId", value: parentId}
            ]
        });
        manager.loadData(true);
    }
    
    function h2y_search(){
    	 var sqlWhere = hwtt_getSqlCondition();
       	 var manager = $("#listgrid").ligerGetGridManager();
            manager.changePage("first"); 
            manager.setOptions({
                parms: [{name:"ifQuery",value:sqlWhere},{name: "parentId", value: parentId}]
            });
            manager.loadData(true);
    }
    
</script>

</head>
<body>
<form id="editForm" method="post">
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
	
	    <div position="center" title="">
	    	<input type="hidden" name="initUnitType" id="initUnitType" value="${initUnitType }"/>
	    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
	        <div id="listgrid"></div>
	    </div>
	</div>
</form>
</body>
</html>