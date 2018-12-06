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
	
		
	</HEAD>
	<script type="text/javascript">
		$("#mybody").ready(function(){
			var websocket = null;
			var displayID;
			var myChart;
			var res = [];
			var res1 = [];
    		var res2 = [];
    		var res3 = [];
    		var res4 = [];
    		var res5 = [];
            //判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
          	//建立连接，这里的/websocket ，是ManagerServlet中开头注解中的那个值
                websocket = new WebSocket("ws://1856o325q1.iok.la:37325/mydesign/dataSumWebsocket");
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
            	console.log("connection");
            }
            //接收到消息的回调方法
            websocket.onmessage = function (event) {
            	var mydata = event.data;
            	console.log(mydata);
            	//接收到的是字符串显示内容
            	var dataSplit = mydata.split("-");
            	document.getElementById('deviceNum').innerHTML = dataSplit[0];
            	document.getElementById('onlineDeviceNum').innerHTML = dataSplit[1];
            	document.getElementById('offlineDeviceNum').innerHTML = parseInt(dataSplit[0])-parseInt(dataSplit[1]);
            	document.getElementById('userNum').innerHTML = dataSplit[2];
            	document.getElementById('recordNum').innerHTML = dataSplit[3];
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
		});
	</script>
	<body class="bg-info" id="mybody">
	<div style="width: 100%;height: 20px;" align="center"></div>
	<div class="col-md-12">
	    <div class="col-md-3">
	    	<div class="panel panel-primary">
		        <div class="panel-heading">设备总数</div>
		        <div class="panel-body"><h2><span id="deviceNum">0</span></h2></div>
		         <div class="panel-footer"><a href="${pageContext.request.contextPath}/getAllDeviceState?pageNumber=1"><font color="gray">查看更多</font></a></div>
	    	</div>
	    </div>
	    <div class="col-md-1"></div>
	    <div class="col-md-3">
	    	<div class="panel panel-success">
		        <div class="panel-heading">在线设备总数</div>
		        <div class="panel-body"><h2><font color="green"><span id="onlineDeviceNum">0</span></font></h2></div>
		        <div class="panel-footer"><a href="${pageContext.request.contextPath}/getAllDeviceState?pageNumber=1"><font color="gray">查看更多</font></a></div>
	    	</div>
	    </div>
	    <div class="col-md-1"></div>
	    <div class="col-md-3">
	    	<div class="panel panel-warning">
		        <div class="panel-heading">离线设备总数</div>
		        <div class="panel-body"><h2><font color="red"><span id="offlineDeviceNum">0</span></font></h2></div>
		        <div class="panel-footer"><a href="${pageContext.request.contextPath}/getAllDeviceState?pageNumber=1"><font color="gray">查看更多</font></a></div>
	    	</div>
	    </div>
    </div>
    <div class="col-md-12">
    	<div class="col-md-3">
	    	<div class="panel panel-success">
		        <div class="panel-heading">用户总数</div>
		        <div class="panel-body"><h2><span id="userNum">0</span></h2></div>
		         <div class="panel-footer"><a href="${pageContext.request.contextPath}/getAllUser?pageNumber=1"><font color="gray">查看更多</font></a></div>
	    	</div>
	    </div>
	    <div class="col-md-1"></div>
	    <div class="col-md-3">
	    	<div class="panel panel-info">
		        <div class="panel-heading">平台上传记录总数</div>
		        <div class="panel-body"><h2><span id="recordNum">0</span></h2></div>
		        <div class="panel-footer"><a href="${pageContext.request.contextPath}/getAllCheckResult?pageNumber=1"><font color="gray">查看更多</font></a></div>
	    	</div>
	    </div>
    </div>
</body>
</html>