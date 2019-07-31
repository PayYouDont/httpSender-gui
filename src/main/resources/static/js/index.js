var app = {};
$(function(){
	app.initMap = initMap;
	app.initMap();
});
var initMap = function(){
	map.centerAndZoom(new BMap.Point(104.075, 30.656), 11); // 初始化地图,设置中心点坐标和地图级别
	// 添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
	}));
	map.setCurrentCity("成都"); // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
		// 靠左上角位置
		anchor : BMAP_ANCHOR_TOP_LEFT,
		// LARGE类型
		type : BMAP_NAVIGATION_CONTROL_LARGE,
		// 启用显示定位
		enableGeolocation : true
	});
	map.addControl(navigationControl);
	// 添加定位控件
	var geolocationControl = new BMap.GeolocationControl();
	geolocationControl.addEventListener("locationSuccess", function(e) {
		// 定位成功事件
		var address = '';
		address += e.addressComponent.province;
		address += e.addressComponent.city;
		address += e.addressComponent.district;
		address += e.addressComponent.street;
		address += e.addressComponent.streetNumber;
		alert("当前定位地址为：" + address);
	});
	geolocationControl.addEventListener("locationError", function(e) {
		// 定位失败事件
		alert(e.message);
	});
	map.addControl(geolocationControl);
	// 用经纬度设置地图中心点
	theLocation();
	map.addEventListener("click", showInfo);
	
	
	// ///////////////////
	var point = new BMap.Point(104.075, 30.656); // 创建点坐标
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);

	var polyline = new BMap.Polyline([ new BMap.Point(111.404, 40.915),
			new BMap.Point(112.404, 42.915), new BMap.Point(113.404, 39.915),
			new BMap.Point(114.404, 42.915), new BMap.Point(115.404, 39.915),
			new BMap.Point(116.404, 42.915) ], {
		strokeColor : "blue",
		strokeWeight : 2,
		strokeOpacity : 0.5
	});
	map.addOverlay(polyline);
}
//点击获取省的范围并圈出
function showInfo(e) {
	var geoc = new BMap.Geocoder();
	var pt = e.point;
	geoc.getLocation(pt, function(rs) {
		var addComp = rs.addressComponents;
		// alert(addComp.province + ", " + addComp.city + ", " +
		// addComp.district + ", " + addComp.street + ", " +
		// addComp.streetNumber);
		getBoundary(addComp.province)
	});

}
//根据地址圈出范围
function getBoundary(address) {
	var bdary = new BMap.Boundary();
	bdary.get(address, function(rs) { // 获取行政区域
		map.clearOverlays(); // 清除地图覆盖物
		var count = rs.boundaries.length; // 行政区域的点有多少个
		if (count === 0) {
			alert('未能获取当前输入行政区域');
			return;
		}
		var pointArray = [];
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {
				strokeWeight : 2,
				strokeColor : "#ff0000"
			}); // 建立多边形覆盖物
			map.addOverlay(ply); // 添加覆盖物
			pointArray = pointArray.concat(ply.getPath());
		}
		map.setViewport(pointArray); // 调整视野
		app.pointArray = pointArray
		//获取遮盖物最大和最小经纬度
		//getMaxAndMinArr(pointArray)
	});
}
function theLocation() {
	if (document.getElementById("longitude").value != ""
			&& document.getElementById("latitude").value != "") {
		map.clearOverlays();
		var new_point = new BMap.Point(
				document.getElementById("longitude").value, document
						.getElementById("latitude").value);
		var marker = new BMap.Marker(new_point); // 创建标注
		map.addOverlay(marker); // 将标注添加到地图中
		map.panTo(new_point);
	}
}
function findtiles(){
	//获取地图缩放级别
	var z = map.getZoom();
	var lng = map.getCenter().lng;
	var lat = map.getCenter().lat;
	console.log(lng,lat);
}
function getMaxAndMinArr(array){
	var MaxAndMinPoint = {
			maxlng:array[0].lng,//最大经度
			minlng:array[0].lng,//最小经度
			maxlat:array[0].lat,//最大纬度
			minlat:array[0].lat //最小纬度
	}
	for(var i=0;i<array.length-1;i++){
		var lng = array[i+1].lng;
		var lat = array[i+1].lat;
		var maxlng = MaxAndMinPoint.maxlng;
		var maxlat = MaxAndMinPoint.maxlat;
		var minlng = MaxAndMinPoint.minlng;
		var minlat = MaxAndMinPoint.minlat;
		MaxAndMinPoint.maxlng = maxlng<lng?lng:maxlng;
		MaxAndMinPoint.maxlat = maxlat<lat?lat:maxlat;
		MaxAndMinPoint.minlng = minlng>lng?lng:minlng;
		MaxAndMinPoint.minlat = minlat>lat?lat:minlat;
	}
	return MaxAndMinPoint;
}
