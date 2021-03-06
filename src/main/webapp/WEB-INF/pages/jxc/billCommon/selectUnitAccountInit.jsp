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
	var selectType = "${selectType}";
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        f_getAccountList();
    });

    function f_getAccountList(){
    	var url_1 = "jxc/common/getAccountList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            <c:if test="${selectType=='multi'}">
            checkbox:true,
        	</c:if>
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                //id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            	 //parent.h2y_suppliserSelectCallBack(row);
                 frameElement.dialog.close();
            }
        });
    }
    
    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: []
        });
        manager.loadData(true);
    }
    
    function h2y_search(){
    	
    	var typeCode = $("#goodstype_td select:last").val();
    	if(""==typeCode || undefined==typeCode){
    		var selectCount = $("#goodstype_td select").length;
    		if(selectCount>1){
    			typeCode = $("#goodstype_td select:eq("+(selectCount-2)+")").val();
    		}
    	}
    	
   	    var manager = $("#listgrid").ligerGetGridManager();
        manager.changePage("first"); 
        manager.setOptions({
            parms: [{name:"typeCode",value:typeCode},
                    {name:"name",value:$("#goodsName").val()},
                    {name:"number",value:$("#goodsNumber").val()}]
        });
        manager.loadData(true);
   }

   function h2y_returnData(){
    	
        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
        
        if(selectType!="multi" && rows.length>1){
            alert("请选择单行记录");
            return;
        }
        
        if(selectType=="multi"){
        	return rows;        	
        }
        return rows[0];
    }
   
   
   function getTypeId(code){
   	
  	 var index = code.lastIndexOf("_");
  	 if(index == -1) return code;
  	 return code.substring(index+1,code.length);
   }
   
   function hwtt_do_change(obj){
		
		obj.change(function(){
			$(this).nextAll().remove();
			addselect(getTypeId(this.value),$(this));
			h2y_search();
		});
	}
</script>

</head>
<body>
<div id="layout1" style="width: 100%">

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