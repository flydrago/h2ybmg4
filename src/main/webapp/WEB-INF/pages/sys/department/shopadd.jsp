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
        var op = "${op}";
        var form = null;
        var mapDataJson = null;
        var fileId = 0;
        $(function () {


            //$.metadata.setType("attr", "validate");
            //form = $("form").ligerForm();
            
            mapDataJson = {};
            mapDataJson.location = $("#sysDepartment_position").val();
            mapDataJson.longitude = $("#sysDepartment_gpsLongitude").val();
            mapDataJson.latitude = $("#sysDepartment_gpsLatitude").val();
            
            $("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            
            if (op == "modify") {
                $("#tr_next").hide();
                
				$(${fileDataListJson}).each(function(){
                	
               		var json_str = "{\"id\":\""+this.id+"\"}";
               		if(this.fileType==1){
               			$("#shoppic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"shopPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=sysDepartmentService&id="+this.id+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"shopPicImg\" src=\"common/image/view.htm?fileBean=sysDepartmentService&id="+this.id+"\"></a><a id=\"file_delete_href_"+fileId+"\" href=\"javascript:deletePic('"+fileId+"');\">删除</a>");
               		}else{
               			if(this.ord==2){
               				$("#shoplogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/><a class=\"shopLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?fileBean=sysDepartmentService&id="+this.id+"\" title=\""+this.fileName+"\"><img class=\"shopLogoImg\" src=\"common/image/view.htm?fileBean=sysDepartmentService&id="+this.id+"\"></a>");
               			}else{
               				$("#shoplogo_div").append("<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/>");
               			}
               		}
               		fileId++;
                });
                
            }else{
            	$("input[type=radio][name='sysDepartment.reverse1']:first").attr("checked",true);
            	$("#approve_tr").hide();
            	$("#spread_tr").hide();
            }
            
            //验证信息
            ${validationRules}
            //验证属性设置
            $.metadata.setType("attr", "validate");
            Validator = deafultValidate($("#editform"));
            
            $("#shoplocation_but").click(function(){
            	openMapLocationDialog();
            });
            
            
            $("#sysDepartment_deptOrd").ligerSpinner({type: 'int' ,height: 25,width:194});
            $("#sysShopInfo_approveLevel").ligerSpinner({type: 'int' ,height: 25,width:194});
            //$("#sysShopInfo_hoursStart").datetimepicker({});
            //$("#sysShopInfo_hoursEnd").datetimepicker({});
            $('#sysShopInfo_hoursStart').timepicker({});
            $('#sysShopInfo_hoursEnd').timepicker({});          
            $("#sysShopInfo_spreadStartDate").datetimepicker({});
            $("#sysShopInfo_spreadEndDate").datetimepicker({});
            
            $("input[type=radio][name='sysShopInfo.isApprove']").change(function(){    
            	if(this.value == 1){
            		 $("#approve_tr").show();
            	}else{
            		 $("#approve_tr").hide();
            		 $("#sysShopInfo_approveReviews").val("");
            	}
            });
            
            
            $("input[type=radio][name='sysShopInfo.isSpread']").change(function(){    
            	if(this.value == 1){
            		 $("#spread_tr").show();
            	}else{
            		 $("#spread_tr").hide(); 
            		 
            		 $("#sysShopInfo_spreadStartDate").val("");
            		 $("#sysShopInfo_spreadEndDate").val("");
            	}
            });
            
            
            
            
            $("#shopPicUploadBut").click(function(){
            	openFileUploadDialog({
            		fileTypeExts:"*.jpg;*.png;*.jpeg;*.gif",
            		multi:true
            	});
            });
            
            $("#shopLogoUploadBut").click(function(){
            	openImageUploadDialog();
            });
            
            
        });


        function h2y_save() {
        	
        	if(!Validator.form()) return;

            var queryString = $('#editform').serialize();
            
            //旗舰店必须上传图片
            if(1 == $("#unitType").val() || "1" == $("#unitType").val()){
            	if('undefined' == $("input[type=hidden][name='logoData']").val() || undefined == $("input[type=hidden][name='logoData']").val()){
                	alert("请上传logo图片！");
                	return;
                }
                
                if('undefined' == $("input[type=hidden][name='picData']").val() || undefined == $("input[type=hidden][name='picData']").val()){
                	alert("请上传门店图片！");
                	return;
                }
            }
            

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("sys/department/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("sys_department_init_htm") != null) {
							top.f_getframe("sys_department_init_htm").f_query();
							top.f_delTab("sys_department_modify_${sysDepartment.id}");
						}
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        
                        if (top.f_getframe("sys_department_init_htm") != null) {
							top.f_getframe("sys_department_init_htm").f_query();
							top.f_delTab("sys_shop_add");
						}
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
        function h2y_mapLocationCallBack(data){
        	mapDataJson = data;
        	if(data!=null){
        		$("#sysDepartment_position").val(data.location);
        		$("#sysDepartment_gpsLongitude").val(data.longitude);
        		$("#sysDepartment_gpsLatitude").val(data.latitude);
        	}
        }
        
        
		function deletePic(fileId){
        	
        	if(!confirm("您确定要删除文件吗?")){
				return true;
			}
        	$("#file_input_"+fileId).remove();
        	$("#file_lightbox_"+fileId).remove();
        	$("#file_img_"+fileId).remove();
        	$("#file_delete_href_"+fileId).remove();
		}

		function h2y_refresh() {
            document.location.reload();
        }
		
		
		function h2y_fileUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}
        	$(data).each(function(){
        		
        		var json_str = "{\"fileType\":\"1\",\"fileName\":\""+this.fileName+"\",\"saveName\":\""+this.saveName+"\",\"savePath\":\""+this.savePath+"\",\"fileSize\":\""+this.fileSize+"\"}";
        		$("#shoppic_div").append("<input id=\"file_input_"+fileId+"\" type=\"hidden\" name=\"picData\"  value='"+json_str+"'/><a id=\"file_lightbox_"+fileId+"\" class=\"shopPicImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+this.savePath+"\" title=\""+this.fileName+"\"><img  id=\"file_img_"+fileId+"\" class=\"shopPicImg\" src=\"common/image/view.htm?URLPATH="+this.savePath+"\"></a><a id=\"file_delete_href_"+fileId+"\" href=\"javascript:deletePic('"+fileId+"');\">删除</a>");
        		fileId++;
        	});
        	
        	$(".shopPicImg_lightbox").lightBox();
        }
		
		function h2y_imageUploadCallBack(data){
        	
        	if(data==null || data.length==0){
        		return;
        	}

        	var imageHtml = "";
        	$(data).each(function(i){
        		var json_str = "{\"fileType\":\"0\",\"fileName\":\""+this.fileName+"\",\"saveName\":\""+this.saveName+"\",\"savePath\":\""+this.savePath+"\",\"fileSize\":\""+this.fileSize+"\"}";
        		if(i==1){
        			imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/><a class=\"shopLogoImg_lightbox\" rel=\"lightbox\" href=\"common/image/view.htm?URLPATH="+this.savePath+"\" title=\""+this.fileName+"\"><img class=\"shopLogoImg\" src=\"common/image/view.htm?URLPATH="+this.savePath+"\"></a>";
        		}else{
        			imageHtml += "<input type=\"hidden\" name=\"logoData\"  value='"+json_str+"'/>";
        		}
        	});
        	$("#shoplogo_div").html(imageHtml);
        	$(".shopLogoImg_lightbox").lightBox();
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
		<div>
			<input type="hidden" name="sysDepartment.id" value="${sysDepartment.id}" />
			<input type="hidden" name="sysShopInfo.id" value="${sysShopInfo.id}" />
			<input type="hidden" name="sysShopInfo.shopId" value="${sysShopInfo.shopId}" />
			<input type="hidden" name="op" value="${op}" />
			<input type="hidden" name="unitType" id="unitType" value="${unitType}" />			
			<input type="hidden" name="sysDepartment.parentId" value="${sysDepartment.parentId}" />
			<input type="hidden" name="sysDepartment.deptType" value="${sysDepartment.deptType}" />
		</div>
		<table class="h2y_table">

			<tr>
				<td class="h2y_table_label_td">名称:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<input name="sysDepartment.deptName" type="text" class="h2y_input_just" id="sysDepartment_deptName"
						value="${sysDepartment.deptName}" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">简称:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<input name="sysDepartment.deptShortName" type="text" class="h2y_input_just" id="sysDepartment_deptShortName" 
					value="${sysDepartment.deptShortName}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">位置:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<input name="sysDepartment.position" type="text" class="h2y_input_just" id="sysDepartment_position" 
					value="${sysDepartment.position}" />
					<input class="button" type="button" value="获取位置" id="shoplocation_but" />
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">纬度:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysDepartment.gpsLongitude" type="text" class="h2y_input_just" id="sysDepartment_gpsLongitude" 
					value="${sysDepartment.gpsLongitude}" />
				</td>
			
				<td class="h2y_table_label_td">经度:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysDepartment.gpsLatitude" type="text" class="h2y_input_just" id="sysDepartment_gpsLatitude" 
					value="${sysDepartment.gpsLatitude}" />
				</td>
			</tr>


			<tr>
				<td class="h2y_table_label_td">门店电话:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysShopInfo.shopPhone" type="text" class="h2y_input_just" id="sysShopInfo_shopPhone" value="${sysShopInfo.shopPhone}" />
				</td>
			
				<td class="h2y_table_label_td">人均消费:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysShopInfo.personCost" type="text" class="h2y_input_just" id="sysShopInfo_personCost" value="${sysShopInfo.personCost}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">星级:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysShopInfo.approveLevel" type="text"  id="sysShopInfo_approveLevel" value="${sysShopInfo.approveLevel}" />
				</td>
			
				<td class="h2y_table_label_td">排序:</td>
				<td class="h2y_dialog_table_edit_td">
					<input name="sysDepartment.deptOrd" type="text" id="sysDepartment_deptOrd" value="${sysDepartment.deptOrd}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">营业开始时间:</td>
				<td class="h2y_table_edit_td2" >
					<input name="sysShopInfo.hoursStart" type="text" id="sysShopInfo_hoursStart"  class="h2y_input_just"
					value="${sysShopInfo.hoursStart}"   />
				</td>
			
				<td class="h2y_table_label_td">营业结束时间:</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="sysShopInfo.hoursEnd" type="text" id="sysShopInfo_hoursEnd"  class="h2y_input_just"
					value="${sysShopInfo.hoursEnd}"  />
				</td>
			</tr>

			
			
			<tr>
				<td class="h2y_table_label_td">类型:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<h2y:input name="sysDepartment.reverse1" id="sysDepartment_reverse1" type="radio" dictcode="shop_type" value="${sysDepartment.reverse1}"/>
				</td>
			</tr>
			

			<tr>
				<td class="h2y_table_label_td">是否提供wifi:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="sysShopInfo.isWifi" id="sysShopInfo_isWifi" type="radio" initoption="0,否:1,是" value="${sysShopInfo.isWifi}"/>
				</td>
			
				<td class="h2y_table_label_td">是否提供车位:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="sysShopInfo.isPark" id="sysShopInfo_isPark" type="radio" initoption="0,否:1,是" value="${sysShopInfo.isPark}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">是否需要预约:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="sysShopInfo.isReservation" id="sysShopInfo_isReservation" type="radio" initoption="0,否:1,是" value="${sysShopInfo.isReservation}"/>
				</td>
			
				<td class="h2y_table_label_td">是否H2Y合作:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="sysShopInfo.isJyd" id="sysShopInfo_isJyd" type="radio" initoption="0,否:1,是" value="${sysShopInfo.isJyd}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">是否小编认证:</td>
				<td class="h2y_dialog_table_edit_td" >
					<h2y:input name="sysShopInfo.isApprove" id="sysShopInfo_isApprove" type="radio" initoption="0,否:1,是" value="${sysShopInfo.isApprove}"/>
				</td>
				
				<td class="h2y_table_label_td">是否推广:</td>
				<td class="h2y_dialog_table_edit_td">
					<h2y:input name="sysShopInfo.isSpread" id="sysShopInfo_isSpread" type="radio" initoption="0,否:1,是" value="${sysShopInfo.isSpread}"/>
				</td>
			</tr>
			
			<tr id="approve_tr" <c:if test="${op == 'modify' and  sysShopInfo.isApprove == 0}">style="display:none"</c:if>>
				<td class="h2y_table_label_td">小编点评:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<input name="sysShopInfo.approveReviews" id="sysShopInfo_approveReviews" type="text" class="h2y_dialog_input_long" id="sysShopInfo_approveReviews" 
					value="${sysShopInfo.approveReviews}" />
				</td>
			</tr>
			
			
			
			<tr id="spread_tr" <c:if test="${op == 'modify' and  sysShopInfo.isSpread == 0}">style="display:none"</c:if>>
				<td class="h2y_table_label_td">推广开始时间:</td>
				<td class="h2y_table_edit_td2" >
					<input name="sysShopInfo.spreadStartDate" type="text" id="sysShopInfo_spreadStartDate"  class="h2y_input_just"
					value="<fmt:formatDate value="${sysShopInfo.spreadStartDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>
			
				<td class="h2y_table_label_td">推广结束时间:</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="sysShopInfo.spreadEndDate" type="text" id="sysShopInfo_spreadEndDate"  class="h2y_input_just"
					value="<fmt:formatDate value="${sysShopInfo.spreadEndDate}"  pattern="yyyy-MM-dd HH:mm"/>" />
				</td>
			</tr>

			<tr>
				<td class="h2y_table_label_td">描述:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<input name="sysDepartment.deptDesc" type="text" class="h2y_dialog_input_long" id="sysDepartment_deptDesc" 
					value="${sysDepartment.deptDesc}" />
				</td>
			</tr>
			
			
			<tr>
				<td class="h2y_table_label_td">备注:</td>
				<td class="h2y_dialog_table_edit_td" colspan="3">
					<input name="sysShopInfo.memo" type="text" class="h2y_dialog_input_long" id="sysShopInfo_memo" 
					value="${sysShopInfo.memo}" />
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="上传Logo" class="button" id="shopLogoUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="shoplogo_div"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="上传图片" class="button" id="shopPicUploadBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="shoppic_div"></div>
				</td>
			</tr>
			
			
		</table>
	</form>

</body>
</html>