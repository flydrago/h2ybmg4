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

        <%--
        $("#tree1").ligerTree({
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });
        --%>
        f_getList();
    });
    
    //得到状态对应的值
    function getOrderStatusName(value){
    	
    	switch (value) {
		case 0:
			
			return "受理订单";
			break;
		case 1:
			
			return "待抢单";
			break;
		case 11:
			
			return "配送中";
			break;
		case 12:
			
			return "配送完成";
			break;
		case -11:
			
			return "客户拒收";
			break;
		case 20:
			
			return "提交订单";
			break;
		case 21:
			
			return "完成";
			break;
		default:
			return "其他";
			break;
		}
    }
	
    
    function f_getList() {
    	
        var url_1 = "business/order/getFlowList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            parms: [{name:"orderNo",value:"${orderNo}"}],
            url: url_1,
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.id;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }
    
</script>
</head>
<body>
<div id="layout1" style="width: 100%">

    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>