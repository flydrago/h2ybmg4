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
	.advert_img { max-width:600px;}
</style>	
<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        
        $(function () {
        	
            if (op == "modify") {
                $("#tr_next").hide();               
            }else{
            	$("input[type=radio][name='activityType']:first").attr("checked",true);
            }
            
            
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#ord").ligerSpinner({type: 'int' ,height: 25});
            
        });


        function h2y_save() {       	       	
        	
			if(!Validator.form()) return;
			
			//活动类型
			var dataType = $("input[type=radio][name='dataType']:checked").val();
            var queryString = $('#editform').serialize();
            
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/commonactivity/save.htm", queryString, function (data) {

            	 var jsonReturn = eval("(" + data + ")");
                 if (op == "modify") {
                     if (jsonReturn.code == "1") {
                         alert(jsonReturn.msg);
                         parent.f_query();
                         frameElement.dialog.close();
                     } else {
                         alert(jsonReturn.msg);
                     }
                 } else {
                     if (jsonReturn.code == "1") {
                         alert(jsonReturn.msg);
                         var ifnext = $("input[@type=radio][name=next][checked]").val();
                         if (ifnext == 1) {
                             $("#title").val("");
                             $("#memo").val("");
                         } else {
                         	 parent.f_query();
                             frameElement.dialog.close();
                         }
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
        
        //比较时间
		function compareTime(startId, endId) { 
			
			var startDate = $("#"+startId).val();
			var endDate = $("#"+endId).val();
			if(startDate==null || endDate==null || startDate=="" || endDate==""){
				alert("请设置时间！");
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
	
	<form name="editform" method="post" action="" id="editform">
		
		<input name="id" type="hidden" id="id" value="${commonActivity.id}" />
		<input name="activityType" type="hidden" id="activityType" value="${commonActivity.activityType}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_dialog_table">
			
			<tr>
				<td class="h2y_table_label_td">
					标题：
				</td>
				<td class="h2y_table_edit_td">
					<input name="title" type="text" id="title" class="h2y_input_just"
							value="${commonActivity.title}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					显示类型：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="dataType" id="dataType" type="radio" initoption="0,商品列表:1,主题图片" value="${commonActivity.dataType}"/>	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					<input name="ord" id="ord" type="input" value="${commonActivity.ord}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否启用：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${commonActivity.status}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td">
					<input name="memo" id="memo" type="input" value="${commonActivity.memo}"  class="h2y_dialog_input_long"/>
				</td>
			</tr>
			
			<tr id="tr_next">
	            <td class="h2y_table_label_td">下一步:</td>
	            <td class="h2y_table_edit_td">
	                <h2y:input name="next" id="next" type="radio" initoption="1,继续添加:0,返回列表" value="1"/>
	            </td>
	        </tr>
		</table>
	</form>
</body>
</html>