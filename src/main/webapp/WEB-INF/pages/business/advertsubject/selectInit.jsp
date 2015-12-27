<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

    $(function () {
    	
        f_getList();
    });
    
    function f_getList() {
    	
        var url_1 = "business/advertsubject/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
            showTitle: false,
            dataAction: "server",
            pageSize: 10,
            height: "100%",
            width: "100%",
            usePager: true,
            pageSizeOptions: [10,20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            	 parent.h2y_advertSubjectSelectCallBack(row);
                 frameElement.dialog.close();
            }
        });
    }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"ifQuery",value:sqlWhere}]
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
        if(rows.length>1){
            alert("请选择单行记录");
            return;
        }
        return rows[0];
    }
</script>


</head>
<body>
<div id="layout1" style="width: 100%">
 	
    <div position="center" title="">
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    	<table>
    		<tr>
    			<td>${conditionHtml}</td>
    			<td align="left"><input type="button" value="搜索" class="button" onclick="h2y_search();" /></td>
    		</tr>
    	</table>
        </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>