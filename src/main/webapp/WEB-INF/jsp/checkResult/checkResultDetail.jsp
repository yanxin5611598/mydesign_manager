<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/all.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.validate.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
	</head>
	<body style="background: ${pageContext.request.contextPath}/image/background.jpg;">
		<form id="mainForm" method="post">
				<div class="rightCont">
					<p class="g_title fix">设备使用详情</p>
					<table class="tab1" width="100%">
						<tbody>
							<tr>
							<td align="right" width="10%">设备编号： </td>
							<td width="30%">
								<input disabled="true" id="weight" name="weight"  value="${result.deviceinfo}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">使用者：</td>
							<td width="30%">
								<input disabled="true" id="title" name="title" value="${result.username}" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">使用时间：</td>
							<td width="30%">
								<input disabled="true" id="title" name="title" value="${result.time}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">使用地点： </td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.gpsinfo}" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">空气质量等级：</td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.airrank}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">甲醛值：</td>
							<td width="30%">
								<input disabled="true" id="weight" name="weight"  value="${result.choh}mg/m³" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">温度值：</td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.tem}℃" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">湿度值： </td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.hum}%" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">PM2.5：</td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.pm25}ug/m³" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">PM10：</td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.pm10}ug/m³" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">评价内容：</td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${result.contentevaluate}" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
					  </tbody>
					</table>
					<div style="text-align: center; margin-top: 30px;">
						<input class="tabSub" value="返     回" type="button" onclick="location.href='${pageContext.request.contextPath}/getAllCheckResult'"/>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>