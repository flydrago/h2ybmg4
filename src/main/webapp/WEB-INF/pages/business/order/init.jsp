<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var unitType = "${sysUnits.unitType}";
    $(function () {
    	
    	hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
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
    
    function getColumns(){
    	
    	var groupComluns = [];
    	
		var gridcomluns = [${gridComluns}];
        
        var orderComlun = {display: '订单信息',columns: []};
        var receiverComlun = {display: '收货信息',columns: []};
        var goodsColmlun = {display: '商品信息',columns: []};
        $(gridcomluns).each(function(){
        	if(this.name=="orderNo" || 
        			this.name=="createDate"  || 
        			this.name=="receiverStatus" ||
        			this.name=="deliveryerStatus"){
        		orderComlun.columns.push(this);
        	}else if(this.name=="receiverAddress" || 
        			 this.name=="receiverName" ||
         			 this.name=="receiverStatus" || 
        			 this.name=="receiverMobile"){
        		receiverComlun.columns.push(this);
        	}else if(this.name=="goodsCount" || 
	       			 this.name=="goodsAmount"){
	    		this.totalSummary = {type: 'sum'};
	    		goodsColmlun.columns.push(this);
	    	}
        });
        
        groupComluns.push(orderComlun);
        groupComluns.push(receiverComlun);
        groupComluns.push(goodsColmlun);
        return groupComluns;
    }
    
    //得到状态对应的值
    function getOrderStatusName(value){
    	
    	switch (value) {
		case 20:
			
			return "待受理";
			break;
		case -21:
			
			return "已取消";
			break;
		case 21:
			
			return "确认收货";
			break;
		case 10:
			
			return "已受理";
			break;
		case -11:
			
			return "客户拒收";
			break;
		case 11:
			
			return "配送完成";
			break;
		default:
			return "其他";
			break;
		}
    }
    
  	//得到状态对应的值
    function getReceiverStatusName(value){
    	
    	switch (value) {
		case 20:
			return "已提交";
			break;
		case -21:
			
			return "已取消";
			break;
		case 21:
			
			return "确认收货";
			break;
		default:
			return "其他";
			break;
		}
    }
	
    
    function f_getList() {
    	
        var url_1 = "business/order/getList.htm";

        $("#listgrid").ligerGrid({
            columns: getColumns(),
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
    
    function h2y_goods(){
    	
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }
         var src = "<%=basePath%>business/order/goods.htm?orderNo="+rows[0].orderNo;
         $.ligerDialog.open({
             name:"business_order_goods",
             title:  "订单商品列表",
             height: 340,
             url: src,
             width: 800,
             showMax: true,
             showToggle: true,
             showMin: true,
             isResize: true,
             modal: true,
             buttons: [
                 { text: '关闭', onclick: function (item, dialog) {
                     dialog.close();
                 } }
             ]
         });
    }
    
    
    function h2y_flow(){
    	
   	 var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        var src = "<%=basePath%>business/order/flow.htm?orderNo="+rows[0].orderNo;
        $.ligerDialog.open({
            name:"business_order_flow",
            title:  "订单跟踪",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '关闭', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
   }
    
 	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	unitId = node.data.id;
        f_query();
    }
    

    function h2y_refresh() {
        document.location.reload();
    }
    
    function h2y_qingDan(){
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }
         
         if(rows[0].deliveryerStatus!=20){
        	 alert("当前订单已经受理！");
        	 return;
         }
         
         h2y_deliveryDeal(10);
    }
    
    
    function h2y_end(){
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].deliveryerStatus==11){
       	 alert("当前订单已完成！");
       	 return;
        }
        h2y_deliveryDeal(11);
    }
    
    function h2y_cancel(){
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].deliveryerStatus==-11){
       	 alert("当前订单已取消！");
       	 return;
        }
        h2y_deliveryDeal(-11);
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
    
    function h2y_deliveryDeal(orderStatus){
    	
	   	 var manager = $("#listgrid").ligerGetGridManager();
	     var rows = manager.getCheckedRows();
	     var postData = {};
	     postData.orderNo = rows[0].orderNo;
	     postData.orderStatus = orderStatus;
         <%--注意该处url可能不符合你的要求，请注意修改--%>
         $.post("<%=basePath%>cmbs/order/deliveryerDeal.htm",{postData:JSON.stringify(postData)},function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.resultFlag == "1") {
                alert(jsonReturn.resultMsg);
                f_query();
            } else {
            	alert(jsonReturn.resultMsg);
            }
         });
    }
    
    
    
    function h2y_deliveryMsg(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        var src = "<%=basePath%>business/mmsghis/init.htm?target=delivery&datasourceType=order&datasourceId="+rows[0].orderNo;
        top.f_addTab("orderDeliveryMsg_"+rows[0].orderNo,"订单配送端消息推送", src);
   }
    
    
    function h2y_appMsg(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        var src = "<%=basePath%>business/mmsghis/init.htm?target=app&datasourceType=order&datasourceId="+rows[0].orderNo;
        top.f_addTab("orderAppMsg_"+rows[0].orderNo,"订单客户端消息推送", src);
   }
    
    
	function h2y_pcMsg(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        var src = "<%=basePath%>business/mmsghis/init.htm?target=pc&datasourceType=order&datasourceId="+rows[0].orderNo;
        top.f_addTab("orderPcMsg_"+rows[0].orderNo,"订单PC消息推送", src);
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
    		${conditionHtml}
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>