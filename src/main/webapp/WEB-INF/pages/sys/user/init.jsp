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
            delay: 2, 
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
    	
        var url_1 = "sys/user/getList.htm?op=grid";

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
    
    
    //移入人员
    function h2y_moveUser(){
    	
    	var treemanager = $("#tree1").ligerGetTreeManager();
        var treeNote = treemanager.getSelected();
        if(treeNote==null || treeNote.length == 0){
            alert("请选择部门！");
            return;
        }
    	openUserSelectDialog();
    }
    
    //移除人员
    function h2y_removeUser(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        $.post("sys/user/move.htm",{op:"out",deptUserId:rows[0].DEPT_USER_ID,userId:rows[0].ID},function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query();
            } else {
                alert(jsonReturn.msg);
            }
    });
    }
    
    

    function h2y_add() {
    	
    	var treemanager = $("#tree1").ligerGetTreeManager();
        var treeNote = treemanager.getSelected();
        if(treeNote==null || treeNote.length == 0){
            alert("请选择部门！");
            return;
        }
        
        if(deptId == 0){
        	alert("单位下面不能直接添加人员！");
        	return;	
        }

        var src = "<%=basePath%>sys/user/add.htm?op=add&sysDeptUser.deptId="+deptId;

        $.ligerDialog.open({
            name:"sys_user_add",
            title:  "添加人员",
            height: 360,
            url: src,
            width: 700,
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            modal: true,
            buttons: [
                { text: '确定', onclick: function (item, dialog) {
                    f_getframe("sys_user_add").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }


    function h2y_modify() {

        var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }

        var src = "sys/user/add.htm?op=modify&sysUser.id="+rows[0].ID+"&sysDeptUser.id="+rows[0].DEPT_USER_ID;

        $.ligerDialog.open({
            name:"sys_user_modify",
            title:  "修改人员",
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
                    f_getframe("sys_user_modify").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }

    function h2y_delete() {

        var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }

        if (!confirm("删除后不可恢复，确定删除此行吗？")) return;
        
        var src = "sys/user/delete.htm?op=delete&sysUser.id="+rows[0].ID+"&sysDeptUser.id="+rows[0].DEPT_USER_ID;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post(src, function (data) {
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
    
    function h2y_query(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        manager.changePage("first"); 
        manager.setOptions({
            parms: [
                { name: "deptId", value: deptId},
                { name: "deptCode", value: deptCode},
                { name: "userName", value: $("#userName").val()},
                { name: "mobile", value: $("#mobile").val()},
                { name: "email", value: $("#email").val()},
                { name: "isCascade", value:isCascade}
            ]
        });
        manager.loadData(true);
    }
    
    
    function h2y_resetPwd(){
    	
        var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }

        var src = "sys/user/resetPwd.htm?id="+rows[0].ID;
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post(src, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
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