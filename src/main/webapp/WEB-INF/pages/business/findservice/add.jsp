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
	      
	 .androidLogoImg { 
	 	max-width:500px;
	 }
	 
	 .iosLogoImg { 
	 	max-width:500px;
	 }
	 
	 .wechatLogoImg { 
	 	max-width:500px;
	 }
</style>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}"
        var form = null;
        var logoType = "";
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            if (op == "modify") {
                $("#tr_next").hide();
                
               
            }
            
            $("#date_cross_table").hide();
            
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
			
			
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/findservice/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_findservice_init_htm") != null) {
							top.f_getframe("business_findservice_init_htm").h2y_refresh();
						}
						top.f_delTab("business_findservice_add${findService.parentId}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_findservice_init_htm") != null) {
							top.f_getframe("business_findservice_init_htm").h2y_refresh();
						}             
                        top.f_delTab("business_findservice_modify${findService.id}");
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
		
		<input name="id" type="hidden" id="id" value="${findService.id}" />
		<input name="parentId" type="hidden" id="parentId" value="${findService.parentId}" />
		<input name="dataType" type="hidden" id="dataType" value="${findService.dataType}" />
		<input name="serviceType" type="hidden" id="serviceType" value="${findService.serviceType}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<input name="clickEvent" type="hidden" id="clickEvent" value="3" />
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td"> 
					<input name="name" id="name" class="h2y_input_just" type="input" value="${findService.name}"/>
				</td>
				<td class="h2y_table_label_td">
					编码：
				</td>
				<td class="h2y_table_edit_td">
					<c:if test="${op == 'add' }">
						<input name="serviceCode" id="serviceCode" class="h2y_input_just" type="input" value="${findService.serviceCode}"/>
					</c:if>
					<c:if test="${op == 'modify' }">
						${findService.serviceCode}
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					<input name="ord" id="ord" type="input" value="${findService.ord}" />
				</td>
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${findService.status}"/>
				</td>
			</tr>
			
			
			
			<tr>
				<td class="h2y_table_label_td">
					登陆可见：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<h2y:input name="isLogin" id="isLogin" type="radio" initoption="0,否:1,是" value="${findService.isLogin}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${findService.memo}"  class="h2y_input_long"/>
				</td>
			</tr>
			
			
			
		</table>
		
	</form>
</body>
</html>