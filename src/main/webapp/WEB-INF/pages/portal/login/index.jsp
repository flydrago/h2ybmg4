<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>H2Y</title>
    <base href="<%=basePath%>"/>
    <link href="<%=uiPath%>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
	<link href="<%=uiPath%>portal/login/css/neiye.css" rel="stylesheet" type="text/css">

    <script src="<%=uiPath%>lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="<%=uiPath%>lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script src="<%=uiPath%>common/js/common.js" type="text/javascript"></script>
	
    <script type="text/javascript">

        var tab = null;
        var tree = null;
        var tabItems = [];
        var tabcount=6;

        $(function () {

            //布局
            $("#layout1").ligerLayout({ leftWidth: 190, height: '100%', heightDiff: -34, space: 4, onHeightChanged: f_heightChanged });

            var height = $(".l-layout-center").height();

            //Tab
            $("#framecenter").ligerTab({
                height: height,
                showSwitchInTab: true,
                showSwitch: true
            });

            $(".l-link").hover(function () {
                $(this).addClass("l-link-over");
            }, function () {
                $(this).removeClass("l-link-over");
            });
            
            //树
            $("#tree1").ligerTree({
                //data : indexdata,
                data:${menuData},
                //url: "portal/login/getMenu.htm",
                checkbox: false,
                isExpand: false, 
                idFieldName: "id",
                parentIDFieldName: "pid",
                textFieldName: "text",
                onSelect: function (node) {
                    if (!node.data.url) return;
                    var tabid = $(node.target).attr("tabid");
                    /*
                    if (!tabid) {
                        tabid = new Date().getTime();
                        $(node.target).attr("tabid", tabid)
                    }*/
                    tabid = generateTabId(node.data.url);
                    if (!tabid) {
                        tabid = new Date().getTime();
                        $(node.target).attr("tabid", tabid)
                    }
                    f_addTab(tabid, node.data.text, node.data.url);
                }
            });

            tab = liger.get("framecenter");
            tree = liger.get("tree1");
            $("#pageloading").hide();
        });
        function f_heightChanged(options) {
            if (tab)
                tab.addHeight(options.diff);
        }

        /*
         根据url生成 tabID
         */
        function generateTabId(url) {
            var tabid = url.replace(new RegExp("[/:\.\?\=\&,]","gm"),"_");
            return tabid;
        }

        function f_addTab(tabid,text, url){
            if(tabid==null) {
                tabid = generateTabId(url);
            }
            //alert(url);
            tab.addTabItem({ tabid : tabid,text: text, url: url });
        }


        //删除指定标签
        function f_delTab(tabid)
        {
            tab.removeTabItem(tabid);
        }
        //删除活动的标签
        function f_delActivityTab()
        {
            tab.removeTabItem(tab.getSelectedTabItemID());
        }
        //获取活动的标签ID
        function f_getSelectedTabItemID()
        {
            return tab.getSelectedTabItemID();
        }
        //刷新执行标签
        function f_refreshTab(tabid)
        {
        	alert(1);
            tab.reload(tabid);
        }
        /*
		注销
		*/
		function logout() {
		  	if(confirm("确定要退出系统？")){
				window.location.href = "<%=basePath%>portal/login/loginOut.htm";
			}
		}
        
        
        function changePwd(){
        	
        	 <%--注意该处url可能不符合你的要求，请注意修改--%>
             var src = "<%=basePath%>sys/user/changePwd.htm";
             $.ligerDialog.open({
                 name:"portal_login_changePwd",
                 title:  "修改密码",
                 height: 340,
                 url: src,
                 width: 650,
                 showMax: true,
                 showToggle: true,
                 showMin: true,
                 isResize: true,
                 modal: true,
                 buttons: [
                     { text: '确定', onclick: function (item, dialog) {
                         f_getframe("portal_login_changePwd").h2y_save();
                     } },
                     { text: '取消', onclick: function (item, dialog) {
                         dialog.close();
                     } }
                 ]
             });
        }

    </script>
</head>
<body style="padding:0px;background:#EAEEF5;">
	
	<div  class="l-topmenu" >
	    <div class="l-topmenu-logo"><p style="padding-left:10px;color:#0a5cb9;font-size:20px;line-height:40px;font-weight:bold;">${sysUnits.unitName}</p></div>
	    <div class="l-topmenu-welcome">
	    	<span class="l-link2" >${sysUser.userName}&nbsp;&nbsp;您好，欢迎回来！</span>
	    	<span class="l-link2" style="color:#006fb4;">${deptMentInfo}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:changePwd();" class="l-link2"><img src="<%=uiPath%>portal/login/img/iconLock.png"/>&nbsp;修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <a href="javascript:logout();" class="l-link2"><img src="<%=uiPath%>portal/login/img/iconExit.png"/>&nbsp;注销</a>
	    </div>
	</div>


	<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; ">
	    <div position="left" style="height:100%;overflow:auto;" title="功能菜单">
	        <ul id="tree1" style="margin-top:3px;">
	    </div>
	    <div position="center" id="framecenter">
	        <div tabid="home" title="主页" style="height:300px">
	            <iframe frameborder="0" name="home" id="home" src="portal/login/desktop.htm"></iframe>
	        </div>
	    </div>
	</div>
	<div  style="height:32px; line-height:32px; text-align:center;">
        ${sysUnits.unitName}后台管理
    </div>
	<div style="display:none"></div>
</body>
</html>
