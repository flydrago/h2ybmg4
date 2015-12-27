<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>H2Y登陆</title>
    <base href="<%=basePath%>"/>
    <link href="<%=uiPath%>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

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

            $.post("portal/login/check.htm",$("#form1").serialize(),function(data){
                alert(data);
                alert(data.msg);
                location.href="portal/login/index.htm";
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

    <style type="text/css">
        body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    </style>

</head>
<body>
<div>
    <form action="loginCheck.action" id="form1">

        <table cellpadding="0" width="500px" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">用户名:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                    <input class="text" id="_userName" name="userName"  value="登录用户名" tabindex="1" maxlength="30" style="color: rgb(153, 153, 153); font-weight: normal; ime-mode: disabled; position: relative;padding-left:37px" type="text"/>
                </td>
            </tr>

            <tr>
                <td align="right" class="l-table-edit-td" valign="top">密码:</td>
                <td align="left" class="l-table-edit-td" style="width:160px">
                    <input type="password" class="text" id="_password" name="password"  value="" style="color: rgb(153, 153, 153); font-weight: normal; position: relative;  padding-left:37px" tabindex="2"  maxlength="30" />
               </td>
            </tr>

            <tr>
                <td align="center" colspan="2">
                    <input type="button" value="登陆" id="Button1" onclick="save()" class="l-button l-button-submit" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
