<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style.css" type="text/css" rel="stylesheet" />
<style type="text/css">
<!--
body {
	background-color:#E7EAEB;
margin:0px;

padding:0px;

background-color:#E7EAEB;

font-family:"微软雅黑","黑体","宋体";

font-size:20px;

height:50px;




}
body,td,th {
	color: #000000;
}
-->
    </style>
<style>
BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }
</style>
</head>

<body>

<form name="Form1" method="post" action="name.aspx" id="Form1">
<!-- 
	  安卓端：需要实现的是
	  后    台：完善
	  下位机：
 -->
	<table width="100%" border="0" height="88" border="1" background="${pageContext.request.contextPath}/images/back1.JPG">
		<tr>
			<td colspan=3 class="ta_01" align="center" bgcolor="#afd1f3"><strong>系统首页</strong></td>
		</tr>

		<tr>
			<td width="65%" height="84" align="center" valign="top">
				<span class="Style1">欢迎您,<%=session.getAttribute("managerName")%>,您属于:<%=session.getAttribute("managerGroup") %>！</span>
			</td>
		</tr>
		<tr><td height=2></td></tr>
	
	</table>

	</form>

</body>

</html>