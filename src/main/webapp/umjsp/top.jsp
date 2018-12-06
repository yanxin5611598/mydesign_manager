<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>

<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css"/>  
<link href="${pageContext.request.contextPath}/css/all.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/pop.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
			
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/json.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/system/index.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script type="text/javascript">
	function custom_close(){
		//自定义关闭当前窗口
		if(confirm("您确定要注销吗?")){
			if (navigator.userAgent.indexOf("MSIE") > 0) {//close IE  
				  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {  
				   window.opener = null;  
				   window.close();  
				  } else {  
				   window.open('', '_top');  
				   window.top.close();  
				  }  
				 }  
				 else if (navigator.userAgent.indexOf("Firefox") > 0) {//close firefox  
				  window.location.href = 'about:blank ';  
				 } else {//close chrome;It is effective when it is only one.  
				  window.opener = null;  
				  window.open('', '_self');  
				  window.close();  
				 }
		}
	}
	/**
	* 打开密码修改弹出层
	*/
	/* function openAddDiv(){
		alert("hello");
		$('#modifyPassword_modal').modal({   //bootstrap打开模态框的方法
			backdrop:'static'   //设置当用户点击模态框的其它背景的地方的时候，模态框不会消失     否则消失
		});
	} */
	</script>
	<body>
		<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img width="100%" src="${pageContext.request.contextPath}/images/top_01.jpg">
				</td>

				<td width="100%" background="${pageContext.request.contextPath}/images/top_10.jpg">
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" valign="bottom" background="${pageContext.request.contextPath}/images/mis_01.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="75%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- 这一部分用于实时显示时间 -->
								<font color="#000000"> 
								<script language="JavaScript">
								<!--
								tmpDate = new Date;
								date = tmpDate.getDate();
								month= tmpDate.getMonth()+1;
								year= tmpDate.getFullYear();
								document.write(year);
								document.write("年");
								document.write(month);
								document.write("月");
								document.write(date);
								document.write("日 ");
								
								myArray=new Array(6);
								myArray[0]="星期日"
								myArray[1]="星期一"
								myArray[2]="星期二"
								myArray[3]="星期三"
								myArray[4]="星期四"
								myArray[5]="星期五"
								myArray[6]="星期六"
								weekday=tmpDate.getDay();
								if (weekday==0 | weekday==6)
								{
								document.write(myArray[weekday])
								}
								else
								{document.write(myArray[weekday])
								};
								// -->
								</script> </font>
							</td>
							<td width="25%">
								欢迎您! &nbsp; <font color="blue"><%=session.getAttribute("managerName") %></font>
								[类别]:<font color="blue"><%=session.getAttribute("managerGroup") %></font>
								&nbsp;
								<a href="${pageContext.request.contextPath}/umjsp/modifyPassword.jsp" target="mainFrame">[修改密码]</a>
								&nbsp;
								<a href="" onclick="custom_close()">[退出登录]</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		
		<!-- 管理员修改密码的Modal -->
	 <div class="modal fade" id="modifyPassword_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">管理员密码修改页面</h4>
	      </div>
	      <div class="modal-body">
	        <form id="modify_form" class="form-horizontal" enctype="multipart/form-data" method="get">
	          <div class="form-group">
			    <label class="col-sm-2 control-label">请输入旧密码:</label>
			    <div class="col-sm-10">
			      <input name="oldPassword" type="text" class="form-control" id="oldPassword">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">请输入新密码:</label>
			    <div class="col-sm-10">
			      <input name="newPassword1" type="password" class="form-control" id="newPassword1">
			      <span id="helpBlock2"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">再次输入新密码:</label>
			    <div class="col-sm-10">
			      <input name="newPassword2" type="password" class="form-control" id="newPassword2">
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
	</div>
	</body>
</HTML>
