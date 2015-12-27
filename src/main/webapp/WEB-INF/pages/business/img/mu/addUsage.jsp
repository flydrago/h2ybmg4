<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../include/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../../include/top_list.jsp"%>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<script type="text/javascript">
        var Validator = null;
        var form = null;
        var logoType = "";
        var moduleId = ${moduleId};
        $(function () {
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            $("#date_cross_table").hide();
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
        });
        
        function h2y_save() {
        	
			if(!Validator.form()) return;

			var postMap = {};
			postMap.usageName = $("#usageName").val();
			postMap.usageCode = $("#usageCode").val();
			postMap.width=$("#width").val();
			postMap.height=$("#height").val();
			postMap.memo = $("#memo").val();
			postMap.moduleId = moduleId;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("img/mu/saveUsage.htm", {postData:JSON.stringify(postMap)}, function (data) {
            	
            	var resJson = eval("("+data+")");
            	
            	if(resJson.resultFlag == 1 || resJson.resultFlag == "1"){
    				alert(resJson.resultMsg);
    				if (top.f_getframe("modules_usage"+moduleId) != null) {
						top.f_getframe("modules_usage"+moduleId).h2y_refresh();
					}
					top.f_delTab("usage_add");
    			}else{
    				alert(resJson.resultMsg);
    			}
                
            });
        }
        
        function h2y_refresh() {
            document.location.reload();
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
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					用途名称：
				</td>
				<td class="h2y_table_edit_td"> 
					<input name="usageName" id="usageName" class="h2y_input_just" type="input" value=""/>
				</td>
				<td class="h2y_table_label_td">
					用途编码：
				</td>
				<td class="h2y_table_edit_td">
					<input name="usageCode" id="usageCode" class="h2y_input_just" type="input" value=""/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					图片宽度：
				</td>
				<td class="h2y_table_edit_td"> 
					<input name="width" id="width" class="h2y_input_just" type="input" value=""/>
				</td>
				<td class="h2y_table_label_td">
					图片高度：
				</td>
				<td class="h2y_table_edit_td">
					<input name="height" id="height" class="h2y_input_just" type="input" value=""/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value=""  class="h2y_input_long"/>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>