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
        var contentEditor = null;
        var logoType = "";
        $(function () {

        	KindEditor.ready(function(K) {
        		 contentEditor = K.create("#subjectContent", {
              	 uploadJson : '<%=basePath%>${imUpUrl}?type=fwb'
               });
       		});
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            if (op == "modify") {
                $("#tr_next").hide();
                
                h2y_fileModify("android","${commonSubject.androidFileName}","${commonSubject.id}");
                h2y_fileModify("ios","${commonSubject.iosFileName}","${commonSubject.id}");
                h2y_fileModify("wechat","${commonSubject.wechatFileName}","${commonSubject.id}");
                
            }else{
            	$("input[type=radio][name='subjectType']:first").attr("checked",true);
            }
            
            $("#date_cross_table").hide();
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#androidLogoUploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:false
            	});
            	logoType = "android";
            });
            
            
            $("#iosLogoUploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:false
            	});
            	logoType = "ios";
            });
            
            $("#wechatLogoUploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:false
            	});
            	logoType = "wechat";
            });
            
        });
        
        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if(isBlank($("input[name='androidFileData']").val()) 
					&& isBlank($("input[name='iosFileData']").val()) 
					&& isBlank($("input[name='wechatFileData']").val())){
				alert("图片Logo至少上传一个！");
				return;
			}
			
			if($("input[type=radio][name='subjectType']:checked").val()!=2){
				contentEditor.html("");
			}
			//同步富文本编辑框数据
            contentEditor.sync();
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/commonsubject/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_commonsubject_init_htm") != null) {
							top.f_getframe("business_commonsubject_init_htm").f_query();
						}
						top.f_delTab("business_commonsubject_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_commonsubject_init_htm") != null) {
							top.f_getframe("business_commonsubject_init_htm").f_query();
						}             
                        top.f_delTab("business_commonsubject_modify${commonSubject.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
               
                isSubmiting = false;
            });
        }
        
        function isBlank(data){
        	
			if(""==data || undefined==data || null==data){
				return true;
			}
			return false;
        }
        
        function h2y_refresh() {
            document.location.reload();
        }
        
        
        function h2y_fileUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	
        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
        	
        	$("#"+logoType+"Logo_div").html("<input type=\"hidden\" name=\""+logoType+"FileData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+logoType+"\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img class=\""+logoType+"LogoImg\" src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a href=\"javascript:deletePic('"+logoType+"');\">删除</a>");
        	$("#file_lightbox_"+logoType).lightBox();
        }
        
        //修改回显图片
 		function h2y_fileModify(logoType,fileName,id){
 			
 			if(""==fileName) return;
 			
        	var json_str = "{\"id\":\""+id+"\"}";
        	$("#"+logoType+"Logo_div").html("<input type=\"hidden\" name=\""+logoType+"FileData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+logoType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=commonSubjectService&id="+id+"&type="+logoType+"\"><img class=\""+logoType+"LogoImg\" src=\"common/image/view.htm?fileBean=commonSubjectService&id="+id+"&type="+logoType+"\"></a><a href=\"javascript:deletePic('"+logoType+"');\">删除</a>");
        	$("#file_lightbox_"+logoType).lightBox();
        }
        
        function deletePic(logoType){
        	$("#"+logoType+"Logo_div").html("");
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
		
		<input name="id" type="hidden" id="id" value="${commonSubject.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					<input name="subjectName" id="subjectName" class="h2y_input_long" type="input" value="${commonSubject.subjectName}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					主题类型：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="subjectType" id="subjectType" type="radio" initoption="0,商品列表:1,商品详细:2,商品宣传" value="${commonSubject.subjectType}"/>
				</td>
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${commonSubject.status}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${commonSubject.memo}"  class="h2y_input_long"/>
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
			
			<tr id="subjectContent_tr">
				<td class="h2y_table_label_td">
					宣传内容：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<textarea name="subjectContent" id="subjectContent" class="h2y_editor_textarea">${commonSubject.subjectContent}</textarea>
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>