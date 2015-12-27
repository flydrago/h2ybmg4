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
          	var typeId = 0;
          	var selectType = "${selectType}";
          	var callBackFlag = "${callBackFlag}";
			$(function(){
				try{
					if("1" == callBackFlag){
						if(null!=parent.goodsDataJson1 && undefined!=parent.goodsDataJson1){
							$(parent.goodsDataJson1).each(function(){
								$("#objcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
							});
						}
					}else if("2" == callBackFlag){
						if(null!=parent.goodsDataJson2 && undefined!=parent.goodsDataJson2){
							$(parent.goodsDataJson2).each(function(){
								$("#objcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
							});
						}
					}else if("3" == callBackFlag){
						if(null!=parent.goodsDataJson3 && undefined!=parent.goodsDataJson3){
							$(parent.goodsDataJson3).each(function(){
								$("#objcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
							});
						}
					}else if("4" == callBackFlag){
						if(null!=parent.goodsDataJson4 && undefined!=parent.goodsDataJson4){
							$(parent.goodsDataJson4).each(function(){
								$("#objcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
							});
						}
					}else{
							if(null!=parent.goodsDataJson && undefined!=parent.goodsDataJson){
								$(parent.goodsDataJson).each(function(){
									$("#objcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
								});
							}
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
					
					if(selectType=="radio"){
						$("#objcolums").empty();  
					}
					var sed=$("option:selected",this).attr("selected",false);
					
					if($("#objcolums option[value='"+sed.val()+"']").length==0)
						$("#objcolums").append(sed);
					
					getGoodsNumber();
				});
            
				$("#objcolums").dblclick(function(){ 
					var oTo_sed =$("option:selected",this).attr("selected",false);
					if($("#oAllsearchcolums option[value='"+oTo_sed.val()+"']").length!=0){
		               oTo_sed.remove();
		            }else{
		               $("#oAllsearchcolums").append(oTo_sed);
		            }
	
	             	getGoodsNumber();
				});
             
             
				//点击添加方法
				$('#Add').bind('click',function(){
					
					//确保第一次快一点
					if($('#objcolums option').length>0){
						
						$("#oAllsearchcolums option:selected").each(function(){
							  //公司还未被选中
							  if($('#objcolums option[value='+$(this).val()+']').length!=0){
								  $(this).remove();
							  }
						});
					}
					
					//优化
					$('#objcolums').append($("#oAllsearchcolums option:selected"));
					getGoodsNumber();
				});
         
				//点击删除方法
				$('#Delete').bind('click',function(){

					//确保第一次快一点
					if($('#oAllsearchcolums option').length>0){

						$("#objcolums option:selected").each(function(){

							if($('#oAllsearchcolums option[value='+$(this).val()+']').length!=0){
								$(this).remove();
							}
						});
					}
					$('#oAllsearchcolums').append($("#objcolums option:selected"));
					getGoodsNumber();
				});
				
				//f_query();
				
				
				$(".l-layout-left").children(".l-layout-header").css({"display":"none"});
				$(".l-layout-right").children(".l-layout-header").css({"display":"none"});
				$(".l-panel").css({"border":"0px"});
				$(".l-layout-center").css({"border":"1px solid #BED5F3"});
				getGoodsNumber();
				
				
				if(selectType=="radio"){
					$("#Add").hide();
					$("#Delete").hide();
				}
			});  
          
			function f_onSelect(node){

				if(node==null || node.data==null || node.data.id==null) return;
		    	typeId = node.data.id;
				var treemanager= $("#tree1").ligerGetTreeManager();
            	f_query();
			}
	      
			function hwtt_refresh(){
				document.location.reload();
			}
        
			function f_query(){    

				var url  = "sys/dialog/getGoodsList.htm";
				var params = {typeId:typeId};
				if("4" == callBackFlag){
					params = {typeId:typeId,goodsUnit:1};
				}
				
				$.post(url, params, function (data){
					
					var search_data=eval(data);  
					$("#oAllsearchcolums").empty();
					if(search_data.length>0){

						$(search_data).each(function(){

							$("#oAllsearchcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
						});
					}
					show_title("oAllsearchcolums");
				});        
			}
			
			//搜索用户
			function searchgoods(){

				var searchName = $("#searchName").val();
				if(searchName==""){
					alert("请输入商品名称！");
					return false;
				}
	
				var params = {searchName:searchName,typeId:typeId};
				//限制规格
				if("4" == callBackFlag){
					params = {searchName:searchName,typeId:typeId,goodsUnit:1};
				}
				
				$.post("sys/dialog/getGoodsList.htm",params,function (data){
	
					var search_data=eval(data);  
	            	if(search_data.length==0){
	                	$("#oAllsearchcolums").empty();
	            	}else{
	                	$("#oAllsearchcolums").empty();
						$(search_data).each(function(){
							$("#oAllsearchcolums").append("<option value=\""+this.ID+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
						});
					}
	            	show_title("oAllsearchcolums");
				});
	        }
	        
	
			function  h2y_returnData(){
				var json="";
				
				$.each($("#objcolums option"),function(i,e){

					var iflag_ = $(e).attr("iflag");

					var optionData = "";

					if(iflag_ == "p_"){
						optionData = "{ID:\""+$(e).val()+"\",NAME:\""+$(e).attr("iname")+"\"}";
					}else{
						optionData = "{ID:\""+$(e).val()+"\",NAME:\""+$(e).attr("iname")+"\"}";
					}

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
	            $("#getGoodsNumber").append("共选中"+p_len+"商品");
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
				 商品名称:
				<input type=text name='searchName'
					style="width: 110px; height: 16px;" id='searchName' />
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
					<input class="button" type="button" value="添加" id="Add" />&nbsp;&nbsp;
					<input class="button" type="button" value="移除" id="Delete" />
				</div>
			</div>
		</div>
	</body>

</html>
