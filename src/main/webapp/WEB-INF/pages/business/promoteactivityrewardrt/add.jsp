<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>${mname}</title>
		<%@ include file="../../include/top_list.jsp" %>
		<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
		<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
		<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
		
		
		<script type="text/javascript">
			var Validator = null;
	        var isSubmiting = false;
	        var op = "${op}";
	        var callBackFlag = 1;
	        
	        
			$(function() {
				
	        	
	        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});	        	
	            
	            $("#promoteActivityRewardRt_double1").ligerSpinner({type: 'float' ,height: 25,width:140,minValue:0});	            
				
				//验证属性设置
	            $.metadata.setType("attr", "validate");
	            //验证信息
	            ${validationRules}
	            //设置默认验证样式
	            Validator = deafultValidate($("#editform"));				
		        
		        
		      //选择商品
	            $("#selectGoodsBut").click(function(){
	            	openPriceSelectDialog({selectType:"radio"});
	            	
	            });
		      
		      //选择优惠券
	            $("#selectCouponsBut").click(function(){
	            	openCouponsSelectDialog({selectType:"radio"});	            	
	            });
		      
	            
		      
		      //数据类型选择
	            $("input[type=radio][name='dataType']").change(function(){
	            	$("#showDataCoupons").hide();
	            	$("#showDataGoods").hide();
	            	$("#showData2").hide();
	            	$("#dataGoodsTd").html("");
	            	$("#dataCouponsTd").html("");
	            	if(this.value == 0){
	            		$("#showDataCoupons").show();
	            	}else if(this.value == 1){
	            		$("#showDataGoods").show();
	            	}else if(this.value == 2){
	            		$("#showData2").show();
	            		$("#showDataTd").html("达人币:");
	            	}else if(this.value == 3){
	            		$("#showData2").show();
	            		$("#showDataTd").html("达人豆:");
	            	}else if(this.value == 4){
	            		$("#showData2").show();
	            		$("#showDataTd").html("储值:");
	            	}
	            });
		        
		      		      
		        if(op == 'modify'){
		        	
		        	//数据类型处理  0：优惠劵、1：商品、2：达人币、3：达人豆、4：储值
		        	if(${promoteActivityRewardRt.dataType==0}){
		        		$("#showDataCoupons").show();
		        		 //初始化优惠券
		                
		        		var couponsHtml = "";
			            couponsHtml+= '<div id="data_tr_${promoteActivityRewardRt.bigint1}">${promoteActivityRewardRt.str2}'+
			              					'<input name="str2" type="hidden" value="${promoteActivityRewardRt.str2}"/>'+
			              	  				'<input name="bigint1" type="hidden" value="${promoteActivityRewardRt.bigint1}"/>'+
			              	  				'<input name="str1" type="hidden" value="${promoteActivityRewardRt.str1}"/>'+
			              					'<img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowGoods(${promoteActivityRewardRt.bigint1})"/>'+
			              					'</div>';
			              				
		              	$("#dataCouponsTd").append(couponsHtml);
		        		 
		        	}else if(${promoteActivityRewardRt.dataType==1}){
		        		$("#showDataGoods").show();
		        		 //初始化商品
		                
		        		var checkedGoodsHtml = '<table class="h2y_table" width="50%" id="dataGoodsTable">' +
								  	 '<tr>'+
								     '<td class="h2y_table_label_td" style="text-align: center;">名称</td>'+
								     '<td class="h2y_table_label_td" style="text-align: center;">商品数量</td>'+
								   	 '<td class="h2y_table_label_td" style="text-align: center;">有效天数</td>'+
								     '<td class="h2y_table_label_td" style="text-align: center;">操作</td>'+
								     '</tr>';
 	
		  				checkedGoodsHtml +=   '<tr id="data_tr_${promoteActivityRewardRt.bigint1}">'+
								'<td class="h2y_table_edit_td">${promoteActivityRewardRt.str2}'+
								'<input name="str2" type="hidden" value="${promoteActivityRewardRt.str2}"/></td>'+
								'<input name="bigint1" type="hidden" value="${promoteActivityRewardRt.bigint1}"/></td>'+
								'<td class="h2y_table_edit_td"><input name="int1" type="text" class="h2y_input_just" value="${promoteActivityRewardRt.int1}"/></td>'+
							    '<td class="h2y_table_edit_td"><input name="int2" type="text" class="h2y_input_just" value="${promoteActivityRewardRt.int2}"/></td>'+
								'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowGoods(${promoteActivityRewardRt.bigint1})"/></td>'+
								'</tr>';				  			
   
	  					checkedGoodsHtml += "</table>";
	  					$("#dataGoodsTd").append(checkedGoodsHtml);
		        		 
		        	}else if(${promoteActivityRewardRt.dataType==2}){
		        		$("#showData2").show();
		        		$("#showDataTd").html("达人币:");
		        	}else if(${promoteActivityRewardRt.dataType==3}){
		        		$("#showData2").show();
		        		$("#showDataTd").html("达人豆:");
		        	}else if(${promoteActivityRewardRt.dataType==4}){
		        		$("#showData2").show();
		        		$("#showDataTd").html("储值:");
		        	}
		        	
		        	
		        	
		        	
		        }else{
		        	//默认优惠券选择
		        	$("#showDataCoupons").show();
		        }
		        
				
			  });
			
			
			
			//打开选择定价商品窗口
	        function openPriceSelectDialog(obj) {
	        	var selectType = "radio";
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
			
	      
	      
	      //打开优惠券选择窗口
	        function openCouponsSelectDialog(obj) {
	        	
	        	var selectType = "radio";
	        	if(obj!=null && obj.selectType!=null){
	        		selectType = obj.selectType;
	        	}
	        	
	            var src = "business/coupons/selectDialogInit.htm?selectType="+selectType;
	            $.ligerDialog.open({
	                name:"select_coupons_dialog",
	                title:  "选择优惠券",
	                height: 450,
	                url: src,
	                width: 700,
	                showMax: true,
	                showToggle: true,
	                showMin: true,
	                isResize: true,
	                modal: true,
	                buttons: [
	                    { text: '确定', onclick: function (item, dialog) {
	                    	var data=$("#select_coupons_dialog")[0].contentWindow.h2y_returnData();								  	
	         			  	if (data){
	         			  		h2y_CouponsSelectCallBack(data);
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
	      	  
	      		  goodsDataJson = data;
	      		  $("#dataGoodsTd").text("");
	          	  //alert(JSON.stringify(data));

	                var dataCount = "1";
	                var effectiveCount = "1";
	                
	                if(data.length != 0){
	              	  $("#dataGoodsTable").remove();
	              	 // $("#giftTd").innerHTML="";
	              	  
	              	  var checkedGoodsHtml = '<table class="h2y_table" width="50%" id="dataGoodsTable">'+
						              		 '<tr>'+
										     '<td class="h2y_table_label_td" style="text-align: center;">名称</td>'+
										     '<td class="h2y_table_label_td" style="text-align: center;">商品数量</td>'+
										   	 '<td class="h2y_table_label_td" style="text-align: center;">有效天数</td>'+
										     '<td class="h2y_table_label_td" style="text-align: center;">操作</td>'+
										     '</tr>';
	              	  for(var i=0;i<data.length;i++){
	              		  if(null != data[i].COUNT){
	              			  dataCount = data[i].COUNT;
	              		  }
	              		  if(null != data[i].EFFECTIVECOUNT){
	              			effectiveCount = data[i].EFFECTIVECOUNT;
	              		  }
	                  	
	              		
					  	 
	              		  
	      				  checkedGoodsHtml +=   '<tr id="data_tr_'+data[i].ID+'">'+
	  			  								'<td class="h2y_table_edit_td">'+data[i].NAME+
	  			  								'<input name="str2" type="hidden" value="'+data[i].NAME+'"/>'+
	  			  								'<input name="bigint1" type="hidden" value="'+data[i].ID+'"/></td>'+
	  			  								'<td class="h2y_table_edit_td"><input name="int1" type="text" class="h2y_input_just" value="'+dataCount+'"/></td>'+
	  			  							    '<td class="h2y_table_edit_td"><input name="int2" type="text" class="h2y_input_just" value="'+effectiveCount+'"/></td>'+
	  			  								'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowGoods('+data[i].ID+')"/></td>'+
	  			  								'</tr>';				  			
	                    }
	              	  checkedGoodsHtml += "</table>";
	              	  $("#dataGoodsTd").append(checkedGoodsHtml);
	              	  
	                }
	      	  
	        }
			
	      
	      
	      //选择优惠券回调
	        function h2y_CouponsSelectCallBack(data){
	      	  
	      		  goodsDataJson = data;
	      		  $("#dataCouponsTd").text("");
	          	  //alert(JSON.stringify(data));
	          	  
	              if(undefined != data){
	              	  $("#dataCouponsTable").remove();
	              	  var couponsHtml = "";
	              	  var checkedCouponsHtml = '<table class="h2y_table" width="50%" id="dataCouponsTable">';
	              	  $(data).each(function(){			    		
	              		  checkedCouponsHtml += '<tr id="data_tr_'+this.ID+'">'+
												'<td class="h2y_table_edit_td">'+this.COUPONS_NAME+'<input name="bigint1" type="hidden" value="'+this.ID+'"/></td>'+
												'<td class="h2y_table_edit_td" style="text-align: center;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowGoods('+this.ID+')"/></td>'+
												'</tr>';
		              	  couponsHtml+= '<div id="data_tr_'+this.ID+'">'+ this.COUPONS_NAME+
		              					'<input name="str2" type="hidden" value="'+this.COUPONS_NAME+'"/>'+
		              	  				'<input name="bigint1" type="hidden" value="'+this.ID+'"/>'+
		              	  				'<input name="str1" type="hidden" value="'+this.COUPONS_CODE+'"/>'+
		              					'<img src="<%=uiPath%>lib/ligerUI/skins/icons/delete.gif" alt="删除" title="删除" onclick="deleteRowGoods('+this.ID+')"/>'+
		              					'</div>';
			      	  });
	              	                	  
	              	  checkedCouponsHtml += "</table>";
	              	  //$("#dataCouponsTd").append(checkedCouponsHtml);
	              	  $("#dataCouponsTd").append(couponsHtml);
	              }             
	        }
	      
	      
	      
	        function deleteRowGoods(index){
				$("#data_tr_"+index).remove();
			}
	      
			function h2y_save(){

				if(!Validator.form()) return;   
				
				var dataType =  $("input[type=radio][name='dataType']:checked").val();
				if(dataType == 0 || dataType == '0'){
					if('undefined' == $("input[type=hidden][name='bigint1']").val() || undefined == $("input[type=hidden][name='bigint1']").val()){
		            	alert("请选择优惠券！");
		            	return;
		            }
				}else if(dataType == 1 || dataType == '1'){
					if('' == $("input[type=hidden][name='bigint1']").val() || undefined == $("input[type=hidden][name='bigint1']").val()){
		            	alert("请选择商品！");
		            	return;
		            }
					if('' == $("input[type=text][name='int1']").val() || undefined == $("input[type=text][name='int1']").val()){
		            	alert("商品数量不能为空！");
		            	return;
		            }
					if('' == $("input[type=text][name='int2']").val() || undefined == $("input[type=text][name='int2']").val()){
		            	alert("商品有效天数不能为空！");
		            	return;
		            }
				}else{
					var double1 = $("#promoteActivityRewardRt_double1").val();
					if(parseFloat(double1) <= 0){
						alert("数据类型对应的数值必须大于0");
						return;
					}
				}
				
				var queryString = $('#editform').serialize(); 

				if(isSubmiting){
					alert("表单正在提交，请稍后操作！");
					return;
				}		
				isSubmiting = true;
				
				<%--注意该处url可能不符合你的要求，请注意修改--%>
				$.post("business/promoteactivityrewardrt/save.htm", queryString,function(data){
					var op=$("#op").val();
					var jsonReturn=eval("("+data+")");
					var promoteActivityRewardRtId = "${promoteActivityRewardRt.id}";
					
					
					if (op == "add") {
	                    if (jsonReturn.code == "1") {
	                        alert(jsonReturn.msg);
	                        if (top.f_getframe("business_promoteactivityrewardrt_init") != null) {
								top.f_getframe("business_promoteactivityrewardrt_init").f_query();
							}
							top.f_delTab("business_promoteactivityrewardrt_add");
	                    } else {
	                        alert(jsonReturn.msg);
	                    }
	                } else {
	                    if (jsonReturn.code == "1") {
	                        alert(jsonReturn.msg);
	                        if (top.f_getframe("business_promoteactivityrewardrt_init") != null) {
								top.f_getframe("business_promoteactivityrewardrt_init").f_query();
							}             
	                        top.f_delTab("business_promoteactivityrewardrt_modify_"+promoteActivityRewardRtId);
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
			
			
			function addDelClass(){
				 $(".del").click(function() {  
					 if (!confirm("删除后不可恢复，确定删除图片吗？")) return;
			    	 $(this).parent().remove();    
				 });
			}
			 
			
			
		</script>
	</head>

<body>
  <div id="layout1" style="width: 100%">
	    <div position="top">
			<table width="100%"  class="my_toptoolbar_td">
				<tr>
				<td id="my_toptoolbar_td">&nbsp;${mname}</td>
				<td align="right" width="500"><div id="toptoolbar"></div></td>
				</tr>
				</table>
			</div>
		</div>

		<form name="editform" method="post" action="" id="editform">
			
			<input type="hidden" name="id" value="${promoteActivityRewardRt.id}" />
			<input type="hidden" name="promoteId" value="${promoteActivityRewardRt.promoteId}" />		
			<input type="hidden" name="op" id="op" value="${op}" />
			
			
			<table class="h2y_table" >
				
				<tr>
					<td class="h2y_table_label_td">
						奖励类别:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">			
						<h2y:input name="rewardTarget" id="promoteActivityRewardRt_rewardTarget" type="radio" initoption="0,认领人:1,分享人" value="${promoteActivityRewardRt.rewardTarget}"/>
					</td>
				</tr>
				
				<tr>
					<td class="h2y_table_label_td">
						数据类型:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">			
						<h2y:input name="dataType" id="promoteActivityRewardRt_dataType" type="radio" dictcode="promote_activity_type" value="${promoteActivityRewardRt.dataType}"/>
					</td>
				</tr>

				
				<tr id="showDataCoupons" style="display:none">
					<td class="h2y_table_label_td">
						<input type="button" value="选择优惠券" class="button" id="selectCouponsBut"/>:
					</td>
					<td class="h2y_table_edit_td2" colspan="3" id="dataCouponsTd">
					</td>
				</tr>
				
				<tr id="showDataGoods" style="display:none">
					<td class="h2y_table_label_td">
						<input type="button" value="选择商品" class="button" id="selectGoodsBut"/>:
					</td>
					<td class="h2y_table_edit_td2" colspan="3" id="dataGoodsTd">
					</td>
				</tr>
				
				<tr id="showData2" style="display:none">
					<td class="h2y_table_label_td" id="showDataTd">
						达人币:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
						<input id="promoteActivityRewardRt_double1" name="double1" type="text" value="${promoteActivityRewardRt.double1}" class="h2y_input_just" />						
					</td>
					
				</tr>
				
				
			</table>
			
			
		</form>
	
 </body>
</html>