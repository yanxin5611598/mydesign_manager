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
		<!-- 引入html页面需要使用的ECharts的js文件 -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
		<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	</HEAD>
	<script type="text/javascript">
			var chartRadar;
			var choh,pm25,pm10,range;
			$(document).ready(function(){
				$('#select_device').modal({   //bootstrap打开模态框的方法
					backdrop:'static'   //设置当用户点击模态框的其它背景的地方的时候，模态框不会消失     否则消失
				});
				//通过Ajax获取所有传感器的数据集合
				var url = "${pageContext.request.contextPath}/userUpDown/generateMeanRadarChart";
				chartRadar = echarts.init(document.getElementById("radar"));
				$.ajax({  
	                type : "post",  
	                async : false, //同步执行  
	                url : url,  
	                dataType : "json", //返回数据形式为json  
	                success : function(result) {  
	                    if (result) { 
	                    	//需要加上date和index  http://echarts.baidu.com/examples/editor.html?c=radar-aqi
	                    	var mydata = result.yseries[0].data.toString();
	                    	choh = mydata.split(",")[0]
	                    	pm25 = mydata.split(",")[3]
	                    	pm10 = mydata.split(",")[4]
	                    	var arr = []
	                    	var mydataSplit = mydata.split(',')
	                    	for(var i = 0;i<mydataSplit.length;i++){
	                    		arr.push(mydataSplit[i])
	                    	}
	                    	var legend = result.legend;
	                    	var legendArr = []
	                    	legendArr.push(legend)
	          		         var optionRadar = {
	          		              title: {
	          		                  text: '各空气参数 - 雷达图',
	          		              },
	          		              tooltip: {},
	          		              legend: {
	          		                  data: legendArr,
	          		              },
	          		              radar: {
	          		            	  name: {
		          		                  textStyle: {
		          		                      color: '#fff',
		          		                      backgroundColor: '#999',
		          		                      borderRadius: 3,
		          		                      padding: [3, 5]
		          		                 }
		          		              },
	          		                  indicator: [
	          		                      {name: '甲醛', max: 0.3},
	          		                      {name: '温度', max: 100},
	          		                      {name: '湿度', max: 100},
	          		                      {name: 'PM2.5', max: 250},
	          		                      {name: 'PM10', max: 350}
	          		                  ]
	          		              },
	          		              series: [{ name:'成都',
	                 	                    type: 'radar',
	                 	                    data:  [
	                 	                           {
	                 	                              value : arr,
	                 	                              name : '各参数平均值'
	                 	                          }]
	          		         		}]
	          		          };
	          		       chartRadar.setOption(optionRadar);  //这一句至关重要 
	          		   		//信息显示
	          		       choh = choh/0.3
	          		       pm25 = pm25/250
	          		       pm10 = pm10/350
	          		       var max = Math.max(choh,pm25,pm10)
	          		       if(max == choh){
	          		    	 var info = $("<p class='bg-moredanger' align='center'><font id='info' size='5'>当前首要污染物是:甲醛</font> </p>")
							 $("#radar_info").append(info);
	          		       }else if(max == pm25){
	          		    	 var info = $("<p class='bg-moredanger' align='center'><font id='info' size='5'>当前首要污染物是:PM2.5</font> </p>")
						     $("#radar_info").append(info);
	          		       }else if(max == pm10){
	          		    	 var info = $("<p class='bg-moredanger' align='center'><font id='info' size='5'>当前首要污染物是:PM10</font> </p>")
							 $("#radar_info").append(info);
	          		       }else{
	          		    	 var info = $("<p class='bg-moredanger' align='center'><font id='info' size='3'>当前首要污染物是:CO2</font> </p>")
							 $("#radar_info").append(info);
	          		       }
	                    }
	                },  
	                error : function(errorMsg) {  
	                    alert("不好意思,图表请求数据失败啦!");  
	                    myChart.hideLoading();  
	                }  
				});
			});
			var param;
			$(document).on("click","button[name='param']",function(){
				$('#select_device').modal('hide');
				$("#mydiv").empty();
				var my_btn = $("<button type='button' id= 'btn_select' align='center' style='width:200px;height:90px;margin-left: 250px;' class='btn btn-primary' onclick='return select()' data-toggle='button' aria-pressed='false' autocomplete='off'><font size='5'>切换至其它类别</font> </button>")
				$("#mydiv").append(my_btn);
				param = $(this).html();
				var myChart;
            	var url = "${pageContext.request.contextPath}/userUpDown/generateChart";
            	myChart = echarts.init(document.getElementById("main"));
            	myChart.showLoading(  
            	  {text: 'Loading...'  }
            	); 
            	var xcategory;  
            	var yvalues;
            	//通过Ajax获取数据  
	            $.ajax({  
	                type : "post",  
	                async : false, //同步执行  
	                url : url,  
	                data:"class="+param,
	                dataType : "json", //返回数据形式为json  
	                success : function(result) {  
	                    if (result) { 
	                    	console.log(result);
	                    	xcategory = result.xcategory;
	                    	yvalues = result.yseries[0].data;
	                    	var legend = result.legend;
	                    	/* alert(xcategory+","+yvalues+","+legend); */
	                    	var option = {  
	                                title : {  
	                                    text : "空气参数曲线",  
	                                    subtext : param 
	                                    //sublink : "${pageContext.request.contextPath}/getAllOrder?pageNumber=1"  
	                                },  
	                                tooltip : {  
	                                    trigger : 'axis'  
	                                },  
	                                legend : {  
	                                    data : [ "值" ]  
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
	                                    name : '时间'
	                                } ],  
	                                yAxis : [ {  
	                                    type : 'value',  
	                                    name : '值',
	                                    axisLabel : {  
	                                        formatter : '{value}'  
	                                    },  
	                                    splitArea : {  
	                                        show : true  
	                                    }  
	                                } ],  
	                                grid : {  
	                                    width : '90%'  
	                                },  
	                                series : [ {  
	                                    name : param ,  
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
	                        myChart.hideLoading();//隐藏掉等待部分  
	                        myChart.setOption(option);  //这一句至关重要  
	                    }  
	                },  
	                error : function(errorMsg) {  
	                    alert("不好意思,图表请求数据失败啦!");  
	                    myChart.hideLoading();  
	                }  
				});
         });  
		function select(){
			$('#select_device').modal({   //bootstrap打开模态框的方法
				backdrop:'static'   //设置当用户点击模态框的其它背景的地方的时候，模态框不会消失     否则消失
			});
		}
        function postImage() {
        	//$('#YWaitDialog').style.display="block";
        	var deviceID = $("#deviceID").val()
			var username = $("#username").val()
            var imageName=username+"_"+Date.parse(new Date())+".png"
            var imageInfo = $("#info").text()
            alert(imageInfo)
            // 向后台发起请求保存图片到指定目录.
            $.ajax({
                type: 'POST',
                dataType: "json",
                url: "${pageContext.request.contextPath}/userUpDown/saveRadarImage?imageName="+imageName,
                data: {"picInfo":chartRadar.getDataURL()
                	  ,"username":username
                	  ,"imageInfo":imageInfo},
                success: function(result) {
                    if(result.result_code == 1){
                    	//$('#YWaitDialog').style.display="none";
                    	alert("保存成功");
                    	//禁止使用保存雷达图的按钮
                    	$("#btn_save").attr("disabled",true);
                    }else{
                    	alert("保存失败");
                    }
                }
            });
        }

		          
     	</script>
	<body id="mybody">
    	<div class="modal fade bs-example-modal-lg" id="select_device" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modal-lg" role="document"  style="width:600px;height:700px;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">请选择要查看的类别</h4>
		      </div>
		   
		      <div class="modal-body">
				  <div class="form-group">
				    <button type="button" class="btn btn-primary" name="param" id="tem" onclick="get_deviceInfo()">温度</button>
				   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary" name="param" id="hum" onclick="get_deviceInfo()">湿度</button>
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary" name="param" id="choh" onclick="get_deviceInfo()">甲醛</button>
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary" name="param" id="pm25" onclick="get_deviceInfo()">PM2.5</button>
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary" name="param" id="pm10" onclick="get_deviceInfo()">PM10</button>
				   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary" name="param" id="range" onclick="get_deviceInfo()">等级</button>
				  </div>
		      </div>
		    </div>
		 </div>
		</div>
		
		
        <div id="main" style="width: 1200px;height: 500px;" align="center"></div>
        <div id="mydiv" align="center" style="margin-top:10px;">
        	
        </div>
        <div id="radar" style="width: 1000px;height: 600px;margin-left: 100px;" align="center">
        	
        </div>
        <div id="radar_info" style="width: 400px;height: 60px;margin-left: 420px;" align="center"></div>
        <div align="center" style="margin-top:10px;">
        <button type='button' id= 'btn_save' align='center' style='width:200px;height:90px;margin-left: 250px;' class='btn btn-primary' onclick='return postImage()' data-toggle='button' aria-pressed='false' autocomplete='off'>
        	<font size='5'>保存雷达图</font>
        </button>
        </div>
        <input type="hidden" id="deviceID" value="${deviceID }"/>
        <input type="hidden" id="username" value="${username }"/>
        <%-- <div id="YWaitDialog"   
		    style="background-color: #e0e0e0;   
		    position: absolute;   
		    margin: auto;
		    display:none;  
		    height: 100%;   
		    width: 100%;">  
		    <p style="text-align: center; align: middle;">  
		    	<img src="${pageContext.request.contextPath }/image/loading2.gif" style="align:middle"/>  
		    </p>  
		</div> --%>
</body>
</html>