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
		 
		 .goods_name_td{
		 	width:200px;
		 }
		 
		 .goods_count_td{
		 	width:100px;
		 }
		 
		 .goods_op_td{
		 	width:100px;
		 }
		 
	</style>

    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;

        var typeIndex = 0;
        var goodsTypeId = "";
        var markDataJson = null;
        
        var introduceEditor = null;
        var specParamEditor = null;
        
        var fileId = 0;
        var goods_select_cb = 0;//0:赠品、1：关联
        $(function () {
        	
            KindEditor.ready(function(K) {
            	 introduceEditor = K.create("#introduce", {
               	 uploadJson : '<%=basePath%>${imUpUrl}?type=fwb'
                });
        	});
            
            KindEditor.ready(function(K) {
            	 specParamEditor = K.create("#specParam", {
               	 uploadJson : '<%=basePath%>${imUpUrl}?type=fwb'
                });
        	});

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            if (op == "modify") {
            	
            	h2y_markSelectCallBack(${markData});
                $("#tr_next").hide();
                
                $(${fileDataListJson}).each(function(){
                	
               		var json_str = "{\"id\":\""+this.id+"\"}";
               		if(this.fileType==1){
               			$("#goodspic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"goodsPicImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a><a id=\"file_delete_href_"+fileId+"\" href=\"javascript:deletePic('"+fileId+"');\">删除</a>");
               		}else{
               			if(this.ord==2){
               				$("#goodslogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/><a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>");
               			}else{
               				$("#goodslogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/>");
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
            }
            
            //添加一级商品类型
            addselect();
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            
            $("#memberPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
            $("#marketPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
			
            $("#inventory").ligerSpinner({type: 'int',height: 25,width:150,isNegative:false});
            var specManager = $("#spec").ligerSpinner({type: 'int' ,minValue:1,height: 25,width:150,isNegative:false});
            $("#ord").ligerSpinner({type: 'int' ,height: 25,width:150,isNegative:false});
            
            $("#selectMarkBut").click(function(){
            	
            	goodsTypeId = $("#goodstype_select").val();
            	if(""==goodsTypeId || undefined==goodsTypeId){
            		alert("请选择商品类型");
            		return;
            	}
            	$("#markTypeId").val(goodsTypeId);
            	openMarkSelectDialog({typeCode:goodsTypeId});
            });
            
            
            $("#goodsPicUploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:true
            	});
            });
            
            $("#goodsLogoUploadBut").click(function(){
            	openImageUploadDialog();
            });
            
            $("#selectGiftBut").click(function(){
            	goods_select_cb = 0;
            	openPriceGoodsSelectDialog({selectType:"radio"});
            });
            
            $("#selectRelationBut").click(function(){
            	goods_select_cb = 1;
            	openPriceGoodsSelectDialog({selectType:"radio"});
            });
            
        });
        
        
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
        
        function h2y_save() {
        	
        	if(!Validator.form()) return;
        	
        	goodsTypeId = $("#goodstype_select").val();
        	if(""==goodsTypeId || undefined==goodsTypeId){
        		alert("请选择商品类型");
        		return;
        	}
        	$("#goodsTypeId").val(goodsTypeId);
        	
        	var markdata = $("#markData").val();
        	if(markdata!="[]" && undefined!=markdata && null !=markdata && $("#markTypeId").val() != goodsTypeId){
        		alert("类型下面的标签不匹配，请重新选择标签！");
        		return;
        	}
        	
        	//同步富文本编辑框数据
            introduceEditor.sync();
            
            if('undefined' == $("input[type=hidden][name='logoData']").val() || undefined == $("input[type=hidden][name='logoData']").val()){
            	alert("请上传logo图片！");
            	return;
            }
            
            if('undefined' == $("input[type=hidden][name='picData']").val() || undefined == $("input[type=hidden][name='picData']").val()){
            	alert("请上传商品图片！");
            	return;
            }
            
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/goodsprice/savePrice.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_goodsprice_init_htm") != null) {
							top.f_getframe("business_goodsprice_init_htm").f_query();
						}
						top.f_delTab("goodsprice_modify_price${goodsPrice.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_goodsprice_init_htm") != null) {
							top.f_getframe("business_goodsprice_init_htm").f_query();
						}
						top.f_delTab("goodsprice_add_price");
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
        
        function h2y_refresh() {
            document.location.reload();
        }
      
        //标签回调
        function h2y_markSelectCallBack(data){
        	markDataJson = data;
        	$("#markData").val(JSON.stringify(data));
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
        
        function h2y_fileUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	$(data).each(function(){
        		
        		var json_str = "{\"fileType\":\"1\",\"fileName\":\""+this.fileName+"\",\"saveName\":\""+this.saveName+"\",\"savePath\":\""+this.savePath+"\",\"fileSize\":\""+this.fileSize+"\"}";
        		$("#goodspic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+this.savePath+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"goodsPicImg\" src=\"common/image/view.htm?URLPATH="+this.savePath+"\"></a><a id=\"file_delete_href_"+fileId+"\" href=\"javascript:deletePic('"+fileId+"');\">删除</a>");
        		fileId++;
        	});
        	
        	$(".goodsPicImg_lightbox").lightBox();
        }
        
        function h2y_imageUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	var imageHtml = "";
        	$(data).each(function(i){
        		var json_str = "{\"fileType\":\"0\",\"fileName\":\""+this.fileName+"\",\"saveName\":\""+this.saveName+"\",\"savePath\":\""+this.savePath+"\",\"fileSize\":\""+this.fileSize+"\"}";
        		if(i==1){
        			imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/><a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+this.savePath+"\" title=\""+this.fileName+"\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?URLPATH="+this.savePath+"\"></a>";
        		}else{
        			imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/>";
        		}
        	});
        	$("#goodslogo_div").html(imageHtml);
        	$(".goodsLogoImg_lightbox").lightBox();
        }
        
        
		function deletePic(fileId){
        	
        	if(!confirm("您确定要删除文件吗?")){
				return true;
			}
        	$("#file_input_"+fileId).remove();
        	$("#file_lightbox_"+fileId).remove();
        	$("#file_img_"+fileId).remove();
        	$("#file_delete_href_"+fileId).remove();
		}
		
		
		function h2y_priceGoodsSelectCallBack(data){
			if (goods_select_cb==0){
				data.GOODS_COUNT = 1;
				if ($("#gift_table input[name='gift_goods_id'][value='"+data.ID+"']").length == 0){
					add_gift(data);
				}
			}else{
				if ($("#relation_table input[name='relation_goods_id'][value='"+data.ID+"']").length == 0){
					add_relation(data);
				}
			}
		}
		
		
		function add_gift(data){
			
		    var trmodel = $("#gift_model_tr").clone();
		    trmodel.find(".goods_name_td").html(data.GOODS_NICK_NAME);
		    trmodel.find("input[name='gift_goods_id']").val(data.ID);
		    trmodel.find("input[name='gift_goods_count']").val(data.GOODS_COUNT);
		    trmodel.find("input[name='gift_goods_count']").ligerSpinner({type: 'int' ,minValue:1,height: 25,width:150,isNegative:false});
		    
		    trmodel.find("a").click(function(){
		        //移除元素
		        $(this).parent().parent().remove();
		    });
		    trmodel.removeAttr("id");
		    trmodel.show();
		    $("#gift_table").append(trmodel);
		}
		
		function add_relation(data){
			
		    var trmodel = $("#relation_model_tr").clone();
		    trmodel.find(".goods_name_td").html(data.GOODS_NICK_NAME);
		    trmodel.find("input[name='relation_goods_id']").val(data.ID);
		    trmodel.find("a").click(function(){
		        //移除元素
		        $(this).parent().parent().remove();
		    });
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
		
		<input name="id" type="hidden" id="id" value="${goodsPrice.id}" />
		<input name="goodsTypeId" type="hidden" id="goodsTypeId" value="${goodsPrice.goodsTypeId}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="goodsNickName" type="text" id="goodsNickName" class="h2y_input_long"
							value="${goodsPrice.goodsNickName}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					库存：
				</td>
				<td class="h2y_table_edit_td" >
					<input name="inventory" type="text" id="inventory" value="${goodsPrice.inventory}"/>
				</td>
				<td class="h2y_table_label_td">
					商品包装：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="goodsUnit" id="goodsUnit" type="radio" dictcode="goods_unit" value="${goodsPrice.goodsUnit}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品规格：
				</td>
				<td class="h2y_table_edit_td">
					<input name="spec" type="text" id="spec"  class="h2y_input_just" value="1"/>
				</td>
				<td class="h2y_table_label_td">排序：</td>
	            <td class="h2y_table_edit_td">
	                <input name="ord" type="text" id="ord" value="${goodsPrice.ord}"/>
	            </td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					会员价：
				</td>
				<td class="h2y_table_edit_td">
					<input name="memberPrice" type="text" id="memberPrice" value="${goodsPrice.memberPrice}"/>
				</td>
				<td class="h2y_table_label_td">
					市场价：
				</td>
				<td class="h2y_table_edit_td">
					<input name="marketPrice" type="text" id="marketPrice" value="${goodsPrice.marketPrice}"/>
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
					<input class="button" type="button" value="选择标签" id="selectMarkBut"/>：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="markData" type="hidden" id="markData" value="${markData}" />
					<input name="markTypeId" type="hidden" id="markTypeId" value="${goodsPrice.goodsTypeId}" />
					<div id="markHtml_show">
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="选择赠品" id="selectGiftBut"/>：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<table id="gift_table" class="model_class">
						<tr>
							<td class="goods_name_td">
								商品
							</td>
							<td class="goods_count_td">
								数量
							</td>
							<td class="goods_op_td">
								操作
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
							<td class="goods_op_td">
								<a><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="关联商品" id="selectRelationBut"/>：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<table id="relation_table" class="model_class">
						<tr>
							<td class="goods_name_td">
								商品
							</td>
							<td class="goods_op_td">
								操作
							</td>
						</tr>
						<tr id="relation_model_tr" style="display: none;">
							<input name="relation_goods_id" type="hidden" value="0" />
							<td class="goods_name_td">
								商品
							</td>
							<td class="goods_op_td">
								<a><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="上传Logo" class="button" id="goodsLogoUploadBut"/>：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodslogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="上传图片" class="button" id="goodsPicUploadBut"/>：
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
					<textarea name="introduce" id="introduce" class="h2y_editor_textarea">${goodsInfo.introduce}</textarea>
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>