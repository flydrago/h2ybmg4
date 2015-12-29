<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/engine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/dwr/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/dwr/interface/JDWRService.js"></script>
	
	<script type="text/javascript">
	 function getDataFromServer() {
		 JDWRService.getMsg({
          callback: getDataFromServerCallBack
        });
      }

      function getDataFromServerCallBack(dataFromServer) {
        alert(dwr.util.toDescriptiveString(dataFromServer, 3));
      }
      
      
 	 function addDemo() {
 		var myJSPerson = {
 				  name:"Fred Bloggs",
 				  age:42
 				};
		 JDWRService.addMsgDemo(myJSPerson);
      }
	</script>

  </head>
  
  <body>
  	<a href="javascript:getDataFromServer();" >Retrieve test data</a><br/>
  	
  	<a href="javascript:addDemo();" >add test data</a><br/>
  </body>
</html>
