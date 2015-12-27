<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var subjectId = "${subjectId}";
	var dataType = "activity";
	var activityType = "${activityType}";
    $(function () {
    	
        f_getList();
    });
    
    function f_getList() {
    	
        var url_1 = "business/advertsubjectinfort/getActivityList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"subjectId",value:subjectId},{name:"dataType",value:dataType},{name:"activityType",value:activityType}],
            showTitle: false,
            dataAction: "server",
            pageSize: 10,
            height: "100%",
            width: "100%",
            usePager: true,
            checkbox:true,
            pageSizeOptions: [10,20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            	 parent.h2y_advertActivitySelectCallBack(row);
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
        return rows;
    }
</script>


</head>
<body>
<div id="layout1" style="width: 100%">
 	
    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>