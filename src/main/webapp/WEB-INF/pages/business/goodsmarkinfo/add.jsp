<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>


    <script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;

        $(function () {
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            //验证信息
            ${validationRules};
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            $("#ord").ligerSpinner({type: 'int' ,height: 25});
            
            
            $("#uploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		uploadLimit:1,
            		multi:false
            	});
            });

	      		      
	        if(op == 'modify'){
	        	//图片处理
	        	if(${goodsMarkInfo.diskFileName!=null}){
                	
                	var json_str = "{\"id\":\"${goodsMarkInfo.id}\"}";
             		$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"goodsMarkInfo_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=goodsMarkInfoService&id=${goodsMarkInfo.id}\" title=\"${goodsMarkInfo.fileName}\"><img class=\"activity_img\" src=\"common/image/view.htm?fileBean=goodsMarkInfoService&id=${goodsMarkInfo.id}\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
             		addDelClass();
             		$("#light_box_a").lightBox();
                }
	        }	
            
        });

        function h2y_save() {
        	var goodsMarkInfoId = "${goodsMarkInfo.id}";
        	if(!Validator.form()) return;
        	var isShowImg = "${isShowImg}";
        	var markId = "${goodsMarkInfo.markId}";

        	if(isShowImg == '1' && ('undefined' == $("input[type=hidden][name='goodsMarkInfo_file']").val() || undefined == $("input[type=hidden][name='goodsMarkInfo_file']").val())){
            	alert("请上传图片！");
            	return;
            }

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/goodsmarkinfo/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                    	alert(jsonReturn.msg);
                    	if (top.f_getframe("goodsmarkinfo_add_"+markId) != null) {
							top.f_getframe("goodsmarkinfo_add_"+markId).f_query();
						}             
                        top.f_delTab("goodsmarkinfo_modify_"+goodsMarkInfoId);
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                    	alert(jsonReturn.msg);
	                    if (top.f_getframe("goodsmarkinfo_add_"+markId) != null) {
								top.f_getframe("goodsmarkinfo_add_"+markId).f_query();
						}
						top.f_delTab("goodsmarkinfo_add");
	                } else {
	                    alert(jsonReturn.msg);
	                }
                }
                isSubmiting = false;
            });
        }
        
        
      //文件上传回调函数
        function h2y_fileUploadCallBack(data){
        	if(data==null || data.length==0){
        		return;
        	}
        	
        	var json_str = "{\"fileName\":\""+data[0].fileName+"\",\"saveName\":\""+data[0].saveName+"\",\"savePath\":\""+data[0].savePath+"\",\"fileSize\":\""+data[0].fileSize+"\"}";
    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"goodsMarkInfo_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"activity_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
        	addDelClass();
        	$("#light_box_a").lightBox();
        }
		
		
		function addDelClass(){
			 $(".del").click(function() {  
				 if (!confirm("删除后不可恢复，确定删除图片吗？")) return;
		    	 $(this).parent().remove();    
			 });
		}
		
		
		function h2y_refresh() {
            document.location.reload();
        }

    </script>

</head>

<body>
<div id="layout1" style="width: 100%">
<div position="top">
			<table width="100%"  class="my_toptoolbar_td">
				<tr>
				<td id="my_toptoolbar_td">&nbsp;${mname}</td>
				<td align="right" width="500"><div id="toptoolbar"></div></td>
				</tr>
				</table>
			</div>
</div>

<form name="editform" method="post" action="" id="editform">
        <input type="hidden" name="id" value="${goodsMarkInfo.id}"/>
        <input type="hidden" name="markId" value="${goodsMarkInfo.markId}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="name" type="text" id="name"  class="h2y_input_just"  value="${goodsMarkInfo.name}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td">备注:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="memo" type="text" id="memo"  class="h2y_dialog_input_long"  value="${goodsMarkInfo.memo}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td" valign="top">排序:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="ord" type="text" id="ord" value="${goodsMarkInfo.ord}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">状态:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${goodsMarkInfo.status}"/>
            </td>
        </tr>
		<c:if test="${isShowImg == 1 }">
	        <tr>
				<td class="h2y_table_label_td">
					<input type="button" value="图片上传" class="button" id="uploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="h2y_file_div"></div>
				</td>
			</tr>
		</c:if>
    </table>
</form>
</div>
</body>
</html>