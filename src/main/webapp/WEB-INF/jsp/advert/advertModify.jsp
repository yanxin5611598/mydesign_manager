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
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/all.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/pop.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
		<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
		</script> --%>
		<script type="text/javascript">
		function modify() {
			alert("hello");
				$("#advert_add").submit();
		}
		</script>
	</HEAD>
	<body style="background: #e1e9eb;">
		<form id="advert_add" name="Form1" action="${pageContext.request.contextPath}/advertModify" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${advert.id}"/>
			<div class="right"  style="background: #f5fafe;">
				<div class="rightCont">
					<p class="g_title fix">广告修改</p>
					<table class="tab1" width="100%">
						<tbody>
							<tr>
							<td align="right" width="10%">标题<font color="red">*</font>：</td>
							<td width="30%">
								<input id="title" name="title" class="allInput" style="width:100%;" type="text" value="${advert.title}"/>
							</td>
							<td align="right" width="10%">上传图片<font color="red">*</font>：</td>
							<td width="30%">
								<a href="${displayImage}" target="_blank"><font color="green">查看图片</font></a>
								<input type="hidden" name="imgFileName" value="${advert.imgFileName}"/>
								<input id="imgFile" name="imgFile" class="allInput" style="width:100%;" type="file"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="10%">链接地址<font color="red">*</font>：</td>
							<td width="30%">
								<input id="link" name="link"  class="allInput" style="width:100%;" type="text" value="${advert.link}"/>
							</td>
							<td align="right" width="10%">权重(值越大排名越靠前)<font color="red">*</font>：</td>
							<td width="30%">
								<input id="weight" name="weight"  class="allInput" style="width:100%;" type="text" value="${advert.weight}"/>
							</td>
						</tr>
					  </tbody>
					</table>
					<div style="text-align: center; margin-top: 30px;">
						<input class="tabSub" value="保     存" onclick="modify();" type="button"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="tabSub" value="返     回" type="button" onclick="location.href = '${pageContext.request.contextPath}/getAllAdvert'"/>
					</div>
				</div>
			</div>
		</form>
	</body>
</HTML>

