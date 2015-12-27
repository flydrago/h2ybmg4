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

    	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '推送消息' , click: h2y_send , icon:'view' },{ line:true },{ text: '添加优惠劵' , click: h2y_add , icon:'add' },{ line:true },{ text: '移除优惠劵' , click: h2y_delete , icon:'delete' },{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        f_getList();
        
        $("#isStart").change(function(){
        	
        	h2y_search();
        });
    });

    function f_getList() {
    	
        var url_1 = "business/couponssource/getCouponsList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name:"sourceId",value:${sourceId}},{name:"isStart",value:$("#isStart").attr("checked")?"yes":"no"}],
            showTitle: false,
            dataAction: "server",
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
            	do_detail(row.ID);
            }
        });
    }
    
    
    function h2y_add() {
    	
    	openCouponsSelectDialog({selectType:"multi",sourceId:${sourceId}});
    }
    
    
    function h2y_couponsSelectCallBack(data){
    	
    	var couponsData = [];
    	$(data).each(function(){
    		var data = {};
    		data.couponsCode = this.COUPONS_CODE;
    		data.couponsId = this.ID;
    		couponsData.push(data);
    	});
    	
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/couponssource/addCoupons.htm",{sourceId:${sourceId},couponsData:JSON.stringify(couponsData)}, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
        });
    }
    
    
    function h2y_delete() {
    	
        var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } 
        
        if (!confirm("删除后不可恢复，确定删除选中行吗？")) return;
        
        var ids = "";
        $(rows).each(function(){
        	if(""==ids){
        		ids = this.CS_RT_ID;
        	}else{
        		ids += ","+ this.CS_RT_ID;
        	}
        });

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/couponssource/removeCoupons.htm",{ids:ids}, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
        });
    }
    

    function h2y_refresh() {
        document.location.reload();
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
            parms: [{name:"ifQuery",value:sqlWhere},
                    {name:"sourceId",value:${sourceId}},
            		{name:"isStart",value:$("#isStart").attr("checked")?"yes":"no"}]
        });
        manager.loadData(true);
   }
   
    //消息推送  仅推送单张优惠券
    function h2y_send(){
    	var manager = $("#listgrid").ligerGetGridManager();          
		var rows = manager.getCheckedRows();
		if(rows==null || rows.length==0){
			alert('请选择行'); return;
		}else if(rows.length>1){
			alert("请选择单行记录");
			return;
		} 

		//只能 推送平台优惠券
		if(rows[0].SOURCE_CODE == 'platformSend'){
		        $.post("business/couponssource/sendCouponsMsg.htm?couponsId="+rows[0].ID, function (data) {
		            var jsonReturn = eval("(" + data + ")");
		            if (jsonReturn.code == "1") {
		                alert(jsonReturn.msg);
		                f_query();
		            } else {
		                alert(jsonReturn.msg);
		            }
		        });
		}else{
			alert("对不起，只能推送平台发送优惠券！");
		}
		
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
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    	    <table>
    			<tr>
    				<td>&nbsp;&nbsp;&nbsp;<input type="checkbox" id="isStart" title="有效的优惠劵"/><label for="isStart" style="color: red;">有效的优惠劵</label> </td>
    				<td>${conditionHtml}</td>
    			</tr>
    		</table>
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>