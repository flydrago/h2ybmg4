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
        
        //限制数量
        var limitSellNumber = 0;
        var limitGradeNumber = 0;
        var limitGiftNumber = 0;
        var inventoryNumber = 0;
        
        var islimitSellNumber = 0;
        var islimitGradeNumber = 0;
        var islimitGiftNumber = 0;
  
        //判断使用哪个回调函数
        var callBackFlag = "1";
        var goodsPriceRtFlag = "0";

        var goodsDataJson2 = ${goodsDataJson2};
        var goodsDataJson3 = ${goodsDataJson3};
        var goodsDataJson4 = ${goodsDataJson4};

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

            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            
            $("#memberPrice").ligerSpinner({type: 'float' ,height: 25,width:140,isNegative:false});
            $("#marketPrice").ligerSpinner({type: 'float' ,height: 25,width:140,isNegative:false});
            $("#sellPrice").ligerSpinner({type: 'float' ,height: 25,width:140,isNegative:false});
			
            $("#inventory").ligerSpinner({type: 'int' ,minValue:1,height: 25,width:140,isNegative:false});
            $("#weight").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false});
            $("#ord").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false});
            
            $("#limitSellNumber").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false});
            $("#limitGradeNumber").ligerSpinner({type: 'float' ,height: 25,width:140,isNegative:false});
            $("#limitGiftNumber").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false});
            
            $("#spec").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false,disabled:true})
            
            
			if (op == "modify") {
            	
                $("#tr_next").hide();
                h2y_showPic(${fileDataListJson});
                getGoodsDetail($("#goodsId").val(),"modify");
            }
            

            $("#goodsPicUploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:true
            	});
            });
            
            $("#goodsLogoUploadBut").click(function(){
            	openImageUploadDialog();
            });
            
            
            //选择商品
            $("#goodsSelectBut").click(function(){
            	callBackFlag = "1";
            	openGoodsSelectDialog();
            });

            //赠品
            $("input[type=radio][name='isGift']").change(function(){    
            	if(this.value == 1){
            		 $("#showGift").show();
            	}else{
            		 $("#showGift").hide();
       	   			 $("#relationGoods").text("");
       	   			 $("#relationGoodsIds").val("");
            	}
            });
            
          //关联
            $("input[type=radio][name='isRelation']").change(function(){                 	
            	if(this.value == 1){
            		$("#showRelation").show();
            	}else{
            		$("#relationGoods").text("");
       			 	$("#relationGoodsIds").val("");
       				$("#showRelation").hide();
            	}
            });
            
          
          //套装、单品关联                        	
            if($("#goodsUnit").val() == 1){
            	$("#showPriceRt").show();
            }else{           	
       			$("#showPriceRt").hide();
            }
           
            
          //设置限购数量
            if(${goodsPrice.isLimitSell==0}){
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
            if(${goodsPrice.isAllowGrade==0}){
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
            if(${goodsPrice.isGiftGrade==0}){
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
            
            //选择赠品商品
            $("#selectGiftGoodsBut").click(function(){
            	callBackFlag = "2";
            	openPriceSelectDialog({selectType:"multi"});
            	
            });
            
            //选择关联商品
            $("#selectGoodsBut").click(function(){
            	callBackFlag = "3";
            	openPriceSelectDialog({selectType:"multi"});
            });
            
            
            //选择套装/单品关联
            $("#selectPriceBut").click(function(){
            	callBackFlag = "4";
            	goodsPriceRtFlag = "1";
            	openPriceSelectDialog({selectType:"radio"});
            	//openGoodsSelectDialog({selectType:"multi"});
            });
            
            //初始化赠品
            if('' != goodsDataJson2 && 'undefined' != goodsDataJson2){
            	callBackFlag = "2";
            	//alert("goodsDataJson2--"+goodsDataJson2);
            	h2y_priceSelectCallBack(goodsDataJson2);
            }
            
            //初始化关联商品
            if('' != goodsDataJson3 && 'undefined' != goodsDataJson3){
            	callBackFlag = "3";
            	//alert("goodsDataJson3--"+goodsDataJson3);
            	h2y_priceSelectCallBack(goodsDataJson3);
            }
            
          //初始化单品、整箱关联
            if('' != goodsDataJson4 && 'undefined' != goodsDataJson4){
            	callBackFlag = "4";
            	//alert("goodsDataJson3--"+goodsDataJson3);
            	h2y_priceSelectCallBack(goodsDataJson4);
            }
            
            
            
        });
        
        
        
      //打开选择定价商品窗口
        function openPriceSelectDialog(obj) {
        	var selectType = "multi";
        	if(obj!=null && obj.selectType!=null){
        		selectType = obj.selectType;
        	}
            var src = "sys/dialog/goodsInit.htm?selectType="+selectType+"&callBackFlag="+callBackFlag;
            $.ligerDialog.open({
                name:"select_goods_dialog",
                title:  "选择商品",
                height: 350,
                url: src,
                width: 700,
                showMax: true,
                showToggle: true,
                showMin: true,
                isResize: true,
                modal: true,
                buttons: [
                    { text: '确定', onclick: function (item, dialog) {
                    	var data=$("#select_goods_dialog")[0].contentWindow.h2y_returnData();								  	
         			  	if (data){
         			  		h2y_priceSelectCallBack(data);
         			  		dialog.close();
         			  	}
                    } },
                    { text: '取消', onclick: function (item, dialog) {
                        	dialog.close();
                    } }
                ]
            });
        }
        
        
      
        
      //选择商品回调
      function h2y_priceSelectCallBack(data){
    	  if("3" == callBackFlag){
    		  goodsDataJson3 = data;
       	      //alert(JSON.stringify(data));
          	  var checkedGoods="";
              var goodsIds="";
              var reg=/,$/gi;
              for(var i=0;i<data.length;i++){
                  checkedGoods+=data[i].NAME;
                  goodsIds+=data[i].ID+";";
                  checkedGoods+="\n";
              }
              document.getElementById("relationGoods").value=checkedGoods;
              goodsIds=goodsIds.replace(reg,"");
              document.getElementById("relationGoodsIds").value=goodsIds;
             // alert("relationGoods--"+relationGoods);
    	  }else if("2" == callBackFlag){//赠品
    		  goodsDataJson2 = data;
    		  $("#giftTd").text("");
        	  //alert(JSON.stringify(data));

              var dataCount = "1";
              if(data.length != 0){
            	  $("#giftTable").remove();
            	 // $("#giftTd").innerHTML="";
            	  var checkedGoodsHtml = '<table class="h2y_table" width="50%" id="giftTable">' +
									  	 '<tr>'+
									     '<td class="h2y_table_label_td" style="text-align: center;">名称</td>'+
									     '<td class="h2y_table_label_td" style="text-align: center;">数量</td>'+
									     '<td class="h2y_table_label_td" style="text-align: center;">操作</td>'+
									     '</tr>';
            	  
            	  
            	  for(var i=0;i<data.length;i++){
            		  if(null != data[i].COUNT){
            			  dataCount = data[i].COUNT;
            		  }
                	
    				  checkedGoodsHtml +=   '<tr id="gift_tr_'+data[i].ID+'">'+
			  								'<td class="h2y_table_edit_td">'+data[i].NAME+'<input name="giftGoodId" type="hidden" value="'+data[i].ID+'"/></td>'+
			  								'<td class="h2y_table_edit_td"><input name="giftGoodCount" type="text" class="h2y_input_just" value="'+dataCount+'"/></td>'+
			  								'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowGift('+data[i].ID+')"/></td>'+
			  								'</tr>';				  			
                  }
            	  checkedGoodsHtml += "</table>";
            	  $("#giftTd").append(checkedGoodsHtml);
            	  
              }             
              
    	  }else if("4" == callBackFlag){//整箱、单品关联
    		  goodsDataJson4 = data;
    		  if(data.length != 0){   			  
    			  //$("#priceTable").remove();
    			  var checkedRtHtml = '<table class="h2y_table" width="50%" id="priceTable">';
    			  var goodsPriceId = $("#id").val();
    			  if(goodsPriceRtFlag == "1"){ 					  
    	            		//判断该单品是否已被关联           		  
    	            		  $.post("business/goodsprice/getGoodsPriceRt.htm?goodsPriceId="+goodsPriceId+"&dataGoodsId="+data[0].ID, function (dataR) {
    	                            var jsonReturn = eval("(" + dataR + ")");
    	                            if (jsonReturn.code == "0") {
    	                                alert(jsonReturn.msg);
    	                                //$("#priceTable").remove();
    	                                goodsPriceRtFlag = "0";
    	                                return;                        		
    	                            }else{
    	                        	   checkedRtHtml +=   '<tr id="rt_tr_'+data[0].ID+'">'+
       										'<td class="h2y_table_edit_td">'+data[0].NAME+'<input name="priceGoodRtId" type="hidden" value="'+data[0].ID+'"/></td>'+									
       										'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowRt('+data[0].ID+')"/></td>'+
       										'</tr>';
    	                        	   checkedRtHtml += "</table>";
    	                        	   $("#priceTable").remove();
    	              	               $("#priceTd").append(checkedRtHtml);
    	              	               goodsPriceRtFlag = "0";
    	                           }
    	                	 });
    	             
    			  }else{
    				  for(var i=0;i<data.length;i++){   	            		
    	            		checkedRtHtml +=   '<tr id="rt_tr_'+data[i].ID+'">'+
    								'<td class="h2y_table_edit_td">'+data[i].NAME+'<input name="priceGoodRtId" type="hidden" value="'+data[i].ID+'"/></td>'+									
    								'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowRt('+data[i].ID+')"/></td>'+
    								'</tr>';
    	              }
    	              checkedRtHtml += "</table>";
    	              $("#priceTable").remove();
    	              $("#priceTd").append(checkedRtHtml);
    	              goodsPriceRtFlag = "0";
    	    		  
    			  }
    		  }	  
    	  }
    	  
      }
        
    //删除行
		function deleteRowGift(index){
			$("#gift_tr_"+index).remove();
		}
    
		 //删除行
		function deleteRowRt(index){
			$("#rt_tr_"+index).remove();
		}
    
    
    //关联套装/单品 回调
      function h2y_priceCallBack(data){
      	if(data==null || data.length==0){
      		return;
      	}

      	$("#priceTd").innerHTML("");
      	var appHtml = '<table class="h2y_table" width="50%" id="priceTable">' +
      				  '<tr>'+
      				  '<td class="h2y_table_label_td" style="text-align: center;">名称</td>'+
      				  '<td class="h2y_table_label_td" style="text-align: center;">数量</td>'+
      				  '<td class="h2y_table_label_td" style="text-align: center;">操作</td>'+
      				  '</tr>';
      				  
      	$(data).each(function(){
      		appHtml = appHtml + '<tr id="">'+
      				  			'<td class="h2y_table_edit_td">名称</td>'+
      				  			'<td class="h2y_table_edit_td">数量</td>'+
      				  			'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteOne()"/></td>'+
						  			'</tr>';
      	})
			appHtml = appHtml + '</table>';
      }
      
        
        //商品选择回调函数
		function h2y_goodsSelectCallBack(data){
        	//关联套装/单品
        	if(callBackFlag == "4"){
        		h2y_priceCallBack(data);
        	}else{
        		//alert(JSON.stringify(data));
    			$("#goodsId").val(data.ID);
    			$("#goodsVersion").val(data.VERSION);	
    			getGoodsDetail(data.ID,"callback");
        	}
			
		}
        
		
        function h2y_showPic(fileData){
        	
        	$("#goodspic_div").html("");
        	var imageHtml = "";
        	$(fileData).each(function(){
            	
           		var json_str = "{\"id\":\""+this.id+"\"}";
           		if(this.fileType==1){
           			$("#goodspic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"goodsPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"goodsPicImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a><a id=\"file_delete_href_"+fileId+"\" href=\"javascript:deletePic('"+fileId+"');\">删除</a>");
           		}else{
           			if(this.ord==2){
           				imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/><a class=\"goodsLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\" title=\""+this.fileName+"\"><img class=\"goodsLogoImg\" src=\"common/image/view.htm?fileBean=goodsService&id="+this.id+"\"></a>";
           			}else{
           				imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/>";
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
        
        function h2y_save() {
        	
        	if(!Validator.form()) return;
        	
        	var goodsId = $("#goodsId").val();
        	if(null==goodsId || undefined==goodsId || ""==goodsId || "0"==goodsId){
        		alert("请选择商品！");
        		return;
        	}
        	//价格验证
        	if(!priceValidator()) return;
        	//数量验证
        	if(!limitNumberValidator()) return;
        	
        	//同步富文本编辑框数据
            introduceEditor.sync();
            specParamEditor.sync();
            
            //箱必须关联单品
            var goodsUnitVal = $("#goodsUnit").val();
            if(goodsUnitVal == 1){
            	if('undefined' == $("input[type=hidden][name='priceGoodRtId']").val() || undefined == $("input[type=hidden][name='priceGoodRtId']").val()){
                	alert("请关联单品！");
                	return;
                }
            }
            
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

            $.post("business/goodsprice/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_goodsprice_init_htm") != null) {
							top.f_getframe("business_goodsprice_init_htm").f_query();
						}
						top.f_delTab("goodsprice_modify${goodsPrice.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_goodsprice_init_htm") != null) {
							top.f_getframe("business_goodsprice_init_htm").f_query();
						}
						top.f_delTab("goodsprice_add");
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
            
            limitSellNumber = data.goods.limitSellNumber;
            limitGradeNumber = data.goods.limitGradeNumber;
            limitGiftNumber = data.goods.limitGiftNumber;
            inventoryNumber = data.goods.inventory;
            
            islimitSellNumber = data.goods.isLimitSell;
            islimitGradeNumber = data.goods.isAllowGrade;
            islimitGiftNumber = data.goods.isGiftGrade;
            
            
            
          //设置虚拟货币数量
            if(${data.goods.isAllowGrade==0}){
            	$("#limitGradeNumber").attr("disabled",true);
            	$("#isAllowGrade").attr("disabled",true);
            }else{
            	$("input[type=radio][name='isAllowGrade']").change(function(){          	
                	if(this.value == 0){
                		$("#limitGradeNumber").val('0');
                		$("#limitGradeNumber").attr("disabled",true);
                	}else{
                		$("#limitGradeNumber").attr("disabled",false);
                	}
                });
            }
            
          
            
          //设置限购数量
            if(${data.goods.isLimitSell==0}){
            	$("#limitSellNumber").attr("disabled",true);
            	$("#isLimitSell").attr("disabled",true);
            	
            }else{
            	$("input[type=radio][name='isLimitSell']").change(function(){          	
                	if(this.value == 0){
                		$("#limitSellNumber").val('0');
                		$("#limitSellNumber").attr("disabled",true);
                	}else{
                		$("#limitSellNumber").attr("disabled",false);
                	}
                });
            }           
			
			
			 //设置赠送达人币数量
            if(${data.goods.isGiftGrade==0}){
            	$("#limitGiftNumber").attr("disabled",true);
            	$("#isGiftGrade").attr("disabled",true);
            }else{
            	$("input[type=radio][name='isGiftGrade']").change(function(){          	
                	if(this.value == 0){
                		$("#limitGiftNumber").val('0');
                		$("#limitGiftNumber").attr("disabled",true);
                	}else{
                		$("#limitGiftNumber").attr("disabled",false);
                	}
                });
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
            //goodsDescHtml += "<br/>规格：1x"+goods.spec;
            $("#goods_desc").html(goodsDescHtml);
            minPrice = parseFloat(goods.minPrice);
            maxPrice = parseFloat(goods.maxPrice);
            
            //标签
            var markHtml = "";
            $(data.markList).each(function(){
            	markHtml += "<br/>"+this.MARK_NAME+"："+this.NAME;
            });
            $("#goods_desc").append(markHtml);
            
            limitSellNumber = data.goods.limitSellNumber;
            limitGradeNumber = data.goods.limitGradeNumber;
            limitGiftNumber = data.goods.limitGiftNumber;
            inventoryNumber = data.goods.inventory;
            
            
            islimitSellNumber = data.goods.isLimitSell;
            islimitGradeNumber = data.goods.isAllowGrade;
            islimitGiftNumber = data.goods.isGiftGrade;
            //设置商品规格
            $("#goodsUnit").val(data.goods.goodsUnit);
            if($("#goodsUnit").val() == 1){
            	$("#showPriceRt").show();
            }else{           	
       			$("#showPriceRt").hide();
            }
            
            
            //箱和提可以修改规格，否则不可以
            if(data.goods.goodsUnit == 1 || data.goods.goodsUnit == 3){
                	$("#spec").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false,disabled:false});
            }else{
                	$("#spec").ligerSpinner({type: 'int' ,height: 25,width:140,isNegative:false,disabled:true});
            }
            if (op != "modify"){
                $("#spec").val(data.goods.spec);
            }
          
           
		}
		
		//数量验证
		function limitNumberValidator(){
			var giftNumber = parseInt($("#limitGiftNumber").val());
			var gradeNumber = parseInt($("#limitGradeNumber").val());
			var sellNumber = parseInt($("#limitSellNumber").val());
			var inventory = parseInt($("#inventory").val());
			
			var isAllowGrade = $("input[type=radio][name='isAllowGrade']:checked").val();
			var isGiftGrade = $("input[type=radio][name='isGiftGrade']:checked").val();
			var isLimitSell = $("input[type=radio][name='isLimitSell']:checked").val();
			
			
			
			if(1 == islimitGradeNumber){
				if(1==isAllowGrade){
					if(gradeNumber > limitGradeNumber){
						alert("使用虚拟货币数量不能大于原商品数量!"+gradeNumber+">"+limitGradeNumber);
						return false;
					}
				}
			}else{
				if(1==isAllowGrade && gradeNumber>=0){
					alert("该商品不能使用虚拟货币!");
					return false;
				}
			}
			

			
			if(1 == islimitSellNumber){
				if(1==isLimitSell){
					if(sellNumber > limitSellNumber){
						alert("限购数量不能大于原商品限购数量!"+sellNumber+">"+  limitSellNumber);
						return false;
					}
				}else{
					alert("请设置该商品限购!");
					return false;
				}
			}
			
			
			if(1 == islimitGradeNumber){
				if(1==isGiftGrade){
					if(giftNumber > limitGiftNumber){
						alert("赠送达人币数量不能大于原商品赠送数量！"+giftNumber+">"+limitGiftNumber);
						return false;
					}
				}
			}else{
				if(1==isGiftGrade && giftNumber>=0){
					alert("该商品不能赠送达人币！");
					return false;
				}
			}
			
			
			if(inventory > inventoryNumber){
				alert("库存数量大于原商品库存数量!"+inventory+">"+inventoryNumber);
				$("#inventory").val(inventoryNumber);
				return false;
			}
			
			
			return true;
		}
		
		//价格验证
		function priceValidator(){
			
			var memberPrice = parseFloat($("#memberPrice").val());
			var marketPrice = parseFloat($("#marketPrice").val());
			var sellPrice = parseFloat($("#sellPrice").val());
			
			
			
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
		
		<input name="id" type="hidden" id="id" value="${goodsPrice.id}" />
		<input name="goodsId" type="hidden" id="goodsId" value="${goodsPrice.goodsId}" />
		<input name="goodsVersion" type="hidden" id="goodsVersion" value="${goodsPrice.goodsVersion}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<input name="goodsUnit" type="hidden" id="goodsUnit" value="${goodsPrice.goodsUnit}" />
		
		<table class="h2y_table">
		
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="选择商品" class="button" id="goodsSelectBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goods_desc">
						
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品别名：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="goodsNickName" type="text" id="goodsNickName" class="h2y_input_long"
							value="${goodsPrice.goodsNickName}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					会员价:
				</td>
				<td class="h2y_table_edit_td">
					<input name="memberPrice" type="text" id="memberPrice" value="${goodsPrice.memberPrice}"/>
				</td>
				<td class="h2y_table_label_td">
					库存:
				</td>
				<td class="h2y_table_edit_td">
					<input name="inventory" type="text" id="inventory" value="${goodsPrice.inventory}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					市场价:
				</td>
				<td class="h2y_table_edit_td">
					<input name="marketPrice" type="text" id="marketPrice" value="${goodsPrice.marketPrice}"/>
				</td>
				<td class="h2y_table_label_td">
					销售价:
				</td>
				<td class="h2y_table_edit_td">
					<input name="sellPrice" type="text" id="sellPrice" value="${goodsPrice.sellPrice}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					权重:
				</td>
				<td class="h2y_table_edit_td">
					<input name="weight" type="text" id="weight" value="${goodsPrice.weight}"/>
				</td>
				<td class="h2y_table_label_td">
					排序:
				</td>
				<td class="h2y_table_edit_td">
					<input name="ord" type="text" id="ord" value="${goodsPrice.ord}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					规格：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="spec" type="text" id="spec"  value="${goodsPrice.spec}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" type="text" id="memo" class="h2y_input_long"
							value="${goodsPrice.memo}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					允许使用虚拟货币：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isAllowGrade" id="isAllowGrade" type="radio" initoption="0,否:1,是" value="${goodsPrice.isAllowGrade}" />		
					
				</td>
				
				<td class="h2y_table_label_td">
					允许使用虚拟货币数量：
				</td>
				<td class="h2y_table_edit_td">
					<input name="limitGradeNumber" type="text" id="limitGradeNumber"  class="h2y_input_just" value="${goodsPrice.limitGradeNumber}" />
				</td>
			
				
			</tr>
			
			
						
			<tr>
				<td class="h2y_table_label_td">
					是否赠送达人币：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isGiftGrade" id="isGiftGrade" type="radio" initoption="0,否:1,是" value="${goodsPrice.isGiftGrade}"/>								
				</td>
				
				<td class="h2y_table_label_td">
					赠送达人币数量：
				</td>
				<td class="h2y_table_edit_td">
					<input name="limitGiftNumber" type="text" id="limitGiftNumber"  class="h2y_input_just" value="${goodsPrice.limitGiftNumber}" />
				</td>
				
				
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否限购：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isLimitSell" id="isLimitSell" type="radio" initoption="0,否:1,是" value="${goodsPrice.isLimitSell}"/>								
				</td>
			
				<td class="h2y_table_label_td">限购数量：</td>
	            <td class="h2y_table_edit_td">
	                <input name="limitSellNumber" type="text" id="limitSellNumber" class="h2y_input_just" value="${goodsPrice.limitSellNumber}" />		
	            </td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					商品一维码：
				</td>
				<td class="h2y_table_edit_td">
					<input name="gdsQr1" type="text" id="gdsQr1" class="h2y_input_just"
							value="${goodsPrice.gdsQr1}" />
				</td>
			
				<td class="h2y_table_label_td">商品内码：</td>
	            <td class="h2y_table_edit_td">
	                <input name="gdsQrInside" type="text" id="gdsQrInside" class="h2y_input_just"
							value="${goodsPrice.gdsQrInside}" />
	            </td>
			</tr>
			
			<tr id="showPriceRt" <c:if test="${ goodsPrice.goodsUnit != 1 && op =='modify'}">style="display:none;"</c:if>>
				<td class="h2y_table_label_td">
					<input type="button" value="关联单品" class="button" id="selectPriceBut" />:
				</td>
				<td class="h2y_table_edit_td2" colspan="3" id="priceTd">
					
				</td>
			</tr>
	
			<tr>
				 <td class="h2y_table_label_td">是否包含赠品:</td>
				 <td class="h2y_table_edit_td">
				 	<h2y:input name="isGift" id="isGift" type="radio" initoption="0,否:1,是" value="${goodsPrice.isGift}" />	
				 </td>
				 <td class="h2y_table_label_td">是否关联商品:</td>
			     <td class="h2y_table_edit_td">
					 	<h2y:input name="isRelation" id="isRelation" type="radio" initoption="0,否:1,是" value="${goodsPrice.isRelation}" />	
				 </td>	
			</tr>
			
			<tr  id="showGift" <c:if test="${ goodsPrice.isGift == 0 || op =='add'}">style="display:none;"</c:if>>
				<td class="h2y_table_label_td">
					<input type="button" value="选择赠品" class="button" id="selectGiftGoodsBut" />:
				</td>
				<td class="h2y_table_edit_td2" colspan="3" id="giftTd">
				</td>
			</tr>
			
			

			 <tr id="showRelation" <c:if test="${ goodsPrice.isRelation == 0 || op =='add'}">style="display:none;"</c:if>> 
			 	<td class="h2y_table_label_td">
			 		<input type="button" value="选择关联商品" class="button" id="selectGoodsBut"/>:
			 	</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
				    <textarea cols="100" rows="8" id="relationGoods" name="relationGoods"  readonly="readonly"  style="width:450px" >${relationGoodsInfos}</textarea>
                    <input type="hidden" name="relationGoodsIds" id="relationGoodsIds" value="${relationGoodsIds }"/>
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
					<input type="button" value="上传Logo" class="button" id="goodsLogoUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodslogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="上传图片" class="button" id="goodsPicUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="goodspic_div"></div>
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>