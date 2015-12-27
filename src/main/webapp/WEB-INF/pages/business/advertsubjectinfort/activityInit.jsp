<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var unitId = "${unitId}";
	var subjectId = "${subjectId}";
	var dataType = "activity";
    $(function () {
    	
    	//hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '添加定额专场' , click: h2y_activity2 , icon:'activity' },{ line:true },{ text: '添加不定额专场' , click: h2y_activity3 , icon:'activity' },{ line:true },{ text: '移除专场' , click: h2y_delete , icon:'delete' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "business/advertsubjectinfort/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns},
          				{display: '排序', name: 'ORD', width: 100, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'spinner' }}],
            url: url_1,
            parms: [{name:"subjectId",value:subjectId},{name:"dataType",value:dataType}],
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
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
                h2y_detail();
            },onAfterEdit: function (row, index, data) {
            	
            	updateOrd(row.record.ID,row.record.ORD);
            }
        });
    }
    
    
	function updateOrd(id,ord){
		
        if(null==id || undefined==id || null==ord || undefined==ord) return;
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/advertsubjectinfort/updateOrd.htm",{id:id,ord:ord}, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                f_query();
            }
        });
    }
    
    
    //定额促销专场选择
    function h2y_activity2() {
    	
    	openAdvertActivitySelectDialog({subjectId:${subjectId},activityType:2});
    }
    
  	//不定额促销专场选择
    function h2y_activity3() {
    	
    	openAdvertActivitySelectDialog({subjectId:${subjectId},activityType:3});
    }
    
    
    //促销活动选择回调
    function h2y_advertActivitySelectCallBack(data){
    	
    	if(null==data || ""==data) return;
    	var dataList = [];
    	$(data).each(function(){
    		var data = {};
    		data.data1 = this.ID;
    		data.dataType = "activity";
    		dataList.push(data);
    	});
    	
    	<%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/advertsubjectinfort/save.htm",{dataList:JSON.stringify(dataList),subjectId:subjectId} ,function (data) {
             var jsonReturn = eval("(" + data + ")");
             if (jsonReturn.code == "1") {
                 alert(jsonReturn.msg);
                 f_query();
             } else {
                 alert(jsonReturn.msg);
             }
        });
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
        $.post("business/advertsubjectinfort/delete.htm?id=" + rows[0].ID, function (data) {
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
	<%--
	<div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
 	--%>
 	
    <div position="center" title="">
    	<%--
			<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>    	
    	 --%>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>