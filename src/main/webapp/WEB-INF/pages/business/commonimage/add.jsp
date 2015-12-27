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
                
                h2y_fileModify("android","${commonImage.androidFileName}","${commonImage.id}");
                h2y_fileModify("ios","${commonImage.iosFileName}","${commonImage.id}");
                h2y_fileModify("wechat","${commonImage.wechatFileName}","${commonImage.id}");
                
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
            
            
            $("#startDate").datetimepicker({});
            $("#endDate").datetimepicker({});
            
            
            if(${commonImage.isDefault==1}){
            	$("#startDate").attr("disabled",true);
        		$("#endDate").attr("disabled",true);
            }
            
            $("input[type=radio][name='isDefault']").change(function(){
            	
            	if(this.value == 1){
            		$("#startDate").attr("disabled",true);
            		$("#endDate").attr("disabled",true);
            	}else{
            		$("#startDate").attr("disabled",false);
            		$("#endDate").attr("disabled",false);
            	}
            });
            $("#ord").ligerSpinner({type: 'int' ,height: 25});
        });
        
        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if(isBlank($("input[name='androidFileData']").val()) 
					&& isBlank($("input[name='iosFileData']").val()) 
					&& isBlank($("input[name='wechatFileData']").val())){
				alert("图片Logo至少上传一个！");
				return;
			}
			
			if($("input[type=radio][name='isDefault']:checked").val()==0){
				
				if(!compareTime("startDate", "endDate")) return;				
			}
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/commonimage/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_commonimage_init_htm") != null) {
							top.f_getframe("business_commonimage_init_htm").f_query();
						}
						top.f_delTab("business_commonimage_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_commonimage_init_htm") != null) {
							top.f_getframe("business_commonimage_init_htm").f_query();
						}             
                        top.f_delTab("business_commonimage_modify${commonImage.id}");
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
        	$("#"+logoType+"Logo_div").html("<input type=\"hidden\" name=\""+logoType+"FileData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+logoType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=commonImageService&id="+id+"&type="+logoType+"\"><img class=\""+logoType+"LogoImg\" src=\"common/image/view.htm?fileBean=commonImageService&id="+id+"&type="+logoType+"\"></a><a href=\"javascript:deletePic('"+logoType+"');\">删除</a>");
        	$("#file_lightbox_"+logoType).lightBox();
        }
        
        function deletePic(logoType){
        	$("#"+logoType+"Logo_div").html("");
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
		
		<input name="id" type="hidden" id="id" value="${commonImage.id}" />
		<input name="typeCode" type="hidden" id="typeCode" value="${commonImage.typeCode}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">
					名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					<input name="logoName" id="logoName" class="h2y_input_long" type="input" value="${commonImage.logoName}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td"> 
					<input name="startDate" id="startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${commonImage.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>~
					<input name="endDate" id="endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${commonImage.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
				<td class="h2y_table_label_td">
					是否默认：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="isDefault" id="isDefault" type="radio" initoption="0,否:1,是" value="${commonImage.isDefault}"/>
				</td>
			</tr>
			
			<tr>
			
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					<input name="ord" id="ord" type="input" value="${commonImage.ord}" />
				</td>
				
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${commonImage.status}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${commonImage.memo}"  class="h2y_input_long"/>
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