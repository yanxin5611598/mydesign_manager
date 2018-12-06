<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<style type="text/css">
<!--
body {
margin:0px;

padding:0px;


font-family:"微软雅黑","黑体","楷体";

font-size:20px;

height:50px;

background-size:cover;   


}
body,td,th {
	color: #000000;
}
-->
    </style>
</head>

<body style="background: ${pageContext.request.contextPath}/image/background.jpg;">
<form name="Form1" method="post" action="name.aspx" id="Form1">
<!-- 
	  安卓端：需要实现的是
	  后    台：完善
	  下位机：
 -->
	<table width="100%" border="0" height="300" border="1" background="${pageContext.request.contextPath}/images/back1.JPG">
		<tr>
			<td colspan=3 class="ta_01" align="center" bgcolor="#afd1f3"><strong>系统首页</strong></td>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<td width="65%" height="200" align="center" valign="top">
				<span class="Style1">欢迎您,<%=session.getAttribute("managerName")%>,您属于:<%=session.getAttribute("managerGroup") %>！</span>
			</td>
		</tr>
		<tr><td height=2></td></tr>
	
	</table>

	</form>
</body>

</html>