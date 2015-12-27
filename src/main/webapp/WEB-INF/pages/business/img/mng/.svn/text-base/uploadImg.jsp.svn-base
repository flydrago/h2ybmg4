<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../include/taglibs.jsp"%>
<%@ include file="../../../include/top_list.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="<%=uiPath%>common/js/common.js"></script>

<style type="text/css">
	 .androidLogoImg { 
	 	max-width:500px;
	 }
	 
</style>

<script type="text/javascript">
		var unitId = ${unitId};
        var Validator = null;
        var form = null;
        
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});

        	$("#date_cross_table").hide();

        	//验证属性设置
            $.metadata.setType("attr", "validate");
            
        	//验证信息
            ${validationRules}
            
        	//设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
          	//打开 选取模块  对话窗
        	$("#selectModule").click(function(){
        		openModuleDialog();
        	});
          
        	//打开 选取用途  对话窗
        	$("#selectUsage").click(function(){
        		var moduleId = $("#moduleId").val();
        		
        		if(moduleId != ""){
	        		openUsageDialog(moduleId);
        		}else{
        			alert("请先选择模块信息");
        		}
        		
        	});
        	
            $("#uploadImg").click(function(){
            	openImgUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:false
            	});
            });
        });
        
        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if( isBlank( $("input[name='FileData']").val() ) ){
				alert("请上传图片！");
				return;
			}
			
			var postMap = {};
			postMap.unitId = unitId;
			postMap.imgName = $("#imgName").val();
			postMap.moduleId = $("#moduleId").val();
			postMap.usageId = $("#usageId").val();
			postMap.memo = $("#memo").val();
			
			postMap.imgFileData = $("#fileData").val();

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("img/mng/saveImg.htm", {postData:JSON.stringify(postMap)}, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
				if (jsonReturn.resultFlag == "1") {
					alert(jsonReturn.resultMsg);
                    if (top.f_getframe("img_mng_init_htm") != null) {
						top.f_getframe("img_mng_init_htm").h2y_refresh();
					}
					top.f_delTab("upload_img");
				} else {
                	alert(jsonReturn.resultMsg);
				}
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
        
        
        function h2y_imgUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
        	
        	$("#imgInfo").html("<input type=\"hidden\" name=\"FileData\" id='fileData'  value='"+json_str+"'/><a id=\"file_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img class=\"LogoImg\" src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a href=\"javascript:deletePic();\">删除</a>");
        	$("#file_lightbox").lightBox();
        }
        
        //修改回显图片
 		function h2y_fileModify(fileName,id){
 			
 			if(""==fileName) return;
 			
        	var json_str = "{\"id\":\""+id+"\"}";
        	$("#imgInfo").html("<input type=\"hidden\" name=\"FileData\" id='fileData'  value='"+json_str+"'/><a id=\"file_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=commonImageService&id="+id+"><img class=\"LogoImg\" src=\"common/image/view.htm?fileBean=commonImageService&id="+id+"></a><a href=\"javascript:deletePic();\">删除</a>");
        	$("#file_lightbox").lightBox();
        }
        
        function deletePic(){
        	$("#imgInfo").html("");
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
					<input name="imgName" id="imgName" class="h2y_input_long" type="input" value=""/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="选择模块" class="button" id="selectModule"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input type="hidden" name="moduleId" id="moduleId">
					
					<div id="moduleInfoDiv">
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="选择用途" class="button" id="selectUsage"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input type="hidden" name="usageId" id="usageId">
					
					<div id="usageInfoDiv">
					</div>
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
			
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="上传图片" class="button" id="uploadImg"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="imgInfo">
					</div>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>