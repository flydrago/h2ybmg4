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
        var typeCode = "";
        var markDataJson = null;
        
        var introduceEditor = null;
        var specParamEditor = null;
        
        var fileId = 0;
        
        var minPrice = null;
        var maxPrice = null;
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            h2y_showPic(${fileDataListJson});
            getGoodsDetail($("#goodsId").val(),"detail");
            
        });
        
        
        function h2y_showPic(fileData){
        	
        	$("#goodspic_div").html("");
        	var imageHtml = "";
        	$(fileData).each(function(){
            	
           		var json_str = "{\"id\":\""+this.id+"\"}";
           		if(this.fileType==1){
           			$("#goodspic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"goodsPicImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>");
           		}else{
           			if(this.ord==2){
           				imageHtml += "<input type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>";
           			}else{
           				imageHtml += "<input type=\"hidden\" name=\"picData\"  value='"+json_str+"'/>";
           			}
           		}
           		fileId++;
            });
        	if(imageHtml!=""){
        		$("#goodslogo_div").html(imageHtml);
                $(".goodsLogoImg_lightbox").lightBox();
        	}
            $(".goodsPicImg_lightbox").lightBox();
        }
        
        function h2y_refresh() {
            document.location.reload();
        }
        
		//得到商品详细
		function getGoodsDetail(goosId,op){
			
			$.post("business/goods/getDetail.htm", {id:goosId,op:op}, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if(op=="callback"){
                	showDefaultData(jsonReturn);
                }
                showGoodsDesc(jsonReturn);
            });
		}
		
		//显示默认数据
		function showDefaultData(data){
			
			 //alert(JSON.stringify(data));
            h2y_showPic(data.fileDataList);
            $("#goodsNickName").val(data.goods.goodsName);
            
            if(data.goods.memberPrice!=null){
            	$("#memberPrice").val(data.goods.memberPrice);
            }
            if(data.goods.marketPrice!=null){
            	$("#marketPrice").val(data.goods.marketPrice);
            }
            if(data.goods.sellPrice!=null){
            	$("#sellPrice").val(data.goods.sellPrice);
            }
            
            if(data.goodsInfo!=null){
            	
            	 if(data.goodsInfo.introduce!=null){
                 	introduceEditor.html(data.goodsInfo.introduce);
                 }
                 if(data.goodsInfo.specParam!=null){
                 	specParamEditor.html(data.goodsInfo.specParam);
                 }
            }
		}
		
		function showGoodsDesc(data){
			
			//类型
            var typeHtml = "";
            $(data.typeList).each(function(){
            	if(typeHtml==""){
            		typeHtml = this.typeName;
            	}else{
            		typeHtml += "/"+this.typeName;
            	}
            });
			
			var goods = data.goods;
			var goodsDescHtml = "名称："+goods.goodsName;
			goodsDescHtml += "<br/>类型为："+typeHtml;
            goodsDescHtml += "<br/>指导价："+goods.minPrice+"~"+goods.maxPrice;
            goodsDescHtml += "<br/>包装："+data.goodsUnitName;
            goodsDescHtml += "<br/>规格：1x"+goods.spec;
            $("#goods_desc").html(goodsDescHtml);
            minPrice = parseFloat(goods.minPrice);
            maxPrice = parseFloat(goods.maxPrice);
            
            //标签
            var markHtml = "";
            $(data.markList).each(function(){
            	markHtml += "<br/>"+this.MARK_NAME+"："+this.NAME;
            });
            $("#goods_desc").append(markHtml);
            
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
		
		<input name="id" type="hidden" id="id" value="${goodsPrice.id}" />
		<input name="goodsId" type="hidden" id="goodsId" value="${goodsPrice.goodsId}" />
		<input name="goodsVersion" type="hidden" id="goodsVersion" value="${goodsPrice.goodsVersion}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
		
			<tr>
				<td class="h2y_table_label_td">
					商品基本信息:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goods_desc">
						
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsPrice.goodsNickName}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					会员价:
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.memberPrice}
				</td>
				<td class="h2y_table_label_td">
					市场价:
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.marketPrice}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					库存:
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.inventory}
				</td>
				<td class="h2y_table_label_td">
					排序:
				</td>
				<td class="h2y_table_edit_td">
					${goodsPrice.ord}
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
					商品介绍：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${goodsInfo.introduce}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品Logo:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodslogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品图片:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodspic_div"></div>
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>