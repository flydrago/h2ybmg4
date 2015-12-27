<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />

		<title>混合选择窗口</title>

		<%@ include file="../../include/top_list.jsp"%>
		
		
		<script type="text/javascript">

			var manager = null;
			var deptCode="";  
			var P_ID = -1;    
          	var isCascade = "no";
          	var choseFlag = "p_";//p_:用户、d_：部门
          	var deptId = 0;
			$(function(){
				try{
					
					if(null!=parent.mixDataJson && undefined!=parent.mixDataJson){
						$(parent.mixDataJson).each(function(){

							if(this.FLAG=="p_"){
								$("#objcolums").append("<option value=\""+this.ID+"\" iflag=\""+this.FLAG+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
							}else if(this.FLAG=="d_"){
								$("#objcolums").append("<option value=\""+this.ID+"\" iflag=\""+this.FLAG+"\" iname=\""+this.NAME+"\">"+this.NAME+"</option>");
							}
						})
					}
                	show_title("oAllsearchcolums");
				}catch(e){
					
                }

				//按用户选择
				$("#chose_people").click(function(){
                    
					if(this.checked) choseFlag = "p_";
				})
				
				//按部门选择
				$("#chose_department").click(function(){
                    
					if(this.checked) choseFlag = "d_";
				})

			    $("#layout1").ligerLayout({topHeight:30,height:270, bottomHeight:30,leftWidth: 280,centerWidth: 200,rightWidth : 200,allowLeftCollapse:false,allowRightCollapse:false});
      
	            $("#tree1").ligerTree({  
			        checkbox: false,
			        nodeWidth: 120,
			        onSelect: f_onSelect,
			        childIcon:'folder',
			        idFieldName:'id',
					parentIDFieldName:'pid',
					textFieldName:'text',
			        data: ${treedata}
			    });
		    
				$("#oAllsearchcolums").dblclick(function(){
             
					var sed=$("option:selected",this).attr("selected",false);
					
					if($("#objcolums option[value='"+sed.val()+"']").length==0)
						$("#objcolums").append(sed);
					getPeopleNumber();
				});
            
				$("#objcolums").dblclick(function(){ 
					var oTo_sed =$("option:selected",this).attr("selected",false);
					if($("#oAllsearchcolums option[value='"+oTo_sed.val()+"']").length!=0){
		               oTo_sed.remove();
		            }else{
		               $("#oAllsearchcolums").append(oTo_sed);
		            }
	
	             	getPeopleNumber();
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
					getPeopleNumber();
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
					getPeopleNumber();
				})
				
				//是否级联
				$("#is_cascade").change(function(){
					
					isCascade = this.checked?"yes":"no";
				});

				f_query(1);
				$(".l-layout-left").children(".l-layout-header").css({"display":"none"});
				$(".l-layout-right").children(".l-layout-header").css({"display":"none"});
				$(".l-panel").css({"border":"0px"});
				$(".l-layout-center").css({"border":"1px solid #BED5F3"});
				getPeopleNumber();
			});  
          
			function f_onSelect(node){

				if(node==null || node.data==null || node.data.id==null) return;
		    	deptId = node.data.id;
		    	deptCode= node.data.code;
				var treemanager= $("#tree1").ligerGetTreeManager();
				var haschildren=node.data.children ? true : false;
		    	if(!haschildren){
					$.ajax({
						type: "POST",
						url: "sys/department/getList.htm?op=tree",
						data: {pid:deptId},
			     		success: function(jsondata){
							var nodes=eval("("+jsondata+")");		     	      
							treemanager.append(node.target, nodes);
			        	}
			    	});
				}
            	f_query(1);
			}
	      
			function hwtt_refresh(){
				document.location.reload();
			}
        
			function f_query(flag){    

				//如果是点击树节点，并且人员和部门未被选中
				if(flag==1 && choseFlag!="p_" && choseFlag!="d_") return;

				var url = "";
				var params = null;

				url = "sys/dialog/getList.htm";
				if(choseFlag=="p_"){
					
					params = {deptCode:deptCode,deptId:deptId,isCascade:isCascade,op:"user"};
				}else if(choseFlag=="d_"){
					
					params = {deptCode:deptCode,deptId:deptId,isCascade:isCascade,op:"dept"};
				}
				
				$.post(url, params, function (data){
					
					var search_data=eval(data);  
					$("#oAllsearchcolums").empty();

					if(search_data.length>0){

						$(search_data).each(function(){

							if(choseFlag=="p_"){
								$("#oAllsearchcolums").append("<option value=\""+choseFlag+this.ID+"\" iflag=\""+choseFlag+"\" iname=\""+this.USER_NAME+"\">"+this.USER_NAME+"</option>");
							}else if(choseFlag=="d_"){
								$("#oAllsearchcolums").append("<option value=\""+choseFlag+this.DEPT_CODE+"\" iflag=\""+choseFlag+"\" iname=\""+this.DEPT_NAME+"\">"+this.DEPT_NAME+"</option>");
							}
						});
					}
					show_title("oAllsearchcolums");
				});        
			}
			
			//搜索用户
			function searchuser(){

				var searchName = $("#searchName").val();
				if(searchName==""){
					alert("请输入用户名或部门名");
					return false;
				}
				var op = choseFlag=="p_" ? "user":"dept";
	
				var params = {searchName:searchName,deptCode:deptCode,deptId:deptId,isCascade:isCascade,op:op};
				
				$.post("sys/dialog/getList.htm",params,function (data){
	
					var search_data=eval(data);  
	            	if(search_data.length==0){
	                	$("#oAllsearchcolums").empty();
	            	}else{
	                	$("#oAllsearchcolums").empty();
	                	
						$(search_data).each(function(){
							if(choseFlag=="p_"){
								$("#oAllsearchcolums").append("<option value=\""+choseFlag+this.ID+"\" iflag=\""+choseFlag+"\" iname=\""+this.USER_NAME+"\">"+this.USER_NAME+"</option>");
							}else if(choseFlag=="d_"){
								$("#oAllsearchcolums").append("<option value=\""+choseFlag+this.DEPT_CODE+"\" iflag=\""+choseFlag+"\" iname=\""+this.DEPT_NAME+"\">"+this.DEPT_NAME+"</option>");
							}
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
						optionData = "{ID:\""+$(e).val()+"\",NAME:\""+$(e).attr("iname")+"\",FLAG:\""+iflag_+"\"}";
					}else{
						optionData = "{ID:\""+$(e).val()+"\",NAME:\""+$(e).attr("iname")+"\",FLAG:\""+iflag_+"\"}";
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
	
	        function removePeople(){
	
	            var oAll_sed =$("#oAllsearchcolums option:selected").attr("selected",false);
	            // alert($('#objcolums option[value='+oAll_sed.val()+']').length);
				if($("#objcolums option[value='"+oAll_sed.val()+"']").length!=0)
	
					$.each($("#objcolums option"),function(i,e){
	   					if($(e).val()==oAll_sed.val()){
							$(this).remove();
						}
					}); 
	
				getPeopleNumber();
			}
	         
			function getPeopleNumber(){
				var p_len=$("#objcolums option[iflag='p_']").length;
				var d_len=$("#objcolums option[iflag='d_']").length;
	            $("#selectPeopleNumber").html("");
	            $("#selectPeopleNumber").append("共选中"+p_len+"人"+"&nbsp;"+d_len+"部门");
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
				&nbsp;
				<input type="radio" name="chose_department_group"
					id="chose_people" checked="checked" />
				用户&nbsp;
				<input type="radio" name="chose_department_group"
					id="chose_department" />
				部门&nbsp;
				 用户名:
				<input type=text name='searchName'
					style="width: 110px; height: 16px;" id='searchName' />
				&nbsp;
				<input type="button" value='搜索' class="button" onclick='searchuser()' />
				&nbsp;&nbsp;<span style="color:red;">级联子级</span>
				<input id="is_cascade" type="checkbox" title="级联选择" class="hwtt_checkbox"/>
			</div>
			
			
			<div position="left" style="overflow: auto;width:278px; height: 95%;">
				<div id="choose_show_tree" style="display: inline;">
					<ul id="tree1"></ul>
				</div>
			</div>
			

			<div position="center">
				<select id="oAllsearchcolums" class="oAllsearchcolums_css" multiple></select>
			</div>
			
			
			<div position="right">
				<select id="objcolums" class="objcolums_css" multiple></select>
			</div>
			 
			 
			<div position="bottom" style="text-align: center; padding-top: 2px;">
				<div id="selectPeopleNumber"
					style="float: right; padding-right: 5px; padding-top: 8px; color: red;"></div>
				<div style="float: left; padding-left: 200px;">
					<input class="button" type="button" value="添加" id="Add" />&nbsp;&nbsp;
					<input class="button" type="button" value="移除" id="Delete" />
				</div>
				
			</div>
		</div>
	</body>

</html>
