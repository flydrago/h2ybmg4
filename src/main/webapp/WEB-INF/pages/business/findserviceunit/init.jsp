<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
	var unitId = 0;
	var unitTreeManager = null; 
	var serviceTreeManager = null; 
    $(function () {
    	
    	//hwtt_setSqlCondition();

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});
        $("#layout1").ligerLayout({
            leftWidth: 400,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        
        $("#unitTree").ligerTree({
            //远程加载，有时会加载不出来
            //url: "business/goodsmark/getList.htm?op=tree",
            data:${unitTreeData},
            checkbox: false,
            nodeWidth: 300,
            delay: 2,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });
        
        
        $("#serviceTree").ligerTree({  
			checkbox: true,
			nodeWidth: 120,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text",
			data: ${serviceTreedata}
		});
    });
    
	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	unitId = node.data.id;
    	serviceTreeManager = $("#serviceTree").ligerGetTreeManager();
    	serviceTreeManager.clear();
    	
    	var url = "<%=basePath%>business/findserviceunit/getList.htm?unitId="+unitId;
		$.ajax({
			type: "POST",
			url: url,
			data: "",
			success: function(jsondata){
				var nodes=eval("("+jsondata+")");	
				//alert(url);
				$("#serviceTree").ligerTree({  
					checkbox: true,
					nodeWidth: 120,
				    idFieldName: "id",
		            parentIDFieldName: "pid",
		            textFieldName: "text",
					data: nodes
				});

				//选中
				serviceTreeManager.selectNode(function (node){
					return node.isselect == "1" ? true : false;
				});
		    }
		});
    }
    
    
    function h2y_save() {
    	
	   //得到选中行的数据JSON对象（是集合形式）       
       var row = serviceTreeManager.getChecked();
       
	   var serviceIds = "";
       $(row).each(function(){
			
			if(serviceIds=="") serviceIds = this.data.id;
			else serviceIds += ","+this.data.id;
       });

		//注意该处url可能不符合你的要求，请注意修改
		var src="<%=basePath%>business/findserviceunit/save.htm";

		$.post(src,{serviceIds:serviceIds,unitId:unitId},function(data){
			var jsonReturn=eval("("+data+")");
			alert(jsonReturn.msg);
		});
    }
    
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
    
    <div position="left" style="height: 94%; overflow: auto;">
        <ul id="unitTree"></ul>
    </div>
    
    <div position="center" title="">
        <ul id="serviceTree"></ul>
    </div>
</div>
</body>
</html>