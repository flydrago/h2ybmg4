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
        var Validator = null;
        var isSubmiting = false;
        var callBackFlag = "1";
        var op = "${op}";
        
        var uploadFlag = 0;//0:logo 1:主页图片
        
        var contentEditor = null;
        $(function () {
        	
        	KindEditor.ready(function(K) {
        		 contentEditor = K.create("#voteSubjectDetail_subjectRule", {
       		 items:['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
       		        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
       		        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
       		        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
       		        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
       		        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',  'table', 'hr', 'emoticons', 'pagebreak',
       		        'anchor', 'link', 'unlink', '|', 'about']
                }); 
      		}); 

        $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
	    //时间格式
        $("#voteSubject_startDate").datetimepicker({});
		$("#voteSubject_endDate").datetimepicker({});
		//验证属性设置
		$.metadata.setType("attr", "validate");
		//验证信息
		${validationRules}
		//设置默认验证样式
		Validator = deafultValidate($("#editform"));
		//推广活动弹出窗口
		$("#selectPromoteBut").click(function(){
			openPromoteCommonSelectDialog({selectType:"radio"});
        });
        $("#uploadBut").click(function(){
        	openFileUploadDialog({
        		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
        		uploadLimit:1,
        		multi:false
        	});
        	
        	uploadFlag = 0;
        });
        
        $("#uploadBut1").click(function(){
        	openFileUploadDialog({
        		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
        		uploadLimit:1,
        		multi:false
        	});
        	
        	uploadFlag = 1;
        });

		
        if(op == 'modify'){
        	
        	$("#h2y_promote_div").html(" ${promoteTitle}"); 	
        	
        	//logo图片处理
        	if(${voteSubject.diskFileName!=null}){
       	        var json_str = "{\"id\":\"${voteSubject.id}\"}";
       	     	$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"voteSubject_file\"  value='"+json_str+"'/><a class=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=voteSubjectService&type=logo&id=${voteSubject.id}\" title=\"${voteSubject.fileName}\"><img class=\"voteSubject_img img-responsive\" src=\"common/image/view.htm?fileBean=voteSubjectService&type=logo&id=${voteSubject.id}\"></a><a id=\"del_logo\" href=\"javascript:void(0);\">删除</a></div>");
       	     	addDelEvent("del_logo");
          	}
        	
        	if(${voteSubjectFile.diskFileName!=null}){
        		
        		var json_str = "{\"id\":\"${voteSubjectFile.id}\"}";
     	     	$("#h2y_main_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"voteSubjectFile_file\"  value='"+json_str+"'/><a class=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=voteSubjectService&type=file&id=${voteSubjectFile.id}\" title=\"${voteSubjectFile.fileName}\"><img class=\"voteSubject_img img-responsive\" src=\"common/image/view.htm?fileBean=voteSubjectService&type=file&id=${voteSubjectFile.id}\"></a><a id=\"del_main\" href=\"javascript:void(0);\">删除</a></div>");
     	     	addDelEvent("del_main");
        	}
    		
        	$(".light_box_a").lightBox();
        } 
        
   });	 
       //保存按钮
	function h2y_save() {
		if (!Validator.form())
			return;
		if(!compareTime("voteSubject_startDate", "voteSubject_endDate")) return;
		
        var promoteId= $("#voteSubject_promoteId").val();
        if(undefined == promoteId || ""==promoteId || "0"==promoteId){
        	alert("请选择推广活动！");
        	return;
        }
        var picLOGO= $("#h2y_file_div input[name='voteSubject_file']").val();
        if(undefined == picLOGO || null== picLOGO || '' == picLOGO){
        	alert("请选择logo图片！");
        	return;
        }
        
        var picmain= $("#h2y_main_div input[name='voteSubjectFile_file']").val();
        if(undefined == picmain || null== picmain  || '' == picmain){
        	alert("请选择主题图片！");
        	return;
        }
        
        if(!compareTime("voteSubject_startDate", "voteSubject_endDate")) return;
		
       //同步富文本编辑框数据
        contentEditor.sync(); 
      
		var queryString = $('#editform').serialize();
		if (isSubmiting) {
			alert("表单正在提交，请稍后操作！");
			return;
		}
		isSubmiting = true;
		<%--注意该处url可能不符合你的要求，请注意修改--%>
		$.post("business/votesubject/save.htm", queryString,function(data){
			var op=$("#op").val();
			var jsonReturn=eval("("+data+")");
			if (op == "add") {
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    if (top.f_getframe("business_votesubject_init_htm") != null) {
						top.f_getframe("business_votesubject_init_htm").f_query();
					}
					top.f_delTab("business_votesubject_add");
                } else {
                    alert(jsonReturn.msg);
                }
            } else {
                if (jsonReturn.code == "1") {
                    alert("修改成功！");
                    if (top.f_getframe("business_votesubject_init_htm") != null) {
						top.f_getframe("business_votesubject_init_htm").f_query();
					}             
                    top.f_delTab("business_votesubject_modify${voteSubject.id}");
                } else {
                    alert("修改失败！");
                }
            }
			
			isSubmiting = false;
		}); 
	}

	function h2y_refresh() {

		document.location.reload();
	}
 	
	//文件上传回调函数
    function h2y_fileUploadCallBack(data){
    	if(data==null || data.length==0){
    		return;
    	}
    	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
    	if(uploadFlag == 0){
    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"voteSubject_file\"  value='"+json_str+"'/><a class=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"voteSubject_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a id=\"del_logo\" href=\"javascript:void(0);\">删除</a></div>");
    		addDelEvent("del_logo");
    	}else{
    		$("#h2y_main_div").html("<div id=\"h2y_main\"><input type=\"hidden\" name=\"voteSubjectFile_file\"  value='"+json_str+"'/><a class=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"voteSubjectFile_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a id=\"del_main\" href=\"javascript:void(0);\">删除</a></div>");
    		addDelEvent("del_main");
    	}
    	$(".light_box_a").lightBox();
    }
	
	function addDelEvent(id){
		
		$("#"+id).click(function() {
			if (!confirm("删除后不可恢复，确定删除图片吗？"))return;
			$(this).parent().remove();
		});
	}
	
	//时间验证
	function compareTime(startId, endId) {

		var startDate = $("#" + startId).val();
		var endDate = $("#" + endId).val();
		if (startDate == null || endDate == null || startDate == ""|| endDate == "") {
			alert("时间不能为空！");
			return false;
		}
		startDate = startDate.replace(/[:-\s]/g, "");
		endDate = endDate.replace(/[:-\s]/g, "");
		if (parseInt(startDate) >= parseInt(endDate)) {
			alert("开始时间不能大于截止时间！");
			return false;
		}
		return true;
	}
	//推广活动窗口回调函数h2y_ui/common/common.js
	function h2y_promoteCommonSelectCallBack(data) {
		$("#voteSubject_promoteId").val(data.ID);
		$("#h2y_promote_div").text(data.TITLE);
		/* alert(JSON.stringify(data)); */
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
		<input name="op" type="hidden" id="op" value="${op}" />
		
		<table class="h2y_table">
			<tr>
				<td class="h2y_table_label_td">投票活动名称：</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					<input name="voteSubject.subjectName" id="voteSubject_subjectName" class="h2y_input_long" type="text" value="${voteSubject.subjectName}"/>
				</td>
			</tr>
			<tr>
				<td class="h2y_table_label_td">投票活动描述：</td>
				<td class="h2y_table_edit_td2" colspan="3"><input
					name="voteSubject.description" id="voteSubject_description"
					class="h2y_input_long" type="text"
					value="${voteSubject.description}" /></td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">开始时间:</td>
				<td class="h2y_table_edit_td"><input
					name="voteSubject.startDate" id="voteSubject_startDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteSubject.startDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

				<td class="h2y_table_label_td">结束时间:</td>
				<td class="h2y_table_edit_td"><input
					name="voteSubject.endDate" id="voteSubject_endDate"
					class="h2y_input_datetime" type="text"
					value="<fmt:formatDate value="${voteSubject.endDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>

			</tr>


			<tr>
				<td class="h2y_table_label_td"><input type="button" value="选择推广活动" class="button" id="selectPromoteBut" />:</td>
				<td class="h2y_table_edit_td2" colspan="3"><div id="h2y_promote_div"></div></td>
			</tr>

			<tr>
				<td class="h2y_table_label_td"><input type="button" value="上传LOGO图片" class="button" id="uploadBut" />:</td>
				<td class="h2y_table_edit_td2" colspan="3"><div id="h2y_file_div"></div></td>
			</tr>

			<tr>
				<td class="h2y_table_label_td"><input type="button" value="上传主页图片" class="button" id="uploadBut1" />:</td>
				<td class="h2y_table_edit_td2" colspan="3"><div id="h2y_main_div"></div></td>
			</tr>


			<tr>
				<td class="h2y_table_label_td">用户每日投票限制次数：</td>
				<td class="h2y_table_edit_td">
					<input name="voteSubject.limitTimes" id="voteSubject_limitTimes" type="text" class="h2y_input_just" value="${voteSubject.limitTimes}"/>
				</td>
				<td class="h2y_table_edit_td" colspan="2" style="color:red">0为不限制</td>
			</tr>
				<tr>
				<td class="h2y_table_label_td">用户投票总限制次数：</td>
				<td class="h2y_table_edit_td">
					<input name="voteSubject.totalTimes" id="voteSubject_totalTimes" type="text" class="h2y_input_just" value="${voteSubject.totalTimes}"/>
				</td>
				<td class="h2y_table_edit_td" colspan="2" style="color:red">0为不限制</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">是否启用：</td>
				<td class="h2y_table_edit_td"  colspan="3">
				<h2y:input name="voteSubject.subjectStatus" id="voteSubject_subjectStatus" type="radio" initoption="1,不启用:0,启用" value="${voteSubject.subjectStatus}" /></td>
			</tr>
			
				<tr id="subjectContent_tr">
				<td class="h2y_table_label_td">投票主题规则：</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<textarea name="voteSubjectDetail.subjectRule" id="voteSubjectDetail_subjectRule" class="h2y_editor_textarea">${voteSubjectDetail.subjectRule}</textarea>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>