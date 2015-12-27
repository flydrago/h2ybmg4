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
		 .goodsLogoImg { max-width:600px;}
		 .goodsPicImg {
		 	width:240px;
		 	height:240px;
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
                
                gdsCode = "${goods.gdsCode}";
                var typeCode_array = gdsCode.split("_");
                modifyselect(typeCode_array);
                
                
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
            }else{
            	
                //添加一级商品类型
                addselect("0","","");
            }
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            
            $("#maxPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
            $("#minPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
            $("#memberPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
            $("#marketPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
            $("#sellPrice").ligerSpinner({type: 'float' ,height: 25,width:150,isNegative:false});
			
            $("#inventory").ligerSpinner({type: 'int',height: 25,width:150,isNegative:false});
            var specManager = $("#spec").ligerSpinner({type: 'int' ,minValue:1,height: 25,width:150,isNegative:false,disabled:true});
            $("#ord").ligerSpinner({type: 'int' ,height: 25,width:150,isNegative:false});
            
            $("#selectMarkBut").click(function(){
            	
            	gdsCode = $("#goodstype_td select:last").val();
            	if(""==gdsCode || undefined==gdsCode){
            		alert("请选择商品类型");
            		return;
            	}
            	$("#markTypeCode").val(gdsCode);
            	openMarkSelectDialog({typeCode:gdsCode});
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
            
            
            //设置限购数量
            if(${goods.isLimitSell==0}){
            	$("#limitSellNumber").attr("disabled",true);
            }           
			$("input[type=radio][name='isLimitSell']").change(function(){          	
            	if(this.value == 0){
            		$("#limitSellNumber").val('0');
            		$("#limitSellNumber").attr("disabled",true);
            	}else{
            		$("#limitSellNumber").attr("disabled",false);
            	}
            });
            
          //设置虚拟货币数量
            if(${goods.isAllowGrade==0}){
            	$("#limitGradeNumber").attr("disabled",true);
            }
            $("input[type=radio][name='isAllowGrade']").change(function(){          	
            	if(this.value == 0){
            		$("#limitGradeNumber").val('0');
            		$("#limitGradeNumber").attr("disabled",true);
            	}else{
            		$("#limitGradeNumber").attr("disabled",false);
            	}
            });
          
          //设置赠送达人币数量
            if(${goods.isGiftGrade==0}){
            	$("#limitGiftNumber").attr("disabled",true);
            }
            $("input[type=radio][name='isGiftGrade']").change(function(){          	
            	if(this.value == 0){
            		$("#limitGiftNumber").val('0');
            		$("#limitGiftNumber").attr("disabled",true);
            	}else{
            		$("#limitGiftNumber").attr("disabled",false);
            	}
            });
          
            //商品包装
            $("input[type=radio][name='goodsUnit']").change(function(){
            	if(this.value == 1 || this.value == 3){
            		specManager.setEnabled(1);
            	}else{
            		$("#spec").val(1);
            		specManager.setDisabled(1);
            	}
            });
            
            //箱修改时可以修改规格
            if(op == 'modify' && ('${goods.goodsUnit}' == 1 || '${goods.goodsUnit}' == 3)){
            	specManager.setEnabled();
            }
            
        });
        
        
        function getTypeId(code){
        	
        	 var index = code.lastIndexOf("_");
        	 if(index == -1) return code;
        	 return code.substring(index+1,code.length);
        }
        
 		function hwtt_do_change(obj){
			
			obj.change(function(){
				$(this).nextAll().remove();
				//设置商品编码前缀
				$("#goosTypePrefix").val(getTypeId($("#goodstype_td select:first").val()));
				$("#goosTypePrefixRed").val($("#goosTypePrefix").val());
				addselect(getTypeId(this.value),$(this));
				$("#_zoneCode").val(this.value);
			});
		}
 		
 		
 		
 		function getTypeCode(typeCode_array,index){
 			
 			var typeCodeTemp = "";
 			for (var i = 0; i < index; i++) {
             	if(typeCodeTemp==""){
             		typeCodeTemp = typeCode_array[i];
             	}else{
             		typeCodeTemp += "_"+typeCode_array[i];
             	}
			}
 			return typeCodeTemp;
 		}
 		
 		
		function modifyselect(typeCode_array){
			
			
			var pid = typeIndex==0 ? 0 : typeCode_array[typeIndex-1];
			
			$.post("business/goodstype/getSelectList.htm",{id:pid},function(data){
				
				typeIndex++;
				var jsonReturn=eval("("+data+")");
				var selectHtml = "";
				$(jsonReturn).each(function(){
					if(getTypeCode(typeCode_array,typeIndex)==this.code){
						selectHtml += "<option value=\""+this.code+"\" selected=\"selected\">"+this.name+"</option>";
					}else{
						selectHtml += "<option value=\""+this.code+"\">"+this.name+"</option>";
					}
				});
				
				if(selectHtml!=""){
					selectHtml = "<select id=\"goodstype_select_"+typeIndex+"\" class=\"goodstype_select\"><option value=\"\"> </option>"+selectHtml+"</select>";
					$("#goodstype_td").append(selectHtml+"");
					hwtt_do_change($("#goodstype_select_"+typeIndex));
					if(typeIndex<=typeCode_array.length){
						modifyselect(typeCode_array,typeIndex);
					}
				}
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

		
		

        function h2y_save() {
        	
        	if(!Validator.form()) return;
        	
        	if(!priceValidator()) return;
        	
        	gdsCode = $("#goodstype_td select:last").val();
        	if(""==gdsCode || undefined==gdsCode){
        		alert("请选择商品类型");
        		return;
        	}
        	$("#gdsCode").val(gdsCode);
        	
        	if($("#markTypeCode").val() != gdsCode){
        		
        		alert("类型下面的标签不匹配，请重新选择标签！");
        		return;
        	}
        	
        	//同步富文本编辑框数据
            introduceEditor.sync();
            specParamEditor.sync();
            
            
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

            $.post("business/goods/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_goods_init_htm") != null) {
							top.f_getframe("business_goods_init_htm").f_query();
						}
						top.f_delTab("goods_modify${goods.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_goods_init_htm") != null) {
							top.f_getframe("business_goods_init_htm").f_query();
						}
						top.f_delTab("goods_add");
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

		//价格验证
		function priceValidator(){
			
			var minPrice = parseFloat($("#minPrice").val());
			var maxPrice = parseFloat($("#maxPrice").val());
			var memberPrice = parseFloat($("#memberPrice").val());
			var marketPrice = parseFloat($("#marketPrice").val());
			var sellPrice = parseFloat($("#sellPrice").val());
			
			//alert(minPrice+":"+maxPrice);
			//alert(minPrice>maxPrice);
			
			if(minPrice > maxPrice){
				
				alert("指导最低价必须小于最高价！");
				return false;
			}
			
			if(memberPrice<minPrice || memberPrice>maxPrice){
				alert("会员价格必须在指导价格中！");
				return false;
			}
			
			if(marketPrice<minPrice || marketPrice>maxPrice){
				alert("市场价格必须在指导价格中！");
				return false;
			}
			
			if(sellPrice<minPrice || sellPrice>maxPrice){
				alert("销售价格必须在指导价格中！");
				return false;
			}
			return true;
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
		
		<input name="id" type="hidden" id="id" value="${goods.id}" />
		<input name="gdsCode" type="hidden" id="gdsCode" value="${goods.gdsCode}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="goodsName" type="text" id="goodsName" class="h2y_input_long"
							value="${goods.goodsName}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品编号：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<input type="hidden" name="goosNumberPrefix" id="goosNumberPrefix" value="${goosNumberPrefix }"/>
					<input type="hidden" name="goosTypePrefix" id="goosTypePrefix" value="${goosTypePrefix }"   />
					
					${goosNumberPrefix }-
					<input type="text" name="goosTypePrefixRed" id="goosTypePrefixRed" value="${goosTypePrefix }"  class="h2y_input_just" disabled/>
					-	
					<input name="shortGoodsNumber" type="text" id="shortGoodsNumber" class="h2y_input_just"
							value="${goods.shortGoodsNumber}" /><!-- 最大长度6位，验证配置 -->
									
				</td>
			
				
			</tr>
			
			
			
			<tr>
				<td class="h2y_table_label_td">
					商品包装：
				</td>
				<td class="h2y_table_edit_td"  colspan="3">
					<c:if test="${op == 'modify' }">
						<h2y:input name="goodsUnit" id="goodsUnit" type="radio" dictcode="goods_unit" value="${goods.goodsUnit}" disabled="disabled"/>
					</c:if>
					<c:if test="${op == 'add' }">
						<h2y:input name="goodsUnit" id="goodsUnit" type="radio" dictcode="goods_unit" value="${goods.goodsUnit}"/><font color="red">(保存成功后不可修改)</font>
					</c:if>
					
				</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">
					商品规格：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<c:if test="${op == 'add' }">
						<input name="spec" type="text" id="spec"  class="h2y_input_just" value="1"/>
					</c:if>
					<c:if test="${op == 'modify' && (goods.goodsUnit == 1 || goods.goodsUnit == 3)}">
						<input name="spec" type="text" id="spec"  class="h2y_input_just" value="${goods.spec}"/>
					</c:if>
					
					<c:if test="${op == 'modify' && goods.goodsUnit != 1 && goods.goodsUnit != 3}">
						<input name="spec" type="hidden" id="spec"  class="h2y_input_just" value="${goods.spec}"/>
						${goods.spec}
					</c:if>
					
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					指导价：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<table>
						<tr>
							<td><input name="minPrice" type="text" id="minPrice" value="${goods.minPrice}"/></td>
							<td>~</td>
							<td><input name="maxPrice" type="text" id="maxPrice" value="${goods.maxPrice}"/></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					会员价：
				</td>
				<td class="h2y_table_edit_td">
					<input name="memberPrice" type="text" id="memberPrice" value="${goods.memberPrice}"/>
				</td>
				<td class="h2y_table_label_td">
					市场价：
				</td>
				<td class="h2y_table_edit_td">
					<input name="marketPrice" type="text" id="marketPrice" value="${goods.marketPrice}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					销售价：
				</td>
				<td class="h2y_table_edit_td">
					<input name="sellPrice" type="text" id="sellPrice" value="${goods.sellPrice}"/>
				</td>
			
				<td class="h2y_table_label_td">排序：</td>
	            <td class="h2y_table_edit_td">
	                <input name="ord" type="text" id="ord" value="${goods.ord}"/>
	            </td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" type="text" id="memo" class="h2y_input_long"
							value="${goods.memo}" />	
				</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">
					库存：
				</td>
				<td class="h2y_table_edit_td">
					<input name="inventory" type="text" id="inventory" value="${goods.inventory}"/>					
				</td>
				<td class="h2y_table_label_td">
					商城是否可见：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isMallVisible" id="isMallVisible" type="radio" initoption="0,否:1,是" value="${goods.isMallVisible}"/>						
				</td>
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					允许使用虚拟货币：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isAllowGrade" id="isAllowGrade" type="radio" initoption="0,否:1,是" value="${goods.isAllowGrade}"/>		
					
				</td>
				
				<td class="h2y_table_label_td">
					允许使用虚拟货币数量：
				</td>
				<td class="h2y_table_edit_td">
					<input name="limitGradeNumber" type="text" id="limitGradeNumber"  class="h2y_input_just" value="${goods.limitGradeNumber}"/>
				</td>
			
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否赠送达人币：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isGiftGrade" id="isGiftGrade" type="radio" initoption="0,否:1,是" value="${goods.isGiftGrade}"/>								
				</td>
				
				<td class="h2y_table_label_td">
					赠送达人币数量：
				</td>
				<td class="h2y_table_edit_td">
					<input name="limitGiftNumber" type="text" id="limitGiftNumber"  class="h2y_input_just" value="${goods.limitGiftNumber}"/>
				</td>
				
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否限购：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isLimitSell" id="isLimitSell" type="radio" initoption="0,否:1,是" value="${goods.isLimitSell}"/>								
				</td>
			
				<td class="h2y_table_label_td">限购数量：</td>
	            <td class="h2y_table_edit_td">
	                <input name="limitSellNumber" type="text" id="limitSellNumber" class="h2y_input_just" value="${goods.limitSellNumber}"/>		
	            </td>
			</tr>
			
			
			
			
			
			
			<tr>
				<td class="h2y_table_label_td">
					商品一维码：
				</td>
				<td class="h2y_table_edit_td">
					<input name="gdsQr1" type="text" id="gdsQr1" class="h2y_input_just"
							value="${goods.gdsQr1}" />
				</td>
			
				<td class="h2y_table_label_td">商品内码：</td>
	            <td class="h2y_table_edit_td">
	                <input name="gdsQrInside" type="text" id="gdsQrInside" class="h2y_input_just"
							value="${goods.gdsQrInside}" />
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
					<input name="markTypeCode" type="hidden" id="markTypeCode" value="${goods.gdsCode}" />
					<div id="markHtml_show">
					</div>
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
			
			<tr>
				<td class="h2y_table_label_td">
					规格参数：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<textarea name="specParam" id="specParam" class="h2y_editor_textarea">${goodsInfo.specParam}</textarea>
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
			
		</table>
	</form>

</body>
</html>