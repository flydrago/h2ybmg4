<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
	var parentId ="${parentId}";
	
    $(function () {
    	
    	//hwtt_setSqlCondition();
		$("#toptoolbar").ligerToolBar({
	        items: [{ line:true },{ text: '查询' , click: h2y_search , icon:'search' },
	                { line:true },{ text: '添加记录' , click: h2y_addRecord , icon:'bookpen' },
	                { line:true },{ text: '查看记录' , click: h2y_lookRecord , icon:'attibutes' },
	                { line:true },{ text: '获取记录' , click: h2y_getRecord , icon:'' },
	                { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]
	    });
       // $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
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
	
    //获取列表
    function f_getList() {
    	
        var url_1 = "business/tracenumbersection/getDetailList.htm?ifReceive=1&ifEnable=1&parentId=" + parentId;

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            showTitle: false,
            dataAction: "server",
            height: "100%",
            width: "100%",
            pageSize: 50,
            usePager: true,
            pageSizeOptions: [50, 100, 150, 200],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }
    
    //刷新
    function h2y_refresh() {
        document.location.reload();
    }
    
    
  	//查询
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
        	 parms: [{name:"ifQuery",value:sqlWhere}]
         });
         
         manager.loadData(true);
    }
       
  	function h2y_addRecord(){
  		var manager = $("#listgrid").ligerGetGridManager();
  		var rows = manager.getCheckedRows();
  		if(rows == null || rows.length == 0){
  			alert("请选择行");
  			return;
  		}else if(rows.length > 1){
  			alert("请选择当行记录");
  			return;
  		}
  		
  		var postDatas = {};
  		postDatas.qrcodeNo = rows[0].BOTTLE_QRCODE_NO;
  		postDatas.optUserId = 1;
  		postDatas.circulateFlag = Math.floor(Math.random()*5);
  		
  		var params = {};
  		params.slock = "1";
  		params.skey = "1";
  		params.sid = "1";
  		params.os = "1";
  		params.osv = "1";
  		params.appv = "1";
  		params.postData = JSON.stringify(postDatas);
  		var src = "<%=basePath%>business/tracecirculaterecord/addRecord.htm";
  		$.post(src,params,function(data){
  			var jsonReturn = eval("(" + data + ")");
  			if(jsonReturn.resultFlag == 1){
  				alert(jsonReturn.resultMsg);
  			}else{
  				alert(jsonReturn.resultMsg);
  			}
  		});
  	}
  	
  	function h2y_lookRecord(){
  		var manager = $("#listgrid").ligerGetGridManager();
  		var rows = manager.getCheckedRows();
  		if(rows == null || rows.length == 0){
  			alert("请选择行");
  			return;
  		}else if(rows.length > 1){
  			alert("请选择单行记录");
  			return;
  		}
  		
  		
  	<%-- //	var src = "<%=basePath%>business/tracecirculaterecord/getRecord.htm"; --%>
  	//	$.post(src,{qrcodeNo:rows[0].BOTTLE_QRCODE_NO},function(data){
  	//		alert(data);
  	//	});
  		var paramString = "bottleQrcodeNo=" + rows[0].BOTTLE_QRCODE_NO;
  		var src = "<%=basePath%>business/tracecirculaterecord/init.htm?" + paramString;
  		top.f_addTab("traceprovidernumbersection_detail"+rows[0].ID,"流通记录", src);
  		
  	}
  	
  	function h2y_getRecord(){
  		var manager = $("#listgrid").ligerGetGridManager();
  		var rows = manager.getCheckedRows();
  		if(rows == null || rows.length == 0){
  			alert("请选择行");
  			return;
  		}else if(rows.length > 1){
  			alert("请选择单行记录");
  			return;
  		}
  		var postDatas = {};
  		postDatas.qrcodeNo = rows[0].BOTTLE_QRCODE_NO;
  		
  		postDatas.circulateFlag = Math.floor(Math.random()*5);
  		
  		var params = {};
  		params.slock = "1";
  		params.skey = "1";
  		params.sid = "1";
  		params.os = "1";
  		params.osv = "1";
  		params.appv = "1";
  		params.postData = JSON.stringify(postDatas);
  		
  		var src = "<%=basePath%>business/tracecirculaterecord/getRecord.htm";
  		$.post(src,params,function(data){
  			alert(data);
  		});
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