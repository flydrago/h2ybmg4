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

<style type="text/css">
	.zone_code_select{ 
		 border: 1px solid #aecaf0;
		 height:25px;
		 line-height: 25px;
	 }
	 
	.advert_img { max-width:600px;}
</style>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var unitType =  "${sysUnits.unitType}";
        var form = null;
        var select_index = 0;
        var fileType = null;//文件上传的标识
        var fileId = 0;
        var loginUnitId = '${loginUnitId}';
        
        var unitSortDataJson = null;
        
        $(function () {
        	
        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            //平台公司可以选择区域
         
            //文件
            if(op == "modify"){
				$(${fileListDataJson}).each(function(){
            		
            		fileId++;
            		var json_str = "{\"fileType\":\""+this.fileType+"\",\"id\":\""+this.id+"\"}";
					
            		//alert($("#h2y_file_div_"+this.fileType).attr("_flag"));
            		if($("#h2y_file_div_"+this.fileType).attr("_flag")=="image"){
            			$("#h2y_file_div_"+this.fileType).append("<div id=\"h2y_file_"+fileId+"\"><input type=\"hidden\" name=\"fileData\"  value='"+json_str+"'/><a class=\"light_box_a_"+this.fileType+"\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=sysUnitsService&id="+this.id+"\" title=\""+this.fileName+"\"><img  class=\"advert_img\"  src=\"common/image/view.htm?fileBean=sysUnitsService&id="+this.id+"\"></a><a href=\"javascript:deletePic('"+fileId+"');\">删除</a></div>");
                    	$(".light_box_a_"+this.fileType).lightBox();
            		}else{
            			$("#h2y_file_div_"+this.fileType).append("<div id=\"h2y_file_"+fileId+"\"><input type=\"hidden\" name=\"fileData\"  value='"+json_str+"'/><a href=\"javascript:downloadFile('"+this.id+"');\" title=\""+this.fileName+"\">"+this.fileName+"</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:deletePic('"+fileId+"');\">删除</a></div>");
            		}
            	});
				//处理域名				
				if(1 != loginUnitId && '1' != loginUnitId){
					var unitDomain = '${sysUnits.unitDomain}';
					var prefixDomain = '${prefixDomain}';
					$("#shortDomain").val(unitDomain.substring(prefixDomain.length,unitDomain.length)); 
				}
            }
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            
                   
            $("#userCount").ligerSpinner({type: 'int',height: 25,width:150,isNegative:false});
            $("#stopDate").datetimepicker({});
            
        });
        
        function h2y_save() {
        	//域名处理        	
        	
			if(1 != loginUnitId && '1' != loginUnitId){
				$("#unitDomain").val('${prefixDomain}'+$("#shortDomain").val()); 
			}
				
        	if(!Validator.form()) return;
        	
            var queryString = $('#editform').serialize();
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/units/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                    	
                        alert(jsonReturn.msg);
                      	//管理页面列表刷新
						//top.f_refreshTab("sys_units_add_htm_op_modify_id_${sysUnits.id}");
                      	if (top.f_getframe("sys_units_init_htm") != null) {
							top.f_getframe("sys_units_init_htm").f_query();
						}else if (top.f_getframe("sys_units_initshop_htm") != null) {
							top.f_getframe("sys_units_initshop_htm").f_query();
						}else if (top.f_getframe("sys_units_initunit_htm") != null) {
							top.f_getframe("sys_units_initunit_htm").f_query();
						}
						top.f_delTab("sys_units_add_htm_op_modify_id_${sysUnits.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                      	//管理页面列表刷新
						//if (top.f_getframe("sys_units_add_htm_op_add_parentId_${sysUnits.parentId}") != null) {
							//top.f_getframe("sys_units_add_htm_op_add_parentId_${sysUnits.parentId}").h2y_refresh();
						//}
                      	//top.f_refreshTab("sys_units_add_htm_op_add_parentId_${sysUnits.parentId}");
                      	if (top.f_getframe("sys_units_init_htm") != null) {
							top.f_getframe("sys_units_init_htm").f_query();
						}else if (top.f_getframe("sys_units_initshop_htm") != null) {
							top.f_getframe("sys_units_initshop_htm").f_query();
						}else if (top.f_getframe("sys_units_initunit_htm") != null) {
							top.f_getframe("sys_units_initunit_htm").f_query();
						}
						top.f_delTab("sys_units_add_htm_op_add_parentId_${sysUnits.parentId}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
        function h2y_refresh() {
            document.location.reload();
        }
        
        
        function h2y_fileupload(type,flag){
        	
        	var fileTypeExts = "*.jpg;*.png;*.jpeg;*.gif";
        	if(flag!="image"){
        		fileTypeExts = "*.*";
        	}
        	
           	openFileUploadDialog({
           		fileTypeExts:fileTypeExts,
           		multi:true
           	});
           	fileType = type;
        }
        
        
        function h2y_fileUploadCallBack(data){
        	
        	if(data==null) return;
        	
        	$(data).each(function(){
        		fileId++;
        		var json_str = "{\"fileType\":\""+fileType+"\",\"fileName\":\""+this.fileName+"\",\"saveName\":\""+this.saveName+"\",\"savePath\":\""+this.savePath+"\",\"fileSize\":\""+this.fileSize+"\"}";
        		
        		if($("#h2y_file_div_"+fileType).attr("_flag")=="image"){
        			$("#h2y_file_div_"+fileType).append("<div id=\"h2y_file_"+fileId+"\"><input type=\"hidden\" name=\"fileData\"  value='"+json_str+"'/><a class=\"light_box_a_"+fileType+"\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+this.savePath+"\" title=\""+this.fileName+"\"><img  class=\"advert_img\"  src=\"common/image/view.htm?URLPATH="+this.savePath+"\"></a><a href=\"javascript:deletePic('"+fileId+"');\">删除</a></div>");
                	$(".light_box_a_"+fileType).lightBox();
        		}else{
        			$("#h2y_file_div_"+fileType).append("<div id=\"h2y_file_"+fileId+"\"><input type=\"hidden\" name=\"fileData\"  value='"+json_str+"'/><a href=\"common/file/down.htm?path="+this.savePath+"&saveName="+this.fileName+"\" title=\""+this.fileName+"\">"+this.fileName+"</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:deletePic('"+fileId+"');\">删除</a></div>");
        		}
        	});
        }
        
		function downloadFile(id){
			
			var url="<%=basePath%>common/file/down.htm?fileBean=sysUnitsService&id="+id;
		    location.href = url;
		}
        
        function deletePic(fileId){
        	
        	if(!confirm("您确定要删除文件吗?")){
				return true;
			}
        	$("#h2y_file_"+fileId).remove();   
		}

        function h2y_unitSortSelectCallBack(data){
        	unitSortDataJson = data;
        	$("#sortData").val(JSON.stringify(data));

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
			<input type="hidden" name="op" value="${op}" /> 
			<input type="hidden" name="id" value="${sysUnits.id}" />
			<input type="hidden" name="parentId" value="${sysUnits.parentId}" />
			<input name="zoneCode" type="hidden" id="_zoneCode" value="${sysUnits.zoneCode}" />
		</div>
		<table class="h2y_table">
		
			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_table_edit_td2">
					<input name="unitName" type="text" id="unitName" class="h2y_input_long"
						value="${sysUnits.unitName}" />	
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">简称:</td>
				<td class="h2y_table_edit_td2">
					<input name="shortName" type="text" id="shortName" class="h2y_input_just"
					value="${sysUnits.shortName}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">域名:</td>
				<td class="h2y_table_edit_td2">
					<c:if test="${loginUnitId == 1 }">
						<input name="unitDomain" type="text" id="unitDomain" class="h2y_input_just"  value="${sysUnits.unitDomain}" />
					</c:if>
					<c:if test="${loginUnitId != 1 }">
						${prefixDomain}<input name="shortDomain" type="text" id="shortDomain"  class="h2y_input_just" value="" />
						<input name="unitDomain" type="hidden" id="unitDomain"  value="${sysUnits.unitDomain}" />
					</c:if>
					
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">人数:</td>
				<td class="h2y_table_edit_td2">
					<input name="userCount" type="text" id="userCount" value="${sysUnits.userCount}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">地址:</td>
				<td class="h2y_table_edit_td2">
					<input name="unitAddress" type="text" id="unitAddress"  class="h2y_input_long"
					value="${sysUnits.unitAddress}" />
				</td>
			</tr>
			
			<td class="h2y_table_label_td">appId:</td>
				<td class="h2y_table_edit_td2">
					<input name="s3ucode" type="text" id="s3ucode"  class="h2y_input_long"
					value="${sysUnits.s3ucode}" />
				</td>
			</tr>
			
			<td class="h2y_table_label_td">appSecret:</td>
				<td class="h2y_table_edit_td2">
					<input name="s3uname" type="text" id="s3uname"  class="h2y_input_long"
					value="${sysUnits.s3uname}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">法人:</td>
				<td class="h2y_table_edit_td2">
					<input name="legalPerson" type="text" id="legalPerson" class="h2y_input_just"
					value="${sysUnits.legalPerson}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">法人手机:</td>
				<td class="h2y_table_edit_td2">
					<input name="legalPersonMobile" type="text" id="legalPersonMobile"  class="h2y_input_just"
					value="${sysUnits.legalPersonMobile}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">停用时间:</td>
				<td class="h2y_table_edit_td2">
					<input name="stopDate" type="text" id="stopDate"  class="h2y_input_just"
					value="<fmt:formatDate value="${sysUnits.stopDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>
			</tr>	
			
			<c:forEach var="c" items="${fileTypeList}">
				<tr>
					<td class="h2y_table_label_td" >
						<input type="button" value="上传${c.name}" class="button" onclick="h2y_fileupload('${c.type}','${c.flag}')" />:
					</td>
					<td class="h2y_table_edit_td2">
						<div id="h2y_file_div_${c.type}" _flag="${c.flag}"></div>
					</td>
				</tr>
			</c:forEach>
			
		</table>
	</form>

</body>
</html>