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
		 
		 .goodsPicImg { 
		 	width:240px;
		 	height:240px;
		 }
		 
		 .goodsLogoImg {
		 	max-width:600px;
		 }
		 
	</style>

    <script type="text/javascript">
    var Validator = null;
    var isSubmiting = false;
    var op = "${op}";
    var form = null;

    var typeIndex = 0;
    var gdsCode = "";
    var markDataJson = null;
    
    
    var introduceEditor = null;
    var specParamEditor = null;
    
    var fileId = 0;
    $(function () {
    	
    	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
    	h2y_markSelectCallBack(${markData});
    	
        $(${fileDataListJson}).each(function(){
        	
       		var json_str = "{\"id\":\""+this.id+"\"}";
       		if(this.fileType==1){
       			$("#goodspic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"goodsPicImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>");
       		}else{
       			if(this.ord==2){
       				$("#goodslogo_div").append("<input type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>");
       			}else{
       				$("#goodslogo_div").append("<input type=\"hidden\" name=\"picData\"  value='"+json_str+"'/>");
       			}
       		}
       		fileId++;
        });
        $(".goodsPicImg_lightbox").lightBox();
        $(".goodsLogoImg_lightbox").lightBox();
        
        //关联商品和赠品回显
        $(${dataGoodsListJson}).each(function(){
        	if(this.DATA_TYPE == 1){
        		add_gift(this);
        	}else{
        		add_relation(this);
        	}
        });
	    //添加一级商品类型
	    addselect();
    });

    function h2y_refresh() {
        document.location.reload();
    }
    
    //标签回调
    function h2y_markSelectCallBack(data){
    	markDataJson = data;
    	var markHtml = "";
    	$(data).each(function(){
    		
    		if(markHtml==""){
    			markHtml = this.MARK_NAME+":"+this.NAME;
    		}else{
    			markHtml += "</br>"+this.MARK_NAME+":"+this.NAME;
    		}
    	});
    	$("#markHtml_show").html(markHtml);
    }
    
    
    function addselect(){
		
		$.post("business/goodstype/getSelectList.htm",function(data){
			
			var jsonReturn=eval("("+data+")");
			var selectHtml = "";
			$(jsonReturn).each(function(){
				
				if(this.id == ${goodsPrice.goodsTypeId}){
					selectHtml += "<option value='"+this.id+"' selected='selected'>"+this.name+"</option>";
				}else{
					selectHtml += "<option value='"+this.id+"' >"+this.name+"</option>";
				}
			});
			
			if(selectHtml!=""){
				selectHtml = "<select id=\"goodstype_select\" class=\"goodstype_select\">"+selectHtml+"</select>";
				$("#goodstype_td").append(selectHtml+"");
			}
		}); 
	}
    
    
    function add_gift(data){
		
	    var trmodel = $("#gift_model_tr").clone();
	    trmodel.find(".goods_name_td").html(data.GOODS_NICK_NAME);
	    trmodel.find(".goods_count_td").html(data.GOODS_COUNT);
	    trmodel.removeAttr("id");
	    trmodel.show();
	    $("#gift_table").append(trmodel);
	}
	
	function add_relation(data){
		
	    var trmodel = $("#relation_model_tr").clone();
	    trmodel.find(".goods_name_td").html(data.GOODS_NICK_NAME);
	    trmodel.removeAttr("id");
	    trmodel.show();
	    $("#relation_table").append(trmodel);
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
				
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
		
			
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsPrice.goodsNickName}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					库存：
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.inventory}
				</td>
				<td class="h2y_table_label_td">排序：</td>
	            <td class="h2y_table_edit_td">
	                ${goodsPrice.ord}
	            </td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品包装：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="goodsUnit" id="goodsUnit" type="radio" disabled="disabled" dictcode="goods_unit" value="${goodsPrice.goodsUnit}"/>
				</td>
				<td class="h2y_table_label_td">
					商品规格：
				</td>
				<td class="h2y_table_edit_td">
					1x${goodsPrice.spec}
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					会员价：
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.memberPrice}
				</td>
				<td class="h2y_table_label_td">
					市场价：
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.marketPrice}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsPrice.memo}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品类型：
				</td>
				<td id="goodstype_td" class="h2y_table_edit_td2" colspan="3">
					
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					赠品：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<table id="gift_table"  class="model_class">
						<tr>
							<td class="goods_name_td">
								商品
							</td>
							<td class="goods_count_td">
								数量
							</td>
						</tr>
						<tr id="gift_model_tr" style="display: none;">
							<input name="gift_goods_id" type="hidden" value="0" />
							<td class="goods_name_td">
								商品
							</td>
							<td class="goods_count_td">
								<input name="gift_goods_count" type="text" value="0"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					关联商品：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<table id="relation_table" class="model_class">
						<tr>
							<td class="goods_name_td">
								商品
							</td>
						</tr>
						<tr id="relation_model_tr" style="display: none;">
							<td class="goods_name_td">
								商品
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品标签：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="markHtml_show">
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品Logo：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodslogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品图片：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodspic_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品介绍：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsInfo.introduce}
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>