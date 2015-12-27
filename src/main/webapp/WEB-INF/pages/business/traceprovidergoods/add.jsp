<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    
	<style type="text/css">
		.goodsInput {
			padding-left: 5px;
			border: 0;
			border-bottom: 1px solid #D1E8FF;
		}
	</style>
	
    <script type="text/javascript">
       var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var parentId = "${parentId}";
        var form = null;
		var goodsIds = ${goodsIds};
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({
        		items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },
        	            { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]
        	});
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            $("#providerGoodsBtn").click(function(){
            	selectGoods();
            });
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
        });

		//保存
        function h2y_save() {
        	
        	if(!Validator.form()) return;

        	if(!judgeHadGoods()) return;
        	
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/traceprovidergoods/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                       /*  parent.f_query();
                        frameElement.dialog.close(); */
                       if (top.f_getframe("business_traceprovidergoods_init_htm") != null) {
							top.f_getframe("business_traceprovidergoods_init_htm").f_query();
						 }
						top.f_delTab("business_traceprovidergoods_add" + parentId); 
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                      //  parent.f_query();
                      //  frameElement.dialog.close();
                        if (top.f_getframe("business_traceprovidergoods_init_htm") != null) {
							top.f_getframe("business_traceprovidergoods_init_htm").f_query();
						 }
						top.f_delTab("business_traceprovidergoods_add" + parentId); 
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
		//刷新
        function h2y_refresh() {
            document.location.reload();
        }
        
        
		//选择商品窗口
        function selectGoods(){
        	callBackFlag = "1";
        	openGoodsSelectDialog();
        }
        
		//回调函数
      function  h2y_goodsSelectCallBack(data){
      		data_to_div_input({inputToId:"goodsDataTd",data:data});
      }
      
		//删除一行商品
      function clearLine(elem){
    	  $(elem).parent().remove();
      }
	
	//判断是否选有商品
	function judgeHadGoods(){
		if($("#goodsDataTd").html().replace(/\s/g, "") == ""){
			alert("请选择商品");
			return false;
		}else{
			return true;	
		}
	}
    </script>
	
</head>

<body>

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
<form name="editform" method="post" action="" id="editform">
        <input type="hidden" name="parentId" value="${parentId}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">供应商名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="providerName" type="text" id="providerName"  class="h2y_dialog_input_long" readonly="readonly" value="${parentName}" style="padding-left:5px;"/>
                <input name="providerId" type="hidden" id="providerId" readonly="readonly" value="${parentId}"/>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td"><input type="button" id="providerGoodsBtn"  value="选择供应商品" style="padding-left:5px;padding-right:5px;"/></td>
            <td id="goodsDataTd" class="h2y_dialog_table_edit_td">
            	
            </td>
        </tr>

    </table>
</form>

</body>
</html>