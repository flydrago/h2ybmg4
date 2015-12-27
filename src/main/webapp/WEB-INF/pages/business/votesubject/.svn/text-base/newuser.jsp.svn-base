<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>投票主题维护</title>
<%@ include file="../../include/top_list.jsp"%>
<script type="text/javascript">  

var subId = ${subId};
var regSource = ${regSource}
	$(function (){
		//查询配置方法
		hwtt_setSqlCondition();
		
		//按钮配置方法
		$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },
		                                       { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
		
		 $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
		 
		f_getList();
	});  

	function f_getList() {
		
	    var url_1 = "business/votesubject/getnewuser.htm?regSource=${regSource}&&subId="+subId;
	
	    $("#listgrid").ligerGrid({
	        columns: [${gridComluns}],
	        url: url_1,
	        showTitle: false,
	        dataAction: "server",
	        pageSize: 20,
            height: "100%",
            width: "100%",
	        usePager: true,
	        onSelectRow: function (row, index, data) {
	            id = row.ID;
	        },
	        onDblClickRow: function (row, index, data) {
	            //alert("行双击事件");
	           h2y_detail();
	        }
	    });
	}
	
	
    function h2y_refresh() {
        document.location.reload();
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
	
	function f_query(){
  	 	var manager = $("#listgrid").ligerGetGridManager();
	    manager.loadData(true);
	}
</script>

</head>
<body>
	<div id="layout1" style="width: 100%">

		<div>
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
		<div title="">
			<div id="conditiondiv"
				style="align:center;padding-top:5px;padding-bottom:5px;">
				${conditionHtml}</div>
			<div id="listgrid"></div>
		</div>
	</div>
</body>
</html>