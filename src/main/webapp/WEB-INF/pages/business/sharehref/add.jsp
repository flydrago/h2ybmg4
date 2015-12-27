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
	        
	        
			$(function() {
				
	        	
	        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});	            
	            $("#shareHref_ord").ligerSpinner({type: 'int' ,height: 25,width:140,minValue:0});
            
				
				//验证属性设置
	            $.metadata.setType("attr", "validate");
	            //验证信息
	            ${validationRules}
	            //设置默认验证样式
	            Validator = deafultValidate($("#editform"));
		        
		        $("#uploadBut").click(function(){
	            	openFileUploadDialog({
	            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
	            		uploadLimit:1,
	            		multi:false
	            	});
	            });

		        
		      		      
		        if(op == 'modify'){
		        	//图片处理
		        	if(${shareHref.diskFileName!=null}){
	                	
	                	var json_str = "{\"id\":\"${shareHref.id}\"}";
	             		$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"shareHref_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=shareHrefService&id=${shareHref.id}\" title=\"${shareHref.fileName}\"><img class=\"activity_img\" src=\"common/image/view.htm?fileBean=shareHrefService&id=${shareHref.id}\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
	             		addDelClass();
	             		$("#light_box_a").lightBox();
	                }
		        }else{
		        	$("input[type=radio][name='dataType']:first").attr("checked",true);
		        }	
		        
				
			  });

	      
	        function deleteRowGoods(index){
				$("#data_tr_"+index).remove();
			}
	      
			function h2y_save(){

				if(!Validator.form()) return;           
				var queryString = $('#editform').serialize(); 
				
				if('undefined' == $("input[type=hidden][name='shareHref_file']").val() || undefined == $("input[type=hidden][name='shareHref_file']").val()){
	            	alert("请上传图片！");
	            	return;
	            }

				if(isSubmiting){
					alert("表单正在提交，请稍后操作！");
					return;
				}		
				isSubmiting = true;
				
				<%--注意该处url可能不符合你的要求，请注意修改--%>
				$.post("business/sharehref/save.htm", queryString,function(data){
					var op=$("#op").val();
					var jsonReturn=eval("("+data+")");
					var shareHrefId = "${shareHref.id}";
					
					
					if (op == "add") {
	                    if (jsonReturn.code == "1") {
	                        alert(jsonReturn.msg);
	                        if (top.f_getframe("business_sharehref_init_htm") != null) {
								top.f_getframe("business_sharehref_init_htm").f_query();
							}
							top.f_delTab("business_sharehref_add");
	                    } else {
	                        alert(jsonReturn.msg);
	                    }
	                } else {
	                    if (jsonReturn.code == "1") {
	                        alert(jsonReturn.msg);
	                        if (top.f_getframe("business_sharehref_init_htm") != null) {
								top.f_getframe("business_sharehref_init_htm").f_query();
							}             
	                        top.f_delTab("business_sharehref_modify_"+shareHrefId);
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
	    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"shareHref_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"shareHref_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
	        	addDelClass();
	        	$("#light_box_a").lightBox();
	        }
			
			
			function addDelClass(){
				 $(".del").click(function() {  
					 if (!confirm("删除后不可恢复，确定删除图片吗？")) return;
			    	 $(this).parent().remove();    
				 });
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
			
			<input type="hidden" name="id" value="${shareHref.id}" />				
			<input type="hidden" name="op" id="op" value="${op}" />
			
			
			<table class="h2y_table" >
				<tr>
					<td class="h2y_table_label_td">
						链接名称:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
					 <input id="shareHref_name" name="name" type="text"
							value="${shareHref.name}" class="h2y_input_long" />
					</td>					
				</tr>
				
				
				<tr>
					<td class="h2y_table_label_td">
						链接标题:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">
					 <input id="shareHref_title" name="title" type="text"
							value="${shareHref.title}" class="h2y_input_long" />
					</td>					
				</tr>
				
				<tr>
					<td class="h2y_table_label_td">
						排序:
					</td>
					<td class="h2y_table_edit_td2">
					 <input id="shareHref_ord" name="ord" type="text"
							value="${shareHref.ord}" class="h2y_input_just" />
					</td>

					<td class="h2y_table_label_td">
						状态：
					</td>
					<td class="h2y_table_edit_td">
						<h2y:input name="hrefStatus" id="hrefStatus" type="radio" initoption="0,启用:1,不启用" value="${shareHref.hrefStatus}"/>
					</td>
					
				</tr>
				
				<tr>
					<td class="h2y_table_label_td">
						数据类型:
					</td>
					<td class="h2y_table_edit_td2" colspan="3">			
						<h2y:input name="dataType" id="shareHref_dataType" type="radio" dictcode="promoteHrefParams" value="${shareHref.dataType}"/>
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
							<textarea id="shareHref_description" name="description" class="h2y_textarea">${shareHref.description}</textarea>
					</td>
					
				</tr>


				
			      
				
			</table>
			
			
		</form>
	
 </body>
</html>