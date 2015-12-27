<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp"%>
    <script type="text/javascript">
        var id = 0;
        var menuId = 0;
        $(function () {
        	hwtt_setSqlCondition();
            $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
            $("#layout1").ligerLayout({
                leftWidth: 190,
                height: "100%",
                topHeight: 23,
                allowTopResize: false
            });
            f_getList();
        });

        
        function f_getList() {
            $("#listgrid").ligerGrid({
                columns: [
                    { display: '商品编号', name: 'NUMBER', align: 'center', width: 100, minWidth: 60 },
                    { display: '商品类别', name: 'TYPE_NAME', align: 'center', width: 100, minWidth: 60 },
                    { display: '商品名称', name: 'NAME', minWidth: 120},
                    { display: '会员价',name: 'MEMBER_PRICE', minWidth: 140 },
                    { display: '市场价',name: 'MARKET_PRICE', minWidth: 140 },
                    { display: '销售价',name: 'SELL_PRICE', minWidth: 140 }
                ],
                url: 'business/goodsprice/buylist.htm',
                showTitle: false,
                dataAction: "server",
                sortName: "ID",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                checkbox:true,
                rownumbers:true,
                isChecked: f_isChecked,
                onCheckRow: f_onCheckRow,
                onCheckAllRow: f_onCheckAllRow,

                onSelectRow: function (row, index, data) {
                    id = row.ID;
                }, onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        
        //查看商品详细
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
            var src = "<%=basePath%>business/goodsprice/add.htm?op=detail&id="+rows[0].ID;
            top.f_addTab("goodsprice_detail"+rows[0].ID,"商品详细", src);
        }
        
        
        //加入购物车
        function h2y_addcar(){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            }
            //var selectIds=checkedCustomer.join(',');
            var selectIds = f_getSelectIds(rows);
            if (!confirm("确定加入购物车?")) return;
            $.post("business/cart/addCart.htm",{selectIds:selectIds},function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.result == "1") {
                	 if (!confirm("加入购物车成功，是否去购物车结算?")) return;
                	 var src = "<%=basePath%>business/cart/init.htm";  
                	 window.location.href=src;
                	 alert("加入购物车成功");
                } else {
                    alert("加入购物车失败");
                }
            });
        	
        }
        
        

        function f_onSelect(node) {
            menuId = node.data.id;
            f_query();
        }

        
        //刷新页面
        function h2y_refresh() {
            document.location.reload();
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
    <div position="center" title="">
        <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>
