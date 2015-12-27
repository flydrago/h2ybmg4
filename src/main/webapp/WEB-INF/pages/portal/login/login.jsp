<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>H2Y登陆页面</title>
    <base href="<%=basePath%>"/>
    <link href="<%=uiPath%>portal/login/css/reg-login.css" rel="stylesheet" type="text/css" />   
    <script src="<%=uiPath%>lib/jquery/jquery-1.5.2.min.js" type="text/javascript" ></script>

    <script type="text/javascript">
        $(function() {

            if(parent.document==document){
            }else{
                top.location.href="<%=basePath%>";
            }
            initPage();
        });

        function enterSubmit(e) {

            if (window.event)
                var keyPressed = window.event.keyCode; // IE
            else
                keyPressed = e.which; // Firefox
            if (keyPressed == 13) {
                save_form();
            }
        }


        function save_form(){
            save();
        }

        function save(){
        	if($("#_userName").val()==""){
        		alert("用户名不能为空！");
        		return;
        	}else {
        		if($("#_userName").val().indexOf("@")<= 0){
        			alert("用户名格式:账号@域名！");
        			return;
        		}else{
        			var userArray= new Array(); //定义一数组
        			userArray = $("#_userName").val().split("@");
        			if(undefined == userArray[1] || '' == userArray[1]){
        				alert("用户名格式:账号@域名,域名不能为空");
        				return;
        			}
        		}
        	}
        	
        	if($("#_password").val()==""){
        		alert("密码不能为空！");
        		return;
        	}
        	
        	

            $.post("portal/login/check.htm",$("#loginform").serialize(),function(data){
                var jsonReturn=eval("("+data+")");
                if(jsonReturn.code == 1){
                	location.href="<%=basePath%>portal/login/index.htm";
                }else{
                	alert(jsonReturn.msg);
                }
            });
        }
        /**
         * 更换验证码
         */
        function changCode(){
            $("#codeImgId").attr("src","<%=basePath%>codeimage.jsp?ram="+new Date());
        }

        /**
         *获取焦点
         */
        function initPage() {
            // if (document.getElementById) {
            var oInput = document.getElementById("_userName");//$("#_userName");
            //alert(oInput);
            oInput.onfocus = function() {
                if (this.value == this.defaultValue) {
                    this.value = "";
                }
            }

            oInput.onblur = function() {
                //alert(this.value);
                if (this.value == "") {
                    this.value = this.defaultValue;
                }
            }

            var passwordInput = document.getElementById("_password");//$("#_password");
            passwordInput.onfocus = function() {
                if (this.value == this.defaultValue) {
                    this.value = "";
                }
            }

            passwordInput.onblur = function() {
                if (this.value == "") {
                    this.value = this.defaultValue;
                }
            }
            //}
        }

        //加入收藏夹(ie7无法支持加入收藏,火狐的收藏有问题)
        function addBookmark() {
            var title = "高信达OA登录首页";
            var url = "<%=basePath%>login1.jsp";
            try{
                window.external.addfavorite(url,title);
            }catch (e){
                try{
                    window.sidebar.addPanel(title, url, "");
                }catch (e){
                    alert("加入收藏失败，请使用ctrl+d进行添加");
                }
            }
        }

        //设置为首页
        function setHome(){
            var url = "<%=basePath%>login1.jsp";
            //alert(url);
            if (document.all){
                document.body.style.behavior='url(#default#homepage)';
                document.body.setHomePage(url);
            }else if (window.sidebar){

                if(window.netscape){
                    try{
                        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                    }catch (e){
                        alert( "该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true" );
                        return;
                    }
                }
                if(window.confirm("你确定要设置"+url+"为首页吗？")==1){

                    var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                    prefs.setCharPref('browser.startup.homepage',url);
                }
            }
        }
    </script>
</head>
<body  onkeypress="enterSubmit(event)" style="background:#dedede;">
<div class="">
	<div class="jx-main">
		<div class="jx-content " >                    
			<div class="loginCon">
				 <form id="loginform" action="">
					<table cellpadding="10" cellspacing="0" border="0" width="100%">
						<tr class="login_head" >
							<td>后台管理系统</td>
						</tr>
						<tr>
							<td>用户名：<input id="_userName" name="account" type="text" class="login_inpName" placeholder="admin@zz"/></td>
						</tr>
						<tr><td ><font style="letter-spacing:0.5em;">密码</font>：<input id="_password" type="password" name='password' class="login_inpLock"/></td></tr>
						<tr><td  align="center"><input type="button" onclick="save()" class="login_iptBut" value="登陆"/></td></tr>
					</table>  
					</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>