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
	.zone_code_select{ 
		 border: 1px solid #aecaf0;
		 height:25px;
		 line-height: 25px;
	 }
	 
	.advert_img { max-width:600px;}
</style>

<script type="text/javascript">
              var Validator = null;
              var isSubmiting = false;
              var select_index = 0;
              var account = ${account};
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'' },
        	                                       { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
                	
                  	var prefix = "";/* ${zonePrefix} */
                 	var prefix_array = prefix.split("_");
                 	if(prefix_array.length>0){
                 		hwtt_do_change($("#frist_zone_select"));
                 		$("#frist_zone_select").find("option[_zoneid='"+prefix_array[select_index]+"']").attr("selected",true);
/* 						alert("option[_zoneid='"+prefix_array[select_index]+"'].value()"); */
                 		if(prefix_array.length>1){
                 			modifyselect(prefix_array);
                 		}
                 	}           	
                	
                	hwtt_do_change($("#frist_zone_select"));
                	
                	
        });
    
        function hwtt_do_change(obj){
         
			obj.change(function(){				
				$(this).nextAll().remove();				
				addselect(this.value,$(this));
				$("#_zoneCode").val(this.value);			
			});
		}
		
        //设置区域编码的值
        function hwtt_zone_change(obj){      	
        	$(obj).nextAll().remove();       		
        	addselect(obj.value,$("#zone_select"));        	
        	$("#_zoneCode").val(obj.value);
        }
        
		
		function addselect(code,obj){
			if(""==code || undefined==code){
				return;
			}
			
			$.post("sys/units/getZone.htm",{op:"code",code:code},function(data){
				
				var jsonReturn=eval("("+data+")");
				var selectHtml = "";
				$(jsonReturn).each(function(){
					selectHtml += "<option value=\""+this.CODE+"\">"+this.NAME+"</option>";
				});
				if(selectHtml!=""){
					selectHtml = "<select class=\"zone_code_select\"><option value=\"\"> </option>"+selectHtml+"</select>";
					$("#zone_code_td").append(selectHtml);					
					hwtt_do_change(obj.next());
				}
			}); 
		}
		
		
		function modifyselect(array){
			
			$.post("sys/units/getZone.htm",{op:"id",code:array[select_index]},function(data){
				var jsonReturn=eval("("+data+")");
				var selectHtml = "";
				$(jsonReturn).each(function(){
					if(this.ID == array[select_index+1]){
						selectHtml += "<option value=\""+this.CODE+"\" selected=\"selected\">"+this.NAME+"</option>";
					}else{
						selectHtml += "<option value=\""+this.CODE+"\">"+this.NAME+"</option>";
					}
				});
				if(selectHtml!=""){
					selectHtml = "<select class=\"zone_code_select\" id=\"zone_code_select_"+select_index+"\"><option value=\"\"> </option>"+selectHtml+"</select>";
					$("#zone_code_td").append(selectHtml);
					hwtt_do_change($("#zone_code_select_"+select_index));
				}
				select_index++;
				if(select_index<(array.length-1)){
					modifyselect(array);
				}
			}); 
		}



        function h2y_save() {
        	
        	//区域为空使用父级区域  例如济源
        	var zoneCode = $("#_zoneCode").val();
        	if(undefined == zoneCode || 'undefined' == zoneCode || '' == zoneCode){
        		$("#_zoneCode").val('${parentZoneCode }');
        	}
            var queryString = $('#editform').serialize();
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/memberuser/zoneUpdateSave.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                    if (jsonReturn.code == "1") {
                    	
                        alert(jsonReturn.msg);
                        top.f_delTab("business_memberuser_zoneUpdate${account}");
                      
                    } else {
                        alert(jsonReturn.msg);
                        top.f_delTab("business_memberuser_zoneUpdate${account}");
                    }
                isSubmiting = false;
            });
        }
        
        function h2y_refresh() {
            document.location.reload();
        }
    </script>

<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>


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
		<div>
			<input type="hidden" name="account" id="account" value="${account}" />
			<input name="zoneCode" type="hidden" id="_zoneCode" value="${sysUnits.zoneCode}" />
		</div>
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">用户账号：</td>
				<td class="h2y_table_edit_td2">${account}</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">用户当前所在区域：</td>
				<td class="h2y_table_edit_td2">${zoneName }</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">目标调度区域:</td>
				<td id="zone_code_td" class="h2y_table_edit_td2"><select
					class="zone_code_select" id="zone_select"
					<c:if test="${!empty zoneList}"> validate="{required:true,messages:{required:'请输入内容'}}" </c:if>
					onchange="hwtt_zone_change(this)">
						<option value=""></option>
						<c:forEach var="c" items="${zoneList}">
							<option _zoneid="${c.ID}" value="${c.CODE}"
								<c:if test="${op == 'modify' && sysUnits.zoneCode == c.CODE }">selected="selected"</c:if>>${c.NAME}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">温馨提示：</td>
				<td class="h2y_table_edit_td2">修改该用户的区域时，与用户存在三级推荐关系的用户区域信息也会同时更改。</td>
			</tr>
		</table>
	</form>

</body>
</html>