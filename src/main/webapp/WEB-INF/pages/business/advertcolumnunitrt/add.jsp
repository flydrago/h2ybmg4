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
        var form = null;
        $(function () {

            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            $("#startDate").datetimepicker();
            $("#endDate").datetimepicker();
        });


        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if($("#columnId :selected").length==0){
				alert("请选择栏位！");
				return;
			}
			
			if(!compareTime("startDate","endDate")){
				return;
			}

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/advertcolumnunitrt/save.htm", queryString, function (data) {

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
                       	parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
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

	<form name="editform" method="post" action="" id="editform">
		
		<input name="id" type="hidden" id="id" value="${advertColumnUnitRt.id}" />
		<input name="unitId" type="hidden" id="unitId" value="${advertColumnUnitRt.unitId}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_dialog_table">
			
			<tr>
				<td class="h2y_table_label_td">
					广告栏：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="columnId" id="columnId" type="select" css="h2y_select_just" initoption="${columnList}" value="${advertColumnUnitRt.columnId}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					开放时间：
				</td>
				<td class="h2y_table_edit_td">
					<input name="startDate" id="startDate" type="input" value="<fmt:formatDate value="${advertColumnUnitRt.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"  class="h2y_input_datetime"/>~
					<input name="endDate" id="endDate" type="input" value="<fmt:formatDate value="${advertColumnUnitRt.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"  class="h2y_input_datetime" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${advertColumnUnitRt.status}"/>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>