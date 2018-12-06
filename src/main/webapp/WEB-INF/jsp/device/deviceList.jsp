<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/all_my.css"/>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
		</script> --%>
	</HEAD>
	<body>
		<br>
			<div class="row">
			<div class="col-md-12">
			<table  class="table table-hover">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>设备最近使用情况列表</strong>
						</TD>
					</tr>
					<form action="${pageContext.request.contextPath }/deviceSearch" id="mainForm" method="post">
						<table class="tab1">
						<tbody>
							<tr>
								<td align="center" width="165">标题(根据设备ID或使用者模糊查询)：</td>
								<td>
									<input name="userinput" id="userinput" value="" class="allInput" type="text"/>
								</td>
	                            <td style="text-align: center;" width="150">
	                            	<input class="tabSub" value="查询" onclick="" type="submit"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                            </td>
	       					</tr>
						</tbody>
					</table></form>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table class="table table-hover">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										设备ID
									</td>
									<td align="center" width="10%">
										最近一次使用者
									</td>
									<td width="20%" align="center">
										使用地点
									</td>
									<td width="15%" align="center">
										使用时间
									</td>
									<td width="10%" align="center">
										使用者联系方式
									</td>
									<td width="20%" align="center">
										设备产地
									</td>
									<td width="15%" align="center">
										设备出厂时间
									</td>
								</tr>
								<c:forEach  var="device" items="${page.list}" varStatus="status">
									<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${device.deviceid}"></c:out>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${device.username}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												<c:out value="${device.placecurrent}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<fmt:formatDate value="${device.timecurrent}" type="both" dateStyle="long"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${device.phone}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												<c:out value="${device.placefrom}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<fmt:formatDate value="${device.timefrom}" type="both" dateStyle="long"/>
											</td>
										</tr>
								</c:forEach>
							</table>
							</td>
							</tr>
				</TBODY>
			</table>
			<!-- 分页 -->
			<div class="page fix">
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=1" class="first">首页</a>
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=${page.pageNum-1}" class="pre">上一页</a>
				当前第<c:out value="${page.pageNum}"/>页|共<c:out value="${page.pages}"/>页
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=${page.pageNum+1}" class="next">下一页</a>
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=${page.pages}" class="last">末页</a>
			</div>
</div></div>
	</body>
</HTML>

