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
		<!-- å¼å¥htmlé¡µé¢éè¦ä½¿ç¨çEChartsçjsæä»¶ -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
		<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	</HEAD>
<body>
<script type="text/javascript">
$('#YWaitDialog').style.display="none";
</script>
<div id="YWaitDialog"   
    style="background-color: #e0e0e0;   
    position: absolute;   
    margin: auto;  
    height: 100%;   
    width: 100%;">  
    <p style="text-align: center; align: middle;">  
    	<img src="${pageContext.request.contextPath }/image/loading2.gif" style="align:middle"/>  
    </p>  
</div>
</body>
</html>