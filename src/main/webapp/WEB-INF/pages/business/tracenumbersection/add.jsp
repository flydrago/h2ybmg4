<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>


    <script type="text/javascript">
       var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var parentId = "${parentId}";
        var spec = "${spec}";
        var numString = "${numString}";
        var numArry = numString == "" ? [] : numString.split(","); 
        var specArryString = "${specArry}";
        var specArry = specArryString == "" ? [] : specArryString.split("-");
        var provinceId = "${provinceId}";
        var form = null;
		
        var startValue = 0;
        var endValue = 0;
        
        var numLimit = "${numLimit}";
        var boxLimit = "${boxLimit}";
        
        $(function () {
        	
        	initUI();
        	fillData();
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            
            $("#selcetProvinceBtn").click(function(){
            	$(".l-verify-tip").hide();
            	setInput3();
    			selectProvince();
        	});
            
            $("#selcetProviderBtn").click(function(){
            	$(".l-verify-tip").hide();
            	setInput3();
				selectionProvider();
        	});
        	
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
        });

        
        function h2y_save() {
        	
        	if(!Validator.form()){
        		setInput2();
        		return;
        	} 
        	
			if(!setInput()) return;
			if(op == "assign_provider_add"){
				if(!setBottleNum($("#boxNum"))) return;
			}

			
            var queryString = $('#editform').serialize();
			
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;
			$("input").attr("disabled",true);
			if(op == "assign_provider_add"){
				$("#loading").show();
				$("#startSelNo").attr("disabled","disabled");
				$("#endSelNo").attr("disabled","disabled");
			}
            $.post("business/tracenumbersection/save.htm", queryString, function (data) {
            	
                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }else if(op == "register_add"){
                	if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }else if(op == "start_add"){
                	if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }else if(op == "assign_province_add"){
                	if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        //parent.f_query();
                       // frameElement.dialog.close();
                       if (top.f_getframe("assign_province_qr" + parentId) != null) {
							top.f_getframe("assign_province_qr" + parentId).f_query();
						 }
						top.f_delTab("business_tracenumbersection_assign_province_add"); 
                    } else {
                        alert(jsonReturn.msg);
                    }
                }else if(op == "assign_provider_add"){
                	$("#loading").hide();
                	if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        //parent.f_query();
                       // frameElement.dialog.close();
                       if (top.f_getframe("assign_provider_qr" + parentId) != null) {
							top.f_getframe("assign_provider_qr" + parentId).f_query();
						 }
						top.f_delTab("business_tracenumbersection_assign_provider_add"); 
                    } else {
                        alert(jsonReturn.msg);
                    }
                }else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }

        // 填充select的数据
        function fillData(){
        	
	    	for(var i = 0; i < numArry.length; i++){
	    		var optObj = $("#optTemplate").clone();
	    		optObj.removeAttr("id");
	    		optObj.val(numArry[i]);
	    		optObj.attr("data-index",i + 2);
	    		optObj.html(numArry[i]);
	    		if(i % 2 == 0){
	    			optObj.appendTo("#startSelNo");
	    		}else{
	    			optObj.appendTo("#endSelNo");
	    		}
	    	}
	    	$("#optTemplate").removeAttr("id");
	    }
        
        //初始化ui
        function initUI(){
        	
        	if(op == "add"){
        		$("#specTr").hide();
        	}else{
        		$("#specTr").show();
        		
        		for(var i = 0; i < specArry.length; i++){
            		var optObj = $("#specTemplate").clone();
            		optObj.removeAttr("id");
            		optObj.val(specArry[i]);
    	    		optObj.html(specArry[i]);
    	    		optObj.appendTo("#spec");
            	}
        		
        		if(op != "register_add"){
        			
        			if(op != "start_add"){
	        			$("#toptoolbar").ligerToolBar({
	    	        		items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },
	    	        	            { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]
	    	        	});
        			}	
        			
                	$("#specTemplate").removeAttr("id");
	                	
        			$("#specTr").find("select option").attr("disabled",true);
        			$("#specTr").find("select").val(spec);
        			if(op == "assign_province_add"){
        				$("#agentTr").show();
        				//$("input[type!=\"button\"]").addClass("h2y_dialog_input_long");
        				$("input[type!=\"button\"]").width(360);
        				$("form table").width("800px");
        			}else if(op == "assign_provider_add"){
        				$("#agentTr").show();
        				$("#providerTr").show();
        				$("#boxNumTr").show();
        				$("#bottleTotalTr").show();
        				$("#endSelNo").hide();
        				$("#endSelNo").find("select option").attr("disabled",true);
        				
        				$("#boxNumTr").find("span[data-num=\"boxNum\"]").text(boxLimit);
        				$("#bottleTotalTr").find("span[data-num=\"bottleNum\"]").text(numLimit);
        				//$("input[type!=\"button\"]").addClass("h2y_dialog_input_long");
        				$("input[type!=\"button\"]").width(360);
        				$("#selcetProvinceBtn").attr("disabled","disabled");
        				$("#selcetProvinceBtn").siblings("input").attr("readonly","readonly");
        				$("form table").width("800px");
        			}
        		}
        	}
        	if(numArry.length == 0){
        		$("input").attr("readonly","readonly");
        		$("#warmTip").find("span").text("暂无数据可以分配了！");
        		return;
        	}
        	$("#warmTip").find("span").html("起始值应大于等于(>=)选取的数值<br/>终止值应小于等于(<=)选取的数值");
        }
        
        
        //选择select来设置input
		function setInputValue(elem){
			$(elem).siblings("input").val($(elem).val());
			var optIndex = $(elem).find("option:selected").attr("data-index");
			optIndex = parseInt(optIndex);
			if(optIndex % 2 == 0){
				optIndex = optIndex + 1;
			}else{
				optIndex = optIndex - 1;
			}
			$(elem).val($(elem).find("option[value=\"\"]").val());
			
			setDataRelate(elem,optIndex);
		}
        
        //设置select的对应关系
        function setDataRelate(elem,index){
        	
        	var reg=/^\d+$/;
        	var idStr = $(elem).attr("id");
        	if( idStr == "startSelNo"){
        		var endOpt = $("#endSelNo").find("option[data-index="+ index + "]");
        		endOpt.attr("selected",true);
        		
        		if(op != "assign_provider_add"){
	        		$("#endSelNo").val($("#endSelNo").find("option[value=\"\"]").val());
	        		$("#endNo").val(endOpt.val());
        		}else{
        			var tempPerNumLimit = parseInt(endOpt.val()) - parseInt($(elem).siblings("input").val());
        			
        			if(tempPerNumLimit >= boxLimit){ //区间大于设定数值得到的箱数限制
	        			if(reg.test($("#boxNum").val())){
	        			 	$("#endNo").val(parseInt($("#startNo").val()) + parseInt($("#boxNum").val()));
	        			}
        			}else{ //区间小于设定数值得到的箱数限制
						boxLimit = tempPerNumLimit;
						numLimit = boxLimit * parseInt(spec);
        				$("#boxNumTr").find("span[data-num=\"boxNum\"]").text(boxLimit);
        				$("#bottleTotalTr").find("span[data-num=\"bottleNum\"]").text(numLimit);
        				setBottleNum($("#boxNum"));
        			}
        		}
        		
        	}else if(idStr == "endSelNo"){
        		var startOpt = $("#startSelNo").find("option[data-index="+ index + "]");
        		startOpt.attr("selected",true);
        		$("#startSelNo").val($("#endSelNo").find("option[value=\"\"]").val());
        		$("#startNo").val(startOpt.val());
        	}
        	startValue = parseInt($("#startNo").val());
        	endValue = parseInt($("#endNo").val());
        	
        	if(op != "assign_provider_add"){
        		$("#startNo").attr({"readonly":false});
        		$("#endNo").attr({"readonly":false});
        	}
        	
        	$("#startNo").removeAttr("onclick");
        	$("#endNo").removeAttr("onclick");
       		
        	$(".l-verify-tip").hide();
       		$("#startSelNo").animate({marginLeft: 0}, "slow");
    		$("#endSelNo").animate({marginLeft: 0}, "slow");
        }
        
        
        //刷新
        function h2y_refresh() {
            document.location.reload();
        }
        
        //控制起始输入框与结束输入框
        function setInput(){//elem
        	
        	$("#startNo").val($("#startNo").val().replace(/(^\s*)|(\s*$)/g, ""));
        	$("#endNo").val($("#endNo").val().replace(/(^\s*)|(\s*$)/g, ""));
        	
        	//var idStr = $(elem).attr("id");
        	//if( idStr == "startNo"){
        		
        		var tempStartValue = parseInt($("#startNo").val());
        		var tempEndValue = parseInt($("#endNo").val());
        		
        		if(tempStartValue < startValue ){
        			alert("起始值应大于等于选定的起始值");
        			$("#startNo").val(startValue);
        			return false;
        		}
        		
        		if(tempEndValue > endValue){
        			alert("终止值应小于等于选定的终止值");
        			$("#endNo").val(endValue);
        			return false;
        		}
        		
        		if(tempStartValue > endValue){
        			alert("起始值小于等于号段终止值");
        			$("#startNo").val(startValue);
        			return;
        		}
        		
        		if(tempEndValue < endValue && tempStartValue > tempEndValue){
        			alert("终止值大于等于号段起始值");
        			$("#endNo").val(endValue);
        			return false;
        		}
        		
        		return true;
        	//}
        }
        
        //选择省级代理
        function selectProvince(){
        	
        	callBackFlag = "1";
        	openProvinceSelectDialog();
        }
        //选择供应商
        function selectionProvider(){
        	callBackFlag = "2";
        	var params = {};
        	params.parentId = provinceId;
        	openProviderSelectDialog(params);
        }
        
      //省级代理选择回调函数
        function h2y_provinceSelectCallBack(data){
        	//provinceDataJson = data;
        	//data_to_input({inputToNameId:"toName",inputToId:"toId",data:data});
        	$("#provinceName").val(data.UNIT_NAME);
        	$("#provinceId").val(data.ID);
        }
      //供应商选择回调函数
        function h2y_providerSelectCallBack(data){
        	//provinceDataJson = data;
        	//data_to_input({inputToNameId:"toName",inputToId:"toId",data:data});
        	$("#providerName").val(data.PROVIDER_NAME);
        	$("#providerId").val(data.ID);
        }
     
      //出现验证信息时部分元素的位置变化
      function setInput2(){
    	  
    	if($("#startNo").attr("ligertipid") != undefined){
  			$("#startSelNo").animate({marginLeft: $(".l-verify-tip-content").outerWidth()}, "slow");
  		}
  		if($("#endNo").attr("ligertipid") != undefined){
  			$("#endSelNo").animate({marginLeft: $(".l-verify-tip-content").outerWidth()}, "slow");
  		}
  		if (op == "assign_province_add") {
			 if($("#provinceName").attr("ligertipid") != undefined){
     			$("#selcetProvinceBtn").animate({marginLeft: $(".l-verify-tip-content").outerWidth()}, "slow");
     		}
        }
  		if (op == "assign_provider_add") {
			 if($("#providerName").attr("ligertipid") != undefined){
     			$("#selcetProviderBtn").animate({marginLeft: $(".l-verify-tip-content").outerWidth()}, "slow");
     		}
        }
      }
      
      //验证信息消失后部分元素的位置变化
      function setInput3(){
    	  
    	$("#startSelNo").animate({marginLeft: 0}, "slow");
  		$("#endSelNo").animate({marginLeft: 0}, "slow");
  		$("#selcetProvinceBtn").animate({marginLeft: 0}, "slow");
  		$("#selcetProviderBtn").animate({marginLeft: 0}, "slow");
      }
      
      
      //箱数框输入的时候设置瓶数
      function setBottleNum(elem){
    	  var reg=/^\d+$/;   // 注意：故意限制了 0321 这种格式，如不需要，直接reg=/^\d+$/;
    	  var tempValue = $(elem).val();
    	  if(reg.test(tempValue)){
    		  tempValue = parseInt(tempValue);
    		  if(tempValue > boxLimit){
    			  alert("箱数不能大于" + boxLimit +"，系统已将其设置为" + boxLimit );
    			  $(elem).val(boxLimit);
    			  $("#bottleNum").val(boxLimit * parseInt(spec));
    			  return false;
    		  }
    		  
    		  $("#bottleNum").val(tempValue * parseInt(spec));
    	  }else{
    		  alert("箱数必须填写,系统已将其设置为1");
    		  $(elem).val(1);
    		  setBottleNum(elem);
    		  return false;
    	  }
    	  
    	  if(reg.test($("#startNo").val())){
			  $("#endNo").val(parseInt($("#startNo").val()) + parseInt($("#boxNum").val()));
		  }
    	  return true;
      }
      
      //
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
                    <div id="toptoolbar" style="font-size:12px;"></div>
                </td>
            </tr>
        </table>
    </div>
