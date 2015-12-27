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

        var picid = ${picId};
        $(function () {
        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        
        	$(picid).each(function(){
        		 $("#h2y_file_div").append("<div class=\"pic_img\"><img class=\"VoteCandidatePic_img\" src=\"${activityURL}/common/image/view.htm?fileBean=voteCandidateService&id="+this.ID+"\"></div>");
        	});
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
				<td class="h2y_table_label_td">候选人编码：</td>
				<td class="h2y_table_edit_td ">${voteCandidate.userNo}</td>
				<td class="h2y_table_label_td">候选人姓名：</td>
				<td class="h2y_table_edit_td">${voteCandidate.userName}</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">候选人得票数：</td>
				<td class="h2y_table_edit_td2">${voteCandidate.userVotes}</td>

				<td class="h2y_table_label_td">候选人电话：</td>
				<td class="h2y_table_edit_td2">${voteCandidate.userPhone}</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">用户账号：</td>
				<td class="h2y_table_edit_td2" colspan="3">${voteCandidate.memberAccount}</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">参加时间:</td>
				<td class="h2y_table_edit_td2"><input
					name="voteCandidate.createDate" id="voteCandidate_createDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteCandidate.createDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

				<td class="h2y_table_label_td">最后一次得票时间:</td>
				<td class="h2y_table_edit_td2"><input
					name="voteCandidate.updateDate" id="voteCandidate_updateDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteCandidate.updateDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

			</tr>


			<tr>
				<td class="h2y_table_label_td">展示图片:</td>
				<td class="h2y_table_edit_td2" colspan="3">

					<div id="h2y_file_div"></div>
				</td>
			</tr>


			<tr>
				<td class="h2y_table_label_td">是否显示：</td>
				<td class="h2y_table_edit_td"><h2y:input
						name="voteCandidate.status" id="voteCandidate_status" type="radio"
						initoption="0,显示:1,不显示" value="${voteCandidate.status}"
						disabled="disabled" /></td>
			</tr>

			<tr id="subjectContent_tr">
				<td class="h2y_table_label_td">竞选宣言：</td>
				<td class="h2y_table_edit_td2" colspan="3"><textarea
						name="voteCandidateDetail.candidaterDesc"
						id="voteCandidateDetail_candidaterDesc" disabled="disabled"
						class="h2y_textarea">${voteCandidateDetail.candidaterDesc} </textarea>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>