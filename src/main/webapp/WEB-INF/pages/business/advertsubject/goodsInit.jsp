<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">

	var subjectId = "${advertSubject.id}";
	var subjectType = "${advertSubject.subjectType}";
    $(function () {
    	
    	//hwtt_setSqlCondition();
    	
    	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '添加商品' , click: h2y_add , icon:'add' },
    	                                       { line:true },{ text: '移除商品' , click: h2y_delete , icon:'delete' },
    	                                       { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});

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
    	
        var url_1 = "business/advertsubject/getGoodsList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [${gridComluns}
            		<c:if test="${advertSubject.subjectType==0}">
          			,{display: '排序', name: 'ORD', width: 100, maxWidth: 150, align: 'left', type: 'text',editor: { type: 'spinner' }}
          			</c:if>
          			],
            url: url_1,
            parms: [{name:"subjectId",value:subjectId}],
            showTitle: false,
            dataAction: "server",
            pageSize: 20,
            <c:if test="${advertSubject.subjectType==0}">
            checkbox:true,
        	</c:if>
            height: "100%",
            width: "100%",
            usePager: false,
            enabledEdit: true,
            clickToEdit: true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
                
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            	selectRows(row);
            },onAfterEdit: function (row, index, data) {
            	updateOrd();
            }
        });
    }
    
    function selectRows(row){
    	var manager = $("#listgrid").ligerGetGridManager();
    	if(manager.isSelected(row))
    		manager.unselect(row);
    	else
    		manager.select(row);
    }
    
	function updateOrd(){
    	
    	var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getUpdated();
        if(null==rows || undefined==rows || null==rows[0] || undefined==rows[0]) return;
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/advertsubject/saveGoodsOrd.htm",{id:rows[0].ID,ord:rows[0].ORD}, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                f_query();
            }
        });
    }
    
 	function f_onSelect(node) {
    	
    	if(node==null || node.data==null || node.data.id==null) return;
        f_query();
    }

    
    function h2y_add() {
    	
    	if(subjectType == "0"){
    		
    		openPriceGoodsSelectDialog({selectType:"multi"});    		
    	}else{
    		
    		 <%--注意该处url可能不符合你的要求，请注意修改--%>
   	        $.post("business/advertsubject/getGoodsRows.htm",{subjectId:subjectId}, function (data) {
   	            if (data > 0) {
   	                alert("主题类型为商品详细的主题，只能添加一个商品！");
   	            } else {
   	            	openPriceGoodsSelectDialog({selectType:"radio"});
   	            }
   	        });
    	}
    }
    
    function h2y_delete() {
    	
        var manager = $("#listgrid").ligerGetGridManager();

        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }

        if (!confirm("确定移除所选商品吗？")) return;
        
        var goodsPriceIds = "";
        $(rows).each(function(){
			if(goodsPriceIds==""){
				goodsPriceIds = this.GOODS_PRICE_ID;
			}else{
				goodsPriceIds += ","+this.GOODS_PRICE_ID; 				
			}
		});

        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/advertsubject/saveGoods.htm",{subjectId:subjectId,goodsPriceIds:goodsPriceIds,op:"delete"}, function (data) {
            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert("移除成功！");
                f_query();
            } else {
            	alert("移除失败！");
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
    
  	//商品选择回调函数
    function h2y_priceGoodsSelectCallBack(data){
    	
    	if(data==null) return;
    	
    	var goodsPriceIds = "";
    	if(subjectType=="0"){
    		$(data).each(function(){
    			if(goodsPriceIds==""){
    				goodsPriceIds = this.ID;
    			}else{
    				goodsPriceIds += ","+this.ID; 				
    			}
    		});
    	}else{
    		goodsPriceIds = data.ID;
    	}
    	//alert("goodsPriceIds:"+goodsPriceIds);
    	<%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/advertsubject/saveGoods.htm",{subjectId:subjectId,goodsPriceIds:goodsPriceIds,op:"add"},function (data) {
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
    	<%--
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${conditionHtml} </div>
    	 --%>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>