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
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/all_my.css"/>
		<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
		</script> --%>
	</HEAD>
	<body>
		<br>
			<div class="row">
			<table class="table table-hover" id="employeeTable">
					<thead>
						<tr>
							<th style="text-align:center;" width="20%">
								<input type="checkbox" id="sum_check"/>
							</th>
							<th style="text-align:center;"
												width="20%">#</th>
							<th style="text-align:center;"
												width="30%">设备ID</th>
							<th style="text-align:center;"
												width="30%">设备状态</th>
						</tr>
						
					</thead>
					<tbody>
						<c:forEach  var="device" items="${page.list}" varStatus="status">
							<c:choose>
								<c:when test="${device.deviceState eq '1' }">
									<!-- 在线 -->
									<tr class="success">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="20%"><input type='checkbox' class='item_check' checked="checked"/></td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="20%">${status.count}</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="30%">
											<c:out value="${device.deviceID}"></c:out>
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="30%">
											<strong><font color="green">在线</font></strong>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<!-- 离线 -->
									<tr class="warning">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="20%"><input type='checkbox' class='item_check'/></td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="20%">${status.count}</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="30%">
											<c:out value="${device.deviceID}"></c:out>
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="30%">
											<c:out value="离线"/>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
			</table>
			<!-- 分页 -->
			<div class="page fix">
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=1" class="first">首页</a>
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=${page.pageNum-1}" class="pre">上一页</a>
				当前第<c:out value="${page.pageNum}"/>页|共<c:out value="${page.pages}"/>页
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=${page.pageNum+1}" class="next">下一页</a>
				<a href="${pageContext.request.contextPath}/getAllDevice?pageNumber=${page.pages}" class="last">末页</a>
			</div>
	</div>
	</body>
</HTML>

