<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <script type="text/javascript">
        var isSubmiting = false;
        var auditBean = "${auditBean}";
        
        function h2y_save() {
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            var postData = {};
            postData.billId = $("input[name='billId']").val();
            postData.checkStatus = $("input[name='checkStatus']:checked").val();
            postData.op = $("input[name='op']").val();
            
            $.post("jxc/common/billAuditSave.htm",{postData:JSON.stringify(postData),auditBean:auditBean}, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.resultFlag == "1") {
                    alert(jsonReturn.resultMsg);
                    parent.h2y_refresh();
                    frameElement.dialog.close();
                } else {
                    alert(jsonReturn.resultMsg);
                    parent.h2y_refresh();
                    frameElement.dialog.close();
                }
                isSubmiting = false;
            });
        }
    </script>

</head>

<body>
<form name="editform" method="post" action="" id="editform">
    <div>
        <input type="hidden" name="billId" value="${billId}"/>
        <input type="hidden" name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">单据编号:</td>
            <td class="h2y_table_label_td" style="text-align: left;">
            	<span>${billNo }</span>
            </td>
        </tr>
        
        <tr>
            <td class="h2y_table_label_td">状态:</td>
            <td class="h2y_dialog_table_edit_td">
            	<input type="radio" name="checkStatus" value="1" checked="checked"/>审核通过&nbsp;&nbsp;
            	<input type="radio" name="checkStatus" value="-1"/>审核不通过
            </td>
        </tr>
        
    </table>
</form>

</body>
</html>