<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>

<style type="text/css">
	.advert_img { max-width:600px;}
</style>

<script type="text/javascript">
       var Validator = null;
       var isSubmiting = false;
       var op = "${op}";
       var form = null;
       var unitSortDataJson = null;

       $(function () {
       	
       		$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
           form = $("form").ligerForm();
           
           
            $(${fileListDataJson}).each(function(){
            	
	       		if($("#h2y_file_div_"+this.fileType).attr("_flag")=="image"){
	       			$("#h2y_file_div_"+this.fileType).append("<div><a class=\"light_box_a_"+this.fileType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=sysUnitsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  class=\"advert_img\"  src=\"common/image/view.htm?fileBean=sysUnitsService&id="+this.id+"\"></a></div>");
	               	$(".light_box_a_"+this.fileType).lightBox();
	       		}else{
	       			$("#h2y_file_div_"+this.fileType).append("<div><a href=\"javascript:downloadFile('"+this.id+"');\" title=\""+this.fileName+"\">"+this.fileName+"</a></div>");
	       		}
       		});
            
       });
       
       
       function downloadFile(id){
			
			var url="<%=basePath%>common/file/down.htm?fileBean=sysUnitsService&id="+id;
		    location.href = url;
		}

      
       
       function h2y_save() {

           var queryString = $('#editform').serialize();
           
           //if($("#unitRoleId option").length == 0){
           //		alert("请维护单位角色！");
           //	return;
           // }

           if (isSubmiting) {
               alert("表单正在提交，请稍后操作！");
               return;
           }
           isSubmiting = true;
           
           <%--注意该处url可能不符合你的要求，请注意修改--%>
           $.post("sys/units/save.htm", queryString, function (data) {

               var jsonReturn = eval("(" + data + ")");
               if (jsonReturn.code == "1") {
                   alert(jsonReturn.msg);
                   if (top.f_getframe("sys_units_checklist_htm") != null) {
						top.f_getframe("sys_units_checklist_htm").f_query();
				   }else if (top.f_getframe("sys_units_checkshoplist_htm") != null) {
						top.f_getframe("sys_units_checkshoplist_htm").f_query();
				   }else if (top.f_getframe("sys_units_checkunitlist_htm") != null) {
						top.f_getframe("sys_units_checkunitlist_htm").f_query();
				   }else if (top.f_getframe("sys_units_provincechecklist_htm") != null) {
						top.f_getframe("sys_units_provincechecklist_htm").h2y_refresh();
				   }
	   			   top.f_delTab("sys_units_check_htm_op_check_id_${sysUnits.id}");
               } else {
                   alert(jsonReturn.msg);
               }
               isSubmiting = false;
           });
       }
       
       function h2y_refresh() {
           document.location.reload();
       }

       
       function h2y_unitSortSelectCallBack(data){
       	unitSortDataJson = data;     	
       	
       	var sortHtml = "";
       	$(data).each(function(){
       		
       		if(sortHtml==""){
       			sortHtml = this.text;
       		}else{
       			sortHtml += "</br>"+this.text;
       		}
       	});
       	$("#sortHtml_show").html(sortHtml);
       }
   </script>

<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>


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
		<div>
			<input type="hidden" name="op" value="check" /> 
			<input type="hidden" name="id" value="${sysUnits.id}" />
			<input type="hidden" name="parentId" value="${sysUnits.parentId}" />
		</div>
		<table class="h2y_table">
		
			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.unitName}
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">简称:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.shortName}
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">域名:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.unitDomain}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">人数:</td>
				<td class="h2y_table_edit_td2">
				${sysUnits.userCount}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">地址:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.unitAddress}
				</td>
			</tr>
			
			<td class="h2y_table_label_td">appId:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.s3ucode}
				</td>
			</tr>
			
			<td class="h2y_table_label_td">appSecret:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.s3uname}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">法人:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.legalPerson}
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">法人手机:</td>
				<td class="h2y_table_edit_td2">
					${sysUnits.legalPersonMobile}
				</td>
			</tr>
			
			<c:forEach var="c" items="${fileTypeList}">
				<tr>
					<td class="h2y_table_label_td" >
						${c.name}:
					</td>
					<td class="h2y_table_edit_td2">
						<div id="h2y_file_div_${c.type}" _flag="${c.flag}"></div>
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td class="h2y_table_label_td">审核:</td>
				<td class="h2y_table_edit_td2">
					<select name="unitStatus" id="unitStatus" ltype="select">
                         <option value="pass">通过</option>
                         <option value="unpass">不通过</option>
                    </select>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">角色:</td>
				<td class="h2y_table_edit_td2">
					<select name="unitRoleId" id="unitRoleId">
                         <c:forEach var="c" items="${roleList}">
                             <option value="${c.ID}" id="${c.ID}">${c.ROLE_NAME}</option>
                         </c:forEach>
                    </select>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>