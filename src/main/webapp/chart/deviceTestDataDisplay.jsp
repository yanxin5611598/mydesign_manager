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
	<body>
    <div class="col-md-12">
    	<!-- 文字显示区域 -->
    	<div id="infoDisplay" style="width: 1000px;height: 100px;" align="center">
    		<h4 align="left">
    			<font id="content" color="green"></font>
    			<span id="deviceNums"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    			<label><font color="green">当前显示的节点：</font></label>
				<select class="text1" id="devices" name="devices">
					<option>请选择</option>
				</select>
    		</h4>
    			
    	</div>
    	<div id="mainDisplay" style="width: 1000px;height: 500px;" align="center">
    		
    	</div>
        <div id="main" style="width: 1000px;height: 30px;" align="center"></div>
    	<script type="text/javascript">
    			var websocket = null;
    			var myChart = echarts.init(document.getElementById('mainDisplay'));
    			var now = new Date();
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
                	chartDisplay();
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
                		//实时数据的结果反馈 
                		console.log(mydata);
                		addNewData(mydata);
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
                function addNewData(realData){
                	var tem,hum,choh,pm25,pm10;
            		var realDataSplit = realData.split(",");
            		tem = realDataSplit[0];
            		hum = realDataSplit[1];
            		choh = realDataSplit[2];
            		pm25 = realDataSplit[3];
            		pm10 = realDataSplit[4];
            		var axisData;
            		clearInterval(app);
            		var app = {};
            		app.timeTicket = setInterval(function (){
            		  	now = new Date(now - 1000);
            		    // 动态数据接口 addData
            		    myChart.setOption([
            		        [
            		            0,        // 温度 
            		           	tem, // 新增数据
            		            false,     // 新增数据是否从队列头部插入
            		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
            		            axisData //横轴数据
            		        ],
            		        [
            		            1,        // 湿度 
            		            hum, // 新增数据
            		            false,     // 新增数据是否从队列头部插入
            		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
            		            axisData //横轴数据
            		        ],
            				[
             		            2,        // 甲醛
             		            choh, // 新增数据
             		            false,     // 新增数据是否从队列头部插入
             		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
             		            axisData //横轴数据
             		        ],
             		        [
             		            3,        // PM2.5
             		            pm25, // 新增数据
             		            false,     // 新增数据是否从队列头部插入
             		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
             		            axisData //横轴数据
             		        ],
             		        [
               		            4,        // PM10
               		            pm10, // 新增数据
               		            false,     // 新增数据是否从队列头部插入
               		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
               		            axisData //横轴数据
               		        ]
            		    ]);
            		}, 1000);
                }
            	function chartDisplay(){
            		var item = $("#devices option:selected").text();
            		
            		
            		var len = 10;
            		var res = [];
            		while (len--) {
            		res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
            		    now = new Date(now - 1000);
            		}
            		var res1 = [];
            		var res2 = [];
            		var res3 = [];
            		var res4 = [];
            		var res5 = [];
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
            		// 使用刚指定的配置项和数据显示图表。
            		myChart.setOption(option);
            	}
        </script>
    </div>
</body>
</html>