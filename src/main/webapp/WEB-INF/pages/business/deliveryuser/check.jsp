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
        var originalProvinceZoneCode = "${provinceZone}";
        var originalCityZoneCode = "${cityZone}";
        var originalCountyZoneCode = "${countyZone}";
        
        $(function () {
        	$("input[type=radio][name='checkStatus'][value='1']").attr("checked",true);

        	$("select[name='chosenProvince']").change(function(){
        		$("select[name='chosenCity']").children(".backreturnCity").remove();
        		$("select[name='chosenCounty']").children(".backreturnCounty").remove();
        		getCityList($(this).children('option:selected').val());
        	});
        	
        	$("select[name='chosenCity']").change(function(){
        		$("select[name='chosenCounty']").children(".backreturnCounty").remove();
        		getCountyList($(this).children('option:selected').val());
        	});
        });
        
        function getCityList(data){
        	$.post("business/deliveryuser/getZoneList.htm",{"zoneLevel":4,"zoneCode":data},function(result){
        		var optionHtml = "<option >--城市--</option>";
        		var resJson = eval("("+result+")");
        		$(resJson).each(function(){
        			if(originalCityZoneCode == this.code){
        				optionHtml += "<option value="+this.code+" selected='selected' class='backreturnCity'>"+this.name+"</option>";
        			}else{
	        			optionHtml += "<option value="+this.code+"  class='backreturnCity'>"+this.name+"</option>";
        			}
        		});
	        	$("select[name='chosenCity']").children().remove();
	        	$("select[name='chosenCity']").append(optionHtml);
        	});
        }
        
        function getCountyList(data){
        	$.post("business/deliveryuser/getZoneList.htm",{"zoneLevel":5,"zoneCode":data},function(result){
        		var optionHtml = "<option>--区县--</option>";
        		var resJson = eval("("+result+")");
        		$(resJson).each(function(){
        			if(originalCountyZoneCode == this.code){
        				optionHtml += "<option value="+this.code+" selected='selected' class='backreturnCounty'>"+this.name+"</option>";
        			}else{
        				optionHtml += "<option value="+this.code+"  class='backreturnCounty'>"+this.name+"</option>"
        			}
        		});
	        	
        		$("select[name='chosenCounty']").children().remove();
        		$("select[name='chosenCounty']").append(optionHtml);
        	});
        }
        
        function h2y_save() {
        	
            var op = $("input[name='op']").val();
            var objectIds = $("input[name='objectIds']").val();
            var checkStatus = $("input[name='checkStatus']:checked").val();
            var chosenProvince = $("select[name='chosenProvince']").children('option:selected').val(); 
            var chosenCity = $("select[name='chosenCity']").children('option:selected').val(); 
            var chosenCounty = $("select[name='chosenCounty']").children('option:selected').val(); 
            var chosenZone = chosenProvince;
            
            if(chosenCity > 0){
            	chosenZone = chosenCity;
            }
            if(chosenCounty > 0){
            	chosenZone = chosenCounty;
            }
            
            
            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            $.post("business/deliveryuser/save.htm", {"op":op,"objectIds":objectIds,"checkStatus":checkStatus,"chosenZone":chosenZone}, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == "1") {
                	
                    alert(jsonReturn.msg);
                    parent.f_refresh();
                    frameElement.dialog.close();
                } else {
                    alert(jsonReturn.msg);
                }
                isSubmiting = false;
            });
        }
        
        
        //门店下拉列表
		function addselect(){
			var unitId = $("#unitId").val();
			$("#shopTd").text("");
			
			if(unitId==""){
				return;
			}
			
			var shopId = "${deliveryUser.shopId}";
			
			$.post("business/deliveryuser/getShopList.htm",{unitId:unitId},function(data){
				
				var jsonReturn=eval("("+data+")");
				var selectHtml = "";
				$(jsonReturn).each(function(){
					if(shopId == this.value){
						selectHtml += "<option selected=\"selected\" value=\""+this.value+"\">"+this.text+"</option>";
					}else{
						selectHtml += "<option value=\""+this.value+"\">"+this.text+"</option>";
					}
					
				});
				
				if(selectHtml!=""){
					selectHtml = "<select name=\"shopId\" id=\"shopId\" onchange=\"fillingShopId(this.value)\" >"+selectHtml+"</select>";
					$("#shopTd").append(selectHtml+"");					
				}
			}); 
		}
    </script>

</head>

<body>
<form name="editform" method="post" action="" id="editform">
    <div>
        <input type="hidden" name="objectIds" value="${deliveryUser.id}"/>
        <input type="hidden" name="op" value="${op}"/>
    </div>
    <table class="h2y_dialog_table">

        <tr>
            <td class="h2y_table_label_td">选择区域：</td>
            <td class="h2y_dialog_table_edit_td" id="chosenRegion">
				<select name="chosenProvince">
	            	<c:if test="${fn:length(provinceList) gt 0}">
				        	<c:forEach items="${provinceList }" var="province">
				        		<c:if test="${province.code == provinceZone}">
									<option value="${province.code }" selected="selected" >${province.name }</option>	            		
				        		</c:if>
				        		<c:if test="${province.code != provinceZone }">
									<option value="${province.code }" >${province.name }</option>	            		
				        		</c:if>
				        	</c:forEach>
	            	</c:if>
				</select>
            	
				<select name="chosenCity">
					<option>--城市--</option>
	            	<c:if test="${fn:length(cityList) gt 0}">
			            <c:forEach items="${cityList }" var="city">
			            	<c:if test="${city.code == cityZone}">
								<option value="${city.code }" selected="selected" class="backreturnCity">${city.name }</option>	            		
				        	</c:if>
				        	<c:if test="${city.code != cityZone }">
								<option value="${city.code }" class="backreturnCity">${city.name }</option>	            		
				        	</c:if>
			            </c:forEach>
					</c:if>
				</select>
            	
				<select name="chosenCounty">
					<option>--区县--</option>
	            	<c:if test="${fn:length(countyList) gt 0}">
			    		<c:forEach items="${countyList }" var="county">
			        		<c:if test="${county.code == countyZone}">
								<option value="${county.code }" selected="selected" class="backreturnCounty">${county.name }</option>	            		
				        	</c:if>
				        	<c:if test="${county.code != countyZone }">
								<option value="${county.code }" class="backreturnCounty">${county.name }</option>	            		
				        	</c:if>
			            </c:forEach>
		            </c:if>
				</select>
            </td>
            
        </tr>
        
        <%-- 
        <tr>
            <td class="h2y_table_label_td">门店:</td>
            <td class="h2y_dialog_table_edit_td" id="shopTd">
            	
            </td>
        </tr> --%>
        
        <tr>
            <td class="h2y_table_label_td">状态:</td>
            <td class="h2y_dialog_table_edit_td">
            	<h2y:input name="checkStatus" id="checkStatus" type="radio" initoption="1,审核通过:-1,审核不通过" value="${deliveryUser.checkStatus}" />
            </td>
        </tr>
        
    </table>
</form>

</body>
</html>