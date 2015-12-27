<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var activityId = 0;
    $(function () {
    	
    	//hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        $("#tree1").ligerTree({
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            isExpand: false, 
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });
        
        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "business/commonactivitysubjectrt/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns},
            			{display: '排序', name: 'ORD', width: 100, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'spinner' }}],
            url: url_1,
            parms: [{name:"activityId",value:activityId}],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: false,
            enabledEdit: true,
            clickToEdit: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
            	do_detail(row.SUBJECT_ID);
            },onAfterEdit: function (row, index, data) {
            	updateOrd();
            }
        });
    }
    
    
    function updateOrd(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getUpdated();
        if(null==rows || undefined==rows || null==rows[0] || undefined==rows[0]) return;
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/commonactivitysubjectrt/update.htm",{id:rows[0].ID,ord:rows[0].ORD}, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                f_query();
            }
        });
    }
    
 	function f_onSelect(node) {
 		
    	if(node==null || node.data==null || node.data.id==null) return;
    	activityId = node.data.id;
    	if(!isActivityType()){
    		 f_query();
    	}
    }
 	
 	function isActivityType(){
 		
 		var tel = /^t_.*/;
 		return tel.test(activityId);
 	}

    
    function h2y_add() {
    	
    	if(activityId==0 || isActivityType()){
    		alert("请选择相应活动！")
    		return;
    	}
    	openSubjectSelectDialog(activityId);
    }
    
    
    function h2y_subjectSelectCallBack(data){
    	
    	 <%--注意该处url可能不符合你的要求，请注意修改--%>
         $.post("business/commonactivitysubjectrt/save.htm",{activityId:activityId,subjectId:data.ID},function (data) {
             var jsonReturn = eval("(" + data + ")");
             if (jsonReturn.code == "1") {
            	 f_query();
                 alert(jsonReturn.msg);
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

        if (!confirm("确定移除此行吗？")) return;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/commonactivitysubjectrt/delete.htm?id=" + rows[0].ID, function (data) {
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
        
        do_detail(rows[0].SUBJECT_ID);
    }
    
	function do_detail(subjectId) {
        
        var src = "<%=basePath%>business/commonsubject/add.htm?op=detail&id="+subjectId;
        top.f_addTab("business_commonsubject_detail"+subjectId,"主题详细", src);
    }

    function h2y_refresh() {
        document.location.reload();
    }
    
    
    function f_query(){
    	//alert(1);
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [{name:"activityId",value:activityId}]
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

	<div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
 
    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>