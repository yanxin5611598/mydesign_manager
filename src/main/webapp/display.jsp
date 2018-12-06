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
		<script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
		<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	</HEAD>
	<script type="text/javascript">
    			$(document).ready(function(){
    				$('#select_device').modal({   //bootstrap打开模态框的方法
    					backdrop:'static'   //设置当用户点击模态框的其它背景的地方的时候，模态框不会消失     否则消失
    				});
    			});
    			var param;
    			var deviceID = <c:out value="${deviceID}"/>
    			$(document).on("click","button[name='param']",function(){
    				$('#select_device').modal('hide');
    				param = $(this).html();
    				startCharts();
    				
    				processResult();
    				Highcharts.setOptions({
                		global: {
               				useUTC: false
                		}
           		    });
    			});
    			
    			var mychart = null;
    			function startCharts(){
    				mychart = new Highcharts.chart('container', {
    		    		chart: {
		    				type: 'spline',
		    				renderTo : 'container',
		    				marginRight: 10	
		    		},
		    		title: {
		    				text: '动态模拟实时数据'
		    		},
		    		xAxis: {
		    				type: 'datetime',
		    				tickPixelInterval: 150
		    		},
		    		yAxis: {
		    				title: {
		    						text: null
		    				}
		    		},
		    		tooltip: {
		    				formatter: function () {
		    						return '<b>' + this.series.name + '</b><br/>' +
		    								Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
		    								Highcharts.numberFormat(this.y, 2);
		    				}
		    		},
		    		legend: {
		    				enabled: false
		    		},
		    		series: [{
		    				name: '随机数据',
		    				data: (function () {
		    						// 生成随机值
		    						var data = [],
		    								time = (new Date()).getTime(),
		    								i;
		    						for (i = -19; i <= 0; i += 1) {
		    								data.push({
		    										x: time + i * 1000,
		    										y: mydata
		    								});
		    						}
		    						return data;
		    				}())
		    			}]
			    	}, function(c) {
			    		activeLastPointToolip(c)
			    	});
    			}
        	    function activeLastPointToolip(chart) {
        		    var points = chart.series[0].points;
        		    chart.tooltip.refresh(points[points.length -1]);
        		}
    			function processResult(){
	    			var websocket = null;
	            	
	                //判断当前浏览器是否支持WebSocket
	                if ('WebSocket' in window) {
	              	//建立连接，这里的/websocket ，是ManagerServlet中开头注解中的那个值
	                    websocket = new WebSocket("ws://1856o325q1.iok.la:37325/mydesign/viewDataWebsocket");
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
	                	websocket.send(deviceID+"-"+param);
	                }
	                //接收到消息的回调方法
	                websocket.onmessage = function (event) {
	                	var mydata = event.data;
	                	
	                	if(mydata=="sorry"){
							alert("对不起,服务器没有数据！");
	                	}else{
	                		alert(mydata)
	                		series.addPoint([ (new Date()).getTime(), mydata], true, true);
	                		activeLastPointToolip(mychart);
	                	}
	                }
	                //连接关闭的回调方法
	                websocket.onclose = function () {
	                    alert("WebSocket连接关闭");
	                }
	                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	                window.onbeforeunload = function () {
	                    closeWebSocket();
	                }
	                //关闭WebSocket连接
	                function closeWebSocket() {
	                    websocket.close();
	                }
	            	$("#devices").change(function(){
	            		var message = $("#devices option:selected").text();
	            		websocket.send(message);
	            	});
    			}
        </script>
	<body>
    	<div class="modal fade" id="select_device" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">请选择要查看的类别</h4>
		      </div>
		      <div class="modal-body">
				  <div class="form-group">
				    <button type="button" class="btn btn-primary" name="param" id="tem" onclick="get_deviceInfo()">温度</button>
				    <button type="button" class="btn btn-primary" name="param" id="hum" onclick="get_deviceInfo()">湿度</button>
				    <button type="button" class="btn btn-primary" name="param" id="choh" onclick="get_deviceInfo()">甲醛</button>
				    <button type="button" class="btn btn-primary" name="param" id="pm25" onclick="get_deviceInfo()">PM2.5</button>
				    <button type="button" class="btn btn-primary" name="param" id="pm10" onclick="get_deviceInfo()">PM10</button>
				    <button type="button" class="btn btn-primary" name="param" id="co2" onclick="get_deviceInfo()">CO2</button>
				  </div>
		      </div>
		    </div>
		 </div>
		</div>
        <div id="container" style="width: 1000px;height: 600px;" align="center"></div>
</body>
</html>