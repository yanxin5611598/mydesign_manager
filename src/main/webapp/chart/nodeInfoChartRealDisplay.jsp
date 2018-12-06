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
        <div id="temAndHum" style="width: 500px;height: 300px;" align="left"></div>
    	<script type="text/javascript">
            $(document).ready(function(){
            	var myChart;
            	var url = "${pageContext.request.contextPath}/dataplay";
            	myChart = echarts.init(document.getElementById("temAndHum"));
            	myChart.showLoading(  
            	  {text: 'Loding...'  }
            	); 
            	var xcategory;  
            	var yvalues;
            	//通过Ajax获取数据  
	            $.ajax({  
	                type : "post",  
	                async : false, //同步执行  
	                url : url,  
	                dataType : "json", //返回数据形式为json  
	                success : function(result) {  
	                    if (result) { 
	                    	xcategory = result.xcategory;
	                    	yvalues = result.yseries[0].data;
	                    	var legend = result.legend;
	                    	/* alert(xcategory+","+yvalues+","+legend); */
	                    	var option = {  
	                                title : {  
	                                    text : "2017-2018年月度订单统计",  
	                                    subtext : "订单信息",  
	                                    sublink : "${pageContext.request.contextPath}/getAllPurchase?pageNumber=1"  
	                                },  
	                                tooltip : {  
	                                    trigger : 'axis'  
	                                },  
	                                legend : {  
	                                    data : [ "月订单金额" ]  
	                                },  
	                                toolbox : {  
	                                    show : true,  
	                                    feature : {  
	                                        mark : {  
	                                            show : true  
	                                        },  
	                                        dataView : {  
	                                            show : true,  
	                                            readOnly : false  
	                                        },  
	                                        magicType : {  
	                                            show : true,  
	                                            type : [ 'line', 'bar','pie','stack','tiled' ]  
	                                        },
	                                        //支持图形缩放功能 
	                                        /* dataZoom : {
	                                        	show : true,
	                                        	xAxisIndex : [0,11],
	                                        	yAxisIndex : [0,4]
	                                        }, */
	                                        restore : {  
	                                            show : true  
	                                        },  
	                                        saveAsImage : {  
	                                            show : true  
	                                        }  
	                                    }  
	                                },  
	                                calculable : true,  
	                                xAxis : [ {  
	                                    type : 'category',  
	                                    boundaryGap : false,  
	                                    data :  xcategory,
	                                    name : '月份'
	                                } ],  
	                                yAxis : [ {  
	                                    type : 'value',  
	                                    name : '金额',
	                                    axisLabel : {  
	                                        formatter : '{value} 元'  
	                                    },  
	                                    splitArea : {  
	                                        show : true  
	                                    }  
	                                } ],  
	                                grid : {  
	                                    width : '90%'  
	                                },  
	                                series : [ {  
	                                    name : '订单金额',  
	                                    type : 'line',  
	                                    data : yvalues,//必须是Integer类型的,String计算平均值会出错  
	                                    markPoint : {  
	                                        data : [ {  
	                                            type : 'max',  
	                                            name : '最大值'  
	                                        }, {  
	                                            type : 'min',  
	                                            name : '最小值'  
	                                        } ]  
	                                    },  
	                                    markLine : {  
	                                        data : [ {  
	                                            type : 'average',  
	                                            name : '平均值'  
	                                        } ]  
	                                    }  
	                                } ]  
	                            };
	                        /* options.legend.data = result.legend;  
	                        options.xAxis[0].data = result.xcategory;  
	                        options.series[0].data = result.yseries[0].data;  
	                        */
	                        myChart.hideLoading();//隐藏掉等待部分  
	                        myChart.setOption(option);  //这一句至关重要  
	                    }  
	                },  
	                error : function(errorMsg) {  
	                    alert("不好意思，大爷，图表请求数据失败啦!");  
	                    myChart.hideLoading();  
	                }  
	            });  
            })
        </script>
    </div>
</body>
</html>