<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="<%=uiPath%>lib/uploadify/uploadify.css"/>

<script src="<%=uiPath%>lib/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<!-- 解决corome崩溃的问题 -->
<%-- <script src="<%=uiPath%>lib/uploadify/jquery.uploadify.js?f=<%=System.currentTimeMillis()%>" type="text/javascript"></script> --%>
<title>文件上传</title>
	<script type="text/javascript">
		var i = 0;
		
        $(function () {
        	 <%-- document.write("<script type='text/javascript' "  
     	            + "src='<%=uiPath%>lib/uploadify/jquery.uploadify.js?" + new Date()  
     	            + "'></s" + "cript>");   --%>
     	    <%-- 解决corome崩溃的问题 --%>
        	var _doc = document.getElementsByTagName('head')[0];
			var script = document.createElement('script');
			script.setAttribute('type', 'text/javascript');
			script.setAttribute('src', '<%=uiPath%>lib/uploadify/jquery.uploadify.js?' + new Date());
			_doc.appendChild(script);
			script.onload = script.onreadystatechange = function() {
				if (!this.readyState || this.readyState == 'loaded'
						|| this.readyState == 'complete') {
					
		        	/*
		        	var fileTypeExts = "${fileTypeExts}"=="" ? "*.*" : "${fileTypeExts}";
		        	var multi = "${multi}";
		        	if(multi=="" || multi=="true"){
		        		multi = false;
		        	}*/
		        	var paraJson = ${paraJson};
		        	var fileTypeExts = paraJson.fileTypeExts!=null ? paraJson.fileTypeExts : "*.*";
		        	var fileSizeLimit = paraJson.fileSizeLimit!=null ? paraJson.fileSizeLimit : 0;
		        	var multi = paraJson.multi!=null ? paraJson.multi : true;
		        	var uploadLimit = paraJson.uploadLimit!=null ? paraJson.uploadLimit : 0;
		        	
					$("#file_upload").uploadify({
						'swf': "<%=uiPath%>lib/uploadify/uploadify.swf",
						'uploader': "<%=basePath%>common/upload/doUpload.htm",
				        'method':"post",
				        'buttonText':"上传文件",
				        'fileTypeExts':fileTypeExts,
				        'fileSizeLimit':fileSizeLimit,
				        'multi':multi,
				        'uploadLimit':uploadLimit,
				        'onUploadComplete' : function(file) {
				        	//alert(JSON.stringify(file));
				        },  
				        'onUploadSuccess' : function(file, data, response) {
				        	//alert(JSON.stringify(data));
				        	var jsonReturn = eval("(" + data + ")");
				        	addFileToTr(jsonReturn);
				        },
				        'onSelect': function(file) { 
				        	//alert(JSON.stringify(file));
				        },  
				        'onError': function(event, queueID, fileObj) {
				        	
				        }  
					});
				}
				script.onload = script.onreadystatechange = null;
			};
        });
        
        function addFileToTr(data){
        	$(data).each(function(){
        		var json_str = "{fileName:\""+this.fileName+"\",saveName:\""+this.saveName+"\",savePath:\""+this.savePath+"\",fileSize:\""+this.fileSize+"\"}";
        		$("#sucess_queue_table").append("<tr id=\"sucess_queue_tr"+i+"\">"+
        				"<td><input type=\"hidden\" name=\"fileJsonData\" value='"+json_str+"'/>"+this.fileName+"</td>"+
        				"<td width=\"25px\" align=\"center\"><a class=\"del\" href=\"javascript:void(0);\" onclick=\"deleteFile("+i+")\"></a></td>"+
        				"</tr>");
        		i++;
        	});
        }
        
        function deleteFile(id){
        	$("#sucess_queue_tr"+id).remove();
        }
        
        
        function h2y_returnData(){
        	var fileJsonList = "";
        	$("#sucess_queue_table [name='fileJsonData']").each(function(){
        		if(fileJsonList==""){
        			fileJsonList=this.value;
        		}else{
        			fileJsonList+=","+this.value;
        		}
        	});
        	fileJsonList = "["+fileJsonList+"]";
        	return eval("(" + fileJsonList + ")");
        }
    </script>
</head>

<body>

	<form>  
		<div id="queue"></div>
   		<input type="file" name="myfiles" id="file_upload"  multiple="true"/>
	</form>  
	
	<div id="sucess_queue"  width="100%">
		<table width="100%" id="sucess_queue_table">
		</table>
	</div>
</body>
</html>