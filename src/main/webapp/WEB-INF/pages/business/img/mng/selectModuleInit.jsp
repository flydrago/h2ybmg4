<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../../include/top_list.jsp" %>

<script type="text/javascript">

	var selectType = "${selectType}";
    $(function () {

        //$("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

		f_getModuleList();        
    });

    function f_getModuleList() {
    	
        var url_1 = "img/mu/getModuleList.htm";

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