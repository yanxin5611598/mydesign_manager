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
	</HEAD>
	<script type="text/javascript">
		$(function(){
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			
			var base = +new Date(2018, 11, 3, 15, 12,10);
			var oneMin =  1000 * 60;
			var date = [];
			  
			var data = [36.5];
			var now = new Date(base);
			  
			function addData(shift) {
			    now = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/') + "\n" + [ now.getHours(), now.getMinutes(), now.getSeconds()].join(':');
			    date.push(now);
			    data.push(36.5 + Math.random().toFixed(2) * 4 - 2);    //保留两位小数
			  
			    if (shift) {
			        date.shift();
			        data.shift();
			    }
			  
			    now = new Date(+new Date(now) + oneMin);
			      
			}
			  
			for (var i = 1; i < 60; i++) {
			    addData();
			}
			  
			option = {
				title: {
				    text: '体温实时监控数据',
				    subtext: '纯属虚构'
				},
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,     //表示数据从原点开始
			        data: date
			    },
			    yAxis: {
			    	min: 34,
					max: 41,
			        boundaryGap: [0, '50%'],
			        type: 'value',
			        splitLine : {
						show : true
					}
			    },
			    //工具箱
			    toolbox: {
			        show: true,
			        left: 'center',
			        feature: {
			            dataZoom: {
			                yAxisIndex: 'none'
			            },
			            dataView: {readOnly: false},
			            magicType: {type: ['line', 'bar']},
			            restore: {},
			            saveAsImage: {}
			        }
			    },
			    //提示框
			    tooltip : {  
			                trigger: 'axis',  
			                formatter:function(params)  
			                {  
			                   var relVal = params[0].name;  
			                   for (var i = 0, l = params.length; i < l; i++) {  
			                        relVal += '<br/>' + params[i].seriesName + ' : ' + params[i].value+"℃";  
			                    }  
			                   return relVal;  
			                }  
			            }, 
			    //下面的时间轴
		        dataZoom: [{
		            startValue: '2018-11-01'
		        }, {
		            type: 'slider'
		        }],
		        
		        grid: { // 控制图的大小，调整下面这些值就可以，
		            y2: 80// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		        },
		        
		        //右上角的颜色分区
			    visualMap: {
		            top: 10,
		            right: 10,
		            precision:2,//设置小数精度，默认0没有小数
		            pieces: [{
		                gt: 35,
		                lte: 36,
		                label:"低体温",
		                color: '#87CEFA'
		            }, {
		                gt: 36,
		                lte: 37.3,
		                label:"正常体温",
		                color: '#008B45'
		            }, {
		                gt: 37.4,
		                lte: 38,
		                label:"低烧",
		                color: '#EE9572'
		            },{
		                gt: 38,
		                lte: 39,
		                label:"中烧",
		                color: '#EE3A8C'
		            },{
		                gt: 39,
		                lte: 41,
		                label:"高烧",
		                color: '#FF0000'
		            }], 
		            outOfRange: {
		                color: '#999'
		            }
		        },
			    
			    series: [
			        {
			            name:'xx体温实时数据',
			            type:'line',
			            smooth:true,
			            symbol: 'none',
			            stack: 'a',
			            
			            data: data,
			            markPoint: {
			                data: [
			                    {type: 'max', name: '最大值'},
			                    {type: 'min', name: '最小值'}
			                ]
			            },
			            
			            markLine: {
			                silent: true,
			                data: [{
			                    yAxis: 35
			                }, {
			                    yAxis: 36
			                },{
			                    yAxis: 37.3
			                }, {
			                    yAxis: 38
			                }, {
			                    yAxis: 39
			                }, {
			                    yAxis: 41
			                }]
			            }
			        }
			    ]
			};
			  
			setInterval(function () {
			    addData(true);
			    myChart.setOption({
			        xAxis: {
			            data: date
			        },
			        series: [{
			            name:'体温',
			            data: data
			        }]
			    });
			}, 1000);
			
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		})
        </script>
	<body>
    	
        <div id="container" style="width: 1000px;height: 600px;" align="center"></div>
</body>
</html>