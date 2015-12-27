<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp"%>

<link href="<%=uiPath%>lib/carousel/css/owl.theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=uiPath%>lib/carousel/css/owl.carousel.css" rel="stylesheet" type="text/css"/>

<script src="<%=uiPath%>lib/carousel/js/owl.carousel.min.js" type="text/javascript"></script>
<style type="text/css">
	 #owl-demo { width: 900px; margin-left: auto; margin-right: auto;}
	 #owl-demo .item{ display: block;}
	 #owl-demo img { display: block; width: 100%;height:350px;}
</style>	
<script type="text/javascript">
    var subjectList = [];
    var zoneCode = "${sysUnits.zoneCode}";
    var isFirst = true;
    $(function () {
    	
    	$("#toptoolbar").ligerToolBar({items: [{ line:true },{ text: '刷新' , click: h2y_refresh , icon:'refresh' }]});
    	
    	h2y_advertShow($("input[type=radio][name='logoType']").val());
    	$("input[type=radio][name='logoType']").change(function(){
    		h2y_advertShow(this.value);
        });
    });
    
    
    function h2y_advertShow(os){
    	
	     var postData = {};
	     postData.zoneCode = zoneCode;
        <%--注意该处url可能不符合你的要求，请注意修改--%>
        $.post("<%=basePath%>cmbs/advert/getList.htm",{postData:JSON.stringify(postData),os:os},function (data) {
           var jsonReturn = eval("(" + data + ")");
           if (jsonReturn.resultFlag == "1") {
               //alert(jsonReturn.resultMsg);
               carouselInit(jsonReturn.resultData);
               isFirst = false;
           } else {
           	   alert(jsonReturn.resultMsg);
           }
        });
   }
    
    //轮播初始化
    function carouselInit(data){
    	
    	if(isFirst){
    		
    		$("#owl-demo").html("");
    		$(data).each(function(){
        		$("#owl-demo").append("<a class=\"item\"><img class=\"columLogoImg\" _imgID=\""+this.subjectId+"\" title=\""+this.subjectName+"\" src=\"${fpUrl}"+this.img+"\"></li></a>");
        	});
        	
        	$("#owl-demo").owlCarousel({
        		items: 1,
        		autoPlay: true
        	});
    	}else{
    		
    		$(data).each(function(i){
        		$("#owl-demo img:eq("+i+")").attr("src","${fpUrl}"+this.img);
        	});
    	}
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
    
    <div class="main">
		<div class="mianc">
			<!-- Demo -->
			<div id="owl-demo" class="owl-carousel">
				
			</div>
			<!-- Demo end -->
		</div>
	</div>
	
	<div style="text-align: center;">
		<h2y:input name="logoType" id="logoType" type="radio" initoption="android,安卓效果:IOS,苹果效果:wechat,微信效果" value="android"/>
	</div>
</body>
</html>