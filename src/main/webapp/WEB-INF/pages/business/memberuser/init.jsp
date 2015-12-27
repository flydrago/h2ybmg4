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
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        f_getList();
    });

    function f_getList() {
        var url_1 = "business/memberuser/getList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
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

    
    //查询推荐用户
    function h2y_recommend(){
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        var src = "<%=basePath%>business/memberuser/recommendInit.htm?account="+rows[0].account;
        top.f_addTab("recommend_list"+rows[0].account,"推荐用户列表", src);
    }
    //会员区域修改
    function h2y_zoneUpdate(){
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        var src = "<%=basePath%>business/memberuser/zoneUpdate.htm?account="+rows[0].account+"&&zoneCode="+rows[0].zone;
         top.f_addTab("business_memberuser_zoneUpdate"+rows[0].account,"会员区域修改", src); 
    }
    
    
    
    function f_html(row) {

        return "";
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
    
    //搜索
    function h2y_search(){
    	
   	    var sqlWhere = hwtt_getSqlCondition();
   	    var manager = $("#listgrid").ligerGetGridManager();
        manager.changePage("first"); 
        manager.setOptions({
            parms: [{name:"ifQuery",value:sqlWhere}]
        });
        manager.loadData(true);
   }
   
    
	
	
	//查看详细
	function h2y_deatail(){
		var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }      
        var src = "<%=basePath%>business/memberuser/detail.htm?id="+rows[0].id;
        top.f_addTab("memberuser_detail"+rows[0].id,"会员详细", src);
	}
	
	
	//修改状态
	function h2y_modify(){
		var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
		
        var opWarn = "禁用后不可恢复，确定禁用选中行吗？";
        
        if (!confirm(opWarn)) return;
        
        var dataIds = "";
        $(rows).each(function(){
        	if(dataIds==""){
        		dataIds = this.id;
        	}else{
        		dataIds += ","+this.id;
        	}
        })
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/memberuser/modifyStatus.htm",{dataIds:dataIds},function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
        });
	}
	
</script>


</head>
<body>
<form>
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
	    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
	        <div id="listgrid"></div>
	    </div>
	</div>
</form>
</body>
</html>