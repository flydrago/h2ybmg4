<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>投票维护</title>
<%@ include file="../../include/top_list.jsp"%>
<script type="text/javascript">  
	var subId = 0;
	$(function (){
		subId = ${subId};
		//查询配置方法
		hwtt_setSqlCondition();
		
		//按钮配置方法
		$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },
		                                       { line:true },{ text: '详细' , click: h2y_detail , icon:'view' },
		                                       { line:true },{ text: '得票记录' , click: h2y_record , icon:'process' },
		                                       { line:true },{ text: '删除' , click: h2y_delete , icon:'delete' },
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
		
	    var url_1 = "business/votecandidate/getList.htm?subId="+subId;
	    $("#listgrid").ligerGrid({
	        columns:[${gridComluns}],
	        url: url_1,
	        parms: [],
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

	

	
	
	function h2y_delete(){
	   	 var manager = $("#listgrid").ligerGetGridManager();
	     var rows = manager.getCheckedRows();
	     if (rows == null || rows.length == 0) {
	         alert('请选择行');
	         return;
	     } else if (rows.length > 1) {
	         alert("请选择单行记录");
	         return;
	     }
	     if (!confirm("删除后不可恢复，确定删除吗？")) return;
        $.post("<%=basePath%>business/votecandidate/delete.htm?op=delete&id="+rows[0].ID, function (data) {
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
        var src = "<%=basePath%>business/votecandidate/detail.htm?id="+rows[0].ID;
        top.f_addTab("business_votecandidate_detail"+rows[0].ID,"候选人详细信息", src);
    }
   
    function h2y_record() {
     	 var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
       var src = "<%=basePath%>business/votecandidate/voterecord.htm?id="+rows[0].ID;
       top.f_addTab("business_votecandidate_voterecord"+rows[0].ID,"候选人投票记录", src);
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
		<div position="center" title="">
			<div id="conditiondiv"
				style="align:center;padding-top:5px;padding-bottom:5px;">
				${conditionHtml}</div>
			<div id="listgrid"></div>
		</div>
	</div>
</body>
</html>