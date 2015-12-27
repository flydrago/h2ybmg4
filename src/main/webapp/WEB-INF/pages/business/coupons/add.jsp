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
		.goodstype_select{ 
			 border: 1px solid #aecaf0;
			 height:25px;
			 line-height: 25px;
		 }
		 
	</style>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        
        var contentEditor = null;
        var typeIndex = 0;
        var markDataJson = null;
        
        var isInit = true;
        $(function () {

        	KindEditor.ready(function(K) {
        		 contentEditor = K.create("#couponsDetail_couponsRule", {
        		 items:['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
        		        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
        		        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
        		        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
        		        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
        		        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',  'table', 'hr', 'emoticons', 'pagebreak',
        		        'anchor', 'link', 'unlink', '|', 'about']
                 });
       		});
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
        	
            if (op == "modify") {
                
            	if("${coupons.isLimitGoods}"=="1"){
            		
            		 $("#goodslistdiv").html("<a href=\"javaScript:h2y_detail(${goodsPrice.id});\">${goodsPrice.goodsNickName}<a/><br/>市场价：￥${goodsPrice.marketPrice}<br/>会员价：￥${goodsPrice.memberPrice}");
                     $("#coupons_limitGoods").val(${goodsPrice.id});
            	}
            	
            	if("${coupons.isLimitType}"=="1"){
            		
            		 var couponsLimitType = "${coupons.limitType}";
                     var couponsLimitType_array = couponsLimitType.split("_");
                     modifyselect(couponsLimitType_array);
           		}
            	
            	if("${coupons.isLimitMark}"=="1"){
            		
	             	$("#markHtml_show").html("${goodsMark.markName}：${goodsMarkInfo.name}");
	             	$("#coupons_limitMark").val(${goodsMarkInfo.id});
          		}
            	
            	//优惠券已认领 不能修改数据
            	if("${coupons.claimedCount}">0){
            		$("#coupons_couponsName").attr("disabled",true);
            		$("#coupons_limitCount").attr("disabled",true);
            		$("#coupons_isActivity").attr("disabled",true);
            		$("#coupons_couponsBalance").attr("disabled",true);
            		$("#coupons_limitAmount").attr("disabled",true);
            		
            		
            		$("input[type=radio][name='coupons.isActivity']").attr("disabled",true);
            		$("input[type=radio][name='coupons.isLimitCount']").attr("disabled",true);           		
            		$("input[type=radio][name='coupons.isLimitAmount']").attr("disabled",true);
            		$("input[type=radio][name='coupons.isLimitType']").attr("disabled",true);
            		$("input[type=radio][name='coupons.isLimitMark']").attr("disabled",true);
            		$("input[type=radio][name='coupons.isLimitGoods']").attr("disabled",true);
            		$("input[type=radio][name='coupons.isLimitPlatform']").attr("disabled",true);
            	}
            	
            }else{
            	
            	$("input[type=radio][name='coupons.isActivity'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isMulti'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitAmount'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitType'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitMark'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitGoods'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitCount'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitPlatform'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.limitPlatform']:first").attr("checked",true);
            	
            }
            
            $("#coupons_couponsBalance").ligerSpinner({type: 'float' ,height: 25,width:$("#coupons_couponsBalance").width(),minValue:0});
            $("#coupons_limitAmount").ligerSpinner({type: 'float' ,height: 25,width:$("#coupons_limitAmount").width(),minValue:0});
            $("#coupons_limitCount").ligerSpinner({type: 'int' ,height: 25,width:$("#coupons_limitCount").width(),minValue:0});
            
          //限总数量
            limitCountChange($("input[type=radio][name='coupons.isLimitCount']:checked").val());
            $("input[type=radio][name='coupons.isLimitCount']").change(function(){
            	
            	limitCountChange(this.value);
            });
            
            //限额
            limitAmountChange($("input[type=radio][name='coupons.isLimitAmount']:checked").val());
            $("input[type=radio][name='coupons.isLimitAmount']").change(function(){
            	
            	limitAmountChange(this.value);
            });
            
            //类型限制
            limitTypeChange($("input[type=radio][name='coupons.isLimitType']:checked").val());
            $("input[type=radio][name='coupons.isLimitType']").change(function(){
            	limitTypeChange(this.value);
            });
            
            //标签限制
            limitMarkChange($("input[type=radio][name='coupons.isLimitMark']:checked").val());
            $("input[type=radio][name='coupons.isLimitMark']").change(function(){
            	limitMarkChange(this.value);
            });
            
            //商品限制
            limitGoodsChange($("input[type=radio][name='coupons.isLimitGoods']:checked").val());
            $("input[type=radio][name='coupons.isLimitGoods']").change(function(){
            	limitGoodsChange(this.value);
            });
            
            //平台限制
            limitPlatformChange($("input[type=radio][name='coupons.isLimitPlatform']:checked").val());
            $("input[type=radio][name='coupons.isLimitPlatform']").change(function(){
            	limitPlatformChange(this.value);
            });
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            
			$("#selectMarkBut").click(function(){
            	
            	var gdsCode = $("#goods_type_td select:last").val();
            	if(""==gdsCode || undefined==gdsCode){
            		alert("请选择商品类型");
            		return;
            	}
            	openMarkSelectDialog({typeCode:gdsCode});
            });
			
			
			$("#selectGoodsBut").click(function(){
            	openPriceGoodsSelectDialog();
            })
            
            
        	$("#coupons_startDate").datetimepicker({});
            $("#coupons_endDate").datetimepicker({});
        });
        
        
        //限总数量
        function limitCountChange(value){
        	
        	var limitSpinnerManager = $("#coupons_limitCount").ligerGetSpinnerManager();
        	if(value=="0"){
        		limitSpinnerManager.setDisabled(1);
        	}else{
        		limitSpinnerManager.setEnabled(1);
        	}
        }
        
        //限额
        function limitAmountChange(value){
        	
        	var limitSpinnerManager = $("#coupons_limitAmount").ligerGetSpinnerManager();
        	if(value=="0"){
        		limitSpinnerManager.setDisabled(1);
        	}else{
        		limitSpinnerManager.setEnabled(1);
        	}
        }
        
        //限制类型
        function limitTypeChange(value){
        	
        	if(value=="0"){
        		
        		$(".goodstype_select").attr("disabled",true);
        	}else{
        		
        		if(isInit && $(".goodstype_select").length==0){
        			 //添加一级商品类型
                    addselect("0","","");
        		}
        		
        		$(".goodstype_select").attr("disabled",false);
        		//不限制商品
            	$("input[type=radio][name='coupons.isLimitGoods'][value='0']").attr("checked",true);
            	limitGoodsChange($("input[type=radio][name='coupons.isLimitGoods']:checked").val());
        	}
        }
        
        
        //限制标签
        function limitMarkChange(value){
        	
        	if(value=="0"){
        		
        		$("#selectMarkBut").attr("disabled",true);
        	}else{
        		$("#selectMarkBut").attr("disabled",false);
        		
        		//不限制商品
            	$("input[type=radio][name='coupons.isLimitGoods'][value='0']").attr("checked",true);
            	limitGoodsChange($("input[type=radio][name='coupons.isLimitGoods']:checked").val());
        	}
        }
        
        
        //限制商品
        function limitGoodsChange(value){
        	
        	if(value=="0"){
        		
        		$("#selectGoodsBut").attr("disabled",true);
        	}else{
        		
        		$("#selectGoodsBut").attr("disabled",false);
				
        		
        		//限制商品后，类型和标签就会失效
        		$("input[type=radio][name='coupons.isLimitType'][value='0']").attr("checked",true);
            	$("input[type=radio][name='coupons.isLimitMark'][value='0']").attr("checked",true);
            	limitTypeChange($("input[type=radio][name='coupons.isLimitType']:checked").val());
                limitMarkChange($("input[type=radio][name='coupons.isLimitMark']:checked").val());
        	}
        }
        
        //限制平台
        function limitPlatformChange(value){
        	
        	if(value=="0"){
        		
        		$("input[type=radio][name='coupons.limitPlatform']").attr("disabled",true);
        	}else{
        		$("input[type=radio][name='coupons.limitPlatform']").attr("disabled",false);
        	}
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
			
			isInit = false;
			
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
					selectHtml = "<select id=\"goodstype_select_"+typeIndex+"\" class=\"goodstype_select\"><option value=\"\">--请选择--</option>"+selectHtml+"</select>";
					$("#goods_type_td").append(selectHtml+"");
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
					selectHtml = "<select id=\"goodstype_select_"+typeIndex+"\" class=\"goodstype_select\"><option value=\"\">--请选择--</option>"+selectHtml+"</select>";
					$("#goods_type_td").append(selectHtml+"");
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
        	//限总数量
        	if($("input[type=radio][name='coupons.isLimitCount']:checked").val()==0){
        		$("#coupons_limitCount").val("");
        	}else{
        		var limitCount = $("#coupons_limitCount").val();
        		if(""==limitCount || undefined==limitCount){
        			alert("请输入限制数量！");
        			return;
        		}
        	}
        	
        	//限额
        	if($("input[type=radio][name='coupons.isLimitAmount']:checked").val()==0){
        		$("#coupons_limitAmount").val("");
        	}else{
        		var limitAmount = $("#coupons_limitAmount").val();
        		if(""==limitAmount || undefined==limitAmount){
        			alert("请输入订单限制金额！");
        			return;
        		}
        	}
        	
        	//类型
        	if($("input[type=radio][name='coupons.isLimitType']:checked").val()==0){
        		$("#coupons_limitType").val("");
        	}else{
        		var limitType = $("#goods_type_td select:last").val();
            	if(""==limitType || undefined==limitType){
            		alert("请选择商品类型");
            		return;
            	}
            	$("#coupons_limitType").val(limitType);
        	}
        	
        	//限标签
			if($("input[type=radio][name='coupons.isLimitMark']:checked").val()==0){
        		$("#coupons_limitMark").val("");
        	}else{
        		var limitMark = $("#coupons_limitMark").val();
        		if(""==limitMark || undefined==limitMark){
        			alert("请选择标签！");
        			return;
        		}
        	}
        	
        	//限商品
			if($("input[type=radio][name='coupons.isLimitGoods']:checked").val()==0){
        		$("#coupons_limitGoods").val("");
        	}else{
        		var limitGoods = $("#coupons_limitGoods").val();
        		if(""==limitGoods || undefined==limitGoods){
        			alert("请选择商品！");
        			return;
        		}
        	}
        	
        	//限平台
			if($("input[type=radio][name='coupons.isLimitPlatform']:checked").val()==1){
				
        		if($("input[type=radio][name='coupons.limitPlatform']:checked").length==0){
        			alert("请选择平台！");
        			return;
        		}
        	}
        	
        	
			if(!Validator.form()) return;
			
			//同步富文本编辑框数据
            contentEditor.sync();
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/coupons/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_coupons_init_htm") != null) {
							top.f_getframe("business_coupons_init_htm").f_query();
						}
						top.f_delTab("business_coupons_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_coupons_init_htm") != null) {
							top.f_getframe("business_coupons_init_htm").f_query();
						}             
                        top.f_delTab("business_coupons_modify${coupons.id}");
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
        	
        	if(data==null) return;
        	
        	if(data.length>1){
        		alert("只能选择一个标签！");
        		return;
        	}
        	
        	var markHtml = data[0].MARK_NAME+":"+data[0].NAME;
        	$("#markHtml_show").html(markHtml);
        	$("#coupons_limitMark").val(data[0].ID);
        }
        
      	
        //商品选择回调函数
        function h2y_priceGoodsSelectCallBack(data){
        	
        	if(data==null || data.length==0) return;
        	$("#coupons_limitGoods").val(data.ID);
        	$("#goodslistdiv").html("<a href=\"javaScript:h2y_detail("+data.ID+");\">"+data.GOODS_NICK_NAME+"</a><br/>市场价：￥"+data.MARKET_PRICE+"<br/>会员价：￥"+data.MEMBER_PRICE);
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
		
		<input name="coupons.id" type="hidden" id="coupons_id" value="${coupons.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<input name="coupons.limitType" id="coupons_limitType" type="hidden" value="${coupons.limitType}"/>
		<input name="coupons.limitMark" id="coupons_limitMark" type="hidden" value="${coupons.limitMark}"/>
		<input name="coupons.limitGoods" id="coupons_limitGoods" type="hidden" value="${coupons.limitGoods}"/>
		<input name="coupons.isMulti" id="coupons_isMulti" type="hidden" value="0"/>
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					<input name="coupons.couponsName" id="coupons_couponsName" class="h2y_input_long" type="input" value="${coupons.couponsName}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<input name="coupons.startDate" id="coupons_startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${coupons.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>~
					<input name="coupons.endDate" id="coupons_endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${coupons.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					活动商品：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isActivity" id="coupons_isActivity" type="radio" initoption="0,支持:1,不支持" value="${coupons.isActivity}"/>
				</td>
				<td class="h2y_table_label_td">
					金额：
				</td>
				<td class="h2y_table_edit_td">
					<input name="coupons.couponsBalance" id="coupons_couponsBalance" type="input" class="h2y_input_just" value="${coupons.couponsBalance}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					限制数量：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitCount" id="coupons_isLimitCount" type="radio" initoption="0,不限制:1,限制" value="${coupons.isLimitCount}"/>
				</td>
				<td class="h2y_table_label_td">
					数量：
				</td>
				<td class="h2y_table_edit_td">
					<input name="coupons.limitCount" id="coupons_limitCount" type="input" class="h2y_input_just" value="${coupons.limitCount}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					订单限额：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitAmount" id="coupons_isLimitAmount" type="radio" initoption="0,不限制:1,限制" value="${coupons.isLimitAmount}"/>
				</td>
				<td class="h2y_table_label_td">
					额度：
				</td>
				<td class="h2y_table_edit_td">
					<input name="coupons.limitAmount" id="coupons_limitAmount" type="input" class="h2y_input_just" value="${coupons.limitAmount}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					限制类型：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitType" id="coupons_isLimitType" type="radio" initoption="0,不限制:1,限制" value="${coupons.isLimitType}"/>
				</td>
				<td class="h2y_table_label_td">
					类型：
				</td>
				<td class="h2y_table_edit_td" id="goods_type_td">
					
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					限制标签：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitMark" id="coupons_isLimitMark" type="radio" initoption="0,不限制:1,限制" value="${coupons.isLimitMark}"/>
				</td>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="选择标签" id="selectMarkBut"/>：
				</td>
				<td class="h2y_table_edit_td">
					<div id="markHtml_show">
						
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					限制商品：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitGoods" id="coupons_isLimitGoods" type="radio" initoption="0,不限制:1,限制" value="${coupons.isLimitGoods}"/>
				</td>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="选择商品" id="selectGoodsBut"/>：
				</td>
				<td class="h2y_table_edit_td">
					<div id="goodslistdiv">
					</div>
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					限制平台：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.isLimitPlatform" id="coupons_isLimitPlatform" type="radio" initoption="0,不限制:1,限制" value="${coupons.isLimitPlatform}"/>
				</td>
				<td class="h2y_table_label_td">
					平台：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="coupons.limitPlatform" id="coupons_limitPlatform" type="radio" dictcode="couponsLimitPlatform" value="${coupons.limitPlatform}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="coupons.memo" id="coupons_memo" type="input" value="${coupons.memo}"  class="h2y_input_long"/>
				</td>
			</tr>
			
			<tr id="subjectContent_tr">
				<td class="h2y_table_label_td">
					规则详细：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<textarea name="couponsDetail.couponsRule" id="couponsDetail_couponsRule" class="h2y_editor_textarea">${couponsDetail.couponsRule}</textarea>
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>