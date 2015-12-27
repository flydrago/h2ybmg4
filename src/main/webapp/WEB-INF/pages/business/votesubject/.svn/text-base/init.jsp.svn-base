<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>投票主题维护</title>
<%@ include file="../../include/top_list.jsp"%>
<script type="text/javascript">  
	$(function (){
		//查询配置方法
		hwtt_setSqlCondition();
		
		//按钮配置方法
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
		
	    var url_1 = "business/votesubject/getList.htm";
	
	    $("#listgrid").ligerGrid({
	        columns: [${gridComluns}],
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

	function h2y_add(){
    	
        var src = "<%=basePath%>business/votesubject/add.htm?op=add";
        top.f_addTab("business_votesubject_add","添加投票主题", src);
	}
	
	function h2y_modify(){
   	 var manager = $("#listgrid").ligerGetGridManager();
     var rows = manager.getCheckedRows();
     if (rows == null || rows.length == 0) {
         alert('请选择行');
         return;
     } else if (rows.length > 1) {
         alert("请选择单行记录");
         return;
     }
     
     var src = "<%=basePath%>business/votesubject/add.htm?op=modify&id="+rows[0].ID;
     top.f_addTab("business_votesubject_modify"+rows[0].ID,"投票主题修改", src);
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
        $.post("<%=basePath%>business/votesubject/delete.htm?op=delete&id="+rows[0].ID, function (data) {
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
        var src = "<%=basePath%>business/votesubject/detail.htm?id="+rows[0].ID;
        top.f_addTab("business_votesubject_detail"+rows[0].ID,"投票主题详细", src);
    }
    
    
    function  h2y_newUser() {
     	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }
        var src = "<%=basePath%>business/votesubject/newuserinit.htm?regSource=4&&id="+rows[0].ID;
        top.f_addTab("business_votesubject_newuser"+rows[0].ID,"投票新增用户", src);

   }
    
    
    function  h2y_candidate() {
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
		}
        
        var src = "<%=basePath%>business/votecandidate/init.htm?id="+rows[0].ID;
       	top.f_addTab("business_votecandidate_init"+rows[0].ID,"候选人管理", src); 
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
		<div  title="">
			<div id="conditiondiv"
				style="align:center;padding-top:5px;padding-bottom:5px;">
				${conditionHtml}</div>
			<div id="listgrid"></div>
		</div>
	</div>
</body>
</html>