<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/h2y-tags" prefix="h2y"%>
<%
    String path = request.getContextPath();
    if(!path.endsWith("/")){
        path=path+"/";
    }
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    String uiPath=basePath+"resources/h2y_ui/";
    String skinName="Aqua";
    String toolbar_bg=uiPath+"dg_ui/skins/"+skinName+"/images/buttons/panel-toolbar.jpg";
%>
