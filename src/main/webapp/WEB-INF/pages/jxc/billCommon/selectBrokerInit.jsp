<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var deptId = 0;
    var deptCode = "";
    var userDataJson = null;
    var isCascade = "no";
    var selectType = "${selectType}";
    $(function () {
    	
        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 230,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        $("#tree1").ligerTree({
            data:${treedata},
            checkbox: false,
            nodeWidth: 150,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });

        f_getList();
        
        $("#is_cascade").change(function(){
        	if(this.checked){
        		isCascade = "yes";
        	}else{
        		isCascade = "no";
        	}
        })
    });

    function f_getList() {
    	
        var url_1 = "jxc/common/getBrokerList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [
                { name: "deptId", value: deptId},{ name: "deptCode", value: deptCode},{ name: "isCascade", value: isCascade} 
            ],
            showTitle: false,
            dataAction: "server",
            sortName: "DU_ORD",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }

    function f_html(row) {

        return "";
    }

    function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	deptId = node.data.id;
    	deptCode = node.data.code;
		var treemanager= $("#tree1").ligerGetTreeManager();
		var haschildren=node.data.children ? true : false;
    	if(!haschildren){
			$.ajax({
				type: "POST",
				url: "sys/department/getList.htm?op=tree",
				data: {pid:deptId},
	     		success: function(jsondata){
					var nodes=eval("("+jsondata+")");		     	      
					treemanager.append(node.target, nodes);
	        	}
	    	});
		}
        f_query();
    }
    
    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
    	
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [
                { name: "deptId", value: deptId},
                { name: "deptCode", value: deptCode},
                { name: "isCascade", value:isCascade}
            ]
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
    
    //人员选择回调函数
    function h2y_userSelectCallBack(data){
    	//回显处理
    	//userDataJson = data;
    	
    	if(data==null || data==undefined || data.length==0){
    		return;
    	}
    	
    	var userIds = "";
    	$(data).each(function(){
    		if(userIds==""){
    			userIds = this.ID;
    		}else{
    			userIds += ","+this.ID;
    		}
    	});
    	
	    $.post("sys/user/move.htm",{op:"in",deptId:deptId,deptCode:deptCode,userIds:userIds},function (data) {
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
<div id="layout1" style="width: 100%">

    <div position="top">
        <table width="100%" class="my_toptoolbar_td">
            <tr>
                <td id="my_toptoolbar_td">
                    <div class="l-toolbar">&nbsp;${mname}</div>
                </td>
                <td align="right" width="700px">
                    <div id="toptoolbar"></div>
                </td>
            </tr>
        </table>
    </div>

    <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>

    <div position="center" title="">
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    		<table id="id-querytable" class="css-querytable" >
    			<tr height="25px">
    				<td align="right" width="80px">是否级联:&nbsp;</td>
    				<td align="left">
    					<input id="is_cascade" type="checkbox" title="级联查询"/>
    				</td>
    				<td align="right" width="150px">用户名（账号）:&nbsp;</td>
    				<td colspan="1"  align="left">
    					<input style="width:100px;" id="userName"  name="userName"   type="text"/>
    				</td>
    				<td align="right"   width="80px"  >电话:&nbsp;</td>
   					<td colspan="1"  align="left" >
   						<input style="width:100px;" id="mobile"  name="mobile"   type="text" />
   					</td>
   					<td align="right"   width="80px"  >邮箱:&nbsp;</td>
   					<td colspan="1"  align="left" >
   						<input style="width:100px;" id="email"  name="email"   type="text" />
   					</td>
   				</tr>
   			</table>
   		</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>