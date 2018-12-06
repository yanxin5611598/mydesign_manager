<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<!-- 引入html页面需要使用的ECharts的js文件 -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/echarts.js"></script>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <!-- 这里要配置参数key,将其值设置为高德官网开发者获取的key -->
    <script src="http://webapi.amap.com/maps?v=1.3&key=b0d049be6b8d4fccf8f6430f291c7649"></script> 
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	</HEAD>
	<body class="bg-warning">
    <div class="col-md-12">
    	<!-- 文字显示区域 -->
    	<div id="infoDisplay" style="width: 100%;height: 30px;" align="center">
    		<h4 align="left">
    			<font color="green"><strong><span id="content"></span></strong></font>
    			<span id="deviceNums"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    			<label><font color="green">当前显示的节点：</font></label>
				<select class="text1" id="devices" name="devices">
					<option>请选择</option>
				</select>
    		</h4>
    			
    	</div>
    	<div id="mapDisplay" style="width: 100%;height: 500px;" align="center">
    		
    	</div>
        <div id="main" style="width: 100%;height: 20px;" align="center"></div>
    	<script type="text/javascript">
    			var websocket = null;
            	
                //判断当前浏览器是否支持WebSocket
                if ('WebSocket' in window) {
              	//建立连接，这里的/websocket ，是ManagerServlet中开头注解中的那个值
                    websocket = new WebSocket("ws://1856o325q1.iok.la:37325/mydesign/locationDisplayWebsocket");
                }
                else {
                    alert('当前浏览器 Not support websocket')
                }
                //连接发生错误的回调方法
                websocket.onerror = function () {
                    alert("WebSocket连接发生错误");
                };
                //连接成功建立的回调方法
                websocket.onopen = function () {
                	document.getElementById('content').innerHTML = "当前在线的设备数：";
                }
                //接收到消息的回调方法
                websocket.onmessage = function (event) {
                	var mydata = event.data;
                	if(mydata.startsWith("deviceNums")){
                		//接收到的是字符串显示内容
                		document.getElementById('deviceNums').innerHTML = mydata.split("-")[0].split("=")[1];
                		var value = mydata.split("-")[1];
	                	var str = ${'value'};
	                	if(str == null){
	                		//当前没有在线设备 
	                		return;
	                	}
	                	$("#devices").empty();//将下拉列表清空（后台守护线程thread1 触发变化 ）
	                	if(str.indexOf(",") != -1){
	                		var	devices = str.substring(1,str.length-1).split(",");
	                		for(var i = 0;i<devices.length;i++){
		                		$("#devices").append("<option value=>"+ devices[i] +"</option>");
		                	}
	                	}else{
	                		$("#devices").append("<option value=>"+ str.substring(1,str.length-1) +"</option>");
	                	}
	                	var message = $("#devices option:selected").text();
	            		websocket.send(message);
                	}else{
                		var x = mydata.split("-")[0];
                		var y = mydata.split("-")[1];
                		mapDisplay(x,y);
                	}
        
                }
                //连接关闭的回调方法
                websocket.onclose = function () {
                    //alert("WebSocket连接关闭");
                }
                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = function () {
                    closeWebSocket();
                }
                //关闭WebSocket连接
                function closeWebSocket() {
                    websocket.close();
                }
                function mapDisplay(x,y){
                	var mylocation;
            		AMap.service('AMap.Geocoder',function(){//回调函数
            		    //实例化Geocoder
            		    geocoder = new AMap.Geocoder({
            		        city: "010"//城市，默认：“全国”
            		    });
            		    //TODO: 使用geocoder 对象完成相关功能
            			//逆地理编码
            		    var lnglatXY=[x, y];//地图上所标点的坐标
            		    geocoder.getAddress(lnglatXY, function(status, result) {
            		        if (status === 'complete' && result.info === 'OK') {
								mylocation = result.regeocode.formattedAddress;
								alert(mylocation);
            		        }else{
            		           // 获取地址失败
            		        }
            		    });  
            		})
            		//地图显示
            		var map = new AMap.Map('mapDisplay', {
            	        resizeEnable: true,
            	        zoom:18,
            	        center: [x, y]
            	    });
            	   /*  AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
            	        //TODO  创建控件并添加
            	        alert("dddd");
            	    }) */
            	    //信息窗体的创建与设定
            	    /* var info = [];
			        info.push("<div><div><img style=\"float:left;\" src=\" http://webapi.amap.com/images/autonavi.png \"/></div> ");
			        info.push("地址 :");
			        info.push(mylocation);
			        info.push("</div>"); */
            	    var infowindow = new AMap.InfoWindow({
            	        offset: new AMap.Pixel(0, -30),
            	        size:new AMap.Size(230,0)
            	    });
            	    infowindow.open(map, map.getCenter());
            	   //点标记的创建与添加
            	    var marker = new AMap.Marker({
            	    	icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            	        position: [x, y],
            	        map:map
            	    });
                }
            	$("#devices").change(function(){
            		var message = $("#devices option:selected").text();
            		websocket.send(message);
            	});
        </script>
    </div>
</body>
</html>