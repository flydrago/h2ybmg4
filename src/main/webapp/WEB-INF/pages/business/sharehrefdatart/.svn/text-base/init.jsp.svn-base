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

			$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '添加' , click: h2y_add , icon:'add' },{ line:true },{ text: '移除' , click: h2y_delete , icon:'delete' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});

			$("#layout1").ligerLayout({
				leftWidth : 190,
				height : "100%",
				topHeight : 23,
				allowTopResize : false
			}); 
			
		       
			f_getList();
		}); 
		
		function f_getList() {
	    	
	        var url_1 = "business/sharehrefdatart/getList.htm?hrefId=${hrefId}";

	        $("#listgrid").ligerGrid({
	            columns: [${gridComluns}
	            ,{display: '排序(可修改)', name: 'ord', width: 100, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'spinner' }}
	            ],
	            url: url_1,
	            parms: [],
	            showTitle: false,
	            dataAction: "server",
	            pageSize: 20,
	            height: "100%",
	            width: "100%",
	            usePager: true,
	            enabledEdit: true,
	            clickToEdit: true,
	            pageSizeOptions: [20, 30, 50, 100],
	            onSelectRow: function (row, index, data) {
	                id = row.ID;
	            },onDblClickRow: function (row, index, data) {
	            	selectRows(row);
	            },onAfterEdit: function (row, index, data) {
	            	updateOrd();
	            }
	        });
	    }
        
		
		function selectRows(row){
	    	var manager = $("#listgrid").ligerGetGridManager();
	    	if(manager.isSelected(row)){
	    		manager.unselect(row);
	    	}else{
	    		manager.select(row);
	    	}
	    		
	    }
		
		
		//修改排序
		function updateOrd(){
	    	
	    	var manager = $("#listgrid").ligerGetGridManager();
	        var rows = manager.getUpdated();
	        if(null==rows || undefined==rows || null==rows[0] || undefined==rows[0]) return;
	        <%--注意该处url可能不符合你的要求，请注意修改--%>
	        $.post("business/sharehrefdatart/saveDataRtOrd.htm",{id:rows[0].id,ord:rows[0].ord}, function (data) {
	            var jsonReturn = eval("(" + data + ")");
	            if (jsonReturn.code == "1") {
	                f_query();
	            }
	        });
	    }
		
		function f_onSelect(node){
			if(node==null || node.data==null || node.data.id==null) return;
		      f_query();
		}
      	     
		function h2y_add() {
	    	
			openPromoteSelectDialog({selectType:"radio"});
	        
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
			$.post("<%=basePath%>business/sharehrefdatart/delete.htm?id="+rows[0].id,  function (data){
				
				var jsonReturn=eval("("+data+")");
				if(jsonReturn.code=="1"){	               					
					alert(jsonReturn.msg);
					f_query();
				}else{
				   alert(jsonReturn.msg);
				} 
			});
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
	    
	    
	    
	   
	  //打开活动选择列表
	    function openPromoteSelectDialog(obj) {
	    	
	    	var selectType = "radio";
	    	if(obj!=null && obj.selectType!=null){
	    		selectType = obj.selectType;
	    	}
	    	
    	
	        var src = "business/sharehrefdatart/promoteInit.htm?selectType="+selectType+"&hrefId=${hrefId}";
	        $.ligerDialog.open({
	            name:"select_promote",
	            title:  "选择活动",
	            height: 500,
	            url: src,
	            width: 700,
	            showMax: true,
	            showToggle: true,
	            showMin: true,
	            isResize: true,
	            modal: true,
	            buttons: [
	                { text: '确定', onclick: function (item, dialog) {
	                	var data=$("#select_promote")[0].contentWindow.h2y_returnData();								  	
	     			  	if (data){
	     			  		h2y_promoteSelectCallBack(data);
	     			  		dialog.close();
	     			  	}
	                } },
	                { text: '取消', onclick: function (item, dialog) {
	                    	dialog.close();
	                } }
	            ]
	        });
	    }
	  
	  
	  
	    function h2y_promoteSelectCallBack(data){
	    	
	    	var promoteData = [];
	    	//alert(JSON.stringify(data));
	    	$(data).each(function(){
	    		var data = {};
	    		data.dataId = this.id;
	    		data.title = this.title;
	    		promoteData.push(data);
	    	});
	    	
	        <%--注意该处url可能不符合你的要求，请注意修改--%>
	        $.post("business/sharehrefdatart/save.htm",{hrefId:${hrefId},str1:'promote',promoteData:JSON.stringify(promoteData)}, function (data) {
	            var jsonReturn = eval("(" + data + ")");
	            if (jsonReturn.code == "1") {
	                alert(jsonReturn.msg);
	                f_query();
	            } else {
	                alert(jsonReturn.msg);
	            }
	        });
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