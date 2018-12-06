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
		<script type="text/javascript">
			function check(){
				var deviceID = document.getElementById("deviceID").value;
				var username = document.getElementById("username").value;
				var place = document.getElementById("place").value;
				if(deviceID == "" && username==""&&place==""){
					alert("请输入内容");
					return false;
				}
				return true;
			}
		</script>
		
	</HEAD>
	<body>
		<br>
		<div class="row">
			<div class="col-md-12">
			<table  class="table table-hover">
				<TBODY>
					<tr>
						<td class="ta_01"align="center" bgColor="#afd1f3">
							<strong>设备使用历史数据列表</strong>
						</TD>
					</tr>
					<form action="${pageContext.request.contextPath }/checkResultSearch" onsubmit="return check()" id="mainForm" method="post">
						<table class="tab1">
						<tbody>
							<tr>
								<td align="center" width="165">检索工具：&nbsp;&nbsp;&nbsp;</td>
								<td align="center" width="165">设备ID：&nbsp;&nbsp;&nbsp;</td>
								<td>
									<input name="deviceID" id="deviceID" value="" class="allInput" type="text"/>
								</td>
								<td align="center" width="165">用户名：&nbsp;&nbsp;&nbsp;</td>
								<td>
									<input name="username" id="username" value="" class="allInput" type="text"/>
								</td>
								<td align="center" width="165">使用地点：&nbsp;&nbsp;&nbsp;</td>
								<td>
									<input name="place" id="place" value="" class="allInput" type="text"/>
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

									<td align="center" width="6%">
										序号
									</td>
									<td align="center" width="5%">
										设备编号
									</td>
									<td align="center" width="10%">
										使用者
									</td>
									<td width="20%" align="center">
										使用地点
									</td>
									<td width="5%" align="center">
										房间号
									</td>
									<td width="15%" align="center">
										使用时间
									</td>
									<td width="7%" align="center">
										空气质量等级
									</td>
									<td width="7%" align="center">
										详情
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<c:forEach  var="result" items="${page.list}" varStatus="status">
									<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												<c:out value="${status.count}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${result.deviceinfo}"></c:out>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${result.username}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="20%">
												<c:out value="${result.gpsinfo}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<c:out value="${result.roominfo}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<c:out value="${result.time}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${result.airrank}"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/checkResultDetail?id=${result.id }">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/checkResultDelete?id=${result.id }">
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
				<a href="${pageContext.request.contextPath}/getAllCheckResult?pageNumber=1" class="first">首页</a>
				<a href="${pageContext.request.contextPath}/getAllCheckResult?pageNumber=${page.pageNum-1}" class="pre">上一页</a>
				当前第<c:out value="${page.pageNum}"/>页|共<c:out value="${page.pages}"/>页
				<a href="${pageContext.request.contextPath}/getAllCheckResult?pageNumber=${page.pageNum+1}" class="next">下一页</a>
				<a href="${pageContext.request.contextPath}/getAllCheckResult?pageNumber=${page.pages}" class="last">末页</a>
			</div>
			</div>
			</div>
	</body>
</HTML>

