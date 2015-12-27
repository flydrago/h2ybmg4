.<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <title>${mname}</title>
    <%@ include file="../../include/top_list.jsp" %>
    <link href="<%=uiPath%>lib/formUI/css/table.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
    var isSubmiting = false;
    var Validator = null;
    var op = "${op}";
	$(function() {
		if(op=="add"){
			$("#des_name").combogrid({
				panelWidth:450,
				idField:'id',
				textField:'name',
				fitColumns: true,  
	            striped: true,  
	            editable:true,  
				url:'sys/zone/getAll.htm',
				pagination : true,//是否分页  
	            rownumbers:true,//序号  
	            fit: true,//自动大小  
	            method:'post',
				columns:[[
				{field:'code',title:'地区编号',width:60},
				{field:'name',title:'地区名称',width:100}
				]],
				keyHandler: {
	                up: function() {},
	                down: function() {},
	                enter: function() {},
	                query: function(q) {
	                    //动态搜索
	                    $("#des_name").combogrid("grid").datagrid("reload", { 'keyword': q });
	                    $("#des_name").combogrid("setValue", q);
	                }
	            },
	            onSelect:function (rowIndex, rowData){
	            	 $("#desZoneCode").val(rowData.code);
	            	 $("#desZoneName").val(rowData.name);
	            }
		  });
		}
		
		
	});
	
	 function h2y_close(){
         var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
         dialog.close();//关闭dialog
     }

	
	function h2y_save(){
		var isSubmiting = false;
		var queryString = $('#editform').serialize();
        if (isSubmiting) {
            alert("表单正在提交，请稍后操作！");
            return;
        }
        isSubmiting = true;
        $.post("business/transfee/save.htm", queryString, function (data) {
            var jsonReturn=eval("("+data+")");
            if (op == "modify") {
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    parent.h2y_reload();
                    h2y_close();
                } else {
                    alert(jsonReturn.msg);
                }
            } else {
                if (jsonReturn.code == "1") {
                    alert(jsonReturn.msg);
                    parent.h2y_reload();
                    h2y_close();
                } else {
                    alert(jsonReturn.msg);
                }
            }
            isSubmiting = false;
        });
		
	}
	</script>
  </head>
  <body>
  <form name="editform" method="post"  id="editform">
		 <table  class="subtable" width="98%" cellspacing="1" cellpadding="0" border="0" align="center">
			<tr>
				 <th height="30" width="23%">单位名称:&nbsp;</th>
			     <td> 
			        <input type="hidden" name="id" value="${transportFee.id}"/>
			        <input type="hidden" name="desZoneCode" id="desZoneCode" value="${transportFee.desZoneCode}"/>
			        <input type="hidden" name="desZoneName" id="desZoneName" value="${transportFee.desZoneName}"/>
			        <input type="text"  readonly="readonly"   style="width:250px;height:25px;" value="${sysUnits.unitName}" />
			     </td>
			</tr>
			<tr>
				<th height="30" width="23%">目的地：&nbsp;</th>
				<td> 
			        <input type="text"   style="width:250px;height:25px;" id="des_name" value="${transportFee.desZoneName}"/>
			    </td>
			</tr>
			<tr>
				<th height="30" width="23%">运费：&nbsp;</th>
				<td> 
			        <input type="text"   style="width:250px;height:25px;" name="feeAmount" value="${transportFee.feeAmount}"/>
			    </td>
			</tr>
			<tr>
				 <th height="30" width="23%">备注:&nbsp;</th>
			     <td> 
			        <textarea cols="100" rows="4"   id="remark" name="remark"   style="width:250px"  >${transportFee.remark}</textarea>
			     </td>
			</tr>
		</table>
		 <table  class="buttable" width="98%" cellspacing="1" cellpadding="0" border="0" align="center">
         <tr>
	         <td>
				<input type="button" value="提交" class="but_submit" onclick="h2y_save();"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="取消" class="inp_but" onclick="h2y_close();"/>
			</td>
		 </tr>
    </table>
	</form>
  </body>
</html>
