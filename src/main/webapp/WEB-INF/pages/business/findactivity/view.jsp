<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <title>小达快报</title>
	
	<meta charset="utf-8">
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="小达快报">
    <meta name="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 ,maximum-scale=1, user-scalable=no">
    <link href="<%=uiPath%>bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="<%=uiPath%>jquery/jquery-1.11.2.min.js"></script>
      <script src="<%=uiPath%>bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
      
      
      <style type="text/css">
        html{
            height: 100%;
            background-color: #eee;
        }
        html,body{
            min-height: 100%;
        }
               
    </style>
    
   
  </head>
  
  <body>

	
	<div class="container-fluid" style="padding:2px;width:60%">
		<div class="row-fluid" >
			<div class="span12" >															
																			
				<div class="thumbnail">										

					<img src="<%=basePath%>common/image/view.htm?fileBean=findActivityService&id=${findActivity.id}">
					<div class="caption">
						<c:if test="${findActivity.ifSetDate == 1}">
							<h4>活动时间：</h4>
							<p>						
								<fmt:formatDate value="${findActivity.startDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>~
								<fmt:formatDate value="${findActivity.endDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
						</c:if>
						
						<p>${findActivity.content }</p>
						
					</div>
				</div>
											
			</div>
		</div>
	</div>
	
 </body>
  	
      
       
     
  </body>
</html>
