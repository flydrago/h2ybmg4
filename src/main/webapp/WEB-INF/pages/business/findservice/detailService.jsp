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

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            
            h2y_fileModify("android","${findService.androidFileName}","${findService.id}");
            h2y_fileModify("ios","${findService.iosFileName}","${findService.id}");
            h2y_fileModify("wechat","${findService.wechatFileName}","${findService.id}");
        });
        
        function h2y_refresh() {
            document.location.reload();
        }
        
        //修改回显图片
 		function h2y_fileModify(logoType,fileName,id){
 			
 			if(""==fileName) return;
 			
        	var json_str = "{\"id\":\""+id+"\"}";
        	$("#"+logoType+"Logo_div").html("<input type=\"hidden\" name=\""+logoType+"FileData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+logoType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=findServiceService&id="+id+"&type="+logoType+"\"><img class=\""+logoType+"LogoImg\" src=\"common/image/view.htm?fileBean=findServiceService&id="+id+"&type="+logoType+"\"></a><a href=\"javascript:deletePic('"+logoType+"');\">删除</a>");
        	$("#file_lightbox_"+logoType).lightBox();
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
				<td class="h2y_table_edit_td"> 
					${findService.name}
				</td>
				<td class="h2y_table_label_td">
					编码：
				</td>
				<td class="h2y_table_edit_td"> 
					${findService.serviceCode}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					${findService.ord}
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
					活动来源：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<h2y:input name="clickEvent" id="clickEvent" type="radio" dictcode="find_service_type" value="${findService.clickEvent}" onchange="javascript:showUrlInput(this.value)"/>
				</td>
			</tr>
			<c:if test="${ findService.clickEvent == 1}">
				<tr>
					<td class="h2y_table_label_td">
						来源url：
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
						${findService.urlParams}
					</td>
				</tr>
			</c:if>
			
			
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
					参数2：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${findService.memo}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="安卓Logo" class="button" id="androidLogoUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="androidLogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="苹果Logo" class="button" id="iosLogoUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="iosLogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="微信Logo" class="button" id="wechatLogoUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="wechatLogo_div"></div>
				</td>
			</tr>
			
		</table>
		
	</form>
</body>
</html>