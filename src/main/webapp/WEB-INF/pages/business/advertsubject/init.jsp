<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var unitId = "${unitId}";
    $(function () {
    	
    	//hwtt_setSqlCondition();

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
        
        f_getList();
    });

    function f_getList() {
    	
        var url_1 = "business/advertsubject/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}],
            url: url_1,
            parms: [],
            showTitle: false,
            dataAction: "server",
            sortName: "CREATE_DATE",
            sortOrder: "desc",
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
                h2y_detail();
            }
        });
    }
    
 	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
        f_query();
    }

    
    function h2y_add() {
    	
        var src = "<%=basePath%>business/advertsubject/add.htm?op=add";
        top.f_addTab("advert_subject_add","广告主题添加", src);
    }
    
    
    function h2y_detail() {
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        var src = "<%=basePath%>business/advertsubject/add.htm?op=detail&id="+rows[0].ID;
        top.f_addTab("advert_subject_detail"+rows[0].ID,"广告主题详细", src);
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
         
         if(rows[0].UNIT_ID!=unitId){
        	 
        	 alert("非当前平台数据不可操作！");
             return;
         }
         
         var src = "<%=basePath%>business/advertsubject/add.htm?op=modify&id="+rows[0].ID;
         top.f_addTab("advert_subject_modify"+rows[0].ID,"广告主题修改", src);
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
        
        if(rows[0].UNIT_ID!=unitId){
       	 
       	 	alert("非当前平台数据不可操作！");
            return;
        }

        if (!confirm("删除后不可恢复，确定删除此行吗？")) return;

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/advertsubject/delete.htm?id=" + rows[0].ID, function (data) {
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
    
    
    function f_query(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        manager.loadData(true);
    }
    
    function h2y_search(){
    	
    	 var sqlWhere = hwtt_getSqlCondition();
    	 var manager = $("#listgrid").ligerGetGridManager();
         manager.changePage("first"); 
         manager.setOptions({
             parms: [{name:"ifQuery",value:sqlWhere}]
         });
         manager.loadData(true);
    }
    
    function h2y_goods(){
    	
    	 var manager = $("#listgrid").ligerGetGridManager();
         var rows = manager.getCheckedRows();
         if (rows == null || rows.length == 0) {
             alert('请选择行');
             return;
         } else if (rows.length > 1) {
             alert("请选择单行记录");
             return;
         }
         
         if(rows[0].UNIT_ID!=unitId){
           	 
        	 alert("非当前平台数据不可操作！");
             return;
         }
         
         if(rows[0].SUBJECT_TYPE==2){
        	 alert("宣传页面，无需维护商品！");
        	 return;
         }
         
         var src = "<%=basePath%>business/advertsubject/goodsInit.htm?subjectId="+rows[0].ID;
         top.f_addTab("advert_subject_goods"+rows[0].ID,"广告主题商品维护", src);
    }
    
    
    //活动维护
    function h2y_activity(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].SUBJECT_TYPE!=2){
	       	 alert("非宣传页面，无需维护活动信息！");
	       	 return;
        }
        
        var src = "<%=basePath%>business/advertsubjectinfort/activityInit.htm?id="+rows[0].ID;
        top.f_addTab("advert_subject_activity_rt"+rows[0].ID,"主题活动信息维护", src);
   }
    
    
    //优惠劵维护
    function h2y_coupons(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].SUBJECT_TYPE!=2){
	       	 alert("非宣传页面，无需维护活动信息！");
	       	 return;
        }
        
        var src = "<%=basePath%>business/advertsubjectinfort/couponsInit.htm?id="+rows[0].ID;
        top.f_addTab("advert_subject_coupons_rt"+rows[0].ID,"主题优惠劵信息维护", src);
   }
    
  	//图片维护
    function h2y_img(){
    	
   	 	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].SUBJECT_TYPE!=2){
	       	 alert("非宣传页面，无需维护活动信息！");
	       	 return;
        }
        
        var src = "<%=basePath%>business/advertsubjectinfort/imgInit.htm?id="+rows[0].ID;
        top.f_addTab("advert_subject_img_rt"+rows[0].ID,"主题图片信息维护", src);
   }
  	
  	//分享图片维护
  	function h2y_share_img(){
  		var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        } else if (rows.length > 1) {
            alert("请选择单行记录");
            return;
        }
        
        if(rows[0].SUBJECT_TYPE!=2){
	       	 alert("非宣传页面，无需维护活动信息！");
	       	 return;
        }
        
        var src = "<%=basePath%>business/advertsubjectinfort/shareImgInit.htm?id="+rows[0].ID;
        top.f_addTab("advert_subject_share_img_rt"+rows[0].ID,"主题分享图片维护", src);
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
                <td align="right" width="90%">
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