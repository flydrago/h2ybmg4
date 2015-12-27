<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>

<script type="text/javascript">
        var Validator = null;
        var isSubmiting = false;
        var op = "${op}";
        var form = null;
        $(function () {

            if (op == "modify") {
            	
            	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '发起审核' , click: h2y_start , icon:'start' },{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
                h2y_priceGoodsSelectModify();
            }else{
            	
            	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '保存' , click: h2y_save , icon:'save' },{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
            }
            
            $("#singlePrice").ligerSpinner({type: 'float' ,height: 25,minValue:0});
            $("#data4").ligerSpinner({type: 'int' ,height: 25,width:$("#data4").width(),minValue:1,maxValue:365});
            
            //验证属性设置
            $.metadata.setType("attr", "validate");
            //验证信息
            ${validationRules}
            //设置默认验证样式
            Validator = deafultValidate($("#editform"));
            
            $("#selectGoodsBut").click(function(){
            	openPriceGoodsSelectDialog();
            })
        });
        

        function h2y_save() {
        	
			if(!Validator.form()) return;

            var queryString = $('#editform').serialize();

            if (isSubmiting) {
                alert("表单正在提交，请稍后操作！");
                return;
            }
            isSubmiting = true;

            <%--注意该处url可能不符合你的要求，请注意修改--%>
            $.post("business/importbag/save.htm", queryString, function (data) {

                var jsonReturn = eval("(" + data + ")");
                if (op == "modify") {
                    if (jsonReturn.code == "1") {
                    	
                        alert(jsonReturn.msg);
                       	if (top.f_getframe("business_importbag_startInit_htm") != null) {
    						top.f_getframe("business_importbag_startInit_htm").f_query();
    					}
						top.f_delTab("business_importbag_modify${importBag.id}");
                    } else {
                        alert(jsonReturn.msg);
                    }
                } else {
                    if (jsonReturn.code == "1") {
                        alert(jsonReturn.msg);
                        	
                       	if (top.f_getframe("business_importbag_startInit_htm") != null) {
       						top.f_getframe("business_importbag_startInit_htm").f_query();
       					}
   						top.f_delTab("business_importbag_add");
                    } else {
                        alert(jsonReturn.msg);
                    }
                }
                isSubmiting = false;
            });
        }
        
        
        
        //商品选择回调函数
        function h2y_priceGoodsSelectCallBack(data){
        	
        	if(data==null || data.length==0) return;
        	$("#goodsPriceId").val(data.ID);
        	$("#goodslistdiv").html("<a href=\"javaScript:h2y_detail("+data.ID+");\">"+data.GOODS_NICK_NAME+"</a><br/>市场价：￥"+data.MARKET_PRICE+"<br/>会员价：￥"+data.MEMBER_PRICE);
        }
        
        
      	//商品选择回调函数
      	function h2y_priceGoodsSelectModify(){
    	  
        	$("#goodslistdiv").html("<a href=\"javaScript:h2y_detail(${importBag.goodsPriceId});\">${goodsPrice.goodsNickName}</a><br/>市场价：￥${goodsPrice.marketPrice}<br/>会员价：￥${goodsPrice.memberPrice}");
        }
        
		//查看商品详细
		function h2y_detail(id) {
	
	        var src = "http://10.10.10.182:80/h2yb2b/business/goodsprice/add.htm?op=detail&id="+id;
	        top.f_addTab("goodsprice_detail"+id,"商品详细", src);
	    }
		
		//查看商品详细
		function h2y_start() {
	
			$("#op").val("start");
			h2y_save();
	    }
		
		function h2y_refresh() {
	        document.location.reload();
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
		
		<input name="id" type="hidden" id="id" value="${importBag.id}" />
		<input name="op" type="hidden" id="op" value="${op}" />
		<input name="goodsPriceId" type="hidden" id="goodsPriceId" value="${importBag.goodsPriceId}" />
		<table class="h2y_table">
			
			<tr>
				<td class="h2y_table_label_td">
					礼包名称：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="bagName" id="bagName" type="input" value="${importBag.bagName}"  class="h2y_input_long"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					业务人员：
				</td>
				<td class="h2y_table_edit_td">
					<input name="businessUser" id="businessUser" type="input" value="${importBag.businessUser}"  class="h2y_input_just"/>
				</td>
				<td class="h2y_table_label_td">
					手机号码：
				</td>
				<td class="h2y_table_edit_td">
					<input name="businessMobile" id="businessMobile" type="input" value="${importBag.businessMobile}"  class="h2y_input_just"/>
				</td>
			</tr>
			
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
					送礼人：
				</td>
				<td class="h2y_table_edit_td">
					<input name="account" id="account" type="input" value="${importBag.account}"  class="h2y_input_just"/>
				</td>
				<td class="h2y_table_label_td">
					单价：
				</td>
				<td class="h2y_table_edit_td">
					<input name="singlePrice" id="singlePrice" type="input" value="${importBag.singlePrice}"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					失效天数：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="data4" id="data4" type="input" value="${importBag.data4}"  class="h2y_input_just"/>
				</td>
			</tr>
			
			<tr>
				<td class="h2y_table_label_td">
					备注：
				</td>
				<td class="h2y_table_edit_td2" colspan="3">
					<input name="memo" id="memo" type="input" value="${importBag.memo}"  class="h2y_input_long"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>