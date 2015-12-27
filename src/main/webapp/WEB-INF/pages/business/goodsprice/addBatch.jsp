<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

        
        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save_batch , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        $("#layout1").ligerLayout({
            leftWidth: 300,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });

        f_getList();
      	//添加一级商品类型
        addselect("0","","");
    });

    function f_getList() {
    	
        var url_1 = "<%=basePath%>business/goodsprice/getBatchList.htm";

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
            checkbox:true,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                //id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
               
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
                    {name:"name",value:$("#name").val()},
                    {name:"number",value:$("#number").val()}]
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
	
	
	function addselect(id,obj){
		
		if(id==""){
			return;
		}
		
		$.post("business/goodstype/getSelectList.htm",{id:id},function(data){
			
			var jsonReturn=eval("("+data+")");
			var selectHtml = "";
			$(jsonReturn).each(function(){
				selectHtml += "<option value=\""+this.code+"\">"+this.name+"</option>";
			});
			
			if(selectHtml!=""){
				selectHtml = "<select id=\"goodstype_select_"+typeIndex+"\" class=\"goodstype_select\"><option value=\"\"> </option>"+selectHtml+"</select>";
				$("#goodstype_td").append(selectHtml+"");
				if(obj!=""){
					hwtt_do_change(obj.next());
				}else{
					hwtt_do_change($("#goodstype_select_"+typeIndex));
				}
			}
			typeIndex++;
		}); 
	}
	
	
	function h2y_save_batch(){
		var manager = $("#listgrid").ligerGetGridManager();
        var rows = manager.getCheckedRows();
        if (rows == null || rows.length == 0) {
            alert('请选择行');
            return;
        }
        
        var dataIds = "";
        $(rows).each(function(){
        	if(dataIds==""){
        		dataIds = this.ID;
        	}else{
        		dataIds += ","+this.ID;
        	}
        });
        op = "saveBatch";
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("business/goodsprice/saveBatch.htm",{dataIds:dataIds,op:op},function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                
                if (top.f_getframe("business_goodsprice_init_htm") != null) {
					top.f_getframe("business_goodsprice_init_htm").f_query();
				 }
				 top.f_delTab("goodsprice_add_batch");
                
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
	 
    <div position="center" title="">
    	<div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">
    	<table id="id-querytable" class="css-querytable">
		    <tr height="25px">
		        <td align="right">
		                                 商品名称:
		        </td>
		        <td align="left">
		            <input class="h2y_input_just" id="name" name="name" type="text" value=""/>
		        </td>
		        <td align="right">
		                               商品编号:
		        </td>
		        <td colspan="1" align="left">
		            <input  class="h2y_input_just" id="number" name="number" type="text" value=""/>
		        </td>
		        <td align="left">
		           <input type="button" value="搜索" class="button" onclick="h2y_search();" />
		        </td>
		    </tr>
		    <tr height="25px">
		        <td align="right">
		                                 商品类型:
		        </td>
		        <td id="goodstype_td"  align="left" colspan="4">
		        	
		        </td>
		    </tr>
		</table> 
    	</div>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>