<form name="editform" method="post" action="" id="editform">
 
    <input type="hidden" name="op" value="${op}"/>
    <input type="hidden" name="parentId" value="${parentId}"/>
    <table class="h2y_dialog_table"><!-- h2y_table -->

        <tr>
            <td class="h2y_table_label_td">号段起始值:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="startNo" type="number" id="startNo"  class="h2y_input_just" readonly="readonly" onclick="javascript:alert('请先选择号段再编辑吧')" style="padding-left:5px;"/>
	             <select id="startSelNo" style="line-height:25px;height:25px;" onchange="setInputValue(this)">
					<option id="optTemplate" value="" data-index="0">--请选择--</option>
				 </select>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td">号段终止值:</td>
            <td class="h2y_dialog_table_edit_td"><!--h2y_dialog_input_long  -->
                <input name="endNo" type="number" id="endNo"  class="h2y_input_just"  value="" readonly="readonly" onclick="javascript:alert('请先选择号段再编辑吧')" style="padding-left:5px;"/>
                 <select id="endSelNo" style="line-height:25px;height:25px;" onchange="setInputValue(this)">
					<option value="" data-index="1">--请选择--</option>
				 </select>
            </td>
        </tr>
        
		<tr id="specTr">
            <td class="h2y_table_label_td">规格:</td>
            <td class="h2y_dialog_table_edit_td"><!--h2y_dialog_input_long  -->
               <select name="spec" id="spec" style="width:60px;text-align:center;">
					<option value="" id="specTemplate"></option>
				</select>&nbsp;<span id="cell">瓶/箱</span>
            </td>
        </tr>
        
        
        <tr id="boxNumTr" style="display:none;">
            <td class="h2y_table_label_td">箱数:</td>
            <td class="h2y_dialog_table_edit_td"><!--h2y_dialog_input_long  -->
              <input name="boxNum" type="number" id="boxNum"  class="h2y_input_just"  value="" oninput="setBottleNum(this)" style="padding-left:5px;"/>
              &nbsp;箱
              &nbsp;<span style="color:red;">一次最多<span data-num="boxNum"></span>箱</span>
            </td>
        </tr>
        
        <tr id="bottleTotalTr" style="display:none;">
            <td class="h2y_table_label_td">合计:</td>
            <td class="h2y_dialog_table_edit_td"><!--h2y_dialog_input_long  -->
               <input name="bottleNum" type="number" class=h2y_dialog_input_long id="bottleNum" value="" readonly="readonly" style="padding-left:5px;border:none;"/>
               &nbsp;瓶
                &nbsp;<span style="color:red;">一次最多<span data-num="bottleNum"></span>瓶</span>
            </td>
        </tr>
		<tr id="agentTr" style="display:none;">
            <td class="h2y_table_label_td">省级代理:</td>
            <td class="h2y_dialog_table_edit_td">
               <input name="provinceName" id="provinceName" type="text" id="toName"  value="${provinceName}" class="h2y_input_just" readonly="readonly" style="padding-left:5px;"onclick="javascript:alert('请选择省级代理')"/>
               <input name="provinceId" type="hidden" id="provinceId" value="${provinceId}"/>
	           <input type="button" id="selcetProvinceBtn" value="选择省级代理" style="padding-left:5px;padding-right:5px;width:100px;height:25px;"/>
            </td>
        </tr>
        <tr id="providerTr" style="display:none;">
            <td class="h2y_table_label_td">供应商代理:</td>
            <td class="h2y_dialog_table_edit_td">
               <input name="providerName" type="text" id="providerName"  class="h2y_input_just" readonly="readonly" style="padding-left:5px;"onclick="javascript:alert('请选择供应商')"/>
               <input name="providerId" type="hidden" id="providerId"/>
	           <input type="button" id="selcetProviderBtn" value="选择供应商" style="padding-left:5px;padding-right:5px;width:100px;height:25px;"/>
            </td>
        </tr>
    </table>
</form>
<div id="warmTip" style="width:100%;text-align:center;margin-top:20px;color:red;">
<span></span>
</div>

<div id="loading" style="display: none;width:100%;text-align:center;margin-top:20px;">
	序列生成中，请耐心等待……<img src="<%=uiPath %>lib/images/loading.gif" width="50" height="50 alt="">
</div>
</body>
</html>