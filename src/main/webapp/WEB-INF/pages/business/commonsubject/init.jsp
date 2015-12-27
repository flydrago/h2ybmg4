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
    	
    	//hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        f_getList();
        $("#isStart").attr("checked",true);
    });

    function f_getList() {
    	
        var url_1 = "business/commonsubject/getList.htm?op=grid";

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
            	do_detail(row.ID);
            }
        });
    }
    
    
    function h2y_add() {
    	
        var src = "<%=basePath%>business/commonsubject/add.htm?op=add";
        top.f_addTab("business_commonsubject_add","主题维护", src);
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
         
         var src = "<%=basePath%>business/commonsubject/add.htm?op=modify&id="+rows[0].ID;
         top.f_addTab("business_commonsubject_modify"+rows[0].ID,"主题修改", src);
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
        
        do_detail(rows[0].ID);
    }
    
    
	function do_detail(subjectId) {
        
        var src = "<%=basePath%>business/commonsubject/add.htm?op=detail&id="+subjectId;
        top.f_addTab("business_commonsubject_detail"+subjectId,"主题详细", src);
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
        $.post("business/commonsubject/delete.htm?id=" + rows[0].ID, function (data) {
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
    
    
    function h2y_goods() {
    	
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }
         
         if(rows[0].SUBJECT_TYPE==2){
        	 alert("宣传页面不需要维护商品！");
        	 return;
         }
         
         //商品列表
         if(rows[0].SUBJECT_TYPE==0){
        	 var src = "<%=basePath%>business/commonsubject/goodsList.htm?id="+rows[0].ID;
             top.f_addTab("business_commonsubject_add_"+rows[0].ID,"商品维护", src);
         }
         
         //商品详细
         if(rows[0].SUBJECT_TYPE==1){
        	 
        	 <%--注意该处url可能不符合你的要求，请注意修改--%>
             $.post("business/commonsubject/getActivityGoods.htm?id=" + rows[0].ID, function (data) {
                 var jsonReturn = eval("(" + data + ")");
                 if (jsonReturn.code == "1") {//修改商品
                	 
                	 var src = "<%=basePath%>business/commonactivitygoodsrt/add.htm?op=modify&id="+jsonReturn.id;
                     top.f_addTab("business_commonactivitygoodsrt_modify_"+jsonReturn.id+"_1","商品维护", src);
                 } else {//添加商品
                	 
                	 var src = "<%=basePath%>business/commonactivitygoodsrt/add.htm?op=add&dataId="+rows[0].ID+"&dataType=1";
                     top.f_addTab("business_commonactivitygoodsrt_add_"+rows[0].ID+"_1","商品维护", src);
                 }
             });
         }
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
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>