<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>


<script type="text/javascript">

    $(function () {
    	
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
        
         $("#start_time").timepicker({
       	   hourGrid: 4,
     	   minuteGrid: 10
         });
         
         $("#end_time").timepicker({
         	   hourGrid: 4,
         	   minuteGrid: 10
         });
         
         $("#start_time").val("00:00");
         $("#end_time").val(getCurTime());
         f_getList();
    });
    
    
    function f_getList() {
    	
        var url_1 = "business/ordercount/getTodayList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms:  [{name:"startTime",value:$("#start_time").val()},
                     {name:"endTime",value:$("#end_time").val()}],
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
    	
    	 var startTime = $("#start_time").val();
    	 var endTime = $("#end_time").val();
    	 
    	 if(!dayCheck(startTime,endTime)) return;
    	
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"startTime",value:startTime},
                     {name:"endTime",value:endTime}]
         });
         manager.loadData(true);
    }
    
    
    function dayCheck(startTime,endTime){
    	
    	if(""==startTime){
  			 
  			 alert("开始时间不能为空！");
  			 return false;
  		 }
  		 
		if(""==endTime){
  			 
  			 alert("截止时间不能为空！");
  			return false;
  		 }
		
		var startTimeInt = startTime.replace(/:/g, "");
		var endTimeInt = endTime.replace(/:/g, "");

		if(startTimeInt>endTimeInt){
			alert("开始时间必须在截止时间之前！");
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
	    m = add_zero(m);
	    d = add_zero(d);
	    return y+"-"+m+"-"+d;   
    } 
    
    function getCurTime(){
     	var d = new Date();
     	var hours = add_zero(d.getHours());
     	var minutes = add_zero(d.getMinutes());
     	return hours+":"+minutes; 
    }

    function add_zero(temp){
     	if(temp<10) return "0"+temp;
     	else return temp;
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
    				<td align='right'   width='80px'  >日期:&nbsp;</td>
    				<td colspan='1'  align='left' >
    					<input style='width:80px;' id='start_time'  name='start_time'   type='text'  value='' />~
    					<input style='width:80px;' id='end_time'  name='end_time'   type='text'  value='' />
    				</td>
    			</tr>
    		</table>
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>