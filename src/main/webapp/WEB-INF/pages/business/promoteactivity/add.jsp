<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>${mname}</title>
		<%@ include file="../../include/top_list.jsp" %>
		<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
		<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
		<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
		
		
		<script type="text/javascript">
			var Validator = null;
	        var isSubmiting = false;
	        var op = "${op}";
	        var callBackFlag = "1";
	        
	        
			$(function() {
				KindEditor.ready(function(K) {
	        		 contentEditor = K.create("#promoteActivityDetail_promoteRule", {
	        		 items:['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
	        		        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	        		        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	        		        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	        		        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	        		        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',  'table', 'hr', 'emoticons', 'pagebreak',
	        		        'anchor', 'link', 'unlink', '|', 'about']
	                 });
	       		});
	        	
	        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});	        	
	            
	        	$("#promoteActivity_claimLimit").ligerSpinner({type: 'int' ,height: 25,width:140,minValue:0});
	            $("#promoteActivity_claimNum").ligerSpinner({type: 'int' ,height: 25,width:140,minValue:0});
	            $("#promoteActivity_ord").ligerSpinner({type: 'int' ,height: 25,width:140,minValue:0});
            
				
				//验证属性设置
	            $.metadata.setType("attr", "validate");
	            //验证信息
	            ${validationRules}
	            //设置默认验证样式
	            Validator = deafultValidate($("#editform"));
				
				$("#promoteActivity_startDate").datetimepicker({});
		        $("#promoteActivity_endDate").datetimepicker({});
		        
		        $("#uploadBut").click(function(){
	            	openFileUploadDialog({
	            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
	            		uploadLimit:1,
	            		multi:false
	            	});
	            });

		      		      
		        if(op == 'modify'){
		        	//图片处理
		        	if(${promoteActivity.diskFileName!=null}){
	                	
	                	var json_str = "{\"id\":\"${promoteActivity.id}\"}";
	             		$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"promoteActivity_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=promoteActivityService&id=${promoteActivity.id}\" title=\"${promoteActivity.fileName}\"><img class=\"activity_img\" src=\"common/image/view.htm?fileBean=promoteActivityService&id=${promoteActivity.id}\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
	             		addDelClass();
	             		$("#light_box_a").lightBox();
	                }
		        }	
				
		   });
			
	      
	        function deleteRowGoods(index){
				$("#data_tr_"+index).remove();
			}
	      
			function h2y_save(){
				

				if(!Validator.form()) return; 
				//同步富文本编辑框数据
	            contentEditor.sync();
				
	            var promoteRule = $("#promoteActivityDetail_promoteRule").val();
	            if(undefined == promoteRule || '' == promoteRule){
	            	alert("活动细则不能为空！");
	            	return;
	            }

	            if(!compareTime("promoteActivity_startDate", "promoteActivity_endDate")) return;
				
				var queryString = $('#editform').serialize(); 

				if(isSubmiting){
					alert("表单正在提交，请稍后操作！");
					return;
				}		
				isSubmiting = true;
				
				<%--注意该处url可能不符合你的要求，请注意修改--%>
				$.post("business/promoteactivity/save.htm", queryString,function(data){
					var op=$("#op").val();
					var jsonReturn=eval("("+data+")");
					var promoteActivityId = "${promoteActivity.id}";
					
					
					if (op == "add") {
	                    if (jsonReturn.code == "1") {
	                        alert(jsonReturn.msg);
	                        if (top.f_getframe("business_promoteactivity_init_htm") != null) {
								top.f_getframe("business_promoteactivity_init_htm").f_query();
							}
							top.f_delTab("business_promoteactivity_add");
	                    } else {
	                        alert(jsonReturn.msg);
	                    }
	                } else {
	                    if (jsonReturn.code == "1") {
	                        alert(jsonReturn.msg);
	                        if (top.f_getframe("business_promoteactivity_init_htm") != null) {
								top.f_getframe("business_promoteactivity_init_htm").f_query();
							}             
	                        top.f_delTab("business_promoteactivity_modify_"+promoteActivityId);
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
			
			
			//文件上传回调函数
	        function h2y_fileUploadCallBack(data){
	        	if(data==null || data.length==0){
	        		return;
	        	}
	        	
	        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
	    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"promoteActivity_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"activity_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
	        	addDelClass();
	        	$("#light_box_a").lightBox();
	        }
			
			
			function addDelClass(){
				 $(".del").click(function() {  
					 if (!confirm("删除后不可恢复，确定删除图片吗？")) return;
			    	 $(this).parent().remove();    
				 });
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
  <div id="layout1" style="width: 100%">
	    <div position="top">
			<table width="100%"  class="my_toptoolbar_td">
				<tr>
				<td id="my_toptoolbar_td">&nbsp;${mname}</td>
				<td align="right" width="500"><div id="toptoolbar"></div></td>
				</tr>
				</table>
			</div>
		</div>

		<form name="editform" method="post" action="" id="editform">
			
			<input type="hidden" name="promoteActivity.id" value="${promoteActivity.id}" />
			<input type="hidden" name="promoteActivityDetail.id" value="${promoteActivityDetail.id}" />
			<input type="hidden" name="op" id="op" value="${op}" />
			
			
			<table class="h2y_table" >
				<tr>
					<td class="h2y_table_label_td">
						推广标题:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
					 <input id="promoteActivity_title" name="promoteActivity.title" type="text"
							value="${promoteActivity.title}" class="h2y_input_long" />
					</td>					
				</tr>
				
				
				<tr>
					<td class="h2y_table_label_td">
						开始时间:
					</td>
					<td class="h2y_table_edit_td2">							
						<input name="promoteActivity.startDate" id="promoteActivity_startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${promoteActivity.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
					</td>
					
					<td class="h2y_table_label_td">
						结束时间:
					</td>
					<td class="h2y_table_edit_td2">
						<input name="promoteActivity.endDate" id="promoteActivity_endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${promoteActivity.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
					</td>
					
				</tr>
				

				<tr>
					<td class="h2y_table_label_td">
						排序:
					</td>
					<td class="h2y_table_edit_td2">
					 <input id="promoteActivity_ord" name="promoteActivity.ord" type="text"
							value="${promoteActivity.ord}" class="h2y_input_just" />
					</td>

					<td class="h2y_table_label_td">
						状态：
					</td>
					<td class="h2y_table_edit_td">
						<h2y:input name="promoteActivity.promoteStatus" id="promoteStatus" type="radio" initoption="0,启用:1,不启用" value="${promoteActivity.promoteStatus}"/>
					</td>
					
				</tr>
				
				
				<tr>
					<td class="h2y_table_label_td">
						认领数量上限:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
					 <input id="promoteActivity_claimLimit" name="promoteActivity.claimLimit" type="text"
							value="${promoteActivity.claimLimit}" class="h2y_input_just" />
					</td>
					
				</tr>
				
				
				<tr>
					<td class="h2y_table_label_td">
						<input type="button" value="图片上传" class="button" id="uploadBut"/>:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
						<div id="h2y_file_div"></div>
					</td>
				</tr>

				<tr>
					<td class="h2y_table_label_td">
						描述:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
							<textarea id="promoteActivity_description" name="promoteActivity.description" class="h2y_textarea">${promoteActivity.description}</textarea>
					</td>
					
				</tr>

				<tr>
					<td class="h2y_table_label_td">
						备注:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
						<textarea id="promoteActivity_memo" name="promoteActivity.memo" class="h2y_textarea">${promoteActivity.memo}</textarea>
					</td>
					
				</tr>

				<tr>
					<td class="h2y_table_label_td">
						活动细则:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">							
						<textarea name="promoteActivityDetail.promoteRule" id="promoteActivityDetail_promoteRule" class="h2y_editor_textarea">${promoteActivityDetail.promoteRule}</textarea>
					</td>
					
				</tr>
				
				
				
			      
				
			</table>
			
			
		</form>
	
 </body>
</html>