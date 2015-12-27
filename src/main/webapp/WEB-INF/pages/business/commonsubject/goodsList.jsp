<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var dataId = ${commonSubject.id};
	var dataType = 1;
    $(function () {
    	
        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },
                                               { line:true },{ text: '添加' , click: h2y_add , icon:'add' },
                                               { line:true },{ text: '修改' , click: h2y_modify , icon:'modify' },
                                               { line:true },{ text: '删除' , click: h2y_delete , icon:'delete' },
                                               { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        $("#isStart").attr("checked",true);
        
        f_getList();
        $("#isStart").change(function(){
        	
        	h2y_search();
        });
    });

    function f_getList() {
    	
        var url_1 = "business/commonactivitygoodsrt/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"dataId",value:dataId},
            		{name:"dataType",value:dataType},
            		{name:"isStart",value:$("#isStart").attr("checked")?"yes":"no"}],
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
    
 	function isActivityType(){
 		
 		var tel = /^t_.*/;
 		return tel.test(dataId);
 	}

    
    function h2y_add() {
    	
        var src = "<%=basePath%>business/commonactivitygoodsrt/add.htm?op=add&dataId="+dataId+"&dataType="+dataType;
        top.f_addTab("business_commonactivitygoodsrt_add_"+dataId+"_"+dataType,"商品维护", src);
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
         
         var src = "<%=basePath%>business/commonactivitygoodsrt/add.htm?op=modify&id="+rows[0].ID;
         top.f_addTab("business_commonactivitygoodsrt_modify_"+rows[0].ID+"_"+dataType,"商品修改", src);
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

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/commonactivitygoodsrt/delete.htm?id=" + rows[0].ID, function (data) {
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
    
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [{name:"dataId",value:dataId},
            		{name:"dataType",value:dataType},
                    {name:"isStart",value:$("#isStart").attr("checked")?"yes":"no"}]
        });
        manager.loadData(true);
   }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"dataId",value:dataId},
                     {name:"dataType",value:dataType},
                     {name:"ifQuery",value:sqlWhere},
                     {name:"isStart",value:$("#isStart").attr("checked")?"yes":"no"}]
         });
         manager.loadData(true);
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
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    		<table>
    			<tr>
    				<td><input type="checkbox" id="isStart" title="正在活动"/><label for="isStart" style="color: red;"> 正在活动</label> </td>
    				<td> ${conditionHtml} </td>
    			</tr>
    		</table>
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>