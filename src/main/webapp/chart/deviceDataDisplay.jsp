<!DOCTYPE html>
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
		<link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap-select.min.css" rel="stylesheet" />

		<script src="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap-select.min.js"></script>
		
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/normalize.css" />
		<script src="${pageContext.request.contextPath }/static/js/gauge.min.js"></script>
		<!-- 引入html页面需要使用的ECharts的js文件 -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
	</HEAD>
	<body class="bg-success">
    <div class="col-md-12">
    	<!-- 文字显示区域 -->
    	<div id="infoDisplay" style="width: 100%;height: 50px;" align="center">
    		<h4 align="left">
    			<font color="green"><strong><span id="content"></span></strong></font>
    			<span id="deviceNums"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    			<label><font color="green">当前显示的节点：</font></label>
				<select class="text1" data-style="btn-info" id="devices" name="devices">
					<option>请选择</option>
				</select>
    		</h4>
    			
    	</div>
    	<div id = "chartDisplay" style="width: 100%;height: 500px;" align="left"></div>
    	<script type="text/javascript">
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
                    websocket = new WebSocket("ws://1856o325q1.iok.la:37325/mydesign/dataDisplayWebsocket");
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
                	createDIV();
                	chartDisplay();
                }
                //接收到消息的回调方法
                websocket.onmessage = function (event) {
                	var mydata = event.data;
                	alert(mydata);
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
                //开始绘制图   ==========================================================start
                if (!Array.prototype.forEach) {
            		Array.prototype.forEach = function(cb) {
            			var i = 0, s = this.length;
            			for (; i < s; i++) {
            				cb && cb(this[i], i, this);
            			}
            		}
            	}

            	var timers = [];

            	function animateGauges(tem,hum,choh,pm25,pm10) {
            		var allGagues = document.gauges;
            		allGagues[0].value = choh;
            		allGagues[1].value = pm25;
            		allGagues[2].value = pm10;
            		allGagues[3].value = hum;
            		allGagues[4].value = tem;
            	}

            	function stopGaugesAnimation() {
            		timers.forEach(function(timer) {
            			clearInterval(timer);
            		});
            	}
            	
            	
            	
            	//结束 绘制图   =================================================================end
                $("#devices").change(function(){
                	$("#chartDisplay").empty();
                	while(!myChart.isDisposed()){
                		//没有被销毁
                		console.log("未被销毁");
                		myChart.dispose();
                	}
                	createDIV();
                	chartDisplay();
                });
                //创建一个div 
                function createDIV(){
                	$("#chartDisplay").append('<div id="mainDisplay" style="width: 1000px;height: 500px;" align="center"></div>');
                }
               	function chartDisplay(){
               		myChart = echarts.init(document.getElementById('mainDisplay'));
               		var now = new Date();
               		var len = 10;
               		
               		while (len--) {
               		res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
               		     now = new Date(now - 3000);
               		}
               		
            		var len = 10;
            		while (len--) {
            		  res1.push(0);
            		  res2.push(0);
            		  res3.push(0);
            		  res4.push(0);
            		  res5.push(0);
            		}
            		var xAxisData = res; //x轴数据
            		var yAxisData = res1; //y轴数据
            		var yAxisData2 = res2; //y轴数据
            		var yAxisData3 = res3; //y轴数据
            		var yAxisData4 = res4; //y轴数据
            		var yAxisData5 = res5; //y轴数据
            		// 指定图表的配置项和数据
            		var option = {
            		title : {
            		    text: '实时空气参数'
            		},
            		tooltip : {
            		        trigger: 'axis'
            		    },
            		    legend: {
            		        data:['温度', '湿度','甲醛','PM2.5','PM10']
            		    },
            		    toolbox: {
            		        show : true,
            		        feature : {
            		            //dataView : {show: true, readOnly: false},
            		            magicType : {show: true, type: ['line', 'bar']},
            		            restore : {show: true},
            		            saveAsImage : {show: true}
            		        }
            		    },
            		    dataZoom : {
            		        show : false,
            		        start : 0,
            		        end : 100
            		    },
            		    xAxis : [
            		        {
            		            type : 'category',
            		            boundaryGap : true,
            		            data:xAxisData
            		        }
            		   ],
            		   yAxis : [
            		      {
            		           type : 'value',
            					min:0,
            		           scale: true,
            		           name : '空气参数',
            		           boundaryGap: [0.2, 0.2]
            		      }
            		  ],
            		  series : [
            		       {
            		            name:'温度',
            		            type:'line',
            		            lineStyle: {
            		            normal: {
            		              //color: '#4F2F4F',
            		              width: 2
            		              //type: solid
            		            },
            		            },
            		            data:yAxisData
            		       },
            		       {
            		           name:'湿度',
            		           type:'line',
            		           lineStyle: {
            		            normal: {
            		              //color: '#4F2F4F',
            		              width: 2
            		              //type: solid
            		            },
            		           },
            		           data:yAxisData2
            		      },
            			  {
               		            name:'甲醛',
               		            type:'line',
               		            lineStyle: {
               		            normal: {
               		              //color: '#4F2F4F',
               		              width: 2
               		              //type: solid
               		            },
               		            },
               		            data:yAxisData3
               		       },
               		       {
               		           name:'PM2.5',
               		           type:'line',
               		           lineStyle: {
               		            normal: {
               		              //color: '#4F2F4F',
               		              width: 2
               		              //type: solid
               		            },
               		           },
               		           data:yAxisData4
               		      },
            			  {
             		            name:'PM10',
             		            type:'line',
             		            lineStyle: {
             		            normal: {
             		              //color: '#4F2F4F',
             		              width: 2
             		              //type: solid
             		            },
             		            },
             		            data:yAxisData5
             		       },
            		  ]
            		};
            		var timeTicket;
            		var tem,hum,choh,pm25,pm10;
               		var date;
               		clearInterval(timeTicket);
               		timeTicket = setInterval(function (){
               		  var url = '${pageContext.request.contextPath}/getRuntimeData';
               		  var item = $("#devices option:selected").text();
               		  var returnData;
               		  $.ajax({
               		      url: url,
               		      type: 'post',
               		      data:"item="+item,
               		      dataType: 'json',
               		      async:false,
               		      error:function(){
               		          console.log("get redis error!!!")
               		      },
               		      success: function(result){
               			
               				  console.log(result.data);
               		          if(result != null){
               		        	date = result.dateTime;
               		          	returnData = result.data.split("\\s+");
               		          	tem = returnData[1];
			               		hum = returnData[2];
			               		choh = returnData[3];
			               		pm25 = returnData[4];
			               		pm10 = returnData[5];
			               		console.log(tem+","+hum+","+choh+","+pm25+","+pm10);
               		          }
               				res.shift();
               				  res.push(date);
               				res1.shift();
               				  res1.push(tem);
               				res2.shift();
             				  res2.push(hum);
             				 res3.shift();
              				  res3.push(choh);
              				res4.shift();
             				  res4.push(pm25);
             				 res5.shift();
              				  res5.push(pm10);
              				animateGauges(hum,tem,choh,pm25,pm10);
               		      }
               		  }); 
               		    // 动态数据接口 addData
               		    setTimeout(myChart.setOption({
              			   xAxis: {
       		                 data: res
		       		       },
               			   series:[
            		       {
            		            name:'温度',
            		            type:'line',
            		            lineStyle: {
            		            normal: {
            		              //color: '#4F2F4F',
            		              width: 2
            		              //type: solid
            		            },
            		            },
            		            data:res1
            		       },
            		       {
            		           name:'湿度',
            		           type:'line',
            		           lineStyle: {
            		            normal: {
            		              //color: '#4F2F4F',
            		              width: 2
            		              //type: solid
            		            },
            		           },
            		           data:res2
            		      },
            			  {
               		            name:'甲醛',
               		            type:'line',
               		            lineStyle: {
               		            normal: {
               		              //color: '#4F2F4F',
               		              width: 2
               		              //type: solid
               		            },
               		            },
               		            data:res3
               		       },
               		       {
               		           name:'PM2.5',
               		           type:'line',
               		           lineStyle: {
               		            normal: {
               		              //color: '#4F2F4F',
               		              width: 2
               		              //type: solid
               		            },
               		           },
               		           data:res4
               		      },
            			  {
             		            name:'PM10',
             		            type:'line',
             		            lineStyle: {
             		            normal: {
             		              //color: '#4F2F4F',
             		              width: 2
             		              //type: solid
             		            },
             		            },
             		            data:res5
             		       }
               	        ]
               			}),500);
                 	}, 1000);
               		// 使用刚指定的配置项和数据显示图表。
               		if (option && typeof option === "object") {
						myChart.setOption(option, true);
					}
               	}
        </script>
    </div>
	<div class="zzsc-container">
	<div class="container" style="width: 1000px; height:600px;margin: 50px;">
		<div style="width: 350px; height:600px;float:left">
		<!-- 温度 -->
		<canvas data-type="linear-gauge"
					data-width="160"
					data-height="500"
					data-border-radius="20"
					data-borders="true"
					data-bar-stroke-width="20"
					data-minor-ticks="10"
					data-major-ticks="0,10,20,30,40,50,60,70,80,90,100"
					data-color-numbers="red,green,blue,transparent,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc"
					data-color-major-ticks="red,green,blue,transparent,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc"
					data-color-bar-stroke="#444"
					data-value="0"
					data-title="温度"
					data-units="℃"
					data-color-value-box-shadow="false"
					data-tick-side="left"
					data-number-side="left"
					data-needle-side="left"
					data-animate-on-init="true"
					data-color-plate="transparent">
			</canvas>
			<!-- 湿度 -->
			<canvas data-type="linear-gauge"
					data-width="160"
					data-height="500"
					data-border-radius="20"
					data-borders="true"
					data-bar-stroke-width="20"
					data-minor-ticks="10"
					data-major-ticks="0,10,20,30,40,50,60,70,80,90,100"
					data-color-numbers="red,green,blue,transparent,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc"
					data-color-major-ticks="red,green,blue,transparent,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc,#ccc"
					data-color-bar-stroke="#444"
					data-value="0"
					data-title="湿度"
					data-units="%"
					data-color-value-box-shadow="false"
					data-tick-side="left"
					data-number-side="left"
					data-needle-side="left"
					data-animate-on-init="true"
					data-color-plate="transparent">
			</canvas>
			</div>
			<div style="width: 480px; height:600px;float:right">
			<!-- 甲醛 -->
			<canvas data-type="radial-gauge"
				data-width="225"
				data-height="225"
				data-units="mg/m³"
				data-title="甲醛"
				data-value="0"
				data-animate-on-init="true"
				data-animated-value="true"
				data-min-value="0"
				data-max-value="0.5"
				data-major-ticks="0,0.05,0.1,0.2,0.3,0.5"
				data-stroke-ticks="false"
				data-highlights='[
					{ "from": 0, "to": 0.05, "color": "rgba(0,255,0,.15)" },
					{ "from": 0.05, "to": 0.1, "color": "rgba(255,255,0,.15)" },
					{ "from": 0.1, "to": 0.2, "color": "rgba(255,30,0,.25)" },
					{ "from": 0.2, "to": 0.3, "color": "rgba(255,0,225,.25)" },
					{ "from": 0.3, "to": 0.5, "color": "rgba(0,0,255,.25)" }
				]'
				data-color-plate="transparent"
				data-color-major-ticks="#f5f5f5"
				data-color-minor-ticks="#ddd"
				data-color-title="#000"
				data-color-units="#ccc"
				data-color-numbers="#000"
				data-value-box="true"
				data-font-value="Led"
				data-font-numbers="Led"
				data-border-outer-width="3"
				data-border-middle-width="10"
				data-border-inner-width="3"
		></canvas>
		<!-- PM2.5-->
			<canvas style="margin-top:0px;" data-type="radial-gauge"
				data-width="225"
				data-height="225"
				data-units="μg/m³"
				data-title="PM2.5"
				data-value="0"
				data-animate-on-init="true"
				data-animated-value="true"
				data-min-value="0"
				data-max-value="400"
				data-major-ticks="0,35,75,150,250,400"
				data-stroke-ticks="false"
				data-highlights='[
					{ "from": 0, "to": 35, "color": "rgba(0,255,0,.15)" },
					{ "from": 35, "to": 75, "color": "rgba(255,255,0,.15)" },
					{ "from": 75, "to": 150, "color": "rgba(255,30,0,.25)" },
					{ "from": 150, "to": 250, "color": "rgba(255,0,225,.25)" },
					{ "from": 250, "to": 400, "color": "rgba(0,0,255,.25)" }
				]'
				data-color-plate="transparent"
				data-color-major-ticks="#f5f5f5"
				data-color-minor-ticks="#ddd"
				data-color-title="#000"
				data-color-units="#ccc"
				data-color-numbers="#000"
				data-color-needle-start="rgba(240, 128, 128, 1)"
				data-color-needle-end="rgba(255, 160, 122, .9)"
				data-value-box="true"
				data-font-value="Led"
				data-font-numbers="Led"
				data-border-outer-width="3"
				data-border-middle-width="3"
				data-border-inner-width="3"
		></canvas>
		<!-- PM10-->
			<canvas data-type="radial-gauge"
				data-width="225"
				data-height="225"
				data-units="μg/m³"
				data-title="PM10"
				data-value="0"
				data-animate-on-init="true"
				data-animated-value="true"
				data-min-value="0"
				data-max-value="500"
				data-major-ticks="0,50,150,250,350,500"
				data-stroke-ticks="false"
				data-highlights='[
					{ "from": 0, "to": 50, "color": "rgba(0,255,0,.15)" },
					{ "from": 50, "to": 150, "color": "rgba(255,255,0,.15)" },
					{ "from": 150, "to": 250, "color": "rgba(255,30,0,.25)" },
					{ "from": 250, "to": 350, "color": "rgba(255,0,225,.25)" },
					{ "from": 350, "to": 500, "color": "rgba(0,0,255,.25)" }
				]'
				data-color-plate="transparent"
				data-color-major-ticks="#f5f5f5"
				data-color-minor-ticks="#ddd"
				data-color-title="#000"
				data-color-units="#ccc"
				data-color-numbers="#000"
				data-color-needle-start="rgba(240, 128, 128, 1)"
				data-color-needle-end="rgba(255, 160, 122, .9)"
				data-value-box="true"
				data-font-value="Led"
				data-font-numbers="Led"
				data-border-outer-width="3"
				data-border-middle-width="3"
				data-border-inner-width="3"
		></canvas>
		</div>
		</div>
	</div>
</body>
</html>