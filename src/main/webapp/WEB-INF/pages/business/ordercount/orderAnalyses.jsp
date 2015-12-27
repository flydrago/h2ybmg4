<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var latitude = "${latitude}";
	var longitude = "${longitude}";
	var radius = "${radius}";

    $(function () {
    	
        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        
        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        f_getList();
    });
    
    var gcolumns = [{display: "日期", name: "ORDER_DAY", width: 100, align: "center", type: "string", isSort:true},
                    {display: "单位", name: "UNIT_SHORT_NAME", width: 150, align: "left", type: "string", isSort:true},
                    {display: "所有订单", columns:[{display: "订单量", name: "ORDER_COUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + suminf.sum + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "商品金额（￥）", name: "GOODS_AMOUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "客单价", name: "AVG_AMOUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "实付金额（￥）", name: "REAL_AMOUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "达人币（￥）", name: "COIN_AMOUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "运费（￥）", name: "GOODS_TRANSPORT_AMOUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "优惠劵（￥）", name: "COUPONS_AMOUNT", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}}]},
                    {display: "有效订单", columns:[{display: "订单量", name: "ORDER_COUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + suminf.sum + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "商品金额（￥）", name: "GOODS_AMOUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "客单价", name: "AVG_AMOUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "实付金额（￥）", name: "REAL_AMOUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "达人币（￥）", name: "COIN_AMOUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "运费（￥）", name: "GOODS_TRANSPORT_AMOUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
                                               	{display: "优惠劵（￥）", name: "COUPONS_AMOUNT_0", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}}]},
	               	{display: "拒收订单", columns:[{display: "订单量", name: "ORDER_COUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + suminf.sum + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
	                                          	{display: "商品金额（￥）", name: "GOODS_AMOUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
	                                          	{display: "客单价", name: "AVG_AMOUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
	                                          	{display: "实付金额（￥）", name: "REAL_AMOUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
	                                          	{display: "达人币（￥）", name: "COIN_AMOUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
	                                          	{display: "运费（￥）", name: "GOODS_TRANSPORT_AMOUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
	                                          	{display: "优惠劵（￥）", name: "COUPONS_AMOUNT_4", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}}]},
					{display: "线下订单", columns:[{display: "订单量", name: "ORDER_COUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + suminf.sum + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
					                       		{display: "商品金额（￥）", name: "GOODS_AMOUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
					                       		{display: "客单价", name: "AVG_AMOUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
					                       		{display: "实付金额（￥）", name: "REAL_AMOUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
					                       		{display: "达人币（￥）", name: "COIN_AMOUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
					                       		{display: "运费（￥）", name: "GOODS_TRANSPORT_AMOUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
					                       		{display: "优惠劵（￥）", name: "COUPONS_AMOUNT_2", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}}]},
					{display: "线上订单", columns:[{display: "订单量", name: "ORDER_COUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + suminf.sum + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
						                       	{display: "商品金额（￥）", name: "GOODS_AMOUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
						                       	{display: "客单价", name: "AVG_AMOUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
						                       	{display: "实付金额（￥）", name: "REAL_AMOUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
						                       	{display: "达人币（￥）", name: "COIN_AMOUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
						                       	{display: "运费（￥）", name: "GOODS_TRANSPORT_AMOUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}},
						                       	{display: "优惠劵（￥）", name: "COUPONS_AMOUNT_1", width: 100, align: "left", type: "string", isSort:true,totalSummary:{render: function (suminf, column, cell){return '<div>总计:' + accDiv(suminf.sum,1) + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'}}]}
                    ];
    
    var totalSum = {render: function (suminf, column, cell){return '<div>总计:' + suminf.sum + '</div>'+'<div>平均:' + accDiv(suminf.avg,1) + '</div>';},align: 'left'};
    
    function f_getList() {
    	
        var url_1 = "business/ordercount/getAnalysesData.htm";

        $("#listgrid").ligerGrid({
            columns: gcolumns,
            url: url_1,
            parms:  [{name:"longitude",value:longitude},
                     {name:"latitude",value:latitude},
                     {name:"radius",value:radius}],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: false,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.id;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
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
    	
    	 var unitName = $("#unit_name").val();
    	 var radiusValue = $("#radiusValue").val();
    	 
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"unitName",value:unitName},
                     {name:"radius",value:radiusValue},
                     {name:"latitude",value:latitude},
                     {name:"longitude",value:longitude}]
         });
         manager.loadData(true);
    }
    
    
    function dayCheck(startDay,endDay){
    	
    	
    	if(""==startDay){
  			 
  			 alert("开始时间不能为空！");
  			 return false;
  		 }
  		 
		if(""==endDay){
  			 
  			 alert("截止时间不能为空！");
  			return false;
  		 }
		
		startDay = startDay.replace(/-/g, "/");
		endDay = endDay.replace(/-/g, "/");
		startDay = new Date(startDay);
		endDay = new Date(endDay);

		var times= endDay.getTime() - startDay.getTime();
		var days = parseInt(times / (1000 * 60 * 60 * 24));
		if(days<0){
			alert("开始时间必须在截止时间之前！");
			return false;
		}
		
		if(days>31){
			alert("一次最多能查询一个31天内的记录！");
			return false;
		}
		return true;
    }
    
    
    //获取当前日期，前后的日期
    function GetDateStr(AddDayCount){   
	    var dd = new Date();   
	    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期   
	    var y = dd.getFullYear();   
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();  
	    if(m<10){
	    	m="0"+m;
	    }
	    if(d<10){
	    	d="0"+d;
	    }
	    return y+"-"+m+"-"+d;   
    } 
    
    
    function accDiv(arg1,arg2){ 
    	
    	if(null==arg1 || null==arg2) return null;
    	
    	var t1=0,t2=0,r1,r2; 
    	try{t1=arg1.toString().split(".")[1].length}catch(e){} 
    	try{t2=arg2.toString().split(".")[1].length}catch(e){} 
    	with(Math){ 
	    	r1=Number(arg1.toString().replace(".",""));
	    	r2=Number(arg2.toString().replace(".",""));
	    	var f = parseFloat((r1/r2)*pow(10,t2-t1));  
	    	return f.toFixed(2);
    	}; 
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
    		<table id='id-querytable' class='css-querytable' >
    			<tr height='25px'>
    				<td align='right' width='80px'  >单位名称:&nbsp;</td>
    				<td colspan='1'  align='left'   >
    				<input style='width:150px;' id='unit_name'  name='unit_name'   type='text'  value='' />
    				</td>
    				<td align='right'   width='80px'  >查询半径:&nbsp;</td>
    				<td colspan='1'  align='left' >
    					<input style='width:150px;' id='radiusValue'  name='radiusValue'   type='text'  value='500' />
    				</td>
    			</tr>
    		</table>
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>