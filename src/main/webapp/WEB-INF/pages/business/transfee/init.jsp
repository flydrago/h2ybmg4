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
                    { display: '供应商', name: 'unit_name', align: 'center',  minWidth: 120 },
                    { display: '目的地', name: 'zone_name', align: 'center',  minWidth: 120 },
                    { display: '运费价格',name: 'fee_amount', minWidth: 140 },
                    { display: '创建时间',name: 'create_date', minWidth: 140 },
                    { display: '状态',name: 'status', minWidth: 140,
                    	render: function (item){
                            if (parseInt(item.status) == 0) return '正常';
                            return '禁用';
                        }
                    }
                ],
                url: 'business/transfee/list.htm',
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
                onSelectRow: function (row, index, data) {
                    id = row.ID;
                }, onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
        }

        
        function h2y_add(){
        	 var src = "<%=basePath%>business/transfee/add.htm?op=add";
             $.ligerDialog.open({
                 name:"transfee_add",
                 title:  "添加运费",
                 url:src,
                 height: 440,
                 width: 700,
                 showMax: true,
                 showToggle: true,
                 showMin: true,
                 isResize: true,
                 modal: true
             });
        }
        
        function h2y_search(){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } 
        	var src = "<%=basePath%>business/transfee/add.htm?op=modify&id="+rows[0].id;
        }
        
        
        function h2y_modify(){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            } else if (rows.length > 1) {
                alert("请选择单行记录");
                return;
            }
        	var src = "<%=basePath%>business/transfee/add.htm?op=modify&id="+rows[0].id;
            $.ligerDialog.open({
                name:"transfee_modify",
                title:  "修改运费",
                url:src,
                height: 440,
                width: 700,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true
            });
        }
        
        
        function h2y_live(){
        	h2y_update("0");
        }
        
        
        
        
        function h2y_delete(){
        	h2y_update("1");
        }
        
        
        
        function h2y_update(status){
        	var manager = $("#listgrid").ligerGetGridManager();
            var rows = manager.getCheckedRows();
            if (rows == null || rows.length == 0) {
                alert('请选择行');
                return;
            }
            if (!confirm("确定禁用吗？")) return;
            $.post("business/transfee/update.htm",{transfeeData:JSON.stringify(rows),status:status},function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                	alert(jsonReturn.msg);
                	h2y_refresh();
                } else {
                	alert(jsonReturn.msg);
                }
            });
        }

       

        
        //刷新页面
        function h2y_refresh() {
            document.location.reload();
        }
        
        function  h2y_reload(){
            $("#listgrid").ligerGetGridManager().reload();
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
