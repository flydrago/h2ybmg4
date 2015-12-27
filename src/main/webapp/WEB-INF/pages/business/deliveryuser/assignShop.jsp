<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>

<style type="text/css">
	.goodstype_select{ 
		 border: 1px solid #aecaf0;
		 height:25px;
		 line-height: 25px;
	 }
</style>


<script type="text/javascript">

	var typeIndex = 0;
	var unitType = "${unitType}";
	var unitId = "${unitId}";
	var zoneCode = "${zoneCode}";
	var deliveryUserId = "${deliveryUserId}";
	var selectType = "multi";
    $(function () {
    	hwtt_setSqlCondition();
        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
	
        f_getShopList();
    });
    
    
    
    function f_getShopList(){
    	var url_1 = "business/deliveryuser/getShopGridData.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"unitId",value:unitId},{name:"deliveryUserId",value:deliveryUserId},{name:"zoneCode",value:zoneCode}],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            checkbox:true,
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
    
    function h2y_returnData(){
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         }
         
         var shopArray = [];
         
         $(rows).each(function(){
	         shopArray.push(this.ID);
         });
         
         return JSON.stringify(shopArray);
    }
    
    function h2y_search(){
      	 var sqlWhere = hwtt_getSqlCondition();
      	 var manager = $("#listgrid").ligerGetGridManager();
           manager.changePage("first"); 
           manager.setOptions({
               parms: [
					   {name:"unitId",value:unitId},                       
					   {name:"deliveryUserId",value:deliveryUserId},
					   {name:"zoneCode",value:zoneCode},
                       {name:"ifQuery",value:sqlWhere}
                       ]
           });
           manager.loadData(true);
      }
</script>

</head>
<body>
<div id="layout1" style="width: 100%">

	<%--
	<div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
	 --%>
	 
    <div position="center" title="">
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    		<table>
    			<tr>
	    			<td align="right">
	    				${conditionHtml} 
	    			</td>
	    			<td>
	    				<input type="button" value="搜索" class="button" onclick="h2y_search();" />
	    			</td>
    			</tr>
    		</table>
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>