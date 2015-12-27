<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../../include/top_list.jsp" %>
<script type="text/javascript">

	var unitId = ${unitId};
	
    $(function () {
    	hwtt_setSqlCondition();
    	
        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "img/mng/getImgList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"unitId",value:unitId}],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            checkbox:true,
            usePager: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                //id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }

    //根据条件查询图片
	function h2y_search(){
		var sqlWhere = hwtt_getSqlCondition();
		var manager = $("#listgrid").ligerGetGridManager();
		manager.changePage("first"); 
        manager.setOptions({
            parms: [{name:"unitId",value:unitId},{name:"ifQuery",value:sqlWhere}]
        });
		manager.loadData(true);
	}
    
    //上传图片
    function h2y_upload() {
    	var src = "<%=basePath%>img/mng/toUploadPage.htm?unitId="+unitId;
        top.f_addTab("upload_img","图片库-上传", src);
    }
	
    //预览图片
    function h2y_preview() {

        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        var src = "<%=basePath%>jxc/purchase/add.htm?op=modify&id="+rows[0].ID;
        top.f_addTab("receipts_modify"+rows[0].ID,"修改单据", src);
    }
    
    //删除图片
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
        $.post("img/mng/deleteImgInfo.htm?imgId=" + rows[0].id, function () {
        	h2y_refresh();
        });
    }
    
    //刷新
    function h2y_refresh() {
        document.location.reload();
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
        var src = "<%=basePath%>jxc/purchase/add.htm?op=detail&id="+rows[0].ID;
        top.f_addTab("receipts_detail"+rows[0].ID,"单据详细", src);
    }
    
    
    function f_query() {
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

    <div position="center" title="">
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>