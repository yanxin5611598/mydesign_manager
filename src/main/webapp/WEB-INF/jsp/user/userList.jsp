<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<td class="ta_01"align="center" bgColor="#afd1f3">
							<strong>用户信息列表</strong>
						</TD>
					</tr>
					<form action="${pageContext.request.contextPath }/userSearch" id="mainForm" method="post">
						<table class="tab1">
						<tbody>
							<tr>
								<td align="center" width="165">标题(根据用户名模糊查询)：</td>
								<td>
									<input name="username" id="username" value="" class="allInput" type="text"/>
								</td>
	                            <td style="text-align:center;" width="150">
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

									<td align="center" width="14%">
										用户编号
									</td>
									<td align="center" width="20%">
										用户名称
									</td>
									<td align="center" width="10%">
										用户状态
									</td>
									<td width="7%" align="center">
										用户详情
									</td>
									<td width="7%" align="center">
										操作
									</td>
								</tr>
								<c:forEach  var="user" items="${page.list}" varStatus="status">
									<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												<c:out value="${status.count}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												<c:out value="${user.username}"></c:out>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<%-- <c:out value="A"></c:out> --%>
												<c:if test="${user.isvip==1}">
													<img src="${pageContext.request.contextPath}/image/vip_24.png" border="0" style="CURSOR: hand">
												</c:if>
												<c:if test="${user.isvip == 0}">
													<img src="${pageContext.request.contextPath}/image/vip_black.png" border="0" style="CURSOR: hand">
												</c:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/userDetail?userid=${user.userid }">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/userDelete?userid=${user.userid}">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
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
				<a href="${pageContext.request.contextPath}/getAllUser?pageNumber=1" class="first">首页</a>
				<a href="${pageContext.request.contextPath}/getAllUser?pageNumber=${page.pageNum-1}" class="pre">上一页</a>
				当前第<c:out value="${page.pageNum}"/>页|共<c:out value="${page.pages}"/>页
				<a href="${pageContext.request.contextPath}/getAllUser?pageNumber=${page.pageNum+1}" class="next">下一页</a>
				<a href="${pageContext.request.contextPath}/getAllUser?pageNumber=${page.pages}" class="last">末页</a>
			</div>
			</div>
			</div>
	</body>
</HTML>

