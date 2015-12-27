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

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            //优惠劵来源
            sourceCodeChange($("input[type=radio][name='sourceCode']:checked").val());
           
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
        });
        
        
        function sourceCodeChange(value){
        	
        	if(value=="fullAomount"){//满额优惠
        		$("#double1_text").html("满赠额度");
        	}else{
        		$("#double1_text").html("浮点数据1");
        	}
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
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					${couponsSource.sourceName}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					来源类型：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<h2y:input name="sourceCode" id="sourceCode" type="radio" disabled="disabled" dictcode="sourceCode" value="${couponsSource.sourceCode}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<fmt:formatDate value="${couponsSource.startDate}"  pattern="yyyy-MM-dd HH:mm"/> ~
					<fmt:formatDate value="${couponsSource.endDate}"  pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${couponsSource.memo}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<span id="double1_text">浮点数据1</span>：
				</td>
				<td class="h2y_table_edit_td">
					${couponsSource.double1}
				</td>
				<td class="h2y_table_label_td">
					<span id="double2_text">浮点数据2</span>：
				</td>
				<td class="h2y_table_edit_td">
					${couponsSource.double2}
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>