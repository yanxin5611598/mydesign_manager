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
							<strong>订单信息列表</strong>
						</TD>
					</tr>
					<form action="${pageContext.request.contextPath }/orderSearch" id="mainForm" method="post">
						<table class="tab1">
						<tbody>
							<tr>
								<td align="center" width="165">标题(根据订单用户模糊查询)：</td>
								<td>
									<input name="username" id="username" value="" class="allInput" type="text"/>
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

									<td align="center" width="18%">
										订单编号
									</td>
									<td align="center" width="17%">
										订单用户
									</td>
									<td width="7%" align="center">
										订单名称
									</td>
									<td width="7%" align="center">
										订单金额
									</td>
									<td width="7%" align="center">
										订单时间
									</td>
								</tr>
								<c:forEach  var="userorder" items="${page.list}" varStatus="status">
									<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												<c:out value="${status.count}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<c:out value="${userorder.username}"></c:out>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<c:out value="${userorder.tradename}"/>
											</td>
									
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<c:out value="${userorder.amount}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<fmt:formatDate value="${userorder.time}" type="both" dateStyle="long"/>
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
				<a href="${pageContext.request.contextPath}/getAllOrder?pageNumber=1" class="first">首页</a>
				<a href="${pageContext.request.contextPath}/getAllOrder?pageNumber=${page.pageNum-1}" class="pre">上一页</a>
				当前第<c:out value="${page.pageNum}"/>页|共<c:out value="${page.pages}"/>页
				<a href="${pageContext.request.contextPath}/getAllOrder?pageNumber=${page.pageNum+1}" class="next">下一页</a>
				<a href="${pageContext.request.contextPath}/getAllOrder?pageNumber=${page.pages}" class="last">末页</a>
			</div>
</div></div>
	</body>
</HTML>

