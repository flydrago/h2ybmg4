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
	 .androidLogoImg { 
	 	max-width:500px;
	 }
	 
	 .iosLogoImg { 
	 	max-width:500px;
	 }
	 
	 .wechatLogoImg { 
	 	max-width:500px;
	 }
	 
	 
	 
	/* 表格*/
	#date_cross_table{border:#D6DFF0 1px solid; border-collapse:collapse; font:12px/24px verdana; margin: 30px  auto;}
	/* 表格*/
	.cross_td{ 
		text-align:center;  
		background: none repeat scroll 0 0 #fff;
	    padding: 4px 0px;	
		border: 1px solid #a3c0e8;
	    white-space: nowrap;
	}
</style>	
<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}"
        var form = null;
        
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            //form = $("form").ligerForm();
            
            if (op == "modify") {
                subjectModify();
            }
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            var startLimitDate = "<fmt:formatDate value="${advertColumnUnit.startDate}"  pattern="yyyy-MM-dd HH:mm"/>";
            var endLimitDate = "<fmt:formatDate value="${advertColumnUnit.endDate}"  pattern="yyyy-MM-dd HH:mm"/>";
            
            
            if(startLimitDate!="" && endLimitDate!=""){
            	
            	 $("#startDate").datetimepicker({
             		minDate: new Date(strToDateTime(startLimitDate)),
             		maxDate: new Date(strToDateTime(endLimitDate))
             	 });
             
	             $("#endDate").datetimepicker({
	         		minDate: new Date(strToDateTime(startLimitDate)),
	         		maxDate: new Date(strToDateTime(endLimitDate))
	             });
            }else{
            	 $("#startDate").datetimepicker({});
	             $("#endDate").datetimepicker({});
            }
            
            $("#repeatStart").timepicker({
         	   hourGrid: 4,
         	   minuteGrid: 10
            });
            
            $("#repeatEnd").timepicker({
         	   hourGrid: 4,
         	   minuteGrid: 10
            });
            
            
            if(${advertColumnSubjectRt.isDefault==1}){
            	$("#startDate").attr("disabled",true);
        		$("#endDate").attr("disabled",true);
            	$("#repeatStart").attr("disabled",true);
        		$("#repeatEnd").attr("disabled",true);
            }else{
            	$("#isDefault_label").hide();
            }
            
            $("input[type=radio][name='isDefault']").change(function(){
            	
            	if(this.value == 1){
            		$("#isDefault_label").show();
            		$("#startDate").attr("disabled",true);
            		$("#endDate").attr("disabled",true);
            		$("#repeatStart").attr("disabled",true);
            		$("#repeatEnd").attr("disabled",true);
            	}else{
            		$("#isDefault_label").hide();
            		$("#startDate").attr("disabled",false);
            		$("#endDate").attr("disabled",false);
            		$("#repeatStart").attr("disabled",false);
            		$("#repeatEnd").attr("disabled",false);
            	}
            });

            
            $("#advertSubjectBut").click(function(){
            	openAdvertSubjectSelectDialog();
            });
            
            $("#date_cross_table").hide();
        });


        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if(undefined==$("#subjectId").val() || ""==$("#subjectId").val() || "0"==$("#subjectId").val()){
				alert("请选择主题！");
				return;
			}
			
			if($("input[type=radio][name='isDefault']:checked").val()==0){
				if(!compareTime("startDate","endDate")){
					return;
				}
				
				if(!compareDay("repeatStart","repeatEnd")){
					return;
				}
			}

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/advertcolunmsubjectrt/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                
                if(jsonReturn.code == "-1"){
                	$("#date_cross_table").show();
                	f_getList();
                }else{
                	$("#date_cross_table").hide();
                }
                
                
                if (op == "add") {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_advertcolunmsubjectrt_init_htm") != null) {
							top.f_getframe("business_advertcolunmsubjectrt_init_htm").f_query();
						}
						top.f_delTab("business_advertcolunmsubjectrt_add${advertColumnSubjectRt.columnId}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if (top.f_getframe("business_advertcolunmsubjectrt_init_htm") != null) {
							top.f_getframe("business_advertcolunmsubjectrt_init_htm").f_query();
						}             
                        top.f_delTab("business_advertcolunmsubjectrt_modify${advertColumnSubjectRt.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
        
		function compareTime(startId, endId) { 
			
			var startDate = $("#"+startId).val();
			var endDate = $("#"+endId).val();
			if(startDate==null || endDate==null || startDate=="" || endDate==""){
				alert("时间不能为空！");
				return false;
			} 
			startDate = startDate.replace(/[:-\s]/g,"");
			endDate = endDate.replace(/[:-\s]/g,"");
			if (parseInt(startDate) >= parseInt(endDate)) {   
		        alert("开始时间不能大于截止时间！");   
		        return false;   
			} 
		    return true;   
		}
		
		function compareDay(startId, endId){
			
			var startDate = $("#"+startId).val();
			var endDate = $("#"+endId).val();
			if(startDate==null || endDate==null || startDate=="" || endDate==""){
				alert("日时间段不能为空！");
				return false;
			} 
			startDate = startDate.replace(/[:-\s]/g,"");
			endDate = endDate.replace(/[:-\s]/g,"");
		    if(parseInt(startDate)>= parseInt(endDate)){
		    	alert("日开始时间段不能小于截止时间段！");
		    	return false;
		    }
		    return true;   
		}
		
		
		function strToDateTime(dateStr) { 
			
		    var startDateTemp = dateStr.split(" ");   

		    var arrStartDate = startDateTemp[0].split("-");   

		    var arrStartTime = startDateTemp[1].split(":");   

			var allStartDate = new Date(arrStartDate[0], arrStartDate[1]-1, arrStartDate[2], arrStartTime[0], arrStartTime[1]);   
		    return allStartDate;   
		}

        function h2y_refresh() {
            document.location.reload();
        }
        
        function h2y_advertSubjectSelectCallBack(data){
        	
        	if(data==null) return;
        	$("#subject_div").html("<span class=\"h2y_star\">"+data.SUBJECT_NAME+"</span></br>");
        	$([{"key":"android","value":"安卓Logo"},{"key":"ios","value":"苹果Logo"},{"key":"wechat","value":"微信Logo"}]).each(function(){
        		$("#subject_div").append("<img title=\""+this.value+"\" alt=\""+this.value+"\" class=\""+this.key+"LogoImg\" src=\"common/image/view.htm?fileBean=advertSubjectService&id="+data.ID+"&type="+this.key+"\">");
        	});
        	$("#subjectId").val(data.ID);
        }
        
        function subjectModify(){
        	
           	$("#subject_div").html("<span class=\"h2y_star\">${advertSubject.subjectName}</span></br>");
           	$([{"key":"android","value":"安卓Logo"},{"key":"ios","value":"苹果Logo"},{"key":"wechat","value":"微信Logo"}]).each(function(){
           		$("#subject_div").append("<img title=\""+this.value+"\" alt=\""+this.value+"\" class=\""+this.key+"LogoImg\" src=\"common/image/view.htm?fileBean=advertSubjectService&id=${advertSubject.id}&type="+this.key+"\">");
           	});
           	$("#subjectId").val(${advertSubject.id});
        }
        
        
  		function f_getList() {
        	
            var url_1 = "business/advertcolunmsubjectrt/getDateCrossList.htm";

            $("#listgrid").ligerGrid({
                columns: [${gridComluns}],
                url: url_1,
                parms: [{name:"op",value:"${op}"},
                        {name:"columnId",value:$("#columnId").val()},
                        {name:"startDate",value:$("#startDate").val()},
                        {name:"endDate",value:$("#endDate").val()},
                        {name:"repeatStart",value:$("#repeatStart").val()},
                        {name:"repeatEnd",value:$("#repeatEnd").val()},
                        {name:"id",value:$("#id").val()}],
                showTitle: false,
                dataAction: "server",
                pageSize: 20,
                height: "100%",
                width: "100%",
                usePager: true,
                pageSizeOptions: [20, 30, 50, 100],
                onSelectRow: function (row, index, data) {
                    id = row.ID;
                },
                onDblClickRow: function (row, index, data) {
                    //alert("行双击事件");
                }
            });
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
		
		<input name="id" type="hidden" id="id" value="${advertColumnSubjectRt.id}" />
		<input name="columnId" type="hidden" id="columnId" value="${advertColumnSubjectRt.columnId}" />
		<input name="subjectId" type="hidden" id="subjectId" value="${advertColumnSubjectRt.subjectId}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					<input type="button" value="广告主题" class="button" id="advertSubjectBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<div id="subject_div">
						
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					<input name="memo" id="memo" type="input" value="${advertColumnSubjectRt.memo}"  class="h2y_input_long"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td">
					<input name="startDate" id="startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${advertColumnSubjectRt.startDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>~
					<input name="endDate" id="endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${advertColumnSubjectRt.endDate}"  pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
				<td class="h2y_table_label_td">
					日重复：
				</td>
				<td class="h2y_table_edit_td">
					<input name="repeatStart" id="repeatStart" style="width: 60px;" class="h2y_input_date" type="input" value="${advertColumnSubjectRt.repeatStart}"/>~
					<input name="repeatEnd" id="repeatEnd" style="width: 60px;" class="h2y_input_date" type="input" value="${advertColumnSubjectRt.repeatEnd}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					是否默认：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<h2y:input name="isDefault" id="isDefault" type="radio" initoption="0,否:1,是" value="${advertColumnSubjectRt.isDefault}"/><span id="isDefault_label" style="color:#FF0000;">选择默认后，当前广告栏会一直显示当前广告主题</span>
				</td>
			</tr>
		</table>
	</form>
	
	<table id="date_cross_table">
		<caption><h2>日期冲突的主题</h2></caption>
		<tr>
			<td class="cross_td">
				<span style="color:red;">请根据下面冲突的主题时间，设置当前主题显示时间以免时间冲突</span>
			</td>
		</tr>
		<tr>
			<td class="cross_td">
				<div position="center" title="">
		  			<div id="listgrid"></div>
		  		</div>
			</td>
		</tr>
	</table>
</body>
</html>