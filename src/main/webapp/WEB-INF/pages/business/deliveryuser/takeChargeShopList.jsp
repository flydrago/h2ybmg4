<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>

<style type="text/css">
	.goodstype_select{ 
		 border: 1px solid #aecaf0;
		 height:25px;
		 line-height: 25px;
	 }
</style>


<script type="text/javascript">

	var typeIndex = 0;
	var unitType = "${unitType}";
	var unitId = "${unitId}";
	var account = "${account}";
	var deliveryUserId = "${deliveryUserId}";
	var selectType = "multi";
    $(function () {
        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '添加门店' , click: h2y_add , icon:'add' },{ text: '移除门店' , click: h2y_delete , icon:'delete' },{ text: '刷新' , click: h2y_refresh , icon:'refresh'}]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
	
        f_getShopList();
    });
    
    function f_getShopList(){
    	var url_1 = "business/deliveryuser/getDeliveryTakeChargeShopList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"account",value:account}],
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
    
    /* function h2y_returnData(){
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         }
         
         var shopArray = [];
         
         $(rows).each(function(){
	         shopArray.push(this.ID);
         });
         
         return JSON.stringify(shopArray);
    } */
    
    /**
     *添加门店
     **/
    function h2y_add(){
    	var src = "business/deliveryuser/assignShop.htm?id="+deliveryUserId;
        $.ligerDialog.open({
            name:"assign_shop_dialog",
            title:  "选择门店",
            height: 450,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                	var data=$("#assign_shop_dialog")[0].contentWindow.h2y_returnData();
                	
                	$.post("business/deliveryuser/saveDeliveryShopRt.htm",{"deliveryUserId":deliveryUserId,"shopArray":data},function(result){
						var resJson = eval("("+result+")");
                		alert(resJson.resultMsg);
                	});
                	
                	dialog.close();
                	document.location.reload();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    	dialog.close();
                } }
            ]
        });
    }
    
    /**
     *删除选中门店
     **/
    function h2y_delete(){
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         }
         
		var shopArray = [];
         
         $(rows).each(function(){
	         shopArray.push(this.ID);
         });
         
         $.post("business/deliveryuser/deleteDeliveryShopRt.htm",{"shopArray":JSON.stringify(shopArray),"deliveryUserId":deliveryUserId},function(result){
        	 var resJson = eval("("+result+")");
        	 alert(resJson.resultMsg);
        	 document.location.reload();
         });
         
    }

    /**
    *刷新
    **/
    function h2y_refresh(){
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
	                    <div id="toptoolbar">
	                    </div>
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