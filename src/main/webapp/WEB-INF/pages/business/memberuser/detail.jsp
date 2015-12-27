<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>

	<style type="text/css">
		.goodstype_select{ 
			 border: 1px solid #aecaf0;
			 height:25px;
			 line-height: 25px;
		 }
		 .goodsLogoImg { max-width:600px;}
		 .goodsPicImg {
		 	width:240px;
		 	height:240px;
		 }
	</style>

    <script type="text/javascript">
	    $(function () {
	
	    	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
	        
	       
	    });
	    function h2y_refresh() {
	        document.location.reload();
	    }
        
    </script>

</head>

<body>
	<div position="top">
        <table width="100%" class="my_toptoolbar_td">
            <tr>
                <td id="my_toptoolbar_td">
                    <div class="l-toolbar">&nbsp;${mname}</div>
                </td>
                <td align="right" width="50%">
                    <div id="toptoolbar"></div>
                </td>
            </tr>
        </table>
    </div>
    
	<form name="editform" method="post" action="" id="editform">
		
		<table class="h2y_table">
					
			
			<tr>
				<td class="h2y_table_label_td">
					用户名：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.account}
				</td>
				<td class="h2y_table_label_td">
					性别：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="sex" id="sex" type="radio" initoption="0,女:1,男" value="${memberUser.sex}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					真实姓名：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.realName}
				</td>
				<td class="h2y_table_label_td">
					用户昵称：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.nickName}
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					出生日期：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.birDate}
				</td>
				<td class="h2y_table_label_td">
					身份证号：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.idCard}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					地址：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.zone}
				</td>
				<td class="h2y_table_label_td">
					详细地址：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.zoneDetail}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					邮箱：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.mailAdd}
				</td>
				<td class="h2y_table_label_td">
					联系电话：
				</td>
				<td class="h2y_table_edit_td">
					${memberUser.telPhone}
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					注册设备：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="regDevice" id="regDevice" type="radio" initoption="1,微信:2,安卓:3,IOS:4,PC" value="${memberUser.regDevice}" />	
				</td>
				<td class="h2y_table_label_td">
					注册来源：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="regSource" id="regSource" type="radio" initoption="1,平台:2,其他" value="${memberUser.regSource}" />	
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					注册日期：
				</td>
				<td class="h2y_table_edit_td">
					<fmt:formatDate value="${memberUser.createDate}"  pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td class="h2y_table_label_td">
					修改日期：
				</td>
				<td class="h2y_table_edit_td">
					<fmt:formatDate value="${memberUser.updateDate}"  pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					${memberUser.memo}
				</td>
			</tr>
			
			
			
		</table>
	</form>

</body>
</html>