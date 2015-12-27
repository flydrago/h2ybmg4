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
    	
        var url_1 = "business/deliveryuser/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
            showTitle: false,
            dataAction: "server",
            sortName: "CREATE_DATE",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            checkbox:true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }
    
    //审核
    function h2y_check() {
    	
    	 var manager = $("#listgrid").ligerGetGridManager();

         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }

         <%--注意该处url可能不符合你的要求，请注意修改--%>
         var src = "<%=basePath%>business/deliveryuser/check.htm?op=check&id="+rows[0].ID;
         $.ligerDialog.open({
             name:"deliveryuser_check",
             title:  "配送员审核",
             height: 340,
             url: src,
             width: 700,
             showMax: true,
             showToggle: true,
             showMin: true,
             isResize: true,
             modal: true,
             buttons: [
                 { text: '确定', onclick: function (item, dialog) {
                     f_getframe("deliveryuser_check").h2y_save();
                 } },
                 { text: '取消', onclick: function (item, dialog) {
                     dialog.close();
                 } }
             ]
         });
    }
    
 	//查询配送员负责的门店列表
    function h2y_shop(){
    	var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
 		
        if(rows[0].CHECK_STATUS == 1 ||rows[0].CHECK_STATUS == "1"){
	    	var src = "<%=basePath%>business/deliveryuser/takeChargeShopList.htm?id="+rows[0].ID;
	        top.f_addTab("assignShop","负责门店列表", src);
        }else{
        	alert("配送员未审核或审核未通过");
        }
        
    }
    
    
    <%-- function h2y_shop2(){
    	
        
        注意该处url可能不符合你的要求，请注意修改
        var src = "<%=basePath%>business/deliveryuser/assignShop.htm?id="+rows[0].ID;
        $.ligerDialog.open({
            name:"assignShop",
            title:  "配送员审核",
            height: 340,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                	var data = $("#assignShop")[0].contentWindow.h2y_returnData();
                	
                	 $.post("business/deliveryuser/saveDeliveryShopRt.htm",{"shopArray":data,"deliveryUserId":rows[0].ID},function(result){
                    	 var resJson = eval("("+result+")");
                    	 alert(resJson.resultMsg);
                     });
                	
                	dialog.close();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    } --%>
    
    //停用
    function h2y_stop() {
    	
    	update_status("stop");
    }
    
    //启用
    function h2y_start() {
    	
    	update_status("start");
    }
    
    function update_status(op){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
        
        var objectIds = "";
        
        $(rows).each(function(){
        	
        	if(this.UNIT_ID){
        		if(objectIds==""){
            		objectIds = this.ID;
            	}else{
    				objectIds += ","+this.ID;        		
            	}
        	}
        });
        
        if(objectIds==""){
        	alert("请选择已审核过的配送员！");
        	return;
        }
        
        $.post("business/deliveryuser/save.htm",{objectIds:objectIds,op:op},function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_refresh();
            } else {
                alert(jsonReturn.msg);
            }
        });
    }
    
    function f_refresh(){
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.loadData(true);
    }
    
    
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

	<%--
	 <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>
	 --%>

    <div position="center" title="">
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>