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
                    { display: '商品名称', name: 'name', minWidth: 120},
                    { display: '商品数量', name: 'count', minWidth: 120,type: 'text', editor: { type: 'spinner'},
                    	totalSummary:{
                           type: 'sum'
                        }
                    },
                    { display: '单价',name: 'memberPrice', minWidth: 140 },
                    { display: '总价',name: 'totalPrice', minWidth: 140,totalSummary:{
                        type: 'sum'
                    } },
                    { display: '供应商',name: 'unit_name', minWidth: 140 }
                ],
                url: 'business/cart/queryCart.htm',
                showTitle: false,
                dataAction: "server",
                sortName: "ID",
                height: "100%",
                width: "100%",
                usePager: 	false,
                checkbox:true,
                rownumbers:true,
                onSelectRow: function (rowdata, rowindex) {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: true,
                isScroll: false,
                checkbox:true,
                rownumbers:true
            });
        }
         
        function h2y_save(){
        	  var manager = $("#listgrid").ligerGetGridManager();
              manager.endEdit();
              var data = manager.getData();
              $.post("business/cart/updateCart.htm",{cartData:JSON.stringify(data)}, function (data) {
                  var jsonReturn = eval("(" + data + ")");
                  if (jsonReturn.result == "1") {
                      alert("保存成功");
                      h2y_refresh();
                  } else {
                	  alert("保存失败");
                  }
              });
              
              
              
        }
        
         
        function h2y_delete(){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            }
            if (!confirm("确定删除吗？")) return;
            $.post("business/cart/delCart.htm",{cartData:JSON.stringify(rows)},function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.result == "1") {
                	alert("删除成功！");
                	h2y_refresh();
                } else {
                    alert("删除失败！");
                }
            });
        }
        
        
        function h2y_view(){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行数据！');
                return;
            }
        	var src = "<%=basePath%>business/order/view.htm?cartData="+JSON.stringify(rows);
            top.f_addTab("order_view","提交订单预览", src);
        }
        
        function h2y_detail(){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
            var src = "<%=basePath%>business/goodsprice/add.htm?op=detail&id="+rows[0].gpid;
            top.f_addTab("goodsprice_detail"+rows[0].ID,"商品详细", src);
        }
        

        function f_onSelect(node) {
            menuId = node.data.id;
            f_query();
        }

        
        //刷新页面
        function h2y_refresh() {
            document.location.reload();
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
