<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DhPM8pha2qqNaHNZRZGpNaNf"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>common/js/data.js"></script>
    <title>订单区域热度图</title>
    <style type="text/css">
		ul,li{list-style: none;margin:0;padding:0;float:left;}
		html{height:100%}
		body{height:100%;margin:0px;padding:0px;font-family:"微软雅黑";}
		#container{height:100%;width:100%;}
		#r-result{width:100%;}
    </style>	
</head>
<body>
	<div id="container" >
	</div>
	<div id="r-result" style="border: 1px solid;">
		<input type="button"  onclick="openHeatmap();" value="显示热力图"/><input type="button"  onclick="closeHeatmap();" value="关闭热力图"/>
	</div>
</body>

<script type="text/javascript">
	var orderAnalysesRadius = 500;
	var cityName = "${cityName}";
	
    var map = new BMap.Map("container");          // 创建地图实例
    
	// 初始化地图，设置中心点坐标和地图级别
    //var point = new BMap.Point(116.418261, 39.921984);
    var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,13);
	map.centerAndZoom(cityName,13);      // 用城市名设置地图中心点
		
 	// 允许滚轮缩放
    map.enableScrollWheelZoom(); 
  
    var points = _points;
    
    var lng = 113.664119,lat=34.753251;

	var tileLayer = new BMap.TileLayer({isTransparentPng: true});
	tileLayer.getTilesUrl = function(tileCoord, zoom) {
		var x = tileCoord.x;
		var y = tileCoord.y;
		//alert(x+"\t"+y);
		return "http://developer.baidu.com/map/jsdemo/img/border.png";
		//return "http://10.99.99.129:8080/h2yfp2/border_3.png";
	}
	
	//添加线条
	map.addTileLayer(tileLayer);
	//function delete_control(){
	//	map.removeTileLayer(tileLayer);
	//}
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮

    //map.addControl(top_left_control);
    //map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);
    
	//添加菜单
	var menu = new BMap.ContextMenu();
	var txtMenuItem = [
		{
			text:"详细数据",
			callback:function(){
				//alert("经度：  "+lng+"纬度：  "+lat);
				window.location.assign("<%=basePath%>/business/ordercount/orderAnalyses.htm?longitude="+lng+" &latitude="+lat+" &radius="+orderAnalysesRadius);
			}
		}
	];
	

	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		//alert("经度：  "+e.point.lng+"  纬度：  "+e.point.lat);
		lng = e.point.lng;
		lat = e.point.lat;
	});
	
	// 定义一个控件类,即function
	function ZoomControl(){
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		this.defaultOffset = new BMap.Size(10, 10);
	}
	
	// 通过JavaScript的prototype属性继承于BMap.Control
	ZoomControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	/* ZoomControl.prototype.initialize = function(map){
		// 创建一个DOM元素
		var div = document.createElement("div");
		// 添加文字说明
		div.appendChild(document.createTextNode("显示热力图"));
		// 设置样式
		div.style.cursor = "pointer";
		div.style.border = "1px solid gray";
		div.style.backgroundColor = "white";
		// 绑定事件,点击一次放大两级
		div.onclick = function(e){
			openHeatmap();
		};
		// 添加DOM元素到地图中
		map.getContainer().appendChild(div);
		// 将DOM元素返回
		return div;
	}; */
	
	// 创建控件
	var myZoomCtrl = new ZoomControl();
	// 添加到地图当中
	map.addControl(myZoomCtrl);
	
	
	for(var i=0; i < txtMenuItem.length; i++){
		menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
	}
	map.addContextMenu(menu);
   
    if(!isSupportCanvas()){
    	alert("热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~");
    }
    
	//详细的参数,可以查看heatmap.js的文档 https://github.com/pa7/heatmap.js/blob/master/README.md
	//参数说明如下:
	/* visible 热力图是否显示,默认为true
     * opacity 热力的透明度,1-100
     * radius 势力图的每个点的半径大小   
     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
     * {
			.2:'rgb(0, 255, 255)',
			.5:'rgb(0, 110, 255)',
			.8:'rgb(100, 0, 255)'
		}
		其中 key 表示插值的位置, 0~1. 
		    value 为颜色值. 
     */
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
	map.addOverlay(heatmapOverlay);
	heatmapOverlay.setDataSet({data:points,max:100});
	//是否显示热力图
    function openHeatmap(){
        heatmapOverlay.show();
    }
	function closeHeatmap(){
        heatmapOverlay.hide();
    }
	closeHeatmap();
    function setGradient(){
     	/*格式如下所示:
		{
	  		0:'rgb(102, 255, 0)',
	 	 	.5:'rgb(255, 170, 0)',
		  	1:'rgb(255, 0, 0)'
		}*/
     	var gradient = {};
     	var colors = document.querySelectorAll("input[type='color']");
     	colors = [].slice.call(colors,0);
     	colors.forEach(function(ele){
			gradient[ele.getAttribute("data-key")] = ele.value; 
     	});
        heatmapOverlay.setOptions({"gradient":gradient});
    }
	//判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
	
    openHeatmap();
</script>
</html>