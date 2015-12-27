<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>H2Y登陆页面</title>
    <base href="<%=basePath%>"/>
    <link href="<%=uiPath%>portal/login/css/base.css" type="text/css" rel="stylesheet"/>
    <link href="<%=uiPath%>portal/login/css/reg-login.css" rel="stylesheet" type="text/css"/>

    <script src="<%=uiPath%>lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

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
        		alert("账号不能为空！");
        		return;
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
<body onkeypress="enterSubmit(event)">
<div class="jx-wrap">
    <!--头部-->
    <div class="jx-top-header">
        <div class="jx-top-head">
            <div class="jx-logo">
                <p style="background:url(<%=uiPath%>/portal/login/img/left.jpg) no-repeat;"
                   class="jx-index">
                    <a target="_blank" href="http://www.jiuxian.com/"></a>
                </p>
                <a class="jx-topic"><img src="<%=uiPath%>/portal/login/img/head_right.jpg"/></a>
            </div>
            <div class="jx-head-nav">
                <div class="jx-head-icon">
                    <i class="jx-jt"></i>
                </div>
                <div class="jx-head-pop">
                    <ul>
                        <li><a href="http://help.jiuxian.com/view-3-117.htm#delivery_01" target="_blank">配送说明</a></li>
                        <li><a href="http://help.jiuxian.com/view-4-120.htm#pay_1" target="_blank">支付方式</a></li>
                        <li><a href="http://help.jiuxian.com/view-5-123.htm#bz_1" target="_blank">售后服务</a></li>
                        <li><a href="http://member.jiuxian.com/company.htm" target="_blank">企业客户</a></li>
                    </ul>
                </div>
            </div>
            <div class="jx-reg">欢迎来到H2Y！</div>
        </div>
    </div>

    <form id="loginform" action="">
        <!--内容-->
        <div class="jx-main-bg">
            <div class="jx-main">
                <div class="jx-content clearfix" style="height:450px;">
                    <div class="loginSideAd">

                        <img width="600" height="450"
                             src="<%=uiPath%>/portal/login/img/main.jpg"/>
                    </div>
                    <div class="loginCon">
                        <div class="loginTit clearfix">
                            <h1>欢迎登录</h1>
                            <!-- -->
                        </div>
                        <div class="loginType loginUsual">
                        	 <div class="Frame enterFrame">
                               <select name="unitId" id="_unitId">
		                          <c:forEach var="c" items="${unitList}">
		                              <option value="${c.ID}">${c.UNIT_NAME}</option>
		                          </c:forEach>
		                     </select>
                            </div>
                            <div class="Frame enterFrame">

								<em class="userIconBox">
								<i class="userIcon"></i>
								</em>
                                <input id="_userName" class="text" type="text" name="account"/>

                                <div class="mistakeTip" style="display:none">请输入用户名/邮箱/手机号</div>
                                <div class="mistakeTip02" style="display:none">请输入用户名/邮箱/手机号</div>
                            </div>
                            <div class="Frame enterFrame">

                                <input maxlength="20" id="_password" class="text" type="password" name='password'/>
                                <div class="mistakeTip" style="display:none">请输入密码</div>
                                <div class="mistakeTip02" style="display:none">请输入密码</div>
                            </div>
                            <div class="loginBtn">
                                <input type="button" id="Button1" onclick="save()" value="登&nbsp;&nbsp;录"/>
                            </div>
                            <div class="moreChoice clearfix">
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </form>

    <!--内容结束-->
    <!--尾部开始-->
    <div class="jx-footer">
        <div class="jx-foot">
        </div>
        <div class="copybot">

        </div>
    </div>
    <!--尾部结束-->
    <!--99click(统计)-->
</div>
</body>
</html>