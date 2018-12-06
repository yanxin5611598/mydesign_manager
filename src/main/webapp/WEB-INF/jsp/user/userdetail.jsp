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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/content/adModify.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form id="mainForm" method="post" action="${basePath}/ad/modify" enctype="multipart/form-data">
				<div class="rightCont">
					<p class="g_title fix">用户详情</p>
					<table class="tab1" width="100%">
						<tbody>
							<tr>
							<td align="right" width="10%">id: </td>
							<td width="30%">
								<input disabled="true" id="weight" name="weight"  value="${user.userid}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">用户名: </td>
							<td width="30%">
								<input disabled="true" id="title" name="title" value="${user.username}" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">性别: </td>
							<td width="30%">
								<input disabled="true" id="title" name="title" value="${user.gender}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">年龄: </td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${user.age}" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">电话: </td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${user.phone}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">Email: </td>
							<td width="30%">
								<input disabled="true" id="weight" name="weight"  value="${user.email}" class="allInput" style="width:100%;" type="text"/>
							</td>
							</tr>
							<tr>
							<td align="right" width="10%">积分: </td>
							<td width="30%">
								<input disabled="true" id="link" name="link"  value="${user.rewardpoint}" class="allInput" style="width:100%;" type="text"/>
							</td>
							<td align="right" width="10%">是否VIP: </td>
							<td width="30%">
								<c:if test="${user.isvip==1}">
									<img src="${pageContext.request.contextPath}/image/vipyes.png" border="0" style="CURSOR: hand">
								</c:if>
								<c:if test="${user.isvip == 0}">
									<img src="${pageContext.request.contextPath}/image/vipno.png" border="0" style="CURSOR: hand">
								</c:if>
							</td>
							</tr>
					  </tbody>
					</table>
					<div style="text-align: center; margin-top: 30px;">
						<input class="tabSub" value="返     回" type="button" onclick="location.href='${pageContext.request.contextPath}/getAllUser'"/>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>