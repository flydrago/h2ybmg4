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
	      
	/* 表格*/
	#date_cross_table{border:#D6DFF0 1px solid; width:800px; border-collapse:collapse; font:12px/24px verdana; margin: 30px  auto;}
	/* 表格*/
	.cross_td{ 
		text-align:center;  
		background: none repeat scroll 0 0 #fff;
	    padding: 4px 0px;	
		border: 1px solid #a3c0e8;
	    white-space: nowrap;
	}
	
	a:link,a:visited{
	 text-decoration:none;  /*超链接无下划线*/
	}
	a:hover{
	 text-decoration:underline;  /*鼠标放上去有下划线*/
	}
</style>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        
        var isCallBack = false;
        $(function () {

        	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
        	
            //form = $("form").ligerForm();
            
            if (op == "modify") {
            	
                $("#tr_next").hide();
                $("#goodsId").val(${goods.id});
            	$("#goodslistdiv").html("<a href=\"javaScript:h2y_detail(${goodsPrice.id});\">${goodsPrice.goodsNickName}<a/><br/>市场价：￥${goodsPrice.marketPrice}<br/>会员价：￥${goodsPrice.memberPrice}");
            }else{
            	$("input[type=radio][name='goodsLevel']:first").attr("checked",true);
            }
            
            $("#date_cross_table").hide();
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
           	$("#startDate").datetimepicker({});
            $("#endDate").datetimepicker({});
            
            $("#selectGoodsBut").click(function(){
            	openPriceGoodsSelectDialog();
            });
            
            $("#activityPrice").ligerSpinner({type: 'float' ,height: 25,width:194,isNegative:false});
            $("#ord").ligerSpinner({type: 'int' ,height: 25,width:194,isNegative:false});
        });
        
        function rewardTypeChangeDo(rewardType){
        	
        	if(rewardType=="0"){
        		$("#rewardNumber").ligerSpinner({disabled:true});
        	}else{
        		$("#rewardNumber").ligerSpinner({disabled:false});
        	}
        }


        function h2y_save() {
        	
			if(!Validator.form()) return;
			
			if($("#goodsPriceId").val()==0){
				
				alert("请选择商品！");
				return;
			}
			
			if(!compareTime("startDate", "endDate")) return;
			
            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/commonactivitygoodsrt/save.htm", queryString, function (data) {

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
                        
                        if("${commonActivityGoodsRt.dataType}"==0){
                        	if (top.f_getframe("business_commonactivitygoodsrt_init_htm") != null) {
     							top.f_getframe("business_commonactivitygoodsrt_init_htm").f_query();
     						}
                        }else{
                        	if (top.f_getframe("business_commonsubject_add_${commonActivityGoodsRt.dataId}") != null) {
     							top.f_getframe("business_commonsubject_add_${commonActivityGoodsRt.dataId}").f_query();
     						}
                        }
						top.f_delTab("business_commonactivitygoodsrt_add_${commonActivityGoodsRt.dataId}_${commonActivityGoodsRt.dataType}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        if("${commonActivityGoodsRt.dataType}"==0){
                        	if (top.f_getframe("business_commonactivitygoodsrt_init_htm") != null) {
     							top.f_getframe("business_commonactivitygoodsrt_init_htm").f_query();
     						}    
                        }else{
                        	if (top.f_getframe("business_commonsubject_add_${commonActivityGoodsRt.dataId}") != null) {
     							top.f_getframe("business_commonsubject_add_${commonActivityGoodsRt.dataId}").f_query();
     						}
                        }
                        top.f_delTab("business_commonactivitygoodsrt_modify_${commonActivityGoodsRt.id}_${commonActivityGoodsRt.dataType}");
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
		
        function h2y_refresh() {
            document.location.reload();
        }
        
        //商品选择回调函数
        function h2y_priceGoodsSelectCallBack(data){
        	
        	if(data==null || data.length==0) return;
        	//alert(JSON.stringify(data));
        	$("#goodsId").val(data.GOODS_ID);
        	$("#goodsPriceId").val(data.ID);
        	$("#goodslistdiv").html("<a href=\"javaScript:h2y_detail("+data.ID+");\">"+data.GOODS_NICK_NAME+"</a><br/>市场价：￥"+data.MARKET_PRICE+"<br/>会员价：￥"+data.MEMBER_PRICE);
        	if('${commonActivityGoodsRt.dataType}' != 2){
        		$("#activityPrice").val(data.MEMBER_PRICE);
        	}
        	
        }
        
		//查看商品详细
		function h2y_detail(id) {
	
	    	var src = "<%=basePath%>business/goodsprice/addPrice.htm?op=detail&id="+id;
	        top.f_addTab("goodsprice_detail_price"+id,"商品详细", src);
	    }
        
        
        function f_getList() {
        	
            var url_1 = "business/commonactivitygoodsrt/getDateCrossList.htm";

            $("#listgrid").ligerGrid({
                columns: [{display: "活动名称", name: "TITLE", width: 150, align: "left", type: "string", isSort:true},
                          {display: "商品名称", name: "GOODS_NICK_NAME", width: 200, align: "left", type: "string", isSort:true},
                          {display: "商品类型", name: "GOODS_TYPE_NAME", width: 100, align: "left", type: "string", isSort:true},
                          {display: "开始时间", name: "START_DATE", width: 150, align: "center", type: "string", isSort:true},
                          {display: "结束时间", name: "END_DATE", width: 150, align: "center", type: "string", isSort:true}],
                url: url_1,
                parms: [{name:"op",value:"${op}"},
                        {name:"startDate",value:$("#startDate").val()},
                        {name:"endDate",value:$("#endDate").val()},
                        {name:"goodsPriceId",value:$("#goodsPriceId").val()},
                        {name:"id",value:$("#id").val()}],
                showTitle: false,
                dataAction: "server",
                sortName: "CREATE_DATE",
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
		
		<input name="id" type="hidden" id="id" value="${commonActivityGoodsRt.id}" />
		<input name="dataId" type="hidden" id="dataId" value="${commonActivityGoodsRt.dataId}" />
		<input name="dataType" type="hidden" id="dataType" value="${commonActivityGoodsRt.dataType}" />
		<input name="goodsId" type="hidden" id="goodsId" value="${commonActivityGoodsRt.goodsId}" />
		<input name="goodsPriceId" type="hidden" id="goodsPriceId" value="${commonActivityGoodsRt.goodsPriceId}" />
		<input name="op" type="hidden" id="op" value="${op}" />
			
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					<input class="button" type="button" value="选择商品" id="selectGoodsBut"/>:
				</td>
				<td class="h2y_table_edit_td2" colspan="3"> 
					<div id="goodslistdiv" style="width:650px;word-wrap:break-word; word-break:break-all;"></div>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					活动价格：
				</td>
				<td class="h2y_table_edit_td">
					<input name="activityPrice" id="activityPrice" class="h2y_input_just" type="input" value="${commonActivityGoodsRt.activityPrice}" <c:if test="${commonActivityGoodsRt.dataType == 0 && dataActivityType == 2}">disabled=true</c:if>/>
				</td>
				<td class="h2y_table_label_td">
					商品级别：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="goodsLevel" id="goodsLevel" type="radio" initoption="1,一般:2,较高:3,高:4,非常高" value="${commonActivityGoodsRt.goodsLevel}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					时间设置：
				</td>
				<td class="h2y_table_edit_td" colspan="3">
					
					<input name="startDate" id="startDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${commonActivityGoodsRt.startDate}"  pattern="yyyy-MM-dd HH:mm"/>" <c:if test="${isSpike == 1}">disabled=true</c:if>/>
					~
					<input name="endDate" id="endDate" class="h2y_input_datetime" type="input" value="<fmt:formatDate value="${commonActivityGoodsRt.endDate}"  pattern="yyyy-MM-dd HH:mm"/>" <c:if test="${isSpike == 1}">disabled=true</c:if>/>
					<span style="color: red;">活动时间一般在两分钟内生效</span>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					排序：
				</td>
				<td class="h2y_table_edit_td">
					<input name="ord" id="ord" class="h2y_input_just" type="input" value="${commonActivityGoodsRt.ord}"/>
				</td>
				<td class="h2y_table_label_td">
					状态：
				</td>
				<td class="h2y_table_edit_td">
					<h2y:input name="status" id="status" type="radio" initoption="0,启用:1,不启用" value="${commonActivityGoodsRt.status}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${commonActivityGoodsRt.memo}"  class="h2y_input_long"/>
				</td>
			</tr>
		</table>
		
		<table id="date_cross_table">
			
			<caption><h2>日期冲突的商品</h2></caption>
			<tr>
				<td class="cross_td">
					<span style="color:red;">请根据下面冲突的商品活动时间，设置当前商品活动时间以免时间冲突</span>
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
		
	</form>
</body>
</html>