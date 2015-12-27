<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />

		<title>商品选择窗口</title>

		<%@ include file="../../include/top_list.jsp"%>
		
		<script type="text/javascript">
			var manager = null;
          	var markId = 0;
          	var typeCode = "${typeCode}";
			$(function(){
				try{
					if(null!=parent.markDataJson && undefined!=parent.markDataJson){
						$(parent.markDataJson).each(function(){
							$("#objcolums").append("<option value=\""+this.ID+"\" imarkname=\""+this.MARK_NAME+"\" imarkid=\""+this.MARK_ID+"\" iname=\""+this.NAME+"\">"+this.MARK_NAME+"--"+this.NAME+"</option>");
						})
					}
                	show_title("oAllsearchcolums");
				}catch(e){
					
                }

			    $("#layout1").ligerLayout({topHeight:30,height:270, bottomHeight:30,leftWidth: 200,centerWidth: 240,rightWidth : 240,allowLeftCollapse:false,allowRightCollapse:false});
      
	            $("#tree1").ligerTree({  
			        checkbox: false,
			        onSelect: f_onSelect,
			        childIcon:'folder',
			        idFieldName:'id',
					parentIDFieldName:'pid',
					textFieldName:'text',
			        data: ${treedata}
			    });
		    
				$("#oAllsearchcolums").dblclick(function(){
					
					var sed=$("option:selected",this).attr("selected",false);
					$("#objcolums option[imarkid='"+sed.attr("imarkid")+"']").remove();
					$("#objcolums").append(sed);
					getGoodsNumber();
				});
            
				$("#objcolums").dblclick(function(){ 
					
					var oTo_sed =$("option:selected",this).attr("selected",false);
					oTo_sed.remove();
	             	getGoodsNumber();
				});
             
				//f_query();
				
				
				$(".l-layout-left").children(".l-layout-header").css({"display":"none"});
				$(".l-layout-right").children(".l-layout-header").css({"display":"none"});
				$(".l-panel").css({"border":"0px"});
				$(".l-layout-center").css({"border":"1px solid #BED5F3"});
				getGoodsNumber();
				
			});  
          
			function f_onSelect(node){

				if(node==null || node.data==null || node.data.id==null) return;
		    	markId = node.data.id+"";
		    	if(markId=="0"){
		    		$("#oAllsearchcolums").empty();
		    		return;
		    	}
				var treemanager= $("#tree1").ligerGetTreeManager();
            	f_query();
			}
	      
			function hwtt_refresh(){
				document.location.reload();
			}
        
			function f_query(){    

				var url  = "business/goodsmarkinfo/getSelectList.htm";
				var params = {markId:markId};
				
				$.post(url, params, function (data){
					
					var search_data=eval(data);  
					$("#oAllsearchcolums").empty();
					if(search_data.length>0){

						$(search_data).each(function(){

							$("#oAllsearchcolums").append("<option value=\""+this.ID+"\" imarkname=\""+this.MARK_NAME+"\" imarkid=\""+this.MARK_ID+"\" iname=\""+this.NAME+"\">"+this.MARK_NAME+"--"+this.NAME+"</option>");
						});
					}
					show_title("oAllsearchcolums");
				});        
			}
			
			//搜索
			function searchgoods(){

				var searchName = $("#searchName").val();
				if(searchName==""){
					alert("请输入名称！");
					return;
				}
				
				if(markId==0){
					alert("请选择标签！");
					return;
				}
	
				var params = {searchName:searchName,markId:markId};
				
				$.post("business/goodsmarkinfo/getSelectList.htm",params,function (data){
	
					var search_data=eval(data);  
	            	if(search_data.length==0){
	                	$("#oAllsearchcolums").empty();
	            	}else{
	                	$("#oAllsearchcolums").empty();
						$(search_data).each(function(){
							$("#oAllsearchcolums").append("<option value=\""+this.ID+"\" imarkname=\""+this.MARK_NAME+"\" imarkid=\""+this.MARK_ID+"\" iname=\""+this.NAME+"\">"+this.MARK_NAME+"--"+this.NAME+"</option>");
						});
					}
	            	show_title("oAllsearchcolums");
				});
	        }
	        
	
			function  h2y_returnData(){
				var json="";
				
				$.each($("#objcolums option"),function(i,e){

					var optionData = "";
					optionData = "{ID:\""+$(e).val()+"\",MARK_NAME:\""+$(e).attr("imarkname")+"\",MARK_ID:\""+$(e).attr("imarkid")+"\",NAME:\""+$(e).attr("iname")+"\"}";
					if(i==0){
						json = optionData;
					}else{
						json += ","+optionData;
					}
				});
				var callData=eval('(['+json+'])');
			    return callData;
			}
	
			function getGoodsNumber(){
				var p_len=$("#objcolums option").length;
	            $("#getGoodsNumber").html("");
	            $("#getGoodsNumber").append("共选中"+p_len+"标签");
			}
	
			function show_title(divid){
				try{
					$($("#"+divid).find("option")).each(function(){
						$(this).attr("title",this.text)
					});
				}catch(e){
					
				}
			}

		</script>

		<style type="text/css">
		
			.oAllsearchcolums_css {
				font-size: 13px;
				border:0px;
				width: 195px;
				height: 200px;
			}

			.objcolums_css {
				font-size: 13px;
				border:0px;
				width: 200px;
				height: 200px;
			}
			
		</style>
	</head>

	<body style="font-size: 12px; font-family: 宋体;">
		<div id="layout1">
			<div position="top" style="padding-top: 2px;text-align: center;">
				 名称:
				<input type=text name="searchName" class="h2y_input_just" id="searchName" />
				&nbsp;
				<input type="button" value='搜索' class="button" onclick="searchgoods()" />
			</div>
			
			<div position="left" style="overflow: auto;width:200px; height: 95%;">
				<div id="choose_show_tree" style="display: inline;">
					<ul id="tree1"></ul>
				</div>
			</div>
			

			<div position="center">
				<select id="oAllsearchcolums" class="oAllsearchcolums_css" style="width: 240px" multiple></select>
			</div>
			
			
			<div position="right">
				<select id="objcolums" class="objcolums_css" style="width: 240px" multiple></select>
			</div>
			 
			<div position="bottom" style="text-align:center; padding-top: 2px;">
				<div id="getGoodsNumber" style="float: right; padding-right: 5px; padding-top: 8px; color: red;"></div>
				<div style="float: left; padding-left: 200px;">
				</div>
			</div>
		</div>
	</body>

</html>
