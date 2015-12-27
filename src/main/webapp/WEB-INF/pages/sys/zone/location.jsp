<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${mname}</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=mm68DA2AyyrcPGRvBLrHzatr"></script>  
<script src="<%=uiPath%>lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>  

<script type="text/javascript">  

	var map = null;
	
	var clickMarker = null;//点击标注
	var defaultMarker = null;//默认标注
	
	var defaultCity = "郑州市";
	
	var op = "${op}";
	var id = "${zone.id}";
	var h2y_longitude = "${zone.longitude}";
	var h2y_latitude = "${zone.latitude}";
	var h2y_location = "${zone.location}";
	var isSubmiting = false;
	$(function () {
		
		map = new BMap.Map("container");  
		//百度地图API功能
		map.centerAndZoom(defaultCity,12);   
	
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.NavigationControl());    
		map.addControl(new BMap.ScaleControl());    
		map.addControl(new BMap.OverviewMapControl());    
		map.addControl(new BMap.MapTypeControl());  
	
		var geoc = new BMap.Geocoder();  
		
		if(h2y_longitude!="" && h2y_latitude!=""){
			
			defaultMarker = new BMap.Marker(new BMap.Point(h2y_longitude,h2y_latitude));
			map.addOverlay(defaultMarker);
		}else{
			
			// 将地址解析结果显示在地图上,并调整地图视野
			geoc.getPoint(h2y_location, function(point){
				if (point) {
					map.centerAndZoom(point, 16);
					defaultMarker = new BMap.Marker(point);
					map.addOverlay(defaultMarker);
					h2y_longitude = point.lng;
					h2y_latitude = point.lat;
				}
			}, defaultCity);
		}
	
		//单击获取点击的经纬度
		map.addEventListener("click",function(e){
			
			h2y_longitude = e.point.lng;
			h2y_latitude = e.point.lat;
			
			map.removeOverlay(defaultMarker);
			if(clickMarker){
				map.removeOverlay(clickMarker);
			}
			clickMarker = new BMap.Marker(e.point);
			map.addOverlay(clickMarker);
			
			geoc.getLocation(e.point, function(rs){
				var addComp = rs.addressComponents;
				h2y_location = addComp.province+ addComp.city + addComp.district + addComp.street+ addComp.streetNumber;
				$("#location_info_div").html("<span >地址："+h2y_location+" 经纬度："+h2y_longitude+","+h2y_latitude+"</span>")
			});        
		});
	});
	
	  function h2y_save() {
	    	
	    	var params = {id:id,op:op,longitude:h2y_longitude,latitude:h2y_latitude,location:h2y_location};
	        if (isSubmiting) {
	            alert("表单正在提交，请稍后操作！");
	            return;
	        }
	        isSubmiting = true;
	        <%--注意该处url可能不符合你的要求，请注意修改--%>
	        $.post("sys/zone/save.htm", params, function (data) {

	            var jsonReturn = eval("(" + data + ")");
	            if (jsonReturn.code == "1") {
	                alert(jsonReturn.msg);
	                parent.f_query();
	                frameElement.dialog.close();
	            } else {
	                alert(jsonReturn.msg);
	            }
	            isSubmiting = false;
	        });
	    }

	
</script>
<style type="text/css">  
html,body{  
    margin:0;
    overflow:hidden;  
    width:100%;
    height:100%;
    }
</style>  
</head>  
<body>  
	<div style="width:90%;height:90%;border:1px solid gray" id="container"></div> 
	<div id="location_info_div" style="text-align: left;color:#000;"></div>
</body>  
</html>  
