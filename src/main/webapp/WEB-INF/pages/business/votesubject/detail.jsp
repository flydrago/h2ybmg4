<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-slide.min.js" type="text/javascript"></script>
<script src="<%=uiPath%>lib/jquery/jquery-ui/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<script type="text/javascript">
        $(function () {
        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        $("#h2y_promote_div").html(" ${promoteTitle}");
		
        	//图片处理
       if(${voteSubject.diskFileName!=null}){
         var json_str1 = "{\"id\":\"${voteSubject.id}\"}"; 
         $("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"voteSubject_file\"  value='"+json_str1+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=voteSubjectService&type=logo&id=${voteSubject.id}\" title=\"${voteSubject.fileName}\"><img class=\"voteSubject_img\" src=\"common/image/view.htm?fileBean=voteSubjectService&type=logo&id=${voteSubject.id}\"></a></div>");
         $("#light_box_a").lightBox();
                }	 
       if(${voteSubjectFile.diskFileName!=null}){
          var json_str2 = "{\"id\":\"${voteSubjectFile.id}\"}"; 
     	  $("#h2y_main_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"voteSubjectFile_file\"  value='"+json_str2+"'/><a id=\"light_box_b\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=voteSubjectService&type=file&id=${voteSubjectFile.id}\" title=\"${voteSubjectFile.fileName}\"><img class=\"voteSubject_img\" src=\"common/image/view.htm?fileBean=voteSubjectService&type=file&id=${voteSubjectFile.id}\"></a></div>");
     	  $("#light_box_b").lightBox();
        }
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
                <td id="my_toptoolbar_td"><div class="l-toolbar">&nbsp;${mname}</div></td>
                <td align="right" width="50%"><div id="toptoolbar"></div></td>
            </tr>
        </table>
    </div>
    
	<form name="editform" method="post" action="" id="editform">
		
		<input name="voteSubject.id" type="hidden" id="voteSubject_id" value="${voteSubject.id}" />
		<input name="voteSubject.promoteId" type="hidden" id="voteSubject_promoteId" value="${voteSubject.promoteId}" />
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">投票活动名称：</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					${voteSubject.subjectName}
				</td>
			</tr>
				<tr>
				<td class="h2y_table_label_td">投票活动描述：</td>
				<td class="h2y_table_edit_td2" colspan="3">${voteSubject.description}</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">开始时间:</td>
				<td class="h2y_table_edit_td2"><input
					name="voteSubject.startDate" id="voteSubject_startDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteSubject.startDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

				<td class="h2y_table_label_td">结束时间:</td>
				<td class="h2y_table_edit_td2"><input
					name="voteSubject.endDate" id="voteSubject_endDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteSubject.endDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

			</tr>

				<tr>
				<td class="h2y_table_label_td">创建时间:</td>
				<td class="h2y_table_edit_td2"><input
					name="voteSubject.createDate" id="voteSubject_createDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteSubject.createDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>
				<td class="h2y_table_label_td">修改时间:</td>
				<td class="h2y_table_edit_td2"><input
					name="voteSubject.updateDate" id="voteSubject_updateDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteSubject.updateDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

			</tr>
			
			<tr>
				<td class="h2y_table_label_td">选择推广活动:</td>
				<td class="h2y_table_edit_td2" colspan="3"><div id="h2y_promote_div"></div></td>
			</tr>

			<tr>
			<td class="h2y_table_label_td">上传logo图片:</td>
				<td class="h2y_table_edit_td2" colspan="3"><div id="h2y_file_div"></div></td>
			</tr>
			<tr>
			<td class="h2y_table_label_td">上传主页图片:</td>
				<td class="h2y_table_edit_td2" colspan="3"><div id="h2y_main_div"></div></td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">用户每日投票限制次数：</td>
				<td class="h2y_table_edit_td">${voteSubject.limitTimes}</td>
				<td class="h2y_table_edit_td" colspan="2" style="color:red">0为不限制</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">用户投票总限制次数：</td>
				<td class="h2y_table_edit_td">${voteSubject.totalTimes}</td>
				<td class="h2y_table_edit_td" colspan="2" style="color:red">0为不限制</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">是否启用：</td>
				<td class="h2y_table_edit_td">
				<h2y:input name="voteSubject.subjectStatus" id="voteSubject_subjectStatus" type="radio" initoption="0,不启用:1,启用" value="${voteSubject.subjectStatus}"  disabled="disabled"/></td>
			</tr>
			
				<tr id="subjectContent_tr">
				<td class="h2y_table_label_td">
					投票主题规则：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<textarea name="voteSubjectDetail.subjectRule" id="voteSubjectDetail_subjectRule" disabled="disabled" class="h2y_textarea">${voteSubjectDetail.subjectRule} </textarea>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>