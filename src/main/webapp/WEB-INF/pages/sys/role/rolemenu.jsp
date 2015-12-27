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
    var roleId = 0;
    var isSubmiting = false;
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [${toolbar}]});

        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });
        

        $("#tree1").ligerTree({
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
/*             delay: 2, */
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });

        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "sys/role/getList.htm?op=rolemenu";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [{name: "roleId", value: roleId}],
            showTitle: false,
            dataAction: "server",
            sortName: "ROLE_ORD",
            pageSize: 1,
            height: "100%",
            width: "100%",
            checkbox: true,
            usePager: true,
            isChecked: function (row, index, data){
            	return row.ISHAS == 0? false:true;
            },
            pageSizeOptions: [1,5],
            tree: { columnName: "NAME" },
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }
    

 	function f_onSelect(node) {
    	if(node==null || node.data==null || node.data.id==null) return;
    	roleId = node.data.id+"";
    	roleId = roleId.replace(new RegExp("privilege_|sys_|normal_","gm"),"");
        f_query();
    }

    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [{name: "roleId", value: roleId}]
        });
        manager.loadData(true);
    }
    
    function h2y_save(){
    	
    	if(roleId == 0){
    		alert("请选中对应的角色！");
    		return;
    	}
    	
    	 var manager = $("#listgrid").ligerGetGridManager();
    	 
    	 var allData = manager.getData();
    	 
    	 var checkedRows = manager.getCheckedRows();
    	 var checkedData = "";
    	 var topMenuIds = "";
    	 
    	 $(allData).each(function(){
    		 
    		 //alert(JSON.stringify(this));
    		 if(h2y_isChecked(checkedRows,this.ID)){
    			 if(checkedData == ""){
        			 checkedData += "{\"ID\":"+this.ID+",\"TYPE\":\""+this.TYPE+"\"}";
        		 }else{
        			 checkedData += ",{\"ID\":"+this.ID+",\"TYPE\":\""+this.TYPE+"\"}";
        		 }
    		 }
    		 	
    		 if(this.P_ID=="0" && this.TYPE=="MENU"){
	    		 if(topMenuIds == ""){
	    			 topMenuIds += this.ID;
	    		 }else{
	    			 topMenuIds += ","+this.ID;
	    		 }
    		 }
    	 });
    	 checkedData = "["+checkedData+"]";
    	 
    	 var src = "sys/role/savePrivilege.htm";

    	 if (isSubmiting) {
             alert("表单正在提交，请稍后操作！");
             return;
         }
         isSubmiting = true;
         <%--注意该处url可能不符合你的要求，请注意修改--%>
         $.post(src,{roleId:roleId,topMenuIds:topMenuIds,checkedData:checkedData}, function (data) {
             var jsonReturn = eval("(" + data + ")");
             if (jsonReturn.code == "1") {
                 alert(jsonReturn.msg);
             } else {
                 alert(jsonReturn.msg);
             }
             isSubmiting = false;
         });
    	 
    }
    
    function h2y_isChecked(data,id){
    	
    	var flag = false;
    	$(data).each(function(){
    		
    		if(this.ID == id){
    			flag = true;
    		}
    		return;
    	});
    	return flag;
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