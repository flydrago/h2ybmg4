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
	.activity_img { max-width:600px;}
</style>	

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        var editor;
        var goodsDataJson = null;
        
        var pushFlag = 1;
        $(function () {
        	
        	//处理选择最小时间
        	var myDate = new Date();
    		var myHours = myDate.getHours();       //获取当前小时数(0-23)
    		var myMinutes = myDate.getMinutes();     //获取当前分钟数(0-59)
    		

    		
    		//处理 小时最小值
    		var myMinHour;//最小小时
    		var myMinMinute;//最小分钟
    		//计算分钟和小时  延后10分钟
    		if(myMinutes + 10 >59){
    			myHours = myHours +1;
    			myMinutes = myMinutes + 10 -59;
    		}else{
    			myMinutes = myMinutes + 10;
    		}
    		//格式化
    		if(myHours<10){
    			myMinHour = "0"+myHours;
    		}else{
    			myMinHour = myHours;
    		}  		
    		//格式化
    		if(myMinutes<10){
    			myMinMinute = "0"+myMinutes;
    		}else{
    			myMinMinute = myMinutes;
    		}
        	
    		
    		
             KindEditor.ready(function(K) {
                     editor = K.create("#editor_id", {
                    	 uploadJson : '<%=basePath%>${imUpUrl}?type=fwb'
                     });
             });

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            if (op == "modify") {
                $("#tr_next").hide();
                h2y_goodsSelectCallBack(${goodsListJson});
                
                if(${findActivity.diskFileName!=null}){
                	
                	var json_str = "{\"id\":\"${findActivity.id}\"}";
             		$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"activity_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=findActivityService&id=${findActivity.id}\" title=\"${findActivity.fileName}\"><img class=\"activity_img\" src=\"common/image/view.htm?fileBean=findActivityService&id=${findActivity.id}\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
             		addDelClass();
             		$("#light_box_a").lightBox();
                }
                //设置推送频数
                pushFlag = ${findActivity.pushTimes};
            }else{
            	$("input[type=radio][name='activityLevel']:first").attr("checked",true);
            	$("input[type=radio][name='activityType']:first").attr("checked",true);
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#selectGoodsBut").click(function(){
            	openGoodsSelectDialog({selectType:"multi"});
            });
            
            $("#startDate").datetimepicker({});
	        $("#endDate").datetimepicker({});
	        $("#pushDate").datetimepicker({});
	       
	        
            
	        
	        if("${findActivity.ifSetDate}" == "1"){
	        	$("#if_set_date_check").attr("checked",true);
	        }
	        
	        setDateChange($("#if_set_date_check").attr("checked"));
            $("#if_set_date_check").change(function(){
            	setDateChange(this.checked);
            });
            
            
            //是否推送
            
            	if("${findActivity.ifSetPush}" == "1"){
            		//显示推送类型
            		$("#pushTypeTr").show();
            		
            		//设置推送时间
    	        	if("${findActivity.pushType}" == "2"){
        	        	$("#pushDateTr2").show();
        	        	$("#pushTimesTr").hide();
        	        	$("#pushDateTr").hide();
        	        	
    	        	}else{
    	        		
                		
        	        	$("#pushTimesTr").show();
        	        	$("#pushDateTr").show();
        	        	$("#pushDateTr2").hide();
        	        	$("#pushDate1").timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
        	        	$("#pushDate2").timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
        	        	$("#pushDate3").timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
        	        	$("#pushDate4").timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
        	        	$("#pushDate5").timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
        	        	$("#pushDate6").timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
    	        	}
    	        }
            
            
            //设置是否推送
            $("input[type=radio][name='ifSetPush']").change(function(){                 	
            	if(this.value == 1){
            		$("#pushTypeTr").show();
    	        	$("#pushTimesTr").show();
    	        	//默认仅推一天
    	        	$("input[type=radio][name='pushType']:last").attr("checked",true); 
    	        	$("#pushDateTr2").show();
    	        	$("#pushTimesTr").hide();
 	        		$("#pushDateTr").hide();
 	        		$("#pushDate").datetimepicker({});							
            	}else{
            		$("#pushTypeTr").hide();
    	        	$("#pushTimesTr").hide();
    	        	$("#pushDateTr").hide();
    	        	$("#pushDateTr2").hide();
    	        	$("#pushDateTd").html("");
    	        	
            	}
            });
	        
            
          //设置推送类型
            $("input[type=radio][name='pushType']").change(function(){
            	if(this.value == 2){
            		$("#pushDateTr2").show();
	        		$("#pushDateTr").hide();
	        		$("#pushTimesTr").hide();
	        		$("#pushDate").datetimepicker({});
            	}else{
            		$("#pushDateTr2").hide();
            		$("#pushDateTr").show();
            		$("#pushTimesTr").show();
	        		
            		$("input[type=radio][name='pushTimes']:last").attr("checked",true);
            		var pushTimes =parseInt($("input[type=radio][name='pushTimes']:checked").val());
            		
	        		pushFlag = pushTimes;
    	        	$("#pushDateTd").html("");
    	        	var pushDateName = "pushDate";  	      
    	        	
    	        	
    	        	
    	        	for(var i=1;i<=pushTimes;i++){
    	        		pushDateName = "pushDate"+i;
    	        		var pushDateText = '<input name="'+pushDateName+'" id="'+pushDateName+'" class="h2y_input_datetime" type="input" value="${findActivity.pushDate1}" /><br/><br/>';
    	        		$("#pushDateTd").append("<div id='pushDiv"+i+"'>时间"+i+"："+pushDateText+"</div>"); 
    	        		// $("input[type=input][name='pushDate']").timepicker({});
    	        		$("#"+pushDateName).timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
    	        	}
    	           
            	}
            });
            
            //设置推送频数
            $("input[type=radio][name='pushTimes']").change(function(){
            	var pushTimes =parseInt( $("input[type=radio][name='pushTimes']:checked").val());
            	if(parseInt(pushFlag)>pushTimes){
            		for(var i=pushFlag;i>pushTimes;i--){
            			$("#pushDiv"+i).remove();
    	        	}           		
            	}else{
            			var pushDateName = "pushDate";  
            			
        	        	//$("#pushDate1").timepicker({hourMin:myMinHour});
        	        	
        	        	for(var i=pushFlag+1;i<=pushTimes;i++){
        	        		pushDateName = "pushDate"+i;
        	        		var pushDateText = '<input name="'+pushDateName+'" id="'+pushDateName+'" class="h2y_input_datetime" type="input" value="${findActivity.pushDate1}" /><br/><br/>';
        	        		$("#pushDateTd").append("<div id='pushDiv"+i+"'>时间"+i+"："+pushDateText+"</div>");
        	        		$("#"+pushDateName).timepicker({hourMin:myMinHour,minuteMin:myMinMinute});
        	        	}           			 	        	
            	}
            	pushFlag = pushTimes;
            	//$("input[type=input][name='pushDate']").timepicker({});
            });
            
            $("#uploadImgBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		uploadLimit:1,
            		multi:false
            	});
            });
            
           
            
        });
        
        
        function setDateChange(isSetDate){
        	
        	if(isSetDate){
        		
        		$("#ifSetDate").val(1);
        		$("#startDate").attr("disabled",false);
        		$("#endDate").attr("disabled",false);
        	}else{
        		$("#ifSetDate").val(0);
        		$("#startDate").attr("disabled",true);
        		$("#endDate").attr("disabled",true);
        	}
        }

        
 		
        function h2y_save() {
        	
			if(!Validator.form()) return;
			editor.sync();
			
            var queryString = $('#editform').serialize();
			
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            
          //设置推送时间
            var pushType = $("input[type=radio][name='pushType']:checked").val();
            if(2 == pushType || "2"==pushType){
            	var pushDateValue = $("#pushDate").val();
            	if(undefined == pushDateValue || '' == pushDateValue){
            		alert("推送时间不能为空！");
            		return;
            	}else{
            		var d2=new Date();//取今天的日期    
            		var d1 = new Date(Date.parse(pushDateValue));       
            		if(d1<d2){    
            		  alert("推送日期不能大于当前日期！");
            		  return;
            		}
            		
            	}
            }else{
            	var pushTimes =parseInt( $("input[type=radio][name='pushTimes']:checked").val());
                for(var i=1;i<=pushTimes;i++){
                	var pushDateValue = $("#pushDate"+i).val();
                	if(undefined == pushDateValue || '' == pushDateValue){
                		alert("推送时间"+i+"不能为空！");
                		return;
                	}
                }
            }
            
            
            isSubmiting = true;
            

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/findactivity/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_findactivity_init_htm") != null) {
							top.f_getframe("business_findactivity_init_htm").f_query();
						}
						top.f_delTab("findactivity_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_findactivity_init_htm") != null) {
							top.f_getframe("business_findactivity_init_htm").f_query();
						}             
                        top.f_delTab("findactivity_modify${findActivity.id}");
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
        
        //商品选择回调函数
        function h2y_goodsSelectCallBack(data){
        	goodsDataJson = data;
        	data_to_div2({divId:"goodslistdiv",data:data,inputName:"goodsId",rowSize:4});
        }
        
        
        //文件上传回调函数
        function h2y_fileUploadCallBack(data){
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"activity_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"activity_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
        	addDelClass();
        	$("#light_box_a").lightBox();
        }
        
        function addDelClass(){
			 $(".del").click(function() {  
				 if (!confirm("删除后不可恢复，确定删除图片吗？")) return;
		    	 $(this).parent().remove();    
			 });
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
		
		<input name="id" type="hidden" id="id" value="${findActivity.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					标题：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="title" type="text" id="title" class="h2y_input_long"
							value="${findActivity.title}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					级别：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="activityLevel" id="activityLevel" type="radio" dictcode="activity_level" value="${findActivity.activityLevel}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					类别：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="activityType" id="activityType" type="radio" dictcode="activity_type" value="${findActivity.activityType}"/>
				</td>
			</tr>
			
			
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td2">
					<input name="ifSetDate" type="hidden" id="ifSetDate" value="${findActivity.ifSetDate}" />
					<input id="if_set_date_check" type="checkbox" title="是否设置时间" class="hwtt_checkbox"/>
					<label for="if_set_date_check">设置时间</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input name="startDate" id="startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${findActivity.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
					~
					<input name="endDate" id="endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${findActivity.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否客户端推送：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="ifSetPush" id="ifSetPush" type="radio" initoption="0,否:1,是" value="${findActivity.ifSetPush}"/>					
				</td>
			</tr>
			
			<tr <c:if test="${ findActivity.ifSetPush == 0 || op =='add'}">style="display:none;"</c:if> id="pushTypeTr">
				<td class="h2y_table_label_td">
					推送类型：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="pushType" id="pushType" type="radio" initoption="1,推一天:2,设置推送时间" value="${findActivity.pushType}"/>					
				</td>
			</tr>
			
			<tr <c:if test="${ findActivity.ifSetPush == 0 || findActivity.pushType == 2 || op =='add'}">style="display:none;"</c:if> id="pushTimesTr">
				<td class="h2y_table_label_td">
					推送频数：
				</td>
				<td class="h2y_table_edit_td2">
					<h2y:input name="pushTimes" id="pushTimes" type="radio" dictcode="activity_push_times" value="${findActivity.pushTimes}"/>					
					<font style="color:red">(每天推送次数)</font>					
				</td>
			</tr>
			
			<tr <c:if test="${ findActivity.ifSetPush == 0 || findActivity.pushType == 2 || op =='add'}">style="display:none;"</c:if> id="pushDateTr">
				<td class="h2y_table_label_td">
					推送时间：
				</td>
				<td class="h2y_table_edit_td2" id="pushDateTd">
					<c:if test="${ findActivity.ifSetPush == 1 && op =='modify' && findActivity.pushTimes>0}">
						<c:forEach var="f" begin="1" end="${findActivity.pushTimes}">
							<div id='pushDiv${f}'>
								时间${f}：<input name="pushDate${f}" id="pushDate${f}" class="h2y_input_datetime" value="<c:if test="${f==1 }">${findActivity.pushDate1}</c:if><c:if test="${f==2 }">${findActivity.pushDate2}</c:if><c:if test="${f==3 }">${findActivity.pushDate3}</c:if><c:if test="${f==4 }">${findActivity.pushDate4}</c:if><c:if test="${f==5 }">${findActivity.pushDate5}</c:if><c:if test="${f==6 }">${findActivity.pushDate6}</c:if>"/><br/><br/>
							</div>
						</c:forEach>
					</c:if>
					
				</td>
			</tr>
			
			<tr <c:if test="${ findActivity.ifSetPush == 0 || op =='add' || findActivity.pushType != 2}">style="display:none;"</c:if> id="pushDateTr2">
				<td class="h2y_table_label_td">
					推送时间：
				</td>
				<td class="h2y_table_edit_td2" id="pushDateTd2">					
					<input name="pushDate" id="pushDate" class="h2y_input_datetime"  value="<fmt:formatDate value="${findActivity.pushDate}"  pattern="yyyy-MM-dd HH:mm"/>" />									
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					描述：
				</td>
				<td class="h2y_table_edit_td2">
					<textarea id="description" name="description" class="h2y_textarea">${findActivity.description}</textarea>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="上传图片" id="uploadImgBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="h2y_file_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					正文：
				</td>
				<td class="h2y_table_edit_td2">
					<textarea name="content" id="editor_id" class="h2y_editor_textarea">${findActivity.content}</textarea>
				</td>
			</tr>

			<tr style="display:none">
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="选择商品" id="selectGoodsBut"/>:
				</td>
				<td class="h2y_table_edit_td2">
					<div id="goodslistdiv" style="width:650px;word-wrap:break-word; word-break:break-all;"></div>
				</td>
			</tr>

			
			
		</table>
	</form>
</body>
</html>