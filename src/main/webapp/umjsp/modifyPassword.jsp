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
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/common/validation/jquery.validate.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/common/validation/messages_zh.js"></script>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/all.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/pop.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
		<%-- <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
		</script> --%>
		<script type="text/javascript">
			/* var oldPassword = $("#oldPassword").val();
			var newPassword1 = $("#newPassword1").val();
			var newPassword1 = $("#newPassword1").val();
			//进行前端校验
			if(){
				
			}
			alert(oldPassword);
				$("#advert_add").submit(); */
				$().ready(function() {  
					$("#manager_modify").validate({
			            /*errorLabelContainer: "#messageBox",       //显示错误信息的容器ID 
			            wrapper: "li",                              //包含每个错误信息的容器*/  
			            rules:{  
			                xm:{  
			                    required: true,  
			                    minlength: 2,  
			                    maxlength: 5  
			                },  
			                pwd:{  
			                    required: true,  
			                    minlength: 6  
			                },  
			                confirm_pwd:{  
			                    required: true,  
			                    equalTo: "#pwd"  
			                },  
			                f2csrq:{  
			                    required: true,  
			                    date: true  
			                },  
			                f2xjzd: {  
			                    required: true    
			                },  
			                f2sfzh:{  
			                    /*digits: true, 
			                    rangelength: [18,20]*/  
			                    required: true,  
			                    isIdCardNo: true  
			                }  
			            },  
			            messages:{  
			                /* xm:{  
			                    required: "请填写姓名",  
			                    minlength: "字符长度不能小于2个字符",  
			                    maxlength: "字符长度不能大于5个字符"  
			                }, */  
			                np1:{  
			                    required: "请填写密码",  
			                    minlength: "字符长度不能小于6个字符"  
			                },  
			                confirm_pwd:{  
			                    required: "请再次输入密码",  
			                    equalTo: "密码不一致"  
			                },  
			                f2csrq:{  
			                    required: "请输入出生日期",  
			                    date: "日期格式不正确(例：2009/04/07)"  
			                },  
			                f2xjzd:{  
			                    required: "请输入地址"     
			                },  
			                f2sfzh:{  
			                    /*digits: "身份证号码只能为数字", 
			                    rangelength: "身份号码长度为18～20个字符"*/  
			                    required: "请输入身份证号",  
			                    isIdCardNo: "身份证号不正确"  
			                }  
			            }  
			        });  
		</script>
	</HEAD>
	<body style="background: #e1e9eb;">
		<form id="manager_modify" name="Form1" action="${pageContext.request.contextPath}/advertModify" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${advert.id}"/>
			<div class="right"  style="background: #f5fafe;">
				<div class="rightCont">
					<p class="g_title fix">管理员密码修改</p>
					<table class="tab1" width="100%">
						<tbody>
							<tr>
								<td align="center" width="5%">
								<label for="op">请输入旧密码:<font color="red">*</font></label></td>
								<td width="30%">
									<input id="oldPassword" name="oldPassword" class="allInput" style="width:60%;" type="text"/>
								</td>
							</tr>
							<tr>
								<td align="center" width="5%"><label for="np1">请输入新密码:<font color="red">*</font></label></td>
								<td width="30%">
									<input id="newPassword1" name="newPassword1" class="allInput" style="width:60%;" type="text"/>
									<span id="result_password"></span>
								</td>
							</tr>
							<tr>
								<td align="center" width="5%"><label for="np2">再次输入新密码:<font color="red">*</font></label></td>
								<td width="30%">
									<input id="newPassword2" name="newPassword2" class="allInput" style="width:60%;" type="text"/>
								</td>
							</tr>
					  </tbody>
					</table>
					<div style="text-align: center; margin-top: 30px;">
						<input class="tabSub" value="保     存" type="submit"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="tabSub" value="返     回" type="button" onclick="location.href = '${pageContext.request.contextPath}/umjsp/welcome.jsp'"/>
					</div>
				</div>
			</div>
		</form>
	</body>
</HTML>

