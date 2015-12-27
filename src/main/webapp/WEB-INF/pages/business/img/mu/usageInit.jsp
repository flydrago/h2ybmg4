<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../../include/top_list.jsp" %>

<script type="text/javascript">

	var moduleId = "${moduleId}";
	
    $(function () {

    	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '增加用途' , click: h2y_add , icon:'add' },{ line:true },{ text: '修改用途' , click: h2y_modify , icon:'modify' },{ line:true },{ text: '删除' , click: h2y_delete , icon:'delete' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        f_getUsageList();
    });

    //用途列表
    function f_getUsageList() {
        var url_1 = "img/mu/getUsageList.htm";
        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [ { name:"moduleId",value:moduleId } ],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            <c:if test="${selectType=='multi'}">
            	checkbox:true,
        	</c:if>
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                //id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            	 //parent.h2y_suppliserSelectCallBack(row);
                 frameElement.dialog.close();
            }
        });
    }
    
    //添加用途
    function h2y_add(){
    	var src = "<%=basePath%>img/mu/addUsage.htm?moduleId="+moduleId;
        top.f_addTab("usage_add","添加用途", src);
    }
    
    //修改用途
    function h2y_modify(){
    	
    }
    
    //删除用途
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
        
        if (!confirm("删除后不可恢复，确定删除此行吗？")) return;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("img/mu/deleteUsage.htm?usageId=" + rows[0].id, function () {
			h2y_refresh();
        });
    }
    
    //刷新页面
    function h2y_refresh(){
    	document.location.reload();
    }
    
    //重新加载数据
    function f_query(){
    	var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: []
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
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    	 
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>