<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.h2y.bmg.util.SysBaseUtil" %>
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
  	<div style="text-align:center;">
  		<p>扫描下方二维码在手机浏览器预览</p>
  		<img  alt="扫描二维码预览" src="${qrCodeUrl }?width=200&height=200&url=${xdkbUrl}">
  	</div>
    
  </body>
</html>
