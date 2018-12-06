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
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/js/ajaxfileupload.js"></script>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/all_my.css"/>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>--%>
		<script type="text/javascript">
		
		</script>
	</HEAD>
	<body>
		<br>
			<div class="row">
			<div class="col-md-12">
			<table  class="table table-hover">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3" width="80%">
							<strong>设备使用情况信息列表</strong>
						</td>
					</tr>
					<form action="${pageContext.request.contextPath }/advertSearch" id="mainForm" method="post">
						<table class="tab1">
						<tbody>
							<tr>
								<td align="center" width="165">标题(根据设备使用者模糊查询)：</td>
								<td>
									<input name="title" id="title" value="" class="allInput" type="text"/>
								</td>
	                            <td style="text-align: right;" width="150">
	                            	<input class="tabSub" value="查询" onclick="" type="submit"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                            	<input class="tabSub" value="添加" onclick="location.href='${pageContext.request.contextPath }/addAdvertInit'" type="button"/>
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
									<td align="center" width="10%">
										设备序号
									</td>
									<td align="center" width="14%">
										用户
									</td>
									<td align="center" width="14%">
										是否归还
									</td>
								</tr>
								<c:forEach  var="equipment" items="${page.list}" varStatus="status">
									<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="6%">
												<c:out value="${status.index + 1}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<c:out value="${equipment.number}"></c:out>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												<c:out value="${equipment.username}"></c:out>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="14%">
												<c:if test="${equipment.isgiveback==true}">
													<c:out value="是"></c:out>	
												</c:if>
												<c:if test="${!equipment.isgiveback==true}">
													<c:out value="否"></c:out>	
												</c:if>
											</td>
											
											<td align="center" style="HEIGHT: 22px" width="7%">
												<a href="${pageContext.request.contextPath}/advertEditInit?id=${advert.id}">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
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
				<a href="${pageContext.request.contextPath}/getAllAdvert?pageNumber=1" class="first">首页</a>
				<a href="${pageContext.request.contextPath}/getAllAdvert?pageNumber=${page.pageNum-1}" class="pre">上一页</a>
				当前第<c:out value="${page.pageNum}"/>页|共<c:out value="${page.pages}"/>页
				<a href="${pageContext.request.contextPath}/getAllAdvert?pageNumber=${page.pageNum+1}" class="next">下一页</a>
				<a href="${pageContext.request.contextPath}/getAllAdvert?pageNumber=${page.pages}" class="last">末页</a>
			</div>
</div></div>


	<!-- 员工新增的Modal -->
	 <!-- <div class="modal fade" id="add_advert_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">广告新增页面</h4>
	      </div>
	      <div class="modal-body">
	      这里使用的是springmvc的文件上传操作，需要将form表单的方法改为post，并且将enctype设置为multipart/form-data
	      在使用springmvc进行文件上传操作的时候，springmvc默认是没有装配MultipartResolver,故在默认情况下其不能够处理文件上传操
	      		作，需要在对应的springmvc配置文件中进行配置
	        <form id="save_form" class="form-horizontal" enctype="multipart/form-data" method="get">
	          <div class="form-group">
			    <label class="col-sm-2 control-label">广告商</label>
			    <div class="col-sm-10">
			      <input name="advertiser" type="text" class="form-control" id="advertiser" placeholder="Advertiser">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">广告图片</label>
			    <div class="col-sm-10">
			      <input name="advertpicture" type="file" accept="image/png,image/gif,image/jpeg" class="form-control" id="advertpicture" placeholder="Advert">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">广告描述</label>
			    <div class="col-sm-10">
			      <input name="advertdiscrib" type="text" class="form-control" id="advertdiscrib">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">广告分类</label>
			    <div class="col-sm-10">
			      <input name="advertcatalog" type="text" class="form-control" id="advertcatalog">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">起始时间</label>
			    <div class="col-sm-10">
			      <input name="advertstarttime" type="datetime-local" class="form-control" id="advertstarttime">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">结束时间</label>
			    <div class="col-sm-10">
			      <input name="advertstoptime" type="datetime-local" class="form-control" id="advertstoptime">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="saveAdvert">保存</button>
	      </div>
	      </form></div>
	    </div>
	 </div>
	</div> -->

	</body>
</HTML>

