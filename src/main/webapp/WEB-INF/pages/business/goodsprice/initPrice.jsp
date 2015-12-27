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
    	var goodsSource = $("#goodsSource").val();
        var url_1 = "business/goodsprice/getList.htm?goodsSource="+goodsSource;

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
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

    function f_html(row) {

        return "";
    }

    
    //添加旗舰商品
	function h2y_add() {
    	
        var src = "<%=basePath%>business/goodsprice/addPrice.htm?op=add";
        top.f_addTab("goodsprice_add_price","添加商品", src);
    }
	//修改旗舰商品
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
        if(rows[0].GOODS_SOURCE == 2){
        	var src = "<%=basePath%>business/goodsprice/addPrice.htm?op=modify&id="+rows[0].ID;
            top.f_addTab("goodsprice_modify_price"+rows[0].ID,"修改商品", src);
        }else{
        	var src = "<%=basePath%>business/goodsprice/add.htm?op=modify&id="+rows[0].ID;
            top.f_addTab("goodsprice_modify"+rows[0].ID,"修改商品", src);
        }
        
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
        
       	var src = "<%=basePath%>business/goodsprice/addPrice.htm?op=detail&id="+rows[0].ID;
        top.f_addTab("goodsprice_detail_price"+rows[0].ID,"商品详细", src);
    }
    
    function h2y_delete() {
    	do_status("delete");
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
    
    
    function h2y_search(){
    	
   	    var sqlWhere = hwtt_getSqlCondition();
   	    var manager = $("#listgrid").ligerGetGridManager();
        manager.changePage("first"); 
        manager.setOptions({
            parms: [{name:"ifQuery",value:sqlWhere}]
        });
        manager.loadData(true);
   }
    
    
    function h2y_up(){
    	do_status("up");
    }
    
    
	function h2y_down(){
		do_status("down");
    }
	
	function do_status(op) {

        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
        
		
        var opWarn = "";
        if(op == "delete"){
        	opWarn = "删除后不可恢复，确定删除选中行吗？";
        }else if(op == "up"){
        	opWarn = "确定上架选中行吗？";
        }else{
        	opWarn = "确定下架选中行吗？";
        }
        if (!confirm(opWarn)) return;
        
        var dataIds = "";
        $(rows).each(function(){
        	if(dataIds==""){
        		dataIds = this.ID;
        	}else{
        		dataIds += ","+this.ID;
        	}
        })
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/goodsprice/status.htm",{dataIds:dataIds,op:op},function (data) {

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
<input type="hidden" name="goodsSource" id="goodsSource" value="${goodsSource }"/>
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