<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>

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
        var editor;
        var logoType = "";
        $(function () {
             KindEditor.ready(function(K) {
                     editor = K.create("#editor_id", {
                    	 uploadJson : '<%=basePath%>${imUpUrl}?type=fwb'
                     });
             });

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
                
            h2y_fileModify("android","${advertSubject.androidFileName}","${advertSubject.id}");
            h2y_fileModify("ios","${advertSubject.iosFileName}","${advertSubject.id}");
            h2y_fileModify("wechat","${advertSubject.wechatFileName}","${advertSubject.id}");
        });
        
        
        function h2y_refresh() {
            document.location.reload();
        }
        
        //修改回显图片
 		function h2y_fileModify(logoType,fileName,id){
 			
 			if(""==fileName) return;
 			
        	var json_str = "{\"id\":\""+id+"\"}";
        	$("#"+logoType+"Logo_div").html("<input type=\"hidden\" name=\""+logoType+"FileData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+logoType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=advertSubjectService&id="+id+"&type="+logoType+"\"><img class=\""+logoType+"LogoImg\" src=\"common/image/view.htm?fileBean=advertSubjectService&id="+id+"&type="+logoType+"\"></a>");
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
		
		<input name="id" type="hidden" id="id" value="${advertSubject.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					主题名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="subjectName" type="text" id="subjectName" class="h2y_input_long"
							value="${advertSubject.subjectName}" />	
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					主题类型：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="subjectType" disabled="disabled" id="subjectType" type="radio" initoption="0,商品列表:1,商品详细:2,宣传页面" value="${advertSubject.subjectType}"/>
				</td>
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status"  disabled="disabled" type="radio" initoption="0,启用:1,不启用" value="${advertSubject.status}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${advertSubject.memo}"  class="h2y_input_long"/>
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
			
			
			<tr>
				<td class="h2y_table_label_td">
					页面内容：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${advertSubject.subjectContent}
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>