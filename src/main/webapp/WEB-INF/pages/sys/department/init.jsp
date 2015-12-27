<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var id = 0;
    var deptType = 0;
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 230,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        $("#tree1").ligerTree({
            //远程加载，有时会加载不出来
            //url: "sys/department/getList.htm?op=tree",
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
    });

    function f_getList() {
        var url_1 = "sys/department/getList.htm";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [
                { name: "pid", value: id},{ name: "op", value: "grid"}
            ],
            showTitle: false,
            dataAction: "server",
            sortName: "ID",
            pageSize: 20,
            height: "100%",
            width: "100%",
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

    function f_html(row) {

        return "";
    }

    function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
    	id = node.data.id;
    	deptType = node.data.type;
		var treemanager= $("#tree1").ligerGetTreeManager();
		var haschildren=node.data.children ? true : false;
    	if(!haschildren){
			$.ajax({
				type: "POST",
				url: "sys/department/getList.htm?op=tree",
				data: {pid:id},
	     		success: function(jsondata){
					var nodes=eval("("+jsondata+")");		     	      
					treemanager.append(node.target, nodes);
					f_query();
	        	}
	    	});
		}else{
			f_query();
		}
    }

    function h2y_add() {
    	
    	var treemanager = $("#tree1").ligerGetTreeManager();
        var treeNote = treemanager.getSelected();
        if(treeNote==null || treeNote.length == 0){
            alert("请选择父级部门！");
            return;
        }
        if(deptType==1){
        	alert("门店下面不能添加部门！");
        	return;
        }

        var src = "<%=basePath%>sys/department/add.htm?op=add&parentId="+id+"&deptType="+0;

        $.ligerDialog.open({
            name:"sys_department_add",
            title:  "添加部门",
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
                    f_getframe("sys_department_add").h2y_save();
                } },
                { text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                } }
            ]
        });
    }
    
    
    function h2y_addshop() {
    	
    	var treemanager = $("#tree1").ligerGetTreeManager();
        var treeNote = treemanager.getSelected();
        if(treeNote==null || treeNote.length == 0){
            alert("请选择父级部门！");
            return;
        }
        
        if(deptType==1){
        	alert("门店下面不能添加门店！");
        	return;
        }
        
        if(id==0){
        	alert("单位下面不能直接门店！");
        	return;
        }

        var src = "<%=basePath%>sys/department/add.htm?op=add&parentId="+id+"&deptType="+1;
        top.f_addTab("sys_shop_add","添加门店", src);

//         $.ligerDialog.open({
//             name:"sys_shop_add",
//             title:  "添加门店",
//             height: 450,
//             url: src,
//             width: 850,
//             showMax: true,
//             showToggle: true,
//             showMin: false,
//             isResize: true,
//             modal: true,
//             buttons: [
//                 { text: '确定', onclick: function (item, dialog) {
//                     f_getframe("sys_shop_add").h2y_save();
//                 } },
//                 { text: '取消', onclick: function (item, dialog) {
//                     dialog.close();
//                 } }
//             ]
//         });
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
        
        var src = "<%=basePath%>sys/department/add.htm?op=modify&id="+rows[0].ID;
        
        var dialogTtile = rows[0].DEPT_TYPE==1?"修改门店":"修改部门";
        
        if(rows[0].DEPT_TYPE==1){
        	top.f_addTab("sys_department_modify_"+rows[0].ID,"修改门店", src);
        }else{
        	$.ligerDialog.open({
                name:"sys_department_modify",
                title:  dialogTtile,
                height: 450,
                url: src,
                width: 850,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true,
                buttons: [
                    { text: '确定', onclick: function (item, dialog) {
                        f_getframe("sys_department_modify").h2y_save();
                    } },
                    { text: '取消', onclick: function (item, dialog) {
                        dialog.close();
                    } }
                ]
            });
        }

        
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

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("sys/department/delete.htm?id=" + rows[0].ID, function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                h2y_refresh()
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
                 { name: "pid", value: id},{ name: "op", value: "grid"}
            ]
        });
        manager.loadData(true);
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
        <ul id="tree1"></ul>
    </div>

    <div position="center" title="">
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>