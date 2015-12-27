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
        var logoType = "life";
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            if (op == "modify") {
				if(${shoplifeCondition.diskFileName!=null}){
                	
                	var json_str = "{\"id\":\"${shoplifeCondition.id}\"}";
             		$("#h2y_file_div").append("<div id=\"h2y_file\"><input type=\"hidden\" name=\"lifeLogo_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=shoplifeConditionService&id=${shoplifeCondition.id}\" title=\"${shoplifeCondition.fileName}\"><img class=\"activity_img\" src=\"common/image/view.htm?fileBean=shoplifeConditionService&id=${shoplifeCondition.id}\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
             		addDelClass();
             		$("#light_box_a").lightBox();
                }
            }
            
          //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#ord").ligerSpinner({type: 'int' ,height: 25});
            
            $("#uploadImgBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		uploadLimit:1,
            		multi:false
            	});
            });
            
            
        });


        function h2y_save() {
        	
        	if(!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/shoplifecondition/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_shoplifecondition_init_htm") != null) {
							top.f_getframe("business_shoplifecondition_init_htm").h2y_refresh();
						}
                        top.f_delTab("shoplifecondition_modify${shoplifeCondition.id}");
						
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_shoplifecondition_init_htm") != null) {
							top.f_getframe("business_shoplifecondition_init_htm").h2y_refresh();
						}             
                        top.f_delTab("shoplifecondition_add");
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
    		$("#h2y_file_div").html("<div id=\"h2y_file\"><input type=\"hidden\" name=\"lifeLogo_file\"  value='"+json_str+"'/><a id=\"light_box_a\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+data[0].savePath+"\" title=\""+data[0].fileName+"\"><img  class=\"activity_img\"  src=\"common/image/view.htm?URLPATH="+data[0].savePath+"\"></a><a class=\"del\" href=\"javascript:void(0);\">删除</a></div>");
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
        <input type="hidden" name="id" value="${shoplifeCondition.id}"/>
        <input type="hidden" name="parentId" value="${shoplifeCondition.parentId}"/>
        <input type="hidden" name="op" value="${op}"/>
    <table class="h2y_table">

        <tr>
            <td class="h2y_table_label_td">名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="conditionName" type="text" id="conditionName"  class="h2y_input_just"  value="${shoplifeCondition.conditionName}"/></td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">检索键:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="conditionKey" type="text" id="conditionKey"  class="h2y_input_just"  value="${shoplifeCondition.conditionKey}"/><font color="red">(客户端用该键来检索，若为空值则不检索)</font>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">检索值:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="conditionValue" type="text" id="conditionValue"  class="h2y_input_just"  value="${shoplifeCondition.conditionValue}"/><font color="red">(客户端用该值来检索，若为空值则不检索)</font>
            </td>
        </tr>
        

        <tr>
            <td class="h2y_table_label_td">备注:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="memo" type="text" id="memo"  class="h2y_dialog_input_long"  value="${shoplifeCondition.memo}"/></td>
        </tr>

        <tr>
            <td class="h2y_table_label_td" valign="top">排序:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="ord" type="text" id="ord" value="${shoplifeCondition.ord}"/>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">状态:</td>
            <td class="h2y_dialog_table_edit_td">
                <h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${shoplifeCondition.status}"/>
            </td>
        </tr>

		<tr>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="上传图片" id="uploadImgBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="h2y_file_div"></div>
				</td>
			</tr>
        

    </table>
</form>

</body>
</html>