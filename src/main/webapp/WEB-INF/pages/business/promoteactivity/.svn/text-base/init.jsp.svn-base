<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>${mname}</title>
		<%@ include file="../../include/top_list.jsp"%>
		<script type="text/javascript">
		var manager = null;      	
		var id = 0;
		$(function(){

			hwtt_setSqlCondition();
			$("#toptoolbar").ligerToolBar({items: [${toolbar}]});

			$("#layout1").ligerLayout({
				leftWidth : 190,
				height : "100%",
				topHeight : 23,
				allowTopResize : false
			}); 
			
		       
			f_getList();
		}); 
		
		function f_getList() {
	    	
	        var url_1 = "business/promoteactivity/getList.htm?op=grid";

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
	            pageSizeOptions: [20, 30, 50, 100],
	            onSelectRow: function (row, index, data) {
	                id = row.ID;
	            },
	            onDblClickRow: function (row, index, data) {
	                //alert("行双击事件");
	            }
	        });
	    }
                 
		function f_onSelect(node){
			if(node==null || node.data==null || node.data.id==null) return;
		      f_query();
		}
      	     
		function h2y_add() {
	    	
	        var src = "<%=basePath%>business/promoteactivity/add.htm?op=add";
	        top.f_addTab("business_promoteactivity_add","推广活动添加", src);
	        
	    }
	     
		function h2y_modify(){    
			
			var manager = $("#listgrid").ligerGetGridManager();          
			var rows = manager.getCheckedRows();
			if(rows==null || rows.length==0){
				alert('请选择行'); return;
			}else if(rows.length>1){
				alert("请选择单行记录");
				return;
			}

			<%--注意该处url可能不符合你的要求，请注意修改--%>
			var src = "<%=basePath%>business/promoteactivity/add.htm?op=modify&id="+rows[0].id;
			top.f_addTab("business_promoteactivity_modify_"+rows[0].id,"推广活动修改", src);
		}

		

		function h2y_delete(){
		   
			var manager = $("#listgrid").ligerGetGridManager();

			var rows = manager.getCheckedRows();
			if(rows==null || rows.length==0){
				alert('请选择行'); return;
			}else if(rows.length>1){
				alert("请选择单行记录");
				return;
			}
			
			if (!confirm("确定删除此行吗？")) return ;
		   
			<%--注意该处url可能不符合你的要求，请注意修改--%>
			$.post("<%=basePath%>business/promoteactivity/delete.htm?promoteActivity.id="+rows[0].id,  function (data){
				
				var jsonReturn=eval("("+data+")");
				if(jsonReturn.code=="1"){	               					
					alert(jsonReturn.msg);
					f_query();
				}else{
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
	            parms: []
	        });
	        manager.loadData(true);
	    }
	    
	    //搜索
	    function h2y_search(){
	    	
	   	    var sqlWhere = hwtt_getSqlCondition();
	   	    var manager = $("#listgrid").ligerGetGridManager();
	        manager.changePage("first"); 
	        manager.setOptions({
	            parms: [{name:"ifQuery",value:sqlWhere}]
	        });
	        manager.loadData(true);
	   }
	   
	   //查看人员
	   function h2y_user_rt(){
		   var manager = $("#listgrid").ligerGetGridManager();
	        var rows = manager.getCheckedRows();
	        if (rows == null || rows.length == 0) {
	            alert('请选择行');
	            return;
	        } else if (rows.length > 1) {
	            alert("请选择单行记录");
	            return;
	        }
	        var src = "<%=basePath%>business/promoteactivity/userRtInit.htm?promoteId="+rows[0].id;
	        top.f_addTab("business_promoteactivity_userRtInit"+rows[0].ID,"参与人员列表", src);
	   }
	   
	   //活动奖励
	   function h2y_reward_rt(){
		   var manager = $("#listgrid").ligerGetGridManager();          
			var rows = manager.getCheckedRows();
			if(rows==null || rows.length==0){
				alert('请选择行'); return;
			}else if(rows.length>1){
				alert("请选择单行记录");
				return;
			}

			<%--注意该处url可能不符合你的要求，请注意修改--%>
			var src = "<%=basePath%>business/promoteactivityrewardrt/init.htm?promoteId="+rows[0].id;
			top.f_addTab("business_promoteactivityrewardrt_init","活动奖励", src);
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
	        var src = "<%=basePath%>business/votesubject/newuserinit.htm?regSource=5&&id="+rows[0].id;
	        top.f_addTab("business_votesubject_newuser"+rows[0].id,"推广活动新增用户", src);

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
			<%--  <div position="left" style="height: 94%; overflow: auto;">
				<ul id="tree1"></ul>
			</div>
			--%>
			<div position="center" title="">			    
				<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>				
				<div id="listgrid"></div>
			</div>

		</div>
	<div id="dialog-confirm">
	</div>
 </body>
</html>