<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<style type="text/css">
	      
	.activity_img {width:360px;height:200px;}
	
	.td_prize_label{ 
		width:100px; 
		text-align:right;
		background: none repeat scroll 0 0 #fff;
	    padding: 4px 6px;	
		border: 1px solid #a3c0e8;  
	}
	
	.td_prize_edit{ 
		width:500px; 
		text-align:left;
		background: none repeat scroll 0 0 #fff;
	    padding: 4px 6px;	
		border: 1px solid #a3c0e8;   
	}
	
	.td_prize_img{ 
		text-align:center;
		background: none repeat scroll 0 0 #fff;
	    padding: 4px 6px;	
		border: 1px solid #a3c0e8;  
	}
	
	.prize_table{
		border:#D6DFF0 1px solid; 
		width:600px; 
		border-collapse:collapse; 
		font:12px/24px verdana; 
		margin: 0  auto;
	}
</style>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}"
        var form = null;
        var prize_index = 0;
        
        //选择商品Div的Id
        var goodsDivId = "";
        var goodsDataJson = null;
        
        var curren_index = 0;
        $(function () {
        	
        	
        	//var tel = /^.*[0-9a-zA-Z]+.*$/;
       /* 	var tel = /^[^0-9a-zA-Z]*$/;
        	var value = "a";
        	alert(value+"   "+tel.test(value));
        	value = "0";
        	alert(value+"   "+tel.test(value));
        	value = "A";
        	alert(value+"   "+tel.test(value));
        	value = "0A";
        	alert(value+"   "+tel.test(value));
        	value = "09aAaz";
        	alert(value+"   "+tel.test(value));
        	value = "09aAaz_";
        	alert(value+"   "+tel.test(value));
        	value = "##";
        	alert(value+"   "+tel.test(value));
        	value = "--";
        	alert(value+"   "+tel.test(value));
        	value = "你好";
        	alert(value+"   "+tel.test(value));
        	value = "@##";
        	alert(value+"   "+tel.test(value));
        	value = " ";
        	alert(value+"   "+tel.test(value));
        	value = "-abc***%%$$-";
        	alert(value+"   "+tel.test(value));
        	value = "-012***%%$$-";
        	alert(value+"   "+tel.test(value));
        	value = "-AZ***%%$$-";
        	alert(value+"   "+tel.test(value));
        	value = "-AZ0a***%%$$-";
        	alert(value+"   "+tel.test(value));
*/
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            if (op == "modify") {
            	
                $("#tr_next").hide();
                if(${wechatActivity.diskFileName!=null}){
                	
                	var json_str = "{\"id\":\"${wechatActivity.id}\"}";
             		$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"h2y_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=wechatActivityService&id=${wechatActivity.id}\" title=\"${wechatActivity.fileName}\"><img class=\"activity_img\" src=\"common/image/view.htm?fileBean=wechatActivityService&id=${wechatActivity.id}\"></a><a id=\"activity_img_del\" href=\"javascript:void(0);\">删除</a></div>");
             		$("#activity_img_del").click(function() {  
	       		    	 $(this).parent().remove();    
	       			});
             		//addDelClass();
             		$("#light_box_a").lightBox();
                }
                
                hwtt_edit_tr();
            }else{
            	
            	$("input[type=radio][name='prizeInfo']:first").attr("checked",true);
            	//添加默认奖项
            	hwtt_add_tr();
            	hwtt_add_tr();
            	hwtt_add_tr();
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#startDate").datetimepicker({});
	        $("#endDate").datetimepicker({});
            
	        if("${wechatActivity.ifSetDate}" == "1"){
	        	$("#if_set_date_check").attr("checked",true);
	        }
	        
	        setDateChange($("#if_set_date_check").attr("checked"));
            $("#if_set_date_check").change(function(){
            	setDateChange(this.checked);
            });
            
            if("${wechatActivity.ifShareReward}" == "1"){ 
	        	$("#ifShareReward_check").attr("checked",true);
	        }
            setShareRewardChange($("#ifShareReward_check").attr("checked"));
            $("#ifShareReward_check").change(function(){
            	setShareRewardChange(this.checked);
            });
            
            
            $("#uploadImgBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		uploadLimit:1,
            		multi:false
            	});
            });
            
            $("#useVal").ligerSpinner({type: 'int' ,height: 25,width:194,isNegative:false});
        });
        
        
        function addPrizeListener(index){
        	
        	 //商品选择
        	 $("#selectGoodsBut"+index).click(function(){
        		 openPriceGoodsSelectDialog({selectType:"radio"});
             	curren_index = $(this).attr("index_");
             })
             
             //商品类型
        	 prizeTypeChange($("#prize_table"+index),$("#prizeType"+index).val());
        	 $("#prizeType"+index).change(function(){
        		 
        		 var tableObj = $(this).parent().parent().parent();
        		 prizeTypeChange(tableObj,this.value);
             })
             
             if(index>2){
            	 
            	 $("#prize_"+index).click(function() {  
      		    	$(this).parentsUntil("div").remove();
      		    	updatePrizeTitle();
      			 });
             }
        }
        
        //奖品类型变化
        function prizeTypeChange(tableObj,typeVal){
        	
        	 if(typeVal=="goods"){
    			 tableObj.find(".goodsBut").attr("disabled",false);
    		 }else{
    			 tableObj.find(".goodsBut").attr("disabled",true);
    		 }
        }
        
        
        //商品选择回调函数
        function h2y_priceGoodsSelectCallBack(data){
        	
        	goodsDataJson = data;
        	$("#goodsId_div"+curren_index).html(data.GOODS_NICK_NAME);
        	$("#prize_table"+curren_index).find("input[name='prizeName']").val(data.GOODS_NICK_NAME);
        	$("#goodsId"+curren_index).val(data.ID);
        }
        
        function setDateChange(isSetDate){
        	
        	if(isSetDate){
        		
        		$("#ifSetDate").val(1);
        		$("#startDate").attr("disabled",false);
        		//$("#endDate").attr("disabled",false);
        	}else{
        		$("#ifSetDate").val(0);
        		$("#startDate").attr("disabled",true);
        		//$("#endDate").attr("disabled",true);
        	}
        }
        
        function setShareRewardChange(isShareReward){
        	
			if(isShareReward){
        		
        		$("#ifShareReward").val(1);
        		$("#rewardNumber").attr("disabled",false);
        	}else{
        		$("#ifShareReward").val(0);
        		$("#rewardNumber").attr("disabled",true);
        	}
        }
        
        //文件上传回调函数
        function h2y_fileUploadCallBack(data){
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"h2y_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"activity_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a class=\"activity_img_del\" href=\"javascript:void(0);\">删除</a></div>");
    		 $("#activity_img_del").click(function() {  
		    	 $(this).parent().remove();    
			 });
        	$("#light_box_a").lightBox();
        }
        

        function h2y_save() {
        	
        	
			if(!Validator.form()) return;
			
			if(!prizeValidate()) return;
			
			if($("#ifSetDate").val()==1 && !compareTime("startDate", "endDate")) return;
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/wechatactivity/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_wechatactivity_init_htm_activityType_${wechatActivity.activityType}") != null) {
							top.f_getframe("business_wechatactivity_init_htm_activityType_${wechatActivity.activityType}").f_query();
						}
						top.f_delTab("wechat_activity_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_wechatactivity_init_htm_activityType_${wechatActivity.activityType}") != null) {
							top.f_getframe("business_wechatactivity_init_htm_activityType_${wechatActivity.activityType}").f_query();
						}             
                        top.f_delTab("wechat_activity_modify${wechatActivity.id}");
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
        
		function compareTime(startId, endId) { 
			
			var startDate = $("#"+startId).val();
			var endDate = $("#"+endId).val();
			if(startDate==null || endDate==null || startDate=="" || endDate==""){
				alert("时间不能为空！");
				return false;
			} 
			startDate = startDate.replace(/[:-\s]/g,"");
			endDate = endDate.replace(/[:-\s]/g,"");
			if (parseInt(startDate) >= parseInt(endDate)) {   
		        alert("开始时间不能大于截止时间！");   
		        return false;   
			} 
		    return true;   
		}
		
		
		function hwtt_edit_tr(){
			
			$(${prizeListJson}).each(function(i){
				
				hwtt_add_tr();
				$("#prize_table"+i).find("input[name='prizeId']").val(this.id);
				$("#prize_table"+i).find("input[name='prizeName']").val(this.prizeName);
				$("#prize_table"+i).find("input[name='prizeNumber']").val(this.prizeNumber);
				$("#prize_table"+i).find("input[name='prizeRate']").val(this.prizeRate*100);
				$("#prize_table"+i).find("input[name='prizeLevel']").val(this.prizeLevel);
				$("#prize_table"+i).find("input[name='prizeCount']").val(this.prizeCount);
				$("#prize_table"+i).find("input[name='ord']").val(this.ord);
				$("#prizeType"+i).val(this.prizeType);
				if(this.prizeType == "goods"){
					$("#prize_table"+i).find(".goodsBut").attr("disabled",false);
				}else{
					$("#prize_table"+i).find(".goodsBut").attr("disabled",true);
				}
				//$("#prize_table"+i).find("input[name='prizeType']").val(this.prizeType);
				$("#prize_table"+i).find("input[name='goodsId']").val(this.goodsPriceId);
				$("#prize_table"+i).find("#goodsId_div"+i).html(this.goodsName);
			});
		}
		
		
		function hwtt_add_tr(){
			
			if($("input[name='prizeName']").length>9){
				alert("奖项已达上限！");
				return;
			}
			
			var newRow = $("#prize_table_model").clone();
			
			newRow.attr("id","prize_table"+prize_index);
			newRow.find("#prizeType").attr("id","prizeType"+prize_index);
			newRow.find("#selectGoodsBut").attr("id","selectGoodsBut"+prize_index).attr("index_",prize_index);
			newRow.find("#goodsId_div").attr("id","goodsId_div"+prize_index);
			newRow.find("#goodsId").attr("id","goodsId"+prize_index);
			newRow.find("#ord").attr("id","ord"+prize_index);
			newRow.find("#ord"+prize_index).ligerSpinner({type: 'int' ,height: 25,width:194});
			newRow.find("#ord"+prize_index).val($("input[name='prizeName']").length);
			newRow.css("display","");
			
			if(prize_index>2){
				newRow.append("<tr><td class=\"td_prize_img\" colspan=\"2\">"+
						"<a id=\"prize_"+prize_index+"\"><img src=\"<%=uiPath%>lib/ligerUI/skins/icons/delete.gif\" alt=\"删除奖项\" title=\"删除奖项\" />删除奖项</a>"+
						"</td></tr>");
			}
			$("#prize_div").append(newRow);	 
			
			//添加响应监听
			addPrizeListener(prize_index);
			updatePrizeTitle();
			
			//添加奖项维护验证
			//addPrizeValidata(prize_index);
			prize_index++;
		}
		
		function updatePrizeTitle(){
			
			$("#prize_div caption").each(function(i){
				$(this).html("奖项"+i);
			});
		}
		
		
		function prizeValidate(){
			
			var flag = true;
			
			$(".goodsBut").each(function(i){
				if(i!=0){
					var index = $(this).attr("index_");
					//alert(index+"prizeType:"+$("#prizeType"+index).val()+" goodsId:"+$("#goodsId"+index).val());
					if($("#prizeType"+index).val()=="goods" && ($("#goodsId"+index).val()=="" || $("#goodsId"+index).val()=="0")){
						
						alert("请选择商品！");
						flag = false;
						return false;
					}
				}
			});
			
			if(!flag) return flag;
			
			$("#prize_div input[name='prizeName']").each(function(i){
				if(i!=0){
					if(this.value==""){
						alert("奖品名称不能为空！");
						flag = false;
						return false;
					}
				}
			});
			
			if(!flag) return flag;
			
			$("#prize_div input[name='prizeNumber']").each(function(i){
				if(i!=0){
					if(this.value==""){
						alert("数量不能为空！");
						flag = false;
						return false;
					}
					
					if(!isNumber(this.value)){
						alert("数量只能为整数！");
						flag = false;
						return false;
					}
					
					if(this.value<1){
						alert("数量值不能小于1！");
						flag = false;
						return false;
					}
				}
			});
			
			if(!flag) return flag;
			
			
			$("#prize_div input[name='prizeCount']").each(function(i){
				if(i!=0){
					if(this.value==""){
						alert("奖品总量不能为空！");
						flag = false;
						return false;
					}
					
					if(!isNumber(this.value)){
						alert("奖品总量只能为整数！");
						flag = false;
						return false;
					}
				}
			});
			if(!flag) return flag;
			
			$("#prize_div input[name='prizeRate']").each(function(i){
				if(i!=0){
					if(this.value == ""){
						alert("抽奖概率不能为空！");
						flag = false;
						return false;
					}
					
					if(!isfloat(this.value)){
						alert("概率只能为整数或浮点数！");
						flag = false;
						return false;
					}
					
					if(this.value > 100){
						alert("概率不能超过100！");
						flag = false;
						return false;
					}
				}
			});
			
			if(!flag) return flag;
			
			$("#prize_div input[name='prizeLevel']").each(function(i){
				if(i!=0){
					if(this.value == ""){
						alert("奖项名称不能为空！");
						flag = false;
						return false;
					}
				}
			});
			
			if(!flag) return flag;
			
			return true;
		}
		
		
		function isfloat(oNum) {
			if (!oNum) return false;
			var strP = /^\d+(\.\d+)?$/;
			if (!strP.test(oNum)) return false;
			try {
				if (parseFloat(oNum) != oNum) return false;
			} catch(ex) {
				return false;
			}
			return true;
		}
		
		function isNumber(oNum) {
			if (!oNum) return false;
			var strP = /^\d+$/; //正整数
			if (!strP.test(oNum)) return false;
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
		
		<input name="id" type="hidden" id="id" value="${wechatActivity.id}" />
		<input name="activityType" type="hidden" id="activityType" value="${wechatActivity.activityType}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					标题：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="name" type="text" id="name" class="h2y_input_long"
							value="${wechatActivity.name}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					关键字：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="keyword" type="text" id="keyword" class="h2y_input_just"
							value="${wechatActivity.keyword}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="ifSetDate" type="hidden" id="ifSetDate" value="${wechatActivity.ifSetDate}" />
					<input id="if_set_date_check" type="checkbox" title="是否定时时间" class="hwtt_checkbox"/>
					<label for="if_set_date_check">设置开始时间</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="startDate" id="startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${wechatActivity.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
					~
					<input name="endDate" id="endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${wechatActivity.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
					<span style="color:red;">（不设置开始时间，活动即立即开始）</span>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					描述：
				</td>
				<td class="h2y_table_edit_td2">
					<textarea id="description" name="description" class="h2y_textarea">${wechatActivity.description}</textarea>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					显示奖品信息：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="prizeInfo" id="prizeInfo" type="radio" initoption="0,名称:1,名称+数量:2,名称+数量+剩余数" value="${wechatActivity.prizeInfo}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					次数限制：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="limitFlag" css="h2y_select_short" id="limitFlag" type="select" initoption="0,每天:1,总共" value="${wechatActivity.limitFlag}"/>
					&nbsp;
					<input name="limitNumber" type="text" id="limitNumber" class="h2y_input_short"
							value="${wechatActivity.limitNumber}"/>&nbsp;次
				</td>
			</tr>
			
			<%--
			<tr>
				<td class="h2y_table_label_td">
					分享：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="ifShareReward" type="hidden" id="ifShareReward" value="${wechatActivity.ifShareReward}" />
					<input id="ifShareReward_check" type="checkbox" title="是否设置时间" class="hwtt_checkbox"/>
					<label for="ifShareReward_check">分享奖励抽奖次数</label>
					&nbsp;
					<input name="rewardNumber" type="text" id="rewardNumber" class="h2y_input_short"
							value="${wechatActivity.rewardNumber}" />&nbsp;次数
				</td>
			</tr>
			 --%>
			
			<tr>
				<td class="h2y_table_label_td">
					消耗达人豆：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="useVal" type="text" id="useVal" class="h2y_input_just"
							value="${wechatActivity.useVal}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="上传图片" id="uploadImgBut"/>:
				</td>
				<td class="h2y_table_edit_td2">
					<div id="h2y_file_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					奖项设置：
				</td>
				<td class="h2y_table_edit_td2" >
					<div id="prize_div">
					
					<table id="prize_table_model" style="display: none;">
						<caption style="text-align: center;">奖项</caption>
						<tr>
							<td class="td_prize_label">
								奖品类型：
							</td>
							<td class="td_prize_edit">
								<h2y:input id="prizeType" name="prizeType" type="select" css="h2y_select_just" dictcode="prize_type" />
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								<input class="button goodsBut" type="button" value="选择商品" index_="" id="selectGoodsBut"/>：
							</td>
							<td class="td_prize_edit">
								<input id="goodsId" name="goodsId" type="hidden" value="" />
								<div id="goodsId_div"></div>
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								奖品：
							</td>
							<td class="td_prize_edit">
								<input name="prizeId" value="0" type="hidden" class="h2y_input_just"/>
								<input name="prizeName" value="" type="text" class="h2y_input_just"/>
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								奖品总数量：
							</td>
							<td class="td_prize_edit">
								<input name="prizeCount" value="0" type="text" class="h2y_input_short"/>
								<span style="color: red;">（0时不做限制）</span>
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								数量：
							</td>
							<td class="td_prize_edit">
								<input name="prizeNumber" value="1" type="text" class="h2y_input_short"/>
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								中奖几率：
							</td>
							<td class="td_prize_edit">
								<input name="prizeRate" value="" type="text" class="h2y_input_short"/>%
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								奖项名称：
							</td>
							<td class="td_prize_edit">
								<input name="prizeLevel" value="" type="text" class="h2y_input_just"/>
							</td>
						</tr>
						<tr>
							<td class="td_prize_label">
								排序：
							</td>
				            <td class="td_prize_edit">
				                <input name="ord" type="text" id="ord" value=""/>
				            </td>
			            </tr>
					</table>
					</div>
					<div style="text-align:center;"><a href="javaScript:hwtt_add_tr();" style="display: inline-block;"><img src="<%=uiPath%>lib/ligerUI/skins/icons/add.gif" alt="添加奖项" title="添加奖项" />添加奖项</a></div>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>