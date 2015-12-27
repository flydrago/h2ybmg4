<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
	<style type="text/css">
		a:link,a:visited{
		 text-decoration:none;  /*超链接无下划线*/
		}
		a:hover{
		 text-decoration:underline;  /*鼠标放上去有下划线*/
		}
	</style>
    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;

        var fileId = 0;
        var storehouseId = ${storehouseGoodsDetail.storehouseId};
        var goodsindex = 0;
        var tempindex = 0;
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	h2y_add();
        });
        
        function h2y_save() {
        	
        	if(!dataValidation()) return;
        	
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/storehousegoodsinfo/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
	            if (jsonReturn.code == "1") {
                     alert(jsonReturn.msg);
                     if (top.f_getframe("storehouse_goods${storehouseGoodsDetail.storehouseId}") != null) {
						top.f_getframe("storehouse_goods${storehouseGoodsDetail.storehouseId}").f_query();
					 }
					 top.f_delTab("storehousegoodsinfo_add${storehouseGoodsDetail.storehouseId}");
                } else {
                    alert(jsonReturn.msg);
                }
                isSubmiting = false;
            });
        }
        
        
        function h2y_refresh() {
            document.location.reload();
        }
		
		//商品选择回调函数
		function h2y_priceGoodsSelectCallBack(data){
			//alert(JSON.stringify(data));
			
			$("#goodsId_"+tempindex).val(data.GOODS_ID);
            $("#goodsPriceId_"+tempindex).val(data.ID);
            $("#goodsPriceVersion_"+tempindex).val(data.VERSION);
            $("#goods_desc_"+tempindex).html("<a href=\"javaScript:h2y_detail("+data.ID+");\"><span title=\"编码："+data.NUMBER+"\">"+data.GOODS_NICK_NAME+"</span></a>");
		}
		
		//查看商品详细
		function h2y_detail(id) {

	        var src = "<%=basePath%>business/goodsprice/add.htm?op=detail&id="+id;
	        top.f_addTab("goodsprice_detail"+id,"商品详细", src);
	    }
		
		
		function h2y_add(){
			
			if($("#goods_table [name='goodsId']").length > 51){
				alert("一次最多添加50商品！");
				return;
			}
			
			goodsindex++;
			var newRow1 = $("#gm_tr").clone();
			newRow1.attr("id","gm_tr_"+goodsindex);
			newRow1.find("#goodsId").attr("id","goodsId_"+goodsindex).val("");
			newRow1.find("#goodsPriceId").attr("id","goodsPriceId_"+goodsindex).val("");
			newRow1.find("#goodsPriceVersion").attr("id","goodsPriceVersion_"+goodsindex).val("");
			newRow1.find("#goodsbut").attr("id","goodsbut_"+goodsindex).attr("onclick","h2y_goodsSelect("+goodsindex+");");
			newRow1.find("#goods_desc").attr("id","goods_desc_"+goodsindex).html("");
			newRow1.find("#goodsCount").attr("id","goodsCount_"+goodsindex).ligerSpinner({minValue:1,maxValue:9999999999,type: 'int' ,height: 25,width:100});
			newRow1.find("#rowop_div").attr("id","rowop_div_"+goodsindex).html(getrowdeleteHtml(goodsindex));
			
			newRow1.css("display","");
			
			$("#goods_table").append(newRow1);
		}
		
		function getrowdeleteHtml(index){
			
			var rowop_html = "";
			if(index == 1){
				rowop_html = "<a href=\"javaScript:h2y_add();\" style=\"display: inline-block;\">";
				rowop_html += "<img src=\"<%=uiPath%>lib/ligerUI/skins/icons/add.gif\" alt=\"添加\" title=\"添加\" /></a>";
			}else{
				rowop_html = "<a href=\"javaScript:h2y_delete("+index+");\" style=\"display: inline-block;\">";
				rowop_html += "<img src=\"<%=uiPath%>lib/ligerUI/skins/icons/delete.gif\" alt=\"删除\" title=\"删除\" /></a>";
			}
			
			return rowop_html;
		}
		
		function h2y_delete(index){
			
			$("#gm_tr_"+index).remove();
		}
		
		
		function h2y_goodsSelect(index){
			tempindex = index;
			openPriceGoodsSelectDialog();
		}
		
		function dataValidation(){
			
			var flag = true;
			
			$("#goods_table input[name='goodsId']").each(function(i){
				if(i!=0){
					if(this.value==""){
						alert("请选择商品！");
						flag = false;
						return false;
					}
				}
			});
			return flag;
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
	
		<input name="storehouseId" id="storehouseId" type="hidden" value="${storehouseGoodsDetail.storehouseId}" />
		
		<table id="goods_table" class="h2y_table" >
			
			<tr>
				<td class="h2y_table_label_td" style="text-align: center;" colspan="2">
					商品
				</td>
				<td class="h2y_table_label_td" style="text-align: center;width:100px;">
					数量
				</td>
				<td class="h2y_table_label_td" style="text-align: center;">
					操作
				</td>
			</tr>
			
			<tr id="gm_tr" style="display: none;">
				<td class="h2y_table_label_td">
					<input name="goodsId" id="goodsId" type="hidden" value="" />
					<input name="goodsPriceId" id="goodsPriceId" type="hidden" value="" />
					<input name="goodsPriceVersion" id="goodsPriceVersion" type="hidden" value="" />
					<input type="button" onclick="h2y_goodsSelect();" value="选择商品" class="button" id="goodsbut"/>:
				</td>
				<td class="h2y_table_edit_td" style="width:600px;">
					<div id="goods_desc">
						
					</div>
				</td>
				<td class="h2y_table_edit_td" style="width:100px;">
					<input name="goodsCount" type="text" id="goodsCount" value="100"/>
				</td>
				<td class="h2y_table_label_td">
					<div id="rowop_div" style="text-align:center;">
					</div>
				</td>
			</tr>
			
			
		</table>
	</form>

</body>
</html>