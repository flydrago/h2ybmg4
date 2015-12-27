<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>

	<style>
		/* 去除type=number的箭头  开始 */
		input[type="number"] {
		    -moz-appearance:textfield;
		}
		input[type="number"]::-webkit-outer-spin-button,
		input[type="number"]::-webkit-inner-spin-button {
		    -webkit-appearance: none;
		}
		/* 去除type=number的箭头 结束 */
	</style>
	
    <script type="text/javascript">
       var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var parentId = "${parentId}";
        var spec = "${spec}";
        var numString = "${numString}";
        var numArry = numString == "" ? [] : numString.split(",");       
        var form = null;

        $(function () {
        	/* $("#toptoolbar").ligerToolBar({
        		items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },
        	            { line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]
        	}); */
        	
            if (op == "modify") {
                $("#tr_next").hide();
            }
            
            
            $("#selcetProvinceBtn").click(function(){
    			selectProvince();
        	});
            
            $("#selcetProviderBtn").click(function(){
				selectionProvider();
        	});
        	
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
        });

        //保存
        function h2y_save() {
        	
        	if(!Validator.form()) return;

            var queryString = $('#editform').serialize();
			
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/traceprovideritem/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                    } else {
                        alert(jsonReturn.msg);
                    }
                }else if(op == "add"){
                	if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        parent.f_query();
                        frameElement.dialog.close();
                     /*   if (top.f_getframe("business_traceprovideritem_init_htm") != null) {
							top.f_getframe("business_traceprovideritem_init_htm").f_query();
						 }
						top.f_delTab("business_traceprovideritem_add_htm_op_add");  */
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
            
        }

       //刷新
        function h2y_refresh() {
            document.location.reload();
        }
        
    </script>

</head>

<body>
<%-- <div position="top">
        <table width="100%" class="my_toptoolbar_td">
            <tr>
                <td id="my_toptoolbar_td">
                    <div class="l-toolbar">&nbsp;${mname}</div>
                </td>
                <td align="right" width="50%">
                    <div id="toptoolbar" style="font-size:12px;"></div>
                </td>
            </tr>
        </table>
    </div> --%>
<form name="editform" method="post" action="" id="editform">
 	<input type="hidden" name="id" value="${traceProviderItem.id}"/>
 	<input type="hidden" name="op" value="${op}"/>
    <table class="h2y_dialog_table"><!-- h2y_table -->

        <tr>
            <td class="h2y_table_label_td">供应商名称:</td>
            <td class="h2y_dialog_table_edit_td">
                <input name="providerName" type="text" id="providerName"  class="h2y_dialog_input_long" value="${traceProviderItem.providerName}" style="padding-left:5px;"/>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td">法人:</td>
            <td class="h2y_dialog_table_edit_td"><!--  -->
                <input name="legalUser" type="text" id="legalUser"  class="h2y_dialog_input_long"  value="${traceProviderItem.legalUser}" style="padding-left:5px;"/>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td">联系电话:</td>
            <td class="h2y_dialog_table_edit_td"><!--  -->
                <input name="contactTelephone" type="tel" id="contactTelephone"  class="h2y_dialog_input_long"  value="${traceProviderItem.contactTelephone}" style="padding-left:5px;"/>
            </td>
        </tr>
        
       <tr>
            <td class="h2y_table_label_td">收款账号:</td>
            <td class="h2y_dialog_table_edit_td"><!--  -->
                <input name="beneficiaryAccount" type="number" id="beneficiaryAccount"  class="h2y_dialog_input_long"  value="${traceProviderItem.beneficiaryAccount}" style="padding-left:5px;"/>
            </td>
        </tr>
       <tr>
            <td class="h2y_table_label_td">公司住址:</td>
            <td class="h2y_dialog_table_edit_td"><!--  -->
           		 <textarea cols="100" rows="3" id="companyAddress" name="companyAddress" style="width:460px;border:1px solid #a3c0e8;padding-left:5px;" >${traceProviderItem.companyAddress}</textarea>
            </td>
        </tr>
		
    </table>
</form>

</body>
</html>