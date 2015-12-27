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

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}"
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            if (op == "modify") {
                
            }else{
            	
            	$("input[type=radio][name='sourceCode']:first").attr("checked",true);
            }
            
            $("#double1").ligerSpinner({type: 'float' ,height: 25,width:$("#double1").width(),minValue:0});
            $("#double2").ligerSpinner({type: 'float' ,height: 25,width:$("#double1").width(),minValue:0});
            
            
            //优惠劵来源
            sourceCodeChange($("input[type=radio][name='sourceCode']:checked").val());
            $("input[type=radio][name='sourceCode']").change(function(){
            	sourceCodeChange(this.value);
            	if($("#sourceName").val()==""){
            		$("#sourceName").val($(this).next("label").text());
            	}
            });
           
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
        	$("#startDate").datetimepicker({});
            $("#endDate").datetimepicker({});
            
        });
        
        
        function sourceCodeChange(value){
        	
        	var double1Manager = $("#double1").ligerGetSpinnerManager();
        	var double2Manager = $("#double2").ligerGetSpinnerManager();
        	
        	if(value=="fullAmount"){//满额优惠
        		double1Manager.setEnabled(1);
        		$("#double1_text").html("满赠额度");
        	}else{
        		double1Manager.setDisabled(1);
        		$("#double1_text").html("浮点数据1");
        	}
        	double2Manager.setDisabled(1);
        }
        
        
        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if(!compareTime("startDate", "endDate")) return;
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/couponssource/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_couponssource_init_htm") != null) {
							top.f_getframe("business_couponssource_init_htm").f_query();
						}
						top.f_delTab("business_couponssource_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_couponssource_init_htm") != null) {
							top.f_getframe("business_couponssource_init_htm").f_query();
						}             
                        top.f_delTab("business_couponssource_modify${couponsSource.id}");
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
		
		<input name="id" type="hidden" id="id" value="${couponsSource.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					<input name="sourceName" id="sourceName" class="h2y_input_long" type="input" value="${couponsSource.sourceName}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					来源类型：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<h2y:input name="sourceCode" id="sourceCode" type="radio" dictcode="sourceCode" value="${couponsSource.sourceCode}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<input name="startDate" id="startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${couponsSource.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>~
					<input name="endDate" id="endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${couponsSource.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${couponsSource.memo}"  class="h2y_input_long"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<span id="double1_text">浮点数据1</span>：
				</td>
				<td class="h2y_table_edit_td">
					<input name="double1" id="double1" type="input" value="${couponsSource.double1}"  class="h2y_input_just"/>
				</td>
				<td class="h2y_table_label_td">
					<span id="double2_text">浮点数据2</span>：
				</td>
				<td class="h2y_table_edit_td">
					<input name="double2" id="double2" type="input" value="${couponsSource.double2}"  class="h2y_input_just"/>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>