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
		var selectType = '${selectType}';
		$(function(){

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
	    	
	        var url_1 = "business/sharehrefdatart/getPromoteList.htm?op=grid";
	        
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
	            <c:if test="${selectType=='radio'}">
	            checkbox:false,
	        	</c:if>
	            pageSizeOptions: [20, 30, 50, 100],
	            onSelectRow: function (row, index, data) {
	                id = row.ID;
	            },
	            onDblClickRow: function (row, index, data) {
	            	
	                parent.h2y_couponsSelectCallBack(row);
	                frameElement.dialog.close();
	            }
	        });
	        
	    }
                 
		function f_onSelect(node){
			if(node==null || node.data==null || node.data.id==null) return;
		      f_query();
		}
      	     
		
	     
		function h2y_refresh(){
			document.location.reload();
		}
       
		function f_query() {
	        var manager = $("#listgrid").ligerGetGridManager();
	        manager.setOptions({
	            parms: []
	        });
	        manager.loadData(true);
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
	        
	        if(selectType!="multi" && rows.length>1){
	            alert("请选择单行记录");
	            return;
	        }
	        
	        if(selectType=="multi"){
	        	return rows;        	
	        }
	        return rows[0];
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
			       <%--
				<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${exparams.conditionHtml} </div>
				--%>
				<div id="listgrid"></div>
			</div>

		</div>
	<div id="dialog-confirm">
	</div>
 </body>
</html>