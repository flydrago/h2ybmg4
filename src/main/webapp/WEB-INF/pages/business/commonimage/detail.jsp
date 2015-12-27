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
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            h2y_fileModify("android","${commonImage.androidFileName}","${commonImage.id}");
            h2y_fileModify("ios","${commonImage.iosFileName}","${commonImage.id}");
            h2y_fileModify("wechat","${commonImage.wechatFileName}","${commonImage.id}");
        });
        
        function h2y_refresh() {
            document.location.reload();
        }
        
        
        //修改回显图片
 		function h2y_fileModify(logoType,fileName,id){
 			
 			if(""==fileName) return;
 			
        	var json_str = "{\"id\":\""+id+"\"}";
        	$("#"+logoType+"Logo_div").html("<a id=\"file_lightbox_"+logoType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=commonImageService&id="+id+"&type="+logoType+"\"><img class=\""+logoType+"LogoImg\" src=\"common/image/view.htm?fileBean=commonImageService&id="+id+"&type="+logoType+"\"></a>");
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
				<td class="h2y_table_edit_td2" colspan="3"> 
					${commonImage.logoName}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td"> 
					<fmt:formatDate value="${commonImage.startDate}"  pattern="yyyy-MM-dd HH:mm"/>~<fmt:formatDate value="${commonImage.endDate}"  pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td class="h2y_table_label_td">
					是否默认：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isDefault" id="isDefault" type="radio"  readonly="readonly" initoption="0,否:1,是" value="${commonImage.isDefault}"/>
				</td>
			</tr>
			
			<tr>
			
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					${commonImage.ord}
				</td>
				
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" readonly="readonly" initoption="0,启用:1,不启用" value="${commonImage.status}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${commonImage.memo}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					安卓Logo:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="androidLogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					苹果Logo:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="iosLogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					微信Logo:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="wechatLogo_div"></div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>